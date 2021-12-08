package optimistic.lock;

import jakarta.inject.Singleton;
import optimistic.lock.image.ImageSingleChoiceQuestion;

import javax.transaction.Transactional;
import java.util.UUID;

@Singleton
public class GameTestSvc {

    private final GameRepository gameRepository;

    public GameTestSvc(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public void removeQuestionFromGame(final UUID gameId, final UUID questionId) {
        var game = gameRepository.findById(gameId).get();
        var questionToRemove = (ImageSingleChoiceQuestion)game.getQuestions().stream()
                .filter(it -> it.getId().equals(questionId))
                .findFirst()
                .get();

        game.getQuestions().remove(questionToRemove);
        gameRepository.update(game);
    }
}
