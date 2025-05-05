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
import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import io.sentry.protocol.SentryStackTrace;
import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;

public class ReactNativeFirebaseFirestoreCollectionModule extends ReactNativeFirebaseModule {
    private static final String SERVICE_NAME = "FirestoreCollection";
    private static SparseArray<ListenerRegistration> collectionSnapshotListeners = new SparseArray<>();

    ReactNativeFirebaseFirestoreCollectionModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE_NAME);
    }

    public void invalidate() {
        super.invalidate();
        int size = collectionSnapshotListeners.size();
        for (int i = 0; i < size; i++) {
            collectionSnapshotListeners.get(collectionSnapshotListeners.keyAt(i)).remove();
        }
        collectionSnapshotListeners.clear();
    }

    @ReactMethod
    public void namedQueryOnSnapshot(String str, String str2, String str3, String str4, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, int i, ReadableMap readableMap2) {
        if (collectionSnapshotListeners.get(i) == null) {
            String str5 = str3;
            UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2).getNamedQuery(str3).addOnCompleteListener(new ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda7(this, str, str2, i, readableArray, readableArray2, readableMap, readableMap2));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$namedQueryOnSnapshot$0(String str, String str2, int i, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, ReadableMap readableMap2, Task task) {
        if (task.isSuccessful()) {
            Query query = (Query) task.getResult();
            if (query == null) {
                sendOnSnapshotError(str, str2, i, new NullPointerException());
            } else {
                handleQueryOnSnapshot(new ReactNativeFirebaseFirestoreQuery(str, str2, query, readableArray, readableArray2, readableMap), str, str2, i, readableMap2);
            }
        } else {
            sendOnSnapshotError(str, str2, i, task.getException());
        }
    }

    @ReactMethod
    public void collectionOnSnapshot(String str, String str2, String str3, String str4, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, int i, ReadableMap readableMap2) {
        if (collectionSnapshotListeners.get(i) == null) {
            handleQueryOnSnapshot(new ReactNativeFirebaseFirestoreQuery(str, str2, UniversalFirebaseFirestoreCommon.getQueryForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2), str3, str4), readableArray, readableArray2, readableMap), str, str2, i, readableMap2);
        }
    }

    @ReactMethod
    public void collectionOffSnapshot(String str, String str2, int i) {
        ListenerRegistration listenerRegistration = collectionSnapshotListeners.get(i);
        if (listenerRegistration != null) {
            listenerRegistration.remove();
            collectionSnapshotListeners.remove(i);
            removeEventListeningExecutor(Integer.toString(i));
        }
    }

    @ReactMethod
    public void namedQueryGet(String str, String str2, String str3, String str4, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, ReadableMap readableMap2, Promise promise) {
        String str5 = str3;
        UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2).getNamedQuery(str3).addOnCompleteListener(new ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda3(this, promise, str, str2, readableArray, readableArray2, readableMap, readableMap2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$namedQueryGet$1(Promise promise, String str, String str2, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, ReadableMap readableMap2, Task task) {
        if (task.isSuccessful()) {
            Query query = (Query) task.getResult();
            if (query == null) {
                ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, new NullPointerException());
            } else {
                handleQueryGet(new ReactNativeFirebaseFirestoreQuery(str, str2, query, readableArray, readableArray2, readableMap), getSource(readableMap2), promise);
            }
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void collectionCount(String str, String str2, String str3, String str4, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, Promise promise) {
        String str5 = str3;
        String str6 = str4;
        new ReactNativeFirebaseFirestoreQuery(str, str2, UniversalFirebaseFirestoreCommon.getQueryForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2), str3, str4), readableArray, readableArray2, readableMap).query.count().get(AggregateSource.SERVER).addOnCompleteListener(new ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda5(promise));
    }

    static /* synthetic */ void lambda$collectionCount$2(Promise promise, Task task) {
        if (task.isSuccessful()) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble(NewHtcHomeBadger.COUNT, Long.valueOf(((AggregateQuerySnapshot) task.getResult()).getCount()).doubleValue());
            promise.resolve(createMap);
            return;
        }
        ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005c, code lost:
        if (r7.equals("sum") == false) goto L_0x0049;
     */
    @com.facebook.react.bridge.ReactMethod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void aggregateQuery(java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, com.facebook.react.bridge.ReadableArray r16, com.facebook.react.bridge.ReadableArray r17, com.facebook.react.bridge.ReadableMap r18, com.facebook.react.bridge.ReadableArray r19, com.facebook.react.bridge.Promise r20) {
        /*
            r11 = this;
            r0 = r19
            r1 = r20
            com.google.firebase.firestore.FirebaseFirestore r2 = io.invertase.firebase.firestore.UniversalFirebaseFirestoreCommon.getFirestoreForApp(r12, r13)
            io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreQuery r10 = new io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreQuery
            r3 = r14
            r4 = r15
            com.google.firebase.firestore.Query r6 = io.invertase.firebase.firestore.UniversalFirebaseFirestoreCommon.getQueryForFirestore(r2, r14, r15)
            r3 = r10
            r4 = r12
            r5 = r13
            r7 = r16
            r8 = r17
            r9 = r18
            r3.<init>(r4, r5, r6, r7, r8, r9)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r3 = 0
            r4 = r3
        L_0x0023:
            int r5 = r19.size()
            r6 = 1
            if (r4 >= r5) goto L_0x009b
            com.facebook.react.bridge.ReadableMap r5 = r0.getMap(r4)
            java.lang.String r7 = "aggregateType"
            java.lang.String r7 = r5.getString(r7)
            if (r7 != 0) goto L_0x0038
            java.lang.String r7 = ""
        L_0x0038:
            java.lang.String r8 = "field"
            java.lang.String r5 = r5.getString(r8)
            r7.hashCode()
            int r8 = r7.hashCode()
            r9 = -1
            switch(r8) {
                case -631448035: goto L_0x005f;
                case 114251: goto L_0x0056;
                case 94851343: goto L_0x004b;
                default: goto L_0x0049;
            }
        L_0x0049:
            r6 = r9
            goto L_0x0069
        L_0x004b:
            java.lang.String r6 = "count"
            boolean r6 = r7.equals(r6)
            if (r6 != 0) goto L_0x0054
            goto L_0x0049
        L_0x0054:
            r6 = 2
            goto L_0x0069
        L_0x0056:
            java.lang.String r8 = "sum"
            boolean r8 = r7.equals(r8)
            if (r8 != 0) goto L_0x0069
            goto L_0x0049
        L_0x005f:
            java.lang.String r6 = "average"
            boolean r6 = r7.equals(r6)
            if (r6 != 0) goto L_0x0068
            goto L_0x0049
        L_0x0068:
            r6 = r3
        L_0x0069:
            switch(r6) {
                case 0: goto L_0x0091;
                case 1: goto L_0x0089;
                case 2: goto L_0x0081;
                default: goto L_0x006c;
            }
        L_0x006c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Invalid AggregateType: "
            r0.<init>(r2)
            java.lang.StringBuilder r0 = r0.append(r7)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "firestore/invalid-argument"
            rejectPromiseWithCodeAndMessage(r1, r2, r0)
            return
        L_0x0081:
            com.google.firebase.firestore.AggregateField$CountAggregateField r5 = com.google.firebase.firestore.AggregateField.count()
            r2.add(r5)
            goto L_0x0098
        L_0x0089:
            com.google.firebase.firestore.AggregateField$SumAggregateField r5 = com.google.firebase.firestore.AggregateField.sum((java.lang.String) r5)
            r2.add(r5)
            goto L_0x0098
        L_0x0091:
            com.google.firebase.firestore.AggregateField$AverageAggregateField r5 = com.google.firebase.firestore.AggregateField.average((java.lang.String) r5)
            r2.add(r5)
        L_0x0098:
            int r4 = r4 + 1
            goto L_0x0023
        L_0x009b:
            com.google.firebase.firestore.Query r4 = r10.query
            java.lang.Object r5 = r2.get(r3)
            com.google.firebase.firestore.AggregateField r5 = (com.google.firebase.firestore.AggregateField) r5
            int r7 = r2.size()
            java.util.List r2 = r2.subList(r6, r7)
            com.google.firebase.firestore.AggregateField[] r3 = new com.google.firebase.firestore.AggregateField[r3]
            java.lang.Object[] r2 = r2.toArray(r3)
            com.google.firebase.firestore.AggregateField[] r2 = (com.google.firebase.firestore.AggregateField[]) r2
            com.google.firebase.firestore.AggregateQuery r2 = r4.aggregate(r5, r2)
            com.google.firebase.firestore.AggregateSource r3 = com.google.firebase.firestore.AggregateSource.SERVER
            com.google.android.gms.tasks.Task r2 = r2.get(r3)
            io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda0 r3 = new io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda0
            r3.<init>(r0, r1)
            r2.addOnCompleteListener(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreCollectionModule.aggregateQuery(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.Promise):void");
    }

    static /* synthetic */ void lambda$aggregateQuery$3(ReadableArray readableArray, Promise promise, Task task) {
        if (task.isSuccessful()) {
            WritableMap createMap = Arguments.createMap();
            AggregateQuerySnapshot aggregateQuerySnapshot = (AggregateQuerySnapshot) task.getResult();
            for (int i = 0; i < readableArray.size(); i++) {
                ReadableMap map = readableArray.getMap(i);
                String string = map.getString("aggregateType");
                if (string == null) {
                    string = "";
                }
                String string2 = map.getString("field");
                String string3 = map.getString("key");
                if (string3 == null) {
                    rejectPromiseWithCodeAndMessage(promise, "firestore/invalid-argument", "key may not be null");
                    return;
                }
                string.hashCode();
                char c = 65535;
                switch (string.hashCode()) {
                    case -631448035:
                        if (string.equals("average")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 114251:
                        if (string.equals("sum")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 94851343:
                        if (string.equals(NewHtcHomeBadger.COUNT)) {
                            c = 2;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        Double d = aggregateQuerySnapshot.get(AggregateField.average(string2));
                        if (d != null) {
                            createMap.putDouble(string3, d.doubleValue());
                            break;
                        } else {
                            createMap.putNull(string3);
                            break;
                        }
                    case 1:
                        Number number = (Number) aggregateQuerySnapshot.get((AggregateField) AggregateField.sum(string2));
                        if (number != null) {
                            createMap.putDouble(string3, number.doubleValue());
                            break;
                        } else {
                            rejectPromiseWithCodeAndMessage(promise, "firestore/unknown", "sum unexpectedly null");
                            return;
                        }
                    case 2:
                        createMap.putDouble(string3, Long.valueOf(aggregateQuerySnapshot.getCount()).doubleValue());
                        break;
                    default:
                        rejectPromiseWithCodeAndMessage(promise, "firestore/invalid-argument", "Invalid AggregateType: " + string);
                        return;
                }
            }
            promise.resolve(createMap);
            return;
        }
        ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
    }

    @ReactMethod
    public void collectionGet(String str, String str2, String str3, String str4, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, ReadableMap readableMap2, Promise promise) {
        String str5 = str3;
        String str6 = str4;
        handleQueryGet(new ReactNativeFirebaseFirestoreQuery(str, str2, UniversalFirebaseFirestoreCommon.getQueryForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str, str2), str3, str4), readableArray, readableArray2, readableMap), getSource(readableMap2), promise);
    }

    private void handleQueryOnSnapshot(ReactNativeFirebaseFirestoreQuery reactNativeFirebaseFirestoreQuery, String str, String str2, int i, ReadableMap readableMap) {
        MetadataChanges metadataChanges;
        if (readableMap == null || !readableMap.hasKey("includeMetadataChanges") || !readableMap.getBoolean("includeMetadataChanges")) {
            metadataChanges = MetadataChanges.EXCLUDE;
        } else {
            metadataChanges = MetadataChanges.INCLUDE;
        }
        collectionSnapshotListeners.put(i, reactNativeFirebaseFirestoreQuery.query.addSnapshotListener(metadataChanges, (EventListener<QuerySnapshot>) new ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda6(this, i, str, str2, metadataChanges)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleQueryOnSnapshot$4(int i, String str, String str2, MetadataChanges metadataChanges, QuerySnapshot querySnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            ListenerRegistration listenerRegistration = collectionSnapshotListeners.get(i);
            if (listenerRegistration != null) {
                listenerRegistration.remove();
                collectionSnapshotListeners.remove(i);
            }
            sendOnSnapshotError(str, str2, i, firebaseFirestoreException);
            return;
        }
        sendOnSnapshotEvent(str, str2, i, querySnapshot, metadataChanges);
    }

    private void handleQueryGet(ReactNativeFirebaseFirestoreQuery reactNativeFirebaseFirestoreQuery, Source source, Promise promise) {
        reactNativeFirebaseFirestoreQuery.get(getExecutor(), source).addOnCompleteListener(new ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda4(promise));
    }

    static /* synthetic */ void lambda$handleQueryGet$5(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    private void sendOnSnapshotEvent(String str, String str2, int i, QuerySnapshot querySnapshot, MetadataChanges metadataChanges) {
        Tasks.call(getTransactionalExecutor(Integer.toString(i)), new ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda1(str, str2, querySnapshot, metadataChanges)).addOnCompleteListener(new ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda2(this, str, str2, i));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendOnSnapshotEvent$7(String str, String str2, int i, Task task) {
        if (task.isSuccessful()) {
            WritableMap createMap = Arguments.createMap();
            createMap.putMap(SentryStackTrace.JsonKeys.SNAPSHOT, (ReadableMap) task.getResult());
            ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseFirestoreEvent("firestore_collection_sync_event", createMap, str, str2, i));
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
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseFirestoreEvent("firestore_collection_sync_event", createMap, str, str2, i));
    }

    private Source getSource(ReadableMap readableMap) {
        if (readableMap == null || !readableMap.hasKey("source")) {
            return Source.DEFAULT;
        }
        String string = readableMap.getString("source");
        if ("server".equals(string)) {
            return Source.SERVER;
        }
        if ("cache".equals(string)) {
            return Source.CACHE;
        }
        return Source.DEFAULT;
    }
}
