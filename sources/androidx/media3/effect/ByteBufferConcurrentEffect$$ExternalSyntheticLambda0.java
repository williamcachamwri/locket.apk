package androidx.media3.effect;

import androidx.media3.effect.ByteBufferGlEffect;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ByteBufferConcurrentEffect$$ExternalSyntheticLambda0 implements AsyncFunction {
    public final /* synthetic */ ByteBufferConcurrentEffect f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ ByteBufferConcurrentEffect$$ExternalSyntheticLambda0(ByteBufferConcurrentEffect byteBufferConcurrentEffect, long j) {
        this.f$0 = byteBufferConcurrentEffect;
        this.f$1 = j;
    }

    public final ListenableFuture apply(Object obj) {
        return this.f$0.m413lambda$queueInputFrame$0$androidxmedia3effectByteBufferConcurrentEffect(this.f$1, (ByteBufferGlEffect.Image) obj);
    }
}
