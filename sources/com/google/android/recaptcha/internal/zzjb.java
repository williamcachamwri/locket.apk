package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzjb extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzjc zzc;
    final /* synthetic */ zzep zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjb(zzjc zzjc, zzep zzep, Continuation continuation) {
        super(2, continuation);
        this.zzc = zzjc;
        this.zzd = zzep;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzjb(this.zzc, this.zzd, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzjb) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        if (r5.zzc.zzx(r5) != r0) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
        if (kotlinx.coroutines.TimeoutKt.withTimeout(20000, new com.google.android.recaptcha.internal.zzja(r5.zzc, r5.zzd, (kotlin.coroutines.Continuation) null), r5) == r0) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0074, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.zzb
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0020
            if (r1 == r3) goto L_0x001a
            if (r1 == r2) goto L_0x0016
            java.lang.Object r0 = r5.zza
            com.google.android.recaptcha.internal.zzbf r0 = (com.google.android.recaptcha.internal.zzbf) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0076
        L_0x0016:
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ Exception -> 0x001e }
            goto L_0x007b
        L_0x001a:
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ Exception -> 0x001e }
            goto L_0x0030
        L_0x001e:
            r6 = move-exception
            goto L_0x004a
        L_0x0020:
            kotlin.ResultKt.throwOnFailure(r6)
            com.google.android.recaptcha.internal.zzjc r6 = r5.zzc     // Catch:{ Exception -> 0x001e }
            r1 = r5
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch:{ Exception -> 0x001e }
            r5.zzb = r3     // Catch:{ Exception -> 0x001e }
            java.lang.Object r6 = r6.zzx(r1)     // Catch:{ Exception -> 0x001e }
            if (r6 == r0) goto L_0x0074
        L_0x0030:
            com.google.android.recaptcha.internal.zzja r6 = new com.google.android.recaptcha.internal.zzja     // Catch:{ Exception -> 0x001e }
            com.google.android.recaptcha.internal.zzjc r1 = r5.zzc     // Catch:{ Exception -> 0x001e }
            com.google.android.recaptcha.internal.zzep r3 = r5.zzd     // Catch:{ Exception -> 0x001e }
            r4 = 0
            r6.<init>(r1, r3, r4)     // Catch:{ Exception -> 0x001e }
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6     // Catch:{ Exception -> 0x001e }
            r1 = r5
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch:{ Exception -> 0x001e }
            r5.zzb = r2     // Catch:{ Exception -> 0x001e }
            r2 = 20000(0x4e20, double:9.8813E-320)
            java.lang.Object r6 = kotlinx.coroutines.TimeoutKt.withTimeout(r2, r6, r1)     // Catch:{ Exception -> 0x001e }
            if (r6 != r0) goto L_0x007b
            goto L_0x0074
        L_0x004a:
            r6.getMessage()
            com.google.android.recaptcha.internal.zzbf r1 = new com.google.android.recaptcha.internal.zzbf
            com.google.android.recaptcha.internal.zzbd r2 = com.google.android.recaptcha.internal.zzbd.zzb
            com.google.android.recaptcha.internal.zzbc r3 = com.google.android.recaptcha.internal.zzbc.zzV
            java.lang.String r4 = r6.getMessage()
            r1.<init>(r2, r3, r4)
            com.google.android.recaptcha.internal.zzbf r6 = com.google.android.recaptcha.internal.zzf.zza(r6, r1)
            com.google.android.recaptcha.internal.zzjc r1 = r5.zzc
            com.google.android.recaptcha.internal.zzcd r1 = r1.zzm()
            com.google.android.recaptcha.internal.zzjg r2 = com.google.android.recaptcha.internal.zzjg.zza
            r3 = r5
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r5.zza = r6
            r4 = 3
            r5.zzb = r4
            java.lang.Object r1 = r1.zzc(r2, r3)
            if (r1 != r0) goto L_0x0075
        L_0x0074:
            return r0
        L_0x0075:
            r0 = r6
        L_0x0076:
            com.google.android.recaptcha.internal.zzep r6 = r5.zzd
            r6.zzb(r0)
        L_0x007b:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzjb.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
