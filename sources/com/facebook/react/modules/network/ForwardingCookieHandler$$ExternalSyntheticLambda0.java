package com.facebook.react.modules.network;

import android.webkit.ValueCallback;
import com.facebook.react.bridge.Callback;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ForwardingCookieHandler$$ExternalSyntheticLambda0 implements ValueCallback {
    public final /* synthetic */ Callback f$0;

    public /* synthetic */ ForwardingCookieHandler$$ExternalSyntheticLambda0(Callback callback) {
        this.f$0 = callback;
    }

    public final void onReceiveValue(Object obj) {
        this.f$0.invoke((Boolean) obj);
    }
}
