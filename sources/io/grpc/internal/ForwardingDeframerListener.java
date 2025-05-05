package io.grpc.internal;

import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;

abstract class ForwardingDeframerListener implements MessageDeframer.Listener {
    /* access modifiers changed from: protected */
    public abstract MessageDeframer.Listener delegate();

    ForwardingDeframerListener() {
    }

    public void bytesRead(int i) {
        delegate().bytesRead(i);
    }

    public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
        delegate().messagesAvailable(messageProducer);
    }

    public void deframerClosed(boolean z) {
        delegate().deframerClosed(z);
    }

    public void deframeFailed(Throwable th) {
        delegate().deframeFailed(th);
    }
}
