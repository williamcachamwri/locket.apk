package com.google.firebase.firestore.remote;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.remote.FirestoreChannel;
import com.google.firebase.firestore.remote.WatchStream;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.v1.BatchGetDocumentsRequest;
import com.google.firestore.v1.BatchGetDocumentsResponse;
import com.google.firestore.v1.CommitRequest;
import com.google.firestore.v1.CommitResponse;
import com.google.firestore.v1.FirestoreGrpc;
import com.google.firestore.v1.RunAggregationQueryRequest;
import com.google.firestore.v1.RunAggregationQueryResponse;
import com.google.firestore.v1.StructuredAggregationQuery;
import com.google.firestore.v1.Target;
import com.google.firestore.v1.Value;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLHandshakeException;

public class Datastore {
    static final String SSL_DEPENDENCY_ERROR_MESSAGE = "The Cloud Firestore client failed to establish a secure connection. This is likely a problem with your app, rather than with Cloud Firestore itself. See https://bit.ly/2XFpdma for instructions on how to enable TLS on Android 4.x devices.";
    static final Set<String> WHITE_LISTED_HEADERS = new HashSet(Arrays.asList(new String[]{"date", "x-google-backends", "x-google-netmon-label", "x-google-service", "x-google-gfe-request-trace"}));
    /* access modifiers changed from: private */
    public final FirestoreChannel channel;
    protected final RemoteSerializer serializer;
    private final AsyncQueue workerQueue;

    Datastore(AsyncQueue asyncQueue, RemoteSerializer remoteSerializer, FirestoreChannel firestoreChannel) {
        this.workerQueue = asyncQueue;
        this.serializer = remoteSerializer;
        this.channel = firestoreChannel;
    }

    /* access modifiers changed from: package-private */
    public void shutdown() {
        this.channel.shutdown();
    }

    /* access modifiers changed from: package-private */
    public AsyncQueue getWorkerQueue() {
        return this.workerQueue;
    }

    /* access modifiers changed from: package-private */
    public WatchStream createWatchStream(WatchStream.Callback callback) {
        return new WatchStream(this.channel, this.workerQueue, this.serializer, callback);
    }

    /* access modifiers changed from: package-private */
    public WriteStream createWriteStream(WriteStream.Callback callback) {
        return new WriteStream(this.channel, this.workerQueue, this.serializer, callback);
    }

