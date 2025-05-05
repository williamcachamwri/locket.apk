package androidx.media3.effect;

import android.content.Context;
import android.opengl.GLES20;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import java.io.IOException;

final class AlphaScaleShaderProgram extends BaseGlShaderProgram {
    private static final String FRAGMENT_SHADER_PATH = "shaders/fragment_shader_alpha_scale_es2.glsl";
    private static final String VERTEX_SHADER_PATH = "shaders/vertex_shader_transformation_es2.glsl";
    private final GlProgram glProgram;

    public AlphaScaleShaderProgram(Context context, boolean z, float f) throws VideoFrameProcessingException {
        super(z, 1);
        try {
            GlProgram glProgram2 = new GlProgram(context, VERTEX_SHADER_PATH, FRAGMENT_SHADER_PATH);
            this.glProgram = glProgram2;
            glProgram2.setBufferAttribute("aFramePosition", GlUtil.getNormalizedCoordinateBounds(), 4);
            float[] create4x4IdentityMatrix = GlUtil.create4x4IdentityMatrix();
            glProgram2.setFloatsUniform("uTransformationMatrix", create4x4IdentityMatrix);
            glProgram2.setFloatsUniform("uTexTransformationMatrix", create4x4IdentityMatrix);
            glProgram2.setFloatUniform("uAlphaScale", f);
        } catch (GlUtil.GlException | IOException e) {
            throw new VideoFrameProcessingException(e);
        }
    }

    public Size configure(int i, int i2) {
        return new Size(i, i2);
    }

    public void drawFrame(int i, long j) throws VideoFrameProcessingException {
        try {
            this.glProgram.use();
            this.glProgram.setSamplerTexIdUniform("uTexSampler", i, 0);
            this.glProgram.bindAttributesAndUniforms();
            GLES20.glDrawArrays(5, 0, 4);
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e, j);
        }
    }
}
