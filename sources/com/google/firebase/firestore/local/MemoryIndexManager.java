package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.IndexManager;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class MemoryIndexManager implements IndexManager {
    private final MemoryCollectionParentIndex collectionParentsIndex = new MemoryCollectionParentIndex();

    public void addFieldIndex(FieldIndex fieldIndex) {
    }

    public void createTargetIndexes(Target target) {
    }

    public void deleteAllFieldIndexes() {
    }

    public void deleteFieldIndex(FieldIndex fieldIndex) {
    }

    public List<DocumentKey> getDocumentsMatchingTarget(Target target) {
        return null;
    }

    public String getNextCollectionGroupToUpdate() {
        return null;
    }

    public void start() {
    }

    public void updateCollectionGroup(String str, FieldIndex.IndexOffset indexOffset) {
    }

    public void updateIndexEntries(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
    }

    public void addToCollectionParentIndex(ResourcePath resourcePath) {
        this.collectionParentsIndex.add(resourcePath);
    }

    public List<ResourcePath> getCollectionParents(String str) {
        return this.collectionParentsIndex.getEntries(str);
    }

    public Collection<FieldIndex> getFieldIndexes(String str) {
        return Collections.emptyList();
    }

    public Collection<FieldIndex> getFieldIndexes() {
        return Collections.emptyList();
    }

    public FieldIndex.IndexOffset getMinOffset(Target target) {
        return FieldIndex.IndexOffset.NONE;
    }

    public FieldIndex.IndexOffset getMinOffset(String str) {
        return FieldIndex.IndexOffset.NONE;
    }

    public IndexManager.IndexType getIndexType(Target target) {
        return IndexManager.IndexType.NONE;
    }

    static class MemoryCollectionParentIndex {
        private final HashMap<String, HashSet<ResourcePath>> index = new HashMap<>();

        MemoryCollectionParentIndex() {
        }

        /* access modifiers changed from: package-private */
        public boolean add(ResourcePath resourcePath) {
            boolean z = true;
            if (resourcePath.length() % 2 != 1) {
                z = false;
            }
            Assert.hardAssert(z, "Expected a collection path.", new Object[0]);
            String lastSegment = resourcePath.getLastSegment();
            ResourcePath resourcePath2 = (ResourcePath) resourcePath.popLast();
            HashSet hashSet = this.index.get(lastSegment);
            if (hashSet == null) {
                hashSet = new HashSet();
                this.index.put(lastSegment, hashSet);
            }
            return hashSet.add(resourcePath2);
        }

        /* access modifiers changed from: package-private */
        public List<ResourcePath> getEntries(String str) {
            HashSet hashSet = this.index.get(str);
            return hashSet != null ? new ArrayList(hashSet) : Collections.emptyList();
        }
    }
}
