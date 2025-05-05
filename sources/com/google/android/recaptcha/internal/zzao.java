package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzao extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzap zzc;
    final /* synthetic */ zzep zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzao(zzap zzap, zzep zzep, Continuation continuation) {
        super(2, continuation);
        this.zzc = zzap;
        this.zzd = zzep;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzao(this.zzc, this.zzd, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzao) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Exception e;
        Ref.ObjectRef objectRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zzb != 0) {
            objectRef = (Ref.ObjectRef) this.zza;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e2) {
                e = e2;
            }
        } else {
            ResultKt.throwOnFailure(obj);
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            try {
                this.zza = objectRef2;
                this.zzb = 1;
                if (TimeoutKt.withTimeout(60000, new zzan(this.zzc, this.zzd, objectRef2, (Continuation) null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } catch (Exception e3) {
                Exception exc = e3;
                objectRef = objectRef2;
                e = exc;
                CompletableDeferred zzf = this.zzc.zzf();
                Throwable th = (Throwable) objectRef.element;
                if (th == null) {
                    th = e;
                }
                zzf.completeExceptionally(th);
                this.zzc.zze = zzaq.zza;
                this.zzd.zzb(new zzbf(zzbd.zzb, zzbc.zza, e.getMessage()));
                return Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }
}
