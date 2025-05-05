package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzgn implements zzgz {
    public static final zzgn zza = new zzgn();

    private zzgn() {
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
                if (zza3 == null) {
                    throw new zzcg(4, 4, (Throwable) null);
                } else if ((zza3 instanceof Integer) || (zza3 instanceof Short) || (zza3 instanceof Byte) || (zza3 instanceof Long) || (zza3 instanceof Double) || (zza3 instanceof Float) || (zza3 instanceof Boolean) || (zza3 instanceof Character) || (zza3 instanceof String)) {
                    zzgf.zzi().zzb(str, zza3.toString());
                } else {
                    throw new zzcg(4, 7, (Throwable) null);
                }
            } else {
                throw new zzcg(4, 5, (Throwable) null);
            }
        } else {
            throw new zzcg(4, 3, (Throwable) null);
        }
    }
}
