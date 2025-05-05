package com.adsbynimbus.render.internal;

import android.webkit.WebResourceResponse;
import java.io.ByteArrayInputStream;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroid/webkit/WebResourceResponse;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebViewExtensions.kt */
final class NimbusWebViewClient$localResponder$1 extends Lambda implements Function1<String, WebResourceResponse> {
    public static final NimbusWebViewClient$localResponder$1 INSTANCE = new NimbusWebViewClient$localResponder$1();

    NimbusWebViewClient$localResponder$1() {
        super(1);
    }

    public final WebResourceResponse invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        byte[] bytes = "".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return WebViewExtensionsKt.asWebResponse$default(new ByteArrayInputStream(bytes), (String) null, 1, (Object) null);
    }
}
