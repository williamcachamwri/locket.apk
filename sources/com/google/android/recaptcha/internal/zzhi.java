package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.collections.ArraysKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzhi implements zzgz {
    public static final zzhi zza = new zzhi();

    private zzhi() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        int length = zzugArr.length;
        if (length != 0) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof Method)) {
                zza2 = null;
            }
            Method method = (Method) zza2;
            if (method != null) {
                Object[] zzg = zzgf.zzc().zzg(ArraysKt.toList((T[]) zzugArr).subList(1, length));
                try {
                    zzgf.zzc().zze(i, method.invoke((Object) null, Arrays.copyOf(zzg, zzg.length)));
                } catch (Exception e) {
                    throw new zzcg(6, 15, e);
                }
            } else {
                throw new zzcg(4, 5, (Throwable) null);
            }
        } else {
            throw new zzcg(4, 3, (Throwable) null);
        }
    }
}
