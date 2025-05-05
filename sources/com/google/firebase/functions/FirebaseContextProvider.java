package com.google.firebase.functions;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class FirebaseContextProvider implements ContextProvider {
    private final String TAG = "FirebaseContextProvider";
    private final AtomicReference<InteropAppCheckTokenProvider> appCheckRef = new AtomicReference<>();
    private final Executor executor;
    private final Provider<FirebaseInstanceIdInternal> instanceId;
    private final Provider<InternalAuthProvider> tokenProvider;

    static /* synthetic */ void lambda$new$0(AppCheckTokenResult appCheckTokenResult) {
    }

    @Inject
    FirebaseContextProvider(Provider<InternalAuthProvider> provider, Provider<FirebaseInstanceIdInternal> provider2, Deferred<InteropAppCheckTokenProvider> deferred, Executor executor2) {
        this.tokenProvider = provider;
        this.instanceId = provider2;
        this.executor = executor2;
        deferred.whenAvailable(new FirebaseContextProvider$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-google-firebase-functions-FirebaseContextProvider  reason: not valid java name */
    public /* synthetic */ void m771lambda$new$1$comgooglefirebasefunctionsFirebaseContextProvider(Provider provider) {
        InteropAppCheckTokenProvider interopAppCheckTokenProvider = (InteropAppCheckTokenProvider) provider.get();
        this.appCheckRef.set(interopAppCheckTokenProvider);
        interopAppCheckTokenProvider.addAppCheckTokenListener(new FirebaseContextProvider$$ExternalSyntheticLambda2());
    }

    public Task<HttpsCallableContext> getContext(boolean z) {
        Task<String> authToken = getAuthToken();
        Task<String> appCheckToken = getAppCheckToken(z);
        return Tasks.whenAll((Task<?>[]) new Task[]{authToken, appCheckToken}).onSuccessTask(this.executor, new FirebaseContextProvider$$ExternalSyntheticLambda0(this, authToken, appCheckToken));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getContext$2$com-google-firebase-functions-FirebaseContextProvider  reason: not valid java name */
    public /* synthetic */ Task m770lambda$getContext$2$comgooglefirebasefunctionsFirebaseContextProvider(Task task, Task task2, Void voidR) throws Exception {
        return Tasks.forResult(new HttpsCallableContext((String) task.getResult(), this.instanceId.get().getToken(), (String) task2.getResult()));
    }

    private Task<String> getAuthToken() {
        InternalAuthProvider internalAuthProvider = this.tokenProvider.get();
        if (internalAuthProvider == null) {
            return Tasks.forResult(null);
        }
        return internalAuthProvider.getAccessToken(false).continueWith(this.executor, new FirebaseContextProvider$$ExternalSyntheticLambda4());
    }

    static /* synthetic */ String lambda$getAuthToken$3(Task task) throws Exception {
        if (task.isSuccessful()) {
            return ((GetTokenResult) task.getResult()).getToken();
        }
        Exception exception = task.getException();
        if (exception instanceof FirebaseNoSignedInUserException) {
            return null;
        }
        throw exception;
    }

    private Task<String> getAppCheckToken(boolean z) {
        InteropAppCheckTokenProvider interopAppCheckTokenProvider = this.appCheckRef.get();
        if (interopAppCheckTokenProvider == null) {
            return Tasks.forResult(null);
        }
        return (z ? interopAppCheckTokenProvider.getLimitedUseToken() : interopAppCheckTokenProvider.getToken(false)).onSuccessTask(this.executor, new FirebaseContextProvider$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getAppCheckToken$4$com-google-firebase-functions-FirebaseContextProvider  reason: not valid java name */
    public /* synthetic */ Task m769lambda$getAppCheckToken$4$comgooglefirebasefunctionsFirebaseContextProvider(AppCheckTokenResult appCheckTokenResult) throws Exception {
        if (appCheckTokenResult.getError() == null) {
            return Tasks.forResult(appCheckTokenResult.getToken());
        }
        SentryLogcatAdapter.w("FirebaseContextProvider", "Error getting App Check token. Error: " + appCheckTokenResult.getError());
        return Tasks.forResult(null);
    }
}
