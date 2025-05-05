package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzaf extends ContinuationImpl {
    Object zza;
    /* synthetic */ Object zzb;
    final /* synthetic */ zzag zzc;
    int zzd;
    zzep zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaf(zzag zzag, Continuation continuation) {
        super(continuation);
        this.zzc = zzag;
    }

    public final Object invokeSuspend(Object obj) {
        this.zzb = obj;
        this.zzd |= Integer.MIN_VALUE;
        return this.zzc.zzd((zzsg) null, this);
    }
}
