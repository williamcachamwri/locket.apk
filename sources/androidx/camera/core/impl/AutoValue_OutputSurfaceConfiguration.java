package androidx.camera.core.impl;

final class AutoValue_OutputSurfaceConfiguration extends OutputSurfaceConfiguration {
    private final OutputSurface imageAnalysisOutputSurface;
    private final OutputSurface imageCaptureOutputSurface;
    private final OutputSurface postviewOutputSurface;
    private final OutputSurface previewOutputSurface;

    AutoValue_OutputSurfaceConfiguration(OutputSurface outputSurface, OutputSurface outputSurface2, OutputSurface outputSurface3, OutputSurface outputSurface4) {
        if (outputSurface != null) {
            this.previewOutputSurface = outputSurface;
            if (outputSurface2 != null) {
                this.imageCaptureOutputSurface = outputSurface2;
                this.imageAnalysisOutputSurface = outputSurface3;
                this.postviewOutputSurface = outputSurface4;
                return;
            }
            throw new NullPointerException("Null imageCaptureOutputSurface");
        }
        throw new NullPointerException("Null previewOutputSurface");
    }

    public OutputSurface getPreviewOutputSurface() {
        return this.previewOutputSurface;
    }

    public OutputSurface getImageCaptureOutputSurface() {
        return this.imageCaptureOutputSurface;
    }

    public OutputSurface getImageAnalysisOutputSurface() {
        return this.imageAnalysisOutputSurface;
    }

    public OutputSurface getPostviewOutputSurface() {
        return this.postviewOutputSurface;
    }

    public String toString() {
        return "OutputSurfaceConfiguration{previewOutputSurface=" + this.previewOutputSurface + ", imageCaptureOutputSurface=" + this.imageCaptureOutputSurface + ", imageAnalysisOutputSurface=" + this.imageAnalysisOutputSurface + ", postviewOutputSurface=" + this.postviewOutputSurface + "}";
    }

    public boolean equals(Object obj) {
        OutputSurface outputSurface;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OutputSurfaceConfiguration)) {
            return false;
        }
        OutputSurfaceConfiguration outputSurfaceConfiguration = (OutputSurfaceConfiguration) obj;
        if (this.previewOutputSurface.equals(outputSurfaceConfiguration.getPreviewOutputSurface()) && this.imageCaptureOutputSurface.equals(outputSurfaceConfiguration.getImageCaptureOutputSurface()) && ((outputSurface = this.imageAnalysisOutputSurface) != null ? outputSurface.equals(outputSurfaceConfiguration.getImageAnalysisOutputSurface()) : outputSurfaceConfiguration.getImageAnalysisOutputSurface() == null)) {
            OutputSurface outputSurface2 = this.postviewOutputSurface;
            if (outputSurface2 == null) {
                if (outputSurfaceConfiguration.getPostviewOutputSurface() == null) {
                    return true;
                }
            } else if (outputSurface2.equals(outputSurfaceConfiguration.getPostviewOutputSurface())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.previewOutputSurface.hashCode() ^ 1000003) * 1000003) ^ this.imageCaptureOutputSurface.hashCode()) * 1000003;
        OutputSurface outputSurface = this.imageAnalysisOutputSurface;
        int i = 0;
        int hashCode2 = (hashCode ^ (outputSurface == null ? 0 : outputSurface.hashCode())) * 1000003;
        OutputSurface outputSurface2 = this.postviewOutputSurface;
        if (outputSurface2 != null) {
            i = outputSurface2.hashCode();
        }
        return hashCode2 ^ i;
    }
}
