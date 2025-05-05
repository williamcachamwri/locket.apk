package androidx.camera.camera2.internal;

import android.util.Size;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.compat.workaround.PreviewPixelHDRnet;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.PreviewConfig;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfig;

final class Camera2SessionOptionUnpacker implements SessionConfig.OptionUnpacker {
    static final Camera2SessionOptionUnpacker INSTANCE = new Camera2SessionOptionUnpacker();

    Camera2SessionOptionUnpacker() {
    }

    public void unpack(Size size, UseCaseConfig<?> useCaseConfig, SessionConfig.Builder builder) {
        SessionConfig defaultSessionConfig = useCaseConfig.getDefaultSessionConfig((SessionConfig) null);
        Config emptyBundle = OptionsBundle.emptyBundle();
        int templateType = SessionConfig.defaultEmptySessionConfig().getTemplateType();
        if (defaultSessionConfig != null) {
            templateType = defaultSessionConfig.getTemplateType();
            builder.addAllDeviceStateCallbacks(defaultSessionConfig.getDeviceStateCallbacks());
            builder.addAllSessionStateCallbacks(defaultSessionConfig.getSessionStateCallbacks());
            builder.addAllRepeatingCameraCaptureCallbacks(defaultSessionConfig.getRepeatingCameraCaptureCallbacks());
            emptyBundle = defaultSessionConfig.getImplementationOptions();
        }
        builder.setImplementationOptions(emptyBundle);
        if (useCaseConfig instanceof PreviewConfig) {
            PreviewPixelHDRnet.setHDRnet(size, builder);
        }
        Camera2ImplConfig camera2ImplConfig = new Camera2ImplConfig(useCaseConfig);
        builder.setTemplateType(camera2ImplConfig.getCaptureRequestTemplate(templateType));
        builder.addDeviceStateCallback(camera2ImplConfig.getDeviceStateCallback(CameraDeviceStateCallbacks.createNoOpCallback()));
        builder.addSessionStateCallback(camera2ImplConfig.getSessionStateCallback(CameraCaptureSessionStateCallbacks.createNoOpCallback()));
        builder.addCameraCaptureCallback(CaptureCallbackContainer.create(camera2ImplConfig.getSessionCaptureCallback(Camera2CaptureCallbacks.createNoOpCallback())));
        builder.setVideoStabilization(useCaseConfig.getVideoStabilizationMode());
        builder.setPreviewStabilization(useCaseConfig.getPreviewStabilizationMode());
        MutableOptionsBundle create = MutableOptionsBundle.create();
        create.insertOption(Camera2ImplConfig.SESSION_PHYSICAL_CAMERA_ID_OPTION, camera2ImplConfig.getPhysicalCameraId((String) null));
        create.insertOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION, Long.valueOf(camera2ImplConfig.getStreamUseCase(-1)));
        builder.addImplementationOptions(create);
        builder.addImplementationOptions(camera2ImplConfig.getCaptureRequestOptions());
    }
}
