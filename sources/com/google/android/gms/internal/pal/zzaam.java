package com.google.android.gms.internal.pal;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzaam extends zzzg {
    zzaam() {
    }

    private static final zzyy zze(zzabc zzabc, int i) throws IOException {
        int i2 = i - 1;
        if (i2 == 5) {
            return new zzzd(zzabc.zzd());
        }
        if (i2 == 6) {
            return new zzzd((Number) new zzzj(zzabc.zzd()));
        }
        if (i2 == 7) {
            return new zzzd(Boolean.valueOf(zzabc.zzk()));
        }
        if (i2 == 8) {
            zzabc.zzi();
            return zzza.zza;
        }
        zzabd.zza(i);
        throw new IllegalStateException("Unexpected token: ".concat(zzabd.zza(i)));
    }

    private static final zzyy zzf(zzabc zzabc, int i) throws IOException {
        int i2 = i - 1;
        if (i2 == 0) {
            zzabc.zze();
            return new zzyx();
        } else if (i2 != 2) {
            return null;
        } else {
            zzabc.zzf();
            return new zzzb();
        }
    }

    public final /* bridge */ /* synthetic */ Object zza(zzabc zzabc) throws IOException {
        int zzl = zzabc.zzl();
        zzyy zzf = zzf(zzabc, zzl);
        if (zzf == null) {
            return zze(zzabc, zzl);
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        while (true) {
            if (zzabc.zzj()) {
                String zzc = zzf instanceof zzzb ? zzabc.zzc() : null;
                int zzl2 = zzabc.zzl();
                zzyy zzf2 = zzf(zzabc, zzl2);
                zzyy zze = zzf2 == null ? zze(zzabc, zzl2) : zzf2;
                if (zzf instanceof zzyx) {
                    ((zzyx) zzf).zze(zze);
                } else {
                    ((zzzb) zzf).zzh(zzc, zze);
                }
                if (zzf2 != null) {
                    arrayDeque.addLast(zzf);
                    zzf = zze;
                }
            } else {
                if (zzf instanceof zzyx) {
                    zzabc.zzg();
                } else {
                    zzabc.zzh();
                }
                if (arrayDeque.isEmpty()) {
                    return zzf;
                }
                zzf = (zzyy) arrayDeque.removeLast();
            }
        }
    }

    /* renamed from: zzd */
    public final void zzb(zzabe zzabe, zzyy zzyy) throws IOException {
        if (zzyy == null || (zzyy instanceof zzza)) {
            zzabe.zzf();
        } else if (zzyy instanceof zzzd) {
            zzzd zzzd = (zzzd) zzyy;
            if (zzzd.zzg()) {
                zzabe.zzg(zzzd.zzb());
            } else if (zzzd.zze()) {
                zzabe.zzi(zzzd.zzc());
            } else {
                zzabe.zzh(zzzd.zzd());
            }
        } else if (zzyy instanceof zzyx) {
            zzabe.zza();
            Iterator it = ((zzyx) zzyy).iterator();
            while (it.hasNext()) {
                zzb(zzabe, (zzyy) it.next());
            }
            zzabe.zzc();
        } else if (zzyy instanceof zzzb) {
            zzabe.zzb();
            for (Map.Entry entry : zzyy.zzf().zzg()) {
                zzabe.zze((String) entry.getKey());
                zzb(zzabe, (zzyy) entry.getValue());
            }
            zzabe.zzd();
        } else {
            StringBuilder sb = new StringBuilder("Couldn't write ");
            Class<?> cls = zzyy.getClass();
            sb.append(cls);
            throw new IllegalArgumentException("Couldn't write ".concat(String.valueOf(cls)));
        }
    }
}
