package com.google.android.recaptcha.internal;

import java.lang.reflect.Array;
import java.util.List;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzgj implements zzgz {
    public static final zzgj zza = new zzgj();

    private zzgj() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        Object obj;
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
                    try {
                        if (zza2 instanceof String) {
                            obj = String.valueOf(((String) zza2).charAt(intValue));
                        } else if (zza2 instanceof List) {
                            obj = ((List) zza2).get(intValue);
                        } else {
                            obj = Array.get(zza2, intValue);
                        }
                        zzgf.zzc().zze(i, obj);
                    } catch (Exception e) {
                        if (e instanceof ArrayIndexOutOfBoundsException) {
                            throw new zzcg(4, 22, e);
                        }
                        throw new zzcg(4, 23, e);
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
