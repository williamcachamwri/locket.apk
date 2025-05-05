package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzin extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzjc zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzin(zzjc zzjc, Continuation continuation) {
        super(2, continuation);
        this.zzc = zzjc;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzin(this.zzc, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzin) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0045, code lost:
        if (r7 != r0) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005e, code lost:
        if (r7 != r0) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0060, code lost:
        ((android.webkit.WebView) r7).addJavascriptInterface(r6.zzc.zzq(), "RN");
        r6.zzb = 4;
        r7 = r6.zzc.zzw(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0077, code lost:
        if (r7 != r0) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x007a, code lost:
        ((android.webkit.WebView) r7).setWebViewClient(new com.google.android.recaptcha.internal.zzim(r6.zzc));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x008a, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x008b, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0014, code lost:
        if (r1 != 3) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0031, code lost:
        if (r7 != r0) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.zzb
            java.lang.String r2 = "RN"
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x0023
            if (r1 == r5) goto L_0x001f
            if (r1 == r4) goto L_0x0017
            kotlin.ResultKt.throwOnFailure(r7)
            if (r1 == r3) goto L_0x0060
            goto L_0x007a
        L_0x0017:
            java.lang.Object r1 = r6.zza
            com.google.android.recaptcha.internal.zzjc r1 = (com.google.android.recaptcha.internal.zzjc) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0047
        L_0x001f:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0033
        L_0x0023:
            kotlin.ResultKt.throwOnFailure(r7)
            com.google.android.recaptcha.internal.zzjc r7 = r6.zzc
            r1 = r6
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r6.zzb = r5
            java.lang.Object r7 = r7.zzw(r1)
            if (r7 == r0) goto L_0x008b
        L_0x0033:
            android.webkit.WebView r7 = (android.webkit.WebView) r7
            r7.removeJavascriptInterface(r2)
            com.google.android.recaptcha.internal.zzjc r7 = r6.zzc
            r1 = r6
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r6.zza = r7
            r6.zzb = r4
            java.lang.Object r7 = r7.zzw(r1)
            if (r7 == r0) goto L_0x008b
        L_0x0047:
            android.webkit.WebView r7 = (android.webkit.WebView) r7
            android.webkit.WebSettings r7 = r7.getSettings()
            r7.setJavaScriptEnabled(r5)
            com.google.android.recaptcha.internal.zzjc r7 = r6.zzc
            r1 = r6
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r4 = 0
            r6.zza = r4
            r6.zzb = r3
            java.lang.Object r7 = r7.zzw(r1)
            if (r7 == r0) goto L_0x008b
        L_0x0060:
            com.google.android.recaptcha.internal.zzjc r1 = r6.zzc
            android.webkit.WebView r7 = (android.webkit.WebView) r7
            com.google.android.recaptcha.internal.zzil r1 = r1.zzq()
            r7.addJavascriptInterface(r1, r2)
            com.google.android.recaptcha.internal.zzjc r7 = r6.zzc
            r1 = r6
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r2 = 4
            r6.zzb = r2
            java.lang.Object r7 = r7.zzw(r1)
            if (r7 != r0) goto L_0x007a
            goto L_0x008b
        L_0x007a:
            com.google.android.recaptcha.internal.zzjc r0 = r6.zzc
            android.webkit.WebView r7 = (android.webkit.WebView) r7
            com.google.android.recaptcha.internal.zzim r1 = new com.google.android.recaptcha.internal.zzim
            r1.<init>(r0)
            android.webkit.WebViewClient r1 = (android.webkit.WebViewClient) r1
            r7.setWebViewClient(r1)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x008b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzin.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
