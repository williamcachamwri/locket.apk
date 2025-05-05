package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzed extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzee zzb;
    final /* synthetic */ CompletableDeferred zzc;
    final /* synthetic */ long zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzed(zzee zzee, CompletableDeferred completableDeferred, long j, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzee;
        this.zzc = completableDeferred;
        this.zzd = j;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzed(this.zzb, this.zzc, this.zzd, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzed) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza != 0) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (zzbf e) {
                this.zzb.zzf = zzco.zzd;
                this.zzc.completeExceptionally(e);
            }
        } else {
            ResultKt.throwOnFailure(obj);
            this.zza = 1;
            obj = zzbs.zza.zza(new zzeb(this.zzb), 100, 1000, 2.0d, new zzec(this.zzb, this.zzd, this.zzc, (Continuation) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        ((Boolean) obj).booleanValue();
        return Unit.INSTANCE;
    }
}
