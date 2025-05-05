package com.google.firebase.firestore.local;

import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.remote.WatchStream;
import com.google.firebase.firestore.util.Preconditions;
import com.google.protobuf.ByteString;
import java.util.Objects;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class TargetData {
    private final Integer expectedCount;
    private final SnapshotVersion lastLimboFreeSnapshotVersion;
    private final QueryPurpose purpose;
    private final ByteString resumeToken;
    private final long sequenceNumber;
    private final SnapshotVersion snapshotVersion;
    private final Target target;
    private final int targetId;

    TargetData(Target target2, int i, long j, QueryPurpose queryPurpose, SnapshotVersion snapshotVersion2, SnapshotVersion snapshotVersion3, ByteString byteString, Integer num) {
        this.target = (Target) Preconditions.checkNotNull(target2);
        this.targetId = i;
        this.sequenceNumber = j;
        this.lastLimboFreeSnapshotVersion = snapshotVersion3;
        this.purpose = queryPurpose;
        this.snapshotVersion = (SnapshotVersion) Preconditions.checkNotNull(snapshotVersion2);
        this.resumeToken = (ByteString) Preconditions.checkNotNull(byteString);
        this.expectedCount = num;
    }

    public TargetData(Target target2, int i, long j, QueryPurpose queryPurpose) {
        this(target2, i, j, queryPurpose, SnapshotVersion.NONE, SnapshotVersion.NONE, WatchStream.EMPTY_RESUME_TOKEN, (Integer) null);
    }

    public TargetData withSequenceNumber(long j) {
        return new TargetData(this.target, this.targetId, j, this.purpose, this.snapshotVersion, this.lastLimboFreeSnapshotVersion, this.resumeToken, this.expectedCount);
    }

    public TargetData withResumeToken(ByteString byteString, SnapshotVersion snapshotVersion2) {
        return new TargetData(this.target, this.targetId, this.sequenceNumber, this.purpose, snapshotVersion2, this.lastLimboFreeSnapshotVersion, byteString, (Integer) null);
    }

    public TargetData withExpectedCount(Integer num) {
        return new TargetData(this.target, this.targetId, this.sequenceNumber, this.purpose, this.snapshotVersion, this.lastLimboFreeSnapshotVersion, this.resumeToken, num);
    }

    public TargetData withLastLimboFreeSnapshotVersion(SnapshotVersion snapshotVersion2) {
        return new TargetData(this.target, this.targetId, this.sequenceNumber, this.purpose, this.snapshotVersion, snapshotVersion2, this.resumeToken, this.expectedCount);
    }

    public Target getTarget() {
        return this.target;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public QueryPurpose getPurpose() {
        return this.purpose;
    }

    public SnapshotVersion getSnapshotVersion() {
        return this.snapshotVersion;
    }

    public ByteString getResumeToken() {
        return this.resumeToken;
    }

    public Integer getExpectedCount() {
        return this.expectedCount;
    }

    public SnapshotVersion getLastLimboFreeSnapshotVersion() {
        return this.lastLimboFreeSnapshotVersion;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TargetData targetData = (TargetData) obj;
        if (!this.target.equals(targetData.target) || this.targetId != targetData.targetId || this.sequenceNumber != targetData.sequenceNumber || !this.purpose.equals(targetData.purpose) || !this.snapshotVersion.equals(targetData.snapshotVersion) || !this.lastLimboFreeSnapshotVersion.equals(targetData.lastLimboFreeSnapshotVersion) || !this.resumeToken.equals(targetData.resumeToken) || !Objects.equals(this.expectedCount, targetData.expectedCount)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((((this.target.hashCode() * 31) + this.targetId) * 31) + ((int) this.sequenceNumber)) * 31) + this.purpose.hashCode()) * 31) + this.snapshotVersion.hashCode()) * 31) + this.lastLimboFreeSnapshotVersion.hashCode()) * 31) + this.resumeToken.hashCode()) * 31) + Objects.hashCode(this.expectedCount);
    }

    public String toString() {
        return "TargetData{target=" + this.target + ", targetId=" + this.targetId + ", sequenceNumber=" + this.sequenceNumber + ", purpose=" + this.purpose + ", snapshotVersion=" + this.snapshotVersion + ", lastLimboFreeSnapshotVersion=" + this.lastLimboFreeSnapshotVersion + ", resumeToken=" + this.resumeToken + ", expectedCount=" + this.expectedCount + AbstractJsonLexerKt.END_OBJ;
    }
}
