package androidx.camera.core.processing.util;

import androidx.camera.core.processing.util.AutoValue_GraphicDeviceInfo;

public abstract class GraphicDeviceInfo {

    public static abstract class Builder {
        public abstract GraphicDeviceInfo build();

        public abstract Builder setEglExtensions(String str);

        public abstract Builder setEglVersion(String str);

        public abstract Builder setGlExtensions(String str);

        public abstract Builder setGlVersion(String str);
    }

    public abstract String getEglExtensions();

    public abstract String getEglVersion();

    public abstract String getGlExtensions();

    public abstract String getGlVersion();

    public static Builder builder() {
        return new AutoValue_GraphicDeviceInfo.Builder().setGlVersion("0.0").setEglVersion("0.0").setGlExtensions("").setEglExtensions("");
    }

    GraphicDeviceInfo() {
    }
}
