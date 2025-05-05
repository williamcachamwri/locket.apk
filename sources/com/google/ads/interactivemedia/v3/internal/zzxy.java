package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzxy extends AbstractMap implements Serializable {
    private static final Comparator zze = new zzxr();
    zzxx zza;
    int zzb;
    int zzc;
    final zzxx zzd;
    private final Comparator zzf;
    private final boolean zzg;
    private zzxt zzh;
    private zzxv zzi;

    public zzxy() {
        this(zze, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002f, code lost:
        if (r10 == false) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005a, code lost:
        if (r10 == false) goto L_0x0066;
     */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0084 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0084 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0080 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0080 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzf(com.google.ads.interactivemedia.v3.internal.zzxx r9, boolean r10) {
        /*
            r8 = this;
        L_0x0000:
            if (r9 == 0) goto L_0x0084
            com.google.ads.interactivemedia.v3.internal.zzxx r0 = r9.zzb
            com.google.ads.interactivemedia.v3.internal.zzxx r1 = r9.zzc
            r2 = 0
            if (r0 == 0) goto L_0x000c
            int r3 = r0.zzi
            goto L_0x000d
        L_0x000c:
            r3 = r2
        L_0x000d:
            if (r1 == 0) goto L_0x0012
            int r4 = r1.zzi
            goto L_0x0013
        L_0x0012:
            r4 = r2
        L_0x0013:
            int r5 = r3 - r4
            r6 = -2
            r7 = 1
            if (r5 != r6) goto L_0x0042
            com.google.ads.interactivemedia.v3.internal.zzxx r0 = r1.zzb
            com.google.ads.interactivemedia.v3.internal.zzxx r3 = r1.zzc
            if (r3 == 0) goto L_0x0022
            int r3 = r3.zzi
            goto L_0x0023
        L_0x0022:
            r3 = r2
        L_0x0023:
            if (r0 == 0) goto L_0x0028
            int r0 = r0.zzi
            goto L_0x0029
        L_0x0028:
            r0 = r2
        L_0x0029:
            int r0 = r0 - r3
            r3 = -1
            if (r0 == r3) goto L_0x003a
            if (r0 != 0) goto L_0x0032
            if (r10 != 0) goto L_0x0033
            goto L_0x003b
        L_0x0032:
            r7 = r10
        L_0x0033:
            r8.zzi(r1)
            r8.zzh(r9)
            goto L_0x003f
        L_0x003a:
            r2 = r10
        L_0x003b:
            r8.zzh(r9)
            r7 = r2
        L_0x003f:
            if (r7 != 0) goto L_0x0084
            goto L_0x0080
        L_0x0042:
            r1 = 2
            if (r5 != r1) goto L_0x006d
            com.google.ads.interactivemedia.v3.internal.zzxx r1 = r0.zzb
            com.google.ads.interactivemedia.v3.internal.zzxx r3 = r0.zzc
            if (r3 == 0) goto L_0x004e
            int r3 = r3.zzi
            goto L_0x004f
        L_0x004e:
            r3 = r2
        L_0x004f:
            if (r1 == 0) goto L_0x0054
            int r1 = r1.zzi
            goto L_0x0055
        L_0x0054:
            r1 = r2
        L_0x0055:
            int r1 = r1 - r3
            if (r1 == r7) goto L_0x0065
            if (r1 != 0) goto L_0x005d
            if (r10 != 0) goto L_0x005e
            goto L_0x0066
        L_0x005d:
            r7 = r10
        L_0x005e:
            r8.zzh(r0)
            r8.zzi(r9)
            goto L_0x006a
        L_0x0065:
            r2 = r10
        L_0x0066:
            r8.zzi(r9)
            r7 = r2
        L_0x006a:
            if (r7 == 0) goto L_0x0080
            goto L_0x0084
        L_0x006d:
            if (r5 != 0) goto L_0x0076
            int r3 = r3 + 1
            r9.zzi = r3
            if (r10 == 0) goto L_0x0080
            goto L_0x0084
        L_0x0076:
            int r0 = java.lang.Math.max(r3, r4)
            int r0 = r0 + r7
            r9.zzi = r0
            if (r10 != 0) goto L_0x0080
            goto L_0x0084
        L_0x0080:
            com.google.ads.interactivemedia.v3.internal.zzxx r9 = r9.zza
            goto L_0x0000
        L_0x0084:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzxy.zzf(com.google.ads.interactivemedia.v3.internal.zzxx, boolean):void");
    }

    private final void zzg(zzxx zzxx, zzxx zzxx2) {
        zzxx zzxx3 = zzxx.zza;
        zzxx.zza = null;
        if (zzxx2 != null) {
            zzxx2.zza = zzxx3;
        }
        if (zzxx3 == null) {
            this.zza = zzxx2;
        } else if (zzxx3.zzb == zzxx) {
            zzxx3.zzb = zzxx2;
        } else {
            zzxx3.zzc = zzxx2;
        }
    }

    private final void zzh(zzxx zzxx) {
        zzxx zzxx2 = zzxx.zzb;
        zzxx zzxx3 = zzxx.zzc;
        zzxx zzxx4 = zzxx3.zzb;
        zzxx zzxx5 = zzxx3.zzc;
        zzxx.zzc = zzxx4;
        if (zzxx4 != null) {
            zzxx4.zza = zzxx;
        }
        zzg(zzxx, zzxx3);
        zzxx3.zzb = zzxx;
        zzxx.zza = zzxx3;
        int i = 0;
        int max = Math.max(zzxx2 != null ? zzxx2.zzi : 0, zzxx4 != null ? zzxx4.zzi : 0) + 1;
        zzxx.zzi = max;
        if (zzxx5 != null) {
            i = zzxx5.zzi;
        }
        zzxx3.zzi = Math.max(max, i) + 1;
    }

    private final void zzi(zzxx zzxx) {
        zzxx zzxx2 = zzxx.zzb;
        zzxx zzxx3 = zzxx.zzc;
        zzxx zzxx4 = zzxx2.zzb;
        zzxx zzxx5 = zzxx2.zzc;
        zzxx.zzb = zzxx5;
        if (zzxx5 != null) {
            zzxx5.zza = zzxx;
        }
        zzg(zzxx, zzxx2);
        zzxx2.zzc = zzxx;
        zzxx.zza = zzxx2;
        int i = 0;
        int max = Math.max(zzxx3 != null ? zzxx3.zzi : 0, zzxx5 != null ? zzxx5.zzi : 0) + 1;
        zzxx.zzi = max;
        if (zzxx4 != null) {
            i = zzxx4.zzi;
        }
        zzxx2.zzi = Math.max(max, i) + 1;
    }

    public final void clear() {
        this.zza = null;
        this.zzb = 0;
        this.zzc++;
        zzxx zzxx = this.zzd;
        zzxx.zze = zzxx;
        zzxx.zzd = zzxx;
    }

    public final boolean containsKey(Object obj) {
        return zzc(obj) != null;
    }

    public final Set entrySet() {
        zzxt zzxt = this.zzh;
        if (zzxt != null) {
            return zzxt;
        }
        zzxt zzxt2 = new zzxt(this);
        this.zzh = zzxt2;
        return zzxt2;
    }

    public final Object get(Object obj) {
        zzxx zzc2 = zzc(obj);
        if (zzc2 != null) {
            return zzc2.zzh;
        }
        return null;
    }

    public final Set keySet() {
        zzxv zzxv = this.zzi;
        if (zzxv != null) {
            return zzxv;
        }
        zzxv zzxv2 = new zzxv(this);
        this.zzi = zzxv2;
        return zzxv2;
    }

    public final Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        } else if (obj2 != null || this.zzg) {
            zzxx zza2 = zza(obj, true);
            Object obj3 = zza2.zzh;
            zza2.zzh = obj2;
            return obj3;
        } else {
            throw new NullPointerException("value == null");
        }
    }

    public final Object remove(Object obj) {
        zzxx zzd2 = zzd(obj);
        if (zzd2 != null) {
            return zzd2.zzh;
        }
        return null;
    }

    public final int size() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final zzxx zza(Object obj, boolean z) {
        int i;
        zzxx zzxx;
        Comparator comparator = this.zzf;
        zzxx zzxx2 = this.zza;
        if (zzxx2 != null) {
            Comparable comparable = comparator == zze ? (Comparable) obj : null;
            while (true) {
                if (comparable != null) {
                    i = comparable.compareTo(zzxx2.zzf);
                } else {
                    i = comparator.compare(obj, zzxx2.zzf);
                }
                if (i == 0) {
                    return zzxx2;
                }
                zzxx zzxx3 = i < 0 ? zzxx2.zzb : zzxx2.zzc;
                if (zzxx3 == null) {
                    break;
                }
                zzxx2 = zzxx3;
            }
        } else {
            i = 0;
        }
        if (!z) {
            return null;
        }
        zzxx zzxx4 = this.zzd;
        if (zzxx2 != null) {
            zzxx = new zzxx(this.zzg, zzxx2, obj, zzxx4, zzxx4.zze);
            if (i < 0) {
                zzxx2.zzb = zzxx;
            } else {
                zzxx2.zzc = zzxx;
            }
            zzf(zzxx2, true);
        } else if (comparator != zze || (obj instanceof Comparable)) {
            zzxx = new zzxx(this.zzg, (zzxx) null, obj, zzxx4, zzxx4.zze);
            this.zza = zzxx;
        } else {
            throw new ClassCastException(String.valueOf(obj.getClass().getName()).concat(" is not Comparable"));
        }
        this.zzb++;
        this.zzc++;
        return zzxx;
    }

    /* access modifiers changed from: package-private */
    public final zzxx zzb(Map.Entry entry) {
        zzxx zzc2 = zzc(entry.getKey());
        if (zzc2 == null || !Objects.equals(zzc2.zzh, entry.getValue())) {
            return null;
        }
        return zzc2;
    }

    /* access modifiers changed from: package-private */
    public final zzxx zzc(Object obj) {
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
    public final zzxx zzd(Object obj) {
        zzxx zzc2 = zzc(obj);
        if (zzc2 != null) {
            zze(zzc2, true);
        }
        return zzc2;
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzxx zzxx, boolean z) {
        zzxx zzxx2;
        int i;
        zzxx zzxx3;
        if (z) {
            zzxx zzxx4 = zzxx.zze;
            zzxx4.zzd = zzxx.zzd;
            zzxx.zzd.zze = zzxx4;
        }
        zzxx zzxx5 = zzxx.zzb;
        zzxx zzxx6 = zzxx.zzc;
        zzxx zzxx7 = zzxx.zza;
        int i2 = 0;
        if (zzxx5 == null || zzxx6 == null) {
            if (zzxx5 != null) {
                zzg(zzxx, zzxx5);
                zzxx.zzb = null;
            } else if (zzxx6 != null) {
                zzg(zzxx, zzxx6);
                zzxx.zzc = null;
            } else {
                zzg(zzxx, (zzxx) null);
            }
            zzf(zzxx7, false);
            this.zzb--;
            this.zzc++;
            return;
        }
        if (zzxx5.zzi > zzxx6.zzi) {
            do {
                zzxx2 = zzxx5;
                zzxx5 = zzxx5.zzc;
            } while (zzxx5 != null);
        } else {
            do {
                zzxx zzxx8 = zzxx6;
                zzxx6 = zzxx6.zzb;
                zzxx3 = zzxx8;
            } while (zzxx6 != null);
            zzxx2 = zzxx3;
        }
        zze(zzxx2, false);
        zzxx zzxx9 = zzxx.zzb;
        if (zzxx9 != null) {
            i = zzxx9.zzi;
            zzxx2.zzb = zzxx9;
            zzxx9.zza = zzxx2;
            zzxx.zzb = null;
        } else {
            i = 0;
        }
        zzxx zzxx10 = zzxx.zzc;
        if (zzxx10 != null) {
            i2 = zzxx10.zzi;
            zzxx2.zzc = zzxx10;
            zzxx10.zza = zzxx2;
            zzxx.zzc = null;
        }
        zzxx2.zzi = Math.max(i, i2) + 1;
        zzg(zzxx, zzxx2);
    }

    public zzxy(Comparator comparator, boolean z) {
        this.zzb = 0;
        this.zzc = 0;
        this.zzf = comparator;
        this.zzg = z;
        this.zzd = new zzxx(z);
    }

    public zzxy(boolean z) {
        this(zze, false);
    }
}
