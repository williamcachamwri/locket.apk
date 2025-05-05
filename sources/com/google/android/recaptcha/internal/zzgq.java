package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzgq implements zzgz {
    public static final zzgq zza = new zzgq();

    private zzgq() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        if (zzugArr.length == 2) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof String)) {
                zza2 = null;
            }
            String str = (String) zza2;
            if (str != null) {
                Object zza3 = zzgf.zzc().zza(zzugArr[1]);
                if (true != (zza3 instanceof String)) {
                    zza3 = null;
                }
                String str2 = (String) zza3;
                if (str2 != null) {
                    zzgf.zzc().zze(i, str.concat(str2));
                    return;
                }
                throw new zzcg(4, 5, (Throwable) null);
            }
            throw new zzcg(4, 5, (Throwable) null);
        }
        throw new zzcg(4, 3, (Throwable) null);
    }
}
