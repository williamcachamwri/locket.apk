package androidx.media3.exoplayer.offline;

import android.os.Handler;
import android.os.Message;
import androidx.media3.exoplayer.offline.DownloadHelper;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DownloadHelper$MediaPreparer$$ExternalSyntheticLambda0 implements Handler.Callback {
    public final /* synthetic */ DownloadHelper.MediaPreparer f$0;

    public /* synthetic */ DownloadHelper$MediaPreparer$$ExternalSyntheticLambda0(DownloadHelper.MediaPreparer mediaPreparer) {
        this.f$0 = mediaPreparer;
    }

    public final boolean handleMessage(Message message) {
        return this.f$0.handleDownloadHelperCallbackMessage(message);
    }
}
