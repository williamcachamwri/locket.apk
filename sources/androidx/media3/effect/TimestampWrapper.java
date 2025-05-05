package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;

public final class TimestampWrapper implements GlEffect {
    public final long endTimeUs;
    public final GlEffect glEffect;
    public final long startTimeUs;

    public TimestampWrapper(GlEffect glEffect2, long j, long j2) {
        boolean z = true;
        Assertions.checkArgument(j >= 0 && j2 >= 0, "startTimeUs and endTimeUs must be non-negative.");
        Assertions.checkArgument(j2 <= j ? false : z, "endTimeUs should be after startTimeUs.");
        this.glEffect = glEffect2;
        this.startTimeUs = j;
        this.endTimeUs = j2;
    }

    public GlShaderProgram toGlShaderProgram(Context context, boolean z) throws VideoFrameProcessingException {
        return new TimestampWrapperShaderProgram(context, z, this);
    }

    public boolean isNoOp(int i, int i2) {
        return this.glEffect.isNoOp(i, i2);
    }
}
