package com.google.android.gms.internal.recaptchabase;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.recaptchabase.ExecuteRequest;
import com.google.android.gms.recaptchabase.ExecuteResult;
import com.google.android.gms.recaptchabase.InitRequest;
import com.google.android.gms.recaptchabase.InitResult;
import com.google.android.gms.recaptchabase.RecaptchaBaseClient;
import com.google.android.gms.recaptchabase.zze;
import com.google.android.gms.tasks.Task;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0015B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B%\b\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00020\t¢\u0006\u0004\b\u0006\u0010\u000bJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016¨\u0006\u0016"}, d2 = {"Lcom/google/android/gms/recaptchabase/internal/InternalRecaptchaBaseClient;", "Lcom/google/android/gms/common/api/GoogleApi;", "Lcom/google/android/gms/common/api/Api$ApiOptions$NoOptions;", "Lcom/google/android/gms/recaptchabase/RecaptchaBaseClient;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "clientBuilder", "Lcom/google/android/gms/common/api/Api$AbstractClientBuilder;", "Lcom/google/android/gms/recaptchabase/internal/RecaptchaBaseClientImpl;", "(Landroid/content/Context;Lcom/google/android/gms/common/api/Api$AbstractClientBuilder;)V", "init", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/recaptchabase/InitResult;", "initRequest", "Lcom/google/android/gms/recaptchabase/InitRequest;", "execute", "Lcom/google/android/gms/recaptchabase/ExecuteResult;", "executeRequest", "Lcom/google/android/gms/recaptchabase/ExecuteRequest;", "Companion", "java.com.google.android.gmscore.integ.client.recaptchabase_recaptchabase_kt"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
public final class zzl extends GoogleApi implements RecaptchaBaseClient {
    public static final /* synthetic */ int zza = 0;
    private static final zzi zzb;
    private static final Api.ClientKey zzc;
    private static final Api zzd;

    static {
        zzi zzi = new zzi();
        zzb = zzi;
        Api.ClientKey clientKey = new Api.ClientKey();
        zzc = clientKey;
        zzd = new Api("RecaptchaBase.API", zzi, clientKey);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzl(Context context) {
        super(context, zzd, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final Task<ExecuteResult> execute(ExecuteRequest executeRequest) {
        Intrinsics.checkNotNullParameter(executeRequest, "executeRequest");
        Task<ExecuteResult> doRead = doRead(TaskApiCall.builder().setFeatures(zze.zza).run(new zzg(executeRequest)).setMethodKey(34002).build());
        Intrinsics.checkNotNullExpressionValue(doRead, "doRead(...)");
        return doRead;
    }

    public final Task<InitResult> init(InitRequest initRequest) {
        Intrinsics.checkNotNullParameter(initRequest, "initRequest");
        Task<InitResult> doRead = doRead(TaskApiCall.builder().setFeatures(zze.zzb).run(new zzh(initRequest)).setMethodKey(34001).build());
        Intrinsics.checkNotNullExpressionValue(doRead, "doRead(...)");
        return doRead;
    }
}
