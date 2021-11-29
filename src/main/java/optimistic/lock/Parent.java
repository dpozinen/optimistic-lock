package optimistic.lock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "parent")
public class Parent extends BaseEntity {

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(name = "parent_child", joinColumns = @JoinColumn(name = "parent_id"),
  inverseJoinColumns = @JoinColumn(name = "child_id"))
  @ToString.Exclude
  private Collection<Child> children;

  static Parent withChildren(Child... children) {
    var parent = new Parent();
    parent.children = Arrays.asList(children);
    return parent;
  }

  public static Parent populate() {
    var parent = new Parent();
    parent.children = new ArrayList<>(Arrays.asList(Child.populate(), Child.populate(), Child.populate()));
    return parent;
  }
}
