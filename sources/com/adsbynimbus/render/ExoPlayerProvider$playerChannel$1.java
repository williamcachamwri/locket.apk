package com.adsbynimbus.render;

import androidx.media3.exoplayer.ExoPlayer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroidx/media3/exoplayer/ExoPlayer;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExoPlayerVideoPlayer.kt */
final class ExoPlayerProvider$playerChannel$1 extends Lambda implements Function1<ExoPlayer, Unit> {
    public static final ExoPlayerProvider$playerChannel$1 INSTANCE = new ExoPlayerProvider$playerChannel$1();

    ExoPlayerProvider$playerChannel$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ExoPlayer) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ExoPlayer exoPlayer) {
        Intrinsics.checkNotNullParameter(exoPlayer, "it");
        exoPlayer.release();
    }
}
