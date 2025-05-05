package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import io.sentry.Session;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzjd implements zzpg {
    private final zznv zza;
    private final zzok zzb;
    private final zzjq zzc;
    private final zzjc zzd;
    private final zzim zze;
    private final zzjs zzf;
    private final zzjk zzg;
    private final zzjb zzh;

    zzjd(zznv zznv, zzok zzok, zzjq zzjq, zzjc zzjc, zzim zzim, zzjs zzjs, zzjk zzjk, zzjb zzjb) {
        this.zza = zznv;
        this.zzb = zzok;
        this.zzc = zzjq;
        this.zzd = zzjc;
        this.zze = zzim;
        this.zzf = zzjs;
        this.zzg = zzjk;
        this.zzh = zzjb;
    }

    private final Map zze() {
        HashMap hashMap = new HashMap();
        zznv zznv = this.zza;
        zzbp zzb2 = this.zzb.zzb();
        hashMap.put("v", zznv.zzd());
        hashMap.put("gms", Boolean.valueOf(this.zza.zzg()));
        hashMap.put("int", zzb2.zzg());
        hashMap.put("up", Boolean.valueOf(this.zzd.zza()));
        hashMap.put("t", new Throwable());
        zzjk zzjk = this.zzg;
        if (zzjk != null) {
            hashMap.put("tcq", Long.valueOf(zzjk.zzc()));
            hashMap.put("tpq", Long.valueOf(this.zzg.zzg()));
            hashMap.put("tcv", Long.valueOf(this.zzg.zzd()));
            hashMap.put("tpv", Long.valueOf(this.zzg.zzh()));
            hashMap.put("tchv", Long.valueOf(this.zzg.zzb()));
            hashMap.put("tphv", Long.valueOf(this.zzg.zzf()));
            hashMap.put("tcc", Long.valueOf(this.zzg.zza()));
            hashMap.put("tpc", Long.valueOf(this.zzg.zze()));
        }
        return hashMap;
    }

    public final Map zza() {
        zzjq zzjq = this.zzc;
        Map zze2 = zze();
        zze2.put("lts", Long.valueOf(zzjq.zza()));
        return zze2;
    }

    public final Map zzb() {
        Map zze2 = zze();
        zzbp zza2 = this.zzb.zza();
        zze2.put("gai", Boolean.valueOf(this.zza.zzh()));
        zze2.put(Session.JsonKeys.DID, zza2.zzf());
        zze2.put("dst", Integer.valueOf(zza2.zzal() - 1));
        zze2.put("doo", Boolean.valueOf(zza2.zzai()));
        zzim zzim = this.zze;
        if (zzim != null) {
            zze2.put("nt", Long.valueOf(zzim.zza()));
        }
        zzjs zzjs = this.zzf;
        if (zzjs != null) {
            zze2.put("vs", Long.valueOf(zzjs.zzc()));
            zze2.put("vf", Long.valueOf(this.zzf.zzb()));
        }
        return zze2;
    }

    public final Map zzc() {
        zzjb zzjb = this.zzh;
        Map zze2 = zze();
        if (zzjb != null) {
            zze2.put("vst", zzjb.zza());
        }
        return zze2;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(View view) {
        this.zzc.zzd(view);
    }
}
