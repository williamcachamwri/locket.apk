package androidx.media3.transformer;

import android.content.Context;
import androidx.media3.common.audio.SpeedProvider;
import androidx.media3.common.util.SpeedProviderUtil;
import androidx.media3.effect.GlEffect;
import androidx.media3.effect.GlShaderProgram;
import androidx.media3.effect.PassthroughShaderProgram;

class InactiveTimestampAdjustment implements GlEffect {
    public final SpeedProvider speedProvider;

    public boolean isNoOp(int i, int i2) {
        return true;
    }

    public InactiveTimestampAdjustment(SpeedProvider speedProvider2) {
        this.speedProvider = speedProvider2;
    }

    public GlShaderProgram toGlShaderProgram(Context context, boolean z) {
        return new PassthroughShaderProgram();
    }

    public long getDurationAfterEffectApplied(long j) {
        return SpeedProviderUtil.getDurationAfterSpeedProviderApplied(this.speedProvider, j);
    }
}
