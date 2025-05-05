package androidx.camera.core.imagecapture;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Size;
import androidx.camera.core.CameraEffect;
import androidx.camera.core.CaptureBundles;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.MetadataImageReader;
import androidx.camera.core.imagecapture.CaptureNode;
import androidx.camera.core.imagecapture.TakePictureManager;
import androidx.camera.core.impl.CaptureBundle;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.CaptureStage;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.compat.workaround.ExifRotationAvailability;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.camera.core.processing.InternalImageProcessor;
import androidx.core.util.Pair;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

public class ImagePipeline {
    static final ExifRotationAvailability EXIF_ROTATION_AVAILABILITY = new ExifRotationAvailability();
    static final byte JPEG_QUALITY_MAX_QUALITY = 100;
    static final byte JPEG_QUALITY_MIN_LATENCY = 95;
    private static int sNextRequestId;
    private final CaptureConfig mCaptureConfig;
    private final CaptureNode mCaptureNode;
    private final CaptureNode.In mPipelineIn;
    private final ProcessingNode mProcessingNode;
    private final ImageCaptureConfig mUseCaseConfig;

    public ImagePipeline(ImageCaptureConfig imageCaptureConfig, Size size, CameraCharacteristics cameraCharacteristics) {
        this(imageCaptureConfig, size, cameraCharacteristics, (CameraEffect) null, false, (Size) null, 35);
    }

    public ImagePipeline(ImageCaptureConfig imageCaptureConfig, Size size, CameraCharacteristics cameraCharacteristics, CameraEffect cameraEffect, boolean z) {
        this(imageCaptureConfig, size, cameraCharacteristics, cameraEffect, z, (Size) null, 35);
    }

    public ImagePipeline(ImageCaptureConfig imageCaptureConfig, Size size, CameraCharacteristics cameraCharacteristics, CameraEffect cameraEffect, boolean z, Size size2, int i) {
        CameraEffect cameraEffect2 = cameraEffect;
        Threads.checkMainThread();
        this.mUseCaseConfig = imageCaptureConfig;
        this.mCaptureConfig = CaptureConfig.Builder.createFrom(imageCaptureConfig).build();
        CaptureNode captureNode = new CaptureNode();
        this.mCaptureNode = captureNode;
        ProcessingNode processingNode = new ProcessingNode((Executor) Objects.requireNonNull(imageCaptureConfig.getIoExecutor(CameraXExecutors.ioExecutor())), cameraCharacteristics, cameraEffect2 != null ? new InternalImageProcessor(cameraEffect2) : null);
        this.mProcessingNode = processingNode;
        ArrayList arrayList = new ArrayList();
        if (imageCaptureConfig.getSecondaryInputFormat() != 0) {
            arrayList.add(32);
            arrayList.add(256);
        } else {
            arrayList.add(Integer.valueOf(getOutputFormat()));
        }
        CaptureNode.In of = CaptureNode.In.of(size, imageCaptureConfig.getInputFormat(), arrayList, z, imageCaptureConfig.getImageReaderProxyProvider(), size2, i);
        this.mPipelineIn = of;
        processingNode.transform(captureNode.transform(of));
    }

    public SessionConfig.Builder createSessionConfigBuilder(Size size) {
        SessionConfig.Builder createFrom = SessionConfig.Builder.createFrom(this.mUseCaseConfig, size);
        createFrom.addNonRepeatingSurface(this.mPipelineIn.getSurface());
        if (this.mPipelineIn.getOutputFormats().size() > 1 && this.mPipelineIn.getSecondarySurface() != null) {
            createFrom.addNonRepeatingSurface(this.mPipelineIn.getSecondarySurface());
        }
        if (this.mPipelineIn.getPostviewSurface() != null) {
            createFrom.setPostviewSurface(this.mPipelineIn.getPostviewSurface());
        }
        return createFrom;
    }

    public void close() {
        Threads.checkMainThread();
        this.mCaptureNode.release();
        this.mProcessingNode.release();
    }

    public int getCapacity() {
        Threads.checkMainThread();
        return this.mCaptureNode.getCapacity();
    }

    public void setOnImageCloseListener(ForwardingImageProxy.OnImageCloseListener onImageCloseListener) {
        Threads.checkMainThread();
        this.mCaptureNode.setOnImageCloseListener(onImageCloseListener);
    }

    /* access modifiers changed from: package-private */
    public Pair<CameraRequest, ProcessingRequest> createRequests(TakePictureRequest takePictureRequest, TakePictureCallback takePictureCallback, ListenableFuture<Void> listenableFuture) {
        Threads.checkMainThread();
        CaptureBundle createCaptureBundle = createCaptureBundle();
        int i = sNextRequestId;
        sNextRequestId = i + 1;
        return new Pair<>(createCameraRequest(i, createCaptureBundle, takePictureRequest, takePictureCallback), createProcessingRequest(i, createCaptureBundle, takePictureRequest, takePictureCallback, listenableFuture));
    }

