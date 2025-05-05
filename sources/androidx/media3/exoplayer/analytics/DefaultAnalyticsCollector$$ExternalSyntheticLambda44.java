package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.audio.AudioSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda44 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ AudioSink.AudioTrackConfig f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda44(AnalyticsListener.EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig) {
        this.f$0 = eventTime;
        this.f$1 = audioTrackConfig;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onAudioTrackInitialized(this.f$0, this.f$1);
    }
}
