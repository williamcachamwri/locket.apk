package com.adsbynimbus.render;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import com.adsbynimbus.Nimbus;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.internal.Component;
import com.adsbynimbus.internal.Platform;
import com.adsbynimbus.render.Renderer;
import com.adsbynimbus.render.internal.WebViewExtensionsKt;
import com.adsbynimbus.render.mraid.EnvironmentKt;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u00012\u00020\u0002:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J3\u0010\u0006\u001a\u00020\u0005\"\f\b\u0000\u0010\u0007*\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u0002H\u0007H\u0016¢\u0006\u0002\u0010\u000fJ;\u0010\u0006\u001a\u00020\u0005\"\f\b\u0000\u0010\u0007*\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u0002H\u0007¢\u0006\u0002\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/adsbynimbus/render/StaticAdRenderer;", "Lcom/adsbynimbus/render/Renderer;", "Lcom/adsbynimbus/internal/Component;", "()V", "install", "", "render", "T", "Lcom/adsbynimbus/render/Renderer$Listener;", "Lcom/adsbynimbus/NimbusError$Listener;", "ad", "Lcom/adsbynimbus/NimbusAd;", "container", "Landroid/view/ViewGroup;", "listener", "(Lcom/adsbynimbus/NimbusAd;Landroid/view/ViewGroup;Lcom/adsbynimbus/render/Renderer$Listener;)V", "replaceController", "", "(Lcom/adsbynimbus/NimbusAd;Landroid/view/ViewGroup;ZLcom/adsbynimbus/render/Renderer$Listener;)V", "Companion", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticAdRenderer.kt */
public final class StaticAdRenderer implements Renderer, Component {
    public static final String BASE_URL = "https://local.adsbynimbus.com";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String STATIC_AD_TYPE = "static";
    public static int completionTimeoutMs;
    /* access modifiers changed from: private */
    public static final Lazy<Boolean> supportsMraid$delegate = LazyKt.lazy(StaticAdRenderer$Companion$supportsMraid$2.INSTANCE);

    @JvmStatic
    public static final void setDefaultCompletionTimeoutMillis(int i) {
        Companion.setDefaultCompletionTimeoutMillis(i);
    }

    public void install() {
        Renderer.INLINE.put(STATIC_AD_TYPE, this);
    }

    public <T extends Renderer.Listener & NimbusError.Listener> void render(NimbusAd nimbusAd, ViewGroup viewGroup, T t) {
        Intrinsics.checkNotNullParameter(nimbusAd, "ad");
        Intrinsics.checkNotNullParameter(viewGroup, TtmlNode.RUBY_CONTAINER);
        Intrinsics.checkNotNullParameter(t, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        render(nimbusAd, viewGroup, true, t);
    }

    public static /* synthetic */ void render$default(StaticAdRenderer staticAdRenderer, NimbusAd nimbusAd, ViewGroup viewGroup, boolean z, Renderer.Listener listener, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        staticAdRenderer.render(nimbusAd, viewGroup, z, listener);
    }

    public final <T extends Renderer.Listener & NimbusError.Listener> void render(NimbusAd nimbusAd, ViewGroup viewGroup, boolean z, T t) {
        StaticAdController staticAdController;
        String injectEnvironment$default;
        NimbusAd nimbusAd2 = nimbusAd;
        ViewGroup viewGroup2 = viewGroup;
        T t2 = t;
        Intrinsics.checkNotNullParameter(nimbusAd2, "ad");
        Intrinsics.checkNotNullParameter(viewGroup2, TtmlNode.RUBY_CONTAINER);
        Intrinsics.checkNotNullParameter(t2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        boolean z2 = viewGroup2 instanceof NimbusAdView;
        NimbusAdView nimbusAdView = z2 ? (NimbusAdView) viewGroup2 : null;
        if (nimbusAdView == null) {
            Context context = viewGroup.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "container.context");
            nimbusAdView = new NimbusAdView(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        }
        WebView webView = new WebView(viewGroup.getContext());
        webView.setId(R.id.nimbus_web_view);
        FrameLayout.LayoutParams layoutParams = nimbusAdView.getLayoutParams(nimbusAd2);
        boolean z3 = false;
        webView.setMinimumWidth(Integer.max(0, layoutParams.width));
        webView.setMinimumHeight(Integer.max(0, layoutParams.height));
        webView.setLayoutParams(layoutParams);
        WebViewExtensionsKt.init(webView);
        nimbusAdView.addView(webView);
        WebView webView2 = (WebView) nimbusAdView.findViewById(R.id.nimbus_web_view);
        if (webView2 != null) {
            staticAdController = new StaticAdController(nimbusAdView, nimbusAd2, completionTimeoutMs);
            if (z) {
                nimbusAdView.adController = staticAdController;
            }
            webView2.setTag(R.id.controller, staticAdController);
            if (!WebViewFeature.isFeatureSupported("WEB_MESSAGE_LISTENER")) {
                injectEnvironment$default = nimbusAd.markup();
            } else {
                WebViewCompat.addWebMessageListener(webView2, Nimbus.sdkName, SetsKt.setOf(BASE_URL), staticAdController);
                String markup = nimbusAd.markup();
                String id = Platform.adInfo.getId();
                if (id == null) {
                    id = Nimbus.EMPTY_AD_ID;
                }
                String str = id;
                boolean isLimitAdTrackingEnabled = Platform.adInfo.isLimitAdTrackingEnabled();
                boolean z4 = Nimbus.COPPA;
                String packageName = viewGroup.getContext().getPackageName();
                Intrinsics.checkNotNullExpressionValue(str, "Platform.adInfo.id ?: EMPTY_AD_ID");
                Intrinsics.checkNotNullExpressionValue(packageName, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
                injectEnvironment$default = EnvironmentKt.injectEnvironment$default(markup, EnvironmentKt.mraidEnv$default(str, isLimitAdTrackingEnabled, packageName, z4, (String) null, (String) null, (String) null, 112, (Object) null), 0, 2, (Object) null);
            }
            String str2 = injectEnvironment$default;
            if (nimbusAd.isMraid() || Nimbus.getAdVisibilityMinPercentage() == 0) {
                z3 = true;
            }
            WebViewExtensionsKt.loadAd$default(webView2, str2, z3, (String) null, 4, (Object) null);
            if (!z2) {
                viewGroup2.addView(nimbusAdView);
            }
        } else {
            staticAdController = null;
        }
        if (staticAdController != null) {
            t2.onAdRendered(staticAdController);
        } else {
            ((NimbusError.Listener) t2).onError(new NimbusError(NimbusError.ErrorType.RENDERER_ERROR, "Error creating WebView.", (Throwable) null));
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\u0010\u001a\u00020\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00078\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8FX\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Lcom/adsbynimbus/render/StaticAdRenderer$Companion;", "", "()V", "BASE_URL", "", "STATIC_AD_TYPE", "completionTimeoutMs", "", "supportsMraid", "", "getSupportsMraid", "()Z", "supportsMraid$delegate", "Lkotlin/Lazy;", "setDefaultCompletionTimeoutMillis", "", "completionTimeoutMillis", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: StaticAdRenderer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void setDefaultCompletionTimeoutMillis(int i) {
            if (StaticAdRenderer.completionTimeoutMs >= 0) {
                StaticAdRenderer.completionTimeoutMs = i;
            }
        }

        public final boolean getSupportsMraid() {
            return ((Boolean) StaticAdRenderer.supportsMraid$delegate.getValue()).booleanValue();
        }
    }
}
