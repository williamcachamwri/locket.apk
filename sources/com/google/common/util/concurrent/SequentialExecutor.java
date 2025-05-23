package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class SequentialExecutor implements Executor {
    /* access modifiers changed from: private */
    public static final LazyLogger log = new LazyLogger(SequentialExecutor.class);
    private final Executor executor;
    /* access modifiers changed from: private */
    public final Deque<Runnable> queue = new ArrayDeque();
    private final QueueWorker worker = new QueueWorker();
    private long workerRunCount = 0;
    /* access modifiers changed from: private */
    @LazyInit
    public WorkerRunningState workerRunningState = WorkerRunningState.IDLE;

    enum WorkerRunningState {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING
    }

    static /* synthetic */ long access$308(SequentialExecutor sequentialExecutor) {
        long j = sequentialExecutor.workerRunCount;
        sequentialExecutor.workerRunCount = 1 + j;
        return j;
    }

    SequentialExecutor(Executor executor2) {
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        r8 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r7.executor.execute(r7.worker);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        if (r7.workerRunningState == com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        r8 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        if (r8 == false) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0038, code lost:
        r4 = r7.queue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003f, code lost:
        if (r7.workerRunCount != r1) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0045, code lost:
        if (r7.workerRunningState != com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0047, code lost:
        r7.workerRunningState = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004b, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0050, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0053, code lost:
        monitor-enter(r7.queue);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0058, code lost:
        if (r7.workerRunningState == com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.IDLE) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0069, code lost:
        r8 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x006c, code lost:
        if ((r1 instanceof java.util.concurrent.RejectedExecutionException) == false) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0071, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0072, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute(final java.lang.Runnable r8) {
        /*
            r7 = this;
            com.google.common.base.Preconditions.checkNotNull(r8)
            java.util.Deque<java.lang.Runnable> r0 = r7.queue
            monitor-enter(r0)
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r1 = r7.workerRunningState     // Catch:{ all -> 0x007d }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r2 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch:{ all -> 0x007d }
            if (r1 == r2) goto L_0x0076
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r1 = r7.workerRunningState     // Catch:{ all -> 0x007d }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r2 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUED     // Catch:{ all -> 0x007d }
            if (r1 != r2) goto L_0x0013
            goto L_0x0076
        L_0x0013:
            long r1 = r7.workerRunCount     // Catch:{ all -> 0x007d }
            com.google.common.util.concurrent.SequentialExecutor$1 r3 = new com.google.common.util.concurrent.SequentialExecutor$1     // Catch:{ all -> 0x007d }
            r3.<init>(r7, r8)     // Catch:{ all -> 0x007d }
            java.util.Deque<java.lang.Runnable> r8 = r7.queue     // Catch:{ all -> 0x007d }
            r8.add(r3)     // Catch:{ all -> 0x007d }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r8 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING     // Catch:{ all -> 0x007d }
            r7.workerRunningState = r8     // Catch:{ all -> 0x007d }
            monitor-exit(r0)     // Catch:{ all -> 0x007d }
            r8 = 1
            r0 = 0
            java.util.concurrent.Executor r4 = r7.executor     // Catch:{ all -> 0x0050 }
            com.google.common.util.concurrent.SequentialExecutor$QueueWorker r5 = r7.worker     // Catch:{ all -> 0x0050 }
            r4.execute(r5)     // Catch:{ all -> 0x0050 }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = r7.workerRunningState
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r4 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING
            if (r3 == r4) goto L_0x0034
            goto L_0x0035
        L_0x0034:
            r8 = r0
        L_0x0035:
            if (r8 == 0) goto L_0x0038
            return
        L_0x0038:
            java.util.Deque<java.lang.Runnable> r4 = r7.queue
            monitor-enter(r4)
            long r5 = r7.workerRunCount     // Catch:{ all -> 0x004d }
            int r8 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r8 != 0) goto L_0x004b
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r8 = r7.workerRunningState     // Catch:{ all -> 0x004d }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r0 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING     // Catch:{ all -> 0x004d }
            if (r8 != r0) goto L_0x004b
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r8 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUED     // Catch:{ all -> 0x004d }
            r7.workerRunningState = r8     // Catch:{ all -> 0x004d }
        L_0x004b:
            monitor-exit(r4)     // Catch:{ all -> 0x004d }
            return
        L_0x004d:
            r8 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x004d }
            throw r8
        L_0x0050:
            r1 = move-exception
            java.util.Deque<java.lang.Runnable> r2 = r7.queue
            monitor-enter(r2)
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r4 = r7.workerRunningState     // Catch:{ all -> 0x0073 }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r5 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.IDLE     // Catch:{ all -> 0x0073 }
            if (r4 == r5) goto L_0x0060
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r4 = r7.workerRunningState     // Catch:{ all -> 0x0073 }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r5 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING     // Catch:{ all -> 0x0073 }
            if (r4 != r5) goto L_0x0069
        L_0x0060:
            java.util.Deque<java.lang.Runnable> r4 = r7.queue     // Catch:{ all -> 0x0073 }
            boolean r3 = r4.removeLastOccurrence(r3)     // Catch:{ all -> 0x0073 }
            if (r3 == 0) goto L_0x0069
            goto L_0x006a
        L_0x0069:
            r8 = r0
        L_0x006a:
            boolean r0 = r1 instanceof java.util.concurrent.RejectedExecutionException     // Catch:{ all -> 0x0073 }
            if (r0 == 0) goto L_0x0072
            if (r8 != 0) goto L_0x0072
            monitor-exit(r2)     // Catch:{ all -> 0x0073 }
            return
        L_0x0072:
            throw r1     // Catch:{ all -> 0x0073 }
        L_0x0073:
            r8 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0073 }
            throw r8
        L_0x0076:
            java.util.Deque<java.lang.Runnable> r1 = r7.queue     // Catch:{ all -> 0x007d }
            r1.add(r8)     // Catch:{ all -> 0x007d }
            monitor-exit(r0)     // Catch:{ all -> 0x007d }
            return
        L_0x007d:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x007d }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SequentialExecutor.execute(java.lang.Runnable):void");
    }

    private final class QueueWorker implements Runnable {
        @CheckForNull
        Runnable task;

        private QueueWorker() {
        }

        public void run() {
            try {
                workOnQueue();
            } catch (Error e) {
                synchronized (SequentialExecutor.this.queue) {
                    WorkerRunningState unused = SequentialExecutor.this.workerRunningState = WorkerRunningState.IDLE;
                    throw e;
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
            if (r1 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0054, code lost:
            r1 = r1 | java.lang.Thread.interrupted();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            r8.task.run();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
            r3 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
            com.google.common.util.concurrent.SequentialExecutor.access$400().get().log(java.util.logging.Level.SEVERE, "Exception while executing runnable " + r8.task, r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
            if (r1 == false) goto L_?;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void workOnQueue() {
            /*
                r8 = this;
                r0 = 0
                r1 = r0
            L_0x0002:
                com.google.common.util.concurrent.SequentialExecutor r2 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x008a }
                java.util.Deque r2 = r2.queue     // Catch:{ all -> 0x008a }
                monitor-enter(r2)     // Catch:{ all -> 0x008a }
                if (r0 != 0) goto L_0x002d
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0087 }
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r0 = r0.workerRunningState     // Catch:{ all -> 0x0087 }
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch:{ all -> 0x0087 }
                if (r0 != r3) goto L_0x0020
                monitor-exit(r2)     // Catch:{ all -> 0x0087 }
                if (r1 == 0) goto L_0x001f
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x001f:
                return
            L_0x0020:
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0087 }
                com.google.common.util.concurrent.SequentialExecutor.access$308(r0)     // Catch:{ all -> 0x0087 }
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0087 }
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch:{ all -> 0x0087 }
                com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState unused = r0.workerRunningState = r3     // Catch:{ all -> 0x0087 }
                r0 = 1
            L_0x002d:
                com.google.common.util.concurrent.SequentialExecutor r3 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0087 }
                java.util.Deque r3 = r3.queue     // Catch:{ all -> 0x0087 }
                java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0087 }
                java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch:{ all -> 0x0087 }
                r8.task = r3     // Catch:{ all -> 0x0087 }
                if (r3 != 0) goto L_0x004f
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0087 }
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.IDLE     // Catch:{ all -> 0x0087 }
                com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState unused = r0.workerRunningState = r3     // Catch:{ all -> 0x0087 }
                monitor-exit(r2)     // Catch:{ all -> 0x0087 }
                if (r1 == 0) goto L_0x004e
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x004e:
                return
            L_0x004f:
                monitor-exit(r2)     // Catch:{ all -> 0x0087 }
                boolean r2 = java.lang.Thread.interrupted()     // Catch:{ all -> 0x008a }
                r1 = r1 | r2
                r2 = 0
                java.lang.Runnable r3 = r8.task     // Catch:{ Exception -> 0x0060 }
                r3.run()     // Catch:{ Exception -> 0x0060 }
            L_0x005b:
                r8.task = r2     // Catch:{ all -> 0x008a }
                goto L_0x0002
            L_0x005e:
                r0 = move-exception
                goto L_0x0084
            L_0x0060:
                r3 = move-exception
                com.google.common.util.concurrent.LazyLogger r4 = com.google.common.util.concurrent.SequentialExecutor.log     // Catch:{ all -> 0x005e }
                java.util.logging.Logger r4 = r4.get()     // Catch:{ all -> 0x005e }
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x005e }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x005e }
                r6.<init>()     // Catch:{ all -> 0x005e }
                java.lang.String r7 = "Exception while executing runnable "
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x005e }
                java.lang.Runnable r7 = r8.task     // Catch:{ all -> 0x005e }
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x005e }
                java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x005e }
                r4.log(r5, r6, r3)     // Catch:{ all -> 0x005e }
                goto L_0x005b
            L_0x0084:
                r8.task = r2     // Catch:{ all -> 0x008a }
                throw r0     // Catch:{ all -> 0x008a }
            L_0x0087:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0087 }
                throw r0     // Catch:{ all -> 0x008a }
            L_0x008a:
                r0 = move-exception
                if (r1 == 0) goto L_0x0094
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                r1.interrupt()
            L_0x0094:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SequentialExecutor.QueueWorker.workOnQueue():void");
        }

        public String toString() {
            Runnable runnable = this.task;
            if (runnable != null) {
                return "SequentialExecutorWorker{running=" + runnable + "}";
            }
            return "SequentialExecutorWorker{state=" + SequentialExecutor.this.workerRunningState + "}";
        }
    }

    public String toString() {
        return "SequentialExecutor@" + System.identityHashCode(this) + "{" + this.executor + "}";
    }
}
