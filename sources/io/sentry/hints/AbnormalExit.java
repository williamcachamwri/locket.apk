package io.sentry.hints;

public interface AbnormalExit {
    boolean ignoreCurrentThread() {
        return false;
    }

    String mechanism();

    Long timestamp() {
        return null;
    }
}
