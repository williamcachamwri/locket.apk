package androidx.media3.effect;

import android.content.Context;
import android.opengl.GLES20;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import java.io.IOException;

final class ColorLutShaderProgram extends BaseGlShaderProgram {
    private static final String FRAGMENT_SHADER_PATH = "shaders/fragment_shader_lut_es2.glsl";
    private static final String VERTEX_SHADER_PATH = "shaders/vertex_shader_transformation_es2.glsl";
    private final ColorLut colorLut;
    private final GlProgram glProgram;

    public ColorLutShaderProgram(Context context, ColorLut colorLut2, boolean z) throws VideoFrameProcessingException {
        super(z, 1);
        Assertions.checkArgument(!z, "ColorLutShaderProgram does not support HDR colors.");
        this.colorLut = colorLut2;
        try {
            GlProgram glProgram2 = new GlProgram(context, VERTEX_SHADER_PATH, FRAGMENT_SHADER_PATH);
            this.glProgram = glProgram2;
            glProgram2.setBufferAttribute("aFramePosition", GlUtil.getNormalizedCoordinateBounds(), 4);
            float[] create4x4IdentityMatrix = GlUtil.create4x4IdentityMatrix();
            glProgram2.setFloatsUniform("uTransformationMatrix", create4x4IdentityMatrix);
            glProgram2.setFloatsUniform("uTexTransformationMatrix", create4x4IdentityMatrix);
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
            this.glProgram.setSamplerTexIdUniform("uColorLut", this.colorLut.getLutTextureId(j), 1);
            this.glProgram.setFloatUniform("uColorLutLength", (float) this.colorLut.getLength(j));
            this.glProgram.bindAttributesAndUniforms();
            GLES20.glDrawArrays(5, 0, 4);
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    public void release() throws VideoFrameProcessingException {
        super.release();
        try {
            this.colorLut.release();
            this.glProgram.delete();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }
}
