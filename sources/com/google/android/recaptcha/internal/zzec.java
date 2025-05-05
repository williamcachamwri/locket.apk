package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CompletableDeferred;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzec extends SuspendLambda implements Function1 {
    Object zza;
    int zzb;
    final /* synthetic */ zzee zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ CompletableDeferred zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzec(zzee zzee, long j, CompletableDeferred completableDeferred, Continuation continuation) {
        super(1, continuation);
        this.zzc = zzee;
        this.zzd = j;
        this.zze = completableDeferred;
    }

    public final Continuation create(Continuation continuation) {
        return new zzec(this.zzc, this.zzd, this.zze, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return ((zzec) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        zzbf e;
        zzep zzep;
        zzep zzep2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zzb;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            zzep zzf = this.zzc.zzb.zzf(41);
            try {
                this.zza = zzf;
                this.zzb = 1;
                Object zzo = this.zzc.zza.zzo(this.zzd, this);
                if (zzo != coroutine_suspended) {
                    Object obj2 = zzo;
                    zzep2 = zzf;
                    obj = obj2;
                }
                return coroutine_suspended;
            } catch (zzbf e2) {
                zzbf zzbf = e2;
                zzep = zzf;
                e = zzbf;
                this.zzc.zzd = e;
                zzep.zzb(e);
                throw e;
            }
        } else if (i != 1) {
            zzep = (zzep) this.zza;
            try {
                ResultKt.throwOnFailure(obj);
                zzep.zza();
                this.zzc.zzf = zzco.zzb;
                return Boxing.boxBoolean(this.zze.complete(Unit.INSTANCE));
            } catch (zzbf e3) {
                e = e3;
            }
        } else {
            zzep2 = (zzep) this.zza;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (zzbf e4) {
                e = e4;
                zzep = zzep2;
            }
        }
        zzse zzse = (zzse) obj;
        this.zzc.zze = zzse;
        this.zza = zzep2;
        this.zzb = 2;
        if (this.zzc.zza.zzn(zzse, this.zzd, this) != coroutine_suspended) {
            zzep = zzep2;
            zzep.zza();
            this.zzc.zzf = zzco.zzb;
            return Boxing.boxBoolean(this.zze.complete(Unit.INSTANCE));
        }
        return coroutine_suspended;
    }
}
