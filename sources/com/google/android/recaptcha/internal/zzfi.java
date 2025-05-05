package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzfi extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzfl zzb;
    int zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfi(zzfl zzfl, Continuation continuation) {
        super(continuation);
        this.zzb = zzfl;
    }

    public final Object invokeSuspend(Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        return zzfl.zzc(this.zzb, (zzbt) null, (zzsr) null, this);
    }
}
