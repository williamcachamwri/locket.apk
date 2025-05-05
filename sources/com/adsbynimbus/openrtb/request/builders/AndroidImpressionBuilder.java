package com.adsbynimbus.openrtb.request.builders;

import com.adsbynimbus.openrtb.request.Banner;
import com.adsbynimbus.openrtb.request.Format;
import com.adsbynimbus.openrtb.request.Impression;
import com.adsbynimbus.openrtb.request.Video;
import com.adsbynimbus.request.RequestExtensions;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0006\u001a\u00020\u00002\u001e\u0010\u0006\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\n0\b0\u0007H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\u000b\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0010\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\tH\u0016J\u0010\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0014\u001a\u00020\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0016H\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0017"}, d2 = {"Lcom/adsbynimbus/openrtb/request/builders/AndroidImpressionBuilder;", "", "impression", "Lcom/adsbynimbus/openrtb/request/Impression;", "getImpression", "()Lcom/adsbynimbus/openrtb/request/Impression;", "apsParams", "", "", "", "", "banner", "Lcom/adsbynimbus/openrtb/request/builders/AndroidBannerBuilder;", "Lcom/adsbynimbus/openrtb/request/Banner;", "facebookAppId", "facebookTestAdType", "interstitial", "instl", "", "secure", "video", "Lcom/adsbynimbus/openrtb/request/builders/AndroidVideoBuilder;", "Lcom/adsbynimbus/openrtb/request/Video;", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AndroidImpressionBuilder.kt */
public interface AndroidImpressionBuilder {
    AndroidImpressionBuilder apsParams(Collection<? extends Map<String, ? extends List<String>>> collection);

    AndroidBannerBuilder banner();

    AndroidImpressionBuilder banner(Banner banner);

    AndroidImpressionBuilder facebookAppId(String str);

    AndroidImpressionBuilder facebookTestAdType(String str);

    Impression getImpression();

    AndroidImpressionBuilder interstitial(boolean z);

    AndroidImpressionBuilder secure(boolean z);

    AndroidImpressionBuilder video(Video video);

    AndroidVideoBuilder video();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AndroidImpressionBuilder.kt */
    public static final class DefaultImpls {
        public static AndroidBannerBuilder banner(AndroidImpressionBuilder androidImpressionBuilder) {
            Banner banner = androidImpressionBuilder.getImpression().banner;
            if (banner == null) {
                banner = new Banner(0, 0, (Format[]) null, 0.0f, (byte[]) null, (byte) 0, (byte[]) null, (Byte) null, 252, (DefaultConstructorMarker) null);
                androidImpressionBuilder.getImpression().banner = banner;
            }
            return new AndroidBannerBuilder(banner);
        }

        public static AndroidVideoBuilder video(AndroidImpressionBuilder androidImpressionBuilder) {
            Video video = androidImpressionBuilder.getImpression().video;
            if (video == null) {
                video = new Video(0.0f, (String[]) null, 0, 0, (byte[]) null, 0, 0, 0, (byte) 0, (byte) 0, (byte) 0, (byte[]) null, 0, 0, 0, 0, (byte) 0, (byte[]) null, (byte[]) null, (Banner[]) null, (byte[]) null, (Map) null, 4194303, (DefaultConstructorMarker) null);
                androidImpressionBuilder.getImpression().video = video;
            }
            return new AndroidVideoBuilder(video);
        }

        public static AndroidImpressionBuilder banner(AndroidImpressionBuilder androidImpressionBuilder, Banner banner) {
            androidImpressionBuilder.getImpression().banner = banner;
            return androidImpressionBuilder;
        }

        public static AndroidImpressionBuilder video(AndroidImpressionBuilder androidImpressionBuilder, Video video) {
            androidImpressionBuilder.getImpression().video = video;
            return androidImpressionBuilder;
        }

        public static AndroidImpressionBuilder interstitial(AndroidImpressionBuilder androidImpressionBuilder, boolean z) {
            androidImpressionBuilder.getImpression().instl = RequestExtensions.getByteValue(z);
            return androidImpressionBuilder;
        }

        public static AndroidImpressionBuilder secure(AndroidImpressionBuilder androidImpressionBuilder, boolean z) {
            androidImpressionBuilder.getImpression().secure = RequestExtensions.getByteValue(z);
            return androidImpressionBuilder;
        }

        public static AndroidImpressionBuilder facebookAppId(AndroidImpressionBuilder androidImpressionBuilder, String str) {
            Intrinsics.checkNotNullParameter(str, "facebookAppId");
            androidImpressionBuilder.getImpression().ext.facebook_app_id = str;
            return androidImpressionBuilder;
        }

        public static AndroidImpressionBuilder facebookTestAdType(AndroidImpressionBuilder androidImpressionBuilder, String str) {
            Intrinsics.checkNotNullParameter(str, "facebookTestAdType");
            androidImpressionBuilder.getImpression().ext.facebook_test_ad_type = str;
            return androidImpressionBuilder;
        }

        public static AndroidImpressionBuilder apsParams(AndroidImpressionBuilder androidImpressionBuilder, Collection<? extends Map<String, ? extends List<String>>> collection) {
            Intrinsics.checkNotNullParameter(collection, "apsParams");
            androidImpressionBuilder.getImpression().ext.aps = CollectionsKt.toSet(collection);
            return androidImpressionBuilder;
        }
    }
}
