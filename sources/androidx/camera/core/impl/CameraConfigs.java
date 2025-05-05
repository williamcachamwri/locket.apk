package androidx.camera.core.impl;

public class CameraConfigs {
    private static final CameraConfig DEFAULT_CAMERA_CONFIG = new DefaultCameraConfig();

    public static CameraConfig defaultConfig() {
        return DEFAULT_CAMERA_CONFIG;
    }

    static final class DefaultCameraConfig implements CameraConfig {
        private final Identifier mIdentifier = Identifier.create(new Object());

        DefaultCameraConfig() {
        }

        public Identifier getCompatibilityId() {
            return this.mIdentifier;
        }

        public Config getConfig() {
            return OptionsBundle.emptyBundle();
        }
    }

    private CameraConfigs() {
    }
}
