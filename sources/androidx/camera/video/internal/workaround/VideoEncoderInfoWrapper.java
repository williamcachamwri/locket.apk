package androidx.camera.video.internal.workaround;

import android.util.Range;
import android.util.Size;
import androidx.camera.core.Logger;
import androidx.camera.video.internal.compat.quirk.DeviceQuirks;
import androidx.camera.video.internal.compat.quirk.MediaCodecInfoReportIncorrectInfoQuirk;
import androidx.camera.video.internal.encoder.VideoEncoderInfo;
import androidx.core.util.Preconditions;
import java.util.HashSet;
import java.util.Set;

public class VideoEncoderInfoWrapper implements VideoEncoderInfo {
    private static final int HEIGHT_4KDCI = 2160;
    private static final String TAG = "VideoEncoderInfoWrapper";
    private static final int WIDTH_4KDCI = 4096;
    private final Set<Size> mExtraSupportedSizes;
    private final Range<Integer> mSupportedHeights;
    private final Range<Integer> mSupportedWidths;
    private final VideoEncoderInfo mVideoEncoderInfo;

    public static VideoEncoderInfo from(VideoEncoderInfo videoEncoderInfo, Size size) {
        boolean z = false;
        if (!(videoEncoderInfo instanceof VideoEncoderInfoWrapper)) {
            if (DeviceQuirks.get(MediaCodecInfoReportIncorrectInfoQuirk.class) == null) {
                if (size != null && !videoEncoderInfo.isSizeSupportedAllowSwapping(size.getWidth(), size.getHeight())) {
                    Logger.w(TAG, String.format("Detected that the device does not support a size %s that should be valid in widths/heights = %s/%s", new Object[]{size, videoEncoderInfo.getSupportedWidths(), videoEncoderInfo.getSupportedHeights()}));
                }
            }
            z = true;
        }
        if (z) {
            videoEncoderInfo = new VideoEncoderInfoWrapper(videoEncoderInfo);
        }
        if (size != null && (videoEncoderInfo instanceof VideoEncoderInfoWrapper)) {
            ((VideoEncoderInfoWrapper) videoEncoderInfo).addExtraSupportedSize(size);
        }
        return videoEncoderInfo;
    }

    private VideoEncoderInfoWrapper(VideoEncoderInfo videoEncoderInfo) {
        HashSet hashSet = new HashSet();
        this.mExtraSupportedSizes = hashSet;
        this.mVideoEncoderInfo = videoEncoderInfo;
        int widthAlignment = videoEncoderInfo.getWidthAlignment();
        this.mSupportedWidths = Range.create(Integer.valueOf(widthAlignment), Integer.valueOf(((int) Math.ceil(4096.0d / ((double) widthAlignment))) * widthAlignment));
        int heightAlignment = videoEncoderInfo.getHeightAlignment();
        this.mSupportedHeights = Range.create(Integer.valueOf(heightAlignment), Integer.valueOf(((int) Math.ceil(2160.0d / ((double) heightAlignment))) * heightAlignment));
        hashSet.addAll(MediaCodecInfoReportIncorrectInfoQuirk.getExtraSupportedSizes());
    }

    public String getName() {
        return this.mVideoEncoderInfo.getName();
    }

    public boolean canSwapWidthHeight() {
        return this.mVideoEncoderInfo.canSwapWidthHeight();
    }

    public boolean isSizeSupported(int i, int i2) {
        if (this.mVideoEncoderInfo.isSizeSupported(i, i2)) {
            return true;
        }
        for (Size next : this.mExtraSupportedSizes) {
            if (next.getWidth() == i && next.getHeight() == i2) {
                return true;
            }
        }
        if (!this.mSupportedWidths.contains(Integer.valueOf(i)) || !this.mSupportedHeights.contains(Integer.valueOf(i2)) || i % this.mVideoEncoderInfo.getWidthAlignment() != 0 || i2 % this.mVideoEncoderInfo.getHeightAlignment() != 0) {
            return false;
        }
        return true;
    }

    public Range<Integer> getSupportedWidths() {
        return this.mSupportedWidths;
    }

    public Range<Integer> getSupportedHeights() {
        return this.mSupportedHeights;
    }

    public Range<Integer> getSupportedWidthsFor(int i) {
        Preconditions.checkArgument(this.mSupportedHeights.contains(Integer.valueOf(i)) && i % this.mVideoEncoderInfo.getHeightAlignment() == 0, "Not supported height: " + i + " which is not in " + this.mSupportedHeights + " or can not be divided by alignment " + this.mVideoEncoderInfo.getHeightAlignment());
        return this.mSupportedWidths;
    }

    public Range<Integer> getSupportedHeightsFor(int i) {
        Preconditions.checkArgument(this.mSupportedWidths.contains(Integer.valueOf(i)) && i % this.mVideoEncoderInfo.getWidthAlignment() == 0, "Not supported width: " + i + " which is not in " + this.mSupportedWidths + " or can not be divided by alignment " + this.mVideoEncoderInfo.getWidthAlignment());
        return this.mSupportedHeights;
    }

    public int getWidthAlignment() {
        return this.mVideoEncoderInfo.getWidthAlignment();
    }

    public int getHeightAlignment() {
        return this.mVideoEncoderInfo.getHeightAlignment();
    }

    public Range<Integer> getSupportedBitrateRange() {
        return this.mVideoEncoderInfo.getSupportedBitrateRange();
    }

    private void addExtraSupportedSize(Size size) {
        this.mExtraSupportedSizes.add(size);
    }
}
