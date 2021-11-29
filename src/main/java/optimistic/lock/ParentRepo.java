package optimistic.lock;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import java.util.UUID;

@Repository
public interface ParentRepo extends JpaRepository<Parent, UUID> {

}
