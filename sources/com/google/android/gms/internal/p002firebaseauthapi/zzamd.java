package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzamd implements Iterator<Map.Entry<K, V>> {
    private int zza;
    private boolean zzb;
    private Iterator<Map.Entry<K, V>> zzc;
    private final /* synthetic */ zzalw zzd;

    public final /* synthetic */ Object next() {
        this.zzb = true;
        int i = this.zza + 1;
        this.zza = i;
        if (i < this.zzd.zzb) {
            return (zzama) this.zzd.zza[this.zza];
        }
        return (Map.Entry) zza().next();
    }

    private final Iterator<Map.Entry<K, V>> zza() {
        if (this.zzc == null) {
            this.zzc = this.zzd.zzc.entrySet().iterator();
        }
        return this.zzc;
    }

    private zzamd(zzalw zzalw) {
        this.zzd = zzalw;
        this.zza = -1;
    }

    public final void remove() {
        if (this.zzb) {
            this.zzb = false;
            this.zzd.zzg();
            if (this.zza < this.zzd.zzb) {
                zzalw zzalw = this.zzd;
                int i = this.zza;
                this.zza = i - 1;
                Object unused = zzalw.zzb(i);
                return;
            }
            zza().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    public final boolean hasNext() {
        if (this.zza + 1 < this.zzd.zzb || (!this.zzd.zzc.isEmpty() && zza().hasNext())) {
            return true;
        }
        return false;
    }
}
