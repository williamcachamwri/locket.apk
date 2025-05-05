package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzbr extends ContinuationImpl {
    Object zza;
    Object zzb;
    long zzc;
    long zzd;
    double zze;
    /* synthetic */ Object zzf;
    final /* synthetic */ zzbs zzg;
    int zzh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbr(zzbs zzbs, Continuation continuation) {
        super(continuation);
        this.zzg = zzbs;
    }

    public final Object invokeSuspend(Object obj) {
        this.zzf = obj;
        this.zzh |= Integer.MIN_VALUE;
        return this.zzg.zza((Function1) null, 0, 0, 0.0d, (Function1) null, this);
    }
}
