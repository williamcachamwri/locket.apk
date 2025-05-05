package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zza extends ContinuationImpl {
    Object zza;
    long zzb;
    /* synthetic */ Object zzc;
    final /* synthetic */ zze zzd;
    int zze;
    String zzf;
    zzep zzg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zza(zze zze2, Continuation continuation) {
        super(continuation);
        this.zzd = zze2;
    }

    public final Object invokeSuspend(Object obj) {
        this.zzc = obj;
        this.zze |= Integer.MIN_VALUE;
        Object zzc2 = this.zzd.zzc((String) null, 0, this);
        return zzc2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? zzc2 : Result.m2443boximpl(zzc2);
    }
}
