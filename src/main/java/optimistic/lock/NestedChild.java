package optimistic.lock;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "nested_child")
public class NestedChild extends BaseEntity {

  private String fieldJustForTheSakeOfIt;

}
