package androidx.media3.exoplayer.analytics;

import androidx.media3.common.FlagSet;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda29 implements ListenerSet.IterationFinishedEvent {
    public final /* synthetic */ DefaultAnalyticsCollector f$0;
    public final /* synthetic */ Player f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda29(DefaultAnalyticsCollector defaultAnalyticsCollector, Player player) {
        this.f$0 = defaultAnalyticsCollector;
        this.f$1 = player;
    }

    public final void invoke(Object obj, FlagSet flagSet) {
        this.f$0.m488lambda$setPlayer$1$androidxmedia3exoplayeranalyticsDefaultAnalyticsCollector(this.f$1, (AnalyticsListener) obj, flagSet);
    }
}
