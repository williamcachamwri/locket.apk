package com.google.android.gms.internal.atv_ads_framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzdc {
    private static final zzdc zzb = new zzdc(true);
    final zzfl zza = new zzfb(16);
    private boolean zzc;
    private boolean zzd;

    private zzdc() {
    }

    public static zzdc zza() {
        throw null;
    }

    private static final void zzd(zzdb zzdb, Object obj) {
        boolean z;
        zzge zzb2 = zzdb.zzb();
        byte[] bArr = zzdp.zzd;
        obj.getClass();
        zzge zzge = zzge.DOUBLE;
        zzgf zzgf = zzgf.INT;
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
                if ((obj instanceof zzcn) || (obj instanceof byte[])) {
                    return;
                }
            case 7:
                if ((obj instanceof Integer) || (obj instanceof zzdj)) {
                    return;
                }
            case 8:
                if ((obj instanceof zzeo) || (obj instanceof zzdt)) {
                    return;
                }
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new Object[]{Integer.valueOf(zzdb.zza()), zzdb.zzb().zza(), obj.getClass().getName()}));
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzdc zzdc = new zzdc();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzdc.zzc((zzdb) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzdc.zzc((zzdb) entry.getKey(), entry.getValue());
        }
        zzdc.zzd = this.zzd;
        return zzdc;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdc)) {
            return false;
        }
        return this.zza.equals(((zzdc) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (!this.zzc) {
            for (int i = 0; i < this.zza.zzb(); i++) {
                Map.Entry zzg = this.zza.zzg(i);
                if (zzg.getValue() instanceof zzdh) {
                    ((zzdh) zzg.getValue()).zzv();
                }
            }
            this.zza.zza();
            this.zzc = true;
        }
    }

    public final void zzc(zzdb zzdb, Object obj) {
        if (!zzdb.zzc()) {
            zzd(zzdb, obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzd(zzdb, arrayList.get(i));
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzdt) {
            this.zzd = true;
        }
        this.zza.put(zzdb, obj);
    }

    private zzdc(boolean z) {
        zzb();
        zzb();
    }
}
