package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public abstract class zze {
    private boolean zza;

    /* access modifiers changed from: protected */
    public zzep zza(String str) {
        throw null;
    }

    /* access modifiers changed from: protected */
    public zzep zzb() {
        throw null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009e A[Catch:{ Exception -> 0x00a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e7 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ed A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzc(java.lang.String r19, long r20, kotlin.coroutines.Continuation r22) throws com.google.android.recaptcha.internal.zzbf {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            r3 = r20
            r0 = r22
            boolean r5 = r0 instanceof com.google.android.recaptcha.internal.zza
            if (r5 == 0) goto L_0x001b
            r5 = r0
            com.google.android.recaptcha.internal.zza r5 = (com.google.android.recaptcha.internal.zza) r5
            int r6 = r5.zze
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r8 = r6 & r7
            if (r8 == 0) goto L_0x001b
            int r6 = r6 - r7
            r5.zze = r6
            goto L_0x0020
        L_0x001b:
            com.google.android.recaptcha.internal.zza r5 = new com.google.android.recaptcha.internal.zza
            r5.<init>(r1, r0)
        L_0x0020:
            java.lang.Object r0 = r5.zzc
            java.lang.Object r12 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r6 = r5.zze
            r13 = 3
            r7 = 2
            r8 = 1
            r14 = 0
            if (r6 == 0) goto L_0x0072
            if (r6 == r8) goto L_0x004f
            if (r6 == r7) goto L_0x0041
            if (r6 != r13) goto L_0x0039
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x00e8
        L_0x0039:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0041:
            java.lang.String r2 = r5.zzf
            r3 = r2
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r5.zza
            com.google.android.recaptcha.internal.zze r3 = (com.google.android.recaptcha.internal.zze) r3
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x00d9
        L_0x004f:
            long r2 = r5.zzb
            com.google.android.recaptcha.internal.zzep r4 = r5.zzg
            r6 = r4
            com.google.android.recaptcha.internal.zzep r6 = (com.google.android.recaptcha.internal.zzep) r6
            java.lang.String r6 = r5.zzf
            r8 = r6
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r8 = r5.zza
            com.google.android.recaptcha.internal.zze r8 = (com.google.android.recaptcha.internal.zze) r8
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x0069 }
            r16 = r6
            r6 = r4
            r3 = r2
            r2 = r16
            goto L_0x0091
        L_0x0069:
            r0 = move-exception
            r10 = r0
            r16 = r2
            r2 = r6
        L_0x006e:
            r3 = r8
            r8 = r16
            goto L_0x00b2
        L_0x0072:
            kotlin.ResultKt.throwOnFailure(r0)
            com.google.android.recaptcha.internal.zzep r6 = r18.zza(r19)
            com.google.android.recaptcha.internal.zzb r0 = new com.google.android.recaptcha.internal.zzb     // Catch:{ Exception -> 0x00ad }
            r0.<init>(r1, r2, r14)     // Catch:{ Exception -> 0x00ad }
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0     // Catch:{ Exception -> 0x00ad }
            r5.zza = r1     // Catch:{ Exception -> 0x00ad }
            r5.zzf = r2     // Catch:{ Exception -> 0x00ad }
            r5.zzg = r6     // Catch:{ Exception -> 0x00ad }
            r5.zzb = r3     // Catch:{ Exception -> 0x00ad }
            r5.zze = r8     // Catch:{ Exception -> 0x00ad }
            java.lang.Object r0 = kotlinx.coroutines.TimeoutKt.withTimeout(r3, r0, r5)     // Catch:{ Exception -> 0x00ad }
            if (r0 == r12) goto L_0x00ac
            r8 = r1
        L_0x0091:
            kotlin.Result r0 = (kotlin.Result) r0     // Catch:{ Exception -> 0x00a6 }
            java.lang.Object r0 = r0.m2453unboximpl()     // Catch:{ Exception -> 0x00a6 }
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x00a6 }
            com.google.android.recaptcha.internal.zzsk r0 = (com.google.android.recaptcha.internal.zzsk) r0     // Catch:{ Exception -> 0x00a6 }
            if (r6 == 0) goto L_0x00a1
            r6.zza()     // Catch:{ Exception -> 0x00a6 }
        L_0x00a1:
            java.lang.Object r0 = kotlin.Result.m2444constructorimpl(r0)     // Catch:{ Exception -> 0x00a6 }
            goto L_0x00ec
        L_0x00a6:
            r0 = move-exception
            r10 = r0
            r16 = r3
            r4 = r6
            goto L_0x006e
        L_0x00ac:
            return r12
        L_0x00ad:
            r0 = move-exception
            r10 = r0
            r8 = r3
            r4 = r6
            r3 = r1
        L_0x00b2:
            com.google.android.recaptcha.internal.zzbf r0 = new com.google.android.recaptcha.internal.zzbf
            com.google.android.recaptcha.internal.zzbd r6 = com.google.android.recaptcha.internal.zzbd.zzb
            com.google.android.recaptcha.internal.zzbc r11 = com.google.android.recaptcha.internal.zzbc.zzaa
            java.lang.String r15 = r10.getMessage()
            r0.<init>(r6, r11, r15)
            com.google.android.recaptcha.internal.zzbf r0 = com.google.android.recaptcha.internal.zzf.zza(r10, r0)
            if (r4 == 0) goto L_0x00c8
            r4.zzb(r0)
        L_0x00c8:
            r5.zza = r3
            r5.zzf = r2
            r5.zzg = r14
            r5.zze = r7
            r6 = r3
            r7 = r2
            r11 = r5
            java.lang.Object r0 = r6.zzi(r7, r8, r10, r11)
            if (r0 == r12) goto L_0x00ed
        L_0x00d9:
            kotlin.Result$Companion r0 = kotlin.Result.Companion
            r5.zza = r14
            r5.zzf = r14
            r5.zze = r13
            java.lang.Object r0 = r3.zzd(r2, r5)
            if (r0 != r12) goto L_0x00e8
            return r12
        L_0x00e8:
            java.lang.Object r0 = kotlin.Result.m2444constructorimpl(r0)
        L_0x00ec:
            return r0
        L_0x00ed:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zze.zzc(java.lang.String, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public abstract Object zzd(String str, Continuation continuation);

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b0, code lost:
        if (r12 == r1) goto L_0x00d4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0097 A[Catch:{ Exception -> 0x0058 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zze(long r9, com.google.android.recaptcha.internal.zzse r11, kotlin.coroutines.Continuation r12) throws com.google.android.recaptcha.internal.zzbf {
        /*
            r8 = this;
            boolean r0 = r12 instanceof com.google.android.recaptcha.internal.zzc
            if (r0 == 0) goto L_0x0013
            r0 = r12
            com.google.android.recaptcha.internal.zzc r0 = (com.google.android.recaptcha.internal.zzc) r0
            int r1 = r0.zzd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzd = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzc r0 = new com.google.android.recaptcha.internal.zzc
            r0.<init>(r8, r12)
        L_0x0018:
            java.lang.Object r12 = r0.zzb
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzd
            r3 = 0
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x005a
            if (r2 == r6) goto L_0x004b
            if (r2 == r5) goto L_0x003d
            if (r2 != r4) goto L_0x0035
            java.lang.Object r9 = r0.zza
            com.google.android.recaptcha.internal.zzbf r9 = (com.google.android.recaptcha.internal.zzbf) r9
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00c7
        L_0x0035:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x003d:
            com.google.android.recaptcha.internal.zzep r9 = r0.zze
            r10 = r9
            com.google.android.recaptcha.internal.zzep r10 = (com.google.android.recaptcha.internal.zzep) r10
            java.lang.Object r10 = r0.zza
            com.google.android.recaptcha.internal.zze r10 = (com.google.android.recaptcha.internal.zze) r10
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00b2
        L_0x004b:
            com.google.android.recaptcha.internal.zzep r9 = r0.zze
            r10 = r9
            com.google.android.recaptcha.internal.zzep r10 = (com.google.android.recaptcha.internal.zzep) r10
            java.lang.Object r10 = r0.zza
            com.google.android.recaptcha.internal.zze r10 = (com.google.android.recaptcha.internal.zze) r10
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Exception -> 0x0058 }
            goto L_0x0088
        L_0x0058:
            r11 = move-exception
            goto L_0x00a3
        L_0x005a:
            kotlin.ResultKt.throwOnFailure(r12)
            com.google.android.recaptcha.internal.zzep r12 = r8.zzb()
            boolean r2 = r8.zza
            if (r2 == 0) goto L_0x0071
            r12.zza()
            kotlin.Result$Companion r9 = kotlin.Result.Companion
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            java.lang.Object r9 = kotlin.Result.m2444constructorimpl(r9)
            return r9
        L_0x0071:
            com.google.android.recaptcha.internal.zzd r2 = new com.google.android.recaptcha.internal.zzd     // Catch:{ Exception -> 0x009f }
            r2.<init>(r8, r11, r3)     // Catch:{ Exception -> 0x009f }
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch:{ Exception -> 0x009f }
            r0.zza = r8     // Catch:{ Exception -> 0x009f }
            r0.zze = r12     // Catch:{ Exception -> 0x009f }
            r0.zzd = r6     // Catch:{ Exception -> 0x009f }
            java.lang.Object r9 = kotlinx.coroutines.TimeoutKt.withTimeout(r9, r2, r0)     // Catch:{ Exception -> 0x009f }
            if (r9 == r1) goto L_0x00d4
            r10 = r8
            r7 = r12
            r12 = r9
            r9 = r7
        L_0x0088:
            kotlin.Result r12 = (kotlin.Result) r12     // Catch:{ Exception -> 0x0058 }
            java.lang.Object r11 = r12.m2453unboximpl()     // Catch:{ Exception -> 0x0058 }
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ Exception -> 0x0058 }
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0058 }
            r10.zza = r6     // Catch:{ Exception -> 0x0058 }
            if (r9 == 0) goto L_0x009a
            r9.zza()     // Catch:{ Exception -> 0x0058 }
        L_0x009a:
            java.lang.Object r9 = kotlin.Result.m2444constructorimpl(r11)     // Catch:{ Exception -> 0x0058 }
            return r9
        L_0x009f:
            r9 = move-exception
            r11 = r9
            r10 = r8
            r9 = r12
        L_0x00a3:
            r12 = 0
            r10.zza = r12
            r0.zza = r10
            r0.zze = r9
            r0.zzd = r5
            java.lang.Object r12 = r10.zzj(r11, r0)
            if (r12 == r1) goto L_0x00d4
        L_0x00b2:
            r11 = r12
            com.google.android.recaptcha.internal.zzbf r11 = (com.google.android.recaptcha.internal.zzbf) r11
            if (r9 == 0) goto L_0x00ba
            r9.zzb(r11)
        L_0x00ba:
            r0.zza = r11
            r0.zze = r3
            r0.zzd = r4
            java.lang.Object r9 = r10.zzg(r11, r0)
            if (r9 == r1) goto L_0x00d4
            r9 = r11
        L_0x00c7:
            kotlin.Result$Companion r10 = kotlin.Result.Companion
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r9)
            java.lang.Object r9 = kotlin.Result.m2444constructorimpl(r9)
            return r9
        L_0x00d4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zze.zze(long, com.google.android.recaptcha.internal.zzse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public abstract Object zzf(String str, Continuation continuation) throws zzbf;

    /* access modifiers changed from: protected */
    public Object zzg(zzbf zzbf, Continuation continuation) {
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: protected */
    public abstract Object zzh(zzse zzse, Continuation continuation) throws zzbf;

    /* access modifiers changed from: protected */
    public Object zzi(String str, long j, Exception exc, Continuation continuation) {
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: protected */
    public Object zzj(Exception exc, Continuation continuation) {
        return zzf.zza(exc, new zzbf(zzbd.zzb, zzbc.zzap, exc.getMessage()));
    }

    /* access modifiers changed from: protected */
    public void zzk(zzst zzst) {
    }

    public final boolean zzl() {
        return this.zza;
    }
}
