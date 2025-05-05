package com.adsbynimbus.render;

import android.content.Context;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import com.google.android.gms.common.ConnectionResult;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Landroidx/media3/exoplayer/ExoPlayer;", "context", "Landroid/content/Context;", "factory", "Landroidx/media3/exoplayer/source/DefaultMediaSourceFactory;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExoPlayerVideoPlayer.kt */
final class ExoPlayerProvider$playerFactory$1 extends Lambda implements Function2<Context, DefaultMediaSourceFactory, ExoPlayer> {
    public static final ExoPlayerProvider$playerFactory$1 INSTANCE = new ExoPlayerProvider$playerFactory$1();

    ExoPlayerProvider$playerFactory$1() {
        super(2);
    }

    public final ExoPlayer invoke(Context context, DefaultMediaSourceFactory defaultMediaSourceFactory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(defaultMediaSourceFactory, "factory");
        ExoPlayer.Builder mediaSourceFactory = new ExoPlayer.Builder(context.getApplicationContext()).setMediaSourceFactory(defaultMediaSourceFactory);
        DefaultLoadControl.Builder builder = new DefaultLoadControl.Builder();
        builder.setBufferDurationsMs(7500, 60000, ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED, 7500);
        ExoPlayer build = mediaSourceFactory.setLoadControl(builder.build()).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(context.applicat…  })\n            .build()");
        return build;
    }
}
