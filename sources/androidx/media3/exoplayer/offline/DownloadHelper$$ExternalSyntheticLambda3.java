package androidx.media3.exoplayer.offline;

import java.io.IOException;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DownloadHelper$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ DownloadHelper f$0;
    public final /* synthetic */ IOException f$1;

    public /* synthetic */ DownloadHelper$$ExternalSyntheticLambda3(DownloadHelper downloadHelper, IOException iOException) {
        this.f$0 = downloadHelper;
        this.f$1 = iOException;
    }

    public final void run() {
        this.f$0.m532lambda$onMediaPreparationFailed$5$androidxmedia3exoplayerofflineDownloadHelper(this.f$1);
    }
}
