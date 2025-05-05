package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzdx extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzee zzb;
    int zzc;
    zzbp zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdx(zzee zzee, Continuation continuation) {
        super(continuation);
        this.zzb = zzee;
    }

    public final Object invokeSuspend(Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        return this.zzb.zzl((Function1) null, this);
    }
}
