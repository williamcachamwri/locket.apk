package io.sentry;

public interface IntegrationName {
    String getIntegrationName() {
        return getClass().getSimpleName().replace("Sentry", "").replace("Integration", "").replace("Interceptor", "").replace("EventProcessor", "");
    }

    void addIntegrationToSdkVersion() {
        SentryIntegrationPackageStorage.getInstance().addIntegration(getIntegrationName());
    }
}
