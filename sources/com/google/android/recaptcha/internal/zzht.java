package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzht implements zzgz {
    public static final zzht zza = new zzht();

    private zzht() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        if (zzugArr.length == 2) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof int[])) {
                zza2 = null;
            }
            int[] iArr = (int[]) zza2;
            if (iArr != null) {
                Object zza3 = zzgf.zzc().zza(zzugArr[1]);
                if (true != (zza3 instanceof String)) {
                    zza3 = null;
                }
                String str = (String) zza3;
                if (str != null) {
                    zzgg zzc = zzgf.zzc();
                    StringBuilder sb = new StringBuilder();
                    try {
                        for (int charAt : iArr) {
                            sb.append(str.charAt(charAt));
                        }
                        zzc.zze(i, sb.toString());
                    } catch (Exception e) {
                        throw new zzcg(4, 22, e);
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
