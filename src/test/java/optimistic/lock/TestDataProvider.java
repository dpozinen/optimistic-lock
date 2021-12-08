package optimistic.lock;

import jakarta.inject.Singleton;
import optimistic.lock.answeroption.AnswerOption;
import optimistic.lock.image.ImageSingleChoiceQuestion;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
public class TestDataProvider {

    private final EntityManager entityManager;

    public TestDataProvider(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Game saveTestGame() {
        var option = new AnswerOption();

        var question = new ImageSingleChoiceQuestion();
        question.setAnswerOptions(List.of(option));

        question.setValidAnswer(option);

        var game = new Game();
        game.setQuestions(List.of(question));
        game.setType(Game.Type.IMAGE);
        game.setBusinessKey(Integer.toString(ThreadLocalRandom.current().nextInt()));

        entityManager.persist(game);
        return game;
    }
}
