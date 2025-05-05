package com.google.firebase.firestore.core;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import java.util.concurrent.Executor;

public class AsyncEventListener<T> implements EventListener<T> {
    private final EventListener<T> eventListener;
    private final Executor executor;
    private volatile boolean muted = false;

    public AsyncEventListener(Executor executor2, EventListener<T> eventListener2) {
        this.executor = executor2;
        this.eventListener = eventListener2;
    }

    public void onEvent(T t, FirebaseFirestoreException firebaseFirestoreException) {
        this.executor.execute(new AsyncEventListener$$ExternalSyntheticLambda0(this, t, firebaseFirestoreException));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onEvent$0$com-google-firebase-firestore-core-AsyncEventListener  reason: not valid java name */
    public /* synthetic */ void m649lambda$onEvent$0$comgooglefirebasefirestorecoreAsyncEventListener(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
        if (!this.muted) {
            this.eventListener.onEvent(obj, firebaseFirestoreException);
        }
    }

    public void mute() {
        this.muted = true;
    }
}
