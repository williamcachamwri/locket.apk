package androidx.media3.exoplayer.analytics;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda31 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ MediaMetadata f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda31(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
        this.f$0 = eventTime;
        this.f$1 = mediaMetadata;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onPlaylistMetadataChanged(this.f$0, this.f$1);
    }
}
