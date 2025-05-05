package androidx.camera.camera2.internal.compat.params;

import android.hardware.camera2.params.DynamicRangeProfiles;
import androidx.camera.core.DynamicRange;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class DynamicRangeConversions {
    private static final Map<DynamicRange, List<Long>> DR_TO_PROFILE_MAP;
    private static final Map<Long, DynamicRange> PROFILE_TO_DR_MAP;

    static {
        HashMap hashMap = new HashMap();
        PROFILE_TO_DR_MAP = hashMap;
        HashMap hashMap2 = new HashMap();
        DR_TO_PROFILE_MAP = hashMap2;
        hashMap.put(1L, DynamicRange.SDR);
        hashMap2.put(DynamicRange.SDR, Collections.singletonList(1L));
        hashMap.put(2L, DynamicRange.HLG_10_BIT);
        hashMap2.put((DynamicRange) hashMap.get(2L), Collections.singletonList(2L));
        hashMap.put(4L, DynamicRange.HDR10_10_BIT);
        hashMap2.put(DynamicRange.HDR10_10_BIT, Collections.singletonList(4L));
        hashMap.put(8L, DynamicRange.HDR10_PLUS_10_BIT);
        hashMap2.put(DynamicRange.HDR10_PLUS_10_BIT, Collections.singletonList(8L));
        List<Long> asList = Arrays.asList(new Long[]{64L, 128L, 16L, 32L});
        for (Long put : asList) {
            PROFILE_TO_DR_MAP.put(put, DynamicRange.DOLBY_VISION_10_BIT);
        }
        DR_TO_PROFILE_MAP.put(DynamicRange.DOLBY_VISION_10_BIT, asList);
        List<Long> asList2 = Arrays.asList(new Long[]{1024L, 2048L, 256L, 512L});
        for (Long put2 : asList2) {
            PROFILE_TO_DR_MAP.put(put2, DynamicRange.DOLBY_VISION_8_BIT);
        }
        DR_TO_PROFILE_MAP.put(DynamicRange.DOLBY_VISION_8_BIT, asList2);
    }

    public static DynamicRange profileToDynamicRange(long j) {
        return PROFILE_TO_DR_MAP.get(Long.valueOf(j));
    }

    public static Long dynamicRangeToFirstSupportedProfile(DynamicRange dynamicRange, DynamicRangeProfiles dynamicRangeProfiles) {
        List<Long> list = DR_TO_PROFILE_MAP.get(dynamicRange);
        if (list == null) {
            return null;
        }
        Set supportedProfiles = dynamicRangeProfiles.getSupportedProfiles();
        for (Long l : list) {
            if (supportedProfiles.contains(l)) {
                return l;
            }
        }
        return null;
    }

    private DynamicRangeConversions() {
    }
}
