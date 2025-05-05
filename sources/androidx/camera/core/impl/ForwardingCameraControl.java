package androidx.camera.core.impl;

import android.graphics.Rect;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.imagecapture.CameraCapturePipeline;
import androidx.camera.core.impl.SessionConfig;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

public class ForwardingCameraControl implements CameraControlInternal {
    private final CameraControlInternal mCameraControlInternal;

    public ForwardingCameraControl(CameraControlInternal cameraControlInternal) {
        this.mCameraControlInternal = cameraControlInternal;
    }

    public ListenableFuture<Void> enableTorch(boolean z) {
        return this.mCameraControlInternal.enableTorch(z);
    }

    public ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction focusMeteringAction) {
        return this.mCameraControlInternal.startFocusAndMetering(focusMeteringAction);
    }

    public ListenableFuture<Void> cancelFocusAndMetering() {
        return this.mCameraControlInternal.cancelFocusAndMetering();
    }

    public ListenableFuture<Void> setZoomRatio(float f) {
        return this.mCameraControlInternal.setZoomRatio(f);
    }

    public ListenableFuture<Void> setLinearZoom(float f) {
        return this.mCameraControlInternal.setLinearZoom(f);
    }

    public ListenableFuture<Integer> setExposureCompensationIndex(int i) {
        return this.mCameraControlInternal.setExposureCompensationIndex(i);
    }

    public int getFlashMode() {
        return this.mCameraControlInternal.getFlashMode();
    }

    public void setFlashMode(int i) {
        this.mCameraControlInternal.setFlashMode(i);
    }

    public void setScreenFlash(ImageCapture.ScreenFlash screenFlash) {
        this.mCameraControlInternal.setScreenFlash(screenFlash);
    }

    public void addZslConfig(SessionConfig.Builder builder) {
        this.mCameraControlInternal.addZslConfig(builder);
    }

    public void setZslDisabledByUserCaseConfig(boolean z) {
        this.mCameraControlInternal.setZslDisabledByUserCaseConfig(z);
    }

    public boolean isZslDisabledByByUserCaseConfig() {
        return this.mCameraControlInternal.isZslDisabledByByUserCaseConfig();
    }

    public ListenableFuture<List<Void>> submitStillCaptureRequests(List<CaptureConfig> list, int i, int i2) {
        return this.mCameraControlInternal.submitStillCaptureRequests(list, i, i2);
    }

    public ListenableFuture<CameraCapturePipeline> getCameraCapturePipelineAsync(int i, int i2) {
        return this.mCameraControlInternal.getCameraCapturePipelineAsync(i, i2);
    }

    public SessionConfig getSessionConfig() {
        return this.mCameraControlInternal.getSessionConfig();
    }

    public Rect getSensorRect() {
        return this.mCameraControlInternal.getSensorRect();
    }

    public void addInteropConfig(Config config) {
        this.mCameraControlInternal.addInteropConfig(config);
    }

    public void clearInteropConfig() {
        this.mCameraControlInternal.clearInteropConfig();
    }

    public Config getInteropConfig() {
        return this.mCameraControlInternal.getInteropConfig();
    }

    public CameraControlInternal getImplementation() {
        return this.mCameraControlInternal.getImplementation();
    }

    public void incrementVideoUsage() {
        this.mCameraControlInternal.incrementVideoUsage();
    }

    public void decrementVideoUsage() {
        this.mCameraControlInternal.decrementVideoUsage();
    }

    public boolean isInVideoUsage() {
        return this.mCameraControlInternal.isInVideoUsage();
    }
}
