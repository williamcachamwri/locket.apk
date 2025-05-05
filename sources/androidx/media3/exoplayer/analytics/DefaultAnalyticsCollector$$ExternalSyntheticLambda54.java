package androidx.media3.exoplayer.analytics;

import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda54 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ PlaybackException f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda54(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
        this.f$0 = eventTime;
        this.f$1 = playbackException;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onPlayerErrorChanged(this.f$0, this.f$1);
    }
}
