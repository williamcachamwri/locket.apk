package com.adsbynimbus.openrtb.request.builders;

import com.adsbynimbus.openrtb.request.Geo;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.sentry.protocol.User;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\bJ\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\fJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/adsbynimbus/openrtb/request/builders/AndroidGeoBuilder;", "", "geo", "Lcom/adsbynimbus/openrtb/request/Geo;", "(Lcom/adsbynimbus/openrtb/request/Geo;)V", "accuracy", "", "city", "", "country", "countryCode", "latitude", "", "locationType", "", "longitude", "metro", "state", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AndroidGeoBuilder.kt */
public final class AndroidGeoBuilder {
    private final Geo geo;

    public AndroidGeoBuilder(Geo geo2) {
        Intrinsics.checkNotNullParameter(geo2, User.JsonKeys.GEO);
        this.geo = geo2;
    }

    public final AndroidGeoBuilder latitude(float f) {
        AndroidGeoBuilder androidGeoBuilder = this;
        this.geo.lat = Float.valueOf(f);
        return this;
    }

    public final AndroidGeoBuilder country(String str) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE);
        AndroidGeoBuilder androidGeoBuilder = this;
        this.geo.country = str;
        return this;
    }

    public final AndroidGeoBuilder longitude(float f) {
        AndroidGeoBuilder androidGeoBuilder = this;
        this.geo.lon = Float.valueOf(f);
        return this;
    }

    public final AndroidGeoBuilder accuracy(int i) {
        AndroidGeoBuilder androidGeoBuilder = this;
        this.geo.accuracy = Integer.valueOf(i);
        return this;
    }

    public final AndroidGeoBuilder locationType(byte b) {
        AndroidGeoBuilder androidGeoBuilder = this;
        this.geo.type = Byte.valueOf(b);
        return this;
    }

    public final AndroidGeoBuilder city(String str) {
        AndroidGeoBuilder androidGeoBuilder = this;
        this.geo.city = str;
        return this;
    }

    public final AndroidGeoBuilder metro(String str) {
        Intrinsics.checkNotNullParameter(str, "metro");
        AndroidGeoBuilder androidGeoBuilder = this;
        this.geo.metro = str;
        return this;
    }

    public final AndroidGeoBuilder state(String str) {
        Intrinsics.checkNotNullParameter(str, "state");
        AndroidGeoBuilder androidGeoBuilder = this;
        this.geo.state = str;
        return this;
    }
}
