package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzzx extends zzwj {
    zzzx() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        ArrayList arrayList = new ArrayList();
        zzacc.zzi();
        while (zzacc.zzp()) {
            try {
                arrayList.add(Integer.valueOf(zzacc.zzb()));
            } catch (NumberFormatException e) {
                throw new zzwe((Throwable) e);
            }
        }
        zzacc.zzk();
        int size = arrayList.size();
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
        for (int i = 0; i < size; i++) {
            atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
        }
        return atomicIntegerArray;
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
        zzace.zzb();
        int length = atomicIntegerArray.length();
        for (int i = 0; i < length; i++) {
            zzace.zzi((long) atomicIntegerArray.get(i));
        }
        zzace.zzd();
    }
}
