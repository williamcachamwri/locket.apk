package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzhb implements zzgz {
    public static final zzhb zza = new zzhb();

    private zzhb() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        if (zzugArr.length == 1) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof Object)) {
                zza2 = null;
            }
            if (zza2 != null) {
                try {
                    if (zza2 instanceof String) {
                        zza2 = zzgf.zzh().zza((String) zza2);
                    }
                    zzgf.zzc().zze(i, zzge.zza(zza2));
                } catch (zzcg e) {
                    throw e;
                } catch (Exception e2) {
                    throw new zzcg(6, 8, e2);
                }
            } else {
                throw new zzcg(4, 5, (Throwable) null);
            }
        } else {
            throw new zzcg(4, 3, (Throwable) null);
        }
    }
}
