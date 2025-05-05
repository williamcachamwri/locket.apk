package com.brentvatne.react;

import com.brentvatne.exoplayer.ReactExoplayerView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/brentvatne/exoplayer/ReactExoplayerView;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoManagerModule.kt */
final class VideoManagerModule$setFullScreenCmd$1 extends Lambda implements Function1<ReactExoplayerView, Unit> {
    final /* synthetic */ boolean $fullScreen;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoManagerModule$setFullScreenCmd$1(boolean z) {
        super(1);
        this.$fullScreen = z;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ReactExoplayerView) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ReactExoplayerView reactExoplayerView) {
        if (reactExoplayerView != null) {
            reactExoplayerView.setFullscreen(this.$fullScreen);
        }
    }
}
