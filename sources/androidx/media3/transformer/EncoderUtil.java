package androidx.media3.transformer;

import android.media.CamcorderProfile;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.util.Pair;
import android.util.Range;
import android.util.Size;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Util;
import com.google.common.base.Ascii;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.primitives.Ints;

public final class EncoderUtil {
    public static final int LEVEL_UNSET = -1;
    private static final ArrayListMultimap<String, MediaCodecInfo> mimeTypeToEncoders = ArrayListMultimap.create();

    public static synchronized ImmutableList<MediaCodecInfo> getSupportedEncoders(String str) {
        ImmutableList<MediaCodecInfo> copyOf;
        synchronized (EncoderUtil.class) {
            maybePopulateEncoderInfo();
            copyOf = ImmutableList.copyOf(mimeTypeToEncoders.get(Ascii.toLowerCase(str)));
        }
        return copyOf;
    }

    public static synchronized ImmutableSet<String> getSupportedMimeTypes() {
        ImmutableSet<String> copyOf;
        synchronized (EncoderUtil.class) {
            maybePopulateEncoderInfo();
            copyOf = ImmutableSet.copyOf(mimeTypeToEncoders.keySet());
        }
        return copyOf;
    }

    public static synchronized void clearCachedEncoders() {
        synchronized (EncoderUtil.class) {
            mimeTypeToEncoders.clear();
        }
    }

