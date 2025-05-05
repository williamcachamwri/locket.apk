package androidx.camera.extensions.internal.sessionprocessor;

import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.extensions.impl.PreviewImageProcessorImpl;
import androidx.camera.extensions.impl.ProcessResultImpl;
import androidx.camera.extensions.internal.ClientVersion;
import androidx.camera.extensions.internal.ExtensionVersion;
import androidx.camera.extensions.internal.Version;
import java.util.List;

class PreviewProcessor {
    private static final String TAG = "PreviewProcessor";
    private final CaptureResultImageMatcher mCaptureResultImageMatcher = new CaptureResultImageMatcher();
    private boolean mIsClosed = false;
    private boolean mIsPaused = false;
    private final Object mLock = new Object();
    private final PreviewImageProcessorImpl mPreviewImageProcessor;

    interface OnCaptureResultCallback {
        void onCaptureResult(long j, List<Pair<CaptureResult.Key, Object>> list);
    }

    PreviewProcessor(PreviewImageProcessorImpl previewImageProcessorImpl, Surface surface, Size size) {
        this.mPreviewImageProcessor = previewImageProcessorImpl;
        previewImageProcessorImpl.onResolutionUpdate(size);
        previewImageProcessorImpl.onOutputSurface(surface, 1);
        previewImageProcessorImpl.onImageFormatUpdate(35);
    }

    /* access modifiers changed from: package-private */
    public void pause() {
        synchronized (this.mLock) {
            this.mIsPaused = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void resume() {
        synchronized (this.mLock) {
            this.mIsPaused = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void start(OnCaptureResultCallback onCaptureResultCallback) {
        this.mCaptureResultImageMatcher.setImageReferenceListener(new PreviewProcessor$$ExternalSyntheticLambda0(this, onCaptureResultCallback));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$start$0$androidx-camera-extensions-internal-sessionprocessor-PreviewProcessor  reason: not valid java name */
    public /* synthetic */ void m231lambda$start$0$androidxcameraextensionsinternalsessionprocessorPreviewProcessor(final OnCaptureResultCallback onCaptureResultCallback, ImageReference imageReference, TotalCaptureResult totalCaptureResult, int i) {
        synchronized (this.mLock) {
            if (this.mIsClosed || this.mIsPaused) {
                imageReference.decrement();
                Logger.d(TAG, "Ignore image in closed or paused state");
                return;
            }
            try {
                if (!ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_3) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_3)) {
                    this.mPreviewImageProcessor.process(imageReference.get(), totalCaptureResult);
                } else {
                    this.mPreviewImageProcessor.process(imageReference.get(), totalCaptureResult, new ProcessResultImpl() {
                        public void onCaptureProcessProgressed(int i) {
                        }

                        public void onCaptureCompleted(long j, List<Pair<CaptureResult.Key, Object>> list) {
                            onCaptureResultCallback.onCaptureResult(j, list);
                        }
                    }, CameraXExecutors.ioExecutor());
                }
            } finally {
                imageReference.decrement();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyCaptureResult(TotalCaptureResult totalCaptureResult) {
        this.mCaptureResultImageMatcher.captureResultIncoming(totalCaptureResult);
    }

    /* access modifiers changed from: package-private */
    public void notifyImage(ImageReference imageReference) {
        this.mCaptureResultImageMatcher.imageIncoming(imageReference);
    }

    /* access modifiers changed from: package-private */
    public void close() {
        synchronized (this.mLock) {
            this.mIsClosed = true;
            this.mCaptureResultImageMatcher.clear();
            this.mCaptureResultImageMatcher.clearImageReferenceListener();
        }
    }
}
