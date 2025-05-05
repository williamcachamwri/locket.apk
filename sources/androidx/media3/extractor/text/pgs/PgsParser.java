package androidx.media3.extractor.text.pgs;

import android.graphics.Bitmap;
import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

public final class PgsParser implements SubtitleParser {
    public static final int CUE_REPLACEMENT_BEHAVIOR = 2;
    private static final byte INFLATE_HEADER = 120;
    private static final int SECTION_TYPE_BITMAP_PICTURE = 21;
    private static final int SECTION_TYPE_END = 128;
    private static final int SECTION_TYPE_IDENTIFIER = 22;
    private static final int SECTION_TYPE_PALETTE = 20;
    private final ParsableByteArray buffer = new ParsableByteArray();
    private final CueBuilder cueBuilder = new CueBuilder();
    private final ParsableByteArray inflatedBuffer = new ParsableByteArray();
    private Inflater inflater;

    public int getCueReplacementBehavior() {
        return 2;
    }

    public void parse(byte[] bArr, int i, int i2, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        this.buffer.reset(bArr, i2 + i);
        this.buffer.setPosition(i);
        maybeInflateData(this.buffer);
        this.cueBuilder.reset();
        ArrayList arrayList = new ArrayList();
        while (this.buffer.bytesLeft() >= 3) {
            Cue readNextSection = readNextSection(this.buffer, this.cueBuilder);
            if (readNextSection != null) {
                arrayList.add(readNextSection);
            }
        }
        consumer.accept(new CuesWithTiming(arrayList, C.TIME_UNSET, C.TIME_UNSET));
    }

