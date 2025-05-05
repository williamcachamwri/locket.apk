package com.google.android.gms.internal.auth;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzdm implements zzdj {
    private static final zzdj zza = zzdl.zza;
    private volatile zzdj zzb;
    @CheckForNull
    private Object zzc;

    zzdm(zzdj zzdj) {
        this.zzb = zzdj;
    }

    public final String toString() {
        Object obj = this.zzb;
        if (obj == zza) {
            obj = "<supplier that returned " + String.valueOf(this.zzc) + ">";
        }
        return "Suppliers.memoize(" + String.valueOf(obj) + ")";
    }

    public final Object zza() {
        zzdj zzdj = this.zzb;
        zzdj zzdj2 = zza;
        if (zzdj != zzdj2) {
            synchronized (this) {
                if (this.zzb != zzdj2) {
                    Object zza2 = this.zzb.zza();
                    this.zzc = zza2;
                    this.zzb = zzdj2;
                    return zza2;
                }
            }
        }
        return this.zzc;
    }
}
