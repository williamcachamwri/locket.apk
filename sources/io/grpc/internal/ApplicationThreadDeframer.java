package io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import io.grpc.Decompressor;
import io.grpc.internal.ApplicationThreadDeframerListener;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

public class ApplicationThreadDeframer implements Deframer {
    /* access modifiers changed from: private */
    public final ApplicationThreadDeframerListener appListener;
    /* access modifiers changed from: private */
    public final MessageDeframer deframer;
    private final MessageDeframer.Listener storedListener;

    interface TransportExecutor extends ApplicationThreadDeframerListener.TransportExecutor {
    }

    ApplicationThreadDeframer(MessageDeframer.Listener listener, TransportExecutor transportExecutor, MessageDeframer messageDeframer) {
        SquelchLateMessagesAvailableDeframerListener squelchLateMessagesAvailableDeframerListener = new SquelchLateMessagesAvailableDeframerListener((MessageDeframer.Listener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER));
        this.storedListener = squelchLateMessagesAvailableDeframerListener;
        ApplicationThreadDeframerListener applicationThreadDeframerListener = new ApplicationThreadDeframerListener(squelchLateMessagesAvailableDeframerListener, transportExecutor);
        this.appListener = applicationThreadDeframerListener;
        messageDeframer.setListener(applicationThreadDeframerListener);
        this.deframer = messageDeframer;
    }

    public void setMaxInboundMessageSize(int i) {
        this.deframer.setMaxInboundMessageSize(i);
    }

    public void setDecompressor(Decompressor decompressor) {
        this.deframer.setDecompressor(decompressor);
    }

    public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
        this.deframer.setFullStreamDecompressor(gzipInflatingBuffer);
    }

    public void request(final int i) {
        this.storedListener.messagesAvailable(new InitializingMessageProducer(new Runnable() {
            public void run() {
                if (!ApplicationThreadDeframer.this.deframer.isClosed()) {
                    try {
                        ApplicationThreadDeframer.this.deframer.request(i);
                    } catch (Throwable th) {
                        ApplicationThreadDeframer.this.appListener.deframeFailed(th);
                        ApplicationThreadDeframer.this.deframer.close();
                    }
                }
            }
        }));
    }

    public void deframe(final ReadableBuffer readableBuffer) {
        this.storedListener.messagesAvailable(new CloseableInitializingMessageProducer(new Runnable() {
            public void run() {
                try {
                    ApplicationThreadDeframer.this.deframer.deframe(readableBuffer);
                } catch (Throwable th) {
                    ApplicationThreadDeframer.this.appListener.deframeFailed(th);
                    ApplicationThreadDeframer.this.deframer.close();
                }
            }
        }, new Closeable() {
            public void close() {
                readableBuffer.close();
            }
        }));
    }

    public void closeWhenComplete() {
        this.storedListener.messagesAvailable(new InitializingMessageProducer(new Runnable() {
            public void run() {
                ApplicationThreadDeframer.this.deframer.closeWhenComplete();
            }
        }));
    }

    public void close() {
        this.deframer.stopDelivery();
        this.storedListener.messagesAvailable(new InitializingMessageProducer(new Runnable() {
            public void run() {
                ApplicationThreadDeframer.this.deframer.close();
            }
        }));
    }

    /* access modifiers changed from: package-private */
    public MessageDeframer.Listener getAppListener() {
        return this.appListener;
    }

    private class InitializingMessageProducer implements StreamListener.MessageProducer {
        private boolean initialized;
        private final Runnable runnable;

        private InitializingMessageProducer(Runnable runnable2) {
            this.initialized = false;
            this.runnable = runnable2;
        }

        private void initialize() {
            if (!this.initialized) {
                this.runnable.run();
                this.initialized = true;
            }
        }

        @Nullable
        public InputStream next() {
            initialize();
            return ApplicationThreadDeframer.this.appListener.messageReadQueuePoll();
        }
    }

    private class CloseableInitializingMessageProducer extends InitializingMessageProducer implements Closeable {
        private final Closeable closeable;

        public CloseableInitializingMessageProducer(Runnable runnable, Closeable closeable2) {
            super(runnable);
            this.closeable = closeable2;
        }

        public void close() throws IOException {
            this.closeable.close();
        }
    }
}
