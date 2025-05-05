package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageWriter;
import android.util.Size;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.internal.compat.quirk.ZslDisablerQuirk;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.camera.core.MetadataImageReader;
import androidx.camera.core.SafeCloseImageReaderProxy;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.CompareSizesByArea;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.compat.ImageWriterCompat;
import androidx.camera.core.internal.utils.ZslRingBuffer;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

final class ZslControlImpl implements ZslControl {
    static final int MAX_IMAGES = 9;
    static final int RING_BUFFER_CAPACITY = 3;
    private static final String TAG = "ZslControlImpl";
    private final CameraCharacteristicsCompat mCameraCharacteristicsCompat;
    final ZslRingBuffer mImageRingBuffer;
    private boolean mIsPrivateReprocessingSupported = false;
    private boolean mIsZslDisabledByFlashMode = false;
    private boolean mIsZslDisabledByUseCaseConfig = false;
    private CameraCaptureCallback mMetadataMatchingCaptureCallback;
    private DeferrableSurface mReprocessingImageDeferrableSurface;
    SafeCloseImageReaderProxy mReprocessingImageReader;
    ImageWriter mReprocessingImageWriter;
    private boolean mShouldZslDisabledByQuirks = false;

    ZslControlImpl(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        boolean z = false;
        this.mCameraCharacteristicsCompat = cameraCharacteristicsCompat;
        this.mIsPrivateReprocessingSupported = ZslUtil.isCapabilitySupported(cameraCharacteristicsCompat, 4);
        this.mShouldZslDisabledByQuirks = DeviceQuirks.get(ZslDisablerQuirk.class) != null ? true : z;
        this.mImageRingBuffer = new ZslRingBuffer(3, new ZslControlImpl$$ExternalSyntheticLambda2());
    }

    public void setZslDisabledByUserCaseConfig(boolean z) {
        this.mIsZslDisabledByUseCaseConfig = z;
    }

    public boolean isZslDisabledByUserCaseConfig() {
        return this.mIsZslDisabledByUseCaseConfig;
    }

    public void setZslDisabledByFlashMode(boolean z) {
        this.mIsZslDisabledByFlashMode = z;
    }

    public boolean isZslDisabledByFlashMode() {
        return this.mIsZslDisabledByFlashMode;
    }

    public void addZslConfig(SessionConfig.Builder builder) {
        cleanup();
        if (this.mIsZslDisabledByUseCaseConfig) {
            builder.setTemplateType(1);
        } else if (this.mShouldZslDisabledByQuirks) {
            builder.setTemplateType(1);
        } else {
            Map<Integer, Size> createReprocessingInputSizeMap = createReprocessingInputSizeMap(this.mCameraCharacteristicsCompat);
            if (!this.mIsPrivateReprocessingSupported || createReprocessingInputSizeMap.isEmpty() || !createReprocessingInputSizeMap.containsKey(34) || !isJpegValidOutputForInputFormat(this.mCameraCharacteristicsCompat, 34)) {
                builder.setTemplateType(1);
                return;
            }
            Size size = createReprocessingInputSizeMap.get(34);
            MetadataImageReader metadataImageReader = new MetadataImageReader(size.getWidth(), size.getHeight(), 34, 9);
            this.mMetadataMatchingCaptureCallback = metadataImageReader.getCameraCaptureCallback();
            this.mReprocessingImageReader = new SafeCloseImageReaderProxy(metadataImageReader);
            metadataImageReader.setOnImageAvailableListener(new ZslControlImpl$$ExternalSyntheticLambda0(this), CameraXExecutors.ioExecutor());
            ImmediateSurface immediateSurface = new ImmediateSurface(this.mReprocessingImageReader.getSurface(), new Size(this.mReprocessingImageReader.getWidth(), this.mReprocessingImageReader.getHeight()), 34);
            this.mReprocessingImageDeferrableSurface = immediateSurface;
            SafeCloseImageReaderProxy safeCloseImageReaderProxy = this.mReprocessingImageReader;
            ListenableFuture<Void> terminationFuture = immediateSurface.getTerminationFuture();
            Objects.requireNonNull(safeCloseImageReaderProxy);
            terminationFuture.addListener(new ZslControlImpl$$ExternalSyntheticLambda1(safeCloseImageReaderProxy), CameraXExecutors.mainThreadExecutor());
            builder.addSurface(this.mReprocessingImageDeferrableSurface);
            builder.addCameraCaptureCallback(this.mMetadataMatchingCaptureCallback);
            builder.addSessionStateCallback(new CameraCaptureSession.StateCallback() {
                public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                }

                public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                    Surface inputSurface = cameraCaptureSession.getInputSurface();
                    if (inputSurface != null) {
                        ZslControlImpl.this.mReprocessingImageWriter = ImageWriterCompat.newInstance(inputSurface, 1);
                    }
                }
            });
            builder.setInputConfiguration(new InputConfiguration(this.mReprocessingImageReader.getWidth(), this.mReprocessingImageReader.getHeight(), this.mReprocessingImageReader.getImageFormat()));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addZslConfig$1$androidx-camera-camera2-internal-ZslControlImpl  reason: not valid java name */
    public /* synthetic */ void m101lambda$addZslConfig$1$androidxcameracamera2internalZslControlImpl(ImageReaderProxy imageReaderProxy) {
        try {
            ImageProxy acquireLatestImage = imageReaderProxy.acquireLatestImage();
            if (acquireLatestImage != null) {
                this.mImageRingBuffer.enqueue(acquireLatestImage);
            }
        } catch (IllegalStateException e) {
            Logger.e(TAG, "Failed to acquire latest image IllegalStateException = " + e.getMessage());
        }
    }

