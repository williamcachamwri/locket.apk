package androidx.media3.effect;

import android.content.Context;
import android.graphics.Gainmap;
import android.graphics.Matrix;
import android.opengl.GLES20;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

final class DefaultShaderProgram extends BaseGlShaderProgram implements ExternalShaderProgram, RepeatingGainmapShaderProgram {
    private static final float[] BT2020_FULL_RANGE_YUV_TO_RGB_COLOR_TRANSFORM_MATRIX = {1.0f, 1.0f, 1.0f, 0.0f, -0.1646f, 1.8814f, 1.4746f, -0.5714f, 0.0f};
    private static final float[] BT2020_LIMITED_RANGE_YUV_TO_RGB_COLOR_TRANSFORM_MATRIX = {1.1689f, 1.1689f, 1.1689f, 0.0f, -0.1881f, 2.1502f, 1.6853f, -0.653f, 0.0f};
    private static final String FRAGMENT_SHADER_COPY_PATH = "shaders/fragment_shader_copy_es2.glsl";
    private static final String FRAGMENT_SHADER_OETF_ES3_PATH = "shaders/fragment_shader_oetf_es3.glsl";
    private static final String FRAGMENT_SHADER_TRANSFORMATION_EXTERNAL_YUV_ES3_PATH = "shaders/fragment_shader_transformation_external_yuv_es3.glsl";
    private static final String FRAGMENT_SHADER_TRANSFORMATION_HDR_INTERNAL_ES3_PATH = "shaders/fragment_shader_transformation_hdr_internal_es3.glsl";
    private static final String FRAGMENT_SHADER_TRANSFORMATION_PATH = "shaders/fragment_shader_transformation_es2.glsl";
    private static final String FRAGMENT_SHADER_TRANSFORMATION_SDR_EXTERNAL_PATH = "shaders/fragment_shader_transformation_sdr_external_es2.glsl";
    private static final String FRAGMENT_SHADER_TRANSFORMATION_SDR_INTERNAL_PATH = "shaders/fragment_shader_transformation_sdr_internal_es2.glsl";
    private static final String FRAGMENT_SHADER_TRANSFORMATION_SDR_OETF_ES2_PATH = "shaders/fragment_shader_transformation_sdr_oetf_es2.glsl";
    private static final String FRAGMENT_SHADER_TRANSFORMATION_ULTRA_HDR_ES3_PATH = "shaders/fragment_shader_transformation_ultra_hdr_es3.glsl";
    private static final ImmutableList<float[]> NDC_SQUARE = ImmutableList.of(new float[]{-1.0f, -1.0f, 0.0f, 1.0f}, new float[]{-1.0f, 1.0f, 0.0f, 1.0f}, new float[]{1.0f, 1.0f, 0.0f, 1.0f}, new float[]{1.0f, -1.0f, 0.0f, 1.0f});
    private static final String VERTEX_SHADER_TRANSFORMATION_ES3_PATH = "shaders/vertex_shader_transformation_es3.glsl";
    private static final String VERTEX_SHADER_TRANSFORMATION_PATH = "shaders/vertex_shader_transformation_es2.glsl";
    private final float[] compositeRgbMatrixArray = GlUtil.create4x4IdentityMatrix();
    private final float[] compositeTransformationMatrixArray = GlUtil.create4x4IdentityMatrix();
    private int gainmapTexId = -1;
    private final GlProgram glProgram;
    private boolean isRepeatingFrameDrawn;
    private Gainmap lastGainmap;
    private final ImmutableList<GlMatrixTransformation> matrixTransformations;
    private int outputColorTransfer;
    private final ImmutableList<RgbMatrix> rgbMatrices;
    private final float[][] rgbMatrixCache;
    private boolean shouldRepeatLastFrame;
    private final float[] tempResultMatrix = new float[16];
    private final float[][] transformationMatrixCache;
    private final boolean useHdr;
    private ImmutableList<float[]> visiblePolygon = NDC_SQUARE;

