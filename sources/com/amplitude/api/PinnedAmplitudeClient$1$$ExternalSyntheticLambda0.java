package com.amplitude.api;

import com.amplitude.api.PinnedAmplitudeClient;
import com.amplitude.util.Provider;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PinnedAmplitudeClient$1$$ExternalSyntheticLambda0 implements Provider {
    public final /* synthetic */ Provider f$0;
    public final /* synthetic */ SSLSocketFactory f$1;
    public final /* synthetic */ X509TrustManager f$2;

    public /* synthetic */ PinnedAmplitudeClient$1$$ExternalSyntheticLambda0(Provider provider, SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
        this.f$0 = provider;
        this.f$1 = sSLSocketFactory;
        this.f$2 = x509TrustManager;
    }

    public final Object get() {
        return PinnedAmplitudeClient.AnonymousClass1.lambda$run$0(this.f$0, this.f$1, this.f$2);
    }
}
