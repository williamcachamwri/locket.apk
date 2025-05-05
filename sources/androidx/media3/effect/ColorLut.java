package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.GlUtil;

public interface ColorLut extends GlEffect {
    int getLength(long j);

    int getLutTextureId(long j);

    void release() throws GlUtil.GlException;

    GlShaderProgram toGlShaderProgram(Context context, boolean z) throws VideoFrameProcessingException {
        return new ColorLutShaderProgram(context, this, z);
    }
}
