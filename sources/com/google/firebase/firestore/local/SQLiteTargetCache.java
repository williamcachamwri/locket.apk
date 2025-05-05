package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.util.SparseArray;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.proto.Target;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Iterator;

final class SQLiteTargetCache implements TargetCache {
    private final SQLitePersistence db;
    private int highestTargetId;
    private long lastListenSequenceNumber;
    private SnapshotVersion lastRemoteSnapshotVersion = SnapshotVersion.NONE;
    private final LocalSerializer localSerializer;
    private long targetCount;

    SQLiteTargetCache(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer2) {
        this.db = sQLitePersistence;
        this.localSerializer = localSerializer2;
    }

    /* access modifiers changed from: package-private */
    public void start() {
        boolean z = true;
        if (this.db.query("SELECT highest_target_id, highest_listen_sequence_number, last_remote_snapshot_version_seconds, last_remote_snapshot_version_nanos, target_count FROM target_globals LIMIT 1").first(new SQLiteTargetCache$$ExternalSyntheticLambda1(this)) != 1) {
            z = false;
        }
        Assert.hardAssert(z, "Missing target_globals entry", new Object[0]);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$start$0$com-google-firebase-firestore-local-SQLiteTargetCache  reason: not valid java name */
    public /* synthetic */ void m738lambda$start$0$comgooglefirebasefirestorelocalSQLiteTargetCache(Cursor cursor) {
        this.highestTargetId = cursor.getInt(0);
        this.lastListenSequenceNumber = (long) cursor.getInt(1);
        this.lastRemoteSnapshotVersion = new SnapshotVersion(new Timestamp(cursor.getLong(2), cursor.getInt(3)));
        this.targetCount = cursor.getLong(4);
    }

    public int getHighestTargetId() {
        return this.highestTargetId;
    }

    public long getHighestListenSequenceNumber() {
        return this.lastListenSequenceNumber;
    }

    public long getTargetCount() {
        return this.targetCount;
    }

    public void forEachTarget(Consumer<TargetData> consumer) {
        this.db.query("SELECT target_proto FROM targets").forEach(new SQLiteTargetCache$$ExternalSyntheticLambda3(this, consumer));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$forEachTarget$1$com-google-firebase-firestore-local-SQLiteTargetCache  reason: not valid java name */
    public /* synthetic */ void m735lambda$forEachTarget$1$comgooglefirebasefirestorelocalSQLiteTargetCache(Consumer consumer, Cursor cursor) {
        consumer.accept(decodeTargetData(cursor.getBlob(0)));
    }

    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.lastRemoteSnapshotVersion;
    }

    public void setLastRemoteSnapshotVersion(SnapshotVersion snapshotVersion) {
        this.lastRemoteSnapshotVersion = snapshotVersion;
        writeMetadata();
    }

    private void saveTargetData(TargetData targetData) {
        int targetId = targetData.getTargetId();
        String canonicalId = targetData.getTarget().getCanonicalId();
        Timestamp timestamp = targetData.getSnapshotVersion().getTimestamp();
        Target encodeTargetData = this.localSerializer.encodeTargetData(targetData);
        this.db.execute("INSERT OR REPLACE INTO targets (target_id, canonical_id, snapshot_version_seconds, snapshot_version_nanos, resume_token, last_listen_sequence_number, target_proto) VALUES (?, ?, ?, ?, ?, ?, ?)", Integer.valueOf(targetId), canonicalId, Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanoseconds()), targetData.getResumeToken().toByteArray(), Long.valueOf(targetData.getSequenceNumber()), encodeTargetData.toByteArray());
    }

    private boolean updateMetadata(TargetData targetData) {
        boolean z;
        if (targetData.getTargetId() > this.highestTargetId) {
            this.highestTargetId = targetData.getTargetId();
            z = true;
        } else {
            z = false;
        }
        if (targetData.getSequenceNumber() <= this.lastListenSequenceNumber) {
            return z;
        }
        this.lastListenSequenceNumber = targetData.getSequenceNumber();
        return true;
    }

    public void addTargetData(TargetData targetData) {
        saveTargetData(targetData);
        updateMetadata(targetData);
        this.targetCount++;
        writeMetadata();
    }

    public void updateTargetData(TargetData targetData) {
        saveTargetData(targetData);
        if (updateMetadata(targetData)) {
            writeMetadata();
        }
    }

    private void writeMetadata() {
        this.db.execute("UPDATE target_globals SET highest_target_id = ?, highest_listen_sequence_number = ?, last_remote_snapshot_version_seconds = ?, last_remote_snapshot_version_nanos = ?, target_count = ?", Integer.valueOf(this.highestTargetId), Long.valueOf(this.lastListenSequenceNumber), Long.valueOf(this.lastRemoteSnapshotVersion.getTimestamp().getSeconds()), Integer.valueOf(this.lastRemoteSnapshotVersion.getTimestamp().getNanoseconds()), Long.valueOf(this.targetCount));
    }

