package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzgd;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zzj {
    public static zzaq zza(zzgd.zzd zzd) {
        if (zzd == null) {
            return zzaq.zzc;
        }
        int i = zzi.zza[zzd.zzb().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        List<zzgd.zzd> zzf = zzd.zzf();
                        ArrayList arrayList = new ArrayList();
                        for (zzgd.zzd zza : zzf) {
                            arrayList.add(zza(zza));
                        }
                        return new zzat(zzd.zzd(), arrayList);
                    } else if (i != 5) {
                        throw new IllegalStateException("Invalid entity: " + String.valueOf(zzd));
                    } else {
                        throw new IllegalArgumentException("Unknown type found. Cannot convert entity");
                    }
                } else if (zzd.zzh()) {
                    return new zzag(Boolean.valueOf(zzd.zzg()));
                } else {
                    return new zzag((Boolean) null);
                }
            } else if (zzd.zzi()) {
                return new zzai(Double.valueOf(zzd.zza()));
            } else {
                return new zzai((Double) null);
            }
        } else if (zzd.zzj()) {
            return new zzas(zzd.zze());
        } else {
            return zzaq.zzj;
        }
    }

    public static zzaq zza(Object obj) {
        if (obj == null) {
            return zzaq.zzd;
        }
        if (obj instanceof String) {
            return new zzas((String) obj);
        }
        if (obj instanceof Double) {
            return new zzai((Double) obj);
        }
        if (obj instanceof Long) {
            return new zzai(Double.valueOf(((Long) obj).doubleValue()));
        }
        if (obj instanceof Integer) {
            return new zzai(Double.valueOf(((Integer) obj).doubleValue()));
        }
        if (obj instanceof Boolean) {
            return new zzag((Boolean) obj);
        }
        if (obj instanceof Map) {
            zzap zzap = new zzap();
            Map map = (Map) obj;
            for (Object next : map.keySet()) {
                zzaq zza = zza(map.get(next));
                if (next != null) {
                    if (!(next instanceof String)) {
                        next = next.toString();
                    }
                    zzap.zza((String) next, zza);
                }
            }
            return zzap;
        } else if (obj instanceof List) {
            zzaf zzaf = new zzaf();
            for (Object zza2 : (List) obj) {
                zzaf.zza(zza(zza2));
            }
            return zzaf;
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }
}
