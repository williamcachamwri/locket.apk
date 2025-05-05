package com.brentvatne.exoplayer;

import android.content.Context;
import androidx.media3.database.StandaloneDatabaseProvider;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor;
import androidx.media3.datasource.cache.SimpleCache;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/brentvatne/exoplayer/RNVSimpleCache;", "", "()V", "simpleCache", "Landroidx/media3/datasource/cache/SimpleCache;", "getCacheFactory", "Landroidx/media3/datasource/DataSource$Factory;", "factory", "Landroidx/media3/datasource/HttpDataSource$Factory;", "setSimpleCache", "", "context", "Landroid/content/Context;", "cacheSize", "", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ReactExoplayerSimpleCache.kt */
public final class RNVSimpleCache {
    public static final RNVSimpleCache INSTANCE = new RNVSimpleCache();
    private static SimpleCache simpleCache;

    private RNVSimpleCache() {
    }

    public final void setSimpleCache(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (simpleCache == null && i > 0) {
            long j = (long) 1024;
            simpleCache = new SimpleCache(new File(context.getCacheDir(), "RNVCache"), new LeastRecentlyUsedCacheEvictor(((long) i) * j * j), new StandaloneDatabaseProvider(context));
        }
    }

    public final DataSource.Factory getCacheFactory(HttpDataSource.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        if (simpleCache == null) {
            return factory;
        }
        CacheDataSource.Factory factory2 = new CacheDataSource.Factory();
        SimpleCache simpleCache2 = simpleCache;
        Intrinsics.checkNotNull(simpleCache2);
        CacheDataSource.Factory upstreamDataSourceFactory = factory2.setCache(simpleCache2).setUpstreamDataSourceFactory(factory);
        Intrinsics.checkNotNullExpressionValue(upstreamDataSourceFactory, "setUpstreamDataSourceFactory(...)");
        return upstreamDataSourceFactory;
    }
}
