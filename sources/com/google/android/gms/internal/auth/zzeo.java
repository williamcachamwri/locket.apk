package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzeo {
    private static final zzem zza = new zzen();
    private static final zzem zzb;

    static {
        zzem zzem;
        try {
            zzem = (zzem) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzem = null;
        }
        zzb = zzem;
    }

    static zzem zza() {
        zzem zzem = zzb;
        if (zzem != null) {
            return zzem;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzem zzb() {
        return zza;
    }
}
