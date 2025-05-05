package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzda extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzde zzb;
    int zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzda(zzde zzde, Continuation continuation) {
        super(continuation);
        this.zzb = zzde;
    }

    public final Object invokeSuspend(Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        Object zzb2 = this.zzb.zzf((RecaptchaAction) null, 0, this);
        return zzb2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? zzb2 : Result.m2443boximpl(zzb2);
    }
}
