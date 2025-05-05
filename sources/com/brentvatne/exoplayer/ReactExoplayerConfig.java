package com.brentvatne.exoplayer;

import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\u00020\u0007X¦\u000e¢\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/brentvatne/exoplayer/ReactExoplayerConfig;", "", "bandwidthMeter", "Landroidx/media3/exoplayer/upstream/DefaultBandwidthMeter;", "getBandwidthMeter", "()Landroidx/media3/exoplayer/upstream/DefaultBandwidthMeter;", "disableDisconnectError", "", "getDisableDisconnectError", "()Z", "setDisableDisconnectError", "(Z)V", "buildLoadErrorHandlingPolicy", "Landroidx/media3/exoplayer/upstream/LoadErrorHandlingPolicy;", "minLoadRetryCount", "", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ReactExoplayerConfig.kt */
public interface ReactExoplayerConfig {
    LoadErrorHandlingPolicy buildLoadErrorHandlingPolicy(int i);

    DefaultBandwidthMeter getBandwidthMeter();

    boolean getDisableDisconnectError();

    void setDisableDisconnectError(boolean z);
}
