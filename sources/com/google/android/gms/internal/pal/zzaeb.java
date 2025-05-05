package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzaeb {
    private static final zzaea zza;
    private static final zzaea zzb = new zzaea();

    static {
        zzaea zzaea;
        try {
            zzaea = (zzaea) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzaea = null;
        }
        zza = zzaea;
    }

    static zzaea zza() {
        return zza;
    }

    static zzaea zzb() {
        return zzb;
    }
}
