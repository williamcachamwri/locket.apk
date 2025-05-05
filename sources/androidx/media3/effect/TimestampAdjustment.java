package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.audio.SpeedProvider;
import androidx.media3.common.util.SpeedProviderUtil;
import androidx.media3.common.util.TimestampConsumer;

public final class TimestampAdjustment implements GlEffect {
    public final SpeedProvider speedProvider;
    public final TimestampMap timestampMap;

    public interface TimestampMap {
        void calculateOutputTimeUs(long j, TimestampConsumer timestampConsumer);
    }

    public TimestampAdjustment(TimestampMap timestampMap2, SpeedProvider speedProvider2) {
        this.timestampMap = timestampMap2;
        this.speedProvider = speedProvider2;
    }

    public GlShaderProgram toGlShaderProgram(Context context, boolean z) {
        return new TimestampAdjustmentShaderProgram(this.timestampMap);
    }

    public long getDurationAfterEffectApplied(long j) {
        return SpeedProviderUtil.getDurationAfterSpeedProviderApplied(this.speedProvider, j);
    }
}
