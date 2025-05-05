package androidx.camera.core.processing.util;

import android.opengl.EGLSurface;

final class AutoValue_OutputSurface extends OutputSurface {
    private final EGLSurface eglSurface;
    private final int height;
    private final int width;

    AutoValue_OutputSurface(EGLSurface eGLSurface, int i, int i2) {
        if (eGLSurface != null) {
            this.eglSurface = eGLSurface;
            this.width = i;
            this.height = i2;
            return;
        }
        throw new NullPointerException("Null eglSurface");
    }

    public EGLSurface getEglSurface() {
        return this.eglSurface;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String toString() {
        return "OutputSurface{eglSurface=" + this.eglSurface + ", width=" + this.width + ", height=" + this.height + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OutputSurface)) {
            return false;
        }
        OutputSurface outputSurface = (OutputSurface) obj;
        if (this.eglSurface.equals(outputSurface.getEglSurface()) && this.width == outputSurface.getWidth() && this.height == outputSurface.getHeight()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.eglSurface.hashCode() ^ 1000003) * 1000003) ^ this.width) * 1000003) ^ this.height;
    }
}
