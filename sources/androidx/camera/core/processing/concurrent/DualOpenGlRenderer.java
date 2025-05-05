package androidx.camera.core.processing.concurrent;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLExt;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.Matrix;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.CompositionSettings;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.processing.OpenGlRenderer;
import androidx.camera.core.processing.ShaderProvider;
import androidx.camera.core.processing.util.GLUtils;
import androidx.camera.core.processing.util.GraphicDeviceInfo;
import androidx.camera.core.processing.util.OutputSurface;
import androidx.core.util.Preconditions;
import java.util.Map;

public final class DualOpenGlRenderer extends OpenGlRenderer {
    private static final String TAG = "DualOpenGlRenderer";
    private final CompositionSettings mPrimaryCompositionSettings;
    private int mPrimaryExternalTextureId = -1;
    private final CompositionSettings mSecondaryCompositionSettings;
    private int mSecondaryExternalTextureId = -1;

    public DualOpenGlRenderer(CompositionSettings compositionSettings, CompositionSettings compositionSettings2) {
        this.mPrimaryCompositionSettings = compositionSettings;
        this.mSecondaryCompositionSettings = compositionSettings2;
    }

    public GraphicDeviceInfo init(DynamicRange dynamicRange, Map<GLUtils.InputFormat, ShaderProvider> map) {
        GraphicDeviceInfo init = super.init(dynamicRange, map);
        this.mPrimaryExternalTextureId = GLUtils.createTexture();
        this.mSecondaryExternalTextureId = GLUtils.createTexture();
        return init;
    }

    public void release() {
        super.release();
        this.mPrimaryExternalTextureId = -1;
        this.mSecondaryExternalTextureId = -1;
    }

    public int getTextureName(boolean z) {
        GLUtils.checkInitializedOrThrow(this.mInitialized, true);
        GLUtils.checkGlThreadOrThrow(this.mGlThread);
        return z ? this.mPrimaryExternalTextureId : this.mSecondaryExternalTextureId;
    }

    public void render(long j, Surface surface, SurfaceOutput surfaceOutput, SurfaceTexture surfaceTexture, SurfaceTexture surfaceTexture2) {
        GLUtils.checkInitializedOrThrow(this.mInitialized, true);
        GLUtils.checkGlThreadOrThrow(this.mGlThread);
        OutputSurface outSurfaceOrThrow = getOutSurfaceOrThrow(surface);
        if (outSurfaceOrThrow == GLUtils.NO_OUTPUT_SURFACE) {
            outSurfaceOrThrow = createOutputSurfaceInternal(surface);
            if (outSurfaceOrThrow != null) {
                this.mOutputSurfaceMap.put(surface, outSurfaceOrThrow);
            } else {
                return;
            }
        }
        if (surface != this.mCurrentSurface) {
            makeCurrent(outSurfaceOrThrow.getEglSurface());
            this.mCurrentSurface = surface;
        }
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES30.glClear(16384);
        OutputSurface outputSurface = outSurfaceOrThrow;
        SurfaceOutput surfaceOutput2 = surfaceOutput;
        renderInternal(outputSurface, surfaceOutput2, surfaceTexture, this.mPrimaryCompositionSettings, this.mPrimaryExternalTextureId, true);
        renderInternal(outputSurface, surfaceOutput2, surfaceTexture2, this.mSecondaryCompositionSettings, this.mSecondaryExternalTextureId, true);
        EGLExt.eglPresentationTimeANDROID(this.mEglDisplay, outSurfaceOrThrow.getEglSurface(), j);
        if (!EGL14.eglSwapBuffers(this.mEglDisplay, outSurfaceOrThrow.getEglSurface())) {
            Logger.w(TAG, "Failed to swap buffers with EGL error: 0x" + Integer.toHexString(EGL14.eglGetError()));
            removeOutputSurfaceInternal(surface, false);
        }
    }

    private void renderInternal(OutputSurface outputSurface, SurfaceOutput surfaceOutput, SurfaceTexture surfaceTexture, CompositionSettings compositionSettings, int i, boolean z) {
        useAndConfigureProgramWithTexture(i);
        GLES20.glViewport(0, 0, outputSurface.getWidth(), outputSurface.getHeight());
        GLES20.glScissor(0, 0, outputSurface.getWidth(), outputSurface.getHeight());
        float[] fArr = new float[16];
        surfaceTexture.getTransformMatrix(fArr);
        float[] fArr2 = new float[16];
        surfaceOutput.updateTransformMatrix(fArr2, fArr, z);
        GLUtils.Program2D program2D = (GLUtils.Program2D) Preconditions.checkNotNull(this.mCurrentProgram);
        if (program2D instanceof GLUtils.SamplerShaderProgram) {
            ((GLUtils.SamplerShaderProgram) program2D).updateTextureMatrix(fArr2);
        }
        program2D.updateTransformMatrix(getTransformMatrix(new Size((int) (((float) outputSurface.getWidth()) * ((Float) compositionSettings.getScale().first).floatValue()), (int) (((float) outputSurface.getHeight()) * ((Float) compositionSettings.getScale().second).floatValue())), new Size(outputSurface.getWidth(), outputSurface.getHeight()), compositionSettings));
        program2D.updateAlpha(compositionSettings.getAlpha());
        GLES20.glEnable(3042);
        GLES20.glBlendFuncSeparate(770, 771, 1, 771);
        GLES20.glDrawArrays(5, 0, 4);
        GLUtils.checkGlErrorOrThrow("glDrawArrays");
        GLES20.glDisable(3042);
    }

    private static float[] getTransformMatrix(Size size, Size size2, CompositionSettings compositionSettings) {
        float[] create4x4IdentityMatrix = GLUtils.create4x4IdentityMatrix();
        float[] create4x4IdentityMatrix2 = GLUtils.create4x4IdentityMatrix();
        float[] create4x4IdentityMatrix3 = GLUtils.create4x4IdentityMatrix();
        Matrix.scaleM(create4x4IdentityMatrix, 0, ((float) size.getWidth()) / ((float) size2.getWidth()), ((float) size.getHeight()) / ((float) size2.getHeight()), 1.0f);
        if (!(((Float) compositionSettings.getScale().first).floatValue() == 0.0f && ((Float) compositionSettings.getScale().second).floatValue() == 0.0f)) {
            Matrix.translateM(create4x4IdentityMatrix2, 0, ((Float) compositionSettings.getOffset().first).floatValue() / ((Float) compositionSettings.getScale().first).floatValue(), ((Float) compositionSettings.getOffset().second).floatValue() / ((Float) compositionSettings.getScale().second).floatValue(), 0.0f);
        }
        Matrix.multiplyMM(create4x4IdentityMatrix3, 0, create4x4IdentityMatrix, 0, create4x4IdentityMatrix2, 0);
        return create4x4IdentityMatrix3;
    }
}
