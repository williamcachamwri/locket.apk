package io.grpc.internal;

import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import java.io.Closeable;

final class SquelchLateMessagesAvailableDeframerListener extends ForwardingDeframerListener {
    private boolean closed;
    private final MessageDeframer.Listener delegate;

    public SquelchLateMessagesAvailableDeframerListener(MessageDeframer.Listener listener) {
        this.delegate = listener;
    }

    /* access modifiers changed from: protected */
    public MessageDeframer.Listener delegate() {
        return this.delegate;
    }

    public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
        if (!this.closed) {
            super.messagesAvailable(messageProducer);
        } else if (messageProducer instanceof Closeable) {
            GrpcUtil.closeQuietly((Closeable) messageProducer);
        }
    }

    public void deframerClosed(boolean z) {
        this.closed = true;
        super.deframerClosed(z);
    }

    public void deframeFailed(Throwable th) {
        this.closed = true;
        super.deframeFailed(th);
    }
}
