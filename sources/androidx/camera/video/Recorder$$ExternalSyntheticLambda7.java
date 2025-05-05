package androidx.camera.video;

import androidx.camera.video.Recorder;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$$ExternalSyntheticLambda7 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Recorder f$0;
    public final /* synthetic */ Recorder.RecordingRecord f$1;

    public /* synthetic */ Recorder$$ExternalSyntheticLambda7(Recorder recorder, Recorder.RecordingRecord recordingRecord) {
        this.f$0 = recorder;
        this.f$1 = recordingRecord;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m251lambda$updateEncoderCallbacks$12$androidxcameravideoRecorder(this.f$1, completer);
    }
}
