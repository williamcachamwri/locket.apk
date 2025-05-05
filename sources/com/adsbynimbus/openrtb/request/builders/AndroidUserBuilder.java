package com.adsbynimbus.openrtb.request.builders;

import androidx.autofill.HintConstants;
import com.adsbynimbus.openrtb.request.Data;
import com.adsbynimbus.openrtb.request.User;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\bJ\u001f\u0010\n\u001a\u00020\u00002\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000b\"\u00020\f¢\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\bJ\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\bJ\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\bJ\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/adsbynimbus/openrtb/request/builders/AndroidUserBuilder;", "", "user", "Lcom/adsbynimbus/openrtb/request/User;", "(Lcom/adsbynimbus/openrtb/request/User;)V", "age", "", "buyerUid", "", "customData", "data", "", "Lcom/adsbynimbus/openrtb/request/Data;", "([Lcom/adsbynimbus/openrtb/request/Data;)Lcom/adsbynimbus/openrtb/request/builders/AndroidUserBuilder;", "gdprConsentString", "consentString", "gdprDidConsent", "didConsent", "", "gender", "keywords", "unityBuyerId", "token", "yearOfBirth", "yob", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AndroidUserBuilder.kt */
public final class AndroidUserBuilder {
    private final User user;

    public AndroidUserBuilder(User user2) {
        Intrinsics.checkNotNullParameter(user2, "user");
        this.user = user2;
    }

    public final AndroidUserBuilder age(int i) {
        AndroidUserBuilder androidUserBuilder = this;
        this.user.age = i;
        return this;
    }

    public final AndroidUserBuilder buyerUid(String str) {
        Intrinsics.checkNotNullParameter(str, "buyerUid");
        AndroidUserBuilder androidUserBuilder = this;
        this.user.buyeruid = str;
        return this;
    }

    public final AndroidUserBuilder yearOfBirth(int i) {
        AndroidUserBuilder androidUserBuilder = this;
        this.user.yob = i;
        return this;
    }

    public final AndroidUserBuilder gender(String str) {
        Intrinsics.checkNotNullParameter(str, HintConstants.AUTOFILL_HINT_GENDER);
        AndroidUserBuilder androidUserBuilder = this;
        this.user.gender = str;
        return this;
    }

    public final AndroidUserBuilder keywords(String str) {
        Intrinsics.checkNotNullParameter(str, "keywords");
        AndroidUserBuilder androidUserBuilder = this;
        this.user.keywords = str;
        return this;
    }

    public final AndroidUserBuilder customData(String str) {
        Intrinsics.checkNotNullParameter(str, "customData");
        AndroidUserBuilder androidUserBuilder = this;
        this.user.custom_data = str;
        return this;
    }

    public final AndroidUserBuilder data(Data... dataArr) {
        Intrinsics.checkNotNullParameter(dataArr, "data");
        AndroidUserBuilder androidUserBuilder = this;
        this.user.data = (Data[]) Arrays.copyOf(dataArr, dataArr.length);
        return this;
    }

    public final AndroidUserBuilder gdprConsentString(String str) {
        Intrinsics.checkNotNullParameter(str, "consentString");
        AndroidUserBuilder androidUserBuilder = this;
        User user2 = this.user;
        User.Extension extension = this.user.ext;
        user2.ext = new User.Extension(str, extension != null ? extension.unity_buyeruid : null, (String) null, (String) null, (String) null, (Set) null, (Map) null, (Map) null, 252, (DefaultConstructorMarker) null);
        return this;
    }

    public final AndroidUserBuilder gdprDidConsent(boolean z) {
        AndroidUserBuilder androidUserBuilder = this;
        User user2 = this.user;
        User.Extension extension = this.user.ext;
        String str = extension != null ? extension.consent : null;
        User.Extension extension2 = this.user.ext;
        user2.ext = new User.Extension(str, extension2 != null ? extension2.unity_buyeruid : null, (String) null, (String) null, (String) null, (Set) null, (Map) null, (Map) null, 252, (DefaultConstructorMarker) null);
        return this;
    }

    public final AndroidUserBuilder unityBuyerId(String str) {
        Intrinsics.checkNotNullParameter(str, "token");
        AndroidUserBuilder androidUserBuilder = this;
        User user2 = this.user;
        User.Extension extension = this.user.ext;
        user2.ext = new User.Extension(extension != null ? extension.consent : null, str, (String) null, (String) null, (String) null, (Set) null, (Map) null, (Map) null, 252, (DefaultConstructorMarker) null);
        return this;
    }
}
