package androidx.media3.effect;

import java.util.Objects;

final class ScaledLanczosFunction implements ConvolutionFunction1D {
    private final float radius;
    private final float scale;

    public ScaledLanczosFunction(float f, float f2) {
        this.radius = f;
        this.scale = f2;
    }

    public float domainStart() {
        return (-this.radius) / this.scale;
    }

    public float domainEnd() {
        return this.radius / this.scale;
    }

    public float value(float f) {
        float f2 = f * this.scale;
        if (((double) Math.abs(f2)) < 1.0E-5d) {
            return 1.0f;
        }
        float abs = Math.abs(f2);
        float f3 = this.radius;
        if (abs > f3) {
            return 0.0f;
        }
        double d = (double) f2;
        double d2 = 3.141592653589793d * d;
        return (float) (((((double) f3) * Math.sin(d2)) * Math.sin(d2 / ((double) this.radius))) / ((9.869604401089358d * d) * d));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScaledLanczosFunction)) {
            return false;
        }
        ScaledLanczosFunction scaledLanczosFunction = (ScaledLanczosFunction) obj;
        if (Float.compare(scaledLanczosFunction.radius, this.radius) == 0 && Float.compare(scaledLanczosFunction.scale, this.scale) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Float.valueOf(this.radius), Float.valueOf(this.scale)});
    }
}
