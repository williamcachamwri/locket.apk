package com.facebook.imagepipeline.core;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\u0006H\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u0006H\u0016J\b\u0010\u000f\u001a\u00020\u0006H\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u0006H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/facebook/imagepipeline/core/DefaultExecutorSupplier;", "Lcom/facebook/imagepipeline/core/ExecutorSupplier;", "numCpuBoundThreads", "", "(I)V", "backgroundExecutor", "Ljava/util/concurrent/Executor;", "backgroundScheduledExecutorService", "Ljava/util/concurrent/ScheduledExecutorService;", "decodeExecutor", "ioBoundExecutor", "lightWeightBackgroundExecutor", "forBackgroundTasks", "forDecode", "forLightweightBackgroundTasks", "forLocalStorageRead", "forLocalStorageWrite", "forThumbnailProducer", "scheduledExecutorServiceForBackgroundTasks", "Companion", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultExecutorSupplier.kt */
public final class DefaultExecutorSupplier implements ExecutorSupplier {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int NUM_IO_BOUND_THREADS = 2;
    private static final int NUM_LIGHTWEIGHT_BACKGROUND_THREADS = 1;
    private final Executor backgroundExecutor;
    private final ScheduledExecutorService backgroundScheduledExecutorService;
    private final Executor decodeExecutor;
    private final Executor ioBoundExecutor;
    private final Executor lightWeightBackgroundExecutor;

    public DefaultExecutorSupplier(int i) {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2, new PriorityThreadFactory(10, "FrescoIoBoundExecutor", true));
        Intrinsics.checkNotNullExpressionValue(newFixedThreadPool, "newFixedThreadPool(\n    …oIoBoundExecutor\", true))");
        this.ioBoundExecutor = newFixedThreadPool;
        ExecutorService newFixedThreadPool2 = Executors.newFixedThreadPool(i, new PriorityThreadFactory(10, "FrescoDecodeExecutor", true));
        Intrinsics.checkNotNullExpressionValue(newFixedThreadPool2, "newFixedThreadPool(\n    …coDecodeExecutor\", true))");
        this.decodeExecutor = newFixedThreadPool2;
        ExecutorService newFixedThreadPool3 = Executors.newFixedThreadPool(i, new PriorityThreadFactory(10, "FrescoBackgroundExecutor", true));
        Intrinsics.checkNotNullExpressionValue(newFixedThreadPool3, "newFixedThreadPool(\n    …ckgroundExecutor\", true))");
        this.backgroundExecutor = newFixedThreadPool3;
        ExecutorService newFixedThreadPool4 = Executors.newFixedThreadPool(1, new PriorityThreadFactory(10, "FrescoLightWeightBackgroundExecutor", true));
        Intrinsics.checkNotNullExpressionValue(newFixedThreadPool4, "newFixedThreadPool(\n    …ckgroundExecutor\", true))");
        this.lightWeightBackgroundExecutor = newFixedThreadPool4;
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(i, new PriorityThreadFactory(10, "FrescoBackgroundExecutor", true));
        Intrinsics.checkNotNullExpressionValue(newScheduledThreadPool, "newScheduledThreadPool(\n…ckgroundExecutor\", true))");
        this.backgroundScheduledExecutorService = newScheduledThreadPool;
    }

    public Executor forLocalStorageRead() {
        return this.ioBoundExecutor;
    }

    public Executor forLocalStorageWrite() {
        return this.ioBoundExecutor;
    }

    public Executor forDecode() {
        return this.decodeExecutor;
    }

    public Executor forBackgroundTasks() {
        return this.backgroundExecutor;
    }

    public ScheduledExecutorService scheduledExecutorServiceForBackgroundTasks() {
        return this.backgroundScheduledExecutorService;
    }

    public Executor forLightweightBackgroundTasks() {
        return this.lightWeightBackgroundExecutor;
    }

    public Executor forThumbnailProducer() {
        return this.ioBoundExecutor;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/facebook/imagepipeline/core/DefaultExecutorSupplier$Companion;", "", "()V", "NUM_IO_BOUND_THREADS", "", "NUM_LIGHTWEIGHT_BACKGROUND_THREADS", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DefaultExecutorSupplier.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
