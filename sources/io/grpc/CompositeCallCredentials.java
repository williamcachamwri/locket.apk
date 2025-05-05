package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.CallCredentials;
import java.util.concurrent.Executor;

public final class CompositeCallCredentials extends CallCredentials {
    private final CallCredentials credentials1;
    /* access modifiers changed from: private */
    public final CallCredentials credentials2;

    public CompositeCallCredentials(CallCredentials callCredentials, CallCredentials callCredentials2) {
        this.credentials1 = (CallCredentials) Preconditions.checkNotNull(callCredentials, "creds1");
        this.credentials2 = (CallCredentials) Preconditions.checkNotNull(callCredentials2, "creds2");
    }

    public void applyRequestMetadata(CallCredentials.RequestInfo requestInfo, Executor executor, CallCredentials.MetadataApplier metadataApplier) {
        this.credentials1.applyRequestMetadata(requestInfo, executor, new WrappingMetadataApplier(requestInfo, executor, metadataApplier, Context.current()));
    }

    private final class WrappingMetadataApplier extends CallCredentials.MetadataApplier {
        private final Executor appExecutor;
        private final Context context;
        private final CallCredentials.MetadataApplier delegate;
        private final CallCredentials.RequestInfo requestInfo;

        public WrappingMetadataApplier(CallCredentials.RequestInfo requestInfo2, Executor executor, CallCredentials.MetadataApplier metadataApplier, Context context2) {
            this.requestInfo = requestInfo2;
            this.appExecutor = executor;
            this.delegate = (CallCredentials.MetadataApplier) Preconditions.checkNotNull(metadataApplier, "delegate");
            this.context = (Context) Preconditions.checkNotNull(context2, "context");
        }

        public void apply(Metadata metadata) {
            Preconditions.checkNotNull(metadata, "headers");
            Context attach = this.context.attach();
            try {
                CompositeCallCredentials.this.credentials2.applyRequestMetadata(this.requestInfo, this.appExecutor, new CombiningMetadataApplier(this.delegate, metadata));
            } finally {
                this.context.detach(attach);
            }
        }

        public void fail(Status status) {
            this.delegate.fail(status);
        }
    }

    private static final class CombiningMetadataApplier extends CallCredentials.MetadataApplier {
        private final CallCredentials.MetadataApplier delegate;
        private final Metadata firstHeaders;

        public CombiningMetadataApplier(CallCredentials.MetadataApplier metadataApplier, Metadata metadata) {
            this.delegate = metadataApplier;
            this.firstHeaders = metadata;
        }

        public void apply(Metadata metadata) {
            Preconditions.checkNotNull(metadata, "headers");
            Metadata metadata2 = new Metadata();
            metadata2.merge(this.firstHeaders);
            metadata2.merge(metadata);
            this.delegate.apply(metadata2);
        }

        public void fail(Status status) {
            this.delegate.fail(status);
        }
    }
}
