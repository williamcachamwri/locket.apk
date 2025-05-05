package com.google.android.gms.measurement.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzjt;
import com.google.android.gms.internal.measurement.zzoe;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzv {
    private String zza;
    private boolean zzb;
    private zzfy.zzm zzc;
    /* access modifiers changed from: private */
    public BitSet zzd;
    private BitSet zze;
    private Map<Integer, Long> zzf;
    private Map<Integer, List<Long>> zzg;
    private final /* synthetic */ zzt zzh;

    /* access modifiers changed from: package-private */
    public final zzfy.zzd zza(int i) {
        ArrayList arrayList;
        List list;
        zzfy.zzd.zza zzb2 = zzfy.zzd.zzb();
        zzb2.zza(i);
        zzb2.zza(this.zzb);
        zzfy.zzm zzm = this.zzc;
        if (zzm != null) {
            zzb2.zza(zzm);
        }
        zzfy.zzm.zza zzd2 = zzfy.zzm.zze().zzb(zzoo.zza(this.zzd)).zzd(zzoo.zza(this.zze));
        if (this.zzf == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(this.zzf.size());
            for (Integer intValue : this.zzf.keySet()) {
                int intValue2 = intValue.intValue();
                Long l = this.zzf.get(Integer.valueOf(intValue2));
                if (l != null) {
                    arrayList.add((zzfy.zze) ((zzjt) zzfy.zze.zzc().zza(intValue2).zza(l.longValue()).zzai()));
                }
            }
        }
        if (arrayList != null) {
            zzd2.zza(arrayList);
        }
        if (this.zzg == null) {
            list = Collections.emptyList();
        } else {
            ArrayList arrayList2 = new ArrayList(this.zzg.size());
            for (Integer next : this.zzg.keySet()) {
                zzfy.zzn.zza zza2 = zzfy.zzn.zzc().zza(next.intValue());
                List list2 = this.zzg.get(next);
                if (list2 != null) {
                    Collections.sort(list2);
                    zza2.zza((Iterable<? extends Long>) list2);
                }
                arrayList2.add((zzfy.zzn) ((zzjt) zza2.zzai()));
            }
            list = arrayList2;
        }
        zzd2.zzc(list);
        zzb2.zza(zzd2);
        return (zzfy.zzd) ((zzjt) zzb2.zzai());
    }

    private zzv(zzt zzt, String str) {
        this.zzh = zzt;
        this.zza = str;
        this.zzb = true;
        this.zzd = new BitSet();
        this.zze = new BitSet();
        this.zzf = new ArrayMap();
        this.zzg = new ArrayMap();
    }

    private zzv(zzt zzt, String str, zzfy.zzm zzm, BitSet bitSet, BitSet bitSet2, Map<Integer, Long> map, Map<Integer, Long> map2) {
        this.zzh = zzt;
        this.zza = str;
        this.zzd = bitSet;
        this.zze = bitSet2;
        this.zzf = map;
        this.zzg = new ArrayMap();
        if (map2 != null) {
            for (Integer next : map2.keySet()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(map2.get(next));
                this.zzg.put(next, arrayList);
            }
        }
        this.zzb = false;
        this.zzc = zzm;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzaa zzaa) {
        int zza2 = zzaa.zza();
        if (zzaa.zzc != null) {
            this.zze.set(zza2, zzaa.zzc.booleanValue());
        }
        if (zzaa.zzd != null) {
            this.zzd.set(zza2, zzaa.zzd.booleanValue());
        }
        if (zzaa.zze != null) {
            Long l = this.zzf.get(Integer.valueOf(zza2));
            long longValue = zzaa.zze.longValue() / 1000;
            if (l == null || longValue > l.longValue()) {
                this.zzf.put(Integer.valueOf(zza2), Long.valueOf(longValue));
            }
        }
        if (zzaa.zzf != null) {
            List list = this.zzg.get(Integer.valueOf(zza2));
            if (list == null) {
                list = new ArrayList();
                this.zzg.put(Integer.valueOf(zza2), list);
            }
            if (zzaa.zzc()) {
                list.clear();
            }
            if (zzoe.zza() && this.zzh.zze().zzf(this.zza, zzbh.zzbp) && zzaa.zzb()) {
                list.clear();
            }
            if (!zzoe.zza() || !this.zzh.zze().zzf(this.zza, zzbh.zzbp)) {
                list.add(Long.valueOf(zzaa.zzf.longValue() / 1000));
                return;
            }
            long longValue2 = zzaa.zzf.longValue() / 1000;
            if (!list.contains(Long.valueOf(longValue2))) {
                list.add(Long.valueOf(longValue2));
            }
        }
    }
}
