package androidx.camera.video.internal.workaround;

import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.EncoderProfilesProvider;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.internal.compat.quirk.SurfaceProcessingQuirk;
import androidx.camera.video.Quality;
import androidx.camera.video.internal.compat.quirk.VideoQualityQuirk;
import java.util.HashMap;
import java.util.Map;

public class QualityValidatedEncoderProfilesProvider implements EncoderProfilesProvider {
    private static final Map<Integer, Quality> CAMCORDER_TO_VIDEO_QUALITY_MAP;
    private final CameraInfoInternal mCameraInfo;
    private final EncoderProfilesProvider mProvider;
    private final Quirks mQuirks;

    static {
        HashMap hashMap = new HashMap();
        CAMCORDER_TO_VIDEO_QUALITY_MAP = hashMap;
        hashMap.put(1, Quality.HIGHEST);
        hashMap.put(8, Quality.UHD);
        hashMap.put(6, Quality.FHD);
        hashMap.put(5, Quality.HD);
        hashMap.put(4, Quality.SD);
        hashMap.put(0, Quality.LOWEST);
    }

    public QualityValidatedEncoderProfilesProvider(EncoderProfilesProvider encoderProfilesProvider, CameraInfoInternal cameraInfoInternal, Quirks quirks) {
        this.mProvider = encoderProfilesProvider;
        this.mCameraInfo = cameraInfoInternal;
        this.mQuirks = quirks;
    }

    public boolean hasProfile(int i) {
        return this.mProvider.hasProfile(i) && isDeviceValidQuality(i);
    }

    public EncoderProfilesProxy getAll(int i) {
        if (!hasProfile(i)) {
            return null;
        }
        return this.mProvider.getAll(i);
    }

    private boolean isDeviceValidQuality(int i) {
        Quality quality = CAMCORDER_TO_VIDEO_QUALITY_MAP.get(Integer.valueOf(i));
        if (quality == null) {
            return true;
        }
        for (VideoQualityQuirk next : this.mQuirks.getAll(VideoQualityQuirk.class)) {
            if (next != null && next.isProblematicVideoQuality(this.mCameraInfo, quality) && !workaroundBySurfaceProcessing(next)) {
                return false;
            }
        }
        return true;
    }

    private static boolean workaroundBySurfaceProcessing(Quirk quirk) {
        return (quirk instanceof SurfaceProcessingQuirk) && ((SurfaceProcessingQuirk) quirk).workaroundBySurfaceProcessing();
    }
}
