package com.google.android.gms.internal.pal;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzjj extends zzjc {
    static final zzjc zza = new zzjj((Object) null, new Object[0], 0);
    final transient Object[] zzb;
    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzjj(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: short[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: short[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: short[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.internal.pal.zzjj zzk(int r16, java.lang.Object[] r17, com.google.android.gms.internal.pal.zzjb r18) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            if (r0 != 0) goto L_0x000d
            com.google.android.gms.internal.pal.zzjc r0 = zza
            com.google.android.gms.internal.pal.zzjj r0 = (com.google.android.gms.internal.pal.zzjj) r0
            return r0
        L_0x000d:
            r3 = 0
            r4 = 0
            r5 = 1
            if (r0 != r5) goto L_0x0025
            r0 = r1[r4]
            r0.getClass()
            r2 = r1[r5]
            r2.getClass()
            com.google.android.gms.internal.pal.zziu.zza(r0, r2)
            com.google.android.gms.internal.pal.zzjj r0 = new com.google.android.gms.internal.pal.zzjj
            r0.<init>(r3, r1, r5)
            return r0
        L_0x0025:
            int r6 = r1.length
            int r6 = r6 >> r5
            java.lang.String r7 = "index"
            com.google.android.gms.internal.pal.zzip.zzb(r0, r6, r7)
            r6 = 2
            int r7 = java.lang.Math.max(r0, r6)
            r8 = 751619276(0x2ccccccc, float:5.8207657E-12)
            if (r7 >= r8) goto L_0x004a
            int r8 = r7 + -1
            int r8 = java.lang.Integer.highestOneBit(r8)
        L_0x003c:
            int r8 = r8 + r8
            double r9 = (double) r8
            r11 = 4604480259023595110(0x3fe6666666666666, double:0.7)
            double r9 = r9 * r11
            double r11 = (double) r7
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L_0x004e
            goto L_0x003c
        L_0x004a:
            r8 = 1073741824(0x40000000, float:2.0)
            if (r7 >= r8) goto L_0x01c2
        L_0x004e:
            if (r0 != r5) goto L_0x005f
            r7 = r1[r4]
            r7.getClass()
            r8 = r1[r5]
            r8.getClass()
            com.google.android.gms.internal.pal.zziu.zza(r7, r8)
            goto L_0x0195
        L_0x005f:
            int r7 = r8 + -1
            r9 = 128(0x80, float:1.794E-43)
            r10 = -1
            if (r8 > r9) goto L_0x00cd
            byte[] r8 = new byte[r8]
            java.util.Arrays.fill(r8, r10)
            r9 = r4
            r10 = r9
        L_0x006d:
            if (r9 >= r0) goto L_0x00be
            int r11 = r9 + r9
            int r12 = r10 + r10
            r13 = r1[r11]
            r13.getClass()
            r11 = r11 ^ r5
            r11 = r1[r11]
            r11.getClass()
            com.google.android.gms.internal.pal.zziu.zza(r13, r11)
            int r14 = r13.hashCode()
            int r14 = com.google.android.gms.internal.pal.zziv.zza(r14)
        L_0x0089:
            r14 = r14 & r7
            byte r15 = r8[r14]
            r4 = 255(0xff, float:3.57E-43)
            r15 = r15 & r4
            if (r15 != r4) goto L_0x009f
            byte r4 = (byte) r12
            r8[r14] = r4
            if (r10 >= r9) goto L_0x009c
            r1[r12] = r13
            r4 = r12 ^ 1
            r1[r4] = r11
        L_0x009c:
            int r10 = r10 + 1
            goto L_0x00b6
        L_0x009f:
            r4 = r1[r15]
            boolean r4 = r13.equals(r4)
            if (r4 == 0) goto L_0x00ba
            r3 = r15 ^ 1
            com.google.android.gms.internal.pal.zzja r4 = new com.google.android.gms.internal.pal.zzja
            r12 = r1[r3]
            r12.getClass()
            r4.<init>(r13, r11, r12)
            r1[r3] = r11
            r3 = r4
        L_0x00b6:
            int r9 = r9 + 1
            r4 = 0
            goto L_0x006d
        L_0x00ba:
            int r14 = r14 + 1
            r4 = 0
            goto L_0x0089
        L_0x00be:
            if (r10 != r0) goto L_0x00c3
            r3 = r8
            goto L_0x0195
        L_0x00c3:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            java.lang.Object[] r3 = new java.lang.Object[]{r8, r4, r3}
            goto L_0x0195
        L_0x00cd:
            r4 = 32768(0x8000, float:4.5918E-41)
            if (r8 > r4) goto L_0x0135
            short[] r4 = new short[r8]
            java.util.Arrays.fill(r4, r10)
            r8 = 0
            r9 = 0
        L_0x00d9:
            if (r8 >= r0) goto L_0x0129
            int r10 = r8 + r8
            int r11 = r9 + r9
            r12 = r1[r10]
            r12.getClass()
            r10 = r10 ^ r5
            r10 = r1[r10]
            r10.getClass()
            com.google.android.gms.internal.pal.zziu.zza(r12, r10)
            int r13 = r12.hashCode()
            int r13 = com.google.android.gms.internal.pal.zziv.zza(r13)
        L_0x00f5:
            r13 = r13 & r7
            short r14 = r4[r13]
            char r14 = (char) r14
            r15 = 65535(0xffff, float:9.1834E-41)
            if (r14 != r15) goto L_0x010c
            short r14 = (short) r11
            r4[r13] = r14
            if (r9 >= r8) goto L_0x0109
            r1[r11] = r12
            r11 = r11 ^ 1
            r1[r11] = r10
        L_0x0109:
            int r9 = r9 + 1
            goto L_0x0123
        L_0x010c:
            r15 = r1[r14]
            boolean r15 = r12.equals(r15)
            if (r15 == 0) goto L_0x0126
            r3 = r14 ^ 1
            com.google.android.gms.internal.pal.zzja r11 = new com.google.android.gms.internal.pal.zzja
            r13 = r1[r3]
            r13.getClass()
            r11.<init>(r12, r10, r13)
            r1[r3] = r10
            r3 = r11
        L_0x0123:
            int r8 = r8 + 1
            goto L_0x00d9
        L_0x0126:
            int r13 = r13 + 1
            goto L_0x00f5
        L_0x0129:
            if (r9 != r0) goto L_0x012c
            goto L_0x018b
        L_0x012c:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r9)
            java.lang.Object[] r3 = new java.lang.Object[]{r4, r7, r3}
            goto L_0x0195
        L_0x0135:
            int[] r4 = new int[r8]
            java.util.Arrays.fill(r4, r10)
            r8 = 0
            r9 = 0
        L_0x013c:
            if (r8 >= r0) goto L_0x0189
            int r11 = r8 + r8
            int r12 = r9 + r9
            r13 = r1[r11]
            r13.getClass()
            r11 = r11 ^ r5
            r11 = r1[r11]
            r11.getClass()
            com.google.android.gms.internal.pal.zziu.zza(r13, r11)
            int r14 = r13.hashCode()
            int r14 = com.google.android.gms.internal.pal.zziv.zza(r14)
        L_0x0158:
            r14 = r14 & r7
            r15 = r4[r14]
            if (r15 != r10) goto L_0x016a
            r4[r14] = r12
            if (r9 >= r8) goto L_0x0167
            r1[r12] = r13
            r12 = r12 ^ 1
            r1[r12] = r11
        L_0x0167:
            int r9 = r9 + 1
            goto L_0x0181
        L_0x016a:
            r10 = r1[r15]
            boolean r10 = r13.equals(r10)
            if (r10 == 0) goto L_0x0185
            r3 = r15 ^ 1
            com.google.android.gms.internal.pal.zzja r10 = new com.google.android.gms.internal.pal.zzja
            r12 = r1[r3]
            r12.getClass()
            r10.<init>(r13, r11, r12)
            r1[r3] = r11
            r3 = r10
        L_0x0181:
            int r8 = r8 + 1
            r10 = -1
            goto L_0x013c
        L_0x0185:
            int r14 = r14 + 1
            r10 = -1
            goto L_0x0158
        L_0x0189:
            if (r9 != r0) goto L_0x018d
        L_0x018b:
            r3 = r4
            goto L_0x0195
        L_0x018d:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r9)
            java.lang.Object[] r3 = new java.lang.Object[]{r4, r7, r3}
        L_0x0195:
            boolean r4 = r3 instanceof java.lang.Object[]
            if (r4 == 0) goto L_0x01bc
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            r0 = r3[r6]
            com.google.android.gms.internal.pal.zzja r0 = (com.google.android.gms.internal.pal.zzja) r0
            if (r2 == 0) goto L_0x01b7
            r2.zzc = r0
            r0 = 0
            r0 = r3[r0]
            r2 = r3[r5]
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            int r3 = r2 + r2
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r3)
            r3 = r0
            r0 = r2
            goto L_0x01bc
        L_0x01b7:
            java.lang.IllegalArgumentException r0 = r0.zza()
            throw r0
        L_0x01bc:
            com.google.android.gms.internal.pal.zzjj r2 = new com.google.android.gms.internal.pal.zzjj
            r2.<init>(r3, r1, r0)
            return r2
        L_0x01c2:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "collection too large"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzjj.zzk(int, java.lang.Object[], com.google.android.gms.internal.pal.zzjb):com.google.android.gms.internal.pal.zzjj");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x009e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009f A[RETURN] */
    @javax.annotation.CheckForNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.zzc
            java.lang.Object[] r1 = r9.zzb
            int r2 = r9.zzd
            r3 = 0
            if (r10 != 0) goto L_0x000c
        L_0x0009:
            r10 = r3
            goto L_0x009c
        L_0x000c:
            r4 = 1
            if (r2 != r4) goto L_0x0022
            r0 = 0
            r0 = r1[r0]
            r0.getClass()
            boolean r10 = r0.equals(r10)
            if (r10 == 0) goto L_0x0009
            r10 = r1[r4]
            r10.getClass()
            goto L_0x009c
        L_0x0022:
            if (r0 != 0) goto L_0x0025
            goto L_0x0009
        L_0x0025:
            boolean r2 = r0 instanceof byte[]
            r5 = -1
            if (r2 == 0) goto L_0x0051
            r2 = r0
            byte[] r2 = (byte[]) r2
            int r0 = r2.length
            int r6 = r0 + -1
            int r0 = r10.hashCode()
            int r0 = com.google.android.gms.internal.pal.zziv.zza(r0)
        L_0x0038:
            r0 = r0 & r6
            byte r5 = r2[r0]
            r7 = 255(0xff, float:3.57E-43)
            r5 = r5 & r7
            if (r5 != r7) goto L_0x0041
            goto L_0x0009
        L_0x0041:
            r7 = r1[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L_0x004e
            r10 = r5 ^ 1
            r10 = r1[r10]
            goto L_0x009c
        L_0x004e:
            int r0 = r0 + 1
            goto L_0x0038
        L_0x0051:
            boolean r2 = r0 instanceof short[]
            if (r2 == 0) goto L_0x007d
            r2 = r0
            short[] r2 = (short[]) r2
            int r0 = r2.length
            int r6 = r0 + -1
            int r0 = r10.hashCode()
            int r0 = com.google.android.gms.internal.pal.zziv.zza(r0)
        L_0x0063:
            r0 = r0 & r6
            short r5 = r2[r0]
            char r5 = (char) r5
            r7 = 65535(0xffff, float:9.1834E-41)
            if (r5 != r7) goto L_0x006d
            goto L_0x0009
        L_0x006d:
            r7 = r1[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L_0x007a
            r10 = r5 ^ 1
            r10 = r1[r10]
            goto L_0x009c
        L_0x007a:
            int r0 = r0 + 1
            goto L_0x0063
        L_0x007d:
            int[] r0 = (int[]) r0
            int r2 = r0.length
            int r2 = r2 + r5
            int r6 = r10.hashCode()
            int r6 = com.google.android.gms.internal.pal.zziv.zza(r6)
        L_0x0089:
            r6 = r6 & r2
            r7 = r0[r6]
            if (r7 != r5) goto L_0x0090
            goto L_0x0009
        L_0x0090:
            r8 = r1[r7]
            boolean r8 = r10.equals(r8)
            if (r8 == 0) goto L_0x00a0
            r10 = r7 ^ 1
            r10 = r1[r10]
        L_0x009c:
            if (r10 != 0) goto L_0x009f
            return r3
        L_0x009f:
            return r10
        L_0x00a0:
            int r6 = r6 + 1
            goto L_0x0089
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzjj.get(java.lang.Object):java.lang.Object");
    }

    public final int size() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final zziw zza() {
        return new zzji(this.zzb, 1, this.zzd);
    }

    /* access modifiers changed from: package-private */
    public final zzjd zzg() {
        return new zzjg(this, this.zzb, 0, this.zzd);
    }

    /* access modifiers changed from: package-private */
    public final zzjd zzh() {
        return new zzjh(this, new zzji(this.zzb, 0, this.zzd));
    }
}
