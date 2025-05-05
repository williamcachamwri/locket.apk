package androidx.media3.exoplayer.analytics;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda24 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ Player.Commands f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda24(AnalyticsListener.EventTime eventTime, Player.Commands commands) {
        this.f$0 = eventTime;
        this.f$1 = commands;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onAvailableCommandsChanged(this.f$0, this.f$1);
    }
}
