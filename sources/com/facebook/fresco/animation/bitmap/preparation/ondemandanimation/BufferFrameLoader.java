package com.facebook.fresco.animation.bitmap.preparation.ondemandanimation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.backend.AnimationInformation;
import com.facebook.fresco.animation.bitmap.BitmapFrameRenderer;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.AnimationLoaderExecutor;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.FpsCompressorInfo;
import com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameResult;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 72\u00020\u0001:\u000267B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000fH\u0016J*\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020\u000fH\u0003J\u0012\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010!\u001a\u00020\u000fH\u0002J\u0010\u0010'\u001a\u00020(2\u0006\u0010!\u001a\u00020\u000fH\u0003J \u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0017J\u0018\u0010+\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0002J(\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0002J\b\u0010.\u001a\u00020\u001dH\u0016J&\u0010/\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000f2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u001d01H\u0017J\u0012\u0010\u001c\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020302H\u0002J\f\u0010\u001f\u001a\u00020\u000f*\u00020\tH\u0002J \u00104\u001a\b\u0012\u0004\u0012\u00020302*\b\u0012\u0004\u0012\u000203022\u0006\u00105\u001a\u000203H\u0002R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001aX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/BufferFrameLoader;", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameLoader;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapFrameRenderer", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;", "fpsCompressor", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/FpsCompressorInfo;", "animationInformation", "Lcom/facebook/fresco/animation/backend/AnimationInformation;", "(Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/FpsCompressorInfo;Lcom/facebook/fresco/animation/backend/AnimationInformation;)V", "getAnimationInformation", "()Lcom/facebook/fresco/animation/backend/AnimationInformation;", "bufferFramesHash", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/BufferFrameLoader$BufferFrame;", "bufferSize", "compressionFrameMap", "", "frameSequence", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/CircularList;", "isFetching", "", "lastRenderedFrameNumber", "renderableFrameIndexes", "", "thresholdFrame", "clear", "", "compressToFps", "fps", "extractDemandedFrame", "targetFrame", "width", "height", "count", "findNearestFrame", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/AnimationBitmapFrame;", "findNearestToRender", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameResult;", "getFrame", "frameNumber", "loadNextFrames", "obtainFrame", "bufferFrame", "onStop", "prepareFrames", "onAnimationLoaded", "Lkotlin/Function0;", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "set", "src", "BufferFrame", "Companion", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BufferFrameLoader.kt */
public final class BufferFrameLoader implements FrameLoader {
    private static final int BUFFER_SECOND_SIZE = 1;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final float THRESHOLD_PERCENTAGE = 0.5f;
    private final AnimationInformation animationInformation;
    private final BitmapFrameRenderer bitmapFrameRenderer;
    private final ConcurrentHashMap<Integer, BufferFrame> bufferFramesHash = new ConcurrentHashMap<>();
    private final int bufferSize;
    private Map<Integer, Integer> compressionFrameMap = MapsKt.emptyMap();
    private final FpsCompressorInfo fpsCompressor;
    private final CircularList frameSequence = new CircularList(getAnimationInformation().getFrameCount());
    /* access modifiers changed from: private */
    public volatile boolean isFetching;
    /* access modifiers changed from: private */
    public int lastRenderedFrameNumber = -1;
    private final PlatformBitmapFactory platformBitmapFactory;
    private Set<Integer> renderableFrameIndexes = SetsKt.emptySet();
    private volatile int thresholdFrame;

    public BufferFrameLoader(PlatformBitmapFactory platformBitmapFactory2, BitmapFrameRenderer bitmapFrameRenderer2, FpsCompressorInfo fpsCompressorInfo, AnimationInformation animationInformation2) {
        Intrinsics.checkNotNullParameter(platformBitmapFactory2, "platformBitmapFactory");
        Intrinsics.checkNotNullParameter(bitmapFrameRenderer2, "bitmapFrameRenderer");
        Intrinsics.checkNotNullParameter(fpsCompressorInfo, "fpsCompressor");
        Intrinsics.checkNotNullParameter(animationInformation2, "animationInformation");
        this.platformBitmapFactory = platformBitmapFactory2;
        this.bitmapFrameRenderer = bitmapFrameRenderer2;
        this.fpsCompressor = fpsCompressorInfo;
        this.animationInformation = animationInformation2;
        int fps = fps(getAnimationInformation()) * 1;
        this.bufferSize = fps;
        compressToFps(fps(getAnimationInformation()));
        this.thresholdFrame = (int) (((float) fps) * 0.5f);
    }

