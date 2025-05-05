package com.google.android.recaptcha.internal;

import java.util.Timer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzes extends SuspendLambda implements Function2 {
    final /* synthetic */ zzeu zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzes(zzeu zzeu, Continuation continuation) {
        super(2, continuation);
        this.zza = zzeu;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzes(this.zza, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzes) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        zzeu zzeu = this.zza;
        synchronized (zzeq.class) {
            zzek zzb = zzeu.zze;
            if (zzb != null && zzb.zzb() == 0) {
                Timer zzc = zzeu.zza;
                if (zzc != null) {
                    zzc.cancel();
                }
                zzeu.zza = null;
            }
            zzeu.zzg();
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
