package com.facebook.react.modules.websocket;

import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeWebSocketModuleSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.network.CustomClientBuilder;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.google.common.net.HttpHeaders;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import io.sentry.clientreport.DiscardedEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

@ReactModule(name = "WebSocketModule")
public final class WebSocketModule extends NativeWebSocketModuleSpec {
    private static CustomClientBuilder customClientBuilder;
    /* access modifiers changed from: private */
    public final Map<Integer, ContentHandler> mContentHandlers = new ConcurrentHashMap();
    private ForwardingCookieHandler mCookieHandler;
    /* access modifiers changed from: private */
    public final Map<Integer, WebSocket> mWebSocketConnections = new ConcurrentHashMap();

    public interface ContentHandler {
        void onMessage(String str, WritableMap writableMap);

        void onMessage(ByteString byteString, WritableMap writableMap);
    }

    public void addListener(String str) {
    }

    public void removeListeners(double d) {
    }

    public WebSocketModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mCookieHandler = new ForwardingCookieHandler(reactApplicationContext);
    }

    public static void setCustomClientBuilder(CustomClientBuilder customClientBuilder2) {
        customClientBuilder = customClientBuilder2;
    }

    private static void applyCustomBuilder(OkHttpClient.Builder builder) {
        CustomClientBuilder customClientBuilder2 = customClientBuilder;
        if (customClientBuilder2 != null) {
            customClientBuilder2.apply(builder);
        }
    }

    public void invalidate() {
        for (WebSocket close : this.mWebSocketConnections.values()) {
            close.close(1001, (String) null);
        }
        this.mWebSocketConnections.clear();
        this.mContentHandlers.clear();
    }

    /* access modifiers changed from: private */
    public void sendEvent(String str, WritableMap writableMap) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext.hasActiveReactInstance()) {
            reactApplicationContext.emitDeviceEvent(str, writableMap);
        }
    }

    public void setContentHandler(int i, ContentHandler contentHandler) {
        if (contentHandler != null) {
            this.mContentHandlers.put(Integer.valueOf(i), contentHandler);
        } else {
            this.mContentHandlers.remove(Integer.valueOf(i));
        }
    }

    public void connect(String str, ReadableArray readableArray, ReadableMap readableMap, double d) {
        boolean z;
        final int i = (int) d;
        OkHttpClient.Builder readTimeout = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(0, TimeUnit.MINUTES);
        applyCustomBuilder(readTimeout);
        OkHttpClient build = readTimeout.build();
        Request.Builder url = new Request.Builder().tag(Integer.valueOf(i)).url(str);
        String cookie = getCookie(str);
        if (cookie != null) {
            url.addHeader("Cookie", cookie);
        }
        if (readableMap == null || !readableMap.hasKey("headers") || !readableMap.getType("headers").equals(ReadableType.Map)) {
            z = false;
        } else {
            ReadableMap map = readableMap.getMap("headers");
            ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
            z = false;
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                if (ReadableType.String.equals(map.getType(nextKey))) {
                    if (nextKey.equalsIgnoreCase("origin")) {
                        z = true;
                    }
                    url.addHeader(nextKey, map.getString(nextKey));
                } else {
                    FLog.w(ReactConstants.TAG, "Ignoring: requested " + nextKey + ", value not a string");
                }
            }
        }
        if (!z) {
            url.addHeader("origin", getDefaultOrigin(str));
        }
        if (readableArray != null && readableArray.size() > 0) {
            StringBuilder sb = new StringBuilder("");
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                String trim = readableArray.getString(i2).trim();
                if (!trim.isEmpty() && !trim.contains(",")) {
                    sb.append(trim);
                    sb.append(",");
                }
            }
            if (sb.length() > 0) {
                sb.replace(sb.length() - 1, sb.length(), "");
                url.addHeader(HttpHeaders.SEC_WEBSOCKET_PROTOCOL, sb.toString());
            }
        }
        build.newWebSocket(url.build(), new WebSocketListener() {
            public void onOpen(WebSocket webSocket, Response response) {
                WebSocketModule.this.mWebSocketConnections.put(Integer.valueOf(i), webSocket);
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putString("protocol", response.header(HttpHeaders.SEC_WEBSOCKET_PROTOCOL, ""));
                WebSocketModule.this.sendEvent("websocketOpen", createMap);
            }

            public void onClosing(WebSocket webSocket, int i, String str) {
                webSocket.close(i, str);
            }

            public void onClosed(WebSocket webSocket, int i, String str) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putInt(UniversalFirebaseFunctionsModule.CODE_KEY, i);
                createMap.putString(DiscardedEvent.JsonKeys.REASON, str);
                WebSocketModule.this.sendEvent("websocketClosed", createMap);
            }

            public void onFailure(WebSocket webSocket, Throwable th, Response response) {
                WebSocketModule.this.notifyWebSocketFailed(i, th.getMessage());
            }

            public void onMessage(WebSocket webSocket, String str) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putString("type", "text");
                ContentHandler contentHandler = (ContentHandler) WebSocketModule.this.mContentHandlers.get(Integer.valueOf(i));
                if (contentHandler != null) {
                    contentHandler.onMessage(str, createMap);
                } else {
                    createMap.putString("data", str);
                }
                WebSocketModule.this.sendEvent("websocketMessage", createMap);
            }

            public void onMessage(WebSocket webSocket, ByteString byteString) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putString("type", "binary");
                ContentHandler contentHandler = (ContentHandler) WebSocketModule.this.mContentHandlers.get(Integer.valueOf(i));
                if (contentHandler != null) {
                    contentHandler.onMessage(byteString, createMap);
                } else {
                    createMap.putString("data", byteString.base64());
                }
                WebSocketModule.this.sendEvent("websocketMessage", createMap);
            }
        });
        build.dispatcher().executorService().shutdown();
    }

    public void close(double d, String str, double d2) {
        int i = (int) d2;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket != null) {
            try {
                webSocket.close((int) d, str);
                this.mWebSocketConnections.remove(Integer.valueOf(i));
                this.mContentHandlers.remove(Integer.valueOf(i));
            } catch (Exception e) {
                FLog.e(ReactConstants.TAG, "Could not close WebSocket connection for id " + i, (Throwable) e);
            }
        }
    }

    public void send(String str, double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString("message", "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt(UniversalFirebaseFunctionsModule.CODE_KEY, 0);
            createMap2.putString(DiscardedEvent.JsonKeys.REASON, "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(str);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    public void sendBinary(String str, double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString("message", "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt(UniversalFirebaseFunctionsModule.CODE_KEY, 0);
            createMap2.putString(DiscardedEvent.JsonKeys.REASON, "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(ByteString.decodeBase64(str));
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    public void sendBinary(ByteString byteString, int i) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString("message", "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt(UniversalFirebaseFunctionsModule.CODE_KEY, 0);
            createMap2.putString(DiscardedEvent.JsonKeys.REASON, "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(byteString);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    public void ping(double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString("message", "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt(UniversalFirebaseFunctionsModule.CODE_KEY, 0);
            createMap2.putString(DiscardedEvent.JsonKeys.REASON, "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(ByteString.EMPTY);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void notifyWebSocketFailed(int i, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", i);
        createMap.putString("message", str);
        sendEvent("websocketFailed", createMap);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0075 A[Catch:{ URISyntaxException -> 0x00a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0090 A[Catch:{ URISyntaxException -> 0x00a1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getDefaultOrigin(java.lang.String r11) {
        /*
            java.net.URI r0 = new java.net.URI     // Catch:{ URISyntaxException -> 0x00a1 }
            r0.<init>(r11)     // Catch:{ URISyntaxException -> 0x00a1 }
            java.lang.String r1 = r0.getScheme()     // Catch:{ URISyntaxException -> 0x00a1 }
            int r2 = r1.hashCode()     // Catch:{ URISyntaxException -> 0x00a1 }
            r3 = 3804(0xedc, float:5.33E-42)
            java.lang.String r4 = "https"
            java.lang.String r5 = "http"
            r6 = -1
            r7 = 0
            r8 = 3
            r9 = 2
            r10 = 1
            if (r2 == r3) goto L_0x0045
            r3 = 118039(0x1cd17, float:1.65408E-40)
            if (r2 == r3) goto L_0x003a
            r3 = 3213448(0x310888, float:4.503E-39)
            if (r2 == r3) goto L_0x0032
            r3 = 99617003(0x5f008eb, float:2.2572767E-35)
            if (r2 == r3) goto L_0x002a
            goto L_0x0050
        L_0x002a:
            boolean r1 = r1.equals(r4)     // Catch:{ URISyntaxException -> 0x00a1 }
            if (r1 == 0) goto L_0x0050
            r1 = r8
            goto L_0x0051
        L_0x0032:
            boolean r1 = r1.equals(r5)     // Catch:{ URISyntaxException -> 0x00a1 }
            if (r1 == 0) goto L_0x0050
            r1 = r9
            goto L_0x0051
        L_0x003a:
            java.lang.String r2 = "wss"
            boolean r1 = r1.equals(r2)     // Catch:{ URISyntaxException -> 0x00a1 }
            if (r1 == 0) goto L_0x0050
            r1 = r7
            goto L_0x0051
        L_0x0045:
            java.lang.String r2 = "ws"
            boolean r1 = r1.equals(r2)     // Catch:{ URISyntaxException -> 0x00a1 }
            if (r1 == 0) goto L_0x0050
            r1 = r10
            goto L_0x0051
        L_0x0050:
            r1 = r6
        L_0x0051:
            if (r1 == 0) goto L_0x006f
            if (r1 == r10) goto L_0x006e
            java.lang.String r4 = ""
            if (r1 == r9) goto L_0x005c
            if (r1 == r8) goto L_0x005c
            goto L_0x006f
        L_0x005c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ URISyntaxException -> 0x00a1 }
            r1.<init>(r4)     // Catch:{ URISyntaxException -> 0x00a1 }
            java.lang.String r2 = r0.getScheme()     // Catch:{ URISyntaxException -> 0x00a1 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ URISyntaxException -> 0x00a1 }
            java.lang.String r4 = r1.toString()     // Catch:{ URISyntaxException -> 0x00a1 }
            goto L_0x006f
        L_0x006e:
            r4 = r5
        L_0x006f:
            int r1 = r0.getPort()     // Catch:{ URISyntaxException -> 0x00a1 }
            if (r1 == r6) goto L_0x0090
            java.lang.String r1 = "%s://%s:%s"
            java.lang.Object[] r2 = new java.lang.Object[r8]     // Catch:{ URISyntaxException -> 0x00a1 }
            r2[r7] = r4     // Catch:{ URISyntaxException -> 0x00a1 }
            java.lang.String r3 = r0.getHost()     // Catch:{ URISyntaxException -> 0x00a1 }
            r2[r10] = r3     // Catch:{ URISyntaxException -> 0x00a1 }
            int r0 = r0.getPort()     // Catch:{ URISyntaxException -> 0x00a1 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ URISyntaxException -> 0x00a1 }
            r2[r9] = r0     // Catch:{ URISyntaxException -> 0x00a1 }
            java.lang.String r11 = java.lang.String.format(r1, r2)     // Catch:{ URISyntaxException -> 0x00a1 }
            goto L_0x00a0
        L_0x0090:
            java.lang.String r1 = "%s://%s"
            java.lang.Object[] r2 = new java.lang.Object[r9]     // Catch:{ URISyntaxException -> 0x00a1 }
            r2[r7] = r4     // Catch:{ URISyntaxException -> 0x00a1 }
            java.lang.String r0 = r0.getHost()     // Catch:{ URISyntaxException -> 0x00a1 }
            r2[r10] = r0     // Catch:{ URISyntaxException -> 0x00a1 }
            java.lang.String r11 = java.lang.String.format(r1, r2)     // Catch:{ URISyntaxException -> 0x00a1 }
        L_0x00a0:
            return r11
        L_0x00a1:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unable to set "
            r1.<init>(r2)
            java.lang.StringBuilder r11 = r1.append(r11)
            java.lang.String r1 = " as default origin header"
            java.lang.StringBuilder r11 = r11.append(r1)
            java.lang.String r11 = r11.toString()
            r0.<init>(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.websocket.WebSocketModule.getDefaultOrigin(java.lang.String):java.lang.String");
    }

    private String getCookie(String str) {
        try {
            List list = this.mCookieHandler.get(new URI(getDefaultOrigin(str)), new HashMap()).get("Cookie");
            if (list == null) {
                return null;
            }
            if (list.isEmpty()) {
                return null;
            }
            return (String) list.get(0);
        } catch (IOException | URISyntaxException unused) {
            throw new IllegalArgumentException("Unable to get cookie from " + str);
        }
    }
}
