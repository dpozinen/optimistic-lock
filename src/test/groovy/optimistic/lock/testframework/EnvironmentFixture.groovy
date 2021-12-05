package optimistic.lock.testframework

trait EnvironmentFixture {
    String[] getEnvironments() {
        String[] environments = []
        if (additionalEnvironments) {
            environments += additionalEnvironments
        }
        environments
    }

    String[] getAdditionalEnvironments() {
        null
    }
}