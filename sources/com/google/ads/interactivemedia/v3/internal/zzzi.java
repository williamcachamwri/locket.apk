package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzzi extends zzwj {
    private static final zzwk zza = new zzzg(1);
    private final zzvr zzb;
    private final int zzc;

    /* synthetic */ zzzi(zzvr zzvr, int i, zzzh zzzh) {
        this.zzb = zzvr;
        this.zzc = i;
    }

    public static zzwk zza(int i) {
        return i == 1 ? zza : new zzzg(0);
    }

    private final Object zzb(zzacc zzacc, int i) throws IOException {
        int i2 = i - 1;
        if (i2 == 5) {
            return zzacc.zzh();
        }
        if (i2 == 6) {
            return zzwh.zza(this.zzc, zzacc);
        }
        if (i2 == 7) {
            return Boolean.valueOf(zzacc.zzq());
        }
        if (i2 == 8) {
            zzacc.zzm();
            return null;
        }
        throw new IllegalStateException("Unexpected token: ".concat(zzacd.zza(i)));
    }

    private static final Object zzc(zzacc zzacc, int i) throws IOException {
        int i2 = i - 1;
        if (i2 == 0) {
            zzacc.zzi();
            return new ArrayList();
        } else if (i2 != 2) {
            return null;
        } else {
            zzacc.zzj();
            return new zzxy();
        }
    }

    public final Object read(zzacc zzacc) throws IOException {
        int zzr = zzacc.zzr();
        Object zzc2 = zzc(zzacc, zzr);
        if (zzc2 == null) {
            return zzb(zzacc, zzr);
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        while (true) {
            if (zzacc.zzp()) {
                String zzg = zzc2 instanceof Map ? zzacc.zzg() : null;
                int zzr2 = zzacc.zzr();
                Object zzc3 = zzc(zzacc, zzr2);
                Object zzb2 = zzc3 == null ? zzb(zzacc, zzr2) : zzc3;
                if (zzc2 instanceof List) {
                    ((List) zzc2).add(zzb2);
                } else {
                    ((Map) zzc2).put(zzg, zzb2);
                }
                if (zzc3 != null) {
                    arrayDeque.addLast(zzc2);
                    zzc2 = zzb2;
                }
            } else {
                if (zzc2 instanceof List) {
                    zzacc.zzk();
                } else {
                    zzacc.zzl();
                }
                if (arrayDeque.isEmpty()) {
                    return zzc2;
                }
                zzc2 = arrayDeque.removeLast();
            }
        }
    }

    public final void write(zzace zzace, Object obj) throws IOException {
        if (obj == null) {
            zzace.zzg();
            return;
        }
        zzwj zza2 = this.zzb.zza(zzaca.zza(obj.getClass()));
        if (zza2 instanceof zzzi) {
            zzace.zzc();
            zzace.zze();
            return;
        }
        zza2.write(zzace, obj);
    }
}
