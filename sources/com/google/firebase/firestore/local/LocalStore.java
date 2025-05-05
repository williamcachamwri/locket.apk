package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.bundle.BundleCallback;
import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.NamedQuery;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.core.TargetIdGenerator;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class LocalStore implements BundleCallback {
    private static final long RESUME_TOKEN_MAX_AGE_SECONDS = TimeUnit.MINUTES.toSeconds(5);
    private final BundleCache bundleCache;
    private DocumentOverlayCache documentOverlayCache;
    private GlobalsCache globalsCache;
    private IndexManager indexManager;
    private LocalDocumentsView localDocuments;
    private final ReferenceSet localViewReferences;
    private MutationQueue mutationQueue;
    private final Persistence persistence;
    private final SparseArray<TargetData> queryDataByTarget = new SparseArray<>();
    private final QueryEngine queryEngine;
    private final RemoteDocumentCache remoteDocuments;
    private final TargetCache targetCache;
    private final Map<Target, Integer> targetIdByTarget = new HashMap();
    private final TargetIdGenerator targetIdGenerator;

    public LocalStore(Persistence persistence2, QueryEngine queryEngine2, User user) {
        Assert.hardAssert(persistence2.isStarted(), "LocalStore was passed an unstarted persistence implementation", new Object[0]);
        this.persistence = persistence2;
        this.queryEngine = queryEngine2;
        this.globalsCache = persistence2.getGlobalsCache();
        TargetCache targetCache2 = persistence2.getTargetCache();
        this.targetCache = targetCache2;
        this.bundleCache = persistence2.getBundleCache();
        this.targetIdGenerator = TargetIdGenerator.forTargetCache(targetCache2.getHighestTargetId());
        this.remoteDocuments = persistence2.getRemoteDocumentCache();
        ReferenceSet referenceSet = new ReferenceSet();
        this.localViewReferences = referenceSet;
        persistence2.getReferenceDelegate().setInMemoryPins(referenceSet);
        initializeUserComponents(user);
    }

    private void initializeUserComponents(User user) {
        IndexManager indexManager2 = this.persistence.getIndexManager(user);
        this.indexManager = indexManager2;
        this.mutationQueue = this.persistence.getMutationQueue(user, indexManager2);
        this.documentOverlayCache = this.persistence.getDocumentOverlayCache(user);
        this.localDocuments = new LocalDocumentsView(this.remoteDocuments, this.mutationQueue, this.documentOverlayCache, this.indexManager);
        this.remoteDocuments.setIndexManager(this.indexManager);
        this.queryEngine.initialize(this.localDocuments, this.indexManager);
    }

    public void start() {
        this.persistence.getOverlayMigrationManager().run();
        startIndexManager();
        startMutationQueue();
    }

    private void startIndexManager() {
        this.persistence.runTransaction("Start IndexManager", (Runnable) new LocalStore$$ExternalSyntheticLambda17(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startIndexManager$0$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ void m695lambda$startIndexManager$0$comgooglefirebasefirestorelocalLocalStore() {
        this.indexManager.start();
    }

    private void startMutationQueue() {
        this.persistence.runTransaction("Start MutationQueue", (Runnable) new LocalStore$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startMutationQueue$1$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ void m696lambda$startMutationQueue$1$comgooglefirebasefirestorelocalLocalStore() {
        this.mutationQueue.start();
    }

    public IndexManager getIndexManagerForCurrentUser() {
        return this.indexManager;
    }

    public LocalDocumentsView getLocalDocumentsForCurrentUser() {
        return this.localDocuments;
    }

    public ImmutableSortedMap<DocumentKey, Document> handleUserChange(User user) {
        List<MutationBatch> allMutationBatches = this.mutationQueue.getAllMutationBatches();
        initializeUserComponents(user);
        startIndexManager();
        startMutationQueue();
        List<MutationBatch> allMutationBatches2 = this.mutationQueue.getAllMutationBatches();
        ImmutableSortedSet<DocumentKey> emptyKeySet = DocumentKey.emptyKeySet();
        for (List<MutationBatch> it : Arrays.asList(new List[]{allMutationBatches, allMutationBatches2})) {
            for (MutationBatch mutations : it) {
                for (Mutation key : mutations.getMutations()) {
                    emptyKeySet = emptyKeySet.insert(key.getKey());
                }
            }
        }
        return this.localDocuments.getDocuments(emptyKeySet);
    }

    public LocalDocumentsResult writeLocally(List<Mutation> list) {
        Timestamp now = Timestamp.now();
        HashSet hashSet = new HashSet();
        for (Mutation key : list) {
            hashSet.add(key.getKey());
        }
        return (LocalDocumentsResult) this.persistence.runTransaction("Locally write mutations", new LocalStore$$ExternalSyntheticLambda3(this, hashSet, list, now));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$writeLocally$2$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ LocalDocumentsResult m697lambda$writeLocally$2$comgooglefirebasefirestorelocalLocalStore(Set set, List list, Timestamp timestamp) {
        Map<DocumentKey, MutableDocument> all = this.remoteDocuments.getAll(set);
        HashSet hashSet = new HashSet();
        for (Map.Entry next : all.entrySet()) {
            if (!((MutableDocument) next.getValue()).isValidDocument()) {
                hashSet.add((DocumentKey) next.getKey());
            }
        }
        Map<DocumentKey, OverlayedDocument> overlayedDocuments = this.localDocuments.getOverlayedDocuments(all);
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Mutation mutation = (Mutation) it.next();
            ObjectValue extractTransformBaseValue = mutation.extractTransformBaseValue(overlayedDocuments.get(mutation.getKey()).getDocument());
            if (extractTransformBaseValue != null) {
                arrayList.add(new PatchMutation(mutation.getKey(), extractTransformBaseValue, extractTransformBaseValue.getFieldMask(), Precondition.exists(true)));
            }
        }
        MutationBatch addMutationBatch = this.mutationQueue.addMutationBatch(timestamp, arrayList, list);
        this.documentOverlayCache.saveOverlays(addMutationBatch.getBatchId(), addMutationBatch.applyToLocalDocumentSet(overlayedDocuments, hashSet));
        return LocalDocumentsResult.fromOverlayedDocuments(addMutationBatch.getBatchId(), overlayedDocuments);
    }

    public ImmutableSortedMap<DocumentKey, Document> acknowledgeBatch(MutationBatchResult mutationBatchResult) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Acknowledge batch", new LocalStore$$ExternalSyntheticLambda2(this, mutationBatchResult));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$acknowledgeBatch$3$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ ImmutableSortedMap m679lambda$acknowledgeBatch$3$comgooglefirebasefirestorelocalLocalStore(MutationBatchResult mutationBatchResult) {
        MutationBatch batch = mutationBatchResult.getBatch();
        this.mutationQueue.acknowledgeBatch(batch, mutationBatchResult.getStreamToken());
        applyWriteToRemoteDocuments(mutationBatchResult);
        this.mutationQueue.performConsistencyCheck();
        this.documentOverlayCache.removeOverlaysForBatchId(mutationBatchResult.getBatch().getBatchId());
        this.localDocuments.recalculateAndSaveOverlays(getKeysWithTransformResults(mutationBatchResult));
        return this.localDocuments.getDocuments(batch.getKeys());
    }

    private Set<DocumentKey> getKeysWithTransformResults(MutationBatchResult mutationBatchResult) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < mutationBatchResult.getMutationResults().size(); i++) {
            if (!mutationBatchResult.getMutationResults().get(i).getTransformResults().isEmpty()) {
                hashSet.add(mutationBatchResult.getBatch().getMutations().get(i).getKey());
            }
        }
        return hashSet;
    }

    public ImmutableSortedMap<DocumentKey, Document> rejectBatch(int i) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Reject batch", new LocalStore$$ExternalSyntheticLambda11(this, i));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$rejectBatch$4$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ ImmutableSortedMap m690lambda$rejectBatch$4$comgooglefirebasefirestorelocalLocalStore(int i) {
        MutationBatch lookupMutationBatch = this.mutationQueue.lookupMutationBatch(i);
        Assert.hardAssert(lookupMutationBatch != null, "Attempt to reject nonexistent batch!", new Object[0]);
        this.mutationQueue.removeMutationBatch(lookupMutationBatch);
        this.mutationQueue.performConsistencyCheck();
        this.documentOverlayCache.removeOverlaysForBatchId(i);
        this.localDocuments.recalculateAndSaveOverlays(lookupMutationBatch.getKeys());
        return this.localDocuments.getDocuments(lookupMutationBatch.getKeys());
    }

    public int getHighestUnacknowledgedBatchId() {
        return this.mutationQueue.getHighestUnacknowledgedBatchId();
    }

    public ByteString getLastStreamToken() {
        return this.mutationQueue.getLastStreamToken();
    }

    public void setLastStreamToken(ByteString byteString) {
        this.persistence.runTransaction("Set stream token", (Runnable) new LocalStore$$ExternalSyntheticLambda5(this, byteString));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setLastStreamToken$5$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ void m694lambda$setLastStreamToken$5$comgooglefirebasefirestorelocalLocalStore(ByteString byteString) {
        this.mutationQueue.setLastStreamToken(byteString);
    }

    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.targetCache.getLastRemoteSnapshotVersion();
    }

    public ByteString getSessionToken() {
        return this.globalsCache.getSessionsToken();
    }

    public void setSessionsToken(ByteString byteString) {
        this.globalsCache.setSessionToken(byteString);
    }

    public ImmutableSortedMap<DocumentKey, Document> applyRemoteEvent(RemoteEvent remoteEvent) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Apply remote event", new LocalStore$$ExternalSyntheticLambda12(this, remoteEvent, remoteEvent.getSnapshotVersion()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyRemoteEvent$6$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ ImmutableSortedMap m682lambda$applyRemoteEvent$6$comgooglefirebasefirestorelocalLocalStore(RemoteEvent remoteEvent, SnapshotVersion snapshotVersion) {
        Map<Integer, TargetChange> targetChanges = remoteEvent.getTargetChanges();
        long currentSequenceNumber = this.persistence.getReferenceDelegate().getCurrentSequenceNumber();
        for (Map.Entry next : targetChanges.entrySet()) {
            int intValue = ((Integer) next.getKey()).intValue();
            TargetChange targetChange = (TargetChange) next.getValue();
            TargetData targetData = this.queryDataByTarget.get(intValue);
            if (targetData != null) {
                this.targetCache.removeMatchingKeys(targetChange.getRemovedDocuments(), intValue);
                this.targetCache.addMatchingKeys(targetChange.getAddedDocuments(), intValue);
                TargetData withSequenceNumber = targetData.withSequenceNumber(currentSequenceNumber);
                if (remoteEvent.getTargetMismatches().containsKey(Integer.valueOf(intValue))) {
                    withSequenceNumber = withSequenceNumber.withResumeToken(ByteString.EMPTY, SnapshotVersion.NONE).withLastLimboFreeSnapshotVersion(SnapshotVersion.NONE);
                } else if (!targetChange.getResumeToken().isEmpty()) {
                    withSequenceNumber = withSequenceNumber.withResumeToken(targetChange.getResumeToken(), remoteEvent.getSnapshotVersion());
                }
                this.queryDataByTarget.put(intValue, withSequenceNumber);
                if (shouldPersistTargetData(targetData, withSequenceNumber, targetChange)) {
                    this.targetCache.updateTargetData(withSequenceNumber);
                }
            }
        }
        Map<DocumentKey, MutableDocument> documentUpdates = remoteEvent.getDocumentUpdates();
        Set<DocumentKey> resolvedLimboDocuments = remoteEvent.getResolvedLimboDocuments();
        for (DocumentKey next2 : documentUpdates.keySet()) {
            if (resolvedLimboDocuments.contains(next2)) {
                this.persistence.getReferenceDelegate().updateLimboDocument(next2);
            }
        }
        DocumentChangeResult populateDocumentChanges = populateDocumentChanges(documentUpdates);
        Map access$200 = populateDocumentChanges.changedDocuments;
        SnapshotVersion lastRemoteSnapshotVersion = this.targetCache.getLastRemoteSnapshotVersion();
        if (!snapshotVersion.equals(SnapshotVersion.NONE)) {
            Assert.hardAssert(snapshotVersion.compareTo(lastRemoteSnapshotVersion) >= 0, "Watch stream reverted to previous snapshot?? (%s < %s)", snapshotVersion, lastRemoteSnapshotVersion);
            this.targetCache.setLastRemoteSnapshotVersion(snapshotVersion);
        }
        return this.localDocuments.getLocalViewOfDocuments(access$200, populateDocumentChanges.existenceChangedKeys);
    }

    private static class DocumentChangeResult {
        /* access modifiers changed from: private */
        public final Map<DocumentKey, MutableDocument> changedDocuments;
        /* access modifiers changed from: private */
        public final Set<DocumentKey> existenceChangedKeys;

        private DocumentChangeResult(Map<DocumentKey, MutableDocument> map, Set<DocumentKey> set) {
            this.changedDocuments = map;
            this.existenceChangedKeys = set;
        }
    }

    private DocumentChangeResult populateDocumentChanges(Map<DocumentKey, MutableDocument> map) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        Map<DocumentKey, MutableDocument> all = this.remoteDocuments.getAll(map.keySet());
        for (Map.Entry next : map.entrySet()) {
            DocumentKey documentKey = (DocumentKey) next.getKey();
            MutableDocument mutableDocument = (MutableDocument) next.getValue();
            MutableDocument mutableDocument2 = all.get(documentKey);
            if (mutableDocument.isFoundDocument() != mutableDocument2.isFoundDocument()) {
                hashSet.add(documentKey);
            }
            if (mutableDocument.isNoDocument() && mutableDocument.getVersion().equals(SnapshotVersion.NONE)) {
                arrayList.add(mutableDocument.getKey());
                hashMap.put(documentKey, mutableDocument);
            } else if (!mutableDocument2.isValidDocument() || mutableDocument.getVersion().compareTo(mutableDocument2.getVersion()) > 0 || (mutableDocument.getVersion().compareTo(mutableDocument2.getVersion()) == 0 && mutableDocument2.hasPendingWrites())) {
                Assert.hardAssert(!SnapshotVersion.NONE.equals(mutableDocument.getReadTime()), "Cannot add a document when the remote version is zero", new Object[0]);
                this.remoteDocuments.add(mutableDocument, mutableDocument.getReadTime());
                hashMap.put(documentKey, mutableDocument);
            } else {
                Logger.debug("LocalStore", "Ignoring outdated watch update for %s.Current version: %s  Watch version: %s", documentKey, mutableDocument2.getVersion(), mutableDocument.getVersion());
            }
        }
        this.remoteDocuments.removeAll(arrayList);
        return new DocumentChangeResult(hashMap, hashSet);
    }

    private static boolean shouldPersistTargetData(TargetData targetData, TargetData targetData2, TargetChange targetChange) {
        if (targetData.getResumeToken().isEmpty()) {
            return true;
        }
        long seconds = targetData2.getSnapshotVersion().getTimestamp().getSeconds() - targetData.getSnapshotVersion().getTimestamp().getSeconds();
        long j = RESUME_TOKEN_MAX_AGE_SECONDS;
        if (seconds >= j || targetData2.getLastLimboFreeSnapshotVersion().getTimestamp().getSeconds() - targetData.getLastLimboFreeSnapshotVersion().getTimestamp().getSeconds() >= j) {
            return true;
        }
        if (targetChange == null) {
            return false;
        }
        if (targetChange.getAddedDocuments().size() + targetChange.getModifiedDocuments().size() + targetChange.getRemovedDocuments().size() > 0) {
            return true;
        }
        return false;
    }

    public void notifyLocalViewChanges(List<LocalViewChanges> list) {
        this.persistence.runTransaction("notifyLocalViewChanges", (Runnable) new LocalStore$$ExternalSyntheticLambda15(this, list));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$notifyLocalViewChanges$7$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ void m689lambda$notifyLocalViewChanges$7$comgooglefirebasefirestorelocalLocalStore(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            LocalViewChanges localViewChanges = (LocalViewChanges) it.next();
            int targetId = localViewChanges.getTargetId();
            this.localViewReferences.addReferences(localViewChanges.getAdded(), targetId);
            ImmutableSortedSet<DocumentKey> removed = localViewChanges.getRemoved();
            Iterator<DocumentKey> it2 = removed.iterator();
            while (it2.hasNext()) {
                this.persistence.getReferenceDelegate().removeReference(it2.next());
            }
            this.localViewReferences.removeReferences(removed, targetId);
            if (!localViewChanges.isFromCache()) {
                TargetData targetData = this.queryDataByTarget.get(targetId);
                Assert.hardAssert(targetData != null, "Can't set limbo-free snapshot version for unknown target: %s", Integer.valueOf(targetId));
                TargetData withLastLimboFreeSnapshotVersion = targetData.withLastLimboFreeSnapshotVersion(targetData.getSnapshotVersion());
                this.queryDataByTarget.put(targetId, withLastLimboFreeSnapshotVersion);
                if (shouldPersistTargetData(targetData, withLastLimboFreeSnapshotVersion, (TargetChange) null)) {
                    this.targetCache.updateTargetData(withLastLimboFreeSnapshotVersion);
                }
            }
        }
    }

    public MutationBatch getNextMutationBatch(int i) {
        return this.mutationQueue.getNextMutationBatchAfterBatchId(i);
    }

    public Document readDocument(DocumentKey documentKey) {
        return this.localDocuments.getDocument(documentKey);
    }

    public TargetData allocateTarget(Target target) {
        int i;
        TargetData targetData = this.targetCache.getTargetData(target);
        if (targetData != null) {
            i = targetData.getTargetId();
        } else {
            AllocateQueryHolder allocateQueryHolder = new AllocateQueryHolder();
            this.persistence.runTransaction("Allocate target", (Runnable) new LocalStore$$ExternalSyntheticLambda6(this, allocateQueryHolder, target));
            i = allocateQueryHolder.targetId;
            targetData = allocateQueryHolder.cached;
        }
        if (this.queryDataByTarget.get(i) == null) {
            this.queryDataByTarget.put(i, targetData);
            this.targetIdByTarget.put(target, Integer.valueOf(i));
        }
        return targetData;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$allocateTarget$8$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ void m680lambda$allocateTarget$8$comgooglefirebasefirestorelocalLocalStore(AllocateQueryHolder allocateQueryHolder, Target target) {
        allocateQueryHolder.targetId = this.targetIdGenerator.nextId();
        allocateQueryHolder.cached = new TargetData(target, allocateQueryHolder.targetId, this.persistence.getReferenceDelegate().getCurrentSequenceNumber(), QueryPurpose.LISTEN);
        this.targetCache.addTargetData(allocateQueryHolder.cached);
    }

    /* access modifiers changed from: package-private */
    public TargetData getTargetData(Target target) {
        Integer num = this.targetIdByTarget.get(target);
        if (num != null) {
            return this.queryDataByTarget.get(num.intValue());
        }
        return this.targetCache.getTargetData(target);
    }

    public boolean hasNewerBundle(BundleMetadata bundleMetadata) {
        return ((Boolean) this.persistence.runTransaction("Has newer bundle", new LocalStore$$ExternalSyntheticLambda9(this, bundleMetadata))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$hasNewerBundle$9$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ Boolean m688lambda$hasNewerBundle$9$comgooglefirebasefirestorelocalLocalStore(BundleMetadata bundleMetadata) {
        BundleMetadata bundleMetadata2 = this.bundleCache.getBundleMetadata(bundleMetadata.getBundleId());
        return Boolean.valueOf(bundleMetadata2 != null && bundleMetadata2.getCreateTime().compareTo(bundleMetadata.getCreateTime()) >= 0);
    }

    public void saveBundle(BundleMetadata bundleMetadata) {
        this.persistence.runTransaction("Save bundle", (Runnable) new LocalStore$$ExternalSyntheticLambda16(this, bundleMetadata));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$saveBundle$10$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ void m692lambda$saveBundle$10$comgooglefirebasefirestorelocalLocalStore(BundleMetadata bundleMetadata) {
        this.bundleCache.saveBundleMetadata(bundleMetadata);
    }

    public ImmutableSortedMap<DocumentKey, Document> applyBundledDocuments(ImmutableSortedMap<DocumentKey, MutableDocument> immutableSortedMap, String str) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Apply bundle documents", new LocalStore$$ExternalSyntheticLambda13(this, immutableSortedMap, allocateTarget(newUmbrellaTarget(str))));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyBundledDocuments$11$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ ImmutableSortedMap m681lambda$applyBundledDocuments$11$comgooglefirebasefirestorelocalLocalStore(ImmutableSortedMap immutableSortedMap, TargetData targetData) {
        ImmutableSortedSet<DocumentKey> emptyKeySet = DocumentKey.emptyKeySet();
        HashMap hashMap = new HashMap();
        Iterator it = immutableSortedMap.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            DocumentKey documentKey = (DocumentKey) entry.getKey();
            MutableDocument mutableDocument = (MutableDocument) entry.getValue();
            if (mutableDocument.isFoundDocument()) {
                emptyKeySet = emptyKeySet.insert(documentKey);
            }
            hashMap.put(documentKey, mutableDocument);
        }
        this.targetCache.removeMatchingKeysForTargetId(targetData.getTargetId());
        this.targetCache.addMatchingKeys(emptyKeySet, targetData.getTargetId());
        DocumentChangeResult populateDocumentChanges = populateDocumentChanges(hashMap);
        return this.localDocuments.getLocalViewOfDocuments(populateDocumentChanges.changedDocuments, populateDocumentChanges.existenceChangedKeys);
    }

    public void saveNamedQuery(NamedQuery namedQuery, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        TargetData allocateTarget = allocateTarget(namedQuery.getBundledQuery().getTarget());
        this.persistence.runTransaction("Saved named query", (Runnable) new LocalStore$$ExternalSyntheticLambda4(this, namedQuery, allocateTarget, allocateTarget.getTargetId(), immutableSortedSet));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$saveNamedQuery$12$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ void m693lambda$saveNamedQuery$12$comgooglefirebasefirestorelocalLocalStore(NamedQuery namedQuery, TargetData targetData, int i, ImmutableSortedSet immutableSortedSet) {
        if (namedQuery.getReadTime().compareTo(targetData.getSnapshotVersion()) > 0) {
            TargetData withResumeToken = targetData.withResumeToken(ByteString.EMPTY, namedQuery.getReadTime());
            this.queryDataByTarget.append(i, withResumeToken);
            this.targetCache.updateTargetData(withResumeToken);
            this.targetCache.removeMatchingKeysForTargetId(i);
            this.targetCache.addMatchingKeys(immutableSortedSet, i);
        }
        this.bundleCache.saveNamedQuery(namedQuery);
    }

    public NamedQuery getNamedQuery(String str) {
        return (NamedQuery) this.persistence.runTransaction("Get named query", new LocalStore$$ExternalSyntheticLambda10(this, str));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getNamedQuery$13$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ NamedQuery m687lambda$getNamedQuery$13$comgooglefirebasefirestorelocalLocalStore(String str) {
        return this.bundleCache.getNamedQuery(str);
    }

    /* access modifiers changed from: package-private */
    public Collection<FieldIndex> getFieldIndexes() {
        return (Collection) this.persistence.runTransaction("Get indexes", new LocalStore$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getFieldIndexes$14$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ Collection m686lambda$getFieldIndexes$14$comgooglefirebasefirestorelocalLocalStore() {
        return this.indexManager.getFieldIndexes();
    }

    public void configureFieldIndexes(List<FieldIndex> list) {
        this.persistence.runTransaction("Configure indexes", (Runnable) new LocalStore$$ExternalSyntheticLambda19(this, list));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$configureFieldIndexes$15$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ void m684lambda$configureFieldIndexes$15$comgooglefirebasefirestorelocalLocalStore(List list) {
        Collection<FieldIndex> fieldIndexes = this.indexManager.getFieldIndexes();
        Comparator<FieldIndex> comparator = FieldIndex.SEMANTIC_COMPARATOR;
        IndexManager indexManager2 = this.indexManager;
        Objects.requireNonNull(indexManager2);
        LocalStore$$ExternalSyntheticLambda7 localStore$$ExternalSyntheticLambda7 = new LocalStore$$ExternalSyntheticLambda7(indexManager2);
        IndexManager indexManager3 = this.indexManager;
        Objects.requireNonNull(indexManager3);
        Util.diffCollections(fieldIndexes, (Collection<FieldIndex>) list, comparator, localStore$$ExternalSyntheticLambda7, (LocalStore$$ExternalSyntheticLambda7) new LocalStore$$ExternalSyntheticLambda8(indexManager3));
    }

    public void deleteAllFieldIndexes() {
        this.persistence.runTransaction("Delete All Indexes", (Runnable) new LocalStore$$ExternalSyntheticLambda14(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$deleteAllFieldIndexes$16$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ void m685lambda$deleteAllFieldIndexes$16$comgooglefirebasefirestorelocalLocalStore() {
        this.indexManager.deleteAllFieldIndexes();
    }

    public void setIndexAutoCreationEnabled(boolean z) {
        this.queryEngine.setIndexAutoCreationEnabled(z);
    }

    private static class AllocateQueryHolder {
        TargetData cached;
        int targetId;

        private AllocateQueryHolder() {
        }
    }

    public void releaseTarget(int i) {
        this.persistence.runTransaction("Release target", (Runnable) new LocalStore$$ExternalSyntheticLambda20(this, i));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$releaseTarget$17$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ void m691lambda$releaseTarget$17$comgooglefirebasefirestorelocalLocalStore(int i) {
        TargetData targetData = this.queryDataByTarget.get(i);
        Assert.hardAssert(targetData != null, "Tried to release nonexistent target: %s", Integer.valueOf(i));
        Iterator<DocumentKey> it = this.localViewReferences.removeReferencesForId(i).iterator();
        while (it.hasNext()) {
            this.persistence.getReferenceDelegate().removeReference(it.next());
        }
        this.persistence.getReferenceDelegate().removeTarget(targetData);
        this.queryDataByTarget.remove(i);
        this.targetIdByTarget.remove(targetData.getTarget());
    }

    public QueryResult executeQuery(Query query, boolean z) {
        TargetData targetData = getTargetData(query.toTarget());
        SnapshotVersion snapshotVersion = SnapshotVersion.NONE;
        ImmutableSortedSet<DocumentKey> emptyKeySet = DocumentKey.emptyKeySet();
        if (targetData != null) {
            snapshotVersion = targetData.getLastLimboFreeSnapshotVersion();
            emptyKeySet = this.targetCache.getMatchingKeysForTargetId(targetData.getTargetId());
        }
        QueryEngine queryEngine2 = this.queryEngine;
        if (!z) {
            snapshotVersion = SnapshotVersion.NONE;
        }
        return new QueryResult(queryEngine2.getDocumentsMatchingQuery(query, snapshotVersion, emptyKeySet), emptyKeySet);
    }

    public ImmutableSortedSet<DocumentKey> getRemoteDocumentKeys(int i) {
        return this.targetCache.getMatchingKeysForTargetId(i);
    }

    private void applyWriteToRemoteDocuments(MutationBatchResult mutationBatchResult) {
        MutationBatch batch = mutationBatchResult.getBatch();
        for (DocumentKey next : batch.getKeys()) {
            MutableDocument mutableDocument = this.remoteDocuments.get(next);
            SnapshotVersion snapshotVersion = mutationBatchResult.getDocVersions().get(next);
            Assert.hardAssert(snapshotVersion != null, "docVersions should contain every doc in the write.", new Object[0]);
            if (mutableDocument.getVersion().compareTo(snapshotVersion) < 0) {
                batch.applyToRemoteDocument(mutableDocument, mutationBatchResult);
                if (mutableDocument.isValidDocument()) {
                    this.remoteDocuments.add(mutableDocument, mutationBatchResult.getCommitVersion());
                }
            }
        }
        this.mutationQueue.removeMutationBatch(batch);
    }

    public LruGarbageCollector.Results collectGarbage(LruGarbageCollector lruGarbageCollector) {
        return (LruGarbageCollector.Results) this.persistence.runTransaction("Collect garbage", new LocalStore$$ExternalSyntheticLambda18(this, lruGarbageCollector));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$collectGarbage$18$com-google-firebase-firestore-local-LocalStore  reason: not valid java name */
    public /* synthetic */ LruGarbageCollector.Results m683lambda$collectGarbage$18$comgooglefirebasefirestorelocalLocalStore(LruGarbageCollector lruGarbageCollector) {
        return lruGarbageCollector.collect(this.queryDataByTarget);
    }

    private static Target newUmbrellaTarget(String str) {
        return Query.atPath(ResourcePath.fromString("__bundle__/docs/" + str)).toTarget();
    }
}
