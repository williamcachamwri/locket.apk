package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.camera2.internal.compat.quirk.CaptureSessionStuckQuirk;
import androidx.camera.camera2.internal.compat.quirk.IncorrectCaptureStateQuirk;
import androidx.camera.camera2.internal.compat.workaround.ForceCloseCaptureSession;
import androidx.camera.camera2.internal.compat.workaround.ForceCloseDeferrableSurface;
import androidx.camera.camera2.internal.compat.workaround.RequestMonitor;
import androidx.camera.camera2.internal.compat.workaround.SessionResetPolicy;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

class SynchronizedCaptureSessionImpl extends SynchronizedCaptureSessionBaseImpl {
    private static final String TAG = "SyncCaptureSessionImpl";
    private final ForceCloseDeferrableSurface mCloseSurfaceQuirk;
    private final AtomicBoolean mClosed;
    private List<DeferrableSurface> mDeferrableSurfaces;
    private final ForceCloseCaptureSession mForceCloseSessionQuirk;
    private final Object mObjectLock = new Object();
    ListenableFuture<List<Void>> mOpenSessionBlockerFuture;
    private final RequestMonitor mRequestMonitor;
    private final ScheduledExecutorService mScheduledExecutorService;
    private final SessionResetPolicy mSessionResetPolicy;

    SynchronizedCaptureSessionImpl(Quirks quirks, Quirks quirks2, CaptureSessionRepository captureSessionRepository, Executor executor, ScheduledExecutorService scheduledExecutorService, Handler handler) {
        super(captureSessionRepository, executor, scheduledExecutorService, handler);
        boolean z = false;
        this.mClosed = new AtomicBoolean(false);
        this.mCloseSurfaceQuirk = new ForceCloseDeferrableSurface(quirks, quirks2);
        this.mRequestMonitor = new RequestMonitor((quirks.contains(CaptureSessionStuckQuirk.class) || quirks.contains(IncorrectCaptureStateQuirk.class)) ? true : z);
        this.mForceCloseSessionQuirk = new ForceCloseCaptureSession(quirks2);
        this.mSessionResetPolicy = new SessionResetPolicy(quirks2);
        this.mScheduledExecutorService = scheduledExecutorService;
    }

