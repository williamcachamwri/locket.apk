package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzmp {
    private final Object zza;
    private final int zzb;

    zzmp(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzmp)) {
            return false;
        }
        zzmp zzmp = (zzmp) obj;
        if (this.zza == zzmp.zza && this.zzb == zzmp.zzb) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
