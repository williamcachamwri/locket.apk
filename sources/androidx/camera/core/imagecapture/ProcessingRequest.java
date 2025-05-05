package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.CaptureBundle;
import androidx.camera.core.impl.CaptureStage;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ProcessingRequest {
    static final int PROGRESS_NOT_RECEIVED = -1;
    private final TakePictureCallback mCallback;
    final ListenableFuture<Void> mCaptureFuture;
    private final Rect mCropRect;
    private final int mJpegQuality;
    private int mLastCaptureProcessProgressed;
    private final ImageCapture.OutputFileOptions mOutputFileOptions;
    private final int mRequestId;
    private final int mRotationDegrees;
    private final ImageCapture.OutputFileOptions mSecondaryOutputFileOptions;
    private final Matrix mSensorToBufferTransform;
    private final List<Integer> mStageIds;
    private final String mTagBundleKey;
    TakePictureRequest mTakePictureRequest;

    ProcessingRequest(CaptureBundle captureBundle, TakePictureRequest takePictureRequest, TakePictureCallback takePictureCallback, ListenableFuture<Void> listenableFuture) {
        this(captureBundle, takePictureRequest, takePictureCallback, listenableFuture, 0);
    }

    ProcessingRequest(CaptureBundle captureBundle, TakePictureRequest takePictureRequest, TakePictureCallback takePictureCallback, ListenableFuture<Void> listenableFuture, int i) {
        this.mLastCaptureProcessProgressed = -1;
        this.mRequestId = i;
        this.mTakePictureRequest = takePictureRequest;
        this.mOutputFileOptions = takePictureRequest.getOutputFileOptions();
        this.mSecondaryOutputFileOptions = takePictureRequest.getSecondaryOutputFileOptions();
        this.mJpegQuality = takePictureRequest.getJpegQuality();
        this.mRotationDegrees = takePictureRequest.getRotationDegrees();
        this.mCropRect = takePictureRequest.getCropRect();
        this.mSensorToBufferTransform = takePictureRequest.getSensorToBufferTransform();
        this.mCallback = takePictureCallback;
        this.mTagBundleKey = String.valueOf(captureBundle.hashCode());
        this.mStageIds = new ArrayList();
        for (CaptureStage id : (List) Objects.requireNonNull(captureBundle.getCaptureStages())) {
            this.mStageIds.add(Integer.valueOf(id.getId()));
        }
        this.mCaptureFuture = listenableFuture;
    }

    /* access modifiers changed from: package-private */
    public String getTagBundleKey() {
        return this.mTagBundleKey;
    }

    /* access modifiers changed from: package-private */
    public List<Integer> getStageIds() {
        return this.mStageIds;
    }

    public int getRequestId() {
        return this.mRequestId;
    }

    /* access modifiers changed from: package-private */
    public TakePictureRequest getTakePictureRequest() {
        return this.mTakePictureRequest;
    }

    /* access modifiers changed from: package-private */
    public ImageCapture.OutputFileOptions getOutputFileOptions() {
        return this.mOutputFileOptions;
    }

    /* access modifiers changed from: package-private */
    public ImageCapture.OutputFileOptions getSecondaryOutputFileOptions() {
        return this.mSecondaryOutputFileOptions;
    }

    /* access modifiers changed from: package-private */
    public Rect getCropRect() {
        return this.mCropRect;
    }

    /* access modifiers changed from: package-private */
    public int getRotationDegrees() {
        return this.mRotationDegrees;
    }

    /* access modifiers changed from: package-private */
    public int getJpegQuality() {
        return this.mJpegQuality;
    }

    /* access modifiers changed from: package-private */
    public Matrix getSensorToBufferTransform() {
        return this.mSensorToBufferTransform;
    }

    /* access modifiers changed from: package-private */
    public boolean isInMemoryCapture() {
        return getOutputFileOptions() == null && getSecondaryOutputFileOptions() == null;
    }

    /* access modifiers changed from: package-private */
    public void onCaptureStarted() {
        this.mCallback.onCaptureStarted();
    }

    /* access modifiers changed from: package-private */
    public void onCaptureProcessProgressed(int i) {
        if (this.mLastCaptureProcessProgressed != i) {
            this.mLastCaptureProcessProgressed = i;
            this.mCallback.onCaptureProcessProgressed(i);
        }
    }

    /* access modifiers changed from: package-private */
    public void onImageCaptured() {
        if (this.mLastCaptureProcessProgressed != -1) {
            onCaptureProcessProgressed(100);
        }
        this.mCallback.onImageCaptured();
    }

    /* access modifiers changed from: package-private */
    public void onFinalResult(ImageCapture.OutputFileResults outputFileResults) {
        this.mCallback.onFinalResult(outputFileResults);
    }

    /* access modifiers changed from: package-private */
    public void onPostviewBitmapAvailable(Bitmap bitmap) {
        this.mCallback.onPostviewBitmapAvailable(bitmap);
    }

    /* access modifiers changed from: package-private */
    public void onFinalResult(ImageProxy imageProxy) {
        this.mCallback.onFinalResult(imageProxy);
    }

    /* access modifiers changed from: package-private */
    public void onProcessFailure(ImageCaptureException imageCaptureException) {
        this.mCallback.onProcessFailure(imageCaptureException);
    }

    /* access modifiers changed from: package-private */
    public void onCaptureFailure(ImageCaptureException imageCaptureException) {
        this.mCallback.onCaptureFailure(imageCaptureException);
    }

    /* access modifiers changed from: package-private */
    public boolean isAborted() {
        return this.mCallback.isAborted();
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> getCaptureFuture() {
        return this.mCaptureFuture;
    }
}
