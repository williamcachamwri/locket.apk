package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzof {
    zzof() {
    }

    public static final boolean zza(Object obj) {
        return !((zzoe) obj).zze();
    }

    public static final Object zzb(Object obj, Object obj2) {
        zzoe zzoe = (zzoe) obj;
        zzoe zzoe2 = (zzoe) obj2;
        if (!zzoe2.isEmpty()) {
            if (!zzoe.zze()) {
                zzoe = zzoe.zzb();
            }
            zzoe.zzd(zzoe2);
        }
        return zzoe;
    }
}
