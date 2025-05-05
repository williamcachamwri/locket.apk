package androidx.camera.core.impl;

import android.graphics.Rect;
import androidx.camera.core.CameraControl;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.imagecapture.CameraCapturePipeline;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;

public interface CameraControlInternal extends CameraControl {
    public static final CameraControlInternal DEFAULT_EMPTY_INSTANCE = new CameraControlInternal() {
        public void addInteropConfig(Config config) {
        }

        public void addZslConfig(SessionConfig.Builder builder) {
        }

        public void clearInteropConfig() {
        }

        public int getFlashMode() {
            return 2;
        }

        public Config getInteropConfig() {
            return null;
        }

        public boolean isZslDisabledByByUserCaseConfig() {
            return false;
        }

        public void setFlashMode(int i) {
        }

        public void setZslDisabledByUserCaseConfig(boolean z) {
        }

        public ListenableFuture<Void> enableTorch(boolean z) {
            return Futures.immediateFuture(null);
        }

        public ListenableFuture<Integer> setExposureCompensationIndex(int i) {
            return Futures.immediateFuture(0);
        }

        public ListenableFuture<List<Void>> submitStillCaptureRequests(List<CaptureConfig> list, int i, int i2) {
            return Futures.immediateFuture(Collections.emptyList());
        }

        public SessionConfig getSessionConfig() {
            return SessionConfig.defaultEmptySessionConfig();
        }

        public Rect getSensorRect() {
            return new Rect();
        }

        public ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction focusMeteringAction) {
            return Futures.immediateFuture(FocusMeteringResult.emptyInstance());
        }

        public ListenableFuture<Void> cancelFocusAndMetering() {
            return Futures.immediateFuture(null);
        }

        public ListenableFuture<Void> setZoomRatio(float f) {
            return Futures.immediateFuture(null);
        }

        public ListenableFuture<Void> setLinearZoom(float f) {
            return Futures.immediateFuture(null);
        }
    };

    public interface ControlUpdateCallback {
        void onCameraControlCaptureRequests(List<CaptureConfig> list);

        void onCameraControlUpdateSessionConfig();
    }

    void addInteropConfig(Config config);

    void addZslConfig(SessionConfig.Builder builder);

    void clearInteropConfig();

    void decrementVideoUsage() {
    }

    int getFlashMode();

    CameraControlInternal getImplementation() {
        return this;
    }

    Config getInteropConfig();

    Rect getSensorRect();

    SessionConfig getSessionConfig();

    void incrementVideoUsage() {
    }

    boolean isInVideoUsage() {
        return false;
    }

    boolean isZslDisabledByByUserCaseConfig();

    void setFlashMode(int i);

    void setScreenFlash(ImageCapture.ScreenFlash screenFlash) {
    }

    void setZslDisabledByUserCaseConfig(boolean z);

    ListenableFuture<List<Void>> submitStillCaptureRequests(List<CaptureConfig> list, int i, int i2);

    ListenableFuture<CameraCapturePipeline> getCameraCapturePipelineAsync(int i, int i2) {
        return Futures.immediateFuture(new CameraCapturePipeline() {
            public ListenableFuture<Void> invokePreCapture() {
                return Futures.immediateFuture(null);
            }

            public ListenableFuture<Void> invokePostCapture() {
                return Futures.immediateFuture(null);
            }
        });
    }

    public static final class CameraControlException extends Exception {
        private CameraCaptureFailure mCameraCaptureFailure;

        public CameraControlException(CameraCaptureFailure cameraCaptureFailure) {
            this.mCameraCaptureFailure = cameraCaptureFailure;
        }

        public CameraControlException(CameraCaptureFailure cameraCaptureFailure, Throwable th) {
            super(th);
            this.mCameraCaptureFailure = cameraCaptureFailure;
        }

        public CameraCaptureFailure getCameraCaptureFailure() {
            return this.mCameraCaptureFailure;
        }
    }
}
