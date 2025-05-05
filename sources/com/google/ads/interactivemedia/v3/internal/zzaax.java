package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaax extends zzwj {
    final /* synthetic */ Class zza;
    final /* synthetic */ zzaay zzb;

    zzaax(zzaay zzaay, Class cls) {
        this.zza = cls;
        this.zzb = zzaay;
    }

    public final Object read(zzacc zzacc) throws IOException {
        Object read = this.zzb.zzb.read(zzacc);
        if (read == null || this.zza.isInstance(read)) {
            return read;
        }
        Class cls = this.zza;
        Class<?> cls2 = read.getClass();
        String name = cls.getName();
        String name2 = cls2.getName();
        String zzf = zzacc.zzf();
        throw new zzwe("Expected a " + name + " but was " + name2 + "; at path " + zzf);
    }

    public final void write(zzace zzace, Object obj) throws IOException {
        this.zzb.zzb.write(zzace, obj);
    }
}
