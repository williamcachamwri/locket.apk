package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Util;

public abstract class IndexEntry implements Comparable<IndexEntry> {
    public abstract byte[] getArrayValue();

    public abstract byte[] getDirectionalValue();

    public abstract DocumentKey getDocumentKey();

    public abstract int getIndexId();

    public static IndexEntry create(int i, DocumentKey documentKey, byte[] bArr, byte[] bArr2) {
        return new AutoValue_IndexEntry(i, documentKey, bArr, bArr2);
    }

    public int compareTo(IndexEntry indexEntry) {
        int compare = Integer.compare(getIndexId(), indexEntry.getIndexId());
        if (compare != 0) {
            return compare;
        }
        int compareTo = getDocumentKey().compareTo(indexEntry.getDocumentKey());
        if (compareTo != 0) {
            return compareTo;
        }
        int compareByteArrays = Util.compareByteArrays(getArrayValue(), indexEntry.getArrayValue());
        if (compareByteArrays != 0) {
            return compareByteArrays;
        }
        return Util.compareByteArrays(getDirectionalValue(), indexEntry.getDirectionalValue());
    }
}
