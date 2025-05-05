package androidx.camera.video.internal.config;

import android.util.Range;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.core.impl.Timebase;
import androidx.camera.video.MediaSpec;
import androidx.camera.video.VideoSpec;
import androidx.camera.video.internal.VideoValidatedEncoderProfilesProxy;
import androidx.camera.video.internal.config.VideoMimeInfo;
import androidx.camera.video.internal.encoder.VideoEncoderConfig;
import androidx.camera.video.internal.encoder.VideoEncoderDataSpace;
import androidx.camera.video.internal.utils.DynamicRangeUtil;
import androidx.core.util.Preconditions;
import androidx.media3.common.MimeTypes;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class VideoConfigUtil {
    private static final Timebase DEFAULT_TIME_BASE = Timebase.UPTIME;
    private static final Map<String, Map<Integer, VideoEncoderDataSpace>> MIME_TO_DATA_SPACE_MAP;
    private static final String TAG = "VideoConfigUtil";

    static {
        HashMap hashMap = new HashMap();
        MIME_TO_DATA_SPACE_MAP = hashMap;
        HashMap hashMap2 = new HashMap();
        hashMap2.put(1, VideoEncoderDataSpace.ENCODER_DATA_SPACE_UNSPECIFIED);
        hashMap2.put(2, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT2020_HLG);
        hashMap2.put(4096, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT2020_PQ);
        hashMap2.put(8192, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT2020_PQ);
        HashMap hashMap3 = new HashMap();
        hashMap3.put(1, VideoEncoderDataSpace.ENCODER_DATA_SPACE_UNSPECIFIED);
        hashMap3.put(2, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT2020_HLG);
        hashMap3.put(4096, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT2020_PQ);
        hashMap3.put(8192, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT2020_PQ);
        HashMap hashMap4 = new HashMap();
        hashMap4.put(1, VideoEncoderDataSpace.ENCODER_DATA_SPACE_UNSPECIFIED);
        hashMap4.put(4, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT2020_HLG);
        hashMap4.put(4096, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT2020_PQ);
        hashMap4.put(16384, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT2020_PQ);
        hashMap4.put(2, VideoEncoderDataSpace.ENCODER_DATA_SPACE_UNSPECIFIED);
        hashMap4.put(8, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT2020_HLG);
        hashMap4.put(8192, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT2020_PQ);
        hashMap4.put(32768, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT2020_PQ);
        HashMap hashMap5 = new HashMap();
        hashMap5.put(256, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT2020_HLG);
        hashMap5.put(512, VideoEncoderDataSpace.ENCODER_DATA_SPACE_BT709);
        hashMap.put(MimeTypes.VIDEO_H265, hashMap2);
        hashMap.put(MimeTypes.VIDEO_AV1, hashMap3);
        hashMap.put(MimeTypes.VIDEO_VP9, hashMap4);
        hashMap.put(MimeTypes.VIDEO_DOLBY_VISION, hashMap5);
    }

    private VideoConfigUtil() {
    }

    public static VideoMimeInfo resolveVideoMimeInfo(MediaSpec mediaSpec, DynamicRange dynamicRange, VideoValidatedEncoderProfilesProxy videoValidatedEncoderProfilesProxy) {
        EncoderProfilesProxy.VideoProfileProxy videoProfileProxy;
        Preconditions.checkState(dynamicRange.isFullySpecified(), "Dynamic range must be a fully specified dynamic range [provided dynamic range: " + dynamicRange + "]");
        String outputFormatToVideoMime = MediaSpec.outputFormatToVideoMime(mediaSpec.getOutputFormat());
        if (videoValidatedEncoderProfilesProxy != null) {
            Set<Integer> dynamicRangeToVideoProfileHdrFormats = DynamicRangeUtil.dynamicRangeToVideoProfileHdrFormats(dynamicRange);
            Set<Integer> dynamicRangeToVideoProfileBitDepth = DynamicRangeUtil.dynamicRangeToVideoProfileBitDepth(dynamicRange);
            Iterator<EncoderProfilesProxy.VideoProfileProxy> it = videoValidatedEncoderProfilesProxy.getVideoProfiles().iterator();
            while (it.hasNext()) {
                videoProfileProxy = it.next();
                if (dynamicRangeToVideoProfileHdrFormats.contains(Integer.valueOf(videoProfileProxy.getHdrFormat())) && dynamicRangeToVideoProfileBitDepth.contains(Integer.valueOf(videoProfileProxy.getBitDepth()))) {
                    String mediaType = videoProfileProxy.getMediaType();
                    if (Objects.equals(outputFormatToVideoMime, mediaType)) {
                        Logger.d(TAG, "MediaSpec video mime matches EncoderProfiles. Using EncoderProfiles to derive VIDEO settings [mime type: " + outputFormatToVideoMime + "]");
                    } else if (mediaSpec.getOutputFormat() == -1) {
                        Logger.d(TAG, "MediaSpec contains OUTPUT_FORMAT_AUTO. Using CamcorderProfile to derive VIDEO settings [mime type: " + outputFormatToVideoMime + ", dynamic range: " + dynamicRange + "]");
                    }
                    outputFormatToVideoMime = mediaType;
                    break;
                }
            }
        }
        videoProfileProxy = null;
        if (videoProfileProxy == null) {
            if (mediaSpec.getOutputFormat() == -1) {
                outputFormatToVideoMime = getDynamicRangeDefaultMime(dynamicRange);
            }
            if (videoValidatedEncoderProfilesProxy == null) {
                Logger.d(TAG, "No EncoderProfiles present. May rely on fallback defaults to derive VIDEO settings [chosen mime type: " + outputFormatToVideoMime + ", dynamic range: " + dynamicRange + "]");
            } else {
                Logger.d(TAG, "No video EncoderProfile is compatible with requested output format and dynamic range. May rely on fallback defaults to derive VIDEO settings [chosen mime type: " + outputFormatToVideoMime + ", dynamic range: " + dynamicRange + "]");
            }
        }
        VideoMimeInfo.Builder builder = VideoMimeInfo.builder(outputFormatToVideoMime);
        if (videoProfileProxy != null) {
            builder.setCompatibleVideoProfile(videoProfileProxy);
        }
        return builder.build();
    }

    private static String getDynamicRangeDefaultMime(DynamicRange dynamicRange) {
        int encoding = dynamicRange.getEncoding();
        if (encoding == 1) {
            return MimeTypes.VIDEO_H264;
        }
        if (encoding == 3 || encoding == 4 || encoding == 5) {
            return MimeTypes.VIDEO_H265;
        }
        if (encoding == 6) {
            return MimeTypes.VIDEO_DOLBY_VISION;
        }
        throw new UnsupportedOperationException("Unsupported dynamic range: " + dynamicRange + "\nNo supported default mime type available.");
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [androidx.core.util.Supplier] */
    /* JADX WARNING: type inference failed for: r9v1, types: [androidx.camera.video.internal.config.VideoEncoderConfigDefaultResolver] */
    /* JADX WARNING: type inference failed for: r0v3, types: [androidx.camera.video.internal.config.VideoEncoderConfigVideoProfileResolver] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.camera.video.internal.encoder.VideoEncoderConfig resolveVideoEncoderConfig(androidx.camera.video.internal.config.VideoMimeInfo r16, androidx.camera.core.impl.Timebase r17, androidx.camera.video.VideoSpec r18, android.util.Size r19, androidx.camera.core.DynamicRange r20, android.util.Range<java.lang.Integer> r21) {
        /*
            androidx.camera.core.impl.EncoderProfilesProxy$VideoProfileProxy r5 = r16.getCompatibleVideoProfile()
            if (r5 == 0) goto L_0x001b
            androidx.camera.video.internal.config.VideoEncoderConfigVideoProfileResolver r8 = new androidx.camera.video.internal.config.VideoEncoderConfigVideoProfileResolver
            java.lang.String r1 = r16.getMimeType()
            r0 = r8
            r2 = r17
            r3 = r18
            r4 = r19
            r6 = r20
            r7 = r21
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            goto L_0x002f
        L_0x001b:
            androidx.camera.video.internal.config.VideoEncoderConfigDefaultResolver r8 = new androidx.camera.video.internal.config.VideoEncoderConfigDefaultResolver
            java.lang.String r10 = r16.getMimeType()
            r9 = r8
            r11 = r17
            r12 = r18
            r13 = r19
            r14 = r20
            r15 = r21
            r9.<init>(r10, r11, r12, r13, r14, r15)
        L_0x002f:
            java.lang.Object r0 = r8.get()
            androidx.camera.video.internal.encoder.VideoEncoderConfig r0 = (androidx.camera.video.internal.encoder.VideoEncoderConfig) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.internal.config.VideoConfigUtil.resolveVideoEncoderConfig(androidx.camera.video.internal.config.VideoMimeInfo, androidx.camera.core.impl.Timebase, androidx.camera.video.VideoSpec, android.util.Size, androidx.camera.core.DynamicRange, android.util.Range):androidx.camera.video.internal.encoder.VideoEncoderConfig");
    }

    public static int scaleAndClampBitrate(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Range<Integer> range) {
        String str;
        Range<Integer> range2 = range;
        int doubleValue = (int) (((double) i) * new Rational(i2, i3).doubleValue() * new Rational(i4, i5).doubleValue() * new Rational(i6, i7).doubleValue() * new Rational(i8, i9).doubleValue());
        if (Logger.isDebugEnabled(TAG)) {
            str = String.format("Base Bitrate(%dbps) * Bit Depth Ratio (%d / %d) * Frame Rate Ratio(%d / %d) * Width Ratio(%d / %d) * Height Ratio(%d / %d) = %d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i9), Integer.valueOf(doubleValue)});
        } else {
            str = "";
        }
        if (!VideoSpec.BITRATE_RANGE_AUTO.equals(range2)) {
            doubleValue = range2.clamp(Integer.valueOf(doubleValue)).intValue();
            if (Logger.isDebugEnabled(TAG)) {
                str = str + String.format("\nClamped to range %s -> %dbps", new Object[]{range2, Integer.valueOf(doubleValue)});
            }
        }
        Logger.d(TAG, str);
        return doubleValue;
    }

    public static VideoEncoderDataSpace mimeAndProfileToEncoderDataSpace(String str, int i) {
        VideoEncoderDataSpace videoEncoderDataSpace;
        Map map = MIME_TO_DATA_SPACE_MAP.get(str);
        if (map != null && (videoEncoderDataSpace = (VideoEncoderDataSpace) map.get(Integer.valueOf(i))) != null) {
            return videoEncoderDataSpace;
        }
        Logger.w(TAG, String.format("Unsupported mime type %s or profile level %d. Data space is unspecified.", new Object[]{str, Integer.valueOf(i)}));
        return VideoEncoderDataSpace.ENCODER_DATA_SPACE_UNSPECIFIED;
    }

    public static VideoEncoderConfig toVideoEncoderConfig(EncoderProfilesProxy.VideoProfileProxy videoProfileProxy) {
        return VideoEncoderConfig.builder().setMimeType(videoProfileProxy.getMediaType()).setProfile(videoProfileProxy.getProfile()).setResolution(new Size(videoProfileProxy.getWidth(), videoProfileProxy.getHeight())).setFrameRate(videoProfileProxy.getFrameRate()).setBitrate(videoProfileProxy.getBitrate()).setInputTimebase(DEFAULT_TIME_BASE).build();
    }
}
