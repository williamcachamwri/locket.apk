package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import android.os.Handler;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.ApiCompat;
import androidx.camera.core.impl.utils.MainThreadAsyncHandler;
import java.util.List;
import java.util.concurrent.Executor;

public final class CameraCaptureSessionCompat {
    private final CameraCaptureSessionCompatImpl mImpl;

    interface CameraCaptureSessionCompatImpl {
        int captureBurstRequests(List<CaptureRequest> list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;

        int captureSingleRequest(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;

        int setRepeatingBurstRequests(List<CaptureRequest> list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;

        int setSingleRepeatingRequest(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;

        CameraCaptureSession unwrap();
    }

    private CameraCaptureSessionCompat(CameraCaptureSession cameraCaptureSession, Handler handler) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mImpl = new CameraCaptureSessionCompatApi28Impl(cameraCaptureSession);
        } else {
            this.mImpl = CameraCaptureSessionCompatBaseImpl.create(cameraCaptureSession, handler);
        }
    }

    public static CameraCaptureSessionCompat toCameraCaptureSessionCompat(CameraCaptureSession cameraCaptureSession) {
        return toCameraCaptureSessionCompat(cameraCaptureSession, MainThreadAsyncHandler.getInstance());
    }

    public static CameraCaptureSessionCompat toCameraCaptureSessionCompat(CameraCaptureSession cameraCaptureSession, Handler handler) {
        return new CameraCaptureSessionCompat(cameraCaptureSession, handler);
    }

    public CameraCaptureSession toCameraCaptureSession() {
        return this.mImpl.unwrap();
    }

    public int captureBurstRequests(List<CaptureRequest> list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.mImpl.captureBurstRequests(list, executor, captureCallback);
    }

    public int captureSingleRequest(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.mImpl.captureSingleRequest(captureRequest, executor, captureCallback);
    }

    public int setRepeatingBurstRequests(List<CaptureRequest> list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.mImpl.setRepeatingBurstRequests(list, executor, captureCallback);
    }

    public int setSingleRepeatingRequest(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.mImpl.setSingleRepeatingRequest(captureRequest, executor, captureCallback);
    }

    static final class CaptureCallbackExecutorWrapper extends CameraCaptureSession.CaptureCallback {
        private final Executor mExecutor;
        final CameraCaptureSession.CaptureCallback mWrappedCallback;

        CaptureCallbackExecutorWrapper(Executor executor, CameraCaptureSession.CaptureCallback captureCallback) {
            this.mExecutor = executor;
            this.mWrappedCallback = captureCallback;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCaptureStarted$0$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m108lambda$onCaptureStarted$0$androidxcameracamera2internalcompatCameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j, long j2) {
            this.mWrappedCallback.onCaptureStarted(cameraCaptureSession, captureRequest, j, j2);
        }

        public void onCaptureStarted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j, long j2) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$$ExternalSyntheticLambda5(this, cameraCaptureSession, captureRequest, j, j2));
        }

