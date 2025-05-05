package io.invertase.firebase.storage;

import android.net.Uri;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

public class ReactNativeFirebaseStorageModule extends ReactNativeFirebaseModule {
    private static final String TAG = "Storage";
    private static HashMap<String, String> emulatorConfigs = new HashMap<>();

    ReactNativeFirebaseStorageModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, TAG);
    }

    public void invalidate() {
        ReactNativeFirebaseStorageTask.destroyAllTasks();
        super.invalidate();
    }

    @ReactMethod
    public void delete(String str, String str2, Promise promise) {
        try {
            getReferenceFromUrl(str2, str).delete().addOnCompleteListener(new ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda6(promise));
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    static /* synthetic */ void lambda$delete$0(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve((Object) null);
        } else {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, (Exception) Objects.requireNonNull(task.getException()));
        }
    }

    @ReactMethod
    public void getDownloadURL(String str, String str2, Promise promise) {
        try {
            getReferenceFromUrl(str2, str).getDownloadUrl().addOnCompleteListener(new ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda1(promise));
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    static /* synthetic */ void lambda$getDownloadURL$1(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult() != null ? ((Uri) task.getResult()).toString() : null);
        } else {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
        }
    }

    @ReactMethod
    public void getMetadata(String str, String str2, Promise promise) {
        try {
            getReferenceFromUrl(str2, str).getMetadata().addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda3(promise));
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    static /* synthetic */ void lambda$getMetadata$2(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(ReactNativeFirebaseStorageCommon.getMetadataAsMap((StorageMetadata) task.getResult()));
        } else {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
        }
    }

    @ReactMethod
    public void list(String str, String str2, ReadableMap readableMap, Promise promise) {
        Task<ListResult> task;
        try {
            StorageReference referenceFromUrl = getReferenceFromUrl(str2, str);
            int i = readableMap.getInt("maxResults");
            if (readableMap.hasKey("pageToken")) {
                task = referenceFromUrl.list(i, (String) Objects.requireNonNull(readableMap.getString("pageToken")));
            } else {
                task = referenceFromUrl.list(i);
            }
            task.addOnCompleteListener((Executor) getExecutor(), (OnCompleteListener<ListResult>) new ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda5(promise));
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    static /* synthetic */ void lambda$list$3(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(ReactNativeFirebaseStorageCommon.getListResultAsMap((ListResult) Objects.requireNonNull((ListResult) task.getResult())));
        } else {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
        }
    }

    @ReactMethod
    public void listAll(String str, String str2, Promise promise) {
        try {
            getReferenceFromUrl(str2, str).listAll().addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda4(promise));
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    static /* synthetic */ void lambda$listAll$4(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(ReactNativeFirebaseStorageCommon.getListResultAsMap((ListResult) Objects.requireNonNull((ListResult) task.getResult())));
        } else {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
        }
    }

    private void dumpMetadata(StorageMetadata storageMetadata) {
        System.err.println("STORAGE dumping metadata contents");
        System.err.println("STORAGE - cacheControl " + storageMetadata.getCacheControl());
        System.err.println("STORAGE - contentDisposition " + storageMetadata.getContentDisposition());
        System.err.println("STORAGE - contentEncoding " + storageMetadata.getContentEncoding());
        System.err.println("STORAGE - contentLanguage " + storageMetadata.getContentLanguage());
        System.err.println("STORAGE - contentType " + storageMetadata.getContentType());
        for (String next : storageMetadata.getCustomMetadataKeys()) {
            System.err.println("STORAGE - customMetadata: '" + next + "' / '" + storageMetadata.getCustomMetadata(next) + "'");
        }
    }

    @ReactMethod
    public void updateMetadata(String str, String str2, ReadableMap readableMap, Promise promise) {
        try {
            StorageReference referenceFromUrl = getReferenceFromUrl(str2, str);
            referenceFromUrl.getMetadata().addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda0(this, readableMap, referenceFromUrl, promise));
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateMetadata$6(ReadableMap readableMap, StorageReference storageReference, Promise promise, Task task) {
        if (task.isSuccessful()) {
            storageReference.updateMetadata(ReactNativeFirebaseStorageCommon.buildMetadataFromMap(readableMap, (Uri) null, (StorageMetadata) task.getResult())).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda2(promise));
        } else {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
        }
    }

    static /* synthetic */ void lambda$updateMetadata$5(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(ReactNativeFirebaseStorageCommon.getMetadataAsMap((StorageMetadata) task.getResult()));
            return;
        }
        task.getException().printStackTrace();
        ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
    }

    @ReactMethod
    public void setMaxDownloadRetryTime(String str, double d, Promise promise) {
        FirebaseStorage.getInstance(FirebaseApp.getInstance(str)).setMaxDownloadRetryTimeMillis((long) d);
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void setMaxOperationRetryTime(String str, double d, Promise promise) {
        FirebaseStorage.getInstance(FirebaseApp.getInstance(str)).setMaxOperationRetryTimeMillis((long) d);
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void setMaxUploadRetryTime(String str, double d, Promise promise) {
        FirebaseStorage.getInstance(FirebaseApp.getInstance(str)).setMaxUploadRetryTimeMillis((long) d);
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void useEmulator(String str, String str2, int i, String str3, Promise promise) {
        FirebaseStorage instance = FirebaseStorage.getInstance(FirebaseApp.getInstance(str), str3);
        String str4 = str + ":" + str3;
        if (emulatorConfigs.get(str4) == null) {
            instance.useEmulator(str2, i);
            emulatorConfigs.put(str4, "true");
        }
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void writeToFile(String str, String str2, String str3, int i, Promise promise) {
        if (!ReactNativeFirebaseStorageCommon.isExternalStorageWritable()) {
            rejectPromiseWithCodeAndMessage(promise, "invalid-device-file-path", "The specified device file path is invalid or is restricted.");
            return;
        }
        try {
            ReactNativeFirebaseStorageDownloadTask reactNativeFirebaseStorageDownloadTask = new ReactNativeFirebaseStorageDownloadTask(i, getReferenceFromUrl(str2, str), str);
            reactNativeFirebaseStorageDownloadTask.begin(getTransactionalExecutor(), str3);
            reactNativeFirebaseStorageDownloadTask.addOnCompleteListener(getTransactionalExecutor(), promise);
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    @ReactMethod
    public void putString(String str, String str2, String str3, String str4, ReadableMap readableMap, int i, Promise promise) {
        try {
            ReactNativeFirebaseStorageUploadTask reactNativeFirebaseStorageUploadTask = new ReactNativeFirebaseStorageUploadTask(i, getReferenceFromUrl(str2, str), str);
            reactNativeFirebaseStorageUploadTask.begin(getTransactionalExecutor(), str3, str4, readableMap);
            reactNativeFirebaseStorageUploadTask.addOnCompleteListener(getTransactionalExecutor(), promise);
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    @ReactMethod
    public void putFile(String str, String str2, String str3, ReadableMap readableMap, int i, Promise promise) {
        try {
            ReactNativeFirebaseStorageUploadTask reactNativeFirebaseStorageUploadTask = new ReactNativeFirebaseStorageUploadTask(i, getReferenceFromUrl(str2, str), str);
            reactNativeFirebaseStorageUploadTask.begin(getTransactionalExecutor(), str3, readableMap);
            reactNativeFirebaseStorageUploadTask.addOnCompleteListener(getTransactionalExecutor(), promise);
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    @ReactMethod
    public void setTaskStatus(String str, int i, int i2, Promise promise) {
        if (i2 == 0) {
            promise.resolve(Boolean.valueOf(ReactNativeFirebaseStorageTask.pauseTaskById(i)));
        } else if (i2 == 1) {
            promise.resolve(Boolean.valueOf(ReactNativeFirebaseStorageTask.resumeTaskById(i)));
        } else if (i2 == 2) {
            promise.resolve(Boolean.valueOf(ReactNativeFirebaseStorageTask.cancelTaskById(i)));
        }
    }

    private String getBucketFromUrl(String str) {
        return str.substring(0, str.substring(5).indexOf("/") + 5);
    }

    private StorageReference getReferenceFromUrl(String str, String str2) throws IllegalArgumentException {
        return FirebaseStorage.getInstance(FirebaseApp.getInstance(str2), getBucketFromUrl(str)).getReferenceFromUrl(str);
    }

    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        if (FirebaseApp.getApps(getReactApplicationContext()).size() > 0) {
            FirebaseStorage instance = FirebaseStorage.getInstance();
            hashMap.put("maxDownloadRetryTime", Long.valueOf(instance.getMaxDownloadRetryTimeMillis()));
            hashMap.put("maxOperationRetryTime", Long.valueOf(instance.getMaxOperationRetryTimeMillis()));
            hashMap.put("maxUploadRetryTime", Long.valueOf(instance.getMaxUploadRetryTimeMillis()));
        }
        return hashMap;
    }
}
