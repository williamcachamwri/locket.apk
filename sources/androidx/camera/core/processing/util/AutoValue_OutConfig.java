package androidx.camera.core.processing.util;

import android.graphics.Rect;
import android.util.Size;
import java.util.UUID;

final class AutoValue_OutConfig extends OutConfig {
    private final Rect getCropRect;
    private final int getFormat;
    private final int getRotationDegrees;
    private final Size getSize;
    private final int getTargets;
    private final UUID getUuid;
    private final boolean isMirroring;
    private final boolean shouldRespectInputCropRect;

    AutoValue_OutConfig(UUID uuid, int i, int i2, Rect rect, Size size, int i3, boolean z, boolean z2) {
        if (uuid != null) {
            this.getUuid = uuid;
            this.getTargets = i;
            this.getFormat = i2;
            if (rect != null) {
                this.getCropRect = rect;
                if (size != null) {
                    this.getSize = size;
                    this.getRotationDegrees = i3;
                    this.isMirroring = z;
                    this.shouldRespectInputCropRect = z2;
                    return;
                }
                throw new NullPointerException("Null getSize");
            }
            throw new NullPointerException("Null getCropRect");
        }
        throw new NullPointerException("Null getUuid");
    }

    /* access modifiers changed from: package-private */
    public UUID getUuid() {
        return this.getUuid;
    }

    public int getTargets() {
        return this.getTargets;
    }

    public int getFormat() {
        return this.getFormat;
    }

    public Rect getCropRect() {
        return this.getCropRect;
    }

    public Size getSize() {
        return this.getSize;
    }

    public int getRotationDegrees() {
        return this.getRotationDegrees;
    }

    public boolean isMirroring() {
        return this.isMirroring;
    }

    public boolean shouldRespectInputCropRect() {
        return this.shouldRespectInputCropRect;
    }

    public String toString() {
        return "OutConfig{getUuid=" + this.getUuid + ", getTargets=" + this.getTargets + ", getFormat=" + this.getFormat + ", getCropRect=" + this.getCropRect + ", getSize=" + this.getSize + ", getRotationDegrees=" + this.getRotationDegrees + ", isMirroring=" + this.isMirroring + ", shouldRespectInputCropRect=" + this.shouldRespectInputCropRect + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OutConfig)) {
            return false;
        }
        OutConfig outConfig = (OutConfig) obj;
        if (this.getUuid.equals(outConfig.getUuid()) && this.getTargets == outConfig.getTargets() && this.getFormat == outConfig.getFormat() && this.getCropRect.equals(outConfig.getCropRect()) && this.getSize.equals(outConfig.getSize()) && this.getRotationDegrees == outConfig.getRotationDegrees() && this.isMirroring == outConfig.isMirroring() && this.shouldRespectInputCropRect == outConfig.shouldRespectInputCropRect()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 1231;
        int hashCode = (((((((((((((this.getUuid.hashCode() ^ 1000003) * 1000003) ^ this.getTargets) * 1000003) ^ this.getFormat) * 1000003) ^ this.getCropRect.hashCode()) * 1000003) ^ this.getSize.hashCode()) * 1000003) ^ this.getRotationDegrees) * 1000003) ^ (this.isMirroring ? 1231 : 1237)) * 1000003;
        if (!this.shouldRespectInputCropRect) {
            i = 1237;
        }
        return hashCode ^ i;
    }
}
