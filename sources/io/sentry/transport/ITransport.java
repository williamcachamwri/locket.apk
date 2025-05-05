package io.sentry.transport;

import io.sentry.Hint;
import io.sentry.SentryEnvelope;
import java.io.Closeable;
import java.io.IOException;

public interface ITransport extends Closeable {
    void flush(long j);

    void send(SentryEnvelope sentryEnvelope, Hint hint) throws IOException;

    void send(SentryEnvelope sentryEnvelope) throws IOException {
        send(sentryEnvelope, new Hint());
    }
}
