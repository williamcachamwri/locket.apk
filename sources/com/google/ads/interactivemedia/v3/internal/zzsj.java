package com.google.ads.interactivemedia.v3.internal;

import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzsj extends zzrp {
    static final zzrp zza = new zzsj((Object) null, new Object[0], 0);
    final transient Object[] zzb;
    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzsj(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [short[], byte[]], vars: [r6v4 ?, r6v6 ?, r6v5 ?, r6v7 ?]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:51)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    static com.google.ads.interactivemedia.v3.internal.zzsj zzl(int r16, java.lang.Object[] r17, com.google.ads.interactivemedia.v3.internal.zzro r18) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            if (r0 != 0) goto L_0x000d
            com.google.ads.interactivemedia.v3.internal.zzrp r0 = zza
            com.google.ads.interactivemedia.v3.internal.zzsj r0 = (com.google.ads.interactivemedia.v3.internal.zzsj) r0
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
            com.google.ads.interactivemedia.v3.internal.zzqt.zzb(r0, r2)
            com.google.ads.interactivemedia.v3.internal.zzsj r0 = new com.google.ads.interactivemedia.v3.internal.zzsj
            r0.<init>(r3, r1, r5)
            return r0
        L_0x0027:
            int r6 = r1.length
            int r6 = r6 >> r5
            java.lang.String r7 = "index"
            com.google.ads.interactivemedia.v3.internal.zzqh.zzb(r0, r6, r7)
            int r6 = com.google.ads.interactivemedia.v3.internal.zzrr.zzj(r16)
            if (r0 != r5) goto L_0x0046
            r0 = r1[r4]
            java.lang.Object r0 = java.util.Objects.requireNonNull(r0)
            r6 = r1[r5]
            java.lang.Object r6 = java.util.Objects.requireNonNull(r6)
            com.google.ads.interactivemedia.v3.internal.zzqt.zzb(r0, r6)
            r0 = r5
            goto L_0x0183
        L_0x0046:
            int r7 = r6 + -1
            r8 = 128(0x80, float:1.794E-43)
            r9 = -1
            if (r6 > r8) goto L_0x00b5
            byte[] r6 = new byte[r6]
            java.util.Arrays.fill(r6, r9)
            r8 = r4
            r9 = r8
        L_0x0054:
            if (r8 >= r0) goto L_0x00a6
            int r10 = r9 + r9
            int r11 = r8 + r8
            r12 = r1[r11]
            java.lang.Object r12 = java.util.Objects.requireNonNull(r12)
            r11 = r11 ^ r5
            r11 = r1[r11]
            java.lang.Object r11 = java.util.Objects.requireNonNull(r11)
            com.google.ads.interactivemedia.v3.internal.zzqt.zzb(r12, r11)
            int r13 = r12.hashCode()
            int r13 = com.google.ads.interactivemedia.v3.internal.zzrg.zzb(r13)
        L_0x0072:
            r13 = r13 & r7
            byte r14 = r6[r13]
            r15 = 255(0xff, float:3.57E-43)
            r14 = r14 & r15
            if (r14 != r15) goto L_0x0088
            byte r14 = (byte) r10
            r6[r13] = r14
            if (r9 >= r8) goto L_0x0085
            r1[r10] = r12
            r10 = r10 ^ 1
            r1[r10] = r11
        L_0x0085:
            int r9 = r9 + 1
            goto L_0x00a0
        L_0x0088:
            r15 = r1[r14]
            boolean r15 = r12.equals(r15)
            if (r15 == 0) goto L_0x00a3
            r3 = r14 ^ 1
            com.google.ads.interactivemedia.v3.internal.zzrn r10 = new com.google.ads.interactivemedia.v3.internal.zzrn
            r13 = r1[r3]
            java.lang.Object r13 = java.util.Objects.requireNonNull(r13)
            r10.<init>(r12, r11, r13)
            r1[r3] = r11
            r3 = r10
        L_0x00a0:
            int r8 = r8 + 1
            goto L_0x0054
        L_0x00a3:
            int r13 = r13 + 1
            goto L_0x0072
        L_0x00a6:
            if (r9 != r0) goto L_0x00ab
        L_0x00a8:
            r3 = r6
            goto L_0x0183
        L_0x00ab:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r9)
            java.lang.Object[] r3 = new java.lang.Object[]{r6, r7, r3}
            goto L_0x0183
        L_0x00b5:
            r8 = 32768(0x8000, float:4.5918E-41)
            if (r6 > r8) goto L_0x0120
            short[] r6 = new short[r6]
            java.util.Arrays.fill(r6, r9)
            r8 = r4
            r9 = r8
        L_0x00c1:
            if (r8 >= r0) goto L_0x0114
            int r10 = r9 + r9
            int r11 = r8 + r8
            r12 = r1[r11]
            java.lang.Object r12 = java.util.Objects.requireNonNull(r12)
            r11 = r11 ^ r5
            r11 = r1[r11]
            java.lang.Object r11 = java.util.Objects.requireNonNull(r11)
            com.google.ads.interactivemedia.v3.internal.zzqt.zzb(r12, r11)
            int r13 = r12.hashCode()
            int r13 = com.google.ads.interactivemedia.v3.internal.zzrg.zzb(r13)
        L_0x00df:
            r13 = r13 & r7
            short r14 = r6[r13]
            char r14 = (char) r14
            r15 = 65535(0xffff, float:9.1834E-41)
            if (r14 != r15) goto L_0x00f6
            short r14 = (short) r10
            r6[r13] = r14
            if (r9 >= r8) goto L_0x00f3
            r1[r10] = r12
            r10 = r10 ^ 1
            r1[r10] = r11
        L_0x00f3:
            int r9 = r9 + 1
            goto L_0x010e
        L_0x00f6:
            r15 = r1[r14]
            boolean r15 = r12.equals(r15)
            if (r15 == 0) goto L_0x0111
            r3 = r14 ^ 1
            com.google.ads.interactivemedia.v3.internal.zzrn r10 = new com.google.ads.interactivemedia.v3.internal.zzrn
            r13 = r1[r3]
            java.lang.Object r13 = java.util.Objects.requireNonNull(r13)
            r10.<init>(r12, r11, r13)
            r1[r3] = r11
            r3 = r10
        L_0x010e:
            int r8 = r8 + 1
            goto L_0x00c1
        L_0x0111:
            int r13 = r13 + 1
            goto L_0x00df
        L_0x0114:
            if (r9 != r0) goto L_0x0117
            goto L_0x00a8
        L_0x0117:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r9)
            java.lang.Object[] r3 = new java.lang.Object[]{r6, r7, r3}
            goto L_0x0183
        L_0x0120:
            int[] r6 = new int[r6]
            java.util.Arrays.fill(r6, r9)
            r8 = r4
            r10 = r8
        L_0x0127:
            if (r8 >= r0) goto L_0x0177
            int r11 = r10 + r10
            int r12 = r8 + r8
            r13 = r1[r12]
            java.lang.Object r13 = java.util.Objects.requireNonNull(r13)
            r12 = r12 ^ r5
            r12 = r1[r12]
            java.lang.Object r12 = java.util.Objects.requireNonNull(r12)
            com.google.ads.interactivemedia.v3.internal.zzqt.zzb(r13, r12)
            int r14 = r13.hashCode()
            int r14 = com.google.ads.interactivemedia.v3.internal.zzrg.zzb(r14)
        L_0x0145:
            r14 = r14 & r7
            r15 = r6[r14]
            if (r15 != r9) goto L_0x0157
            r6[r14] = r11
            if (r10 >= r8) goto L_0x0154
            r1[r11] = r13
            r11 = r11 ^ 1
            r1[r11] = r12
        L_0x0154:
            int r10 = r10 + 1
            goto L_0x016f
        L_0x0157:
            r9 = r1[r15]
            boolean r9 = r13.equals(r9)
            if (r9 == 0) goto L_0x0173
            r3 = r15 ^ 1
            com.google.ads.interactivemedia.v3.internal.zzrn r9 = new com.google.ads.interactivemedia.v3.internal.zzrn
            r11 = r1[r3]
            java.lang.Object r11 = java.util.Objects.requireNonNull(r11)
            r9.<init>(r13, r12, r11)
            r1[r3] = r12
            r3 = r9
        L_0x016f:
            int r8 = r8 + 1
            r9 = -1
            goto L_0x0127
        L_0x0173:
            int r14 = r14 + 1
            r9 = -1
            goto L_0x0145
        L_0x0177:
            if (r10 != r0) goto L_0x017b
            goto L_0x00a8
        L_0x017b:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r10)
            java.lang.Object[] r3 = new java.lang.Object[]{r6, r7, r3}
        L_0x0183:
            boolean r6 = r3 instanceof java.lang.Object[]
            if (r6 == 0) goto L_0x01aa
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            r0 = 2
            r0 = r3[r0]
            com.google.ads.interactivemedia.v3.internal.zzrn r0 = (com.google.ads.interactivemedia.v3.internal.zzrn) r0
            if (r2 == 0) goto L_0x01a5
            r2.zzc = r0
            r0 = r3[r4]
            r2 = r3[r5]
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            int r3 = r2 + r2
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r3)
            r3 = r0
            r0 = r2
            goto L_0x01aa
        L_0x01a5:
            java.lang.IllegalArgumentException r0 = r0.zza()
            throw r0
        L_0x01aa:
            com.google.ads.interactivemedia.v3.internal.zzsj r2 = new com.google.ads.interactivemedia.v3.internal.zzsj
            r2.<init>(r3, r1, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzsj.zzl(int, java.lang.Object[], com.google.ads.interactivemedia.v3.internal.zzro):com.google.ads.interactivemedia.v3.internal.zzsj");
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a1 A[RETURN] */
    @javax.annotation.CheckForNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r10) {
        /*
            r9 = this;
            r0 = 0
            if (r10 != 0) goto L_0x0006
        L_0x0003:
            r10 = r0
            goto L_0x009e
        L_0x0006:
            int r1 = r9.zzd
            java.lang.Object[] r2 = r9.zzb
            r3 = 1
            if (r1 != r3) goto L_0x0022
            r1 = 0
            r1 = r2[r1]
            java.lang.Object r1 = java.util.Objects.requireNonNull(r1)
            boolean r10 = r1.equals(r10)
            if (r10 == 0) goto L_0x0003
            r10 = r2[r3]
            java.lang.Object r10 = java.util.Objects.requireNonNull(r10)
            goto L_0x009e
        L_0x0022:
            java.lang.Object r1 = r9.zzc
            if (r1 != 0) goto L_0x0027
            goto L_0x0003
        L_0x0027:
            boolean r4 = r1 instanceof byte[]
            r5 = -1
            if (r4 == 0) goto L_0x0053
            r4 = r1
            byte[] r4 = (byte[]) r4
            int r1 = r4.length
            int r6 = r1 + -1
            int r1 = r10.hashCode()
            int r1 = com.google.ads.interactivemedia.v3.internal.zzrg.zzb(r1)
        L_0x003a:
            r1 = r1 & r6
            byte r5 = r4[r1]
            r7 = 255(0xff, float:3.57E-43)
            r5 = r5 & r7
            if (r5 != r7) goto L_0x0043
            goto L_0x0003
        L_0x0043:
            r7 = r2[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L_0x0050
            r10 = r5 ^ 1
            r10 = r2[r10]
            goto L_0x009e
        L_0x0050:
            int r1 = r1 + 1
            goto L_0x003a
        L_0x0053:
            boolean r4 = r1 instanceof short[]
            if (r4 == 0) goto L_0x007f
            r4 = r1
            short[] r4 = (short[]) r4
            int r1 = r4.length
            int r6 = r1 + -1
            int r1 = r10.hashCode()
            int r1 = com.google.ads.interactivemedia.v3.internal.zzrg.zzb(r1)
        L_0x0065:
            r1 = r1 & r6
            short r5 = r4[r1]
            char r5 = (char) r5
            r7 = 65535(0xffff, float:9.1834E-41)
            if (r5 != r7) goto L_0x006f
            goto L_0x0003
        L_0x006f:
            r7 = r2[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L_0x007c
            r10 = r5 ^ 1
            r10 = r2[r10]
            goto L_0x009e
        L_0x007c:
            int r1 = r1 + 1
            goto L_0x0065
        L_0x007f:
            int[] r1 = (int[]) r1
            int r4 = r1.length
            int r4 = r4 + r5
            int r6 = r10.hashCode()
            int r6 = com.google.ads.interactivemedia.v3.internal.zzrg.zzb(r6)
        L_0x008b:
            r6 = r6 & r4
            r7 = r1[r6]
            if (r7 != r5) goto L_0x0092
            goto L_0x0003
        L_0x0092:
            r8 = r2[r7]
            boolean r8 = r10.equals(r8)
            if (r8 == 0) goto L_0x00a2
            r10 = r7 ^ 1
            r10 = r2[r10]
        L_0x009e:
            if (r10 != 0) goto L_0x00a1
            return r0
        L_0x00a1:
            return r10
        L_0x00a2:
            int r6 = r6 + 1
            goto L_0x008b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzsj.get(java.lang.Object):java.lang.Object");
    }

    public final int size() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final zzri zza() {
        return new zzsi(this.zzb, 1, this.zzd);
    }

    /* access modifiers changed from: package-private */
    public final zzrr zzg() {
        return new zzsg(this, this.zzb, 0, this.zzd);
    }

    /* access modifiers changed from: package-private */
    public final zzrr zzh() {
        return new zzsh(this, new zzsi(this.zzb, 0, this.zzd));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzk() {
        return false;
    }
}
