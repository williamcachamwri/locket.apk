package androidx.media3.transformer;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Util;
import androidx.media3.transformer.TransformationRequest;
import androidx.media3.transformer.Transformer;
import java.util.concurrent.atomic.AtomicInteger;

final class FallbackListener {
    private final Composition composition;
    private TransformationRequest fallbackTransformationRequest;
    private final TransformationRequest originalTransformationRequest;
    private final AtomicInteger trackCount = new AtomicInteger();
    private final HandlerWrapper transformerListenerHandler;
    private final ListenerSet<Transformer.Listener> transformerListeners;

    public FallbackListener(Composition composition2, ListenerSet<Transformer.Listener> listenerSet, HandlerWrapper handlerWrapper, TransformationRequest transformationRequest) {
        this.composition = composition2;
        this.transformerListeners = listenerSet;
        this.transformerListenerHandler = handlerWrapper;
        this.originalTransformationRequest = transformationRequest;
        this.fallbackTransformationRequest = transformationRequest;
    }

    public void setTrackCount(int i) {
        this.trackCount.set(i);
    }

    public synchronized void onTransformationRequestFinalized(TransformationRequest transformationRequest) {
        Assertions.checkState(this.trackCount.getAndDecrement() > 0);
        TransformationRequest.Builder buildUpon = this.fallbackTransformationRequest.buildUpon();
        if (!Util.areEqual(transformationRequest.audioMimeType, this.originalTransformationRequest.audioMimeType)) {
            buildUpon.setAudioMimeType(transformationRequest.audioMimeType);
        }
        if (!Util.areEqual(transformationRequest.videoMimeType, this.originalTransformationRequest.videoMimeType)) {
            buildUpon.setVideoMimeType(transformationRequest.videoMimeType);
        }
        if (transformationRequest.outputHeight != this.originalTransformationRequest.outputHeight) {
            buildUpon.setResolution(transformationRequest.outputHeight);
        }
        if (transformationRequest.hdrMode != this.originalTransformationRequest.hdrMode) {
            buildUpon.setHdrMode(transformationRequest.hdrMode);
        }
        TransformationRequest build = buildUpon.build();
        this.fallbackTransformationRequest = build;
        if (this.trackCount.get() == 0 && !this.originalTransformationRequest.equals(this.fallbackTransformationRequest)) {
            this.transformerListenerHandler.post(new FallbackListener$$ExternalSyntheticLambda1(this, build));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTransformationRequestFinalized$1$androidx-media3-transformer-FallbackListener  reason: not valid java name */
    public /* synthetic */ void m1124lambda$onTransformationRequestFinalized$1$androidxmedia3transformerFallbackListener(TransformationRequest transformationRequest) {
        this.transformerListeners.sendEvent(-1, new FallbackListener$$ExternalSyntheticLambda0(this, transformationRequest));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTransformationRequestFinalized$0$androidx-media3-transformer-FallbackListener  reason: not valid java name */
    public /* synthetic */ void m1123lambda$onTransformationRequestFinalized$0$androidxmedia3transformerFallbackListener(TransformationRequest transformationRequest, Transformer.Listener listener) {
        listener.onFallbackApplied(this.composition, this.originalTransformationRequest, transformationRequest);
    }
}
