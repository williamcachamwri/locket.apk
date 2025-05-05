package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzgd {
    private static final zzgc zza;
    private static final zzgc zzb = new zzgc();

    static {
        zzgc zzgc;
        try {
            zzgc = (zzgc) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzgc = null;
        }
        zza = zzgc;
    }

    static zzgc zza() {
        return zza;
    }

    static zzgc zzb() {
        return zzb;
    }
}
