package com.facebook.fresco.animation.bitmap.preparation.ondemandanimation;

import android.os.Handler;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0002J\u0018\u0010\"\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/AnimationCoordinator;", "", "()V", "FPS_STEP_PERCENTAGE", "", "FREQUENCY_LOADERS_MS", "", "FREQUENCY_PERFORMANCE_MS", "MIN_RENDERING_FPS_PERCENTAGE", "calculatePerformance", "Ljava/lang/Runnable;", "clearUnusedFrameLoaders", "criticalCounter", "Ljava/util/concurrent/atomic/AtomicInteger;", "failuresCounter", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "handler$delegate", "Lkotlin/Lazy;", "runningAnimations", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/DynamicRenderingFps;", "", "successCounter", "onRenderFrame", "", "animation", "frameResult", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameResult;", "scheduleLoaders", "", "schedulePerformance", "updateRenderingFps", "delta", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AnimationCoordinator.kt */
public final class AnimationCoordinator {
    private static final float FPS_STEP_PERCENTAGE = 0.2f;
    private static final long FREQUENCY_LOADERS_MS = 10000;
    private static final long FREQUENCY_PERFORMANCE_MS = 2000;
    public static final AnimationCoordinator INSTANCE;
    private static final float MIN_RENDERING_FPS_PERCENTAGE = 0.5f;
    private static final Runnable calculatePerformance;
    private static final Runnable clearUnusedFrameLoaders;
    private static final AtomicInteger criticalCounter = new AtomicInteger(0);
    private static final AtomicInteger failuresCounter = new AtomicInteger(0);
    private static final Lazy handler$delegate = LazyKt.lazy(AnimationCoordinator$handler$2.INSTANCE);
    private static final ConcurrentHashMap<DynamicRenderingFps, Integer> runningAnimations = new ConcurrentHashMap<>();
    private static final AtomicInteger successCounter = new AtomicInteger(0);

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AnimationCoordinator.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameResult$FrameType[] r0 = com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameResult.FrameType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameResult$FrameType r1 = com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameResult.FrameType.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameResult$FrameType r1 = com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameResult.FrameType.NEAREST     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameResult$FrameType r1 = com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameResult.FrameType.MISSING     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.AnimationCoordinator.WhenMappings.<clinit>():void");
        }
    }

    private AnimationCoordinator() {
    }

    static {
        AnimationCoordinator animationCoordinator = new AnimationCoordinator();
        INSTANCE = animationCoordinator;
        AnimationCoordinator$$ExternalSyntheticLambda0 animationCoordinator$$ExternalSyntheticLambda0 = new AnimationCoordinator$$ExternalSyntheticLambda0();
        calculatePerformance = animationCoordinator$$ExternalSyntheticLambda0;
        AnimationCoordinator$$ExternalSyntheticLambda1 animationCoordinator$$ExternalSyntheticLambda1 = new AnimationCoordinator$$ExternalSyntheticLambda1();
        clearUnusedFrameLoaders = animationCoordinator$$ExternalSyntheticLambda1;
        animationCoordinator.getHandler().post(animationCoordinator$$ExternalSyntheticLambda0);
        animationCoordinator.getHandler().post(animationCoordinator$$ExternalSyntheticLambda1);
    }

    private final Handler getHandler() {
        return (Handler) handler$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final void calculatePerformance$lambda$2() {
        float andSet = (float) successCounter.getAndSet(0);
        float andSet2 = (float) failuresCounter.getAndSet(0);
        float andSet3 = (float) criticalCounter.getAndSet(0);
        float f = andSet + andSet2 + andSet3;
        if (f > 0.0f) {
            float f2 = andSet / f;
            float f3 = andSet3 / f;
            if (andSet2 / f > 0.25f || f3 > 0.1f) {
                for (Map.Entry entry : runningAnimations.entrySet()) {
                    int intValue = ((Number) entry.getValue()).intValue();
                    INSTANCE.updateRenderingFps((DynamicRenderingFps) entry.getKey(), -intValue);
                }
            } else if (f2 > 0.98f) {
                for (Map.Entry entry2 : runningAnimations.entrySet()) {
                    int intValue2 = ((Number) entry2.getValue()).intValue();
                    INSTANCE.updateRenderingFps((DynamicRenderingFps) entry2.getKey(), intValue2);
                }
            }
            runningAnimations.clear();
        }
        INSTANCE.schedulePerformance();
    }

    /* access modifiers changed from: private */
    public static final void clearUnusedFrameLoaders$lambda$3() {
        FrameLoaderFactory.Companion.clearUnusedUntil(new Date(System.currentTimeMillis() - 10000));
        INSTANCE.scheduleLoaders();
    }

    private final boolean schedulePerformance() {
        return getHandler().postDelayed(calculatePerformance, 2000);
    }

    private final boolean scheduleLoaders() {
        return getHandler().postDelayed(clearUnusedFrameLoaders, 10000);
    }

    private final void updateRenderingFps(DynamicRenderingFps dynamicRenderingFps, int i) {
        int coerceIn = RangesKt.coerceIn(dynamicRenderingFps.getRenderingFps() + i, (int) RangesKt.coerceAtLeast(((float) dynamicRenderingFps.getAnimationFps()) * 0.5f, 1.0f), dynamicRenderingFps.getAnimationFps());
        if (coerceIn != dynamicRenderingFps.getRenderingFps()) {
            dynamicRenderingFps.setRenderingFps(coerceIn);
        }
    }

    public final void onRenderFrame(DynamicRenderingFps dynamicRenderingFps, FrameResult frameResult) {
        Intrinsics.checkNotNullParameter(dynamicRenderingFps, "animation");
        Intrinsics.checkNotNullParameter(frameResult, "frameResult");
        ConcurrentHashMap<DynamicRenderingFps, Integer> concurrentHashMap = runningAnimations;
        if (!concurrentHashMap.contains(dynamicRenderingFps)) {
            concurrentHashMap.put(dynamicRenderingFps, Integer.valueOf((int) (((float) dynamicRenderingFps.getAnimationFps()) * 0.2f)));
        }
        int i = WhenMappings.$EnumSwitchMapping$0[frameResult.getType().ordinal()];
        if (i == 1) {
            successCounter.incrementAndGet();
        } else if (i == 2) {
            failuresCounter.incrementAndGet();
        } else if (i == 3) {
            criticalCounter.incrementAndGet();
        }
    }
}
