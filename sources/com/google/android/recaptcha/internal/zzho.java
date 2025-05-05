package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzho implements zzgz {
    public static final zzho zza = new zzho();

    private zzho() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        if (zzugArr.length == 1) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof String)) {
                zza2 = null;
            }
            String str = (String) zza2;
            if (str != null) {
                zzgf.zzf(str);
                return;
            }
            throw new zzcg(4, 5, (Throwable) null);
        }
        throw new zzcg(4, 3, (Throwable) null);
    }
}
