package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzfd extends SuspendLambda implements Function2 {
    final /* synthetic */ zzfh zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zztq zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfd(zzfh zzfh, String str, zztq zztq, Continuation continuation) {
        super(2, continuation);
        this.zza = zzfh;
        this.zzb = str;
        this.zzc = zztq;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzfd(this.zza, this.zzb, this.zzc, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzfd) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        try {
            if (zzfh.zzb(this.zza).zzb(this.zzb)) {
                return this.zza.zzg().zza(this.zzb, this.zzc);
            }
            throw new zzbf(zzbd.zzc, zzbc.zzQ, (String) null);
        } catch (zzbf e) {
            throw e;
        } catch (Exception e2) {
            throw new zzbf(zzbd.zzb, zzbc.zzaz, e2.getMessage());
        }
    }
}
