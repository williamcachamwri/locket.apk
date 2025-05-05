package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzdb extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzde zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ RecaptchaAction zzd;
    final /* synthetic */ String zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdb(zzde zzde, long j, RecaptchaAction recaptchaAction, String str, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzde;
        this.zzc = j;
        this.zzd = recaptchaAction;
        this.zze = str;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzdb(this.zzb, this.zzc, this.zzd, this.zze, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzdb) create((zzem) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        if (i == 0) {
            zzde.zze(this.zzb, this.zzc, this.zzd);
            zzde zzde = this.zzb;
            String str = this.zze;
            RecaptchaAction recaptchaAction = this.zzd;
            long j = this.zzc;
            this.zza = 1;
            obj = zzde.zzb.zza(str, recaptchaAction, j, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Result.m2443boximpl(Result.m2444constructorimpl((String) obj));
    }
}
