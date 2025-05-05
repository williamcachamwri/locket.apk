package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzfj extends SuspendLambda implements Function2 {
    final /* synthetic */ zzfl zza;
    final /* synthetic */ zzbt zzb;
    final /* synthetic */ zzsr zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfj(zzfl zzfl, zzbt zzbt, zzsr zzsr, Continuation continuation) {
        super(2, continuation);
        this.zza = zzfl;
        this.zzb = zzbt;
        this.zzc = zzsr;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzfj(this.zza, this.zzb, this.zzc, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzfj) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        zzey zzey = null;
        try {
            zzey = zzfl.zza(this.zza).zza(this.zzb.zzd());
            zzey.zzc();
            zzey.zze(this.zzc.zzd());
            zzst zzst = (zzst) zzey.zza(zzst.zzi());
            zzey.zzd();
            return zzst;
        } catch (zzbf e) {
            throw e;
        } catch (Exception e2) {
            throw new zzbf(zzbd.zzc, zzbc.zzF, e2.getMessage());
        } catch (Throwable th) {
            if (zzey != null) {
                zzey.zzd();
            }
            throw th;
        }
    }
}
