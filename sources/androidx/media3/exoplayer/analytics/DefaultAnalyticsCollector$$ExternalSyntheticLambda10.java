package androidx.media3.exoplayer.analytics;

import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda10 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ PlaybackParameters f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda10(AnalyticsListener.EventTime eventTime, PlaybackParameters playbackParameters) {
        this.f$0 = eventTime;
        this.f$1 = playbackParameters;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onPlaybackParametersChanged(this.f$0, this.f$1);
    }
}
