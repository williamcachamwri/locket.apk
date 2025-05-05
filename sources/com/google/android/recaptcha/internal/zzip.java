package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzip extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzjc zzb;
    final /* synthetic */ String zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzip(zzjc zzjc, String str, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzjc;
        this.zzc = str;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzip(this.zzb, this.zzc, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzip) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        if (r1 != 1) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001a, code lost:
        if (r5 != r0) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002f, code lost:
        if (r4.zzb.zzF(r4.zzc, r4) == r0) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0034, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.zza
            r2 = 1
            kotlin.ResultKt.throwOnFailure(r5)
            if (r1 == 0) goto L_0x000f
            if (r1 == r2) goto L_0x001c
            goto L_0x0032
        L_0x000f:
            com.google.android.recaptcha.internal.zzjc r5 = r4.zzb
            r1 = r4
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r4.zza = r2
            java.lang.Object r5 = r5.zzw(r1)
            if (r5 == r0) goto L_0x0035
        L_0x001c:
            android.webkit.WebView r5 = (android.webkit.WebView) r5
            r5.clearCache(r2)
            com.google.android.recaptcha.internal.zzjc r5 = r4.zzb
            java.lang.String r1 = r4.zzc
            r2 = r4
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = 2
            r4.zza = r3
            java.lang.Object r5 = r5.zzF(r1, r2)
            if (r5 != r0) goto L_0x0032
            goto L_0x0035
        L_0x0032:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x0035:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzip.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
