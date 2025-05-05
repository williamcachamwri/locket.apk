package com.google.android.gms.internal.mlkit_vision_barcode;

import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzco extends zzbs implements Serializable {
    final Object zza;
    final Object zzb;

    zzco(Object obj, Object obj2) {
        this.zza = obj;
        this.zzb = obj2;
    }

    public final Object getKey() {
        return this.zza;
    }

    public final Object getValue() {
        return this.zzb;
    }

    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }
}
