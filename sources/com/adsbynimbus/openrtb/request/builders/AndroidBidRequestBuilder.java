package com.adsbynimbus.openrtb.request.builders;

import com.adsbynimbus.openrtb.request.App;
import com.adsbynimbus.openrtb.request.Banner;
import com.adsbynimbus.openrtb.request.BidRequest;
import com.adsbynimbus.openrtb.request.Data;
import com.adsbynimbus.openrtb.request.Impression;
import com.adsbynimbus.openrtb.request.Publisher;
import com.adsbynimbus.openrtb.request.Regs;
import com.adsbynimbus.openrtb.request.Source;
import com.adsbynimbus.openrtb.request.User;
import com.adsbynimbus.openrtb.request.Video;
import com.adsbynimbus.openrtb.request.builders.AndroidImpressionBuilder;
import com.adsbynimbus.openrtb.request.builders.AndroidRegsBuilder;
import com.adsbynimbus.openrtb.request.builders.AndroidSourceBuilder;
import com.adsbynimbus.request.RequestExtensions;
import io.sentry.SentryBaseEvent;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0011\u001a\u00020\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u0013J\u001f\u0010\u0014\u001a\u00020\u00002\u0012\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u0016\"\u00020\u0017¢\u0006\u0002\u0010\u0018J\u0006\u0010\u0007\u001a\u00020\u0001J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\u0002J\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0017J\u0006\u0010\u001a\u001a\u00020\u0003J\u0010\u0010\u001a\u001a\u00020\u00002\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\"J\u0010\u0010!\u001a\u00020\u00002\b\u0010!\u001a\u0004\u0018\u00010#R\u0014\u0010\u0007\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006$"}, d2 = {"Lcom/adsbynimbus/openrtb/request/builders/AndroidBidRequestBuilder;", "Lcom/adsbynimbus/openrtb/request/builders/AndroidImpressionBuilder;", "Lcom/adsbynimbus/openrtb/request/builders/AndroidRegsBuilder;", "Lcom/adsbynimbus/openrtb/request/builders/AndroidSourceBuilder;", "request", "Lcom/adsbynimbus/openrtb/request/BidRequest;", "(Lcom/adsbynimbus/openrtb/request/BidRequest;)V", "impression", "Lcom/adsbynimbus/openrtb/request/Impression;", "getImpression", "()Lcom/adsbynimbus/openrtb/request/Impression;", "regs", "Lcom/adsbynimbus/openrtb/request/Regs;", "getRegs", "()Lcom/adsbynimbus/openrtb/request/Regs;", "getRequest", "()Lcom/adsbynimbus/openrtb/request/BidRequest;", "app", "Lcom/adsbynimbus/openrtb/request/builders/AndroidAppBuilder;", "Lcom/adsbynimbus/openrtb/request/App;", "blockedDomains", "domains", "", "", "([Ljava/lang/String;)Lcom/adsbynimbus/openrtb/request/builders/AndroidBidRequestBuilder;", "sessionId", "source", "Lcom/adsbynimbus/openrtb/request/Source;", "test", "enabled", "", "timeout", "", "user", "Lcom/adsbynimbus/openrtb/request/builders/AndroidUserBuilder;", "Lcom/adsbynimbus/openrtb/request/User;", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AndroidBidRequestBuilder.kt */
public final class AndroidBidRequestBuilder implements AndroidImpressionBuilder, AndroidRegsBuilder, AndroidSourceBuilder {
    private final BidRequest request;

    public AndroidBidRequestBuilder(BidRequest bidRequest) {
        Intrinsics.checkNotNullParameter(bidRequest, SentryBaseEvent.JsonKeys.REQUEST);
        this.request = bidRequest;
    }

    public AndroidImpressionBuilder apsParams(Collection<? extends Map<String, ? extends List<String>>> collection) {
        return AndroidImpressionBuilder.DefaultImpls.apsParams(this, collection);
    }

    public AndroidBannerBuilder banner() {
        return AndroidImpressionBuilder.DefaultImpls.banner(this);
    }

    public AndroidImpressionBuilder banner(Banner banner) {
        return AndroidImpressionBuilder.DefaultImpls.banner(this, banner);
    }

    public AndroidRegsBuilder ccpa(String str) {
        return AndroidRegsBuilder.DefaultImpls.ccpa(this, str);
    }

    public AndroidRegsBuilder coppa(boolean z) {
        return AndroidRegsBuilder.DefaultImpls.coppa(this, z);
    }

    public AndroidImpressionBuilder facebookAppId(String str) {
        return AndroidImpressionBuilder.DefaultImpls.facebookAppId(this, str);
    }

    public AndroidImpressionBuilder facebookTestAdType(String str) {
        return AndroidImpressionBuilder.DefaultImpls.facebookTestAdType(this, str);
    }

    public AndroidRegsBuilder gdpr(boolean z) {
        return AndroidRegsBuilder.DefaultImpls.gdpr(this, z);
    }

    public BidRequest getRequest() {
        return this.request;
    }

    public AndroidImpressionBuilder interstitial(boolean z) {
        return AndroidImpressionBuilder.DefaultImpls.interstitial(this, z);
    }

    public AndroidSourceBuilder omSdk(String str, String str2) {
        return AndroidSourceBuilder.DefaultImpls.omSdk(this, str, str2);
    }

    public AndroidImpressionBuilder secure(boolean z) {
        return AndroidImpressionBuilder.DefaultImpls.secure(this, z);
    }

    public AndroidImpressionBuilder video(Video video) {
        return AndroidImpressionBuilder.DefaultImpls.video(this, video);
    }

    public AndroidVideoBuilder video() {
        return AndroidImpressionBuilder.DefaultImpls.video(this);
    }

    public Impression getImpression() {
        return getRequest().imp[0];
    }

    public Regs getRegs() {
        Regs regs = getRequest().regs;
        if (regs != null) {
            return regs;
        }
        Regs regs2 = new Regs((byte) 0, (Regs.Extension) null, 3, (DefaultConstructorMarker) null);
        getRequest().regs = regs2;
        return regs2;
    }

    public final AndroidBidRequestBuilder impression(Impression impression) {
        Intrinsics.checkNotNullParameter(impression, "impression");
        AndroidBidRequestBuilder androidBidRequestBuilder = this;
        Impression[] impressionArr = {impression};
        getRequest().imp = impressionArr;
        return this;
    }

    public final AndroidImpressionBuilder impression() {
        return this;
    }

    public final AndroidBidRequestBuilder app(App app) {
        AndroidBidRequestBuilder androidBidRequestBuilder = this;
        getRequest().app = app;
        return this;
    }

    public final AndroidAppBuilder app() {
        App app = getRequest().app;
        if (app == null) {
            app = new App((String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String[]) null, (String[]) null, (String[]) null, (Byte) null, (Byte) null, (Publisher) null, 4095, (DefaultConstructorMarker) null);
            getRequest().app = app;
        }
        return new AndroidAppBuilder(app);
    }

    public final AndroidRegsBuilder regs() {
        return this;
    }

    public final AndroidSourceBuilder source() {
        return this;
    }

    public final AndroidUserBuilder user() {
        User user = getRequest().user;
        if (user == null) {
            user = new User(0, (String) null, 0, (String) null, (String) null, (String) null, (Data[]) null, (User.Extension) null, 255, (DefaultConstructorMarker) null);
            getRequest().user = user;
        }
        return new AndroidUserBuilder(user);
    }

    public final AndroidBidRequestBuilder user(User user) {
        AndroidBidRequestBuilder androidBidRequestBuilder = this;
        getRequest().user = user;
        return this;
    }

    public final AndroidBidRequestBuilder test(boolean z) {
        AndroidBidRequestBuilder androidBidRequestBuilder = this;
        getRequest().test = RequestExtensions.getByteValue(z);
        return this;
    }

    public final AndroidBidRequestBuilder timeout(int i) {
        AndroidBidRequestBuilder androidBidRequestBuilder = this;
        getRequest().tmax = i;
        return this;
    }

    public final AndroidBidRequestBuilder regs(Regs regs) {
        AndroidBidRequestBuilder androidBidRequestBuilder = this;
        BidRequest request2 = getRequest();
        if (regs == null) {
            regs = new Regs((byte) 0, (Regs.Extension) null, 3, (DefaultConstructorMarker) null);
        }
        request2.regs = regs;
        return this;
    }

    public final AndroidBidRequestBuilder source(Source source) {
        AndroidBidRequestBuilder androidBidRequestBuilder = this;
        getRequest().source = source;
        return this;
    }

    public final AndroidBidRequestBuilder blockedDomains(String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "domains");
        AndroidBidRequestBuilder androidBidRequestBuilder = this;
        getRequest().badv = (String[]) Arrays.copyOf(strArr, strArr.length);
        return this;
    }

    public final AndroidBidRequestBuilder sessionId(String str) {
        Intrinsics.checkNotNullParameter(str, "sessionId");
        AndroidBidRequestBuilder androidBidRequestBuilder = this;
        getRequest().ext.put("session_id", str);
        return this;
    }
}
