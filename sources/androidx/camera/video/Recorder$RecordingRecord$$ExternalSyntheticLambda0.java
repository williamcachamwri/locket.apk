package androidx.camera.video;

import android.media.MediaMuxer;
import android.os.ParcelFileDescriptor;
import androidx.camera.video.Recorder;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$RecordingRecord$$ExternalSyntheticLambda0 implements Recorder.RecordingRecord.MediaMuxerSupplier {
    public final /* synthetic */ OutputOptions f$0;
    public final /* synthetic */ ParcelFileDescriptor f$1;

    public /* synthetic */ Recorder$RecordingRecord$$ExternalSyntheticLambda0(OutputOptions outputOptions, ParcelFileDescriptor parcelFileDescriptor) {
        this.f$0 = outputOptions;
        this.f$1 = parcelFileDescriptor;
    }

    public final MediaMuxer get(int i, Consumer consumer) {
        return Recorder.RecordingRecord.lambda$initializeRecording$1(this.f$0, this.f$1, i, consumer);
    }
}
