package androidx.camera.video.internal.encoder;

import android.util.Range;
import androidx.core.util.Preconditions;

public class SwappedVideoEncoderInfo implements VideoEncoderInfo {
    private final VideoEncoderInfo mVideoEncoderInfo;

    public SwappedVideoEncoderInfo(VideoEncoderInfo videoEncoderInfo) {
        Preconditions.checkArgument(videoEncoderInfo.canSwapWidthHeight());
        this.mVideoEncoderInfo = videoEncoderInfo;
    }

    public String getName() {
        return this.mVideoEncoderInfo.getName();
    }

    public boolean canSwapWidthHeight() {
        return this.mVideoEncoderInfo.canSwapWidthHeight();
    }

    public boolean isSizeSupported(int i, int i2) {
        return this.mVideoEncoderInfo.isSizeSupported(i2, i);
    }

    public Range<Integer> getSupportedWidths() {
        return this.mVideoEncoderInfo.getSupportedHeights();
    }

    public Range<Integer> getSupportedHeights() {
        return this.mVideoEncoderInfo.getSupportedWidths();
    }

    public Range<Integer> getSupportedWidthsFor(int i) {
        return this.mVideoEncoderInfo.getSupportedHeightsFor(i);
    }

    public Range<Integer> getSupportedHeightsFor(int i) {
        return this.mVideoEncoderInfo.getSupportedWidthsFor(i);
    }

    public int getWidthAlignment() {
        return this.mVideoEncoderInfo.getHeightAlignment();
    }

    public int getHeightAlignment() {
        return this.mVideoEncoderInfo.getWidthAlignment();
    }

    public Range<Integer> getSupportedBitrateRange() {
        return this.mVideoEncoderInfo.getSupportedBitrateRange();
    }
}
