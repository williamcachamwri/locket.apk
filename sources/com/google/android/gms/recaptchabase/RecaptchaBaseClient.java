package com.google.android.gms.recaptchabase;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H'J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\u0006\u0010\n\u001a\u00020\u000bH'ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lcom/google/android/gms/recaptchabase/RecaptchaBaseClient;", "Lcom/google/android/gms/common/api/HasApiKey;", "Lcom/google/android/gms/common/api/Api$ApiOptions$NoOptions;", "init", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/recaptchabase/InitResult;", "initRequest", "Lcom/google/android/gms/recaptchabase/InitRequest;", "execute", "Lcom/google/android/gms/recaptchabase/ExecuteResult;", "executeRequest", "Lcom/google/android/gms/recaptchabase/ExecuteRequest;", "java.com.google.android.gmscore.integ.client.recaptchabase_recaptchabase_kt"}, k = 1, mv = {2, 0, 0}, xi = 48)
@KeepForSdk
/* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
public interface RecaptchaBaseClient extends HasApiKey<Api.ApiOptions.NoOptions> {
    Task<ExecuteResult> execute(ExecuteRequest executeRequest);

    Task<InitResult> init(InitRequest initRequest);
}
