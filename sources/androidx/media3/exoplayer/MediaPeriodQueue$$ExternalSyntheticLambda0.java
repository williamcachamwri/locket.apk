package androidx.media3.exoplayer;

import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.collect.ImmutableList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaPeriodQueue$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaPeriodQueue f$0;
    public final /* synthetic */ ImmutableList.Builder f$1;
    public final /* synthetic */ MediaSource.MediaPeriodId f$2;

    public /* synthetic */ MediaPeriodQueue$$ExternalSyntheticLambda0(MediaPeriodQueue mediaPeriodQueue, ImmutableList.Builder builder, MediaSource.MediaPeriodId mediaPeriodId) {
        this.f$0 = mediaPeriodQueue;
        this.f$1 = builder;
        this.f$2 = mediaPeriodId;
    }

    public final void run() {
        this.f$0.m474lambda$notifyQueueUpdate$0$androidxmedia3exoplayerMediaPeriodQueue(this.f$1, this.f$2);
    }
}
