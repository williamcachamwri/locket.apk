package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzoe implements zzgu {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzoj zzb;
    private final /* synthetic */ zznv zzc;

    zzoe(zznv zznv, String str, zzoj zzoj) {
        this.zza = str;
        this.zzb = zzoj;
        this.zzc = zznv;
    }

    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zzc.zza(this.zza, i, th, bArr, this.zzb);
    }
}
