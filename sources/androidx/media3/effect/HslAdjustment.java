package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;

public final class HslAdjustment implements GlEffect {
    public final float hueAdjustmentDegrees;
    public final float lightnessAdjustment;
    public final float saturationAdjustment;

    public static final class Builder {
        private float hueAdjustment;
        private float lightnessAdjustment;
        private float saturationAdjustment;

        public Builder adjustHue(float f) {
            this.hueAdjustment = f % 360.0f;
            return this;
        }

        public Builder adjustSaturation(float f) {
            Assertions.checkArgument(-100.0f <= f && f <= 100.0f, "Can adjust the saturation by only 100 in either direction, but provided " + f);
            this.saturationAdjustment = f;
            return this;
        }

        public Builder adjustLightness(float f) {
            Assertions.checkArgument(-100.0f <= f && f <= 100.0f, "Can adjust the lightness by only 100 in either direction, but provided " + f);
            this.lightnessAdjustment = f;
            return this;
        }

        public HslAdjustment build() {
            return new HslAdjustment(this.hueAdjustment, this.saturationAdjustment, this.lightnessAdjustment);
        }
    }

    private HslAdjustment(float f, float f2, float f3) {
        this.hueAdjustmentDegrees = f;
        this.saturationAdjustment = f2;
        this.lightnessAdjustment = f3;
    }

    public BaseGlShaderProgram toGlShaderProgram(Context context, boolean z) throws VideoFrameProcessingException {
        return new HslShaderProgram(context, this, z);
    }

    public boolean isNoOp(int i, int i2) {
        return this.hueAdjustmentDegrees == 0.0f && this.saturationAdjustment == 0.0f && this.lightnessAdjustment == 0.0f;
    }
}
