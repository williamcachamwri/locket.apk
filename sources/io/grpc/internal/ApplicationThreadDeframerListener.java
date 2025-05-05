package io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Queue;

final class ApplicationThreadDeframerListener implements MessageDeframer.Listener {
    private final Queue<InputStream> messageReadQueue = new ArrayDeque();
    /* access modifiers changed from: private */
    public final MessageDeframer.Listener storedListener;
    private final TransportExecutor transportExecutor;

    public interface TransportExecutor {
        void runOnTransportThread(Runnable runnable);
    }

    public ApplicationThreadDeframerListener(MessageDeframer.Listener listener, TransportExecutor transportExecutor2) {
        this.storedListener = (MessageDeframer.Listener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.transportExecutor = (TransportExecutor) Preconditions.checkNotNull(transportExecutor2, "transportExecutor");
    }

    public void bytesRead(final int i) {
        this.transportExecutor.runOnTransportThread(new Runnable() {
            public void run() {
                ApplicationThreadDeframerListener.this.storedListener.bytesRead(i);
            }
        });
    }

    public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
        while (true) {
            InputStream next = messageProducer.next();
            if (next != null) {
                this.messageReadQueue.add(next);
            } else {
                return;
            }
        }
    }

    public void deframerClosed(final boolean z) {
        this.transportExecutor.runOnTransportThread(new Runnable() {
            public void run() {
                ApplicationThreadDeframerListener.this.storedListener.deframerClosed(z);
            }
        });
    }

    public void deframeFailed(final Throwable th) {
        this.transportExecutor.runOnTransportThread(new Runnable() {
            public void run() {
                ApplicationThreadDeframerListener.this.storedListener.deframeFailed(th);
            }
        });
    }

    public InputStream messageReadQueuePoll() {
        return this.messageReadQueue.poll();
    }
}
