package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Map;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzer implements Map.Entry {
    private final Map.Entry zza;

    /* synthetic */ zzer(Map.Entry entry, zzeq zzeq) {
        this.zza = entry;
    }

    public final Object getKey() {
        return this.zza.getKey();
    }

    public final Object getValue() {
        if (((zzet) this.zza.getValue()) == null) {
            return null;
        }
        throw null;
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzfo) {
            return ((zzet) this.zza.getValue()).zzc((zzfo) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzet zza() {
        return (zzet) this.zza.getValue();
    }
}
