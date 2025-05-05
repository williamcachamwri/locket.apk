package com.facebook.fresco.animation.bitmap.preparation.ondemandanimation;

import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/UnusedFrameLoader;", "", "frameLoader", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameLoader;", "insertedTime", "Ljava/util/Date;", "(Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameLoader;Ljava/util/Date;)V", "getFrameLoader", "()Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameLoader;", "getInsertedTime", "()Ljava/util/Date;", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AnimationLoaderFactory.kt */
final class UnusedFrameLoader {
    private final FrameLoader frameLoader;
    private final Date insertedTime;

    public UnusedFrameLoader(FrameLoader frameLoader2, Date date) {
        Intrinsics.checkNotNullParameter(frameLoader2, "frameLoader");
        Intrinsics.checkNotNullParameter(date, "insertedTime");
        this.frameLoader = frameLoader2;
        this.insertedTime = date;
    }

    public final FrameLoader getFrameLoader() {
        return this.frameLoader;
    }

    public final Date getInsertedTime() {
        return this.insertedTime;
    }
}
