package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLongArray;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzvp extends zzwj {
    final /* synthetic */ zzwj zza;

    zzvp(zzwj zzwj) {
        this.zza = zzwj;
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        ArrayList arrayList = new ArrayList();
        zzacc.zzi();
        while (zzacc.zzp()) {
            arrayList.add(Long.valueOf(((Number) this.zza.read(zzacc)).longValue()));
        }
        zzacc.zzk();
        int size = arrayList.size();
        AtomicLongArray atomicLongArray = new AtomicLongArray(size);
        for (int i = 0; i < size; i++) {
            atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
        }
        return atomicLongArray;
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
        zzace.zzb();
        int length = atomicLongArray.length();
        for (int i = 0; i < length; i++) {
            this.zza.write(zzace, Long.valueOf(atomicLongArray.get(i)));
        }
        zzace.zzd();
    }
}
