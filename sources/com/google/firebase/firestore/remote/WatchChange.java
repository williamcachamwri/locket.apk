package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public abstract class WatchChange {

    public enum WatchTargetChangeType {
        NoChange,
        Added,
        Removed,
        Current,
        Reset
    }

    private WatchChange() {
    }

    public static final class DocumentChange extends WatchChange {
        private final DocumentKey documentKey;
        private final MutableDocument newDocument;
        private final List<Integer> removedTargetIds;
        private final List<Integer> updatedTargetIds;

        public DocumentChange(List<Integer> list, List<Integer> list2, DocumentKey documentKey2, MutableDocument mutableDocument) {
            super();
            this.updatedTargetIds = list;
            this.removedTargetIds = list2;
            this.documentKey = documentKey2;
            this.newDocument = mutableDocument;
        }

        public List<Integer> getUpdatedTargetIds() {
            return this.updatedTargetIds;
        }

        public List<Integer> getRemovedTargetIds() {
            return this.removedTargetIds;
        }

        public MutableDocument getNewDocument() {
            return this.newDocument;
        }

        public DocumentKey getDocumentKey() {
            return this.documentKey;
        }

        public String toString() {
            return "DocumentChange{updatedTargetIds=" + this.updatedTargetIds + ", removedTargetIds=" + this.removedTargetIds + ", key=" + this.documentKey + ", newDocument=" + this.newDocument + AbstractJsonLexerKt.END_OBJ;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DocumentChange documentChange = (DocumentChange) obj;
            if (!this.updatedTargetIds.equals(documentChange.updatedTargetIds) || !this.removedTargetIds.equals(documentChange.removedTargetIds) || !this.documentKey.equals(documentChange.documentKey)) {
                return false;
            }
            MutableDocument mutableDocument = this.newDocument;
            MutableDocument mutableDocument2 = documentChange.newDocument;
            if (mutableDocument != null) {
                return mutableDocument.equals(mutableDocument2);
            }
            if (mutableDocument2 == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int hashCode = ((((this.updatedTargetIds.hashCode() * 31) + this.removedTargetIds.hashCode()) * 31) + this.documentKey.hashCode()) * 31;
            MutableDocument mutableDocument = this.newDocument;
            return hashCode + (mutableDocument != null ? mutableDocument.hashCode() : 0);
        }
    }

    public static final class ExistenceFilterWatchChange extends WatchChange {
        private final ExistenceFilter existenceFilter;
        private final int targetId;

        public ExistenceFilterWatchChange(int i, ExistenceFilter existenceFilter2) {
            super();
            this.targetId = i;
            this.existenceFilter = existenceFilter2;
        }

        public int getTargetId() {
            return this.targetId;
        }

        public ExistenceFilter getExistenceFilter() {
            return this.existenceFilter;
        }

        public String toString() {
            return "ExistenceFilterWatchChange{targetId=" + this.targetId + ", existenceFilter=" + this.existenceFilter + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public static final class WatchTargetChange extends WatchChange {
        private final Status cause;
        private final WatchTargetChangeType changeType;
        private final ByteString resumeToken;
        private final List<Integer> targetIds;

        public WatchTargetChange(WatchTargetChangeType watchTargetChangeType, List<Integer> list) {
            this(watchTargetChangeType, list, WatchStream.EMPTY_RESUME_TOKEN, (Status) null);
        }

        public WatchTargetChange(WatchTargetChangeType watchTargetChangeType, List<Integer> list, ByteString byteString) {
            this(watchTargetChangeType, list, byteString, (Status) null);
        }

        public WatchTargetChange(WatchTargetChangeType watchTargetChangeType, List<Integer> list, ByteString byteString, Status status) {
            super();
            Assert.hardAssert(status == null || watchTargetChangeType == WatchTargetChangeType.Removed, "Got cause for a target change that was not a removal", new Object[0]);
            this.changeType = watchTargetChangeType;
            this.targetIds = list;
            this.resumeToken = byteString;
            if (status == null || status.isOk()) {
                this.cause = null;
            } else {
                this.cause = status;
            }
        }

        public WatchTargetChangeType getChangeType() {
            return this.changeType;
        }

        public List<Integer> getTargetIds() {
            return this.targetIds;
        }

        public ByteString getResumeToken() {
            return this.resumeToken;
        }

        public Status getCause() {
            return this.cause;
        }

        public String toString() {
            return "WatchTargetChange{changeType=" + this.changeType + ", targetIds=" + this.targetIds + AbstractJsonLexerKt.END_OBJ;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            WatchTargetChange watchTargetChange = (WatchTargetChange) obj;
            if (this.changeType != watchTargetChange.changeType || !this.targetIds.equals(watchTargetChange.targetIds) || !this.resumeToken.equals(watchTargetChange.resumeToken)) {
                return false;
            }
            Status status = this.cause;
            if (status != null) {
                if (watchTargetChange.cause == null || !status.getCode().equals(watchTargetChange.cause.getCode())) {
                    return false;
                }
                return true;
            } else if (watchTargetChange.cause == null) {
                return true;
            } else {
                return false;
            }
        }

        public int hashCode() {
            int hashCode = ((((this.changeType.hashCode() * 31) + this.targetIds.hashCode()) * 31) + this.resumeToken.hashCode()) * 31;
            Status status = this.cause;
            return hashCode + (status != null ? status.getCode().hashCode() : 0);
        }
    }
}
