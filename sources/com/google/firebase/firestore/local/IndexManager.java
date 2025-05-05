package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.ResourcePath;
import java.util.Collection;
import java.util.List;

public interface IndexManager {

    public enum IndexType {
        NONE,
        PARTIAL,
        FULL
    }

    void addFieldIndex(FieldIndex fieldIndex);

    void addToCollectionParentIndex(ResourcePath resourcePath);

    void createTargetIndexes(Target target);

    void deleteAllFieldIndexes();

    void deleteFieldIndex(FieldIndex fieldIndex);

    List<ResourcePath> getCollectionParents(String str);

    List<DocumentKey> getDocumentsMatchingTarget(Target target);

    Collection<FieldIndex> getFieldIndexes();

    Collection<FieldIndex> getFieldIndexes(String str);

    IndexType getIndexType(Target target);

    FieldIndex.IndexOffset getMinOffset(Target target);

    FieldIndex.IndexOffset getMinOffset(String str);

    String getNextCollectionGroupToUpdate();

    void start();

    void updateCollectionGroup(String str, FieldIndex.IndexOffset indexOffset);

    void updateIndexEntries(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap);
}
