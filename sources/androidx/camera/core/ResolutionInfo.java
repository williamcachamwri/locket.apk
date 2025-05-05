package androidx.camera.core;

import android.graphics.Rect;
import android.util.Size;
import androidx.camera.core.AutoValue_ResolutionInfo_ResolutionInfoInternal;

public class ResolutionInfo {
    private final ResolutionInfoInternal mResolutionInfoInternal;

    public ResolutionInfo(Size size, Rect rect, int i) {
        this.mResolutionInfoInternal = new AutoValue_ResolutionInfo_ResolutionInfoInternal.Builder().setResolution(size).setCropRect(rect).setRotationDegrees(i).build();
    }

    public Size getResolution() {
        return this.mResolutionInfoInternal.getResolution();
    }

    public Rect getCropRect() {
        return this.mResolutionInfoInternal.getCropRect();
    }

    public int getRotationDegrees() {
        return this.mResolutionInfoInternal.getRotationDegrees();
    }

    public int hashCode() {
        return this.mResolutionInfoInternal.hashCode();
    }

    public boolean equals(Object obj) {
        return this.mResolutionInfoInternal.equals(obj);
    }

    public String toString() {
        return this.mResolutionInfoInternal.toString();
    }

    static abstract class ResolutionInfoInternal {
        /* access modifiers changed from: package-private */
        public abstract Rect getCropRect();

        /* access modifiers changed from: package-private */
        public abstract Size getResolution();

        /* access modifiers changed from: package-private */
        public abstract int getRotationDegrees();

        ResolutionInfoInternal() {
        }

        static abstract class Builder {
            /* access modifiers changed from: package-private */
            public abstract ResolutionInfoInternal build();

            /* access modifiers changed from: package-private */
            public abstract Builder setCropRect(Rect rect);

            /* access modifiers changed from: package-private */
            public abstract Builder setResolution(Size size);

            /* access modifiers changed from: package-private */
            public abstract Builder setRotationDegrees(int i);

            Builder() {
            }
        }
    }
}