    private void maybeInflateData(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() > 0 && parsableByteArray.peekUnsignedByte() == 120) {
            if (this.inflater == null) {
                this.inflater = new Inflater();
            }
            if (Util.inflate(parsableByteArray, this.inflatedBuffer, this.inflater)) {
                parsableByteArray.reset(this.inflatedBuffer.getData(), this.inflatedBuffer.limit());
            }
        }
    }

    private static Cue readNextSection(ParsableByteArray parsableByteArray, CueBuilder cueBuilder2) {
        int limit = parsableByteArray.limit();
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int position = parsableByteArray.getPosition() + readUnsignedShort;
        Cue cue = null;
        if (position > limit) {
            parsableByteArray.setPosition(limit);
            return null;
        }
        if (readUnsignedByte != 128) {
            switch (readUnsignedByte) {
                case 20:
                    cueBuilder2.parsePaletteSection(parsableByteArray, readUnsignedShort);
                    break;
                case 21:
                    cueBuilder2.parseBitmapSection(parsableByteArray, readUnsignedShort);
                    break;
                case 22:
                    cueBuilder2.parseIdentifierSection(parsableByteArray, readUnsignedShort);
                    break;
            }
        } else {
            cue = cueBuilder2.build();
            cueBuilder2.reset();
        }
        parsableByteArray.setPosition(position);
        return cue;
    }

    private static final class CueBuilder {
        private final ParsableByteArray bitmapData = new ParsableByteArray();
        private int bitmapHeight;
        private int bitmapWidth;
        private int bitmapX;
        private int bitmapY;
        private final int[] colors = new int[256];
        private boolean colorsSet;
        private int planeHeight;
        private int planeWidth;

        /* access modifiers changed from: private */
        public void parsePaletteSection(ParsableByteArray parsableByteArray, int i) {
            if (i % 5 == 2) {
                parsableByteArray.skipBytes(2);
                Arrays.fill(this.colors, 0);
                int i2 = i / 5;
                for (int i3 = 0; i3 < i2; i3++) {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    int readUnsignedByte4 = parsableByteArray.readUnsignedByte();
                    double d = (double) readUnsignedByte2;
                    double d2 = (double) (readUnsignedByte3 - 128);
                    double d3 = (double) (readUnsignedByte4 - 128);
                    this.colors[readUnsignedByte] = (Util.constrainValue((int) ((d - (0.34414d * d3)) - (d2 * 0.71414d)), 0, 255) << 8) | (parsableByteArray.readUnsignedByte() << 24) | (Util.constrainValue((int) ((1.402d * d2) + d), 0, 255) << 16) | Util.constrainValue((int) (d + (d3 * 1.772d)), 0, 255);
                }
                this.colorsSet = true;
            }
        }

        /* access modifiers changed from: private */
        public void parseBitmapSection(ParsableByteArray parsableByteArray, int i) {
            int readUnsignedInt24;
            if (i >= 4) {
                parsableByteArray.skipBytes(3);
                int i2 = i - 4;
                if ((parsableByteArray.readUnsignedByte() & 128) != 0) {
                    if (i2 >= 7 && (readUnsignedInt24 = parsableByteArray.readUnsignedInt24()) >= 4) {
                        this.bitmapWidth = parsableByteArray.readUnsignedShort();
                        this.bitmapHeight = parsableByteArray.readUnsignedShort();
                        this.bitmapData.reset(readUnsignedInt24 - 4);
                        i2 -= 7;
                    } else {
                        return;
                    }
                }
                int position = this.bitmapData.getPosition();
                int limit = this.bitmapData.limit();
                if (position < limit && i2 > 0) {
                    int min = Math.min(i2, limit - position);
                    parsableByteArray.readBytes(this.bitmapData.getData(), position, min);
                    this.bitmapData.setPosition(position + min);
                }
            }
        }

        /* access modifiers changed from: private */
        public void parseIdentifierSection(ParsableByteArray parsableByteArray, int i) {
            if (i >= 19) {
                this.planeWidth = parsableByteArray.readUnsignedShort();
                this.planeHeight = parsableByteArray.readUnsignedShort();
                parsableByteArray.skipBytes(11);
                this.bitmapX = parsableByteArray.readUnsignedShort();
                this.bitmapY = parsableByteArray.readUnsignedShort();
            }
        }

        public Cue build() {
            int i;
            int i2;
            if (this.planeWidth == 0 || this.planeHeight == 0 || this.bitmapWidth == 0 || this.bitmapHeight == 0 || this.bitmapData.limit() == 0 || this.bitmapData.getPosition() != this.bitmapData.limit() || !this.colorsSet) {
                return null;
            }
            this.bitmapData.setPosition(0);
            int i3 = this.bitmapWidth * this.bitmapHeight;
            int[] iArr = new int[i3];
            int i4 = 0;
            while (i4 < i3) {
                int readUnsignedByte = this.bitmapData.readUnsignedByte();
                if (readUnsignedByte != 0) {
                    i2 = i4 + 1;
                    iArr[i4] = this.colors[readUnsignedByte];
                } else {
                    int readUnsignedByte2 = this.bitmapData.readUnsignedByte();
                    if (readUnsignedByte2 != 0) {
                        if ((readUnsignedByte2 & 64) == 0) {
                            i = readUnsignedByte2 & 63;
                        } else {
                            i = ((readUnsignedByte2 & 63) << 8) | this.bitmapData.readUnsignedByte();
                        }
                        i2 = i + i4;
                        Arrays.fill(iArr, i4, i2, (readUnsignedByte2 & 128) == 0 ? this.colors[0] : this.colors[this.bitmapData.readUnsignedByte()]);
                    }
                }
                i4 = i2;
            }
            return new Cue.Builder().setBitmap(Bitmap.createBitmap(iArr, this.bitmapWidth, this.bitmapHeight, Bitmap.Config.ARGB_8888)).setPosition(((float) this.bitmapX) / ((float) this.planeWidth)).setPositionAnchor(0).setLine(((float) this.bitmapY) / ((float) this.planeHeight), 0).setLineAnchor(0).setSize(((float) this.bitmapWidth) / ((float) this.planeWidth)).setBitmapHeight(((float) this.bitmapHeight) / ((float) this.planeHeight)).build();
        }

        public void reset() {
            this.planeWidth = 0;
            this.planeHeight = 0;
            this.bitmapX = 0;
            this.bitmapY = 0;
            this.bitmapWidth = 0;
            this.bitmapHeight = 0;
            this.bitmapData.reset(0);
            this.colorsSet = false;
        }
    }
}
