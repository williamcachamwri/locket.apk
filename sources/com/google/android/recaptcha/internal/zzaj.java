package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzaj extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzap zzb;
    int zzc;
    String zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaj(zzap zzap, Continuation continuation) {
        super(continuation);
        this.zzb = zzap;
    }

    public final Object invokeSuspend(Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        return this.zzb.zzj((String) null, this);
    }
}
