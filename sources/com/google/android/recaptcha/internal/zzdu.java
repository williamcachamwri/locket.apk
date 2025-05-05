package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzdu extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzdv zzb;
    final /* synthetic */ zzep zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdu(zzdv zzdv, zzep zzep, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzdv;
        this.zzc = zzep;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzdu(this.zzb, this.zzc, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzdu) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        if (i == 0) {
            this.zza = 1;
            obj = BuildersKt.withContext(this.zzb.zzi.zza().getCoroutineContext(), new zzdj(this.zzb, (Continuation) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        zzse zzse = (zzse) obj;
        this.zzc.zza();
        return zzse;
    }
}
