package androidx.camera.core.processing.concurrent;

import androidx.camera.core.processing.util.OutConfig;

final class AutoValue_DualOutConfig extends DualOutConfig {
    private final OutConfig primaryOutConfig;
    private final OutConfig secondaryOutConfig;

    AutoValue_DualOutConfig(OutConfig outConfig, OutConfig outConfig2) {
        if (outConfig != null) {
            this.primaryOutConfig = outConfig;
            if (outConfig2 != null) {
                this.secondaryOutConfig = outConfig2;
                return;
            }
            throw new NullPointerException("Null secondaryOutConfig");
        }
        throw new NullPointerException("Null primaryOutConfig");
    }

    public OutConfig getPrimaryOutConfig() {
        return this.primaryOutConfig;
    }

    public OutConfig getSecondaryOutConfig() {
        return this.secondaryOutConfig;
    }

    public String toString() {
        return "DualOutConfig{primaryOutConfig=" + this.primaryOutConfig + ", secondaryOutConfig=" + this.secondaryOutConfig + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DualOutConfig)) {
            return false;
        }
        DualOutConfig dualOutConfig = (DualOutConfig) obj;
        if (!this.primaryOutConfig.equals(dualOutConfig.getPrimaryOutConfig()) || !this.secondaryOutConfig.equals(dualOutConfig.getSecondaryOutConfig())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.primaryOutConfig.hashCode() ^ 1000003) * 1000003) ^ this.secondaryOutConfig.hashCode();
    }
}
