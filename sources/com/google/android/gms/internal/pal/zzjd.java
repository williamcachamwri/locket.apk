package com.google.android.gms.internal.pal;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzjd extends zziw implements Set {
    @CheckForNull
    private transient zziz zza;

    zzjd() {
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
        return zzjk.zza(this);
    }

    /* renamed from: zzd */
    public abstract zzjl iterator();

    public final zziz zzf() {
        zziz zziz = this.zza;
        if (zziz != null) {
            return zziz;
        }
        zziz zzg = zzg();
        this.zza = zzg;
        return zzg;
    }

    /* access modifiers changed from: package-private */
    public zziz zzg() {
        return zziz.zzg(toArray());
    }
}
