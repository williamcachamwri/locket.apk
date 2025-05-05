package com.google.android.recaptcha.internal;

import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzfs extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzgf zzb;
    final /* synthetic */ List zzc;
    final /* synthetic */ zzfv zzd;
    private /* synthetic */ Object zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfs(zzgf zzgf, List list, zzfv zzfv, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzgf;
        this.zzc = list;
        this.zzd = zzfv;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        zzfs zzfs = new zzfs(this.zzb, this.zzc, this.zzd, continuation);
        zzfs.zze = obj;
        return zzfs;
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzfs) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        if (i == 0) {
            CoroutineScope coroutineScope = (CoroutineScope) this.zze;
            while (true) {
                zzgf zzgf = this.zzb;
                if (zzgf.zza() < 0) {
                    break;
                }
                if (zzgf.zza() >= this.zzc.size() || !CoroutineScopeKt.isActive(coroutineScope)) {
                    break;
                }
                zzuh zzuh = (zzuh) this.zzc.get(this.zzb.zza());
                try {
                    zzfv.zzf(this.zzd, zzuh, this.zzb);
                } catch (Exception e) {
                    zzuh.zzk();
                    Boxing.boxInt(zzuh.zzg());
                    CollectionsKt.joinToString$default(zzuh.zzj(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new zzfr(this.zzd), 31, (Object) null);
                    this.zza = 1;
                    if (this.zzd.zzh(e, this.zzb, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
