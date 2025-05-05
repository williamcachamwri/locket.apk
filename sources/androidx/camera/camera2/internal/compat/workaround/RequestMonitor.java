package androidx.camera.camera2.internal.compat.workaround;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Log;
import androidx.camera.camera2.internal.Camera2CaptureCallbacks;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RequestMonitor {
    private static final String TAG = "RequestMonitor";
    private final boolean mQuirkEnabled;
    private final List<ListenableFuture<Void>> mRequestTasks = Collections.synchronizedList(new ArrayList());

    static /* synthetic */ Void lambda$getRequestsProcessedFuture$0(List list) {
        return null;
    }

    public RequestMonitor(boolean z) {
        this.mQuirkEnabled = z;
    }

    public boolean shouldMonitorRequest() {
        return this.mQuirkEnabled;
    }

    public ListenableFuture<Void> getRequestsProcessedFuture() {
        if (this.mRequestTasks.isEmpty()) {
            return Futures.immediateFuture(null);
        }
        return Futures.nonCancellationPropagating(Futures.transform(Futures.successfulAsList(new ArrayList(this.mRequestTasks)), new RequestMonitor$$ExternalSyntheticLambda1(), CameraXExecutors.directExecutor()));
    }

    public CameraCaptureSession.CaptureCallback createMonitorListener(CameraCaptureSession.CaptureCallback captureCallback) {
        if (!shouldMonitorRequest()) {
            return captureCallback;
        }
        return Camera2CaptureCallbacks.createComboCallback(createMonitorListener(), captureCallback);
    }

    private CameraCaptureSession.CaptureCallback createMonitorListener() {
        RequestCompleteListener requestCompleteListener = new RequestCompleteListener();
        ListenableFuture<Void> listenableFuture = requestCompleteListener.mStartRequestFuture;
        this.mRequestTasks.add(listenableFuture);
        Log.d(TAG, "RequestListener " + requestCompleteListener + " monitoring " + this);
        listenableFuture.addListener(new RequestMonitor$$ExternalSyntheticLambda0(this, requestCompleteListener, listenableFuture), CameraXExecutors.directExecutor());
        return requestCompleteListener;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createMonitorListener$1$androidx-camera-camera2-internal-compat-workaround-RequestMonitor  reason: not valid java name */
    public /* synthetic */ void m123lambda$createMonitorListener$1$androidxcameracamera2internalcompatworkaroundRequestMonitor(RequestCompleteListener requestCompleteListener, ListenableFuture listenableFuture) {
        Log.d(TAG, "RequestListener " + requestCompleteListener + " done " + this);
        this.mRequestTasks.remove(listenableFuture);
    }

    public void stop() {
        LinkedList linkedList = new LinkedList(this.mRequestTasks);
        while (!linkedList.isEmpty()) {
            ((ListenableFuture) Objects.requireNonNull((ListenableFuture) linkedList.poll())).cancel(true);
        }
    }

    static class RequestCompleteListener extends CameraCaptureSession.CaptureCallback {
        CallbackToFutureAdapter.Completer<Void> mStartRequestCompleter;
        final ListenableFuture<Void> mStartRequestFuture = CallbackToFutureAdapter.getFuture(new RequestMonitor$RequestCompleteListener$$ExternalSyntheticLambda0(this));

        RequestCompleteListener() {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$androidx-camera-camera2-internal-compat-workaround-RequestMonitor$RequestCompleteListener  reason: not valid java name */
        public /* synthetic */ Object m124lambda$new$0$androidxcameracamera2internalcompatworkaroundRequestMonitor$RequestCompleteListener(CallbackToFutureAdapter.Completer completer) throws Exception {
            this.mStartRequestCompleter = completer;
            return "RequestCompleteListener[" + this + "]";
        }

        public void onCaptureStarted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j, long j2) {
            completeFuture();
        }

        public void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
            completeFuture();
        }

        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            completeFuture();
        }

        public void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            completeFuture();
        }

        public void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i, long j) {
            completeFuture();
        }

        private void completeFuture() {
            CallbackToFutureAdapter.Completer<Void> completer = this.mStartRequestCompleter;
            if (completer != null) {
                completer.set(null);
                this.mStartRequestCompleter = null;
            }
        }
    }
}
