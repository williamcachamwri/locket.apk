package com.google.android.gms.internal.fido;

import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
abstract class zzbb extends zzbc {
    zzbb() {
    }

    public final boolean contains(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = zzf().get(entry.getKey());
            if (obj2 == null || !obj2.equals(entry.getValue())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return zzbx.zza(zzf().entrySet());
    }

    public final int size() {
        return zzf().size();
    }

    /* access modifiers changed from: package-private */
    public abstract zzba zzf();

    /* access modifiers changed from: package-private */
    public final boolean zzg() {
        return false;
    }
}
