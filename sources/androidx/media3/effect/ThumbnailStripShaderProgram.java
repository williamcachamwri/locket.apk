package androidx.media3.effect;

import android.content.Context;
import android.opengl.GLES20;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import java.io.IOException;

final class ThumbnailStripShaderProgram extends BaseGlShaderProgram {
    private static final String FRAGMENT_SHADER_PATH = "shaders/fragment_shader_copy_es2.glsl";
    private static final String VERTEX_SHADER_PATH = "shaders/vertex_shader_thumbnail_strip_es2.glsl";
    private boolean clearedGlBuffer;
    private final GlProgram glProgram;
    private final ThumbnailStripEffect thumbnailStripEffect;

    public boolean shouldClearTextureBuffer() {
        return false;
    }

    public ThumbnailStripShaderProgram(Context context, boolean z, ThumbnailStripEffect thumbnailStripEffect2) throws VideoFrameProcessingException {
        super(z, 1);
        this.thumbnailStripEffect = thumbnailStripEffect2;
        try {
            GlProgram glProgram2 = new GlProgram(context, VERTEX_SHADER_PATH, FRAGMENT_SHADER_PATH);
            this.glProgram = glProgram2;
            glProgram2.setBufferAttribute("aFramePosition", GlUtil.getNormalizedCoordinateBounds(), 4);
        } catch (GlUtil.GlException | IOException e) {
            throw VideoFrameProcessingException.from(e);
        }
    }

    public Size configure(int i, int i2) {
        return new Size(this.thumbnailStripEffect.stripWidth, this.thumbnailStripEffect.stripHeight);
    }

    public void drawFrame(int i, long j) throws VideoFrameProcessingException {
        if (!this.clearedGlBuffer) {
            try {
                GlUtil.clearFocusedBuffers();
                this.clearedGlBuffer = true;
            } catch (GlUtil.GlException e) {
                throw new VideoFrameProcessingException((Throwable) e, j);
            }
        }
        long msToUs = Util.msToUs(this.thumbnailStripEffect.getNextTimestampMs());
        if (!this.thumbnailStripEffect.isDone() && j >= msToUs) {
            try {
                this.glProgram.use();
                this.glProgram.setSamplerTexIdUniform("uTexSampler", i, 0);
                this.glProgram.setIntUniform("uIndex", this.thumbnailStripEffect.getNextThumbnailIndex());
                this.glProgram.setIntUniform("uCount", this.thumbnailStripEffect.getNumberOfThumbnails());
                this.glProgram.bindAttributesAndUniforms();
                GLES20.glDrawArrays(5, 0, 4);
                this.thumbnailStripEffect.onThumbnailDrawn();
            } catch (GlUtil.GlException e2) {
                throw new VideoFrameProcessingException((Throwable) e2, j);
            }
        }
    }

    public void release() throws VideoFrameProcessingException {
        super.release();
        try {
            this.glProgram.delete();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }
}
