package com.google.android.recaptcha.internal;

import android.webkit.ValueCallback;
import android.webkit.WebView;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzis extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzjc zzb;
    final /* synthetic */ String zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzis(zzjc zzjc, String str, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzjc;
        this.zzc = str;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzis(this.zzb, this.zzc, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzis) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        if (i == 0) {
            this.zza = 1;
            obj = this.zzb.zzw(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        String str = this.zzc;
        ((WebView) obj).evaluateJavascript("recaptcha.m.Main.execute(\"" + str + "\")", (ValueCallback) null);
        return Unit.INSTANCE;
    }
}
