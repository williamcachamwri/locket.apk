package androidx.camera.core.imagecapture;

import android.util.Log;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.camera.core.imagecapture.TakePictureManager;
import androidx.camera.core.imagecapture.TakePictureRequest;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.core.util.Pair;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

public class TakePictureManagerImpl implements TakePictureManager, ForwardingImageProxy.OnImageCloseListener, TakePictureRequest.RetryControl {
    private static final String TAG = "TakePictureManagerImpl";
    private RequestWithCallback mCapturingRequest;
    final ImageCaptureControl mImageCaptureControl;
    ImagePipeline mImagePipeline;
    private final List<RequestWithCallback> mIncompleteRequests;
    final Deque<TakePictureRequest> mNewRequests = new ArrayDeque();
    boolean mPaused = false;

    public TakePictureManagerImpl(ImageCaptureControl imageCaptureControl) {
        Threads.checkMainThread();
        this.mImageCaptureControl = imageCaptureControl;
        this.mIncompleteRequests = new ArrayList();
    }

    public void setImagePipeline(ImagePipeline imagePipeline) {
        Threads.checkMainThread();
        this.mImagePipeline = imagePipeline;
        imagePipeline.setOnImageCloseListener(this);
    }

    public void offerRequest(TakePictureRequest takePictureRequest) {
        Threads.checkMainThread();
        this.mNewRequests.offer(takePictureRequest);
        issueNextRequest();
    }

    public void retryRequest(TakePictureRequest takePictureRequest) {
        Threads.checkMainThread();
        Logger.d(TAG, "Add a new request for retrying.");
        this.mNewRequests.addFirst(takePictureRequest);
        issueNextRequest();
    }

    public void pause() {
        Threads.checkMainThread();
        this.mPaused = true;
        RequestWithCallback requestWithCallback = this.mCapturingRequest;
        if (requestWithCallback != null) {
            requestWithCallback.abortSilentlyAndRetry();
        }
    }

    public void resume() {
        Threads.checkMainThread();
        this.mPaused = false;
        issueNextRequest();
    }

    public void abortRequests() {
        Threads.checkMainThread();
        ImageCaptureException imageCaptureException = new ImageCaptureException(3, "Camera is closed.", (Throwable) null);
        for (TakePictureRequest onError : this.mNewRequests) {
            onError.onError(imageCaptureException);
        }
        this.mNewRequests.clear();
        for (RequestWithCallback abortAndSendErrorToApp : new ArrayList(this.mIncompleteRequests)) {
            abortAndSendErrorToApp.abortAndSendErrorToApp(imageCaptureException);
        }
    }

    /* access modifiers changed from: package-private */
    public void issueNextRequest() {
        Threads.checkMainThread();
        Log.d(TAG, "Issue the next TakePictureRequest.");
        if (hasCapturingRequest()) {
            Log.d(TAG, "There is already a request in-flight.");
        } else if (this.mPaused) {
            Log.d(TAG, "The class is paused.");
        } else if (this.mImagePipeline.getCapacity() == 0) {
            Log.d(TAG, "Too many acquire images. Close image to be able to process next.");
        } else {
            TakePictureRequest poll = this.mNewRequests.poll();
            if (poll == null) {
                Log.d(TAG, "No new request.");
                return;
            }
            RequestWithCallback requestWithCallback = new RequestWithCallback(poll, this);
            trackCurrentRequests(requestWithCallback);
            Pair<CameraRequest, ProcessingRequest> createRequests = this.mImagePipeline.createRequests(poll, requestWithCallback, requestWithCallback.getCaptureFuture());
            this.mImagePipeline.submitProcessingRequest((ProcessingRequest) Objects.requireNonNull((ProcessingRequest) createRequests.second));
            requestWithCallback.setCaptureRequestFuture(submitCameraRequest((CameraRequest) Objects.requireNonNull((CameraRequest) createRequests.first)));
        }
    }

    private void trackCurrentRequests(RequestWithCallback requestWithCallback) {
        Preconditions.checkState(!hasCapturingRequest());
        this.mCapturingRequest = requestWithCallback;
        requestWithCallback.getCaptureFuture().addListener(new TakePictureManagerImpl$$ExternalSyntheticLambda1(this), CameraXExecutors.directExecutor());
        this.mIncompleteRequests.add(requestWithCallback);
        requestWithCallback.getCompleteFuture().addListener(new TakePictureManagerImpl$$ExternalSyntheticLambda2(this, requestWithCallback), CameraXExecutors.directExecutor());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$trackCurrentRequests$0$androidx-camera-core-imagecapture-TakePictureManagerImpl  reason: not valid java name */
    public /* synthetic */ void m168lambda$trackCurrentRequests$0$androidxcameracoreimagecaptureTakePictureManagerImpl() {
        this.mCapturingRequest = null;
        issueNextRequest();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$trackCurrentRequests$1$androidx-camera-core-imagecapture-TakePictureManagerImpl  reason: not valid java name */
    public /* synthetic */ void m169lambda$trackCurrentRequests$1$androidxcameracoreimagecaptureTakePictureManagerImpl(RequestWithCallback requestWithCallback) {
        this.mIncompleteRequests.remove(requestWithCallback);
    }

    private ListenableFuture<Void> submitCameraRequest(final CameraRequest cameraRequest) {
        Threads.checkMainThread();
        this.mImageCaptureControl.lockFlashMode();
        ListenableFuture<Void> submitStillCaptureRequests = this.mImageCaptureControl.submitStillCaptureRequests(cameraRequest.getCaptureConfigs());
        Futures.addCallback(submitStillCaptureRequests, new FutureCallback<Void>() {
            public void onSuccess(Void voidR) {
                TakePictureManagerImpl.this.mImageCaptureControl.unlockFlashMode();
            }

            public void onFailure(Throwable th) {
                if (!cameraRequest.isAborted()) {
                    int id = cameraRequest.getCaptureConfigs().get(0).getId();
                    if (th instanceof ImageCaptureException) {
                        TakePictureManagerImpl.this.mImagePipeline.notifyCaptureError(TakePictureManager.CaptureError.of(id, (ImageCaptureException) th));
                    } else {
                        TakePictureManagerImpl.this.mImagePipeline.notifyCaptureError(TakePictureManager.CaptureError.of(id, new ImageCaptureException(2, "Failed to submit capture request", th)));
                    }
                    TakePictureManagerImpl.this.mImageCaptureControl.unlockFlashMode();
                }
            }
        }, CameraXExecutors.mainThreadExecutor());
        return submitStillCaptureRequests;
    }

    public boolean hasCapturingRequest() {
        return this.mCapturingRequest != null;
    }

    public RequestWithCallback getCapturingRequest() {
        return this.mCapturingRequest;
    }

    public List<RequestWithCallback> getIncompleteRequests() {
        return this.mIncompleteRequests;
    }

    public ImagePipeline getImagePipeline() {
        return this.mImagePipeline;
    }

    public void onImageClose(ImageProxy imageProxy) {
        CameraXExecutors.mainThreadExecutor().execute(new TakePictureManagerImpl$$ExternalSyntheticLambda0(this));
    }
}
