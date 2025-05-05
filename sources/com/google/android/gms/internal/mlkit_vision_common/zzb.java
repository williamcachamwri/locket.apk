package com.google.android.gms.internal.mlkit_vision_common;

import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public final class zzb extends zza {
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
