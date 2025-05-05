package com.adsbynimbus.render.internal;

import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import androidx.webkit.WebResourceErrorCompat;
import androidx.webkit.WebViewClientCompat;
import androidx.webkit.WebViewFeature;
import com.adsbynimbus.internal.Logger;
import com.adsbynimbus.render.R;
import com.adsbynimbus.render.StaticAdController;
import com.adsbynimbus.render.StaticAdRenderer;
import io.sentry.SentryBaseEvent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0016J \u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0017J\u001a\u0010\u0019\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0016J\u0018\u0010\u001a\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u001a\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0016R&\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/adsbynimbus/render/internal/NimbusWebViewClient;", "Landroidx/webkit/WebViewClientCompat;", "()V", "localResponder", "Lkotlin/Function1;", "", "Landroid/webkit/WebResourceResponse;", "getLocalResponder", "()Lkotlin/jvm/functions/Function1;", "setLocalResponder", "(Lkotlin/jvm/functions/Function1;)V", "onPageFinished", "", "view", "Landroid/webkit/WebView;", "url", "onReceivedError", "request", "Landroid/webkit/WebResourceRequest;", "error", "Landroidx/webkit/WebResourceErrorCompat;", "onRenderProcessGone", "", "detail", "Landroid/webkit/RenderProcessGoneDetail;", "shouldInterceptRequest", "shouldOverrideUrlLoading", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebViewExtensions.kt */
public final class NimbusWebViewClient extends WebViewClientCompat {
    public static final NimbusWebViewClient INSTANCE = new NimbusWebViewClient();
    private static Function1<? super String, ? extends WebResourceResponse> localResponder = NimbusWebViewClient$localResponder$1.INSTANCE;

    private NimbusWebViewClient() {
    }

    public final Function1<String, WebResourceResponse> getLocalResponder() {
        return localResponder;
    }

    public final void setLocalResponder(Function1<? super String, ? extends WebResourceResponse> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        localResponder = function1;
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        Intrinsics.checkNotNullParameter(webView, "view");
        Intrinsics.checkNotNullParameter(webResourceRequest, SentryBaseEvent.JsonKeys.REQUEST);
        String uri = webResourceRequest.getUrl().toString();
        Intrinsics.checkNotNullExpressionValue(uri, "it");
        if (!StringsKt.contains$default((CharSequence) uri, (CharSequence) StaticAdRenderer.BASE_URL, false, 2, (Object) null)) {
            uri = null;
        }
        if (uri == null) {
            return null;
        }
        WebResourceResponse checkLoadMraid = WebViewExtensionsKt.checkLoadMraid(webView, uri);
        if (checkLoadMraid == null) {
            checkLoadMraid = (WebResourceResponse) localResponder.invoke(uri);
        }
        return checkLoadMraid;
    }

    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceErrorCompat webResourceErrorCompat) {
        Intrinsics.checkNotNullParameter(webView, "view");
        Intrinsics.checkNotNullParameter(webResourceRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(webResourceErrorCompat, "error");
        if (WebViewFeature.isFeatureSupported("WEB_RESOURCE_ERROR_GET_DESCRIPTION")) {
            Logger.log(5, webResourceErrorCompat.getDescription() + " : " + webResourceRequest.getUrl());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.adsbynimbus.render.StaticAdController} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onRenderProcessGone(android.webkit.WebView r3, android.webkit.RenderProcessGoneDetail r4) {
        /*
            r2 = this;
            java.lang.String r4 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r4)
            android.view.ViewParent r4 = r3.getParent()
            boolean r0 = r4 instanceof android.view.ViewGroup
            r1 = 0
            if (r0 == 0) goto L_0x0012
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4
            goto L_0x0013
        L_0x0012:
            r4 = r1
        L_0x0013:
            if (r4 == 0) goto L_0x001b
            r0 = r3
            android.view.View r0 = (android.view.View) r0
            r4.removeView(r0)
        L_0x001b:
            int r4 = com.adsbynimbus.render.R.id.controller
            java.lang.Object r3 = r3.getTag(r4)
            boolean r4 = r3 instanceof com.adsbynimbus.render.StaticAdController
            if (r4 == 0) goto L_0x0028
            r1 = r3
            com.adsbynimbus.render.StaticAdController r1 = (com.adsbynimbus.render.StaticAdController) r1
        L_0x0028:
            if (r1 == 0) goto L_0x002d
            r1.renderProcessGone$static_release()
        L_0x002d:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.internal.NimbusWebViewClient.onRenderProcessGone(android.webkit.WebView, android.webkit.RenderProcessGoneDetail):boolean");
    }

    public void onPageFinished(WebView webView, String str) {
        Intrinsics.checkNotNullParameter(webView, "view");
        Object tag = webView.getTag(R.id.controller);
        StaticAdController staticAdController = tag instanceof StaticAdController ? (StaticAdController) tag : null;
        if (staticAdController != null) {
            WebViewExtensionsKt.mute(webView, staticAdController.getVolume() == 0);
            staticAdController.onLoaded$static_release();
        }
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        Intrinsics.checkNotNullParameter(webView, "view");
        if (str == null) {
            return null;
        }
        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) StaticAdRenderer.BASE_URL, false, 2, (Object) null)) {
            str = null;
        }
        if (str == null) {
            return null;
        }
        WebResourceResponse checkLoadMraid = WebViewExtensionsKt.checkLoadMraid(webView, str);
        if (checkLoadMraid == null) {
            checkLoadMraid = (WebResourceResponse) localResponder.invoke(str);
        }
        return checkLoadMraid;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Intrinsics.checkNotNullParameter(webView, "view");
        Object tag = webView.getTag(R.id.controller);
        StaticAdController staticAdController = tag instanceof StaticAdController ? (StaticAdController) tag : null;
        if (staticAdController == null) {
            return false;
        }
        Uri parse = Uri.parse(str);
        Intrinsics.checkNotNullExpressionValue(parse, "parse(url)");
        return staticAdController.openClickThrough$static_release(parse);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        Intrinsics.checkNotNullParameter(webView, "view");
        Intrinsics.checkNotNullParameter(webResourceRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Object tag = webView.getTag(R.id.controller);
        StaticAdController staticAdController = tag instanceof StaticAdController ? (StaticAdController) tag : null;
        if (staticAdController == null) {
            return false;
        }
        Uri url = webResourceRequest.getUrl();
        Intrinsics.checkNotNullExpressionValue(url, "request.url");
        return staticAdController.openClickThrough$static_release(url);
    }
}
