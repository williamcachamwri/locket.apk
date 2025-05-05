package com.google.android.recaptcha.internal;

import android.content.ContentValues;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzet extends SuspendLambda implements Function2 {
    final /* synthetic */ zzeu zza;
    final /* synthetic */ zztz zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzet(zzeu zzeu, zztz zztz, Continuation continuation) {
        super(2, continuation);
        this.zza = zzeu;
        this.zzb = zztz;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzet(this.zza, this.zzb, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzet) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        zztz zztz = this.zzb;
        zzeu zzeu = this.zza;
        synchronized (zzeq.class) {
            if (zzeu.zze != null) {
                byte[] zzd = zztz.zzd();
                zzel zzel = new zzel(zzkj.zzg().zzi(zzd, 0, zzd.length), System.currentTimeMillis(), 0);
                zzek zzb2 = zzeu.zze;
                ContentValues contentValues = new ContentValues();
                contentValues.put("ss", zzel.zzc());
                contentValues.put("ts", Long.valueOf(zzel.zzb()));
                zzb2.getWritableDatabase().insert("ce", (String) null, contentValues);
                int zzb3 = zzeu.zze.zzb() - 500;
                if (zzb3 > 0) {
                    zzeu.zze.zza(CollectionsKt.take(zzeu.zze.zzd(), zzb3));
                }
                if (zzeu.zze.zzb() >= 20) {
                    zzeu.zzg();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
