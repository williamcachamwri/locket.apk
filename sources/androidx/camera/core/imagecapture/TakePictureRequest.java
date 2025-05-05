package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.internal.compat.workaround.CaptureFailedRetryEnabler;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

public abstract class TakePictureRequest {
    private static final String TAG = "TakePictureRequest";
    private final Map<Integer, Boolean> mFormatCaptureStatus = new HashMap();
    private int mRemainingRetires = new CaptureFailedRetryEnabler().getRetryCount();

    interface RetryControl {
        void retryRequest(TakePictureRequest takePictureRequest);
    }

    /* access modifiers changed from: package-private */
    public abstract Executor getAppExecutor();

    /* access modifiers changed from: package-private */
    public abstract int getCaptureMode();

    public abstract Rect getCropRect();

    public abstract ImageCapture.OnImageCapturedCallback getInMemoryCallback();

    public abstract int getJpegQuality();

    public abstract ImageCapture.OnImageSavedCallback getOnDiskCallback();

    public abstract ImageCapture.OutputFileOptions getOutputFileOptions();

    public abstract int getRotationDegrees();

    public abstract ImageCapture.OutputFileOptions getSecondaryOutputFileOptions();

    /* access modifiers changed from: package-private */
    public abstract Matrix getSensorToBufferTransform();

    /* access modifiers changed from: package-private */
    public abstract List<CameraCaptureCallback> getSessionConfigCameraCaptureCallbacks();

    /* access modifiers changed from: package-private */
    public abstract boolean isSimultaneousCapture();

