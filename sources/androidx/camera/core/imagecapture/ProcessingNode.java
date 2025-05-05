package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureResult;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.camera.core.imagecapture.Bitmap2JpegBytes;
import androidx.camera.core.imagecapture.DngImage2Disk;
import androidx.camera.core.imagecapture.Image2JpegBytes;
import androidx.camera.core.imagecapture.JpegBytes2Disk;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.internal.compat.quirk.IncorrectJpegMetadataQuirk;
import androidx.camera.core.internal.compat.quirk.LowMemoryQuirk;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.camera.core.processing.Edge;
import androidx.camera.core.processing.InternalImageProcessor;
import androidx.camera.core.processing.Node;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import androidx.core.util.Preconditions;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

public class ProcessingNode implements Node<In, Void> {
    private static final String TAG = "ProcessingNode";
    private Operation<Bitmap2JpegBytes.In, Packet<byte[]>> mBitmap2JpegBytes;
    private Operation<Packet<Bitmap>, Packet<Bitmap>> mBitmapEffect;
    final Executor mBlockingExecutor;
    private final CameraCharacteristics mCameraCharacteristics;
    DngImage2Disk mDngImage2Disk;
    private final boolean mHasIncorrectJpegMetadataQuirk;
    private Operation<Packet<ImageProxy>, Bitmap> mImage2Bitmap;
    private Operation<Image2JpegBytes.In, Packet<byte[]>> mImage2JpegBytes;
    final InternalImageProcessor mImageProcessor;
    private Operation<InputPacket, Packet<ImageProxy>> mInput2Packet;
    private In mInputEdge;
    private Operation<Packet<byte[]>, Packet<Bitmap>> mJpegBytes2CroppedBitmap;
    private Operation<JpegBytes2Disk.In, ImageCapture.OutputFileResults> mJpegBytes2Disk;
    private Operation<Packet<byte[]>, Packet<ImageProxy>> mJpegBytes2Image;
    private Operation<Packet<ImageProxy>, ImageProxy> mJpegImage2Result;
    private final Quirks mQuirks;

    public void release() {
    }

    ProcessingNode(Executor executor, CameraCharacteristics cameraCharacteristics) {
        this(executor, cameraCharacteristics, (InternalImageProcessor) null, DeviceQuirks.getAll());
    }

    ProcessingNode(Executor executor, Quirks quirks, CameraCharacteristics cameraCharacteristics) {
        this(executor, cameraCharacteristics, (InternalImageProcessor) null, quirks);
    }

    ProcessingNode(Executor executor, CameraCharacteristics cameraCharacteristics, InternalImageProcessor internalImageProcessor) {
        this(executor, cameraCharacteristics, internalImageProcessor, DeviceQuirks.getAll());
    }

    ProcessingNode(Executor executor, CameraCharacteristics cameraCharacteristics, InternalImageProcessor internalImageProcessor, Quirks quirks) {
        if (DeviceQuirks.get(LowMemoryQuirk.class) != null) {
            this.mBlockingExecutor = CameraXExecutors.newSequentialExecutor(executor);
        } else {
            this.mBlockingExecutor = executor;
        }
        this.mImageProcessor = internalImageProcessor;
        this.mCameraCharacteristics = cameraCharacteristics;
        this.mQuirks = quirks;
        this.mHasIncorrectJpegMetadataQuirk = quirks.contains(IncorrectJpegMetadataQuirk.class);
    }

