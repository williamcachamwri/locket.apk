package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.DocumentKey;
import java.util.Arrays;

final class AutoValue_IndexEntry extends IndexEntry {
    private final byte[] arrayValue;
    private final byte[] directionalValue;
    private final DocumentKey documentKey;
    private final int indexId;

    AutoValue_IndexEntry(int i, DocumentKey documentKey2, byte[] bArr, byte[] bArr2) {
        this.indexId = i;
        if (documentKey2 != null) {
            this.documentKey = documentKey2;
            if (bArr != null) {
                this.arrayValue = bArr;
                if (bArr2 != null) {
                    this.directionalValue = bArr2;
                    return;
                }
                throw new NullPointerException("Null directionalValue");
            }
            throw new NullPointerException("Null arrayValue");
        }
        throw new NullPointerException("Null documentKey");
    }

    public int getIndexId() {
        return this.indexId;
    }

    public DocumentKey getDocumentKey() {
        return this.documentKey;
    }

    public byte[] getArrayValue() {
        return this.arrayValue;
    }

    public byte[] getDirectionalValue() {
        return this.directionalValue;
    }

    public String toString() {
        return "IndexEntry{indexId=" + this.indexId + ", documentKey=" + this.documentKey + ", arrayValue=" + Arrays.toString(this.arrayValue) + ", directionalValue=" + Arrays.toString(this.directionalValue) + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IndexEntry)) {
            return false;
        }
        IndexEntry indexEntry = (IndexEntry) obj;
        if (this.indexId == indexEntry.getIndexId() && this.documentKey.equals(indexEntry.getDocumentKey())) {
            boolean z = indexEntry instanceof AutoValue_IndexEntry;
            if (Arrays.equals(this.arrayValue, z ? ((AutoValue_IndexEntry) indexEntry).arrayValue : indexEntry.getArrayValue())) {
                if (Arrays.equals(this.directionalValue, z ? ((AutoValue_IndexEntry) indexEntry).directionalValue : indexEntry.getDirectionalValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.indexId ^ 1000003) * 1000003) ^ this.documentKey.hashCode()) * 1000003) ^ Arrays.hashCode(this.arrayValue)) * 1000003) ^ Arrays.hashCode(this.directionalValue);
    }
}
