package io.invertase.firebase.firestore;

import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Source;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.perf.FirebasePerformance;
import io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import io.sentry.protocol.SentryStackTrace;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class ReactNativeFirebaseFirestoreDocumentModule extends ReactNativeFirebaseModule {
    private static final String SERVICE_NAME = "FirestoreDocument";
    private static SparseArray<ListenerRegistration> documentSnapshotListeners = new SparseArray<>();

    ReactNativeFirebaseFirestoreDocumentModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE_NAME);
    }

    public void invalidate() {
        super.invalidate();
        int size = documentSnapshotListeners.size();
        for (int i = 0; i < size; i++) {
            documentSnapshotListeners.get(documentSnapshotListeners.keyAt(i)).remove();
        }
        documentSnapshotListeners.clear();
    }

    @ReactMethod
    public void documentOnSnapshot(String str, String str2, String str3, int i, ReadableMap readableMap) {
        MetadataChanges metadataChanges;
        if (documentSnapshotListeners.get(i) == null) {
            DocumentReference documentForFirestore = UniversalFirebaseFirestoreCommon.getDocumentForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2), str3);
            ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda0 reactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda0 = new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda0(this, i, str, str2);
            if (readableMap == null || !readableMap.hasKey("includeMetadataChanges") || !readableMap.getBoolean("includeMetadataChanges")) {
                metadataChanges = MetadataChanges.EXCLUDE;
            } else {
                metadataChanges = MetadataChanges.INCLUDE;
            }
            documentSnapshotListeners.put(i, documentForFirestore.addSnapshotListener(metadataChanges, (EventListener<DocumentSnapshot>) reactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda0));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$documentOnSnapshot$0(int i, String str, String str2, DocumentSnapshot documentSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            ListenerRegistration listenerRegistration = documentSnapshotListeners.get(i);
            if (listenerRegistration != null) {
                listenerRegistration.remove();
                documentSnapshotListeners.remove(i);
            }
            sendOnSnapshotError(str, str2, i, firebaseFirestoreException);
            return;
        }
        sendOnSnapshotEvent(str, str2, i, documentSnapshot);
    }

    @ReactMethod
    public void documentOffSnapshot(String str, String str2, int i) {
        ListenerRegistration listenerRegistration = documentSnapshotListeners.get(i);
        if (listenerRegistration != null) {
            listenerRegistration.remove();
            documentSnapshotListeners.remove(i);
        }
    }

    @ReactMethod
    public void documentGet(String str, String str2, String str3, ReadableMap readableMap, Promise promise) {
        Source source;
        DocumentReference documentForFirestore = UniversalFirebaseFirestoreCommon.getDocumentForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2), str3);
        if (readableMap == null || !readableMap.hasKey("source")) {
            source = Source.DEFAULT;
        } else {
            String string = readableMap.getString("source");
            if ("server".equals(string)) {
                source = Source.SERVER;
            } else if ("cache".equals(string)) {
                source = Source.CACHE;
            } else {
                source = Source.DEFAULT;
            }
        }
        Tasks.call(getExecutor(), new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda5(documentForFirestore, source, str, str2)).addOnCompleteListener(new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda6(promise));
    }

    static /* synthetic */ void lambda$documentGet$2(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void documentDelete(String str, String str2, String str3, Promise promise) {
        DocumentReference documentForFirestore = UniversalFirebaseFirestoreCommon.getDocumentForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2), str3);
        ExecutorService transactionalExecutor = getTransactionalExecutor();
        Objects.requireNonNull(documentForFirestore);
        Tasks.call(transactionalExecutor, new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda15(documentForFirestore)).addOnCompleteListener(new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda1(promise));
    }

    static /* synthetic */ void lambda$documentDelete$3(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve((Object) null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void documentSet(String str, String str2, String str3, ReadableMap readableMap, ReadableMap readableMap2, Promise promise) {
        FirebaseFirestore firestoreForApp = UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2);
        Tasks.call(getTransactionalExecutor(), new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda12(firestoreForApp, readableMap)).continueWithTask(getTransactionalExecutor(), new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda13(readableMap2, UniversalFirebaseFirestoreCommon.getDocumentForFirestore(firestoreForApp, str3))).addOnCompleteListener(new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda14(promise));
    }

    static /* synthetic */ Task lambda$documentSet$5(ReadableMap readableMap, DocumentReference documentReference, Task task) throws Exception {
        Map map = (Map) Objects.requireNonNull((Map) task.getResult());
        if (readableMap.hasKey("merge") && readableMap.getBoolean("merge")) {
            return documentReference.set(map, SetOptions.merge());
        }
        if (!readableMap.hasKey("mergeFields")) {
            return documentReference.set(map);
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Object> it = ((ReadableArray) Objects.requireNonNull(readableMap.getArray("mergeFields"))).toArrayList().iterator();
        while (it.hasNext()) {
            arrayList.add((String) it.next());
        }
        return documentReference.set(map, SetOptions.mergeFields((List<String>) arrayList));
    }

    static /* synthetic */ void lambda$documentSet$6(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve((Object) null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void documentUpdate(String str, String str2, String str3, ReadableMap readableMap, Promise promise) {
        FirebaseFirestore firestoreForApp = UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2);
        Tasks.call(getTransactionalExecutor(), new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda9(firestoreForApp, readableMap)).continueWithTask(getTransactionalExecutor(), new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda10(UniversalFirebaseFirestoreCommon.getDocumentForFirestore(firestoreForApp, str3))).addOnCompleteListener(new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda11(promise));
    }

    static /* synthetic */ void lambda$documentUpdate$9(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve((Object) null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void documentBatch(String str, String str2, ReadableArray readableArray, Promise promise) {
        FirebaseFirestore firestoreForApp = UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2);
        Tasks.call(getTransactionalExecutor(), new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda2(firestoreForApp, readableArray)).continueWithTask(getTransactionalExecutor(), new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda3(firestoreForApp)).addOnCompleteListener(new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda4(promise));
    }

    static /* synthetic */ Task lambda$documentBatch$11(FirebaseFirestore firebaseFirestore, Task task) throws Exception {
        WriteBatch batch = firebaseFirestore.batch();
        for (Map map : (List) task.getResult()) {
            Map map2 = (Map) map.get("data");
            DocumentReference documentForFirestore = UniversalFirebaseFirestoreCommon.getDocumentForFirestore(firebaseFirestore, (String) map.get("path"));
            String str = (String) Objects.requireNonNull((String) map.get("type"));
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1785516855:
                    if (str.equals("UPDATE")) {
                        c = 0;
                        break;
                    }
                    break;
                case 81986:
                    if (str.equals("SET")) {
                        c = 1;
                        break;
                    }
                    break;
                case 2012838315:
                    if (str.equals(FirebasePerformance.HttpMethod.DELETE)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    batch = batch.update(documentForFirestore, (Map<String, Object>) (Map) Objects.requireNonNull(map2));
                    break;
                case 1:
                    Map map3 = (Map) map.get("options");
                    if (!((Map) Objects.requireNonNull(map3)).containsKey("merge") || !((Boolean) map3.get("merge")).booleanValue()) {
                        if (!map3.containsKey("mergeFields")) {
                            batch = batch.set(documentForFirestore, Objects.requireNonNull(map2));
                            break;
                        } else {
                            ArrayList arrayList = new ArrayList();
                            for (String add : (List) Objects.requireNonNull((List) map3.get("mergeFields"))) {
                                arrayList.add(add);
                            }
                            batch = batch.set(documentForFirestore, Objects.requireNonNull(map2), SetOptions.mergeFields((List<String>) arrayList));
                            break;
                        }
                    } else {
                        batch = batch.set(documentForFirestore, Objects.requireNonNull(map2), SetOptions.merge());
                        break;
                    }
                    break;
                case 2:
                    batch = batch.delete(documentForFirestore);
                    break;
            }
        }
        return batch.commit();
    }

    static /* synthetic */ void lambda$documentBatch$12(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve((Object) null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    private void sendOnSnapshotEvent(String str, String str2, int i, DocumentSnapshot documentSnapshot) {
        Tasks.call(getExecutor(), new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda7(str, str2, documentSnapshot)).addOnCompleteListener(new ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda8(this, str, str2, i));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendOnSnapshotEvent$14(String str, String str2, int i, Task task) {
        if (task.isSuccessful()) {
            WritableMap createMap = Arguments.createMap();
            createMap.putMap(SentryStackTrace.JsonKeys.SNAPSHOT, (ReadableMap) task.getResult());
            ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseFirestoreEvent("firestore_document_sync_event", createMap, str, str2, i));
            return;
        }
        sendOnSnapshotError(str, str2, i, task.getException());
    }

    private void sendOnSnapshotError(String str, String str2, int i, Exception exc) {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        if (exc instanceof FirebaseFirestoreException) {
            UniversalFirebaseFirestoreException universalFirebaseFirestoreException = new UniversalFirebaseFirestoreException((FirebaseFirestoreException) exc, exc.getCause());
            createMap2.putString(UniversalFirebaseFunctionsModule.CODE_KEY, universalFirebaseFirestoreException.getCode());
            createMap2.putString("message", universalFirebaseFirestoreException.getMessage());
        } else {
            createMap2.putString(UniversalFirebaseFunctionsModule.CODE_KEY, "unknown");
            createMap2.putString("message", "An unknown error occurred");
        }
        createMap.putMap("error", createMap2);
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseFirestoreEvent("firestore_document_sync_event", createMap, str, str2, i));
    }
}
