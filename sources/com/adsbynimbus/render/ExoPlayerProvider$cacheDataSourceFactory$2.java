package com.adsbynimbus.render;

import androidx.media3.database.StandaloneDatabaseProvider;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor;
import androidx.media3.datasource.cache.SimpleCache;
import com.adsbynimbus.Nimbus;
import com.adsbynimbus.internal.PlatformKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/media3/datasource/cache/CacheDataSource$Factory;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExoPlayerVideoPlayer.kt */
final class ExoPlayerProvider$cacheDataSourceFactory$2 extends Lambda implements Function0<CacheDataSource.Factory> {
    public static final ExoPlayerProvider$cacheDataSourceFactory$2 INSTANCE = new ExoPlayerProvider$cacheDataSourceFactory$2();

    ExoPlayerProvider$cacheDataSourceFactory$2() {
        super(0);
    }

    public final CacheDataSource.Factory invoke() {
        CacheDataSource.Factory upstreamDataSourceFactory = new CacheDataSource.Factory().setUpstreamDataSourceFactory(new DefaultHttpDataSource.Factory().setUserAgent(Nimbus.getUserAgent()));
        Nimbus nimbus = Nimbus.INSTANCE;
        Nimbus nimbus2 = Nimbus.INSTANCE;
        CacheDataSource.Factory flags = upstreamDataSourceFactory.setCache(new SimpleCache(new File(PlatformKt.getApplication().getCacheDir(), "nimbus-video-cache"), new LeastRecentlyUsedCacheEvictor(31457280), new StandaloneDatabaseProvider(PlatformKt.getApplication()))).setFlags(2);
        Intrinsics.checkNotNullExpressionValue(flags, "Factory()\n            .s…AG_IGNORE_CACHE_ON_ERROR)");
        return flags;
    }
}
