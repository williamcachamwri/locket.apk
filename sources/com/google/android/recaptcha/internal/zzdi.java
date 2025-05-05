package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzdi extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzdv zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ long zze;
    final /* synthetic */ zzsr zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdi(zzdv zzdv, String str, long j, zzsr zzsr, Continuation continuation) {
        super(2, continuation);
        this.zzc = zzdv;
        this.zzd = str;
        this.zze = j;
        this.zzf = zzsr;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzdi(this.zzc, this.zzd, this.zze, this.zzf, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzdi) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        TimeoutCancellationException e;
        zzep zzep;
        zzbf e2;
        Exception e3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zzb != 0) {
            zzep = (zzep) this.zza;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (TimeoutCancellationException e4) {
                e = e4;
            } catch (zzbf e5) {
                e2 = e5;
                zzbf zzb2 = this.zzc.zzs(e2, e2);
                zzep.zzb(zzb2);
                throw zzb2;
            } catch (Exception e6) {
                e3 = e6;
                zzbf zzb3 = this.zzc.zzs(e3, new zzbf(zzbd.zzc, zzbc.zzZ, e3.getMessage()));
                zzep.zzb(zzb3);
                throw zzb3;
            }
        } else {
            ResultKt.throwOnFailure(obj);
            zzep zzf2 = this.zzc.zzu(this.zzd).zzf(28);
            try {
                this.zza = zzf2;
                this.zzb = 1;
                Object withTimeout = TimeoutKt.withTimeout(this.zze, new zzdh(this.zzc, this.zzf, zzf2, (Continuation) null), this);
                if (withTimeout == coroutine_suspended) {
                    return coroutine_suspended;
                }
                zzep = zzf2;
                obj = withTimeout;
            } catch (TimeoutCancellationException e7) {
                TimeoutCancellationException timeoutCancellationException = e7;
                zzep = zzf2;
                e = timeoutCancellationException;
                zzbf zzb4 = this.zzc.zzs(e, new zzbf(zzbd.zzc, zzbc.zzb, e.getMessage()));
                zzep.zzb(zzb4);
                throw zzb4;
            } catch (zzbf e8) {
                zzbf zzbf = e8;
                zzep = zzf2;
                e2 = zzbf;
                zzbf zzb22 = this.zzc.zzs(e2, e2);
                zzep.zzb(zzb22);
                throw zzb22;
            } catch (Exception e9) {
                Exception exc = e9;
                zzep = zzf2;
                e3 = exc;
                zzbf zzb32 = this.zzc.zzs(e3, new zzbf(zzbd.zzc, zzbc.zzZ, e3.getMessage()));
                zzep.zzb(zzb32);
                throw zzb32;
            }
        }
        return (zzst) obj;
    }
}
