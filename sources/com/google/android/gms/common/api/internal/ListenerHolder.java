package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class ListenerHolder<L> {
    private final Executor zaa;
    private volatile Object zab;
    private volatile ListenerKey zac;

    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public static final class ListenerKey<L> {
        private final Object zaa;
        private final String zab;

        ListenerKey(L l, String str) {
            this.zaa = l;
            this.zab = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ListenerKey)) {
                return false;
            }
            ListenerKey listenerKey = (ListenerKey) obj;
            return this.zaa == listenerKey.zaa && this.zab.equals(listenerKey.zab);
        }

        public int hashCode() {
            return (System.identityHashCode(this.zaa) * 31) + this.zab.hashCode();
        }

        public String toIdString() {
            int identityHashCode = System.identityHashCode(this.zaa);
            return this.zab + "@" + identityHashCode;
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public interface Notifier<L> {
        void notifyListener(L l);

        void onNotifyListenerFailed();
    }

    ListenerHolder(Looper looper, L l, String str) {
        this.zaa = new HandlerExecutor(looper);
        this.zab = Preconditions.checkNotNull(l, "Listener must not be null");
        this.zac = new ListenerKey(l, Preconditions.checkNotEmpty(str));
    }

    public void clear() {
        this.zab = null;
        this.zac = null;
    }

    public ListenerKey<L> getListenerKey() {
        return this.zac;
    }

    public boolean hasListener() {
        return this.zab != null;
    }

    public void notifyListener(Notifier<? super L> notifier) {
        Preconditions.checkNotNull(notifier, "Notifier must not be null");
        this.zaa.execute(new zacb(this, notifier));
    }

    /* access modifiers changed from: package-private */
    public final void zaa(Notifier notifier) {
        Object obj = this.zab;
        if (obj == null) {
            notifier.onNotifyListenerFailed();
            return;
        }
        try {
            notifier.notifyListener(obj);
        } catch (RuntimeException e) {
            notifier.onNotifyListenerFailed();
            throw e;
        }
    }

    ListenerHolder(Executor executor, L l, String str) {
        this.zaa = (Executor) Preconditions.checkNotNull(executor, "Executor must not be null");
        this.zab = Preconditions.checkNotNull(l, "Listener must not be null");
        this.zac = new ListenerKey(l, Preconditions.checkNotEmpty(str));
    }
}
