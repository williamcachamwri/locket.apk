package com.adsbynimbus.render.internal;

import androidx.core.app.NotificationCompat;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.VerificationProvider;
import com.adsbynimbus.render.AdEvent;
import com.adsbynimbus.render.CompanionAd;
import com.adsbynimbus.render.Renderer;
import com.iab.omid.library.adsbynimbus.adsession.CreativeType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u0019\u001a\u00020\u001aH\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u0001J\u0016\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001eH\u0001¢\u0006\u0002\u0010 J\t\u0010!\u001a\u00020\u001aH\u0001J\t\u0010\"\u001a\u00020\u0013H\u0001J\t\u0010#\u001a\u00020\u0013H\u0001J\b\u0010$\u001a\u00020\u0004H\u0016J\t\u0010%\u001a\u00020\u0004H\u0001J\u000b\u0010&\u001a\u0004\u0018\u00010\u0004H\u0001J\t\u0010'\u001a\u00020\u0004H\u0001J\u0015\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040)H\u0001J\u0019\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020-H\u0001J\t\u0010.\u001a\u00020\u0004H\u0001J\t\u0010/\u001a\u00020\u0013H\u0001J\t\u00100\u001a\u00020\u001aH\u0001R\u0014\u0010\t\u001a\u00020\u00048VX\u0005¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0014R\u0015\u0010\u0015\u001a\u00020\u00138À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u00061"}, d2 = {"Lcom/adsbynimbus/render/internal/OMInjectedAd;", "Lcom/adsbynimbus/NimbusAd;", "ad", "serviceJs", "", "providers", "", "Lcom/adsbynimbus/VerificationProvider;", "(Lcom/adsbynimbus/NimbusAd;Ljava/lang/String;Ljava/util/List;)V", "auctionId", "getAuctionId", "()Ljava/lang/String;", "creativeType", "Lcom/iab/omid/library/adsbynimbus/adsession/CreativeType;", "getCreativeType", "()Lcom/iab/omid/library/adsbynimbus/adsession/CreativeType;", "injectedMarkup", "getInjectedMarkup", "isMeasurable", "", "()Z", "isRenderedByNimbus", "isRenderedByNimbus$render_release", "getProviders", "()Ljava/util/List;", "bidInCents", "", "bidRaw", "", "companionAds", "", "Lcom/adsbynimbus/render/CompanionAd;", "()[Lcom/adsbynimbus/render/CompanionAd;", "height", "isInterstitial", "isMraid", "markup", "network", "placementId", "position", "renderInfo", "", "trackersForEvent", "", "event", "Lcom/adsbynimbus/render/AdEvent;", "type", "useNewRenderer", "width", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OpenMeasurement.kt */
public final class OMInjectedAd implements NimbusAd {
    private final /* synthetic */ NimbusAd $$delegate_0;
    private final CreativeType creativeType;
    private final String injectedMarkup;
    private final boolean isMeasurable;
    private final List<VerificationProvider> providers;

    public int bidInCents() {
        return this.$$delegate_0.bidInCents();
    }

    public float bidRaw() {
        return this.$$delegate_0.bidRaw();
    }

    public CompanionAd[] companionAds() {
        return this.$$delegate_0.companionAds();
    }

    public String getAuctionId() {
        return this.$$delegate_0.getAuctionId();
    }

    public int height() {
        return this.$$delegate_0.height();
    }

    public boolean isInterstitial() {
        return this.$$delegate_0.isInterstitial();
    }

    public boolean isMraid() {
        return this.$$delegate_0.isMraid();
    }

    public String network() {
        return this.$$delegate_0.network();
    }

    public String placementId() {
        return this.$$delegate_0.placementId();
    }

    public String position() {
        return this.$$delegate_0.position();
    }

    public Map<String, String> renderInfo() {
        return this.$$delegate_0.renderInfo();
    }

    public Collection<String> trackersForEvent(AdEvent adEvent) {
        Intrinsics.checkNotNullParameter(adEvent, NotificationCompat.CATEGORY_EVENT);
        return this.$$delegate_0.trackersForEvent(adEvent);
    }

    public String type() {
        return this.$$delegate_0.type();
    }

    public boolean useNewRenderer() {
        return this.$$delegate_0.useNewRenderer();
    }

    public int width() {
        return this.$$delegate_0.width();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00bf, code lost:
        if (isInterstitial() != false) goto L_0x00c2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public OMInjectedAd(com.adsbynimbus.NimbusAd r4, java.lang.String r5, java.util.List<? extends com.adsbynimbus.VerificationProvider> r6) {
        /*
            r3 = this;
            java.lang.String r0 = "ad"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "serviceJs"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "providers"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r3.<init>()
            r3.providers = r6
            r3.$$delegate_0 = r4
            java.lang.String r0 = r3.type()
            java.lang.String r1 = "video"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)
            r1 = 1
            if (r0 == 0) goto L_0x0027
            com.iab.omid.library.adsbynimbus.adsession.CreativeType r0 = com.iab.omid.library.adsbynimbus.adsession.CreativeType.VIDEO
            goto L_0x0051
        L_0x0027:
            java.lang.String r0 = r3.type()
            java.lang.String r2 = "static"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
            if (r0 == 0) goto L_0x004f
            com.adsbynimbus.render.Renderer$Companion r0 = com.adsbynimbus.render.Renderer.Companion
            boolean r0 = r3.isInterstitial()
            if (r0 == 0) goto L_0x003f
            androidx.collection.SimpleArrayMap<java.lang.String, com.adsbynimbus.render.Renderer$Blocking> r0 = com.adsbynimbus.render.Renderer.BLOCKING
            goto L_0x0041
        L_0x003f:
            androidx.collection.SimpleArrayMap<java.lang.String, com.adsbynimbus.render.Renderer> r0 = com.adsbynimbus.render.Renderer.INLINE
        L_0x0041:
            java.lang.String r2 = r3.network()
            boolean r0 = r0.containsKey(r2)
            r0 = r0 ^ r1
            if (r0 == 0) goto L_0x004f
            com.iab.omid.library.adsbynimbus.adsession.CreativeType r0 = com.iab.omid.library.adsbynimbus.adsession.CreativeType.HTML_DISPLAY
            goto L_0x0051
        L_0x004f:
            com.iab.omid.library.adsbynimbus.adsession.CreativeType r0 = com.iab.omid.library.adsbynimbus.adsession.CreativeType.NATIVE_DISPLAY
        L_0x0051:
            r3.creativeType = r0
            com.iab.omid.library.adsbynimbus.adsession.CreativeType r2 = com.iab.omid.library.adsbynimbus.adsession.CreativeType.HTML_DISPLAY
            if (r0 == r2) goto L_0x005c
            java.lang.String r4 = r4.markup()
            goto L_0x0090
        L_0x005c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r4.markup()
            java.lang.String r5 = com.iab.omid.library.adsbynimbus.ScriptInjector.injectScriptContentIntoHtml(r5, r2)
            r0.append(r5)
            java.lang.String r5 = "</body>"
            int r5 = r0.lastIndexOf(r5)
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r6 = r6.iterator()
        L_0x0078:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto L_0x008c
            java.lang.Object r2 = r6.next()
            com.adsbynimbus.VerificationProvider r2 = (com.adsbynimbus.VerificationProvider) r2
            java.lang.String r2 = r2.verificationMarkup(r4)
            r0.insert(r5, r2)
            goto L_0x0078
        L_0x008c:
            java.lang.String r4 = r0.toString()
        L_0x0090:
            r3.injectedMarkup = r4
            com.iab.omid.library.adsbynimbus.adsession.CreativeType r4 = r3.creativeType
            com.iab.omid.library.adsbynimbus.adsession.CreativeType r5 = com.iab.omid.library.adsbynimbus.adsession.CreativeType.HTML_DISPLAY
            if (r4 == r5) goto L_0x00c3
            java.util.List<com.adsbynimbus.VerificationProvider> r4 = r3.providers
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r4 = r4.isEmpty()
            r4 = r4 ^ r1
            if (r4 == 0) goto L_0x00c2
            com.adsbynimbus.render.Renderer$Companion r4 = com.adsbynimbus.render.Renderer.Companion
            boolean r4 = r3.isInterstitial()
            if (r4 == 0) goto L_0x00ae
            androidx.collection.SimpleArrayMap<java.lang.String, com.adsbynimbus.render.Renderer$Blocking> r4 = com.adsbynimbus.render.Renderer.BLOCKING
            goto L_0x00b0
        L_0x00ae:
            androidx.collection.SimpleArrayMap<java.lang.String, com.adsbynimbus.render.Renderer> r4 = com.adsbynimbus.render.Renderer.INLINE
        L_0x00b0:
            java.lang.String r5 = r3.network()
            boolean r4 = r4.containsKey(r5)
            r4 = r4 ^ r1
            if (r4 != 0) goto L_0x00c3
            boolean r4 = r3.isInterstitial()
            if (r4 != 0) goto L_0x00c2
            goto L_0x00c3
        L_0x00c2:
            r1 = 0
        L_0x00c3:
            r3.isMeasurable = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.internal.OMInjectedAd.<init>(com.adsbynimbus.NimbusAd, java.lang.String, java.util.List):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OMInjectedAd(NimbusAd nimbusAd, String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nimbusAd, str, (i & 4) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<VerificationProvider> getProviders() {
        return this.providers;
    }

    public final boolean isRenderedByNimbus$render_release() {
        Renderer.Companion companion = Renderer.Companion;
        return !(isInterstitial() ? Renderer.BLOCKING : Renderer.INLINE).containsKey(network());
    }

    public final CreativeType getCreativeType() {
        return this.creativeType;
    }

    public final String getInjectedMarkup() {
        return this.injectedMarkup;
    }

    public String markup() {
        return this.injectedMarkup;
    }

    public final boolean isMeasurable() {
        return this.isMeasurable;
    }
}
