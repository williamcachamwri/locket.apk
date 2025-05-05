package io.sentry;

public interface Integration extends IntegrationName {
    void register(IHub iHub, SentryOptions sentryOptions);
}
