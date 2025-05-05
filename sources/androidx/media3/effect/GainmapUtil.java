package androidx.media3.effect;

import android.graphics.Bitmap;
import android.graphics.Gainmap;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;

class GainmapUtil {
    private GainmapUtil() {
    }

    public static boolean equals(Gainmap gainmap, Gainmap gainmap2) {
        return gainmap.getGamma() == gainmap2.getGamma() && gainmap.getRatioMax() == gainmap2.getRatioMax() && gainmap.getRatioMin() == gainmap2.getRatioMin() && gainmap.getEpsilonHdr() == gainmap2.getEpsilonHdr() && gainmap.getEpsilonSdr() == gainmap2.getEpsilonSdr() && gainmap.getDisplayRatioForFullHdr() == gainmap2.getDisplayRatioForFullHdr() && gainmap.getMinDisplayRatioForHdrTransition() == gainmap2.getMinDisplayRatioForHdrTransition() && gainmap.getGainmapContents() == gainmap2.getGainmapContents() && gainmap.getGainmapContents().getGenerationId() == gainmap2.getGainmapContents().getGenerationId();
    }

    public static void setGainmapUniforms(GlProgram glProgram, Gainmap gainmap, int i) throws GlUtil.GlException {
        int i2 = 1;
        int i3 = gainmap.getGainmapContents().getConfig() == Bitmap.Config.ALPHA_8 ? 1 : 0;
        float[] gamma = gainmap.getGamma();
        int i4 = (gamma[0] == 1.0f && gamma[1] == 1.0f && gamma[2] == 1.0f) ? 1 : 0;
        if (!areAllChannelsEqual(gamma) || !areAllChannelsEqual(gainmap.getRatioMax()) || !areAllChannelsEqual(gainmap.getRatioMin())) {
            i2 = 0;
        }
        glProgram.setIntUniform(addIndex("uGainmapIsAlpha", i), i3);
        glProgram.setIntUniform(addIndex("uNoGamma", i), i4);
        glProgram.setIntUniform(addIndex("uSingleChannel", i), i2);
        glProgram.setFloatsUniform(addIndex("uLogRatioMin", i), logRgb(gainmap.getRatioMin()));
        glProgram.setFloatsUniform(addIndex("uLogRatioMax", i), logRgb(gainmap.getRatioMax()));
        glProgram.setFloatsUniform(addIndex("uEpsilonSdr", i), gainmap.getEpsilonSdr());
        glProgram.setFloatsUniform(addIndex("uEpsilonHdr", i), gainmap.getEpsilonHdr());
        glProgram.setFloatsUniform(addIndex("uGainmapGamma", i), gamma);
        glProgram.setFloatUniform(addIndex("uDisplayRatioHdr", i), gainmap.getDisplayRatioForFullHdr());
        glProgram.setFloatUniform(addIndex("uDisplayRatioSdr", i), gainmap.getMinDisplayRatioForHdrTransition());
        GlUtil.checkGlError();
    }

    private static boolean areAllChannelsEqual(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        return f == f2 && f2 == fArr[2];
    }

    private static String addIndex(String str, int i) {
        return i == -1 ? str : str + i;
    }

    private static float[] logRgb(float[] fArr) {
        return new float[]{(float) Math.log((double) fArr[0]), (float) Math.log((double) fArr[1]), (float) Math.log((double) fArr[2])};
    }
}
