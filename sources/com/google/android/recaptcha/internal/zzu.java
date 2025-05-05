package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzu extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzx zzb;
    final /* synthetic */ String zzc;
    private /* synthetic */ Object zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzu(zzx zzx, String str, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzx;
        this.zzc = str;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        zzu zzu = new zzu(this.zzb, this.zzc, continuation);
        zzu.zzd = obj;
        return zzu;
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzu) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        if (i == 0) {
            CoroutineScope coroutineScope = (CoroutineScope) this.zzd;
            List arrayList = new ArrayList();
            zzx zzx = this.zzb;
            zzx.zzo().put(this.zzc, arrayList);
            List arrayList2 = new ArrayList();
            Collection arrayList3 = new ArrayList();
            for (Object next : this.zzb.zzb) {
                if (((zzaa) next).zzf()) {
                    arrayList3.add(next);
                }
            }
            for (zzaa zzt : (List) arrayList3) {
                arrayList2.add(BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new zzt(zzt, this.zzc, arrayList, (Continuation) null), 3, (Object) null));
            }
            Job[] jobArr = (Job[]) arrayList2.toArray(new Job[0]);
            this.zza = 1;
            if (AwaitKt.joinAll((Job[]) Arrays.copyOf(jobArr, jobArr.length), (Continuation<? super Unit>) this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        Result.Companion companion = Result.Companion;
        return Result.m2443boximpl(Result.m2444constructorimpl(this.zzb.zzq(this.zzc)));
    }
}
