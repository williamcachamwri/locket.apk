package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzjm<T extends zzjo<T>> {
    private static final zzjm<?> zzb = new zzjm<>(true);
    final zzlv<T, Object> zza;
    private boolean zzc;
    private boolean zzd;

    static int zza(zzms zzms, int i, Object obj) {
        int zzi = zzjc.zzi(i);
        if (zzms == zzms.GROUP) {
            zzjv.zza((zzlc) obj);
            zzi <<= 1;
        }
        return zzi + zza(zzms, obj);
    }

    private static int zza(zzms zzms, Object obj) {
        switch (zzjl.zzb[zzms.ordinal()]) {
            case 1:
                return zzjc.zza(((Double) obj).doubleValue());
            case 2:
                return zzjc.zza(((Float) obj).floatValue());
            case 3:
                return zzjc.zzd(((Long) obj).longValue());
            case 4:
                return zzjc.zzg(((Long) obj).longValue());
            case 5:
                return zzjc.zzf(((Integer) obj).intValue());
            case 6:
                return zzjc.zzc(((Long) obj).longValue());
            case 7:
                return zzjc.zze(((Integer) obj).intValue());
            case 8:
                return zzjc.zza(((Boolean) obj).booleanValue());
            case 9:
                return zzjc.zzb((zzlc) obj);
            case 10:
                if (obj instanceof zzkg) {
                    return zzjc.zza((zzkk) (zzkg) obj);
                }
                return zzjc.zzc((zzlc) obj);
            case 11:
                if (obj instanceof zzik) {
                    return zzjc.zzb((zzik) obj);
                }
                return zzjc.zzb((String) obj);
            case 12:
                if (obj instanceof zzik) {
                    return zzjc.zzb((zzik) obj);
                }
                return zzjc.zza((byte[]) obj);
            case 13:
                return zzjc.zzj(((Integer) obj).intValue());
            case 14:
                return zzjc.zzg(((Integer) obj).intValue());
            case 15:
                return zzjc.zze(((Long) obj).longValue());
            case 16:
                return zzjc.zzh(((Integer) obj).intValue());
            case 17:
                return zzjc.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzjy) {
                    return zzjc.zzd(((zzjy) obj).zza());
                }
                return zzjc.zzd(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zza(zzjo<?> zzjo, Object obj) {
        zzms zzb2 = zzjo.zzb();
        int zza2 = zzjo.zza();
        if (!zzjo.zze()) {
            return zza(zzb2, zza2, obj);
        }
        List list = (List) obj;
        int size = list.size();
        int i = 0;
        if (!zzjo.zzd()) {
            int i2 = 0;
            while (i < size) {
                i2 += zza(zzb2, zza2, list.get(i));
                i++;
            }
            return i2;
        } else if (list.isEmpty()) {
            return 0;
        } else {
            int i3 = 0;
            while (i < size) {
                i3 += zza(zzb2, list.get(i));
                i++;
            }
            return zzjc.zzi(zza2) + i3 + zzjc.zzj(i3);
        }
    }

    public final int zza() {
        int zza2 = this.zza.zza();
        int i = 0;
        for (int i2 = 0; i2 < zza2; i2++) {
            i += zza(this.zza.zza(i2));
        }
        for (Map.Entry<T, Object> zza3 : this.zza.zzb()) {
            i += zza(zza3);
        }
        return i;
    }

    private static int zza(Map.Entry<T, Object> entry) {
        zzjo zzjo = (zzjo) entry.getKey();
        Object value = entry.getValue();
        if (zzjo.zzc() != zzmz.MESSAGE || zzjo.zze() || zzjo.zzd()) {
            return zza((zzjo<?>) zzjo, value);
        }
        if (value instanceof zzkg) {
            return zzjc.zza(((zzjo) entry.getKey()).zza(), (zzkk) (zzkg) value);
        }
        return zzjc.zzb(((zzjo) entry.getKey()).zza(), (zzlc) value);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public static <T extends zzjo<T>> zzjm<T> zzb() {
        return zzb;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzjm zzjm = new zzjm();
        int zza2 = this.zza.zza();
        for (int i = 0; i < zza2; i++) {
            Map.Entry<T, Object> zza3 = this.zza.zza(i);
            zzjm.zzb((zzjo) zza3.getKey(), zza3.getValue());
        }
        for (Map.Entry next : this.zza.zzb()) {
            zzjm.zzb((zzjo) next.getKey(), next.getValue());
        }
        zzjm.zzd = this.zzd;
        return zzjm;
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzlh) {
            return ((zzlh) obj).zza();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final Object zza(T t) {
        Object obj = this.zza.get(t);
        if (!(obj instanceof zzkg)) {
            return obj;
        }
        zzkg zzkg = (zzkg) obj;
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<T, Object>> zzc() {
        if (this.zza.isEmpty()) {
            return Collections.emptyIterator();
        }
        if (this.zzd) {
            return new zzki(this.zza.zzc().iterator());
        }
        return this.zza.zzc().iterator();
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        if (this.zza.isEmpty()) {
            return Collections.emptyIterator();
        }
        if (this.zzd) {
            return new zzki(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    private zzjm() {
        this.zza = new zzly();
    }

    private zzjm(zzlv<T, Object> zzlv) {
        this.zza = zzlv;
        zze();
    }

    private zzjm(boolean z) {
        this(new zzly());
        zze();
    }

    public final void zze() {
        if (!this.zzc) {
            int zza2 = this.zza.zza();
            for (int i = 0; i < zza2; i++) {
                Object value = this.zza.zza(i).getValue();
                if (value instanceof zzjt) {
                    ((zzjt) value).zzcl();
                }
            }
            for (Map.Entry<T, Object> value2 : this.zza.zzb()) {
                Object value3 = value2.getValue();
                if (value3 instanceof zzjt) {
                    ((zzjt) value3).zzcl();
                }
            }
            this.zza.zzd();
            this.zzc = true;
        }
    }

    public final void zza(zzjm<T> zzjm) {
        int zza2 = zzjm.zza.zza();
        for (int i = 0; i < zza2; i++) {
            zzb(zzjm.zza.zza(i));
        }
        for (Map.Entry<T, Object> zzb2 : zzjm.zza.zzb()) {
            zzb(zzb2);
        }
    }

    private final void zzb(Map.Entry<T, Object> entry) {
        Object obj;
        zzjo zzjo = (zzjo) entry.getKey();
        Object value = entry.getValue();
        boolean z = value instanceof zzkg;
        if (zzjo.zze()) {
            if (!z) {
                Object zza2 = zza(zzjo);
                List list = (List) value;
                int size = list.size();
                if (zza2 == null) {
                    zza2 = new ArrayList(size);
                }
                List list2 = (List) zza2;
                for (int i = 0; i < size; i++) {
                    list2.add(zza(list.get(i)));
                }
                this.zza.put(zzjo, zza2);
                return;
            }
            throw new IllegalStateException("Lazy fields can not be repeated");
        } else if (zzjo.zzc() == zzmz.MESSAGE) {
            Object zza3 = zza(zzjo);
            if (zza3 == null) {
                this.zza.put(zzjo, zza(value));
                if (z) {
                    this.zzd = true;
                }
            } else if (!z) {
                if (zza3 instanceof zzlh) {
                    obj = zzjo.zza((zzlh) zza3, (zzlh) value);
                } else {
                    obj = zzjo.zza(((zzlc) zza3).zzcj(), (zzlc) value).zzai();
                }
                this.zza.put(zzjo, obj);
            } else {
                zzkg zzkg = (zzkg) value;
                throw new NoSuchMethodError();
            }
        } else if (!z) {
            this.zza.put(zzjo, zza(value));
        } else {
            throw new IllegalStateException("Lazy fields must be message-valued");
        }
    }

    private final void zzb(T t, Object obj) {
        if (!t.zze()) {
            zzc(t, obj);
        } else if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                zzc(t, obj2);
                arrayList.add(obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzkg) {
            this.zzd = true;
        }
        this.zza.put(t, obj);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        if ((r4 instanceof com.google.android.gms.internal.measurement.zzjy) == false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        if ((r4 instanceof byte[]) == false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0020, code lost:
        if ((r4 instanceof com.google.android.gms.internal.measurement.zzkg) == false) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zzc(T r3, java.lang.Object r4) {
        /*
            com.google.android.gms.internal.measurement.zzms r0 = r3.zzb()
            com.google.android.gms.internal.measurement.zzjv.zza(r4)
            int[] r1 = com.google.android.gms.internal.measurement.zzjl.zza
            com.google.android.gms.internal.measurement.zzmz r0 = r0.zzb()
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 1
            r2 = 0
            switch(r0) {
                case 1: goto L_0x0044;
                case 2: goto L_0x0041;
                case 3: goto L_0x003e;
                case 4: goto L_0x003b;
                case 5: goto L_0x0038;
                case 6: goto L_0x0035;
                case 7: goto L_0x002c;
                case 8: goto L_0x0023;
                case 9: goto L_0x001a;
                default: goto L_0x0018;
            }
        L_0x0018:
            r1 = r2
            goto L_0x0046
        L_0x001a:
            boolean r0 = r4 instanceof com.google.android.gms.internal.measurement.zzlc
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4 instanceof com.google.android.gms.internal.measurement.zzkg
            if (r0 == 0) goto L_0x0018
            goto L_0x0046
        L_0x0023:
            boolean r0 = r4 instanceof java.lang.Integer
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4 instanceof com.google.android.gms.internal.measurement.zzjy
            if (r0 == 0) goto L_0x0018
            goto L_0x0046
        L_0x002c:
            boolean r0 = r4 instanceof com.google.android.gms.internal.measurement.zzik
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4 instanceof byte[]
            if (r0 == 0) goto L_0x0018
            goto L_0x0046
        L_0x0035:
            boolean r1 = r4 instanceof java.lang.String
            goto L_0x0046
        L_0x0038:
            boolean r1 = r4 instanceof java.lang.Boolean
            goto L_0x0046
        L_0x003b:
            boolean r1 = r4 instanceof java.lang.Double
            goto L_0x0046
        L_0x003e:
            boolean r1 = r4 instanceof java.lang.Float
            goto L_0x0046
        L_0x0041:
            boolean r1 = r4 instanceof java.lang.Long
            goto L_0x0046
        L_0x0044:
            boolean r1 = r4 instanceof java.lang.Integer
        L_0x0046:
            if (r1 == 0) goto L_0x0049
            return
        L_0x0049:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            int r1 = r3.zza()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.google.android.gms.internal.measurement.zzms r3 = r3.zzb()
            com.google.android.gms.internal.measurement.zzmz r3 = r3.zzb()
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getName()
            java.lang.Object[] r3 = new java.lang.Object[]{r1, r3, r4}
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjm.zzc(com.google.android.gms.internal.measurement.zzjo, java.lang.Object):void");
    }

    static void zza(zzjc zzjc, zzms zzms, int i, Object obj) throws IOException {
        if (zzms == zzms.GROUP) {
            zzlc zzlc = (zzlc) obj;
            zzjv.zza(zzlc);
            zzjc.zzc(i, 3);
            zzlc.zza(zzjc);
            zzjc.zzc(i, 4);
            return;
        }
        zzjc.zzc(i, zzms.zza());
        switch (zzjl.zzb[zzms.ordinal()]) {
            case 1:
                zzjc.zzb(((Double) obj).doubleValue());
                return;
            case 2:
                zzjc.zzb(((Float) obj).floatValue());
                return;
            case 3:
                zzjc.zzb(((Long) obj).longValue());
                return;
            case 4:
                zzjc.zzb(((Long) obj).longValue());
                return;
            case 5:
                zzjc.zzb(((Integer) obj).intValue());
                return;
            case 6:
                zzjc.zza(((Long) obj).longValue());
                return;
            case 7:
                zzjc.zza(((Integer) obj).intValue());
                return;
            case 8:
                zzjc.zzb(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzlc) obj).zza(zzjc);
                return;
            case 10:
                zzjc.zza((zzlc) obj);
                return;
            case 11:
                if (obj instanceof zzik) {
                    zzjc.zza((zzik) obj);
                    return;
                } else {
                    zzjc.zza((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzik) {
                    zzjc.zza((zzik) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzjc.zzb(bArr, 0, bArr.length);
                return;
            case 13:
                zzjc.zzc(((Integer) obj).intValue());
                return;
            case 14:
                zzjc.zza(((Integer) obj).intValue());
                return;
            case 15:
                zzjc.zza(((Long) obj).longValue());
                return;
            case 16:
                zzjc.zzk(((Integer) obj).intValue());
                return;
            case 17:
                zzjc.zzh(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzjy) {
                    zzjc.zzb(((zzjy) obj).zza());
                    return;
                } else {
                    zzjc.zzb(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzjm)) {
            return false;
        }
        return this.zza.equals(((zzjm) obj).zza);
    }

    public final boolean zzf() {
        return this.zzc;
    }

    public final boolean zzg() {
        int zza2 = this.zza.zza();
        for (int i = 0; i < zza2; i++) {
            if (!zzc(this.zza.zza(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> zzc2 : this.zza.zzb()) {
            if (!zzc(zzc2)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzjo<T>> boolean zzc(Map.Entry<T, Object> entry) {
        zzjo zzjo = (zzjo) entry.getKey();
        if (zzjo.zzc() != zzmz.MESSAGE) {
            return true;
        }
        if (!zzjo.zze()) {
            return zzb(entry.getValue());
        }
        List list = (List) entry.getValue();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!zzb(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzb(Object obj) {
        if (obj instanceof zzle) {
            return ((zzle) obj).zzcn();
        }
        if (obj instanceof zzkg) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }
}
