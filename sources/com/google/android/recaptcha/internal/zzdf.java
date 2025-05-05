package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzdf extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzdv zzb;
    int zzc;
    zzep zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdf(zzdv zzdv, Continuation continuation) {
        super(continuation);
        this.zzb = zzdv;
    }

    public final Object invokeSuspend(Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        return this.zzb.zzl((String) null, 0, this);
    }
}
