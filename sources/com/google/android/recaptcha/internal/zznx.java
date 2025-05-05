package com.google.android.recaptcha.internal;

import java.util.List;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zznx {
    zznx() {
    }

    public static final List zza(Object obj, long j) {
        zznm zznm = (zznm) zzpu.zzf(obj, j);
        if (zznm.zzc()) {
            return zznm;
        }
        int size = zznm.size();
        zznm zzd = zznm.zzd(size == 0 ? 10 : size + size);
        zzpu.zzs(obj, j, zzd);
        return zzd;
    }
}
