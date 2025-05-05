package com.iab.omid.library.adsbynimbus.adsession;

import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import androidx.webkit.JavaScriptReplyProxy;
import androidx.webkit.ProxyConfig;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import com.iab.omid.library.adsbynimbus.internal.e;
import com.iab.omid.library.adsbynimbus.internal.f;
import com.iab.omid.library.adsbynimbus.utils.d;
import com.iab.omid.library.adsbynimbus.utils.g;
import com.iab.omid.library.adsbynimbus.utils.i;
import com.iab.omid.library.adsbynimbus.weakreference.a;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

public class JavaScriptSessionService {
    /* access modifiers changed from: private */
    public static String JS_MESSAGE_DATA_AD_SESSION_ID = "adSessionId";
    /* access modifiers changed from: private */
    public static String JS_MESSAGE_KEY_DATA = "data";
    /* access modifiers changed from: private */
    public static String JS_MESSAGE_KEY_METHOD = "method";
    private static String JS_MESSAGE_LISTENER_JS_SESSION_SERVICE = "omidJsSessionService";
    /* access modifiers changed from: private */
    public static String JS_MESSAGE_METHOD_FINISH_SESSION = "finishSession";
    /* access modifiers changed from: private */
    public static String JS_MESSAGE_METHOD_START_SESSION = "startSession";
    private static i webViewUtil = new i();
    private final HashMap<String, AdSession> adSessions = new HashMap<>();
    private final f friendlyObstructions = new f();
    private final boolean isHtmlAdView;
    private final Partner partner;
    private a weakAdView;
    private final WebView webView;

    public interface TearDownHandler {
        void onTearDown(boolean z);
    }

    private JavaScriptSessionService(Partner partner2, WebView webView2, boolean z) {
        g.a();
        g.a((Object) partner2, "Partner is null");
        g.a((Object) webView2, "WebView is null");
        this.partner = partner2;
        this.webView = webView2;
        this.isHtmlAdView = z;
        if (z) {
            setAdView(webView2);
        }
        addWebViewListener();
    }

    private void addWebViewListener() {
        if (WebViewFeature.isFeatureSupported("WEB_MESSAGE_LISTENER")) {
            removeWebViewListener();
            webViewUtil.a(this.webView, JS_MESSAGE_LISTENER_JS_SESSION_SERVICE, new HashSet(Arrays.asList(new String[]{ProxyConfig.MATCH_ALL_SCHEMES})), new WebViewCompat.WebMessageListener() {
                public void onPostMessage(WebView webView, WebMessageCompat webMessageCompat, Uri uri, boolean z, JavaScriptReplyProxy javaScriptReplyProxy) {
                    try {
                        JSONObject jSONObject = new JSONObject(webMessageCompat.getData());
                        String string = jSONObject.getString(JavaScriptSessionService.JS_MESSAGE_KEY_METHOD);
                        String string2 = jSONObject.getJSONObject(JavaScriptSessionService.JS_MESSAGE_KEY_DATA).getString(JavaScriptSessionService.JS_MESSAGE_DATA_AD_SESSION_ID);
                        if (string.equals(JavaScriptSessionService.JS_MESSAGE_METHOD_START_SESSION)) {
                            JavaScriptSessionService.this.startSession(string2);
                        } else if (string.equals(JavaScriptSessionService.JS_MESSAGE_METHOD_FINISH_SESSION)) {
                            JavaScriptSessionService.this.finishSession(string2);
                        } else {
                            d.b("Unexpected method in JavaScriptSessionService: " + string);
                        }
                    } catch (JSONException e) {
                        d.a("Error parsing JS message in JavaScriptSessionService.", e);
                    }
                }
            });
            return;
        }
        throw new UnsupportedOperationException("The JavaScriptSessionService cannot be supported in this WebView version.");
    }

    public static JavaScriptSessionService create(Partner partner2, WebView webView2, boolean z) {
        return new JavaScriptSessionService(partner2, webView2, z);
    }

    private AdSessionConfiguration createAdSessionConfiguration() {
        CreativeType creativeType = CreativeType.DEFINED_BY_JAVASCRIPT;
        ImpressionType impressionType = ImpressionType.DEFINED_BY_JAVASCRIPT;
        Owner owner = Owner.JAVASCRIPT;
        return AdSessionConfiguration.createAdSessionConfiguration(creativeType, impressionType, owner, owner, false);
    }

    private AdSessionContext createAdSessionContext() {
        return this.isHtmlAdView ? AdSessionContext.createHtmlAdSessionContext(this.partner, this.webView, (String) null, (String) null) : AdSessionContext.createJavascriptAdSessionContext(this.partner, this.webView, (String) null, (String) null);
    }

    /* access modifiers changed from: private */
    public void finishSession(String str) {
        AdSession adSession = this.adSessions.get(str);
        if (adSession != null) {
            adSession.finish();
            this.adSessions.remove(str);
        }
    }

    /* access modifiers changed from: private */
    public void removeWebViewListener() {
        webViewUtil.a(this.webView, JS_MESSAGE_LISTENER_JS_SESSION_SERVICE);
    }

    /* access modifiers changed from: private */
    public void startSession(String str) {
        a aVar = new a(createAdSessionConfiguration(), createAdSessionContext(), str);
        this.adSessions.put(str, aVar);
        aVar.registerAdView(getAdView());
        for (e next : this.friendlyObstructions.a()) {
            aVar.addFriendlyObstruction((View) next.c().get(), next.b(), next.a());
        }
        aVar.start();
    }

    public void addFriendlyObstruction(View view, FriendlyObstructionPurpose friendlyObstructionPurpose, String str) {
        for (AdSession addFriendlyObstruction : this.adSessions.values()) {
            addFriendlyObstruction.addFriendlyObstruction(view, friendlyObstructionPurpose, str);
        }
        this.friendlyObstructions.a(view, friendlyObstructionPurpose, str);
    }

    /* access modifiers changed from: package-private */
    public View getAdView() {
        a aVar = this.weakAdView;
        if (aVar == null) {
            return null;
        }
        return (View) aVar.get();
    }

    public void removeAllFriendlyObstructions() {
        for (AdSession removeAllFriendlyObstructions : this.adSessions.values()) {
            removeAllFriendlyObstructions.removeAllFriendlyObstructions();
        }
        this.friendlyObstructions.b();
    }

    public void removeFriendlyObstruction(View view) {
        for (AdSession removeFriendlyObstruction : this.adSessions.values()) {
            removeFriendlyObstruction.removeFriendlyObstruction(view);
        }
        this.friendlyObstructions.c(view);
    }

    public void setAdView(View view) {
        if (getAdView() != view) {
            for (AdSession registerAdView : this.adSessions.values()) {
                registerAdView.registerAdView(view);
            }
            this.weakAdView = new a(view);
        }
    }

    public void tearDown(final TearDownHandler tearDownHandler) {
        for (AdSession finish : this.adSessions.values()) {
            finish.finish();
        }
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                JavaScriptSessionService.this.removeWebViewListener();
                tearDownHandler.onTearDown(true);
                timer.cancel();
            }
        }, 1000);
    }
}
