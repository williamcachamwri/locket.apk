package androidx.media3.effect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Gainmap;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;

final class OverlayShaderProgram extends BaseGlShaderProgram {
    private static final String FRAGMENT_SHADER_METHODS_INSERT = "shaders/insert_overlay_fragment_shader_methods.glsl";
    private static final int HDR_TYPE_TEXT = 2;
    private static final int HDR_TYPE_ULTRA_HDR = 1;
    private static final int MAX_OVERLAY_SAMPLERS = 15;
    private static final String TEXTURE_INDEX_FORMAT_SPECIFIER = "%";
    private static final String ULTRA_HDR_INSERT = "shaders/insert_ultra_hdr.glsl";
    private final SparseIntArray gainmapTexIds;
    private final GlProgram glProgram;
    private final int[] hdrTypes;
    private final SparseArray<Gainmap> lastGainmaps;
    private final ImmutableList<TextureOverlay> overlays;
    private final SamplerOverlayMatrixProvider samplerOverlayMatrixProvider;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OverlayShaderProgram(Context context, boolean z, ImmutableList<TextureOverlay> immutableList) throws VideoFrameProcessingException {
        super(z, 1);
        boolean z2 = true;
        if (z) {
            this.hdrTypes = findHdrTypes(immutableList);
        } else {
            this.hdrTypes = null;
            Assertions.checkArgument(immutableList.size() > 15 ? false : z2, "OverlayShaderProgram does not support more than 15 SDR overlays in the same instance.");
        }
        this.overlays = immutableList;
        this.samplerOverlayMatrixProvider = new SamplerOverlayMatrixProvider();
        this.lastGainmaps = new SparseArray<>();
        this.gainmapTexIds = new SparseIntArray();
        try {
            GlProgram glProgram2 = new GlProgram(createVertexShader(immutableList.size()), createFragmentShader(context, immutableList.size(), this.hdrTypes));
            this.glProgram = glProgram2;
            glProgram2.setBufferAttribute("aFramePosition", GlUtil.getNormalizedCoordinateBounds(), 4);
        } catch (GlUtil.GlException | IOException e) {
            throw new VideoFrameProcessingException(e);
        }
    }

    public Size configure(int i, int i2) {
        Size size = new Size(i, i2);
        this.samplerOverlayMatrixProvider.configure(size);
        UnmodifiableIterator<TextureOverlay> it = this.overlays.iterator();
        while (it.hasNext()) {
            it.next().configure(size);
        }
        return size;
    }

