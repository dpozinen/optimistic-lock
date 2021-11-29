package optimistic.lock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "child")
public class Child extends BaseEntity {


  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(name = "child_nested_child", joinColumns = @JoinColumn(name = "child_id"),
      inverseJoinColumns = @JoinColumn(name = "nested_child_id"))
  @ToString.Exclude
  Collection<NestedChild> nestedChildren;

  private String fieldJustForTheSakeOfIt;

  public static Child populate() {
    var child = new Child();
    child.nestedChildren = new ArrayList<>(Arrays.asList(new NestedChild(), new NestedChild()));
    return child;
  }
}
