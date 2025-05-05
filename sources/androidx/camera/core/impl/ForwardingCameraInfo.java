package androidx.camera.core.impl;

import android.util.Range;
import android.util.Size;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraState;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.ExposureState;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.ZoomState;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public class ForwardingCameraInfo implements CameraInfoInternal {
    private final CameraInfoInternal mCameraInfoInternal;

    public ForwardingCameraInfo(CameraInfoInternal cameraInfoInternal) {
        this.mCameraInfoInternal = cameraInfoInternal;
    }

    public int getSensorRotationDegrees() {
        return this.mCameraInfoInternal.getSensorRotationDegrees();
    }

    public int getSensorRotationDegrees(int i) {
        return this.mCameraInfoInternal.getSensorRotationDegrees(i);
    }

    public boolean hasFlashUnit() {
        return this.mCameraInfoInternal.hasFlashUnit();
    }

    public LiveData<Integer> getTorchState() {
        return this.mCameraInfoInternal.getTorchState();
    }

    public LiveData<ZoomState> getZoomState() {
        return this.mCameraInfoInternal.getZoomState();
    }

    public ExposureState getExposureState() {
        return this.mCameraInfoInternal.getExposureState();
    }

    public LiveData<CameraState> getCameraState() {
        return this.mCameraInfoInternal.getCameraState();
    }

    public String getImplementationType() {
        return this.mCameraInfoInternal.getImplementationType();
    }

    public int getLensFacing() {
        return this.mCameraInfoInternal.getLensFacing();
    }

    public float getIntrinsicZoomRatio() {
        return this.mCameraInfoInternal.getIntrinsicZoomRatio();
    }

    public boolean isFocusMeteringSupported(FocusMeteringAction focusMeteringAction) {
        return this.mCameraInfoInternal.isFocusMeteringSupported(focusMeteringAction);
    }

    public boolean isZslSupported() {
        return this.mCameraInfoInternal.isZslSupported();
    }

    public Set<Range<Integer>> getSupportedFrameRateRanges() {
        return this.mCameraInfoInternal.getSupportedFrameRateRanges();
    }

    public boolean isPrivateReprocessingSupported() {
        return this.mCameraInfoInternal.isPrivateReprocessingSupported();
    }

    public boolean isLogicalMultiCameraSupported() {
        return this.mCameraInfoInternal.isLogicalMultiCameraSupported();
    }

    public String getCameraId() {
        return this.mCameraInfoInternal.getCameraId();
    }

    public void addSessionCaptureCallback(Executor executor, CameraCaptureCallback cameraCaptureCallback) {
        this.mCameraInfoInternal.addSessionCaptureCallback(executor, cameraCaptureCallback);
    }

    public void removeSessionCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
        this.mCameraInfoInternal.removeSessionCaptureCallback(cameraCaptureCallback);
    }

    public Quirks getCameraQuirks() {
        return this.mCameraInfoInternal.getCameraQuirks();
    }

    public EncoderProfilesProvider getEncoderProfilesProvider() {
        return this.mCameraInfoInternal.getEncoderProfilesProvider();
    }

    public Timebase getTimebase() {
        return this.mCameraInfoInternal.getTimebase();
    }

    public Set<Integer> getSupportedOutputFormats() {
        return this.mCameraInfoInternal.getSupportedOutputFormats();
    }

    public List<Size> getSupportedResolutions(int i) {
        return this.mCameraInfoInternal.getSupportedResolutions(i);
    }

    public List<Size> getSupportedHighResolutions(int i) {
        return this.mCameraInfoInternal.getSupportedHighResolutions(i);
    }

    public Set<DynamicRange> getSupportedDynamicRanges() {
        return this.mCameraInfoInternal.getSupportedDynamicRanges();
    }

    public Set<DynamicRange> querySupportedDynamicRanges(Set<DynamicRange> set) {
        return this.mCameraInfoInternal.querySupportedDynamicRanges(set);
    }

    public CameraInfoInternal getImplementation() {
        return this.mCameraInfoInternal.getImplementation();
    }

    public CameraSelector getCameraSelector() {
        return this.mCameraInfoInternal.getCameraSelector();
    }

    public boolean isPreviewStabilizationSupported() {
        return this.mCameraInfoInternal.isPreviewStabilizationSupported();
    }

    public boolean isVideoStabilizationSupported() {
        return this.mCameraInfoInternal.isVideoStabilizationSupported();
    }

    public Object getCameraCharacteristics() {
        return this.mCameraInfoInternal.getCameraCharacteristics();
    }

    public Object getPhysicalCameraCharacteristics(String str) {
        return this.mCameraInfoInternal.getPhysicalCameraCharacteristics(str);
    }

    public Set<CameraInfo> getPhysicalCameraInfos() {
        return this.mCameraInfoInternal.getPhysicalCameraInfos();
    }
}
