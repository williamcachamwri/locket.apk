package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zztt extends zzts {
    final AtomicIntegerFieldUpdater zza;

    zztt(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
        super((zztr) null);
        this.zza = atomicIntegerFieldUpdater;
    }

    /* access modifiers changed from: package-private */
    public final int zza(zztw zztw) {
        return this.zza.decrementAndGet(zztw);
    }
}
