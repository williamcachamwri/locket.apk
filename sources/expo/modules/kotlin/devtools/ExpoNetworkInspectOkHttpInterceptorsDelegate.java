package expo.modules.kotlin.devtools;

import kotlin.Metadata;
import okhttp3.Request;
import okhttp3.Response;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\"\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\tH&¨\u0006\f"}, d2 = {"Lexpo/modules/kotlin/devtools/ExpoNetworkInspectOkHttpInterceptorsDelegate;", "", "didReceiveResponse", "", "requestId", "", "request", "Lokhttp3/Request;", "response", "Lokhttp3/Response;", "willSendRequest", "redirectResponse", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoNetworkInspectOkHttpInterceptors.kt */
public interface ExpoNetworkInspectOkHttpInterceptorsDelegate {
    void didReceiveResponse(String str, Request request, Response response);

    void willSendRequest(String str, Request request, Response response);
}
