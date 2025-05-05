package com.adsbynimbus.openrtb.request.builders;

import com.adsbynimbus.openrtb.request.App;
import com.adsbynimbus.openrtb.request.Publisher;
import com.adsbynimbus.request.RequestExtensions;
import com.google.firebase.dynamiclinks.DynamicLink;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u001f\u0010\t\u001a\u00020\u00002\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\n\"\u00020\b¢\u0006\u0002\u0010\u000bJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\bJ\u001f\u0010\u000e\u001a\u00020\u00002\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\n\"\u00020\b¢\u0006\u0002\u0010\u000bJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010J\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0014J\u001f\u0010\u0015\u001a\u00020\u00002\u0012\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\n\"\u00020\b¢\u0006\u0002\u0010\u000bJ\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\bJ\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0018"}, d2 = {"Lcom/adsbynimbus/openrtb/request/builders/AndroidAppBuilder;", "", "app", "Lcom/adsbynimbus/openrtb/request/App;", "(Lcom/adsbynimbus/openrtb/request/App;)V", "getApp", "()Lcom/adsbynimbus/openrtb/request/App;", "bundle", "", "categories", "", "([Ljava/lang/String;)Lcom/adsbynimbus/openrtb/request/builders/AndroidAppBuilder;", "domain", "name", "pageCategories", "paid", "", "privacyPolicy", "publisher", "Lcom/adsbynimbus/openrtb/request/builders/AndroidPublisherBuilder;", "Lcom/adsbynimbus/openrtb/request/Publisher;", "sectionCategories", "storeUrl", "version", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(message = "This class is deprecated in favor of modifying the App object directly")
/* compiled from: AndroidAppBuilder.kt */
public final class AndroidAppBuilder {
    private final App app;

    public AndroidAppBuilder(App app2) {
        Intrinsics.checkNotNullParameter(app2, io.sentry.protocol.App.TYPE);
        this.app = app2;
    }

    public final App getApp() {
        return this.app;
    }

    public final AndroidPublisherBuilder publisher() {
        return new AndroidPublisherBuilder(this.app);
    }

    public final AndroidAppBuilder name(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        AndroidAppBuilder androidAppBuilder = this;
        this.app.name = str;
        return this;
    }

    public final AndroidAppBuilder bundle(String str) {
        Intrinsics.checkNotNullParameter(str, "bundle");
        AndroidAppBuilder androidAppBuilder = this;
        this.app.bundle = str;
        return this;
    }

    public final AndroidAppBuilder domain(String str) {
        Intrinsics.checkNotNullParameter(str, DynamicLink.Builder.KEY_DOMAIN);
        AndroidAppBuilder androidAppBuilder = this;
        this.app.domain = str;
        return this;
    }

    public final AndroidAppBuilder storeUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "storeUrl");
        AndroidAppBuilder androidAppBuilder = this;
        this.app.storeurl = str;
        return this;
    }

    public final AndroidAppBuilder categories(String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "categories");
        AndroidAppBuilder androidAppBuilder = this;
        this.app.cat = (String[]) Arrays.copyOf(strArr, strArr.length);
        return this;
    }

    public final AndroidAppBuilder paid(boolean z) {
        AndroidAppBuilder androidAppBuilder = this;
        this.app.paid = Byte.valueOf(RequestExtensions.getByteValue(z));
        return this;
    }

    public final AndroidAppBuilder publisher(Publisher publisher) {
        Intrinsics.checkNotNullParameter(publisher, "publisher");
        AndroidAppBuilder androidAppBuilder = this;
        this.app.publisher = publisher;
        return this;
    }

    public final AndroidAppBuilder pageCategories(String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "pageCategories");
        AndroidAppBuilder androidAppBuilder = this;
        this.app.pagecat = (String[]) Arrays.copyOf(strArr, strArr.length);
        return this;
    }

    public final AndroidAppBuilder sectionCategories(String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "sectionCategories");
        AndroidAppBuilder androidAppBuilder = this;
        this.app.sectioncat = (String[]) Arrays.copyOf(strArr, strArr.length);
        return this;
    }

    public final AndroidAppBuilder privacyPolicy(boolean z) {
        AndroidAppBuilder androidAppBuilder = this;
        this.app.privacypolicy = Byte.valueOf(RequestExtensions.getByteValue(z));
        return this;
    }

    public final AndroidAppBuilder version(String str) {
        Intrinsics.checkNotNullParameter(str, "version");
        AndroidAppBuilder androidAppBuilder = this;
        this.app.ver = str;
        return this;
    }
}
