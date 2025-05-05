package com.facebook.fresco.animation.bitmap.preparation;

import android.graphics.Bitmap;
import android.os.SystemClock;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.backend.AnimationInformation;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparationStrategy;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.AnimationLoaderExecutor;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFrameTask;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFrameTaskFactory;
import java.util.SortedSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 82\u00020\u0001:\u00018B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0005H\u0002J\b\u0010!\u001a\u00020\"H\u0016J\u0018\u0010#\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010$2\u0006\u0010&\u001a\u00020\u0005H\u0002J\u0017\u0010'\u001a\u0004\u0018\u00010\u00052\u0006\u0010(\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010)J(\u0010*\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010$2\u0006\u0010+\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0005H\u0017J\b\u0010,\u001a\u00020\u000bH\u0002J\u0010\u0010-\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u0005H\u0002J \u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u001e2\u000e\u00101\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u000102H\u0002J\b\u00103\u001a\u00020\"H\u0016J(\u00104\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u000e\u00105\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u000102H\u0017J\u0010\u00106\u001a\u00020\"2\u0006\u00107\u001a\u00020\u0005H\u0002R\u000e\u0010\r\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u000b8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u001bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/BalancedAnimationStrategy;", "Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparationStrategy;", "animationInformation", "Lcom/facebook/fresco/animation/backend/AnimationInformation;", "onDemandPreparationMs", "", "loadFrameTaskFactory", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameTaskFactory;", "bitmapCache", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "downscaleFrameToDrawableDimensions", "", "(Lcom/facebook/fresco/animation/backend/AnimationInformation;ILcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameTaskFactory;Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;Z)V", "animationHeight", "animationWidth", "fetchingFrames", "Ljava/util/concurrent/atomic/AtomicBoolean;", "fetchingOnDemand", "frameCount", "framesCached", "getFramesCached", "()Z", "nextPrepareFrames", "", "onDemandBitmap", "Lcom/facebook/fresco/animation/bitmap/preparation/OnDemandFrame;", "onDemandFrames", "Ljava/util/SortedSet;", "onDemandRatio", "calculateFrameSize", "Lcom/facebook/fresco/animation/bitmap/preparation/Size;", "canvasWidth", "canvasHeight", "clearFrames", "", "findNearestFrame", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "fromFrame", "findNextOnDemandFrame", "from", "(I)Ljava/lang/Integer;", "getBitmapFrame", "frameNumber", "isFirstFrameReady", "isOnDemandFrame", "loadAllFrames", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameTask;", "frameSize", "notifyOnLoad", "Lkotlin/Function0;", "onStop", "prepareFrames", "onAnimationLoaded", "prepareNextOnDemandFrame", "lastFrameRendered", "Companion", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BalancedAnimationStrategy.kt */
public final class BalancedAnimationStrategy implements BitmapFramePreparationStrategy {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final int FETCH_FIRST_CACHE_DELAY_MS = 500;
    /* access modifiers changed from: private */
    public static final long FETCH_FULL_ANIMATION_CACHE_DELAY_MS = TimeUnit.SECONDS.toMillis(5);
    private final int animationHeight;
    private final int animationWidth;
    /* access modifiers changed from: private */
    public final BitmapFrameCache bitmapCache;
    private final boolean downscaleFrameToDrawableDimensions;
    /* access modifiers changed from: private */
    public final AtomicBoolean fetchingFrames = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final AtomicBoolean fetchingOnDemand = new AtomicBoolean(false);
    private final int frameCount;
    private final LoadFrameTaskFactory loadFrameTaskFactory;
    /* access modifiers changed from: private */
    public long nextPrepareFrames = SystemClock.uptimeMillis();
    /* access modifiers changed from: private */
    public OnDemandFrame onDemandBitmap;
    /* access modifiers changed from: private */
    public final SortedSet<Integer> onDemandFrames = SetsKt.sortedSetOf(new Integer[0]);
    private final int onDemandRatio;

