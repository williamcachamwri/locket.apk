package com.google.firebase.storage;

import android.app.Activity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StorageTask.ProvideError;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.Executor;

public abstract class StorageTask<ResultT extends ProvideError> extends ControllableTask<ResultT> {
    static final int INTERNAL_STATE_CANCELED = 256;
    static final int INTERNAL_STATE_CANCELING = 32;
    static final int INTERNAL_STATE_FAILURE = 64;
    static final int INTERNAL_STATE_IN_PROGRESS = 4;
    static final int INTERNAL_STATE_NOT_STARTED = 1;
    static final int INTERNAL_STATE_PAUSED = 16;
    static final int INTERNAL_STATE_PAUSING = 8;
    static final int INTERNAL_STATE_QUEUED = 2;
    static final int INTERNAL_STATE_SUCCESS = 128;
    static final int STATES_CANCELED = 256;
    static final int STATES_COMPLETE = 448;
    static final int STATES_FAILURE = 64;
    static final int STATES_INPROGRESS = -465;
    static final int STATES_PAUSED = 16;
    static final int STATES_SUCCESS = 128;
    private static final String TAG = "StorageTask";
    private static final HashMap<Integer, HashSet<Integer>> ValidTaskInitiatedStateChanges;
    private static final HashMap<Integer, HashSet<Integer>> ValidUserInitiatedStateChanges;
    final TaskListenerImpl<OnCanceledListener, ResultT> cancelManager = new TaskListenerImpl<>(this, 256, new StorageTask$$ExternalSyntheticLambda12(this));
    final TaskListenerImpl<OnCompleteListener<ResultT>, ResultT> completeListener = new TaskListenerImpl<>(this, STATES_COMPLETE, new StorageTask$$ExternalSyntheticLambda11(this));
    private volatile int currentState = 1;
    final TaskListenerImpl<OnFailureListener, ResultT> failureManager = new TaskListenerImpl<>(this, 64, new StorageTask$$ExternalSyntheticLambda10(this));
    private ResultT finalResult;
    final TaskListenerImpl<OnPausedListener<? super ResultT>, ResultT> pausedManager = new TaskListenerImpl<>(this, 16, new StorageTask$$ExternalSyntheticLambda2());
    final TaskListenerImpl<OnProgressListener<? super ResultT>, ResultT> progressManager = new TaskListenerImpl<>(this, STATES_INPROGRESS, new StorageTask$$ExternalSyntheticLambda1());
    final TaskListenerImpl<OnSuccessListener<? super ResultT>, ResultT> successManager = new TaskListenerImpl<>(this, 128, new StorageTask$$ExternalSyntheticLambda9(this));
    protected final Object syncObject = new Object();

    protected interface ProvideError {
        Exception getError();
    }

    private String getStateString(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 8 ? i != 16 ? i != 32 ? i != 64 ? i != 128 ? i != 256 ? "Unknown Internal State!" : "INTERNAL_STATE_CANCELED" : "INTERNAL_STATE_SUCCESS" : "INTERNAL_STATE_FAILURE" : "INTERNAL_STATE_CANCELING" : "INTERNAL_STATE_PAUSED" : "INTERNAL_STATE_PAUSING" : "INTERNAL_STATE_IN_PROGRESS" : "INTERNAL_STATE_QUEUED" : "INTERNAL_STATE_NOT_STARTED";
    }

    /* access modifiers changed from: package-private */
    public abstract StorageReference getStorage();

    /* access modifiers changed from: protected */
    public void onCanceled() {
    }

    /* access modifiers changed from: protected */
    public void onFailure() {
    }

    /* access modifiers changed from: protected */
    public void onPaused() {
    }

    /* access modifiers changed from: protected */
    public void onProgress() {
    }

    /* access modifiers changed from: protected */
    public void onQueued() {
    }

    /* access modifiers changed from: protected */
    public void onSuccess() {
    }

    /* access modifiers changed from: package-private */
    public void resetState() {
    }

    /* access modifiers changed from: package-private */
    public abstract void run();

