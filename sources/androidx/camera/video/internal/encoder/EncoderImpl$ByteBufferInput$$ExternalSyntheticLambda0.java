package androidx.camera.video.internal.encoder;

import androidx.camera.core.impl.Observable;
import androidx.camera.video.internal.BufferProvider;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Map.Entry f$0;
    public final /* synthetic */ BufferProvider.State f$1;

    public /* synthetic */ EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda0(Map.Entry entry, BufferProvider.State state) {
        this.f$0 = entry;
        this.f$1 = state;
    }

    public final void run() {
        ((Observable.Observer) this.f$0.getKey()).onNewData(this.f$1);
    }
}
