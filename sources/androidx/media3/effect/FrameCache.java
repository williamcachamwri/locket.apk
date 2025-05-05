package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;

public final class FrameCache implements GlEffect {
    public final int capacity;

    public FrameCache(int i) {
        Assertions.checkArgument(i > 0 && i < 9);
        this.capacity = i;
    }

    public GlShaderProgram toGlShaderProgram(Context context, boolean z) throws VideoFrameProcessingException {
        return new FrameCacheGlShaderProgram(context, this.capacity, z);
    }
}
