package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageProxy;
import androidx.camera.core.imagecapture.Image2JpegBytes;
import androidx.camera.core.processing.Packet;

final class AutoValue_Image2JpegBytes_In extends Image2JpegBytes.In {
    private final int jpegQuality;
    private final Packet<ImageProxy> packet;

    AutoValue_Image2JpegBytes_In(Packet<ImageProxy> packet2, int i) {
        if (packet2 != null) {
            this.packet = packet2;
            this.jpegQuality = i;
            return;
        }
        throw new NullPointerException("Null packet");
    }

    /* access modifiers changed from: package-private */
    public Packet<ImageProxy> getPacket() {
        return this.packet;
    }

    /* access modifiers changed from: package-private */
    public int getJpegQuality() {
        return this.jpegQuality;
    }

    public String toString() {
        return "In{packet=" + this.packet + ", jpegQuality=" + this.jpegQuality + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Image2JpegBytes.In)) {
            return false;
        }
        Image2JpegBytes.In in = (Image2JpegBytes.In) obj;
        if (!this.packet.equals(in.getPacket()) || this.jpegQuality != in.getJpegQuality()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.packet.hashCode() ^ 1000003) * 1000003) ^ this.jpegQuality;
    }
}
