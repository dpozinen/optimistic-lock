package optimistic.lock.extra;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitJoinTableNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;


public class JpaImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {
  @Override
  public Identifier determineJoinTableName(ImplicitJoinTableNameSource source) {
    String name =
        source.getOwningPhysicalTableName()
            + "_"
            + source.getAssociationOwningAttributePath().getProperty();
    return toIdentifier(name, source.getBuildingContext());
  }
}
