package com.facebook.fresco.animation.bitmap.preparation.ondemandanimation;

import com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFramePriorityTask;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"com/facebook/fresco/animation/bitmap/preparation/ondemandanimation/BufferFrameLoader$loadNextFrames$1", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask;", "priority", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;", "getPriority", "()Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;", "run", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BufferFrameLoader.kt */
public final class BufferFrameLoader$loadNextFrames$1 implements LoadFramePriorityTask {
    final /* synthetic */ int $height;
    final /* synthetic */ int $width;
    private final LoadFramePriorityTask.Priority priority = LoadFramePriorityTask.Priority.HIGH;
    final /* synthetic */ BufferFrameLoader this$0;

    BufferFrameLoader$loadNextFrames$1(BufferFrameLoader bufferFrameLoader, int i, int i2) {
        this.this$0 = bufferFrameLoader;
        this.$width = i;
        this.$height = i2;
    }

    public int compareTo(LoadFramePriorityTask loadFramePriorityTask) {
        return LoadFramePriorityTask.DefaultImpls.compareTo(this, loadFramePriorityTask);
    }

    public LoadFramePriorityTask.Priority getPriority() {
        return this.priority;
    }

    public void run() {
        do {
        } while (!BufferFrameLoader.extractDemandedFrame$default(this.this$0, RangesKt.coerceAtLeast(this.this$0.lastRenderedFrameNumber, 0), this.$width, this.$height, 0, 8, (Object) null));
        this.this$0.isFetching = false;
    }
}