    public AnimationInformation getAnimationInformation() {
        return this.animationInformation;
    }

    public FrameResult getFrame(int i, int i2, int i3) {
        Integer num = this.compressionFrameMap.get(Integer.valueOf(i));
        if (num == null) {
            return findNearestToRender(i);
        }
        int intValue = num.intValue();
        this.lastRenderedFrameNumber = intValue;
        BufferFrame bufferFrame = this.bufferFramesHash.get(Integer.valueOf(intValue));
        if (bufferFrame == null || !bufferFrame.isFrameAvailable()) {
            bufferFrame = null;
        }
        if (bufferFrame != null) {
            if (this.frameSequence.isTargetAhead(this.thresholdFrame, intValue, this.bufferSize)) {
                loadNextFrames(i2, i3);
            }
            return new FrameResult(bufferFrame.getBitmapRef().clone(), FrameResult.FrameType.SUCCESS);
        }
        loadNextFrames(i2, i3);
        return findNearestToRender(intValue);
    }

    private final FrameResult findNearestToRender(int i) {
        AnimationBitmapFrame findNearestFrame = findNearestFrame(i);
        if (findNearestFrame == null) {
            return new FrameResult((CloseableReference<Bitmap>) null, FrameResult.FrameType.MISSING);
        }
        CloseableReference<Bitmap> clone = findNearestFrame.getBitmap().clone();
        Intrinsics.checkNotNullExpressionValue(clone, "nearestFrame.bitmap.clone()");
        this.lastRenderedFrameNumber = findNearestFrame.getFrameNumber();
        return new FrameResult(clone, FrameResult.FrameType.NEAREST);
    }

