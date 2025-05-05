package androidx.media3.effect;

import androidx.media3.common.util.Assertions;
import java.util.Objects;

public final class GaussianFunction implements ConvolutionFunction1D {
    private final float numStdDev;
    private final float sigma;

    public GaussianFunction(float f, float f2) {
        Assertions.checkArgument(f > 0.0f && f2 > 0.0f);
        this.sigma = f;
        this.numStdDev = f2;
    }

    public float domainStart() {
        return (-this.numStdDev) * this.sigma;
    }

    public float domainEnd() {
        return this.numStdDev * this.sigma;
    }

    public float value(float f) {
        float abs = Math.abs(f);
        float f2 = this.numStdDev;
        float f3 = this.sigma;
        if (abs > f2 * f3) {
            return 0.0f;
        }
        float f4 = f / f3;
        return (float) ((Math.exp((double) (((-f4) * f4) / 2.0f)) / Math.sqrt(6.283185307179586d)) / ((double) this.sigma));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GaussianFunction)) {
            return false;
        }
        GaussianFunction gaussianFunction = (GaussianFunction) obj;
        if (Float.compare(gaussianFunction.sigma, this.sigma) == 0 && Float.compare(gaussianFunction.numStdDev, this.numStdDev) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Float.valueOf(this.sigma), Float.valueOf(this.numStdDev)});
    }
}
