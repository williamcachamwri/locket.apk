package com.adsbynimbus.render.internal;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "androidx/core/view/ViewKt$doOnPreDraw$1"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: View.kt */
public final class WebViewExtensionsKt$loadAd$lambda$3$$inlined$doOnPreDraw$1 implements Runnable {
    final /* synthetic */ Function1 $action;
    final /* synthetic */ View $this_doOnPreDraw;

    public WebViewExtensionsKt$loadAd$lambda$3$$inlined$doOnPreDraw$1(Function1 function1, View view) {
        this.$action = function1;
        this.$this_doOnPreDraw = view;
    }

    public final void run() {
        this.$action.invoke(this.$this_doOnPreDraw);
    }
}
