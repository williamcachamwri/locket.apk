package com.adsbynimbus.render;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.webkit.JavaScriptReplyProxy;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import com.adsbynimbus.Nimbus;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.internal.Components;
import com.adsbynimbus.render.internal.AdTrackersKt;
import com.adsbynimbus.render.internal.WebViewExtensionsKt;
import com.adsbynimbus.render.mraid.Host;
import com.adsbynimbus.render.mraid.HostKt;
import com.adsbynimbus.render.mraid.Position;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u00103\u001a\u000204H\u0017J\r\u00105\u001a\u000204H\u0000¢\u0006\u0002\b6J\b\u00107\u001a\u000204H\u0014J\u0018\u00108\u001a\u0002042\u0006\u00109\u001a\u00020\b2\u0006\u0010:\u001a\u00020;H\u0015J\r\u0010<\u001a\u000204H\u0000¢\u0006\u0002\b=J0\u0010>\u001a\u0002042\u0006\u0010+\u001a\u00020?2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u000f2\u0006\u0010E\u001a\u00020FH\u0016J\u0015\u0010G\u001a\u00020\u000f2\u0006\u0010H\u001a\u00020CH\u0000¢\u0006\u0002\bIJ\r\u0010J\u001a\u000204H\u0000¢\u0006\u0002\bKJ\b\u0010L\u001a\u000204H\u0016J\b\u0010M\u001a\u000204H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u000f8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011R\u001a\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001b\u0010\"\u001a\u00020#8FX\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b$\u0010%R\u001a\u0010(\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0011\"\u0004\b*\u0010\u0013R\u0014\u0010+\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R$\u0010/\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\r\"\u0004\b1\u00102¨\u0006N"}, d2 = {"Lcom/adsbynimbus/render/StaticAdController;", "Lcom/adsbynimbus/render/AdController;", "Landroidx/webkit/WebViewCompat$WebMessageListener;", "layout", "Lcom/adsbynimbus/render/NimbusAdView;", "ad", "Lcom/adsbynimbus/NimbusAd;", "completionTimeout", "", "(Lcom/adsbynimbus/render/NimbusAdView;Lcom/adsbynimbus/NimbusAd;I)V", "getAd", "()Lcom/adsbynimbus/NimbusAd;", "getCompletionTimeout", "()I", "didFireImpression", "", "getDidFireImpression", "()Z", "setDidFireImpression", "(Z)V", "didUserClick", "getDidUserClick", "lastClickTime", "", "getLastClickTime", "()J", "setLastClickTime", "(J)V", "markup", "", "getMarkup", "()Ljava/lang/String;", "setMarkup", "(Ljava/lang/String;)V", "mraidHost", "Lcom/adsbynimbus/render/mraid/Host;", "getMraidHost", "()Lcom/adsbynimbus/render/mraid/Host;", "mraidHost$delegate", "Lkotlin/Lazy;", "mraidInitialized", "getMraidInitialized", "setMraidInitialized", "view", "getView", "()Lcom/adsbynimbus/render/NimbusAdView;", "value", "volume", "getVolume", "setVolume", "(I)V", "destroy", "", "maybeFireImpression", "maybeFireImpression$static_release", "onClickDetected", "onExposureChanged", "exposure", "visibleRect", "Landroid/graphics/Rect;", "onLoaded", "onLoaded$static_release", "onPostMessage", "Landroid/webkit/WebView;", "message", "Landroidx/webkit/WebMessageCompat;", "sourceOrigin", "Landroid/net/Uri;", "isMainFrame", "replyProxy", "Landroidx/webkit/JavaScriptReplyProxy;", "openClickThrough", "uri", "openClickThrough$static_release", "renderProcessGone", "renderProcessGone$static_release", "start", "stop", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticAdController.kt */
public final class StaticAdController extends AdController implements WebViewCompat.WebMessageListener {
    private final NimbusAd ad;
    private final int completionTimeout;
    private boolean didFireImpression;
    private long lastClickTime;
    private String markup;
    private final Lazy mraidHost$delegate = LazyKt.lazy(new StaticAdController$mraidHost$2(this));
    private boolean mraidInitialized;
    private final NimbusAdView view;
    private int volume;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: StaticAdController.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.adsbynimbus.render.AdState[] r0 = com.adsbynimbus.render.AdState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.adsbynimbus.render.AdState r1 = com.adsbynimbus.render.AdState.LOADING     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.adsbynimbus.render.AdState r1 = com.adsbynimbus.render.AdState.READY     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.adsbynimbus.render.AdState r1 = com.adsbynimbus.render.AdState.RESUMED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.adsbynimbus.render.AdState r1 = com.adsbynimbus.render.AdState.PAUSED     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.adsbynimbus.render.AdState r1 = com.adsbynimbus.render.AdState.DESTROYED     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.StaticAdController.WhenMappings.<clinit>():void");
        }
    }

    public final NimbusAd getAd() {
        return this.ad;
    }

    public final int getCompletionTimeout() {
        return this.completionTimeout;
    }

    public StaticAdController(NimbusAdView nimbusAdView, NimbusAd nimbusAd, int i) {
        Intrinsics.checkNotNullParameter(nimbusAdView, TtmlNode.TAG_LAYOUT);
        Intrinsics.checkNotNullParameter(nimbusAd, "ad");
        this.ad = nimbusAd;
        this.completionTimeout = i;
        this.view = nimbusAdView;
    }

    public final boolean getDidFireImpression() {
        return this.didFireImpression;
    }

    public final void setDidFireImpression(boolean z) {
        this.didFireImpression = z;
    }

    public final long getLastClickTime() {
        return this.lastClickTime;
    }

    public final void setLastClickTime(long j) {
        this.lastClickTime = j;
    }

    public final String getMarkup() {
        return this.markup;
    }

    public final void setMarkup(String str) {
        this.markup = str;
    }

    public final boolean getMraidInitialized() {
        return this.mraidInitialized;
    }

    public final void setMraidInitialized(boolean z) {
        this.mraidInitialized = z;
    }

    public final Host getMraidHost() {
        return (Host) this.mraidHost$delegate.getValue();
    }

    public final boolean getDidUserClick() {
        return System.currentTimeMillis() - getLastClickTime() < 1000;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int i) {
        this.volume = i;
        WebView webView = (WebView) getView().findViewById(R.id.nimbus_web_view);
        if (webView != null) {
            boolean z = true;
            if (!(this.state != AdState.DESTROYED)) {
                webView = null;
            }
            if (webView != null) {
                if (i != 0) {
                    z = false;
                }
                WebViewExtensionsKt.mute(webView, z);
            }
        }
    }

    public void start() {
        if (this.state != AdState.DESTROYED && Components.isApi23()) {
            WebView webView = (WebView) getView().findViewById(R.id.nimbus_web_view);
            WebSettings settings = webView != null ? webView.getSettings() : null;
            if (settings != null) {
                settings.setOffscreenPreRaster(true);
            }
        }
    }

    public void stop() {
        if (this.state != AdState.DESTROYED && Components.isApi23()) {
            WebView webView = (WebView) getView().findViewById(R.id.nimbus_web_view);
            WebSettings settings = webView != null ? webView.getSettings() : null;
            if (settings != null) {
                settings.setOffscreenPreRaster(false);
            }
        }
        if (this.state == AdState.RESUMED) {
            dispatchAdEvent(AdEvent.PAUSED);
        }
    }

    public NimbusAdView getView() {
        return this.view;
    }

    public void destroy() {
        if (this.state != AdState.DESTROYED) {
            dispatchAdEvent(AdEvent.DESTROYED);
            WebView webView = (WebView) getView().findViewById(R.id.nimbus_web_view);
            if (webView != null) {
                if (WebViewFeature.isFeatureSupported("WEB_MESSAGE_LISTENER")) {
                    WebViewCompat.removeWebMessageListener(webView, Nimbus.sdkName);
                }
                Job unused = BuildersKt__Builders_commonKt.launch$default(Components.getNimbusScope(), Dispatchers.getMain(), (CoroutineStart) null, new StaticAdController$destroy$1$1(webView, (Continuation<? super StaticAdController$destroy$1$1>) null), 2, (Object) null);
            }
            NimbusAdView view2 = getView();
            View view3 = view2;
            Object tag = view3.getTag(R.id.expand_container);
            Dialog dialog = tag instanceof Dialog ? (Dialog) tag : null;
            if (dialog != null) {
                dialog.dismiss();
            }
            view3.setTag(R.id.expand_container, (Object) null);
            view3.setTag(R.id.placeholder, (Object) null);
            view2.destroy();
        }
    }

    /* access modifiers changed from: protected */
    public void onExposureChanged(int i, Rect rect) {
        WebView webView;
        WebView webView2;
        Intrinsics.checkNotNullParameter(rect, "visibleRect");
        boolean z = true;
        boolean z2 = i >= Math.max(Nimbus.getAdVisibilityMinPercentage(), 1);
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()];
        WebView webView3 = null;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 == 5) {
                            return;
                        }
                    } else if (z2) {
                        dispatchAdEvent(AdEvent.RESUMED);
                    }
                } else if (!z2) {
                    dispatchAdEvent(AdEvent.PAUSED);
                }
            } else if (z2) {
                maybeFireImpression$static_release();
            }
            String updateExposure = HostKt.updateExposure(getMraidHost(), i, new Position(rect.width(), rect.height(), rect.left, rect.top));
            if ((updateExposure.length() > 0) && (webView2 = (WebView) getView().findViewById(R.id.nimbus_web_view)) != null) {
                webView2.evaluateJavascript(updateExposure, (ValueCallback) null);
            }
            if (WebViewFeature.isFeatureSupported("MUTE_AUDIO") && (webView = (WebView) getView().findViewById(R.id.nimbus_web_view)) != null) {
                if (this.state != AdState.DESTROYED) {
                    webView3 = webView;
                }
                if (webView3 != null) {
                    if (!(i == 0 || getVolume() == 0)) {
                        z = false;
                    }
                    if (z != WebViewCompat.isAudioMuted(webView3)) {
                        WebViewCompat.setAudioMuted(webView3, z);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        String str = this.markup;
        if (str != null) {
            String str2 = z2 ? str : null;
            if (str2 != null) {
                WebView webView4 = (WebView) getView().findViewById(R.id.nimbus_web_view);
                if (webView4 != null) {
                    webView4.loadDataWithBaseURL(StaticAdRenderer.BASE_URL, str2, (String) null, (String) null, (String) null);
                }
                this.markup = null;
            }
        }
    }

    public final void onLoaded$static_release() {
        if (this.state == AdState.LOADING) {
            dispatchAdEvent(AdEvent.LOADED);
            if (getView().getExposure() > 0) {
                maybeFireImpression$static_release();
            } else {
                getView().onGlobalLayout();
            }
        }
    }

    public final void maybeFireImpression$static_release() {
        if (!this.didFireImpression) {
            this.didFireImpression = true;
            dispatchAdEvent(AdEvent.IMPRESSION);
            if (this.completionTimeout > 0) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(Components.getNimbusScope(), (CoroutineContext) null, (CoroutineStart) null, new StaticAdController$maybeFireImpression$1(this, (Continuation<? super StaticAdController$maybeFireImpression$1>) null), 3, (Object) null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onClickDetected() {
        this.lastClickTime = System.currentTimeMillis();
        if (getVolume() == 0 || this.state != AdState.DESTROYED) {
            setVolume(100);
        }
    }

    public final void renderProcessGone$static_release() {
        dispatchError(new NimbusError(NimbusError.ErrorType.WEBVIEW_ERROR, "WebView render process gone", (Throwable) null));
    }

    public void onPostMessage(WebView webView, WebMessageCompat webMessageCompat, Uri uri, boolean z, JavaScriptReplyProxy javaScriptReplyProxy) {
        Intrinsics.checkNotNullParameter(webView, "view");
        Intrinsics.checkNotNullParameter(webMessageCompat, "message");
        Intrinsics.checkNotNullParameter(uri, "sourceOrigin");
        Intrinsics.checkNotNullParameter(javaScriptReplyProxy, "replyProxy");
        boolean z2 = false;
        String onMraidCommand = (!Intrinsics.areEqual((Object) webMessageCompat.getData(), (Object) HostKt.READY) || this.mraidInitialized) ? HostKt.onMraidCommand(this, webMessageCompat.getData()) : HostKt.initMraid$default(this, (Position) null, false, 3, (Object) null);
        if (onMraidCommand.length() > 0) {
            z2 = true;
        }
        if (z2) {
            webView.evaluateJavascript(onMraidCommand, (ValueCallback) null);
        }
    }

    public final boolean openClickThrough$static_release(Uri uri) {
        Boolean bool;
        Intrinsics.checkNotNullParameter(uri, "uri");
        if ((System.currentTimeMillis() - getLastClickTime() < 1000) || getView().getClickProtectionDisabled()) {
            try {
                Result.Companion companion = Result.Companion;
                StaticAdController staticAdController = this;
                Context context = getView().getContext();
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                intent.setFlags(268435456);
                context.startActivity(intent);
                dispatchAdEvent(AdEvent.CLICKED);
                AdTrackersKt.trackEvent$default(this.ad, AdEvent.CLICKED, (Function1) null, 2, (Object) null);
                bool = Result.m2444constructorimpl(true);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                bool = Result.m2444constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m2450isFailureimpl(bool)) {
                bool = false;
            }
            if (((Boolean) bool).booleanValue()) {
                return true;
            }
        }
        return false;
    }
}
