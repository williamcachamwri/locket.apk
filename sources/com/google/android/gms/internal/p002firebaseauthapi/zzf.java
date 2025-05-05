package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
abstract class zzf<T> implements Iterator<T> {
    private int zza = 2;
    @CheckForNull
    private T zzb;

    /* access modifiers changed from: protected */
    @CheckForNull
    public abstract T zza();

    /* access modifiers changed from: protected */
    @CheckForNull
    public final T zzb() {
        this.zza = 3;
        return null;
    }

    public final T next() {
        if (hasNext()) {
            this.zza = 2;
            T t = this.zzb;
            this.zzb = null;
            return t;
        }
        throw new NoSuchElementException();
    }

    protected zzf() {
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        int i = this.zza;
        if (i != 4) {
            int i2 = i - 1;
            if (i2 == 0) {
                return true;
            }
            if (i2 != 2) {
                this.zza = 4;
                this.zzb = zza();
                if (this.zza != 3) {
                    this.zza = 1;
                    return true;
                }
            }
            return false;
        }
        throw new IllegalStateException();
    }
}
