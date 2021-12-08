package optimistic.lock

import optimistic.lock.answeroption.AnswerOption
import optimistic.lock.image.ImageSingleChoiceQuestion
import optimistic.lock.testframework.ApplicationContextSpecification
import optimistic.lock.testframework.testcontainers.MariaDbFixture

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

class NewOptimisticLockSpec extends ApplicationContextSpecification
        implements MariaDbFixture {

    @Override
    Map<String, Object> getCustomConfiguration() {
        [
                "datasources.default.url"     : "jdbc:mariadb://localhost:${getMariaDbConfiguration().get("port")}/opti",
                "datasources.default.username": getMariaDbConfiguration().get("username"),
                "datasources.default.password": getMariaDbConfiguration().get("password"),
        ]
    }

    GameRepository gameRepository
    QuestionRepository questionRepository

    TestDataProvider testDataProvider
    GameTestSvc gameTestSvc

    EntityManagerFactory entityManagerFactory
    EntityManager entityManager

    @SuppressWarnings('unused')
    void setup() {
        gameRepository = applicationContext.getBean(GameRepository)
        questionRepository = applicationContext.getBean(QuestionRepository)

        testDataProvider = applicationContext.getBean(TestDataProvider)
        gameTestSvc = applicationContext.getBean(GameTestSvc)

        entityManagerFactory = applicationContext.getBean(EntityManagerFactory)
        entityManager = entityManagerFactory.createEntityManager()
    }

    void "when removing question from game, game has no longer any questions"() {
        given:
        def testGame = testDataProvider.saveTestGame()
        and:
        Game game = gameRepository.findById(testGame.getId()).orElseThrow()
        and:
        Question question = testGame.questions.first()
        assert question != null
        and:
        def validAnswer = ((ImageSingleChoiceQuestion)question).getValidAnswer()
        assert validAnswer != null

        when:
        gameTestSvc.removeQuestionFromGame(game.getId(), question.getId())

        then:
        noExceptionThrown()
        and:
        0 == entityManager.find(Game.class, game.getId()).getQuestions().size()
        and:
        null == entityManager.find(AnswerOption.class, validAnswer.getId())
    }
}
