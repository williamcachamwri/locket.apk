package com.google.android.gms.internal.pal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzacr {
    private static final zzacr zzb = new zzacr(true);
    final zzafe zza = new zzaeu(16);
    private boolean zzc;
    private boolean zzd;

    private zzacr() {
    }

    public static zzacr zza() {
        throw null;
    }

    private static final void zzd(zzacq zzacq, Object obj) {
        boolean z;
        zzafy zzb2 = zzacq.zzb();
        zzadg.zze(obj);
        zzafy zzafy = zzafy.DOUBLE;
        zzafz zzafz = zzafz.INT;
        switch (zzb2.zza().ordinal()) {
            case 0:
                z = obj instanceof Integer;
                break;
            case 1:
                z = obj instanceof Long;
                break;
            case 2:
                z = obj instanceof Float;
                break;
            case 3:
                z = obj instanceof Double;
                break;
            case 4:
                z = obj instanceof Boolean;
                break;
            case 5:
                z = obj instanceof String;
                break;
            case 6:
                if ((obj instanceof zzaby) || (obj instanceof byte[])) {
                    return;
                }
            case 7:
                if ((obj instanceof Integer) || (obj instanceof zzadb)) {
                    return;
                }
            case 8:
                if ((obj instanceof zzaef) || (obj instanceof zzadk)) {
                    return;
                }
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new Object[]{Integer.valueOf(zzacq.zza()), zzacq.zzb().zza(), obj.getClass().getName()}));
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzacr zzacr = new zzacr();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzacr.zzc((zzacq) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzacr.zzc((zzacq) entry.getKey(), entry.getValue());
        }
        zzacr.zzd = this.zzd;
        return zzacr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzacr)) {
            return false;
        }
        return this.zza.equals(((zzacr) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (!this.zzc) {
            this.zza.zza();
            this.zzc = true;
        }
    }

    public final void zzc(zzacq zzacq, Object obj) {
        if (!zzacq.zzc()) {
            zzd(zzacq, obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzd(zzacq, arrayList.get(i));
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzadk) {
            this.zzd = true;
        }
        this.zza.put(zzacq, obj);
    }

    private zzacr(boolean z) {
        zzb();
        zzb();
    }
}
