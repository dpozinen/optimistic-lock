package optimistic.lock


import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import optimistic.lock.answeroption.AnswerOption
import optimistic.lock.image.ImageSingleChoiceQuestion
import spock.lang.Shared
import spock.lang.Specification

import java.util.concurrent.ThreadLocalRandom

@MicronautTest
class OptimisticLockSpec extends Specification {

    @Inject
    GameRepository gameRepository

    @Inject
    QuestionRepository questionRepository

    @Shared
    UUID gameId

    @Shared
    UUID questionId

    void setup() {
        def option = new AnswerOption()
        def aos = [option, new AnswerOption(), new AnswerOption(), new AnswerOption()]

        def question = new ImageSingleChoiceQuestion()
        question.answerOptions = aos
        question.validAnswer = aos[0]

        def game = new Game()
        game.questions = [question]
        game.type = Game.Type.IMAGE
        game.businessKey = ThreadLocalRandom.current().nextInt()
        gameRepository.saveAndFlush(game)

        gameId = game.id
        questionId = game.questions[0].id
//
//        def persistedQ = questionRepository.findById(questionId).get() as ImageSingleChoiceQuestion
//        persistedQ.answerOptions.add(new AnswerOption())
//        questionRepository.saveAndFlush(persistedQ)
//
//        def persistedG = gameRepository.findById(gameId).get()

//        gameRepository.saveAndFlush(persistedG)
    }

    void 'test it works'() {
        given:
        Game game = gameRepository.findById(gameId).orElseThrow()
        Question question = questionRepository.findByGameAndId(gameId, questionId).orElseThrow()

        when:
        game.getQuestions().remove(question)
        gameRepository.saveAndFlush(game)

        then:
        noExceptionThrown()
    }

    /*
    notice the
     -> update question set version=2 ...
     ...
     -> delete from question where id= ... and version=1;

    update game set version=2, business_key='82234840', type='IMAGE' where id='ee99254a-8ce8-484f-9833-4e79eedd68b9' and version=1;
    update question set version=2 where id='32e00fb6-c9ed-42b4-ac4e-51224307e86e' and version=1;
    update image_single_choice_question set valid_answer_id='null' where id='32e00fb6-c9ed-42b4-ac4e-51224307e86e';
    delete from question_answer_options where question_id='32e00fb6-c9ed-42b4-ac4e-51224307e86e';
    delete from game_questions where game_id='ee99254a-8ce8-484f-9833-4e79eedd68b9';
    delete from answer_option where id='21b8b639-aa91-4e05-b797-341e520f95db' and version=0;
    delete from answer_option where id='5cda5e33-b808-43cb-9646-b0992743c73e' and version=0;
    delete from answer_option where id='29cb5c88-78fd-47cd-ae29-51752265c0ee' and version=0;
    delete from answer_option where id='219b2b29-ac10-4686-a617-20f09f6d1508' and version=0;
    delete from answer_option where id='17491630-983c-47c8-aa5d-a46d894e1c56' and version=0;
    delete from image_single_choice_question where id='32e00fb6-c9ed-42b4-ac4e-51224307e86e';
    delete from question where id='32e00fb6-c9ed-42b4-ac4e-51224307e86e' and version=1;

     */

}