    /* access modifiers changed from: package-private */
    public abstract void schedule();

    /* access modifiers changed from: package-private */
    public abstract ResultT snapStateImpl();

    static {
        HashMap<Integer, HashSet<Integer>> hashMap = new HashMap<>();
        ValidUserInitiatedStateChanges = hashMap;
        HashMap<Integer, HashSet<Integer>> hashMap2 = new HashMap<>();
        ValidTaskInitiatedStateChanges = hashMap2;
        hashMap.put(1, new HashSet(Arrays.asList(new Integer[]{16, 256})));
        hashMap.put(2, new HashSet(Arrays.asList(new Integer[]{8, 32})));
        hashMap.put(4, new HashSet(Arrays.asList(new Integer[]{8, 32})));
        hashMap.put(16, new HashSet(Arrays.asList(new Integer[]{2, 256})));
        hashMap.put(64, new HashSet(Arrays.asList(new Integer[]{2, 256})));
        hashMap2.put(1, new HashSet(Arrays.asList(new Integer[]{2, 64})));
        hashMap2.put(2, new HashSet(Arrays.asList(new Integer[]{4, 64, 128})));
        hashMap2.put(4, new HashSet(Arrays.asList(new Integer[]{4, 64, 128})));
        hashMap2.put(8, new HashSet(Arrays.asList(new Integer[]{16, 64, 128})));
        hashMap2.put(32, new HashSet(Arrays.asList(new Integer[]{256, 64, 128})));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-google-firebase-storage-StorageTask  reason: not valid java name */
    public /* synthetic */ void m847lambda$new$0$comgooglefirebasestorageStorageTask(OnSuccessListener onSuccessListener, ProvideError provideError) {
        StorageTaskManager.getInstance().unRegister(this);
        onSuccessListener.onSuccess(provideError);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-google-firebase-storage-StorageTask  reason: not valid java name */
    public /* synthetic */ void m848lambda$new$1$comgooglefirebasestorageStorageTask(OnFailureListener onFailureListener, ProvideError provideError) {
        StorageTaskManager.getInstance().unRegister(this);
        onFailureListener.onFailure(provideError.getError());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$2$com-google-firebase-storage-StorageTask  reason: not valid java name */
    public /* synthetic */ void m849lambda$new$2$comgooglefirebasestorageStorageTask(OnCompleteListener onCompleteListener, ProvideError provideError) {
        StorageTaskManager.getInstance().unRegister(this);
        onCompleteListener.onComplete(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$3$com-google-firebase-storage-StorageTask  reason: not valid java name */
    public /* synthetic */ void m850lambda$new$3$comgooglefirebasestorageStorageTask(OnCanceledListener onCanceledListener, ProvideError provideError) {
        StorageTaskManager.getInstance().unRegister(this);
        onCanceledListener.onCanceled();
    }

    protected StorageTask() {
    }

    /* access modifiers changed from: package-private */
    public boolean queue() {
        if (!tryChangeState(2, false)) {
            return false;
        }
        schedule();
        return true;
    }

    public boolean resume() {
        if (!tryChangeState(2, true)) {
            return false;
        }
        resetState();
        schedule();
        return true;
    }

    public boolean pause() {
        return tryChangeState(new int[]{16, 8}, true);
    }

    public boolean cancel() {
        return tryChangeState(new int[]{256, 32}, true);
    }

    public boolean isComplete() {
        return (getInternalState() & STATES_COMPLETE) != 0;
    }

    public boolean isSuccessful() {
        return (getInternalState() & 128) != 0;
    }

    public boolean isCanceled() {
        return getInternalState() == 256;
    }

    public boolean isInProgress() {
        return (getInternalState() & STATES_INPROGRESS) != 0;
    }

    public boolean isPaused() {
        return (getInternalState() & 16) != 0;
    }

    public ResultT getResult() {
        if (getFinalResult() != null) {
            Exception error = getFinalResult().getError();
            if (error == null) {
                return getFinalResult();
            }
            throw new RuntimeExecutionException(error);
        }
        throw new IllegalStateException();
    }

    public <X extends Throwable> ResultT getResult(Class<X> cls) throws Throwable {
        if (getFinalResult() == null) {
            throw new IllegalStateException();
        } else if (!cls.isInstance(getFinalResult().getError())) {
            Exception error = getFinalResult().getError();
            if (error == null) {
                return getFinalResult();
            }
            throw new RuntimeExecutionException(error);
        } else {
            throw ((Throwable) cls.cast(getFinalResult().getError()));
        }
    }

    public Exception getException() {
        if (getFinalResult() == null) {
            return null;
        }
        return getFinalResult().getError();
    }

    public ResultT getSnapshot() {
        return snapState();
    }

    /* access modifiers changed from: package-private */
    public int getInternalState() {
        return this.currentState;
    }

    /* access modifiers changed from: package-private */
    public Object getSyncObject() {
        return this.syncObject;
    }

    /* access modifiers changed from: package-private */
    public ResultT snapState() {
        ResultT snapStateImpl;
        synchronized (this.syncObject) {
            snapStateImpl = snapStateImpl();
        }
        return snapStateImpl;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c2, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean tryChangeState(int[] r9, boolean r10) {
        /*
            r8 = this;
            if (r10 == 0) goto L_0x0005
            java.util.HashMap<java.lang.Integer, java.util.HashSet<java.lang.Integer>> r0 = ValidUserInitiatedStateChanges
            goto L_0x0007
        L_0x0005:
            java.util.HashMap<java.lang.Integer, java.util.HashSet<java.lang.Integer>> r0 = ValidTaskInitiatedStateChanges
        L_0x0007:
            java.lang.Object r1 = r8.syncObject
            monitor-enter(r1)
            int r2 = r9.length     // Catch:{ all -> 0x0100 }
            r3 = 0
            r4 = r3
        L_0x000d:
            if (r4 >= r2) goto L_0x00c8
            r5 = r9[r4]     // Catch:{ all -> 0x0100 }
            int r6 = r8.getInternalState()     // Catch:{ all -> 0x0100 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0100 }
            java.lang.Object r6 = r0.get(r6)     // Catch:{ all -> 0x0100 }
            java.util.HashSet r6 = (java.util.HashSet) r6     // Catch:{ all -> 0x0100 }
            if (r6 == 0) goto L_0x00c4
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0100 }
            boolean r6 = r6.contains(r7)     // Catch:{ all -> 0x0100 }
            if (r6 == 0) goto L_0x00c4
            r8.currentState = r5     // Catch:{ all -> 0x0100 }
            int r9 = r8.currentState     // Catch:{ all -> 0x0100 }
            r0 = 2
            if (r9 == r0) goto L_0x005a
            r0 = 4
            if (r9 == r0) goto L_0x0056
            r0 = 16
            if (r9 == r0) goto L_0x0052
            r0 = 64
            if (r9 == r0) goto L_0x004e
            r0 = 128(0x80, float:1.794E-43)
            if (r9 == r0) goto L_0x004a
            r0 = 256(0x100, float:3.59E-43)
            if (r9 == r0) goto L_0x0046
            goto L_0x0064
        L_0x0046:
            r8.onCanceled()     // Catch:{ all -> 0x0100 }
            goto L_0x0064
        L_0x004a:
            r8.onSuccess()     // Catch:{ all -> 0x0100 }
            goto L_0x0064
        L_0x004e:
            r8.onFailure()     // Catch:{ all -> 0x0100 }
            goto L_0x0064
        L_0x0052:
            r8.onPaused()     // Catch:{ all -> 0x0100 }
            goto L_0x0064
        L_0x0056:
            r8.onProgress()     // Catch:{ all -> 0x0100 }
            goto L_0x0064
        L_0x005a:
            com.google.firebase.storage.StorageTaskManager r9 = com.google.firebase.storage.StorageTaskManager.getInstance()     // Catch:{ all -> 0x0100 }
            r9.ensureRegistered(r8)     // Catch:{ all -> 0x0100 }
            r8.onQueued()     // Catch:{ all -> 0x0100 }
        L_0x0064:
            com.google.firebase.storage.TaskListenerImpl<com.google.android.gms.tasks.OnSuccessListener<? super ResultT>, ResultT> r9 = r8.successManager     // Catch:{ all -> 0x0100 }
            r9.onInternalStateChanged()     // Catch:{ all -> 0x0100 }
            com.google.firebase.storage.TaskListenerImpl<com.google.android.gms.tasks.OnFailureListener, ResultT> r9 = r8.failureManager     // Catch:{ all -> 0x0100 }
            r9.onInternalStateChanged()     // Catch:{ all -> 0x0100 }
            com.google.firebase.storage.TaskListenerImpl<com.google.android.gms.tasks.OnCanceledListener, ResultT> r9 = r8.cancelManager     // Catch:{ all -> 0x0100 }
            r9.onInternalStateChanged()     // Catch:{ all -> 0x0100 }
            com.google.firebase.storage.TaskListenerImpl<com.google.android.gms.tasks.OnCompleteListener<ResultT>, ResultT> r9 = r8.completeListener     // Catch:{ all -> 0x0100 }
            r9.onInternalStateChanged()     // Catch:{ all -> 0x0100 }
            com.google.firebase.storage.TaskListenerImpl<com.google.firebase.storage.OnPausedListener<? super ResultT>, ResultT> r9 = r8.pausedManager     // Catch:{ all -> 0x0100 }
            r9.onInternalStateChanged()     // Catch:{ all -> 0x0100 }
            com.google.firebase.storage.TaskListenerImpl<com.google.firebase.storage.OnProgressListener<? super ResultT>, ResultT> r9 = r8.progressManager     // Catch:{ all -> 0x0100 }
            r9.onInternalStateChanged()     // Catch:{ all -> 0x0100 }
            java.lang.String r9 = "StorageTask"
            r0 = 3
            boolean r9 = android.util.Log.isLoggable(r9, r0)     // Catch:{ all -> 0x0100 }
            if (r9 == 0) goto L_0x00c1
            java.lang.String r9 = "StorageTask"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0100 }
            r0.<init>()     // Catch:{ all -> 0x0100 }
            java.lang.String r2 = "changed internal state to: "
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0100 }
            java.lang.String r2 = r8.getStateString((int) r5)     // Catch:{ all -> 0x0100 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0100 }
            java.lang.String r2 = " isUser: "
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0100 }
            java.lang.StringBuilder r10 = r0.append(r10)     // Catch:{ all -> 0x0100 }
            java.lang.String r0 = " from state:"
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch:{ all -> 0x0100 }
            int r0 = r8.currentState     // Catch:{ all -> 0x0100 }
            java.lang.String r0 = r8.getStateString((int) r0)     // Catch:{ all -> 0x0100 }
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch:{ all -> 0x0100 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0100 }
            android.util.Log.d(r9, r10)     // Catch:{ all -> 0x0100 }
        L_0x00c1:
            monitor-exit(r1)     // Catch:{ all -> 0x0100 }
            r9 = 1
            return r9
        L_0x00c4:
            int r4 = r4 + 1
            goto L_0x000d
        L_0x00c8:
            java.lang.String r0 = "StorageTask"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0100 }
            r2.<init>()     // Catch:{ all -> 0x0100 }
            java.lang.String r4 = "unable to change internal state to: "
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x0100 }
            java.lang.String r9 = r8.getStateString((int[]) r9)     // Catch:{ all -> 0x0100 }
            java.lang.StringBuilder r9 = r2.append(r9)     // Catch:{ all -> 0x0100 }
            java.lang.String r2 = " isUser: "
            java.lang.StringBuilder r9 = r9.append(r2)     // Catch:{ all -> 0x0100 }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x0100 }
            java.lang.String r10 = " from state:"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x0100 }
            int r10 = r8.currentState     // Catch:{ all -> 0x0100 }
            java.lang.String r10 = r8.getStateString((int) r10)     // Catch:{ all -> 0x0100 }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x0100 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0100 }
            io.sentry.android.core.SentryLogcatAdapter.w((java.lang.String) r0, (java.lang.String) r9)     // Catch:{ all -> 0x0100 }
            monitor-exit(r1)     // Catch:{ all -> 0x0100 }
            return r3
        L_0x0100:
            r9 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0100 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.storage.StorageTask.tryChangeState(int[], boolean):boolean");
    }

    /* access modifiers changed from: package-private */
    public boolean tryChangeState(int i, boolean z) {
        return tryChangeState(new int[]{i}, z);
    }

    private ResultT getFinalResult() {
        ResultT resultt = this.finalResult;
        if (resultt != null) {
            return resultt;
        }
        if (!isComplete()) {
            return null;
        }
        if (this.finalResult == null) {
            this.finalResult = snapState();
        }
        return this.finalResult;
    }

    public StorageTask<ResultT> addOnPausedListener(OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        this.pausedManager.addListener((Activity) null, (Executor) null, onPausedListener);
        return this;
    }

    public StorageTask<ResultT> addOnPausedListener(Executor executor, OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        Preconditions.checkNotNull(executor);
        this.pausedManager.addListener((Activity) null, executor, onPausedListener);
        return this;
    }

    public StorageTask<ResultT> addOnPausedListener(Activity activity, OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        Preconditions.checkNotNull(activity);
        this.pausedManager.addListener(activity, (Executor) null, onPausedListener);
        return this;
    }

    public StorageTask<ResultT> removeOnPausedListener(OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        this.pausedManager.m851lambda$addListener$0$comgooglefirebasestorageTaskListenerImpl(onPausedListener);
        return this;
    }

    public StorageTask<ResultT> addOnProgressListener(OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        this.progressManager.addListener((Activity) null, (Executor) null, onProgressListener);
        return this;
    }

    public StorageTask<ResultT> addOnProgressListener(Executor executor, OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        Preconditions.checkNotNull(executor);
        this.progressManager.addListener((Activity) null, executor, onProgressListener);
        return this;
    }

    public StorageTask<ResultT> addOnProgressListener(Activity activity, OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        Preconditions.checkNotNull(activity);
        this.progressManager.addListener(activity, (Executor) null, onProgressListener);
        return this;
    }

    public StorageTask<ResultT> removeOnProgressListener(OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        this.progressManager.m851lambda$addListener$0$comgooglefirebasestorageTaskListenerImpl(onProgressListener);
        return this;
    }

    public StorageTask<ResultT> addOnSuccessListener(OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.addListener((Activity) null, (Executor) null, onSuccessListener);
        return this;
    }

    public StorageTask<ResultT> addOnSuccessListener(Executor executor, OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.addListener((Activity) null, executor, onSuccessListener);
        return this;
    }

    public StorageTask<ResultT> addOnSuccessListener(Activity activity, OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.addListener(activity, (Executor) null, onSuccessListener);
        return this;
    }

    public StorageTask<ResultT> removeOnSuccessListener(OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.m851lambda$addListener$0$comgooglefirebasestorageTaskListenerImpl(onSuccessListener);
        return this;
    }

    public StorageTask<ResultT> addOnFailureListener(OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        this.failureManager.addListener((Activity) null, (Executor) null, onFailureListener);
        return this;
    }

    public StorageTask<ResultT> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        Preconditions.checkNotNull(executor);
        this.failureManager.addListener((Activity) null, executor, onFailureListener);
        return this;
    }

    public StorageTask<ResultT> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        Preconditions.checkNotNull(activity);
        this.failureManager.addListener(activity, (Executor) null, onFailureListener);
        return this;
    }

    public StorageTask<ResultT> removeOnFailureListener(OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        this.failureManager.m851lambda$addListener$0$comgooglefirebasestorageTaskListenerImpl(onFailureListener);
        return this;
    }

    public StorageTask<ResultT> addOnCompleteListener(OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        this.completeListener.addListener((Activity) null, (Executor) null, onCompleteListener);
        return this;
    }

    public StorageTask<ResultT> addOnCompleteListener(Executor executor, OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        Preconditions.checkNotNull(executor);
        this.completeListener.addListener((Activity) null, executor, onCompleteListener);
        return this;
    }

    public StorageTask<ResultT> addOnCompleteListener(Activity activity, OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        Preconditions.checkNotNull(activity);
        this.completeListener.addListener(activity, (Executor) null, onCompleteListener);
        return this;
    }

    public StorageTask<ResultT> removeOnCompleteListener(OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        this.completeListener.m851lambda$addListener$0$comgooglefirebasestorageTaskListenerImpl(onCompleteListener);
        return this;
    }

    public StorageTask<ResultT> addOnCanceledListener(OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        this.cancelManager.addListener((Activity) null, (Executor) null, onCanceledListener);
        return this;
    }

    public StorageTask<ResultT> addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        Preconditions.checkNotNull(executor);
        this.cancelManager.addListener((Activity) null, executor, onCanceledListener);
        return this;
    }

    public StorageTask<ResultT> addOnCanceledListener(Activity activity, OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        Preconditions.checkNotNull(activity);
        this.cancelManager.addListener(activity, (Executor) null, onCanceledListener);
        return this;
    }

    public StorageTask<ResultT> removeOnCanceledListener(OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        this.cancelManager.m851lambda$addListener$0$comgooglefirebasestorageTaskListenerImpl(onCanceledListener);
        return this;
    }

    public <ContinuationResultT> Task<ContinuationResultT> continueWith(Continuation<ResultT, ContinuationResultT> continuation) {
        return continueWithImpl((Executor) null, continuation);
    }

    public <ContinuationResultT> Task<ContinuationResultT> continueWith(Executor executor, Continuation<ResultT, ContinuationResultT> continuation) {
        return continueWithImpl(executor, continuation);
    }

    private <ContinuationResultT> Task<ContinuationResultT> continueWithImpl(Executor executor, Continuation<ResultT, ContinuationResultT> continuation) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.completeListener.addListener((Activity) null, executor, new StorageTask$$ExternalSyntheticLambda6(this, continuation, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$continueWithImpl$4$com-google-firebase-storage-StorageTask  reason: not valid java name */
    public /* synthetic */ void m844lambda$continueWithImpl$4$comgooglefirebasestorageStorageTask(Continuation continuation, TaskCompletionSource taskCompletionSource, Task task) {
        try {
            Object then = continuation.then(this);
            if (!taskCompletionSource.getTask().isComplete()) {
                taskCompletionSource.setResult(then);
            }
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                taskCompletionSource.setException((Exception) e.getCause());
            } else {
                taskCompletionSource.setException(e);
            }
        } catch (Exception e2) {
            taskCompletionSource.setException(e2);
        }
    }

    public <ContinuationResultT> Task<ContinuationResultT> continueWithTask(Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        return continueWithTaskImpl((Executor) null, continuation);
    }

    public <ContinuationResultT> Task<ContinuationResultT> continueWithTask(Executor executor, Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        return continueWithTaskImpl(executor, continuation);
    }

    public <ContinuationResultT> Task<ContinuationResultT> onSuccessTask(SuccessContinuation<ResultT, ContinuationResultT> successContinuation) {
        return successTaskImpl((Executor) null, successContinuation);
    }

    public <ContinuationResultT> Task<ContinuationResultT> onSuccessTask(Executor executor, SuccessContinuation<ResultT, ContinuationResultT> successContinuation) {
        return successTaskImpl(executor, successContinuation);
    }

    private <ContinuationResultT> Task<ContinuationResultT> continueWithTaskImpl(Executor executor, Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
        this.completeListener.addListener((Activity) null, executor, new StorageTask$$ExternalSyntheticLambda8(this, continuation, taskCompletionSource, cancellationTokenSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$continueWithTaskImpl$5$com-google-firebase-storage-StorageTask  reason: not valid java name */
    public /* synthetic */ void m845lambda$continueWithTaskImpl$5$comgooglefirebasestorageStorageTask(Continuation continuation, TaskCompletionSource taskCompletionSource, CancellationTokenSource cancellationTokenSource, Task task) {
        try {
            Task task2 = (Task) continuation.then(this);
            if (taskCompletionSource.getTask().isComplete()) {
                return;
            }
            if (task2 == null) {
                taskCompletionSource.setException(new NullPointerException("Continuation returned null"));
                return;
            }
            Objects.requireNonNull(taskCompletionSource);
            task2.addOnSuccessListener(new StorageTask$$ExternalSyntheticLambda0(taskCompletionSource));
            Objects.requireNonNull(taskCompletionSource);
            task2.addOnFailureListener(new StorageTask$$ExternalSyntheticLambda4(taskCompletionSource));
            Objects.requireNonNull(cancellationTokenSource);
            task2.addOnCanceledListener(new StorageTask$$ExternalSyntheticLambda5(cancellationTokenSource));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                taskCompletionSource.setException((Exception) e.getCause());
            } else {
                taskCompletionSource.setException(e);
            }
        } catch (Exception e2) {
            taskCompletionSource.setException(e2);
        }
    }

    private <ContinuationResultT> Task<ContinuationResultT> successTaskImpl(Executor executor, SuccessContinuation<ResultT, ContinuationResultT> successContinuation) {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
        this.successManager.addListener((Activity) null, executor, new StorageTask$$ExternalSyntheticLambda3(successContinuation, taskCompletionSource, cancellationTokenSource));
        return taskCompletionSource.getTask();
    }

    static /* synthetic */ void lambda$successTaskImpl$6(SuccessContinuation successContinuation, TaskCompletionSource taskCompletionSource, CancellationTokenSource cancellationTokenSource, ProvideError provideError) {
        try {
            Task then = successContinuation.then(provideError);
            Objects.requireNonNull(taskCompletionSource);
            then.addOnSuccessListener(new StorageTask$$ExternalSyntheticLambda0(taskCompletionSource));
            Objects.requireNonNull(taskCompletionSource);
            then.addOnFailureListener(new StorageTask$$ExternalSyntheticLambda4(taskCompletionSource));
            Objects.requireNonNull(cancellationTokenSource);
            then.addOnCanceledListener(new StorageTask$$ExternalSyntheticLambda5(cancellationTokenSource));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                taskCompletionSource.setException((Exception) e.getCause());
            } else {
                taskCompletionSource.setException(e);
            }
        } catch (Exception e2) {
            taskCompletionSource.setException(e2);
        }
    }

    /* access modifiers changed from: package-private */
    public Runnable getRunnable() {
        return new StorageTask$$ExternalSyntheticLambda7(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getRunnable$7$com-google-firebase-storage-StorageTask  reason: not valid java name */
    public /* synthetic */ void m846lambda$getRunnable$7$comgooglefirebasestorageStorageTask() {
        try {
            run();
        } finally {
            ensureFinalState();
        }
    }

    private void ensureFinalState() {
        if (!isComplete() && !isPaused() && getInternalState() != 2 && !tryChangeState(256, false)) {
            tryChangeState(64, false);
        }
    }

    private String getStateString(int[] iArr) {
        if (iArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int stateString : iArr) {
            sb.append(getStateString(stateString)).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    public class SnapshotBase implements ProvideError {
        private final Exception error;

        public SnapshotBase(Exception exc) {
            if (exc != null) {
                this.error = exc;
            } else if (StorageTask.this.isCanceled()) {
                this.error = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
            } else if (StorageTask.this.getInternalState() == 64) {
                this.error = StorageException.fromErrorStatus(Status.RESULT_INTERNAL_ERROR);
            } else {
                this.error = null;
            }
        }

        public StorageTask<ResultT> getTask() {
            return StorageTask.this;
        }

        public StorageReference getStorage() {
            return getTask().getStorage();
        }

        public Exception getError() {
            return this.error;
        }
    }
}
