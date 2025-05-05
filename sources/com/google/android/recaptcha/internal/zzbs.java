package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzbs {
    public static final zzbs zza = new zzbs();

    private zzbs() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0084 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0085 A[PHI: r0 
      PHI: (r0v4 java.lang.Object) = (r0v6 java.lang.Object), (r0v1 java.lang.Object) binds: [B:21:0x0082, B:16:?] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zza(kotlin.jvm.functions.Function1 r19, long r20, long r22, double r24, kotlin.jvm.functions.Function1 r26, kotlin.coroutines.Continuation r27) {
        /*
            r18 = this;
            r0 = r27
            boolean r1 = r0 instanceof com.google.android.recaptcha.internal.zzbr
            if (r1 == 0) goto L_0x0017
            r1 = r0
            com.google.android.recaptcha.internal.zzbr r1 = (com.google.android.recaptcha.internal.zzbr) r1
            int r2 = r1.zzh
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0017
            int r2 = r2 - r3
            r1.zzh = r2
            r2 = r18
            goto L_0x001e
        L_0x0017:
            com.google.android.recaptcha.internal.zzbr r1 = new com.google.android.recaptcha.internal.zzbr
            r2 = r18
            r1.<init>(r2, r0)
        L_0x001e:
            java.lang.Object r0 = r1.zzf
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.zzh
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L_0x0063
            if (r4 == r6) goto L_0x004f
            if (r4 != r5) goto L_0x0047
            long r7 = r1.zzd
            double r9 = r1.zze
            long r11 = r1.zzc
            java.lang.Object r4 = r1.zzb
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            java.lang.Object r13 = r1.zza
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            kotlin.ResultKt.throwOnFailure(r0)
        L_0x003f:
            r15 = r11
            r12 = r1
            r11 = r4
            r1 = r13
            r13 = r3
            r3 = r7
            r7 = r15
            goto L_0x0072
        L_0x0047:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004f:
            long r7 = r1.zzd
            double r9 = r1.zze
            long r11 = r1.zzc
            java.lang.Object r4 = r1.zzb
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            java.lang.Object r13 = r1.zza
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x0061 }
            goto L_0x0085
        L_0x0061:
            r0 = move-exception
            goto L_0x0091
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r0)
            r7 = r22
            r9 = r24
            r11 = r26
            r12 = r1
            r13 = r3
            r1 = r19
            r3 = r20
        L_0x0072:
            r12.zza = r1     // Catch:{ Exception -> 0x0086 }
            r12.zzb = r11     // Catch:{ Exception -> 0x0086 }
            r12.zzc = r7     // Catch:{ Exception -> 0x0086 }
            r12.zze = r9     // Catch:{ Exception -> 0x0086 }
            r12.zzd = r3     // Catch:{ Exception -> 0x0086 }
            r12.zzh = r6     // Catch:{ Exception -> 0x0086 }
            java.lang.Object r0 = r11.invoke(r12)     // Catch:{ Exception -> 0x0086 }
            if (r0 != r13) goto L_0x0085
            return r13
        L_0x0085:
            return r0
        L_0x0086:
            r0 = move-exception
            r15 = r13
            r13 = r1
            r1 = r12
            r16 = r3
            r4 = r11
            r11 = r7
            r3 = r15
            r7 = r16
        L_0x0091:
            java.lang.Object r14 = r13.invoke(r0)
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x00b8
            double r7 = (double) r7
            double r7 = r7 * r9
            long r7 = (long) r7
            long r7 = kotlin.ranges.RangesKt.coerceAtMost((long) r7, (long) r11)
            r1.zza = r13
            r1.zzb = r4
            r1.zzc = r11
            r1.zze = r9
            r1.zzd = r7
            r1.zzh = r5
            java.lang.Object r0 = kotlinx.coroutines.DelayKt.delay(r7, r1)
            if (r0 == r3) goto L_0x00b7
            goto L_0x003f
        L_0x00b7:
            return r3
        L_0x00b8:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzbs.zza(kotlin.jvm.functions.Function1, long, long, double, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
