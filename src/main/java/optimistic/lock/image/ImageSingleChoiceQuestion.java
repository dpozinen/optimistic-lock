package optimistic.lock.image;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import optimistic.lock.Question;
import optimistic.lock.answeroption.AnswerOption;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "ImageSingleChoiceQuestion")
@Table(name = "image_single_choice_question")
public class ImageSingleChoiceQuestion extends Question {

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AnswerOption validAnswer;
}
