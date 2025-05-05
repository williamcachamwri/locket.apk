package com.google.android.gms.internal.recaptchabase;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J8\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"com/google/android/gms/recaptchabase/internal/InternalRecaptchaBaseClient$Companion$clientBuilder$1", "Lcom/google/android/gms/common/api/Api$AbstractClientBuilder;", "Lcom/google/android/gms/recaptchabase/internal/RecaptchaBaseClientImpl;", "Lcom/google/android/gms/common/api/Api$ApiOptions$NoOptions;", "buildClient", "context", "Landroid/content/Context;", "looper", "Landroid/os/Looper;", "commonSettings", "Lcom/google/android/gms/common/internal/ClientSettings;", "apiOptions", "connectedListener", "Lcom/google/android/gms/common/api/internal/ConnectionCallbacks;", "connectionFailedListener", "Lcom/google/android/gms/common/api/internal/OnConnectionFailedListener;", "java.com.google.android.gmscore.integ.client.recaptchabase_recaptchabase_kt"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
public final class zzi extends Api.AbstractClientBuilder {
    zzi() {
    }

    public final /* bridge */ /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(looper, "looper");
        Intrinsics.checkNotNullParameter(clientSettings, "commonSettings");
        Intrinsics.checkNotNullParameter((Api.ApiOptions.NoOptions) obj, "apiOptions");
        Intrinsics.checkNotNullParameter(connectionCallbacks, "connectedListener");
        Intrinsics.checkNotNullParameter(onConnectionFailedListener, "connectionFailedListener");
        return new zzm(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