    private void removeTarget(int i) {
        removeMatchingKeysForTargetId(i);
        this.db.execute("DELETE FROM targets WHERE target_id = ?", Integer.valueOf(i));
        this.targetCount--;
    }

    public void removeTargetData(TargetData targetData) {
        removeTarget(targetData.getTargetId());
        writeMetadata();
    }

    /* access modifiers changed from: package-private */
    public int removeQueries(long j, SparseArray<?> sparseArray) {
        int[] iArr = new int[1];
        this.db.query("SELECT target_id FROM targets WHERE last_listen_sequence_number <= ?").binding(Long.valueOf(j)).forEach(new SQLiteTargetCache$$ExternalSyntheticLambda2(this, sparseArray, iArr));
        writeMetadata();
        return iArr[0];
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeQueries$2$com-google-firebase-firestore-local-SQLiteTargetCache  reason: not valid java name */
    public /* synthetic */ void m737lambda$removeQueries$2$comgooglefirebasefirestorelocalSQLiteTargetCache(SparseArray sparseArray, int[] iArr, Cursor cursor) {
        int i = cursor.getInt(0);
        if (sparseArray.get(i) == null) {
            removeTarget(i);
            iArr[0] = iArr[0] + 1;
        }
    }

    public TargetData getTargetData(com.google.firebase.firestore.core.Target target) {
        String canonicalId = target.getCanonicalId();
        TargetDataHolder targetDataHolder = new TargetDataHolder();
        this.db.query("SELECT target_proto FROM targets WHERE canonical_id = ?").binding(canonicalId).forEach(new SQLiteTargetCache$$ExternalSyntheticLambda0(this, target, targetDataHolder));
        return targetDataHolder.targetData;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getTargetData$3$com-google-firebase-firestore-local-SQLiteTargetCache  reason: not valid java name */
    public /* synthetic */ void m736lambda$getTargetData$3$comgooglefirebasefirestorelocalSQLiteTargetCache(com.google.firebase.firestore.core.Target target, TargetDataHolder targetDataHolder, Cursor cursor) {
        TargetData decodeTargetData = decodeTargetData(cursor.getBlob(0));
        if (target.equals(decodeTargetData.getTarget())) {
            targetDataHolder.targetData = decodeTargetData;
        }
    }

    private static class TargetDataHolder {
        TargetData targetData;

        private TargetDataHolder() {
        }
    }

    private TargetData decodeTargetData(byte[] bArr) {
        try {
            return this.localSerializer.decodeTargetData(Target.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("TargetData failed to parse: %s", e);
        }
    }

    public void addMatchingKeys(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i) {
        SQLiteStatement prepare = this.db.prepare("INSERT OR IGNORE INTO target_documents (target_id, path) VALUES (?, ?)");
        SQLiteLruReferenceDelegate referenceDelegate = this.db.getReferenceDelegate();
        Iterator<DocumentKey> it = immutableSortedSet.iterator();
        while (it.hasNext()) {
            DocumentKey next = it.next();
            this.db.execute(prepare, Integer.valueOf(i), EncodedPath.encode(next.getPath()));
            referenceDelegate.addReference(next);
        }
    }

    public void removeMatchingKeys(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i) {
        SQLiteStatement prepare = this.db.prepare("DELETE FROM target_documents WHERE target_id = ? AND path = ?");
        SQLiteLruReferenceDelegate referenceDelegate = this.db.getReferenceDelegate();
        Iterator<DocumentKey> it = immutableSortedSet.iterator();
        while (it.hasNext()) {
            DocumentKey next = it.next();
            this.db.execute(prepare, Integer.valueOf(i), EncodedPath.encode(next.getPath()));
            referenceDelegate.removeReference(next);
        }
    }

    public void removeMatchingKeysForTargetId(int i) {
        this.db.execute("DELETE FROM target_documents WHERE target_id = ?", Integer.valueOf(i));
    }

    public ImmutableSortedSet<DocumentKey> getMatchingKeysForTargetId(int i) {
        DocumentKeysHolder documentKeysHolder = new DocumentKeysHolder();
        this.db.query("SELECT path FROM target_documents WHERE target_id = ?").binding(Integer.valueOf(i)).forEach(new SQLiteTargetCache$$ExternalSyntheticLambda4(documentKeysHolder));
        return documentKeysHolder.keys;
    }

    private static class DocumentKeysHolder {
        /* access modifiers changed from: package-private */
        public ImmutableSortedSet<DocumentKey> keys;

        private DocumentKeysHolder() {
            this.keys = DocumentKey.emptyKeySet();
        }
    }

    public boolean containsKey(DocumentKey documentKey) {
        return !this.db.query("SELECT target_id FROM target_documents WHERE path = ? AND target_id != 0 LIMIT 1").binding(EncodedPath.encode(documentKey.getPath())).isEmpty();
    }
}
