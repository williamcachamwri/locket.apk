package com.google.firebase.firestore;

import androidx.core.util.Consumer;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Function;
import java.util.concurrent.Executor;

final class FirestoreClientProvider {
    private AsyncQueue asyncQueue = new AsyncQueue();
    private FirestoreClient client;
    private final Function<AsyncQueue, FirestoreClient> clientFactory;

    FirestoreClientProvider(Function<AsyncQueue, FirestoreClient> function) {
        this.clientFactory = function;
    }

    /* access modifiers changed from: package-private */
    public boolean isConfigured() {
        return this.client != null;
    }

    /* access modifiers changed from: package-private */
    public synchronized void ensureConfigured() {
        if (!isConfigured()) {
            this.client = this.clientFactory.apply(this.asyncQueue);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized <T> T call(Function<FirestoreClient, T> function) {
        ensureConfigured();
        return function.apply(this.client);
    }

    /* access modifiers changed from: package-private */
    public synchronized void procedure(Consumer<FirestoreClient> consumer) {
        ensureConfigured();
        consumer.accept(this.client);
    }

    /* access modifiers changed from: package-private */
    public synchronized <T> T executeIfShutdown(Function<Executor, T> function, Function<Executor, T> function2) {
        FirestoreClientProvider$$ExternalSyntheticLambda0 firestoreClientProvider$$ExternalSyntheticLambda0 = new FirestoreClientProvider$$ExternalSyntheticLambda0(this);
        FirestoreClient firestoreClient = this.client;
        if (firestoreClient != null) {
            if (!firestoreClient.isTerminated()) {
                return function2.apply(firestoreClientProvider$$ExternalSyntheticLambda0);
            }
        }
        return function.apply(firestoreClientProvider$$ExternalSyntheticLambda0);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$executeIfShutdown$0$com-google-firebase-firestore-FirestoreClientProvider  reason: not valid java name */
    public /* synthetic */ void m634lambda$executeIfShutdown$0$comgooglefirebasefirestoreFirestoreClientProvider(Runnable runnable) {
        this.asyncQueue.enqueueAndForgetEvenAfterShutdown(runnable);
    }

    /* access modifiers changed from: package-private */
    public synchronized Task<Void> terminate() {
        Task<Void> terminate;
        ensureConfigured();
        terminate = this.client.terminate();
        this.asyncQueue.shutdown();
        return terminate;
    }

    /* access modifiers changed from: package-private */
    public AsyncQueue getAsyncQueue() {
        return this.asyncQueue;
    }
}
