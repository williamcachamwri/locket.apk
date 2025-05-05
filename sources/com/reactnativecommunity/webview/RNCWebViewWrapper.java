package com.reactnativecommunity.webview;

import android.content.Context;
import android.view.View;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.FrameLayout;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/reactnativecommunity/webview/RNCWebViewWrapper;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "webView", "Lcom/reactnativecommunity/webview/RNCWebView;", "(Landroid/content/Context;Lcom/reactnativecommunity/webview/RNCWebView;)V", "getWebView", "()Lcom/reactnativecommunity/webview/RNCWebView;", "Companion", "react-native-webview_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RNCWebViewWrapper.kt */
public final class RNCWebViewWrapper extends FrameLayout {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final RNCWebView webView;

    @JvmStatic
    public static final int getReactTagFromWebView(WebView webView2) {
        return Companion.getReactTagFromWebView(webView2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RNCWebViewWrapper(Context context, RNCWebView rNCWebView) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(rNCWebView, "webView");
        rNCWebView.setBackgroundColor(0);
        addView(rNCWebView);
        View childAt = getChildAt(0);
        Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type com.reactnativecommunity.webview.RNCWebView");
        this.webView = (RNCWebView) childAt;
    }

    public final RNCWebView getWebView() {
        return this.webView;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/reactnativecommunity/webview/RNCWebViewWrapper$Companion;", "", "()V", "getReactTagFromWebView", "", "webView", "Landroid/webkit/WebView;", "react-native-webview_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNCWebViewWrapper.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final int getReactTagFromWebView(WebView webView) {
            Intrinsics.checkNotNullParameter(webView, "webView");
            ViewParent parent = webView.getParent();
            View view = parent instanceof View ? (View) parent : null;
            if (view != null) {
                return view.getId();
            }
            return -1;
        }
    }
}
