package androidx.camera.core.imagecapture;

import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.SettableImageProxy;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.TagBundle;
import androidx.camera.core.internal.CameraCaptureResultImageInfo;
import androidx.camera.core.streamsharing.VirtualCameraCaptureResult;
import androidx.core.util.Preconditions;
import java.util.concurrent.Executor;

public class NoMetadataImageReader implements ImageReaderProxy {
    private ProcessingRequest mPendingRequest;
    private final ImageReaderProxy mWrappedImageReader;

    NoMetadataImageReader(ImageReaderProxy imageReaderProxy) {
        this.mWrappedImageReader = imageReaderProxy;
    }

    /* access modifiers changed from: package-private */
    public void acceptProcessingRequest(ProcessingRequest processingRequest) {
        Preconditions.checkState(this.mPendingRequest == null, "Pending request should be null");
        this.mPendingRequest = processingRequest;
    }

    /* access modifiers changed from: package-private */
    public void clearProcessingRequest() {
        this.mPendingRequest = null;
    }

    public ImageProxy acquireLatestImage() {
        return createImageProxyWithEmptyMetadata(this.mWrappedImageReader.acquireLatestImage());
    }

    public ImageProxy acquireNextImage() {
        return createImageProxyWithEmptyMetadata(this.mWrappedImageReader.acquireNextImage());
    }

    public void close() {
        this.mWrappedImageReader.close();
    }

    public int getHeight() {
        return this.mWrappedImageReader.getHeight();
    }

    public int getWidth() {
        return this.mWrappedImageReader.getWidth();
    }

    public int getImageFormat() {
        return this.mWrappedImageReader.getImageFormat();
    }

    public int getMaxImages() {
        return this.mWrappedImageReader.getMaxImages();
    }

    public Surface getSurface() {
        return this.mWrappedImageReader.getSurface();
    }

    public void setOnImageAvailableListener(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, Executor executor) {
        this.mWrappedImageReader.setOnImageAvailableListener(new NoMetadataImageReader$$ExternalSyntheticLambda0(this, onImageAvailableListener), executor);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setOnImageAvailableListener$0$androidx-camera-core-imagecapture-NoMetadataImageReader  reason: not valid java name */
    public /* synthetic */ void m161lambda$setOnImageAvailableListener$0$androidxcameracoreimagecaptureNoMetadataImageReader(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, ImageReaderProxy imageReaderProxy) {
        onImageAvailableListener.onImageAvailable(this);
    }

    public void clearOnImageAvailableListener() {
        this.mWrappedImageReader.clearOnImageAvailableListener();
    }

    private ImageProxy createImageProxyWithEmptyMetadata(ImageProxy imageProxy) {
        TagBundle tagBundle;
        if (imageProxy == null) {
            return null;
        }
        if (this.mPendingRequest == null) {
            tagBundle = TagBundle.emptyBundle();
        } else {
            tagBundle = TagBundle.create(new Pair(this.mPendingRequest.getTagBundleKey(), this.mPendingRequest.getStageIds().get(0)));
        }
        this.mPendingRequest = null;
        return new SettableImageProxy(imageProxy, new Size(imageProxy.getWidth(), imageProxy.getHeight()), new CameraCaptureResultImageInfo(new VirtualCameraCaptureResult(tagBundle, imageProxy.getImageInfo().getTimestamp())));
    }
}
