package com.google.android.recaptcha.internal;

import java.util.Arrays;
import kotlin.collections.ArraysKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzhc implements zzgz {
    public static final zzhc zza = new zzhc();

    private zzhc() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        int length = zzugArr.length;
        if (length != 0) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof Class)) {
                zza2 = null;
            }
            Class cls = (Class) zza2;
            if (cls != null) {
                Class[] zzf = zzgf.zzc().zzf(ArraysKt.toList((T[]) zzugArr).subList(1, length));
                try {
                    zzgf.zzc().zze(i, cls.getConstructor((Class[]) Arrays.copyOf(zzf, zzf.length)));
                } catch (Exception e) {
                    throw new zzcg(6, 9, e);
                }
            } else {
                throw new zzcg(4, 5, (Throwable) null);
            }
        } else {
            throw new zzcg(4, 3, (Throwable) null);
        }
    }
}
