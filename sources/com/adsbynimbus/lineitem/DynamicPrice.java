package com.adsbynimbus.lineitem;

import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import com.adsbynimbus.Nimbus;
import com.adsbynimbus.request.NimbusResponse;
import com.google.logging.type.LogSeverity;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\u001a \u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u000f*\u00020\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0015\u0010\n\u001a\u00020\u0003*\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"BID_KEY", "", "DEFAULT_BANNER", "Lcom/adsbynimbus/lineitem/Mapping;", "DEFAULT_FULLSCREEN", "DURATION_KEY", "ID_KEY", "NETWORK_KEY", "SIZE_KEY", "VIDEO_BID_KEY", "defaultMapping", "Lcom/adsbynimbus/request/NimbusResponse;", "getDefaultMapping", "(Lcom/adsbynimbus/request/NimbusResponse;)Lcom/adsbynimbus/lineitem/Mapping;", "targetingMap", "", "mapping", "request_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: DynamicPrice.kt */
public final class DynamicPrice {
    public static final String BID_KEY = "na_bid";
    public static final Mapping DEFAULT_BANNER = new LinearPriceMapping(new LinearPriceGranularity(0, 300, 1), new LinearPriceGranularity(300, LogSeverity.EMERGENCY_VALUE, 5), new LinearPriceGranularity(LogSeverity.EMERGENCY_VALUE, 2000, 50), new LinearPriceGranularity(2000, 3500, 100));
    public static final Mapping DEFAULT_FULLSCREEN = new LinearPriceMapping(new LinearPriceGranularity(0, 3500, 5), new LinearPriceGranularity(3500, PlaybackException.ERROR_CODE_DRM_UNSPECIFIED, 100));
    public static final String DURATION_KEY = "na_duration";
    public static final String ID_KEY = "na_id";
    public static final String NETWORK_KEY = "na_network";
    public static final String SIZE_KEY = "na_size";
    public static final String VIDEO_BID_KEY = "na_bid_video";

    public static final Mapping getDefaultMapping(NimbusResponse nimbusResponse) {
        Intrinsics.checkNotNullParameter(nimbusResponse, "<this>");
        return nimbusResponse.isInterstitial() ? DEFAULT_FULLSCREEN : DEFAULT_BANNER;
    }

    public static /* synthetic */ Map targetingMap$default(NimbusResponse nimbusResponse, Mapping mapping, int i, Object obj) {
        if ((i & 1) != 0) {
            mapping = getDefaultMapping(nimbusResponse);
        }
        return targetingMap(nimbusResponse, mapping);
    }

    public static final Map<String, String> targetingMap(NimbusResponse nimbusResponse, Mapping mapping) {
        Map map;
        Intrinsics.checkNotNullParameter(nimbusResponse, "<this>");
        Intrinsics.checkNotNullParameter(mapping, "mapping");
        Map mapOf = MapsKt.mapOf(TuplesKt.to(ID_KEY, nimbusResponse.bid.auction_id), TuplesKt.to(SIZE_KEY, new StringBuilder().append(nimbusResponse.bid.width).append('x').append(nimbusResponse.bid.height).toString()), TuplesKt.to(NETWORK_KEY, nimbusResponse.bid.network));
        String str = "0";
        String str2 = null;
        if (Intrinsics.areEqual((Object) nimbusResponse.bid.type, (Object) MimeTypes.BASE_TYPE_VIDEO)) {
            Pair[] pairArr = new Pair[2];
            String target = mapping.getTarget(nimbusResponse);
            if (!Nimbus.testMode) {
                str2 = target;
            }
            if (str2 != null) {
                str = str2;
            }
            pairArr[0] = TuplesKt.to(VIDEO_BID_KEY, str);
            pairArr[1] = TuplesKt.to(DURATION_KEY, String.valueOf(nimbusResponse.bid.duration));
            map = MapsKt.mapOf(pairArr);
        } else {
            String target2 = mapping.getTarget(nimbusResponse);
            if (!Nimbus.testMode) {
                str2 = target2;
            }
            if (str2 != null) {
                str = str2;
            }
            map = MapsKt.mapOf(TuplesKt.to(BID_KEY, str));
        }
        return MapsKt.plus(mapOf, map);
    }
}
