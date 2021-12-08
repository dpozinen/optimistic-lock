package optimistic.lock.testframework

trait ConfigurationFixture {
    Map<String, Object> getConfiguration() {
        Map<String, Object> m = [:]
        if (customConfiguration) {
            m += customConfiguration
        }
        m
    }

    Map<String, Object> getCustomConfiguration() {
        null
    }
}