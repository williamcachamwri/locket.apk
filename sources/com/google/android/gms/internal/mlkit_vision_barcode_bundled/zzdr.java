package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzdr {
    private static final zzdp zza = new zzdq();
    private static final zzdp zzb;

    static {
        zzdp zzdp;
        try {
            zzdp = (zzdp) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzdp = null;
        }
        zzb = zzdp;
    }

    static zzdp zza() {
        zzdp zzdp = zzb;
        if (zzdp != null) {
            return zzdp;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzdp zzb() {
        return zza;
    }
}
