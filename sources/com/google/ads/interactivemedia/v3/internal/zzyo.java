package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzyo extends zzwj {
    private final zzwj zza;
    private final zzya zzb;

    public zzyo(zzvr zzvr, Type type, zzwj zzwj, zzya zzya) {
        this.zza = new zzzw(zzvr, zzwj, type);
        this.zzb = zzya;
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        Collection collection = (Collection) this.zzb.zza();
        zzacc.zzi();
        while (zzacc.zzp()) {
            collection.add(this.zza.read(zzacc));
        }
        zzacc.zzk();
        return collection;
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        Collection<Object> collection = (Collection) obj;
        if (collection == null) {
            zzace.zzg();
            return;
        }
        zzace.zzb();
        for (Object write : collection) {
            this.zza.write(zzace, write);
        }
        zzace.zzd();
    }
}
