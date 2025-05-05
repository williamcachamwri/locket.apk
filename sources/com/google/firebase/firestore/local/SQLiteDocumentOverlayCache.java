package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.v1.Write;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.Executor;

public class SQLiteDocumentOverlayCache implements DocumentOverlayCache {
    private final SQLitePersistence db;
    private final LocalSerializer serializer;
    private final String uid;

    public SQLiteDocumentOverlayCache(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer, User user) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
    }

    public Overlay getOverlay(DocumentKey documentKey) {
        return (Overlay) this.db.query("SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_path = ? AND document_id = ?").binding(this.uid, EncodedPath.encode((ResourcePath) documentKey.getPath().popLast()), documentKey.getPath().getLastSegment()).firstValue(new SQLiteDocumentOverlayCache$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getOverlay$0$com-google-firebase-firestore-local-SQLiteDocumentOverlayCache  reason: not valid java name */
    public /* synthetic */ Overlay m700lambda$getOverlay$0$comgooglefirebasefirestorelocalSQLiteDocumentOverlayCache(Cursor cursor) {
        return decodeOverlay(cursor.getBlob(0), cursor.getInt(1));
    }

    public Map<DocumentKey, Overlay> getOverlays(SortedSet<DocumentKey> sortedSet) {
        Assert.hardAssert(sortedSet.comparator() == null, "getOverlays() requires natural order", new Object[0]);
        HashMap hashMap = new HashMap();
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        ResourcePath resourcePath = ResourcePath.EMPTY;
        ArrayList arrayList = new ArrayList();
        for (DocumentKey documentKey : sortedSet) {
            if (!resourcePath.equals(documentKey.getCollectionPath())) {
                processSingleCollection(hashMap, backgroundQueue, resourcePath, arrayList);
                resourcePath = documentKey.getCollectionPath();
                arrayList.clear();
            }
            arrayList.add(documentKey.getDocumentId());
        }
        processSingleCollection(hashMap, backgroundQueue, resourcePath, arrayList);
        backgroundQueue.drain();
        return hashMap;
    }

    private void processSingleCollection(Map<DocumentKey, Overlay> map, BackgroundQueue backgroundQueue, ResourcePath resourcePath, List<Object> list) {
        if (!list.isEmpty()) {
            SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_path = ? AND document_id IN (", Arrays.asList(new Object[]{this.uid, EncodedPath.encode(resourcePath)}), list, ")");
            while (longQuery.hasMoreSubqueries()) {
                longQuery.performNextSubquery().forEach(new SQLiteDocumentOverlayCache$$ExternalSyntheticLambda1(this, backgroundQueue, map));
            }
        }
    }

    private void saveOverlay(int i, DocumentKey documentKey, Mutation mutation) {
        this.db.execute("INSERT OR REPLACE INTO document_overlays (uid, collection_group, collection_path, document_id, largest_batch_id, overlay_mutation) VALUES (?, ?, ?, ?, ?, ?)", this.uid, documentKey.getCollectionGroup(), EncodedPath.encode((ResourcePath) documentKey.getPath().popLast()), documentKey.getPath().getLastSegment(), Integer.valueOf(i), this.serializer.encodeMutation(mutation).toByteArray());
    }

    public void saveOverlays(int i, Map<DocumentKey, Mutation> map) {
        for (Map.Entry next : map.entrySet()) {
            DocumentKey documentKey = (DocumentKey) next.getKey();
            saveOverlay(i, documentKey, (Mutation) Preconditions.checkNotNull((Mutation) next.getValue(), "null value for key: %s", documentKey));
        }
    }

    public void removeOverlaysForBatchId(int i) {
        this.db.execute("DELETE FROM document_overlays WHERE uid = ? AND largest_batch_id = ?", this.uid, Integer.valueOf(i));
    }

    public Map<DocumentKey, Overlay> getOverlays(ResourcePath resourcePath, int i) {
        HashMap hashMap = new HashMap();
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        this.db.query("SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_path = ? AND largest_batch_id > ?").binding(this.uid, EncodedPath.encode(resourcePath), Integer.valueOf(i)).forEach(new SQLiteDocumentOverlayCache$$ExternalSyntheticLambda3(this, backgroundQueue, hashMap));
        backgroundQueue.drain();
        return hashMap;
    }

    public Map<DocumentKey, Overlay> getOverlays(String str, int i, int i2) {
        HashMap hashMap = new HashMap();
        String[] strArr = new String[1];
        String[] strArr2 = new String[1];
        int[] iArr = new int[1];
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        this.db.query("SELECT overlay_mutation, largest_batch_id, collection_path, document_id  FROM document_overlays WHERE uid = ? AND collection_group = ? AND largest_batch_id > ? ORDER BY largest_batch_id, collection_path, document_id LIMIT ?").binding(this.uid, str, Integer.valueOf(i), Integer.valueOf(i2)).forEach(new SQLiteDocumentOverlayCache$$ExternalSyntheticLambda4(this, iArr, strArr, strArr2, backgroundQueue, hashMap));
        if (strArr[0] == null) {
            return hashMap;
        }
        SQLitePersistence.Query query = this.db.query("SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_group = ? AND (collection_path > ? OR (collection_path = ? AND document_id > ?)) AND largest_batch_id = ?");
        String str2 = this.uid;
        String str3 = strArr[0];
        query.binding(str2, str, str3, str3, strArr2[0], Integer.valueOf(iArr[0])).forEach(new SQLiteDocumentOverlayCache$$ExternalSyntheticLambda5(this, backgroundQueue, hashMap));
        backgroundQueue.drain();
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getOverlays$3$com-google-firebase-firestore-local-SQLiteDocumentOverlayCache  reason: not valid java name */
    public /* synthetic */ void m702lambda$getOverlays$3$comgooglefirebasefirestorelocalSQLiteDocumentOverlayCache(int[] iArr, String[] strArr, String[] strArr2, BackgroundQueue backgroundQueue, Map map, Cursor cursor) {
        iArr[0] = cursor.getInt(1);
        strArr[0] = cursor.getString(2);
        strArr2[0] = cursor.getString(3);
        m705lambda$processSingleCollection$1$comgooglefirebasefirestorelocalSQLiteDocumentOverlayCache(backgroundQueue, map, cursor);
    }

    /* access modifiers changed from: private */
    /* renamed from: processOverlaysInBackground */
    public void m705lambda$processSingleCollection$1$comgooglefirebasefirestorelocalSQLiteDocumentOverlayCache(BackgroundQueue backgroundQueue, Map<DocumentKey, Overlay> map, Cursor cursor) {
        byte[] blob = cursor.getBlob(0);
        int i = cursor.getInt(1);
        Executor executor = backgroundQueue;
        if (cursor.isLast()) {
            executor = Executors.DIRECT_EXECUTOR;
        }
        executor.execute(new SQLiteDocumentOverlayCache$$ExternalSyntheticLambda2(this, blob, i, map));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$processOverlaysInBackground$5$com-google-firebase-firestore-local-SQLiteDocumentOverlayCache  reason: not valid java name */
    public /* synthetic */ void m704lambda$processOverlaysInBackground$5$comgooglefirebasefirestorelocalSQLiteDocumentOverlayCache(byte[] bArr, int i, Map map) {
        Overlay decodeOverlay = decodeOverlay(bArr, i);
        synchronized (map) {
            map.put(decodeOverlay.getKey(), decodeOverlay);
        }
    }

    private Overlay decodeOverlay(byte[] bArr, int i) {
        try {
            return Overlay.create(i, this.serializer.decodeMutation(Write.parseFrom(bArr)));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("Overlay failed to parse: %s", e);
        }
    }
}
