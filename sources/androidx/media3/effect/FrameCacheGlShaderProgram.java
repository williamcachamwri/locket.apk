package androidx.media3.effect;

import android.content.Context;
import android.opengl.GLES20;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import java.io.IOException;

class FrameCacheGlShaderProgram extends BaseGlShaderProgram {
    private static final String FRAGMENT_SHADER_TRANSFORMATION_ES2_PATH = "shaders/fragment_shader_transformation_es2.glsl";
    private static final String VERTEX_SHADER_TRANSFORMATION_ES2_PATH = "shaders/vertex_shader_transformation_es2.glsl";
    private final GlProgram copyProgram;

    public FrameCacheGlShaderProgram(Context context, int i, boolean z) throws VideoFrameProcessingException {
        super(z, i);
        try {
            GlProgram glProgram = new GlProgram(context, VERTEX_SHADER_TRANSFORMATION_ES2_PATH, FRAGMENT_SHADER_TRANSFORMATION_ES2_PATH);
            this.copyProgram = glProgram;
            float[] create4x4IdentityMatrix = GlUtil.create4x4IdentityMatrix();
            glProgram.setFloatsUniform("uTexTransformationMatrix", create4x4IdentityMatrix);
            glProgram.setFloatsUniform("uTransformationMatrix", create4x4IdentityMatrix);
            glProgram.setFloatsUniform("uRgbMatrix", create4x4IdentityMatrix);
            glProgram.setBufferAttribute("aFramePosition", GlUtil.getNormalizedCoordinateBounds(), 4);
        } catch (GlUtil.GlException | IOException e) {
            throw VideoFrameProcessingException.from(e);
        }
    }

    public Size configure(int i, int i2) {
        return new Size(i, i2);
    }

    public void drawFrame(int i, long j) throws VideoFrameProcessingException {
        try {
            this.copyProgram.use();
            this.copyProgram.setSamplerTexIdUniform("uTexSampler", i, 0);
            this.copyProgram.bindAttributesAndUniforms();
            GLES20.glDrawArrays(5, 0, 4);
        } catch (GlUtil.GlException e) {
            throw VideoFrameProcessingException.from(e);
        }
    }

    public void release() throws VideoFrameProcessingException {
        super.release();
        try {
            this.copyProgram.delete();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }
}
