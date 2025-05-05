package androidx.camera.video;

import android.content.Context;
import android.net.Uri;
import androidx.camera.video.Recorder;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$RecordingRecord$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ MediaStoreOutputOptions f$0;
    public final /* synthetic */ Context f$1;

    public /* synthetic */ Recorder$RecordingRecord$$ExternalSyntheticLambda2(MediaStoreOutputOptions mediaStoreOutputOptions, Context context) {
        this.f$0 = mediaStoreOutputOptions;
        this.f$1 = context;
    }

    public final void accept(Object obj) {
        Recorder.RecordingRecord.lambda$initializeRecording$4(this.f$0, this.f$1, (Uri) obj);
    }
}
