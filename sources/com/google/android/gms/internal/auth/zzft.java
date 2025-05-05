package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzft {
    private static final zzfs zza;
    private static final zzfs zzb = new zzfs();

    static {
        zzfs zzfs;
        try {
            zzfs = (zzfs) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzfs = null;
        }
        zza = zzfs;
    }

    static zzfs zza() {
        return zza;
    }

    static zzfs zzb() {
        return zzb;
    }
}
