package optimistic.lock.answeroption;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import optimistic.lock.BaseEntity;

@Entity
@Table(name = "answer_option_pair")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnswerOptionPair extends BaseEntity {

  @OneToOne
  private AnswerOption a;

  @OneToOne
  private AnswerOption b;
}
