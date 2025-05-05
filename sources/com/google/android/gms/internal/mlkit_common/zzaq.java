package com.google.android.gms.internal.mlkit_common;

import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzaq extends zzai {
    static final zzai zza = new zzaq((Object) null, new Object[0], 0);
    final transient Object[] zzb;
    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzaq(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: short[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: short[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: short[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.internal.mlkit_common.zzaq zzg(int r16, java.lang.Object[] r17, com.google.android.gms.internal.mlkit_common.zzah r18) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            if (r0 != 0) goto L_0x000d
            com.google.android.gms.internal.mlkit_common.zzai r0 = zza
            com.google.android.gms.internal.mlkit_common.zzaq r0 = (com.google.android.gms.internal.mlkit_common.zzaq) r0
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
            com.google.android.gms.internal.mlkit_common.zzw.zza(r0, r2)
            com.google.android.gms.internal.mlkit_common.zzaq r0 = new com.google.android.gms.internal.mlkit_common.zzaq
            r0.<init>(r3, r1, r5)
            return r0
        L_0x0027:
            int r6 = r1.length
            int r6 = r6 >> r5
            java.lang.String r7 = "index"
            com.google.android.gms.internal.mlkit_common.zzt.zzb(r0, r6, r7)
            r6 = 2
            int r7 = java.lang.Math.max(r0, r6)
            r8 = 751619276(0x2ccccccc, float:5.8207657E-12)
            if (r7 >= r8) goto L_0x004c
            int r8 = r7 + -1
            int r8 = java.lang.Integer.highestOneBit(r8)
        L_0x003e:
            int r8 = r8 + r8
            double r9 = (double) r8
            r11 = 4604480259023595110(0x3fe6666666666666, double:0.7)
            double r9 = r9 * r11
            double r11 = (double) r7
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L_0x0050
            goto L_0x003e
        L_0x004c:
            r8 = 1073741824(0x40000000, float:2.0)
            if (r7 >= r8) goto L_0x01d0
        L_0x0050:
            if (r0 != r5) goto L_0x0064
            r0 = r1[r4]
            java.lang.Object r0 = java.util.Objects.requireNonNull(r0)
            r7 = r1[r5]
            java.lang.Object r7 = java.util.Objects.requireNonNull(r7)
            com.google.android.gms.internal.mlkit_common.zzw.zza(r0, r7)
            r0 = r5
            goto L_0x01a3
        L_0x0064:
            int r7 = r8 + -1
            r9 = 128(0x80, float:1.794E-43)
            r10 = -1
            if (r8 > r9) goto L_0x00d5
            byte[] r8 = new byte[r8]
            java.util.Arrays.fill(r8, r10)
            r9 = r4
            r10 = r9
        L_0x0072:
            if (r9 >= r0) goto L_0x00c6
            int r11 = r10 + r10
            int r12 = r9 + r9
            r13 = r1[r12]
            java.lang.Object r13 = java.util.Objects.requireNonNull(r13)
            r12 = r12 ^ r5
            r12 = r1[r12]
            java.lang.Object r12 = java.util.Objects.requireNonNull(r12)
            com.google.android.gms.internal.mlkit_common.zzw.zza(r13, r12)
            int r14 = r13.hashCode()
            int r14 = com.google.android.gms.internal.mlkit_common.zzy.zza(r14)
        L_0x0090:
            r14 = r14 & r7
            byte r15 = r8[r14]
            r4 = 255(0xff, float:3.57E-43)
            r15 = r15 & r4
            if (r15 != r4) goto L_0x00a6
            byte r4 = (byte) r11
            r8[r14] = r4
            if (r10 >= r9) goto L_0x00a3
            r1[r11] = r13
            r4 = r11 ^ 1
            r1[r4] = r12
        L_0x00a3:
            int r10 = r10 + 1
            goto L_0x00be
        L_0x00a6:
            r4 = r1[r15]
            boolean r4 = r13.equals(r4)
            if (r4 == 0) goto L_0x00c2
            r3 = r15 ^ 1
            com.google.android.gms.internal.mlkit_common.zzag r4 = new com.google.android.gms.internal.mlkit_common.zzag
            r11 = r1[r3]
            java.lang.Object r11 = java.util.Objects.requireNonNull(r11)
            r4.<init>(r13, r12, r11)
            r1[r3] = r12
            r3 = r4
        L_0x00be:
            int r9 = r9 + 1
            r4 = 0
            goto L_0x0072
        L_0x00c2:
            int r14 = r14 + 1
            r4 = 0
            goto L_0x0090
        L_0x00c6:
            if (r10 != r0) goto L_0x00cb
            r3 = r8
            goto L_0x01a3
        L_0x00cb:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            java.lang.Object[] r3 = new java.lang.Object[]{r8, r4, r3}
            goto L_0x01a3
        L_0x00d5:
            r4 = 32768(0x8000, float:4.5918E-41)
            if (r8 > r4) goto L_0x0140
            short[] r4 = new short[r8]
            java.util.Arrays.fill(r4, r10)
            r8 = 0
            r9 = 0
        L_0x00e1:
            if (r8 >= r0) goto L_0x0134
            int r10 = r9 + r9
            int r11 = r8 + r8
            r12 = r1[r11]
            java.lang.Object r12 = java.util.Objects.requireNonNull(r12)
            r11 = r11 ^ r5
            r11 = r1[r11]
            java.lang.Object r11 = java.util.Objects.requireNonNull(r11)
            com.google.android.gms.internal.mlkit_common.zzw.zza(r12, r11)
            int r13 = r12.hashCode()
            int r13 = com.google.android.gms.internal.mlkit_common.zzy.zza(r13)
        L_0x00ff:
            r13 = r13 & r7
            short r14 = r4[r13]
            char r14 = (char) r14
            r15 = 65535(0xffff, float:9.1834E-41)
            if (r14 != r15) goto L_0x0116
            short r14 = (short) r10
            r4[r13] = r14
            if (r9 >= r8) goto L_0x0113
            r1[r10] = r12
            r10 = r10 ^ 1
            r1[r10] = r11
        L_0x0113:
            int r9 = r9 + 1
            goto L_0x012e
        L_0x0116:
            r15 = r1[r14]
            boolean r15 = r12.equals(r15)
            if (r15 == 0) goto L_0x0131
            r3 = r14 ^ 1
            com.google.android.gms.internal.mlkit_common.zzag r10 = new com.google.android.gms.internal.mlkit_common.zzag
            r13 = r1[r3]
            java.lang.Object r13 = java.util.Objects.requireNonNull(r13)
            r10.<init>(r12, r11, r13)
            r1[r3] = r11
            r3 = r10
        L_0x012e:
            int r8 = r8 + 1
            goto L_0x00e1
        L_0x0131:
            int r13 = r13 + 1
            goto L_0x00ff
        L_0x0134:
            if (r9 != r0) goto L_0x0137
            goto L_0x0199
        L_0x0137:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r9)
            java.lang.Object[] r3 = new java.lang.Object[]{r4, r7, r3}
            goto L_0x01a3
        L_0x0140:
            int[] r4 = new int[r8]
            java.util.Arrays.fill(r4, r10)
            r8 = 0
            r9 = 0
        L_0x0147:
            if (r8 >= r0) goto L_0x0197
            int r11 = r9 + r9
            int r12 = r8 + r8
            r13 = r1[r12]
            java.lang.Object r13 = java.util.Objects.requireNonNull(r13)
            r12 = r12 ^ r5
            r12 = r1[r12]
            java.lang.Object r12 = java.util.Objects.requireNonNull(r12)
            com.google.android.gms.internal.mlkit_common.zzw.zza(r13, r12)
            int r14 = r13.hashCode()
            int r14 = com.google.android.gms.internal.mlkit_common.zzy.zza(r14)
        L_0x0165:
            r14 = r14 & r7
            r15 = r4[r14]
            if (r15 != r10) goto L_0x0177
            r4[r14] = r11
            if (r9 >= r8) goto L_0x0174
            r1[r11] = r13
            r11 = r11 ^ 1
            r1[r11] = r12
        L_0x0174:
            int r9 = r9 + 1
            goto L_0x018f
        L_0x0177:
            r10 = r1[r15]
            boolean r10 = r13.equals(r10)
            if (r10 == 0) goto L_0x0193
            r3 = r15 ^ 1
            com.google.android.gms.internal.mlkit_common.zzag r10 = new com.google.android.gms.internal.mlkit_common.zzag
            r11 = r1[r3]
            java.lang.Object r11 = java.util.Objects.requireNonNull(r11)
            r10.<init>(r13, r12, r11)
            r1[r3] = r12
            r3 = r10
        L_0x018f:
            int r8 = r8 + 1
            r10 = -1
            goto L_0x0147
        L_0x0193:
            int r14 = r14 + 1
            r10 = -1
            goto L_0x0165
        L_0x0197:
            if (r9 != r0) goto L_0x019b
        L_0x0199:
            r3 = r4
            goto L_0x01a3
        L_0x019b:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r9)
            java.lang.Object[] r3 = new java.lang.Object[]{r4, r7, r3}
        L_0x01a3:
            boolean r4 = r3 instanceof java.lang.Object[]
            if (r4 == 0) goto L_0x01ca
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            r0 = r3[r6]
            com.google.android.gms.internal.mlkit_common.zzag r0 = (com.google.android.gms.internal.mlkit_common.zzag) r0
            if (r2 == 0) goto L_0x01c5
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
            goto L_0x01ca
        L_0x01c5:
            java.lang.IllegalArgumentException r0 = r0.zza()
            throw r0
        L_0x01ca:
            com.google.android.gms.internal.mlkit_common.zzaq r2 = new com.google.android.gms.internal.mlkit_common.zzaq
            r2.<init>(r3, r1, r0)
            return r2
        L_0x01d0:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "collection too large"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_common.zzaq.zzg(int, java.lang.Object[], com.google.android.gms.internal.mlkit_common.zzah):com.google.android.gms.internal.mlkit_common.zzaq");
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
            int r1 = com.google.android.gms.internal.mlkit_common.zzy.zza(r1)
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
            int r1 = com.google.android.gms.internal.mlkit_common.zzy.zza(r1)
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
            int r6 = com.google.android.gms.internal.mlkit_common.zzy.zza(r6)
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_common.zzaq.get(java.lang.Object):java.lang.Object");
    }

    public final int size() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final zzab zza() {
        return new zzap(this.zzb, 1, this.zzd);
    }

    /* access modifiers changed from: package-private */
    public final zzaj zzd() {
        return new zzan(this, this.zzb, 0, this.zzd);
    }

    /* access modifiers changed from: package-private */
    public final zzaj zze() {
        return new zzao(this, new zzap(this.zzb, 0, this.zzd));
    }
}
