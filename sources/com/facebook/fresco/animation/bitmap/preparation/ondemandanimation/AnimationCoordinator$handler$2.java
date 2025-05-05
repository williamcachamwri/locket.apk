package com.facebook.fresco.animation.bitmap.preparation.ondemandanimation;

import android.os.Handler;
import android.os.HandlerThread;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/os/Handler;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AnimationCoordinator.kt */
final class AnimationCoordinator$handler$2 extends Lambda implements Function0<Handler> {
    public static final AnimationCoordinator$handler$2 INSTANCE = new AnimationCoordinator$handler$2();

    AnimationCoordinator$handler$2() {
        super(0);
    }

    public final Handler invoke() {
        HandlerThread handlerThread = new HandlerThread("FrescoAnimationWorker");
        handlerThread.start();
        return new Handler(handlerThread.getLooper());
    }
}