    public void drawFrame(int i, long j) throws VideoFrameProcessingException {
        try {
            this.glProgram.use();
            for (int i2 = 1; i2 <= this.overlays.size(); i2++) {
                int i3 = i2 - 1;
                TextureOverlay textureOverlay = (TextureOverlay) this.overlays.get(i3);
                int[] iArr = this.hdrTypes;
                if (iArr != null) {
                    int i4 = iArr[i3];
                    if (i4 == 1) {
                        Assertions.checkArgument(textureOverlay instanceof BitmapOverlay);
                        Bitmap bitmap = ((BitmapOverlay) textureOverlay).getBitmap(j);
                        Assertions.checkArgument(bitmap.hasGainmap());
                        Gainmap gainmap = (Gainmap) Assertions.checkNotNull(bitmap.getGainmap());
                        Gainmap gainmap2 = this.lastGainmaps.get(i2);
                        if (gainmap2 == null || !GainmapUtil.equals(gainmap2, gainmap)) {
                            this.lastGainmaps.put(i2, gainmap);
                            if (this.gainmapTexIds.get(i2, -1) == -1) {
                                this.gainmapTexIds.put(i2, GlUtil.createTexture(gainmap.getGainmapContents()));
                            } else {
                                GlUtil.setTexture(this.gainmapTexIds.get(i2), gainmap.getGainmapContents());
                            }
                            this.glProgram.setSamplerTexIdUniform("uGainmapTexSampler" + i2, this.gainmapTexIds.get(i2), i2);
                            GainmapUtil.setGainmapUniforms(this.glProgram, this.lastGainmaps.get(i2), i2);
                        }
                    } else if (i4 == 2) {
                        float[] create4x4IdentityMatrix = GlUtil.create4x4IdentityMatrix();
                        float f = textureOverlay.getOverlaySettings(j).hdrLuminanceMultiplier;
                        Matrix.scaleM(create4x4IdentityMatrix, 0, f, f, f);
                        this.glProgram.setFloatsUniform(Util.formatInvariant("uLuminanceMatrix%d", Integer.valueOf(i2)), create4x4IdentityMatrix);
                    }
                }
                this.glProgram.setSamplerTexIdUniform(Util.formatInvariant("uOverlayTexSampler%d", Integer.valueOf(i2)), textureOverlay.getTextureId(j), i2);
                this.glProgram.setFloatsUniform(Util.formatInvariant("uVertexTransformationMatrix%d", Integer.valueOf(i2)), textureOverlay.getVertexTransformation(j));
                OverlaySettings overlaySettings = textureOverlay.getOverlaySettings(j);
                this.glProgram.setFloatsUniform(Util.formatInvariant("uTransformationMatrix%d", Integer.valueOf(i2)), this.samplerOverlayMatrixProvider.getTransformationMatrix(textureOverlay.getTextureSize(j), overlaySettings));
                this.glProgram.setFloatUniform(Util.formatInvariant("uOverlayAlphaScale%d", Integer.valueOf(i2)), overlaySettings.alphaScale);
            }
            this.glProgram.setSamplerTexIdUniform("uVideoTexSampler0", i, 0);
            this.glProgram.bindAttributesAndUniforms();
            GLES20.glDrawArrays(5, 0, 4);
            GlUtil.checkGlError();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e, j);
        }
    }

    public void release() throws VideoFrameProcessingException {
        int i;
        super.release();
        try {
            this.glProgram.delete();
            for (int i2 = 0; i2 < this.overlays.size(); i2++) {
                ((TextureOverlay) this.overlays.get(i2)).release();
                int[] iArr = this.hdrTypes;
                if (!(iArr == null || iArr[i2] != 1 || (i = this.gainmapTexIds.get(i2, -1)) == -1)) {
                    GlUtil.deleteTexture(i);
                }
            }
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    private static int[] findHdrTypes(ImmutableList<TextureOverlay> immutableList) {
        int[] iArr = new int[immutableList.size()];
        int i = 15;
        int i2 = 0;
        while (i2 < immutableList.size()) {
            TextureOverlay textureOverlay = (TextureOverlay) immutableList.get(i2);
            if (textureOverlay instanceof TextOverlay) {
                iArr[i2] = 2;
                i--;
            } else if (textureOverlay instanceof BitmapOverlay) {
                Assertions.checkState(Util.SDK_INT >= 34);
                iArr[i2] = 1;
                i -= 2;
            } else {
                throw new IllegalArgumentException(textureOverlay + " is not supported on HDR content.");
            }
            if (i >= 0) {
                i2++;
            } else {
                throw new IllegalArgumentException("Too many HDR overlays in the same OverlayShaderProgram instance.");
            }
        }
        return iArr;
    }

    private static String createVertexShader(int i) {
        StringBuilder sb = new StringBuilder("#version 100\nattribute vec4 aFramePosition;\nvarying vec2 vVideoTexSamplingCoord0;\n");
        for (int i2 = 1; i2 <= i; i2++) {
            sb.append(Util.formatInvariant("uniform mat4 uTransformationMatrix%s;\n", Integer.valueOf(i2))).append(Util.formatInvariant("uniform mat4 uVertexTransformationMatrix%s;\n", Integer.valueOf(i2))).append(Util.formatInvariant("varying vec2 vOverlayTexSamplingCoord%s;\n", Integer.valueOf(i2)));
        }
        sb.append("vec2 getTexSamplingCoord(vec2 ndcPosition){\n  return vec2(ndcPosition.x * 0.5 + 0.5, ndcPosition.y * 0.5 + 0.5);\n}\nvoid main() {\n  gl_Position = aFramePosition;\n  vVideoTexSamplingCoord0 = getTexSamplingCoord(aFramePosition.xy);\n");
        for (int i3 = 1; i3 <= i; i3++) {
            sb.append(replaceFormatSpecifierWithIndex("      vec4 aOverlayPosition% =\n  uVertexTransformationMatrix% * uTransformationMatrix% * aFramePosition;\nvOverlayTexSamplingCoord% = getTexSamplingCoord(aOverlayPosition%.xy);", i3));
        }
        sb.append("}\n");
        return sb.toString();
    }

    private static String createFragmentShader(Context context, int i, int[] iArr) throws IOException {
        String str;
        StringBuilder sb = new StringBuilder("#version 100\nprecision mediump float;\nuniform sampler2D uVideoTexSampler0;\nvarying vec2 vVideoTexSamplingCoord0;\n\n");
        sb.append(Util.loadAsset(context, FRAGMENT_SHADER_METHODS_INSERT));
        if (iArr != null) {
            sb.append(Util.loadAsset(context, ULTRA_HDR_INSERT));
        }
        for (int i2 = 1; i2 <= i; i2++) {
            sb.append(Util.formatInvariant("uniform sampler2D uOverlayTexSampler%d;\n", Integer.valueOf(i2))).append(Util.formatInvariant("uniform float uOverlayAlphaScale%d;\n", Integer.valueOf(i2))).append(Util.formatInvariant("varying vec2 vOverlayTexSamplingCoord%d;\n", Integer.valueOf(i2))).append("\n");
            if (iArr != null) {
                int i3 = iArr[i2 - 1];
                if (i3 == 1) {
                    sb.append("// Uniforms for applying the gainmap to the base.\n").append(Util.formatInvariant("uniform sampler2D uGainmapTexSampler%d;\n", Integer.valueOf(i2))).append(Util.formatInvariant("uniform int uGainmapIsAlpha%d;\n", Integer.valueOf(i2))).append(Util.formatInvariant("uniform int uNoGamma%d;\n", Integer.valueOf(i2))).append(Util.formatInvariant("uniform int uSingleChannel%d;\n", Integer.valueOf(i2))).append(Util.formatInvariant("uniform vec4 uLogRatioMin%d;\n", Integer.valueOf(i2))).append(Util.formatInvariant("uniform vec4 uLogRatioMax%d;\n", Integer.valueOf(i2))).append(Util.formatInvariant("uniform vec4 uEpsilonSdr%d;\n", Integer.valueOf(i2))).append(Util.formatInvariant("uniform vec4 uEpsilonHdr%d;\n", Integer.valueOf(i2))).append(Util.formatInvariant("uniform vec4 uGainmapGamma%d;\n", Integer.valueOf(i2))).append(Util.formatInvariant("uniform float uDisplayRatioHdr%d;\n", Integer.valueOf(i2))).append(Util.formatInvariant("uniform float uDisplayRatioSdr%d;\n", Integer.valueOf(i2))).append("\n");
                } else if (i3 == 2) {
                    sb.append(Util.formatInvariant("uniform mat4 uLuminanceMatrix%d;\n", Integer.valueOf(i2)));
                }
            }
        }
        sb.append("void main() {\n vec4 videoColor = vec4(texture2D(uVideoTexSampler0, vVideoTexSamplingCoord0));\n vec4 fragColor = videoColor;\n");
        for (int i4 = 1; i4 <= i; i4++) {
            sb.append(replaceFormatSpecifierWithIndex("        vec4 electricalOverlayColor% = getClampToBorderOverlayColor(\n      uOverlayTexSampler%, vOverlayTexSamplingCoord%, uOverlayAlphaScale%);\n", i4));
            if (iArr != null) {
                int i5 = iArr[i4 - 1];
                if (i5 == 1) {
                    sb.append(replaceFormatSpecifierWithIndex("        vec4 gainmap% = texture2D(uGainmapTexSampler%, vOverlayTexSamplingCoord%);\n  vec3 opticalBt709Color% = applyGainmap(\n      srgbEotf(electricalOverlayColor%), gainmap%, uGainmapIsAlpha%, uNoGamma%,\n      uSingleChannel%, uLogRatioMin%, uLogRatioMax%, uEpsilonSdr%, uEpsilonHdr%,\n      uGainmapGamma%, uDisplayRatioHdr%, uDisplayRatioSdr%);\n  vec4 opticalBt2020OverlayColor% =\n      vec4(scaleHdrLuminance(bt709ToBt2020(opticalBt709Color%)),           electricalOverlayColor%.a);", i4));
                    str = "opticalBt2020OverlayColor";
                } else if (i5 == 2) {
                    sb.append(replaceFormatSpecifierWithIndex("vec4 opticalOverlayColor% = uLuminanceMatrix% * srgbEotf(electricalOverlayColor%);\n", i4));
                    str = "opticalOverlayColor";
                }
                sb.append(Util.formatInvariant("  fragColor = getMixColor(fragColor, %s%d);\n", str, Integer.valueOf(i4)));
            }
            str = "electricalOverlayColor";
            sb.append(Util.formatInvariant("  fragColor = getMixColor(fragColor, %s%d);\n", str, Integer.valueOf(i4)));
        }
        sb.append("  gl_FragColor = fragColor;\n}\n");
        return sb.toString();
    }

    private static String replaceFormatSpecifierWithIndex(String str, int i) {
        return str.replace(TEXTURE_INDEX_FORMAT_SPECIFIER, Integer.toString(i));
    }
}
