package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzct  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzct {
    public static final zzvu zza = zza(16);
    public static final zzvu zzb = zza(16, 16, 32, 16, zzur.SHA256);

    private static zzvu zza(int i, int i2, int i3, int i4, zzur zzur) {
        return (zzvu) ((zzajy) zzvu.zza().zza(((zzsb) ((zzajy) zzsb.zza().zza((zzsh) ((zzajy) zzsh.zzb().zza((zzsk) ((zzajy) zzsk.zzb().zza(16).zze())).zza(i).zze())).zza((zzux) ((zzajy) zzux.zzc().zza((zzva) ((zzajy) zzva.zzc().zza(zzur).zza(i4).zze())).zza(32).zze())).zze())).a_()).zza(zzdd.zza()).zza(zzws.TINK).zze());
    }

    private static zzvu zza(int i, int i2) {
        return (zzvu) ((zzajy) zzvu.zza().zza(((zzsq) ((zzajy) zzsq.zzb().zza(i).zza((zzst) ((zzajy) zzst.zzb().zza(16).zze())).zze())).a_()).zza(zzdl.zza()).zza(zzws.TINK).zze());
    }

    private static zzvu zza(int i) {
        return (zzvu) ((zzajy) zzvu.zza().zza(((zzsz) ((zzajy) zzsz.zzc().zza(i).zze())).a_()).zza(zzds.zza()).zza(zzws.TINK).zze());
    }

    static {
        zza(32);
        zza(16, 16);
        zza(32, 16);
        zza(32, 16, 32, 32, zzur.SHA256);
        zzvu zzvu = (zzvu) ((zzajy) zzvu.zza().zza(zzeh.zza()).zza(zzws.TINK).zze());
        zzvu zzvu2 = (zzvu) ((zzajy) zzvu.zza().zza(zzfx.zza()).zza(zzws.TINK).zze());
    }
}
