package com.adsbynimbus.render.internal;

import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import androidx.core.view.ViewGroupKt;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.internal.Components;
import com.adsbynimbus.render.AdEvent;
import com.adsbynimbus.render.NimbusAdView;
import java.net.HttpURLConnection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a(\u0010\u0007\u001a\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¨\u0006\u0010"}, d2 = {"isClickInButton", "", "Lcom/adsbynimbus/render/NimbusAdView;", "isClickInChildView", "view", "Landroid/view/View;", "isClickInWebView", "trackEvent", "Lkotlinx/coroutines/Job;", "Lcom/adsbynimbus/NimbusAd;", "adEvent", "Lcom/adsbynimbus/render/AdEvent;", "connectionProvider", "Lkotlin/Function1;", "", "Ljava/net/HttpURLConnection;", "render_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: AdTrackers.kt */
public final class AdTrackersKt {
    public static /* synthetic */ Job trackEvent$default(NimbusAd nimbusAd, AdEvent adEvent, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = AdTrackersKt$trackEvent$1.INSTANCE;
        }
        return trackEvent(nimbusAd, adEvent, function1);
    }

    public static final Job trackEvent(NimbusAd nimbusAd, AdEvent adEvent, Function1<? super String, ? extends HttpURLConnection> function1) {
        Intrinsics.checkNotNullParameter(nimbusAd, "<this>");
        Intrinsics.checkNotNullParameter(adEvent, "adEvent");
        Intrinsics.checkNotNullParameter(function1, "connectionProvider");
        return BuildersKt__Builders_commonKt.launch$default(Components.getNimbusScope(), (CoroutineContext) null, (CoroutineStart) null, new AdTrackersKt$trackEvent$2(nimbusAd, adEvent, function1, (Continuation<? super AdTrackersKt$trackEvent$2>) null), 3, (Object) null);
    }

    public static final boolean isClickInChildView(NimbusAdView nimbusAdView, View view) {
        Intrinsics.checkNotNullParameter(nimbusAdView, "<this>");
        Intrinsics.checkNotNullParameter(view, "view");
        MotionEvent downEvent$render_release = nimbusAdView.getDownEvent$render_release();
        if (downEvent$render_release == null || view.getWidth() <= 0 || view.getHeight() <= 0 || downEvent$render_release.getX() - view.getX() >= ((float) view.getWidth()) || downEvent$render_release.getY() - view.getY() >= ((float) view.getHeight()) || downEvent$render_release.getX() - view.getX() <= 0.0f || downEvent$render_release.getY() - view.getY() <= 0.0f) {
            return false;
        }
        return true;
    }

    public static final boolean isClickInWebView(NimbusAdView nimbusAdView) {
        boolean z;
        Intrinsics.checkNotNullParameter(nimbusAdView, "<this>");
        Iterator<View> it = ViewGroupKt.getChildren(nimbusAdView).iterator();
        do {
            z = false;
            if (!it.hasNext()) {
                return false;
            }
            View next = it.next();
            if ((next instanceof WebView) && isClickInChildView(nimbusAdView, next)) {
                z = true;
                continue;
            }
        } while (!z);
        return true;
    }

    public static final boolean isClickInButton(NimbusAdView nimbusAdView) {
        boolean z;
        Intrinsics.checkNotNullParameter(nimbusAdView, "<this>");
        Iterator<View> it = ViewGroupKt.getChildren(nimbusAdView).iterator();
        do {
            z = false;
            if (!it.hasNext()) {
                return false;
            }
            View next = it.next();
            if ((next instanceof Button) && isClickInChildView(nimbusAdView, next)) {
                z = true;
                continue;
            }
        } while (!z);
        return true;
    }
}
