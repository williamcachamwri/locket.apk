package com.adsbynimbus.render;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.ImageButton;
import androidx.media3.exoplayer.ExoPlayer;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.render.internal.AdTrackersKt;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B-\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\b\u0010-\u001a\u00020.H\u0017J\u0010\u0010/\u001a\u00020.2\u0006\u00100\u001a\u000201H\u0016J\u0010\u00102\u001a\u00020.2\u0006\u00103\u001a\u000204H\u0016J\b\u00105\u001a\u00020.H\u0014J\u0018\u00106\u001a\u00020.2\u0006\u00107\u001a\u00020(2\u0006\u00108\u001a\u000209H\u0014J\u0010\u0010:\u001a\u00020.2\u0006\u0010;\u001a\u00020\u0018H\u0014J\b\u0010<\u001a\u00020.H\u0016J\b\u0010=\u001a\u00020.H\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R$\u0010'\u001a\u00020(2\u0006\u0010'\u001a\u00020(8V@VX\u000e¢\u0006\f\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u0006>"}, d2 = {"Lcom/adsbynimbus/render/ImaVideoAdController;", "Lcom/adsbynimbus/render/AdController;", "Lcom/google/ads/interactivemedia/v3/api/AdEvent$AdEventListener;", "Lcom/google/ads/interactivemedia/v3/api/AdErrorEvent$AdErrorListener;", "adView", "Lcom/adsbynimbus/render/NimbusAdView;", "container", "Lcom/google/ads/interactivemedia/v3/api/AdDisplayContainer;", "player", "Lcom/adsbynimbus/render/ExoPlayerVideoPlayer;", "loader", "Lcom/google/ads/interactivemedia/v3/api/AdsLoader;", "adsManager", "Lcom/google/ads/interactivemedia/v3/api/AdsManager;", "(Lcom/adsbynimbus/render/NimbusAdView;Lcom/google/ads/interactivemedia/v3/api/AdDisplayContainer;Lcom/adsbynimbus/render/ExoPlayerVideoPlayer;Lcom/google/ads/interactivemedia/v3/api/AdsLoader;Lcom/google/ads/interactivemedia/v3/api/AdsManager;)V", "getAdsManager", "()Lcom/google/ads/interactivemedia/v3/api/AdsManager;", "getContainer", "()Lcom/google/ads/interactivemedia/v3/api/AdDisplayContainer;", "duration", "", "getDuration", "()F", "isStateChange", "", "()Z", "setStateChange", "(Z)V", "getLoader", "()Lcom/google/ads/interactivemedia/v3/api/AdsLoader;", "muteButton", "Landroid/widget/ImageButton;", "getMuteButton", "()Landroid/widget/ImageButton;", "getPlayer", "()Lcom/adsbynimbus/render/ExoPlayerVideoPlayer;", "view", "getView", "()Lcom/adsbynimbus/render/NimbusAdView;", "volume", "", "getVolume", "()I", "setVolume", "(I)V", "destroy", "", "onAdError", "adErrorEvent", "Lcom/google/ads/interactivemedia/v3/api/AdErrorEvent;", "onAdEvent", "adEvent", "Lcom/google/ads/interactivemedia/v3/api/AdEvent;", "onClickDetected", "onExposureChanged", "exposure", "visibleRect", "Landroid/graphics/Rect;", "onViewableChanged", "isViewable", "start", "stop", "video_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImaVideoAdController.kt */
public final class ImaVideoAdController extends AdController implements AdEvent.AdEventListener, AdErrorEvent.AdErrorListener {
    private final AdsManager adsManager;
    private final AdDisplayContainer container;
    private boolean isStateChange;
    private final AdsLoader loader;
    private final ImageButton muteButton;
    private final ExoPlayerVideoPlayer player;
    private final NimbusAdView view;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ImaVideoAdController.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(2:17|18)|19|21) */
        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|21) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType[] r0 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.LOADED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.CLICKED     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.STARTED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.RESUMED     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.PAUSED     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.FIRST_QUARTILE     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.MIDPOINT     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.THIRD_QUARTILE     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.COMPLETED     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.ImaVideoAdController.WhenMappings.<clinit>():void");
        }
    }

    public final AdDisplayContainer getContainer() {
        return this.container;
    }

    public final ExoPlayerVideoPlayer getPlayer() {
        return this.player;
    }

    public final AdsLoader getLoader() {
        return this.loader;
    }

    public final AdsManager getAdsManager() {
        return this.adsManager;
    }

    public ImaVideoAdController(NimbusAdView nimbusAdView, AdDisplayContainer adDisplayContainer, ExoPlayerVideoPlayer exoPlayerVideoPlayer, AdsLoader adsLoader, AdsManager adsManager2) {
        Intrinsics.checkNotNullParameter(nimbusAdView, "adView");
        Intrinsics.checkNotNullParameter(adDisplayContainer, TtmlNode.RUBY_CONTAINER);
        Intrinsics.checkNotNullParameter(exoPlayerVideoPlayer, "player");
        Intrinsics.checkNotNullParameter(adsLoader, "loader");
        Intrinsics.checkNotNullParameter(adsManager2, "adsManager");
        this.container = adDisplayContainer;
        this.player = exoPlayerVideoPlayer;
        this.loader = adsLoader;
        this.adsManager = adsManager2;
        adsManager2.addAdErrorListener(this);
        adsManager2.addAdEventListener(this);
        Collection<CompanionAdSlot> companionSlots = adDisplayContainer.getCompanionSlots();
        Intrinsics.checkNotNullExpressionValue(companionSlots, "container.companionSlots");
        for (CompanionAdSlot addClickListener : companionSlots) {
            addClickListener.addClickListener(new ImaVideoAdController$$ExternalSyntheticLambda0(this));
        }
        this.view = nimbusAdView;
        this.muteButton = nimbusAdView.getMuteButton();
    }

    public final boolean isStateChange() {
        return this.isStateChange;
    }

    public final void setStateChange(boolean z) {
        this.isStateChange = z;
    }

    static final void lambda$1$lambda$0(ImaVideoAdController imaVideoAdController) {
        imaVideoAdController.dispatchAdEvent(AdEvent.CLICKED);
    }

    public NimbusAdView getView() {
        return this.view;
    }

    public final ImageButton getMuteButton() {
        return this.muteButton;
    }

    public void destroy() {
        if (this.state != AdState.DESTROYED) {
            dispatchAdEvent(AdEvent.DESTROYED);
            this.isStateChange = true;
            this.adsManager.removeAdErrorListener(this);
            this.adsManager.removeAdEventListener(this);
            this.adsManager.destroy();
            this.loader.release();
            getView().destroy();
        }
    }

    public void start() {
        if (!this.started && this.state != AdState.DESTROYED) {
            this.started = true;
            onExposureChanged(getView().getExposure(), getView().getVisibleRect());
        }
    }

    public void stop() {
        if (this.started && this.state != AdState.DESTROYED) {
            this.started = false;
            if (this.state == AdState.RESUMED) {
                ExoPlayer exoPlayer = this.player.getExoPlayer();
                if (exoPlayer != null) {
                    exoPlayer.pause();
                }
                this.adsManager.pause();
                this.isStateChange = true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onClickDetected() {
        if (AdTrackersKt.isClickInChildView(getView(), this.muteButton)) {
            this.muteButton.performClick();
            return;
        }
        int childCount = getView().getChildCount();
        while (true) {
            childCount--;
            if (-1 < childCount) {
                View childAt = getView().getChildAt(childCount);
                WebView webView = childAt instanceof WebView ? (WebView) childAt : null;
                if (webView != null) {
                    webView.evaluateJavascript("try{ document.getElementsByClassName(\"videoAdUiLearnMore\")[0].click(); } catch (e) {}", (ValueCallback) null);
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onViewableChanged(boolean z) {
        ExoPlayer exoPlayer;
        if (!z && (exoPlayer = this.player.getExoPlayer()) != null) {
            exoPlayer.pause();
        }
        if (this.started && !this.isStateChange && this.state == AdState.RESUMED) {
            this.adsManager.pause();
            this.isStateChange = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onExposureChanged(int i, Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "visibleRect");
        if (this.started && !this.isStateChange) {
            if (i > 25) {
                if (this.state == AdState.READY) {
                    this.adsManager.start();
                    this.isStateChange = true;
                } else if (this.state == AdState.PAUSED) {
                    this.adsManager.resume();
                    this.isStateChange = true;
                }
            } else if (this.state == AdState.RESUMED) {
                this.adsManager.pause();
                this.isStateChange = true;
            }
        }
    }

    public int getVolume() {
        return this.player.volume();
    }

    public void setVolume(int i) {
        if (i != this.player.volume()) {
            this.player.setVolume(RangesKt.coerceIn(i, 0, 100));
            this.muteButton.setImageLevel(i);
            dispatchAdEvent(AdEvent.VOLUME_CHANGED);
        }
    }

    public float getDuration() {
        return (float) this.player.getDuration();
    }

    public void onAdError(AdErrorEvent adErrorEvent) {
        Intrinsics.checkNotNullParameter(adErrorEvent, "adErrorEvent");
        dispatchError(new NimbusError(NimbusError.ErrorType.CONTROLLER_ERROR, "Error during video playback", adErrorEvent.getError()));
    }

    public void onAdEvent(AdEvent adEvent) {
        ViewGroup container2;
        Intrinsics.checkNotNullParameter(adEvent, "adEvent");
        switch (WhenMappings.$EnumSwitchMapping$0[adEvent.getType().ordinal()]) {
            case 1:
                dispatchAdEvent(AdEvent.LOADED);
                onExposureChanged(getView().getExposure(), getView().getVisibleRect());
                this.muteButton.bringToFront();
                return;
            case 2:
                dispatchAdEvent(AdEvent.CLICKED);
                return;
            case 3:
                dispatchAdEvent(AdEvent.IMPRESSION);
                this.isStateChange = false;
                Collection<CompanionAdSlot> companionSlots = this.container.getCompanionSlots();
                Intrinsics.checkNotNullExpressionValue(companionSlots, "container.companionSlots");
                Collection arrayList = new ArrayList();
                for (Object next : companionSlots) {
                    CompanionAdSlot companionAdSlot = (CompanionAdSlot) next;
                    if (companionAdSlot.isFilled() && companionAdSlot.getHeight() <= 90) {
                        arrayList.add(next);
                    }
                }
                for (CompanionAdSlot container3 : (List) arrayList) {
                    ViewGroup container4 = container3.getContainer();
                    if (container4 != null) {
                        container4.setVisibility(0);
                    }
                }
                return;
            case 4:
                dispatchAdEvent(AdEvent.RESUMED);
                this.isStateChange = false;
                return;
            case 5:
                dispatchAdEvent(AdEvent.PAUSED);
                this.isStateChange = false;
                return;
            case 6:
                dispatchAdEvent(AdEvent.FIRST_QUARTILE);
                return;
            case 7:
                dispatchAdEvent(AdEvent.MIDPOINT);
                return;
            case 8:
                dispatchAdEvent(AdEvent.THIRD_QUARTILE);
                return;
            case 9:
                dispatchAdEvent(AdEvent.COMPLETED);
                Unit unit = Unit.INSTANCE;
                Collection<CompanionAdSlot> companionSlots2 = this.container.getCompanionSlots();
                Intrinsics.checkNotNullExpressionValue(companionSlots2, "container.companionSlots");
                for (CompanionAdSlot companionAdSlot2 : companionSlots2) {
                    if (companionAdSlot2.isFilled() && (container2 = companionAdSlot2.getContainer()) != null) {
                        container2.setVisibility(0);
                    }
                }
                this.muteButton.setVisibility(8);
                return;
            default:
                return;
        }
    }
}
