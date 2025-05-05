package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageCapture;
import androidx.camera.core.imagecapture.JpegBytes2Disk;
import androidx.camera.core.processing.Packet;

final class AutoValue_JpegBytes2Disk_In extends JpegBytes2Disk.In {
    private final ImageCapture.OutputFileOptions outputFileOptions;
    private final Packet<byte[]> packet;

    AutoValue_JpegBytes2Disk_In(Packet<byte[]> packet2, ImageCapture.OutputFileOptions outputFileOptions2) {
        if (packet2 != null) {
            this.packet = packet2;
            if (outputFileOptions2 != null) {
                this.outputFileOptions = outputFileOptions2;
                return;
            }
            throw new NullPointerException("Null outputFileOptions");
        }
        throw new NullPointerException("Null packet");
    }

    /* access modifiers changed from: package-private */
    public Packet<byte[]> getPacket() {
        return this.packet;
    }

    /* access modifiers changed from: package-private */
    public ImageCapture.OutputFileOptions getOutputFileOptions() {
        return this.outputFileOptions;
    }

    public String toString() {
        return "In{packet=" + this.packet + ", outputFileOptions=" + this.outputFileOptions + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JpegBytes2Disk.In)) {
            return false;
        }
        JpegBytes2Disk.In in = (JpegBytes2Disk.In) obj;
        if (!this.packet.equals(in.getPacket()) || !this.outputFileOptions.equals(in.getOutputFileOptions())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.packet.hashCode() ^ 1000003) * 1000003) ^ this.outputFileOptions.hashCode();
    }
}
