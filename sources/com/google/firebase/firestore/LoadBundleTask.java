package com.google.firebase.firestore;

import android.app.Activity;
import com.google.android.gms.common.api.internal.ActivityLifecycleObserver;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

public class LoadBundleTask extends Task<LoadBundleTaskProgress> {
    private final TaskCompletionSource<LoadBundleTaskProgress> completionSource;
    private final Task<LoadBundleTaskProgress> delegate;
    private final Object lock = new Object();
    private final Queue<ManagedListener> progressListeners;
    private LoadBundleTaskProgress snapshot = LoadBundleTaskProgress.INITIAL;

    public LoadBundleTask() {
        TaskCompletionSource<LoadBundleTaskProgress> taskCompletionSource = new TaskCompletionSource<>();
        this.completionSource = taskCompletionSource;
        this.delegate = taskCompletionSource.getTask();
        this.progressListeners = new ArrayDeque();
    }

    public boolean isComplete() {
        return this.delegate.isComplete();
    }

    public boolean isSuccessful() {
        return this.delegate.isSuccessful();
    }

    public boolean isCanceled() {
        return this.delegate.isCanceled();
    }

    public LoadBundleTaskProgress getResult() {
        return this.delegate.getResult();
    }

    public <X extends Throwable> LoadBundleTaskProgress getResult(Class<X> cls) throws Throwable {
        return this.delegate.getResult(cls);
    }

    public Exception getException() {
        return this.delegate.getException();
    }

    public Task<LoadBundleTaskProgress> addOnSuccessListener(OnSuccessListener<? super LoadBundleTaskProgress> onSuccessListener) {
        return this.delegate.addOnSuccessListener(onSuccessListener);
    }

    public Task<LoadBundleTaskProgress> addOnSuccessListener(Executor executor, OnSuccessListener<? super LoadBundleTaskProgress> onSuccessListener) {
        return this.delegate.addOnSuccessListener(executor, onSuccessListener);
    }

    public Task<LoadBundleTaskProgress> addOnSuccessListener(Activity activity, OnSuccessListener<? super LoadBundleTaskProgress> onSuccessListener) {
        return this.delegate.addOnSuccessListener(activity, onSuccessListener);
    }

    public Task<LoadBundleTaskProgress> addOnFailureListener(OnFailureListener onFailureListener) {
        return this.delegate.addOnFailureListener(onFailureListener);
    }

