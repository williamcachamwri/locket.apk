package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;
import java.util.List;

final class AutoValue_FieldIndex extends FieldIndex {
    private final String collectionGroup;
    private final int indexId;
    private final FieldIndex.IndexState indexState;
    private final List<FieldIndex.Segment> segments;

    AutoValue_FieldIndex(int i, String str, List<FieldIndex.Segment> list, FieldIndex.IndexState indexState2) {
        this.indexId = i;
        if (str != null) {
            this.collectionGroup = str;
            if (list != null) {
                this.segments = list;
                if (indexState2 != null) {
                    this.indexState = indexState2;
                    return;
                }
                throw new NullPointerException("Null indexState");
            }
            throw new NullPointerException("Null segments");
        }
        throw new NullPointerException("Null collectionGroup");
    }

    public int getIndexId() {
        return this.indexId;
    }

    public String getCollectionGroup() {
        return this.collectionGroup;
    }

    public List<FieldIndex.Segment> getSegments() {
        return this.segments;
    }

    public FieldIndex.IndexState getIndexState() {
        return this.indexState;
    }

    public String toString() {
        return "FieldIndex{indexId=" + this.indexId + ", collectionGroup=" + this.collectionGroup + ", segments=" + this.segments + ", indexState=" + this.indexState + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldIndex)) {
            return false;
        }
        FieldIndex fieldIndex = (FieldIndex) obj;
        if (this.indexId != fieldIndex.getIndexId() || !this.collectionGroup.equals(fieldIndex.getCollectionGroup()) || !this.segments.equals(fieldIndex.getSegments()) || !this.indexState.equals(fieldIndex.getIndexState())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((this.indexId ^ 1000003) * 1000003) ^ this.collectionGroup.hashCode()) * 1000003) ^ this.segments.hashCode()) * 1000003) ^ this.indexState.hashCode();
    }
}
