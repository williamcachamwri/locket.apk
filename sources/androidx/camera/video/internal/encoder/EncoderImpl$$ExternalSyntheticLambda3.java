package androidx.camera.video.internal.encoder;

import androidx.camera.video.internal.encoder.EncoderImpl;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ EncoderImpl.MediaCodecCallback f$1;

    public /* synthetic */ EncoderImpl$$ExternalSyntheticLambda3(Executor executor, EncoderImpl.MediaCodecCallback mediaCodecCallback) {
        this.f$0 = executor;
        this.f$1 = mediaCodecCallback;
    }

    public final void run() {
        EncoderImpl.lambda$addSignalEosTimeoutIfNeeded$9(this.f$0, this.f$1);
    }
}
