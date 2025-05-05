package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.proto.MaybeDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class SQLiteRemoteDocumentCache implements RemoteDocumentCache {
    static final int BINDS_PER_STATEMENT = 9;
    private final SQLitePersistence db;
    private IndexManager indexManager;
    private final LocalSerializer serializer;

    SQLiteRemoteDocumentCache(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
    }

    public void setIndexManager(IndexManager indexManager2) {
        this.indexManager = indexManager2;
    }

    public void add(MutableDocument mutableDocument, SnapshotVersion snapshotVersion) {
        Assert.hardAssert(!snapshotVersion.equals(SnapshotVersion.NONE), "Cannot add document to the RemoteDocumentCache with a read time of zero", new Object[0]);
        DocumentKey key = mutableDocument.getKey();
        Timestamp timestamp = snapshotVersion.getTimestamp();
        this.db.execute("INSERT OR REPLACE INTO remote_documents (path, path_length, read_time_seconds, read_time_nanos, contents) VALUES (?, ?, ?, ?, ?)", EncodedPath.encode(key.getPath()), Integer.valueOf(key.getPath().length()), Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanoseconds()), this.serializer.encodeMaybeDocument(mutableDocument).toByteArray());
        this.indexManager.addToCollectionParentIndex(mutableDocument.getKey().getCollectionPath());
    }

    public void removeAll(Collection<DocumentKey> collection) {
        if (!collection.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
            for (DocumentKey next : collection) {
                arrayList.add(EncodedPath.encode(next.getPath()));
                emptyDocumentMap = emptyDocumentMap.insert(next, MutableDocument.newNoDocument(next, SnapshotVersion.NONE));
            }
            SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "DELETE FROM remote_documents WHERE path IN (", arrayList, ")");
            while (longQuery.hasMoreSubqueries()) {
                longQuery.executeNextSubquery();
            }
            this.indexManager.updateIndexEntries(emptyDocumentMap);
        }
    }

    public MutableDocument get(DocumentKey documentKey) {
        return getAll(Collections.singletonList(documentKey)).get(documentKey);
    }

    public Map<DocumentKey, MutableDocument> getAll(Iterable<DocumentKey> iterable) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (DocumentKey next : iterable) {
            arrayList.add(EncodedPath.encode(next.getPath()));
            hashMap.put(next, MutableDocument.newInvalidDocument(next));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "SELECT contents, read_time_seconds, read_time_nanos FROM remote_documents WHERE path IN (", arrayList, ") ORDER BY path");
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(new SQLiteRemoteDocumentCache$$ExternalSyntheticLambda2(this, backgroundQueue, hashMap));
        }
        backgroundQueue.drain();
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getAll$0$com-google-firebase-firestore-local-SQLiteRemoteDocumentCache  reason: not valid java name */
    public /* synthetic */ void m719lambda$getAll$0$comgooglefirebasefirestorelocalSQLiteRemoteDocumentCache(BackgroundQueue backgroundQueue, Map map, Cursor cursor) {
        processRowInBackground(backgroundQueue, map, cursor, (Function<MutableDocument, Boolean>) null);
    }

    public Map<DocumentKey, MutableDocument> getAll(String str, FieldIndex.IndexOffset indexOffset, int i) {
        List<ResourcePath> collectionParents = this.indexManager.getCollectionParents(str);
        ArrayList arrayList = new ArrayList(collectionParents.size());
        for (ResourcePath append : collectionParents) {
            arrayList.add((ResourcePath) append.append(str));
        }
        if (arrayList.isEmpty()) {
            return Collections.emptyMap();
        }
        if (arrayList.size() * 9 < 900) {
            return getAll(arrayList, indexOffset, i, (Function<MutableDocument, Boolean>) null);
        }
        HashMap hashMap = new HashMap();
        int i2 = 0;
        while (i2 < arrayList.size()) {
            int i3 = i2 + 100;
            hashMap.putAll(getAll(arrayList.subList(i2, Math.min(arrayList.size(), i3)), indexOffset, i, (Function<MutableDocument, Boolean>) null));
            i2 = i3;
        }
        return Util.firstNEntries(hashMap, i, FieldIndex.IndexOffset.DOCUMENT_COMPARATOR);
    }

    private Map<DocumentKey, MutableDocument> getAll(List<ResourcePath> list, FieldIndex.IndexOffset indexOffset, int i, @Nullable Function<MutableDocument, Boolean> function, @Nullable QueryContext queryContext) {
        Timestamp timestamp = indexOffset.getReadTime().getTimestamp();
        DocumentKey documentKey = indexOffset.getDocumentKey();
        StringBuilder repeatSequence = Util.repeatSequence("SELECT contents, read_time_seconds, read_time_nanos, path FROM remote_documents WHERE path >= ? AND path < ? AND path_length = ? AND (read_time_seconds > ? OR ( read_time_seconds = ? AND read_time_nanos > ?) OR ( read_time_seconds = ? AND read_time_nanos = ? and path > ?)) ", list.size(), " UNION ");
        repeatSequence.append("ORDER BY read_time_seconds, read_time_nanos, path LIMIT ?");
        Object[] objArr = new Object[((list.size() * 9) + 1)];
        int i2 = 0;
        for (ResourcePath next : list) {
            String encode = EncodedPath.encode(next);
            int i3 = i2 + 1;
            objArr[i2] = encode;
            int i4 = i3 + 1;
            objArr[i3] = EncodedPath.prefixSuccessor(encode);
            int i5 = i4 + 1;
            objArr[i4] = Integer.valueOf(next.length() + 1);
            int i6 = i5 + 1;
            objArr[i5] = Long.valueOf(timestamp.getSeconds());
            int i7 = i6 + 1;
            objArr[i6] = Long.valueOf(timestamp.getSeconds());
            int i8 = i7 + 1;
            objArr[i7] = Integer.valueOf(timestamp.getNanoseconds());
            int i9 = i8 + 1;
            objArr[i8] = Long.valueOf(timestamp.getSeconds());
            int i10 = i9 + 1;
            objArr[i9] = Integer.valueOf(timestamp.getNanoseconds());
            objArr[i10] = EncodedPath.encode(documentKey.getPath());
            i2 = i10 + 1;
        }
        objArr[i2] = Integer.valueOf(i);
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        HashMap hashMap = new HashMap();
        this.db.query(repeatSequence.toString()).binding(objArr).forEach(new SQLiteRemoteDocumentCache$$ExternalSyntheticLambda1(this, backgroundQueue, hashMap, function, queryContext));
        backgroundQueue.drain();
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getAll$1$com-google-firebase-firestore-local-SQLiteRemoteDocumentCache  reason: not valid java name */
    public /* synthetic */ void m720lambda$getAll$1$comgooglefirebasefirestorelocalSQLiteRemoteDocumentCache(BackgroundQueue backgroundQueue, Map map, Function function, QueryContext queryContext, Cursor cursor) {
        processRowInBackground(backgroundQueue, map, cursor, function);
        if (queryContext != null) {
            queryContext.incrementDocumentReadCount();
        }
    }

    private Map<DocumentKey, MutableDocument> getAll(List<ResourcePath> list, FieldIndex.IndexOffset indexOffset, int i, @Nullable Function<MutableDocument, Boolean> function) {
        return getAll(list, indexOffset, i, function, (QueryContext) null);
    }

    private void processRowInBackground(BackgroundQueue backgroundQueue, Map<DocumentKey, MutableDocument> map, Cursor cursor, @Nullable Function<MutableDocument, Boolean> function) {
        byte[] blob = cursor.getBlob(0);
        int i = cursor.getInt(1);
        int i2 = cursor.getInt(2);
        Executor executor = backgroundQueue;
        if (cursor.isLast()) {
            executor = Executors.DIRECT_EXECUTOR;
        }
        executor.execute(new SQLiteRemoteDocumentCache$$ExternalSyntheticLambda3(this, blob, i, i2, function, map));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$processRowInBackground$2$com-google-firebase-firestore-local-SQLiteRemoteDocumentCache  reason: not valid java name */
    public /* synthetic */ void m721lambda$processRowInBackground$2$comgooglefirebasefirestorelocalSQLiteRemoteDocumentCache(byte[] bArr, int i, int i2, Function function, Map map) {
        MutableDocument decodeMaybeDocument = decodeMaybeDocument(bArr, i, i2);
        if (function == null || ((Boolean) function.apply(decodeMaybeDocument)).booleanValue()) {
            synchronized (map) {
                map.put(decodeMaybeDocument.getKey(), decodeMaybeDocument);
            }
        }
    }

    public Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset, @Nonnull Set<DocumentKey> set) {
        return getDocumentsMatchingQuery(query, indexOffset, set, (QueryContext) null);
    }

    public Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset, @Nonnull Set<DocumentKey> set, @Nullable QueryContext queryContext) {
        return getAll(Collections.singletonList(query.getPath()), indexOffset, Integer.MAX_VALUE, new SQLiteRemoteDocumentCache$$ExternalSyntheticLambda0(query, set), queryContext);
    }

    static /* synthetic */ Boolean lambda$getDocumentsMatchingQuery$3(Query query, Set set, MutableDocument mutableDocument) {
        return Boolean.valueOf(query.matches(mutableDocument) || set.contains(mutableDocument.getKey()));
    }

    private MutableDocument decodeMaybeDocument(byte[] bArr, int i, int i2) {
        try {
            return this.serializer.decodeMaybeDocument(MaybeDocument.parseFrom(bArr)).setReadTime(new SnapshotVersion(new Timestamp((long) i, i2)));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("MaybeDocument failed to parse: %s", e);
        }
    }
}
