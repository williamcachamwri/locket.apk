package com.adsbynimbus.render.internal;

import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Ljava/net/HttpURLConnection;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AdTrackers.kt */
final class AdTrackersKt$trackEvent$1 extends Lambda implements Function1<String, HttpURLConnection> {
    public static final AdTrackersKt$trackEvent$1 INSTANCE = new AdTrackersKt$trackEvent$1();

    AdTrackersKt$trackEvent$1() {
        super(1);
    }

    public final HttpURLConnection invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        URLConnection uRLConnection = (URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection());
        Intrinsics.checkNotNull(uRLConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        return (HttpURLConnection) uRLConnection;
    }
}
