package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzzl<T, A> extends zzwj<T> {
    private final zzzo zza;

    zzzl(zzzo zzzo) {
        this.zza = zzzo;
    }

    public final T read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        Object zza2 = zza();
        Map map = this.zza.zzb;
        try {
            zzacc.zzj();
            while (zzacc.zzp()) {
                zzzm zzzm = (zzzm) map.get(zzacc.zzg());
                if (zzzm == null) {
                    zzacc.zzo();
                } else {
                    zzc(zza2, zzacc, zzzm);
                }
            }
            zzacc.zzl();
            return zzb(zza2);
        } catch (IllegalStateException e) {
            throw new zzwe((Throwable) e);
        } catch (IllegalAccessException e2) {
            throw zzabp.zzb(e2);
        }
    }

    public final void write(zzace zzace, T t) throws IOException {
        if (t == null) {
            zzace.zzg();
            return;
        }
        zzace.zzc();
        try {
            for (zzzm zzc : this.zza.zzc) {
                zzc.zzc(zzace, t);
            }
            zzace.zze();
        } catch (IllegalAccessException e) {
            throw zzabp.zzb(e);
        }
    }

    /* access modifiers changed from: package-private */
    public abstract Object zza();

    /* access modifiers changed from: package-private */
    public abstract Object zzb(Object obj);

    /* access modifiers changed from: package-private */
    public abstract void zzc(Object obj, zzacc zzacc, zzzm zzzm) throws IllegalAccessException, IOException;
}
