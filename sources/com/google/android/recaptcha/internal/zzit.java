package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzit extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzjc zzb;
    int zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzit(zzjc zzjc, Continuation continuation) {
        super(continuation);
        this.zzb = zzjc;
    }

    public final Object invokeSuspend(Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        Object zzh = this.zzb.zzh((zzse) null, this);
        return zzh == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? zzh : Result.m2443boximpl(zzh);
    }
}