    public Task<List<MutationResult>> commit(List<Mutation> list) {
        CommitRequest.Builder newBuilder = CommitRequest.newBuilder();
        newBuilder.setDatabase(this.serializer.databaseName());
        for (Mutation encodeMutation : list) {
            newBuilder.addWrites(this.serializer.encodeMutation(encodeMutation));
        }
        return this.channel.runRpc(FirestoreGrpc.getCommitMethod(), (CommitRequest) newBuilder.build()).continueWith(this.workerQueue.getExecutor(), new Datastore$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$commit$0$com-google-firebase-firestore-remote-Datastore  reason: not valid java name */
    public /* synthetic */ List m747lambda$commit$0$comgooglefirebasefirestoreremoteDatastore(Task task) throws Exception {
        if (!task.isSuccessful()) {
            if ((task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                this.channel.invalidateToken();
            }
            throw task.getException();
        }
        CommitResponse commitResponse = (CommitResponse) task.getResult();
        SnapshotVersion decodeVersion = this.serializer.decodeVersion(commitResponse.getCommitTime());
        int writeResultsCount = commitResponse.getWriteResultsCount();
        ArrayList arrayList = new ArrayList(writeResultsCount);
        for (int i = 0; i < writeResultsCount; i++) {
            arrayList.add(this.serializer.decodeMutationResult(commitResponse.getWriteResults(i), decodeVersion));
        }
        return arrayList;
    }

    public Task<List<MutableDocument>> lookup(final List<DocumentKey> list) {
        BatchGetDocumentsRequest.Builder newBuilder = BatchGetDocumentsRequest.newBuilder();
        newBuilder.setDatabase(this.serializer.databaseName());
        for (DocumentKey encodeKey : list) {
            newBuilder.addDocuments(this.serializer.encodeKey(encodeKey));
        }
        final ArrayList arrayList = new ArrayList();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.channel.runStreamingResponseRpc(FirestoreGrpc.getBatchGetDocumentsMethod(), (BatchGetDocumentsRequest) newBuilder.build(), new FirestoreChannel.StreamingListener<BatchGetDocumentsResponse>() {
            public void onMessage(BatchGetDocumentsResponse batchGetDocumentsResponse) {
                arrayList.add(batchGetDocumentsResponse);
                if (arrayList.size() == list.size()) {
                    HashMap hashMap = new HashMap();
                    for (BatchGetDocumentsResponse decodeMaybeDocument : arrayList) {
                        MutableDocument decodeMaybeDocument2 = Datastore.this.serializer.decodeMaybeDocument(decodeMaybeDocument);
                        hashMap.put(decodeMaybeDocument2.getKey(), decodeMaybeDocument2);
                    }
                    ArrayList arrayList = new ArrayList();
                    for (DocumentKey documentKey : list) {
                        arrayList.add((MutableDocument) hashMap.get(documentKey));
                    }
                    taskCompletionSource.trySetResult(arrayList);
                }
            }

            public void onClose(Status status) {
                if (status.isOk()) {
                    taskCompletionSource.trySetResult(Collections.emptyList());
                    return;
                }
                FirebaseFirestoreException exceptionFromStatus = Util.exceptionFromStatus(status);
                if (exceptionFromStatus.getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                    Datastore.this.channel.invalidateToken();
                }
                taskCompletionSource.trySetException(exceptionFromStatus);
            }
        });
        return taskCompletionSource.getTask();
    }

    public Task<Map<String, Value>> runAggregateQuery(Query query, List<AggregateField> list) {
        Target.QueryTarget encodeQueryTarget = this.serializer.encodeQueryTarget(query.toAggregateTarget());
        HashMap hashMap = new HashMap();
        StructuredAggregationQuery encodeStructuredAggregationQuery = this.serializer.encodeStructuredAggregationQuery(encodeQueryTarget, list, hashMap);
        RunAggregationQueryRequest.Builder newBuilder = RunAggregationQueryRequest.newBuilder();
        newBuilder.setParent(encodeQueryTarget.getParent());
        newBuilder.setStructuredAggregationQuery(encodeStructuredAggregationQuery);
        return this.channel.runRpc(FirestoreGrpc.getRunAggregationQueryMethod(), (RunAggregationQueryRequest) newBuilder.build()).continueWith(this.workerQueue.getExecutor(), new Datastore$$ExternalSyntheticLambda0(this, hashMap));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$runAggregateQuery$1$com-google-firebase-firestore-remote-Datastore  reason: not valid java name */
    public /* synthetic */ Map m748lambda$runAggregateQuery$1$comgooglefirebasefirestoreremoteDatastore(HashMap hashMap, Task task) throws Exception {
        if (!task.isSuccessful()) {
            if ((task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                this.channel.invalidateToken();
            }
            throw task.getException();
        }
        HashMap hashMap2 = new HashMap();
        for (Map.Entry next : ((RunAggregationQueryResponse) task.getResult()).getResult().getAggregateFieldsMap().entrySet()) {
            Assert.hardAssert(hashMap.containsKey(next.getKey()), "%s not present in aliasMap", next.getKey());
            hashMap2.put((String) hashMap.get(next.getKey()), (Value) next.getValue());
        }
        return hashMap2;
    }

    public static boolean isPermanentError(Status status) {
        return isPermanentError(FirebaseFirestoreException.Code.fromValue(status.getCode().value()));
    }

    /* renamed from: com.google.firebase.firestore.remote.Datastore$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code;

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.firebase.firestore.FirebaseFirestoreException$Code[] r0 = com.google.firebase.firestore.FirebaseFirestoreException.Code.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code = r0
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.OK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.CANCELLED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.DEADLINE_EXCEEDED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.INTERNAL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.UNAVAILABLE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.UNAUTHENTICATED     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.INVALID_ARGUMENT     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.NOT_FOUND     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.ALREADY_EXISTS     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.PERMISSION_DENIED     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x009c }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.FAILED_PRECONDITION     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.ABORTED     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.OUT_OF_RANGE     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.UNIMPLEMENTED     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x00cc }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.DATA_LOSS     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.remote.Datastore.AnonymousClass2.<clinit>():void");
        }
    }

    public static boolean isPermanentError(FirebaseFirestoreException.Code code) {
        switch (AnonymousClass2.$SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[code.ordinal()]) {
            case 1:
                throw new IllegalArgumentException("Treated status OK as error");
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return false;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                return true;
            default:
                throw new IllegalArgumentException("Unknown gRPC status code: " + code);
        }
    }

    public static boolean isMissingSslCiphers(Status status) {
        status.getCode();
        Throwable cause = status.getCause();
        if (!(cause instanceof SSLHandshakeException)) {
            return false;
        }
        cause.getMessage().contains("no ciphers available");
        return false;
    }

    public static boolean isPermanentWriteError(Status status) {
        return isPermanentError(status) && !status.getCode().equals(Status.Code.ABORTED);
    }
}
