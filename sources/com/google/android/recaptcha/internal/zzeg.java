package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzeg extends ContinuationImpl {
    double zza;
    /* synthetic */ Object zzb;
    final /* synthetic */ zzeh zzc;
    int zzd;
    zzeh zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzeg(zzeh zzeh, Continuation continuation) {
        super(continuation);
        this.zzc = zzeh;
    }

    public final Object invokeSuspend(Object obj) {
        this.zzb = obj;
        this.zzd |= Integer.MIN_VALUE;
        return this.zzc.zzb(0, this);
    }
}
