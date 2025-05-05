package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;

final class AutoValue_FieldIndex_IndexOffset extends FieldIndex.IndexOffset {
    private final DocumentKey documentKey;
    private final int largestBatchId;
    private final SnapshotVersion readTime;

    AutoValue_FieldIndex_IndexOffset(SnapshotVersion snapshotVersion, DocumentKey documentKey2, int i) {
        if (snapshotVersion != null) {
            this.readTime = snapshotVersion;
            if (documentKey2 != null) {
                this.documentKey = documentKey2;
                this.largestBatchId = i;
                return;
            }
            throw new NullPointerException("Null documentKey");
        }
        throw new NullPointerException("Null readTime");
    }

    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    public DocumentKey getDocumentKey() {
        return this.documentKey;
    }

    public int getLargestBatchId() {
        return this.largestBatchId;
    }

    public String toString() {
        return "IndexOffset{readTime=" + this.readTime + ", documentKey=" + this.documentKey + ", largestBatchId=" + this.largestBatchId + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldIndex.IndexOffset)) {
            return false;
        }
        FieldIndex.IndexOffset indexOffset = (FieldIndex.IndexOffset) obj;
        if (!this.readTime.equals(indexOffset.getReadTime()) || !this.documentKey.equals(indexOffset.getDocumentKey()) || this.largestBatchId != indexOffset.getLargestBatchId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((this.readTime.hashCode() ^ 1000003) * 1000003) ^ this.documentKey.hashCode()) * 1000003) ^ this.largestBatchId;
    }
}
