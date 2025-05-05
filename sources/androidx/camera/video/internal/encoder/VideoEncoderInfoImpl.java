package androidx.camera.video.internal.encoder;

import android.media.MediaCodecInfo;
import android.util.Range;
import android.util.Size;
import androidx.arch.core.util.Function;
import androidx.camera.core.Logger;
import androidx.camera.video.internal.utils.CodecUtil;
import androidx.camera.video.internal.workaround.VideoEncoderInfoWrapper;
import java.util.Objects;

public class VideoEncoderInfoImpl extends EncoderInfoImpl implements VideoEncoderInfo {
    public static final Function<VideoEncoderConfig, VideoEncoderInfo> FINDER = new VideoEncoderInfoImpl$$ExternalSyntheticLambda0();
    private static final String TAG = "VideoEncoderInfoImpl";
    private final MediaCodecInfo.VideoCapabilities mVideoCapabilities = ((MediaCodecInfo.VideoCapabilities) Objects.requireNonNull(this.mCodecCapabilities.getVideoCapabilities()));

    public boolean canSwapWidthHeight() {
        return true;
    }

    static /* synthetic */ VideoEncoderInfo lambda$static$0(VideoEncoderConfig videoEncoderConfig) {
        try {
            return VideoEncoderInfoWrapper.from(from(videoEncoderConfig), (Size) null);
        } catch (InvalidConfigException e) {
            Logger.w(TAG, "Unable to find a VideoEncoderInfoImpl", e);
            return null;
        }
    }

    public static VideoEncoderInfoImpl from(VideoEncoderConfig videoEncoderConfig) throws InvalidConfigException {
        return new VideoEncoderInfoImpl(CodecUtil.findCodecAndGetCodecInfo(videoEncoderConfig), videoEncoderConfig.getMimeType());
    }

    VideoEncoderInfoImpl(MediaCodecInfo mediaCodecInfo, String str) throws InvalidConfigException {
        super(mediaCodecInfo, str);
    }

    public boolean isSizeSupported(int i, int i2) {
        return this.mVideoCapabilities.isSizeSupported(i, i2);
    }

    public Range<Integer> getSupportedWidths() {
        return this.mVideoCapabilities.getSupportedWidths();
    }

    public Range<Integer> getSupportedHeights() {
        return this.mVideoCapabilities.getSupportedHeights();
    }

    public Range<Integer> getSupportedWidthsFor(int i) {
        try {
            return this.mVideoCapabilities.getSupportedWidthsFor(i);
        } catch (Throwable th) {
            throw toIllegalArgumentException(th);
        }
    }

    public Range<Integer> getSupportedHeightsFor(int i) {
        try {
            return this.mVideoCapabilities.getSupportedHeightsFor(i);
        } catch (Throwable th) {
            throw toIllegalArgumentException(th);
        }
    }

    public int getWidthAlignment() {
        return this.mVideoCapabilities.getWidthAlignment();
    }

    public int getHeightAlignment() {
        return this.mVideoCapabilities.getHeightAlignment();
    }

    public Range<Integer> getSupportedBitrateRange() {
        return this.mVideoCapabilities.getBitrateRange();
    }

    private static IllegalArgumentException toIllegalArgumentException(Throwable th) {
        if (th instanceof IllegalArgumentException) {
            return (IllegalArgumentException) th;
        }
        return new IllegalArgumentException(th);
    }
}
