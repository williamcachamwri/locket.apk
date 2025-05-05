package androidx.camera.video.internal.workaround;

import androidx.arch.core.util.Function;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.EncoderProfilesProvider;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.core.impl.Quirks;
import androidx.camera.video.internal.compat.quirk.ExtraSupportedQualityQuirk;
import androidx.camera.video.internal.encoder.VideoEncoderConfig;
import androidx.camera.video.internal.encoder.VideoEncoderInfo;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QualityAddedEncoderProfilesProvider implements EncoderProfilesProvider {
    private Map<Integer, EncoderProfilesProxy> mExtraQualityToEncoderProfiles;
    private final EncoderProfilesProvider mProvider;

    public QualityAddedEncoderProfilesProvider(EncoderProfilesProvider encoderProfilesProvider, Quirks quirks, CameraInfoInternal cameraInfoInternal, Function<VideoEncoderConfig, VideoEncoderInfo> function) {
        this.mProvider = encoderProfilesProvider;
        List<ExtraSupportedQualityQuirk> all = quirks.getAll(ExtraSupportedQualityQuirk.class);
        if (!all.isEmpty()) {
            Preconditions.checkState(all.size() != 1 ? false : true);
            Map<Integer, EncoderProfilesProxy> extraEncoderProfiles = all.get(0).getExtraEncoderProfiles(cameraInfoInternal, encoderProfilesProvider, function);
            if (extraEncoderProfiles != null) {
                this.mExtraQualityToEncoderProfiles = new HashMap(extraEncoderProfiles);
            }
        }
    }

    public boolean hasProfile(int i) {
        return getProfilesInternal(i) != null;
    }

    public EncoderProfilesProxy getAll(int i) {
        return getProfilesInternal(i);
    }

    private EncoderProfilesProxy getProfilesInternal(int i) {
        Map<Integer, EncoderProfilesProxy> map = this.mExtraQualityToEncoderProfiles;
        if (map == null || !map.containsKey(Integer.valueOf(i))) {
            return this.mProvider.getAll(i);
        }
        return this.mExtraQualityToEncoderProfiles.get(Integer.valueOf(i));
    }
}
