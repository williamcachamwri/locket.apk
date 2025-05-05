package com.google.android.recaptcha.internal;

import java.util.TimerTask;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzer extends TimerTask {
    final /* synthetic */ zzeu zza;

    public zzer(zzeu zzeu) {
        this.zza = zzeu;
    }

    public final void run() {
        TimerTask timerTask = this;
        zzeu zzeu = this.zza;
        Job unused = BuildersKt__Builders_commonKt.launch$default(zzeu.zzd, (CoroutineContext) null, (CoroutineStart) null, new zzes(zzeu, (Continuation) null), 3, (Object) null);
    }
}
