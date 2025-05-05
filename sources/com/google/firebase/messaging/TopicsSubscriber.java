package com.google.firebase.messaging;

import android.content.Context;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class TopicsSubscriber {
    static final String ERROR_INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    private static final long MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8);
    private static final long MIN_DELAY_SEC = 30;
    private static final long RPC_TIMEOUT_SEC = 30;
    private final Context context;
    private final FirebaseMessaging firebaseMessaging;
    private final Metadata metadata;
    private final Map<String, ArrayDeque<TaskCompletionSource<Void>>> pendingOperations = new ArrayMap();
    private final GmsRpc rpc;
    private final TopicsStore store;
    private final ScheduledExecutorService syncExecutor;
    private boolean syncScheduledOrRunning = false;

    static Task<TopicsSubscriber> createInstance(FirebaseMessaging firebaseMessaging2, Metadata metadata2, GmsRpc gmsRpc, Context context2, ScheduledExecutorService scheduledExecutorService) {
        return Tasks.call(scheduledExecutorService, new TopicsSubscriber$$ExternalSyntheticLambda0(context2, scheduledExecutorService, firebaseMessaging2, metadata2, gmsRpc));
    }

    static /* synthetic */ TopicsSubscriber lambda$createInstance$0(Context context2, ScheduledExecutorService scheduledExecutorService, FirebaseMessaging firebaseMessaging2, Metadata metadata2, GmsRpc gmsRpc) throws Exception {
        return new TopicsSubscriber(firebaseMessaging2, metadata2, TopicsStore.getInstance(context2, scheduledExecutorService), gmsRpc, context2, scheduledExecutorService);
    }

    private TopicsSubscriber(FirebaseMessaging firebaseMessaging2, Metadata metadata2, TopicsStore topicsStore, GmsRpc gmsRpc, Context context2, ScheduledExecutorService scheduledExecutorService) {
        this.firebaseMessaging = firebaseMessaging2;
        this.metadata = metadata2;
        this.store = topicsStore;
        this.rpc = gmsRpc;
        this.context = context2;
        this.syncExecutor = scheduledExecutorService;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> subscribeToTopic(String str) {
        Task<Void> scheduleTopicOperation = scheduleTopicOperation(TopicOperation.subscribe(str));
        startTopicsSyncIfNecessary();
        return scheduleTopicOperation;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> unsubscribeFromTopic(String str) {
        Task<Void> scheduleTopicOperation = scheduleTopicOperation(TopicOperation.unsubscribe(str));
        startTopicsSyncIfNecessary();
        return scheduleTopicOperation;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> scheduleTopicOperation(TopicOperation topicOperation) {
        this.store.addTopicOperation(topicOperation);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        addToPendingOperations(topicOperation, taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    private void addToPendingOperations(TopicOperation topicOperation, TaskCompletionSource<Void> taskCompletionSource) {
        ArrayDeque arrayDeque;
        synchronized (this.pendingOperations) {
            String serialize = topicOperation.serialize();
            if (this.pendingOperations.containsKey(serialize)) {
                arrayDeque = this.pendingOperations.get(serialize);
            } else {
                ArrayDeque arrayDeque2 = new ArrayDeque();
                this.pendingOperations.put(serialize, arrayDeque2);
                arrayDeque = arrayDeque2;
            }
            arrayDeque.add(taskCompletionSource);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasPendingOperation() {
        return this.store.getNextTopicOperation() != null;
    }

    /* access modifiers changed from: package-private */
    public void startTopicsSyncIfNecessary() {
        if (hasPendingOperation()) {
            startSync();
        }
    }

    private void startSync() {
        if (!isSyncScheduledOrRunning()) {
            syncWithDelaySecondsInternal(0);
        }
    }

    /* access modifiers changed from: package-private */
    public void syncWithDelaySecondsInternal(long j) {
        scheduleSyncTaskWithDelaySeconds(new TopicsSyncTask(this, this.context, this.metadata, Math.min(Math.max(30, 2 * j), MAX_DELAY_SEC)), j);
        setSyncScheduledOrRunning(true);
    }

    /* access modifiers changed from: package-private */
    public void scheduleSyncTaskWithDelaySeconds(Runnable runnable, long j) {
        this.syncExecutor.schedule(runnable, j, TimeUnit.SECONDS);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        if (performTopicOperation(r0) != false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean syncTopics() throws java.io.IOException {
        /*
            r2 = this;
        L_0x0000:
            monitor-enter(r2)
            com.google.firebase.messaging.TopicsStore r0 = r2.store     // Catch:{ all -> 0x002b }
            com.google.firebase.messaging.TopicOperation r0 = r0.getNextTopicOperation()     // Catch:{ all -> 0x002b }
            if (r0 != 0) goto L_0x0019
            boolean r0 = isDebugLogEnabled()     // Catch:{ all -> 0x002b }
            if (r0 == 0) goto L_0x0016
            java.lang.String r0 = "FirebaseMessaging"
            java.lang.String r1 = "topic sync succeeded"
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x002b }
        L_0x0016:
            monitor-exit(r2)     // Catch:{ all -> 0x002b }
            r0 = 1
            return r0
        L_0x0019:
            monitor-exit(r2)     // Catch:{ all -> 0x002b }
            boolean r1 = r2.performTopicOperation(r0)
            if (r1 != 0) goto L_0x0022
            r0 = 0
            return r0
        L_0x0022:
            com.google.firebase.messaging.TopicsStore r1 = r2.store
            r1.removeTopicOperation(r0)
            r2.markCompletePendingOperation(r0)
            goto L_0x0000
        L_0x002b:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x002b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.TopicsSubscriber.syncTopics():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void markCompletePendingOperation(com.google.firebase.messaging.TopicOperation r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.String, java.util.ArrayDeque<com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>>> r0 = r4.pendingOperations
            monitor-enter(r0)
            java.lang.String r5 = r5.serialize()     // Catch:{ all -> 0x0032 }
            java.util.Map<java.lang.String, java.util.ArrayDeque<com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>>> r1 = r4.pendingOperations     // Catch:{ all -> 0x0032 }
            boolean r1 = r1.containsKey(r5)     // Catch:{ all -> 0x0032 }
            if (r1 != 0) goto L_0x0011
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x0011:
            java.util.Map<java.lang.String, java.util.ArrayDeque<com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>>> r1 = r4.pendingOperations     // Catch:{ all -> 0x0032 }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x0032 }
            java.util.ArrayDeque r1 = (java.util.ArrayDeque) r1     // Catch:{ all -> 0x0032 }
            java.lang.Object r2 = r1.poll()     // Catch:{ all -> 0x0032 }
            com.google.android.gms.tasks.TaskCompletionSource r2 = (com.google.android.gms.tasks.TaskCompletionSource) r2     // Catch:{ all -> 0x0032 }
            if (r2 == 0) goto L_0x0025
            r3 = 0
            r2.setResult(r3)     // Catch:{ all -> 0x0032 }
        L_0x0025:
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0030
            java.util.Map<java.lang.String, java.util.ArrayDeque<com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>>> r1 = r4.pendingOperations     // Catch:{ all -> 0x0032 }
            r1.remove(r5)     // Catch:{ all -> 0x0032 }
        L_0x0030:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x0032:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.TopicsSubscriber.markCompletePendingOperation(com.google.firebase.messaging.TopicOperation):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0079 A[Catch:{ IOException -> 0x009f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean performTopicOperation(com.google.firebase.messaging.TopicOperation r10) throws java.io.IOException {
        /*
            r9 = this;
            java.lang.String r0 = "FirebaseMessaging"
            java.lang.String r1 = "Unsubscribe from topic: "
            java.lang.String r2 = "Subscribe to topic: "
            java.lang.String r3 = "Unknown topic operation"
            r4 = 0
            java.lang.String r5 = r10.getOperation()     // Catch:{ IOException -> 0x009f }
            int r6 = r5.hashCode()     // Catch:{ IOException -> 0x009f }
            r7 = 83
            r8 = 1
            if (r6 == r7) goto L_0x0025
            r7 = 85
            if (r6 == r7) goto L_0x001b
            goto L_0x002f
        L_0x001b:
            java.lang.String r6 = "U"
            boolean r5 = r5.equals(r6)     // Catch:{ IOException -> 0x009f }
            if (r5 == 0) goto L_0x002f
            r5 = r8
            goto L_0x0030
        L_0x0025:
            java.lang.String r6 = "S"
            boolean r5 = r5.equals(r6)     // Catch:{ IOException -> 0x009f }
            if (r5 == 0) goto L_0x002f
            r5 = r4
            goto L_0x0030
        L_0x002f:
            r5 = -1
        L_0x0030:
            java.lang.String r6 = " succeeded."
            if (r5 == 0) goto L_0x0079
            if (r5 == r8) goto L_0x0053
            boolean r1 = isDebugLogEnabled()     // Catch:{ IOException -> 0x009f }
            if (r1 == 0) goto L_0x009e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x009f }
            r1.<init>(r3)     // Catch:{ IOException -> 0x009f }
            java.lang.StringBuilder r10 = r1.append(r10)     // Catch:{ IOException -> 0x009f }
            java.lang.String r1 = "."
            java.lang.StringBuilder r10 = r10.append(r1)     // Catch:{ IOException -> 0x009f }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x009f }
            android.util.Log.d(r0, r10)     // Catch:{ IOException -> 0x009f }
            goto L_0x009e
        L_0x0053:
            java.lang.String r2 = r10.getTopic()     // Catch:{ IOException -> 0x009f }
            r9.blockingUnsubscribeFromTopic(r2)     // Catch:{ IOException -> 0x009f }
            boolean r2 = isDebugLogEnabled()     // Catch:{ IOException -> 0x009f }
            if (r2 == 0) goto L_0x009e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x009f }
            r2.<init>(r1)     // Catch:{ IOException -> 0x009f }
            java.lang.String r10 = r10.getTopic()     // Catch:{ IOException -> 0x009f }
            java.lang.StringBuilder r10 = r2.append(r10)     // Catch:{ IOException -> 0x009f }
            java.lang.StringBuilder r10 = r10.append(r6)     // Catch:{ IOException -> 0x009f }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x009f }
            android.util.Log.d(r0, r10)     // Catch:{ IOException -> 0x009f }
            goto L_0x009e
        L_0x0079:
            java.lang.String r1 = r10.getTopic()     // Catch:{ IOException -> 0x009f }
            r9.blockingSubscribeToTopic(r1)     // Catch:{ IOException -> 0x009f }
            boolean r1 = isDebugLogEnabled()     // Catch:{ IOException -> 0x009f }
            if (r1 == 0) goto L_0x009e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x009f }
            r1.<init>(r2)     // Catch:{ IOException -> 0x009f }
            java.lang.String r10 = r10.getTopic()     // Catch:{ IOException -> 0x009f }
            java.lang.StringBuilder r10 = r1.append(r10)     // Catch:{ IOException -> 0x009f }
            java.lang.StringBuilder r10 = r10.append(r6)     // Catch:{ IOException -> 0x009f }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x009f }
            android.util.Log.d(r0, r10)     // Catch:{ IOException -> 0x009f }
        L_0x009e:
            return r8
        L_0x009f:
            r10 = move-exception
            java.lang.String r1 = "SERVICE_NOT_AVAILABLE"
            java.lang.String r2 = r10.getMessage()
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00d2
            java.lang.String r1 = "INTERNAL_SERVER_ERROR"
            java.lang.String r2 = r10.getMessage()
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00d2
            java.lang.String r1 = "TOO_MANY_SUBSCRIBERS"
            java.lang.String r2 = r10.getMessage()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00c5
            goto L_0x00d2
        L_0x00c5:
            java.lang.String r1 = r10.getMessage()
            if (r1 != 0) goto L_0x00d1
            java.lang.String r10 = "Topic operation failed without exception message. Will retry Topic operation."
            io.sentry.android.core.SentryLogcatAdapter.e(r0, r10)
            return r4
        L_0x00d1:
            throw r10
        L_0x00d2:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Topic operation failed: "
            r1.<init>(r2)
            java.lang.String r10 = r10.getMessage()
            java.lang.StringBuilder r10 = r1.append(r10)
            java.lang.String r1 = ". Will retry Topic operation."
            java.lang.StringBuilder r10 = r10.append(r1)
            java.lang.String r10 = r10.toString()
            io.sentry.android.core.SentryLogcatAdapter.e(r0, r10)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.TopicsSubscriber.performTopicOperation(com.google.firebase.messaging.TopicOperation):boolean");
    }

    private void blockingSubscribeToTopic(String str) throws IOException {
        awaitTask(this.rpc.subscribeToTopic(this.firebaseMessaging.blockingGetToken(), str));
    }

    private void blockingUnsubscribeFromTopic(String str) throws IOException {
        awaitTask(this.rpc.unsubscribeFromTopic(this.firebaseMessaging.blockingGetToken(), str));
    }

    private static <T> void awaitTask(Task<T> task) throws IOException {
        try {
            Tasks.await(task, 30, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new IOException(e);
            }
        } catch (InterruptedException | TimeoutException e2) {
            throw new IOException("SERVICE_NOT_AVAILABLE", e2);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean isSyncScheduledOrRunning() {
        return this.syncScheduledOrRunning;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setSyncScheduledOrRunning(boolean z) {
        this.syncScheduledOrRunning = z;
    }

    static boolean isDebugLogEnabled() {
        return Log.isLoggable(Constants.TAG, 3);
    }

    /* access modifiers changed from: package-private */
    public TopicsStore getStore() {
        return this.store;
    }
}
