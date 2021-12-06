
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

@Entity
@Getter
@Setter
@ToString
@Table(name = "game")
public class Game extends BaseEntity {

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
