package com.adsbynimbus.request.internal;

import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.openrtb.request.EID;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0016\u0010\u0005\u001a\u00020\u0006*\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0005\u0010\b¨\u0006\t"}, d2 = {"globalExtendedIds", "", "Lcom/adsbynimbus/openrtb/request/EID;", "getGlobalExtendedIds", "()Ljava/util/Set;", "isRewarded", "", "Lcom/adsbynimbus/NimbusAd;", "(Lcom/adsbynimbus/NimbusAd;)Z", "request_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: RequestExtensions.kt */
public final class RequestExtensionsKt {
    private static final Set<EID> globalExtendedIds = new LinkedHashSet();

    public static final Set<EID> getGlobalExtendedIds() {
        return globalExtendedIds;
    }

    public static final boolean isRewarded(NimbusAd nimbusAd) {
        Intrinsics.checkNotNullParameter(nimbusAd, "<this>");
        return Boolean.parseBoolean(nimbusAd.renderInfo().get("is_rewarded"));
    }
}