    public ImageProxy dequeueImageFromBuffer() {
        try {
            return (ImageProxy) this.mImageRingBuffer.dequeue();
        } catch (NoSuchElementException unused) {
            Logger.e(TAG, "dequeueImageFromBuffer no such element");
            return null;
        }
    }

    public boolean enqueueImageToImageWriter(ImageProxy imageProxy) {
        Image image = imageProxy.getImage();
        ImageWriter imageWriter = this.mReprocessingImageWriter;
        if (!(imageWriter == null || image == null)) {
            try {
                ImageWriterCompat.queueInputImage(imageWriter, image);
                return true;
            } catch (IllegalStateException e) {
                Logger.e(TAG, "enqueueImageToImageWriter throws IllegalStateException = " + e.getMessage());
            }
        }
        return false;
    }

    private void cleanup() {
        ZslRingBuffer zslRingBuffer = this.mImageRingBuffer;
        while (!zslRingBuffer.isEmpty()) {
            ((ImageProxy) zslRingBuffer.dequeue()).close();
        }
        DeferrableSurface deferrableSurface = this.mReprocessingImageDeferrableSurface;
        if (deferrableSurface != null) {
            SafeCloseImageReaderProxy safeCloseImageReaderProxy = this.mReprocessingImageReader;
            if (safeCloseImageReaderProxy != null) {
                ListenableFuture<Void> terminationFuture = deferrableSurface.getTerminationFuture();
                Objects.requireNonNull(safeCloseImageReaderProxy);
                terminationFuture.addListener(new ZslControlImpl$$ExternalSyntheticLambda1(safeCloseImageReaderProxy), CameraXExecutors.mainThreadExecutor());
                this.mReprocessingImageReader = null;
            }
            deferrableSurface.close();
            this.mReprocessingImageDeferrableSurface = null;
        }
        ImageWriter imageWriter = this.mReprocessingImageWriter;
        if (imageWriter != null) {
            imageWriter.close();
            this.mReprocessingImageWriter = null;
        }
    }

    private Map<Integer, Size> createReprocessingInputSizeMap(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        StreamConfigurationMap streamConfigurationMap;
        try {
            streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristicsCompat.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        } catch (AssertionError e) {
            Logger.e(TAG, "Failed to retrieve StreamConfigurationMap, error = " + e.getMessage());
            streamConfigurationMap = null;
        }
        if (streamConfigurationMap == null || streamConfigurationMap.getInputFormats() == null) {
            return new HashMap();
        }
        HashMap hashMap = new HashMap();
        for (int i : streamConfigurationMap.getInputFormats()) {
            Size[] inputSizes = streamConfigurationMap.getInputSizes(i);
            if (inputSizes != null) {
                Arrays.sort(inputSizes, new CompareSizesByArea(true));
                hashMap.put(Integer.valueOf(i), inputSizes[0]);
            }
        }
        return hashMap;
    }

    private boolean isJpegValidOutputForInputFormat(CameraCharacteristicsCompat cameraCharacteristicsCompat, int i) {
        int[] validOutputFormatsForInput;
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristicsCompat.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap == null || (validOutputFormatsForInput = streamConfigurationMap.getValidOutputFormatsForInput(i)) == null) {
            return false;
        }
        for (int i2 : validOutputFormatsForInput) {
            if (i2 == 256) {
                return true;
            }
        }
        return false;
    }
}
