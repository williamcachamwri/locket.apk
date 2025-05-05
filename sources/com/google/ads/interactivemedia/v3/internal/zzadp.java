package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzadp {
    private static final zzadp zzb = new zzadp(true);
    final zzagd zza = new zzafw();
    private boolean zzc;
    private boolean zzd;

    private zzadp() {
    }

    static int zza(zzags zzags, int i, Object obj) {
        zzadf.zzz(i << 3);
        if (zzags.GROUP == null) {
            zzafb zzafb = (zzafb) obj;
            byte[] bArr = zzaee.zzb;
            if (zzafb instanceof zzaci) {
                zzaci zzaci = (zzaci) zzafb;
                throw null;
            }
        }
        zzagt zzagt = zzagt.INT;
        throw null;
    }

    public static int zzb(zzado zzado, Object obj) {
        zzags zzb2 = zzado.zzb();
        int zza2 = zzado.zza();
        if (!zzado.zze()) {
            return zza(zzb2, zza2, obj);
        }
        List list = (List) obj;
        int size = list.size();
        if (!zzado.zzd()) {
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zza(zzb2, zza2, list.get(i2));
            }
            return i;
        } else if (list.isEmpty()) {
            return 0;
        } else {
            if (size <= 0) {
                return zzadf.zzz(zza2 << 3) + zzadf.zzz(0);
            }
            list.get(0);
            zzags zzags = zzags.DOUBLE;
            zzagt zzagt = zzagt.INT;
            throw null;
        }
    }

    public static zzadp zzd() {
        return zzb;
    }

    private static boolean zzi(Map.Entry entry) {
        zzado zzado = (zzado) entry.getKey();
        if (zzado.zzc() != zzagt.MESSAGE) {
            return true;
        }
        if (!zzado.zze()) {
            return zzj(entry.getValue());
        }
        List list = (List) entry.getValue();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!zzj(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzj(Object obj) {
        if (obj instanceof zzafc) {
            return ((zzafc) obj).zzaT();
        }
        if (obj instanceof zzael) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static final int zzk(Map.Entry entry) {
        int i;
        int zzz;
        int zzz2;
        int zzax;
        int zzz3;
        zzado zzado = (zzado) entry.getKey();
        Object value = entry.getValue();
        if (zzado.zzc() != zzagt.MESSAGE || zzado.zze() || zzado.zzd()) {
            return zzb(zzado, value);
        }
        if (value instanceof zzael) {
            int zza2 = ((zzado) entry.getKey()).zza();
            int zzz4 = zzadf.zzz(8);
            i = zzz4 + zzz4;
            zzz = zzadf.zzz(16) + zzadf.zzz(zza2);
            zzz2 = zzadf.zzz(24);
            zzax = ((zzael) value).zza();
            zzz3 = zzadf.zzz(zzax);
        } else {
            int zza3 = ((zzado) entry.getKey()).zza();
            int zzz5 = zzadf.zzz(8);
            i = zzz5 + zzz5;
            zzz = zzadf.zzz(16) + zzadf.zzz(zza3);
            zzz2 = zzadf.zzz(24);
            zzax = ((zzafb) value).zzax();
            zzz3 = zzadf.zzz(zzax);
        }
        return i + zzz + zzz2 + zzz3 + zzax;
    }

    private static final void zzl(zzado zzado, Object obj) {
        boolean z;
        zzags zzb2 = zzado.zzb();
        byte[] bArr = zzaee.zzb;
        obj.getClass();
        zzags zzags = zzags.DOUBLE;
        zzagt zzagt = zzagt.INT;
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
                if ((obj instanceof zzacw) || (obj instanceof byte[])) {
                    return;
                }
            case 7:
                if ((obj instanceof Integer) || (obj instanceof zzaea)) {
                    return;
                }
            case 8:
                if ((obj instanceof zzafb) || (obj instanceof zzael)) {
                    return;
                }
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new Object[]{Integer.valueOf(zzado.zza()), zzado.zzb().zza(), obj.getClass().getName()}));
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzadp zzadp = new zzadp();
        int zzc2 = this.zza.zzc();
        for (int i = 0; i < zzc2; i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzadp.zzg((zzado) ((zzafx) zzg).zza(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzd()) {
            zzadp.zzg((zzado) entry.getKey(), entry.getValue());
        }
        zzadp.zzd = this.zzd;
        return zzadp;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzadp)) {
            return false;
        }
        return this.zza.equals(((zzadp) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzc() {
        int zzc2 = this.zza.zzc();
        int i = 0;
        for (int i2 = 0; i2 < zzc2; i2++) {
            i += zzk(this.zza.zzg(i2));
        }
        for (Map.Entry zzk : this.zza.zzd()) {
            i += zzk(zzk);
        }
        return i;
    }

    public final Iterator zze() {
        if (this.zza.isEmpty()) {
            return Collections.emptyIterator();
        }
        if (this.zzd) {
            return new zzaek(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    public final void zzf() {
        if (!this.zzc) {
            int zzc2 = this.zza.zzc();
            for (int i = 0; i < zzc2; i++) {
                Map.Entry zzg = this.zza.zzg(i);
                if (zzg.getValue() instanceof zzady) {
                    ((zzady) zzg.getValue()).zzaK();
                }
            }
            this.zza.zza();
            this.zzc = true;
        }
    }

    public final void zzg(zzado zzado, Object obj) {
        if (!zzado.zze()) {
            zzl(zzado, obj);
        } else if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                zzl(zzado, obj2);
                arrayList.add(obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzael) {
            this.zzd = true;
        }
        this.zza.put(zzado, obj);
    }

    public final boolean zzh() {
        int zzc2 = this.zza.zzc();
        for (int i = 0; i < zzc2; i++) {
            if (!zzi(this.zza.zzg(i))) {
                return false;
            }
        }
        for (Map.Entry zzi : this.zza.zzd()) {
            if (!zzi(zzi)) {
                return false;
            }
        }
        return true;
    }

    private zzadp(boolean z) {
        zzf();
        zzf();
    }
}
