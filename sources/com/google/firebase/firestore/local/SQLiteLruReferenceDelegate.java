package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.util.SparseArray;
import com.google.firebase.firestore.core.ListenSequence;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import java.util.ArrayList;
import java.util.List;

class SQLiteLruReferenceDelegate implements ReferenceDelegate, LruDelegate {
    static final int REMOVE_ORPHANED_DOCUMENTS_BATCH_SIZE = 100;
    private long currentSequenceNumber = -1;
    private final LruGarbageCollector garbageCollector;
    private ReferenceSet inMemoryPins;
    private ListenSequence listenSequence;
    private final SQLitePersistence persistence;

    SQLiteLruReferenceDelegate(SQLitePersistence sQLitePersistence, LruGarbageCollector.Params params) {
        this.persistence = sQLitePersistence;
        this.garbageCollector = new LruGarbageCollector(this, params);
    }

    /* access modifiers changed from: package-private */
    public void start(long j) {
        this.listenSequence = new ListenSequence(j);
    }

    public void onTransactionStarted() {
        Assert.hardAssert(this.currentSequenceNumber == -1, "Starting a transaction without committing the previous one", new Object[0]);
        this.currentSequenceNumber = this.listenSequence.next();
    }

    public void onTransactionCommitted() {
        Assert.hardAssert(this.currentSequenceNumber != -1, "Committing a transaction without having started one", new Object[0]);
        this.currentSequenceNumber = -1;
    }

    public long getCurrentSequenceNumber() {
        Assert.hardAssert(this.currentSequenceNumber != -1, "Attempting to get a sequence number outside of a transaction", new Object[0]);
        return this.currentSequenceNumber;
    }

    public LruGarbageCollector getGarbageCollector() {
        return this.garbageCollector;
    }

    public long getSequenceNumberCount() {
        return this.persistence.getTargetCache().getTargetCount() + ((Long) this.persistence.query("SELECT COUNT(*) FROM (SELECT sequence_number FROM target_documents GROUP BY path HAVING COUNT(*) = 1 AND target_id = 0)").firstValue(new SQLiteLruReferenceDelegate$$ExternalSyntheticLambda0())).longValue();
    }

    public void forEachTarget(Consumer<TargetData> consumer) {
        this.persistence.getTargetCache().forEachTarget(consumer);
    }

    public void forEachOrphanedDocumentSequenceNumber(Consumer<Long> consumer) {
        this.persistence.query("select sequence_number from target_documents group by path having COUNT(*) = 1 AND target_id = 0").forEach(new SQLiteLruReferenceDelegate$$ExternalSyntheticLambda1(consumer));
    }

    public void setInMemoryPins(ReferenceSet referenceSet) {
        this.inMemoryPins = referenceSet;
    }

    public void addReference(DocumentKey documentKey) {
        writeSentinel(documentKey);
    }

    public void removeReference(DocumentKey documentKey) {
        writeSentinel(documentKey);
    }

    public int removeTargets(long j, SparseArray<?> sparseArray) {
        return this.persistence.getTargetCache().removeQueries(j, sparseArray);
    }

    public void removeMutationReference(DocumentKey documentKey) {
        writeSentinel(documentKey);
    }

    private boolean mutationQueuesContainKey(DocumentKey documentKey) {
        return !this.persistence.query("SELECT 1 FROM document_mutations WHERE path = ?").binding(EncodedPath.encode(documentKey.getPath())).isEmpty();
    }

    private boolean isPinned(DocumentKey documentKey) {
        if (this.inMemoryPins.containsKey(documentKey)) {
            return true;
        }
        return mutationQueuesContainKey(documentKey);
    }

    private void removeSentinel(DocumentKey documentKey) {
        this.persistence.execute("DELETE FROM target_documents WHERE path = ? AND target_id = 0", EncodedPath.encode(documentKey.getPath()));
    }

    public int removeOrphanedDocuments(long j) {
        int[] iArr = new int[1];
        ArrayList arrayList = new ArrayList();
        ResourcePath[] resourcePathArr = {ResourcePath.EMPTY};
        while (true) {
            boolean z = true;
            while (true) {
                if (!z) {
                    this.persistence.getRemoteDocumentCache().removeAll(arrayList);
                    return iArr[0];
                } else if (this.persistence.query("select path from target_documents group by path having COUNT(*) = 1 AND target_id = 0 AND sequence_number <= ? AND path > ? LIMIT ?").binding(Long.valueOf(j), EncodedPath.encode(resourcePathArr[0]), 100).forEach(new SQLiteLruReferenceDelegate$$ExternalSyntheticLambda2(this, iArr, arrayList, resourcePathArr)) != 100) {
                    z = false;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeOrphanedDocuments$2$com-google-firebase-firestore-local-SQLiteLruReferenceDelegate  reason: not valid java name */
    public /* synthetic */ void m709lambda$removeOrphanedDocuments$2$comgooglefirebasefirestorelocalSQLiteLruReferenceDelegate(int[] iArr, List list, ResourcePath[] resourcePathArr, Cursor cursor) {
        ResourcePath decodeResourcePath = EncodedPath.decodeResourcePath(cursor.getString(0));
        DocumentKey fromPath = DocumentKey.fromPath(decodeResourcePath);
        if (!isPinned(fromPath)) {
            iArr[0] = iArr[0] + 1;
            list.add(fromPath);
            removeSentinel(fromPath);
        }
        resourcePathArr[0] = decodeResourcePath;
    }

    public void removeTarget(TargetData targetData) {
        this.persistence.getTargetCache().updateTargetData(targetData.withSequenceNumber(getCurrentSequenceNumber()));
    }

    public void updateLimboDocument(DocumentKey documentKey) {
        writeSentinel(documentKey);
    }

    private void writeSentinel(DocumentKey documentKey) {
        this.persistence.execute("INSERT OR REPLACE INTO target_documents (target_id, path, sequence_number) VALUES (0, ?, ?)", EncodedPath.encode(documentKey.getPath()), Long.valueOf(getCurrentSequenceNumber()));
    }

    public long getByteSize() {
        return this.persistence.getByteSize();
    }
}