    public ListenableFuture<Void> openCaptureSession(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat, List<DeferrableSurface> list) {
        ListenableFuture<Void> nonCancellationPropagating;
        synchronized (this.mObjectLock) {
            List<SynchronizedCaptureSession> captureSessions = this.mCaptureSessionRepository.getCaptureSessions();
            ArrayList arrayList = new ArrayList();
            for (SynchronizedCaptureSession openingBlocker : captureSessions) {
                arrayList.add(openingBlocker.getOpeningBlocker());
            }
            ListenableFuture<List<Void>> successfulAsList = Futures.successfulAsList(arrayList);
            this.mOpenSessionBlockerFuture = successfulAsList;
            nonCancellationPropagating = Futures.nonCancellationPropagating(FutureChain.from(successfulAsList).transformAsync(new SynchronizedCaptureSessionImpl$$ExternalSyntheticLambda1(this, cameraDevice, sessionConfigurationCompat, list), getExecutor()));
        }
        return nonCancellationPropagating;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$openCaptureSession$0$androidx-camera-camera2-internal-SynchronizedCaptureSessionImpl  reason: not valid java name */
    public /* synthetic */ ListenableFuture m93lambda$openCaptureSession$0$androidxcameracamera2internalSynchronizedCaptureSessionImpl(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat, List list, List list2) throws Exception {
        if (this.mSessionResetPolicy.needAbortCapture()) {
            closeCreatedSession();
        }
        debugLog("start openCaptureSession");
        return super.openCaptureSession(cameraDevice, sessionConfigurationCompat, list);
    }

    private void closeCreatedSession() {
        for (SynchronizedCaptureSession close : this.mCaptureSessionRepository.getCaptureSessions()) {
            close.close();
        }
    }

    public ListenableFuture<Void> getOpeningBlocker() {
        return Futures.makeTimeoutFuture(1500, this.mScheduledExecutorService, this.mRequestMonitor.getRequestsProcessedFuture());
    }

    public ListenableFuture<List<Surface>> startWithDeferrableSurface(List<DeferrableSurface> list, long j) {
        ListenableFuture<List<Surface>> startWithDeferrableSurface;
        synchronized (this.mObjectLock) {
            this.mDeferrableSurfaces = list;
            startWithDeferrableSurface = super.startWithDeferrableSurface(list, j);
        }
        return startWithDeferrableSurface;
    }

    public boolean stop() {
        boolean stop;
        synchronized (this.mObjectLock) {
            if (isCameraCaptureSessionOpen()) {
                this.mCloseSurfaceQuirk.onSessionEnd(this.mDeferrableSurfaces);
            } else {
                ListenableFuture<List<Void>> listenableFuture = this.mOpenSessionBlockerFuture;
                if (listenableFuture != null) {
                    listenableFuture.cancel(true);
                }
            }
            stop = super.stop();
        }
        return stop;
    }

    public int setSingleRepeatingRequest(CaptureRequest captureRequest, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return super.setSingleRepeatingRequest(captureRequest, this.mRequestMonitor.createMonitorListener(captureCallback));
    }

    public int captureBurstRequests(List<CaptureRequest> list, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return super.captureBurstRequests(list, this.mRequestMonitor.createMonitorListener(captureCallback));
    }

    public void onConfigured(SynchronizedCaptureSession synchronizedCaptureSession) {
        debugLog("Session onConfigured()");
        this.mForceCloseSessionQuirk.onSessionConfigured(synchronizedCaptureSession, this.mCaptureSessionRepository.getCreatingCaptureSessions(), this.mCaptureSessionRepository.getCaptureSessions(), new SynchronizedCaptureSessionImpl$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onConfigured$1$androidx-camera-camera2-internal-SynchronizedCaptureSessionImpl  reason: not valid java name */
    public /* synthetic */ void m92lambda$onConfigured$1$androidxcameracamera2internalSynchronizedCaptureSessionImpl(SynchronizedCaptureSession synchronizedCaptureSession) {
        super.onConfigured(synchronizedCaptureSession);
    }

    public void close() {
        if (!this.mClosed.compareAndSet(false, true)) {
            debugLog("close() has been called. Skip this invocation.");
            return;
        }
        if (this.mSessionResetPolicy.needAbortCapture()) {
            try {
                debugLog("Call abortCaptures() before closing session.");
                abortCaptures();
            } catch (Exception e) {
                debugLog("Exception when calling abortCaptures()" + e);
            }
        }
        debugLog("Session call close()");
        this.mRequestMonitor.getRequestsProcessedFuture().addListener(new SynchronizedCaptureSessionImpl$$ExternalSyntheticLambda0(this), getExecutor());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$close$2$androidx-camera-camera2-internal-SynchronizedCaptureSessionImpl  reason: not valid java name */
    public /* synthetic */ void m91lambda$close$2$androidxcameracamera2internalSynchronizedCaptureSessionImpl() {
        debugLog("Session call super.close()");
        super.close();
    }

    public void onClosed(SynchronizedCaptureSession synchronizedCaptureSession) {
        synchronized (this.mObjectLock) {
            this.mCloseSurfaceQuirk.onSessionEnd(this.mDeferrableSurfaces);
        }
        debugLog("onClosed()");
        super.onClosed(synchronizedCaptureSession);
    }

    public void finishClose() {
        super.finishClose();
        this.mRequestMonitor.stop();
    }

    public void onCameraDeviceError(int i) {
        super.onCameraDeviceError(i);
        if (i == 5) {
            synchronized (this.mObjectLock) {
                if (isCameraCaptureSessionOpen() && this.mDeferrableSurfaces != null) {
                    debugLog("Close DeferrableSurfaces for CameraDevice error.");
                    for (DeferrableSurface close : this.mDeferrableSurfaces) {
                        close.close();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void debugLog(String str) {
        Logger.d(TAG, "[" + this + "] " + str);
    }
}
