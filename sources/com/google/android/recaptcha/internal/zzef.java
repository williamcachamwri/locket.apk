package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzef extends ContinuationImpl {
    double zza;
    /* synthetic */ Object zzb;
    final /* synthetic */ zzeh zzc;
    int zzd;
    zzeh zze;
    String zzf;
    RecaptchaAction zzg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzef(zzeh zzeh, Continuation continuation) {
        super(continuation);
        this.zzc = zzeh;
    }

    public final Object invokeSuspend(Object obj) {
        this.zzb = obj;
        this.zzd |= Integer.MIN_VALUE;
        return this.zzc.zza((String) null, (RecaptchaAction) null, 0, this);
    }
}
