package com.google.android.recaptcha.internal;

import java.util.TimerTask;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzak extends TimerTask {
    final /* synthetic */ zzap zza;

    public zzak(zzap zzap) {
        this.zza = zzap;
    }

    public final void run() {
        TimerTask timerTask = this;
        zzap zzap = this.zza;
        Job unused = BuildersKt__Builders_commonKt.launch$default(zzap.zzb, (CoroutineContext) null, (CoroutineStart) null, new zzal(zzap, (Continuation) null), 3, (Object) null);
    }
}
