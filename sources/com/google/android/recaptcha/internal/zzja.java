package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzja extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    Object zzd;
    int zze;
    final /* synthetic */ zzjc zzf;
    final /* synthetic */ zzep zzg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzja(zzjc zzjc, zzep zzep, Continuation continuation) {
        super(2, continuation);
        this.zzf = zzjc;
        this.zzg = zzep;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzja(this.zzf, this.zzg, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzja) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a8, code lost:
        if (r10.zzE(r1, r9) != r0) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00c6, code lost:
        if (r9.zzf.zzA().await(r9) != r0) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00c8, code lost:
        r9.zze = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00da, code lost:
        if (r9.zzf.zzm().zzc(com.google.android.recaptcha.internal.zzjg.zzc, r9) != r0) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00dd, code lost:
        r9.zzg.zza();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00e4, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        if (r1 != 3) goto L_0x00dd;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.zze
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x002b
            if (r1 == r4) goto L_0x0017
            kotlin.ResultKt.throwOnFailure(r10)
            if (r1 == r3) goto L_0x00aa
            if (r1 == r2) goto L_0x00c8
            goto L_0x00dd
        L_0x0017:
            java.lang.Object r1 = r9.zzd
            com.google.android.recaptcha.internal.zzci r1 = (com.google.android.recaptcha.internal.zzci) r1
            java.lang.Object r4 = r9.zzc
            com.google.android.recaptcha.internal.zzse r4 = (com.google.android.recaptcha.internal.zzse) r4
            java.lang.Object r6 = r9.zzb
            com.google.android.recaptcha.internal.zzjc r6 = (com.google.android.recaptcha.internal.zzjc) r6
            java.lang.Object r7 = r9.zza
            com.google.android.recaptcha.internal.zzjc r7 = (com.google.android.recaptcha.internal.zzjc) r7
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0065
        L_0x002b:
            kotlin.ResultKt.throwOnFailure(r10)
            com.google.android.recaptcha.internal.zzjc r6 = r9.zzf
            com.google.android.recaptcha.internal.zzse r10 = r6.zzf
            if (r10 != 0) goto L_0x0037
            r10 = r5
        L_0x0037:
            com.google.android.recaptcha.internal.zzjc r1 = r9.zzf
            com.google.android.recaptcha.internal.zzci r7 = new com.google.android.recaptcha.internal.zzci
            com.google.android.recaptcha.internal.zzse r1 = r1.zzf
            if (r1 != 0) goto L_0x0042
            r1 = r5
        L_0x0042:
            com.google.android.recaptcha.internal.zzlg r1 = r1.zzf()
            r7.<init>(r1)
            r1 = r7
            com.google.android.recaptcha.internal.zzci r1 = (com.google.android.recaptcha.internal.zzci) r1
            com.google.android.recaptcha.internal.zzjc r1 = r9.zzf
            r8 = r9
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r9.zza = r6
            r9.zzb = r6
            r9.zzc = r10
            r9.zzd = r7
            r9.zze = r4
            java.lang.Object r1 = r1.zzw(r8)
            if (r1 == r0) goto L_0x00e5
            r4 = r10
            r10 = r1
            r1 = r7
            r7 = r6
        L_0x0065:
            android.webkit.WebView r10 = (android.webkit.WebView) r10
            com.google.android.recaptcha.internal.zzfv r10 = r6.zzC(r4, r1, r10)
            com.google.android.recaptcha.internal.zzfq r10 = (com.google.android.recaptcha.internal.zzfq) r10
            r7.zzb = r10
            com.google.android.recaptcha.internal.zzjc r10 = r9.zzf
            kotlinx.coroutines.CompletableDeferred r10 = r10.zzA()
            int r10 = r10.hashCode()
            kotlin.coroutines.jvm.internal.Boxing.boxInt(r10)
            com.google.android.recaptcha.internal.zzjc r10 = r9.zzf
            com.google.android.recaptcha.internal.zzjj r10 = r10.zzh
            r10.zzd()
            com.google.android.recaptcha.internal.zzjc r10 = r9.zzf
            com.google.android.recaptcha.internal.zzjj r10 = r10.zzh
            r10.zze()
            com.google.android.recaptcha.internal.zzjc r10 = r9.zzf
            com.google.android.recaptcha.internal.zzse r1 = r10.zzf
            if (r1 != 0) goto L_0x0097
            r1 = r5
        L_0x0097:
            r4 = r9
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r9.zza = r5
            r9.zzb = r5
            r9.zzc = r5
            r9.zzd = r5
            r9.zze = r3
            java.lang.Object r10 = r10.zzE(r1, r4)
            if (r10 == r0) goto L_0x00e5
        L_0x00aa:
            com.google.android.recaptcha.internal.zzjc r10 = r9.zzf
            kotlinx.coroutines.CompletableDeferred r10 = r10.zzA()
            int r10 = r10.hashCode()
            kotlin.coroutines.jvm.internal.Boxing.boxInt(r10)
            com.google.android.recaptcha.internal.zzjc r10 = r9.zzf
            kotlinx.coroutines.CompletableDeferred r10 = r10.zzA()
            r1 = r9
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r9.zze = r2
            java.lang.Object r10 = r10.await(r1)
            if (r10 == r0) goto L_0x00e5
        L_0x00c8:
            com.google.android.recaptcha.internal.zzjc r10 = r9.zzf
            com.google.android.recaptcha.internal.zzcd r10 = r10.zzm()
            com.google.android.recaptcha.internal.zzjg r1 = com.google.android.recaptcha.internal.zzjg.zzc
            r2 = r9
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = 4
            r9.zze = r3
            java.lang.Object r10 = r10.zzc(r1, r2)
            if (r10 != r0) goto L_0x00dd
            goto L_0x00e5
        L_0x00dd:
            com.google.android.recaptcha.internal.zzep r10 = r9.zzg
            r10.zza()
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x00e5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzja.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
