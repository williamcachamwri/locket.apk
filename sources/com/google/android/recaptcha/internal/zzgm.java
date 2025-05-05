package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzgm implements zzgz {
    public static final zzgm zza = new zzgm();

    private zzgm() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        int length = zzugArr.length;
        if (length != 0) {
            zzth zzf = zztk.zzf();
            int i2 = 0;
            while (i2 < length) {
                Object zza2 = zzgf.zzc().zza(zzugArr[i2]);
                if (zza2 != null) {
                    zzti zzf2 = zztj.zzf();
                    if (zza2 instanceof Integer) {
                        zzf2.zzu(((Number) zza2).intValue());
                    } else if (zza2 instanceof Short) {
                        zzf2.zzt(((Number) zza2).shortValue());
                    } else if (zza2 instanceof Byte) {
                        zzf2.zzf(zzlg.zzl(new byte[]{((Number) zza2).byteValue()}, 0, 1));
                    } else if (zza2 instanceof Long) {
                        zzf2.zzv(((Number) zza2).longValue());
                    } else if (zza2 instanceof Double) {
                        zzf2.zzr(((Number) zza2).doubleValue());
                    } else if (zza2 instanceof Float) {
                        zzf2.zzs(((Number) zza2).floatValue());
                    } else if (zza2 instanceof Boolean) {
                        zzf2.zze(((Boolean) zza2).booleanValue());
                    } else if (zza2 instanceof Character) {
                        zzf2.zzq(String.valueOf(((Character) zza2).charValue()));
                    } else if (zza2 instanceof String) {
                        zzf2.zzw((String) zza2);
                    } else {
                        zzf2.zzw(zza2.toString());
                    }
                    zzf.zzf((zztj) zzf2.zzk());
                    i2++;
                } else {
                    throw new zzcg(4, 4, (Throwable) null);
                }
            }
            zzgg zzc = zzgf.zzc();
            byte[] zzd = ((zztk) zzf.zzk()).zzd();
            zzc.zze(i, zzkj.zzh().zzi(zzd, 0, zzd.length));
            return;
        }
        throw new zzcg(4, 3, (Throwable) null);
    }
}
