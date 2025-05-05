package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.p001authapi.zbd;

/* compiled from: com.google.android.gms:play-services-auth@@21.2.0 */
final class zba extends Api.AbstractClientBuilder {
    zba() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zbd(context, looper, clientSettings, (zbd) obj, connectionCallbacks, onConnectionFailedListener);
    }
}
