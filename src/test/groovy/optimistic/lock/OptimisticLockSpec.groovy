package optimistic.lock

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification
import jakarta.inject.Inject

@MicronautTest
class OptimisticLockSpec extends Specification {

    @Inject
    ParentRepo parentRepo

    @Inject
    ChildRepo childRepo

    void setup() {
        def parent = Parent.populate()
        parentRepo.saveAndFlush(parent)
    }

    void 'test it works'() {
        given:
        def persistedChild = childRepo.findAll()[0]
        def persistedParent = parentRepo.findAll()[0]

        when:
        def before = childRepo.findAll().size()
        persistedParent.children.remove(persistedChild)
        parentRepo.saveAndFlush(persistedParent)

        then:
        noExceptionThrown()

        and:
        childRepo.findAll().size() < before
    }

}
