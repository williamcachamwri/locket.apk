package com.adsbynimbus.openrtb.request.builders;

import com.adsbynimbus.openrtb.request.Banner;
import com.adsbynimbus.openrtb.request.Format;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00002\n\u0010\u0005\u001a\u00020\u0006\"\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u0012\u0010\n\u001a\u00020\u00002\n\u0010\u000b\u001a\u00020\u0006\"\u00020\u0007J\u001f\u0010\f\u001a\u00020\u00002\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r\"\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0007J\u0016\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/adsbynimbus/openrtb/request/builders/AndroidBannerBuilder;", "", "banner", "Lcom/adsbynimbus/openrtb/request/Banner;", "(Lcom/adsbynimbus/openrtb/request/Banner;)V", "apis", "", "", "bidFloor", "", "blockedAttributes", "battr", "format", "", "Lcom/adsbynimbus/openrtb/request/Format;", "([Lcom/adsbynimbus/openrtb/request/Format;)Lcom/adsbynimbus/openrtb/request/builders/AndroidBannerBuilder;", "position", "size", "width", "", "height", "vcm", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AndroidBannerBuilder.kt */
public final class AndroidBannerBuilder {
    private final Banner banner;

    public AndroidBannerBuilder(Banner banner2) {
        Intrinsics.checkNotNullParameter(banner2, "banner");
        this.banner = banner2;
    }

    public final AndroidBannerBuilder format(Format... formatArr) {
        Intrinsics.checkNotNullParameter(formatArr, "format");
        AndroidBannerBuilder androidBannerBuilder = this;
        this.banner.format = (Format[]) Arrays.copyOf(formatArr, formatArr.length);
        return this;
    }

    public final AndroidBannerBuilder size(int i, int i2) {
        AndroidBannerBuilder androidBannerBuilder = this;
        this.banner.w = i;
        this.banner.h = i2;
        return this;
    }

    public final AndroidBannerBuilder position(byte b) {
        AndroidBannerBuilder androidBannerBuilder = this;
        this.banner.pos = b;
        return this;
    }

    public final AndroidBannerBuilder bidFloor(float f) {
        AndroidBannerBuilder androidBannerBuilder = this;
        this.banner.bidfloor = f;
        return this;
    }

    public final AndroidBannerBuilder apis(byte... bArr) {
        Intrinsics.checkNotNullParameter(bArr, "apis");
        AndroidBannerBuilder androidBannerBuilder = this;
        this.banner.api = bArr;
        return this;
    }

    public final AndroidBannerBuilder blockedAttributes(byte... bArr) {
        Intrinsics.checkNotNullParameter(bArr, "battr");
        AndroidBannerBuilder androidBannerBuilder = this;
        this.banner.battr = bArr;
        return this;
    }

    public final AndroidBannerBuilder vcm(byte b) {
        AndroidBannerBuilder androidBannerBuilder = this;
        this.banner.vcm = Byte.valueOf(b);
        return this;
    }
}
