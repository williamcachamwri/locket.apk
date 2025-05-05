package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzcu extends ContinuationImpl {
    Object zza;
    Object zzb;
    Object zzc;
    long zzd;
    /* synthetic */ Object zze;
    final /* synthetic */ zzcx zzf;
    int zzg;
    String zzh;
    zzcj zzi;
    zzbk zzj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcu(zzcx zzcx, Continuation continuation) {
        super(continuation);
        this.zzf = zzcx;
    }

    public final Object invokeSuspend(Object obj) {
        this.zze = obj;
        this.zzg |= Integer.MIN_VALUE;
        return this.zzf.zzg((String) null, 0, (zzcp) null, (zzbk) null, (zzcj) null, this);
    }
}
