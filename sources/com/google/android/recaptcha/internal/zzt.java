package com.google.android.recaptcha.internal;

import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzt extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzaa zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ List zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzt(zzaa zzaa, String str, List list, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzaa;
        this.zzc = str;
        this.zzd = list;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzt(this.zzb, this.zzc, this.zzd, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzt) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        if (i == 0) {
            this.zza = 1;
            obj = this.zzb.zzc(this.zzc, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        this.zzd.add((zzac) obj);
        return Unit.INSTANCE;
    }
}