    public static DefaultShaderProgram create(Context context, List<GlMatrixTransformation> list, List<RgbMatrix> list2, boolean z) throws VideoFrameProcessingException {
        return new DefaultShaderProgram(createGlProgram(context, VERTEX_SHADER_TRANSFORMATION_PATH, list2.isEmpty() ? FRAGMENT_SHADER_COPY_PATH : FRAGMENT_SHADER_TRANSFORMATION_PATH), ImmutableList.copyOf(list), ImmutableList.copyOf(list2), 1, z);
    }

    public static DefaultShaderProgram createWithInternalSampler(Context context, ColorInfo colorInfo, ColorInfo colorInfo2, int i, int i2) throws VideoFrameProcessingException {
        int i3 = 0;
        Assertions.checkState(colorInfo.colorTransfer != 2 || i2 == 2);
        boolean isTransferHdr = ColorInfo.isTransferHdr(colorInfo);
        boolean z = i2 == 2 && colorInfo2.colorSpace == 6;
        GlProgram createGlProgram = createGlProgram(context, (isTransferHdr || z) ? VERTEX_SHADER_TRANSFORMATION_ES3_PATH : VERTEX_SHADER_TRANSFORMATION_PATH, z ? FRAGMENT_SHADER_TRANSFORMATION_ULTRA_HDR_ES3_PATH : isTransferHdr ? FRAGMENT_SHADER_TRANSFORMATION_HDR_INTERNAL_ES3_PATH : FRAGMENT_SHADER_TRANSFORMATION_SDR_INTERNAL_PATH);
        if (!z) {
            Assertions.checkArgument(isTransferHdr || colorInfo.colorTransfer == 2 || colorInfo.colorTransfer == 3);
            createGlProgram.setIntUniform("uInputColorTransfer", colorInfo.colorTransfer);
        }
        if (isTransferHdr) {
            if (colorInfo2.colorSpace != 6) {
                i3 = 1;
            }
            createGlProgram.setIntUniform("uApplyHdrToSdrToneMapping", i3);
        }
        ImmutableList of = ImmutableList.of();
        if (i2 == 2) {
            of = ImmutableList.of(new DefaultShaderProgram$$ExternalSyntheticLambda0());
        }
        return createWithSampler(createGlProgram, colorInfo, colorInfo2, i, of);
    }

