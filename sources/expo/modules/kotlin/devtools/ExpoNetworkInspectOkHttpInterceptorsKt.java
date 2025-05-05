package expo.modules.kotlin.devtools;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"TAG", "", "delegate", "Lexpo/modules/kotlin/devtools/ExpoNetworkInspectOkHttpInterceptorsDelegate;", "getDelegate", "()Lexpo/modules/kotlin/devtools/ExpoNetworkInspectOkHttpInterceptorsDelegate;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoNetworkInspectOkHttpInterceptors.kt */
public final class ExpoNetworkInspectOkHttpInterceptorsKt {
    private static final String TAG = "ExpoNetworkInspector";
    private static final ExpoNetworkInspectOkHttpInterceptorsDelegate delegate = ExpoRequestCdpInterceptor.INSTANCE;

    public static final ExpoNetworkInspectOkHttpInterceptorsDelegate getDelegate() {
        return delegate;
    }
}
