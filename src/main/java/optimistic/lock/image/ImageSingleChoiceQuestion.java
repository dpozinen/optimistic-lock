package optimistic.lock.image;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import optimistic.lock.Question;
import optimistic.lock.answeroption.AnswerOption;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "ImageSingleChoiceQuestion")
@Table(name = "image_single_choice_question")
public class ImageSingleChoiceQuestion extends Question {

    @OneToOne(cascade = CascadeType.ALL)
    private AnswerOption validAnswer;
}

