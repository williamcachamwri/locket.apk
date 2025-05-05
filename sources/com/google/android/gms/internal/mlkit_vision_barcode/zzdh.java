package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractCollection;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
abstract class zzdh extends AbstractCollection {
    zzdh() {
    }

    public final void clear() {
        zza().zzs();
    }

    public final boolean contains(@CheckForNull Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return zza().zzx(entry.getKey(), entry.getValue());
    }

    public final boolean remove(@CheckForNull Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return zza().zzy(entry.getKey(), entry.getValue());
    }

    public final int size() {
        return zza().zzh();
    }

    /* access modifiers changed from: package-private */
    public abstract zzdg zza();
}
