package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Map;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaw<K, V> extends zzap<K, V> {
    private static final zzap<Object, Object> zza = new zzaw((Object) null, new Object[0], 0);
    @CheckForNull
    private final transient Object zzb;
    private final transient Object[] zzc;
    private final transient int zzd;

    public final int size() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzd() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final zzak<V> zza() {
        return new zzba(this.zzc, 1, this.zzd);
    }

    /* access modifiers changed from: package-private */
    public final zzau<Map.Entry<K, V>> zzb() {
        return new zzav(this, this.zzc, 0, this.zzd);
    }

    /* access modifiers changed from: package-private */
    public final zzau<K> zzc() {
        return new zzax(this, new zzba(this.zzc, 0, this.zzd));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: short[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: short[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: short[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <K, V> com.google.android.gms.internal.p002firebaseauthapi.zzaw<K, V> zza(int r16, java.lang.Object[] r17, com.google.android.gms.internal.p002firebaseauthapi.zzas<K, V> r18) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            if (r0 != 0) goto L_0x000d
            com.google.android.gms.internal.firebase-auth-api.zzap<java.lang.Object, java.lang.Object> r0 = zza
            com.google.android.gms.internal.firebase-auth-api.zzaw r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzaw) r0
            return r0
        L_0x000d:
            r3 = 0
            r4 = 0
            r5 = 1
            if (r0 != r5) goto L_0x0027
            r0 = r1[r4]
            java.lang.Object r0 = java.util.Objects.requireNonNull(r0)
            r2 = r1[r5]
            java.lang.Object r2 = java.util.Objects.requireNonNull(r2)
            com.google.android.gms.internal.p002firebaseauthapi.zzai.zza((java.lang.Object) r0, (java.lang.Object) r2)
            com.google.android.gms.internal.firebase-auth-api.zzaw r0 = new com.google.android.gms.internal.firebase-auth-api.zzaw
            r0.<init>(r3, r1, r5)
            return r0
        L_0x0027:
            int r6 = r1.length
            int r6 = r6 >> r5
            com.google.android.gms.internal.p002firebaseauthapi.zzy.zzb(r0, r6)
            r6 = 2
            int r7 = java.lang.Math.max(r0, r6)
            r8 = 751619276(0x2ccccccc, float:5.8207657E-12)
            if (r7 >= r8) goto L_0x004c
            int r8 = r7 + -1
            int r8 = java.lang.Integer.highestOneBit(r8)
            int r8 = r8 << r5
        L_0x003d:
            double r9 = (double) r8
            r11 = 4604480259023595110(0x3fe6666666666666, double:0.7)
            double r9 = r9 * r11
            double r11 = (double) r7
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L_0x0055
            int r8 = r8 << 1
            goto L_0x003d
        L_0x004c:
            r8 = 1073741824(0x40000000, float:2.0)
            if (r7 >= r8) goto L_0x0052
            r7 = r5
            goto L_0x0053
        L_0x0052:
            r7 = r4
        L_0x0053:
            if (r7 == 0) goto L_0x01d1
        L_0x0055:
            if (r0 != r5) goto L_0x0068
            r7 = r1[r4]
            java.lang.Object r7 = java.util.Objects.requireNonNull(r7)
            r8 = r1[r5]
            java.lang.Object r8 = java.util.Objects.requireNonNull(r8)
            com.google.android.gms.internal.p002firebaseauthapi.zzai.zza((java.lang.Object) r7, (java.lang.Object) r8)
            goto L_0x01a4
        L_0x0068:
            int r7 = r8 + -1
            r9 = 128(0x80, float:1.794E-43)
            r10 = -1
            if (r8 > r9) goto L_0x00d8
            byte[] r8 = new byte[r8]
            java.util.Arrays.fill(r8, r10)
            r9 = r4
            r10 = r9
        L_0x0076:
            if (r9 >= r0) goto L_0x00c9
            int r11 = r9 * 2
            int r12 = r10 * 2
            r13 = r1[r11]
            java.lang.Object r13 = java.util.Objects.requireNonNull(r13)
            r11 = r11 ^ r5
            r11 = r1[r11]
            java.lang.Object r11 = java.util.Objects.requireNonNull(r11)
            com.google.android.gms.internal.p002firebaseauthapi.zzai.zza((java.lang.Object) r13, (java.lang.Object) r11)
            int r14 = r13.hashCode()
            int r14 = com.google.android.gms.internal.p002firebaseauthapi.zzah.zza(r14)
        L_0x0094:
            r14 = r14 & r7
            byte r15 = r8[r14]
            r4 = 255(0xff, float:3.57E-43)
            r15 = r15 & r4
            if (r15 != r4) goto L_0x00aa
            byte r4 = (byte) r12
            r8[r14] = r4
            if (r10 >= r9) goto L_0x00a7
            r1[r12] = r13
            r4 = r12 ^ 1
            r1[r4] = r11
        L_0x00a7:
            int r10 = r10 + 1
            goto L_0x00c1
        L_0x00aa:
            r4 = r1[r15]
            boolean r4 = r13.equals(r4)
            if (r4 == 0) goto L_0x00c5
            com.google.android.gms.internal.firebase-auth-api.zzar r3 = new com.google.android.gms.internal.firebase-auth-api.zzar
            r4 = r15 ^ 1
            r12 = r1[r4]
            java.lang.Object r12 = java.util.Objects.requireNonNull(r12)
            r3.<init>(r13, r11, r12)
            r1[r4] = r11
        L_0x00c1:
            int r9 = r9 + 1
            r4 = 0
            goto L_0x0076
        L_0x00c5:
            int r14 = r14 + 1
            r4 = 0
            goto L_0x0094
        L_0x00c9:
            if (r10 != r0) goto L_0x00ce
            r3 = r8
            goto L_0x01a4
        L_0x00ce:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            java.lang.Object[] r3 = new java.lang.Object[]{r8, r4, r3}
            goto L_0x01a4
        L_0x00d8:
            r4 = 32768(0x8000, float:4.5918E-41)
            if (r8 > r4) goto L_0x0142
            short[] r4 = new short[r8]
            java.util.Arrays.fill(r4, r10)
            r8 = 0
            r9 = 0
        L_0x00e4:
            if (r8 >= r0) goto L_0x0136
            int r10 = r8 * 2
            int r11 = r9 * 2
            r12 = r1[r10]
            java.lang.Object r12 = java.util.Objects.requireNonNull(r12)
            r10 = r10 ^ r5
            r10 = r1[r10]
            java.lang.Object r10 = java.util.Objects.requireNonNull(r10)
            com.google.android.gms.internal.p002firebaseauthapi.zzai.zza((java.lang.Object) r12, (java.lang.Object) r10)
            int r13 = r12.hashCode()
            int r13 = com.google.android.gms.internal.p002firebaseauthapi.zzah.zza(r13)
        L_0x0102:
            r13 = r13 & r7
            short r14 = r4[r13]
            r15 = 65535(0xffff, float:9.1834E-41)
            r14 = r14 & r15
            if (r14 != r15) goto L_0x0119
            short r14 = (short) r11
            r4[r13] = r14
            if (r9 >= r8) goto L_0x0116
            r1[r11] = r12
            r11 = r11 ^ 1
            r1[r11] = r10
        L_0x0116:
            int r9 = r9 + 1
            goto L_0x0130
        L_0x0119:
            r15 = r1[r14]
            boolean r15 = r12.equals(r15)
            if (r15 == 0) goto L_0x0133
            com.google.android.gms.internal.firebase-auth-api.zzar r3 = new com.google.android.gms.internal.firebase-auth-api.zzar
            r11 = r14 ^ 1
            r13 = r1[r11]
            java.lang.Object r13 = java.util.Objects.requireNonNull(r13)
            r3.<init>(r12, r10, r13)
            r1[r11] = r10
        L_0x0130:
            int r8 = r8 + 1
            goto L_0x00e4
        L_0x0133:
            int r13 = r13 + 1
            goto L_0x0102
        L_0x0136:
            if (r9 != r0) goto L_0x0139
            goto L_0x019a
        L_0x0139:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r9)
            java.lang.Object[] r3 = new java.lang.Object[]{r4, r7, r3}
            goto L_0x01a4
        L_0x0142:
            int[] r4 = new int[r8]
            java.util.Arrays.fill(r4, r10)
            r8 = 0
            r9 = 0
        L_0x0149:
            if (r8 >= r0) goto L_0x0198
            int r11 = r8 * 2
            int r12 = r9 * 2
            r13 = r1[r11]
            java.lang.Object r13 = java.util.Objects.requireNonNull(r13)
            r11 = r11 ^ r5
            r11 = r1[r11]
            java.lang.Object r11 = java.util.Objects.requireNonNull(r11)
            com.google.android.gms.internal.p002firebaseauthapi.zzai.zza((java.lang.Object) r13, (java.lang.Object) r11)
            int r14 = r13.hashCode()
            int r14 = com.google.android.gms.internal.p002firebaseauthapi.zzah.zza(r14)
        L_0x0167:
            r14 = r14 & r7
            r15 = r4[r14]
            if (r15 != r10) goto L_0x0179
            r4[r14] = r12
            if (r9 >= r8) goto L_0x0176
            r1[r12] = r13
            r12 = r12 ^ 1
            r1[r12] = r11
        L_0x0176:
            int r9 = r9 + 1
            goto L_0x0190
        L_0x0179:
            r10 = r1[r15]
            boolean r10 = r13.equals(r10)
            if (r10 == 0) goto L_0x0194
            com.google.android.gms.internal.firebase-auth-api.zzar r3 = new com.google.android.gms.internal.firebase-auth-api.zzar
            r10 = r15 ^ 1
            r12 = r1[r10]
            java.lang.Object r12 = java.util.Objects.requireNonNull(r12)
            r3.<init>(r13, r11, r12)
            r1[r10] = r11
        L_0x0190:
            int r8 = r8 + 1
            r10 = -1
            goto L_0x0149
        L_0x0194:
            int r14 = r14 + 1
            r10 = -1
            goto L_0x0167
        L_0x0198:
            if (r9 != r0) goto L_0x019c
        L_0x019a:
            r3 = r4
            goto L_0x01a4
        L_0x019c:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r9)
            java.lang.Object[] r3 = new java.lang.Object[]{r4, r7, r3}
        L_0x01a4:
            boolean r4 = r3 instanceof java.lang.Object[]
            if (r4 == 0) goto L_0x01cb
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            r0 = r3[r6]
            com.google.android.gms.internal.firebase-auth-api.zzar r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzar) r0
            if (r2 == 0) goto L_0x01c6
            r2.zza = r0
            r0 = 0
            r0 = r3[r0]
            r2 = r3[r5]
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            int r3 = r2 << 1
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r3)
            r3 = r0
            r0 = r2
            goto L_0x01cb
        L_0x01c6:
            java.lang.IllegalArgumentException r0 = r0.zza()
            throw r0
        L_0x01cb:
            com.google.android.gms.internal.firebase-auth-api.zzaw r2 = new com.google.android.gms.internal.firebase-auth-api.zzaw
            r2.<init>(r3, r1, r0)
            return r2
        L_0x01d1:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "collection too large"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaw.zza(int, java.lang.Object[], com.google.android.gms.internal.firebase-auth-api.zzas):com.google.android.gms.internal.firebase-auth-api.zzaw");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a1 A[RETURN] */
    @javax.annotation.CheckForNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V get(@javax.annotation.CheckForNull java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.zzb
            java.lang.Object[] r1 = r8.zzc
            int r2 = r8.zzd
            r3 = 0
            if (r9 != 0) goto L_0x000c
        L_0x0009:
            r9 = r3
            goto L_0x009e
        L_0x000c:
            r4 = 1
            if (r2 != r4) goto L_0x0024
            r0 = 0
            r0 = r1[r0]
            java.lang.Object r0 = java.util.Objects.requireNonNull(r0)
            boolean r9 = r0.equals(r9)
            if (r9 == 0) goto L_0x0009
            r9 = r1[r4]
            java.lang.Object r9 = java.util.Objects.requireNonNull(r9)
            goto L_0x009e
        L_0x0024:
            if (r0 != 0) goto L_0x0027
            goto L_0x0009
        L_0x0027:
            boolean r2 = r0 instanceof byte[]
            if (r2 == 0) goto L_0x0052
            r2 = r0
            byte[] r2 = (byte[]) r2
            int r0 = r2.length
            int r5 = r0 + -1
            int r0 = r9.hashCode()
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzah.zza(r0)
        L_0x0039:
            r0 = r0 & r5
            byte r6 = r2[r0]
            r7 = 255(0xff, float:3.57E-43)
            r6 = r6 & r7
            if (r6 != r7) goto L_0x0042
            goto L_0x0009
        L_0x0042:
            r7 = r1[r6]
            boolean r7 = r9.equals(r7)
            if (r7 == 0) goto L_0x004f
            r9 = r6 ^ 1
            r9 = r1[r9]
            goto L_0x009e
        L_0x004f:
            int r0 = r0 + 1
            goto L_0x0039
        L_0x0052:
            boolean r2 = r0 instanceof short[]
            if (r2 == 0) goto L_0x007e
            r2 = r0
            short[] r2 = (short[]) r2
            int r0 = r2.length
            int r5 = r0 + -1
            int r0 = r9.hashCode()
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzah.zza(r0)
        L_0x0064:
            r0 = r0 & r5
            short r6 = r2[r0]
            r7 = 65535(0xffff, float:9.1834E-41)
            r6 = r6 & r7
            if (r6 != r7) goto L_0x006e
            goto L_0x0009
        L_0x006e:
            r7 = r1[r6]
            boolean r7 = r9.equals(r7)
            if (r7 == 0) goto L_0x007b
            r9 = r6 ^ 1
            r9 = r1[r9]
            goto L_0x009e
        L_0x007b:
            int r0 = r0 + 1
            goto L_0x0064
        L_0x007e:
            int[] r0 = (int[]) r0
            int r2 = r0.length
            int r2 = r2 - r4
            int r5 = r9.hashCode()
            int r5 = com.google.android.gms.internal.p002firebaseauthapi.zzah.zza(r5)
        L_0x008a:
            r5 = r5 & r2
            r6 = r0[r5]
            r7 = -1
            if (r6 != r7) goto L_0x0092
            goto L_0x0009
        L_0x0092:
            r7 = r1[r6]
            boolean r7 = r9.equals(r7)
            if (r7 == 0) goto L_0x00a2
            r9 = r6 ^ 1
            r9 = r1[r9]
        L_0x009e:
            if (r9 != 0) goto L_0x00a1
            return r3
        L_0x00a1:
            return r9
        L_0x00a2:
            int r5 = r5 + 1
            goto L_0x008a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaw.get(java.lang.Object):java.lang.Object");
    }

    private zzaw(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzb = obj;
        this.zzc = objArr;
        this.zzd = i;
    }
}
