package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.TransactionOptions;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ExponentialBackoff;
import com.google.firebase.firestore.util.Function;

public class TransactionRunner<TResult> {
    private AsyncQueue asyncQueue;
    private int attemptsRemaining;
    private ExponentialBackoff backoff;
    private RemoteStore remoteStore;
    private TaskCompletionSource<TResult> taskSource = new TaskCompletionSource<>();
    private Function<Transaction, Task<TResult>> updateFunction;

    public TransactionRunner(AsyncQueue asyncQueue2, RemoteStore remoteStore2, TransactionOptions transactionOptions, Function<Transaction, Task<TResult>> function) {
        this.asyncQueue = asyncQueue2;
        this.remoteStore = remoteStore2;
        this.updateFunction = function;
        this.attemptsRemaining = transactionOptions.getMaxAttempts();
        this.backoff = new ExponentialBackoff(asyncQueue2, AsyncQueue.TimerId.RETRY_TRANSACTION);
    }

    public Task<TResult> run() {
        runWithBackoff();
        return this.taskSource.getTask();
    }

    private void runWithBackoff() {
        this.attemptsRemaining--;
        this.backoff.backoffAndRun(new TransactionRunner$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$runWithBackoff$2$com-google-firebase-firestore-core-TransactionRunner  reason: not valid java name */
    public /* synthetic */ void m674lambda$runWithBackoff$2$comgooglefirebasefirestorecoreTransactionRunner() {
        Transaction createTransaction = this.remoteStore.createTransaction();
        this.updateFunction.apply(createTransaction).addOnCompleteListener(this.asyncQueue.getExecutor(), new TransactionRunner$$ExternalSyntheticLambda0(this, createTransaction));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$runWithBackoff$1$com-google-firebase-firestore-core-TransactionRunner  reason: not valid java name */
    public /* synthetic */ void m673lambda$runWithBackoff$1$comgooglefirebasefirestorecoreTransactionRunner(Transaction transaction, Task task) {
        if (!task.isSuccessful()) {
            handleTransactionError(task);
        } else {
            transaction.commit().addOnCompleteListener(this.asyncQueue.getExecutor(), new TransactionRunner$$ExternalSyntheticLambda1(this, task));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$runWithBackoff$0$com-google-firebase-firestore-core-TransactionRunner  reason: not valid java name */
    public /* synthetic */ void m672lambda$runWithBackoff$0$comgooglefirebasefirestorecoreTransactionRunner(Task task, Task task2) {
        if (task2.isSuccessful()) {
            this.taskSource.setResult(task.getResult());
        } else {
            handleTransactionError(task2);
        }
    }

    private void handleTransactionError(Task task) {
        if (this.attemptsRemaining <= 0 || !isRetryableTransactionError(task.getException())) {
            this.taskSource.setException(task.getException());
        } else {
            runWithBackoff();
        }
    }

    private static boolean isRetryableTransactionError(Exception exc) {
        if (!(exc instanceof FirebaseFirestoreException)) {
            return false;
        }
        FirebaseFirestoreException firebaseFirestoreException = (FirebaseFirestoreException) exc;
        FirebaseFirestoreException.Code code = firebaseFirestoreException.getCode();
        if (code == FirebaseFirestoreException.Code.ABORTED || code == FirebaseFirestoreException.Code.ALREADY_EXISTS || code == FirebaseFirestoreException.Code.FAILED_PRECONDITION || !Datastore.isPermanentError(firebaseFirestoreException.getCode())) {
            return true;
        }
        return false;
    }
}
