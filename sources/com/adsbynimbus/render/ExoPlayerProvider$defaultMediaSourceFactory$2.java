package com.adsbynimbus.render;

import androidx.media3.datasource.DataSource;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/media3/exoplayer/source/DefaultMediaSourceFactory;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExoPlayerVideoPlayer.kt */
final class ExoPlayerProvider$defaultMediaSourceFactory$2 extends Lambda implements Function0<DefaultMediaSourceFactory> {
    public static final ExoPlayerProvider$defaultMediaSourceFactory$2 INSTANCE = new ExoPlayerProvider$defaultMediaSourceFactory$2();

    ExoPlayerProvider$defaultMediaSourceFactory$2() {
        super(0);
    }

    public final DefaultMediaSourceFactory invoke() {
        return new DefaultMediaSourceFactory((DataSource.Factory) ExoPlayerProvider.INSTANCE.getCacheDataSourceFactory());
    }
}
