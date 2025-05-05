package com.brentvatne.exoplayer;

import android.content.Context;
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter;
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/brentvatne/exoplayer/DefaultReactExoplayerConfig;", "Lcom/brentvatne/exoplayer/ReactExoplayerConfig;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "bandWidthMeter", "Landroidx/media3/exoplayer/upstream/DefaultBandwidthMeter;", "bandwidthMeter", "getBandwidthMeter", "()Landroidx/media3/exoplayer/upstream/DefaultBandwidthMeter;", "disableDisconnectError", "", "getDisableDisconnectError", "()Z", "setDisableDisconnectError", "(Z)V", "buildLoadErrorHandlingPolicy", "Landroidx/media3/exoplayer/upstream/LoadErrorHandlingPolicy;", "minLoadRetryCount", "", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DefaultReactExoplayerConfig.kt */
public final class DefaultReactExoplayerConfig implements ReactExoplayerConfig {
    private DefaultBandwidthMeter bandWidthMeter;
    private boolean disableDisconnectError;

    public DefaultReactExoplayerConfig(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DefaultBandwidthMeter build = new DefaultBandwidthMeter.Builder(context).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        this.bandWidthMeter = build;
    }

    public boolean getDisableDisconnectError() {
        return this.disableDisconnectError;
    }

    public void setDisableDisconnectError(boolean z) {
        this.disableDisconnectError = z;
    }

    public DefaultBandwidthMeter getBandwidthMeter() {
        return this.bandWidthMeter;
    }

    public LoadErrorHandlingPolicy buildLoadErrorHandlingPolicy(int i) {
        if (getDisableDisconnectError()) {
            return new ReactExoplayerLoadErrorHandlingPolicy(i);
        }
        return new DefaultLoadErrorHandlingPolicy(i);
    }
}
