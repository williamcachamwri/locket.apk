package com.google.firebase.iid;

import android.os.Looper;
import android.util.Log;
import androidx.webkit.ProxyConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.Store;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.messaging.Constants;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;

@Deprecated
/* compiled from: com.google.firebase:firebase-iid@@21.1.0 */
public class FirebaseInstanceId {
    private static final Pattern API_KEY_FORMAT = Pattern.compile("\\AA[\\w-]{38}\\z");
    private static final long MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8);
    private static Store store;
    static ScheduledExecutorService syncExecutor;
    private final FirebaseApp app;
    final Executor fileIoExecutor;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final Metadata metadata;
    private final List<FirebaseInstanceIdInternal.NewTokenListener> newTokenListeners;
    private final RequestDeduplicator requestDeduplicator;
    private final GmsRpc rpc;
    private boolean syncScheduledOrRunning;

    FirebaseInstanceId(FirebaseApp firebaseApp, Metadata metadata2, Executor executor, Executor executor2, Provider<UserAgentPublisher> provider, Provider<HeartBeatInfo> provider2, FirebaseInstallationsApi firebaseInstallationsApi) {
        this.syncScheduledOrRunning = false;
        this.newTokenListeners = new ArrayList();
        if (Metadata.getDefaultSenderId(firebaseApp) != null) {
            synchronized (FirebaseInstanceId.class) {
                if (store == null) {
                    store = new Store(firebaseApp.getApplicationContext());
                }
            }
            this.app = firebaseApp;
            this.metadata = metadata2;
            this.rpc = new GmsRpc(firebaseApp, metadata2, provider, provider2, firebaseInstallationsApi);
            this.fileIoExecutor = executor2;
            this.requestDeduplicator = new RequestDeduplicator(executor);
            this.firebaseInstallations = firebaseInstallationsApi;
            return;
        }
        throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
    }

    private <T> T awaitTask(Task<T> task) throws IOException {
        try {
            return Tasks.await(task, 30000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(cause.getMessage())) {
                    resetStorage();
                }
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new IOException(e);
            }
        } catch (InterruptedException | TimeoutException unused) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    private static <T> T awaitTaskAllowOnMainThread(Task<T> task) throws InterruptedException {
        Preconditions.checkNotNull(task, "Task must not be null");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        task.addOnCompleteListener(FirebaseInstanceId$$Lambda$1.$instance, (OnCompleteListener<T>) new FirebaseInstanceId$$Lambda$2(countDownLatch));
        countDownLatch.await(30000, TimeUnit.MILLISECONDS);
        return getResultOrThrowException(task);
    }

    private static void checkRequiredFirebaseOptions(FirebaseApp firebaseApp) {
        Preconditions.checkNotEmpty(firebaseApp.getOptions().getProjectId(), "Please set your project ID. A valid Firebase project ID is required to communicate with Firebase server APIs: It identifies your project with Google.");
        Preconditions.checkNotEmpty(firebaseApp.getOptions().getApplicationId(), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.");
        Preconditions.checkNotEmpty(firebaseApp.getOptions().getApiKey(), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.");
        Preconditions.checkArgument(isValidAppIdFormat(firebaseApp.getOptions().getApplicationId()), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        Preconditions.checkArgument(isValidApiKeyFormat(firebaseApp.getOptions().getApiKey()), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.");
    }

    public static synchronized void clearInstancesForTest() {
        synchronized (FirebaseInstanceId.class) {
            ScheduledExecutorService scheduledExecutorService = syncExecutor;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
            }
            syncExecutor = null;
            store = null;
        }
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    private static <T> T getResultOrThrowException(Task<T> task) {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        if (task.isCanceled()) {
            throw new CancellationException("Task is already canceled");
        } else if (task.isComplete()) {
            throw new IllegalStateException(task.getException());
        } else {
            throw new IllegalThreadStateException("Firebase Installations getId Task has timed out.");
        }
    }

    private String getSubtype() {
        if (FirebaseApp.DEFAULT_APP_NAME.equals(this.app.getName())) {
            return "";
        }
        return this.app.getPersistenceKey();
    }

    static boolean isDebugLogEnabled() {
        return Log.isLoggable("FirebaseInstanceId", 3);
    }

    static boolean isValidApiKeyFormat(@Nonnull String str) {
        return API_KEY_FORMAT.matcher(str).matches();
    }

    static boolean isValidAppIdFormat(@Nonnull String str) {
        return str.contains(":");
    }

    private static String rationaliseScope(String str) {
        return (str.isEmpty() || str.equalsIgnoreCase("fcm") || str.equalsIgnoreCase(Constants.MessageTypes.MESSAGE)) ? ProxyConfig.MATCH_ALL_SCHEMES : str;
    }

    private void startSyncIfNecessary() {
        if (tokenNeedsRefresh(getTokenWithoutTriggeringSync())) {
            startSync();
        }
    }

    /* access modifiers changed from: package-private */
    public void addNewTokenListener(FirebaseInstanceIdInternal.NewTokenListener newTokenListener) {
        this.newTokenListeners.add(newTokenListener);
    }

    /* access modifiers changed from: package-private */
    public String blockingGetMasterToken() throws IOException {
        return getToken(Metadata.getDefaultSenderId(this.app), ProxyConfig.MATCH_ALL_SCHEMES);
    }

    @Deprecated
    public void deleteInstanceId() throws IOException {
        checkRequiredFirebaseOptions(this.app);
        if (Looper.getMainLooper() != Looper.myLooper()) {
            awaitTask(this.firebaseInstallations.delete());
            resetStorage();
            return;
        }
        throw new IOException(InstanceID.ERROR_MAIN_THREAD);
    }

    @Deprecated
    public void deleteToken(String str, String str2) throws IOException {
        checkRequiredFirebaseOptions(this.app);
        if (Looper.getMainLooper() != Looper.myLooper()) {
            String rationaliseScope = rationaliseScope(str2);
            awaitTask(this.rpc.deleteToken(getIdWithoutTriggeringSync(), str, rationaliseScope));
            store.deleteToken(getSubtype(), str, rationaliseScope);
            return;
        }
        throw new IOException(InstanceID.ERROR_MAIN_THREAD);
    }

    /* access modifiers changed from: package-private */
    public void enqueueTaskWithDelaySeconds(Runnable runnable, long j) {
        synchronized (FirebaseInstanceId.class) {
            if (syncExecutor == null) {
                syncExecutor = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("FirebaseInstanceId"));
            }
            syncExecutor.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    /* access modifiers changed from: package-private */
    public FirebaseApp getApp() {
        return this.app;
    }

    public long getCreationTime() {
        return store.getCreationTime(this.app.getPersistenceKey());
    }

    @Deprecated
    public String getId() {
        checkRequiredFirebaseOptions(this.app);
        startSyncIfNecessary();
        return getIdWithoutTriggeringSync();
    }

    /* access modifiers changed from: package-private */
    public String getIdWithoutTriggeringSync() {
        try {
            store.setCreationTime(this.app.getPersistenceKey());
            return (String) awaitTaskAllowOnMainThread(this.firebaseInstallations.getId());
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    @Deprecated
    public Task<InstanceIdResult> getInstanceId() {
        checkRequiredFirebaseOptions(this.app);
        return getInstanceId(Metadata.getDefaultSenderId(this.app), ProxyConfig.MATCH_ALL_SCHEMES);
    }

    @Deprecated
    public String getToken() {
        checkRequiredFirebaseOptions(this.app);
        Store.Token tokenWithoutTriggeringSync = getTokenWithoutTriggeringSync();
        if (tokenNeedsRefresh(tokenWithoutTriggeringSync)) {
            startSync();
        }
        return Store.Token.getTokenOrNull(tokenWithoutTriggeringSync);
    }

    /* access modifiers changed from: package-private */
    public Store.Token getTokenWithoutTriggeringSync() {
        return getTokenWithoutTriggeringSync(Metadata.getDefaultSenderId(this.app), ProxyConfig.MATCH_ALL_SCHEMES);
    }

    public boolean isFcmAutoInitEnabled() {
        throw new IllegalStateException("FirebaseMessaging version not supported. Update to latest version.");
    }

    public boolean isGmsCorePresent() {
        return this.metadata.isGmscorePresent();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task lambda$getInstanceId$0$FirebaseInstanceId(String str, String str2, String str3, String str4) throws Exception {
        store.saveToken(getSubtype(), str, str2, str4, this.metadata.getAppVersionCode());
        return Tasks.forResult(new InstanceIdResultImpl(str3, str4));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$getInstanceId$1$FirebaseInstanceId(Store.Token token, InstanceIdResult instanceIdResult) {
        String token2 = instanceIdResult.getToken();
        if (token == null || !token2.equals(token.token)) {
            for (FirebaseInstanceIdInternal.NewTokenListener onNewToken : this.newTokenListeners) {
                onNewToken.onNewToken(token2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task lambda$getInstanceId$2$FirebaseInstanceId(String str, String str2, String str3, Store.Token token) {
        return this.rpc.getToken(str, str2, str3).onSuccessTask(this.fileIoExecutor, new FirebaseInstanceId$$Lambda$4(this, str2, str3, str)).addOnSuccessListener(FirebaseInstanceId$$Lambda$5.$instance, new FirebaseInstanceId$$Lambda$6(this, token));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task lambda$getInstanceId$3$FirebaseInstanceId(String str, String str2, Task task) throws Exception {
        String idWithoutTriggeringSync = getIdWithoutTriggeringSync();
        Store.Token tokenWithoutTriggeringSync = getTokenWithoutTriggeringSync(str, str2);
        if (!tokenNeedsRefresh(tokenWithoutTriggeringSync)) {
            return Tasks.forResult(new InstanceIdResultImpl(idWithoutTriggeringSync, tokenWithoutTriggeringSync.token));
        }
        return this.requestDeduplicator.getOrStartGetTokenRequest(str, str2, new FirebaseInstanceId$$Lambda$3(this, idWithoutTriggeringSync, str, str2, tokenWithoutTriggeringSync));
    }

    /* access modifiers changed from: package-private */
    public synchronized void resetStorage() {
        store.deleteAll();
    }

    public void setFcmAutoInitEnabled(boolean z) {
        throw new IllegalStateException("FirebaseMessaging version not supported. Update to latest version.");
    }

    /* access modifiers changed from: package-private */
    public synchronized void setSyncScheduledOrRunning(boolean z) {
        this.syncScheduledOrRunning = z;
    }

    /* access modifiers changed from: package-private */
    public synchronized void startSync() {
        if (!this.syncScheduledOrRunning) {
            syncWithDelaySecondsInternal(0);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void syncWithDelaySecondsInternal(long j) {
        enqueueTaskWithDelaySeconds(new SyncTask(this, Math.min(Math.max(30, j + j), MAX_DELAY_SEC)), j);
        this.syncScheduledOrRunning = true;
    }

    /* access modifiers changed from: package-private */
    public boolean tokenNeedsRefresh(Store.Token token) {
        return token == null || token.needsRefresh(this.metadata.getAppVersionCode());
    }

    public static FirebaseInstanceId getInstance(FirebaseApp firebaseApp) {
        checkRequiredFirebaseOptions(firebaseApp);
        FirebaseInstanceId firebaseInstanceId = (FirebaseInstanceId) firebaseApp.get(FirebaseInstanceId.class);
        Preconditions.checkNotNull(firebaseInstanceId, "Firebase Instance ID component is not present");
        return firebaseInstanceId;
    }

    /* access modifiers changed from: package-private */
    public Store.Token getTokenWithoutTriggeringSync(String str, String str2) {
        return store.getToken(getSubtype(), str, str2);
    }

    private Task<InstanceIdResult> getInstanceId(String str, String str2) {
        return Tasks.forResult(null).continueWithTask(this.fileIoExecutor, new FirebaseInstanceId$$Lambda$0(this, str, rationaliseScope(str2)));
    }

    @Deprecated
    public String getToken(String str, String str2) throws IOException {
        checkRequiredFirebaseOptions(this.app);
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return ((InstanceIdResult) awaitTask(getInstanceId(str, str2))).getToken();
        }
        throw new IOException(InstanceID.ERROR_MAIN_THREAD);
    }

    FirebaseInstanceId(FirebaseApp firebaseApp, Provider<UserAgentPublisher> provider, Provider<HeartBeatInfo> provider2, FirebaseInstallationsApi firebaseInstallationsApi) {
        this(firebaseApp, new Metadata(firebaseApp.getApplicationContext()), FirebaseIidExecutors.newCachedSingleThreadExecutor(), FirebaseIidExecutors.newCachedSingleThreadExecutor(), provider, provider2, firebaseInstallationsApi);
    }
}
