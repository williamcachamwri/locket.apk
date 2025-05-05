package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.imagecapture.TakePictureRequest;
import androidx.camera.core.impl.utils.Threads;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Objects;

public class RequestWithCallback implements TakePictureCallback {
    private CallbackToFutureAdapter.Completer<Void> mCaptureCompleter;
    private final ListenableFuture<Void> mCaptureFuture;
    private ListenableFuture<Void> mCaptureRequestFuture;
    private CallbackToFutureAdapter.Completer<Void> mCompleteCompleter;
    private final ListenableFuture<Void> mCompleteFuture;
    private boolean mIsAborted = false;
    private boolean mIsStarted = false;
    private final TakePictureRequest.RetryControl mRetryControl;
    private final TakePictureRequest mTakePictureRequest;

    RequestWithCallback(TakePictureRequest takePictureRequest, TakePictureRequest.RetryControl retryControl) {
        this.mTakePictureRequest = takePictureRequest;
        this.mRetryControl = retryControl;
        this.mCaptureFuture = CallbackToFutureAdapter.getFuture(new RequestWithCallback$$ExternalSyntheticLambda0(this));
        this.mCompleteFuture = CallbackToFutureAdapter.getFuture(new RequestWithCallback$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-camera-core-imagecapture-RequestWithCallback  reason: not valid java name */
    public /* synthetic */ Object m166lambda$new$0$androidxcameracoreimagecaptureRequestWithCallback(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mCaptureCompleter = completer;
        return "CaptureCompleteFuture";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$androidx-camera-core-imagecapture-RequestWithCallback  reason: not valid java name */
    public /* synthetic */ Object m167lambda$new$1$androidxcameracoreimagecaptureRequestWithCallback(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mCompleteCompleter = completer;
        return "RequestCompleteFuture";
    }

    public void setCaptureRequestFuture(ListenableFuture<Void> listenableFuture) {
        Threads.checkMainThread();
        Preconditions.checkState(this.mCaptureRequestFuture == null, "CaptureRequestFuture can only be set once.");
        this.mCaptureRequestFuture = listenableFuture;
    }

    public void onCaptureStarted() {
        Threads.checkMainThread();
        if (!this.mIsAborted && !this.mIsStarted) {
            this.mIsStarted = true;
            ImageCapture.OnImageCapturedCallback inMemoryCallback = this.mTakePictureRequest.getInMemoryCallback();
            if (inMemoryCallback != null) {
                inMemoryCallback.onCaptureStarted();
            }
            ImageCapture.OnImageSavedCallback onDiskCallback = this.mTakePictureRequest.getOnDiskCallback();
            if (onDiskCallback != null) {
                onDiskCallback.onCaptureStarted();
            }
        }
    }

    public void onImageCaptured() {
        Threads.checkMainThread();
        if (!this.mIsAborted) {
            if (!this.mIsStarted) {
                onCaptureStarted();
            }
            this.mCaptureCompleter.set(null);
        }
    }

    public void onFinalResult(ImageCapture.OutputFileResults outputFileResults) {
        Threads.checkMainThread();
        if (!this.mIsAborted) {
            checkOnImageCaptured();
            markComplete();
            this.mTakePictureRequest.onResult(outputFileResults);
        }
    }

    public void onFinalResult(ImageProxy imageProxy) {
        Threads.checkMainThread();
        if (this.mIsAborted) {
            imageProxy.close();
            return;
        }
        checkOnImageCaptured();
        markComplete();
        this.mTakePictureRequest.onResult(imageProxy);
    }

    public void onCaptureProcessProgressed(int i) {
        Threads.checkMainThread();
        if (!this.mIsAborted) {
            this.mTakePictureRequest.onCaptureProcessProgressed(i);
        }
    }

    public void onPostviewBitmapAvailable(Bitmap bitmap) {
        Threads.checkMainThread();
        if (!this.mIsAborted) {
            this.mTakePictureRequest.onPostviewBitmapAvailable(bitmap);
        }
    }

    public void onProcessFailure(ImageCaptureException imageCaptureException) {
        Threads.checkMainThread();
        if (!this.mIsAborted) {
            checkOnImageCaptured();
            markComplete();
            onFailure(imageCaptureException);
        }
    }

    public boolean isAborted() {
        return this.mIsAborted;
    }

    public void onCaptureFailure(ImageCaptureException imageCaptureException) {
        Threads.checkMainThread();
        if (!this.mIsAborted) {
            boolean decrementRetryCounter = this.mTakePictureRequest.decrementRetryCounter();
            if (!decrementRetryCounter) {
                onFailure(imageCaptureException);
            }
            markComplete();
            this.mCaptureCompleter.setException(imageCaptureException);
            if (decrementRetryCounter) {
                this.mRetryControl.retryRequest(this.mTakePictureRequest);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void abortAndSendErrorToApp(ImageCaptureException imageCaptureException) {
        Threads.checkMainThread();
        if (!this.mCompleteFuture.isDone()) {
            abort(imageCaptureException);
            onFailure(imageCaptureException);
        }
    }

    /* access modifiers changed from: package-private */
    public void abortSilentlyAndRetry() {
        Threads.checkMainThread();
        if (!this.mCompleteFuture.isDone()) {
            abort(new ImageCaptureException(3, "The request is aborted silently and retried.", (Throwable) null));
            this.mRetryControl.retryRequest(this.mTakePictureRequest);
        }
    }

    private void abort(ImageCaptureException imageCaptureException) {
        Threads.checkMainThread();
        this.mIsAborted = true;
        ((ListenableFuture) Objects.requireNonNull(this.mCaptureRequestFuture)).cancel(true);
        this.mCaptureCompleter.setException(imageCaptureException);
        this.mCompleteCompleter.set(null);
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> getCaptureFuture() {
        Threads.checkMainThread();
        return this.mCaptureFuture;
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> getCompleteFuture() {
        Threads.checkMainThread();
        return this.mCompleteFuture;
    }

    public TakePictureRequest getTakePictureRequest() {
        return this.mTakePictureRequest;
    }

    private void checkOnImageCaptured() {
        Preconditions.checkState(this.mCaptureFuture.isDone(), "onImageCaptured() must be called before onFinalResult()");
    }

    private void markComplete() {
        if (!this.mTakePictureRequest.isSimultaneousCapture() || this.mTakePictureRequest.isFormatProcessedInSimultaneousCapture()) {
            if (!this.mTakePictureRequest.isSimultaneousCapture()) {
                Preconditions.checkState(!this.mCompleteFuture.isDone(), "The callback can only complete once.");
            }
            this.mCompleteCompleter.set(null);
        }
    }

    private void onFailure(ImageCaptureException imageCaptureException) {
        Threads.checkMainThread();
        this.mTakePictureRequest.onError(imageCaptureException);
    }
}
