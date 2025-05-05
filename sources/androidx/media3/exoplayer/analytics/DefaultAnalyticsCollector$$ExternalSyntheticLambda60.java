package androidx.media3.exoplayer.analytics;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda60 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ AudioAttributes f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda60(AnalyticsListener.EventTime eventTime, AudioAttributes audioAttributes) {
        this.f$0 = eventTime;
        this.f$1 = audioAttributes;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onAudioAttributesChanged(this.f$0, this.f$1);
    }
}
