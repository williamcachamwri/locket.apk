package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.view.Surface;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat;
import androidx.camera.camera2.internal.compat.CameraDeviceCompat;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.DeferrableSurfaces;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

class SynchronizedCaptureSessionBaseImpl extends SynchronizedCaptureSession.StateCallback implements SynchronizedCaptureSession, SynchronizedCaptureSession.Opener {
    private static final String TAG = "SyncCaptureSessionBase";
    CameraCaptureSessionCompat mCameraCaptureSessionCompat;
    final CaptureSessionRepository mCaptureSessionRepository;
    SynchronizedCaptureSession.StateCallback mCaptureSessionStateCallback;
    private boolean mClosed = false;
    final Handler mCompatHandler;
    final Executor mExecutor;
    private List<DeferrableSurface> mHeldDeferrableSurfaces = null;
    final Object mLock = new Object();
    CallbackToFutureAdapter.Completer<Void> mOpenCaptureSessionCompleter;
    ListenableFuture<Void> mOpenCaptureSessionFuture;
    private boolean mOpenerDisabled = false;
    private final ScheduledExecutorService mScheduledExecutorService;
    private boolean mSessionFinished = false;
    private ListenableFuture<List<Surface>> mStartingSurface;

    public SynchronizedCaptureSession.StateCallback getStateCallback() {
        return this;
    }

    public void onCameraDeviceError(int i) {
    }

    SynchronizedCaptureSessionBaseImpl(CaptureSessionRepository captureSessionRepository, Executor executor, ScheduledExecutorService scheduledExecutorService, Handler handler) {
        this.mCaptureSessionRepository = captureSessionRepository;
        this.mCompatHandler = handler;
        this.mExecutor = executor;
        this.mScheduledExecutorService = scheduledExecutorService;
    }

    public ListenableFuture<Void> getOpeningBlocker() {
        return Futures.immediateFuture(null);
    }

    public ListenableFuture<Void> openCaptureSession(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat, List<DeferrableSurface> list) {
        synchronized (this.mLock) {
            if (this.mOpenerDisabled) {
                ListenableFuture<Void> immediateFailedFuture = Futures.immediateFailedFuture(new CancellationException("Opener is disabled"));
                return immediateFailedFuture;
            }
            this.mCaptureSessionRepository.onCreateCaptureSession(this);
            ListenableFuture<Void> future = CallbackToFutureAdapter.getFuture(new SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda2(this, list, CameraDeviceCompat.toCameraDeviceCompat(cameraDevice, this.mCompatHandler), sessionConfigurationCompat));
            this.mOpenCaptureSessionFuture = future;
            Futures.addCallback(future, new FutureCallback<Void>() {
                public void onSuccess(Void voidR) {
                }

                public void onFailure(Throwable th) {
                    SynchronizedCaptureSessionBaseImpl.this.finishClose();
                    SynchronizedCaptureSessionBaseImpl.this.mCaptureSessionRepository.onCaptureSessionConfigureFail(SynchronizedCaptureSessionBaseImpl.this);
                }
            }, CameraXExecutors.directExecutor());
            ListenableFuture<Void> nonCancellationPropagating = Futures.nonCancellationPropagating(this.mOpenCaptureSessionFuture);
            return nonCancellationPropagating;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$openCaptureSession$0$androidx-camera-camera2-internal-SynchronizedCaptureSessionBaseImpl  reason: not valid java name */
    public /* synthetic */ Object m89lambda$openCaptureSession$0$androidxcameracamera2internalSynchronizedCaptureSessionBaseImpl(List list, CameraDeviceCompat cameraDeviceCompat, SessionConfigurationCompat sessionConfigurationCompat, CallbackToFutureAdapter.Completer completer) throws Exception {
        String str;
        synchronized (this.mLock) {
            holdDeferrableSurfaces(list);
            Preconditions.checkState(this.mOpenCaptureSessionCompleter == null, "The openCaptureSessionCompleter can only set once!");
            this.mOpenCaptureSessionCompleter = completer;
            cameraDeviceCompat.createCaptureSession(sessionConfigurationCompat);
            str = "openCaptureSession[session=" + this + "]";
        }
        return str;
    }

    /* access modifiers changed from: package-private */
    public boolean isCameraCaptureSessionOpen() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mOpenCaptureSessionFuture != null;
        }
        return z;
    }

