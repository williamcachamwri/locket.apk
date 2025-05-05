package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzda {
    private static final zzcy zza = new zzcz();
    private static final zzcy zzb;

    static {
        zzcy zzcy;
        try {
            zzcy = (zzcy) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzcy = null;
        }
        zzb = zzcy;
    }

    static zzcy zza() {
        zzcy zzcy = zzb;
        if (zzcy != null) {
            return zzcy;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzcy zzb() {
        return zza;
    }
}
