package com.google.ads.interactivemedia.v3.internal;

import android.os.Build;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzfj {
    private final Map zza = new LinkedHashMap();
    private final zzaha zzb;
    private final int zzc;
    private int zzd = 0;
    private zzahj zze;

    public zzfj(int i) {
        this.zzc = i;
        zzaha zzc2 = zzahb.zzc();
        zzc2.zzc(Build.MODEL);
        zzc2.zzb(Build.MANUFACTURER);
        zzc2.zza(Build.VERSION.RELEASE);
        this.zzb = zzc2;
        this.zze = zzahk.zzd();
    }

    public final zzqf zza(String str) {
        if (!this.zza.containsKey(str)) {
            return zzqf.zzf();
        }
        zzahj zzahj = ((zzfi) this.zza.get(str)).zza;
        int i = this.zzc;
        zzahd zzc2 = zzahe.zzc();
        zzc2.zza(i);
        zzc2.zzd(((zzfi) this.zza.get(str)).zzb);
        zzc2.zzb(this.zzb);
        zzahj.zzaj((zzahk) this.zze.zzal());
        zzahj zzahj2 = zzahj;
        zzc2.zzc(zzahj);
        return zzqf.zzh((zzahe) zzc2.zzal());
    }

    public final zzahj zzb() {
        return this.zze;
    }

    public final zzahj zzc(String str) {
        if (!this.zza.containsKey(str)) {
            Map map = this.zza;
            int i = this.zzd;
            this.zzd = i + 1;
            map.put(str, new zzfi(i));
        }
        return ((zzfi) this.zza.get(str)).zza;
    }

    public final void zzd() {
        this.zza.clear();
        this.zzd = 0;
    }

    public final void zze(String str) {
        this.zza.remove(str);
    }

    public final void zzf(zzahj zzahj) {
        this.zze = zzahj;
    }
}
