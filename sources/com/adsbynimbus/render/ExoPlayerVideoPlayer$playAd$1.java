package com.adsbynimbus.render;

import android.content.Context;
import androidx.media3.exoplayer.ExoPlayer;
import com.adsbynimbus.render.VideoAdRenderer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.render.ExoPlayerVideoPlayer$playAd$1", f = "ExoPlayerVideoPlayer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ExoPlayerVideoPlayer.kt */
final class ExoPlayerVideoPlayer$playAd$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ ExoPlayerVideoPlayer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExoPlayerVideoPlayer$playAd$1(ExoPlayerVideoPlayer exoPlayerVideoPlayer, Continuation<? super ExoPlayerVideoPlayer$playAd$1> continuation) {
        super(2, continuation);
        this.this$0 = exoPlayerVideoPlayer;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExoPlayerVideoPlayer$playAd$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExoPlayerVideoPlayer$playAd$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.textureView.setVisibility(0);
            ExoPlayerVideoPlayer exoPlayerVideoPlayer = this.this$0;
            VideoAdRenderer.PlayerProvider playerProvider = exoPlayerVideoPlayer.provider;
            Context context = this.this$0.textureView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "textureView.context");
            ExoPlayer acquirePlayer = playerProvider.acquirePlayer(context);
            ExoPlayerVideoPlayer exoPlayerVideoPlayer2 = this.this$0;
            acquirePlayer.addListener(exoPlayerVideoPlayer2);
            acquirePlayer.setVolume(((float) exoPlayerVideoPlayer2.volume()) * 0.01f);
            if (!Intrinsics.areEqual((Object) acquirePlayer.getCurrentMediaItem(), (Object) exoPlayerVideoPlayer2.getMediaItem())) {
                acquirePlayer.setVideoTextureView(exoPlayerVideoPlayer2.textureView);
                acquirePlayer.setMediaItem(exoPlayerVideoPlayer2.getMediaItem());
                acquirePlayer.setRepeatMode(0);
                if (exoPlayerVideoPlayer2.getPosition() > 0) {
                    acquirePlayer.seekTo(exoPlayerVideoPlayer2.getPosition());
                }
                acquirePlayer.prepare();
            }
            acquirePlayer.play();
            exoPlayerVideoPlayer.setExoPlayer(acquirePlayer);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
