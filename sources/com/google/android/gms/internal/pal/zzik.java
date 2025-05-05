package com.google.android.gms.internal.pal;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzik extends zzih {
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
