package com.adsbynimbus.render;

import com.adsbynimbus.render.VideoAdRenderer;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
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
import kotlinx.coroutines.CoroutineScopeKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.render.ExoPlayerVideoPlayer$loadAd$1", f = "ExoPlayerVideoPlayer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ExoPlayerVideoPlayer.kt */
final class ExoPlayerVideoPlayer$loadAd$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ExoPlayerVideoPlayer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExoPlayerVideoPlayer$loadAd$1(ExoPlayerVideoPlayer exoPlayerVideoPlayer, Continuation<? super ExoPlayerVideoPlayer$loadAd$1> continuation) {
        super(2, continuation);
        this.this$0 = exoPlayerVideoPlayer;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ExoPlayerVideoPlayer$loadAd$1 exoPlayerVideoPlayer$loadAd$1 = new ExoPlayerVideoPlayer$loadAd$1(this.this$0, continuation);
        exoPlayerVideoPlayer$loadAd$1.L$0 = obj;
        return exoPlayerVideoPlayer$loadAd$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExoPlayerVideoPlayer$loadAd$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            VideoAdRenderer.PlayerProvider playerProvider = this.this$0.provider;
            String url = this.this$0.getMediaInfo().getUrl();
            Intrinsics.checkNotNullExpressionValue(url, "mediaInfo.url");
            playerProvider.cacheVideo(url);
            if (CoroutineScopeKt.isActive((CoroutineScope) this.L$0) && this.this$0.isLoading()) {
                ExoPlayerVideoPlayer exoPlayerVideoPlayer = this.this$0;
                for (VideoAdPlayer.VideoAdPlayerCallback onLoaded : this.this$0.callbacks) {
                    onLoaded.onLoaded(exoPlayerVideoPlayer.getMediaInfo());
                }
                Unit unit = Unit.INSTANCE;
                this.this$0.setLoading(false);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