    public void prepareFrames(int i, int i2, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "onAnimationLoaded");
        loadNextFrames(i, i2);
        function0.invoke();
    }

    public void compressToFps(int i) {
        Map<Integer, Integer> calculateReducedIndexes = this.fpsCompressor.calculateReducedIndexes(getAnimationInformation().getLoopDurationMs() * RangesKt.coerceAtLeast(getAnimationInformation().getLoopCount(), 1), getAnimationInformation().getFrameCount(), RangesKt.coerceAtMost(i, fps(getAnimationInformation())));
        this.compressionFrameMap = calculateReducedIndexes;
        this.renderableFrameIndexes = CollectionsKt.toSet(calculateReducedIndexes.values());
    }

    public void onStop() {
        AnimationBitmapFrame findNearestFrame = findNearestFrame(this.lastRenderedFrameNumber);
        Set<Integer> keySet = this.bufferFramesHash.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "bufferFramesHash.keys");
        for (Integer num : CollectionsKt.filterNotNull(SetsKt.minus(keySet, findNearestFrame != null ? Integer.valueOf(findNearestFrame.getFrameNumber()) : null))) {
            BufferFrame bufferFrame = this.bufferFramesHash.get(num);
            if (bufferFrame != null) {
                bufferFrame.release();
            }
            this.bufferFramesHash.remove(num);
        }
    }

    public void clear() {
        Collection<BufferFrame> values = this.bufferFramesHash.values();
        Intrinsics.checkNotNullExpressionValue(values, "bufferFramesHash.values");
        for (BufferFrame release : values) {
            release.release();
        }
        this.bufferFramesHash.clear();
        this.lastRenderedFrameNumber = -1;
    }

    private final void loadNextFrames(int i, int i2) {
        if (!this.isFetching) {
            this.isFetching = true;
            AnimationLoaderExecutor.INSTANCE.execute(new BufferFrameLoader$loadNextFrames$1(this, i, i2));
        }
    }

    static /* synthetic */ boolean extractDemandedFrame$default(BufferFrameLoader bufferFrameLoader, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i4 = 0;
        }
        return bufferFrameLoader.extractDemandedFrame(i, i2, i3, i4);
    }

    private final boolean extractDemandedFrame(int i, int i2, int i3, int i4) {
        int i5;
        Collection arrayList = new ArrayList();
        for (Object next : this.frameSequence.sublist(i, this.bufferSize)) {
            if (this.renderableFrameIndexes.contains(Integer.valueOf(((Number) next).intValue()))) {
                arrayList.add(next);
            }
        }
        List list = (List) arrayList;
        Iterable<Number> iterable = list;
        Set set = CollectionsKt.toSet(iterable);
        Set<Integer> keySet = this.bufferFramesHash.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "bufferFramesHash.keys");
        ArrayDeque arrayDeque = new ArrayDeque(SetsKt.minus(keySet, set));
        for (Number intValue : iterable) {
            int intValue2 = intValue.intValue();
            if (this.bufferFramesHash.get(Integer.valueOf(intValue2)) == null) {
                int i6 = this.lastRenderedFrameNumber;
                if (i6 != -1 && !set.contains(Integer.valueOf(i6))) {
                    return false;
                }
                int i7 = (Integer) arrayDeque.pollFirst();
                if (i7 == null) {
                    i7 = -1;
                }
                Intrinsics.checkNotNullExpressionValue(i7, "oldFramesNumbers.pollFirst() ?: -1");
                int intValue3 = i7.intValue();
                BufferFrame bufferFrame = this.bufferFramesHash.get(Integer.valueOf(intValue3));
                if (bufferFrame == null) {
                    CloseableReference<Bitmap> createBitmap = this.platformBitmapFactory.createBitmap(i2, i3);
                    Intrinsics.checkNotNullExpressionValue(createBitmap, "platformBitmapFactory.createBitmap(width, height)");
                    bufferFrame = new BufferFrame(createBitmap);
                }
                Intrinsics.checkNotNullExpressionValue(bufferFrame, "bufferFramesHash[depreca…ateBitmap(width, height))");
                bufferFrame.setUpdatingFrame(true);
                obtainFrame(bufferFrame, intValue2, i2, i3);
                this.bufferFramesHash.remove(Integer.valueOf(intValue3));
                bufferFrame.setUpdatingFrame(false);
                this.bufferFramesHash.put(Integer.valueOf(intValue2), bufferFrame);
            }
        }
        if (list.isEmpty()) {
            i5 = (int) (((float) this.bufferSize) * 0.5f);
        } else {
            int size = list.size();
            i5 = ((Number) list.get(RangesKt.coerceIn((int) (((float) size) * 0.5f), 0, size - 1))).intValue();
        }
        this.thresholdFrame = i5;
        return true;
    }

    private final void obtainFrame(BufferFrame bufferFrame, int i, int i2, int i3) {
        int frameNumber;
        AnimationBitmapFrame findNearestFrame = findNearestFrame(i);
        CloseableReference<Bitmap> bitmap = findNearestFrame != null ? findNearestFrame.getBitmap() : null;
        if (findNearestFrame == null || bitmap == null || (frameNumber = findNearestFrame.getFrameNumber()) >= i) {
            CloseableReference<Bitmap> bitmapRef = bufferFrame.getBitmapRef();
            clear(bitmapRef);
            Iterator it = new IntRange(0, i).iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                BitmapFrameRenderer bitmapFrameRenderer2 = this.bitmapFrameRenderer;
                Bitmap bitmap2 = bitmapRef.get();
                Intrinsics.checkNotNullExpressionValue(bitmap2, "targetBitmap.get()");
                bitmapFrameRenderer2.renderFrame(nextInt, bitmap2);
            }
            return;
        }
        CloseableReference<Bitmap> bitmapRef2 = bufferFrame.getBitmapRef();
        Bitmap bitmap3 = bitmap.get();
        Intrinsics.checkNotNullExpressionValue(bitmap3, "nearestBitmap.get()");
        set(bitmapRef2, bitmap3);
        Iterator it2 = new IntRange(frameNumber + 1, i).iterator();
        while (it2.hasNext()) {
            int nextInt2 = ((IntIterator) it2).nextInt();
            BitmapFrameRenderer bitmapFrameRenderer3 = this.bitmapFrameRenderer;
            Bitmap bitmap4 = bitmapRef2.get();
            Intrinsics.checkNotNullExpressionValue(bitmap4, "targetBitmap.get()");
            bitmapFrameRenderer3.renderFrame(nextInt2, bitmap4);
        }
    }

    private final AnimationBitmapFrame findNearestFrame(int i) {
        AnimationBitmapFrame animationBitmapFrame;
        Iterator it = new IntRange(0, this.frameSequence.getSize()).iterator();
        do {
            animationBitmapFrame = null;
            if (!it.hasNext()) {
                break;
            }
            int position = this.frameSequence.getPosition(i - ((IntIterator) it).nextInt());
            BufferFrame bufferFrame = this.bufferFramesHash.get(Integer.valueOf(position));
            if (bufferFrame != null) {
                if (!bufferFrame.isFrameAvailable()) {
                    bufferFrame = null;
                }
                if (bufferFrame != null) {
                    animationBitmapFrame = new AnimationBitmapFrame(position, bufferFrame.getBitmapRef());
                    continue;
                } else {
                    continue;
                }
            }
        } while (animationBitmapFrame == null);
        return animationBitmapFrame;
    }

    private final void clear(CloseableReference<Bitmap> closeableReference) {
        if (closeableReference.isValid()) {
            new Canvas(closeableReference.get()).drawColor(0, PorterDuff.Mode.CLEAR);
        }
    }

    private final CloseableReference<Bitmap> set(CloseableReference<Bitmap> closeableReference, Bitmap bitmap) {
        if (closeableReference.isValid() && !Intrinsics.areEqual((Object) closeableReference.get(), (Object) bitmap)) {
            Canvas canvas = new Canvas(closeableReference.get());
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        }
        return closeableReference;
    }

    private final int fps(AnimationInformation animationInformation2) {
        return (int) RangesKt.coerceAtLeast(TimeUnit.SECONDS.toMillis(1) / ((long) (animationInformation2.getLoopDurationMs() / animationInformation2.getFrameCount())), 1);
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u001a\u0010\u000b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\n\"\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/BufferFrameLoader$BufferFrame;", "", "bitmapRef", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "(Lcom/facebook/common/references/CloseableReference;)V", "getBitmapRef", "()Lcom/facebook/common/references/CloseableReference;", "isFrameAvailable", "", "()Z", "isUpdatingFrame", "setUpdatingFrame", "(Z)V", "release", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BufferFrameLoader.kt */
    private static final class BufferFrame {
        private final CloseableReference<Bitmap> bitmapRef;
        private boolean isUpdatingFrame;

        public BufferFrame(CloseableReference<Bitmap> closeableReference) {
            Intrinsics.checkNotNullParameter(closeableReference, "bitmapRef");
            this.bitmapRef = closeableReference;
        }

        public final CloseableReference<Bitmap> getBitmapRef() {
            return this.bitmapRef;
        }

        public final boolean isUpdatingFrame() {
            return this.isUpdatingFrame;
        }

        public final void setUpdatingFrame(boolean z) {
            this.isUpdatingFrame = z;
        }

        public final boolean isFrameAvailable() {
            return !this.isUpdatingFrame && this.bitmapRef.isValid();
        }

        public final void release() {
            CloseableReference.closeSafely((CloseableReference<?>) this.bitmapRef);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/BufferFrameLoader$Companion;", "", "()V", "BUFFER_SECOND_SIZE", "", "THRESHOLD_PERCENTAGE", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BufferFrameLoader.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
