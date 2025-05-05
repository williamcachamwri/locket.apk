package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.workaround.TemplateParamsOverride;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.StreamSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Camera2CaptureRequestBuilder {
    private static final String TAG = "Camera2CaptureRequestBuilder";

    private Camera2CaptureRequestBuilder() {
    }

    private static List<Surface> getConfiguredSurfaces(List<DeferrableSurface> list, Map<DeferrableSurface, Surface> map) {
        ArrayList arrayList = new ArrayList();
        for (DeferrableSurface deferrableSurface : list) {
            Surface surface = map.get(deferrableSurface);
            if (surface != null) {
                arrayList.add(surface);
            } else {
                throw new IllegalArgumentException("DeferrableSurface not in configuredSurfaceMap");
            }
        }
        return arrayList;
    }

    private static void applyTemplateParamsOverrideWorkaround(CaptureRequest.Builder builder, int i, TemplateParamsOverride templateParamsOverride) {
        for (Map.Entry next : templateParamsOverride.getOverrideParams(i).entrySet()) {
            builder.set((CaptureRequest.Key) next.getKey(), next.getValue());
        }
    }

    private static void applyImplementationOptionToCaptureBuilder(CaptureRequest.Builder builder, Config config) {
        CaptureRequestOptions build = CaptureRequestOptions.Builder.from(config).build();
        for (Config.Option next : build.listOptions()) {
            CaptureRequest.Key key = (CaptureRequest.Key) next.getToken();
            try {
                builder.set(key, build.retrieveOption(next));
            } catch (IllegalArgumentException unused) {
                Logger.e(TAG, "CaptureRequest.Key is not supported: " + key);
            }
        }
    }

    private static void applyAeFpsRange(CaptureConfig captureConfig, CaptureRequest.Builder builder) {
        if (!captureConfig.getExpectedFrameRateRange().equals(StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED)) {
            builder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, captureConfig.getExpectedFrameRateRange());
        }
    }

    static void applyVideoStabilization(CaptureConfig captureConfig, CaptureRequest.Builder builder) {
        if (captureConfig.getPreviewStabilizationMode() == 1 || captureConfig.getVideoStabilizationMode() == 1) {
            builder.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 0);
        } else if (captureConfig.getPreviewStabilizationMode() == 2) {
            builder.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 2);
        } else if (captureConfig.getVideoStabilizationMode() == 2) {
            builder.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 1);
        }
    }

    public static CaptureRequest build(CaptureConfig captureConfig, CameraDevice cameraDevice, Map<DeferrableSurface, Surface> map, boolean z, TemplateParamsOverride templateParamsOverride) throws CameraAccessException {
        CaptureRequest.Builder builder;
        if (cameraDevice == null) {
            return null;
        }
        List<Surface> configuredSurfaces = getConfiguredSurfaces(captureConfig.getSurfaces(), map);
        if (configuredSurfaces.isEmpty()) {
            return null;
        }
        CameraCaptureResult cameraCaptureResult = captureConfig.getCameraCaptureResult();
        if (captureConfig.getTemplateType() != 5 || cameraCaptureResult == null || !(cameraCaptureResult.getCaptureResult() instanceof TotalCaptureResult)) {
            Logger.d(TAG, "createCaptureRequest");
            if (captureConfig.getTemplateType() == 5) {
                builder = cameraDevice.createCaptureRequest(z ? 1 : 2);
            } else {
                builder = cameraDevice.createCaptureRequest(captureConfig.getTemplateType());
            }
        } else {
            Logger.d(TAG, "createReprocessCaptureRequest");
            builder = Api23Impl.createReprocessCaptureRequest(cameraDevice, (TotalCaptureResult) cameraCaptureResult.getCaptureResult());
        }
        applyTemplateParamsOverrideWorkaround(builder, captureConfig.getTemplateType(), templateParamsOverride);
        applyAeFpsRange(captureConfig, builder);
        applyVideoStabilization(captureConfig, builder);
        if (captureConfig.getImplementationOptions().containsOption(CaptureConfig.OPTION_ROTATION)) {
            builder.set(CaptureRequest.JPEG_ORIENTATION, (Integer) captureConfig.getImplementationOptions().retrieveOption(CaptureConfig.OPTION_ROTATION));
        }
        if (captureConfig.getImplementationOptions().containsOption(CaptureConfig.OPTION_JPEG_QUALITY)) {
            builder.set(CaptureRequest.JPEG_QUALITY, Byte.valueOf(((Integer) captureConfig.getImplementationOptions().retrieveOption(CaptureConfig.OPTION_JPEG_QUALITY)).byteValue()));
        }
        applyImplementationOptionToCaptureBuilder(builder, captureConfig.getImplementationOptions());
        for (Surface addTarget : configuredSurfaces) {
            builder.addTarget(addTarget);
        }
        builder.setTag(captureConfig.getTagBundle());
        return builder.build();
    }

    public static CaptureRequest buildWithoutTarget(CaptureConfig captureConfig, CameraDevice cameraDevice, TemplateParamsOverride templateParamsOverride) throws CameraAccessException {
        if (cameraDevice == null) {
            return null;
        }
        Logger.d(TAG, "template type = " + captureConfig.getTemplateType());
        CaptureRequest.Builder createCaptureRequest = cameraDevice.createCaptureRequest(captureConfig.getTemplateType());
        applyTemplateParamsOverrideWorkaround(createCaptureRequest, captureConfig.getTemplateType(), templateParamsOverride);
        applyAeFpsRange(captureConfig, createCaptureRequest);
        applyImplementationOptionToCaptureBuilder(createCaptureRequest, captureConfig.getImplementationOptions());
        return createCaptureRequest.build();
    }

    static class Api23Impl {
        private Api23Impl() {
        }

        static CaptureRequest.Builder createReprocessCaptureRequest(CameraDevice cameraDevice, TotalCaptureResult totalCaptureResult) throws CameraAccessException {
            return cameraDevice.createReprocessCaptureRequest(totalCaptureResult);
        }
    }
}
