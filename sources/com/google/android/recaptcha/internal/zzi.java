package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzi extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzl zzb;
    int zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzi(zzl zzl, Continuation continuation) {
        super(continuation);
        this.zzb = zzl;
    }

    public final Object invokeSuspend(Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        Object zzc2 = this.zzb.zzc(0, (zzse) null, (zzem) null, this);
        return zzc2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? zzc2 : Result.m2443boximpl(zzc2);
    }
}
