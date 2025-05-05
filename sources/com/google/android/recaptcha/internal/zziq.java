package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zziq extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzjc zzb;
    int zzc;
    zzjc zzd;
    String zze;
    String zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zziq(zzjc zzjc, Continuation continuation) {
        super(continuation);
        this.zzb = zzjc;
    }

    public final Object invokeSuspend(Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        return this.zzb.zzF((String) null, this);
    }
}
