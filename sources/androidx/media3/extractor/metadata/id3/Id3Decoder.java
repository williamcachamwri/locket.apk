package androidx.media3.extractor.metadata.id3;

import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import androidx.media3.muxer.MuxerUtil;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class Id3Decoder extends SimpleMetadataDecoder {
    private static final int FRAME_FLAG_V3_HAS_GROUP_IDENTIFIER = 32;
    private static final int FRAME_FLAG_V3_IS_COMPRESSED = 128;
    private static final int FRAME_FLAG_V3_IS_ENCRYPTED = 64;
    private static final int FRAME_FLAG_V4_HAS_DATA_LENGTH = 1;
    private static final int FRAME_FLAG_V4_HAS_GROUP_IDENTIFIER = 64;
    private static final int FRAME_FLAG_V4_IS_COMPRESSED = 8;
    private static final int FRAME_FLAG_V4_IS_ENCRYPTED = 4;
    private static final int FRAME_FLAG_V4_IS_UNSYNCHRONIZED = 2;
    public static final int ID3_HEADER_LENGTH = 10;
    public static final int ID3_TAG = 4801587;
    private static final int ID3_TEXT_ENCODING_ISO_8859_1 = 0;
    private static final int ID3_TEXT_ENCODING_UTF_16 = 1;
    private static final int ID3_TEXT_ENCODING_UTF_16BE = 2;
    private static final int ID3_TEXT_ENCODING_UTF_8 = 3;
    public static final FramePredicate NO_FRAMES_PREDICATE = new Id3Decoder$$ExternalSyntheticLambda0();
    private static final String TAG = "Id3Decoder";
    private final FramePredicate framePredicate;

    public interface FramePredicate {
        boolean evaluate(int i, int i2, int i3, int i4, int i5);
    }

    private static int delimiterLength(int i) {
        return (i == 0 || i == 3) ? 1 : 2;
    }

    static /* synthetic */ boolean lambda$static$0(int i, int i2, int i3, int i4, int i5) {
        return false;
    }

    public Id3Decoder() {
        this((FramePredicate) null);
    }

    public Id3Decoder(FramePredicate framePredicate2) {
        this.framePredicate = framePredicate2;
    }

    /* access modifiers changed from: protected */
    public Metadata decode(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer) {
        return decode(byteBuffer.array(), byteBuffer.limit());
    }

    public Metadata decode(byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i);
        Id3Header decodeHeader = decodeHeader(parsableByteArray);
        if (decodeHeader == null) {
            return null;
        }
        int position = parsableByteArray.getPosition();
        int i2 = decodeHeader.majorVersion == 2 ? 6 : 10;
        int access$100 = decodeHeader.framesSize;
        if (decodeHeader.isUnsynchronized) {
            access$100 = removeUnsynchronization(parsableByteArray, decodeHeader.framesSize);
        }
        parsableByteArray.setLimit(position + access$100);
        boolean z = false;
        if (!validateFrames(parsableByteArray, decodeHeader.majorVersion, i2, false)) {
            if (decodeHeader.majorVersion != 4 || !validateFrames(parsableByteArray, 4, i2, true)) {
                Log.w(TAG, "Failed to validate ID3 tag with majorVersion=" + decodeHeader.majorVersion);
                return null;
            }
            z = true;
        }
        while (parsableByteArray.bytesLeft() >= i2) {
            Id3Frame decodeFrame = decodeFrame(decodeHeader.majorVersion, parsableByteArray, z, i2, this.framePredicate);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    private static Id3Header decodeHeader(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() < 10) {
            Log.w(TAG, "Data too short to be an ID3 tag");
            return null;
        }
        int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
        if (readUnsignedInt24 != 4801587) {
            Log.w(TAG, "Unexpected first three bytes of ID3 tag header: 0x" + String.format("%06X", new Object[]{Integer.valueOf(readUnsignedInt24)}));
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        boolean z = true;
        parsableByteArray.skipBytes(1);
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        int readSynchSafeInt = parsableByteArray.readSynchSafeInt();
        if (readUnsignedByte == 2) {
            if ((readUnsignedByte2 & 64) != 0) {
                Log.w(TAG, "Skipped ID3 tag with majorVersion=2 and undefined compression scheme");
                return null;
            }
        } else if (readUnsignedByte == 3) {
            if ((readUnsignedByte2 & 64) != 0) {
                int readInt = parsableByteArray.readInt();
                parsableByteArray.skipBytes(readInt);
                readSynchSafeInt -= readInt + 4;
            }
        } else if (readUnsignedByte == 4) {
            if ((readUnsignedByte2 & 64) != 0) {
                int readSynchSafeInt2 = parsableByteArray.readSynchSafeInt();
                parsableByteArray.skipBytes(readSynchSafeInt2 - 4);
                readSynchSafeInt -= readSynchSafeInt2;
            }
            if ((readUnsignedByte2 & 16) != 0) {
                readSynchSafeInt -= 10;
            }
        } else {
            Log.w(TAG, "Skipped ID3 tag with unsupported majorVersion=" + readUnsignedByte);
            return null;
        }
        if (readUnsignedByte >= 4 || (readUnsignedByte2 & 128) == 0) {
            z = false;
        }
        return new Id3Header(readUnsignedByte, z, readSynchSafeInt);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0076, code lost:
        if ((r10 & 1) != 0) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0086, code lost:
        if ((r10 & 128) != 0) goto L_0x008b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0098 A[SYNTHETIC, Splitter:B:46:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0094 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean validateFrames(androidx.media3.common.util.ParsableByteArray r18, int r19, int r20, boolean r21) {
        /*
            r1 = r18
            r0 = r19
            int r2 = r18.getPosition()
        L_0x0008:
            int r3 = r18.bytesLeft()     // Catch:{ all -> 0x00af }
            r4 = 1
            r5 = r20
            if (r3 < r5) goto L_0x00ab
            r3 = 3
            r6 = 0
            if (r0 < r3) goto L_0x0022
            int r7 = r18.readInt()     // Catch:{ all -> 0x00af }
            long r8 = r18.readUnsignedInt()     // Catch:{ all -> 0x00af }
            int r10 = r18.readUnsignedShort()     // Catch:{ all -> 0x00af }
            goto L_0x002c
        L_0x0022:
            int r7 = r18.readUnsignedInt24()     // Catch:{ all -> 0x00af }
            int r8 = r18.readUnsignedInt24()     // Catch:{ all -> 0x00af }
            long r8 = (long) r8
            r10 = r6
        L_0x002c:
            r11 = 0
            if (r7 != 0) goto L_0x003a
            int r7 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r7 != 0) goto L_0x003a
            if (r10 != 0) goto L_0x003a
            r1.setPosition(r2)
            return r4
        L_0x003a:
            r7 = 4
            if (r0 != r7) goto L_0x006b
            if (r21 != 0) goto L_0x006b
            r13 = 8421504(0x808080, double:4.160776E-317)
            long r13 = r13 & r8
            int r11 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x004b
            r1.setPosition(r2)
            return r6
        L_0x004b:
            r11 = 255(0xff, double:1.26E-321)
            long r13 = r8 & r11
            r15 = 8
            long r15 = r8 >> r15
            long r15 = r15 & r11
            r17 = 7
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 16
            long r15 = r8 >> r15
            long r15 = r15 & r11
            r17 = 14
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 24
            long r8 = r8 >> r15
            long r8 = r8 & r11
            r11 = 21
            long r8 = r8 << r11
            long r8 = r8 | r13
        L_0x006b:
            if (r0 != r7) goto L_0x007b
            r3 = r10 & 64
            if (r3 == 0) goto L_0x0073
            r3 = r4
            goto L_0x0074
        L_0x0073:
            r3 = r6
        L_0x0074:
            r7 = r10 & 1
            if (r7 == 0) goto L_0x0079
            goto L_0x008b
        L_0x0079:
            r4 = r6
            goto L_0x008b
        L_0x007b:
            if (r0 != r3) goto L_0x0089
            r3 = r10 & 32
            if (r3 == 0) goto L_0x0083
            r3 = r4
            goto L_0x0084
        L_0x0083:
            r3 = r6
        L_0x0084:
            r7 = r10 & 128(0x80, float:1.794E-43)
            if (r7 == 0) goto L_0x0079
            goto L_0x008b
        L_0x0089:
            r3 = r6
            r4 = r3
        L_0x008b:
            if (r4 == 0) goto L_0x008f
            int r3 = r3 + 4
        L_0x008f:
            long r3 = (long) r3
            int r3 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x0098
            r1.setPosition(r2)
            return r6
        L_0x0098:
            int r3 = r18.bytesLeft()     // Catch:{ all -> 0x00af }
            long r3 = (long) r3
            int r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x00a5
            r1.setPosition(r2)
            return r6
        L_0x00a5:
            int r3 = (int) r8
            r1.skipBytes(r3)     // Catch:{ all -> 0x00af }
            goto L_0x0008
        L_0x00ab:
            r1.setPosition(r2)
            return r4
        L_0x00af:
            r0 = move-exception
            r1.setPosition(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.metadata.id3.Id3Decoder.validateFrames(androidx.media3.common.util.ParsableByteArray, int, int, boolean):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0194, code lost:
        if (r13 == 67) goto L_0x0196;
     */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0209  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.extractor.metadata.id3.Id3Frame decodeFrame(int r20, androidx.media3.common.util.ParsableByteArray r21, boolean r22, int r23, androidx.media3.extractor.metadata.id3.Id3Decoder.FramePredicate r24) {
        /*
            r7 = r20
            r8 = r21
            int r9 = r21.readUnsignedByte()
            int r10 = r21.readUnsignedByte()
            int r11 = r21.readUnsignedByte()
            r12 = 3
            if (r7 < r12) goto L_0x0019
            int r1 = r21.readUnsignedByte()
            r13 = r1
            goto L_0x001a
        L_0x0019:
            r13 = 0
        L_0x001a:
            r14 = 4
            if (r7 != r14) goto L_0x003c
            int r1 = r21.readUnsignedIntToInt()
            if (r22 != 0) goto L_0x003a
            r2 = r1 & 255(0xff, float:3.57E-43)
            int r3 = r1 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 7
            r2 = r2 | r3
            int r3 = r1 >> 16
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 14
            r2 = r2 | r3
            int r1 = r1 >> 24
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << 21
            r1 = r1 | r2
        L_0x003a:
            r15 = r1
            goto L_0x0048
        L_0x003c:
            if (r7 != r12) goto L_0x0043
            int r1 = r21.readUnsignedIntToInt()
            goto L_0x003a
        L_0x0043:
            int r1 = r21.readUnsignedInt24()
            goto L_0x003a
        L_0x0048:
            if (r7 < r12) goto L_0x0050
            int r1 = r21.readUnsignedShort()
            r6 = r1
            goto L_0x0051
        L_0x0050:
            r6 = 0
        L_0x0051:
            r16 = 0
            if (r9 != 0) goto L_0x0067
            if (r10 != 0) goto L_0x0067
            if (r11 != 0) goto L_0x0067
            if (r13 != 0) goto L_0x0067
            if (r15 != 0) goto L_0x0067
            if (r6 != 0) goto L_0x0067
            int r0 = r21.limit()
            r8.setPosition(r0)
            return r16
        L_0x0067:
            int r1 = r21.getPosition()
            int r5 = r1 + r15
            int r1 = r21.limit()
            java.lang.String r4 = "Id3Decoder"
            if (r5 <= r1) goto L_0x0082
            java.lang.String r0 = "Frame size exceeds remaining tag data"
            androidx.media3.common.util.Log.w(r4, r0)
            int r0 = r21.limit()
            r8.setPosition(r0)
            return r16
        L_0x0082:
            if (r24 == 0) goto L_0x009a
            r1 = r24
            r2 = r20
            r3 = r9
            r17 = r4
            r4 = r10
            r14 = r5
            r5 = r11
            r0 = r6
            r6 = r13
            boolean r1 = r1.evaluate(r2, r3, r4, r5, r6)
            if (r1 != 0) goto L_0x009e
            r8.setPosition(r14)
            return r16
        L_0x009a:
            r17 = r4
            r14 = r5
            r0 = r6
        L_0x009e:
            r1 = 1
            if (r7 != r12) goto L_0x00bd
            r2 = r0 & 128(0x80, float:1.794E-43)
            if (r2 == 0) goto L_0x00a7
            r2 = r1
            goto L_0x00a8
        L_0x00a7:
            r2 = 0
        L_0x00a8:
            r3 = r0 & 64
            if (r3 == 0) goto L_0x00ae
            r3 = r1
            goto L_0x00af
        L_0x00ae:
            r3 = 0
        L_0x00af:
            r0 = r0 & 32
            if (r0 == 0) goto L_0x00b5
            r0 = r1
            goto L_0x00b6
        L_0x00b5:
            r0 = 0
        L_0x00b6:
            r18 = r3
            r5 = 0
            r3 = r2
            r2 = r0
            r0 = r3
            goto L_0x00f0
        L_0x00bd:
            r2 = 4
            if (r7 != r2) goto L_0x00ea
            r2 = r0 & 64
            if (r2 == 0) goto L_0x00c6
            r2 = r1
            goto L_0x00c7
        L_0x00c6:
            r2 = 0
        L_0x00c7:
            r3 = r0 & 8
            if (r3 == 0) goto L_0x00cd
            r3 = r1
            goto L_0x00ce
        L_0x00cd:
            r3 = 0
        L_0x00ce:
            r4 = r0 & 4
            if (r4 == 0) goto L_0x00d4
            r4 = r1
            goto L_0x00d5
        L_0x00d4:
            r4 = 0
        L_0x00d5:
            r5 = r0 & 2
            if (r5 == 0) goto L_0x00db
            r5 = r1
            goto L_0x00dc
        L_0x00db:
            r5 = 0
        L_0x00dc:
            r0 = r0 & r1
            if (r0 == 0) goto L_0x00e1
            r0 = r1
            goto L_0x00e2
        L_0x00e1:
            r0 = 0
        L_0x00e2:
            r18 = r4
            r19 = r3
            r3 = r0
            r0 = r19
            goto L_0x00f0
        L_0x00ea:
            r0 = 0
            r2 = 0
            r3 = 0
            r5 = 0
            r18 = 0
        L_0x00f0:
            if (r0 != 0) goto L_0x022c
            if (r18 == 0) goto L_0x00f6
            goto L_0x022c
        L_0x00f6:
            if (r2 == 0) goto L_0x00fd
            int r15 = r15 + -1
            r8.skipBytes(r1)
        L_0x00fd:
            if (r3 == 0) goto L_0x0105
            int r15 = r15 + -4
            r0 = 4
            r8.skipBytes(r0)
        L_0x0105:
            if (r5 == 0) goto L_0x010b
            int r15 = removeUnsynchronization(r8, r15)
        L_0x010b:
            r0 = 84
            r1 = 88
            r2 = 2
            if (r9 != r0) goto L_0x0120
            if (r10 != r1) goto L_0x0120
            if (r11 != r1) goto L_0x0120
            if (r7 == r2) goto L_0x011a
            if (r13 != r1) goto L_0x0120
        L_0x011a:
            androidx.media3.extractor.metadata.id3.TextInformationFrame r0 = decodeTxxxFrame(r8, r15)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            goto L_0x01f6
        L_0x0120:
            if (r9 != r0) goto L_0x0135
            java.lang.String r0 = getFrameId(r7, r9, r10, r11, r13)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            androidx.media3.extractor.metadata.id3.TextInformationFrame r0 = decodeTextInformationFrame(r8, r15, r0)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            goto L_0x01f6
        L_0x012c:
            r0 = move-exception
            goto L_0x0200
        L_0x012f:
            r0 = move-exception
            goto L_0x0204
        L_0x0132:
            r0 = move-exception
            goto L_0x0204
        L_0x0135:
            r3 = 87
            if (r9 != r3) goto L_0x0147
            if (r10 != r1) goto L_0x0147
            if (r11 != r1) goto L_0x0147
            if (r7 == r2) goto L_0x0141
            if (r13 != r1) goto L_0x0147
        L_0x0141:
            androidx.media3.extractor.metadata.id3.UrlLinkFrame r0 = decodeWxxxFrame(r8, r15)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            goto L_0x01f6
        L_0x0147:
            if (r9 != r3) goto L_0x0153
            java.lang.String r0 = getFrameId(r7, r9, r10, r11, r13)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            androidx.media3.extractor.metadata.id3.UrlLinkFrame r0 = decodeUrlLinkFrame(r8, r15, r0)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            goto L_0x01f6
        L_0x0153:
            r1 = 73
            r3 = 80
            if (r9 != r3) goto L_0x0169
            r4 = 82
            if (r10 != r4) goto L_0x0169
            if (r11 != r1) goto L_0x0169
            r4 = 86
            if (r13 != r4) goto L_0x0169
            androidx.media3.extractor.metadata.id3.PrivFrame r0 = decodePrivFrame(r8, r15)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            goto L_0x01f6
        L_0x0169:
            r4 = 71
            r5 = 79
            if (r9 != r4) goto L_0x0181
            r4 = 69
            if (r10 != r4) goto L_0x0181
            if (r11 != r5) goto L_0x0181
            r4 = 66
            if (r13 == r4) goto L_0x017b
            if (r7 != r2) goto L_0x0181
        L_0x017b:
            androidx.media3.extractor.metadata.id3.GeobFrame r0 = decodeGeobFrame(r8, r15)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            goto L_0x01f6
        L_0x0181:
            r4 = 65
            r6 = 67
            if (r7 != r2) goto L_0x018e
            if (r9 != r3) goto L_0x019c
            if (r10 != r1) goto L_0x019c
            if (r11 != r6) goto L_0x019c
            goto L_0x0196
        L_0x018e:
            if (r9 != r4) goto L_0x019c
            if (r10 != r3) goto L_0x019c
            if (r11 != r1) goto L_0x019c
            if (r13 != r6) goto L_0x019c
        L_0x0196:
            androidx.media3.extractor.metadata.id3.ApicFrame r0 = decodeApicFrame(r8, r15, r7)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            goto L_0x01f6
        L_0x019c:
            r1 = 77
            if (r9 != r6) goto L_0x01ad
            if (r10 != r5) goto L_0x01ad
            if (r11 != r1) goto L_0x01ad
            if (r13 == r1) goto L_0x01a8
            if (r7 != r2) goto L_0x01ad
        L_0x01a8:
            androidx.media3.extractor.metadata.id3.CommentFrame r0 = decodeCommentFrame(r8, r15)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            goto L_0x01f6
        L_0x01ad:
            if (r9 != r6) goto L_0x01c7
            r2 = 72
            if (r10 != r2) goto L_0x01c7
            if (r11 != r4) goto L_0x01c7
            if (r13 != r3) goto L_0x01c7
            r1 = r21
            r2 = r15
            r3 = r20
            r4 = r22
            r5 = r23
            r6 = r24
            androidx.media3.extractor.metadata.id3.ChapterFrame r0 = decodeChapterFrame(r1, r2, r3, r4, r5, r6)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            goto L_0x01f6
        L_0x01c7:
            if (r9 != r6) goto L_0x01df
            if (r10 != r0) goto L_0x01df
            if (r11 != r5) goto L_0x01df
            if (r13 != r6) goto L_0x01df
            r1 = r21
            r2 = r15
            r3 = r20
            r4 = r22
            r5 = r23
            r6 = r24
            androidx.media3.extractor.metadata.id3.ChapterTocFrame r0 = decodeChapterTOCFrame(r1, r2, r3, r4, r5, r6)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            goto L_0x01f6
        L_0x01df:
            if (r9 != r1) goto L_0x01ee
            r1 = 76
            if (r10 != r1) goto L_0x01ee
            if (r11 != r1) goto L_0x01ee
            if (r13 != r0) goto L_0x01ee
            androidx.media3.extractor.metadata.id3.MlltFrame r0 = decodeMlltFrame(r8, r15)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            goto L_0x01f6
        L_0x01ee:
            java.lang.String r0 = getFrameId(r7, r9, r10, r11, r13)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
            androidx.media3.extractor.metadata.id3.BinaryFrame r0 = decodeBinaryFrame(r8, r15, r0)     // Catch:{ OutOfMemoryError -> 0x0132, Exception -> 0x012f, all -> 0x012c }
        L_0x01f6:
            r8.setPosition(r14)
            r19 = r16
            r16 = r0
            r0 = r19
            goto L_0x0207
        L_0x0200:
            r8.setPosition(r14)
            throw r0
        L_0x0204:
            r8.setPosition(r14)
        L_0x0207:
            if (r16 != 0) goto L_0x022b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Failed to decode frame: id="
            r1.<init>(r2)
            java.lang.String r2 = getFrameId(r7, r9, r10, r11, r13)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = ", frameSize="
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r15)
            java.lang.String r1 = r1.toString()
            r2 = r17
            androidx.media3.common.util.Log.w(r2, r1, r0)
        L_0x022b:
            return r16
        L_0x022c:
            r2 = r17
            java.lang.String r0 = "Skipping unsupported compressed or encrypted frame"
            androidx.media3.common.util.Log.w(r2, r0)
            r8.setPosition(r14)
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.metadata.id3.Id3Decoder.decodeFrame(int, androidx.media3.common.util.ParsableByteArray, boolean, int, androidx.media3.extractor.metadata.id3.Id3Decoder$FramePredicate):androidx.media3.extractor.metadata.id3.Id3Frame");
    }

    private static TextInformationFrame decodeTxxxFrame(ParsableByteArray parsableByteArray, int i) {
        if (i < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int indexOfTerminator = indexOfTerminator(bArr, 0, readUnsignedByte);
        return new TextInformationFrame("TXXX", new String(bArr, 0, indexOfTerminator, getCharset(readUnsignedByte)), (List<String>) decodeTextInformationFrameValues(bArr, readUnsignedByte, indexOfTerminator + delimiterLength(readUnsignedByte)));
    }

    private static TextInformationFrame decodeTextInformationFrame(ParsableByteArray parsableByteArray, int i, String str) {
        if (i < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        return new TextInformationFrame(str, (String) null, (List<String>) decodeTextInformationFrameValues(bArr, readUnsignedByte, 0));
    }

    private static ImmutableList<String> decodeTextInformationFrameValues(byte[] bArr, int i, int i2) {
        if (i2 >= bArr.length) {
            return ImmutableList.of("");
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        int indexOfTerminator = indexOfTerminator(bArr, i2, i);
        while (i2 < indexOfTerminator) {
            builder.add((Object) new String(bArr, i2, indexOfTerminator - i2, getCharset(i)));
            i2 = delimiterLength(i) + indexOfTerminator;
            indexOfTerminator = indexOfTerminator(bArr, i2, i);
        }
        ImmutableList<String> build = builder.build();
        return build.isEmpty() ? ImmutableList.of("") : build;
    }

    private static UrlLinkFrame decodeWxxxFrame(ParsableByteArray parsableByteArray, int i) {
        if (i < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int indexOfTerminator = indexOfTerminator(bArr, 0, readUnsignedByte);
        String str = new String(bArr, 0, indexOfTerminator, getCharset(readUnsignedByte));
        int delimiterLength = indexOfTerminator + delimiterLength(readUnsignedByte);
        return new UrlLinkFrame("WXXX", str, decodeStringIfValid(bArr, delimiterLength, indexOfZeroByte(bArr, delimiterLength), StandardCharsets.ISO_8859_1));
    }

    private static UrlLinkFrame decodeUrlLinkFrame(ParsableByteArray parsableByteArray, int i, String str) {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        return new UrlLinkFrame(str, (String) null, new String(bArr, 0, indexOfZeroByte(bArr, 0), StandardCharsets.ISO_8859_1));
    }

    private static PrivFrame decodePrivFrame(ParsableByteArray parsableByteArray, int i) {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        int indexOfZeroByte = indexOfZeroByte(bArr, 0);
        return new PrivFrame(new String(bArr, 0, indexOfZeroByte, StandardCharsets.ISO_8859_1), copyOfRangeIfValid(bArr, indexOfZeroByte + 1, i));
    }

    private static GeobFrame decodeGeobFrame(ParsableByteArray parsableByteArray, int i) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        Charset charset = getCharset(readUnsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int indexOfZeroByte = indexOfZeroByte(bArr, 0);
        String normalizeMimeType = MimeTypes.normalizeMimeType(new String(bArr, 0, indexOfZeroByte, StandardCharsets.ISO_8859_1));
        int i3 = indexOfZeroByte + 1;
        int indexOfTerminator = indexOfTerminator(bArr, i3, readUnsignedByte);
        String decodeStringIfValid = decodeStringIfValid(bArr, i3, indexOfTerminator, charset);
        int delimiterLength = indexOfTerminator + delimiterLength(readUnsignedByte);
        int indexOfTerminator2 = indexOfTerminator(bArr, delimiterLength, readUnsignedByte);
        return new GeobFrame(normalizeMimeType, decodeStringIfValid, decodeStringIfValid(bArr, delimiterLength, indexOfTerminator2, charset), copyOfRangeIfValid(bArr, indexOfTerminator2 + delimiterLength(readUnsignedByte), i2));
    }

    private static ApicFrame decodeApicFrame(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3;
        String str;
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        Charset charset = getCharset(readUnsignedByte);
        int i4 = i - 1;
        byte[] bArr = new byte[i4];
        parsableByteArray.readBytes(bArr, 0, i4);
        if (i2 == 2) {
            str = "image/" + Ascii.toLowerCase(new String(bArr, 0, 3, StandardCharsets.ISO_8859_1));
            if ("image/jpg".equals(str)) {
                str = "image/jpeg";
            }
            i3 = 2;
        } else {
            i3 = indexOfZeroByte(bArr, 0);
            String lowerCase = Ascii.toLowerCase(new String(bArr, 0, i3, StandardCharsets.ISO_8859_1));
            str = lowerCase.indexOf(47) == -1 ? "image/" + lowerCase : lowerCase;
        }
        int i5 = i3 + 2;
        int indexOfTerminator = indexOfTerminator(bArr, i5, readUnsignedByte);
        return new ApicFrame(str, new String(bArr, i5, indexOfTerminator - i5, charset), bArr[i3 + 1] & 255, copyOfRangeIfValid(bArr, indexOfTerminator + delimiterLength(readUnsignedByte), i4));
    }

    private static CommentFrame decodeCommentFrame(ParsableByteArray parsableByteArray, int i) {
        if (i < 4) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        Charset charset = getCharset(readUnsignedByte);
        byte[] bArr = new byte[3];
        parsableByteArray.readBytes(bArr, 0, 3);
        String str = new String(bArr, 0, 3);
        int i2 = i - 4;
        byte[] bArr2 = new byte[i2];
        parsableByteArray.readBytes(bArr2, 0, i2);
        int indexOfTerminator = indexOfTerminator(bArr2, 0, readUnsignedByte);
        String str2 = new String(bArr2, 0, indexOfTerminator, charset);
        int delimiterLength = indexOfTerminator + delimiterLength(readUnsignedByte);
        return new CommentFrame(str, str2, decodeStringIfValid(bArr2, delimiterLength, indexOfTerminator(bArr2, delimiterLength, readUnsignedByte), charset));
    }

    private static ChapterFrame decodeChapterFrame(ParsableByteArray parsableByteArray, int i, int i2, boolean z, int i3, FramePredicate framePredicate2) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int position = parsableByteArray.getPosition();
        int indexOfZeroByte = indexOfZeroByte(parsableByteArray.getData(), position);
        String str = new String(parsableByteArray.getData(), position, indexOfZeroByte - position, StandardCharsets.ISO_8859_1);
        parsableByteArray.setPosition(indexOfZeroByte + 1);
        int readInt = parsableByteArray.readInt();
        int readInt2 = parsableByteArray.readInt();
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        long j = readUnsignedInt == MuxerUtil.UNSIGNED_INT_MAX_VALUE ? -1 : readUnsignedInt;
        long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
        long j2 = readUnsignedInt2 == MuxerUtil.UNSIGNED_INT_MAX_VALUE ? -1 : readUnsignedInt2;
        ArrayList arrayList = new ArrayList();
        int i4 = position + i;
        while (parsableByteArray.getPosition() < i4) {
            Id3Frame decodeFrame = decodeFrame(i2, parsableByteArray, z, i3, framePredicate2);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        return new ChapterFrame(str, readInt, readInt2, j, j2, (Id3Frame[]) arrayList.toArray(new Id3Frame[0]));
    }

    private static ChapterTocFrame decodeChapterTOCFrame(ParsableByteArray parsableByteArray, int i, int i2, boolean z, int i3, FramePredicate framePredicate2) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int position = parsableByteArray.getPosition();
        int indexOfZeroByte = indexOfZeroByte(parsableByteArray.getData(), position);
        String str = new String(parsableByteArray.getData(), position, indexOfZeroByte - position, StandardCharsets.ISO_8859_1);
        parsableByteArray2.setPosition(indexOfZeroByte + 1);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        boolean z2 = (readUnsignedByte & 2) != 0;
        boolean z3 = (readUnsignedByte & 1) != 0;
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        String[] strArr = new String[readUnsignedByte2];
        for (int i4 = 0; i4 < readUnsignedByte2; i4++) {
            int position2 = parsableByteArray.getPosition();
            int indexOfZeroByte2 = indexOfZeroByte(parsableByteArray.getData(), position2);
            strArr[i4] = new String(parsableByteArray.getData(), position2, indexOfZeroByte2 - position2, StandardCharsets.ISO_8859_1);
            parsableByteArray2.setPosition(indexOfZeroByte2 + 1);
        }
        ArrayList arrayList = new ArrayList();
        int i5 = position + i;
        while (parsableByteArray.getPosition() < i5) {
            Id3Frame decodeFrame = decodeFrame(i2, parsableByteArray2, z, i3, framePredicate2);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        return new ChapterTocFrame(str, z2, z3, strArr, (Id3Frame[]) arrayList.toArray(new Id3Frame[0]));
    }

    private static MlltFrame decodeMlltFrame(ParsableByteArray parsableByteArray, int i) {
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
        int readUnsignedInt242 = parsableByteArray.readUnsignedInt24();
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        ParsableBitArray parsableBitArray = new ParsableBitArray();
        parsableBitArray.reset(parsableByteArray);
        int i2 = ((i - 10) * 8) / (readUnsignedByte + readUnsignedByte2);
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int readBits = parsableBitArray.readBits(readUnsignedByte);
            int readBits2 = parsableBitArray.readBits(readUnsignedByte2);
            iArr[i3] = readBits;
            iArr2[i3] = readBits2;
        }
        return new MlltFrame(readUnsignedShort, readUnsignedInt24, readUnsignedInt242, iArr, iArr2);
    }

    private static BinaryFrame decodeBinaryFrame(ParsableByteArray parsableByteArray, int i, String str) {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        return new BinaryFrame(str, bArr);
    }

    private static int removeUnsynchronization(ParsableByteArray parsableByteArray, int i) {
        byte[] data = parsableByteArray.getData();
        int position = parsableByteArray.getPosition();
        int i2 = position;
        while (true) {
            int i3 = i2 + 1;
            if (i3 >= position + i) {
                return i;
            }
            if ((data[i2] & 255) == 255 && data[i3] == 0) {
                System.arraycopy(data, i2 + 2, data, i3, (i - (i2 - position)) - 2);
                i--;
            }
            i2 = i3;
        }
    }

    private static Charset getCharset(int i) {
        if (i == 1) {
            return StandardCharsets.UTF_16;
        }
        if (i == 2) {
            return StandardCharsets.UTF_16BE;
        }
        if (i != 3) {
            return StandardCharsets.ISO_8859_1;
        }
        return StandardCharsets.UTF_8;
    }

    private static String getFrameId(int i, int i2, int i3, int i4, int i5) {
        if (i == 2) {
            return String.format(Locale.US, "%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)});
        }
        return String.format(Locale.US, "%c%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)});
    }

    private static int indexOfTerminator(byte[] bArr, int i, int i2) {
        int indexOfZeroByte = indexOfZeroByte(bArr, i);
        if (i2 == 0 || i2 == 3) {
            return indexOfZeroByte;
        }
        while (indexOfZeroByte < bArr.length - 1) {
            if ((indexOfZeroByte - i) % 2 == 0 && bArr[indexOfZeroByte + 1] == 0) {
                return indexOfZeroByte;
            }
            indexOfZeroByte = indexOfZeroByte(bArr, indexOfZeroByte + 1);
        }
        return bArr.length;
    }

    private static int indexOfZeroByte(byte[] bArr, int i) {
        while (i < bArr.length) {
            if (bArr[i] == 0) {
                return i;
            }
            i++;
        }
        return bArr.length;
    }

    private static byte[] copyOfRangeIfValid(byte[] bArr, int i, int i2) {
        if (i2 <= i) {
            return Util.EMPTY_BYTE_ARRAY;
        }
        return Arrays.copyOfRange(bArr, i, i2);
    }

    private static String decodeStringIfValid(byte[] bArr, int i, int i2, Charset charset) {
        return (i2 <= i || i2 > bArr.length) ? "" : new String(bArr, i, i2 - i, charset);
    }

    private static final class Id3Header {
        /* access modifiers changed from: private */
        public final int framesSize;
        /* access modifiers changed from: private */
        public final boolean isUnsynchronized;
        /* access modifiers changed from: private */
        public final int majorVersion;

        public Id3Header(int i, boolean z, int i2) {
            this.majorVersion = i;
            this.isUnsynchronized = z;
            this.framesSize = i2;
        }
    }
}
