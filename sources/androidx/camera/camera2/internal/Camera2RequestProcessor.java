package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.view.Surface;
import androidx.camera.camera2.internal.CaptureSession;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.RequestProcessor;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.SessionProcessorSurface;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Camera2RequestProcessor implements RequestProcessor {
    private static final String TAG = "Camera2RequestProcessor";
    private CaptureSession mCaptureSession;
    private volatile boolean mIsClosed;
    private final Object mLock = new Object();
    private List<SessionProcessorSurface> mProcessorSurfaces;
    private volatile SessionConfig mSessionConfig;

    public Camera2RequestProcessor(CaptureSession captureSession, List<SessionProcessorSurface> list) {
        boolean z = false;
        this.mIsClosed = false;
        Preconditions.checkArgument(captureSession.mState == CaptureSession.State.OPENED ? true : z, "CaptureSession state must be OPENED. Current state:" + captureSession.mState);
        this.mCaptureSession = captureSession;
        this.mProcessorSurfaces = Collections.unmodifiableList(new ArrayList(list));
    }

    public void close() {
        synchronized (this.mLock) {
            this.mIsClosed = true;
            this.mCaptureSession = null;
            this.mSessionConfig = null;
            this.mProcessorSurfaces = null;
        }
    }

    public void updateSessionConfig(SessionConfig sessionConfig) {
        synchronized (this.mLock) {
            this.mSessionConfig = sessionConfig;
        }
    }

    private boolean areRequestsValid(List<RequestProcessor.Request> list) {
        for (RequestProcessor.Request isRequestValid : list) {
            if (!isRequestValid(isRequestValid)) {
                return false;
            }
        }
        return true;
    }

    private boolean isRequestValid(RequestProcessor.Request request) {
        if (request.getTargetOutputConfigIds().isEmpty()) {
            Logger.e(TAG, "Unable to submit the RequestProcessor.Request: empty targetOutputConfigIds");
            return false;
        }
        for (Integer next : request.getTargetOutputConfigIds()) {
            if (findSurface(next.intValue()) == null) {
                Logger.e(TAG, "Unable to submit the RequestProcessor.Request: targetOutputConfigId(" + next + ") is not a valid id");
                return false;
            }
        }
        return true;
    }

    public int submit(RequestProcessor.Request request, RequestProcessor.Callback callback) {
        return submit((List<RequestProcessor.Request>) Arrays.asList(new RequestProcessor.Request[]{request}), callback);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0079, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int submit(java.util.List<androidx.camera.core.impl.RequestProcessor.Request> r7, androidx.camera.core.impl.RequestProcessor.Callback r8) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            boolean r1 = r6.mIsClosed     // Catch:{ all -> 0x007b }
            if (r1 != 0) goto L_0x0078
            boolean r1 = r6.areRequestsValid(r7)     // Catch:{ all -> 0x007b }
            if (r1 == 0) goto L_0x0078
            androidx.camera.camera2.internal.CaptureSession r1 = r6.mCaptureSession     // Catch:{ all -> 0x007b }
            if (r1 != 0) goto L_0x0012
            goto L_0x0078
        L_0x0012:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x007b }
            r1.<init>()     // Catch:{ all -> 0x007b }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x007b }
            r2 = 1
        L_0x001c:
            boolean r3 = r7.hasNext()     // Catch:{ all -> 0x007b }
            if (r3 == 0) goto L_0x0070
            java.lang.Object r3 = r7.next()     // Catch:{ all -> 0x007b }
            androidx.camera.core.impl.RequestProcessor$Request r3 = (androidx.camera.core.impl.RequestProcessor.Request) r3     // Catch:{ all -> 0x007b }
            androidx.camera.core.impl.CaptureConfig$Builder r4 = new androidx.camera.core.impl.CaptureConfig$Builder     // Catch:{ all -> 0x007b }
            r4.<init>()     // Catch:{ all -> 0x007b }
            int r5 = r3.getTemplateId()     // Catch:{ all -> 0x007b }
            r4.setTemplateType(r5)     // Catch:{ all -> 0x007b }
            androidx.camera.core.impl.Config r5 = r3.getParameters()     // Catch:{ all -> 0x007b }
            r4.setImplementationOptions(r5)     // Catch:{ all -> 0x007b }
            androidx.camera.camera2.internal.Camera2RequestProcessor$Camera2CallbackWrapper r5 = new androidx.camera.camera2.internal.Camera2RequestProcessor$Camera2CallbackWrapper     // Catch:{ all -> 0x007b }
            r5.<init>(r3, r8, r2)     // Catch:{ all -> 0x007b }
            androidx.camera.camera2.internal.CaptureCallbackContainer r2 = androidx.camera.camera2.internal.CaptureCallbackContainer.create(r5)     // Catch:{ all -> 0x007b }
            r4.addCameraCaptureCallback(r2)     // Catch:{ all -> 0x007b }
            java.util.List r2 = r3.getTargetOutputConfigIds()     // Catch:{ all -> 0x007b }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x007b }
        L_0x004f:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x007b }
            if (r3 == 0) goto L_0x0067
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x007b }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ all -> 0x007b }
            int r3 = r3.intValue()     // Catch:{ all -> 0x007b }
            androidx.camera.core.impl.DeferrableSurface r3 = r6.findSurface(r3)     // Catch:{ all -> 0x007b }
            r4.addSurface(r3)     // Catch:{ all -> 0x007b }
            goto L_0x004f
        L_0x0067:
            androidx.camera.core.impl.CaptureConfig r2 = r4.build()     // Catch:{ all -> 0x007b }
            r1.add(r2)     // Catch:{ all -> 0x007b }
            r2 = 0
            goto L_0x001c
        L_0x0070:
            androidx.camera.camera2.internal.CaptureSession r7 = r6.mCaptureSession     // Catch:{ all -> 0x007b }
            int r7 = r7.issueBurstCaptureRequest(r1)     // Catch:{ all -> 0x007b }
            monitor-exit(r0)     // Catch:{ all -> 0x007b }
            return r7
        L_0x0078:
            monitor-exit(r0)     // Catch:{ all -> 0x007b }
            r7 = -1
            return r7
        L_0x007b:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x007b }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.Camera2RequestProcessor.submit(java.util.List, androidx.camera.core.impl.RequestProcessor$Callback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a4, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int setRepeating(androidx.camera.core.impl.RequestProcessor.Request r6, androidx.camera.core.impl.RequestProcessor.Callback r7) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            boolean r1 = r5.mIsClosed     // Catch:{ all -> 0x00a6 }
            if (r1 != 0) goto L_0x00a3
            boolean r1 = r5.isRequestValid(r6)     // Catch:{ all -> 0x00a6 }
            if (r1 == 0) goto L_0x00a3
            androidx.camera.camera2.internal.CaptureSession r1 = r5.mCaptureSession     // Catch:{ all -> 0x00a6 }
            if (r1 != 0) goto L_0x0013
            goto L_0x00a3
        L_0x0013:
            androidx.camera.core.impl.SessionConfig$Builder r1 = new androidx.camera.core.impl.SessionConfig$Builder     // Catch:{ all -> 0x00a6 }
            r1.<init>()     // Catch:{ all -> 0x00a6 }
            int r2 = r6.getTemplateId()     // Catch:{ all -> 0x00a6 }
            r1.setTemplateType(r2)     // Catch:{ all -> 0x00a6 }
            androidx.camera.core.impl.Config r2 = r6.getParameters()     // Catch:{ all -> 0x00a6 }
            r1.setImplementationOptions(r2)     // Catch:{ all -> 0x00a6 }
            androidx.camera.camera2.internal.Camera2RequestProcessor$Camera2CallbackWrapper r2 = new androidx.camera.camera2.internal.Camera2RequestProcessor$Camera2CallbackWrapper     // Catch:{ all -> 0x00a6 }
            r3 = 1
            r2.<init>(r6, r7, r3)     // Catch:{ all -> 0x00a6 }
            androidx.camera.camera2.internal.CaptureCallbackContainer r7 = androidx.camera.camera2.internal.CaptureCallbackContainer.create(r2)     // Catch:{ all -> 0x00a6 }
            r1.addCameraCaptureCallback(r7)     // Catch:{ all -> 0x00a6 }
            androidx.camera.core.impl.SessionConfig r7 = r5.mSessionConfig     // Catch:{ all -> 0x00a6 }
            if (r7 == 0) goto L_0x0077
            androidx.camera.core.impl.SessionConfig r7 = r5.mSessionConfig     // Catch:{ all -> 0x00a6 }
            java.util.List r7 = r7.getRepeatingCameraCaptureCallbacks()     // Catch:{ all -> 0x00a6 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x00a6 }
        L_0x0041:
            boolean r2 = r7.hasNext()     // Catch:{ all -> 0x00a6 }
            if (r2 == 0) goto L_0x0051
            java.lang.Object r2 = r7.next()     // Catch:{ all -> 0x00a6 }
            androidx.camera.core.impl.CameraCaptureCallback r2 = (androidx.camera.core.impl.CameraCaptureCallback) r2     // Catch:{ all -> 0x00a6 }
            r1.addCameraCaptureCallback(r2)     // Catch:{ all -> 0x00a6 }
            goto L_0x0041
        L_0x0051:
            androidx.camera.core.impl.SessionConfig r7 = r5.mSessionConfig     // Catch:{ all -> 0x00a6 }
            androidx.camera.core.impl.CaptureConfig r7 = r7.getRepeatingCaptureConfig()     // Catch:{ all -> 0x00a6 }
            androidx.camera.core.impl.TagBundle r7 = r7.getTagBundle()     // Catch:{ all -> 0x00a6 }
            java.util.Set r2 = r7.listKeys()     // Catch:{ all -> 0x00a6 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00a6 }
        L_0x0063:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00a6 }
            if (r3 == 0) goto L_0x0077
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x00a6 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x00a6 }
            java.lang.Object r4 = r7.getTag(r3)     // Catch:{ all -> 0x00a6 }
            r1.addTag(r3, r4)     // Catch:{ all -> 0x00a6 }
            goto L_0x0063
        L_0x0077:
            java.util.List r6 = r6.getTargetOutputConfigIds()     // Catch:{ all -> 0x00a6 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x00a6 }
        L_0x007f:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x00a6 }
            if (r7 == 0) goto L_0x0097
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x00a6 }
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ all -> 0x00a6 }
            int r7 = r7.intValue()     // Catch:{ all -> 0x00a6 }
            androidx.camera.core.impl.DeferrableSurface r7 = r5.findSurface(r7)     // Catch:{ all -> 0x00a6 }
            r1.addSurface(r7)     // Catch:{ all -> 0x00a6 }
            goto L_0x007f
        L_0x0097:
            androidx.camera.camera2.internal.CaptureSession r6 = r5.mCaptureSession     // Catch:{ all -> 0x00a6 }
            androidx.camera.core.impl.SessionConfig r7 = r1.build()     // Catch:{ all -> 0x00a6 }
            int r6 = r6.issueRepeatingCaptureRequests(r7)     // Catch:{ all -> 0x00a6 }
            monitor-exit(r0)     // Catch:{ all -> 0x00a6 }
            return r6
        L_0x00a3:
            monitor-exit(r0)     // Catch:{ all -> 0x00a6 }
            r6 = -1
            return r6
        L_0x00a6:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00a6 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.Camera2RequestProcessor.setRepeating(androidx.camera.core.impl.RequestProcessor$Request, androidx.camera.core.impl.RequestProcessor$Callback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0012, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void abortCaptures() {
        /*
            r2 = this;
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            boolean r1 = r2.mIsClosed     // Catch:{ all -> 0x0013 }
            if (r1 != 0) goto L_0x0011
            androidx.camera.camera2.internal.CaptureSession r1 = r2.mCaptureSession     // Catch:{ all -> 0x0013 }
            if (r1 != 0) goto L_0x000c
            goto L_0x0011
        L_0x000c:
            r1.abortCaptures()     // Catch:{ all -> 0x0013 }
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            return
        L_0x0011:
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            return
        L_0x0013:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.Camera2RequestProcessor.abortCaptures():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0012, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stopRepeating() {
        /*
            r2 = this;
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            boolean r1 = r2.mIsClosed     // Catch:{ all -> 0x0013 }
            if (r1 != 0) goto L_0x0011
            androidx.camera.camera2.internal.CaptureSession r1 = r2.mCaptureSession     // Catch:{ all -> 0x0013 }
            if (r1 != 0) goto L_0x000c
            goto L_0x0011
        L_0x000c:
            r1.stopRepeating()     // Catch:{ all -> 0x0013 }
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            return
        L_0x0011:
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            return
        L_0x0013:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.Camera2RequestProcessor.stopRepeating():void");
    }

    private class Camera2CallbackWrapper extends CameraCaptureSession.CaptureCallback {
        private final RequestProcessor.Callback mCallback;
        private final boolean mInvokeSequenceCallback;
        private final RequestProcessor.Request mRequest;

        Camera2CallbackWrapper(RequestProcessor.Request request, RequestProcessor.Callback callback, boolean z) {
            this.mCallback = callback;
            this.mRequest = request;
            this.mInvokeSequenceCallback = z;
        }

        public void onCaptureStarted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j, long j2) {
            this.mCallback.onCaptureStarted(this.mRequest, j2, j);
        }

        public void onCaptureProgressed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
            this.mCallback.onCaptureProgressed(this.mRequest, new Camera2CameraCaptureResult(captureResult));
        }

        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            this.mCallback.onCaptureCompleted(this.mRequest, new Camera2CameraCaptureResult(totalCaptureResult));
        }

        public void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            this.mCallback.onCaptureFailed(this.mRequest, new Camera2CameraCaptureFailure(CameraCaptureFailure.Reason.ERROR, captureFailure));
        }

        public void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i, long j) {
            if (this.mInvokeSequenceCallback) {
                this.mCallback.onCaptureSequenceCompleted(i, j);
            }
        }

        public void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
            if (this.mInvokeSequenceCallback) {
                this.mCallback.onCaptureSequenceAborted(i);
            }
        }

        public void onCaptureBufferLost(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, Surface surface, long j) {
            this.mCallback.onCaptureBufferLost(this.mRequest, j, Camera2RequestProcessor.this.findOutputConfigId(surface));
        }
    }

    /* access modifiers changed from: package-private */
    public int findOutputConfigId(Surface surface) {
        synchronized (this.mLock) {
            List<SessionProcessorSurface> list = this.mProcessorSurfaces;
            if (list == null) {
                return -1;
            }
            for (SessionProcessorSurface next : list) {
                try {
                    if (next.getSurface().get() == surface) {
                        int outputConfigId = next.getOutputConfigId();
                        return outputConfigId;
                    }
                } catch (InterruptedException | ExecutionException unused) {
                }
            }
            return -1;
        }
    }

    private DeferrableSurface findSurface(int i) {
        synchronized (this.mLock) {
            List<SessionProcessorSurface> list = this.mProcessorSurfaces;
            if (list == null) {
                return null;
            }
            for (SessionProcessorSurface next : list) {
                if (next.getOutputConfigId() == i) {
                    return next;
                }
            }
            return null;
        }
    }
}