    public BalancedAnimationStrategy(AnimationInformation animationInformation, int i, LoadFrameTaskFactory loadFrameTaskFactory2, BitmapFrameCache bitmapFrameCache, boolean z) {
        Intrinsics.checkNotNullParameter(animationInformation, "animationInformation");
        Intrinsics.checkNotNullParameter(loadFrameTaskFactory2, "loadFrameTaskFactory");
        Intrinsics.checkNotNullParameter(bitmapFrameCache, "bitmapCache");
        this.loadFrameTaskFactory = loadFrameTaskFactory2;
        this.bitmapCache = bitmapFrameCache;
        this.downscaleFrameToDrawableDimensions = z;
        int frameCount2 = animationInformation.getFrameCount();
        this.frameCount = frameCount2;
        this.animationWidth = animationInformation.width();
        this.animationHeight = animationInformation.height();
        this.onDemandRatio = RangesKt.coerceAtLeast((int) ((float) Math.ceil((double) (((float) i) / ((float) (animationInformation.getLoopDurationMs() / frameCount2))))), 2);
    }

    public void prepareFrames(BitmapFramePreparer bitmapFramePreparer, BitmapFrameCache bitmapFrameCache, AnimationBackend animationBackend, int i, Function0<Unit> function0) {
        BitmapFramePreparationStrategy.DefaultImpls.prepareFrames(this, bitmapFramePreparer, bitmapFrameCache, animationBackend, i, function0);
    }

    private final boolean getFramesCached() {
        return this.bitmapCache.isAnimationReady();
    }

    private final boolean isFirstFrameReady() {
        CloseableReference<Bitmap> cachedFrame = this.bitmapCache.getCachedFrame(0);
        return cachedFrame != null && cachedFrame.isValid();
    }

    public void prepareFrames(int i, int i2, Function0<Unit> function0) {
        LoadFrameTask loadFrameTask;
        if (i > 0 && i2 > 0 && this.animationWidth > 0 && this.animationHeight > 0) {
            if (!getFramesCached() && !this.fetchingFrames.get() && SystemClock.uptimeMillis() >= this.nextPrepareFrames) {
                this.fetchingFrames.set(true);
                Size calculateFrameSize = calculateFrameSize(i, i2);
                if (!isFirstFrameReady()) {
                    loadFrameTask = this.loadFrameTaskFactory.createFirstFrameTask(calculateFrameSize.getWidth(), calculateFrameSize.getHeight(), new BalancedAnimationStrategy$prepareFrames$task$1(this, calculateFrameSize, function0));
                } else {
                    loadFrameTask = loadAllFrames(calculateFrameSize, function0);
                }
                AnimationLoaderExecutor.INSTANCE.execute(loadFrameTask);
            } else if (getFramesCached() && function0 != null) {
                function0.invoke();
            }
        }
    }

    /* access modifiers changed from: private */
    public final LoadFrameTask loadAllFrames(Size size, Function0<Unit> function0) {
        return this.loadFrameTaskFactory.createLoadFullAnimationTask(size.getWidth(), size.getHeight(), this.frameCount, new BalancedAnimationStrategy$loadAllFrames$1(this, function0));
    }

    public CloseableReference<Bitmap> getBitmapFrame(int i, int i2, int i3) {
        CloseableReference<Bitmap> cachedFrame = this.bitmapCache.getCachedFrame(i);
        boolean z = true;
        if (cachedFrame != null && cachedFrame.isValid()) {
            prepareNextOnDemandFrame(i);
            return cachedFrame;
        }
        if (!isOnDemandFrame(i)) {
            prepareFrames(i2, i3, BalancedAnimationStrategy$getBitmapFrame$1.INSTANCE);
        }
        OnDemandFrame onDemandFrame = this.onDemandBitmap;
        if (onDemandFrame == null || !onDemandFrame.isValidFor(i)) {
            z = false;
        }
        if (!z) {
            return findNearestFrame(i);
        }
        OnDemandFrame onDemandFrame2 = this.onDemandBitmap;
        if (onDemandFrame2 != null) {
            return onDemandFrame2.getBitmap();
        }
        return null;
    }

    private final void prepareNextOnDemandFrame(int i) {
        boolean z = true;
        if (!this.fetchingOnDemand.getAndSet(true)) {
            Integer findNextOnDemandFrame = findNextOnDemandFrame(i);
            if (findNextOnDemandFrame != null) {
                OnDemandFrame onDemandFrame = this.onDemandBitmap;
                if (onDemandFrame == null || !onDemandFrame.isValidFor(findNextOnDemandFrame.intValue())) {
                    z = false;
                }
                if (!z) {
                    AnimationLoaderExecutor.INSTANCE.execute(this.loadFrameTaskFactory.createOnDemandTask(findNextOnDemandFrame.intValue(), new BalancedAnimationStrategy$prepareNextOnDemandFrame$onDemandTask$1(this), new BalancedAnimationStrategy$prepareNextOnDemandFrame$onDemandTask$2(this, findNextOnDemandFrame)));
                    return;
                }
            }
            this.fetchingOnDemand.set(false);
        }
    }

