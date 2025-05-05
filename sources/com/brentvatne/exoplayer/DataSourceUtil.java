package com.brentvatne.exoplayer;

import android.net.Uri;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.AssetDataSource;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.okhttp.OkHttpDataSource;
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.network.CookieJarContainer;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.google.common.net.HttpHeaders;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CookieJar;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0007J0\u0010\u000e\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012H\u0002J0\u0010\u0013\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012H\u0002J0\u0010\u0014\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012H\u0007J0\u0010\u0015\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012H\u0007J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/brentvatne/exoplayer/DataSourceUtil;", "", "()V", "defaultDataSourceFactory", "Landroidx/media3/datasource/DataSource$Factory;", "defaultHttpDataSourceFactory", "Landroidx/media3/datasource/HttpDataSource$Factory;", "userAgent", "", "buildAssetDataSourceFactory", "context", "Lcom/facebook/react/bridge/ReactContext;", "srcUri", "Landroid/net/Uri;", "buildDataSourceFactory", "bandwidthMeter", "Landroidx/media3/exoplayer/upstream/DefaultBandwidthMeter;", "requestHeaders", "", "buildHttpDataSourceFactory", "getDefaultDataSourceFactory", "getDefaultHttpDataSourceFactory", "getUserAgent", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DataSourceUtil.kt */
public final class DataSourceUtil {
    public static final DataSourceUtil INSTANCE = new DataSourceUtil();
    private static DataSource.Factory defaultDataSourceFactory;
    private static HttpDataSource.Factory defaultHttpDataSourceFactory;
    private static String userAgent;

    private DataSourceUtil() {
    }

    private final String getUserAgent(ReactContext reactContext) {
        if (userAgent == null) {
            userAgent = Util.getUserAgent(reactContext, reactContext.getPackageName());
        }
        String str = userAgent;
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        if ((r3 == null || r3.isEmpty()) == false) goto L_0x0017;
     */
    @kotlin.jvm.JvmStatic
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final androidx.media3.datasource.DataSource.Factory getDefaultDataSourceFactory(com.facebook.react.bridge.ReactContext r1, androidx.media3.exoplayer.upstream.DefaultBandwidthMeter r2, java.util.Map<java.lang.String, java.lang.String> r3) {
        /*
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            androidx.media3.datasource.DataSource$Factory r0 = defaultDataSourceFactory
            if (r0 == 0) goto L_0x0017
            if (r3 == 0) goto L_0x0014
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            r0 = 0
            goto L_0x0015
        L_0x0014:
            r0 = 1
        L_0x0015:
            if (r0 != 0) goto L_0x001f
        L_0x0017:
            com.brentvatne.exoplayer.DataSourceUtil r0 = INSTANCE
            androidx.media3.datasource.DataSource$Factory r1 = r0.buildDataSourceFactory(r1, r2, r3)
            defaultDataSourceFactory = r1
        L_0x001f:
            androidx.media3.datasource.DataSource$Factory r1 = defaultDataSourceFactory
            java.lang.String r2 = "null cannot be cast to non-null type androidx.media3.datasource.DataSource.Factory"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brentvatne.exoplayer.DataSourceUtil.getDefaultDataSourceFactory(com.facebook.react.bridge.ReactContext, androidx.media3.exoplayer.upstream.DefaultBandwidthMeter, java.util.Map):androidx.media3.datasource.DataSource$Factory");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        if ((r3 == null || r3.isEmpty()) == false) goto L_0x0017;
     */
    @kotlin.jvm.JvmStatic
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final androidx.media3.datasource.HttpDataSource.Factory getDefaultHttpDataSourceFactory(com.facebook.react.bridge.ReactContext r1, androidx.media3.exoplayer.upstream.DefaultBandwidthMeter r2, java.util.Map<java.lang.String, java.lang.String> r3) {
        /*
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            androidx.media3.datasource.HttpDataSource$Factory r0 = defaultHttpDataSourceFactory
            if (r0 == 0) goto L_0x0017
            if (r3 == 0) goto L_0x0014
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            r0 = 0
            goto L_0x0015
        L_0x0014:
            r0 = 1
        L_0x0015:
            if (r0 != 0) goto L_0x001f
        L_0x0017:
            com.brentvatne.exoplayer.DataSourceUtil r0 = INSTANCE
            androidx.media3.datasource.HttpDataSource$Factory r1 = r0.buildHttpDataSourceFactory(r1, r2, r3)
            defaultHttpDataSourceFactory = r1
        L_0x001f:
            androidx.media3.datasource.HttpDataSource$Factory r1 = defaultHttpDataSourceFactory
            java.lang.String r2 = "null cannot be cast to non-null type androidx.media3.datasource.HttpDataSource.Factory"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brentvatne.exoplayer.DataSourceUtil.getDefaultHttpDataSourceFactory(com.facebook.react.bridge.ReactContext, androidx.media3.exoplayer.upstream.DefaultBandwidthMeter, java.util.Map):androidx.media3.datasource.HttpDataSource$Factory");
    }

    private final DataSource.Factory buildDataSourceFactory(ReactContext reactContext, DefaultBandwidthMeter defaultBandwidthMeter, Map<String, String> map) {
        return new DefaultDataSource.Factory(reactContext, buildHttpDataSourceFactory(reactContext, defaultBandwidthMeter, map));
    }

    private final HttpDataSource.Factory buildHttpDataSourceFactory(ReactContext reactContext, DefaultBandwidthMeter defaultBandwidthMeter, Map<String, String> map) {
        OkHttpClient okHttpClient = OkHttpClientProvider.getOkHttpClient();
        CookieJar cookieJar = okHttpClient.cookieJar();
        Intrinsics.checkNotNull(cookieJar, "null cannot be cast to non-null type com.facebook.react.modules.network.CookieJarContainer");
        ((CookieJarContainer) cookieJar).setCookieJar(new JavaNetCookieJar(new ForwardingCookieHandler(reactContext)));
        Intrinsics.checkNotNull(okHttpClient, "null cannot be cast to non-null type okhttp3.Call.Factory");
        OkHttpDataSource.Factory transferListener = new OkHttpDataSource.Factory(okHttpClient).setTransferListener(defaultBandwidthMeter);
        Intrinsics.checkNotNullExpressionValue(transferListener, "setTransferListener(...)");
        if (map != null) {
            transferListener.setDefaultRequestProperties((Map) map);
            if (!map.containsKey(HttpHeaders.USER_AGENT)) {
                transferListener.setUserAgent(getUserAgent(reactContext));
            }
        } else {
            transferListener.setUserAgent(getUserAgent(reactContext));
        }
        return transferListener;
    }

    @JvmStatic
    public static final DataSource.Factory buildAssetDataSourceFactory(ReactContext reactContext, Uri uri) {
        Intrinsics.checkNotNull(uri);
        DataSpec dataSpec = new DataSpec(uri);
        Intrinsics.checkNotNull(reactContext);
        AssetDataSource assetDataSource = new AssetDataSource(reactContext);
        assetDataSource.open(dataSpec);
        return new DataSourceUtil$$ExternalSyntheticLambda0(assetDataSource);
    }

    /* access modifiers changed from: private */
    public static final DataSource buildAssetDataSourceFactory$lambda$0(AssetDataSource assetDataSource) {
        Intrinsics.checkNotNullParameter(assetDataSource, "$rawResourceDataSource");
        return assetDataSource;
    }
}
