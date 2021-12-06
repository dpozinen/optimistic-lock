
package optimistic.lock;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