    public Void transform(In in) {
        this.mInputEdge = in;
        in.getEdge().setListener(new ProcessingNode$$ExternalSyntheticLambda1(this));
        in.getPostviewEdge().setListener(new ProcessingNode$$ExternalSyntheticLambda2(this));
        this.mInput2Packet = new ProcessingInput2Packet();
        this.mImage2JpegBytes = new Image2JpegBytes(this.mQuirks);
        this.mJpegBytes2CroppedBitmap = new JpegBytes2CroppedBitmap();
        this.mBitmap2JpegBytes = new Bitmap2JpegBytes();
        this.mJpegBytes2Disk = new JpegBytes2Disk();
        this.mJpegImage2Result = new JpegImage2Result();
        this.mImage2Bitmap = new Image2Bitmap();
        if (in.getInputFormat() == 35 || this.mImageProcessor != null || this.mHasIncorrectJpegMetadataQuirk) {
            this.mJpegBytes2Image = new JpegBytes2Image();
        }
        if (this.mImageProcessor == null) {
            return null;
        }
        this.mBitmapEffect = new BitmapEffect(this.mImageProcessor);
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$transform$1$androidx-camera-core-imagecapture-ProcessingNode  reason: not valid java name */
    public /* synthetic */ void m163lambda$transform$1$androidxcameracoreimagecaptureProcessingNode(InputPacket inputPacket) {
        if (inputPacket.getProcessingRequest().isAborted()) {
            inputPacket.getImageProxy().close();
        } else {
            this.mBlockingExecutor.execute(new ProcessingNode$$ExternalSyntheticLambda5(this, inputPacket));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$transform$3$androidx-camera-core-imagecapture-ProcessingNode  reason: not valid java name */
    public /* synthetic */ void m165lambda$transform$3$androidxcameracoreimagecaptureProcessingNode(InputPacket inputPacket) {
        if (inputPacket.getProcessingRequest().isAborted()) {
            Logger.w(TAG, "The postview image is closed due to request aborted");
            inputPacket.getImageProxy().close();
            return;
        }
        this.mBlockingExecutor.execute(new ProcessingNode$$ExternalSyntheticLambda4(this, inputPacket));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: processInputPacket */
    public void m162lambda$transform$0$androidxcameracoreimagecaptureProcessingNode(InputPacket inputPacket) {
        ProcessingRequest processingRequest = inputPacket.getProcessingRequest();
        try {
            boolean z = true;
            boolean z2 = this.mInputEdge.getOutputFormats().size() > 1;
            if (inputPacket.getProcessingRequest().isInMemoryCapture()) {
                CameraXExecutors.mainThreadExecutor().execute(new ProcessingNode$$ExternalSyntheticLambda6(processingRequest, processInMemoryCapture(inputPacket)));
                return;
            }
            ImageCapture.OutputFileResults processOnDiskCapture = processOnDiskCapture(inputPacket);
            if (z2) {
                if (!processingRequest.getTakePictureRequest().isFormatProcessedInSimultaneousCapture()) {
                    z = false;
                }
            }
            if (z) {
                CameraXExecutors.mainThreadExecutor().execute(new ProcessingNode$$ExternalSyntheticLambda7(processingRequest, processOnDiskCapture));
            }
        } catch (ImageCaptureException e) {
            sendError(processingRequest, e);
        } catch (OutOfMemoryError e2) {
            sendError(processingRequest, new ImageCaptureException(0, "Processing failed due to low memory.", e2));
        } catch (RuntimeException e3) {
            sendError(processingRequest, new ImageCaptureException(0, "Processing failed.", e3));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: processPostviewInputPacket */
    public void m164lambda$transform$2$androidxcameracoreimagecaptureProcessingNode(InputPacket inputPacket) {
        List<Integer> outputFormats = this.mInputEdge.getOutputFormats();
        boolean z = true;
        Preconditions.checkArgument(!outputFormats.isEmpty());
        int intValue = outputFormats.get(0).intValue();
        if (!(intValue == 35 || intValue == 256)) {
            z = false;
        }
        Preconditions.checkArgument(z, String.format("Postview only support YUV and JPEG output formats. Output format: %s", new Object[]{Integer.valueOf(intValue)}));
        try {
            CameraXExecutors.mainThreadExecutor().execute(new ProcessingNode$$ExternalSyntheticLambda0(inputPacket.getProcessingRequest(), this.mImage2Bitmap.apply(this.mInput2Packet.apply(inputPacket))));
        } catch (Exception e) {
            inputPacket.getImageProxy().close();
            Logger.e(TAG, "process postview input packet failed.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public ImageCapture.OutputFileResults processOnDiskCapture(InputPacket inputPacket) throws ImageCaptureException {
        List<Integer> outputFormats = this.mInputEdge.getOutputFormats();
        Preconditions.checkArgument(!outputFormats.isEmpty());
        boolean z = false;
        int intValue = outputFormats.get(0).intValue();
        Preconditions.checkArgument(ImageUtil.isJpegFormats(intValue) || ImageUtil.isRawFormats(intValue), String.format("On-disk capture only support JPEG and JPEG/R and RAW output formats. Output format: %s", new Object[]{Integer.valueOf(intValue)}));
        ProcessingRequest processingRequest = inputPacket.getProcessingRequest();
        Preconditions.checkArgument(processingRequest.getOutputFileOptions() != null, "OutputFileOptions cannot be empty");
        Packet apply = this.mInput2Packet.apply(inputPacket);
        if (outputFormats.size() > 1) {
            if (!(processingRequest.getOutputFileOptions() == null || processingRequest.getSecondaryOutputFileOptions() == null)) {
                z = true;
            }
            Preconditions.checkArgument(z, "The number of OutputFileOptions for simultaneous capture should be at least two");
            if (apply.getFormat() != 32) {
                ImageCapture.OutputFileResults saveJpegToDisk = saveJpegToDisk(apply, (ImageCapture.OutputFileOptions) Objects.requireNonNull(processingRequest.getSecondaryOutputFileOptions()), processingRequest.getJpegQuality());
                processingRequest.getTakePictureRequest().markFormatProcessStatusInSimultaneousCapture(256, true);
                return saveJpegToDisk;
            }
            ImageCapture.OutputFileResults saveRawToDisk = saveRawToDisk(apply, (ImageCapture.OutputFileOptions) Objects.requireNonNull(processingRequest.getOutputFileOptions()));
            processingRequest.getTakePictureRequest().markFormatProcessStatusInSimultaneousCapture(32, true);
            return saveRawToDisk;
        } else if (intValue != 32) {
            return saveJpegToDisk(apply, (ImageCapture.OutputFileOptions) Objects.requireNonNull(processingRequest.getOutputFileOptions()), processingRequest.getJpegQuality());
        } else {
            return saveRawToDisk(apply, (ImageCapture.OutputFileOptions) Objects.requireNonNull(processingRequest.getOutputFileOptions()));
        }
    }

    private ImageCapture.OutputFileResults saveRawToDisk(Packet<ImageProxy> packet, ImageCapture.OutputFileOptions outputFileOptions) throws ImageCaptureException {
        if (this.mDngImage2Disk == null) {
            if (this.mCameraCharacteristics == null) {
                throw new ImageCaptureException(0, "CameraCharacteristics is null, DngCreator cannot be created", (Throwable) null);
            } else if (packet.getCameraCaptureResult().getCaptureResult() != null) {
                this.mDngImage2Disk = new DngImage2Disk((CameraCharacteristics) Objects.requireNonNull(this.mCameraCharacteristics), (CaptureResult) Objects.requireNonNull(packet.getCameraCaptureResult().getCaptureResult()));
            } else {
                throw new ImageCaptureException(0, "CameraCaptureResult is null, DngCreator cannot be created", (Throwable) null);
            }
        }
        return this.mDngImage2Disk.apply(DngImage2Disk.In.of(packet.getData(), packet.getRotationDegrees(), (ImageCapture.OutputFileOptions) Objects.requireNonNull(outputFileOptions)));
    }

    private ImageCapture.OutputFileResults saveJpegToDisk(Packet<ImageProxy> packet, ImageCapture.OutputFileOptions outputFileOptions, int i) throws ImageCaptureException {
        Packet<byte[]> apply = this.mImage2JpegBytes.apply(Image2JpegBytes.In.of(packet, i));
        if (apply.hasCropping() || this.mBitmapEffect != null) {
            apply = cropAndMaybeApplyEffect(apply, i);
        }
        return this.mJpegBytes2Disk.apply(JpegBytes2Disk.In.of(apply, (ImageCapture.OutputFileOptions) Objects.requireNonNull(outputFileOptions)));
    }

    /* access modifiers changed from: package-private */
    public ImageProxy processInMemoryCapture(InputPacket inputPacket) throws ImageCaptureException {
        ProcessingRequest processingRequest = inputPacket.getProcessingRequest();
        Packet apply = this.mInput2Packet.apply(inputPacket);
        List<Integer> outputFormats = this.mInputEdge.getOutputFormats();
        Preconditions.checkArgument(!outputFormats.isEmpty());
        boolean z = false;
        int intValue = outputFormats.get(0).intValue();
        if ((apply.getFormat() == 35 || this.mBitmapEffect != null || this.mHasIncorrectJpegMetadataQuirk) && intValue == 256) {
            Packet<byte[]> apply2 = this.mImage2JpegBytes.apply(Image2JpegBytes.In.of(apply, processingRequest.getJpegQuality()));
            if (this.mBitmapEffect != null) {
                apply2 = cropAndMaybeApplyEffect(apply2, processingRequest.getJpegQuality());
            }
            apply = this.mJpegBytes2Image.apply(apply2);
        }
        ImageProxy apply3 = this.mJpegImage2Result.apply(apply);
        if (outputFormats.size() > 1) {
            z = true;
        }
        if (z) {
            processingRequest.getTakePictureRequest().markFormatProcessStatusInSimultaneousCapture(apply3.getFormat(), true);
        }
        return apply3;
    }

    private Packet<byte[]> cropAndMaybeApplyEffect(Packet<byte[]> packet, int i) throws ImageCaptureException {
        Preconditions.checkState(ImageUtil.isJpegFormats(packet.getFormat()));
        Packet apply = this.mJpegBytes2CroppedBitmap.apply(packet);
        Operation<Packet<Bitmap>, Packet<Bitmap>> operation = this.mBitmapEffect;
        if (operation != null) {
            apply = operation.apply(apply);
        }
        return this.mBitmap2JpegBytes.apply(Bitmap2JpegBytes.In.of(apply, i));
    }

    private void sendError(ProcessingRequest processingRequest, ImageCaptureException imageCaptureException) {
        CameraXExecutors.mainThreadExecutor().execute(new ProcessingNode$$ExternalSyntheticLambda3(processingRequest, imageCaptureException));
    }

    static abstract class InputPacket {
        /* access modifiers changed from: package-private */
        public abstract ImageProxy getImageProxy();

        /* access modifiers changed from: package-private */
        public abstract ProcessingRequest getProcessingRequest();

        InputPacket() {
        }

        static InputPacket of(ProcessingRequest processingRequest, ImageProxy imageProxy) {
            return new AutoValue_ProcessingNode_InputPacket(processingRequest, imageProxy);
        }
    }

    static abstract class In {
        /* access modifiers changed from: package-private */
        public abstract Edge<InputPacket> getEdge();

        /* access modifiers changed from: package-private */
        public abstract int getInputFormat();

        /* access modifiers changed from: package-private */
        public abstract List<Integer> getOutputFormats();

        /* access modifiers changed from: package-private */
        public abstract Edge<InputPacket> getPostviewEdge();

        In() {
        }

        static In of(int i, List<Integer> list) {
            return new AutoValue_ProcessingNode_In(new Edge(), new Edge(), i, list);
        }
    }

    /* access modifiers changed from: package-private */
    public void injectProcessingInput2Packet(Operation<InputPacket, Packet<ImageProxy>> operation) {
        this.mInput2Packet = operation;
    }
}
