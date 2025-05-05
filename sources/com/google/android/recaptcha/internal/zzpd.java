package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzpd implements Iterator {
    final /* synthetic */ zzpg zza;
    private int zzb = -1;
    private boolean zzc;
    private Iterator zzd;

    /* synthetic */ zzpd(zzpg zzpg, zzpf zzpf) {
        this.zza = zzpg;
    }

    private final Iterator zza() {
        if (this.zzd == null) {
            this.zzd = this.zza.zzc.entrySet().iterator();
        }
        return this.zzd;
    }

    public final boolean hasNext() {
        int i = this.zzb + 1;
        zzpg zzpg = this.zza;
        if (i < zzpg.zzb) {
            return true;
        }
        if (!zzpg.zzc.isEmpty()) {
            return zza().hasNext();
        }
        return false;
    }

    public final /* bridge */ /* synthetic */ Object next() {
        this.zzc = true;
        int i = this.zzb + 1;
        this.zzb = i;
        zzpg zzpg = this.zza;
        if (i < zzpg.zzb) {
            return (zzpc) zzpg.zza[i];
        }
        return (Map.Entry) zza().next();
    }

    public final void remove() {
        if (this.zzc) {
            this.zzc = false;
            this.zza.zzo();
            int i = this.zzb;
            zzpg zzpg = this.zza;
            if (i < zzpg.zzb) {
                this.zzb = i - 1;
                Object unused = zzpg.zzm(i);
                return;
            }
            zza().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
