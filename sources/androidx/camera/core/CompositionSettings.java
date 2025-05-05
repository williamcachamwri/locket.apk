package androidx.camera.core;

import androidx.core.util.Pair;

public class CompositionSettings {
    public static final CompositionSettings DEFAULT = new Builder().setAlpha(1.0f).setOffset(0.0f, 0.0f).setScale(1.0f, 1.0f).build();
    private final float mAlpha;
    private final Pair<Float, Float> mOffset;
    private final Pair<Float, Float> mScale;

    private CompositionSettings(float f, Pair<Float, Float> pair, Pair<Float, Float> pair2) {
        this.mAlpha = f;
        this.mOffset = pair;
        this.mScale = pair2;
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public Pair<Float, Float> getOffset() {
        return this.mOffset;
    }

    public Pair<Float, Float> getScale() {
        return this.mScale;
    }

    public static final class Builder {
        private float mAlpha = 1.0f;
        private Pair<Float, Float> mOffset;
        private Pair<Float, Float> mScale;

        public Builder() {
            Float valueOf = Float.valueOf(1.0f);
            Float valueOf2 = Float.valueOf(0.0f);
            this.mOffset = Pair.create(valueOf2, valueOf2);
            this.mScale = Pair.create(valueOf, valueOf);
        }

        public Builder setAlpha(float f) {
            this.mAlpha = f;
            return this;
        }

        public Builder setOffset(float f, float f2) {
            this.mOffset = Pair.create(Float.valueOf(f), Float.valueOf(f2));
            return this;
        }

        public Builder setScale(float f, float f2) {
            this.mScale = Pair.create(Float.valueOf(f), Float.valueOf(f2));
            return this;
        }

        public CompositionSettings build() {
            return new CompositionSettings(this.mAlpha, this.mOffset, this.mScale);
        }
    }
}
