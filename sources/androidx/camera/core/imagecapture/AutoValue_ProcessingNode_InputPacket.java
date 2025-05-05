package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageProxy;
import androidx.camera.core.imagecapture.ProcessingNode;

final class AutoValue_ProcessingNode_InputPacket extends ProcessingNode.InputPacket {
    private final ImageProxy imageProxy;
    private final ProcessingRequest processingRequest;

    AutoValue_ProcessingNode_InputPacket(ProcessingRequest processingRequest2, ImageProxy imageProxy2) {
        if (processingRequest2 != null) {
            this.processingRequest = processingRequest2;
            if (imageProxy2 != null) {
                this.imageProxy = imageProxy2;
                return;
            }
            throw new NullPointerException("Null imageProxy");
        }
        throw new NullPointerException("Null processingRequest");
    }

    /* access modifiers changed from: package-private */
    public ProcessingRequest getProcessingRequest() {
        return this.processingRequest;
    }

    /* access modifiers changed from: package-private */
    public ImageProxy getImageProxy() {
        return this.imageProxy;
    }

    public String toString() {
        return "InputPacket{processingRequest=" + this.processingRequest + ", imageProxy=" + this.imageProxy + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProcessingNode.InputPacket)) {
            return false;
        }
        ProcessingNode.InputPacket inputPacket = (ProcessingNode.InputPacket) obj;
        if (!this.processingRequest.equals(inputPacket.getProcessingRequest()) || !this.imageProxy.equals(inputPacket.getImageProxy())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.processingRequest.hashCode() ^ 1000003) * 1000003) ^ this.imageProxy.hashCode();
    }
}
