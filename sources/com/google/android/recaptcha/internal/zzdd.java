package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzdd extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzde zzb;
    int zzc;
    zzep zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdd(zzde zzde, Continuation continuation) {
        super(continuation);
        this.zzb = zzde;
    }

    public final Object invokeSuspend(Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        return this.zzb.zzg((String) null, (Function2) null, this);
    }
}
