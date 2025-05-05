package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzzc extends zzwj {
    private final zzwj zza;
    private final zzwj zzb;
    private final zzya zzc;

    public zzzc(zzzd zzzd, zzvr zzvr, Type type, zzwj zzwj, Type type2, zzwj zzwj2, zzya zzya) {
        this.zza = new zzzw(zzvr, zzwj, type);
        this.zzb = new zzzw(zzvr, zzwj2, type2);
        this.zzc = zzya;
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        int zzr = zzacc.zzr();
        if (zzr == 9) {
            zzacc.zzm();
            return null;
        }
        Map map = (Map) this.zzc.zza();
        if (zzr == 1) {
            zzacc.zzi();
            while (zzacc.zzp()) {
                zzacc.zzi();
                Object read = this.zza.read(zzacc);
                if (map.put(read, this.zzb.read(zzacc)) == null) {
                    zzacc.zzk();
                } else {
                    throw new zzwe("duplicate key: ".concat(String.valueOf(String.valueOf(read))));
                }
            }
            zzacc.zzk();
        } else {
            zzacc.zzj();
            while (zzacc.zzp()) {
                zzxp.zza.zza(zzacc);
                Object read2 = this.zza.read(zzacc);
                if (map.put(read2, this.zzb.read(zzacc)) != null) {
                    throw new zzwe("duplicate key: ".concat(String.valueOf(String.valueOf(read2))));
                }
            }
            zzacc.zzl();
        }
        return map;
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        Map map = (Map) obj;
        if (map == null) {
            zzace.zzg();
            return;
        }
        zzace.zzc();
        for (Map.Entry entry : map.entrySet()) {
            zzace.zzf(String.valueOf(entry.getKey()));
            this.zzb.write(zzace, entry.getValue());
        }
        zzace.zze();
    }
}
