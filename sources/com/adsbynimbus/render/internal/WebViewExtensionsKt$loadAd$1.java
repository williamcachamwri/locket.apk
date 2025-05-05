package com.adsbynimbus.render.internal;

import android.view.View;
import android.webkit.WebView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebViewExtensions.kt */
final class WebViewExtensionsKt$loadAd$1 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ String $baseUrl;
    final /* synthetic */ String $markup;
    final /* synthetic */ WebView $this_loadAd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WebViewExtensionsKt$loadAd$1(WebView webView, String str, String str2) {
        super(1);
        this.$this_loadAd = webView;
        this.$baseUrl = str;
        this.$markup = str2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
        this.$this_loadAd.loadDataWithBaseURL(this.$baseUrl, this.$markup, (String) null, (String) null, (String) null);
    }
}
