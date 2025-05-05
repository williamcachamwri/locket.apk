package androidx.camera.extensions.internal.sessionprocessor;

import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.OutputSurface;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.extensions.impl.CaptureProcessorImpl;
import androidx.camera.extensions.impl.ProcessResultImpl;
import androidx.camera.extensions.internal.ClientVersion;
import androidx.camera.extensions.internal.ExtensionVersion;
import androidx.camera.extensions.internal.Version;
import androidx.camera.extensions.internal.compat.workaround.CaptureOutputSurfaceForCaptureProcessor;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class StillCaptureProcessor {
    private static final String TAG = "StillCaptureProcessor";
    private static final long UNSPECIFIED_TIMESTAMP = -1;
    CaptureOutputSurfaceForCaptureProcessor mCaptureOutputSurface;
    final CaptureProcessorImpl mCaptureProcessorImpl;
    final CaptureResultImageMatcher mCaptureResultImageMatcher = new CaptureResultImageMatcher();
    HashMap<Integer, Pair<ImageReference, TotalCaptureResult>> mCaptureResults = new HashMap<>();
    boolean mIsClosed;
    private boolean mIsPostviewConfigured;
    final Object mLock = new Object();
    OnCaptureResultCallback mOnCaptureResultCallback = null;
    TotalCaptureResult mSourceCaptureResult = null;
    long mTimeStampForOutputImage;

    interface OnCaptureResultCallback {
        void onCaptureCompleted(long j, List<Pair<CaptureResult.Key, Object>> list);

        void onCaptureProcessProgressed(int i);

        void onError(Exception exc);

        void onProcessCompleted();
    }

    StillCaptureProcessor(CaptureProcessorImpl captureProcessorImpl, Surface surface, Size size, OutputSurface outputSurface, boolean z) {
        boolean z2 = false;
        this.mIsClosed = false;
        this.mTimeStampForOutputImage = -1;
        this.mCaptureProcessorImpl = captureProcessorImpl;
        CaptureOutputSurfaceForCaptureProcessor captureOutputSurfaceForCaptureProcessor = new CaptureOutputSurfaceForCaptureProcessor(surface, size, z);
        this.mCaptureOutputSurface = captureOutputSurfaceForCaptureProcessor;
        captureProcessorImpl.onOutputSurface(captureOutputSurfaceForCaptureProcessor.getSurface(), 35);
        captureProcessorImpl.onImageFormatUpdate(35);
        this.mIsPostviewConfigured = outputSurface != null;
        if (outputSurface == null || !ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4)) {
            captureProcessorImpl.onResolutionUpdate(size);
            return;
        }
        Preconditions.checkArgument(outputSurface.getImageFormat() == 35 ? true : z2);
        captureProcessorImpl.onResolutionUpdate(size, outputSurface.getSize());
        captureProcessorImpl.onPostviewOutputSurface(outputSurface.getSurface());
    }

    /* access modifiers changed from: package-private */
    public void clearCaptureResults() {
        synchronized (this.mLock) {
            for (Pair<ImageReference, TotalCaptureResult> pair : this.mCaptureResults.values()) {
                ((ImageReference) pair.first).decrement();
            }
            this.mCaptureResults.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void startCapture(boolean z, List<Integer> list, OnCaptureResultCallback onCaptureResultCallback) {
        Logger.d(TAG, "Start the capture: enablePostview=" + z);
        this.mTimeStampForOutputImage = -1;
        synchronized (this.mLock) {
            Preconditions.checkState(!this.mIsClosed, "StillCaptureProcessor is closed. Can't invoke startCapture()");
            this.mOnCaptureResultCallback = onCaptureResultCallback;
            clearCaptureResults();
        }
        this.mCaptureResultImageMatcher.clear();
        this.mCaptureResultImageMatcher.setImageReferenceListener(new StillCaptureProcessor$$ExternalSyntheticLambda0(this, list, onCaptureResultCallback, z));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0061, code lost:
        return;
     */
    /* renamed from: lambda$startCapture$0$androidx-camera-extensions-internal-sessionprocessor-StillCaptureProcessor  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void m233lambda$startCapture$0$androidxcameraextensionsinternalsessionprocessorStillCaptureProcessor(java.util.List r6, androidx.camera.extensions.internal.sessionprocessor.StillCaptureProcessor.OnCaptureResultCallback r7, boolean r8, androidx.camera.extensions.internal.sessionprocessor.ImageReference r9, android.hardware.camera2.TotalCaptureResult r10, int r11) {
        /*
            r5 = this;
            java.lang.String r0 = "mCaptureResult has capture stage Id: "
            java.lang.String r1 = "onImageReferenceIncoming  captureStageId="
            java.lang.Object r2 = r5.mLock
            monitor-enter(r2)
            boolean r3 = r5.mIsClosed     // Catch:{ all -> 0x0062 }
            if (r3 == 0) goto L_0x0017
            r9.decrement()     // Catch:{ all -> 0x0062 }
            java.lang.String r6 = "StillCaptureProcessor"
            java.lang.String r7 = "Ignore image in closed state"
            androidx.camera.core.Logger.d(r6, r7)     // Catch:{ all -> 0x0062 }
            monitor-exit(r2)     // Catch:{ all -> 0x0062 }
            return
        L_0x0017:
            java.lang.String r3 = "StillCaptureProcessor"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0062 }
            r4.<init>(r1)     // Catch:{ all -> 0x0062 }
            java.lang.StringBuilder r1 = r4.append(r11)     // Catch:{ all -> 0x0062 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0062 }
            androidx.camera.core.Logger.d(r3, r1)     // Catch:{ all -> 0x0062 }
            java.util.HashMap<java.lang.Integer, android.util.Pair<androidx.camera.extensions.internal.sessionprocessor.ImageReference, android.hardware.camera2.TotalCaptureResult>> r1 = r5.mCaptureResults     // Catch:{ all -> 0x0062 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x0062 }
            android.util.Pair r3 = new android.util.Pair     // Catch:{ all -> 0x0062 }
            r3.<init>(r9, r10)     // Catch:{ all -> 0x0062 }
            r1.put(r11, r3)     // Catch:{ all -> 0x0062 }
            java.lang.String r9 = "StillCaptureProcessor"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0062 }
            r10.<init>(r0)     // Catch:{ all -> 0x0062 }
            java.util.HashMap<java.lang.Integer, android.util.Pair<androidx.camera.extensions.internal.sessionprocessor.ImageReference, android.hardware.camera2.TotalCaptureResult>> r11 = r5.mCaptureResults     // Catch:{ all -> 0x0062 }
            java.util.Set r11 = r11.keySet()     // Catch:{ all -> 0x0062 }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0062 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0062 }
            androidx.camera.core.Logger.d(r9, r10)     // Catch:{ all -> 0x0062 }
            java.util.HashMap<java.lang.Integer, android.util.Pair<androidx.camera.extensions.internal.sessionprocessor.ImageReference, android.hardware.camera2.TotalCaptureResult>> r9 = r5.mCaptureResults     // Catch:{ all -> 0x0062 }
            java.util.Set r9 = r9.keySet()     // Catch:{ all -> 0x0062 }
            boolean r6 = r9.containsAll(r6)     // Catch:{ all -> 0x0062 }
            if (r6 == 0) goto L_0x0060
            java.util.HashMap<java.lang.Integer, android.util.Pair<androidx.camera.extensions.internal.sessionprocessor.ImageReference, android.hardware.camera2.TotalCaptureResult>> r6 = r5.mCaptureResults     // Catch:{ all -> 0x0062 }
            r5.process(r6, r7, r8)     // Catch:{ all -> 0x0062 }
        L_0x0060:
            monitor-exit(r2)     // Catch:{ all -> 0x0062 }
            return
        L_0x0062:
            r6 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0062 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.extensions.internal.sessionprocessor.StillCaptureProcessor.m233lambda$startCapture$0$androidxcameraextensionsinternalsessionprocessorStillCaptureProcessor(java.util.List, androidx.camera.extensions.internal.sessionprocessor.StillCaptureProcessor$OnCaptureResultCallback, boolean, androidx.camera.extensions.internal.sessionprocessor.ImageReference, android.hardware.camera2.TotalCaptureResult, int):void");
    }

    /* access modifiers changed from: package-private */
    public void process(Map<Integer, Pair<ImageReference, TotalCaptureResult>> map, OnCaptureResultCallback onCaptureResultCallback, boolean z) {
        HashMap hashMap = new HashMap();
        synchronized (this.mLock) {
            for (Integer next : map.keySet()) {
                Pair pair = map.get(next);
                hashMap.put(next, new Pair(((ImageReference) pair.first).get(), (TotalCaptureResult) pair.second));
            }
        }
        CameraXExecutors.ioExecutor().execute(new StillCaptureProcessor$$ExternalSyntheticLambda1(this, z, hashMap, onCaptureResultCallback));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$process$1$androidx-camera-extensions-internal-sessionprocessor-StillCaptureProcessor  reason: not valid java name */
    public /* synthetic */ void m232lambda$process$1$androidxcameraextensionsinternalsessionprocessorStillCaptureProcessor(boolean z, HashMap hashMap, final OnCaptureResultCallback onCaptureResultCallback) {
        synchronized (this.mLock) {
            try {
                if (this.mIsClosed) {
                    Logger.d(TAG, "Ignore process() in closed state.");
                    Logger.d(TAG, "CaptureProcessorImpl.process() finish");
                    OnCaptureResultCallback onCaptureResultCallback2 = this.mOnCaptureResultCallback;
                    if (onCaptureResultCallback2 != null) {
                        onCaptureResultCallback2.onProcessCompleted();
                        this.mOnCaptureResultCallback = null;
                    }
                    clearCaptureResults();
                    return;
                }
                Logger.d(TAG, "CaptureProcessorImpl.process() begin");
                if (ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) && ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) && z && this.mIsPostviewConfigured) {
                    this.mCaptureProcessorImpl.processWithPostview(hashMap, new ProcessResultImpl() {
                        public void onCaptureCompleted(long j, List<Pair<CaptureResult.Key, Object>> list) {
                            onCaptureResultCallback.onCaptureCompleted(j, list);
                        }

                        public void onCaptureProcessProgressed(int i) {
                            onCaptureResultCallback.onCaptureProcessProgressed(i);
                        }
                    }, CameraXExecutors.directExecutor());
                } else if (!ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_3) || !ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_3)) {
                    this.mCaptureProcessorImpl.process(hashMap);
                } else {
                    this.mCaptureProcessorImpl.process(hashMap, new ProcessResultImpl() {
                        public void onCaptureCompleted(long j, List<Pair<CaptureResult.Key, Object>> list) {
                            onCaptureResultCallback.onCaptureCompleted(j, list);
                        }

                        public void onCaptureProcessProgressed(int i) {
                            onCaptureResultCallback.onCaptureProcessProgressed(i);
                        }
                    }, CameraXExecutors.directExecutor());
                }
                Logger.d(TAG, "CaptureProcessorImpl.process() finish");
                OnCaptureResultCallback onCaptureResultCallback3 = this.mOnCaptureResultCallback;
                if (onCaptureResultCallback3 != null) {
                    onCaptureResultCallback3.onProcessCompleted();
                    this.mOnCaptureResultCallback = null;
                }
                clearCaptureResults();
            } catch (Exception e) {
                try {
                    Logger.e(TAG, "mCaptureProcessorImpl.process exception ", e);
                    this.mOnCaptureResultCallback = null;
                    if (onCaptureResultCallback != null) {
                        onCaptureResultCallback.onError(e);
                    }
                    Logger.d(TAG, "CaptureProcessorImpl.process() finish");
                    OnCaptureResultCallback onCaptureResultCallback4 = this.mOnCaptureResultCallback;
                    if (onCaptureResultCallback4 != null) {
                        onCaptureResultCallback4.onProcessCompleted();
                        this.mOnCaptureResultCallback = null;
                    }
                } catch (Throwable th) {
                    Logger.d(TAG, "CaptureProcessorImpl.process() finish");
                    OnCaptureResultCallback onCaptureResultCallback5 = this.mOnCaptureResultCallback;
                    if (onCaptureResultCallback5 != null) {
                        onCaptureResultCallback5.onProcessCompleted();
                        this.mOnCaptureResultCallback = null;
                    }
                    clearCaptureResults();
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyCaptureResult(TotalCaptureResult totalCaptureResult, int i) {
        Long l;
        this.mCaptureResultImageMatcher.captureResultIncoming(totalCaptureResult, i);
        if (this.mTimeStampForOutputImage == -1 && (l = (Long) totalCaptureResult.get(CaptureResult.SENSOR_TIMESTAMP)) != null) {
            long longValue = l.longValue();
            this.mTimeStampForOutputImage = longValue;
            this.mCaptureOutputSurface.setOutputImageTimestamp(longValue);
        }
        synchronized (this.mLock) {
            if (this.mSourceCaptureResult == null) {
                this.mSourceCaptureResult = totalCaptureResult;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyImage(ImageReference imageReference) {
        this.mCaptureResultImageMatcher.imageIncoming(imageReference);
    }

    /* access modifiers changed from: package-private */
    public void close() {
        synchronized (this.mLock) {
            Logger.d(TAG, "Close the StillCaptureProcessor");
            this.mIsClosed = true;
            clearCaptureResults();
            this.mCaptureResultImageMatcher.clearImageReferenceListener();
            this.mCaptureResultImageMatcher.clear();
            this.mCaptureOutputSurface.close();
        }
    }
}
