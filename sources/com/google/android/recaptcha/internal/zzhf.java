package com.google.android.recaptcha.internal;

import java.lang.reflect.Field;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzhf implements zzgz {
    public static final zzhf zza = new zzhf();

    private zzhf() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        if (zzugArr.length == 2) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof Field)) {
                zza2 = null;
            }
            Field field = (Field) zza2;
            if (field != null) {
                try {
                    zzgf.zzc().zze(i, field.get(zzgf.zzc().zza(zzugArr[1])));
                } catch (Exception e) {
                    throw new zzcg(6, 16, e);
                }
            } else {
                throw new zzcg(4, 5, (Throwable) null);
            }
        } else {
            throw new zzcg(4, 3, (Throwable) null);
        }
    }
}
