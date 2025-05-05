package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.FieldIndex;
import com.google.protobuf.ByteString;

public class IndexByteEncoder {
    private final AscendingIndexByteEncoder ascending = new AscendingIndexByteEncoder();
    private final DescendingIndexByteEncoder descending = new DescendingIndexByteEncoder();
    /* access modifiers changed from: private */
    public final OrderedCodeWriter orderedCode = new OrderedCodeWriter();

    class AscendingIndexByteEncoder extends DirectionalIndexByteEncoder {
        AscendingIndexByteEncoder() {
        }

        public void writeBytes(ByteString byteString) {
            IndexByteEncoder.this.orderedCode.writeBytesAscending(byteString);
        }

        public void writeString(String str) {
            IndexByteEncoder.this.orderedCode.writeUtf8Ascending(str);
        }

        public void writeLong(long j) {
            IndexByteEncoder.this.orderedCode.writeSignedLongAscending(j);
        }

        public void writeDouble(double d) {
            IndexByteEncoder.this.orderedCode.writeDoubleAscending(d);
        }

        public void writeInfinity() {
            IndexByteEncoder.this.orderedCode.writeInfinityAscending();
        }
    }

    class DescendingIndexByteEncoder extends DirectionalIndexByteEncoder {
        DescendingIndexByteEncoder() {
        }

        public void writeBytes(ByteString byteString) {
            IndexByteEncoder.this.orderedCode.writeBytesDescending(byteString);
        }

        public void writeString(String str) {
            IndexByteEncoder.this.orderedCode.writeUtf8Descending(str);
        }

        public void writeLong(long j) {
            IndexByteEncoder.this.orderedCode.writeSignedLongDescending(j);
        }

        public void writeDouble(double d) {
            IndexByteEncoder.this.orderedCode.writeDoubleDescending(d);
        }

        public void writeInfinity() {
            IndexByteEncoder.this.orderedCode.writeInfinityDescending();
        }
    }

    public void seed(byte[] bArr) {
        this.orderedCode.seed(bArr);
    }

    public DirectionalIndexByteEncoder forKind(FieldIndex.Segment.Kind kind) {
        if (kind.equals(FieldIndex.Segment.Kind.DESCENDING)) {
            return this.descending;
        }
        return this.ascending;
    }

    public byte[] getEncodedBytes() {
        return this.orderedCode.encodedBytes();
    }

    public void reset() {
        this.orderedCode.reset();
    }
}
