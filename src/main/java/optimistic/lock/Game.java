
package optimistic.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import optimistic.lock.image.ImageSingleChoiceQuestion;

@Getter
@Setter
@ToString
@Entity
@Table(name = "game")
public class Game extends BaseEntity {

  /**
   * Business related identifier that helps a human to refer to the game.
   *
   * <p>Examples are "game.1" or "question.human.body.skull"
   *
   * <p>The business key will be used to fetch localize question texts from the RMS.
   *
   * <p>The question text for the business key "g.1" would be "g.1.game" by convention
   */
  public UUID getMessageKey() {
    return getId();
  }

  @Column(length = 150, unique = true)
  private String businessKey;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Type type;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  @OrderColumn(name = "sort_index")
  private List<Question> questions = new ArrayList<>();

  public enum Type {
    PUZZLE,
    IMAGE,
    MEMORY,
    MULTI_CHOICE
  }
}
