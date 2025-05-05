package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzhu implements zzgz {
    public static final zzhu zza = new zzhu();

    private zzhu() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        int length = zzugArr.length;
        if (length == 2) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof String)) {
                zza2 = null;
            }
            String str = (String) zza2;
            if (str != null) {
                Object zza3 = zzgf.zzc().zza(zzugArr[1]);
                if (true != (zza3 instanceof zzbp)) {
                    zza3 = null;
                }
                zzbp zzbp = (zzbp) zza3;
                if (zzbp != null) {
                    byte[] zzd = zzex.zza(zzgf.zzb(), zzbp).zzd();
                    zzgf.zzi().zzb(str, zzkj.zzh().zzi(zzd, 0, zzd.length));
                    return;
                }
                throw new zzcg(4, 5, (Throwable) null);
            }
            throw new zzcg(4, 5, (Throwable) null);
        } else if (length == 0) {
            zzgf.zzc().zze(i, new zzbp());
        } else {
            throw new zzcg(4, 3, (Throwable) null);
        }
    }
}
