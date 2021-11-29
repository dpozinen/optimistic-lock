
package optimistic.lock.image;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import optimistic.lock.Question;
import optimistic.lock.answeroption.AnswerOption;

@Entity(name = "ImageSingleChoiceQuestion")
@Table(name = "image_single_choice_question")
@Getter
@Setter
@ToString
public class ImageSingleChoiceQuestion extends Question {

  @OneToOne(cascade = CascadeType.ALL)
  private AnswerOption validAnswer;

}
