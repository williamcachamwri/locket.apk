package androidx.media3.extractor.metadata.scte35;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import java.nio.ByteBuffer;

public final class SpliceInfoDecoder extends SimpleMetadataDecoder {
    private static final int TYPE_PRIVATE_COMMAND = 255;
    private static final int TYPE_SPLICE_INSERT = 5;
    private static final int TYPE_SPLICE_NULL = 0;
    private static final int TYPE_SPLICE_SCHEDULE = 4;
    private static final int TYPE_TIME_SIGNAL = 6;
    private final ParsableByteArray sectionData = new ParsableByteArray();
    private final ParsableBitArray sectionHeader = new ParsableBitArray();
    private TimestampAdjuster timestampAdjuster;

    /* access modifiers changed from: protected */
    public Metadata decode(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer) {
        Metadata.Entry entry;
        if (this.timestampAdjuster == null || metadataInputBuffer.subsampleOffsetUs != this.timestampAdjuster.getTimestampOffsetUs()) {
            TimestampAdjuster timestampAdjuster2 = new TimestampAdjuster(metadataInputBuffer.timeUs);
            this.timestampAdjuster = timestampAdjuster2;
            timestampAdjuster2.adjustSampleTimestamp(metadataInputBuffer.timeUs - metadataInputBuffer.subsampleOffsetUs);
        }
        byte[] array = byteBuffer.array();
        int limit = byteBuffer.limit();
        this.sectionData.reset(array, limit);
        this.sectionHeader.reset(array, limit);
        this.sectionHeader.skipBits(39);
        long readBits = (((long) this.sectionHeader.readBits(1)) << 32) | ((long) this.sectionHeader.readBits(32));
        this.sectionHeader.skipBits(20);
        int readBits2 = this.sectionHeader.readBits(12);
        int readBits3 = this.sectionHeader.readBits(8);
        this.sectionData.skipBytes(14);
        if (readBits3 == 0) {
            entry = new SpliceNullCommand();
        } else if (readBits3 == 255) {
            entry = PrivateCommand.parseFromSection(this.sectionData, readBits2, readBits);
        } else if (readBits3 == 4) {
            entry = SpliceScheduleCommand.parseFromSection(this.sectionData);
        } else if (readBits3 != 5) {
            entry = readBits3 != 6 ? null : TimeSignalCommand.parseFromSection(this.sectionData, readBits, this.timestampAdjuster);
        } else {
            entry = SpliceInsertCommand.parseFromSection(this.sectionData, readBits, this.timestampAdjuster);
        }
        if (entry == null) {
            return new Metadata(new Metadata.Entry[0]);
        }
        return new Metadata(entry);
    }
}
