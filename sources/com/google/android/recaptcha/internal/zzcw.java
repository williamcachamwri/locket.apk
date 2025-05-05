package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzcw extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzcx zzb;
    int zzc;
    zzep zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcw(zzcx zzcx, Continuation continuation) {
        super(continuation);
        this.zzb = zzcx;
    }

    public final Object invokeSuspend(Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        return this.zzb.zzj((String) null, 0, (Function2) null, this);
    }
}
