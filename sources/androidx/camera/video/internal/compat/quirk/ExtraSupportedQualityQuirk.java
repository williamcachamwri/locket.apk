package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import android.util.Range;
import androidx.arch.core.util.Function;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.EncoderProfilesProvider;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.core.impl.Quirk;
import androidx.camera.video.VideoSpec;
import androidx.camera.video.internal.config.VideoConfigUtil;
import androidx.camera.video.internal.encoder.VideoEncoderConfig;
import androidx.camera.video.internal.encoder.VideoEncoderInfo;
import java.util.Collections;
import java.util.Map;

public class ExtraSupportedQualityQuirk implements Quirk {
    private static final String MOTO_C_FRONT_CAM_ID = "1";

    static boolean load() {
        return isMotoC();
    }

    private static boolean isMotoC() {
        return "motorola".equalsIgnoreCase(Build.BRAND) && "moto c".equalsIgnoreCase(Build.MODEL);
    }

    public Map<Integer, EncoderProfilesProxy> getExtraEncoderProfiles(CameraInfoInternal cameraInfoInternal, EncoderProfilesProvider encoderProfilesProvider, Function<VideoEncoderConfig, VideoEncoderInfo> function) {
        if (isMotoC()) {
            return getExtraEncoderProfilesForMotoC(cameraInfoInternal, encoderProfilesProvider, function);
        }
        return Collections.emptyMap();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0015, code lost:
        r6 = r6.getAll(1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map<java.lang.Integer, androidx.camera.core.impl.EncoderProfilesProxy> getExtraEncoderProfilesForMotoC(androidx.camera.core.impl.CameraInfoInternal r5, androidx.camera.core.impl.EncoderProfilesProvider r6, androidx.arch.core.util.Function<androidx.camera.video.internal.encoder.VideoEncoderConfig, androidx.camera.video.internal.encoder.VideoEncoderInfo> r7) {
        /*
            r4 = this;
            java.lang.String r0 = "1"
            java.lang.String r5 = r5.getCameraId()
            boolean r5 = r0.equals(r5)
            r0 = 0
            if (r5 == 0) goto L_0x006c
            r5 = 4
            boolean r1 = r6.hasProfile(r5)
            if (r1 == 0) goto L_0x0015
            goto L_0x006c
        L_0x0015:
            r1 = 1
            androidx.camera.core.impl.EncoderProfilesProxy r6 = r6.getAll(r1)
            androidx.camera.core.impl.EncoderProfilesProxy$VideoProfileProxy r2 = androidx.camera.video.internal.utils.EncoderProfilesUtil.getFirstVideoProfile(r6)
            if (r2 != 0) goto L_0x0021
            return r0
        L_0x0021:
            android.util.Range r7 = getSupportedBitrateRange(r2, r7)
            android.util.Size r0 = androidx.camera.core.internal.utils.SizeUtil.RESOLUTION_480P
            androidx.camera.core.impl.EncoderProfilesProxy$VideoProfileProxy r7 = androidx.camera.video.internal.utils.EncoderProfilesUtil.deriveVideoProfile(r2, r0, r7)
            int r0 = r6.getDefaultDurationSeconds()
            int r3 = r6.getRecommendedFileFormat()
            java.util.List r6 = r6.getAudioProfiles()
            java.util.List r7 = java.util.Collections.singletonList(r7)
            androidx.camera.core.impl.EncoderProfilesProxy$ImmutableEncoderProfilesProxy r6 = androidx.camera.core.impl.EncoderProfilesProxy.ImmutableEncoderProfilesProxy.create(r0, r3, r6, r7)
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r7.put(r5, r6)
            android.util.Size r5 = new android.util.Size
            int r0 = r2.getWidth()
            int r2 = r2.getHeight()
            r5.<init>(r0, r2)
            android.util.Size r0 = androidx.camera.core.internal.utils.SizeUtil.RESOLUTION_480P
            int r0 = androidx.camera.core.internal.utils.SizeUtil.getArea(r0)
            int r5 = androidx.camera.core.internal.utils.SizeUtil.getArea(r5)
            if (r0 <= r5) goto L_0x006b
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
            r7.put(r5, r6)
        L_0x006b:
            return r7
        L_0x006c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.internal.compat.quirk.ExtraSupportedQualityQuirk.getExtraEncoderProfilesForMotoC(androidx.camera.core.impl.CameraInfoInternal, androidx.camera.core.impl.EncoderProfilesProvider, androidx.arch.core.util.Function):java.util.Map");
    }

    private static Range<Integer> getSupportedBitrateRange(EncoderProfilesProxy.VideoProfileProxy videoProfileProxy, Function<VideoEncoderConfig, VideoEncoderInfo> function) {
        VideoEncoderInfo apply = function.apply(VideoConfigUtil.toVideoEncoderConfig(videoProfileProxy));
        if (apply != null) {
            return apply.getSupportedBitrateRange();
        }
        return VideoSpec.BITRATE_RANGE_AUTO;
    }
}
