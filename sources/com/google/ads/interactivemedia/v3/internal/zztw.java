package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.zztg;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
class zztw extends zztg.zzi {
    private static final zzts zza;
    private static final zzut zzd;
    /* access modifiers changed from: private */
    public volatile int remaining;
    @CheckForNull
    private volatile Set<Throwable> seenExceptions = null;

    static {
        Throwable th;
        zzts zzts;
        Class<zztw> cls = zztw.class;
        zzd = new zzut(cls);
        try {
            zzts = new zztt(AtomicReferenceFieldUpdater.newUpdater(cls, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(cls, "remaining"));
            th = null;
        } catch (Throwable th2) {
            zzts = new zztv((zztu) null);
            th = th2;
        }
        zza = zzts;
        if (th != null) {
            zzd.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFutureState", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
    }

    zztw(int i) {
        this.remaining = i;
    }

    /* access modifiers changed from: package-private */
    public final int zzx() {
        return zza.zza(this);
    }

    /* access modifiers changed from: package-private */
    public final void zzz() {
        this.seenExceptions = null;
    }
}
