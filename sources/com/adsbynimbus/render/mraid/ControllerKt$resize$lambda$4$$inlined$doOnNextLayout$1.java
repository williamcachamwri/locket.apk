package com.adsbynimbus.render.mraid;

import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.adsbynimbus.render.StaticAdController;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\u00020\u0001JP\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u0016¨\u0006\u000f¸\u0006\u0000"}, d2 = {"androidx/core/view/ViewKt$doOnNextLayout$1", "Landroid/view/View$OnLayoutChangeListener;", "onLayoutChange", "", "view", "Landroid/view/View;", "left", "", "top", "right", "bottom", "oldLeft", "oldTop", "oldRight", "oldBottom", "core-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: View.kt */
public final class ControllerKt$resize$lambda$4$$inlined$doOnNextLayout$1 implements View.OnLayoutChangeListener {
    final /* synthetic */ WebView $this_apply$inlined;
    final /* synthetic */ StaticAdController $this_resize$inlined;

    public ControllerKt$resize$lambda$4$$inlined$doOnNextLayout$1(StaticAdController staticAdController, WebView webView) {
        this.$this_resize$inlined = staticAdController;
        this.$this_apply$inlined = webView;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        view.removeOnLayoutChangeListener(this);
        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "_get_position_$lambda$34");
        Position position = new Position(EnvironmentKt.pxToDp(displayMetrics, view.getWidth()), EnvironmentKt.pxToDp(displayMetrics, view.getHeight()), EnvironmentKt.pxToDp(displayMetrics, view.getLeft()), EnvironmentKt.pxToDp(displayMetrics, view.getTop()));
        boolean z = !Intrinsics.areEqual((Object) this.$this_resize$inlined.getMraidHost().State, (Object) HostKt.RESIZED);
        this.$this_resize$inlined.getMraidHost().CurrentPosition = position;
        this.$this_resize$inlined.getMraidHost().State = HostKt.RESIZED;
        WebView webView = this.$this_apply$inlined;
        StringBuilder sb = new StringBuilder();
        CommandKt.updatePosition(sb, this.$this_resize$inlined.getMraidHost().CurrentPosition, false);
        if (z) {
            CommandKt.updateState(sb, this.$this_resize$inlined.getMraidHost().State);
        }
        CommandKt.dispatchSizeChange(sb, new Size(position.getWidth(), position.getHeight()));
        if (z) {
            CommandKt.dispatchStateChange(sb, HostKt.RESIZED);
        }
        webView.evaluateJavascript(sb.toString(), (ValueCallback) null);
    }
}
