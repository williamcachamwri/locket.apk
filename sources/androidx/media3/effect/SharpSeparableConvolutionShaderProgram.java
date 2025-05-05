package androidx.media3.effect;

import android.content.Context;
import android.graphics.Matrix;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import java.io.IOException;

final class SharpSeparableConvolutionShaderProgram extends SeparableConvolutionShaderProgram {
    private final GlProgram sharpTransformGlProgram;
    private final float[] sharpTransformMatrixValues;

    public SharpSeparableConvolutionShaderProgram(Context context, boolean z, SeparableConvolution separableConvolution, float f, float f2) throws VideoFrameProcessingException {
        super(context, z, separableConvolution, 1.0f / f, 1.0f / f2);
        try {
            this.sharpTransformGlProgram = new GlProgram(context, "shaders/vertex_shader_transformation_es2.glsl", "shaders/fragment_shader_copy_es2.glsl");
            Matrix matrix = new Matrix();
            matrix.setScale(f, f2);
            this.sharpTransformMatrixValues = MatrixUtils.getGlMatrixArray(matrix);
        } catch (GlUtil.GlException | IOException e) {
            throw new VideoFrameProcessingException(e);
        }
    }

    /* access modifiers changed from: protected */
    public void onBlurRendered(GlTextureInfo glTextureInfo) throws GlUtil.GlException {
        float[] create4x4IdentityMatrix = GlUtil.create4x4IdentityMatrix();
        this.sharpTransformGlProgram.use();
        this.sharpTransformGlProgram.setSamplerTexIdUniform("uTexSampler", glTextureInfo.texId, 0);
        this.sharpTransformGlProgram.setFloatsUniform("uTexTransformationMatrix", create4x4IdentityMatrix);
        this.sharpTransformGlProgram.setFloatsUniform("uTransformationMatrix", this.sharpTransformMatrixValues);
        this.sharpTransformGlProgram.setBufferAttribute("aFramePosition", GlUtil.getNormalizedCoordinateBounds(), 4);
        this.sharpTransformGlProgram.bindAttributesAndUniforms();
    }

    public void release() throws VideoFrameProcessingException {
        super.release();
        try {
            this.sharpTransformGlProgram.delete();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }
}
