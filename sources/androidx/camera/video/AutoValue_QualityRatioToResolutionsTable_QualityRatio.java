package androidx.camera.video;

import androidx.camera.video.QualityRatioToResolutionsTable;

final class AutoValue_QualityRatioToResolutionsTable_QualityRatio extends QualityRatioToResolutionsTable.QualityRatio {
    private final int aspectRatio;
    private final Quality quality;

    AutoValue_QualityRatioToResolutionsTable_QualityRatio(Quality quality2, int i) {
        if (quality2 != null) {
            this.quality = quality2;
            this.aspectRatio = i;
            return;
        }
        throw new NullPointerException("Null quality");
    }

    /* access modifiers changed from: package-private */
    public Quality getQuality() {
        return this.quality;
    }

    /* access modifiers changed from: package-private */
    public int getAspectRatio() {
        return this.aspectRatio;
    }

    public String toString() {
        return "QualityRatio{quality=" + this.quality + ", aspectRatio=" + this.aspectRatio + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QualityRatioToResolutionsTable.QualityRatio)) {
            return false;
        }
        QualityRatioToResolutionsTable.QualityRatio qualityRatio = (QualityRatioToResolutionsTable.QualityRatio) obj;
        if (!this.quality.equals(qualityRatio.getQuality()) || this.aspectRatio != qualityRatio.getAspectRatio()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.quality.hashCode() ^ 1000003) * 1000003) ^ this.aspectRatio;
    }
}
