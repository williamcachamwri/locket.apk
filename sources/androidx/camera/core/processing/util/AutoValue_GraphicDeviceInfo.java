package androidx.camera.core.processing.util;

import androidx.camera.core.processing.util.GraphicDeviceInfo;

final class AutoValue_GraphicDeviceInfo extends GraphicDeviceInfo {
    private final String eglExtensions;
    private final String eglVersion;
    private final String glExtensions;
    private final String glVersion;

    private AutoValue_GraphicDeviceInfo(String str, String str2, String str3, String str4) {
        this.glVersion = str;
        this.eglVersion = str2;
        this.glExtensions = str3;
        this.eglExtensions = str4;
    }

    public String getGlVersion() {
        return this.glVersion;
    }

    public String getEglVersion() {
        return this.eglVersion;
    }

    public String getGlExtensions() {
        return this.glExtensions;
    }

    public String getEglExtensions() {
        return this.eglExtensions;
    }

    public String toString() {
        return "GraphicDeviceInfo{glVersion=" + this.glVersion + ", eglVersion=" + this.eglVersion + ", glExtensions=" + this.glExtensions + ", eglExtensions=" + this.eglExtensions + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GraphicDeviceInfo)) {
            return false;
        }
        GraphicDeviceInfo graphicDeviceInfo = (GraphicDeviceInfo) obj;
        if (!this.glVersion.equals(graphicDeviceInfo.getGlVersion()) || !this.eglVersion.equals(graphicDeviceInfo.getEglVersion()) || !this.glExtensions.equals(graphicDeviceInfo.getGlExtensions()) || !this.eglExtensions.equals(graphicDeviceInfo.getEglExtensions())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((this.glVersion.hashCode() ^ 1000003) * 1000003) ^ this.eglVersion.hashCode()) * 1000003) ^ this.glExtensions.hashCode()) * 1000003) ^ this.eglExtensions.hashCode();
    }

    static final class Builder extends GraphicDeviceInfo.Builder {
        private String eglExtensions;
        private String eglVersion;
        private String glExtensions;
        private String glVersion;

        Builder() {
        }

        public GraphicDeviceInfo.Builder setGlVersion(String str) {
            if (str != null) {
                this.glVersion = str;
                return this;
            }
            throw new NullPointerException("Null glVersion");
        }

        public GraphicDeviceInfo.Builder setEglVersion(String str) {
            if (str != null) {
                this.eglVersion = str;
                return this;
            }
            throw new NullPointerException("Null eglVersion");
        }

        public GraphicDeviceInfo.Builder setGlExtensions(String str) {
            if (str != null) {
                this.glExtensions = str;
                return this;
            }
            throw new NullPointerException("Null glExtensions");
        }

        public GraphicDeviceInfo.Builder setEglExtensions(String str) {
            if (str != null) {
                this.eglExtensions = str;
                return this;
            }
            throw new NullPointerException("Null eglExtensions");
        }

        public GraphicDeviceInfo build() {
            String str = this.glVersion == null ? " glVersion" : "";
            if (this.eglVersion == null) {
                str = str + " eglVersion";
            }
            if (this.glExtensions == null) {
                str = str + " glExtensions";
            }
            if (this.eglExtensions == null) {
                str = str + " eglExtensions";
            }
            if (str.isEmpty()) {
                return new AutoValue_GraphicDeviceInfo(this.glVersion, this.eglVersion, this.glExtensions, this.eglExtensions);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
