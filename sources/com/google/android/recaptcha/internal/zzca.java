package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzca extends ContinuationImpl {
    Object zza;
    /* synthetic */ Object zzb;
    final /* synthetic */ zzcd zzc;
    int zzd;
    zzcd zze;
    zzjg zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzca(zzcd zzcd, Continuation continuation) {
        super(continuation);
        this.zzc = zzcd;
    }

    public final Object invokeSuspend(Object obj) {
        this.zzb = obj;
        this.zzd |= Integer.MIN_VALUE;
        return this.zzc.zza((Object) null, this);
    }
}
