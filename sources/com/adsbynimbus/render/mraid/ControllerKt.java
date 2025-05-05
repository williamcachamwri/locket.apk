package com.adsbynimbus.render.mraid;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.adsbynimbus.Nimbus;
import com.adsbynimbus.internal.Components;
import com.adsbynimbus.internal.Logger;
import com.adsbynimbus.render.NimbusAdView;
import com.adsbynimbus.render.R;
import com.adsbynimbus.render.StaticAdController;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0013H\u0000\u001a\f\u0010\u0014\u001a\u00020\u0012*\u00020\u0013H\u0000\u001a\f\u0010\u0015\u001a\u00020\u0012*\u00020\u0013H\u0000\".\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018À\u0002@À\u0002X\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\".\u0010\b\u001a\u0004\u0018\u00010\u0003*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00038À\u0002@À\u0002X\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\"\u0019\u0010\r\u001a\u00020\u000e*\u00020\u00038À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0016"}, d2 = {"value", "Landroid/app/Dialog;", "expandContainer", "Landroid/view/View;", "getExpandContainer", "(Landroid/view/View;)Landroid/app/Dialog;", "setExpandContainer", "(Landroid/view/View;Landroid/app/Dialog;)V", "placeholder", "getPlaceholder", "(Landroid/view/View;)Landroid/view/View;", "setPlaceholder", "(Landroid/view/View;Landroid/view/View;)V", "position", "Lcom/adsbynimbus/render/mraid/Position;", "getPosition", "(Landroid/view/View;)Lcom/adsbynimbus/render/mraid/Position;", "close", "", "Lcom/adsbynimbus/render/StaticAdController;", "expand", "resize", "static_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Controller.kt */
public final class ControllerKt {
    public static final void resize(StaticAdController staticAdController) {
        WebView webView;
        Intrinsics.checkNotNullParameter(staticAdController, "<this>");
        ResizeProperties resizeProperties = staticAdController.getMraidHost().ResizeProperties;
        if (resizeProperties != null && (webView = (WebView) staticAdController.getView().findViewById(R.id.nimbus_web_view)) != null) {
            View view = webView;
            view.addOnLayoutChangeListener(new ControllerKt$resize$lambda$4$$inlined$doOnNextLayout$1(staticAdController, webView));
            DisplayMetrics displayMetrics = webView.getResources().getDisplayMetrics();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null) {
                Intrinsics.checkNotNullExpressionValue(displayMetrics, "resize$lambda$4$lambda$3$lambda$2");
                layoutParams.width = EnvironmentKt.dpToPx(displayMetrics, resizeProperties.getWidth());
                layoutParams.height = EnvironmentKt.dpToPx(displayMetrics, resizeProperties.getHeight());
                view.setLayoutParams(layoutParams);
                webView.setTranslationX((float) EnvironmentKt.dpToPx(displayMetrics, resizeProperties.getOffsetX()));
                webView.setTranslationY((float) EnvironmentKt.dpToPx(displayMetrics, resizeProperties.getOffsetY()));
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
        }
    }

    public static final void expand(StaticAdController staticAdController) {
        Object obj;
        Intrinsics.checkNotNullParameter(staticAdController, "<this>");
        Host mraidHost = staticAdController.getMraidHost();
        try {
            Result.Companion companion = Result.Companion;
            NimbusAdView view = staticAdController.getView();
            DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
            Intrinsics.checkNotNullExpressionValue(displayMetrics, "expand$lambda$19$lambda$18$lambda$5");
            Size size = new Size(EnvironmentKt.dpToPx(displayMetrics, mraidHost.ExpandProperties.getWidth()), EnvironmentKt.dpToPx(displayMetrics, mraidHost.ExpandProperties.getHeight()));
            ViewParent parent = view.getParent();
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
            ViewGroup viewGroup = (ViewGroup) parent;
            View view2 = new View(view.getContext());
            view2.setLayoutParams(view.getLayoutParams());
            viewGroup.addView(view2);
            view.setTag(R.id.placeholder, view2);
            viewGroup.removeView(view);
            Dialog dialog = new Dialog(view.getContext(), R.style.NimbusContainer);
            dialog.setCancelable(false);
            Window window = dialog.getWindow();
            if (window != null) {
                if (Components.isApi28()) {
                    window.getAttributes().layoutInDisplayCutoutMode = 1;
                }
                WindowCompat.setDecorFitsSystemWindows(window, false);
                WindowInsetsControllerCompat windowInsetsController = ViewCompat.getWindowInsetsController(window.getDecorView());
                if (windowInsetsController != null) {
                    windowInsetsController.setAppearanceLightStatusBars(true);
                    windowInsetsController.setSystemBarsBehavior(2);
                    windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
                }
            }
            dialog.setContentView(view, new ViewGroup.LayoutParams(-1, -1));
            view.setTag(R.id.expand_container, dialog);
            ImageButton imageButton = new ImageButton(view.getContext());
            int px = staticAdController.getView().getPx((Number) 8);
            imageButton.setId(R.id.nimbus_close);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, GravityCompat.END);
            layoutParams.setMargins(px, px, px, px);
            imageButton.setLayoutParams(layoutParams);
            if (Nimbus.closeDrawable == null) {
                imageButton.setImageResource(17301560);
            } else {
                Drawable drawable = Nimbus.closeDrawable;
                imageButton.setImageDrawable(drawable != null ? drawable.mutate() : null);
            }
            imageButton.setContentDescription(imageButton.getContext().getString(R.string.nimbus_dismiss));
            imageButton.setPadding(px, px, px, px);
            imageButton.setOnClickListener(new ControllerKt$$ExternalSyntheticLambda0(staticAdController));
            view.addView(imageButton);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            WebView webView = (WebView) view.findViewById(R.id.nimbus_web_view);
            if (webView != null) {
                webView.addOnLayoutChangeListener(new ControllerKt$expand$lambda$19$lambda$18$lambda$17$$inlined$doOnNextLayout$1(mraidHost, webView));
                View view3 = webView;
                ViewGroup.LayoutParams layoutParams2 = view3.getLayoutParams();
                if (layoutParams2 != null) {
                    layoutParams2.width = size.getWidth();
                    layoutParams2.height = size.getHeight();
                    view3.setLayoutParams(layoutParams2);
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
                }
            }
            dialog.show();
            obj = Result.m2444constructorimpl(view);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        Throwable r0 = Result.m2447exceptionOrNullimpl(obj);
        if (r0 != null) {
            Logger.log(5, r0.getMessage());
            WebView webView2 = (WebView) staticAdController.getView().findViewById(R.id.nimbus_web_view);
            if (webView2 != null) {
                StringBuilder sb = new StringBuilder();
                CommandKt.dispatchError(sb, "error expanding ad");
                webView2.evaluateJavascript(sb.toString(), (ValueCallback) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void expand$lambda$19$lambda$18$lambda$13$lambda$12(StaticAdController staticAdController, View view) {
        close(staticAdController);
    }

    public static final void close(StaticAdController staticAdController) {
        Object obj;
        Intrinsics.checkNotNullParameter(staticAdController, "<this>");
        WebView webView = null;
        if (Intrinsics.areEqual((Object) staticAdController.getMraidHost().State, (Object) "default")) {
            WebView webView2 = (WebView) staticAdController.getView().findViewById(R.id.nimbus_web_view);
            if (webView2 != null) {
                StringBuilder sb = new StringBuilder();
                CommandKt.updateState(sb, "hidden");
                CommandKt.dispatchStateChange(sb, "hidden");
                webView2.evaluateJavascript(sb.toString(), (ValueCallback) null);
            }
            staticAdController.destroy();
            return;
        }
        try {
            Result.Companion companion = Result.Companion;
            if (Intrinsics.areEqual((Object) staticAdController.getMraidHost().State, (Object) HostKt.EXPANDED)) {
                NimbusAdView view = staticAdController.getView();
                ViewParent parent = view.getParent();
                ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup != null) {
                    viewGroup.removeView(view);
                }
                View findViewById = view.findViewById(R.id.nimbus_close);
                if (findViewById != null) {
                    Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<View>(R.id.nimbus_close)");
                    view.removeView(findViewById);
                }
                Object tag = view.getTag(R.id.placeholder);
                View view2 = tag instanceof View ? (View) tag : null;
                if (view2 != null) {
                    view.setLayoutParams(view2.getLayoutParams());
                    ViewParent parent2 = view2.getParent();
                    Intrinsics.checkNotNull(parent2, "null cannot be cast to non-null type android.view.ViewGroup");
                    ViewGroup viewGroup2 = (ViewGroup) parent2;
                    viewGroup2.removeView(view2);
                    viewGroup2.addView(view);
                }
                Object tag2 = view.getTag(R.id.expand_container);
                Dialog dialog = tag2 instanceof Dialog ? (Dialog) tag2 : null;
                if (dialog != null) {
                    dialog.dismiss();
                }
                view.setTag(R.id.expand_container, (Object) null);
            }
            WebView webView3 = (WebView) staticAdController.getView().findViewById(R.id.nimbus_web_view);
            if (webView3 != null) {
                webView3.addOnLayoutChangeListener(new ControllerKt$close$lambda$32$lambda$31$$inlined$doOnNextLayout$1(webView3, staticAdController));
                DisplayMetrics displayMetrics = webView3.getResources().getDisplayMetrics();
                View view3 = webView3;
                ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
                if (layoutParams != null) {
                    Intrinsics.checkNotNullExpressionValue(displayMetrics, "close$lambda$32$lambda$31$lambda$30$lambda$29");
                    layoutParams.width = EnvironmentKt.dpToPx(displayMetrics, staticAdController.getMraidHost().DefaultPosition.getWidth());
                    layoutParams.height = EnvironmentKt.dpToPx(displayMetrics, staticAdController.getMraidHost().DefaultPosition.getHeight());
                    view3.setLayoutParams(layoutParams);
                    webView = webView3;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
                }
            }
            obj = Result.m2444constructorimpl(webView);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2447exceptionOrNullimpl(obj) != null) {
            staticAdController.destroy();
        }
    }

    public static final Position getPosition(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "_get_position_$lambda$34");
        return new Position(EnvironmentKt.pxToDp(displayMetrics, view.getWidth()), EnvironmentKt.pxToDp(displayMetrics, view.getHeight()), EnvironmentKt.pxToDp(displayMetrics, view.getLeft()), EnvironmentKt.pxToDp(displayMetrics, view.getTop()));
    }

    public static final View getPlaceholder(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Object tag = view.getTag(R.id.placeholder);
        if (tag instanceof View) {
            return (View) tag;
        }
        return null;
    }

    public static final void setPlaceholder(View view, View view2) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setTag(R.id.placeholder, view2);
    }

    public static final Dialog getExpandContainer(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Object tag = view.getTag(R.id.expand_container);
        if (tag instanceof Dialog) {
            return (Dialog) tag;
        }
        return null;
    }

    public static final void setExpandContainer(View view, Dialog dialog) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setTag(R.id.expand_container, dialog);
    }
}
