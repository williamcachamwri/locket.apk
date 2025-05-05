package com.google.ads.interactivemedia.v3.internal;

import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
abstract class zzrq extends zzrr {
    zzrq() {
    }

    public final boolean contains(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = zzh().get(entry.getKey());
            if (obj2 == null || !obj2.equals(entry.getValue())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return zzso.zza(zzh().entrySet());
    }

    public final int size() {
        return zzh().size();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        return zzh().zzk();
    }

    /* access modifiers changed from: package-private */
    public abstract zzrp zzh();

    /* access modifiers changed from: package-private */
    public final boolean zzi() {
        return false;
    }
}
