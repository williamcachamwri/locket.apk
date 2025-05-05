package com.facebook.fresco.animation.bitmap.preparation.loadframe;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\nJ\u0011\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0000H\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask;", "", "Ljava/lang/Runnable;", "priority", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;", "getPriority", "()Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;", "compareTo", "", "other", "Priority", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AnimationLoaderExecutor.kt */
public interface LoadFramePriorityTask extends Comparable<LoadFramePriorityTask>, Runnable {
    int compareTo(LoadFramePriorityTask loadFramePriorityTask);

    Priority getPriority();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AnimationLoaderExecutor.kt */
    public static final class DefaultImpls {
        public static int compareTo(LoadFramePriorityTask loadFramePriorityTask, LoadFramePriorityTask loadFramePriorityTask2) {
            Intrinsics.checkNotNullParameter(loadFramePriorityTask2, "other");
            return loadFramePriorityTask2.getPriority().compareTo(loadFramePriorityTask.getPriority());
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "HIGH", "MEDIUM", "LOW", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AnimationLoaderExecutor.kt */
    public enum Priority {
        HIGH(10),
        MEDIUM(5),
        LOW(1);
        
        private final int value;

        private Priority(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }
    }
}
