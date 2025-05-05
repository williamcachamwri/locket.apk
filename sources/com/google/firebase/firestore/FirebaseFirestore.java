package com.google.firebase.firestore;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.FirebaseAppCheckTokenProvider;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.ComponentProvider;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.remote.FirestoreChannel;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ByteBufferInputStream;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.inject.Deferred;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FirebaseFirestore {
    private static final String TAG = "FirebaseFirestore";
    private final CredentialsProvider<String> appCheckProvider;
    private final CredentialsProvider<User> authProvider;
    final FirestoreClientProvider clientProvider = new FirestoreClientProvider(new FirebaseFirestore$$ExternalSyntheticLambda9(this));
    private final Function<FirebaseFirestoreSettings, ComponentProvider> componentProviderFactory;
    private final Context context;
    private final DatabaseId databaseId;
    private EmulatedServiceSettings emulatorSettings;
    private final FirebaseApp firebaseApp;
    private final InstanceRegistry instanceRegistry;
    private final GrpcMetadataProvider metadataProvider;
    private final String persistenceKey;
    private PersistentCacheIndexManager persistentCacheIndexManager;
    private FirebaseFirestoreSettings settings;
    private final UserDataReader userDataReader;

    public interface InstanceRegistry {
        void remove(String str);
    }

    private static FirebaseApp getDefaultFirebaseApp() {
        FirebaseApp instance = FirebaseApp.getInstance();
        if (instance != null) {
            return instance;
        }
        throw new IllegalStateException("You must call FirebaseApp.initializeApp first.");
    }

    public static FirebaseFirestore getInstance() {
        return getInstance(getDefaultFirebaseApp(), "(default)");
    }

    public static FirebaseFirestore getInstance(FirebaseApp firebaseApp2) {
        return getInstance(firebaseApp2, "(default)");
    }

    public static FirebaseFirestore getInstance(String str) {
        return getInstance(getDefaultFirebaseApp(), str);
    }

    public static FirebaseFirestore getInstance(FirebaseApp firebaseApp2, String str) {
        Preconditions.checkNotNull(firebaseApp2, "Provided FirebaseApp must not be null.");
        Preconditions.checkNotNull(str, "Provided database name must not be null.");
        FirestoreMultiDbComponent firestoreMultiDbComponent = (FirestoreMultiDbComponent) firebaseApp2.get(FirestoreMultiDbComponent.class);
        Preconditions.checkNotNull(firestoreMultiDbComponent, "Firestore component is not present.");
        return firestoreMultiDbComponent.get(str);
    }

    static FirebaseFirestore newInstance(Context context2, FirebaseApp firebaseApp2, Deferred<InternalAuthProvider> deferred, Deferred<InteropAppCheckTokenProvider> deferred2, String str, InstanceRegistry instanceRegistry2, GrpcMetadataProvider grpcMetadataProvider) {
        String projectId = firebaseApp2.getOptions().getProjectId();
        if (projectId != null) {
            String str2 = str;
            DatabaseId forDatabase = DatabaseId.forDatabase(projectId, str);
            Deferred<InternalAuthProvider> deferred3 = deferred;
            Deferred<InteropAppCheckTokenProvider> deferred4 = deferred2;
            return new FirebaseFirestore(context2, forDatabase, firebaseApp2.getName(), new FirebaseAuthCredentialsProvider(deferred), new FirebaseAppCheckTokenProvider(deferred2), new FirebaseFirestore$$ExternalSyntheticLambda14(), firebaseApp2, instanceRegistry2, grpcMetadataProvider);
        }
        throw new IllegalArgumentException("FirebaseOptions.getProjectId() cannot be null");
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [java.lang.Object, com.google.firebase.firestore.auth.CredentialsProvider<com.google.firebase.firestore.auth.User>] */
    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.firebase.firestore.auth.CredentialsProvider<java.lang.String>, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.firebase.firestore.util.Function<com.google.firebase.firestore.FirebaseFirestoreSettings, com.google.firebase.firestore.core.ComponentProvider>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    FirebaseFirestore(android.content.Context r1, com.google.firebase.firestore.model.DatabaseId r2, java.lang.String r3, com.google.firebase.firestore.auth.CredentialsProvider<com.google.firebase.firestore.auth.User> r4, com.google.firebase.firestore.auth.CredentialsProvider<java.lang.String> r5, com.google.firebase.firestore.util.Function<com.google.firebase.firestore.FirebaseFirestoreSettings, com.google.firebase.firestore.core.ComponentProvider> r6, com.google.firebase.FirebaseApp r7, com.google.firebase.firestore.FirebaseFirestore.InstanceRegistry r8, com.google.firebase.firestore.remote.GrpcMetadataProvider r9) {
        /*
            r0 = this;
            r0.<init>()
            java.lang.Object r1 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r1)
            android.content.Context r1 = (android.content.Context) r1
            r0.context = r1
            java.lang.Object r1 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r2)
            com.google.firebase.firestore.model.DatabaseId r1 = (com.google.firebase.firestore.model.DatabaseId) r1
            java.lang.Object r1 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r1)
            com.google.firebase.firestore.model.DatabaseId r1 = (com.google.firebase.firestore.model.DatabaseId) r1
            r0.databaseId = r1
            com.google.firebase.firestore.UserDataReader r1 = new com.google.firebase.firestore.UserDataReader
            r1.<init>(r2)
            r0.userDataReader = r1
            java.lang.Object r1 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r3)
            java.lang.String r1 = (java.lang.String) r1
            r0.persistenceKey = r1
            java.lang.Object r1 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r4)
            com.google.firebase.firestore.auth.CredentialsProvider r1 = (com.google.firebase.firestore.auth.CredentialsProvider) r1
            r0.authProvider = r1
            java.lang.Object r1 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r5)
            com.google.firebase.firestore.auth.CredentialsProvider r1 = (com.google.firebase.firestore.auth.CredentialsProvider) r1
            r0.appCheckProvider = r1
            java.lang.Object r1 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r6)
            com.google.firebase.firestore.util.Function r1 = (com.google.firebase.firestore.util.Function) r1
            r0.componentProviderFactory = r1
            com.google.firebase.firestore.FirestoreClientProvider r1 = new com.google.firebase.firestore.FirestoreClientProvider
            com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda9 r2 = new com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda9
            r2.<init>(r0)
            r1.<init>(r2)
            r0.clientProvider = r1
            r0.firebaseApp = r7
            r0.instanceRegistry = r8
            r0.metadataProvider = r9
            com.google.firebase.firestore.FirebaseFirestoreSettings$Builder r1 = new com.google.firebase.firestore.FirebaseFirestoreSettings$Builder
            r1.<init>()
            com.google.firebase.firestore.FirebaseFirestoreSettings r1 = r1.build()
            r0.settings = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.FirebaseFirestore.<init>(android.content.Context, com.google.firebase.firestore.model.DatabaseId, java.lang.String, com.google.firebase.firestore.auth.CredentialsProvider, com.google.firebase.firestore.auth.CredentialsProvider, com.google.firebase.firestore.util.Function, com.google.firebase.FirebaseApp, com.google.firebase.firestore.FirebaseFirestore$InstanceRegistry, com.google.firebase.firestore.remote.GrpcMetadataProvider):void");
    }

    public FirebaseFirestoreSettings getFirestoreSettings() {
        return this.settings;
    }

    public void setFirestoreSettings(FirebaseFirestoreSettings firebaseFirestoreSettings) {
        Preconditions.checkNotNull(firebaseFirestoreSettings, "Provided settings must not be null.");
        synchronized (this.databaseId) {
            FirebaseFirestoreSettings mergeEmulatorSettings = mergeEmulatorSettings(firebaseFirestoreSettings, this.emulatorSettings);
            if (this.clientProvider.isConfigured()) {
                if (!this.settings.equals(mergeEmulatorSettings)) {
                    throw new IllegalStateException("FirebaseFirestore has already been started and its settings can no longer be changed. You can only call setFirestoreSettings() before calling any other methods on a FirebaseFirestore object.");
                }
            }
            this.settings = mergeEmulatorSettings;
        }
    }

    public void useEmulator(String str, int i) {
        synchronized (this.clientProvider) {
            if (!this.clientProvider.isConfigured()) {
                EmulatedServiceSettings emulatedServiceSettings = new EmulatedServiceSettings(str, i);
                this.emulatorSettings = emulatedServiceSettings;
                this.settings = mergeEmulatorSettings(this.settings, emulatedServiceSettings);
            } else {
                throw new IllegalStateException("Cannot call useEmulator() after instance has already been initialized.");
            }
        }
    }

    /* access modifiers changed from: private */
    public FirestoreClient newClient(AsyncQueue asyncQueue) {
        FirestoreClient firestoreClient;
        synchronized (this.clientProvider) {
            firestoreClient = new FirestoreClient(this.context, new DatabaseInfo(this.databaseId, this.persistenceKey, this.settings.getHost(), this.settings.isSslEnabled()), this.authProvider, this.appCheckProvider, asyncQueue, this.metadataProvider, this.componentProviderFactory.apply(this.settings));
        }
        return firestoreClient;
    }

    private FirebaseFirestoreSettings mergeEmulatorSettings(FirebaseFirestoreSettings firebaseFirestoreSettings, EmulatedServiceSettings emulatedServiceSettings) {
        if (emulatedServiceSettings == null) {
            return firebaseFirestoreSettings;
        }
        if (!FirebaseFirestoreSettings.DEFAULT_HOST.equals(firebaseFirestoreSettings.getHost())) {
            Logger.warn(TAG, "Host has been set in FirebaseFirestoreSettings and useEmulator, emulator host will be used.", new Object[0]);
        }
        return new FirebaseFirestoreSettings.Builder(firebaseFirestoreSettings).setHost(emulatedServiceSettings.getHost() + ":" + emulatedServiceSettings.getPort()).setSslEnabled(false).build();
    }

    public FirebaseApp getApp() {
        return this.firebaseApp;
    }

    @Deprecated
    public Task<Void> setIndexConfiguration(String str) {
        this.clientProvider.ensureConfigured();
        Preconditions.checkState(this.settings.isPersistenceEnabled(), "Cannot enable indexes when persistence is disabled");
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("indexes")) {
                JSONArray jSONArray = jSONObject.getJSONArray("indexes");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("collectionGroup");
                    ArrayList arrayList2 = new ArrayList();
                    JSONArray optJSONArray = jSONObject2.optJSONArray("fields");
                    int i2 = 0;
                    while (optJSONArray != null && i2 < optJSONArray.length()) {
                        JSONObject jSONObject3 = optJSONArray.getJSONObject(i2);
                        FieldPath fromServerFormat = FieldPath.fromServerFormat(jSONObject3.getString("fieldPath"));
                        if ("CONTAINS".equals(jSONObject3.optString("arrayConfig"))) {
                            arrayList2.add(FieldIndex.Segment.create(fromServerFormat, FieldIndex.Segment.Kind.CONTAINS));
                        } else if ("ASCENDING".equals(jSONObject3.optString("order"))) {
                            arrayList2.add(FieldIndex.Segment.create(fromServerFormat, FieldIndex.Segment.Kind.ASCENDING));
                        } else {
                            arrayList2.add(FieldIndex.Segment.create(fromServerFormat, FieldIndex.Segment.Kind.DESCENDING));
                        }
                        i2++;
                    }
                    arrayList.add(FieldIndex.create(-1, string, arrayList2, FieldIndex.INITIAL_STATE));
                }
            }
            return (Task) this.clientProvider.call(new FirebaseFirestore$$ExternalSyntheticLambda7(arrayList));
        } catch (JSONException e) {
            throw new IllegalArgumentException("Failed to parse index configuration", e);
        }
    }

    public PersistentCacheIndexManager getPersistentCacheIndexManager() {
        this.clientProvider.ensureConfigured();
        if (this.persistentCacheIndexManager == null && (this.settings.isPersistenceEnabled() || (this.settings.getCacheSettings() instanceof PersistentCacheSettings))) {
            this.persistentCacheIndexManager = new PersistentCacheIndexManager(this.clientProvider);
        }
        return this.persistentCacheIndexManager;
    }

    public CollectionReference collection(String str) {
        Preconditions.checkNotNull(str, "Provided collection path must not be null.");
        this.clientProvider.ensureConfigured();
        return new CollectionReference(ResourcePath.fromString(str), this);
    }

    public DocumentReference document(String str) {
        Preconditions.checkNotNull(str, "Provided document path must not be null.");
        this.clientProvider.ensureConfigured();
        return DocumentReference.forPath(ResourcePath.fromString(str), this);
    }

    public Query collectionGroup(String str) {
        Preconditions.checkNotNull(str, "Provided collection ID must not be null.");
        if (!str.contains("/")) {
            this.clientProvider.ensureConfigured();
            return new Query(new Query(ResourcePath.EMPTY, str), this);
        }
        throw new IllegalArgumentException(String.format("Invalid collectionId '%s'. Collection IDs must not contain '/'.", new Object[]{str}));
    }

    private <ResultT> Task<ResultT> runTransaction(TransactionOptions transactionOptions, Transaction.Function<ResultT> function, Executor executor) {
        this.clientProvider.ensureConfigured();
        return (Task) this.clientProvider.call(new FirebaseFirestore$$ExternalSyntheticLambda12(transactionOptions, new FirebaseFirestore$$ExternalSyntheticLambda11(this, executor, function)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$runTransaction$2$com-google-firebase-firestore-FirebaseFirestore  reason: not valid java name */
    public /* synthetic */ Task m633lambda$runTransaction$2$comgooglefirebasefirestoreFirebaseFirestore(Executor executor, Transaction.Function function, com.google.firebase.firestore.core.Transaction transaction) {
        return Tasks.call(executor, new FirebaseFirestore$$ExternalSyntheticLambda15(this, function, transaction));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$runTransaction$1$com-google-firebase-firestore-FirebaseFirestore  reason: not valid java name */
    public /* synthetic */ Object m632lambda$runTransaction$1$comgooglefirebasefirestoreFirebaseFirestore(Transaction.Function function, com.google.firebase.firestore.core.Transaction transaction) throws Exception {
        return function.apply(new Transaction(transaction, this));
    }

    public <TResult> Task<TResult> runTransaction(Transaction.Function<TResult> function) {
        return runTransaction(TransactionOptions.DEFAULT, function);
    }

    public <TResult> Task<TResult> runTransaction(TransactionOptions transactionOptions, Transaction.Function<TResult> function) {
        Preconditions.checkNotNull(function, "Provided transaction update function must not be null.");
        return runTransaction(transactionOptions, function, com.google.firebase.firestore.core.Transaction.getDefaultExecutor());
    }

    public WriteBatch batch() {
        this.clientProvider.ensureConfigured();
        return new WriteBatch(this);
    }

    public Task<Void> runBatch(WriteBatch.Function function) {
        WriteBatch batch = batch();
        function.apply(batch);
        return batch.commit();
    }

    public Task<Void> terminate() {
        this.instanceRegistry.remove(getDatabaseId().getDatabaseId());
        return this.clientProvider.terminate();
    }

    public Task<Void> waitForPendingWrites() {
        return (Task) this.clientProvider.call(new FirebaseFirestore$$ExternalSyntheticLambda0());
    }

    public Task<Void> enableNetwork() {
        return (Task) this.clientProvider.call(new FirebaseFirestore$$ExternalSyntheticLambda13());
    }

    public Task<Void> disableNetwork() {
        return (Task) this.clientProvider.call(new FirebaseFirestore$$ExternalSyntheticLambda10());
    }

    public static void setLoggingEnabled(boolean z) {
        if (z) {
            Logger.setLogLevel(Logger.Level.DEBUG);
        } else {
            Logger.setLogLevel(Logger.Level.WARN);
        }
    }

    public Task<Void> clearPersistence() {
        return (Task) this.clientProvider.executeIfShutdown(new FirebaseFirestore$$ExternalSyntheticLambda1(this), new FirebaseFirestore$$ExternalSyntheticLambda2());
    }

    /* access modifiers changed from: private */
    public Task<Void> clearPersistence(Executor executor) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        executor.execute(new FirebaseFirestore$$ExternalSyntheticLambda8(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$clearPersistence$5$com-google-firebase-firestore-FirebaseFirestore  reason: not valid java name */
    public /* synthetic */ void m630lambda$clearPersistence$5$comgooglefirebasefirestoreFirebaseFirestore(TaskCompletionSource taskCompletionSource) {
        try {
            SQLitePersistence.clearPersistence(this.context, this.databaseId, this.persistenceKey);
            taskCompletionSource.setResult(null);
        } catch (FirebaseFirestoreException e) {
            taskCompletionSource.setException(e);
        }
    }

    public ListenerRegistration addSnapshotsInSyncListener(Runnable runnable) {
        return addSnapshotsInSyncListener(Executors.DEFAULT_CALLBACK_EXECUTOR, runnable);
    }

    public ListenerRegistration addSnapshotsInSyncListener(Activity activity, Runnable runnable) {
        return addSnapshotsInSyncListener(Executors.DEFAULT_CALLBACK_EXECUTOR, activity, runnable);
    }

    public ListenerRegistration addSnapshotsInSyncListener(Executor executor, Runnable runnable) {
        return addSnapshotsInSyncListener(executor, (Activity) null, runnable);
    }

    public LoadBundleTask loadBundle(InputStream inputStream) {
        LoadBundleTask loadBundleTask = new LoadBundleTask();
        this.clientProvider.procedure(new FirebaseFirestore$$ExternalSyntheticLambda5(inputStream, loadBundleTask));
        return loadBundleTask;
    }

    public LoadBundleTask loadBundle(byte[] bArr) {
        return loadBundle((InputStream) new ByteArrayInputStream(bArr));
    }

    public LoadBundleTask loadBundle(ByteBuffer byteBuffer) {
        return loadBundle((InputStream) new ByteBufferInputStream(byteBuffer));
    }

    public Task<Query> getNamedQuery(String str) {
        return ((Task) this.clientProvider.call(new FirebaseFirestore$$ExternalSyntheticLambda3(str))).continueWith(new FirebaseFirestore$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getNamedQuery$8$com-google-firebase-firestore-FirebaseFirestore  reason: not valid java name */
    public /* synthetic */ Query m631lambda$getNamedQuery$8$comgooglefirebasefirestoreFirebaseFirestore(Task task) throws Exception {
        Query query = (Query) task.getResult();
        if (query != null) {
            return new Query(query, this);
        }
        return null;
    }

    private ListenerRegistration addSnapshotsInSyncListener(Executor executor, Activity activity, Runnable runnable) {
        return (ListenerRegistration) this.clientProvider.call(new FirebaseFirestore$$ExternalSyntheticLambda17(new AsyncEventListener(executor, new FirebaseFirestore$$ExternalSyntheticLambda16(runnable)), activity));
    }

    static /* synthetic */ void lambda$addSnapshotsInSyncListener$9(Runnable runnable, Void voidR, FirebaseFirestoreException firebaseFirestoreException) {
        Assert.hardAssert(firebaseFirestoreException == null, "snapshots-in-sync listeners should never get errors.", new Object[0]);
        runnable.run();
    }

    static /* synthetic */ void lambda$addSnapshotsInSyncListener$10(AsyncEventListener asyncEventListener, FirestoreClient firestoreClient) {
        asyncEventListener.mute();
        firestoreClient.removeSnapshotsInSyncListener(asyncEventListener);
    }

    /* access modifiers changed from: package-private */
    public <T> T callClient(Function<FirestoreClient, T> function) {
        return this.clientProvider.call(function);
    }

    /* access modifiers changed from: package-private */
    public DatabaseId getDatabaseId() {
        return this.databaseId;
    }

    /* access modifiers changed from: package-private */
    public UserDataReader getUserDataReader() {
        return this.userDataReader;
    }

    /* access modifiers changed from: package-private */
    public void validateReference(DocumentReference documentReference) {
        Preconditions.checkNotNull(documentReference, "Provided DocumentReference must not be null.");
        if (documentReference.getFirestore() != this) {
            throw new IllegalArgumentException("Provided document reference is from a different Cloud Firestore instance.");
        }
    }

    static void setClientLanguage(String str) {
        FirestoreChannel.setClientLanguage(str);
    }
}
