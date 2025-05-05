package com.google.android.gms.internal.atv_ads_framework;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzbq extends zzbh {
    static final zzbh zza = new zzbq((Object) null, new Object[0], 0);
    final transient Object[] zzb;
    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzbq(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [short[], byte[]], vars: [r5v5 ?, r5v7 ?, r5v6 ?, r5v8 ?]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:51)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    static com.google.android.gms.internal.atv_ads_framework.zzbq zzf(int r17, java.lang.Object[] r18, com.google.android.gms.internal.atv_ads_framework.zzbg r19) {
        /*
            r0 = r17
            r1 = r18
            if (r0 != 0) goto L_0x000b
            com.google.android.gms.internal.atv_ads_framework.zzbh r0 = zza
            com.google.android.gms.internal.atv_ads_framework.zzbq r0 = (com.google.android.gms.internal.atv_ads_framework.zzbq) r0
            return r0
        L_0x000b:
            r2 = 0
            r3 = 0
            r4 = 1
            if (r0 != r4) goto L_0x0023
            r0 = r1[r3]
            r0.getClass()
            r3 = r1[r4]
            r3.getClass()
            com.google.android.gms.internal.atv_ads_framework.zzaz.zza(r0, r3)
            com.google.android.gms.internal.atv_ads_framework.zzbq r0 = new com.google.android.gms.internal.atv_ads_framework.zzbq
            r0.<init>(r2, r1, r4)
            return r0
        L_0x0023:
            int r5 = r1.length
            int r5 = r5 >> r4
            java.lang.String r6 = "index"
            com.google.android.gms.internal.atv_ads_framework.zzaq.zzb(r0, r5, r6)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzbi.zzh(r17)
            if (r0 != r4) goto L_0x003f
            r5 = r1[r3]
            r5.getClass()
            r6 = r1[r4]
            r6.getClass()
            com.google.android.gms.internal.atv_ads_framework.zzaz.zza(r5, r6)
            goto L_0x0171
        L_0x003f:
            int r6 = r5 + -1
            r7 = 128(0x80, float:1.794E-43)
            r8 = -1
            if (r5 > r7) goto L_0x00ab
            byte[] r5 = new byte[r5]
            java.util.Arrays.fill(r5, r8)
            r7 = r3
            r8 = r7
        L_0x004d:
            if (r7 >= r0) goto L_0x009c
            int r9 = r8 + r8
            int r10 = r7 + r7
            r11 = r1[r10]
            r11.getClass()
            r10 = r10 ^ r4
            r10 = r1[r10]
            r10.getClass()
            com.google.android.gms.internal.atv_ads_framework.zzaz.zza(r11, r10)
            int r12 = r11.hashCode()
            int r12 = com.google.android.gms.internal.atv_ads_framework.zzba.zza(r12)
        L_0x0069:
            r12 = r12 & r6
            byte r13 = r5[r12]
            r14 = 255(0xff, float:3.57E-43)
            r13 = r13 & r14
            if (r13 != r14) goto L_0x007f
            byte r13 = (byte) r9
            r5[r12] = r13
            if (r8 >= r7) goto L_0x007c
            r1[r9] = r11
            r9 = r9 ^ 1
            r1[r9] = r10
        L_0x007c:
            int r8 = r8 + 1
            goto L_0x0096
        L_0x007f:
            r14 = r1[r13]
            boolean r14 = r11.equals(r14)
            if (r14 == 0) goto L_0x0099
            r2 = r13 ^ 1
            com.google.android.gms.internal.atv_ads_framework.zzbf r9 = new com.google.android.gms.internal.atv_ads_framework.zzbf
            r12 = r1[r2]
            r12.getClass()
            r9.<init>(r11, r10, r12)
            r1[r2] = r10
            r2 = r9
        L_0x0096:
            int r7 = r7 + 1
            goto L_0x004d
        L_0x0099:
            int r12 = r12 + 1
            goto L_0x0069
        L_0x009c:
            if (r8 != r0) goto L_0x00a1
        L_0x009e:
            r2 = r5
            goto L_0x0171
        L_0x00a1:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r8)
            java.lang.Object[] r2 = new java.lang.Object[]{r5, r6, r2}
            goto L_0x0171
        L_0x00ab:
            r7 = 32768(0x8000, float:4.5918E-41)
            if (r5 > r7) goto L_0x0113
            short[] r5 = new short[r5]
            java.util.Arrays.fill(r5, r8)
            r7 = r3
            r8 = r7
        L_0x00b7:
            if (r7 >= r0) goto L_0x0107
            int r9 = r8 + r8
            int r10 = r7 + r7
            r11 = r1[r10]
            r11.getClass()
            r10 = r10 ^ r4
            r10 = r1[r10]
            r10.getClass()
            com.google.android.gms.internal.atv_ads_framework.zzaz.zza(r11, r10)
            int r12 = r11.hashCode()
            int r12 = com.google.android.gms.internal.atv_ads_framework.zzba.zza(r12)
        L_0x00d3:
            r12 = r12 & r6
            short r13 = r5[r12]
            char r13 = (char) r13
            r14 = 65535(0xffff, float:9.1834E-41)
            if (r13 != r14) goto L_0x00ea
            short r13 = (short) r9
            r5[r12] = r13
            if (r8 >= r7) goto L_0x00e7
            r1[r9] = r11
            r9 = r9 ^ 1
            r1[r9] = r10
        L_0x00e7:
            int r8 = r8 + 1
            goto L_0x0101
        L_0x00ea:
            r14 = r1[r13]
            boolean r14 = r11.equals(r14)
            if (r14 == 0) goto L_0x0104
            r2 = r13 ^ 1
            com.google.android.gms.internal.atv_ads_framework.zzbf r9 = new com.google.android.gms.internal.atv_ads_framework.zzbf
            r12 = r1[r2]
            r12.getClass()
            r9.<init>(r11, r10, r12)
            r1[r2] = r10
            r2 = r9
        L_0x0101:
            int r7 = r7 + 1
            goto L_0x00b7
        L_0x0104:
            int r12 = r12 + 1
            goto L_0x00d3
        L_0x0107:
            if (r8 != r0) goto L_0x010a
            goto L_0x009e
        L_0x010a:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r8)
            java.lang.Object[] r2 = new java.lang.Object[]{r5, r6, r2}
            goto L_0x0171
        L_0x0113:
            int[] r5 = new int[r5]
            java.util.Arrays.fill(r5, r8)
            r7 = r3
            r9 = r7
        L_0x011a:
            if (r7 >= r0) goto L_0x0165
            int r10 = r9 + r9
            int r11 = r7 + r7
            r12 = r1[r11]
            r12.getClass()
            r11 = r11 ^ r4
            r11 = r1[r11]
            r11.getClass()
            com.google.android.gms.internal.atv_ads_framework.zzaz.zza(r12, r11)
            int r13 = r12.hashCode()
            int r13 = com.google.android.gms.internal.atv_ads_framework.zzba.zza(r13)
        L_0x0136:
            r13 = r13 & r6
            r14 = r5[r13]
            if (r14 != r8) goto L_0x0148
            r5[r13] = r10
            if (r9 >= r7) goto L_0x0145
            r1[r10] = r12
            r10 = r10 ^ 1
            r1[r10] = r11
        L_0x0145:
            int r9 = r9 + 1
            goto L_0x015f
        L_0x0148:
            r15 = r1[r14]
            boolean r15 = r12.equals(r15)
            if (r15 == 0) goto L_0x0162
            r2 = r14 ^ 1
            com.google.android.gms.internal.atv_ads_framework.zzbf r10 = new com.google.android.gms.internal.atv_ads_framework.zzbf
            r13 = r1[r2]
            r13.getClass()
            r10.<init>(r12, r11, r13)
            r1[r2] = r11
            r2 = r10
        L_0x015f:
            int r7 = r7 + 1
            goto L_0x011a
        L_0x0162:
            int r13 = r13 + 1
            goto L_0x0136
        L_0x0165:
            if (r9 != r0) goto L_0x0169
            goto L_0x009e
        L_0x0169:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r9)
            java.lang.Object[] r2 = new java.lang.Object[]{r5, r6, r2}
        L_0x0171:
            boolean r5 = r2 instanceof java.lang.Object[]
            if (r5 == 0) goto L_0x0195
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            r0 = 2
            r0 = r2[r0]
            com.google.android.gms.internal.atv_ads_framework.zzbf r0 = (com.google.android.gms.internal.atv_ads_framework.zzbf) r0
            r5 = r19
            r5.zzc = r0
            r0 = r2[r3]
            r2 = r2[r4]
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            int r3 = r2 + r2
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r3)
            r16 = r2
            r2 = r0
            r0 = r16
        L_0x0195:
            com.google.android.gms.internal.atv_ads_framework.zzbq r3 = new com.google.android.gms.internal.atv_ads_framework.zzbq
            r3.<init>(r2, r1, r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.atv_ads_framework.zzbq.zzf(int, java.lang.Object[], com.google.android.gms.internal.atv_ads_framework.zzbg):com.google.android.gms.internal.atv_ads_framework.zzbq");
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
            int r0 = com.google.android.gms.internal.atv_ads_framework.zzba.zza(r0)
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
            int r0 = com.google.android.gms.internal.atv_ads_framework.zzba.zza(r0)
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
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzba.zza(r6)
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.atv_ads_framework.zzbq.get(java.lang.Object):java.lang.Object");
    }

    public final int size() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final zzbb zza() {
        return new zzbp(this.zzb, 1, this.zzd);
    }

    /* access modifiers changed from: package-private */
    public final zzbi zzc() {
        return new zzbn(this, this.zzb, 0, this.zzd);
    }

    /* access modifiers changed from: package-private */
    public final zzbi zzd() {
        return new zzbo(this, new zzbp(this.zzb, 0, this.zzd));
    }
}
