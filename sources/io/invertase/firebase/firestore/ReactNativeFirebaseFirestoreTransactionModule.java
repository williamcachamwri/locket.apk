package io.invertase.firebase.firestore;

import android.os.AsyncTask;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.perf.FirebasePerformance;
import io.invertase.firebase.common.RCTConvertFirebase;
import io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import io.sentry.ProfilingTraceData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReactNativeFirebaseFirestoreTransactionModule extends ReactNativeFirebaseModule {
    private static final String SERVICE_NAME = "FirestoreTransaction";
    private SparseArray<ReactNativeFirebaseFirestoreTransactionHandler> transactionHandlers = new SparseArray<>();

    ReactNativeFirebaseFirestoreTransactionModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE_NAME);
    }

    public void invalidate() {
        int size = this.transactionHandlers.size();
        for (int i = 0; i < size; i++) {
            ReactNativeFirebaseFirestoreTransactionHandler reactNativeFirebaseFirestoreTransactionHandler = this.transactionHandlers.get(this.transactionHandlers.keyAt(i));
            if (reactNativeFirebaseFirestoreTransactionHandler != null) {
                reactNativeFirebaseFirestoreTransactionHandler.abort();
            }
        }
        this.transactionHandlers.clear();
        super.invalidate();
    }

    @ReactMethod
    public void transactionGetDocument(String str, String str2, int i, String str3, Promise promise) {
        ReactNativeFirebaseFirestoreTransactionHandler reactNativeFirebaseFirestoreTransactionHandler = this.transactionHandlers.get(i);
        if (reactNativeFirebaseFirestoreTransactionHandler == null) {
            rejectPromiseWithCodeAndMessage(promise, "internal-error", "An internal error occurred whilst attempting to find a native transaction by id.");
            return;
        }
        Tasks.call(getTransactionalExecutor(), new ReactNativeFirebaseFirestoreTransactionModule$$ExternalSyntheticLambda3(str, str2, reactNativeFirebaseFirestoreTransactionHandler, UniversalFirebaseFirestoreCommon.getDocumentForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2), str3))).addOnCompleteListener(new ReactNativeFirebaseFirestoreTransactionModule$$ExternalSyntheticLambda4(promise));
    }

    static /* synthetic */ void lambda$transactionGetDocument$1(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void transactionDispose(String str, String str2, int i) {
        ReactNativeFirebaseFirestoreTransactionHandler reactNativeFirebaseFirestoreTransactionHandler = this.transactionHandlers.get(i);
        if (reactNativeFirebaseFirestoreTransactionHandler != null) {
            reactNativeFirebaseFirestoreTransactionHandler.abort();
            this.transactionHandlers.delete(i);
        }
    }

    @ReactMethod
    public void transactionApplyBuffer(String str, String str2, int i, ReadableArray readableArray) {
        ReactNativeFirebaseFirestoreTransactionHandler reactNativeFirebaseFirestoreTransactionHandler = this.transactionHandlers.get(i);
        if (reactNativeFirebaseFirestoreTransactionHandler != null) {
            reactNativeFirebaseFirestoreTransactionHandler.signalBufferReceived(readableArray);
        }
    }

    @ReactMethod
    public void transactionBegin(String str, String str2, int i) {
        ReactNativeFirebaseFirestoreTransactionHandler reactNativeFirebaseFirestoreTransactionHandler = new ReactNativeFirebaseFirestoreTransactionHandler(str, i);
        this.transactionHandlers.put(i, reactNativeFirebaseFirestoreTransactionHandler);
        FirebaseFirestore firestoreForApp = UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2);
        ReactNativeFirebaseEventEmitter sharedInstance = ReactNativeFirebaseEventEmitter.getSharedInstance();
        firestoreForApp.runTransaction(new ReactNativeFirebaseFirestoreTransactionModule$$ExternalSyntheticLambda0(reactNativeFirebaseFirestoreTransactionHandler, sharedInstance, str2, firestoreForApp)).addOnCompleteListener(new ReactNativeFirebaseFirestoreTransactionModule$$ExternalSyntheticLambda1(reactNativeFirebaseFirestoreTransactionHandler, sharedInstance, str2));
    }

    static /* synthetic */ Void lambda$transactionBegin$3(ReactNativeFirebaseFirestoreTransactionHandler reactNativeFirebaseFirestoreTransactionHandler, ReactNativeFirebaseEventEmitter reactNativeFirebaseEventEmitter, String str, FirebaseFirestore firebaseFirestore, Transaction transaction) throws FirebaseFirestoreException {
        reactNativeFirebaseFirestoreTransactionHandler.resetState(transaction);
        AsyncTask.execute(new ReactNativeFirebaseFirestoreTransactionModule$$ExternalSyntheticLambda2(reactNativeFirebaseEventEmitter, reactNativeFirebaseFirestoreTransactionHandler, str));
        reactNativeFirebaseFirestoreTransactionHandler.await();
        if (reactNativeFirebaseFirestoreTransactionHandler.aborted) {
            throw new FirebaseFirestoreException("abort", FirebaseFirestoreException.Code.ABORTED);
        } else if (!reactNativeFirebaseFirestoreTransactionHandler.timeout) {
            ReadableArray commandBuffer = reactNativeFirebaseFirestoreTransactionHandler.getCommandBuffer();
            if (commandBuffer == null) {
                return null;
            }
            int size = commandBuffer.size();
            for (int i = 0; i < size; i++) {
                ReadableMap map = commandBuffer.getMap(i);
                String string = ((ReadableMap) Objects.requireNonNull(map)).getString("path");
                String string2 = map.getString("type");
                DocumentReference documentForFirestore = UniversalFirebaseFirestoreCommon.getDocumentForFirestore(firebaseFirestore, string);
                String str2 = (String) Objects.requireNonNull(string2);
                str2.hashCode();
                char c = 65535;
                switch (str2.hashCode()) {
                    case -1785516855:
                        if (str2.equals("UPDATE")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 81986:
                        if (str2.equals("SET")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 2012838315:
                        if (str2.equals(FirebasePerformance.HttpMethod.DELETE)) {
                            c = 2;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        transaction.update(documentForFirestore, ReactNativeFirebaseFirestoreSerialize.parseReadableMap(firebaseFirestore, map.getMap("data")));
                        break;
                    case 1:
                        Map<String, Object> parseReadableMap = ReactNativeFirebaseFirestoreSerialize.parseReadableMap(firebaseFirestore, map.getMap("data"));
                        ReadableMap map2 = map.getMap("options");
                        if (!((ReadableMap) Objects.requireNonNull(map2)).hasKey("merge") || !map2.getBoolean("merge")) {
                            if (!map2.hasKey("mergeFields")) {
                                transaction.set(documentForFirestore, parseReadableMap);
                                break;
                            } else {
                                ArrayList arrayList = new ArrayList();
                                Iterator<Object> it = RCTConvertFirebase.toArrayList(map2.getArray("mergeFields")).iterator();
                                while (it.hasNext()) {
                                    arrayList.add((String) it.next());
                                }
                                transaction.set(documentForFirestore, parseReadableMap, SetOptions.mergeFields((List<String>) arrayList));
                                break;
                            }
                        } else {
                            transaction.set(documentForFirestore, parseReadableMap, SetOptions.merge());
                            break;
                        }
                    case 2:
                        transaction.delete(documentForFirestore);
                        break;
                }
            }
            return null;
        } else {
            throw new FirebaseFirestoreException(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT, FirebaseFirestoreException.Code.DEADLINE_EXCEEDED);
        }
    }

    static /* synthetic */ void lambda$transactionBegin$2(ReactNativeFirebaseEventEmitter reactNativeFirebaseEventEmitter, ReactNativeFirebaseFirestoreTransactionHandler reactNativeFirebaseFirestoreTransactionHandler, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("type", "update");
        reactNativeFirebaseEventEmitter.sendEvent(new ReactNativeFirebaseFirestoreEvent("firestore_transaction_event", createMap, reactNativeFirebaseFirestoreTransactionHandler.getAppName(), str, reactNativeFirebaseFirestoreTransactionHandler.getTransactionId()));
    }

    static /* synthetic */ void lambda$transactionBegin$4(ReactNativeFirebaseFirestoreTransactionHandler reactNativeFirebaseFirestoreTransactionHandler, ReactNativeFirebaseEventEmitter reactNativeFirebaseEventEmitter, String str, Task task) {
        if (!reactNativeFirebaseFirestoreTransactionHandler.aborted) {
            WritableMap createMap = Arguments.createMap();
            if (task.isSuccessful()) {
                createMap.putString("type", "complete");
                reactNativeFirebaseEventEmitter.sendEvent(new ReactNativeFirebaseFirestoreEvent("firestore_transaction_event", createMap, reactNativeFirebaseFirestoreTransactionHandler.getAppName(), str, reactNativeFirebaseFirestoreTransactionHandler.getTransactionId()));
                return;
            }
            createMap.putString("type", "error");
            Exception exception = task.getException();
            WritableMap createMap2 = Arguments.createMap();
            UniversalFirebaseFirestoreException universalFirebaseFirestoreException = new UniversalFirebaseFirestoreException((FirebaseFirestoreException) exception, exception.getCause());
            createMap2.putString(UniversalFirebaseFunctionsModule.CODE_KEY, universalFirebaseFirestoreException.getCode());
            createMap2.putString("message", universalFirebaseFirestoreException.getMessage());
            createMap.putMap("error", createMap2);
            reactNativeFirebaseEventEmitter.sendEvent(new ReactNativeFirebaseFirestoreEvent("firestore_transaction_event", createMap, reactNativeFirebaseFirestoreTransactionHandler.getAppName(), str, reactNativeFirebaseFirestoreTransactionHandler.getTransactionId()));
        }
    }
}
