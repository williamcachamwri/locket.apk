package com.google.android.recaptcha.internal;

import android.app.Application;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.RecaptchaException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.TimeoutCancellationException;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzcs {
    private static zzcx zza;

    public static final zzcx zza(Application application) {
        zzcx zzcx = zza;
        if (zzcx == null) {
            zzcx = new zzcx(application);
        }
        if (zza == null) {
            zza = zzcx;
        }
        return zzcx;
    }

    public static final Object zzb(Application application, String str, long j, Continuation continuation) throws TimeoutCancellationException, ApiException, RecaptchaException {
        return zzcx.zzh(zza(application), str, j, (zzcp) null, (zzbk) null, (zzcj) null, continuation, 28, (Object) null);
    }

    public static final Task zzc(Application application, String str, long j) throws TimeoutCancellationException, ApiException, RecaptchaException {
        return zzau.zza(BuildersKt__Builders_commonKt.async$default(zza(application).zzd().zza(), (CoroutineContext) null, (CoroutineStart) null, new zzcq(application, str, j, (Continuation) null), 3, (Object) null));
    }

    public static final Object zzd(Application application, String str, Continuation continuation) throws ApiException, RecaptchaException {
        return zzcx.zzf(zza(application), str, (zzcp) null, (zzbk) null, continuation, 6, (Object) null);
    }

    public static final Task zze(Application application, String str) throws ApiException, RecaptchaException {
        return zzau.zza(BuildersKt__Builders_commonKt.async$default(zza(application).zzd().zza(), (CoroutineContext) null, (CoroutineStart) null, new zzcr(application, str, (Continuation) null), 3, (Object) null));
    }
}
