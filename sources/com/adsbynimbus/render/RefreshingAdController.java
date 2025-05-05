package com.adsbynimbus.render;

import android.content.Context;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.NimbusAdManager;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.render.AdController;
import com.adsbynimbus.request.NimbusRequest;
import com.adsbynimbus.request.NimbusResponse;
import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;
import io.sentry.SentryBaseEvent;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B/\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\b\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020\u0001H\u0016J\u0010\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020.H\u0016J\u0010\u0010/\u001a\u00020&2\u0006\u00100\u001a\u000201H\u0016J\u0010\u00102\u001a\u00020&2\u0006\u00103\u001a\u000204H\u0014J\b\u00105\u001a\u00020&H\u0016J\b\u00106\u001a\u00020&H\u0016J\b\u00107\u001a\u00020&H\u0016R(\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0012\u0010 \u001a\u00020\u00178Æ\u0002¢\u0006\u0006\u001a\u0004\b!\u0010\u0019R\u0010\u0010\b\u001a\u00020\t8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u00068"}, d2 = {"Lcom/adsbynimbus/render/RefreshingAdController;", "Lcom/adsbynimbus/render/AdController;", "Lcom/adsbynimbus/render/AdController$Listener;", "Lcom/adsbynimbus/NimbusAdManager$Listener;", "Ljava/lang/Runnable;", "adView", "Lcom/adsbynimbus/render/NimbusAdView;", "caller", "nimbusAdManager", "Lcom/adsbynimbus/NimbusAdManager;", "request", "Lcom/adsbynimbus/request/NimbusRequest;", "refreshIntervalMillis", "", "(Lcom/adsbynimbus/render/NimbusAdView;Lcom/adsbynimbus/NimbusAdManager$Listener;Lcom/adsbynimbus/NimbusAdManager;Lcom/adsbynimbus/request/NimbusRequest;I)V", "<set-?>", "Lcom/adsbynimbus/NimbusAd;", "ad", "getAd", "()Lcom/adsbynimbus/NimbusAd;", "setAd$all_release", "(Lcom/adsbynimbus/NimbusAd;)V", "lastEventTime", "", "getLastEventTime", "()J", "setLastEventTime", "(J)V", "listener", "Ljava/lang/ref/WeakReference;", "getListener", "()Ljava/lang/ref/WeakReference;", "nextRequestTime", "getNextRequestTime", "view", "getView", "()Lcom/adsbynimbus/render/NimbusAdView;", "destroy", "", "onAdEvent", "adEvent", "Lcom/adsbynimbus/render/AdEvent;", "onAdRendered", "controller", "onAdResponse", "nimbusResponse", "Lcom/adsbynimbus/request/NimbusResponse;", "onError", "error", "Lcom/adsbynimbus/NimbusError;", "onViewableChanged", "isViewable", "", "run", "start", "stop", "all_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RefreshingAdController.kt */
public final class RefreshingAdController extends AdController implements AdController.Listener, NimbusAdManager.Listener, Runnable {
    private NimbusAd ad;
    private long lastEventTime;
    private final WeakReference<NimbusAdManager.Listener> listener;
    public final NimbusAdManager nimbusAdManager;
    public final int refreshIntervalMillis;
    public final NimbusRequest request;
    private final NimbusAdView view;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RefreshingAdController.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|(2:17|18)|19|21) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x003b */
        static {
            /*
                com.adsbynimbus.render.AdEvent[] r0 = com.adsbynimbus.render.AdEvent.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                com.adsbynimbus.render.AdEvent r2 = com.adsbynimbus.render.AdEvent.LOADED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                com.adsbynimbus.render.AdEvent r3 = com.adsbynimbus.render.AdEvent.DESTROYED     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                r3 = 3
                com.adsbynimbus.render.AdEvent r4 = com.adsbynimbus.render.AdEvent.IMPRESSION     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r0[r4] = r3     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                com.adsbynimbus.NimbusError$ErrorType[] r0 = com.adsbynimbus.NimbusError.ErrorType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.adsbynimbus.NimbusError$ErrorType r4 = com.adsbynimbus.NimbusError.ErrorType.NOT_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                com.adsbynimbus.NimbusError$ErrorType r1 = com.adsbynimbus.NimbusError.ErrorType.NO_BID     // Catch:{ NoSuchFieldError -> 0x003b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003b }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003b }
            L_0x003b:
                com.adsbynimbus.NimbusError$ErrorType r1 = com.adsbynimbus.NimbusError.ErrorType.NETWORK_ERROR     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                $EnumSwitchMapping$1 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.RefreshingAdController.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RefreshingAdController(NimbusAdView nimbusAdView, NimbusAdManager.Listener listener2, NimbusAdManager nimbusAdManager2, NimbusRequest nimbusRequest, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(nimbusAdView, listener2, nimbusAdManager2, nimbusRequest, (i2 & 16) != 0 ? HttpUrlConnectionNetworkFetcher.HTTP_DEFAULT_TIMEOUT : i);
    }

    public RefreshingAdController(NimbusAdView nimbusAdView, NimbusAdManager.Listener listener2, NimbusAdManager nimbusAdManager2, NimbusRequest nimbusRequest, int i) {
        Intrinsics.checkNotNullParameter(nimbusAdView, "adView");
        Intrinsics.checkNotNullParameter(listener2, "caller");
        Intrinsics.checkNotNullParameter(nimbusAdManager2, "nimbusAdManager");
        Intrinsics.checkNotNullParameter(nimbusRequest, SentryBaseEvent.JsonKeys.REQUEST);
        this.nimbusAdManager = nimbusAdManager2;
        this.request = nimbusRequest;
        this.refreshIntervalMillis = i;
        this.listener = new WeakReference<>(listener2);
        this.view = nimbusAdView;
    }

    public final NimbusAd getAd() {
        return this.ad;
    }

    public final void setAd$all_release(NimbusAd nimbusAd) {
        this.ad = nimbusAd;
    }

    public final long getLastEventTime() {
        return this.lastEventTime;
    }

    public final void setLastEventTime(long j) {
        this.lastEventTime = j;
    }

    public final WeakReference<NimbusAdManager.Listener> getListener() {
        return this.listener;
    }

    public final long getNextRequestTime() {
        return ((long) this.refreshIntervalMillis) - (System.currentTimeMillis() - getLastEventTime());
    }

    public NimbusAdView getView() {
        return this.view;
    }

    /* JADX WARNING: type inference failed for: r0v8, types: [android.view.ViewParent] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void destroy() {
        /*
            r3 = this;
            com.adsbynimbus.render.AdState r0 = r3.state
            com.adsbynimbus.render.AdState r1 = com.adsbynimbus.render.AdState.DESTROYED
            if (r0 == r1) goto L_0x0046
            com.adsbynimbus.render.AdEvent r0 = com.adsbynimbus.render.AdEvent.DESTROYED
            r3.dispatchAdEvent(r0)
            java.lang.ref.WeakReference<com.adsbynimbus.NimbusAdManager$Listener> r0 = r3.listener
            r0.clear()
            com.adsbynimbus.render.NimbusAdView r0 = r3.getView()
            r1 = r3
            java.lang.Runnable r1 = (java.lang.Runnable) r1
            r0.removeCallbacks(r1)
            com.adsbynimbus.render.NimbusAdView r0 = r3.getView()
            r1 = 0
            r0.refreshingController = r1
            com.adsbynimbus.render.NimbusAdView r0 = r3.getView()
            com.adsbynimbus.render.AdController r0 = r0.adController
            if (r0 == 0) goto L_0x002c
            r0.destroy()
        L_0x002c:
            com.adsbynimbus.render.NimbusAdView r0 = r3.getView()
            android.view.ViewParent r0 = r0.getParent()
            boolean r2 = r0 instanceof android.view.ViewGroup
            if (r2 == 0) goto L_0x003b
            r1 = r0
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
        L_0x003b:
            if (r1 == 0) goto L_0x0046
            com.adsbynimbus.render.NimbusAdView r0 = r3.getView()
            android.view.View r0 = (android.view.View) r0
            r1.removeView(r0)
        L_0x0046:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.RefreshingAdController.destroy():void");
    }

    public void start() {
        this.started = true;
        onViewableChanged(getView().isVisibleInWindow());
    }

    public void stop() {
        this.started = false;
        getView().removeCallbacks(this);
    }

    /* access modifiers changed from: protected */
    public void onViewableChanged(boolean z) {
        if (!z) {
            getView().removeCallbacks(this);
        } else if (this.started) {
            NimbusAd nimbusAd = this.ad;
            if (nimbusAd == null) {
                getView().postDelayed(this, ((long) this.refreshIntervalMillis) - (System.currentTimeMillis() - getLastEventTime()));
            } else if (nimbusAd != null) {
                AdController adController = getView().adController;
                if (adController != null) {
                    adController.destroy();
                }
                Renderer.Companion.loadAd(nimbusAd, getView(), this);
                this.ad = null;
            }
        }
    }

    public void onAdResponse(NimbusResponse nimbusResponse) {
        Intrinsics.checkNotNullParameter(nimbusResponse, "nimbusResponse");
        NimbusAdManager.Listener listener2 = (NimbusAdManager.Listener) this.listener.get();
        if (listener2 != null) {
            listener2.onAdResponse(nimbusResponse);
        }
        dispatchAdEvent(AdEvent.LOADED);
        if (!getView().isVisibleInWindow() || !this.started) {
            this.ad = nimbusResponse;
            return;
        }
        AdController adController = getView().adController;
        if (adController != null) {
            adController.destroy();
        }
        Renderer.Companion.loadAd(nimbusResponse, getView(), this);
    }

    public void onAdRendered(AdController adController) {
        Intrinsics.checkNotNullParameter(adController, "controller");
        adController.listeners.add(this);
    }

    public void onAdEvent(AdEvent adEvent) {
        Intrinsics.checkNotNullParameter(adEvent, "adEvent");
        int i = WhenMappings.$EnumSwitchMapping$0[adEvent.ordinal()];
        if (i != 1 && i != 2) {
            if (i != 3) {
                dispatchAdEvent(adEvent);
                return;
            }
            dispatchAdEvent(adEvent);
            this.lastEventTime = System.currentTimeMillis();
            getView().postDelayed(this, (long) this.refreshIntervalMillis);
        }
    }

    public void onError(NimbusError nimbusError) {
        Intrinsics.checkNotNullParameter(nimbusError, "error");
        int i = WhenMappings.$EnumSwitchMapping$1[nimbusError.errorType.ordinal()];
        if (i == 1) {
            destroy();
        } else if (i == 2 || i == 3) {
            NimbusAdManager.Listener listener2 = (NimbusAdManager.Listener) this.listener.get();
            if (listener2 != null) {
                listener2.onError(nimbusError);
            }
            this.lastEventTime = System.currentTimeMillis();
            getView().postDelayed(this, (long) this.refreshIntervalMillis);
        } else {
            dispatchError(nimbusError);
        }
    }

    public void run() {
        Long valueOf = Long.valueOf(((long) this.refreshIntervalMillis) - (System.currentTimeMillis() - getLastEventTime()));
        valueOf.longValue();
        if (!getView().isVisibleInWindow()) {
            valueOf = null;
        }
        if (valueOf != null) {
            long longValue = valueOf.longValue();
            if (longValue > 0) {
                getView().postDelayed(this, longValue);
                return;
            }
            this.lastEventTime = System.currentTimeMillis();
            NimbusAdManager nimbusAdManager2 = this.nimbusAdManager;
            Context context = getView().getContext();
            Intrinsics.checkNotNullExpressionValue(context, "view.context");
            nimbusAdManager2.makeRequest(context, this.request, this);
        }
    }
}
