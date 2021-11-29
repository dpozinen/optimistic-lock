
package optimistic.lock;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

  @Query(
      """
      select q from Question q, Game g
      where g.id = :game
      and q in elements(g.questions)
      and q.id = :id
      """)
  Optional<Question> findByGameAndId(UUID game, UUID id);

}
