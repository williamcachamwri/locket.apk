package androidx.camera.video;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.camera.video.Recorder;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$RecordingRecord$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ ParcelFileDescriptor f$0;

    public /* synthetic */ Recorder$RecordingRecord$$ExternalSyntheticLambda3(ParcelFileDescriptor parcelFileDescriptor) {
        this.f$0 = parcelFileDescriptor;
    }

    public final void accept(Object obj) {
        Recorder.RecordingRecord.lambda$initializeRecording$5(this.f$0, (Uri) obj);
    }
}
