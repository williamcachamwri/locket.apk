package androidx.media3.extractor.ts;

import androidx.media3.common.C;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.PositionHolder;
import com.google.common.base.Ascii;
import java.io.IOException;

final class PsDurationReader {
    private static final String TAG = "PsDurationReader";
    private static final int TIMESTAMP_SEARCH_BYTES = 20000;
    private long durationUs = C.TIME_UNSET;
    private long firstScrValue = C.TIME_UNSET;
    private boolean isDurationRead;
    private boolean isFirstScrValueRead;
    private boolean isLastScrValueRead;
    private long lastScrValue = C.TIME_UNSET;
    private final ParsableByteArray packetBuffer = new ParsableByteArray();
    private final TimestampAdjuster scrTimestampAdjuster = new TimestampAdjuster(0);

    PsDurationReader() {
    }

    public boolean isDurationReadFinished() {
        return this.isDurationRead;
    }

    public TimestampAdjuster getScrTimestampAdjuster() {
        return this.scrTimestampAdjuster;
    }

    public int readDuration(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        if (!this.isLastScrValueRead) {
            return readLastScrValue(extractorInput, positionHolder);
        }
        if (this.lastScrValue == C.TIME_UNSET) {
            return finishReadDuration(extractorInput);
        }
        if (!this.isFirstScrValueRead) {
            return readFirstScrValue(extractorInput, positionHolder);
        }
        long j = this.firstScrValue;
        if (j == C.TIME_UNSET) {
            return finishReadDuration(extractorInput);
        }
        this.durationUs = this.scrTimestampAdjuster.adjustTsTimestampGreaterThanPreviousTimestamp(this.lastScrValue) - this.scrTimestampAdjuster.adjustTsTimestamp(j);
        return finishReadDuration(extractorInput);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public static long readScrValueFromPack(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        if (parsableByteArray.bytesLeft() < 9) {
            return C.TIME_UNSET;
        }
        byte[] bArr = new byte[9];
        parsableByteArray.readBytes(bArr, 0, 9);
        parsableByteArray.setPosition(position);
        if (!checkMarkerBits(bArr)) {
            return C.TIME_UNSET;
        }
        return readScrValueFromPackHeader(bArr);
    }

    private int finishReadDuration(ExtractorInput extractorInput) {
        this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        this.isDurationRead = true;
        extractorInput.resetPeekPosition();
        return 0;
    }

    private int readFirstScrValue(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        int min = (int) Math.min(20000, extractorInput.getLength());
        long j = (long) 0;
        if (extractorInput.getPosition() != j) {
            positionHolder.position = j;
            return 1;
        }
        this.packetBuffer.reset(min);
        extractorInput.resetPeekPosition();
        extractorInput.peekFully(this.packetBuffer.getData(), 0, min);
        this.firstScrValue = readFirstScrValueFromBuffer(this.packetBuffer);
        this.isFirstScrValueRead = true;
        return 0;
    }

    private long readFirstScrValueFromBuffer(ParsableByteArray parsableByteArray) {
        int limit = parsableByteArray.limit();
        for (int position = parsableByteArray.getPosition(); position < limit - 3; position++) {
            if (peekIntAtPosition(parsableByteArray.getData(), position) == 442) {
                parsableByteArray.setPosition(position + 4);
                long readScrValueFromPack = readScrValueFromPack(parsableByteArray);
                if (readScrValueFromPack != C.TIME_UNSET) {
                    return readScrValueFromPack;
                }
            }
        }
        return C.TIME_UNSET;
    }

    private int readLastScrValue(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        long length = extractorInput.getLength();
        int min = (int) Math.min(20000, length);
        long j = length - ((long) min);
        if (extractorInput.getPosition() != j) {
            positionHolder.position = j;
            return 1;
        }
        this.packetBuffer.reset(min);
        extractorInput.resetPeekPosition();
        extractorInput.peekFully(this.packetBuffer.getData(), 0, min);
        this.lastScrValue = readLastScrValueFromBuffer(this.packetBuffer);
        this.isLastScrValueRead = true;
        return 0;
    }

    private long readLastScrValueFromBuffer(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        for (int limit = parsableByteArray.limit() - 4; limit >= position; limit--) {
            if (peekIntAtPosition(parsableByteArray.getData(), limit) == 442) {
                parsableByteArray.setPosition(limit + 4);
                long readScrValueFromPack = readScrValueFromPack(parsableByteArray);
                if (readScrValueFromPack != C.TIME_UNSET) {
                    return readScrValueFromPack;
                }
            }
        }
        return C.TIME_UNSET;
    }

    private int peekIntAtPosition(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << Ascii.CAN) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    private static boolean checkMarkerBits(byte[] bArr) {
        if ((bArr[0] & 196) == 68 && (bArr[2] & 4) == 4 && (bArr[4] & 4) == 4 && (bArr[5] & 1) == 1 && (bArr[8] & 3) == 3) {
            return true;
        }
        return false;
    }

    private static long readScrValueFromPackHeader(byte[] bArr) {
        byte b = bArr[0];
        byte b2 = bArr[2];
        return (((((long) b) & 56) >> 3) << 30) | ((((long) b) & 3) << 28) | ((((long) bArr[1]) & 255) << 20) | (((((long) b2) & 248) >> 3) << 15) | ((((long) b2) & 3) << 13) | ((((long) bArr[3]) & 255) << 5) | ((((long) bArr[4]) & 248) >> 3);
    }
}
