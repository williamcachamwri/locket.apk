package androidx.camera.view;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.view.PixelCopy;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.view.PreviewView;
import androidx.camera.view.PreviewViewImplementation;
import androidx.core.content.ContextCompat;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

final class SurfaceViewImplementation extends PreviewViewImplementation {
    private static final int SCREENSHOT_TIMEOUT_MILLIS = 100;
    private static final String TAG = "SurfaceViewImpl";
    final SurfaceRequestCallback mSurfaceRequestCallback = new SurfaceRequestCallback();
    SurfaceView mSurfaceView;

    /* access modifiers changed from: package-private */
    public void onAttachedToWindow() {
    }

    /* access modifiers changed from: package-private */
    public void onDetachedFromWindow() {
    }

    SurfaceViewImplementation(FrameLayout frameLayout, PreviewTransformation previewTransformation) {
        super(frameLayout, previewTransformation);
    }

    /* access modifiers changed from: package-private */
    public void onSurfaceRequested(SurfaceRequest surfaceRequest, PreviewViewImplementation.OnSurfaceNotInUseListener onSurfaceNotInUseListener) {
        if (!shouldReusePreview(this.mSurfaceView, this.mResolution, surfaceRequest)) {
            this.mResolution = surfaceRequest.getResolution();
            initializePreview();
        }
        if (onSurfaceNotInUseListener != null) {
            Executor mainExecutor = ContextCompat.getMainExecutor(this.mSurfaceView.getContext());
            Objects.requireNonNull(onSurfaceNotInUseListener);
            surfaceRequest.addRequestCancellationListener(mainExecutor, new SurfaceViewImplementation$$ExternalSyntheticLambda0(onSurfaceNotInUseListener));
        }
        this.mSurfaceView.post(new SurfaceViewImplementation$$ExternalSyntheticLambda1(this, surfaceRequest, onSurfaceNotInUseListener));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSurfaceRequested$0$androidx-camera-view-SurfaceViewImplementation  reason: not valid java name */
    public /* synthetic */ void m322lambda$onSurfaceRequested$0$androidxcameraviewSurfaceViewImplementation(SurfaceRequest surfaceRequest, PreviewViewImplementation.OnSurfaceNotInUseListener onSurfaceNotInUseListener) {
        this.mSurfaceRequestCallback.setSurfaceRequest(surfaceRequest, onSurfaceNotInUseListener);
    }

    /* access modifiers changed from: package-private */
    public void initializePreview() {
        Preconditions.checkNotNull(this.mParent);
        Preconditions.checkNotNull(this.mResolution);
        SurfaceView surfaceView = new SurfaceView(this.mParent.getContext());
        this.mSurfaceView = surfaceView;
        surfaceView.setLayoutParams(new FrameLayout.LayoutParams(this.mResolution.getWidth(), this.mResolution.getHeight()));
        this.mParent.removeAllViews();
        this.mParent.addView(this.mSurfaceView);
        this.mSurfaceView.getHolder().addCallback(this.mSurfaceRequestCallback);
    }

    /* access modifiers changed from: package-private */
    public View getPreview() {
        return this.mSurfaceView;
    }

    /* access modifiers changed from: package-private */
    public Bitmap getPreviewBitmap() {
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView == null || surfaceView.getHolder().getSurface() == null || !this.mSurfaceView.getHolder().getSurface().isValid()) {
            return null;
        }
        Semaphore semaphore = new Semaphore(0);
        Bitmap createBitmap = Bitmap.createBitmap(this.mSurfaceView.getWidth(), this.mSurfaceView.getHeight(), Bitmap.Config.ARGB_8888);
        HandlerThread handlerThread = new HandlerThread("pixelCopyRequest Thread");
        handlerThread.start();
        Api24Impl.pixelCopyRequest(this.mSurfaceView, createBitmap, new SurfaceViewImplementation$$ExternalSyntheticLambda2(semaphore), new Handler(handlerThread.getLooper()));
        try {
            if (!semaphore.tryAcquire(1, 100, TimeUnit.MILLISECONDS)) {
                Logger.e(TAG, "Timed out while trying to acquire screenshot.");
            }
        } catch (InterruptedException e) {
            Logger.e(TAG, "Interrupted while trying to acquire screenshot.", e);
        } catch (Throwable th) {
            handlerThread.quitSafely();
            throw th;
        }
        handlerThread.quitSafely();
        return createBitmap;
    }

    static /* synthetic */ void lambda$getPreviewBitmap$1(Semaphore semaphore, int i) {
        if (i == 0) {
            Logger.d(TAG, "PreviewView.SurfaceViewImplementation.getBitmap() succeeded");
        } else {
            Logger.e(TAG, "PreviewView.SurfaceViewImplementation.getBitmap() failed with error " + i);
        }
        semaphore.release();
    }

    class SurfaceRequestCallback implements SurfaceHolder.Callback {
        private Size mCurrentSurfaceSize;
        private boolean mNeedToInvalidate = false;
        private PreviewViewImplementation.OnSurfaceNotInUseListener mOnSurfaceNotInUseListener;
        private SurfaceRequest mSurfaceRequest;
        private SurfaceRequest mSurfaceRequestToBeInvalidated;
        private Size mTargetSize;
        private boolean mWasSurfaceProvided = false;

