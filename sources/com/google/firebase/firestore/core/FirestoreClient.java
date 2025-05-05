package com.google.firebase.firestore.core;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.LoadBundleTask;
import com.google.firebase.firestore.TransactionOptions;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.bundle.BundleReader;
import com.google.firebase.firestore.bundle.BundleSerializer;
import com.google.firebase.firestore.bundle.NamedQuery;
import com.google.firebase.firestore.core.ComponentProvider;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.local.IndexBackfiller;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.QueryResult;
import com.google.firebase.firestore.local.Scheduler;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.firestore.v1.Value;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class FirestoreClient {
    private static final String LOG_TAG = "FirestoreClient";
    private static final int MAX_CONCURRENT_LIMBO_RESOLUTIONS = 100;
    private final CredentialsProvider<String> appCheckProvider;
    private final AsyncQueue asyncQueue;
    private final CredentialsProvider<User> authProvider;
    private final BundleSerializer bundleSerializer;
    private final DatabaseInfo databaseInfo;
    private EventManager eventManager;
    private Scheduler gcScheduler;
    private Scheduler indexBackfillScheduler;
    private LocalStore localStore;
    private Persistence persistence;
    private RemoteStore remoteStore;
    private SyncEngine syncEngine;

    static /* synthetic */ void lambda$new$3(String str) {
    }

    public FirestoreClient(Context context, DatabaseInfo databaseInfo2, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, AsyncQueue asyncQueue2, GrpcMetadataProvider grpcMetadataProvider, ComponentProvider componentProvider) {
        this.databaseInfo = databaseInfo2;
        this.authProvider = credentialsProvider;
        this.appCheckProvider = credentialsProvider2;
        this.asyncQueue = asyncQueue2;
        this.bundleSerializer = new BundleSerializer(new RemoteSerializer(databaseInfo2.getDatabaseId()));
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        asyncQueue2.enqueueAndForget(new FirestoreClient$$ExternalSyntheticLambda17(this, taskCompletionSource, context, componentProvider, grpcMetadataProvider));
        credentialsProvider.setChangeListener(new FirestoreClient$$ExternalSyntheticLambda18(this, atomicBoolean, taskCompletionSource, asyncQueue2));
        credentialsProvider2.setChangeListener(new FirestoreClient$$ExternalSyntheticLambda19());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m660lambda$new$0$comgooglefirebasefirestorecoreFirestoreClient(TaskCompletionSource taskCompletionSource, Context context, ComponentProvider componentProvider, GrpcMetadataProvider grpcMetadataProvider) {
        try {
            initialize(context, (User) Tasks.await(taskCompletionSource.getTask()), componentProvider, grpcMetadataProvider);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$2$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m662lambda$new$2$comgooglefirebasefirestorecoreFirestoreClient(AtomicBoolean atomicBoolean, TaskCompletionSource taskCompletionSource, AsyncQueue asyncQueue2, User user) {
        if (atomicBoolean.compareAndSet(false, true)) {
            Assert.hardAssert(!taskCompletionSource.getTask().isComplete(), "Already fulfilled first user task", new Object[0]);
            taskCompletionSource.setResult(user);
            return;
        }
        asyncQueue2.enqueueAndForget(new FirestoreClient$$ExternalSyntheticLambda9(this, user));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m661lambda$new$1$comgooglefirebasefirestorecoreFirestoreClient(User user) {
        Assert.hardAssert(this.syncEngine != null, "SyncEngine not yet initialized", new Object[0]);
        Logger.debug(LOG_TAG, "Credential changed. Current user: %s", user.getUid());
        this.syncEngine.handleCredentialChange(user);
    }

    public Task<Void> disableNetwork() {
        verifyNotTerminated();
        return this.asyncQueue.enqueue((Runnable) new FirestoreClient$$ExternalSyntheticLambda16(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$disableNetwork$4$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m653lambda$disableNetwork$4$comgooglefirebasefirestorecoreFirestoreClient() {
        this.remoteStore.disableNetwork();
    }

    public Task<Void> enableNetwork() {
        verifyNotTerminated();
        return this.asyncQueue.enqueue((Runnable) new FirestoreClient$$ExternalSyntheticLambda12(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$enableNetwork$5$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m654lambda$enableNetwork$5$comgooglefirebasefirestorecoreFirestoreClient() {
        this.remoteStore.enableNetwork();
    }

    public Task<Void> terminate() {
        this.authProvider.removeChangeListener();
        this.appCheckProvider.removeChangeListener();
        return this.asyncQueue.enqueueAndInitiateShutdown(new FirestoreClient$$ExternalSyntheticLambda8(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$terminate$6$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m667lambda$terminate$6$comgooglefirebasefirestorecoreFirestoreClient() {
        this.remoteStore.shutdown();
        this.persistence.shutdown();
        Scheduler scheduler = this.gcScheduler;
        if (scheduler != null) {
            scheduler.stop();
        }
        Scheduler scheduler2 = this.indexBackfillScheduler;
        if (scheduler2 != null) {
            scheduler2.stop();
        }
    }

    public boolean isTerminated() {
        return this.asyncQueue.isShuttingDown();
    }

    public QueryListener listen(Query query, EventManager.ListenOptions listenOptions, EventListener<ViewSnapshot> eventListener) {
        verifyNotTerminated();
        QueryListener queryListener = new QueryListener(query, listenOptions, eventListener);
        this.asyncQueue.enqueueAndForget(new FirestoreClient$$ExternalSyntheticLambda7(this, queryListener));
        return queryListener;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$listen$7$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m658lambda$listen$7$comgooglefirebasefirestorecoreFirestoreClient(QueryListener queryListener) {
        this.eventManager.addQueryListener(queryListener);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$stopListening$8$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m666lambda$stopListening$8$comgooglefirebasefirestorecoreFirestoreClient(QueryListener queryListener) {
        this.eventManager.removeQueryListener(queryListener);
    }

    public void stopListening(QueryListener queryListener) {
        this.asyncQueue.enqueueAndForget(new FirestoreClient$$ExternalSyntheticLambda14(this, queryListener));
    }

    public Task<Document> getDocumentFromLocalCache(DocumentKey documentKey) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new FirestoreClient$$ExternalSyntheticLambda2(this, documentKey)).continueWith(new FirestoreClient$$ExternalSyntheticLambda3());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getDocumentFromLocalCache$9$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ Document m655lambda$getDocumentFromLocalCache$9$comgooglefirebasefirestorecoreFirestoreClient(DocumentKey documentKey) throws Exception {
        return this.localStore.readDocument(documentKey);
    }

    static /* synthetic */ Document lambda$getDocumentFromLocalCache$10(Task task) throws Exception {
        Document document = (Document) task.getResult();
        if (document.isFoundDocument()) {
            return document;
        }
        if (document.isNoDocument()) {
            return null;
        }
        throw new FirebaseFirestoreException("Failed to get document from cache. (However, this document may exist on the server. Run again without setting source to CACHE to attempt to retrieve the document from the server.)", FirebaseFirestoreException.Code.UNAVAILABLE);
    }

    public Task<ViewSnapshot> getDocumentsFromLocalCache(Query query) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new FirestoreClient$$ExternalSyntheticLambda6(this, query));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getDocumentsFromLocalCache$11$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ ViewSnapshot m656lambda$getDocumentsFromLocalCache$11$comgooglefirebasefirestorecoreFirestoreClient(Query query) throws Exception {
        QueryResult executeQuery = this.localStore.executeQuery(query, true);
        View view = new View(query, executeQuery.getRemoteKeys());
        return view.applyChanges(view.computeDocChanges(executeQuery.getDocuments())).getSnapshot();
    }

    public Task<Void> write(List<Mutation> list) {
        verifyNotTerminated();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(new FirestoreClient$$ExternalSyntheticLambda10(this, list, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$write$12$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m670lambda$write$12$comgooglefirebasefirestorecoreFirestoreClient(List list, TaskCompletionSource taskCompletionSource) {
        this.syncEngine.writeMutations(list, taskCompletionSource);
    }

    public <TResult> Task<TResult> transaction(TransactionOptions transactionOptions, Function<Transaction, Task<TResult>> function) {
        verifyNotTerminated();
        return AsyncQueue.callTask(this.asyncQueue.getExecutor(), new FirestoreClient$$ExternalSyntheticLambda5(this, transactionOptions, function));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$transaction$13$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ Task m668lambda$transaction$13$comgooglefirebasefirestorecoreFirestoreClient(TransactionOptions transactionOptions, Function function) throws Exception {
        return this.syncEngine.transaction(this.asyncQueue, transactionOptions, function);
    }

    public Task<Map<String, Value>> runAggregateQuery(Query query, List<AggregateField> list) {
        verifyNotTerminated();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(new FirestoreClient$$ExternalSyntheticLambda1(this, query, list, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$runAggregateQuery$16$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m664lambda$runAggregateQuery$16$comgooglefirebasefirestorecoreFirestoreClient(Query query, List list, TaskCompletionSource taskCompletionSource) {
        this.syncEngine.runAggregateQuery(query, list).addOnSuccessListener(new FirestoreClient$$ExternalSyntheticLambda22(taskCompletionSource)).addOnFailureListener(new FirestoreClient$$ExternalSyntheticLambda23(taskCompletionSource));
    }

    public Task<Void> waitForPendingWrites() {
        verifyNotTerminated();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(new FirestoreClient$$ExternalSyntheticLambda0(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$waitForPendingWrites$17$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m669lambda$waitForPendingWrites$17$comgooglefirebasefirestorecoreFirestoreClient(TaskCompletionSource taskCompletionSource) {
        this.syncEngine.registerPendingWritesTask(taskCompletionSource);
    }

    private void initialize(Context context, User user, ComponentProvider componentProvider, GrpcMetadataProvider grpcMetadataProvider) {
        Logger.debug(LOG_TAG, "Initializing. user=%s", user.getUid());
        ComponentProvider componentProvider2 = componentProvider;
        componentProvider2.initialize(new ComponentProvider.Configuration(context, this.asyncQueue, this.databaseInfo, user, 100, this.authProvider, this.appCheckProvider, grpcMetadataProvider));
        this.persistence = componentProvider.getPersistence();
        this.gcScheduler = componentProvider.getGarbageCollectionScheduler();
        this.localStore = componentProvider.getLocalStore();
        this.remoteStore = componentProvider.getRemoteStore();
        this.syncEngine = componentProvider.getSyncEngine();
        this.eventManager = componentProvider.getEventManager();
        IndexBackfiller indexBackfiller = componentProvider.getIndexBackfiller();
        Scheduler scheduler = this.gcScheduler;
        if (scheduler != null) {
            scheduler.start();
        }
        if (indexBackfiller != null) {
            IndexBackfiller.Scheduler scheduler2 = indexBackfiller.getScheduler();
            this.indexBackfillScheduler = scheduler2;
            scheduler2.start();
        }
    }

    public void addSnapshotsInSyncListener(EventListener<Void> eventListener) {
        verifyNotTerminated();
        this.asyncQueue.enqueueAndForget(new FirestoreClient$$ExternalSyntheticLambda21(this, eventListener));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addSnapshotsInSyncListener$18$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m650lambda$addSnapshotsInSyncListener$18$comgooglefirebasefirestorecoreFirestoreClient(EventListener eventListener) {
        this.eventManager.addSnapshotsInSyncListener(eventListener);
    }

    public void loadBundle(InputStream inputStream, LoadBundleTask loadBundleTask) {
        verifyNotTerminated();
        this.asyncQueue.enqueueAndForget(new FirestoreClient$$ExternalSyntheticLambda24(this, new BundleReader(this.bundleSerializer, inputStream), loadBundleTask));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$loadBundle$19$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m659lambda$loadBundle$19$comgooglefirebasefirestorecoreFirestoreClient(BundleReader bundleReader, LoadBundleTask loadBundleTask) {
        this.syncEngine.loadBundle(bundleReader, loadBundleTask);
    }

    public Task<Query> getNamedQuery(String str) {
        verifyNotTerminated();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(new FirestoreClient$$ExternalSyntheticLambda4(this, str, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getNamedQuery$20$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m657lambda$getNamedQuery$20$comgooglefirebasefirestorecoreFirestoreClient(String str, TaskCompletionSource taskCompletionSource) {
        NamedQuery namedQuery = this.localStore.getNamedQuery(str);
        if (namedQuery != null) {
            Target target = namedQuery.getBundledQuery().getTarget();
            taskCompletionSource.setResult(new Query(target.getPath(), target.getCollectionGroup(), target.getFilters(), target.getOrderBy(), target.getLimit(), namedQuery.getBundledQuery().getLimitType(), target.getStartAt(), target.getEndAt()));
            return;
        }
        taskCompletionSource.setResult(null);
    }

    public Task<Void> configureFieldIndexes(List<FieldIndex> list) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue((Runnable) new FirestoreClient$$ExternalSyntheticLambda11(this, list));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$configureFieldIndexes$21$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m651lambda$configureFieldIndexes$21$comgooglefirebasefirestorecoreFirestoreClient(List list) {
        this.localStore.configureFieldIndexes(list);
    }

    public void setIndexAutoCreationEnabled(boolean z) {
        verifyNotTerminated();
        this.asyncQueue.enqueueAndForget(new FirestoreClient$$ExternalSyntheticLambda13(this, z));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setIndexAutoCreationEnabled$22$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m665lambda$setIndexAutoCreationEnabled$22$comgooglefirebasefirestorecoreFirestoreClient(boolean z) {
        this.localStore.setIndexAutoCreationEnabled(z);
    }

    public void deleteAllFieldIndexes() {
        verifyNotTerminated();
        this.asyncQueue.enqueueAndForget(new FirestoreClient$$ExternalSyntheticLambda20(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$deleteAllFieldIndexes$23$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m652lambda$deleteAllFieldIndexes$23$comgooglefirebasefirestorecoreFirestoreClient() {
        this.localStore.deleteAllFieldIndexes();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeSnapshotsInSyncListener$24$com-google-firebase-firestore-core-FirestoreClient  reason: not valid java name */
    public /* synthetic */ void m663lambda$removeSnapshotsInSyncListener$24$comgooglefirebasefirestorecoreFirestoreClient(EventListener eventListener) {
        this.eventManager.removeSnapshotsInSyncListener(eventListener);
    }

    public void removeSnapshotsInSyncListener(EventListener<Void> eventListener) {
        this.asyncQueue.enqueueAndForget(new FirestoreClient$$ExternalSyntheticLambda15(this, eventListener));
    }

    private void verifyNotTerminated() {
        if (isTerminated()) {
            throw new IllegalStateException("The client has already been terminated");
        }
    }
}
