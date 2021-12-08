package optimistic.lock.testframework.testcontainers

import org.testcontainers.containers.MariaDBContainer

class MariaDb {
    static MariaDBContainer mariaDBContainer

    static init() {
        if (mariaDBContainer == null) {
            mariaDBContainer = new MariaDBContainer()
                    .withDatabaseName("opti")
                    .withUsername("username")
                    .withPassword("P4ssword")
            mariaDBContainer.start()
        }
    }
}
