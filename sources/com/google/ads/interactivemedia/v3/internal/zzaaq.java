package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaaq extends zzwj {
    zzaaq() {
    }

    private static final zzvw zzb(zzacc zzacc, int i) throws IOException {
        int i2 = i - 1;
        if (i2 == 5) {
            return new zzwb(zzacc.zzh());
        }
        if (i2 == 6) {
            return new zzwb((Number) new zzxq(zzacc.zzh()));
        }
        if (i2 == 7) {
            return new zzwb(Boolean.valueOf(zzacc.zzq()));
        }
        if (i2 == 8) {
            zzacc.zzm();
            return zzvy.zza;
        }
        throw new IllegalStateException("Unexpected token: ".concat(zzacd.zza(i)));
    }

    private static final zzvw zzc(zzacc zzacc, int i) throws IOException {
        int i2 = i - 1;
        if (i2 == 0) {
            zzacc.zzi();
            return new zzvu();
        } else if (i2 != 2) {
            return null;
        } else {
            zzacc.zzj();
            return new zzvz();
        }
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc instanceof zzyz) {
            return ((zzyz) zzacc).zzd();
        }
        int zzr = zzacc.zzr();
        zzvw zzc = zzc(zzacc, zzr);
        if (zzc == null) {
            return zzb(zzacc, zzr);
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        while (true) {
            if (zzacc.zzp()) {
                String zzg = zzc instanceof zzvz ? zzacc.zzg() : null;
                int zzr2 = zzacc.zzr();
                zzvw zzc2 = zzc(zzacc, zzr2);
                zzvw zzb = zzc2 == null ? zzb(zzacc, zzr2) : zzc2;
                if (zzc instanceof zzvu) {
                    ((zzvu) zzc).zza(zzb);
                } else {
                    ((zzvz) zzc).zzb(zzg, zzb);
                }
                if (zzc2 != null) {
                    arrayDeque.addLast(zzc);
                    zzc = zzb;
                }
            } else {
                if (zzc instanceof zzvu) {
                    zzacc.zzk();
                } else {
                    zzacc.zzl();
                }
                if (arrayDeque.isEmpty()) {
                    return zzc;
                }
                zzc = (zzvw) arrayDeque.removeLast();
            }
        }
    }

    /* renamed from: zza */
    public final void write(zzace zzace, zzvw zzvw) throws IOException {
        if (zzvw == null || (zzvw instanceof zzvy)) {
            zzace.zzg();
        } else if (zzvw instanceof zzwb) {
            zzwb zzwb = (zzwb) zzvw;
            if (zzwb.zzj()) {
                zzace.zzk(zzwb.zzd());
            } else if (zzwb.zzi()) {
                zzace.zzm(zzwb.zzh());
            } else {
                zzace.zzl(zzwb.zze());
            }
        } else if (zzvw instanceof zzvu) {
            zzace.zzb();
            Iterator it = ((zzvu) zzvw).iterator();
            while (it.hasNext()) {
                write(zzace, (zzvw) it.next());
            }
            zzace.zzd();
        } else if (zzvw instanceof zzvz) {
            zzace.zzc();
            for (Map.Entry entry : ((zzvz) zzvw).zza()) {
                zzace.zzf((String) entry.getKey());
                write(zzace, (zzvw) entry.getValue());
            }
            zzace.zze();
        } else {
            throw new IllegalArgumentException("Couldn't write ".concat(String.valueOf(String.valueOf(zzvw.getClass()))));
        }
    }
}
