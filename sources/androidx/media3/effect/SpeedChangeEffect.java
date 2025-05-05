package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.C;
import androidx.media3.common.audio.SpeedProvider;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.SpeedProviderUtil;

public final class SpeedChangeEffect implements GlEffect {
    private final SpeedProvider speedProvider;

    public SpeedChangeEffect(final float f) {
        Assertions.checkArgument(f > 0.0f);
        this.speedProvider = new SpeedProvider() {
            public long getNextSpeedChangeTimeUs(long j) {
                return C.TIME_UNSET;
            }

            public float getSpeed(long j) {
                return f;
            }
        };
    }

    public SpeedChangeEffect(SpeedProvider speedProvider2) {
        this.speedProvider = speedProvider2;
    }

    public GlShaderProgram toGlShaderProgram(Context context, boolean z) {
        return new SpeedChangeShaderProgram(this.speedProvider);
    }

    public boolean isNoOp(int i, int i2) {
        return this.speedProvider.getSpeed(0) == 1.0f && this.speedProvider.getNextSpeedChangeTimeUs(0) == C.TIME_UNSET;
    }

    public long getDurationAfterEffectApplied(long j) {
        return SpeedProviderUtil.getDurationAfterSpeedProviderApplied(this.speedProvider, j);
    }
}
