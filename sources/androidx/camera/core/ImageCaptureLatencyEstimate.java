package androidx.camera.core;

import java.util.Objects;

public class ImageCaptureLatencyEstimate {
    public static final long UNDEFINED_CAPTURE_LATENCY = -1;
    public static final ImageCaptureLatencyEstimate UNDEFINED_IMAGE_CAPTURE_LATENCY = new ImageCaptureLatencyEstimate(-1, -1);
    public static final long UNDEFINED_PROCESSING_LATENCY = -1;
    private final long mCaptureLatencyMillis;
    private final long mProcessingLatencyMillis;
    private final long mTotalCaptureLatencyMillis;

    private long computeTotalCaptureLatencyMillis(long j, long j2) {
        if (j == -1 || j2 == -1) {
            return -1;
        }
        return j + j2;
    }

    public ImageCaptureLatencyEstimate(long j, long j2) {
        this.mCaptureLatencyMillis = j;
        this.mProcessingLatencyMillis = j2;
        this.mTotalCaptureLatencyMillis = computeTotalCaptureLatencyMillis(j, j2);
    }

    public long getCaptureLatencyMillis() {
        return this.mCaptureLatencyMillis;
    }

    public long getProcessingLatencyMillis() {
        return this.mProcessingLatencyMillis;
    }

    public long getTotalCaptureLatencyMillis() {
        return this.mTotalCaptureLatencyMillis;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageCaptureLatencyEstimate)) {
            return false;
        }
        ImageCaptureLatencyEstimate imageCaptureLatencyEstimate = (ImageCaptureLatencyEstimate) obj;
        if (this.mCaptureLatencyMillis == imageCaptureLatencyEstimate.getCaptureLatencyMillis() && this.mProcessingLatencyMillis == imageCaptureLatencyEstimate.getProcessingLatencyMillis() && this.mTotalCaptureLatencyMillis == imageCaptureLatencyEstimate.getTotalCaptureLatencyMillis()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Long.valueOf(this.mCaptureLatencyMillis), Long.valueOf(this.mProcessingLatencyMillis), Long.valueOf(this.mTotalCaptureLatencyMillis)});
    }

    public String toString() {
        return "captureLatencyMillis=" + this.mCaptureLatencyMillis + ", processingLatencyMillis=" + this.mProcessingLatencyMillis + ", totalCaptureLatencyMillis=" + this.mTotalCaptureLatencyMillis;
    }
}
