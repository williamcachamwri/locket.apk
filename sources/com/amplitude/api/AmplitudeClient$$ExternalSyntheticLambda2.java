package com.amplitude.api;

import com.amplitude.util.Provider;
import okhttp3.Call;
import okhttp3.Request;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AmplitudeClient$$ExternalSyntheticLambda2 implements Call.Factory {
    public final /* synthetic */ Provider f$0;

    public /* synthetic */ AmplitudeClient$$ExternalSyntheticLambda2(Provider provider) {
        this.f$0 = provider;
    }

    public final Call newCall(Request request) {
        return ((Call.Factory) this.f$0.get()).newCall(request);
    }
}
