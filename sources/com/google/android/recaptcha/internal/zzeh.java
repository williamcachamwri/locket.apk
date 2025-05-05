package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzeh implements zzcp {
    private final zzdv zza;
    private zzco zzb = zzco.zza;
    private zzse zzc;

    public zzeh(zzdv zzdv) {
        this.zza = zzdv;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a0 A[Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b9 A[Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zza(java.lang.String r18, com.google.android.recaptcha.RecaptchaAction r19, long r20, kotlin.coroutines.Continuation r22) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r2 = r22
            boolean r3 = r2 instanceof com.google.android.recaptcha.internal.zzef
            if (r3 == 0) goto L_0x0019
            r3 = r2
            com.google.android.recaptcha.internal.zzef r3 = (com.google.android.recaptcha.internal.zzef) r3
            int r4 = r3.zzd
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r4 & r5
            if (r6 == 0) goto L_0x0019
            int r4 = r4 - r5
            r3.zzd = r4
            goto L_0x001e
        L_0x0019:
            com.google.android.recaptcha.internal.zzef r3 = new com.google.android.recaptcha.internal.zzef
            r3.<init>(r1, r2)
        L_0x001e:
            r9 = r3
            java.lang.Object r2 = r9.zzb
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r9.zzd
            r5 = 2
            r6 = 1
            r7 = 0
            if (r4 == 0) goto L_0x0063
            if (r4 == r6) goto L_0x0047
            if (r4 != r5) goto L_0x003f
            java.lang.String r0 = r9.zzf
            r3 = r0
            java.lang.String r3 = (java.lang.String) r3
            com.google.android.recaptcha.internal.zzeh r3 = r9.zze
            r4 = r3
            com.google.android.recaptcha.internal.zzeh r4 = (com.google.android.recaptcha.internal.zzeh) r4
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            goto L_0x00ba
        L_0x003f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0047:
            double r10 = r9.zza
            com.google.android.recaptcha.RecaptchaAction r0 = r9.zzg
            r4 = r0
            com.google.android.recaptcha.RecaptchaAction r4 = (com.google.android.recaptcha.RecaptchaAction) r4
            java.lang.String r4 = r9.zzf
            r6 = r4
            java.lang.String r6 = (java.lang.String) r6
            com.google.android.recaptcha.internal.zzeh r6 = r9.zze
            r8 = r6
            com.google.android.recaptcha.internal.zzeh r8 = (com.google.android.recaptcha.internal.zzeh) r8
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            r11 = r10
            r10 = r6
            r16 = r4
            r4 = r0
            r0 = r16
            goto L_0x0098
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r2)
            com.google.android.recaptcha.internal.zzco r2 = r1.zzb     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            com.google.android.recaptcha.internal.zzcl r4 = com.google.android.recaptcha.internal.zzco.zzb     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r4)     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            if (r2 == 0) goto L_0x00c7
            r10 = r20
            double r10 = (double) r10     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            com.google.android.recaptcha.internal.zzdv r2 = r1.zza     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            r12 = 4601778099247172813(0x3fdccccccccccccd, double:0.45)
            double r12 = r12 * r10
            r9.zze = r1     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            r9.zzf = r0     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            r4 = r19
            r9.zzg = r4     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            r14 = 4603129179135383962(0x3fe199999999999a, double:0.55)
            double r10 = r10 * r14
            r9.zza = r10     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            r9.zzd = r6     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            long r12 = (long) r12     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            java.lang.Object r2 = r2.zzl(r0, r12, r9)     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            if (r2 == r3) goto L_0x00c6
            r11 = r10
            r10 = r1
        L_0x0098:
            com.google.android.recaptcha.internal.zzsk r2 = (com.google.android.recaptcha.internal.zzsk) r2     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            com.google.android.recaptcha.internal.zzdv r6 = r10.zza     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            com.google.android.recaptcha.internal.zzse r8 = r10.zzc     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            if (r8 != 0) goto L_0x00a1
            r8 = r7
        L_0x00a1:
            com.google.android.recaptcha.internal.zzsr r2 = r6.zzi(r4, r2, r8)     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            com.google.android.recaptcha.internal.zzdv r4 = r10.zza     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            long r11 = (long) r11     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            r9.zze = r10     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            r9.zzf = r0     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            r9.zzg = r7     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            r9.zzd = r5     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            r5 = r2
            r6 = r0
            r7 = r11
            java.lang.Object r2 = r4.zzm(r5, r6, r7, r9)     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            if (r2 == r3) goto L_0x00c6
            r3 = r10
        L_0x00ba:
            com.google.android.recaptcha.internal.zzst r2 = (com.google.android.recaptcha.internal.zzst) r2     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            com.google.android.recaptcha.internal.zzdv r3 = r3.zza     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            r3.zzq(r0, r2)     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            java.lang.String r0 = r2.zzj()     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            return r0
        L_0x00c6:
            return r3
        L_0x00c7:
            com.google.android.recaptcha.internal.zzbf r0 = new com.google.android.recaptcha.internal.zzbf     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            com.google.android.recaptcha.internal.zzbd r2 = com.google.android.recaptcha.internal.zzbd.zzb     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            com.google.android.recaptcha.internal.zzbc r3 = com.google.android.recaptcha.internal.zzbc.zzas     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            r0.<init>(r2, r3, r7)     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
            throw r0     // Catch:{ zzbf -> 0x00e0, Exception -> 0x00d1 }
        L_0x00d1:
            r0 = move-exception
            com.google.android.recaptcha.internal.zzbf r2 = new com.google.android.recaptcha.internal.zzbf
            com.google.android.recaptcha.internal.zzbd r3 = com.google.android.recaptcha.internal.zzbd.zzb
            com.google.android.recaptcha.internal.zzbc r4 = com.google.android.recaptcha.internal.zzbc.zzaC
            java.lang.String r0 = r0.getMessage()
            r2.<init>(r3, r4, r0)
            throw r2
        L_0x00e0:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzeh.zza(java.lang.String, com.google.android.recaptcha.RecaptchaAction, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a1, code lost:
        if (r11.zza.zzn(r2, (long) r12, r0) == r1) goto L_0x00ae;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzb(long r11, kotlin.coroutines.Continuation r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof com.google.android.recaptcha.internal.zzeg
            if (r0 == 0) goto L_0x0013
            r0 = r13
            com.google.android.recaptcha.internal.zzeg r0 = (com.google.android.recaptcha.internal.zzeg) r0
            int r1 = r0.zzd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzd = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzeg r0 = new com.google.android.recaptcha.internal.zzeg
            r0.<init>(r10, r13)
        L_0x0018:
            java.lang.Object r13 = r0.zzb
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzd
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004f
            if (r2 == r4) goto L_0x003d
            if (r2 != r3) goto L_0x0035
            com.google.android.recaptcha.internal.zzeh r11 = r0.zze
            r12 = r11
            com.google.android.recaptcha.internal.zzeh r12 = (com.google.android.recaptcha.internal.zzeh) r12
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ zzbf -> 0x0032 }
            goto L_0x00a3
        L_0x0032:
            r12 = move-exception
            goto L_0x00b2
        L_0x0035:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x003d:
            double r11 = r0.zza
            com.google.android.recaptcha.internal.zzeh r2 = r0.zze
            r4 = r2
            com.google.android.recaptcha.internal.zzeh r4 = (com.google.android.recaptcha.internal.zzeh) r4
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ zzbf -> 0x004c }
            r9 = r2
            r2 = r13
            r12 = r11
            r11 = r9
            goto L_0x0092
        L_0x004c:
            r12 = move-exception
            r11 = r2
            goto L_0x00b2
        L_0x004f:
            kotlin.ResultKt.throwOnFailure(r13)
            com.google.android.recaptcha.internal.zzco r13 = r10.zzb
            com.google.android.recaptcha.internal.zzcl r2 = com.google.android.recaptcha.internal.zzco.zzb
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r2)
            if (r13 != 0) goto L_0x00bb
            com.google.android.recaptcha.internal.zzco r13 = r10.zzb
            com.google.android.recaptcha.internal.zzck r2 = com.google.android.recaptcha.internal.zzco.zzd
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r2)
            if (r13 == 0) goto L_0x006b
            goto L_0x00bb
        L_0x006b:
            com.google.android.recaptcha.internal.zzcm r13 = com.google.android.recaptcha.internal.zzco.zzc
            com.google.android.recaptcha.internal.zzco r13 = (com.google.android.recaptcha.internal.zzco) r13
            r10.zzb = r13
            double r11 = (double) r11
            com.google.android.recaptcha.internal.zzdv r13 = r10.zza     // Catch:{ zzbf -> 0x00af }
            r5 = 4603579539098121011(0x3fe3333333333333, double:0.6)
            double r5 = r5 * r11
            r0.zze = r10     // Catch:{ zzbf -> 0x00af }
            r7 = 4600877379321698714(0x3fd999999999999a, double:0.4)
            double r11 = r11 * r7
            r0.zza = r11     // Catch:{ zzbf -> 0x00af }
            r0.zzd = r4     // Catch:{ zzbf -> 0x00af }
            long r4 = (long) r5     // Catch:{ zzbf -> 0x00af }
            java.lang.Object r13 = r13.zzo(r4, r0)     // Catch:{ zzbf -> 0x00af }
            if (r13 == r1) goto L_0x00ae
            r2 = r13
            r12 = r11
            r11 = r10
        L_0x0092:
            com.google.android.recaptcha.internal.zzse r2 = (com.google.android.recaptcha.internal.zzse) r2     // Catch:{ zzbf -> 0x0032 }
            r11.zzc = r2     // Catch:{ zzbf -> 0x0032 }
            com.google.android.recaptcha.internal.zzdv r4 = r11.zza     // Catch:{ zzbf -> 0x0032 }
            long r12 = (long) r12     // Catch:{ zzbf -> 0x0032 }
            r0.zze = r11     // Catch:{ zzbf -> 0x0032 }
            r0.zzd = r3     // Catch:{ zzbf -> 0x0032 }
            java.lang.Object r12 = r4.zzn(r2, r12, r0)     // Catch:{ zzbf -> 0x0032 }
            if (r12 == r1) goto L_0x00ae
        L_0x00a3:
            com.google.android.recaptcha.internal.zzcl r12 = com.google.android.recaptcha.internal.zzco.zzb     // Catch:{ zzbf -> 0x0032 }
            com.google.android.recaptcha.internal.zzco r12 = (com.google.android.recaptcha.internal.zzco) r12     // Catch:{ zzbf -> 0x0032 }
            r11.zzb = r12     // Catch:{ zzbf -> 0x0032 }
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00ae:
            return r1
        L_0x00af:
            r11 = move-exception
            r12 = r11
            r11 = r10
        L_0x00b2:
            com.google.android.recaptcha.internal.zzck r13 = com.google.android.recaptcha.internal.zzco.zzd
            com.google.android.recaptcha.internal.zzco r13 = (com.google.android.recaptcha.internal.zzco) r13
            r11.zzb = r13
            throw r12
        L_0x00bb:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzeh.zzb(long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
