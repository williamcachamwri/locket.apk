package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzob implements zzoi {
    private final zzoi[] zza;

    zzob(zzoi... zzoiArr) {
        this.zza = zzoiArr;
    }

    public final zzoh zzb(Class cls) {
        for (int i = 0; i < 2; i++) {
            zzoi zzoi = this.zza[i];
            if (zzoi.zzc(cls)) {
                return zzoi.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    public final boolean zzc(Class cls) {
        for (int i = 0; i < 2; i++) {
            if (this.zza[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
