package androidx.camera.video;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ Recorder f$0;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$1;

    public /* synthetic */ Recorder$$ExternalSyntheticLambda0(Recorder recorder, CallbackToFutureAdapter.Completer completer) {
        this.f$0 = recorder;
        this.f$1 = completer;
    }

    public final void accept(Object obj) {
        this.f$0.m250lambda$updateEncoderCallbacks$11$androidxcameravideoRecorder(this.f$1, (Throwable) obj);
    }
}
