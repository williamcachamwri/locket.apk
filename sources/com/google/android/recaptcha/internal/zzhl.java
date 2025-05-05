package com.google.android.recaptcha.internal;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import kotlin.collections.ArraysKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzhl implements zzgz {
    public static final zzhl zza = new zzhl();

    private zzhl() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        int length = zzugArr.length;
        if (length != 0) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof Object)) {
                zza2 = null;
            }
            if (zza2 != null) {
                Constructor<?> constructor = zza2 instanceof Constructor ? (Constructor) zza2 : zza2.getClass().getConstructor(new Class[0]);
                Object[] zzg = zzgf.zzc().zzg(ArraysKt.toList((T[]) zzugArr).subList(1, length));
                try {
                    zzgf.zzc().zze(i, constructor.newInstance(Arrays.copyOf(zzg, zzg.length)));
                } catch (Exception e) {
                    throw new zzcg(6, 14, e);
                }
            } else {
                throw new zzcg(4, 5, (Throwable) null);
            }
        } else {
            throw new zzcg(4, 3, (Throwable) null);
        }
    }
}
