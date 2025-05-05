package com.google.firebase.firestore.model.mutation;

final class AutoValue_Overlay extends Overlay {
    private final int largestBatchId;
    private final Mutation mutation;

    AutoValue_Overlay(int i, Mutation mutation2) {
        this.largestBatchId = i;
        if (mutation2 != null) {
            this.mutation = mutation2;
            return;
        }
        throw new NullPointerException("Null mutation");
    }

    public int getLargestBatchId() {
        return this.largestBatchId;
    }

    public Mutation getMutation() {
        return this.mutation;
    }

    public String toString() {
        return "Overlay{largestBatchId=" + this.largestBatchId + ", mutation=" + this.mutation + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Overlay)) {
            return false;
        }
        Overlay overlay = (Overlay) obj;
        if (this.largestBatchId != overlay.getLargestBatchId() || !this.mutation.equals(overlay.getMutation())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.largestBatchId ^ 1000003) * 1000003) ^ this.mutation.hashCode();
    }
}
