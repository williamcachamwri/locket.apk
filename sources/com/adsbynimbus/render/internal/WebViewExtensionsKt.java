package com.adsbynimbus.render.internal;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.core.view.OneShotPreDrawListener;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import com.adsbynimbus.internal.Components;
import com.adsbynimbus.render.R;
import com.adsbynimbus.render.StaticAdController;
import com.adsbynimbus.render.StaticAdRenderer;
import com.adsbynimbus.render.mraid.HostKt;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\u001a\u0014\u0010\r\u001a\u00020\u000e*\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0007\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0007H\u0000\u001a\f\u0010\u0013\u001a\u00020\u0014*\u00020\u0002H\u0001\u001a&\u0010\u0015\u001a\u00020\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0007\u001a\u0014\u0010\u001a\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0006H\u0001\"\u001b\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0019\u0010\u0005\u001a\u00020\u0006*\u00020\u00078À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\b\"\u001b\u0010\t\u001a\u0004\u0018\u00010\u0002*\u00020\n8À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001c"}, d2 = {"controller", "Lcom/adsbynimbus/render/StaticAdController;", "Landroid/webkit/WebView;", "getController", "(Landroid/webkit/WebView;)Lcom/adsbynimbus/render/StaticAdController;", "isBaseUrl", "", "", "(Ljava/lang/String;)Z", "webView", "Landroid/view/ViewGroup;", "getWebView", "(Landroid/view/ViewGroup;)Landroid/webkit/WebView;", "asWebResponse", "Landroid/webkit/WebResourceResponse;", "Ljava/io/InputStream;", "mimeType", "checkLoadMraid", "url", "init", "", "loadAd", "", "markup", "preload", "baseUrl", "mute", "muted", "static_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebViewExtensions.kt */
public final class WebViewExtensionsKt {
    public static final void init(WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<this>");
        webView.setWebViewClient(NimbusWebViewClient.INSTANCE);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        if (Components.isApi21()) {
            settings.setMixedContentMode(0);
        }
        if (Components.isApi23()) {
            settings.setOffscreenPreRaster(true);
        }
        if (WebViewFeature.isFeatureSupported("MUTE_AUDIO")) {
            WebViewCompat.setAudioMuted(webView, true);
        }
    }

    public static final WebView getWebView(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        return (WebView) viewGroup.findViewById(R.id.nimbus_web_view);
    }

    public static final boolean isBaseUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return StringsKt.contains$default((CharSequence) str, (CharSequence) StaticAdRenderer.BASE_URL, false, 2, (Object) null);
    }

    public static final StaticAdController getController(WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<this>");
        Object tag = webView.getTag(R.id.controller);
        if (tag instanceof StaticAdController) {
            return (StaticAdController) tag;
        }
        return null;
    }

    public static final void mute(WebView webView, boolean z) {
        Intrinsics.checkNotNullParameter(webView, "<this>");
        if (WebViewFeature.isFeatureSupported("MUTE_AUDIO")) {
            WebViewCompat.setAudioMuted(webView, z);
        } else {
            webView.evaluateJavascript("try{[\"audio\", \"video\"].forEach(t => document.querySelectorAll(t).forEach(e => {e.defaultMuted=" + z + ",e.muted=" + z + ";}));}catch(e){}", (ValueCallback) null);
        }
    }

    public static /* synthetic */ WebResourceResponse asWebResponse$default(InputStream inputStream, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "text/javascript";
        }
        return asWebResponse(inputStream, str);
    }

    public static final WebResourceResponse asWebResponse(InputStream inputStream, String str) {
        Intrinsics.checkNotNullParameter(inputStream, "<this>");
        Intrinsics.checkNotNullParameter(str, "mimeType");
        return new WebResourceResponse(str, Charsets.UTF_8.name(), inputStream);
    }

    public static /* synthetic */ Object loadAd$default(WebView webView, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            str2 = StaticAdRenderer.BASE_URL;
        }
        return loadAd(webView, str, z, str2);
    }

    public static final Object loadAd(WebView webView, String str, boolean z, String str2) {
        Intrinsics.checkNotNullParameter(webView, "<this>");
        Intrinsics.checkNotNullParameter(str, "markup");
        Intrinsics.checkNotNullParameter(str2, "baseUrl");
        Function1 webViewExtensionsKt$loadAd$1 = new WebViewExtensionsKt$loadAd$1(webView, str2, str);
        View view = webView;
        if (!z) {
            return OneShotPreDrawListener.add(view, new WebViewExtensionsKt$loadAd$lambda$3$$inlined$doOnPreDraw$1(webViewExtensionsKt$loadAd$1, view));
        }
        if (!view.isLaidOut() || view.isLayoutRequested()) {
            view.addOnLayoutChangeListener(new WebViewExtensionsKt$loadAd$lambda$3$$inlined$doOnLayout$1(webViewExtensionsKt$loadAd$1));
        } else {
            webViewExtensionsKt$loadAd$1.invoke(view);
        }
        return Unit.INSTANCE;
    }

    public static final WebResourceResponse checkLoadMraid(WebView webView, String str) {
        Intrinsics.checkNotNullParameter(webView, "<this>");
        Intrinsics.checkNotNullParameter(str, "url");
        Object tag = webView.getTag(R.id.controller);
        StaticAdController staticAdController = tag instanceof StaticAdController ? (StaticAdController) tag : null;
        if (staticAdController == null) {
            return null;
        }
        if (!StringsKt.contains((CharSequence) str, (CharSequence) "mraid.js", true)) {
            staticAdController = null;
        }
        if (staticAdController == null) {
            return null;
        }
        InputStream open = webView.getResources().getAssets().open("nimbus_mraid.js", 2);
        byte[] bytes = HostKt.initJs(staticAdController.getMraidHost(), !staticAdController.getMraidInitialized()).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return asWebResponse$default(new SequenceInputStream(open, new ByteArrayInputStream(bytes)), (String) null, 1, (Object) null);
    }
}
