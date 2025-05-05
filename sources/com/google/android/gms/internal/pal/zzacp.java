package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzacp {
    private static final zzacn zza = new zzaco();
    private static final zzacn zzb;

    static {
        zzacn zzacn;
        try {
            zzacn = (zzacn) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzacn = null;
        }
        zzb = zzacn;
    }

    static zzacn zza() {
        zzacn zzacn = zzb;
        if (zzacn != null) {
            return zzacn;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzacn zzb() {
        return zza;
    }
}
