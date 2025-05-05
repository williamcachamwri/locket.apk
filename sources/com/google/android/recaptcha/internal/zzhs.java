package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzhs implements zzgz {
    public static final zzhs zza = new zzhs();

    private zzhs() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        Serializable serializable;
        if (zzugArr.length == 2) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof Object)) {
                zza2 = null;
            }
            if (zza2 != null) {
                Object zza3 = zzgf.zzc().zza(zzugArr[1]);
                if (true != (zza3 instanceof Integer)) {
                    zza3 = null;
                }
                Integer num = (Integer) zza3;
                if (num != null) {
                    int intValue = num.intValue();
                    if (zza2 instanceof Integer) {
                        serializable = Integer.valueOf(((Number) zza2).intValue() - intValue);
                    } else if (zza2 instanceof int[]) {
                        Collection arrayList = new ArrayList(r2);
                        for (int i2 : (int[]) zza2) {
                            arrayList.add(Integer.valueOf(i2 - intValue));
                        }
                        serializable = (Serializable) ((List) arrayList).toArray(new Integer[0]);
                    } else {
                        throw new zzcg(4, 5, (Throwable) null);
                    }
                    zzgf.zzc().zze(i, serializable);
                    return;
                }
                throw new zzcg(4, 5, (Throwable) null);
            }
            throw new zzcg(4, 5, (Throwable) null);
        }
        throw new zzcg(4, 3, (Throwable) null);
    }
}
