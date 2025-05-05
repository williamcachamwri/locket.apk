package com.google.ads.interactivemedia.v3.internal;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzrz {
    @CheckForNull
    static Object zza(@CheckForNull Map.Entry entry) {
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }

    public static HashMap zzb(int i) {
        int i2;
        if (i < 3) {
            zzqt.zza(i, "expectedSize");
            i2 = i + 1;
        } else {
            i2 = i < 1073741824 ? (int) Math.ceil(((double) i) / 0.75d) : Integer.MAX_VALUE;
        }
        return new HashMap(i2);
    }
}