    /* access modifiers changed from: package-private */
    public boolean decrementRetryCounter() {
        Threads.checkMainThread();
        int i = this.mRemainingRetires;
        if (i <= 0) {
            return false;
        }
        this.mRemainingRetires = i - 1;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void incrementRetryCounter() {
        Threads.checkMainThread();
        this.mRemainingRetires++;
    }

    /* access modifiers changed from: package-private */
    public int getRemainingRetries() {
        Threads.checkMainThread();
        return this.mRemainingRetires;
    }

    /* access modifiers changed from: package-private */
    public void initFormatProcessStatusInSimultaneousCapture() {
        this.mFormatCaptureStatus.put(32, false);
        this.mFormatCaptureStatus.put(256, false);
    }

    /* access modifiers changed from: package-private */
    public void markFormatProcessStatusInSimultaneousCapture(int i, boolean z) {
        if (!this.mFormatCaptureStatus.containsKey(Integer.valueOf(i))) {
            Logger.e(TAG, "The format is not supported in simultaneous capture");
        } else {
            this.mFormatCaptureStatus.put(Integer.valueOf(i), Boolean.valueOf(z));
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isFormatProcessedInSimultaneousCapture() {
        for (Map.Entry<Integer, Boolean> value : this.mFormatCaptureStatus.entrySet()) {
            if (!((Boolean) value.getValue()).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void onError(ImageCaptureException imageCaptureException) {
        getAppExecutor().execute(new TakePictureRequest$$ExternalSyntheticLambda3(this, imageCaptureException));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onError$0$androidx-camera-core-imagecapture-TakePictureRequest  reason: not valid java name */
    public /* synthetic */ void m171lambda$onError$0$androidxcameracoreimagecaptureTakePictureRequest(ImageCaptureException imageCaptureException) {
        boolean z = true;
        boolean z2 = getInMemoryCallback() != null;
        if (getOnDiskCallback() == null) {
            z = false;
        }
        if (z2 && !z) {
            ((ImageCapture.OnImageCapturedCallback) Objects.requireNonNull(getInMemoryCallback())).onError(imageCaptureException);
        } else if (!z || z2) {
            throw new IllegalStateException("One and only one callback is allowed.");
        } else {
            ((ImageCapture.OnImageSavedCallback) Objects.requireNonNull(getOnDiskCallback())).onError(imageCaptureException);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onResult$1$androidx-camera-core-imagecapture-TakePictureRequest  reason: not valid java name */
    public /* synthetic */ void m173lambda$onResult$1$androidxcameracoreimagecaptureTakePictureRequest(ImageCapture.OutputFileResults outputFileResults) {
        ((ImageCapture.OnImageSavedCallback) Objects.requireNonNull(getOnDiskCallback())).onImageSaved((ImageCapture.OutputFileResults) Objects.requireNonNull(outputFileResults));
    }

    /* access modifiers changed from: package-private */
    public void onResult(ImageCapture.OutputFileResults outputFileResults) {
        getAppExecutor().execute(new TakePictureRequest$$ExternalSyntheticLambda2(this, outputFileResults));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onResult$2$androidx-camera-core-imagecapture-TakePictureRequest  reason: not valid java name */
    public /* synthetic */ void m174lambda$onResult$2$androidxcameracoreimagecaptureTakePictureRequest(ImageProxy imageProxy) {
        ((ImageCapture.OnImageCapturedCallback) Objects.requireNonNull(getInMemoryCallback())).onCaptureSuccess((ImageProxy) Objects.requireNonNull(imageProxy));
    }

    /* access modifiers changed from: package-private */
    public void onResult(ImageProxy imageProxy) {
        getAppExecutor().execute(new TakePictureRequest$$ExternalSyntheticLambda4(this, imageProxy));
    }

    /* access modifiers changed from: package-private */
    public void onCaptureProcessProgressed(int i) {
        getAppExecutor().execute(new TakePictureRequest$$ExternalSyntheticLambda0(this, i));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCaptureProcessProgressed$3$androidx-camera-core-imagecapture-TakePictureRequest  reason: not valid java name */
    public /* synthetic */ void m170lambda$onCaptureProcessProgressed$3$androidxcameracoreimagecaptureTakePictureRequest(int i) {
        if (getOnDiskCallback() != null) {
            getOnDiskCallback().onCaptureProcessProgressed(i);
        } else if (getInMemoryCallback() != null) {
            getInMemoryCallback().onCaptureProcessProgressed(i);
        }
    }

    /* access modifiers changed from: package-private */
    public void onPostviewBitmapAvailable(Bitmap bitmap) {
        getAppExecutor().execute(new TakePictureRequest$$ExternalSyntheticLambda1(this, bitmap));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPostviewBitmapAvailable$4$androidx-camera-core-imagecapture-TakePictureRequest  reason: not valid java name */
    public /* synthetic */ void m172lambda$onPostviewBitmapAvailable$4$androidxcameracoreimagecaptureTakePictureRequest(Bitmap bitmap) {
        if (getOnDiskCallback() != null) {
            getOnDiskCallback().onPostviewBitmapAvailable(bitmap);
        } else if (getInMemoryCallback() != null) {
            getInMemoryCallback().onPostviewBitmapAvailable(bitmap);
        }
    }

    public static TakePictureRequest of(Executor executor, ImageCapture.OnImageCapturedCallback onImageCapturedCallback, ImageCapture.OnImageSavedCallback onImageSavedCallback, ImageCapture.OutputFileOptions outputFileOptions, ImageCapture.OutputFileOptions outputFileOptions2, Rect rect, Matrix matrix, int i, int i2, int i3, boolean z, List<CameraCaptureCallback> list) {
        boolean z2 = true;
        Preconditions.checkArgument((onImageSavedCallback == null) == (outputFileOptions == null), "onDiskCallback and outputFileOptions should be both null or both non-null.");
        boolean z3 = onImageSavedCallback == null;
        if (onImageCapturedCallback != null) {
            z2 = false;
        }
        Preconditions.checkArgument(z2 ^ z3, "One and only one on-disk or in-memory callback should be present.");
        AutoValue_TakePictureRequest autoValue_TakePictureRequest = new AutoValue_TakePictureRequest(executor, onImageCapturedCallback, onImageSavedCallback, outputFileOptions, outputFileOptions2, rect, matrix, i, i2, i3, z, list);
        if (z) {
            autoValue_TakePictureRequest.initFormatProcessStatusInSimultaneousCapture();
        }
        return autoValue_TakePictureRequest;
    }
}
