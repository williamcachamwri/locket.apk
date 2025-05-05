package com.facebook.imagepipeline.producers;

import com.facebook.common.logging.FLog;
import com.facebook.common.time.MonotonicClock;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import io.sentry.ProfilingTraceData;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public class PriorityNetworkFetcher<FETCH_STATE extends FetchState> implements NetworkFetcher<PriorityFetchState<FETCH_STATE>> {
    static final int INFINITE_REQUEUE = -1;
    static final int NO_DELAYED_REQUESTS = -1;
    public static final String TAG = "PriorityNetworkFetcher";
    /* access modifiers changed from: private */
    public final boolean doNotCancelRequests;
    private long firstDelayedRequestEnqueuedTimeStamp;
    private final int immediateRequeueCount;
    /* access modifiers changed from: private */
    public final boolean inflightFetchesCanBeCancelled;
    private volatile boolean isRunning;
    private final MonotonicClock mClock;
    /* access modifiers changed from: private */
    public final HashSet<PriorityFetchState<FETCH_STATE>> mCurrentlyFetching;
    private final LinkedList<PriorityFetchState<FETCH_STATE>> mDelayedQueue;
    private final NetworkFetcher<FETCH_STATE> mDelegate;
    private final LinkedList<PriorityFetchState<FETCH_STATE>> mHiPriQueue;
    private final boolean mIsHiPriFifo;
    private final Object mLock;
    private final LinkedList<PriorityFetchState<FETCH_STATE>> mLowPriQueue;
    private final int mMaxOutstandingHiPri;
    private final int mMaxOutstandingLowPri;
    /* access modifiers changed from: private */
    public final int maxAttemptCount;
    /* access modifiers changed from: private */
    public final int maxConnectAttemptCount;
    /* access modifiers changed from: private */
    public final int maxNumberOfRequeue;
    private final boolean multipleDequeue;
    /* access modifiers changed from: private */
    public final boolean nonRecoverableExceptionPreventsRequeue;
    private final long requeueDelayTimeInMillis;
    /* access modifiers changed from: private */
    public final boolean retryLowPriAll;
    /* access modifiers changed from: private */
    public final boolean retryLowPriConnectionException;
    /* access modifiers changed from: private */
    public final boolean retryLowPriUnknownHostException;

    public PriorityNetworkFetcher(NetworkFetcher<FETCH_STATE> networkFetcher, boolean z, int i, int i2, boolean z2, int i3, boolean z3, int i4, int i5, boolean z4, boolean z5, int i6, int i7, boolean z6, boolean z7, boolean z8) {
        this(networkFetcher, z, i, i2, z2, i3, z3, i4, i5, z4, z5, i6, i7, z6, z7, z8, RealtimeSinceBootClock.get());
    }

    public PriorityNetworkFetcher(NetworkFetcher<FETCH_STATE> networkFetcher, boolean z, int i, int i2, boolean z2, int i3, boolean z3, int i4, int i5, boolean z4, boolean z5, int i6, int i7, boolean z6, boolean z7, boolean z8, MonotonicClock monotonicClock) {
        int i8 = i;
        int i9 = i2;
        this.mLock = new Object();
        this.mHiPriQueue = new LinkedList<>();
        this.mLowPriQueue = new LinkedList<>();
        this.mCurrentlyFetching = new HashSet<>();
        this.mDelayedQueue = new LinkedList<>();
        this.isRunning = true;
        this.mDelegate = networkFetcher;
        this.mIsHiPriFifo = z;
        this.mMaxOutstandingHiPri = i8;
        this.mMaxOutstandingLowPri = i9;
        if (i8 > i9) {
            this.inflightFetchesCanBeCancelled = z2;
            this.maxNumberOfRequeue = i3;
            this.doNotCancelRequests = z3;
            this.immediateRequeueCount = i4;
            this.requeueDelayTimeInMillis = (long) i5;
            this.multipleDequeue = z4;
            this.nonRecoverableExceptionPreventsRequeue = z5;
            this.maxConnectAttemptCount = i6;
            this.maxAttemptCount = i7;
            this.retryLowPriAll = z6;
            this.retryLowPriUnknownHostException = z7;
            this.retryLowPriConnectionException = z8;
            this.mClock = monotonicClock;
            return;
        }
        throw new IllegalArgumentException("maxOutstandingHiPri should be > maxOutstandingLowPri");
    }

    public void pause() {
        this.isRunning = false;
    }

    public void resume() {
        this.isRunning = true;
        dequeueIfAvailableSlots();
    }

    public void fetch(final PriorityFetchState<FETCH_STATE> priorityFetchState, final NetworkFetcher.Callback callback) {
        priorityFetchState.getContext().addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                if (!PriorityNetworkFetcher.this.doNotCancelRequests) {
                    if (PriorityNetworkFetcher.this.inflightFetchesCanBeCancelled || !PriorityNetworkFetcher.this.mCurrentlyFetching.contains(priorityFetchState)) {
                        PriorityNetworkFetcher.this.removeFromQueue(priorityFetchState, "CANCEL");
                        callback.onCancellation();
                    }
                }
            }

            public void onPriorityChanged() {
                PriorityNetworkFetcher priorityNetworkFetcher = PriorityNetworkFetcher.this;
                PriorityFetchState priorityFetchState = priorityFetchState;
                priorityNetworkFetcher.changePriority(priorityFetchState, priorityFetchState.getContext().getPriority() == Priority.HIGH);
            }
        });
        synchronized (this.mLock) {
            if (this.mCurrentlyFetching.contains(priorityFetchState)) {
                FLog.e(TAG, "fetch state was enqueued twice: " + priorityFetchState);
                return;
            }
            boolean z = priorityFetchState.getContext().getPriority() == Priority.HIGH;
            FLog.v(TAG, "enqueue: %s %s", (Object) z ? "HI-PRI" : "LOW-PRI", (Object) priorityFetchState.getUri());
            priorityFetchState.callback = callback;
            putInQueue(priorityFetchState, z);
            dequeueIfAvailableSlots();
        }
    }

    public void onFetchCompletion(PriorityFetchState<FETCH_STATE> priorityFetchState, int i) {
        removeFromQueue(priorityFetchState, "SUCCESS");
        this.mDelegate.onFetchCompletion(priorityFetchState.delegatedState, i);
    }

    /* access modifiers changed from: private */
    public void removeFromQueue(PriorityFetchState<FETCH_STATE> priorityFetchState, String str) {
        synchronized (this.mLock) {
            FLog.v(TAG, "remove: %s %s", (Object) str, (Object) priorityFetchState.getUri());
            this.mCurrentlyFetching.remove(priorityFetchState);
            if (!this.mHiPriQueue.remove(priorityFetchState)) {
                this.mLowPriQueue.remove(priorityFetchState);
            }
        }
        dequeueIfAvailableSlots();
    }

    private void moveDelayedRequestsToPriorityQueues() {
        if (!this.mDelayedQueue.isEmpty() && this.mClock.now() - this.firstDelayedRequestEnqueuedTimeStamp > this.requeueDelayTimeInMillis) {
            Iterator it = this.mDelayedQueue.iterator();
            while (it.hasNext()) {
                PriorityFetchState priorityFetchState = (PriorityFetchState) it.next();
                putInQueue(priorityFetchState, priorityFetchState.getContext().getPriority() == Priority.HIGH);
            }
            this.mDelayedQueue.clear();
        }
    }

    private void putInDelayedQueue(PriorityFetchState<FETCH_STATE> priorityFetchState) {
        if (this.mDelayedQueue.isEmpty()) {
            this.firstDelayedRequestEnqueuedTimeStamp = this.mClock.now();
        }
        priorityFetchState.delayCount++;
        this.mDelayedQueue.addLast(priorityFetchState);
    }

    /* access modifiers changed from: private */
    public void retry(PriorityFetchState<FETCH_STATE> priorityFetchState) {
        FLog.v(TAG, "retry: %s", (Object) priorityFetchState.getUri());
        synchronized (this.mLock) {
            priorityFetchState.delegatedState = this.mDelegate.createFetchState(priorityFetchState.getConsumer(), priorityFetchState.getContext());
            this.mCurrentlyFetching.remove(priorityFetchState);
            if (!this.mHiPriQueue.remove(priorityFetchState)) {
                this.mLowPriQueue.remove(priorityFetchState);
            }
            this.mHiPriQueue.addFirst(priorityFetchState);
        }
        dequeueIfAvailableSlots();
    }

    /* access modifiers changed from: private */
    public void requeue(PriorityFetchState<FETCH_STATE> priorityFetchState) {
        synchronized (this.mLock) {
            FLog.v(TAG, "requeue: %s", (Object) priorityFetchState.getUri());
            boolean z = true;
            priorityFetchState.requeueCount++;
            priorityFetchState.delegatedState = this.mDelegate.createFetchState(priorityFetchState.getConsumer(), priorityFetchState.getContext());
            this.mCurrentlyFetching.remove(priorityFetchState);
            if (!this.mHiPriQueue.remove(priorityFetchState)) {
                this.mLowPriQueue.remove(priorityFetchState);
            }
            if (this.immediateRequeueCount == -1 || priorityFetchState.requeueCount <= this.immediateRequeueCount) {
                if (priorityFetchState.getContext().getPriority() != Priority.HIGH) {
                    z = false;
                }
                putInQueue(priorityFetchState, z);
            } else {
                putInDelayedQueue(priorityFetchState);
            }
        }
        dequeueIfAvailableSlots();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0062, code lost:
        delegateFetch(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0067, code lost:
        if (r10.multipleDequeue == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0069, code lost:
        dequeueIfAvailableSlots();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void dequeueIfAvailableSlots() {
        /*
            r10 = this;
            boolean r0 = r10.isRunning
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.Object r0 = r10.mLock
            monitor-enter(r0)
            r10.moveDelayedRequestsToPriorityQueues()     // Catch:{ all -> 0x006d }
            java.util.HashSet<com.facebook.imagepipeline.producers.PriorityNetworkFetcher$PriorityFetchState<FETCH_STATE>> r1 = r10.mCurrentlyFetching     // Catch:{ all -> 0x006d }
            int r1 = r1.size()     // Catch:{ all -> 0x006d }
            int r2 = r10.mMaxOutstandingHiPri     // Catch:{ all -> 0x006d }
            if (r1 >= r2) goto L_0x001e
            java.util.LinkedList<com.facebook.imagepipeline.producers.PriorityNetworkFetcher$PriorityFetchState<FETCH_STATE>> r2 = r10.mHiPriQueue     // Catch:{ all -> 0x006d }
            java.lang.Object r2 = r2.pollFirst()     // Catch:{ all -> 0x006d }
            com.facebook.imagepipeline.producers.PriorityNetworkFetcher$PriorityFetchState r2 = (com.facebook.imagepipeline.producers.PriorityNetworkFetcher.PriorityFetchState) r2     // Catch:{ all -> 0x006d }
            goto L_0x001f
        L_0x001e:
            r2 = 0
        L_0x001f:
            if (r2 != 0) goto L_0x002d
            int r3 = r10.mMaxOutstandingLowPri     // Catch:{ all -> 0x006d }
            if (r1 >= r3) goto L_0x002d
            java.util.LinkedList<com.facebook.imagepipeline.producers.PriorityNetworkFetcher$PriorityFetchState<FETCH_STATE>> r2 = r10.mLowPriQueue     // Catch:{ all -> 0x006d }
            java.lang.Object r2 = r2.pollFirst()     // Catch:{ all -> 0x006d }
            com.facebook.imagepipeline.producers.PriorityNetworkFetcher$PriorityFetchState r2 = (com.facebook.imagepipeline.producers.PriorityNetworkFetcher.PriorityFetchState) r2     // Catch:{ all -> 0x006d }
        L_0x002d:
            if (r2 != 0) goto L_0x0031
            monitor-exit(r0)     // Catch:{ all -> 0x006d }
            return
        L_0x0031:
            com.facebook.common.time.MonotonicClock r3 = r10.mClock     // Catch:{ all -> 0x006d }
            long r3 = r3.now()     // Catch:{ all -> 0x006d }
            r2.dequeuedTimestamp = r3     // Catch:{ all -> 0x006d }
            java.util.HashSet<com.facebook.imagepipeline.producers.PriorityNetworkFetcher$PriorityFetchState<FETCH_STATE>> r3 = r10.mCurrentlyFetching     // Catch:{ all -> 0x006d }
            r3.add(r2)     // Catch:{ all -> 0x006d }
            java.lang.String r4 = TAG     // Catch:{ all -> 0x006d }
            java.lang.String r5 = "fetching: %s (concurrent: %s hi-pri queue: %s low-pri queue: %s)"
            android.net.Uri r6 = r2.getUri()     // Catch:{ all -> 0x006d }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x006d }
            java.util.LinkedList<com.facebook.imagepipeline.producers.PriorityNetworkFetcher$PriorityFetchState<FETCH_STATE>> r1 = r10.mHiPriQueue     // Catch:{ all -> 0x006d }
            int r1 = r1.size()     // Catch:{ all -> 0x006d }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x006d }
            java.util.LinkedList<com.facebook.imagepipeline.producers.PriorityNetworkFetcher$PriorityFetchState<FETCH_STATE>> r1 = r10.mLowPriQueue     // Catch:{ all -> 0x006d }
            int r1 = r1.size()     // Catch:{ all -> 0x006d }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x006d }
            com.facebook.common.logging.FLog.v((java.lang.String) r4, (java.lang.String) r5, (java.lang.Object) r6, (java.lang.Object) r7, (java.lang.Object) r8, (java.lang.Object) r9)     // Catch:{ all -> 0x006d }
            monitor-exit(r0)     // Catch:{ all -> 0x006d }
            r10.delegateFetch(r2)
            boolean r0 = r10.multipleDequeue
            if (r0 == 0) goto L_0x006c
            r10.dequeueIfAvailableSlots()
        L_0x006c:
            return
        L_0x006d:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x006d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PriorityNetworkFetcher.dequeueIfAvailableSlots():void");
    }

    private void delegateFetch(final PriorityFetchState<FETCH_STATE> priorityFetchState) {
        try {
            AnonymousClass2 r0 = new NetworkFetcher.Callback() {
                public void onResponse(InputStream inputStream, int i) throws IOException {
                    NetworkFetcher.Callback callback = priorityFetchState.callback;
                    if (callback != null) {
                        callback.onResponse(inputStream, i);
                    }
                }

                public void onFailure(Throwable th) {
                    if (PriorityNetworkFetcher.shouldRetry(th, priorityFetchState.attemptCount, PriorityNetworkFetcher.this.maxConnectAttemptCount, PriorityNetworkFetcher.this.maxAttemptCount, PriorityNetworkFetcher.this.retryLowPriAll, PriorityNetworkFetcher.this.retryLowPriUnknownHostException, PriorityNetworkFetcher.this.retryLowPriConnectionException, priorityFetchState.getContext().getPriority())) {
                        PriorityNetworkFetcher.this.retry(priorityFetchState);
                        return;
                    }
                    if (!(PriorityNetworkFetcher.this.maxNumberOfRequeue == -1 || priorityFetchState.requeueCount < PriorityNetworkFetcher.this.maxNumberOfRequeue) || (PriorityNetworkFetcher.this.nonRecoverableExceptionPreventsRequeue && (th instanceof NonrecoverableException))) {
                        PriorityNetworkFetcher.this.removeFromQueue(priorityFetchState, "FAIL");
                        NetworkFetcher.Callback callback = priorityFetchState.callback;
                        if (callback != null) {
                            callback.onFailure(th);
                            return;
                        }
                        return;
                    }
                    PriorityNetworkFetcher.this.requeue(priorityFetchState);
                }

                public void onCancellation() {
                    PriorityNetworkFetcher.this.removeFromQueue(priorityFetchState, "CANCEL");
                    NetworkFetcher.Callback callback = priorityFetchState.callback;
                    if (callback != null) {
                        callback.onCancellation();
                    }
                }
            };
            priorityFetchState.attemptCount++;
            this.mDelegate.fetch(priorityFetchState.delegatedState, r0);
        } catch (Exception unused) {
            removeFromQueue(priorityFetchState, "FAIL");
        }
    }

    private void changePriorityInDelayedQueue(PriorityFetchState<FETCH_STATE> priorityFetchState) {
        if (this.mDelayedQueue.remove(priorityFetchState)) {
            priorityFetchState.priorityChangedCount++;
            this.mDelayedQueue.addLast(priorityFetchState);
        }
    }

    /* access modifiers changed from: private */
    public void changePriority(PriorityFetchState<FETCH_STATE> priorityFetchState, boolean z) {
        synchronized (this.mLock) {
            if (!(z ? this.mLowPriQueue : this.mHiPriQueue).remove(priorityFetchState)) {
                changePriorityInDelayedQueue(priorityFetchState);
                return;
            }
            FLog.v(TAG, "change-pri: %s %s", (Object) z ? "HIPRI" : "LOWPRI", (Object) priorityFetchState.getUri());
            priorityFetchState.priorityChangedCount++;
            putInQueue(priorityFetchState, z);
            dequeueIfAvailableSlots();
        }
    }

    private void putInQueue(PriorityFetchState<FETCH_STATE> priorityFetchState, boolean z) {
        if (!z) {
            this.mLowPriQueue.addLast(priorityFetchState);
        } else if (this.mIsHiPriFifo) {
            this.mHiPriQueue.addLast(priorityFetchState);
        } else {
            this.mHiPriQueue.addFirst(priorityFetchState);
        }
    }

    /* access modifiers changed from: package-private */
    public List<PriorityFetchState<FETCH_STATE>> getHiPriQueue() {
        return this.mHiPriQueue;
    }

    /* access modifiers changed from: package-private */
    public List<PriorityFetchState<FETCH_STATE>> getLowPriQueue() {
        return this.mLowPriQueue;
    }

    /* access modifiers changed from: package-private */
    public List<PriorityFetchState<FETCH_STATE>> getDelayedQeueue() {
        return this.mDelayedQueue;
    }

    /* access modifiers changed from: package-private */
    public HashSet<PriorityFetchState<FETCH_STATE>> getCurrentlyFetching() {
        return this.mCurrentlyFetching;
    }

    public static class PriorityFetchState<FETCH_STATE extends FetchState> extends FetchState {
        int attemptCount;
        @Nullable
        NetworkFetcher.Callback callback;
        final int currentlyFetchingCountWhenCreated;
        int delayCount;
        public FETCH_STATE delegatedState;
        long dequeuedTimestamp;
        final long enqueuedTimestamp;
        final int hiPriCountWhenCreated;
        final boolean isInitialPriorityHigh;
        final int lowPriCountWhenCreated;
        int priorityChangedCount;
        int requeueCount;

        private PriorityFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext, FETCH_STATE fetch_state, long j, int i, int i2, int i3) {
            super(consumer, producerContext);
            boolean z = false;
            this.requeueCount = 0;
            this.attemptCount = 0;
            this.delayCount = 0;
            this.priorityChangedCount = 0;
            this.delegatedState = fetch_state;
            this.enqueuedTimestamp = j;
            this.hiPriCountWhenCreated = i;
            this.lowPriCountWhenCreated = i2;
            this.isInitialPriorityHigh = producerContext.getPriority() == Priority.HIGH ? true : z;
            this.currentlyFetchingCountWhenCreated = i3;
        }
    }

    public static class NonrecoverableException extends Throwable {
        public NonrecoverableException(String str) {
            super(str);
        }
    }

    public static class RetriableIOException extends IOException {
        public RetriableIOException(Throwable th) {
            super(th);
        }

        public String toString() {
            Throwable cause = getCause();
            return cause != null ? cause.toString() : toString();
        }
    }

    public PriorityFetchState<FETCH_STATE> createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        return new PriorityFetchState(consumer, producerContext, this.mDelegate.createFetchState(consumer, producerContext), this.mClock.now(), this.mHiPriQueue.size(), this.mLowPriQueue.size(), this.mCurrentlyFetching.size());
    }

    public boolean shouldPropagate(PriorityFetchState<FETCH_STATE> priorityFetchState) {
        return this.mDelegate.shouldPropagate(priorityFetchState.delegatedState);
    }

    @Nullable
    public Map<String, String> getExtraMap(PriorityFetchState<FETCH_STATE> priorityFetchState, int i) {
        HashMap hashMap;
        Map<String, String> extraMap = this.mDelegate.getExtraMap(priorityFetchState.delegatedState, i);
        if (extraMap == null) {
            hashMap = new HashMap();
        }
        hashMap.put("pri_queue_time", "" + (priorityFetchState.dequeuedTimestamp - priorityFetchState.enqueuedTimestamp));
        hashMap.put("hipri_queue_size", "" + priorityFetchState.hiPriCountWhenCreated);
        hashMap.put("lowpri_queue_size", "" + priorityFetchState.lowPriCountWhenCreated);
        hashMap.put("requeueCount", "" + priorityFetchState.requeueCount);
        hashMap.put("priority_changed_count", "" + priorityFetchState.priorityChangedCount);
        hashMap.put("request_initial_priority_is_high", "" + priorityFetchState.isInitialPriorityHigh);
        hashMap.put("currently_fetching_size", "" + priorityFetchState.currentlyFetchingCountWhenCreated);
        hashMap.put("delay_count", "" + priorityFetchState.delayCount);
        return hashMap;
    }

    private static boolean shouldRetryLowPrioritySpecificExceptions(Throwable th, boolean z, boolean z2) {
        if (th instanceof UnknownHostException) {
            return z;
        }
        if (th instanceof ConnectException) {
            return z2;
        }
        return false;
    }

    public static boolean shouldRetry(Throwable th, int i, int i2, int i3, boolean z, boolean z2, boolean z3, Priority priority) {
        boolean z4 = th instanceof ConnectException;
        if ((z4 && i >= i2) || i >= i3) {
            return false;
        }
        boolean z5 = priority == Priority.HIGH;
        if (!z && !z5) {
            return shouldRetryLowPrioritySpecificExceptions(th, z2, z3);
        }
        if ((z5 && (th instanceof NonrecoverableException)) || (th instanceof SocketTimeoutException) || (th instanceof UnknownHostException) || z4 || (th instanceof RetriableIOException)) {
            return true;
        }
        String message = th.getMessage();
        if (message == null) {
            return false;
        }
        boolean z6 = th instanceof IOException;
        if ((!z6 || !message.contains("Canceled")) && ((!z6 || !message.contains("unexpected end of stream on null")) && ((!(th instanceof SocketException) || !message.contains("Socket closed")) && (!z5 || !(th instanceof InterruptedIOException) || !message.contains(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT))))) {
            return false;
        }
        return true;
    }
}
