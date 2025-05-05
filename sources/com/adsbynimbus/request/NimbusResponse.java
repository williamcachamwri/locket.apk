package com.adsbynimbus.request;

import androidx.core.app.NotificationCompat;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.openrtb.response.BidResponse;
import com.adsbynimbus.render.AdEvent;
import com.adsbynimbus.render.CompanionAd;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0005\n\u0002\b\u000b\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002>?B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010.\u001a\u00020\rH\u0016J\b\u0010/\u001a\u000200H\u0016J\u0015\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012H\u0016¢\u0006\u0002\u00101J\b\u0010\u0017\u001a\u00020\rH\u0016J\b\u00102\u001a\u000203H\u0016J\b\u00104\u001a\u000203H\u0016J\b\u0010\u001e\u001a\u00020\u0006H\u0016J\b\u0010 \u001a\u00020\u0006H\u0016J\n\u00105\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u00106\u001a\u00020\u0006H\u0016J\u0014\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000608H\u0016J\u0018\u00109\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010:2\u0006\u0010;\u001a\u00020<H\u0016J\b\u0010*\u001a\u00020\u0006H\u0016J\b\u0010=\u001a\u000203H\u0016J\b\u0010,\u001a\u00020\rH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u000bR\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u00020\r8\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u000bR\u0016\u0010\u000f\u001a\u00020\r8\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u000bR\u001c\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00128\u0006@\u0006X\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00068\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u000bR\u0016\u0010\u0017\u001a\u00020\r8\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u000bR\u0016\u0010\u0019\u001a\u00020\u001a8\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001b\u0010\u000bR\u0016\u0010\u001c\u001a\u00020\u001a8\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001d\u0010\u000bR\u0016\u0010\u001e\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001f\u0010\u000bR\u0016\u0010 \u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b!\u0010\u000bR\u0014\u0010\"\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0018\u0010#\u001a\u0004\u0018\u00010\u00068\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b$\u0010\u000bR\u001e\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060&8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010'\u001a\u00020(8\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b)\u0010\u000bR\u0016\u0010*\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b+\u0010\u000bR\u0016\u0010,\u001a\u00020\r8\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b-\u0010\u000b¨\u0006@"}, d2 = {"Lcom/adsbynimbus/request/NimbusResponse;", "Lcom/adsbynimbus/NimbusAd;", "bid", "Lcom/adsbynimbus/openrtb/response/BidResponse;", "(Lcom/adsbynimbus/openrtb/response/BidResponse;)V", "auctionId", "", "getAuctionId", "()Ljava/lang/String;", "auction_id", "getAuction_id$annotations", "()V", "bid_in_cents", "", "getBid_in_cents$annotations", "bid_raw", "getBid_raw$annotations", "companionAds", "", "Lcom/adsbynimbus/render/CompanionAd;", "[Lcom/adsbynimbus/render/CompanionAd;", "content_type", "getContent_type$annotations", "height", "getHeight$annotations", "is_interstitial", "", "is_interstitial$annotations", "is_mraid", "is_mraid$annotations", "markup", "getMarkup$annotations", "network", "getNetwork$annotations", "placementIdOverride", "placement_id", "getPlacement_id$annotations", "renderInfoOverride", "", "trackers", "Lcom/adsbynimbus/request/NimbusResponse$Trackers;", "getTrackers$annotations", "type", "getType$annotations", "width", "getWidth$annotations", "bidInCents", "bidRaw", "", "()[Lcom/adsbynimbus/render/CompanionAd;", "isInterstitial", "", "isMraid", "placementId", "position", "renderInfo", "", "trackersForEvent", "", "event", "Lcom/adsbynimbus/render/AdEvent;", "useNewRenderer", "Listener", "Trackers", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NimbusResponse.kt */
public final class NimbusResponse implements NimbusAd {
    private final String auctionId;
    public final String auction_id;
    public final BidResponse bid;
    public final int bid_in_cents;
    public final int bid_raw;
    public transient CompanionAd[] companionAds;
    public final String content_type;
    public final int height;
    public final byte is_interstitial;
    public final byte is_mraid;
    public final String markup;
    public final String network;
    public transient String placementIdOverride;
    public final String placement_id;
    public transient Map<String, String> renderInfoOverride = new LinkedHashMap();
    public final Trackers trackers;
    public final String type;
    public final int width;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/request/NimbusResponse$Listener;", "", "onAdResponse", "", "nimbusResponse", "Lcom/adsbynimbus/request/NimbusResponse;", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NimbusResponse.kt */
    public interface Listener {
        void onAdResponse(NimbusResponse nimbusResponse);
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NimbusResponse.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.adsbynimbus.render.AdEvent[] r0 = com.adsbynimbus.render.AdEvent.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.IMPRESSION     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.CLICKED     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.request.NimbusResponse.WhenMappings.<clinit>():void");
        }
    }

    @Deprecated(message = "Field has been moved to the bid property", replaceWith = @ReplaceWith(expression = "bid.auction_id", imports = {}))
    public static /* synthetic */ void getAuction_id$annotations() {
    }

    @Deprecated(message = "Field has been moved to the bid property", replaceWith = @ReplaceWith(expression = "bid.bid_in_cents", imports = {}))
    public static /* synthetic */ void getBid_in_cents$annotations() {
    }

    @Deprecated(message = "Field has been moved to the bid property", replaceWith = @ReplaceWith(expression = "bid.bid_raw", imports = {}))
    public static /* synthetic */ void getBid_raw$annotations() {
    }

    @Deprecated(message = "Field has been moved to the bid property", replaceWith = @ReplaceWith(expression = "bid.content_type", imports = {}))
    public static /* synthetic */ void getContent_type$annotations() {
    }

    @Deprecated(message = "Field has been moved to the bid property", replaceWith = @ReplaceWith(expression = "bid.height", imports = {}))
    public static /* synthetic */ void getHeight$annotations() {
    }

    @Deprecated(message = "Field has been moved to the bid property", replaceWith = @ReplaceWith(expression = "bid.markup", imports = {}))
    public static /* synthetic */ void getMarkup$annotations() {
    }

    @Deprecated(message = "Field has been moved to the bid property", replaceWith = @ReplaceWith(expression = "bid.network", imports = {}))
    public static /* synthetic */ void getNetwork$annotations() {
    }

    @Deprecated(message = "Field has been moved to the bid property", replaceWith = @ReplaceWith(expression = "bid.placement_id", imports = {}))
    public static /* synthetic */ void getPlacement_id$annotations() {
    }

    @Deprecated(message = "Trackers can be accessed on the bid property", replaceWith = @ReplaceWith(expression = "bid", imports = {}))
    public static /* synthetic */ void getTrackers$annotations() {
    }

    @Deprecated(message = "Field has been moved to the bid property", replaceWith = @ReplaceWith(expression = "bid.type", imports = {}))
    public static /* synthetic */ void getType$annotations() {
    }

    @Deprecated(message = "Field has been moved to the bid property", replaceWith = @ReplaceWith(expression = "bid.width", imports = {}))
    public static /* synthetic */ void getWidth$annotations() {
    }

    @Deprecated(message = "Field has been moved to the bid property", replaceWith = @ReplaceWith(expression = "bid.is_interstitial", imports = {}))
    public static /* synthetic */ void is_interstitial$annotations() {
    }

    @Deprecated(message = "Field has been moved to the bid property", replaceWith = @ReplaceWith(expression = "bid.is_mraid", imports = {}))
    public static /* synthetic */ void is_mraid$annotations() {
    }

    public NimbusResponse(BidResponse bidResponse) {
        Intrinsics.checkNotNullParameter(bidResponse, "bid");
        this.bid = bidResponse;
        this.auctionId = bidResponse.auction_id;
        this.type = bidResponse.type;
        this.auction_id = bidResponse.auction_id;
        this.bid_in_cents = bidResponse.bid_in_cents;
        this.bid_raw = bidResponse.bid_in_cents;
        this.content_type = bidResponse.content_type;
        this.height = bidResponse.height;
        this.width = bidResponse.width;
        this.is_interstitial = bidResponse.is_interstitial;
        this.markup = bidResponse.markup;
        this.network = bidResponse.network;
        String[] strArr = null;
        BidResponse bidResponse2 = bidResponse.trackers.containsKey("impression_trackers") ? bidResponse : null;
        String[] impression_trackers = bidResponse2 != null ? bidResponse2.getImpression_trackers() : null;
        BidResponse bidResponse3 = bidResponse.trackers.containsKey("click_trackers") ? bidResponse : null;
        this.trackers = new Trackers(impression_trackers, bidResponse3 != null ? bidResponse3.getClick_trackers() : strArr);
        this.placement_id = bidResponse.placement_id;
        this.is_mraid = bidResponse.is_mraid;
    }

    public String getAuctionId() {
        return this.auctionId;
    }

    public String position() {
        return this.bid.position;
    }

    public String type() {
        return this.bid.type;
    }

    public String markup() {
        return this.bid.markup;
    }

    public String network() {
        return this.bid.network;
    }

    public String placementId() {
        String str = this.placementIdOverride;
        return str == null ? this.bid.placement_id : str;
    }

    public Map<String, String> renderInfo() {
        Map<String, String> map = this.renderInfoOverride;
        return map == null ? MapsKt.emptyMap() : map;
    }

    public int bidInCents() {
        return this.bid.bid_in_cents;
    }

    public float bidRaw() {
        return this.bid.bid_raw;
    }

    public int width() {
        return this.bid.width;
    }

    public int height() {
        return this.bid.height;
    }

    public boolean isInterstitial() {
        return this.bid.is_interstitial > 0;
    }

    public boolean isMraid() {
        return this.bid.is_mraid > 0;
    }

    public Collection<String> trackersForEvent(AdEvent adEvent) {
        Intrinsics.checkNotNullParameter(adEvent, NotificationCompat.CATEGORY_EVENT);
        int i = WhenMappings.$EnumSwitchMapping$0[adEvent.ordinal()];
        List list = null;
        if (i == 1) {
            String[] impression_trackers = this.bid.getImpression_trackers();
            if (impression_trackers != null) {
                list = ArraysKt.toList((T[]) impression_trackers);
            }
            return list;
        } else if (i != 2) {
            return null;
        } else {
            String[] click_trackers = this.bid.getClick_trackers();
            if (click_trackers != null) {
                list = ArraysKt.toList((T[]) click_trackers);
            }
            return list;
        }
    }

    public boolean useNewRenderer() {
        return this.bid.ext.use_new_renderer;
    }

    public CompanionAd[] companionAds() {
        return this.companionAds;
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u001a\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/adsbynimbus/request/NimbusResponse$Trackers;", "", "impression_trackers", "", "", "click_trackers", "([Ljava/lang/String;[Ljava/lang/String;)V", "[Ljava/lang/String;", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @Deprecated(message = "Exists for compatibility and should not be used")
    /* compiled from: NimbusResponse.kt */
    public static final class Trackers {
        public final String[] click_trackers;
        public final String[] impression_trackers;

        public Trackers(String[] strArr, String[] strArr2) {
            this.impression_trackers = strArr;
            this.click_trackers = strArr2;
        }
    }
}
