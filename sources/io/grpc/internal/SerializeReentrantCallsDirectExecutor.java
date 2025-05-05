package io.grpc.internal;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

class SerializeReentrantCallsDirectExecutor implements Executor {
    private static final Logger log = Logger.getLogger(SerializeReentrantCallsDirectExecutor.class.getName());
    private boolean executing;
    private ArrayDeque<Runnable> taskQueue;

    SerializeReentrantCallsDirectExecutor() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        if (r6.taskQueue == null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
        if (r6.taskQueue != null) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0016, code lost:
        completeQueuedTasks();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r6.executing = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute(java.lang.Runnable r7) {
        /*
            r6 = this;
            java.lang.String r0 = "Exception while executing runnable "
            java.lang.String r1 = "'task' must not be null."
            com.google.common.base.Preconditions.checkNotNull(r7, r1)
            boolean r1 = r6.executing
            if (r1 != 0) goto L_0x0041
            r1 = 1
            r6.executing = r1
            r1 = 0
            r7.run()     // Catch:{ all -> 0x001c }
            java.util.ArrayDeque<java.lang.Runnable> r7 = r6.taskQueue
            if (r7 == 0) goto L_0x0019
        L_0x0016:
            r6.completeQueuedTasks()
        L_0x0019:
            r6.executing = r1
            goto L_0x0044
        L_0x001c:
            r2 = move-exception
            java.util.logging.Logger r3 = log     // Catch:{ all -> 0x0036 }
            java.util.logging.Level r4 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x0036 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0036 }
            r5.<init>(r0)     // Catch:{ all -> 0x0036 }
            java.lang.StringBuilder r7 = r5.append(r7)     // Catch:{ all -> 0x0036 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0036 }
            r3.log(r4, r7, r2)     // Catch:{ all -> 0x0036 }
            java.util.ArrayDeque<java.lang.Runnable> r7 = r6.taskQueue
            if (r7 == 0) goto L_0x0019
            goto L_0x0016
        L_0x0036:
            r7 = move-exception
            java.util.ArrayDeque<java.lang.Runnable> r0 = r6.taskQueue
            if (r0 == 0) goto L_0x003e
            r6.completeQueuedTasks()
        L_0x003e:
            r6.executing = r1
            throw r7
        L_0x0041:
            r6.enqueue(r7)
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.SerializeReentrantCallsDirectExecutor.execute(java.lang.Runnable):void");
    }

    private void completeQueuedTasks() {
        while (true) {
            Runnable poll = this.taskQueue.poll();
            if (poll != null) {
                try {
                    poll.run();
                } catch (Throwable th) {
                    log.log(Level.SEVERE, "Exception while executing runnable " + poll, th);
                }
            } else {
                return;
            }
        }
    }

    private void enqueue(Runnable runnable) {
        if (this.taskQueue == null) {
            this.taskQueue = new ArrayDeque<>(4);
        }
        this.taskQueue.add(runnable);
    }
}
