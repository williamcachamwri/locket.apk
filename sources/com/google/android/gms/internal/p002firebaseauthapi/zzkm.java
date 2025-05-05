package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzkm {
    public static final zzvu zza;
    private static final byte[] zzb;

    @Deprecated
    private static zzvu zza(zzup zzup, zzur zzur, zztu zztu, zzvu zzvu, zzws zzws, byte[] bArr) {
        return (zzvu) ((zzajy) zzvu.zza().zza(zzjl.zza()).zza(zzws).zza(((zzua) ((zzajy) zzua.zza().zza((zzud) ((zzajy) zzud.zzc().zza((zzum) ((zzajy) zzum.zza().zza(zzup).zza(zzur).zza(zzaip.zza(bArr)).zze())).zza((zztx) ((zzajy) zztx.zza().zza(zzvu).zze())).zza(zztu).zze())).zze())).a_()).zze());
    }

    static {
        byte[] bArr = new byte[0];
        zzb = bArr;
        byte[] bArr2 = bArr;
        zza = zza(zzup.NIST_P256, zzur.SHA256, zztu.UNCOMPRESSED, zzct.zza, zzws.TINK, bArr2);
        zza(zzup.NIST_P256, zzur.SHA256, zztu.COMPRESSED, zzct.zza, zzws.RAW, bArr2);
        zza(zzup.NIST_P256, zzur.SHA256, zztu.UNCOMPRESSED, zzct.zzb, zzws.TINK, bArr2);
    }
}
