package com.google.android.gms.internal.fido;

import java.util.AbstractMap;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
final class zzbe extends zzaz {
    final /* synthetic */ zzbf zza;

    zzbe(zzbf zzbf) {
        this.zza = zzbf;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return new AbstractMap.SimpleImmutableEntry(this.zza.zza.zze.zzd.get(i), this.zza.zza.zzf.get(i));
    }

    public final int size() {
        return this.zza.zza.size();
    }
}
