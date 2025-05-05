package com.adsbynimbus.openrtb.request.builders;

import androidx.media3.common.MimeTypes;
import com.adsbynimbus.openrtb.request.Banner;
import com.adsbynimbus.openrtb.request.Video;
import com.adsbynimbus.request.RequestExtensions;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00002\n\u0010\u0005\u001a\u00020\u0006\"\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u0012\u0010\u000e\u001a\u00020\u00002\n\u0010\u000e\u001a\u00020\u0006\"\u00020\u0007J\u001f\u0010\u000f\u001a\u00020\u00002\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u0010\"\u00020\u0011¢\u0006\u0002\u0010\u0012J\u0012\u0010\u0013\u001a\u00020\u00002\n\u0010\u0014\u001a\u00020\u0006\"\u00020\u0007J\u0016\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\fJ\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0007J\u001f\u0010\u0019\u001a\u00020\u00002\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001b0\u0010\"\u00020\u001b¢\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0007J\u0012\u0010\u001e\u001a\u00020\u00002\n\u0010\u001e\u001a\u00020\u0006\"\u00020\u0007J\u0016\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\fJ\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\fJ\u0012\u0010#\u001a\u00020\u00002\n\u0010#\u001a\u00020\u0006\"\u00020\u0007J\u000e\u0010$\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%J\u0016\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\fJ\u0010\u0010)\u001a\u00020\u00002\b\b\u0001\u0010)\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/adsbynimbus/openrtb/request/builders/AndroidVideoBuilder;", "", "video", "Lcom/adsbynimbus/openrtb/request/Video;", "(Lcom/adsbynimbus/openrtb/request/Video;)V", "apis", "", "", "bidFloor", "", "bitrate", "minBitrate", "", "maxBitrate", "companionAdTypes", "companionAds", "", "Lcom/adsbynimbus/openrtb/request/Banner;", "([Lcom/adsbynimbus/openrtb/request/Banner;)Lcom/adsbynimbus/openrtb/request/builders/AndroidVideoBuilder;", "deliveryMethod", "deliveryMethods", "duration", "minDuration", "maxDuration", "linearity", "mimes", "mimeTypes", "", "([Ljava/lang/String;)Lcom/adsbynimbus/openrtb/request/builders/AndroidVideoBuilder;", "placement", "playbackMethod", "playerSize", "width", "height", "position", "protocols", "rewarded", "", "skipEnabled", "skipMin", "skipAfter", "startDelay", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AndroidVideoBuilder.kt */
public final class AndroidVideoBuilder {
    private final Video video;

    public AndroidVideoBuilder(Video video2) {
        Intrinsics.checkNotNullParameter(video2, MimeTypes.BASE_TYPE_VIDEO);
        this.video = video2;
    }

    public final AndroidVideoBuilder position(int i) {
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.pos = (byte) i;
        return this;
    }

    public final AndroidVideoBuilder mimes(String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "mimeTypes");
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.mimes = (String[]) Arrays.copyOf(strArr, strArr.length);
        return this;
    }

    public final AndroidVideoBuilder bidFloor(float f) {
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.bidfloor = f;
        return this;
    }

    public final AndroidVideoBuilder duration(int i, int i2) {
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.minduration = i;
        this.video.maxduration = i2;
        return this;
    }

    public final AndroidVideoBuilder bitrate(int i, int i2) {
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.minbitrate = i;
        this.video.maxbitrate = i2;
        return this;
    }

    public final AndroidVideoBuilder playerSize(int i, int i2) {
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.w = i;
        this.video.h = i2;
        return this;
    }

    public final AndroidVideoBuilder protocols(byte... bArr) {
        Intrinsics.checkNotNullParameter(bArr, "protocols");
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.protocols = bArr;
        return this;
    }

    public final AndroidVideoBuilder skipEnabled(int i, int i2) {
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.skip = 1;
        this.video.skipmin = i;
        this.video.skipafter = i2;
        return this;
    }

    public final AndroidVideoBuilder startDelay(int i) {
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.startdelay = i;
        return this;
    }

    public final AndroidVideoBuilder playbackMethod(byte... bArr) {
        Intrinsics.checkNotNullParameter(bArr, "playbackMethod");
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.playbackmethod = bArr;
        return this;
    }

    public final AndroidVideoBuilder placement(byte b) {
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.placement = b;
        return this;
    }

    public final AndroidVideoBuilder linearity(byte b) {
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.linearity = b;
        return this;
    }

    public final AndroidVideoBuilder deliveryMethod(byte... bArr) {
        Intrinsics.checkNotNullParameter(bArr, "deliveryMethods");
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.delivery = bArr;
        return this;
    }

    public final AndroidVideoBuilder apis(byte... bArr) {
        Intrinsics.checkNotNullParameter(bArr, "apis");
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.api = bArr;
        return this;
    }

    public final AndroidVideoBuilder companionAds(Banner... bannerArr) {
        Intrinsics.checkNotNullParameter(bannerArr, "companionAds");
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.companionad = (Banner[]) Arrays.copyOf(bannerArr, bannerArr.length);
        return this;
    }

    public final AndroidVideoBuilder companionAdTypes(byte... bArr) {
        Intrinsics.checkNotNullParameter(bArr, "companionAdTypes");
        AndroidVideoBuilder androidVideoBuilder = this;
        this.video.companiontype = bArr;
        return this;
    }

    public final AndroidVideoBuilder rewarded(boolean z) {
        AndroidVideoBuilder androidVideoBuilder = this;
        Video video2 = this.video;
        video2.ext.put("is_rewarded", Byte.valueOf(RequestExtensions.getByteValue(z)));
        return this;
    }
}
