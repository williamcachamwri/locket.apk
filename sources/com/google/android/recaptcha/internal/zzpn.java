package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
abstract class zzpn {
    private static volatile int zza = 100;

    zzpn() {
    }

    /* access modifiers changed from: package-private */
    public abstract Object zza(Object obj);

    /* access modifiers changed from: package-private */
    public abstract Object zzb();

    /* access modifiers changed from: package-private */
    public abstract Object zzc(Object obj);

    /* access modifiers changed from: package-private */
    public abstract void zzd(Object obj, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void zze(Object obj, int i, long j);

    /* access modifiers changed from: package-private */
    public abstract void zzf(Object obj, int i, Object obj2);

    /* access modifiers changed from: package-private */
    public abstract void zzg(Object obj, int i, zzlg zzlg);

    /* access modifiers changed from: package-private */
    public abstract void zzh(Object obj, int i, long j);

    /* access modifiers changed from: package-private */
    public abstract void zzi(Object obj);

    /* access modifiers changed from: package-private */
    public abstract void zzj(Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036 A[LOOP:0: B:17:0x0036->B:20:0x0043, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzk(java.lang.Object r8, com.google.android.recaptcha.internal.zzox r9, int r10) throws java.io.IOException {
        /*
            r7 = this;
            int r0 = r9.zzd()
            int r1 = r0 >>> 3
            r0 = r0 & 7
            r2 = 1
            if (r0 == 0) goto L_0x0075
            if (r0 == r2) goto L_0x006d
            r3 = 2
            if (r0 == r3) goto L_0x0065
            r3 = 4
            r4 = 3
            if (r0 == r4) goto L_0x002b
            if (r0 == r3) goto L_0x0029
            r10 = 5
            if (r0 != r10) goto L_0x0021
            int r9 = r9.zzf()
            r7.zzd(r8, r1, r9)
            return r2
        L_0x0021:
            com.google.android.recaptcha.internal.zzno r8 = new com.google.android.recaptcha.internal.zzno
            java.lang.String r9 = "Protocol message tag had invalid wire type."
            r8.<init>(r9)
            throw r8
        L_0x0029:
            r8 = 0
            return r8
        L_0x002b:
            java.lang.Object r0 = r7.zzb()
            int r4 = r1 << 3
            int r10 = r10 + r2
            int r5 = zza
            if (r10 >= r5) goto L_0x005d
        L_0x0036:
            int r5 = r9.zzc()
            r6 = 2147483647(0x7fffffff, float:NaN)
            if (r5 == r6) goto L_0x0045
            boolean r5 = r7.zzk(r0, r9, r10)
            if (r5 != 0) goto L_0x0036
        L_0x0045:
            r10 = r4 | 4
            int r9 = r9.zzd()
            if (r10 != r9) goto L_0x0055
            java.lang.Object r9 = r7.zzc(r0)
            r7.zzf(r8, r1, r9)
            return r2
        L_0x0055:
            com.google.android.recaptcha.internal.zznp r8 = new com.google.android.recaptcha.internal.zznp
            java.lang.String r9 = "Protocol message end-group tag did not match expected tag."
            r8.<init>((java.lang.String) r9)
            throw r8
        L_0x005d:
            com.google.android.recaptcha.internal.zznp r8 = new com.google.android.recaptcha.internal.zznp
            java.lang.String r9 = "Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit."
            r8.<init>((java.lang.String) r9)
            throw r8
        L_0x0065:
            com.google.android.recaptcha.internal.zzlg r9 = r9.zzp()
            r7.zzg(r8, r1, r9)
            return r2
        L_0x006d:
            long r9 = r9.zzk()
            r7.zze(r8, r1, r9)
            return r2
        L_0x0075:
            long r9 = r9.zzl()
            r7.zzh(r8, r1, r9)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzpn.zzk(java.lang.Object, com.google.android.recaptcha.internal.zzox, int):boolean");
    }
}
