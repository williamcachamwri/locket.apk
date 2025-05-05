package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzyn extends zzwj {
    public static final zzwk zza = new zzym();
    private final Class zzb;
    private final zzwj zzc;

    public zzyn(zzvr zzvr, zzwj zzwj, Class cls) {
        this.zzc = new zzzw(zzvr, zzwj, cls);
        this.zzb = cls;
    }

    public final Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        zzacc.zzi();
        while (zzacc.zzp()) {
            arrayList.add(this.zzc.read(zzacc));
        }
        zzacc.zzk();
        int size = arrayList.size();
        if (!this.zzb.isPrimitive()) {
            return arrayList.toArray((Object[]) Array.newInstance(this.zzb, size));
        }
        Object newInstance = Array.newInstance(this.zzb, size);
        for (int i = 0; i < size; i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    public final void write(zzace zzace, Object obj) throws IOException {
        if (obj == null) {
            zzace.zzg();
            return;
        }
        zzace.zzb();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.zzc.write(zzace, Array.get(obj, i));
        }
        zzace.zzd();
    }
}
