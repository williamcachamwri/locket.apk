package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
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
import kotlinx.coroutines.Deferred;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzk extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzl zzb;
    final /* synthetic */ zzem zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zzse zze;
    private /* synthetic */ Object zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzk(zzl zzl, zzem zzem, long j, zzse zzse, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzl;
        this.zzc = zzem;
        this.zzd = j;
        this.zze = zzse;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        zzk zzk = new zzk(this.zzb, this.zzc, this.zzd, this.zze, continuation);
        zzk.zzf = obj;
        return zzk;
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzk) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        zzep zzep;
        Object obj3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza != 0) {
            zzep = (zzep) this.zzf;
            ResultKt.throwOnFailure(obj);
            obj2 = obj;
        } else {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.zzf;
            this.zzb.zzb = this.zzc;
            zzem zzem = this.zzc;
            zzem.zzc(zzem.zzd());
            zzep zzf2 = zzem.zzf(30);
            List arrayList = new ArrayList();
            for (zze zzj : this.zzb.zzd()) {
                arrayList.add(BuildersKt__Builders_commonKt.async$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new zzj(zzj, this.zzd, this.zze, (Continuation) null), 3, (Object) null));
            }
            Deferred[] deferredArr = (Deferred[]) arrayList.toArray(new Deferred[0]);
            this.zzf = zzf2;
            this.zza = 1;
            obj2 = AwaitKt.awaitAll((Deferred<? extends T>[]) (Deferred[]) Arrays.copyOf(deferredArr, deferredArr.length), this);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            zzep = zzf2;
        }
        Iterable iterable = (List) obj2;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!Result.m2450isFailureimpl(((Result) it.next()).m2453unboximpl())) {
                        zzep.zza();
                        obj3 = Result.m2444constructorimpl(Unit.INSTANCE);
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        zzbf zzbf = new zzbf(zzbd.zzb, zzbc.zzY, (String) null);
        zzep.zzb(zzbf);
        Result.Companion companion = Result.Companion;
        obj3 = Result.m2444constructorimpl(ResultKt.createFailure(zzbf));
        return Result.m2443boximpl(obj3);
    }
}
