package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzdl extends ContinuationImpl {
    Object zza;
    /* synthetic */ Object zzb;
    final /* synthetic */ zzdv zzc;
    int zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdl(zzdv zzdv, Continuation continuation) {
        super(continuation);
        this.zzc = zzdv;
    }

    public final Object invokeSuspend(Object obj) {
        this.zzb = obj;
        this.zzd |= Integer.MIN_VALUE;
        return this.zzc.zzv((zzse) null, 0, this);
    }
}
