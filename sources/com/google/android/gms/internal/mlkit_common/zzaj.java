package com.google.android.gms.internal.mlkit_common;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public abstract class zzaj extends zzab implements Set {
    @CheckForNull
    private transient zzaf zza;

    zzaj() {
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size() && containsAll(set)) {
                    return true;
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public final int hashCode() {
        return zzar.zza(this);
    }

    /* renamed from: zzd */
    public abstract zzas iterator();

    public final zzaf zzf() {
        zzaf zzaf = this.zza;
        if (zzaf != null) {
            return zzaf;
        }
        zzaf zzg = zzg();
        this.zza = zzg;
        return zzg;
    }

    /* access modifiers changed from: package-private */
    public zzaf zzg() {
        Object[] array = toArray();
        int i = zzaf.zzd;
        return zzaf.zzg(array, array.length);
    }
}
