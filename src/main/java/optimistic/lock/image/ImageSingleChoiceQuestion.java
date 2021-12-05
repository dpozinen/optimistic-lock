package optimistic.lock.image;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import optimistic.lock.Question;
import optimistic.lock.answeroption.AnswerOption;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity(name = "ImageSingleChoiceQuestion")
@Table(name = "image_single_choice_question")
public class ImageSingleChoiceQuestion extends Question {

    @OneToMany
    private Set<AnswerOption> validAnswers = new HashSet<>();

    public AnswerOption getValidAnswer() {
        return validAnswers.stream().findFirst().orElse(null);
    }

    public void setValidAnswer(final AnswerOption answerOption) {
        validAnswers.clear();
        validAnswers.add(answerOption);
    }
}
