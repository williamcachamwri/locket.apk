package io.invertase.firebase.firestore;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.PersistentCacheIndexManager;
import io.invertase.firebase.common.RCTConvertFirebase;
import io.invertase.firebase.common.ReactNativeFirebaseModule;

public class ReactNativeFirebaseFirestoreModule extends ReactNativeFirebaseModule {
    private static final String SERVICE_NAME = "Firestore";
    private final UniversalFirebaseFirestoreModule module;

    ReactNativeFirebaseFirestoreModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE_NAME);
        this.module = new UniversalFirebaseFirestoreModule(reactApplicationContext, SERVICE_NAME);
    }

    @ReactMethod
    public void setLogLevel(String str) {
        if ("debug".equals(str) || "error".equals(str)) {
            FirebaseFirestore.setLoggingEnabled(true);
        } else {
            FirebaseFirestore.setLoggingEnabled(false);
        }
    }

    @ReactMethod
    public void loadBundle(String str, String str2, String str3, Promise promise) {
        this.module.loadBundle(str, str2, str3).addOnCompleteListener(new ReactNativeFirebaseFirestoreModule$$ExternalSyntheticLambda5(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBundle$0(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(taskProgressToWritableMap((LoadBundleTaskProgress) task.getResult()));
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void clearPersistence(String str, String str2, Promise promise) {
        this.module.clearPersistence(str, str2).addOnCompleteListener(new ReactNativeFirebaseFirestoreModule$$ExternalSyntheticLambda3(promise));
    }

    static /* synthetic */ void lambda$clearPersistence$1(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve((Object) null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void waitForPendingWrites(String str, String str2, Promise promise) {
        this.module.waitForPendingWrites(str, str2).addOnCompleteListener(new ReactNativeFirebaseFirestoreModule$$ExternalSyntheticLambda0(promise));
    }

    static /* synthetic */ void lambda$waitForPendingWrites$2(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve((Object) null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void disableNetwork(String str, String str2, Promise promise) {
        this.module.disableNetwork(str, str2).addOnCompleteListener(new ReactNativeFirebaseFirestoreModule$$ExternalSyntheticLambda2(promise));
    }

    static /* synthetic */ void lambda$disableNetwork$3(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve((Object) null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void enableNetwork(String str, String str2, Promise promise) {
        this.module.enableNetwork(str, str2).addOnCompleteListener(new ReactNativeFirebaseFirestoreModule$$ExternalSyntheticLambda6(promise));
    }

    static /* synthetic */ void lambda$enableNetwork$4(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve((Object) null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void useEmulator(String str, String str2, String str3, int i, Promise promise) {
        this.module.useEmulator(str, str2, str3, i).addOnCompleteListener(new ReactNativeFirebaseFirestoreModule$$ExternalSyntheticLambda1(promise));
    }

    static /* synthetic */ void lambda$useEmulator$5(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve((Object) null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void settings(String str, String str2, ReadableMap readableMap, Promise promise) {
        this.module.settings(UniversalFirebaseFirestoreCommon.createFirestoreKey(str, str2), RCTConvertFirebase.toHashMap(readableMap)).addOnCompleteListener(new ReactNativeFirebaseFirestoreModule$$ExternalSyntheticLambda4(promise));
    }

    static /* synthetic */ void lambda$settings$6(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve((Object) null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void terminate(String str, String str2, Promise promise) {
        this.module.terminate(str, str2).addOnCompleteListener(new ReactNativeFirebaseFirestoreModule$$ExternalSyntheticLambda7(promise));
    }

    static /* synthetic */ void lambda$terminate$7(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve((Object) null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void persistenceCacheIndexManager(String str, String str2, int i, Promise promise) {
        PersistentCacheIndexManager persistentCacheIndexManager = UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2).getPersistentCacheIndexManager();
        if (persistentCacheIndexManager != null) {
            if (i == 0) {
                persistentCacheIndexManager.enableIndexAutoCreation();
            } else if (i == 1) {
                persistentCacheIndexManager.disableIndexAutoCreation();
            } else if (i == 2) {
                persistentCacheIndexManager.deleteAllIndexes();
            }
            promise.resolve((Object) null);
            return;
        }
        promise.reject("firestore/index-manager-null", "`PersistentCacheIndexManager` is not available, persistence has not been enabled for Firestore");
    }

    private WritableMap taskProgressToWritableMap(LoadBundleTaskProgress loadBundleTaskProgress) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("bytesLoaded", (double) loadBundleTaskProgress.getBytesLoaded());
        createMap.putInt("documentsLoaded", loadBundleTaskProgress.getDocumentsLoaded());
        createMap.putDouble("totalBytes", (double) loadBundleTaskProgress.getTotalBytes());
        createMap.putInt("totalDocuments", loadBundleTaskProgress.getTotalDocuments());
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState[loadBundleTaskProgress.getTaskState().ordinal()];
        String str = "Running";
        if (i != 1) {
            if (i == 2) {
                str = "Success";
            } else if (i == 3) {
                str = "Error";
            }
        }
        createMap.putString("taskState", str);
        return createMap;
    }

    /* renamed from: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreModule$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.firebase.firestore.LoadBundleTaskProgress$TaskState[] r0 = com.google.firebase.firestore.LoadBundleTaskProgress.TaskState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState = r0
                com.google.firebase.firestore.LoadBundleTaskProgress$TaskState r1 = com.google.firebase.firestore.LoadBundleTaskProgress.TaskState.RUNNING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.LoadBundleTaskProgress$TaskState r1 = com.google.firebase.firestore.LoadBundleTaskProgress.TaskState.SUCCESS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.firestore.LoadBundleTaskProgress$TaskState r1 = com.google.firebase.firestore.LoadBundleTaskProgress.TaskState.ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreModule.AnonymousClass1.<clinit>():void");
        }
    }
}
