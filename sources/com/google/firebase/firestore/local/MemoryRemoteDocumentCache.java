package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

final class MemoryRemoteDocumentCache implements RemoteDocumentCache {
    /* access modifiers changed from: private */
    public ImmutableSortedMap<DocumentKey, Document> docs = DocumentCollections.emptyDocumentMap();
    private IndexManager indexManager;

    MemoryRemoteDocumentCache() {
    }

    public void setIndexManager(IndexManager indexManager2) {
        this.indexManager = indexManager2;
    }

    public void add(MutableDocument mutableDocument, SnapshotVersion snapshotVersion) {
        Assert.hardAssert(this.indexManager != null, "setIndexManager() not called", new Object[0]);
        Assert.hardAssert(!snapshotVersion.equals(SnapshotVersion.NONE), "Cannot add document to the RemoteDocumentCache with a read time of zero", new Object[0]);
        this.docs = this.docs.insert(mutableDocument.getKey(), mutableDocument.mutableCopy().setReadTime(snapshotVersion));
        this.indexManager.addToCollectionParentIndex(mutableDocument.getKey().getCollectionPath());
    }

    public void removeAll(Collection<DocumentKey> collection) {
        Assert.hardAssert(this.indexManager != null, "setIndexManager() not called", new Object[0]);
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (DocumentKey next : collection) {
            this.docs = this.docs.remove(next);
            emptyDocumentMap = emptyDocumentMap.insert(next, MutableDocument.newNoDocument(next, SnapshotVersion.NONE));
        }
        this.indexManager.updateIndexEntries(emptyDocumentMap);
    }

    public MutableDocument get(DocumentKey documentKey) {
        Document document = this.docs.get(documentKey);
        return document != null ? document.mutableCopy() : MutableDocument.newInvalidDocument(documentKey);
    }

    public Map<DocumentKey, MutableDocument> getAll(Iterable<DocumentKey> iterable) {
        HashMap hashMap = new HashMap();
        for (DocumentKey next : iterable) {
            hashMap.put(next, get(next));
        }
        return hashMap;
    }

    public Map<DocumentKey, MutableDocument> getAll(String str, FieldIndex.IndexOffset indexOffset, int i) {
        throw new UnsupportedOperationException("getAll(String, IndexOffset, int) is not supported.");
    }

    public Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset, Set<DocumentKey> set, QueryContext queryContext) {
        HashMap hashMap = new HashMap();
        Iterator<Map.Entry<DocumentKey, Document>> iteratorFrom = this.docs.iteratorFrom(DocumentKey.fromPath((ResourcePath) query.getPath().append("")));
        while (iteratorFrom.hasNext()) {
            Map.Entry next = iteratorFrom.next();
            Document document = (Document) next.getValue();
            DocumentKey documentKey = (DocumentKey) next.getKey();
            if (!query.getPath().isPrefixOf(documentKey.getPath())) {
                break;
            } else if (documentKey.getPath().length() <= query.getPath().length() + 1 && FieldIndex.IndexOffset.fromDocument(document).compareTo(indexOffset) > 0) {
                if (set.contains(document.getKey()) || query.matches(document)) {
                    hashMap.put(document.getKey(), document.mutableCopy());
                }
            }
        }
        return hashMap;
    }

    public Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset, Set<DocumentKey> set) {
        return getDocumentsMatchingQuery(query, indexOffset, set, (QueryContext) null);
    }

    /* access modifiers changed from: package-private */
    public Iterable<Document> getDocuments() {
        return new DocumentIterable();
    }

    /* access modifiers changed from: package-private */
    public long getByteSize(LocalSerializer localSerializer) {
        Iterator<Document> it = new DocumentIterable().iterator();
        long j = 0;
        while (it.hasNext()) {
            j += (long) localSerializer.encodeMaybeDocument(it.next()).getSerializedSize();
        }
        return j;
    }

    private class DocumentIterable implements Iterable<Document> {
        private DocumentIterable() {
        }

        public Iterator<Document> iterator() {
            final Iterator it = MemoryRemoteDocumentCache.this.docs.iterator();
            return new Iterator<Document>() {
                public boolean hasNext() {
                    return it.hasNext();
                }

                public Document next() {
                    return (Document) ((Map.Entry) it.next()).getValue();
                }
            };
        }
    }
}
