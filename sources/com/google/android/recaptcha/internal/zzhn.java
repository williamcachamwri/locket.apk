package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzhn implements zzgz {
    public static final zzhn zza = new zzhn();

    private zzhn() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        if (zzugArr.length == 1) {
            zzgf.zzc().zze(i, zzgf.zzc().zza(zzugArr[0]));
            return;
        }
        throw new zzcg(4, 3, (Throwable) null);
    }
}
