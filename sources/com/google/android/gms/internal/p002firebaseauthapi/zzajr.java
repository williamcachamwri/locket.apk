package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzajr<T extends zzajt<T>> {
    private static final zzajr<?> zzb = new zzajr<>(true);
    final zzalw<T, Object> zza;
    private boolean zzc;
    private boolean zzd;

    static int zza(zzamw zzamw, int i, Object obj) {
        int zzi = zzajg.zzi(i);
        if (zzamw == zzamw.GROUP) {
            zzakb.zza((zzalc) obj);
            zzi <<= 1;
        }
        return zzi + zza(zzamw, obj);
    }

    private static int zza(zzamw zzamw, Object obj) {
        switch (zzajq.zzb[zzamw.ordinal()]) {
            case 1:
                return zzajg.zza(((Double) obj).doubleValue());
            case 2:
                return zzajg.zza(((Float) obj).floatValue());
            case 3:
                return zzajg.zzd(((Long) obj).longValue());
            case 4:
                return zzajg.zzg(((Long) obj).longValue());
            case 5:
                return zzajg.zze(((Integer) obj).intValue());
            case 6:
                return zzajg.zzc(((Long) obj).longValue());
            case 7:
                return zzajg.zzd(((Integer) obj).intValue());
            case 8:
                return zzajg.zza(((Boolean) obj).booleanValue());
            case 9:
                return zzajg.zza((zzalc) obj);
            case 10:
                if (obj instanceof zzakg) {
                    return zzajg.zza((zzakk) (zzakg) obj);
                }
                return zzajg.zzb((zzalc) obj);
            case 11:
                if (obj instanceof zzaip) {
                    return zzajg.zza((zzaip) obj);
                }
                return zzajg.zza((String) obj);
            case 12:
                if (obj instanceof zzaip) {
                    return zzajg.zza((zzaip) obj);
                }
                return zzajg.zza((byte[]) obj);
            case 13:
                return zzajg.zzj(((Integer) obj).intValue());
            case 14:
                return zzajg.zzg(((Integer) obj).intValue());
            case 15:
                return zzajg.zze(((Long) obj).longValue());
            case 16:
                return zzajg.zzh(((Integer) obj).intValue());
            case 17:
                return zzajg.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzaka) {
                    return zzajg.zzc(((zzaka) obj).zza());
                }
                return zzajg.zzc(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zza(zzajt<?> zzajt, Object obj) {
        zzamw zzb2 = zzajt.zzb();
        int zza2 = zzajt.zza();
        if (!zzajt.zze()) {
            return zza(zzb2, zza2, obj);
        }
        List list = (List) obj;
        int size = list.size();
        int i = 0;
        if (!zzajt.zzd()) {
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
            return zzajg.zzi(zza2) + i3 + zzajg.zzj(i3);
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
        zzajt zzajt = (zzajt) entry.getKey();
        Object value = entry.getValue();
        if (zzajt.zzc() != zzand.MESSAGE || zzajt.zze() || zzajt.zzd()) {
            return zza((zzajt<?>) zzajt, value);
        }
        if (value instanceof zzakg) {
            return zzajg.zza(((zzajt) entry.getKey()).zza(), (zzakk) (zzakg) value);
        }
        return zzajg.zza(((zzajt) entry.getKey()).zza(), (zzalc) value);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public static <T extends zzajt<T>> zzajr<T> zzb() {
        return zzb;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzajr zzajr = new zzajr();
        int zza2 = this.zza.zza();
        for (int i = 0; i < zza2; i++) {
            Map.Entry<T, Object> zza3 = this.zza.zza(i);
            zzajr.zzb((zzajt) zza3.getKey(), zza3.getValue());
        }
        for (Map.Entry next : this.zza.zzb()) {
            zzajr.zzb((zzajt) next.getKey(), next.getValue());
        }
        zzajr.zzd = this.zzd;
        return zzajr;
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzall) {
            return ((zzall) obj).zza();
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
        if (!(obj instanceof zzakg)) {
            return obj;
        }
        zzakg zzakg = (zzakg) obj;
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<T, Object>> zzc() {
        if (this.zza.isEmpty()) {
            return Collections.emptyIterator();
        }
        if (this.zzd) {
            return new zzaki(this.zza.zzc().iterator());
        }
        return this.zza.zzc().iterator();
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        if (this.zza.isEmpty()) {
            return Collections.emptyIterator();
        }
        if (this.zzd) {
            return new zzaki(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    private zzajr() {
        this.zza = new zzalz();
    }

    private zzajr(zzalw<T, Object> zzalw) {
        this.zza = zzalw;
        zze();
    }

    private zzajr(boolean z) {
        this(new zzalz());
        zze();
    }

    public final void zze() {
        if (!this.zzc) {
            int zza2 = this.zza.zza();
            for (int i = 0; i < zza2; i++) {
                Object value = this.zza.zza(i).getValue();
                if (value instanceof zzajy) {
                    ((zzajy) value).zzs();
                }
            }
            for (Map.Entry<T, Object> value2 : this.zza.zzb()) {
                Object value3 = value2.getValue();
                if (value3 instanceof zzajy) {
                    ((zzajy) value3).zzs();
                }
            }
            this.zza.zzd();
            this.zzc = true;
        }
    }

    public final void zza(zzajr<T> zzajr) {
        int zza2 = zzajr.zza.zza();
        for (int i = 0; i < zza2; i++) {
            zzb(zzajr.zza.zza(i));
        }
        for (Map.Entry<T, Object> zzb2 : zzajr.zza.zzb()) {
            zzb(zzb2);
        }
    }

    private final void zzb(Map.Entry<T, Object> entry) {
        Object obj;
        zzajt zzajt = (zzajt) entry.getKey();
        Object value = entry.getValue();
        boolean z = value instanceof zzakg;
        if (zzajt.zze()) {
            if (!z) {
                Object zza2 = zza(zzajt);
                List list = (List) value;
                int size = list.size();
                if (zza2 == null) {
                    zza2 = new ArrayList(size);
                }
                List list2 = (List) zza2;
                for (int i = 0; i < size; i++) {
                    list2.add(zza(list.get(i)));
                }
                this.zza.put(zzajt, zza2);
                return;
            }
            throw new IllegalStateException("Lazy fields can not be repeated");
        } else if (zzajt.zzc() == zzand.MESSAGE) {
            Object zza3 = zza(zzajt);
            if (zza3 == null) {
                this.zza.put(zzajt, zza(value));
                if (z) {
                    this.zzd = true;
                }
            } else if (!z) {
                if (zza3 instanceof zzall) {
                    obj = zzajt.zza((zzall) zza3, (zzall) value);
                } else {
                    obj = zzajt.zza(((zzalc) zza3).zzr(), (zzalc) value).zze();
                }
                this.zza.put(zzajt, obj);
            } else {
                zzakg zzakg = (zzakg) value;
                throw new NoSuchMethodError();
            }
        } else if (!z) {
            this.zza.put(zzajt, zza(value));
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
        if (obj instanceof zzakg) {
            this.zzd = true;
        }
        this.zza.put(t, obj);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        if ((r4 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzaka) == false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        if ((r4 instanceof byte[]) == false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0020, code lost:
        if ((r4 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzakg) == false) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zzc(T r3, java.lang.Object r4) {
        /*
            com.google.android.gms.internal.firebase-auth-api.zzamw r0 = r3.zzb()
            com.google.android.gms.internal.p002firebaseauthapi.zzakb.zza(r4)
            int[] r1 = com.google.android.gms.internal.p002firebaseauthapi.zzajq.zza
            com.google.android.gms.internal.firebase-auth-api.zzand r0 = r0.zzb()
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
            boolean r0 = r4 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzalc
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzakg
            if (r0 == 0) goto L_0x0018
            goto L_0x0046
        L_0x0023:
            boolean r0 = r4 instanceof java.lang.Integer
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzaka
            if (r0 == 0) goto L_0x0018
            goto L_0x0046
        L_0x002c:
            boolean r0 = r4 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzaip
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
            com.google.android.gms.internal.firebase-auth-api.zzamw r3 = r3.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzand r3 = r3.zzb()
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getName()
            java.lang.Object[] r3 = new java.lang.Object[]{r1, r3, r4}
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzajr.zzc(com.google.android.gms.internal.firebase-auth-api.zzajt, java.lang.Object):void");
    }

    static void zza(zzajg zzajg, zzamw zzamw, int i, Object obj) throws IOException {
        if (zzamw == zzamw.GROUP) {
            zzalc zzalc = (zzalc) obj;
            zzakb.zza(zzalc);
            zzajg.zzk(i, 3);
            zzalc.zza(zzajg);
            zzajg.zzk(i, 4);
            return;
        }
        zzajg.zzk(i, zzamw.zza());
        switch (zzajq.zzb[zzamw.ordinal()]) {
            case 1:
                zzajg.zzb(((Double) obj).doubleValue());
                return;
            case 2:
                zzajg.zzb(((Float) obj).floatValue());
                return;
            case 3:
                zzajg.zzj(((Long) obj).longValue());
                return;
            case 4:
                zzajg.zzj(((Long) obj).longValue());
                return;
            case 5:
                zzajg.zzl(((Integer) obj).intValue());
                return;
            case 6:
                zzajg.zzh(((Long) obj).longValue());
                return;
            case 7:
                zzajg.zzk(((Integer) obj).intValue());
                return;
            case 8:
                zzajg.zzb(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzalc) obj).zza(zzajg);
                return;
            case 10:
                zzajg.zzc((zzalc) obj);
                return;
            case 11:
                if (obj instanceof zzaip) {
                    zzajg.zzb((zzaip) obj);
                    return;
                } else {
                    zzajg.zzb((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzaip) {
                    zzajg.zzb((zzaip) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzajg.zzb(bArr, 0, bArr.length);
                return;
            case 13:
                zzajg.zzn(((Integer) obj).intValue());
                return;
            case 14:
                zzajg.zzk(((Integer) obj).intValue());
                return;
            case 15:
                zzajg.zzh(((Long) obj).longValue());
                return;
            case 16:
                zzajg.zzm(((Integer) obj).intValue());
                return;
            case 17:
                zzajg.zzi(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzaka) {
                    zzajg.zzl(((zzaka) obj).zza());
                    return;
                } else {
                    zzajg.zzl(((Integer) obj).intValue());
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
        if (!(obj instanceof zzajr)) {
            return false;
        }
        return this.zza.equals(((zzajr) obj).zza);
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

    private static <T extends zzajt<T>> boolean zzc(Map.Entry<T, Object> entry) {
        zzajt zzajt = (zzajt) entry.getKey();
        if (zzajt.zzc() != zzand.MESSAGE) {
            return true;
        }
        if (!zzajt.zze()) {
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
        if (obj instanceof zzale) {
            return ((zzale) obj).zzj();
        }
        if (obj instanceof zzakg) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }
}
