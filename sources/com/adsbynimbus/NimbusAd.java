package com.adsbynimbus;

import androidx.core.app.NotificationCompat;
import com.adsbynimbus.render.AdEvent;
import com.adsbynimbus.render.CompanionAd;
import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010$\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u0015\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u0007H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0003H&J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010\u0015\u001a\u00020\u0003H\u0016J\u0014\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017H\u0016J\u0018\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0003H&J\b\u0010\u001d\u001a\u00020\u0010H\u0016J\b\u0010\u001e\u001a\u00020\u0007H\u0016R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u001f"}, d2 = {"Lcom/adsbynimbus/NimbusAd;", "", "auctionId", "", "getAuctionId", "()Ljava/lang/String;", "bidInCents", "", "bidRaw", "", "companionAds", "", "Lcom/adsbynimbus/render/CompanionAd;", "()[Lcom/adsbynimbus/render/CompanionAd;", "height", "isInterstitial", "", "isMraid", "markup", "network", "placementId", "position", "renderInfo", "", "trackersForEvent", "", "event", "Lcom/adsbynimbus/render/AdEvent;", "type", "useNewRenderer", "width", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NimbusAd.kt */
public interface NimbusAd {
    int bidInCents();

    float bidRaw();

    CompanionAd[] companionAds();

    String getAuctionId();

    int height();

    boolean isInterstitial();

    boolean isMraid();

    String markup();

    String network();

    String placementId();

    String position();

    Map<String, String> renderInfo();

    Collection<String> trackersForEvent(AdEvent adEvent);

    String type();

    boolean useNewRenderer();

    int width();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NimbusAd.kt */
    public static final class DefaultImpls {
        public static int bidInCents(NimbusAd nimbusAd) {
            return 0;
        }

        public static float bidRaw(NimbusAd nimbusAd) {
            return 0.0f;
        }

        public static CompanionAd[] companionAds(NimbusAd nimbusAd) {
            return null;
        }

        public static String getAuctionId(NimbusAd nimbusAd) {
            return "";
        }

        public static int height(NimbusAd nimbusAd) {
            return 0;
        }

        public static boolean isInterstitial(NimbusAd nimbusAd) {
            return false;
        }

        public static boolean isMraid(NimbusAd nimbusAd) {
            return false;
        }

        public static String network(NimbusAd nimbusAd) {
            return "";
        }

        public static String placementId(NimbusAd nimbusAd) {
            return null;
        }

        public static String position(NimbusAd nimbusAd) {
            return "";
        }

        public static Collection<String> trackersForEvent(NimbusAd nimbusAd, AdEvent adEvent) {
            Intrinsics.checkNotNullParameter(adEvent, NotificationCompat.CATEGORY_EVENT);
            return null;
        }

        public static boolean useNewRenderer(NimbusAd nimbusAd) {
            return false;
        }

        public static int width(NimbusAd nimbusAd) {
            return 0;
        }

        public static Map<String, String> renderInfo(NimbusAd nimbusAd) {
            return MapsKt.emptyMap();
        }
    }
}
