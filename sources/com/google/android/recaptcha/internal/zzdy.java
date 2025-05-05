package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzdy extends ContinuationImpl {
    long zza;
    /* synthetic */ Object zzb;
    final /* synthetic */ zzee zzc;
    int zzd;
    zzee zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdy(zzee zzee, Continuation continuation) {
        super(continuation);
        this.zzc = zzee;
    }

    public final Object invokeSuspend(Object obj) {
        this.zzb = obj;
        this.zzd |= Integer.MIN_VALUE;
        return this.zzc.zzm(0, this);
    }
}
