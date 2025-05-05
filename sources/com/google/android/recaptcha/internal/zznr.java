package com.google.android.recaptcha.internal;

import java.util.Map;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zznr implements Map.Entry {
    private final Map.Entry zza;

    /* synthetic */ zznr(Map.Entry entry, zznt zznt) {
        this.zza = entry;
    }

    public final Object getKey() {
        return this.zza.getKey();
    }

    public final Object getValue() {
        if (((zznu) this.zza.getValue()) == null) {
            return null;
        }
        throw null;
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzok) {
            return ((zznu) this.zza.getValue()).zzc((zzok) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zznu zza() {
        return (zznu) this.zza.getValue();
    }
}
