package io.sentry.config;

final class SystemPropertyPropertiesProvider extends AbstractPropertiesProvider {
    private static final String PREFIX = "sentry.";

    public SystemPropertyPropertiesProvider() {
        super(PREFIX, System.getProperties());
    }
}
