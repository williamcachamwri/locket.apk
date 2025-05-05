package androidx.media3.effect;

import android.content.Context;
import android.opengl.GLES20;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import androidx.media3.effect.ConvolutionFunction1D;
import androidx.media3.effect.GlShaderProgram;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.concurrent.Executor;

public class SeparableConvolutionShaderProgram implements GlShaderProgram {
    private static final String FRAGMENT_SHADER_PATH = "shaders/fragment_shader_separable_convolution_es2.glsl";
    private static final int FUNCTION_LUT_PADDING = 5;
    private static final int RASTER_SAMPLES_PER_TEXEL = 5;
    private static final String VERTEX_SHADER_PATH = "shaders/vertex_shader_transformation_es2.glsl";
    private final ConvolutionFunction1D.Provider convolutionFunction1DProvider;
    private GlShaderProgram.ErrorListener errorListener;
    private Executor errorListenerExecutor;
    private float functionLutCenterX;
    private float functionLutDomainStart;
    private float functionLutTexelStep;
    private GlTextureInfo functionLutTexture;
    private float functionLutWidth;
    private final GlProgram glProgram;
    private GlShaderProgram.InputListener inputListener;
    private Size intermediateSize;
    private GlTextureInfo intermediateTexture;
    private ConvolutionFunction1D lastConvolutionFunction;
    private Size lastInputSize;
    private GlShaderProgram.OutputListener outputListener;
    private Size outputSize;
    private GlTextureInfo outputTexture;
    private boolean outputTextureInUse;
    private final boolean useHdr;

    static /* synthetic */ void lambda$new$0(VideoFrameProcessingException videoFrameProcessingException) {
    }

    /* access modifiers changed from: protected */
    public void onBlurRendered(GlTextureInfo glTextureInfo) throws GlUtil.GlException {
    }

    public SeparableConvolutionShaderProgram(Context context, boolean z, SeparableConvolution separableConvolution, float f, float f2) throws VideoFrameProcessingException {
        this(context, z, new SeparableConvolutionWrapper(separableConvolution, f, f2));
    }

    public SeparableConvolutionShaderProgram(Context context, boolean z, ConvolutionFunction1D.Provider provider) throws VideoFrameProcessingException {
        this.useHdr = z;
        this.convolutionFunction1DProvider = provider;
        this.inputListener = new GlShaderProgram.InputListener() {
        };
        this.outputListener = new GlShaderProgram.OutputListener() {
        };
        this.errorListener = new SeparableConvolutionShaderProgram$$ExternalSyntheticLambda1();
        this.errorListenerExecutor = MoreExecutors.directExecutor();
        this.functionLutTexture = GlTextureInfo.UNSET;
        this.intermediateTexture = GlTextureInfo.UNSET;
        this.outputTexture = GlTextureInfo.UNSET;
        this.lastInputSize = Size.ZERO;
        this.intermediateSize = Size.ZERO;
        this.outputSize = Size.ZERO;
        this.lastConvolutionFunction = null;
        try {
            this.glProgram = new GlProgram(context, VERTEX_SHADER_PATH, FRAGMENT_SHADER_PATH);
        } catch (GlUtil.GlException | IOException e) {
            throw new VideoFrameProcessingException(e);
        }
    }

    public final void setInputListener(GlShaderProgram.InputListener inputListener2) {
        this.inputListener = inputListener2;
        if (!this.outputTextureInUse) {
            inputListener2.onReadyToAcceptInputFrame();
        }
    }

    public final void setOutputListener(GlShaderProgram.OutputListener outputListener2) {
        this.outputListener = outputListener2;
    }

    public final void setErrorListener(Executor executor, GlShaderProgram.ErrorListener errorListener2) {
        this.errorListenerExecutor = executor;
        this.errorListener = errorListener2;
    }

