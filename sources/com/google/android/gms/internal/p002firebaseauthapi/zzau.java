package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzau  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public abstract class zzau<E> extends zzak<E> implements Set<E> {
    @CheckForNull
    private transient zzal<E> zza;

    public int hashCode() {
        return zzaz.zza(this);
    }

    public zzal<E> zzc() {
        zzal<E> zzal = this.zza;
        if (zzal != null) {
            return zzal;
        }
        zzal<E> zzg = zzg();
        this.zza = zzg;
        return zzg;
    }

    /* access modifiers changed from: package-private */
    public zzal<E> zzg() {
        return zzal.zza(toArray());
    }

    public /* synthetic */ Iterator iterator() {
        return iterator();
    }

    zzau() {
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        return zzaz.zza(this, obj);
    }
}
