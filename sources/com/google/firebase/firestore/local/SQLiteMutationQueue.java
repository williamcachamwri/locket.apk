package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.proto.WriteBatch;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Preconditions;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class SQLiteMutationQueue implements MutationQueue {
    private static final int BLOB_MAX_INLINE_LENGTH = 1000000;
    private final SQLitePersistence db;
    private final IndexManager indexManager;
    private ByteString lastStreamToken;
    private int nextBatchId;
    private final LocalSerializer serializer;
    private final String uid;

    SQLiteMutationQueue(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer, User user, IndexManager indexManager2) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
        this.lastStreamToken = WriteStream.EMPTY_STREAM_TOKEN;
        this.indexManager = indexManager2;
    }

    public void start() {
        loadNextBatchIdAcrossAllUsers();
        if (this.db.query("SELECT last_stream_token FROM mutation_queues WHERE uid = ?").binding(this.uid).first(new SQLiteMutationQueue$$ExternalSyntheticLambda6(this)) == 0) {
            writeMutationQueueMetadata();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$start$0$com-google-firebase-firestore-local-SQLiteMutationQueue  reason: not valid java name */
    public /* synthetic */ void m717lambda$start$0$comgooglefirebasefirestorelocalSQLiteMutationQueue(Cursor cursor) {
        this.lastStreamToken = ByteString.copyFrom(cursor.getBlob(0));
    }

    private void loadNextBatchIdAcrossAllUsers() {
        ArrayList<String> arrayList = new ArrayList<>();
        this.db.query("SELECT uid FROM mutation_queues").forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda0(arrayList));
        this.nextBatchId = 0;
        for (String str : arrayList) {
            this.db.query("SELECT MAX(batch_id) FROM mutations WHERE uid = ?").binding(str).forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda3(this));
        }
        this.nextBatchId++;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$loadNextBatchIdAcrossAllUsers$2$com-google-firebase-firestore-local-SQLiteMutationQueue  reason: not valid java name */
    public /* synthetic */ void m715lambda$loadNextBatchIdAcrossAllUsers$2$comgooglefirebasefirestorelocalSQLiteMutationQueue(Cursor cursor) {
        this.nextBatchId = Math.max(this.nextBatchId, cursor.getInt(0));
    }

    public boolean isEmpty() {
        return this.db.query("SELECT batch_id FROM mutations WHERE uid = ? LIMIT 1").binding(this.uid).isEmpty();
    }

    public void acknowledgeBatch(MutationBatch mutationBatch, ByteString byteString) {
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(byteString);
        writeMutationQueueMetadata();
    }

    public ByteString getLastStreamToken() {
        return this.lastStreamToken;
    }

    public void setLastStreamToken(ByteString byteString) {
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(byteString);
        writeMutationQueueMetadata();
    }

    private void writeMutationQueueMetadata() {
        this.db.execute("INSERT OR REPLACE INTO mutation_queues (uid, last_acknowledged_batch_id, last_stream_token) VALUES (?, ?, ?)", this.uid, -1, this.lastStreamToken.toByteArray());
    }

    public MutationBatch addMutationBatch(Timestamp timestamp, List<Mutation> list, List<Mutation> list2) {
        int i = this.nextBatchId;
        this.nextBatchId = i + 1;
        MutationBatch mutationBatch = new MutationBatch(i, timestamp, list, list2);
        this.db.execute("INSERT INTO mutations (uid, batch_id, mutations) VALUES (?, ?, ?)", this.uid, Integer.valueOf(i), this.serializer.encodeMutationBatch(mutationBatch).toByteArray());
        HashSet hashSet = new HashSet();
        SQLiteStatement prepare = this.db.prepare("INSERT INTO document_mutations (uid, path, batch_id) VALUES (?, ?, ?)");
        for (Mutation key : list2) {
            DocumentKey key2 = key.getKey();
            if (hashSet.add(key2)) {
                this.db.execute(prepare, this.uid, EncodedPath.encode(key2.getPath()), Integer.valueOf(i));
                this.indexManager.addToCollectionParentIndex(key2.getCollectionPath());
            }
        }
        return mutationBatch;
    }

    public MutationBatch lookupMutationBatch(int i) {
        return (MutationBatch) this.db.query("SELECT SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? AND batch_id = ?").binding(1000000, this.uid, Integer.valueOf(i)).firstValue(new SQLiteMutationQueue$$ExternalSyntheticLambda8(this, i));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$lookupMutationBatch$3$com-google-firebase-firestore-local-SQLiteMutationQueue  reason: not valid java name */
    public /* synthetic */ MutationBatch m716lambda$lookupMutationBatch$3$comgooglefirebasefirestorelocalSQLiteMutationQueue(int i, Cursor cursor) {
        return decodeInlineMutationBatch(i, cursor.getBlob(0));
    }

    public MutationBatch getNextMutationBatchAfterBatchId(int i) {
        return (MutationBatch) this.db.query("SELECT batch_id, SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? AND batch_id >= ? ORDER BY batch_id ASC LIMIT 1").binding(1000000, this.uid, Integer.valueOf(i + 1)).firstValue(new SQLiteMutationQueue$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getNextMutationBatchAfterBatchId$4$com-google-firebase-firestore-local-SQLiteMutationQueue  reason: not valid java name */
    public /* synthetic */ MutationBatch m714lambda$getNextMutationBatchAfterBatchId$4$comgooglefirebasefirestorelocalSQLiteMutationQueue(Cursor cursor) {
        return decodeInlineMutationBatch(cursor.getInt(0), cursor.getBlob(1));
    }

    public int getHighestUnacknowledgedBatchId() {
        return ((Integer) this.db.query("SELECT IFNULL(MAX(batch_id), ?) FROM mutations WHERE uid = ?").binding(-1, this.uid).firstValue(new SQLiteMutationQueue$$ExternalSyntheticLambda4())).intValue();
    }

    public List<MutationBatch> getAllMutationBatches() {
        ArrayList arrayList = new ArrayList();
        this.db.query("SELECT batch_id, SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? ORDER BY batch_id ASC").binding(1000000, this.uid).forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda5(this, arrayList));
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getAllMutationBatches$6$com-google-firebase-firestore-local-SQLiteMutationQueue  reason: not valid java name */
    public /* synthetic */ void m710lambda$getAllMutationBatches$6$comgooglefirebasefirestorelocalSQLiteMutationQueue(List list, Cursor cursor) {
        list.add(decodeInlineMutationBatch(cursor.getInt(0), cursor.getBlob(1)));
    }

    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKey(DocumentKey documentKey) {
        String encode = EncodedPath.encode(documentKey.getPath());
        ArrayList arrayList = new ArrayList();
        this.db.query("SELECT m.batch_id, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path = ? AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id").binding(1000000, this.uid, encode).forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda9(this, arrayList));
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getAllMutationBatchesAffectingDocumentKey$7$com-google-firebase-firestore-local-SQLiteMutationQueue  reason: not valid java name */
    public /* synthetic */ void m711lambda$getAllMutationBatchesAffectingDocumentKey$7$comgooglefirebasefirestorelocalSQLiteMutationQueue(List list, Cursor cursor) {
        list.add(decodeInlineMutationBatch(cursor.getInt(0), cursor.getBlob(1)));
    }

    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKeys(Iterable<DocumentKey> iterable) {
        ArrayList arrayList = new ArrayList();
        for (DocumentKey path : iterable) {
            arrayList.add(EncodedPath.encode(path.getPath()));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "SELECT DISTINCT dm.batch_id, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path IN (", Arrays.asList(new Object[]{1000000, this.uid}), arrayList, ") AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id");
        ArrayList arrayList2 = new ArrayList();
        HashSet hashSet = new HashSet();
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda11(this, hashSet, arrayList2));
        }
        if (longQuery.getSubqueriesPerformed() > 1) {
            Collections.sort(arrayList2, new SQLiteMutationQueue$$ExternalSyntheticLambda1());
        }
        return arrayList2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getAllMutationBatchesAffectingDocumentKeys$8$com-google-firebase-firestore-local-SQLiteMutationQueue  reason: not valid java name */
    public /* synthetic */ void m712lambda$getAllMutationBatchesAffectingDocumentKeys$8$comgooglefirebasefirestorelocalSQLiteMutationQueue(Set set, List list, Cursor cursor) {
        int i = cursor.getInt(0);
        if (!set.contains(Integer.valueOf(i))) {
            set.add(Integer.valueOf(i));
            list.add(decodeInlineMutationBatch(i, cursor.getBlob(1)));
        }
    }

    public List<MutationBatch> getAllMutationBatchesAffectingQuery(Query query) {
        Assert.hardAssert(!query.isCollectionGroupQuery(), "CollectionGroup queries should be handled in LocalDocumentsView", new Object[0]);
        ResourcePath path = query.getPath();
        String encode = EncodedPath.encode(path);
        String prefixSuccessor = EncodedPath.prefixSuccessor(encode);
        ArrayList arrayList = new ArrayList();
        this.db.query("SELECT dm.batch_id, dm.path, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path >= ? AND dm.path < ? AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id").binding(1000000, this.uid, encode, prefixSuccessor).forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda7(this, arrayList, path.length() + 1));
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getAllMutationBatchesAffectingQuery$10$com-google-firebase-firestore-local-SQLiteMutationQueue  reason: not valid java name */
    public /* synthetic */ void m713lambda$getAllMutationBatchesAffectingQuery$10$comgooglefirebasefirestorelocalSQLiteMutationQueue(List list, int i, Cursor cursor) {
        int i2 = cursor.getInt(0);
        int size = list.size();
        if ((size <= 0 || i2 != ((MutationBatch) list.get(size - 1)).getBatchId()) && EncodedPath.decodeResourcePath(cursor.getString(1)).length() == i) {
            list.add(decodeInlineMutationBatch(i2, cursor.getBlob(2)));
        }
    }

    public void removeMutationBatch(MutationBatch mutationBatch) {
        SQLiteStatement prepare = this.db.prepare("DELETE FROM mutations WHERE uid = ? AND batch_id = ?");
        SQLiteStatement prepare2 = this.db.prepare("DELETE FROM document_mutations WHERE uid = ? AND path = ? AND batch_id = ?");
        int batchId = mutationBatch.getBatchId();
        Assert.hardAssert(this.db.execute(prepare, this.uid, Integer.valueOf(batchId)) != 0, "Mutation batch (%s, %d) did not exist", this.uid, Integer.valueOf(mutationBatch.getBatchId()));
        for (Mutation key : mutationBatch.getMutations()) {
            DocumentKey key2 = key.getKey();
            this.db.execute(prepare2, this.uid, EncodedPath.encode(key2.getPath()), Integer.valueOf(batchId));
            this.db.getReferenceDelegate().removeMutationReference(key2);
        }
    }

    public void performConsistencyCheck() {
        if (isEmpty()) {
            ArrayList arrayList = new ArrayList();
            this.db.query("SELECT path FROM document_mutations WHERE uid = ?").binding(this.uid).forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda10(arrayList));
            Assert.hardAssert(arrayList.isEmpty(), "Document leak -- detected dangling mutation references when queue is empty. Dangling keys: %s", arrayList);
        }
    }

    private MutationBatch decodeInlineMutationBatch(int i, byte[] bArr) {
        try {
            if (bArr.length < 1000000) {
                return this.serializer.decodeMutationBatch(WriteBatch.parseFrom(bArr));
            }
            BlobAccumulator blobAccumulator = new BlobAccumulator(bArr);
            while (blobAccumulator.more) {
                this.db.query("SELECT SUBSTR(mutations, ?, ?) FROM mutations WHERE uid = ? AND batch_id = ?").binding(Integer.valueOf((blobAccumulator.numChunks() * 1000000) + 1), 1000000, this.uid, Integer.valueOf(i)).first(blobAccumulator);
            }
            return this.serializer.decodeMutationBatch(WriteBatch.parseFrom(blobAccumulator.result()));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("MutationBatch failed to parse: %s", e);
        }
    }

    private static class BlobAccumulator implements Consumer<Cursor> {
        private final ArrayList<ByteString> chunks = new ArrayList<>();
        /* access modifiers changed from: private */
        public boolean more = true;

        BlobAccumulator(byte[] bArr) {
            addChunk(bArr);
        }

        /* access modifiers changed from: package-private */
        public int numChunks() {
            return this.chunks.size();
        }

        /* access modifiers changed from: package-private */
        public ByteString result() {
            return ByteString.copyFrom((Iterable<ByteString>) this.chunks);
        }

        public void accept(Cursor cursor) {
            byte[] blob = cursor.getBlob(0);
            addChunk(blob);
            if (blob.length < 1000000) {
                this.more = false;
            }
        }

        private void addChunk(byte[] bArr) {
            this.chunks.add(ByteString.copyFrom(bArr));
        }
    }
}
