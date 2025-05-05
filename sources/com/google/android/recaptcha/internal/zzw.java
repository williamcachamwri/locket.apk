package com.google.android.recaptcha.internal;

import java.util.Iterator;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzw extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzse zzd;
    final /* synthetic */ zzx zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzw(zzse zzse, zzx zzx, Continuation continuation) {
        super(2, continuation);
        this.zzd = zzse;
        this.zze = zzx;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzw(this.zzd, this.zze, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzw) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        zzsg zzsg;
        Iterator it;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zzc != 0) {
            it = (Iterator) this.zzb;
            zzsg = (zzsg) this.zza;
            ResultKt.throwOnFailure(obj);
        } else {
            ResultKt.throwOnFailure(obj);
            if (!this.zzd.zzU()) {
                Result.Companion companion = Result.Companion;
                return Result.m2443boximpl(Result.m2444constructorimpl(ResultKt.createFailure(new zzbf(zzbd.zzb, zzbc.zzab, (String) null))));
            }
            zzsg = this.zzd.zzk();
            if (zzsg.zzi().zzn()) {
                Result.Companion companion2 = Result.Companion;
                return Result.m2443boximpl(Result.m2444constructorimpl(ResultKt.createFailure(new zzbf(zzbd.zzb, zzbc.zzab, (String) null))));
            }
            this.zze.zzc = zzsg.zzi();
            it = this.zze.zzb.iterator();
        }
        while (it.hasNext()) {
            this.zza = zzsg;
            this.zzb = it;
            this.zzc = 1;
            if (((zzaa) it.next()).zzd(zzsg, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        Result.Companion companion3 = Result.Companion;
        return Result.m2443boximpl(Result.m2444constructorimpl(Unit.INSTANCE));
    }
}
