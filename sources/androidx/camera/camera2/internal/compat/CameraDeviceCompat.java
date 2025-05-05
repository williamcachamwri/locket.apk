package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraDevice;
import android.os.Build;
import android.os.Handler;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.core.impl.utils.MainThreadAsyncHandler;
import java.util.concurrent.Executor;

public final class CameraDeviceCompat {
    public static final int SESSION_OPERATION_MODE_CONSTRAINED_HIGH_SPEED = 1;
    public static final int SESSION_OPERATION_MODE_NORMAL = 0;
    private final CameraDeviceCompatImpl mImpl;

    interface CameraDeviceCompatImpl {
        void createCaptureSession(SessionConfigurationCompat sessionConfigurationCompat) throws CameraAccessExceptionCompat;

        CameraDevice unwrap();
    }

    private CameraDeviceCompat(CameraDevice cameraDevice, Handler handler) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mImpl = new CameraDeviceCompatApi28Impl(cameraDevice);
        } else {
            this.mImpl = CameraDeviceCompatApi24Impl.create(cameraDevice, handler);
        }
    }

    public static CameraDeviceCompat toCameraDeviceCompat(CameraDevice cameraDevice) {
        return toCameraDeviceCompat(cameraDevice, MainThreadAsyncHandler.getInstance());
    }

    public static CameraDeviceCompat toCameraDeviceCompat(CameraDevice cameraDevice, Handler handler) {
        return new CameraDeviceCompat(cameraDevice, handler);
    }

    public CameraDevice toCameraDevice() {
        return this.mImpl.unwrap();
    }

    public void createCaptureSession(SessionConfigurationCompat sessionConfigurationCompat) throws CameraAccessExceptionCompat {
        this.mImpl.createCaptureSession(sessionConfigurationCompat);
    }

    static final class StateCallbackExecutorWrapper extends CameraDevice.StateCallback {
        private final Executor mExecutor;
        final CameraDevice.StateCallback mWrappedCallback;

        StateCallbackExecutorWrapper(Executor executor, CameraDevice.StateCallback stateCallback) {
            this.mExecutor = executor;
            this.mWrappedCallback = stateCallback;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onOpened$0$androidx-camera-camera2-internal-compat-CameraDeviceCompat$StateCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m119lambda$onOpened$0$androidxcameracamera2internalcompatCameraDeviceCompat$StateCallbackExecutorWrapper(CameraDevice cameraDevice) {
            this.mWrappedCallback.onOpened(cameraDevice);
        }

        public void onOpened(CameraDevice cameraDevice) {
            this.mExecutor.execute(new CameraDeviceCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda2(this, cameraDevice));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onDisconnected$1$androidx-camera-camera2-internal-compat-CameraDeviceCompat$StateCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m117lambda$onDisconnected$1$androidxcameracamera2internalcompatCameraDeviceCompat$StateCallbackExecutorWrapper(CameraDevice cameraDevice) {
            this.mWrappedCallback.onDisconnected(cameraDevice);
        }

        public void onDisconnected(CameraDevice cameraDevice) {
            this.mExecutor.execute(new CameraDeviceCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda1(this, cameraDevice));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onError$2$androidx-camera-camera2-internal-compat-CameraDeviceCompat$StateCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m118lambda$onError$2$androidxcameracamera2internalcompatCameraDeviceCompat$StateCallbackExecutorWrapper(CameraDevice cameraDevice, int i) {
            this.mWrappedCallback.onError(cameraDevice, i);
        }

        public void onError(CameraDevice cameraDevice, int i) {
            this.mExecutor.execute(new CameraDeviceCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda3(this, cameraDevice, i));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onClosed$3$androidx-camera-camera2-internal-compat-CameraDeviceCompat$StateCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m116lambda$onClosed$3$androidxcameracamera2internalcompatCameraDeviceCompat$StateCallbackExecutorWrapper(CameraDevice cameraDevice) {
            this.mWrappedCallback.onClosed(cameraDevice);
        }

        public void onClosed(CameraDevice cameraDevice) {
            this.mExecutor.execute(new CameraDeviceCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda0(this, cameraDevice));
        }
    }
}
