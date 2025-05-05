package com.google.android.recaptcha.internal;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzgl implements zzgz {
    public static final zzgl zza = new zzgl();

    private zzgl() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        if (zzugArr.length == 3) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof Integer)) {
                zza2 = null;
            }
            Integer num = (Integer) zza2;
            if (num != null) {
                int intValue = num.intValue();
                if (intValue != 0) {
                    Object zza3 = zzgf.zzc().zza(zzugArr[1]);
                    if (true != (zza3 instanceof Object)) {
                        zza3 = null;
                    }
                    if (zza3 != null) {
                        Object zza4 = zzgf.zzc().zza(zzugArr[2]);
                        if (true != (zza4 instanceof Object)) {
                            zza4 = null;
                        }
                        if (zza4 == null) {
                            throw new zzcg(4, 5, (Throwable) null);
                        } else if (Intrinsics.areEqual(zza3, zza4)) {
                            zzgf.zzg(zzgf.zza() + intValue);
                        }
                    } else {
                        throw new zzcg(4, 5, (Throwable) null);
                    }
                } else {
                    throw new zzcg(4, 6, (Throwable) null);
                }
            } else {
                throw new zzcg(4, 5, (Throwable) null);
            }
        } else {
            throw new zzcg(4, 3, (Throwable) null);
        }
    }
}
