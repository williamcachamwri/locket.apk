package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.util.Assert;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

class LocalDocumentsView {
    private final DocumentOverlayCache documentOverlayCache;
    private final IndexManager indexManager;
    private final MutationQueue mutationQueue;
    private final RemoteDocumentCache remoteDocumentCache;

    LocalDocumentsView(RemoteDocumentCache remoteDocumentCache2, MutationQueue mutationQueue2, DocumentOverlayCache documentOverlayCache2, IndexManager indexManager2) {
        this.remoteDocumentCache = remoteDocumentCache2;
        this.mutationQueue = mutationQueue2;
        this.documentOverlayCache = documentOverlayCache2;
        this.indexManager = indexManager2;
    }

    /* access modifiers changed from: package-private */
    public RemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    /* access modifiers changed from: package-private */
    public MutationQueue getMutationQueue() {
        return this.mutationQueue;
    }

    /* access modifiers changed from: package-private */
    public DocumentOverlayCache getDocumentOverlayCache() {
        return this.documentOverlayCache;
    }

    /* access modifiers changed from: package-private */
    public Document getDocument(DocumentKey documentKey) {
        Overlay overlay = this.documentOverlayCache.getOverlay(documentKey);
        MutableDocument baseDocument = getBaseDocument(documentKey, overlay);
        if (overlay != null) {
            overlay.getMutation().applyToLocalView(baseDocument, FieldMask.EMPTY, Timestamp.now());
        }
        return baseDocument;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, Document> getDocuments(Iterable<DocumentKey> iterable) {
        return getLocalViewOfDocuments(this.remoteDocumentCache.getAll(iterable), new HashSet());
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, Document> getLocalViewOfDocuments(Map<DocumentKey, MutableDocument> map, Set<DocumentKey> set) {
        HashMap hashMap = new HashMap();
        populateOverlays(hashMap, map.keySet());
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (Map.Entry next : computeViews(map, hashMap, set).entrySet()) {
            emptyDocumentMap = emptyDocumentMap.insert((DocumentKey) next.getKey(), ((OverlayedDocument) next.getValue()).getDocument());
        }
        return emptyDocumentMap;
    }

    /* access modifiers changed from: package-private */
    public Map<DocumentKey, OverlayedDocument> getOverlayedDocuments(Map<DocumentKey, MutableDocument> map) {
        HashMap hashMap = new HashMap();
        populateOverlays(hashMap, map.keySet());
        return computeViews(map, hashMap, new HashSet());
    }

    private Map<DocumentKey, OverlayedDocument> computeViews(Map<DocumentKey, MutableDocument> map, Map<DocumentKey, Overlay> map2, Set<DocumentKey> set) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        for (MutableDocument next : map.values()) {
            Overlay overlay = map2.get(next.getKey());
            if (set.contains(next.getKey()) && (overlay == null || (overlay.getMutation() instanceof PatchMutation))) {
                hashMap.put(next.getKey(), next);
            } else if (overlay != null) {
                hashMap2.put(next.getKey(), overlay.getMutation().getFieldMask());
                overlay.getMutation().applyToLocalView(next, overlay.getMutation().getFieldMask(), Timestamp.now());
            } else {
                hashMap2.put(next.getKey(), FieldMask.EMPTY);
            }
        }
        hashMap2.putAll(recalculateAndSaveOverlays((Map<DocumentKey, MutableDocument>) hashMap));
        HashMap hashMap3 = new HashMap();
        for (Map.Entry next2 : map.entrySet()) {
            hashMap3.put((DocumentKey) next2.getKey(), new OverlayedDocument((Document) next2.getValue(), (FieldMask) hashMap2.get(next2.getKey())));
        }
        return hashMap3;
    }

    private Map<DocumentKey, FieldMask> recalculateAndSaveOverlays(Map<DocumentKey, MutableDocument> map) {
        List<MutationBatch> allMutationBatchesAffectingDocumentKeys = this.mutationQueue.getAllMutationBatchesAffectingDocumentKeys(map.keySet());
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        for (MutationBatch next : allMutationBatchesAffectingDocumentKeys) {
            for (DocumentKey next2 : next.getKeys()) {
                MutableDocument mutableDocument = map.get(next2);
                if (mutableDocument != null) {
                    hashMap.put(next2, next.applyToLocalView(mutableDocument, hashMap.containsKey(next2) ? (FieldMask) hashMap.get(next2) : FieldMask.EMPTY));
                    int batchId = next.getBatchId();
                    if (!treeMap.containsKey(Integer.valueOf(batchId))) {
                        treeMap.put(Integer.valueOf(batchId), new HashSet());
                    }
                    ((Set) treeMap.get(Integer.valueOf(batchId))).add(next2);
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (Map.Entry entry : treeMap.descendingMap().entrySet()) {
            HashMap hashMap2 = new HashMap();
            for (DocumentKey documentKey : (Set) entry.getValue()) {
                if (!hashSet.contains(documentKey)) {
                    Mutation calculateOverlayMutation = Mutation.calculateOverlayMutation(map.get(documentKey), (FieldMask) hashMap.get(documentKey));
                    if (calculateOverlayMutation != null) {
                        hashMap2.put(documentKey, calculateOverlayMutation);
                    }
                    hashSet.add(documentKey);
                }
            }
            this.documentOverlayCache.saveOverlays(((Integer) entry.getKey()).intValue(), hashMap2);
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public void recalculateAndSaveOverlays(Set<DocumentKey> set) {
        recalculateAndSaveOverlays(this.remoteDocumentCache.getAll(set));
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset, QueryContext queryContext) {
        ResourcePath path = query.getPath();
        if (query.isDocumentQuery()) {
            return getDocumentsMatchingDocumentQuery(path);
        }
        if (query.isCollectionGroupQuery()) {
            return getDocumentsMatchingCollectionGroupQuery(query, indexOffset, queryContext);
        }
        return getDocumentsMatchingCollectionQuery(query, indexOffset, queryContext);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset) {
        return getDocumentsMatchingQuery(query, indexOffset, (QueryContext) null);
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingDocumentQuery(ResourcePath resourcePath) {
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        Document document = getDocument(DocumentKey.fromPath(resourcePath));
        return document.isFoundDocument() ? emptyDocumentMap.insert(document.getKey(), document) : emptyDocumentMap;
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionGroupQuery(Query query, FieldIndex.IndexOffset indexOffset, QueryContext queryContext) {
        Assert.hardAssert(query.getPath().isEmpty(), "Currently we only support collection group queries at the root.", new Object[0]);
        String collectionGroup = query.getCollectionGroup();
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (ResourcePath append : this.indexManager.getCollectionParents(collectionGroup)) {
            Iterator<Map.Entry<DocumentKey, Document>> it = getDocumentsMatchingCollectionQuery(query.asCollectionQueryAtPath((ResourcePath) append.append(collectionGroup)), indexOffset, queryContext).iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                emptyDocumentMap = emptyDocumentMap.insert((DocumentKey) next.getKey(), (Document) next.getValue());
            }
        }
        return emptyDocumentMap;
    }

    /* access modifiers changed from: package-private */
    public LocalDocumentsResult getNextDocuments(String str, FieldIndex.IndexOffset indexOffset, int i) {
        Map map;
        Map<DocumentKey, MutableDocument> all = this.remoteDocumentCache.getAll(str, indexOffset, i);
        if (i - all.size() > 0) {
            map = this.documentOverlayCache.getOverlays(str, indexOffset.getLargestBatchId(), i - all.size());
        } else {
            map = new HashMap();
        }
        int i2 = -1;
        for (Overlay overlay : map.values()) {
            if (!all.containsKey(overlay.getKey())) {
                all.put(overlay.getKey(), getBaseDocument(overlay.getKey(), overlay));
            }
            i2 = Math.max(i2, overlay.getLargestBatchId());
        }
        populateOverlays(map, all.keySet());
        return LocalDocumentsResult.fromOverlayedDocuments(i2, computeViews(all, map, Collections.emptySet()));
    }

    private void populateOverlays(Map<DocumentKey, Overlay> map, Set<DocumentKey> set) {
        TreeSet treeSet = new TreeSet();
        for (DocumentKey next : set) {
            if (!map.containsKey(next)) {
                treeSet.add(next);
            }
        }
        map.putAll(this.documentOverlayCache.getOverlays(treeSet));
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionQuery(Query query, FieldIndex.IndexOffset indexOffset, QueryContext queryContext) {
        Map<DocumentKey, Overlay> overlays = this.documentOverlayCache.getOverlays(query.getPath(), indexOffset.getLargestBatchId());
        Map<DocumentKey, MutableDocument> documentsMatchingQuery = this.remoteDocumentCache.getDocumentsMatchingQuery(query, indexOffset, overlays.keySet(), queryContext);
        for (Map.Entry next : overlays.entrySet()) {
            if (!documentsMatchingQuery.containsKey(next.getKey())) {
                documentsMatchingQuery.put((DocumentKey) next.getKey(), MutableDocument.newInvalidDocument((DocumentKey) next.getKey()));
            }
        }
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (Map.Entry next2 : documentsMatchingQuery.entrySet()) {
            Overlay overlay = overlays.get(next2.getKey());
            if (overlay != null) {
                overlay.getMutation().applyToLocalView((MutableDocument) next2.getValue(), FieldMask.EMPTY, Timestamp.now());
            }
            if (query.matches((Document) next2.getValue())) {
                emptyDocumentMap = emptyDocumentMap.insert((DocumentKey) next2.getKey(), (Document) next2.getValue());
            }
        }
        return emptyDocumentMap;
    }

    private MutableDocument getBaseDocument(DocumentKey documentKey, Overlay overlay) {
        if (overlay == null || (overlay.getMutation() instanceof PatchMutation)) {
            return this.remoteDocumentCache.get(documentKey);
        }
        return MutableDocument.newInvalidDocument(documentKey);
    }
}
