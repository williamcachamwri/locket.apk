package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zav;
import com.google.android.gms.signin.internal.zac;
import com.google.android.gms.signin.internal.zak;
import com.google.android.gms.signin.zad;
import com.google.android.gms.signin.zae;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zact extends zac implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final Api.AbstractClientBuilder zaa = zad.zac;
    private final Context zab;
    private final Handler zac;
    private final Api.AbstractClientBuilder zad;
    private final Set zae;
    private final ClientSettings zaf;
    private zae zag;
    /* access modifiers changed from: private */
    public zacs zah;

    public zact(Context context, Handler handler, ClientSettings clientSettings) {
        Api.AbstractClientBuilder abstractClientBuilder = zaa;
        this.zab = context;
        this.zac = handler;
        this.zaf = (ClientSettings) Preconditions.checkNotNull(clientSettings, "ClientSettings must not be null");
        this.zae = clientSettings.getRequiredScopes();
        this.zad = abstractClientBuilder;
    }

    static /* bridge */ /* synthetic */ void zad(zact zact, zak zak) {
        ConnectionResult zaa2 = zak.zaa();
        if (zaa2.isSuccess()) {
            zav zav = (zav) Preconditions.checkNotNull(zak.zab());
            ConnectionResult zaa3 = zav.zaa();
            if (!zaa3.isSuccess()) {
                String valueOf = String.valueOf(String.valueOf(zaa3));
                SentryLogcatAdapter.wtf("SignInCoordinator", "Sign-in succeeded with resolve account failure: ".concat(valueOf), new Exception());
                zact.zah.zae(zaa3);
                zact.zag.disconnect();
                return;
            }
            zact.zah.zaf(zav.zab(), zact.zae);
        } else {
            zact.zah.zae(zaa2);
        }
        zact.zag.disconnect();
    }

    public final void onConnected(Bundle bundle) {
        this.zag.zad(this);
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zah.zae(connectionResult);
    }

    public final void onConnectionSuspended(int i) {
        this.zah.zag(i);
    }

    public final void zab(zak zak) {
        this.zac.post(new zacr(this, zak));
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.gms.common.api.Api$Client, com.google.android.gms.signin.zae] */
    public final void zae(zacs zacs) {
        zae zae2 = this.zag;
        if (zae2 != null) {
            zae2.disconnect();
        }
        this.zaf.zae(Integer.valueOf(System.identityHashCode(this)));
        Api.AbstractClientBuilder abstractClientBuilder = this.zad;
        Context context = this.zab;
        Handler handler = this.zac;
        ClientSettings clientSettings = this.zaf;
        this.zag = abstractClientBuilder.buildClient(context, handler.getLooper(), clientSettings, clientSettings.zaa(), (GoogleApiClient.ConnectionCallbacks) this, (GoogleApiClient.OnConnectionFailedListener) this);
        this.zah = zacs;
        Set set = this.zae;
        if (set == null || set.isEmpty()) {
            this.zac.post(new zacq(this));
        } else {
            this.zag.zab();
        }
    }

    public final void zaf() {
        zae zae2 = this.zag;
        if (zae2 != null) {
            zae2.disconnect();
        }
    }
}
