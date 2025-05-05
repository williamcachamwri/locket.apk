package androidx.media3.effect;

import android.util.Pair;
import androidx.media3.common.util.Assertions;

public final class OverlaySettings {
    public final float alphaScale;
    public final Pair<Float, Float> backgroundFrameAnchor;
    public final float hdrLuminanceMultiplier;
    public final Pair<Float, Float> overlayFrameAnchor;
    public final float rotationDegrees;
    public final Pair<Float, Float> scale;

    public static final class Builder {
        private float alphaScale;
        private Pair<Float, Float> backgroundFrameAnchor;
        private float hdrLuminanceMultiplier;
        private Pair<Float, Float> overlayFrameAnchor;
        private float rotationDegrees;
        private Pair<Float, Float> scale;

        public Builder() {
            Float valueOf = Float.valueOf(1.0f);
            this.alphaScale = 1.0f;
            Float valueOf2 = Float.valueOf(0.0f);
            this.backgroundFrameAnchor = Pair.create(valueOf2, valueOf2);
            this.overlayFrameAnchor = Pair.create(valueOf2, valueOf2);
            this.scale = Pair.create(valueOf, valueOf);
            this.rotationDegrees = 0.0f;
            this.hdrLuminanceMultiplier = 1.0f;
        }

        private Builder(OverlaySettings overlaySettings) {
            this.alphaScale = overlaySettings.alphaScale;
            this.backgroundFrameAnchor = overlaySettings.backgroundFrameAnchor;
            this.overlayFrameAnchor = overlaySettings.overlayFrameAnchor;
            this.scale = overlaySettings.scale;
            this.rotationDegrees = overlaySettings.rotationDegrees;
        }

        public Builder setAlphaScale(float f) {
            Assertions.checkArgument(0.0f <= f, "alphaScale needs to be greater than or equal to zero.");
            this.alphaScale = f;
            return this;
        }

        public Builder setBackgroundFrameAnchor(float f, float f2) {
            boolean z = true;
            Assertions.checkArgument(-1.0f <= f && f <= 1.0f);
            if (-1.0f > f2 || f2 > 1.0f) {
                z = false;
            }
            Assertions.checkArgument(z);
            this.backgroundFrameAnchor = Pair.create(Float.valueOf(f), Float.valueOf(f2));
            return this;
        }

        public Builder setOverlayFrameAnchor(float f, float f2) {
            boolean z = true;
            Assertions.checkArgument(-1.0f <= f && f <= 1.0f);
            if (-1.0f > f2 || f2 > 1.0f) {
                z = false;
            }
            Assertions.checkArgument(z);
            this.overlayFrameAnchor = Pair.create(Float.valueOf(f), Float.valueOf(f2));
            return this;
        }

        public Builder setScale(float f, float f2) {
            this.scale = Pair.create(Float.valueOf(f), Float.valueOf(f2));
            return this;
        }

        public Builder setRotationDegrees(float f) {
            this.rotationDegrees = f;
            return this;
        }

        public Builder setHdrLuminanceMultiplier(float f) {
            this.hdrLuminanceMultiplier = f;
            return this;
        }

        public OverlaySettings build() {
            return new OverlaySettings(this.alphaScale, this.backgroundFrameAnchor, this.overlayFrameAnchor, this.scale, this.rotationDegrees, this.hdrLuminanceMultiplier);
        }
    }

    private OverlaySettings(float f, Pair<Float, Float> pair, Pair<Float, Float> pair2, Pair<Float, Float> pair3, float f2, float f3) {
        this.alphaScale = f;
        this.backgroundFrameAnchor = pair;
        this.overlayFrameAnchor = pair2;
        this.scale = pair3;
        this.rotationDegrees = f2;
        this.hdrLuminanceMultiplier = f3;
    }

    /* access modifiers changed from: package-private */
    public Builder buildUpon() {
        return new Builder();
    }
}
