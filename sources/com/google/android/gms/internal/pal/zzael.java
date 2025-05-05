package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzael {
    private static final zzaek zza;
    private static final zzaek zzb = new zzaek();

    static {
        zzaek zzaek;
        try {
            zzaek = (zzaek) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzaek = null;
        }
        zza = zzaek;
    }

    static zzaek zza() {
        return zza;
    }

    static zzaek zzb() {
        return zzb;
    }
}
