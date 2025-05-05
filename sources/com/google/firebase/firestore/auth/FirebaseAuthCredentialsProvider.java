package com.google.firebase.firestore.auth;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Listener;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.InternalTokenResult;

public final class FirebaseAuthCredentialsProvider extends CredentialsProvider<User> {
    private static final String LOG_TAG = "FirebaseAuthCredentialsProvider";
    private Listener<User> changeListener;
    private boolean forceRefresh;
    private final IdTokenListener idTokenListener = new FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda0(this);
    private InternalAuthProvider internalAuthProvider;
    private int tokenCounter;

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-google-firebase-firestore-auth-FirebaseAuthCredentialsProvider  reason: not valid java name */
    public /* synthetic */ void m647lambda$new$0$comgooglefirebasefirestoreauthFirebaseAuthCredentialsProvider(InternalTokenResult internalTokenResult) {
        onIdTokenChanged();
    }

    public FirebaseAuthCredentialsProvider(Deferred<InternalAuthProvider> deferred) {
        deferred.whenAvailable(new FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-google-firebase-firestore-auth-FirebaseAuthCredentialsProvider  reason: not valid java name */
    public /* synthetic */ void m648lambda$new$1$comgooglefirebasefirestoreauthFirebaseAuthCredentialsProvider(Provider provider) {
        synchronized (this) {
            this.internalAuthProvider = (InternalAuthProvider) provider.get();
            onIdTokenChanged();
            this.internalAuthProvider.addIdTokenListener(this.idTokenListener);
        }
    }

    public synchronized Task<String> getToken() {
        InternalAuthProvider internalAuthProvider2 = this.internalAuthProvider;
        if (internalAuthProvider2 == null) {
            return Tasks.forException(new FirebaseApiNotAvailableException("auth is not available"));
        }
        Task<GetTokenResult> accessToken = internalAuthProvider2.getAccessToken(this.forceRefresh);
        this.forceRefresh = false;
        return accessToken.continueWithTask(Executors.DIRECT_EXECUTOR, new FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda2(this, this.tokenCounter));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getToken$2$com-google-firebase-firestore-auth-FirebaseAuthCredentialsProvider  reason: not valid java name */
    public /* synthetic */ Task m646lambda$getToken$2$comgooglefirebasefirestoreauthFirebaseAuthCredentialsProvider(int i, Task task) throws Exception {
        synchronized (this) {
            if (i != this.tokenCounter) {
                Logger.debug(LOG_TAG, "getToken aborted due to token change", new Object[0]);
                Task<String> token = getToken();
                return token;
            } else if (task.isSuccessful()) {
                Task forResult = Tasks.forResult(((GetTokenResult) task.getResult()).getToken());
                return forResult;
            } else {
                Task forException = Tasks.forException(task.getException());
                return forException;
            }
        }
    }

    public synchronized void invalidateToken() {
        this.forceRefresh = true;
    }

    public synchronized void setChangeListener(Listener<User> listener) {
        this.changeListener = listener;
        listener.onValue(getUser());
    }

    public synchronized void removeChangeListener() {
        this.changeListener = null;
        InternalAuthProvider internalAuthProvider2 = this.internalAuthProvider;
        if (internalAuthProvider2 != null) {
            internalAuthProvider2.removeIdTokenListener(this.idTokenListener);
        }
    }

    private synchronized void onIdTokenChanged() {
        this.tokenCounter++;
        Listener<User> listener = this.changeListener;
        if (listener != null) {
            listener.onValue(getUser());
        }
    }

    private synchronized User getUser() {
        String uid;
        InternalAuthProvider internalAuthProvider2 = this.internalAuthProvider;
        uid = internalAuthProvider2 == null ? null : internalAuthProvider2.getUid();
        return uid != null ? new User(uid) : User.UNAUTHENTICATED;
    }
}
