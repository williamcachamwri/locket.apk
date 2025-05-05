package androidx.camera.video;

import android.net.Uri;
import androidx.camera.video.Recorder;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$RecordingRecord$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ MediaStoreOutputOptions f$0;

    public /* synthetic */ Recorder$RecordingRecord$$ExternalSyntheticLambda1(MediaStoreOutputOptions mediaStoreOutputOptions) {
        this.f$0 = mediaStoreOutputOptions;
    }

    public final void accept(Object obj) {
        Recorder.RecordingRecord.lambda$initializeRecording$2(this.f$0, (Uri) obj);
    }
}
