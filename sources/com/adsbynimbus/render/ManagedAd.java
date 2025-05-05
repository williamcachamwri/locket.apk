package com.adsbynimbus.render;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.adsbynimbus.NimbusAdManager;
import com.adsbynimbus.request.NimbusRequest;
import io.sentry.SentryBaseEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\u0005\u001a\u00020\t*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011\"\u001b\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"refreshingAdView", "Lcom/adsbynimbus/render/NimbusAdView;", "Landroid/view/ViewGroup;", "getRefreshingAdView", "(Landroid/view/ViewGroup;)Lcom/adsbynimbus/render/NimbusAdView;", "refreshingController", "Lcom/adsbynimbus/render/RefreshingAdController;", "getRefreshingController", "(Landroid/view/ViewGroup;)Lcom/adsbynimbus/render/RefreshingAdController;", "Lcom/adsbynimbus/render/AdController;", "adManager", "Lcom/adsbynimbus/NimbusAdManager;", "request", "Lcom/adsbynimbus/request/NimbusRequest;", "refreshInterval", "", "caller", "Lcom/adsbynimbus/NimbusAdManager$Listener;", "all_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: RefreshingAdController.kt */
public final class ManagedAd {
    public static /* synthetic */ AdController refreshingController$default(ViewGroup viewGroup, NimbusAdManager nimbusAdManager, NimbusRequest nimbusRequest, int i, NimbusAdManager.Listener listener, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 30;
        }
        return refreshingController(viewGroup, nimbusAdManager, nimbusRequest, i, listener);
    }

    public static final NimbusAdView getRefreshingAdView(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        return (NimbusAdView) viewGroup.findViewById(R.id.nimbus_refreshing_controller);
    }

    public static final AdController refreshingController(ViewGroup viewGroup, NimbusAdManager nimbusAdManager, NimbusRequest nimbusRequest, int i, NimbusAdManager.Listener listener) {
        AdController adController;
        boolean z;
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        Intrinsics.checkNotNullParameter(nimbusAdManager, "adManager");
        Intrinsics.checkNotNullParameter(nimbusRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(listener, "caller");
        NimbusAdView nimbusAdView = (NimbusAdView) viewGroup.findViewById(R.id.nimbus_refreshing_controller);
        RefreshingAdController refreshingAdController = null;
        if (nimbusAdView != null) {
            adController = nimbusAdView.refreshingController;
        } else {
            adController = null;
        }
        RefreshingAdController refreshingAdController2 = adController instanceof RefreshingAdController ? (RefreshingAdController) adController : null;
        if (refreshingAdController2 != null) {
            if (!Intrinsics.areEqual((Object) refreshingAdController2.request.position, (Object) nimbusRequest.position)) {
                refreshingAdController2.destroy();
                Unit unit = Unit.INSTANCE;
                z = false;
            } else {
                z = true;
            }
            if (z) {
                refreshingAdController = refreshingAdController2;
            }
            if (refreshingAdController != null) {
                return refreshingAdController;
            }
        }
        Context context = viewGroup.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        NimbusAdView nimbusAdView2 = new NimbusAdView(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        nimbusAdView2.setId(R.id.nimbus_refreshing_controller);
        nimbusAdView2.setMinimumWidth(1);
        nimbusAdView2.setMinimumHeight(1);
        AdController refreshingAdController3 = new RefreshingAdController(nimbusAdView2, listener, nimbusAdManager, nimbusRequest, RangesKt.coerceAtLeast(i, 30) * 1000);
        nimbusAdView2.refreshingController = refreshingAdController3;
        viewGroup.addView(nimbusAdView2);
        return refreshingAdController3;
    }

    public static final RefreshingAdController getRefreshingController(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        NimbusAdView nimbusAdView = (NimbusAdView) viewGroup.findViewById(R.id.nimbus_refreshing_controller);
        AdController adController = nimbusAdView != null ? nimbusAdView.refreshingController : null;
        if (adController instanceof RefreshingAdController) {
            return (RefreshingAdController) adController;
        }
        return null;
    }
}
