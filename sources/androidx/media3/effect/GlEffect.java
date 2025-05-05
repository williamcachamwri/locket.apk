package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.Effect;
import androidx.media3.common.VideoFrameProcessingException;

public interface GlEffect extends Effect {
    boolean isNoOp(int i, int i2) {
        return false;
    }

    GlShaderProgram toGlShaderProgram(Context context, boolean z) throws VideoFrameProcessingException;
}