    public final void queueInputFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j) {
        Assertions.checkState(!this.outputTextureInUse, "The shader program does not currently accept input frames. Release prior output frames first.");
        try {
            ensureTexturesAreConfigured(glObjectsProvider, new Size(glTextureInfo.width, glTextureInfo.height), j);
            this.outputTextureInUse = true;
            renderHorizontal(glTextureInfo);
            renderVertical();
            onBlurRendered(glTextureInfo);
            GLES20.glDrawArrays(5, 0, 4);
            GlUtil.checkGlError();
            this.inputListener.onInputFrameProcessed(glTextureInfo);
            this.outputListener.onOutputFrameAvailable(this.outputTexture, j);
        } catch (GlUtil.GlException e) {
            this.errorListenerExecutor.execute(new SeparableConvolutionShaderProgram$$ExternalSyntheticLambda0(this, e, j));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$queueInputFrame$1$androidx-media3-effect-SeparableConvolutionShaderProgram  reason: not valid java name */
    public /* synthetic */ void m452lambda$queueInputFrame$1$androidxmedia3effectSeparableConvolutionShaderProgram(GlUtil.GlException glException, long j) {
        this.errorListener.onError(VideoFrameProcessingException.from(glException, j));
    }

    public final void releaseOutputFrame(GlTextureInfo glTextureInfo) {
        this.outputTextureInUse = false;
        this.inputListener.onReadyToAcceptInputFrame();
    }

    public final void signalEndOfCurrentInputStream() {
        this.outputListener.onCurrentOutputStreamEnded();
    }

    public final void flush() {
        this.outputTextureInUse = false;
        this.inputListener.onFlush();
        this.inputListener.onReadyToAcceptInputFrame();
    }

    public void release() throws VideoFrameProcessingException {
        try {
            this.outputTexture.release();
            this.intermediateTexture.release();
            this.functionLutTexture.release();
            this.glProgram.delete();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    private void renderOnePass(int i, boolean z) throws GlUtil.GlException {
        int width = z ? this.lastInputSize.getWidth() : this.intermediateSize.getHeight();
        this.glProgram.use();
        this.glProgram.setSamplerTexIdUniform("uTexSampler", i, 0);
        this.glProgram.setIntUniform("uIsHorizontal", z ? 1 : 0);
        float f = (float) width;
        this.glProgram.setFloatUniform("uSourceTexelSize", 1.0f / f);
        this.glProgram.setFloatUniform("uSourceFullSize", f);
        this.glProgram.setFloatUniform("uConvStartTexels", this.functionLutDomainStart);
        this.glProgram.setFloatUniform("uConvWidthTexels", this.functionLutWidth);
        this.glProgram.setFloatUniform("uFunctionLookupStepSize", this.functionLutTexelStep);
        this.glProgram.setFloatsUniform("uFunctionLookupCenter", new float[]{this.functionLutCenterX, 0.5f});
        this.glProgram.setSamplerTexIdUniform("uFunctionLookupSampler", this.functionLutTexture.texId, 1);
        this.glProgram.bindAttributesAndUniforms();
        GLES20.glDrawArrays(5, 0, 4);
        GlUtil.checkGlError();
    }

    private void renderHorizontal(GlTextureInfo glTextureInfo) throws GlUtil.GlException {
        GlUtil.focusFramebufferUsingCurrentContext(this.intermediateTexture.fboId, this.intermediateTexture.width, this.intermediateTexture.height);
        GlUtil.clearFocusedBuffers();
        renderOnePass(glTextureInfo.texId, true);
    }

    private void renderVertical() throws GlUtil.GlException {
        GlUtil.focusFramebufferUsingCurrentContext(this.outputTexture.fboId, this.outputTexture.width, this.outputTexture.height);
        GlUtil.clearFocusedBuffers();
        renderOnePass(this.intermediateTexture.texId, false);
    }

    private void ensureTexturesAreConfigured(GlObjectsProvider glObjectsProvider, Size size, long j) throws GlUtil.GlException {
        this.outputSize = this.convolutionFunction1DProvider.configure(size);
        ConvolutionFunction1D convolution = this.convolutionFunction1DProvider.getConvolution(j);
        if (!convolution.equals(this.lastConvolutionFunction)) {
            updateFunctionTexture(convolution);
            this.lastConvolutionFunction = convolution;
        }
        if (!size.equals(this.lastInputSize)) {
            this.glProgram.setBufferAttribute("aFramePosition", GlUtil.getNormalizedCoordinateBounds(), 4);
            float[] create4x4IdentityMatrix = GlUtil.create4x4IdentityMatrix();
            this.glProgram.setFloatsUniform("uTransformationMatrix", create4x4IdentityMatrix);
            this.glProgram.setFloatsUniform("uTexTransformationMatrix", create4x4IdentityMatrix);
            Size size2 = new Size(this.outputSize.getWidth(), size.getHeight());
            this.intermediateSize = size2;
            this.intermediateTexture = configurePixelTexture(glObjectsProvider, this.intermediateTexture, size2);
            this.outputTexture = configurePixelTexture(glObjectsProvider, this.outputTexture, this.outputSize);
            this.lastInputSize = size;
        }
    }

    private void updateFunctionTexture(ConvolutionFunction1D convolutionFunction1D) throws GlUtil.GlException {
        int ceil = (int) Math.ceil((double) ((convolutionFunction1D.width() * 5.0f) + 10.0f));
        float f = (float) ceil;
        this.functionLutTexelStep = 1.0f / (f / 5.0f);
        FloatBuffer allocate = FloatBuffer.allocate(ceil);
        float domainStart = convolutionFunction1D.domainStart();
        int i = 0;
        int i2 = 0;
        while (i < ceil) {
            int i3 = i - 5;
            allocate.put(i2, (i3 < 0 || i > ceil + -5) ? 0.0f : convolutionFunction1D.value((((float) i3) * 0.2f) + domainStart));
            i++;
            i2++;
        }
        this.functionLutCenterX = (-(domainStart - 1.1f)) / (0.2f * f);
        this.functionLutDomainStart = convolutionFunction1D.domainStart();
        this.functionLutWidth = convolutionFunction1D.width();
        if (this.functionLutTexture == GlTextureInfo.UNSET || this.functionLutTexture.width != ceil) {
            this.functionLutTexture.release();
            this.functionLutTexture = new GlTextureInfo(GlUtil.generateTexture(), -1, -1, ceil, 1);
        }
        GlUtil.bindTexture(3553, this.functionLutTexture.texId, 9729);
        GLES20.glTexImage2D(3553, 0, 33325, ceil, 1, 0, 6403, 5126, allocate);
        GlUtil.checkGlError();
    }

    private GlTextureInfo configurePixelTexture(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, Size size) throws GlUtil.GlException {
        if (size.getWidth() == glTextureInfo.width && size.getHeight() == glTextureInfo.height) {
            return glTextureInfo;
        }
        glTextureInfo.release();
        return glObjectsProvider.createBuffersForTexture(GlUtil.createTexture(size.getWidth(), size.getHeight(), this.useHdr), size.getWidth(), size.getHeight());
    }

    private static final class SeparableConvolutionWrapper implements ConvolutionFunction1D.Provider {
        private final float scaleHeight;
        private final float scaleWidth;
        private final SeparableConvolution separableConvolution;

        public SeparableConvolutionWrapper(SeparableConvolution separableConvolution2, float f, float f2) {
            this.separableConvolution = separableConvolution2;
            this.scaleWidth = f;
            this.scaleHeight = f2;
        }

        public ConvolutionFunction1D getConvolution(long j) {
            return this.separableConvolution.getConvolution(j);
        }

        public Size configure(Size size) {
            return new Size((int) (((float) size.getWidth()) * this.scaleWidth), (int) (((float) size.getHeight()) * this.scaleHeight));
        }
    }
}
