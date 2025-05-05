package androidx.camera.camera2.internal;

import androidx.camera.camera2.internal.SupportedSurfaceCombination;

final class AutoValue_SupportedSurfaceCombination_FeatureSettings extends SupportedSurfaceCombination.FeatureSettings {
    private final int cameraMode;
    private final boolean previewStabilizationOn;
    private final int requiredMaxBitDepth;
    private final boolean ultraHdrOn;

    AutoValue_SupportedSurfaceCombination_FeatureSettings(int i, int i2, boolean z, boolean z2) {
        this.cameraMode = i;
        this.requiredMaxBitDepth = i2;
        this.previewStabilizationOn = z;
        this.ultraHdrOn = z2;
    }

    /* access modifiers changed from: package-private */
    public int getCameraMode() {
        return this.cameraMode;
    }

    /* access modifiers changed from: package-private */
    public int getRequiredMaxBitDepth() {
        return this.requiredMaxBitDepth;
    }

    /* access modifiers changed from: package-private */
    public boolean isPreviewStabilizationOn() {
        return this.previewStabilizationOn;
    }

    /* access modifiers changed from: package-private */
    public boolean isUltraHdrOn() {
        return this.ultraHdrOn;
    }

    public String toString() {
        return "FeatureSettings{cameraMode=" + this.cameraMode + ", requiredMaxBitDepth=" + this.requiredMaxBitDepth + ", previewStabilizationOn=" + this.previewStabilizationOn + ", ultraHdrOn=" + this.ultraHdrOn + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SupportedSurfaceCombination.FeatureSettings)) {
            return false;
        }
        SupportedSurfaceCombination.FeatureSettings featureSettings = (SupportedSurfaceCombination.FeatureSettings) obj;
        if (this.cameraMode == featureSettings.getCameraMode() && this.requiredMaxBitDepth == featureSettings.getRequiredMaxBitDepth() && this.previewStabilizationOn == featureSettings.isPreviewStabilizationOn() && this.ultraHdrOn == featureSettings.isUltraHdrOn()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 1231;
        int i2 = (((((this.cameraMode ^ 1000003) * 1000003) ^ this.requiredMaxBitDepth) * 1000003) ^ (this.previewStabilizationOn ? 1231 : 1237)) * 1000003;
        if (!this.ultraHdrOn) {
            i = 1237;
        }
        return i2 ^ i;
    }
}