    /* access modifiers changed from: package-private */
    public void submitProcessingRequest(ProcessingRequest processingRequest) {
        Threads.checkMainThread();
        this.mPipelineIn.getRequestEdge().accept(processingRequest);
    }

    /* access modifiers changed from: package-private */
    public void notifyCaptureError(TakePictureManager.CaptureError captureError) {
        Threads.checkMainThread();
        this.mPipelineIn.getErrorEdge().accept(captureError);
    }

    private int getOutputFormat() {
        Integer num = (Integer) this.mUseCaseConfig.retrieveOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, null);
        if (num != null) {
            return num.intValue();
        }
        Integer num2 = (Integer) this.mUseCaseConfig.retrieveOption(ImageInputConfig.OPTION_INPUT_FORMAT, null);
        if (num2 == null || num2.intValue() != 4101) {
            return (num2 == null || num2.intValue() != 32) ? 256 : 32;
        }
        return 4101;
    }

    private CaptureBundle createCaptureBundle() {
        return (CaptureBundle) Objects.requireNonNull(this.mUseCaseConfig.getCaptureBundle(CaptureBundles.singleDefaultCaptureBundle()));
    }

    private ProcessingRequest createProcessingRequest(int i, CaptureBundle captureBundle, TakePictureRequest takePictureRequest, TakePictureCallback takePictureCallback, ListenableFuture<Void> listenableFuture) {
        return new ProcessingRequest(captureBundle, takePictureRequest, takePictureCallback, listenableFuture, i);
    }

    private boolean shouldEnablePostview() {
        return this.mPipelineIn.getPostviewSurface() != null;
    }

    public Size getPostviewSize() {
        return this.mPipelineIn.getPostviewSize();
    }

    private CameraRequest createCameraRequest(int i, CaptureBundle captureBundle, TakePictureRequest takePictureRequest, TakePictureCallback takePictureCallback) {
        ArrayList arrayList = new ArrayList();
        String valueOf = String.valueOf(captureBundle.hashCode());
        for (CaptureStage captureStage : (List) Objects.requireNonNull(captureBundle.getCaptureStages())) {
            CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setTemplateType(this.mCaptureConfig.getTemplateType());
            builder.addImplementationOptions(this.mCaptureConfig.getImplementationOptions());
            builder.addAllCameraCaptureCallbacks(takePictureRequest.getSessionConfigCameraCaptureCallbacks());
            builder.addSurface(this.mPipelineIn.getSurface());
            if (this.mPipelineIn.getOutputFormats().size() > 1 && this.mPipelineIn.getSecondarySurface() != null) {
                builder.addSurface(this.mPipelineIn.getSecondarySurface());
            }
            builder.setPostviewEnabled(shouldEnablePostview());
            if (ImageUtil.isJpegFormats(this.mPipelineIn.getInputFormat()) || ImageUtil.isRawFormats(this.mPipelineIn.getInputFormat())) {
                if (EXIF_ROTATION_AVAILABILITY.isRotationOptionSupported()) {
                    builder.addImplementationOption(CaptureConfig.OPTION_ROTATION, Integer.valueOf(takePictureRequest.getRotationDegrees()));
                }
                builder.addImplementationOption(CaptureConfig.OPTION_JPEG_QUALITY, Integer.valueOf(getCameraRequestJpegQuality(takePictureRequest)));
            }
            builder.addImplementationOptions(captureStage.getCaptureConfig().getImplementationOptions());
            builder.addTag(valueOf, Integer.valueOf(captureStage.getId()));
            builder.setId(i);
            builder.addCameraCaptureCallback(this.mPipelineIn.getCameraCaptureCallback());
            if (this.mPipelineIn.getOutputFormats().size() > 1 && this.mPipelineIn.getSecondaryCameraCaptureCallback() != null) {
                builder.addCameraCaptureCallback(this.mPipelineIn.getSecondaryCameraCaptureCallback());
            }
            arrayList.add(builder.build());
        }
        return new CameraRequest(arrayList, takePictureCallback);
    }

    /* access modifiers changed from: package-private */
    public int getCameraRequestJpegQuality(TakePictureRequest takePictureRequest) {
        boolean z = takePictureRequest.getOnDiskCallback() != null;
        boolean hasCropping = TransformUtils.hasCropping(takePictureRequest.getCropRect(), this.mPipelineIn.getSize());
        if (!z || !hasCropping) {
            return takePictureRequest.getJpegQuality();
        }
        return takePictureRequest.getCaptureMode() == 0 ? 100 : 95;
    }

    /* access modifiers changed from: package-private */
    public CaptureNode getCaptureNode() {
        return this.mCaptureNode;
    }

    /* access modifiers changed from: package-private */
    public ProcessingNode getProcessingNode() {
        return this.mProcessingNode;
    }

    public boolean expectsMetadata() {
        return this.mCaptureNode.getSafeCloseImageReaderProxy().getImageReaderProxy() instanceof MetadataImageReader;
    }
}
