package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.extractor.OpusUtil;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

public final class OggOpusAudioPacketizer {
    private static final int CHECKSUM_INDEX = 22;
    private static final int FIRST_AUDIO_SAMPLE_PAGE_SEQUENCE_NUMBER = 2;
    private static final byte[] OGG_DEFAULT_COMMENT_HEADER_PAGE = {79, 103, 103, 83, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 11, -103, 87, 83, 1, 16, 79, 112, 117, 115, 84, 97, 103, 115, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final byte[] OGG_DEFAULT_ID_HEADER_PAGE = {79, 103, 103, 83, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Ascii.FS, -43, -59, -9, 1, 19, 79, 112, 117, 115, 72, 101, 97, 100, 1, 2, 56, 1, Byte.MIN_VALUE, -69, 0, 0, 0, 0, 0};
    private static final int OGG_PACKET_HEADER_LENGTH = 28;
    private static final int SERIAL_NUMBER = 0;
    private int granulePosition = 0;
    private ByteBuffer outputBuffer = AudioProcessor.EMPTY_BUFFER;
    private int pageSequenceNumber = 2;

    public void packetize(DecoderInputBuffer decoderInputBuffer, List<byte[]> list) {
        Assertions.checkNotNull(decoderInputBuffer.data);
        if (decoderInputBuffer.data.limit() - decoderInputBuffer.data.position() != 0) {
            this.outputBuffer = packetizeInternal(decoderInputBuffer.data, (this.pageSequenceNumber == 2 && (list.size() == 1 || list.size() == 3)) ? list.get(0) : null);
            decoderInputBuffer.clear();
            decoderInputBuffer.ensureSpaceForWrite(this.outputBuffer.remaining());
            decoderInputBuffer.data.put(this.outputBuffer);
            decoderInputBuffer.flip();
        }
    }

    public void reset() {
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.granulePosition = 0;
        this.pageSequenceNumber = 2;
    }

    private ByteBuffer packetizeInternal(ByteBuffer byteBuffer, byte[] bArr) {
        int i;
        int i2;
        ByteBuffer byteBuffer2 = byteBuffer;
        byte[] bArr2 = bArr;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i3 = limit - position;
        int i4 = (i3 + 255) / 255;
        int i5 = i4 + 27 + i3;
        if (this.pageSequenceNumber == 2) {
            if (bArr2 != null) {
                i2 = bArr2.length + 28;
            } else {
                i2 = OGG_DEFAULT_ID_HEADER_PAGE.length;
            }
            i5 += OGG_DEFAULT_COMMENT_HEADER_PAGE.length + i2;
            i = i2;
        } else {
            i = 0;
        }
        ByteBuffer replaceOutputBuffer = replaceOutputBuffer(i5);
        if (this.pageSequenceNumber == 2) {
            if (bArr2 != null) {
                writeOggIdHeaderPage(replaceOutputBuffer, bArr2);
            } else {
                replaceOutputBuffer.put(OGG_DEFAULT_ID_HEADER_PAGE);
            }
            replaceOutputBuffer.put(OGG_DEFAULT_COMMENT_HEADER_PAGE);
        }
        int parsePacketAudioSampleCount = this.granulePosition + OpusUtil.parsePacketAudioSampleCount(byteBuffer);
        this.granulePosition = parsePacketAudioSampleCount;
        ByteBuffer byteBuffer3 = replaceOutputBuffer;
        writeOggPacketHeader(replaceOutputBuffer, (long) parsePacketAudioSampleCount, this.pageSequenceNumber, i4, false);
        for (int i6 = 0; i6 < i4; i6++) {
            if (i3 >= 255) {
                byteBuffer3.put((byte) -1);
                i3 -= 255;
            } else {
                byteBuffer3.put((byte) i3);
                i3 = 0;
            }
        }
        while (position < limit) {
            byteBuffer3.put(byteBuffer2.get(position));
            position++;
        }
        byteBuffer2.position(byteBuffer.limit());
        byteBuffer3.flip();
        if (this.pageSequenceNumber == 2) {
            byte[] array = byteBuffer3.array();
            byte[] bArr3 = OGG_DEFAULT_COMMENT_HEADER_PAGE;
            byteBuffer3.putInt(i + bArr3.length + 22, Util.crc32(array, byteBuffer3.arrayOffset() + i + bArr3.length, byteBuffer3.limit() - byteBuffer3.position(), 0));
        } else {
            byteBuffer3.putInt(22, Util.crc32(byteBuffer3.array(), byteBuffer3.arrayOffset(), byteBuffer3.limit() - byteBuffer3.position(), 0));
        }
        this.pageSequenceNumber++;
        return byteBuffer3;
    }

    private void writeOggIdHeaderPage(ByteBuffer byteBuffer, byte[] bArr) {
        writeOggPacketHeader(byteBuffer, 0, 0, 1, true);
        byteBuffer.put(UnsignedBytes.checkedCast((long) bArr.length));
        byteBuffer.put(bArr);
        byteBuffer.putInt(22, Util.crc32(byteBuffer.array(), byteBuffer.arrayOffset(), bArr.length + 28, 0));
        byteBuffer.position(bArr.length + 28);
    }

    private void writeOggPacketHeader(ByteBuffer byteBuffer, long j, int i, int i2, boolean z) {
        byteBuffer.put((byte) 79);
        byteBuffer.put((byte) 103);
        byteBuffer.put((byte) 103);
        byteBuffer.put((byte) 83);
        byteBuffer.put((byte) 0);
        byteBuffer.put(z ? (byte) 2 : 0);
        byteBuffer.putLong(j);
        byteBuffer.putInt(0);
        byteBuffer.putInt(i);
        byteBuffer.putInt(0);
        byteBuffer.put(UnsignedBytes.checkedCast((long) i2));
    }

    private ByteBuffer replaceOutputBuffer(int i) {
        if (this.outputBuffer.capacity() < i) {
            this.outputBuffer = ByteBuffer.allocate(i).order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.outputBuffer.clear();
        }
        return this.outputBuffer;
    }
}
