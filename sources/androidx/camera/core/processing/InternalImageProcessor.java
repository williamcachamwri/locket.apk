package androidx.camera.core.processing;

import androidx.camera.core.CameraEffect;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProcessor;
import androidx.camera.core.ProcessingException;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import java.util.Objects;
import java.util.concurrent.Executor;

public class InternalImageProcessor {
    private final Consumer<Throwable> mErrorListener;
    private final Executor mExecutor;
    private final ImageProcessor mImageProcessor;

    public InternalImageProcessor(CameraEffect cameraEffect) {
        Preconditions.checkArgument(cameraEffect.getTargets() == 4);
        this.mExecutor = cameraEffect.getExecutor();
        this.mImageProcessor = (ImageProcessor) Objects.requireNonNull(cameraEffect.getImageProcessor());
        this.mErrorListener = cameraEffect.getErrorListener();
    }

    public ImageProcessor.Response safeProcess(ImageProcessor.Request request) throws ImageCaptureException {
        try {
            return (ImageProcessor.Response) CallbackToFutureAdapter.getFuture(new InternalImageProcessor$$ExternalSyntheticLambda1(this, request)).get();
        } catch (Exception e) {
            Throwable cause = e.getCause();
            Throwable th = e;
            if (cause != null) {
                th = e.getCause();
            }
            throw new ImageCaptureException(0, "Failed to invoke ImageProcessor.", th);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$safeProcess$1$androidx-camera-core-processing-InternalImageProcessor  reason: not valid java name */
    public /* synthetic */ Object m201lambda$safeProcess$1$androidxcameracoreprocessingInternalImageProcessor(ImageProcessor.Request request, CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new InternalImageProcessor$$ExternalSyntheticLambda0(this, request, completer));
        return "InternalImageProcessor#process " + request.hashCode();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$safeProcess$0$androidx-camera-core-processing-InternalImageProcessor  reason: not valid java name */
    public /* synthetic */ void m200lambda$safeProcess$0$androidxcameracoreprocessingInternalImageProcessor(ImageProcessor.Request request, CallbackToFutureAdapter.Completer completer) {
        try {
            completer.set(this.mImageProcessor.process(request));
        } catch (ProcessingException e) {
            this.mErrorListener.accept(e);
            completer.setException(e);
        }
    }
}
