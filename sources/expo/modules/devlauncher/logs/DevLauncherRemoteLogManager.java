package expo.modules.devlauncher.logs;

import android.net.Uri;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0006\u0010\u0014\u001a\u00020\u000bR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lexpo/modules/devlauncher/logs/DevLauncherRemoteLogManager;", "Lokhttp3/WebSocketListener;", "httpClient", "Lokhttp3/OkHttpClient;", "url", "Landroid/net/Uri;", "(Lokhttp3/OkHttpClient;Landroid/net/Uri;)V", "batch", "", "", "deferError", "", "message", "throwable", "", "onOpen", "webSocket", "Lokhttp3/WebSocket;", "response", "Lokhttp3/Response;", "sendViaWebSocket", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherRemoteLogManager.kt */
public final class DevLauncherRemoteLogManager extends WebSocketListener {
    private final List<String> batch = new ArrayList();
    private final OkHttpClient httpClient;
    private final Uri url;

    public DevLauncherRemoteLogManager(OkHttpClient okHttpClient, Uri uri) {
        Intrinsics.checkNotNullParameter(okHttpClient, "httpClient");
        Intrinsics.checkNotNullParameter(uri, "url");
        this.httpClient = okHttpClient;
        this.url = uri;
    }

    public final void deferError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "throwable");
        this.batch.add(DevLauncherRemoteLogManagerKt.toRemoteLogString(th));
    }

    public final void deferError(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        this.batch.add(str);
    }

    public final void sendViaWebSocket() {
        Request.Builder builder = new Request.Builder();
        String uri = this.url.toString();
        Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
        this.httpClient.newWebSocket(builder.url(uri).build(), this);
    }

    public void onOpen(WebSocket webSocket, Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(response, io.sentry.protocol.Response.TYPE);
        webSocket.send(new DevLauncherRemoteLog(this.batch, (String) null, (String) null, 6, (DefaultConstructorMarker) null).toJson());
        webSocket.close(1000, (String) null);
        this.batch.clear();
    }
}
