package androidx.camera.core;

import android.graphics.Rect;
import android.util.Size;
import androidx.camera.core.ResolutionInfo;

final class AutoValue_ResolutionInfo_ResolutionInfoInternal extends ResolutionInfo.ResolutionInfoInternal {
    private final Rect cropRect;
    private final Size resolution;
    private final int rotationDegrees;

    private AutoValue_ResolutionInfo_ResolutionInfoInternal(Size size, Rect rect, int i) {
        this.resolution = size;
        this.cropRect = rect;
        this.rotationDegrees = i;
    }

    /* access modifiers changed from: package-private */
    public Size getResolution() {
        return this.resolution;
    }

    /* access modifiers changed from: package-private */
    public Rect getCropRect() {
        return this.cropRect;
    }

    /* access modifiers changed from: package-private */
    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    public String toString() {
        return "ResolutionInfoInternal{resolution=" + this.resolution + ", cropRect=" + this.cropRect + ", rotationDegrees=" + this.rotationDegrees + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResolutionInfo.ResolutionInfoInternal)) {
            return false;
        }
        ResolutionInfo.ResolutionInfoInternal resolutionInfoInternal = (ResolutionInfo.ResolutionInfoInternal) obj;
        if (!this.resolution.equals(resolutionInfoInternal.getResolution()) || !this.cropRect.equals(resolutionInfoInternal.getCropRect()) || this.rotationDegrees != resolutionInfoInternal.getRotationDegrees()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((this.resolution.hashCode() ^ 1000003) * 1000003) ^ this.cropRect.hashCode()) * 1000003) ^ this.rotationDegrees;
    }

    static final class Builder extends ResolutionInfo.ResolutionInfoInternal.Builder {
        private Rect cropRect;
        private Size resolution;
        private Integer rotationDegrees;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public ResolutionInfo.ResolutionInfoInternal.Builder setResolution(Size size) {
            if (size != null) {
                this.resolution = size;
                return this;
            }
            throw new NullPointerException("Null resolution");
        }

        /* access modifiers changed from: package-private */
        public ResolutionInfo.ResolutionInfoInternal.Builder setCropRect(Rect rect) {
            if (rect != null) {
                this.cropRect = rect;
                return this;
            }
            throw new NullPointerException("Null cropRect");
        }

        /* access modifiers changed from: package-private */
        public ResolutionInfo.ResolutionInfoInternal.Builder setRotationDegrees(int i) {
            this.rotationDegrees = Integer.valueOf(i);
            return this;
        }

        /* access modifiers changed from: package-private */
        public ResolutionInfo.ResolutionInfoInternal build() {
            String str = this.resolution == null ? " resolution" : "";
            if (this.cropRect == null) {
                str = str + " cropRect";
            }
            if (this.rotationDegrees == null) {
                str = str + " rotationDegrees";
            }
            if (str.isEmpty()) {
                return new AutoValue_ResolutionInfo_ResolutionInfoInternal(this.resolution, this.cropRect, this.rotationDegrees.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
