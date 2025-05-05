package com.google.android.recaptcha.internal;

import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzhe implements zzgz {
    public static final zzhe zza = new zzhe();

    private zzhe() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        int length = zzugArr.length;
        if (length >= 2) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof Object)) {
                zza2 = null;
            }
            if (zza2 != null) {
                Class<?> cls = zza2 instanceof Class ? (Class) zza2 : zza2.getClass();
                Object zza3 = zzgf.zzc().zza(zzugArr[1]);
                if (true != (zza3 instanceof String)) {
                    zza3 = null;
                }
                String str = (String) zza3;
                if (str != null) {
                    String zza4 = zzgf.zzh().zza(str);
                    if (!Intrinsics.areEqual((Object) zza4, (Object) "forName")) {
                        Class[] zzf = zzgf.zzc().zzf(ArraysKt.toList((T[]) zzugArr).subList(2, length));
                        try {
                            zzgf.zzc().zze(i, cls.getMethod(zza4, (Class[]) Arrays.copyOf(zzf, zzf.length)));
                        } catch (Exception e) {
                            throw new zzcg(6, 13, e);
                        }
                    } else {
                        throw new zzcg(6, 48, (Throwable) null);
                    }
                } else {
                    throw new zzcg(4, 5, (Throwable) null);
                }
            } else {
                throw new zzcg(4, 5, (Throwable) null);
            }
        } else {
            throw new zzcg(4, 3, (Throwable) null);
        }
    }
}
