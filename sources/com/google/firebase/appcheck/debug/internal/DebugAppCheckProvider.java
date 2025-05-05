package com.google.firebase.appcheck.debug.internal;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckProvider;
import com.google.firebase.appcheck.AppCheckToken;
import com.google.firebase.appcheck.debug.InternalDebugSecretProvider;
import com.google.firebase.appcheck.internal.AppCheckTokenResponse;
import com.google.firebase.appcheck.internal.NetworkClient;
import com.google.firebase.appcheck.internal.RetryManager;
import com.google.firebase.inject.Provider;
import java.util.UUID;
import java.util.concurrent.Executor;

public class DebugAppCheckProvider implements AppCheckProvider {
    private static final String TAG = "com.google.firebase.appcheck.debug.internal.DebugAppCheckProvider";
    private static final String UTF_8 = "UTF-8";
    private final Executor blockingExecutor;
    private final Task<String> debugSecretTask;
    private final Executor liteExecutor;
    private final NetworkClient networkClient;
    private final RetryManager retryManager;

    public DebugAppCheckProvider(FirebaseApp firebaseApp, Provider<InternalDebugSecretProvider> provider, Executor executor, Executor executor2, Executor executor3) {
        Task<String> task;
        Preconditions.checkNotNull(firebaseApp);
        this.networkClient = new NetworkClient(firebaseApp);
        this.liteExecutor = executor;
        this.blockingExecutor = executor3;
        this.retryManager = new RetryManager();
        String debugSecret = provider.get() != null ? provider.get().getDebugSecret() : null;
        if (debugSecret == null) {
            task = determineDebugSecret(firebaseApp, executor2);
        } else {
            task = Tasks.forResult(debugSecret);
        }
        this.debugSecretTask = task;
    }

    DebugAppCheckProvider(String str, NetworkClient networkClient2, Executor executor, Executor executor2, RetryManager retryManager2) {
        this.networkClient = networkClient2;
        this.liteExecutor = executor;
        this.blockingExecutor = executor2;
        this.retryManager = retryManager2;
        this.debugSecretTask = Tasks.forResult(str);
    }

    static Task<String> determineDebugSecret(FirebaseApp firebaseApp, Executor executor) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        executor.execute(new DebugAppCheckProvider$$ExternalSyntheticLambda1(firebaseApp, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    static /* synthetic */ void lambda$determineDebugSecret$0(FirebaseApp firebaseApp, TaskCompletionSource taskCompletionSource) {
        StorageHelper storageHelper = new StorageHelper(firebaseApp.getApplicationContext(), firebaseApp.getPersistenceKey());
        String retrieveDebugSecret = storageHelper.retrieveDebugSecret();
        if (retrieveDebugSecret == null) {
            retrieveDebugSecret = UUID.randomUUID().toString();
            storageHelper.saveDebugSecret(retrieveDebugSecret);
        }
        Log.d(TAG, "Enter this debug secret into the allow list in the Firebase Console for your project: " + retrieveDebugSecret);
        taskCompletionSource.setResult(retrieveDebugSecret);
    }

    public Task<AppCheckToken> getToken() {
        return this.debugSecretTask.onSuccessTask(this.liteExecutor, new DebugAppCheckProvider$$ExternalSyntheticLambda2(this)).onSuccessTask(this.liteExecutor, new DebugAppCheckProvider$$ExternalSyntheticLambda3());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getToken$2$com-google-firebase-appcheck-debug-internal-DebugAppCheckProvider  reason: not valid java name */
    public /* synthetic */ Task m571lambda$getToken$2$comgooglefirebaseappcheckdebuginternalDebugAppCheckProvider(String str) throws Exception {
        return Tasks.call(this.blockingExecutor, new DebugAppCheckProvider$$ExternalSyntheticLambda0(this, new ExchangeDebugTokenRequest(str)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getToken$1$com-google-firebase-appcheck-debug-internal-DebugAppCheckProvider  reason: not valid java name */
    public /* synthetic */ AppCheckTokenResponse m570lambda$getToken$1$comgooglefirebaseappcheckdebuginternalDebugAppCheckProvider(ExchangeDebugTokenRequest exchangeDebugTokenRequest) throws Exception {
        return this.networkClient.exchangeAttestationForAppCheckToken(exchangeDebugTokenRequest.toJsonString().getBytes("UTF-8"), 2, this.retryManager);
    }
}
