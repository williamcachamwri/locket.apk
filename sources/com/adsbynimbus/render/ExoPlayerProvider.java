package com.adsbynimbus.render;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.datasource.cache.CacheWriter;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import com.adsbynimbus.internal.Logger;
import com.adsbynimbus.render.VideoAdRenderer;
import java.io.InterruptedIOException;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ChannelsKt;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0016H\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0016H\u0016J\u0010\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u0011H\u0016J\u0010\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020\u001eH\u0017J\u0010\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8FX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R,\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00110\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006+"}, d2 = {"Lcom/adsbynimbus/render/ExoPlayerProvider;", "Lcom/adsbynimbus/render/VideoAdRenderer$PlayerProvider;", "Landroid/content/ComponentCallbacks2;", "()V", "cacheDataSourceFactory", "Landroidx/media3/datasource/cache/CacheDataSource$Factory;", "getCacheDataSourceFactory", "()Landroidx/media3/datasource/cache/CacheDataSource$Factory;", "cacheDataSourceFactory$delegate", "Lkotlin/Lazy;", "defaultMediaSourceFactory", "Landroidx/media3/exoplayer/source/DefaultMediaSourceFactory;", "getDefaultMediaSourceFactory", "()Landroidx/media3/exoplayer/source/DefaultMediaSourceFactory;", "defaultMediaSourceFactory$delegate", "playerChannel", "Lkotlinx/coroutines/channels/Channel;", "Landroidx/media3/exoplayer/ExoPlayer;", "getPlayerChannel", "()Lkotlinx/coroutines/channels/Channel;", "playerFactory", "Lkotlin/Function2;", "Landroid/content/Context;", "getPlayerFactory", "()Lkotlin/jvm/functions/Function2;", "setPlayerFactory", "(Lkotlin/jvm/functions/Function2;)V", "acquirePlayer", "context", "cacheVideo", "", "url", "", "createPlayer", "offerPlayer", "player", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onLowMemory", "onTrimMemory", "level", "", "video_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExoPlayerVideoPlayer.kt */
public final class ExoPlayerProvider implements VideoAdRenderer.PlayerProvider, ComponentCallbacks2 {
    public static final ExoPlayerProvider INSTANCE = new ExoPlayerProvider();
    private static final Lazy cacheDataSourceFactory$delegate = LazyKt.lazy(ExoPlayerProvider$cacheDataSourceFactory$2.INSTANCE);
    private static final Lazy defaultMediaSourceFactory$delegate = LazyKt.lazy(ExoPlayerProvider$defaultMediaSourceFactory$2.INSTANCE);
    private static final Channel<ExoPlayer> playerChannel = ChannelKt.Channel(1, BufferOverflow.DROP_LATEST, ExoPlayerProvider$playerChannel$1.INSTANCE);
    private static Function2<? super Context, ? super DefaultMediaSourceFactory, ? extends ExoPlayer> playerFactory = ExoPlayerProvider$playerFactory$1.INSTANCE;

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
    }

    private ExoPlayerProvider() {
    }

    public final CacheDataSource.Factory getCacheDataSourceFactory() {
        return (CacheDataSource.Factory) cacheDataSourceFactory$delegate.getValue();
    }

    public final DefaultMediaSourceFactory getDefaultMediaSourceFactory() {
        return (DefaultMediaSourceFactory) defaultMediaSourceFactory$delegate.getValue();
    }

    public final Channel<ExoPlayer> getPlayerChannel() {
        return playerChannel;
    }

    public final Function2<Context, DefaultMediaSourceFactory, ExoPlayer> getPlayerFactory() {
        return playerFactory;
    }

    public final void setPlayerFactory(Function2<? super Context, ? super DefaultMediaSourceFactory, ? extends ExoPlayer> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        playerFactory = function2;
    }

    public void cacheVideo(String str) {
        Object obj;
        Intrinsics.checkNotNullParameter(str, "url");
        try {
            Result.Companion companion = Result.Companion;
            ExoPlayerProvider exoPlayerProvider = this;
            new CacheWriter(getCacheDataSourceFactory().createDataSource(), new DataSpec.Builder().setUri(str).setFlags(4).build(), (byte[]) null, (CacheWriter.ProgressListener) null).cache();
            obj = Result.m2444constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        Throwable r4 = Result.m2447exceptionOrNullimpl(obj);
        if (r4 != null && !(r4 instanceof InterruptedIOException)) {
            Logger.log(3, "Unable to preload video");
        }
    }

    public ExoPlayer createPlayer(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (ExoPlayer) playerFactory.invoke(context, getDefaultMediaSourceFactory());
    }

    public ExoPlayer acquirePlayer(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object r0 = playerChannel.m1825tryReceivePtdJZtk();
        if (r0 instanceof ChannelResult.Failed) {
            ChannelResult.m1805exceptionOrNullimpl(r0);
            r0 = INSTANCE.createPlayer(context);
        }
        return (ExoPlayer) r0;
    }

    public void offerPlayer(ExoPlayer exoPlayer) {
        Intrinsics.checkNotNullParameter(exoPlayer, "player");
        Object trySendBlocking = ChannelsKt.trySendBlocking(playerChannel, exoPlayer);
        if (trySendBlocking instanceof ChannelResult.Failed) {
            ChannelResult.m1805exceptionOrNullimpl(trySendBlocking);
            exoPlayer.release();
        }
    }

    @Deprecated(message = "Deprecated in Java")
    public void onLowMemory() {
        ExoPlayer exoPlayer;
        for (int i = 0; i < 2; i++) {
            Channel<ExoPlayer> channel = playerChannel;
            if (!channel.isEmpty() && (exoPlayer = (ExoPlayer) ChannelResult.m1806getOrNullimpl(channel.m1825tryReceivePtdJZtk())) != null) {
                exoPlayer.release();
            }
        }
    }

    public void onTrimMemory(int i) {
        onLowMemory();
    }
}