    /* access modifiers changed from: private */
    public final boolean isOnDemandFrame(int i) {
        int i2 = this.onDemandRatio;
        if (i2 <= this.frameCount && i % i2 == 1) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002d, code lost:
        if (r1.isValid() == true) goto L_0x0031;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.facebook.common.references.CloseableReference<android.graphics.Bitmap> findNearestFrame(int r6) {
        /*
            r5 = this;
            r0 = 0
            kotlin.ranges.IntProgression r6 = kotlin.ranges.RangesKt.downTo((int) r6, (int) r0)
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            kotlin.sequences.Sequence r6 = kotlin.collections.CollectionsKt.asSequence(r6)
            java.util.Iterator r6 = r6.iterator()
        L_0x000f:
            boolean r1 = r6.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x0036
            java.lang.Object r1 = r6.next()
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            com.facebook.fresco.animation.bitmap.BitmapFrameCache r3 = r5.bitmapCache
            com.facebook.common.references.CloseableReference r1 = r3.getCachedFrame(r1)
            if (r1 == 0) goto L_0x0030
            boolean r3 = r1.isValid()
            r4 = 1
            if (r3 != r4) goto L_0x0030
            goto L_0x0031
        L_0x0030:
            r4 = r0
        L_0x0031:
            if (r4 == 0) goto L_0x0034
            r2 = r1
        L_0x0034:
            if (r2 == 0) goto L_0x000f
        L_0x0036:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.animation.bitmap.preparation.BalancedAnimationStrategy.findNearestFrame(int):com.facebook.common.references.CloseableReference");
    }

    public void onStop() {
        OnDemandFrame onDemandFrame = this.onDemandBitmap;
        if (onDemandFrame != null) {
            onDemandFrame.close();
        }
        this.bitmapCache.clear();
    }

    public void clearFrames() {
        this.bitmapCache.clear();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.Integer} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Integer findNextOnDemandFrame(int r6) {
        /*
            r5 = this;
            java.util.SortedSet<java.lang.Integer> r0 = r5.onDemandFrames
            boolean r0 = r0.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x000a
            return r1
        L_0x000a:
            java.util.SortedSet<java.lang.Integer> r0 = r5.onDemandFrames
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x0012:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0030
            java.lang.Object r2 = r0.next()
            r3 = r2
            java.lang.Integer r3 = (java.lang.Integer) r3
            java.lang.String r4 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            int r3 = r3.intValue()
            if (r3 <= r6) goto L_0x002c
            r3 = 1
            goto L_0x002d
        L_0x002c:
            r3 = 0
        L_0x002d:
            if (r3 == 0) goto L_0x0012
            r1 = r2
        L_0x0030:
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 != 0) goto L_0x003d
            java.util.SortedSet<java.lang.Integer> r6 = r5.onDemandFrames
            java.lang.Object r6 = r6.first()
            r1 = r6
            java.lang.Integer r1 = (java.lang.Integer) r1
        L_0x003d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.animation.bitmap.preparation.BalancedAnimationStrategy.findNextOnDemandFrame(int):java.lang.Integer");
    }

    private final Size calculateFrameSize(int i, int i2) {
        if (!this.downscaleFrameToDrawableDimensions) {
            return new Size(this.animationWidth, this.animationHeight);
        }
        int i3 = this.animationWidth;
        int i4 = this.animationHeight;
        if (i < i3 || i2 < i4) {
            double d = ((double) i3) / ((double) i4);
            if (i2 > i) {
                i4 = RangesKt.coerceAtMost(i2, i4);
                i3 = (int) (((double) i4) * d);
            } else {
                i3 = RangesKt.coerceAtMost(i, i3);
                i4 = (int) (((double) i3) / d);
            }
        }
        return new Size(i3, i4);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/BalancedAnimationStrategy$Companion;", "", "()V", "FETCH_FIRST_CACHE_DELAY_MS", "", "FETCH_FULL_ANIMATION_CACHE_DELAY_MS", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BalancedAnimationStrategy.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
