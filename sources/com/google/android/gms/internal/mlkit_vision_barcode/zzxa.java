package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzxa {
    private static zzwz zza;

    public static synchronized zzwp zza(zzwh zzwh) {
        zzwp zzwp;
        synchronized (zzxa.class) {
            if (zza == null) {
                zza = new zzwz((zzwy) null);
            }
            zzwp = (zzwp) zza.get(zzwh);
        }
        return zzwp;
    }

    public static synchronized zzwp zzb(String str) {
        zzwp zza2;
        synchronized (zzxa.class) {
            zza2 = zza(zzwh.zzd(str).zzd());
        }
        return zza2;
    }
}