        SurfaceRequestCallback() {
        }

        /* access modifiers changed from: package-private */
        public void setSurfaceRequest(SurfaceRequest surfaceRequest, PreviewViewImplementation.OnSurfaceNotInUseListener onSurfaceNotInUseListener) {
            cancelPreviousRequest();
            if (this.mNeedToInvalidate) {
                this.mNeedToInvalidate = false;
                surfaceRequest.invalidate();
                return;
            }
            this.mSurfaceRequest = surfaceRequest;
            this.mOnSurfaceNotInUseListener = onSurfaceNotInUseListener;
            Size resolution = surfaceRequest.getResolution();
            this.mTargetSize = resolution;
            this.mWasSurfaceProvided = false;
            if (!tryToComplete()) {
                Logger.d(SurfaceViewImplementation.TAG, "Wait for new Surface creation.");
                SurfaceViewImplementation.this.mSurfaceView.getHolder().setFixedSize(resolution.getWidth(), resolution.getHeight());
            }
        }

        private boolean tryToComplete() {
            Surface surface = SurfaceViewImplementation.this.mSurfaceView.getHolder().getSurface();
            if (!canProvideSurface()) {
                return false;
            }
            Logger.d(SurfaceViewImplementation.TAG, "Surface set on Preview.");
            ((SurfaceRequest) Objects.requireNonNull(this.mSurfaceRequest)).provideSurface(surface, ContextCompat.getMainExecutor(SurfaceViewImplementation.this.mSurfaceView.getContext()), new SurfaceViewImplementation$SurfaceRequestCallback$$ExternalSyntheticLambda0(this.mOnSurfaceNotInUseListener));
            this.mWasSurfaceProvided = true;
            SurfaceViewImplementation.this.onSurfaceProvided();
            return true;
        }

        static /* synthetic */ void lambda$tryToComplete$0(PreviewViewImplementation.OnSurfaceNotInUseListener onSurfaceNotInUseListener, SurfaceRequest.Result result) {
            Logger.d(SurfaceViewImplementation.TAG, "Safe to release surface.");
            if (onSurfaceNotInUseListener != null) {
                onSurfaceNotInUseListener.onSurfaceNotInUse();
            }
        }

        private boolean canProvideSurface() {
            return !this.mWasSurfaceProvided && this.mSurfaceRequest != null && Objects.equals(this.mTargetSize, this.mCurrentSurfaceSize);
        }

        private void cancelPreviousRequest() {
            if (this.mSurfaceRequest != null) {
                Logger.d(SurfaceViewImplementation.TAG, "Request canceled: " + this.mSurfaceRequest);
                this.mSurfaceRequest.willNotProvideSurface();
            }
        }

        private void closeSurface() {
            if (this.mSurfaceRequest != null) {
                Logger.d(SurfaceViewImplementation.TAG, "Surface closed " + this.mSurfaceRequest);
                this.mSurfaceRequest.getDeferrableSurface().close();
            }
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            SurfaceRequest surfaceRequest;
            Logger.d(SurfaceViewImplementation.TAG, "Surface created.");
            if (this.mNeedToInvalidate && (surfaceRequest = this.mSurfaceRequestToBeInvalidated) != null) {
                surfaceRequest.invalidate();
                this.mSurfaceRequestToBeInvalidated = null;
                this.mNeedToInvalidate = false;
            }
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            Logger.d(SurfaceViewImplementation.TAG, "Surface changed. Size: " + i2 + ViewHierarchyNode.JsonKeys.X + i3);
            this.mCurrentSurfaceSize = new Size(i2, i3);
            tryToComplete();
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            Logger.d(SurfaceViewImplementation.TAG, "Surface destroyed.");
            if (this.mWasSurfaceProvided) {
                closeSurface();
            } else {
                cancelPreviousRequest();
            }
            this.mNeedToInvalidate = true;
            SurfaceRequest surfaceRequest = this.mSurfaceRequest;
            if (surfaceRequest != null) {
                this.mSurfaceRequestToBeInvalidated = surfaceRequest;
            }
            this.mWasSurfaceProvided = false;
            this.mSurfaceRequest = null;
            this.mOnSurfaceNotInUseListener = null;
            this.mCurrentSurfaceSize = null;
            this.mTargetSize = null;
        }
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> waitForNextFrame() {
        return Futures.immediateFuture(null);
    }

    /* access modifiers changed from: package-private */
    public void setFrameUpdateListener(Executor executor, PreviewView.OnFrameUpdateListener onFrameUpdateListener) {
        throw new IllegalArgumentException("SurfaceView doesn't support frame update listener");
    }

    private static class Api24Impl {
        private Api24Impl() {
        }

        static void pixelCopyRequest(SurfaceView surfaceView, Bitmap bitmap, PixelCopy.OnPixelCopyFinishedListener onPixelCopyFinishedListener, Handler handler) {
            PixelCopy.request(surfaceView, bitmap, onPixelCopyFinishedListener, handler);
        }
    }

    private static boolean shouldReusePreview(SurfaceView surfaceView, Size size, SurfaceRequest surfaceRequest) {
        return surfaceView != null && Objects.equals(size, surfaceRequest.getResolution());
    }
}
