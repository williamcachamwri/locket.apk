package com.brentvatne.react;

import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoManagerModule$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ VideoManagerModule f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Function1 f$2;

    public /* synthetic */ VideoManagerModule$$ExternalSyntheticLambda0(VideoManagerModule videoManagerModule, int i, Function1 function1) {
        this.f$0 = videoManagerModule;
        this.f$1 = i;
        this.f$2 = function1;
    }

    public final void run() {
        VideoManagerModule.performOnPlayerView$lambda$0(this.f$0, this.f$1, this.f$2);
    }
}
