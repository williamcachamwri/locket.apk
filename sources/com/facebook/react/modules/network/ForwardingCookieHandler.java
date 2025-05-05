package com.facebook.react.modules.network;

import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ForwardingCookieHandler extends CookieHandler {
    private static final String COOKIE_HEADER = "Cookie";
    private static final String VERSION_ONE_HEADER = "Set-cookie2";
    private static final String VERSION_ZERO_HEADER = "Set-cookie";
    private final ReactContext mContext;
    private CookieManager mCookieManager;

    public void destroy() {
    }

    public ForwardingCookieHandler(ReactContext reactContext) {
        this.mContext = reactContext;
    }

    public Map<String, List<String>> get(URI uri, Map<String, List<String>> map) throws IOException {
        CookieManager cookieManager = getCookieManager();
        if (cookieManager == null) {
            return Collections.emptyMap();
        }
        String cookie = cookieManager.getCookie(uri.toString());
        if (TextUtils.isEmpty(cookie)) {
            return Collections.emptyMap();
        }
        return Collections.singletonMap("Cookie", Collections.singletonList(cookie));
    }

    public void put(URI uri, Map<String, List<String>> map) throws IOException {
        String uri2 = uri.toString();
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            if (str != null && isCookieHeader(str)) {
                addCookies(uri2, (List) next.getValue());
            }
        }
    }

    public void clearCookies(Callback callback) {
        CookieManager cookieManager = getCookieManager();
        if (cookieManager != null) {
            cookieManager.removeAllCookies(new ForwardingCookieHandler$$ExternalSyntheticLambda0(callback));
        }
    }

    public void addCookies(String str, List<String> list) {
        CookieManager cookieManager = getCookieManager();
        if (cookieManager != null) {
            for (String addCookieAsync : list) {
                addCookieAsync(str, addCookieAsync);
            }
            cookieManager.flush();
        }
    }

    private void addCookieAsync(String str, String str2) {
        CookieManager cookieManager = getCookieManager();
        if (cookieManager != null) {
            cookieManager.setCookie(str, str2, (ValueCallback) null);
        }
    }

    private static boolean isCookieHeader(String str) {
        return str.equalsIgnoreCase(VERSION_ZERO_HEADER) || str.equalsIgnoreCase(VERSION_ONE_HEADER);
    }

    private CookieManager getCookieManager() {
        if (this.mCookieManager == null) {
            try {
                this.mCookieManager = CookieManager.getInstance();
            } catch (Exception | IllegalArgumentException unused) {
                return null;
            }
        }
        return this.mCookieManager;
    }
}
