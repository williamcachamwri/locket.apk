package androidx.media3.exoplayer.analytics;

import androidx.media3.common.VideoSize;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda64 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ VideoSize f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda64(AnalyticsListener.EventTime eventTime, VideoSize videoSize) {
        this.f$0 = eventTime;
        this.f$1 = videoSize;
    }

    public final void invoke(Object obj) {
        DefaultAnalyticsCollector.lambda$onVideoSizeChanged$59(this.f$0, this.f$1, (AnalyticsListener) obj);
    }
}
