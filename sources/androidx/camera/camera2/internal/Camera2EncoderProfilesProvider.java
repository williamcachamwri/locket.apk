package androidx.camera.camera2.internal;

import android.media.CamcorderProfile;
import android.media.EncoderProfiles;
import android.os.Build;
import android.util.Size;
import androidx.camera.camera2.internal.compat.quirk.CamcorderProfileResolutionQuirk;
import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.internal.compat.quirk.InvalidVideoProfilesQuirk;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.EncoderProfilesProvider;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.compat.EncoderProfilesProxyCompat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Camera2EncoderProfilesProvider implements EncoderProfilesProvider {
    private static final String TAG = "Camera2EncoderProfilesProvider";
    private final String mCameraId;
    private final Quirks mCameraQuirks;
    private final Map<Integer, EncoderProfilesProxy> mEncoderProfilesCache = new HashMap();
    private final boolean mHasValidCameraId;
    private final int mIntCameraId;

    public Camera2EncoderProfilesProvider(String str, Quirks quirks) {
        int i;
        boolean z;
        this.mCameraId = str;
        try {
            i = Integer.parseInt(str);
            z = true;
        } catch (NumberFormatException unused) {
            Logger.w(TAG, "Camera id is not an integer: " + str + ", unable to create Camera2EncoderProfilesProvider");
            z = false;
            i = -1;
        }
        this.mHasValidCameraId = z;
        this.mIntCameraId = i;
        this.mCameraQuirks = quirks;
    }

    public boolean hasProfile(int i) {
        if (this.mHasValidCameraId && getAll(i) != null) {
            return true;
        }
        return false;
    }

    public EncoderProfilesProxy getAll(int i) {
        EncoderProfilesProxy encoderProfilesProxy = null;
        if (!this.mHasValidCameraId || !CamcorderProfile.hasProfile(this.mIntCameraId, i)) {
            return null;
        }
        if (this.mEncoderProfilesCache.containsKey(Integer.valueOf(i))) {
            return this.mEncoderProfilesCache.get(Integer.valueOf(i));
        }
        EncoderProfilesProxy profilesInternal = getProfilesInternal(i);
        if (profilesInternal == null || isEncoderProfilesResolutionValidInQuirk(profilesInternal)) {
            encoderProfilesProxy = profilesInternal;
        } else if (i == 1) {
            encoderProfilesProxy = findHighestQualityProfiles();
        } else if (i == 0) {
            encoderProfilesProxy = findLowestQualityProfiles();
        }
        this.mEncoderProfilesCache.put(Integer.valueOf(i), encoderProfilesProxy);
        return encoderProfilesProxy;
    }

    private EncoderProfilesProxy findHighestQualityProfiles() {
        for (Integer intValue : QUALITY_HIGH_TO_LOW) {
            EncoderProfilesProxy all = getAll(intValue.intValue());
            if (all != null) {
                return all;
            }
        }
        return null;
    }

    private EncoderProfilesProxy findLowestQualityProfiles() {
        for (int size = QUALITY_HIGH_TO_LOW.size() - 1; size >= 0; size--) {
            EncoderProfilesProxy all = getAll(size);
            if (all != null) {
                return all;
            }
        }
        return null;
    }

    private EncoderProfilesProxy getProfilesInternal(int i) {
        if (Build.VERSION.SDK_INT >= 31) {
            EncoderProfiles all = Api31Impl.getAll(this.mCameraId, i);
            if (all == null) {
                return null;
            }
            if (DeviceQuirks.get(InvalidVideoProfilesQuirk.class) != null) {
                Logger.d(TAG, "EncoderProfiles contains invalid video profiles, use CamcorderProfile to create EncoderProfilesProxy.");
            } else {
                try {
                    return EncoderProfilesProxyCompat.from(all);
                } catch (NullPointerException e) {
                    Logger.w(TAG, "Failed to create EncoderProfilesProxy, EncoderProfiles might  contain invalid video profiles. Use CamcorderProfile instead.", e);
                }
            }
        }
        return createProfilesFromCamcorderProfile(i);
    }

    private EncoderProfilesProxy createProfilesFromCamcorderProfile(int i) {
        CamcorderProfile camcorderProfile;
        try {
            camcorderProfile = CamcorderProfile.get(this.mIntCameraId, i);
        } catch (RuntimeException e) {
            Logger.w(TAG, "Unable to get CamcorderProfile by quality: " + i, e);
            camcorderProfile = null;
        }
        if (camcorderProfile != null) {
            return EncoderProfilesProxyCompat.from(camcorderProfile);
        }
        return null;
    }

    private boolean isEncoderProfilesResolutionValidInQuirk(EncoderProfilesProxy encoderProfilesProxy) {
        CamcorderProfileResolutionQuirk camcorderProfileResolutionQuirk = (CamcorderProfileResolutionQuirk) this.mCameraQuirks.get(CamcorderProfileResolutionQuirk.class);
        if (camcorderProfileResolutionQuirk == null) {
            return true;
        }
        List<EncoderProfilesProxy.VideoProfileProxy> videoProfiles = encoderProfilesProxy.getVideoProfiles();
        if (videoProfiles.isEmpty()) {
            return true;
        }
        EncoderProfilesProxy.VideoProfileProxy videoProfileProxy = videoProfiles.get(0);
        return camcorderProfileResolutionQuirk.getSupportedResolutions().contains(new Size(videoProfileProxy.getWidth(), videoProfileProxy.getHeight()));
    }

    static class Api31Impl {
        static EncoderProfiles getAll(String str, int i) {
            return CamcorderProfile.getAll(str, i);
        }

        private Api31Impl() {
        }
    }
}
