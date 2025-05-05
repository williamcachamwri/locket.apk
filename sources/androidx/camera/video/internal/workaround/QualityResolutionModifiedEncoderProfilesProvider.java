package androidx.camera.video.internal.workaround;

import android.util.Size;
import androidx.camera.core.impl.EncoderProfilesProvider;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.core.impl.Quirks;
import androidx.camera.video.internal.compat.quirk.StretchedVideoResolutionQuirk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QualityResolutionModifiedEncoderProfilesProvider implements EncoderProfilesProvider {
    private final Map<Integer, EncoderProfilesProxy> mEncoderProfilesCache = new HashMap();
    private final EncoderProfilesProvider mProvider;
    private final Quirks mQuirks;

    public QualityResolutionModifiedEncoderProfilesProvider(EncoderProfilesProvider encoderProfilesProvider, Quirks quirks) {
        this.mProvider = encoderProfilesProvider;
        this.mQuirks = quirks;
    }

    public boolean hasProfile(int i) {
        if (this.mProvider.hasProfile(i) && getProfilesInternal(i) != null) {
            return true;
        }
        return false;
    }

    public EncoderProfilesProxy getAll(int i) {
        return getProfilesInternal(i);
    }

    private EncoderProfilesProxy getProfilesInternal(int i) {
        EncoderProfilesProxy encoderProfilesProxy;
        if (this.mEncoderProfilesCache.containsKey(Integer.valueOf(i))) {
            return this.mEncoderProfilesCache.get(Integer.valueOf(i));
        }
        if (this.mProvider.hasProfile(i)) {
            encoderProfilesProxy = (EncoderProfilesProxy) Objects.requireNonNull(this.mProvider.getAll(i));
            Size alternativeResolution = getAlternativeResolution(i);
            if (alternativeResolution != null) {
                encoderProfilesProxy = createNewEncoderProfiles(encoderProfilesProxy, alternativeResolution);
            }
        } else {
            encoderProfilesProxy = null;
        }
        this.mEncoderProfilesCache.put(Integer.valueOf(i), encoderProfilesProxy);
        return encoderProfilesProxy;
    }

    private Size getAlternativeResolution(int i) {
        for (StretchedVideoResolutionQuirk next : this.mQuirks.getAll(StretchedVideoResolutionQuirk.class)) {
            if (next != null) {
                return next.getAlternativeResolution(i);
            }
        }
        return null;
    }

    private EncoderProfilesProxy createNewEncoderProfiles(EncoderProfilesProxy encoderProfilesProxy, Size size) {
        ArrayList arrayList = new ArrayList();
        for (EncoderProfilesProxy.VideoProfileProxy generateVideoProfile : encoderProfilesProxy.getVideoProfiles()) {
            arrayList.add(generateVideoProfile(generateVideoProfile, size));
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return EncoderProfilesProxy.ImmutableEncoderProfilesProxy.create(encoderProfilesProxy.getDefaultDurationSeconds(), encoderProfilesProxy.getRecommendedFileFormat(), encoderProfilesProxy.getAudioProfiles(), arrayList);
    }

    private static EncoderProfilesProxy.VideoProfileProxy generateVideoProfile(EncoderProfilesProxy.VideoProfileProxy videoProfileProxy, Size size) {
        return EncoderProfilesProxy.VideoProfileProxy.create(videoProfileProxy.getCodec(), videoProfileProxy.getMediaType(), videoProfileProxy.getBitrate(), videoProfileProxy.getFrameRate(), size.getWidth(), size.getHeight(), videoProfileProxy.getProfile(), videoProfileProxy.getBitDepth(), videoProfileProxy.getChromaSubsampling(), videoProfileProxy.getHdrFormat());
    }
}
