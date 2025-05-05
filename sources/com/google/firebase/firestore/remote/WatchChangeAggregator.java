package com.google.firebase.firestore.remote;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.remote.BloomFilter;
import com.google.firebase.firestore.remote.TestingHooks;
import com.google.firebase.firestore.remote.WatchChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.firestore.v1.BloomFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WatchChangeAggregator {
    private static final String LOG_TAG = "WatchChangeAggregator";
    private final DatabaseId databaseId;
    private Map<DocumentKey, Set<Integer>> pendingDocumentTargetMapping = new HashMap();
    private Map<DocumentKey, MutableDocument> pendingDocumentUpdates = new HashMap();
    private Map<Integer, QueryPurpose> pendingTargetResets = new HashMap();
    private final TargetMetadataProvider targetMetadataProvider;
    private final Map<Integer, TargetState> targetStates = new HashMap();

    enum BloomFilterApplicationStatus {
        SUCCESS,
        SKIPPED,
        FALSE_POSITIVE
    }

    public interface TargetMetadataProvider {
        ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i);

        TargetData getTargetDataForTarget(int i);
    }

    public WatchChangeAggregator(DatabaseId databaseId2, TargetMetadataProvider targetMetadataProvider2) {
        this.databaseId = databaseId2;
        this.targetMetadataProvider = targetMetadataProvider2;
    }

    public void handleDocumentChange(WatchChange.DocumentChange documentChange) {
        MutableDocument newDocument = documentChange.getNewDocument();
        DocumentKey documentKey = documentChange.getDocumentKey();
        for (Integer intValue : documentChange.getUpdatedTargetIds()) {
            int intValue2 = intValue.intValue();
            if (newDocument == null || !newDocument.isFoundDocument()) {
                removeDocumentFromTarget(intValue2, documentKey, newDocument);
            } else {
                addDocumentToTarget(intValue2, newDocument);
            }
        }
        for (Integer intValue3 : documentChange.getRemovedTargetIds()) {
            removeDocumentFromTarget(intValue3.intValue(), documentKey, documentChange.getNewDocument());
        }
    }

    public void handleTargetChange(WatchChange.WatchTargetChange watchTargetChange) {
        for (Integer intValue : getTargetIds(watchTargetChange)) {
            int intValue2 = intValue.intValue();
            TargetState ensureTargetState = ensureTargetState(intValue2);
            int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[watchTargetChange.getChangeType().ordinal()];
            boolean z = true;
            if (i != 1) {
                if (i == 2) {
                    ensureTargetState.recordTargetResponse();
                    if (!ensureTargetState.isPending()) {
                        ensureTargetState.clearChanges();
                    }
                    ensureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
                } else if (i == 3) {
                    ensureTargetState.recordTargetResponse();
                    if (!ensureTargetState.isPending()) {
                        removeTarget(intValue2);
                    }
                    if (watchTargetChange.getCause() != null) {
                        z = false;
                    }
                    Assert.hardAssert(z, "WatchChangeAggregator does not handle errored targets", new Object[0]);
                } else if (i != 4) {
                    if (i != 5) {
                        throw Assert.fail("Unknown target watch change state: %s", watchTargetChange.getChangeType());
                    } else if (isActiveTarget(intValue2)) {
                        resetTarget(intValue2);
                        ensureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
                    }
                } else if (isActiveTarget(intValue2)) {
                    ensureTargetState.markCurrent();
                    ensureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
                }
            } else if (isActiveTarget(intValue2)) {
                ensureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
            }
        }
    }

    /* renamed from: com.google.firebase.firestore.remote.WatchChangeAggregator$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.firebase.firestore.remote.WatchChange$WatchTargetChangeType[] r0 = com.google.firebase.firestore.remote.WatchChange.WatchTargetChangeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType = r0
                com.google.firebase.firestore.remote.WatchChange$WatchTargetChangeType r1 = com.google.firebase.firestore.remote.WatchChange.WatchTargetChangeType.NoChange     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.remote.WatchChange$WatchTargetChangeType r1 = com.google.firebase.firestore.remote.WatchChange.WatchTargetChangeType.Added     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.firestore.remote.WatchChange$WatchTargetChangeType r1 = com.google.firebase.firestore.remote.WatchChange.WatchTargetChangeType.Removed     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.firestore.remote.WatchChange$WatchTargetChangeType r1 = com.google.firebase.firestore.remote.WatchChange.WatchTargetChangeType.Current     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.firebase.firestore.remote.WatchChange$WatchTargetChangeType r1 = com.google.firebase.firestore.remote.WatchChange.WatchTargetChangeType.Reset     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.remote.WatchChangeAggregator.AnonymousClass1.<clinit>():void");
        }
    }

    private Collection<Integer> getTargetIds(WatchChange.WatchTargetChange watchTargetChange) {
        List<Integer> targetIds = watchTargetChange.getTargetIds();
        if (!targetIds.isEmpty()) {
            return targetIds;
        }
        ArrayList arrayList = new ArrayList();
        for (Integer next : this.targetStates.keySet()) {
            if (isActiveTarget(next.intValue())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public void handleExistenceFilter(WatchChange.ExistenceFilterWatchChange existenceFilterWatchChange) {
        BloomFilterApplicationStatus bloomFilterApplicationStatus;
        QueryPurpose queryPurpose;
        int targetId = existenceFilterWatchChange.getTargetId();
        int count = existenceFilterWatchChange.getExistenceFilter().getCount();
        TargetData queryDataForActiveTarget = queryDataForActiveTarget(targetId);
        if (queryDataForActiveTarget != null) {
            Target target = queryDataForActiveTarget.getTarget();
            if (!target.isDocumentQuery()) {
                int currentDocumentCountForTarget = getCurrentDocumentCountForTarget(targetId);
                if (currentDocumentCountForTarget != count) {
                    BloomFilter parseBloomFilter = parseBloomFilter(existenceFilterWatchChange);
                    if (parseBloomFilter != null) {
                        bloomFilterApplicationStatus = applyBloomFilter(parseBloomFilter, existenceFilterWatchChange, currentDocumentCountForTarget);
                    } else {
                        bloomFilterApplicationStatus = BloomFilterApplicationStatus.SKIPPED;
                    }
                    if (bloomFilterApplicationStatus != BloomFilterApplicationStatus.SUCCESS) {
                        resetTarget(targetId);
                        if (bloomFilterApplicationStatus == BloomFilterApplicationStatus.FALSE_POSITIVE) {
                            queryPurpose = QueryPurpose.EXISTENCE_FILTER_MISMATCH_BLOOM;
                        } else {
                            queryPurpose = QueryPurpose.EXISTENCE_FILTER_MISMATCH;
                        }
                        this.pendingTargetResets.put(Integer.valueOf(targetId), queryPurpose);
                    }
                    TestingHooks.getInstance().notifyOnExistenceFilterMismatch(TestingHooks.ExistenceFilterMismatchInfo.from(currentDocumentCountForTarget, existenceFilterWatchChange.getExistenceFilter(), this.databaseId, parseBloomFilter, bloomFilterApplicationStatus));
                }
            } else if (count == 0) {
                DocumentKey fromPath = DocumentKey.fromPath(target.getPath());
                removeDocumentFromTarget(targetId, fromPath, MutableDocument.newNoDocument(fromPath, SnapshotVersion.NONE));
            } else {
                boolean z = true;
                if (count != 1) {
                    z = false;
                }
                Assert.hardAssert(z, "Single document existence filter with count: %d", Integer.valueOf(count));
            }
        }
    }

    private BloomFilter parseBloomFilter(WatchChange.ExistenceFilterWatchChange existenceFilterWatchChange) {
        BloomFilter unchangedNames = existenceFilterWatchChange.getExistenceFilter().getUnchangedNames();
        if (unchangedNames != null && unchangedNames.hasBits()) {
            try {
                BloomFilter create = BloomFilter.create(unchangedNames.getBits().getBitmap(), unchangedNames.getBits().getPadding(), unchangedNames.getHashCount());
                if (create.getBitCount() == 0) {
                    return null;
                }
                return create;
            } catch (BloomFilter.BloomFilterCreateException e) {
                Logger.warn(LOG_TAG, "Applying bloom filter failed: (" + e.getMessage() + "); ignoring the bloom filter and falling back to full re-query.", new Object[0]);
            }
        }
        return null;
    }

    private BloomFilterApplicationStatus applyBloomFilter(BloomFilter bloomFilter, WatchChange.ExistenceFilterWatchChange existenceFilterWatchChange, int i) {
        if (existenceFilterWatchChange.getExistenceFilter().getCount() == i - filterRemovedDocuments(bloomFilter, existenceFilterWatchChange.getTargetId())) {
            return BloomFilterApplicationStatus.SUCCESS;
        }
        return BloomFilterApplicationStatus.FALSE_POSITIVE;
    }

    private int filterRemovedDocuments(BloomFilter bloomFilter, int i) {
        String str = "projects/" + this.databaseId.getProjectId() + "/databases/" + this.databaseId.getDatabaseId() + "/documents/";
        Iterator<DocumentKey> it = this.targetMetadataProvider.getRemoteKeysForTarget(i).iterator();
        int i2 = 0;
        while (it.hasNext()) {
            DocumentKey next = it.next();
            if (!bloomFilter.mightContain(str + next.getPath().canonicalString())) {
                removeDocumentFromTarget(i, next, (MutableDocument) null);
                i2++;
            }
        }
        return i2;
    }

    public RemoteEvent createRemoteEvent(SnapshotVersion snapshotVersion) {
        boolean z;
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.targetStates.entrySet()) {
            int intValue = ((Integer) next.getKey()).intValue();
            TargetState targetState = (TargetState) next.getValue();
            TargetData queryDataForActiveTarget = queryDataForActiveTarget(intValue);
            if (queryDataForActiveTarget != null) {
                if (targetState.isCurrent() && queryDataForActiveTarget.getTarget().isDocumentQuery()) {
                    DocumentKey fromPath = DocumentKey.fromPath(queryDataForActiveTarget.getTarget().getPath());
                    if (this.pendingDocumentUpdates.get(fromPath) == null && !targetContainsDocument(intValue, fromPath)) {
                        removeDocumentFromTarget(intValue, fromPath, MutableDocument.newNoDocument(fromPath, snapshotVersion));
                    }
                }
                if (targetState.hasChanges()) {
                    hashMap.put(Integer.valueOf(intValue), targetState.toTargetChange());
                    targetState.clearChanges();
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (Map.Entry next2 : this.pendingDocumentTargetMapping.entrySet()) {
            DocumentKey documentKey = (DocumentKey) next2.getKey();
            Iterator it = ((Set) next2.getValue()).iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = true;
                    break;
                }
                TargetData queryDataForActiveTarget2 = queryDataForActiveTarget(((Integer) it.next()).intValue());
                if (queryDataForActiveTarget2 != null && !queryDataForActiveTarget2.getPurpose().equals(QueryPurpose.LIMBO_RESOLUTION)) {
                    z = false;
                    break;
                }
            }
            if (z) {
                hashSet.add(documentKey);
            }
        }
        for (MutableDocument readTime : this.pendingDocumentUpdates.values()) {
            readTime.setReadTime(snapshotVersion);
        }
        RemoteEvent remoteEvent = new RemoteEvent(snapshotVersion, Collections.unmodifiableMap(hashMap), Collections.unmodifiableMap(this.pendingTargetResets), Collections.unmodifiableMap(this.pendingDocumentUpdates), Collections.unmodifiableSet(hashSet));
        this.pendingDocumentUpdates = new HashMap();
        this.pendingDocumentTargetMapping = new HashMap();
        this.pendingTargetResets = new HashMap();
        return remoteEvent;
    }

    private void addDocumentToTarget(int i, MutableDocument mutableDocument) {
        DocumentViewChange.Type type;
        if (isActiveTarget(i)) {
            if (targetContainsDocument(i, mutableDocument.getKey())) {
                type = DocumentViewChange.Type.MODIFIED;
            } else {
                type = DocumentViewChange.Type.ADDED;
            }
            ensureTargetState(i).addDocumentChange(mutableDocument.getKey(), type);
            this.pendingDocumentUpdates.put(mutableDocument.getKey(), mutableDocument);
            ensureDocumentTargetMapping(mutableDocument.getKey()).add(Integer.valueOf(i));
        }
    }

    private void removeDocumentFromTarget(int i, DocumentKey documentKey, MutableDocument mutableDocument) {
        if (isActiveTarget(i)) {
            TargetState ensureTargetState = ensureTargetState(i);
            if (targetContainsDocument(i, documentKey)) {
                ensureTargetState.addDocumentChange(documentKey, DocumentViewChange.Type.REMOVED);
            } else {
                ensureTargetState.removeDocumentChange(documentKey);
            }
            ensureDocumentTargetMapping(documentKey).add(Integer.valueOf(i));
            if (mutableDocument != null) {
                this.pendingDocumentUpdates.put(documentKey, mutableDocument);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void removeTarget(int i) {
        this.targetStates.remove(Integer.valueOf(i));
    }

    private int getCurrentDocumentCountForTarget(int i) {
        TargetChange targetChange = ensureTargetState(i).toTargetChange();
        return (this.targetMetadataProvider.getRemoteKeysForTarget(i).size() + targetChange.getAddedDocuments().size()) - targetChange.getRemovedDocuments().size();
    }

    /* access modifiers changed from: package-private */
    public void recordPendingTargetRequest(int i) {
        ensureTargetState(i).recordPendingTargetRequest();
    }

    private TargetState ensureTargetState(int i) {
        TargetState targetState = this.targetStates.get(Integer.valueOf(i));
        if (targetState != null) {
            return targetState;
        }
        TargetState targetState2 = new TargetState();
        this.targetStates.put(Integer.valueOf(i), targetState2);
        return targetState2;
    }

    private Set<Integer> ensureDocumentTargetMapping(DocumentKey documentKey) {
        Set<Integer> set = this.pendingDocumentTargetMapping.get(documentKey);
        if (set != null) {
            return set;
        }
        HashSet hashSet = new HashSet();
        this.pendingDocumentTargetMapping.put(documentKey, hashSet);
        return hashSet;
    }

    private boolean isActiveTarget(int i) {
        return queryDataForActiveTarget(i) != null;
    }

    private TargetData queryDataForActiveTarget(int i) {
        TargetState targetState = this.targetStates.get(Integer.valueOf(i));
        if (targetState == null || !targetState.isPending()) {
            return this.targetMetadataProvider.getTargetDataForTarget(i);
        }
        return null;
    }

    private void resetTarget(int i) {
        Assert.hardAssert(this.targetStates.get(Integer.valueOf(i)) != null && !this.targetStates.get(Integer.valueOf(i)).isPending(), "Should only reset active targets", new Object[0]);
        this.targetStates.put(Integer.valueOf(i), new TargetState());
        Iterator<DocumentKey> it = this.targetMetadataProvider.getRemoteKeysForTarget(i).iterator();
        while (it.hasNext()) {
            removeDocumentFromTarget(i, it.next(), (MutableDocument) null);
        }
    }

    private boolean targetContainsDocument(int i, DocumentKey documentKey) {
        return this.targetMetadataProvider.getRemoteKeysForTarget(i).contains(documentKey);
    }
}
