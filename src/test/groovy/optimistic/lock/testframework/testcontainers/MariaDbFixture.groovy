package optimistic.lock.testframework.testcontainers

trait MariaDbFixture {
    Map<String, Object> getMariaDbConfiguration() {
        if (MariaDb.mariaDBContainer == null || !MariaDb.mariaDBContainer.isRunning()) {
            MariaDb.init()
        }
        [
                "port"    : MariaDb.mariaDBContainer.getFirstMappedPort().toString(),
                "db"      : MariaDb.mariaDBContainer.getDatabaseName(),
                "username": MariaDb.mariaDBContainer.getUsername(),
                "password": MariaDb.mariaDBContainer.getPassword()
        ]
    }
}