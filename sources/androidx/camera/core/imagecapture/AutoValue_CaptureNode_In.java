package androidx.camera.core.imagecapture;

import android.util.Size;
import androidx.camera.core.ImageReaderProxyProvider;
import androidx.camera.core.imagecapture.CaptureNode;
import androidx.camera.core.imagecapture.TakePictureManager;
import androidx.camera.core.processing.Edge;
import java.util.List;

final class AutoValue_CaptureNode_In extends CaptureNode.In {
    private final Edge<TakePictureManager.CaptureError> errorEdge;
    private final ImageReaderProxyProvider imageReaderProxyProvider;
    private final int inputFormat;
    private final List<Integer> outputFormats;
    private final int postviewImageFormat;
    private final Size postviewSize;
    private final Edge<ProcessingRequest> requestEdge;
    private final Size size;
    private final boolean virtualCamera;

    AutoValue_CaptureNode_In(Size size2, int i, List<Integer> list, boolean z, ImageReaderProxyProvider imageReaderProxyProvider2, Size size3, int i2, Edge<ProcessingRequest> edge, Edge<TakePictureManager.CaptureError> edge2) {
        if (size2 != null) {
            this.size = size2;
            this.inputFormat = i;
            if (list != null) {
                this.outputFormats = list;
                this.virtualCamera = z;
                this.imageReaderProxyProvider = imageReaderProxyProvider2;
                this.postviewSize = size3;
                this.postviewImageFormat = i2;
                if (edge != null) {
                    this.requestEdge = edge;
                    if (edge2 != null) {
                        this.errorEdge = edge2;
                        return;
                    }
                    throw new NullPointerException("Null errorEdge");
                }
                throw new NullPointerException("Null requestEdge");
            }
            throw new NullPointerException("Null outputFormats");
        }
        throw new NullPointerException("Null size");
    }

    /* access modifiers changed from: package-private */
    public Size getSize() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public int getInputFormat() {
        return this.inputFormat;
    }

    /* access modifiers changed from: package-private */
    public List<Integer> getOutputFormats() {
        return this.outputFormats;
    }

    /* access modifiers changed from: package-private */
    public boolean isVirtualCamera() {
        return this.virtualCamera;
    }

    /* access modifiers changed from: package-private */
    public ImageReaderProxyProvider getImageReaderProxyProvider() {
        return this.imageReaderProxyProvider;
    }

    /* access modifiers changed from: package-private */
    public Size getPostviewSize() {
        return this.postviewSize;
    }

    /* access modifiers changed from: package-private */
    public int getPostviewImageFormat() {
        return this.postviewImageFormat;
    }

    /* access modifiers changed from: package-private */
    public Edge<ProcessingRequest> getRequestEdge() {
        return this.requestEdge;
    }

    /* access modifiers changed from: package-private */
    public Edge<TakePictureManager.CaptureError> getErrorEdge() {
        return this.errorEdge;
    }

    public String toString() {
        return "In{size=" + this.size + ", inputFormat=" + this.inputFormat + ", outputFormats=" + this.outputFormats + ", virtualCamera=" + this.virtualCamera + ", imageReaderProxyProvider=" + this.imageReaderProxyProvider + ", postviewSize=" + this.postviewSize + ", postviewImageFormat=" + this.postviewImageFormat + ", requestEdge=" + this.requestEdge + ", errorEdge=" + this.errorEdge + "}";
    }

    public boolean equals(Object obj) {
        ImageReaderProxyProvider imageReaderProxyProvider2;
        Size size2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CaptureNode.In)) {
            return false;
        }
        CaptureNode.In in = (CaptureNode.In) obj;
        if (!this.size.equals(in.getSize()) || this.inputFormat != in.getInputFormat() || !this.outputFormats.equals(in.getOutputFormats()) || this.virtualCamera != in.isVirtualCamera() || ((imageReaderProxyProvider2 = this.imageReaderProxyProvider) != null ? !imageReaderProxyProvider2.equals(in.getImageReaderProxyProvider()) : in.getImageReaderProxyProvider() != null) || ((size2 = this.postviewSize) != null ? !size2.equals(in.getPostviewSize()) : in.getPostviewSize() != null) || this.postviewImageFormat != in.getPostviewImageFormat() || !this.requestEdge.equals(in.getRequestEdge()) || !this.errorEdge.equals(in.getErrorEdge())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (((((((this.size.hashCode() ^ 1000003) * 1000003) ^ this.inputFormat) * 1000003) ^ this.outputFormats.hashCode()) * 1000003) ^ (this.virtualCamera ? 1231 : 1237)) * 1000003;
        ImageReaderProxyProvider imageReaderProxyProvider2 = this.imageReaderProxyProvider;
        int i = 0;
        int hashCode2 = (hashCode ^ (imageReaderProxyProvider2 == null ? 0 : imageReaderProxyProvider2.hashCode())) * 1000003;
        Size size2 = this.postviewSize;
        if (size2 != null) {
            i = size2.hashCode();
        }
        return ((((((hashCode2 ^ i) * 1000003) ^ this.postviewImageFormat) * 1000003) ^ this.requestEdge.hashCode()) * 1000003) ^ this.errorEdge.hashCode();
    }
}