    public static ImmutableList<MediaCodecInfo> getSupportedEncodersForHdrEditing(String str, ColorInfo colorInfo) {
        if (Util.SDK_INT < 33 || colorInfo == null) {
            return ImmutableList.of();
        }
        ImmutableList<MediaCodecInfo> supportedEncoders = getSupportedEncoders(str);
        ImmutableList<Integer> codecProfilesForHdrFormat = getCodecProfilesForHdrFormat(str, colorInfo.colorTransfer);
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < supportedEncoders.size(); i++) {
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) supportedEncoders.get(i);
            if (!mediaCodecInfo.isAlias()) {
                if (isFeatureSupported(mediaCodecInfo, str, "hdr-editing") || (colorInfo.colorTransfer == 7 && Util.SDK_INT >= 35 && isFeatureSupported(mediaCodecInfo, str, "hlg-editing"))) {
                    for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : mediaCodecInfo.getCapabilitiesForType(str).profileLevels) {
                        if (codecProfilesForHdrFormat.contains(Integer.valueOf(codecProfileLevel.profile))) {
                            builder.add((Object) mediaCodecInfo);
                        }
                    }
                }
            }
        }
        return builder.build();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0025, code lost:
        if (r4.equals(androidx.media3.common.MimeTypes.VIDEO_H264) == false) goto L_0x0010;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.common.collect.ImmutableList<java.lang.Integer> getCodecProfilesForHdrFormat(java.lang.String r4, int r5) {
        /*
            r4.hashCode()
            int r0 = r4.hashCode()
            r1 = 2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
            r3 = -1
            switch(r0) {
                case -1662735862: goto L_0x0034;
                case -1662541442: goto L_0x0028;
                case 1331836730: goto L_0x001e;
                case 1599127257: goto L_0x0012;
                default: goto L_0x0010;
            }
        L_0x0010:
            r1 = r3
            goto L_0x003f
        L_0x0012:
            java.lang.String r0 = "video/x-vnd.on2.vp9"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L_0x001c
            goto L_0x0010
        L_0x001c:
            r1 = 3
            goto L_0x003f
        L_0x001e:
            java.lang.String r0 = "video/avc"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L_0x003f
            goto L_0x0010
        L_0x0028:
            java.lang.String r0 = "video/hevc"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L_0x0032
            goto L_0x0010
        L_0x0032:
            r1 = 1
            goto L_0x003f
        L_0x0034:
            java.lang.String r0 = "video/av01"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L_0x003e
            goto L_0x0010
        L_0x003e:
            r1 = 0
        L_0x003f:
            r4 = 6
            r0 = 4096(0x1000, float:5.74E-42)
            r3 = 7
            switch(r1) {
                case 0: goto L_0x0079;
                case 1: goto L_0x0067;
                case 2: goto L_0x005a;
                case 3: goto L_0x0047;
                default: goto L_0x0046;
            }
        L_0x0046:
            goto L_0x008b
        L_0x0047:
            if (r5 == r3) goto L_0x004b
            if (r5 != r4) goto L_0x008b
        L_0x004b:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            r5 = 8192(0x2000, float:1.14794E-41)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            com.google.common.collect.ImmutableList r4 = com.google.common.collect.ImmutableList.of(r4, r5)
            return r4
        L_0x005a:
            if (r5 != r3) goto L_0x008b
            r4 = 16
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            com.google.common.collect.ImmutableList r4 = com.google.common.collect.ImmutableList.of(r4)
            return r4
        L_0x0067:
            if (r5 != r3) goto L_0x006e
            com.google.common.collect.ImmutableList r4 = com.google.common.collect.ImmutableList.of(r2)
            return r4
        L_0x006e:
            if (r5 != r4) goto L_0x008b
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            com.google.common.collect.ImmutableList r4 = com.google.common.collect.ImmutableList.of(r4)
            return r4
        L_0x0079:
            if (r5 != r3) goto L_0x0080
            com.google.common.collect.ImmutableList r4 = com.google.common.collect.ImmutableList.of(r2)
            return r4
        L_0x0080:
            if (r5 != r4) goto L_0x008b
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            com.google.common.collect.ImmutableList r4 = com.google.common.collect.ImmutableList.of(r4)
            return r4
        L_0x008b:
            com.google.common.collect.ImmutableList r4 = com.google.common.collect.ImmutableList.of()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.EncoderUtil.getCodecProfilesForHdrFormat(java.lang.String, int):com.google.common.collect.ImmutableList");
    }

    public static boolean isSizeSupported(MediaCodecInfo mediaCodecInfo, String str, int i, int i2) {
        if (mediaCodecInfo.getCapabilitiesForType(str).getVideoCapabilities().isSizeSupported(i, i2)) {
            return true;
        }
        if (i == 1920 && i2 == 1080) {
            return CamcorderProfile.hasProfile(6);
        }
        if (i == 3840 && i2 == 2160) {
            return CamcorderProfile.hasProfile(8);
        }
        return false;
    }

    public static Range<Integer> getSupportedHeights(MediaCodecInfo mediaCodecInfo, String str, int i) {
        return mediaCodecInfo.getCapabilitiesForType(str).getVideoCapabilities().getSupportedHeightsFor(i);
    }

    public static Pair<Range<Integer>, Range<Integer>> getSupportedResolutionRanges(MediaCodecInfo mediaCodecInfo, String str) {
        MediaCodecInfo.VideoCapabilities videoCapabilities = mediaCodecInfo.getCapabilitiesForType(str).getVideoCapabilities();
        return Pair.create(videoCapabilities.getSupportedWidths(), videoCapabilities.getSupportedHeights());
    }

    public static Size getSupportedResolution(MediaCodecInfo mediaCodecInfo, String str, int i, int i2) {
        MediaCodecInfo.VideoCapabilities videoCapabilities = mediaCodecInfo.getCapabilitiesForType(str).getVideoCapabilities();
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        int alignResolution = alignResolution(i, widthAlignment);
        int alignResolution2 = alignResolution(i2, heightAlignment);
        if (isSizeSupported(mediaCodecInfo, str, alignResolution, alignResolution2)) {
            return new Size(alignResolution, alignResolution2);
        }
        float[] fArr = {0.95f, 0.9f, 0.85f, 0.8f, 0.75f, 0.7f, 0.6666667f, 0.6f, 0.55f, 0.5f, 0.4f, 0.33333334f, 0.25f};
        for (int i3 = 0; i3 < 13; i3++) {
            float f = fArr[i3];
            int alignResolution3 = alignResolution(Math.round(((float) i) * f), widthAlignment);
            int alignResolution4 = alignResolution(Math.round(((float) i2) * f), heightAlignment);
            if (isSizeSupported(mediaCodecInfo, str, alignResolution3, alignResolution4)) {
                return new Size(alignResolution3, alignResolution4);
            }
        }
        int intValue = videoCapabilities.getSupportedHeightsFor(videoCapabilities.getSupportedWidths().clamp(Integer.valueOf(i)).intValue()).clamp(Integer.valueOf(i2)).intValue();
        if (intValue != i2) {
            i = alignResolution((int) Math.round((((double) i) * ((double) intValue)) / ((double) i2)), widthAlignment);
            i2 = alignResolution(intValue, heightAlignment);
        }
        if (isSizeSupported(mediaCodecInfo, str, i, i2)) {
            return new Size(i, i2);
        }
        return null;
    }

    public static ImmutableSet<Integer> findSupportedEncodingProfiles(MediaCodecInfo mediaCodecInfo, String str) {
        MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr = mediaCodecInfo.getCapabilitiesForType(str).profileLevels;
        ImmutableSet.Builder builder = new ImmutableSet.Builder();
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : codecProfileLevelArr) {
            builder.add((Object) Integer.valueOf(codecProfileLevel.profile));
        }
        return builder.build();
    }

    public static int findHighestSupportedEncodingLevel(MediaCodecInfo mediaCodecInfo, String str, int i) {
        int i2 = -1;
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : mediaCodecInfo.getCapabilitiesForType(str).profileLevels) {
            if (codecProfileLevel.profile == i) {
                i2 = Math.max(i2, codecProfileLevel.level);
            }
        }
        return i2;
    }

    public static String findCodecForFormat(MediaFormat mediaFormat, boolean z) {
        float f;
        String str;
        MediaCodecList mediaCodecList = new MediaCodecList(0);
        if (Util.SDK_INT != 21 || !mediaFormat.containsKey("frame-rate")) {
            f = -1.0f;
        } else {
            try {
                f = mediaFormat.getFloat("frame-rate");
            } catch (ClassCastException unused) {
                f = (float) mediaFormat.getInteger("frame-rate");
            }
            mediaFormat.setString("frame-rate", (String) null);
        }
        if (z) {
            str = mediaCodecList.findDecoderForFormat(mediaFormat);
        } else {
            str = mediaCodecList.findEncoderForFormat(mediaFormat);
        }
        if (Util.SDK_INT == 21) {
            MediaFormatUtil.maybeSetInteger(mediaFormat, "frame-rate", Math.round(f));
        }
        return str;
    }

    public static Range<Integer> getSupportedBitrateRange(MediaCodecInfo mediaCodecInfo, String str) {
        return mediaCodecInfo.getCapabilitiesForType(str).getVideoCapabilities().getBitrateRange();
    }

    public static boolean isBitrateModeSupported(MediaCodecInfo mediaCodecInfo, String str, int i) {
        return mediaCodecInfo.getCapabilitiesForType(str).getEncoderCapabilities().isBitrateModeSupported(i);
    }

    public static ImmutableList<Integer> getSupportedColorFormats(MediaCodecInfo mediaCodecInfo, String str) {
        return ImmutableList.copyOf(Ints.asList(mediaCodecInfo.getCapabilitiesForType(str).colorFormats));
    }

    public static boolean isHardwareAccelerated(MediaCodecInfo mediaCodecInfo, String str) {
        if (Util.SDK_INT >= 29) {
            return Api29.isHardwareAccelerated(mediaCodecInfo);
        }
        return !isSoftwareOnly(mediaCodecInfo, str);
    }

    public static boolean isFeatureSupported(MediaCodecInfo mediaCodecInfo, String str, String str2) {
        return mediaCodecInfo.getCapabilitiesForType(str).isFeatureSupported(str2);
    }

    public static int getMaxSupportedInstances(MediaCodecInfo mediaCodecInfo, String str) {
        return mediaCodecInfo.getCapabilitiesForType(str).getMaxSupportedInstances();
    }

    private static boolean isSoftwareOnly(MediaCodecInfo mediaCodecInfo, String str) {
        if (Util.SDK_INT >= 29) {
            return Api29.isSoftwareOnly(mediaCodecInfo);
        }
        if (MimeTypes.isAudio(str)) {
            return true;
        }
        String lowerCase = Ascii.toLowerCase(mediaCodecInfo.getName());
        if (lowerCase.startsWith("arc.")) {
            return false;
        }
        if (lowerCase.startsWith("omx.google.") || lowerCase.startsWith("omx.ffmpeg.")) {
            return true;
        }
        if ((lowerCase.startsWith("omx.sec.") && lowerCase.contains(".sw.")) || lowerCase.equals("omx.qcom.video.decoder.hevcswvdec") || lowerCase.startsWith("c2.android.") || lowerCase.startsWith("c2.google.")) {
            return true;
        }
        if (lowerCase.startsWith("omx.") || lowerCase.startsWith("c2.")) {
            return false;
        }
        return true;
    }

    private static int alignResolution(int i, int i2) {
        boolean z = true;
        if (i % 10 != 1) {
            z = false;
        }
        if (z) {
            return (int) (((double) i2) * Math.floor((double) (((float) i) / ((float) i2))));
        }
        return Math.round(((float) i) / ((float) i2)) * i2;
    }

    private static synchronized void maybePopulateEncoderInfo() {
        synchronized (EncoderUtil.class) {
            if (mimeTypeToEncoders.isEmpty()) {
                for (MediaCodecInfo mediaCodecInfo : new MediaCodecList(0).getCodecInfos()) {
                    if (mediaCodecInfo.isEncoder()) {
                        for (String lowerCase : mediaCodecInfo.getSupportedTypes()) {
                            mimeTypeToEncoders.put(Ascii.toLowerCase(lowerCase), mediaCodecInfo);
                        }
                    }
                }
            }
        }
    }

    private static final class Api29 {
        private Api29() {
        }

        public static boolean isHardwareAccelerated(MediaCodecInfo mediaCodecInfo) {
            return mediaCodecInfo.isHardwareAccelerated();
        }

        public static boolean isSoftwareOnly(MediaCodecInfo mediaCodecInfo) {
            return mediaCodecInfo.isSoftwareOnly();
        }
    }

    private EncoderUtil() {
    }
}
