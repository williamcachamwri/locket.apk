package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;

final class AutoValue_FieldIndex_Segment extends FieldIndex.Segment {
    private final FieldPath fieldPath;
    private final FieldIndex.Segment.Kind kind;

    AutoValue_FieldIndex_Segment(FieldPath fieldPath2, FieldIndex.Segment.Kind kind2) {
        if (fieldPath2 != null) {
            this.fieldPath = fieldPath2;
            if (kind2 != null) {
                this.kind = kind2;
                return;
            }
            throw new NullPointerException("Null kind");
        }
        throw new NullPointerException("Null fieldPath");
    }

    public FieldPath getFieldPath() {
        return this.fieldPath;
    }

    public FieldIndex.Segment.Kind getKind() {
        return this.kind;
    }

    public String toString() {
        return "Segment{fieldPath=" + this.fieldPath + ", kind=" + this.kind + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldIndex.Segment)) {
            return false;
        }
        FieldIndex.Segment segment = (FieldIndex.Segment) obj;
        if (!this.fieldPath.equals(segment.getFieldPath()) || !this.kind.equals(segment.getKind())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.fieldPath.hashCode() ^ 1000003) * 1000003) ^ this.kind.hashCode();
    }
}
