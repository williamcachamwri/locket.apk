package com.adsbynimbus.openrtb.request.builders;

import com.adsbynimbus.openrtb.request.App;
import com.adsbynimbus.openrtb.request.Publisher;
import com.google.firebase.dynamiclinks.DynamicLink;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001f\u0010\u0007\u001a\u00020\u00002\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\tJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/adsbynimbus/openrtb/request/builders/AndroidPublisherBuilder;", "", "app", "Lcom/adsbynimbus/openrtb/request/App;", "(Lcom/adsbynimbus/openrtb/request/App;)V", "getApp", "()Lcom/adsbynimbus/openrtb/request/App;", "categories", "", "", "([Ljava/lang/String;)Lcom/adsbynimbus/openrtb/request/builders/AndroidPublisherBuilder;", "domain", "name", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AndroidPublisherBuilder.kt */
public final class AndroidPublisherBuilder {
    private final App app;

    public AndroidPublisherBuilder(App app2) {
        Intrinsics.checkNotNullParameter(app2, io.sentry.protocol.App.TYPE);
        this.app = app2;
    }

    public final App getApp() {
        return this.app;
    }

    public final AndroidPublisherBuilder categories(String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "categories");
        AndroidPublisherBuilder androidPublisherBuilder = this;
        App app2 = this.app;
        Publisher publisher = this.app.publisher;
        String str = null;
        String str2 = publisher != null ? publisher.name : null;
        Publisher publisher2 = this.app.publisher;
        if (publisher2 != null) {
            str = publisher2.domain;
        }
        app2.publisher = new Publisher(str2, str, (String[]) Arrays.copyOf(strArr, strArr.length));
        return this;
    }

    public final AndroidPublisherBuilder name(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        AndroidPublisherBuilder androidPublisherBuilder = this;
        App app2 = this.app;
        Publisher publisher = this.app.publisher;
        String[] strArr = null;
        String str2 = publisher != null ? publisher.domain : null;
        Publisher publisher2 = this.app.publisher;
        if (publisher2 != null) {
            strArr = publisher2.cat;
        }
        app2.publisher = new Publisher(str, str2, strArr);
        return this;
    }

    public final AndroidPublisherBuilder domain(String str) {
        Intrinsics.checkNotNullParameter(str, DynamicLink.Builder.KEY_DOMAIN);
        AndroidPublisherBuilder androidPublisherBuilder = this;
        App app2 = this.app;
        Publisher publisher = this.app.publisher;
        String[] strArr = null;
        String str2 = publisher != null ? publisher.name : null;
        Publisher publisher2 = this.app.publisher;
        if (publisher2 != null) {
            strArr = publisher2.cat;
        }
        app2.publisher = new Publisher(str2, str, strArr);
        return this;
    }
}