        public void onCaptureProgressed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$$ExternalSyntheticLambda3(this, cameraCaptureSession, captureRequest, captureResult));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCaptureProgressed$1$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m105lambda$onCaptureProgressed$1$androidxcameracamera2internalcompatCameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
            this.mWrappedCallback.onCaptureProgressed(cameraCaptureSession, captureRequest, captureResult);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCaptureCompleted$2$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m103lambda$onCaptureCompleted$2$androidxcameracamera2internalcompatCameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            this.mWrappedCallback.onCaptureCompleted(cameraCaptureSession, captureRequest, totalCaptureResult);
        }

        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$$ExternalSyntheticLambda4(this, cameraCaptureSession, captureRequest, totalCaptureResult));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCaptureFailed$3$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m104lambda$onCaptureFailed$3$androidxcameracamera2internalcompatCameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            this.mWrappedCallback.onCaptureFailed(cameraCaptureSession, captureRequest, captureFailure);
        }

        public void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$$ExternalSyntheticLambda6(this, cameraCaptureSession, captureRequest, captureFailure));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCaptureSequenceCompleted$4$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m107lambda$onCaptureSequenceCompleted$4$androidxcameracamera2internalcompatCameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, int i, long j) {
            this.mWrappedCallback.onCaptureSequenceCompleted(cameraCaptureSession, i, j);
        }

        public void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i, long j) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$$ExternalSyntheticLambda2(this, cameraCaptureSession, i, j));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCaptureSequenceAborted$5$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m106lambda$onCaptureSequenceAborted$5$androidxcameracamera2internalcompatCameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, int i) {
            this.mWrappedCallback.onCaptureSequenceAborted(cameraCaptureSession, i);
        }

        public void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$$ExternalSyntheticLambda0(this, cameraCaptureSession, i));
        }

        public void onCaptureBufferLost(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, Surface surface, long j) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$$ExternalSyntheticLambda1(this, cameraCaptureSession, captureRequest, surface, j));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCaptureBufferLost$6$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m102lambda$onCaptureBufferLost$6$androidxcameracamera2internalcompatCameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, Surface surface, long j) {
            ApiCompat.Api24Impl.onCaptureBufferLost(this.mWrappedCallback, cameraCaptureSession, captureRequest, surface, j);
        }
    }

    static final class StateCallbackExecutorWrapper extends CameraCaptureSession.StateCallback {
        private final Executor mExecutor;
        final CameraCaptureSession.StateCallback mWrappedCallback;

        StateCallbackExecutorWrapper(Executor executor, CameraCaptureSession.StateCallback stateCallback) {
            this.mExecutor = executor;
            this.mWrappedCallback = stateCallback;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onConfigured$0$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$StateCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m113lambda$onConfigured$0$androidxcameracamera2internalcompatCameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession) {
            this.mWrappedCallback.onConfigured(cameraCaptureSession);
        }

        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda0(this, cameraCaptureSession));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onConfigureFailed$1$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$StateCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m112lambda$onConfigureFailed$1$androidxcameracamera2internalcompatCameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession) {
            this.mWrappedCallback.onConfigureFailed(cameraCaptureSession);
        }

        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda4(this, cameraCaptureSession));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onReady$2$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$StateCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m114lambda$onReady$2$androidxcameracamera2internalcompatCameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession) {
            this.mWrappedCallback.onReady(cameraCaptureSession);
        }

        public void onReady(CameraCaptureSession cameraCaptureSession) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda5(this, cameraCaptureSession));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onActive$3$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$StateCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m109lambda$onActive$3$androidxcameracamera2internalcompatCameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession) {
            this.mWrappedCallback.onActive(cameraCaptureSession);
        }

        public void onActive(CameraCaptureSession cameraCaptureSession) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda3(this, cameraCaptureSession));
        }

        public void onCaptureQueueEmpty(CameraCaptureSession cameraCaptureSession) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda2(this, cameraCaptureSession));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCaptureQueueEmpty$4$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$StateCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m110lambda$onCaptureQueueEmpty$4$androidxcameracamera2internalcompatCameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession) {
            ApiCompat.Api26Impl.onCaptureQueueEmpty(this.mWrappedCallback, cameraCaptureSession);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onClosed$5$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$StateCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m111lambda$onClosed$5$androidxcameracamera2internalcompatCameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession) {
            this.mWrappedCallback.onClosed(cameraCaptureSession);
        }

        public void onClosed(CameraCaptureSession cameraCaptureSession) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda1(this, cameraCaptureSession));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onSurfacePrepared$6$androidx-camera-camera2-internal-compat-CameraCaptureSessionCompat$StateCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m115lambda$onSurfacePrepared$6$androidxcameracamera2internalcompatCameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, Surface surface) {
            ApiCompat.Api23Impl.onSurfacePrepared(this.mWrappedCallback, cameraCaptureSession, surface);
        }

        public void onSurfacePrepared(CameraCaptureSession cameraCaptureSession, Surface surface) {
            this.mExecutor.execute(new CameraCaptureSessionCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda6(this, cameraCaptureSession, surface));
        }
    }
}
