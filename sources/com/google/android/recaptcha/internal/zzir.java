package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzir extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzjc zzb;
    int zzc;
    zzjc zzd;
    String zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzir(zzjc zzjc, Continuation continuation) {
        super(continuation);
        this.zzb = zzjc;
    }

    public final Object invokeSuspend(Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        Object zzf = this.zzb.zzf((String) null, this);
        return zzf == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? zzf : Result.m2443boximpl(zzf);
    }
}
