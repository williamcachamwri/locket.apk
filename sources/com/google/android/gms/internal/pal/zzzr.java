package com.google.android.gms.internal.pal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzzr extends AbstractMap implements Serializable {
    private static final Comparator zzf = new zzzk();
    final Comparator zza;
    zzzq zzb;
    int zzc = 0;
    int zzd = 0;
    final zzzq zze = new zzzq();
    private zzzm zzg;
    private zzzo zzh;

    public zzzr() {
        Comparator comparator = zzf;
        this.zza = comparator;
    }

    private final void zzf(zzzq zzzq, boolean z) {
        while (zzzq != null) {
            zzzq zzzq2 = zzzq.zzb;
            zzzq zzzq3 = zzzq.zzc;
            int i = 0;
            int i2 = zzzq2 != null ? zzzq2.zzh : 0;
            int i3 = zzzq3 != null ? zzzq3.zzh : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                zzzq zzzq4 = zzzq3.zzb;
                zzzq zzzq5 = zzzq3.zzc;
                int i5 = zzzq5 != null ? zzzq5.zzh : 0;
                if (zzzq4 != null) {
                    i = zzzq4.zzh;
                }
                int i6 = i - i5;
                if (i6 == -1 || (i6 == 0 && !z)) {
                    zzh(zzzq);
                } else {
                    zzi(zzzq3);
                    zzh(zzzq);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                zzzq zzzq6 = zzzq2.zzb;
                zzzq zzzq7 = zzzq2.zzc;
                int i7 = zzzq7 != null ? zzzq7.zzh : 0;
                if (zzzq6 != null) {
                    i = zzzq6.zzh;
                }
                int i8 = i - i7;
                if (i8 == 1 || (i8 == 0 && !z)) {
                    zzi(zzzq);
                } else {
                    zzh(zzzq2);
                    zzi(zzzq);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                zzzq.zzh = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                zzzq.zzh = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            zzzq = zzzq.zza;
        }
    }

    private final void zzg(zzzq zzzq, zzzq zzzq2) {
        zzzq zzzq3 = zzzq.zza;
        zzzq.zza = null;
        if (zzzq2 != null) {
            zzzq2.zza = zzzq3;
        }
        if (zzzq3 == null) {
            this.zzb = zzzq2;
        } else if (zzzq3.zzb == zzzq) {
            zzzq3.zzb = zzzq2;
        } else {
            zzzq3.zzc = zzzq2;
        }
    }

    private final void zzh(zzzq zzzq) {
        zzzq zzzq2 = zzzq.zzb;
        zzzq zzzq3 = zzzq.zzc;
        zzzq zzzq4 = zzzq3.zzb;
        zzzq zzzq5 = zzzq3.zzc;
        zzzq.zzc = zzzq4;
        if (zzzq4 != null) {
            zzzq4.zza = zzzq;
        }
        zzg(zzzq, zzzq3);
        zzzq3.zzb = zzzq;
        zzzq.zza = zzzq3;
        int i = 0;
        int max = Math.max(zzzq2 != null ? zzzq2.zzh : 0, zzzq4 != null ? zzzq4.zzh : 0) + 1;
        zzzq.zzh = max;
        if (zzzq5 != null) {
            i = zzzq5.zzh;
        }
        zzzq3.zzh = Math.max(max, i) + 1;
    }

    private final void zzi(zzzq zzzq) {
        zzzq zzzq2 = zzzq.zzb;
        zzzq zzzq3 = zzzq.zzc;
        zzzq zzzq4 = zzzq2.zzb;
        zzzq zzzq5 = zzzq2.zzc;
        zzzq.zzb = zzzq5;
        if (zzzq5 != null) {
            zzzq5.zza = zzzq;
        }
        zzg(zzzq, zzzq2);
        zzzq2.zzc = zzzq;
        zzzq.zza = zzzq2;
        int i = 0;
        int max = Math.max(zzzq3 != null ? zzzq3.zzh : 0, zzzq5 != null ? zzzq5.zzh : 0) + 1;
        zzzq.zzh = max;
        if (zzzq4 != null) {
            i = zzzq4.zzh;
        }
        zzzq2.zzh = Math.max(max, i) + 1;
    }

    public final void clear() {
        this.zzb = null;
        this.zzc = 0;
        this.zzd++;
        zzzq zzzq = this.zze;
        zzzq.zze = zzzq;
        zzzq.zzd = zzzq;
    }

    public final boolean containsKey(Object obj) {
        return zzc(obj) != null;
    }

    public final Set entrySet() {
        zzzm zzzm = this.zzg;
        if (zzzm != null) {
            return zzzm;
        }
        zzzm zzzm2 = new zzzm(this);
        this.zzg = zzzm2;
        return zzzm2;
    }

    public final Object get(Object obj) {
        zzzq zzc2 = zzc(obj);
        if (zzc2 != null) {
            return zzc2.zzg;
        }
        return null;
    }

    public final Set keySet() {
        zzzo zzzo = this.zzh;
        if (zzzo != null) {
            return zzzo;
        }
        zzzo zzzo2 = new zzzo(this);
        this.zzh = zzzo2;
        return zzzo2;
    }

    public final Object remove(Object obj) {
        zzzq zzd2 = zzd(obj);
        if (zzd2 != null) {
            return zzd2.zzg;
        }
        return null;
    }

    public final int size() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final zzzq zza(Object obj, boolean z) {
        int i;
        zzzq zzzq;
        Comparator comparator = this.zza;
        zzzq zzzq2 = this.zzb;
        if (zzzq2 != null) {
            Comparable comparable = comparator == zzf ? (Comparable) obj : null;
            while (true) {
                if (comparable != null) {
                    i = comparable.compareTo(zzzq2.zzf);
                } else {
                    i = comparator.compare(obj, zzzq2.zzf);
                }
                if (i == 0) {
                    return zzzq2;
                }
                zzzq zzzq3 = i < 0 ? zzzq2.zzb : zzzq2.zzc;
                if (zzzq3 == null) {
                    break;
                }
                zzzq2 = zzzq3;
            }
        } else {
            i = 0;
        }
        if (!z) {
            return null;
        }
        zzzq zzzq4 = this.zze;
        if (zzzq2 != null) {
            zzzq = new zzzq(zzzq2, obj, zzzq4, zzzq4.zze);
            if (i < 0) {
                zzzq2.zzb = zzzq;
            } else {
                zzzq2.zzc = zzzq;
            }
            zzf(zzzq2, true);
        } else if (comparator != zzf || (obj instanceof Comparable)) {
            zzzq = new zzzq((zzzq) null, obj, zzzq4, zzzq4.zze);
            this.zzb = zzzq;
        } else {
            throw new ClassCastException(String.valueOf(obj.getClass().getName()).concat(" is not Comparable"));
        }
        this.zzc++;
        this.zzd++;
        return zzzq;
    }

    /* access modifiers changed from: package-private */
    public final zzzq zzb(Map.Entry entry) {
        zzzq zzc2 = zzc(entry.getKey());
        if (zzc2 == null) {
            return null;
        }
        Object obj = zzc2.zzg;
        Object value = entry.getValue();
        if (obj == value || (obj != null && obj.equals(value))) {
            return zzc2;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final zzzq zzc(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return zza(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final zzzq zzd(Object obj) {
        zzzq zzc2 = zzc(obj);
        if (zzc2 != null) {
            zze(zzc2, true);
        }
        return zzc2;
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzzq zzzq, boolean z) {
        zzzq zzzq2;
        int i;
        zzzq zzzq3;
        if (z) {
            zzzq zzzq4 = zzzq.zze;
            zzzq4.zzd = zzzq.zzd;
            zzzq.zzd.zze = zzzq4;
        }
        zzzq zzzq5 = zzzq.zzb;
        zzzq zzzq6 = zzzq.zzc;
        zzzq zzzq7 = zzzq.zza;
        int i2 = 0;
        if (zzzq5 == null || zzzq6 == null) {
            if (zzzq5 != null) {
                zzg(zzzq, zzzq5);
                zzzq.zzb = null;
            } else if (zzzq6 != null) {
                zzg(zzzq, zzzq6);
                zzzq.zzc = null;
            } else {
                zzg(zzzq, (zzzq) null);
            }
            zzf(zzzq7, false);
            this.zzc--;
            this.zzd++;
            return;
        }
        if (zzzq5.zzh > zzzq6.zzh) {
            do {
                zzzq2 = zzzq5;
                zzzq5 = zzzq5.zzc;
            } while (zzzq5 != null);
        } else {
            do {
                zzzq zzzq8 = zzzq6;
                zzzq6 = zzzq6.zzb;
                zzzq3 = zzzq8;
            } while (zzzq6 != null);
            zzzq2 = zzzq3;
        }
        zze(zzzq2, false);
        zzzq zzzq9 = zzzq.zzb;
        if (zzzq9 != null) {
            i = zzzq9.zzh;
            zzzq2.zzb = zzzq9;
            zzzq9.zza = zzzq2;
            zzzq.zzb = null;
        } else {
            i = 0;
        }
        zzzq zzzq10 = zzzq.zzc;
        if (zzzq10 != null) {
            i2 = zzzq10.zzh;
            zzzq2.zzc = zzzq10;
            zzzq10.zza = zzzq2;
            zzzq.zzc = null;
        }
        zzzq2.zzh = Math.max(i, i2) + 1;
        zzg(zzzq, zzzq2);
    }

    public final Object put(Object obj, Object obj2) {
        if (obj != null) {
            zzzq zza2 = zza(obj, true);
            Object obj3 = zza2.zzg;
            zza2.zzg = obj2;
            return obj3;
        }
        throw new NullPointerException("key == null");
    }
}
