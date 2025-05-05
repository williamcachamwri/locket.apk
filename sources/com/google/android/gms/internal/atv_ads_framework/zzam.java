package com.google.android.gms.internal.atv_ads_framework;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzam extends zzal {
    public static boolean zza(@CheckForNull Object obj, @CheckForNull Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return false;
    }
}
