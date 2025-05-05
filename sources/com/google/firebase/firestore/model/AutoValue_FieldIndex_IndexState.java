package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;

final class AutoValue_FieldIndex_IndexState extends FieldIndex.IndexState {
    private final FieldIndex.IndexOffset offset;
    private final long sequenceNumber;

    AutoValue_FieldIndex_IndexState(long j, FieldIndex.IndexOffset indexOffset) {
        this.sequenceNumber = j;
        if (indexOffset != null) {
            this.offset = indexOffset;
            return;
        }
        throw new NullPointerException("Null offset");
    }

    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public FieldIndex.IndexOffset getOffset() {
        return this.offset;
    }

    public String toString() {
        return "IndexState{sequenceNumber=" + this.sequenceNumber + ", offset=" + this.offset + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldIndex.IndexState)) {
            return false;
        }
        FieldIndex.IndexState indexState = (FieldIndex.IndexState) obj;
        if (this.sequenceNumber != indexState.getSequenceNumber() || !this.offset.equals(indexState.getOffset())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.sequenceNumber;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.offset.hashCode();
    }
}