    public Task<LoadBundleTaskProgress> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        return this.delegate.addOnFailureListener(executor, onFailureListener);
    }

    public Task<LoadBundleTaskProgress> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        return this.delegate.addOnFailureListener(activity, onFailureListener);
    }

    public Task<LoadBundleTaskProgress> addOnCompleteListener(OnCompleteListener<LoadBundleTaskProgress> onCompleteListener) {
        return this.delegate.addOnCompleteListener(onCompleteListener);
    }

    public Task<LoadBundleTaskProgress> addOnCompleteListener(Executor executor, OnCompleteListener<LoadBundleTaskProgress> onCompleteListener) {
        return this.delegate.addOnCompleteListener(executor, onCompleteListener);
    }

    public Task<LoadBundleTaskProgress> addOnCompleteListener(Activity activity, OnCompleteListener<LoadBundleTaskProgress> onCompleteListener) {
        return this.delegate.addOnCompleteListener(activity, onCompleteListener);
    }

    public Task<LoadBundleTaskProgress> addOnCanceledListener(OnCanceledListener onCanceledListener) {
        return this.delegate.addOnCanceledListener(onCanceledListener);
    }

    public Task<LoadBundleTaskProgress> addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        return this.delegate.addOnCanceledListener(executor, onCanceledListener);
    }

    public Task<LoadBundleTaskProgress> addOnCanceledListener(Activity activity, OnCanceledListener onCanceledListener) {
        return this.delegate.addOnCanceledListener(activity, onCanceledListener);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<LoadBundleTaskProgress, TContinuationResult> continuation) {
        return this.delegate.continueWith(continuation);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Executor executor, Continuation<LoadBundleTaskProgress, TContinuationResult> continuation) {
        return this.delegate.continueWith(executor, continuation);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<LoadBundleTaskProgress, Task<TContinuationResult>> continuation) {
        return this.delegate.continueWithTask(continuation);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Executor executor, Continuation<LoadBundleTaskProgress, Task<TContinuationResult>> continuation) {
        return this.delegate.continueWithTask(executor, continuation);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(SuccessContinuation<LoadBundleTaskProgress, TContinuationResult> successContinuation) {
        return this.delegate.onSuccessTask(successContinuation);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Executor executor, SuccessContinuation<LoadBundleTaskProgress, TContinuationResult> successContinuation) {
        return this.delegate.onSuccessTask(executor, successContinuation);
    }

    public LoadBundleTask addOnProgressListener(OnProgressListener<LoadBundleTaskProgress> onProgressListener) {
        ManagedListener managedListener = new ManagedListener((Executor) null, onProgressListener);
        synchronized (this.lock) {
            this.progressListeners.add(managedListener);
        }
        return this;
    }

    public LoadBundleTask addOnProgressListener(Executor executor, OnProgressListener<LoadBundleTaskProgress> onProgressListener) {
        ManagedListener managedListener = new ManagedListener(executor, onProgressListener);
        synchronized (this.lock) {
            this.progressListeners.add(managedListener);
        }
        return this;
    }

    public LoadBundleTask addOnProgressListener(Activity activity, OnProgressListener<LoadBundleTaskProgress> onProgressListener) {
        ManagedListener managedListener = new ManagedListener((Executor) null, onProgressListener);
        synchronized (this.lock) {
            this.progressListeners.add(managedListener);
        }
        ActivityLifecycleObserver.of(activity).onStopCallOnce(new LoadBundleTask$$ExternalSyntheticLambda0(this, onProgressListener));
        return this;
    }

    /* access modifiers changed from: private */
    /* renamed from: removeOnProgressListener */
    public void m636lambda$addOnProgressListener$0$comgooglefirebasefirestoreLoadBundleTask(OnProgressListener<LoadBundleTaskProgress> onProgressListener) {
        synchronized (this.lock) {
            this.progressListeners.remove(new ManagedListener((Executor) null, onProgressListener));
        }
    }

    public void setResult(LoadBundleTaskProgress loadBundleTaskProgress) {
        Assert.hardAssert(loadBundleTaskProgress.getTaskState().equals(LoadBundleTaskProgress.TaskState.SUCCESS), "Expected success, but was " + loadBundleTaskProgress.getTaskState(), new Object[0]);
        synchronized (this.lock) {
            this.snapshot = loadBundleTaskProgress;
            for (ManagedListener invokeAsync : this.progressListeners) {
                invokeAsync.invokeAsync(this.snapshot);
            }
            this.progressListeners.clear();
        }
        this.completionSource.setResult(loadBundleTaskProgress);
    }

    public void setException(Exception exc) {
        synchronized (this.lock) {
            LoadBundleTaskProgress loadBundleTaskProgress = new LoadBundleTaskProgress(this.snapshot.getDocumentsLoaded(), this.snapshot.getTotalDocuments(), this.snapshot.getBytesLoaded(), this.snapshot.getTotalBytes(), exc, LoadBundleTaskProgress.TaskState.ERROR);
            this.snapshot = loadBundleTaskProgress;
            for (ManagedListener invokeAsync : this.progressListeners) {
                invokeAsync.invokeAsync(loadBundleTaskProgress);
            }
            this.progressListeners.clear();
        }
        this.completionSource.setException(exc);
    }

    public void updateProgress(LoadBundleTaskProgress loadBundleTaskProgress) {
        synchronized (this.lock) {
            this.snapshot = loadBundleTaskProgress;
            for (ManagedListener invokeAsync : this.progressListeners) {
                invokeAsync.invokeAsync(loadBundleTaskProgress);
            }
        }
    }

    private static class ManagedListener {
        Executor executor;
        OnProgressListener<LoadBundleTaskProgress> listener;

        ManagedListener(Executor executor2, OnProgressListener<LoadBundleTaskProgress> onProgressListener) {
            this.executor = executor2 == null ? TaskExecutors.MAIN_THREAD : executor2;
            this.listener = onProgressListener;
        }

        public void invokeAsync(LoadBundleTaskProgress loadBundleTaskProgress) {
            this.executor.execute(new LoadBundleTask$ManagedListener$$ExternalSyntheticLambda0(this, loadBundleTaskProgress));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$invokeAsync$0$com-google-firebase-firestore-LoadBundleTask$ManagedListener  reason: not valid java name */
        public /* synthetic */ void m637lambda$invokeAsync$0$comgooglefirebasefirestoreLoadBundleTask$ManagedListener(LoadBundleTaskProgress loadBundleTaskProgress) {
            this.listener.onProgress(loadBundleTaskProgress);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.listener.equals(((ManagedListener) obj).listener);
        }

        public int hashCode() {
            return this.listener.hashCode();
        }
    }
}
