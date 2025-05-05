package com.adsbynimbus.render;

import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.render.ExoPlayerVideoPlayer$onIsPlayingChanged$3", f = "ExoPlayerVideoPlayer.kt", i = {0}, l = {185}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* compiled from: ExoPlayerVideoPlayer.kt */
final class ExoPlayerVideoPlayer$onIsPlayingChanged$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ExoPlayerVideoPlayer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExoPlayerVideoPlayer$onIsPlayingChanged$3(ExoPlayerVideoPlayer exoPlayerVideoPlayer, Continuation<? super ExoPlayerVideoPlayer$onIsPlayingChanged$3> continuation) {
        super(2, continuation);
        this.this$0 = exoPlayerVideoPlayer;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ExoPlayerVideoPlayer$onIsPlayingChanged$3 exoPlayerVideoPlayer$onIsPlayingChanged$3 = new ExoPlayerVideoPlayer$onIsPlayingChanged$3(this.this$0, continuation);
        exoPlayerVideoPlayer$onIsPlayingChanged$3.L$0 = obj;
        return exoPlayerVideoPlayer$onIsPlayingChanged$3;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExoPlayerVideoPlayer$onIsPlayingChanged$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            ExoPlayerVideoPlayer exoPlayerVideoPlayer = this.this$0;
            for (VideoAdPlayer.VideoAdPlayerCallback onAdProgress : this.this$0.callbacks) {
                onAdProgress.onAdProgress(exoPlayerVideoPlayer.getMediaInfo(), exoPlayerVideoPlayer.getAdProgress());
            }
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(200, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
