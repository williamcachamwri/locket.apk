package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzdh extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzdv zzb;
    final /* synthetic */ zzsr zzc;
    final /* synthetic */ zzep zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdh(zzdv zzdv, zzsr zzsr, zzep zzep, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzdv;
        this.zzc = zzsr;
        this.zzd = zzep;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzdh(this.zzb, this.zzc, this.zzd, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzdh) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        if (i == 0) {
            zzdv zzdv = this.zzb;
            zzsr zzsr = this.zzc;
            this.zza = 1;
            obj = zzdv.zzh(zzdv).zzb(zzdv.zzd(zzdv), zzsr, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        zzst zzst = (zzst) obj;
        this.zzd.zza();
        return zzst;
    }
}
