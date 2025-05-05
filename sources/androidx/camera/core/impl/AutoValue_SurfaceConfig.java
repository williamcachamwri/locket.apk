package androidx.camera.core.impl;

import androidx.camera.core.impl.SurfaceConfig;

final class AutoValue_SurfaceConfig extends SurfaceConfig {
    private final SurfaceConfig.ConfigSize configSize;
    private final SurfaceConfig.ConfigType configType;
    private final long streamUseCase;

    AutoValue_SurfaceConfig(SurfaceConfig.ConfigType configType2, SurfaceConfig.ConfigSize configSize2, long j) {
        if (configType2 != null) {
            this.configType = configType2;
            if (configSize2 != null) {
                this.configSize = configSize2;
                this.streamUseCase = j;
                return;
            }
            throw new NullPointerException("Null configSize");
        }
        throw new NullPointerException("Null configType");
    }

    public SurfaceConfig.ConfigType getConfigType() {
        return this.configType;
    }

    public SurfaceConfig.ConfigSize getConfigSize() {
        return this.configSize;
    }

    public long getStreamUseCase() {
        return this.streamUseCase;
    }

    public String toString() {
        return "SurfaceConfig{configType=" + this.configType + ", configSize=" + this.configSize + ", streamUseCase=" + this.streamUseCase + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SurfaceConfig)) {
            return false;
        }
        SurfaceConfig surfaceConfig = (SurfaceConfig) obj;
        if (!this.configType.equals(surfaceConfig.getConfigType()) || !this.configSize.equals(surfaceConfig.getConfigSize()) || this.streamUseCase != surfaceConfig.getStreamUseCase()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.streamUseCase;
        return ((((this.configType.hashCode() ^ 1000003) * 1000003) ^ this.configSize.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }
}