    public SessionConfigurationCompat createSessionConfigurationCompat(int i, List<OutputConfigurationCompat> list, SynchronizedCaptureSession.StateCallback stateCallback) {
        this.mCaptureSessionStateCallback = stateCallback;
        return new SessionConfigurationCompat(i, list, getExecutor(), new CameraCaptureSession.StateCallback() {
            public void onReady(CameraCaptureSession cameraCaptureSession) {
                SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(cameraCaptureSession);
                SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                synchronizedCaptureSessionBaseImpl.onReady(synchronizedCaptureSessionBaseImpl);
            }

            public void onActive(CameraCaptureSession cameraCaptureSession) {
                SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(cameraCaptureSession);
                SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                synchronizedCaptureSessionBaseImpl.onActive(synchronizedCaptureSessionBaseImpl);
            }

            public void onCaptureQueueEmpty(CameraCaptureSession cameraCaptureSession) {
                SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(cameraCaptureSession);
                SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                synchronizedCaptureSessionBaseImpl.onCaptureQueueEmpty(synchronizedCaptureSessionBaseImpl);
            }

            public void onSurfacePrepared(CameraCaptureSession cameraCaptureSession, Surface surface) {
                SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(cameraCaptureSession);
                SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                synchronizedCaptureSessionBaseImpl.onSurfacePrepared(synchronizedCaptureSessionBaseImpl, surface);
            }

            public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                CallbackToFutureAdapter.Completer<Void> completer;
                try {
                    SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(cameraCaptureSession);
                    SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                    synchronizedCaptureSessionBaseImpl.onConfigured(synchronizedCaptureSessionBaseImpl);
                    synchronized (SynchronizedCaptureSessionBaseImpl.this.mLock) {
                        Preconditions.checkNotNull(SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter, "OpenCaptureSession completer should not null");
                        completer = SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter;
                        SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter = null;
                    }
                    completer.set(null);
                } catch (Throwable th) {
                    synchronized (SynchronizedCaptureSessionBaseImpl.this.mLock) {
                        Preconditions.checkNotNull(SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter, "OpenCaptureSession completer should not null");
                        CallbackToFutureAdapter.Completer<Void> completer2 = SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter;
                        SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter = null;
                        completer2.set(null);
                        throw th;
                    }
                }
            }

            public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                CallbackToFutureAdapter.Completer<Void> completer;
                try {
                    SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(cameraCaptureSession);
                    SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                    synchronizedCaptureSessionBaseImpl.onConfigureFailed(synchronizedCaptureSessionBaseImpl);
                    synchronized (SynchronizedCaptureSessionBaseImpl.this.mLock) {
                        Preconditions.checkNotNull(SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter, "OpenCaptureSession completer should not null");
                        completer = SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter;
                        SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter = null;
                    }
                    completer.setException(new IllegalStateException("onConfigureFailed"));
                } catch (Throwable th) {
                    synchronized (SynchronizedCaptureSessionBaseImpl.this.mLock) {
                        Preconditions.checkNotNull(SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter, "OpenCaptureSession completer should not null");
                        CallbackToFutureAdapter.Completer<Void> completer2 = SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter;
                        SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter = null;
                        completer2.setException(new IllegalStateException("onConfigureFailed"));
                        throw th;
                    }
                }
            }

            public void onClosed(CameraCaptureSession cameraCaptureSession) {
                SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(cameraCaptureSession);
                SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                synchronizedCaptureSessionBaseImpl.onClosed(synchronizedCaptureSessionBaseImpl);
            }
        });
    }

    public Executor getExecutor() {
        return this.mExecutor;
    }

    /* access modifiers changed from: package-private */
    public void createCaptureSessionCompat(CameraCaptureSession cameraCaptureSession) {
        if (this.mCameraCaptureSessionCompat == null) {
            this.mCameraCaptureSessionCompat = CameraCaptureSessionCompat.toCameraCaptureSessionCompat(cameraCaptureSession, this.mCompatHandler);
        }
    }

    public ListenableFuture<List<Surface>> startWithDeferrableSurface(List<DeferrableSurface> list, long j) {
        synchronized (this.mLock) {
            if (this.mOpenerDisabled) {
                ListenableFuture<List<Surface>> immediateFailedFuture = Futures.immediateFailedFuture(new CancellationException("Opener is disabled"));
                return immediateFailedFuture;
            }
            FutureChain<T> transformAsync = FutureChain.from(DeferrableSurfaces.surfaceListWithTimeout(list, false, j, getExecutor(), this.mScheduledExecutorService)).transformAsync(new SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda1(this, list), getExecutor());
            this.mStartingSurface = transformAsync;
            ListenableFuture<List<Surface>> nonCancellationPropagating = Futures.nonCancellationPropagating(transformAsync);
            return nonCancellationPropagating;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startWithDeferrableSurface$1$androidx-camera-camera2-internal-SynchronizedCaptureSessionBaseImpl  reason: not valid java name */
    public /* synthetic */ ListenableFuture m90lambda$startWithDeferrableSurface$1$androidxcameracamera2internalSynchronizedCaptureSessionBaseImpl(List list, List list2) throws Exception {
        Logger.d(TAG, "[" + this + "] getSurface done with results: " + list2);
        if (list2.isEmpty()) {
            return Futures.immediateFailedFuture(new IllegalArgumentException("Unable to open capture session without surfaces"));
        }
        if (list2.contains((Object) null)) {
            return Futures.immediateFailedFuture(new DeferrableSurface.SurfaceClosedException("Surface closed", (DeferrableSurface) list.get(list2.indexOf((Object) null))));
        }
        return Futures.immediateFuture(list2);
    }

    public boolean stop() {
        boolean z;
        ListenableFuture<List<Surface>> listenableFuture = null;
        try {
            synchronized (this.mLock) {
                if (!this.mOpenerDisabled) {
                    ListenableFuture<List<Surface>> listenableFuture2 = this.mStartingSurface;
                    if (listenableFuture2 != null) {
                        listenableFuture = listenableFuture2;
                    }
                    this.mOpenerDisabled = true;
                }
                z = !isCameraCaptureSessionOpen();
            }
            if (listenableFuture != null) {
                listenableFuture.cancel(true);
            }
            return z;
        } catch (Throwable th) {
            if (listenableFuture != null) {
                listenableFuture.cancel(true);
            }
            throw th;
        }
    }

    public CameraCaptureSessionCompat toCameraCaptureSessionCompat() {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat);
        return this.mCameraCaptureSessionCompat;
    }

    public CameraDevice getDevice() {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat);
        return this.mCameraCaptureSessionCompat.toCameraCaptureSession().getDevice();
    }

    public Surface getInputSurface() {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat);
        return Api23Impl.getInputSurface(this.mCameraCaptureSessionCompat.toCameraCaptureSession());
    }

    public int captureSingleRequest(CaptureRequest captureRequest, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.captureSingleRequest(captureRequest, getExecutor(), captureCallback);
    }

    public int captureBurstRequests(List<CaptureRequest> list, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.captureBurstRequests(list, getExecutor(), captureCallback);
    }

    public int setSingleRepeatingRequest(CaptureRequest captureRequest, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.setSingleRepeatingRequest(captureRequest, getExecutor(), captureCallback);
    }

    public int setRepeatingBurstRequests(List<CaptureRequest> list, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.setRepeatingBurstRequests(list, getExecutor(), captureCallback);
    }

    public int captureSingleRequest(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.captureSingleRequest(captureRequest, executor, captureCallback);
    }

    public int captureBurstRequests(List<CaptureRequest> list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.captureBurstRequests(list, executor, captureCallback);
    }

    public int setSingleRepeatingRequest(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.setSingleRepeatingRequest(captureRequest, executor, captureCallback);
    }

    public int setRepeatingBurstRequests(List<CaptureRequest> list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.setRepeatingBurstRequests(list, executor, captureCallback);
    }

    public void stopRepeating() throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        this.mCameraCaptureSessionCompat.toCameraCaptureSession().stopRepeating();
    }

    public void abortCaptures() throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        this.mCameraCaptureSessionCompat.toCameraCaptureSession().abortCaptures();
    }

    public void close() {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        this.mCaptureSessionRepository.onCaptureSessionClosing(this);
        this.mCameraCaptureSessionCompat.toCameraCaptureSession().close();
        getExecutor().execute(new SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$close$2$androidx-camera-camera2-internal-SynchronizedCaptureSessionBaseImpl  reason: not valid java name */
    public /* synthetic */ void m86lambda$close$2$androidxcameracamera2internalSynchronizedCaptureSessionBaseImpl() {
        onSessionFinished(this);
    }

    public void onReady(SynchronizedCaptureSession synchronizedCaptureSession) {
        Objects.requireNonNull(this.mCaptureSessionStateCallback);
        this.mCaptureSessionStateCallback.onReady(synchronizedCaptureSession);
    }

    public void onActive(SynchronizedCaptureSession synchronizedCaptureSession) {
        Objects.requireNonNull(this.mCaptureSessionStateCallback);
        this.mCaptureSessionStateCallback.onActive(synchronizedCaptureSession);
    }

    public void onCaptureQueueEmpty(SynchronizedCaptureSession synchronizedCaptureSession) {
        Objects.requireNonNull(this.mCaptureSessionStateCallback);
        this.mCaptureSessionStateCallback.onCaptureQueueEmpty(synchronizedCaptureSession);
    }

    public void onSurfacePrepared(SynchronizedCaptureSession synchronizedCaptureSession, Surface surface) {
        Objects.requireNonNull(this.mCaptureSessionStateCallback);
        this.mCaptureSessionStateCallback.onSurfacePrepared(synchronizedCaptureSession, surface);
    }

    public void onConfigured(SynchronizedCaptureSession synchronizedCaptureSession) {
        Objects.requireNonNull(this.mCaptureSessionStateCallback);
        this.mCaptureSessionRepository.onCaptureSessionCreated(this);
        this.mCaptureSessionStateCallback.onConfigured(synchronizedCaptureSession);
    }

    public void onConfigureFailed(SynchronizedCaptureSession synchronizedCaptureSession) {
        Objects.requireNonNull(this.mCaptureSessionStateCallback);
        finishClose();
        this.mCaptureSessionRepository.onCaptureSessionConfigureFail(this);
        this.mCaptureSessionStateCallback.onConfigureFailed(synchronizedCaptureSession);
    }

    public void onClosed(SynchronizedCaptureSession synchronizedCaptureSession) {
        ListenableFuture<Void> listenableFuture;
        synchronized (this.mLock) {
            if (!this.mClosed) {
                this.mClosed = true;
                Preconditions.checkNotNull(this.mOpenCaptureSessionFuture, "Need to call openCaptureSession before using this API.");
                listenableFuture = this.mOpenCaptureSessionFuture;
            } else {
                listenableFuture = null;
            }
        }
        finishClose();
        if (listenableFuture != null) {
            listenableFuture.addListener(new SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda3(this, synchronizedCaptureSession), CameraXExecutors.directExecutor());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onClosed$3$androidx-camera-camera2-internal-SynchronizedCaptureSessionBaseImpl  reason: not valid java name */
    public /* synthetic */ void m87lambda$onClosed$3$androidxcameracamera2internalSynchronizedCaptureSessionBaseImpl(SynchronizedCaptureSession synchronizedCaptureSession) {
        this.mCaptureSessionRepository.onCaptureSessionClosed(this);
        onSessionFinished(synchronizedCaptureSession);
        if (this.mCameraCaptureSessionCompat != null) {
            Objects.requireNonNull(this.mCaptureSessionStateCallback);
            this.mCaptureSessionStateCallback.onClosed(synchronizedCaptureSession);
            return;
        }
        Logger.w(TAG, "[" + this + "] Cannot call onClosed() when the CameraCaptureSession is not correctly configured.");
    }

    /* access modifiers changed from: package-private */
    public void onSessionFinished(SynchronizedCaptureSession synchronizedCaptureSession) {
        ListenableFuture<Void> listenableFuture;
        synchronized (this.mLock) {
            if (!this.mSessionFinished) {
                this.mSessionFinished = true;
                Preconditions.checkNotNull(this.mOpenCaptureSessionFuture, "Need to call openCaptureSession before using this API.");
                listenableFuture = this.mOpenCaptureSessionFuture;
            } else {
                listenableFuture = null;
            }
        }
        if (listenableFuture != null) {
            listenableFuture.addListener(new SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda0(this, synchronizedCaptureSession), CameraXExecutors.directExecutor());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSessionFinished$4$androidx-camera-camera2-internal-SynchronizedCaptureSessionBaseImpl  reason: not valid java name */
    public /* synthetic */ void m88lambda$onSessionFinished$4$androidxcameracamera2internalSynchronizedCaptureSessionBaseImpl(SynchronizedCaptureSession synchronizedCaptureSession) {
        Objects.requireNonNull(this.mCaptureSessionStateCallback);
        this.mCaptureSessionStateCallback.onSessionFinished(synchronizedCaptureSession);
    }

    /* access modifiers changed from: package-private */
    public void holdDeferrableSurfaces(List<DeferrableSurface> list) throws DeferrableSurface.SurfaceClosedException {
        synchronized (this.mLock) {
            releaseDeferrableSurfaces();
            DeferrableSurfaces.incrementAll(list);
            this.mHeldDeferrableSurfaces = list;
        }
    }

    /* access modifiers changed from: package-private */
    public void releaseDeferrableSurfaces() {
        synchronized (this.mLock) {
            List<DeferrableSurface> list = this.mHeldDeferrableSurfaces;
            if (list != null) {
                DeferrableSurfaces.decrementAll(list);
                this.mHeldDeferrableSurfaces = null;
            }
        }
    }

    public void finishClose() {
        releaseDeferrableSurfaces();
    }

    private static class Api23Impl {
        private Api23Impl() {
        }

        static Surface getInputSurface(CameraCaptureSession cameraCaptureSession) {
            return cameraCaptureSession.getInputSurface();
        }
    }
}
