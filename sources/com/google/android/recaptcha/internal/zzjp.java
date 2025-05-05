package com.google.android.recaptcha.internal;

import java.util.Collection;
import java.util.Queue;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public abstract class zzjp extends zzjn implements Queue {
    protected zzjp() {
    }

    public final Object element() {
        return zzd().element();
    }

    public boolean offer(Object obj) {
        return zzd().offer(obj);
    }

    @CheckForNull
    public final Object peek() {
        return zzd().peek();
    }

    @CheckForNull
    public final Object poll() {
        return zzd().poll();
    }

    public final Object remove() {
        return zzd().remove();
    }

    /* access modifiers changed from: protected */
    public /* bridge */ /* synthetic */ Collection zzc() {
        throw null;
    }

    /* access modifiers changed from: protected */
    public abstract Queue zzd();
}
