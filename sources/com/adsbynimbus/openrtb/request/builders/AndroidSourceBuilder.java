package com.adsbynimbus.openrtb.request.builders;

import com.adsbynimbus.openrtb.request.BidRequest;
import com.adsbynimbus.openrtb.request.Source;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\n"}, d2 = {"Lcom/adsbynimbus/openrtb/request/builders/AndroidSourceBuilder;", "", "request", "Lcom/adsbynimbus/openrtb/request/BidRequest;", "getRequest", "()Lcom/adsbynimbus/openrtb/request/BidRequest;", "omSdk", "partnerName", "", "sdkVersion", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AndroidSourceBuilder.kt */
public interface AndroidSourceBuilder {
    BidRequest getRequest();

    AndroidSourceBuilder omSdk(String str, String str2);

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AndroidSourceBuilder.kt */
    public static final class DefaultImpls {
        public static AndroidSourceBuilder omSdk(AndroidSourceBuilder androidSourceBuilder, String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "partnerName");
            Intrinsics.checkNotNullParameter(str2, RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
            BidRequest request = androidSourceBuilder.getRequest();
            Source source = new Source((Map) null, 1, (DefaultConstructorMarker) null);
            source.getExt().put("omidpn", str);
            source.getExt().put("omidpv", str2);
            request.source = source;
            return androidSourceBuilder;
        }
    }
}
