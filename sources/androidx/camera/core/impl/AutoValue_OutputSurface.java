package androidx.camera.core.impl;

import android.util.Size;
import android.view.Surface;

final class AutoValue_OutputSurface extends OutputSurface {
    private final int imageFormat;
    private final Size size;
    private final Surface surface;

    AutoValue_OutputSurface(Surface surface2, Size size2, int i) {
        if (surface2 != null) {
            this.surface = surface2;
            if (size2 != null) {
                this.size = size2;
                this.imageFormat = i;
                return;
            }
            throw new NullPointerException("Null size");
        }
        throw new NullPointerException("Null surface");
    }

    public Surface getSurface() {
        return this.surface;
    }

    public Size getSize() {
        return this.size;
    }

    public int getImageFormat() {
        return this.imageFormat;
    }

    public String toString() {
        return "OutputSurface{surface=" + this.surface + ", size=" + this.size + ", imageFormat=" + this.imageFormat + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OutputSurface)) {
            return false;
        }
        OutputSurface outputSurface = (OutputSurface) obj;
        if (!this.surface.equals(outputSurface.getSurface()) || !this.size.equals(outputSurface.getSize()) || this.imageFormat != outputSurface.getImageFormat()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((this.surface.hashCode() ^ 1000003) * 1000003) ^ this.size.hashCode()) * 1000003) ^ this.imageFormat;
    }
}
