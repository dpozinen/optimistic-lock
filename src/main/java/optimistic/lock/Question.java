
package optimistic.lock;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import optimistic.lock.answeroption.AnswerOption;

@Setter
@Getter
@Entity
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "question")
public abstract class Question extends BaseEntity {


  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  @OrderColumn(name = "sort_index")
  private List<AnswerOption> answerOptions = new ArrayList<>();
}