    static /* synthetic */ Matrix lambda$createWithInternalSampler$0(long j) {
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, -1.0f);
        return matrix;
    }

    public static DefaultShaderProgram createWithExternalSampler(Context context, ColorInfo colorInfo, ColorInfo colorInfo2, int i, boolean z) throws VideoFrameProcessingException {
        float[] fArr;
        boolean isTransferHdr = ColorInfo.isTransferHdr(colorInfo);
        GlProgram createGlProgram = createGlProgram(context, isTransferHdr ? VERTEX_SHADER_TRANSFORMATION_ES3_PATH : VERTEX_SHADER_TRANSFORMATION_PATH, isTransferHdr ? FRAGMENT_SHADER_TRANSFORMATION_EXTERNAL_YUV_ES3_PATH : FRAGMENT_SHADER_TRANSFORMATION_SDR_EXTERNAL_PATH);
        if (isTransferHdr) {
            if (GlUtil.isYuvTargetExtensionSupported()) {
                int i2 = 1;
                if (colorInfo.colorRange == 1) {
                    fArr = BT2020_FULL_RANGE_YUV_TO_RGB_COLOR_TRANSFORM_MATRIX;
                } else {
                    fArr = BT2020_LIMITED_RANGE_YUV_TO_RGB_COLOR_TRANSFORM_MATRIX;
                }
                createGlProgram.setFloatsUniform("uYuvToRgbColorTransform", fArr);
                createGlProgram.setIntUniform("uInputColorTransfer", colorInfo.colorTransfer);
                if (colorInfo2.colorSpace == 6) {
                    i2 = 0;
                }
                createGlProgram.setIntUniform("uApplyHdrToSdrToneMapping", i2);
            } else {
                throw new VideoFrameProcessingException("The EXT_YUV_target extension is required for HDR editing input.");
            }
        }
        createGlProgram.setExternalTexturesRequireNearestSampling(z);
        return createWithSampler(createGlProgram, colorInfo, colorInfo2, i, ImmutableList.of());
    }

    public static DefaultShaderProgram createApplyingOetf(Context context, List<GlMatrixTransformation> list, List<RgbMatrix> list2, ColorInfo colorInfo, int i) throws VideoFrameProcessingException {
        boolean isTransferHdr = ColorInfo.isTransferHdr(colorInfo);
        boolean z = true;
        boolean z2 = i == 2;
        GlProgram createGlProgram = createGlProgram(context, isTransferHdr ? VERTEX_SHADER_TRANSFORMATION_ES3_PATH : VERTEX_SHADER_TRANSFORMATION_PATH, isTransferHdr ? FRAGMENT_SHADER_OETF_ES3_PATH : z2 ? FRAGMENT_SHADER_TRANSFORMATION_SDR_OETF_ES2_PATH : list2.isEmpty() ? FRAGMENT_SHADER_COPY_PATH : FRAGMENT_SHADER_TRANSFORMATION_PATH);
        int i2 = colorInfo.colorTransfer;
        if (isTransferHdr) {
            if (!(i2 == 7 || i2 == 6)) {
                z = false;
            }
            Assertions.checkArgument(z);
            createGlProgram.setIntUniform("uOutputColorTransfer", i2);
        } else if (z2) {
            if (!(i2 == 3 || i2 == 10)) {
                z = false;
            }
            Assertions.checkArgument(z);
            createGlProgram.setIntUniform("uOutputColorTransfer", i2);
        }
        return new DefaultShaderProgram(createGlProgram, ImmutableList.copyOf(list), ImmutableList.copyOf(list2), colorInfo.colorTransfer, isTransferHdr);
    }

    private static DefaultShaderProgram createWithSampler(GlProgram glProgram2, ColorInfo colorInfo, ColorInfo colorInfo2, int i, ImmutableList<GlMatrixTransformation> immutableList) {
        GlProgram glProgram3 = glProgram2;
        ColorInfo colorInfo3 = colorInfo;
        ColorInfo colorInfo4 = colorInfo2;
        boolean isTransferHdr = ColorInfo.isTransferHdr(colorInfo);
        boolean z = false;
        boolean z2 = (colorInfo3.colorSpace == 1 || colorInfo3.colorSpace == 2) && colorInfo4.colorSpace == 6;
        int i2 = colorInfo4.colorTransfer;
        if (isTransferHdr) {
            if (i2 == 3) {
                i2 = 10;
            }
            Assertions.checkArgument(i2 == 1 || i2 == 10 || i2 == 6 || i2 == 7);
            glProgram2.setIntUniform("uOutputColorTransfer", i2);
        } else if (z2) {
            Assertions.checkArgument(i2 == 1 || i2 == 6 || i2 == 7);
            glProgram2.setIntUniform("uOutputColorTransfer", i2);
        } else {
            int i3 = i;
            glProgram2.setIntUniform("uSdrWorkingColorSpace", i);
            Assertions.checkArgument(i2 == 3 || i2 == 1);
            glProgram2.setIntUniform("uOutputColorTransfer", i2);
        }
        ImmutableList of = ImmutableList.of();
        int i4 = colorInfo4.colorTransfer;
        if (isTransferHdr || z2) {
            z = true;
        }
        return new DefaultShaderProgram(glProgram2, immutableList, of, i4, z);
    }

    private DefaultShaderProgram(GlProgram glProgram2, ImmutableList<GlMatrixTransformation> immutableList, ImmutableList<RgbMatrix> immutableList2, int i, boolean z) {
        super(z, 1);
        this.glProgram = glProgram2;
        this.outputColorTransfer = i;
        this.matrixTransformations = immutableList;
        this.rgbMatrices = immutableList2;
        this.useHdr = z;
        this.transformationMatrixCache = (float[][]) Array.newInstance(Float.TYPE, new int[]{immutableList.size(), 16});
        this.rgbMatrixCache = (float[][]) Array.newInstance(Float.TYPE, new int[]{immutableList2.size(), 16});
    }

    private static GlProgram createGlProgram(Context context, String str, String str2) throws VideoFrameProcessingException {
        try {
            GlProgram glProgram2 = new GlProgram(context, str, str2);
            glProgram2.setFloatsUniform("uTexTransformationMatrix", GlUtil.create4x4IdentityMatrix());
            return glProgram2;
        } catch (GlUtil.GlException | IOException e) {
            throw new VideoFrameProcessingException(e);
        }
    }

    public void setTextureTransformMatrix(float[] fArr) {
        this.glProgram.setFloatsUniform("uTexTransformationMatrix", fArr);
    }

    public Size configure(int i, int i2) {
        return MatrixUtils.configureAndGetOutputSize(i, i2, this.matrixTransformations);
    }

    public void drawFrame(int i, long j) throws VideoFrameProcessingException {
        boolean z = updateCompositeRgbMatrixArray(j) || updateCompositeTransformationMatrixAndVisiblePolygon(j);
        if (this.visiblePolygon.size() >= 3) {
            if (!this.shouldRepeatLastFrame || z || !this.isRepeatingFrameDrawn) {
                try {
                    this.glProgram.use();
                    setGainmapSamplerAndUniforms();
                    this.glProgram.setSamplerTexIdUniform("uTexSampler", i, 0);
                    this.glProgram.setFloatsUniform("uTransformationMatrix", this.compositeTransformationMatrixArray);
                    this.glProgram.setFloatsUniformIfPresent("uRgbMatrix", this.compositeRgbMatrixArray);
                    this.glProgram.setBufferAttribute("aFramePosition", GlUtil.createVertexBuffer(this.visiblePolygon), 4);
                    this.glProgram.bindAttributesAndUniforms();
                    GLES20.glDrawArrays(6, 0, this.visiblePolygon.size());
                    GlUtil.checkGlError();
                    this.isRepeatingFrameDrawn = true;
                } catch (GlUtil.GlException e) {
                    throw new VideoFrameProcessingException((Throwable) e, j);
                }
            }
        }
    }

    public void release() throws VideoFrameProcessingException {
        super.release();
        try {
            this.glProgram.delete();
            int i = this.gainmapTexId;
            if (i != -1) {
                GlUtil.deleteTexture(i);
            }
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    public void setGainmap(Gainmap gainmap) throws GlUtil.GlException {
        if (this.useHdr) {
            Gainmap gainmap2 = this.lastGainmap;
            if (gainmap2 == null || !GainmapUtil.equals(gainmap2, gainmap)) {
                this.isRepeatingFrameDrawn = false;
                this.lastGainmap = gainmap;
                int i = this.gainmapTexId;
                if (i == -1) {
                    this.gainmapTexId = GlUtil.createTexture(gainmap.getGainmapContents());
                } else {
                    GlUtil.setTexture(i, gainmap.getGainmapContents());
                }
            }
        }
    }

    public void signalNewRepeatingFrameSequence() {
        Assertions.checkState(this.outputTexturePool.capacity() == 1);
        this.shouldRepeatLastFrame = true;
        this.isRepeatingFrameDrawn = false;
    }

    public boolean shouldClearTextureBuffer() {
        return !this.isRepeatingFrameDrawn || !this.shouldRepeatLastFrame;
    }

    public void setOutputColorTransfer(int i) {
        boolean z = true;
        if (this.outputColorTransfer == 1) {
            z = false;
        }
        Assertions.checkState(z);
        this.outputColorTransfer = i;
        this.glProgram.setIntUniform("uOutputColorTransfer", i);
    }

    public int getOutputColorTransfer() {
        return this.outputColorTransfer;
    }

    private boolean updateCompositeTransformationMatrixAndVisiblePolygon(long j) {
        float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, new int[]{this.matrixTransformations.size(), 16});
        for (int i = 0; i < this.matrixTransformations.size(); i++) {
            fArr[i] = ((GlMatrixTransformation) this.matrixTransformations.get(i)).getGlMatrixArray(j);
        }
        if (!updateMatrixCache(this.transformationMatrixCache, fArr)) {
            return false;
        }
        GlUtil.setToIdentity(this.compositeTransformationMatrixArray);
        this.visiblePolygon = NDC_SQUARE;
        for (float[] fArr2 : this.transformationMatrixCache) {
            android.opengl.Matrix.multiplyMM(this.tempResultMatrix, 0, fArr2, 0, this.compositeTransformationMatrixArray, 0);
            float[] fArr3 = this.tempResultMatrix;
            System.arraycopy(fArr3, 0, this.compositeTransformationMatrixArray, 0, fArr3.length);
            ImmutableList<float[]> clipConvexPolygonToNdcRange = MatrixUtils.clipConvexPolygonToNdcRange(MatrixUtils.transformPoints(fArr2, this.visiblePolygon));
            this.visiblePolygon = clipConvexPolygonToNdcRange;
            if (clipConvexPolygonToNdcRange.size() < 3) {
                return true;
            }
        }
        android.opengl.Matrix.invertM(this.tempResultMatrix, 0, this.compositeTransformationMatrixArray, 0);
        this.visiblePolygon = MatrixUtils.transformPoints(this.tempResultMatrix, this.visiblePolygon);
        return true;
    }

    private boolean updateCompositeRgbMatrixArray(long j) {
        float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, new int[]{this.rgbMatrices.size(), 16});
        for (int i = 0; i < this.rgbMatrices.size(); i++) {
            fArr[i] = ((RgbMatrix) this.rgbMatrices.get(i)).getMatrix(j, this.useHdr);
        }
        if (!updateMatrixCache(this.rgbMatrixCache, fArr)) {
            return false;
        }
        GlUtil.setToIdentity(this.compositeRgbMatrixArray);
        for (int i2 = 0; i2 < this.rgbMatrices.size(); i2++) {
            android.opengl.Matrix.multiplyMM(this.tempResultMatrix, 0, ((RgbMatrix) this.rgbMatrices.get(i2)).getMatrix(j, this.useHdr), 0, this.compositeRgbMatrixArray, 0);
            float[] fArr2 = this.tempResultMatrix;
            System.arraycopy(fArr2, 0, this.compositeRgbMatrixArray, 0, fArr2.length);
        }
        return true;
    }

    private static boolean updateMatrixCache(float[][] fArr, float[][] fArr2) {
        boolean z = false;
        for (int i = 0; i < fArr.length; i++) {
            float[] fArr3 = fArr[i];
            float[] fArr4 = fArr2[i];
            if (!Arrays.equals(fArr3, fArr4)) {
                Assertions.checkState(fArr4.length == 16, "A 4x4 transformation matrix must have 16 elements");
                System.arraycopy(fArr4, 0, fArr3, 0, fArr4.length);
                z = true;
            }
        }
        return z;
    }

    private void setGainmapSamplerAndUniforms() throws GlUtil.GlException {
        if (this.lastGainmap != null) {
            if (Util.SDK_INT >= 34) {
                this.glProgram.setSamplerTexIdUniform("uGainmapTexSampler", this.gainmapTexId, 1);
                GainmapUtil.setGainmapUniforms(this.glProgram, this.lastGainmap, -1);
                return;
            }
            throw new IllegalStateException("Gainmaps not supported under API 34.");
        }
    }
}
