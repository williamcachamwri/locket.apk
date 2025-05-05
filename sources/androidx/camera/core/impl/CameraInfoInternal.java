package androidx.camera.core.impl;

import android.util.Size;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.DynamicRange;
import androidx.core.util.Preconditions;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public interface CameraInfoInternal extends CameraInfo {
    void addSessionCaptureCallback(Executor executor, CameraCaptureCallback cameraCaptureCallback);

    Object getCameraCharacteristics();

    String getCameraId();

    Quirks getCameraQuirks();

    EncoderProfilesProvider getEncoderProfilesProvider();

    CameraInfoInternal getImplementation() {
        return this;
    }

    Object getPhysicalCameraCharacteristics(String str);

    Set<DynamicRange> getSupportedDynamicRanges();

    List<Size> getSupportedHighResolutions(int i);

    Set<Integer> getSupportedOutputFormats();

    List<Size> getSupportedResolutions(int i);

    Timebase getTimebase();

    boolean isCaptureProcessProgressSupported() {
        return false;
    }

    boolean isPostviewSupported() {
        return false;
    }

    boolean isPreviewStabilizationSupported();

    boolean isVideoStabilizationSupported();

    void removeSessionCaptureCallback(CameraCaptureCallback cameraCaptureCallback);

    CameraSelector getCameraSelector() {
        return new CameraSelector.Builder().addCameraFilter(new CameraInfoInternal$$ExternalSyntheticLambda0(this)).addCameraFilter(new LensFacingCameraFilter(getLensFacing())).build();
    }

    static /* synthetic */ List lambda$getCameraSelector$0(CameraInfoInternal _this, List list) {
        String cameraId = _this.getCameraId();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CameraInfo cameraInfo = (CameraInfo) it.next();
            Preconditions.checkArgument(cameraInfo instanceof CameraInfoInternal);
            if (((CameraInfoInternal) cameraInfo).getCameraId().equals(cameraId)) {
                return Collections.singletonList(cameraInfo);
            }
        }
        throw new IllegalStateException("Unable to find camera with id " + cameraId + " from list of available cameras.");
    }
}
