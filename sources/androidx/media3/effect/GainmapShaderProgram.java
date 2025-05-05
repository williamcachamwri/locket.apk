package androidx.media3.effect;

import android.graphics.Gainmap;
import androidx.media3.common.util.GlUtil;

interface GainmapShaderProgram extends GlShaderProgram {
    void setGainmap(Gainmap gainmap) throws GlUtil.GlException;
}
