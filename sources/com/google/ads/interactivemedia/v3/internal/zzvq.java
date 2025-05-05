package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzvq<T> extends zzzr<T> {
    private zzwj zza = null;

    zzvq() {
    }

    private final zzwj zzc() {
        zzwj zzwj = this.zza;
        if (zzwj != null) {
            return zzwj;
        }
        throw new IllegalStateException("Adapter for type with cyclic dependency has been used before dependency has been resolved");
    }

    public final T read(zzacc zzacc) throws IOException {
        return zzc().read(zzacc);
    }

    public final void write(zzace zzace, T t) throws IOException {
        zzc().write(zzace, t);
    }

    public final zzwj zza() {
        return zzc();
    }

    public final void zzb(zzwj zzwj) {
        if (this.zza == null) {
            this.zza = zzwj;
            return;
        }
        throw new AssertionError("Delegate is already set");
    }
}
