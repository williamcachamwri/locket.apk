package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzzw<T> extends zzwj<T> {
    private final zzvr zza;
    private final zzwj zzb;
    private final Type zzc;

    zzzw(zzvr zzvr, zzwj zzwj, Type type) {
        this.zza = zzvr;
        this.zzb = zzwj;
        this.zzc = type;
    }

    public final T read(zzacc zzacc) throws IOException {
        return this.zzb.read(zzacc);
    }

    public final void write(zzace zzace, T t) throws IOException {
        zzwj zza2;
        Type type = this.zzc;
        Type type2 = (t == null || (!(type instanceof Class) && !(type instanceof TypeVariable))) ? type : t.getClass();
        zzwj zzwj = this.zzb;
        if (type2 != type) {
            zzwj = this.zza.zza(zzaca.zzb(type2));
            if (zzwj instanceof zzzl) {
                zzwj zzwj2 = this.zzb;
                while ((zzwj2 instanceof zzzr) && (zza2 = ((zzzr) zzwj2).zza()) != zzwj2) {
                    zzwj2 = zza2;
                }
                if (!(zzwj2 instanceof zzzl)) {
                    zzwj = this.zzb;
                }
            }
        }
        zzwj.write(zzace, t);
    }
}
