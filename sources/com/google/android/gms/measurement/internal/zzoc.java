package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzoc implements zzgu {
    private final /* synthetic */ String zza;
    private final /* synthetic */ List zzb;
    private final /* synthetic */ zznv zzc;

    zzoc(zznv zznv, String str, List list) {
        this.zza = str;
        this.zzb = list;
        this.zzc = zznv;
    }

    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zzc.zza(true, i, th, bArr, this.zza, this.zzb);
    }
}
