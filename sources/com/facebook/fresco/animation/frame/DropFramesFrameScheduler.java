package com.facebook.fresco.animation.frame;

import com.facebook.fresco.animation.backend.AnimationInformation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0006H\u0007J\b\u0010\r\u001a\u00020\u0006H\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\bH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/facebook/fresco/animation/frame/DropFramesFrameScheduler;", "Lcom/facebook/fresco/animation/frame/FrameScheduler;", "animationInformation", "Lcom/facebook/fresco/animation/backend/AnimationInformation;", "(Lcom/facebook/fresco/animation/backend/AnimationInformation;)V", "_loopDurationMs", "", "getFrameNumberToRender", "", "animationTimeMs", "lastFrameTimeMs", "getFrameNumberWithinLoop", "timeInCurrentLoopMs", "getLoopDurationMs", "getTargetRenderTimeForNextFrameMs", "getTargetRenderTimeMs", "frameNumber", "isInfiniteAnimation", "", "Companion", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DropFramesFrameScheduler.kt */
public final class DropFramesFrameScheduler implements FrameScheduler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int UNSET = -1;
    private long _loopDurationMs = -1;
    private final AnimationInformation animationInformation;

    public DropFramesFrameScheduler(AnimationInformation animationInformation2) {
        Intrinsics.checkNotNullParameter(animationInformation2, "animationInformation");
        this.animationInformation = animationInformation2;
    }

    public int getFrameNumberToRender(long j, long j2) {
        long loopDurationMs = getLoopDurationMs();
        if (loopDurationMs == 0) {
            return getFrameNumberWithinLoop(0);
        }
        if (isInfiniteAnimation() || j / loopDurationMs < ((long) this.animationInformation.getLoopCount())) {
            return getFrameNumberWithinLoop(j % loopDurationMs);
        }
        return -1;
    }

    public long getLoopDurationMs() {
        long j = this._loopDurationMs;
        if (j != -1) {
            return j;
        }
        this._loopDurationMs = 0;
        int frameCount = this.animationInformation.getFrameCount();
        for (int i = 0; i < frameCount; i++) {
            this._loopDurationMs += (long) this.animationInformation.getFrameDurationMs(i);
        }
        return this._loopDurationMs;
    }

    public long getTargetRenderTimeMs(int i) {
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j += (long) this.animationInformation.getFrameDurationMs(i);
        }
        return j;
    }

    public long getTargetRenderTimeForNextFrameMs(long j) {
        long loopDurationMs = getLoopDurationMs();
        long j2 = 0;
        if (loopDurationMs == 0) {
            return -1;
        }
        if (!isInfiniteAnimation() && j / loopDurationMs >= ((long) this.animationInformation.getLoopCount())) {
            return -1;
        }
        long j3 = j % loopDurationMs;
        int frameCount = this.animationInformation.getFrameCount();
        for (int i = 0; i < frameCount && j2 <= j3; i++) {
            j2 += (long) this.animationInformation.getFrameDurationMs(i);
        }
        return j + (j2 - j3);
    }

    public boolean isInfiniteAnimation() {
        return this.animationInformation.getLoopCount() == 0;
    }

    public final int getFrameNumberWithinLoop(long j) {
        int i = 0;
        long j2 = 0;
        do {
            j2 += (long) this.animationInformation.getFrameDurationMs(i);
            i++;
        } while (j >= j2);
        return i - 1;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/facebook/fresco/animation/frame/DropFramesFrameScheduler$Companion;", "", "()V", "UNSET", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DropFramesFrameScheduler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
