package androidx.media3.effect;

import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class VideoFrameProcessingTaskExecutor {
    private static final long EXECUTOR_SERVICE_TIMEOUT_MS = 500;
    private final ErrorListener errorListener;
    private final Queue<Task> highPriorityTasks = new ArrayDeque();
    private final Object lock = new Object();
    private boolean shouldCancelTasks;
    private final boolean shouldShutdownExecutorService;
    private final ExecutorService singleThreadExecutorService;
    private final Future<Thread> threadFuture;

    interface ErrorListener {
        void onError(VideoFrameProcessingException videoFrameProcessingException);
    }

    interface Task {
        void run() throws VideoFrameProcessingException, GlUtil.GlException;
    }

    static /* synthetic */ void lambda$submitWithHighPriority$1() throws VideoFrameProcessingException, GlUtil.GlException {
    }

    public VideoFrameProcessingTaskExecutor(ExecutorService executorService, boolean z, ErrorListener errorListener2) {
        this.singleThreadExecutorService = executorService;
        this.threadFuture = executorService.submit(new VideoFrameProcessingTaskExecutor$$ExternalSyntheticLambda2());
        this.shouldShutdownExecutorService = z;
        this.errorListener = errorListener2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0012, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0014, code lost:
        handleException(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void submit(androidx.media3.effect.VideoFrameProcessingTaskExecutor.Task r3, boolean r4) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.lock
            monitor-enter(r0)
            boolean r1 = r2.shouldCancelTasks     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x000b
            if (r4 == 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return
        L_0x000b:
            r2.wrapTaskAndSubmitToExecutorService(r3, r4)     // Catch:{ RejectedExecutionException -> 0x0010 }
            r3 = 0
            goto L_0x0011
        L_0x0010:
            r3 = move-exception
        L_0x0011:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            if (r3 == 0) goto L_0x0017
            r2.handleException(r3)
        L_0x0017:
            return
        L_0x0018:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.VideoFrameProcessingTaskExecutor.submit(androidx.media3.effect.VideoFrameProcessingTaskExecutor$Task, boolean):void");
    }

    public void submit(Task task) {
        submit(task, true);
    }

    public void invoke(Task task) throws InterruptedException {
        if (isRunningOnVideoFrameProcessingThread()) {
            try {
                task.run();
            } catch (Exception e) {
                handleException(e);
            }
        } else {
            try {
                this.singleThreadExecutorService.submit(new VideoFrameProcessingTaskExecutor$$ExternalSyntheticLambda3(this, task)).get(500, TimeUnit.MILLISECONDS);
            } catch (RuntimeException | ExecutionException | TimeoutException e2) {
                handleException(e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$invoke$0$androidx-media3-effect-VideoFrameProcessingTaskExecutor  reason: not valid java name */
    public /* synthetic */ void m462lambda$invoke$0$androidxmedia3effectVideoFrameProcessingTaskExecutor(Task task) {
        try {
            task.run();
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void submitWithHighPriority(Task task) {
        synchronized (this.lock) {
            if (!this.shouldCancelTasks) {
                this.highPriorityTasks.add(task);
                submit(new VideoFrameProcessingTaskExecutor$$ExternalSyntheticLambda1());
            }
        }
    }

    public void flush() throws InterruptedException {
        synchronized (this.lock) {
            this.shouldCancelTasks = true;
            this.highPriorityTasks.clear();
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        wrapTaskAndSubmitToExecutorService(new VideoFrameProcessingTaskExecutor$$ExternalSyntheticLambda0(this, countDownLatch), false);
        countDownLatch.await();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$flush$2$androidx-media3-effect-VideoFrameProcessingTaskExecutor  reason: not valid java name */
    public /* synthetic */ void m461lambda$flush$2$androidxmedia3effectVideoFrameProcessingTaskExecutor(CountDownLatch countDownLatch) throws VideoFrameProcessingException, GlUtil.GlException {
        synchronized (this.lock) {
            this.shouldCancelTasks = false;
        }
        countDownLatch.countDown();
    }

    public void release(Task task) throws InterruptedException {
        Assertions.checkState(!isRunningOnVideoFrameProcessingThread());
        synchronized (this.lock) {
            this.shouldCancelTasks = true;
            this.highPriorityTasks.clear();
        }
        wrapTaskAndSubmitToExecutorService(task, false);
        if (this.shouldShutdownExecutorService) {
            this.singleThreadExecutorService.shutdown();
            if (!this.singleThreadExecutorService.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                this.errorListener.onError(new VideoFrameProcessingException("Release timed out. OpenGL resources may not be cleaned up properly."));
            }
        }
    }

    public void verifyVideoFrameProcessingThread() {
        try {
            Assertions.checkState(isRunningOnVideoFrameProcessingThread());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            handleException(e);
        }
    }

    private boolean isRunningOnVideoFrameProcessingThread() throws InterruptedException {
        try {
            if (Thread.currentThread() == this.threadFuture.get(500, TimeUnit.MILLISECONDS)) {
                return true;
            }
            return false;
        } catch (InterruptedException e) {
            throw e;
        } catch (Exception e2) {
            handleException(e2);
            return false;
        }
    }

    private Future<?> wrapTaskAndSubmitToExecutorService(Task task, boolean z) {
        return this.singleThreadExecutorService.submit(new VideoFrameProcessingTaskExecutor$$ExternalSyntheticLambda4(this, z, task));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r3 = r2.lock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x000e, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0 = r2.highPriorityTasks.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0017, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0018, code lost:
        if (r0 != null) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r4.run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x001e, code lost:
        r0.run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* renamed from: lambda$wrapTaskAndSubmitToExecutorService$3$androidx-media3-effect-VideoFrameProcessingTaskExecutor  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void m463lambda$wrapTaskAndSubmitToExecutorService$3$androidxmedia3effectVideoFrameProcessingTaskExecutor(boolean r3, androidx.media3.effect.VideoFrameProcessingTaskExecutor.Task r4) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.lock     // Catch:{ Exception -> 0x0028 }
            monitor-enter(r0)     // Catch:{ Exception -> 0x0028 }
            boolean r1 = r2.shouldCancelTasks     // Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x000b
            if (r3 == 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return
        L_0x000b:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
        L_0x000c:
            java.lang.Object r3 = r2.lock     // Catch:{ Exception -> 0x0028 }
            monitor-enter(r3)     // Catch:{ Exception -> 0x0028 }
            java.util.Queue<androidx.media3.effect.VideoFrameProcessingTaskExecutor$Task> r0 = r2.highPriorityTasks     // Catch:{ all -> 0x0022 }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0022 }
            androidx.media3.effect.VideoFrameProcessingTaskExecutor$Task r0 = (androidx.media3.effect.VideoFrameProcessingTaskExecutor.Task) r0     // Catch:{ all -> 0x0022 }
            monitor-exit(r3)     // Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x001e
            r4.run()     // Catch:{ Exception -> 0x0028 }
            goto L_0x002c
        L_0x001e:
            r0.run()     // Catch:{ Exception -> 0x0028 }
            goto L_0x000c
        L_0x0022:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0022 }
            throw r4     // Catch:{ Exception -> 0x0028 }
        L_0x0025:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r3     // Catch:{ Exception -> 0x0028 }
        L_0x0028:
            r3 = move-exception
            r2.handleException(r3)
        L_0x002c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.VideoFrameProcessingTaskExecutor.m463lambda$wrapTaskAndSubmitToExecutorService$3$androidxmedia3effectVideoFrameProcessingTaskExecutor(boolean, androidx.media3.effect.VideoFrameProcessingTaskExecutor$Task):void");
    }

    private void handleException(Exception exc) {
        synchronized (this.lock) {
            if (!this.shouldCancelTasks) {
                this.shouldCancelTasks = true;
                this.errorListener.onError(VideoFrameProcessingException.from(exc));
            }
        }
    }
}
