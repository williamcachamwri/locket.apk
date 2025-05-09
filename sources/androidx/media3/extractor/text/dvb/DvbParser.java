package androidx.media3.extractor.text.dvb;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.SparseArray;
import androidx.core.view.ViewCompat;
import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

public final class DvbParser implements SubtitleParser {
    public static final int CUE_REPLACEMENT_BEHAVIOR = 2;
    private static final int DATA_TYPE_24_TABLE_DATA = 32;
    private static final int DATA_TYPE_28_TABLE_DATA = 33;
    private static final int DATA_TYPE_2BP_CODE_STRING = 16;
    private static final int DATA_TYPE_48_TABLE_DATA = 34;
    private static final int DATA_TYPE_4BP_CODE_STRING = 17;
    private static final int DATA_TYPE_8BP_CODE_STRING = 18;
    private static final int DATA_TYPE_END_LINE = 240;
    private static final int OBJECT_CODING_PIXELS = 0;
    private static final int OBJECT_CODING_STRING = 1;
    private static final int PAGE_STATE_NORMAL = 0;
    private static final int REGION_DEPTH_4_BIT = 2;
    private static final int REGION_DEPTH_8_BIT = 3;
    private static final int SEGMENT_TYPE_CLUT_DEFINITION = 18;
    private static final int SEGMENT_TYPE_DISPLAY_DEFINITION = 20;
    private static final int SEGMENT_TYPE_OBJECT_DATA = 19;
    private static final int SEGMENT_TYPE_PAGE_COMPOSITION = 16;
    private static final int SEGMENT_TYPE_REGION_COMPOSITION = 17;
    private static final String TAG = "DvbParser";
    private static final byte[] defaultMap2To4 = {0, 7, 8, 15};
    private static final byte[] defaultMap2To8 = {0, 119, -120, -1};
    private static final byte[] defaultMap4To8 = {0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1};
    private Bitmap bitmap;
    private final Canvas canvas = new Canvas();
    private final ClutDefinition defaultClutDefinition = new ClutDefinition(0, generateDefault2BitClutEntries(), generateDefault4BitClutEntries(), generateDefault8BitClutEntries());
    private final DisplayDefinition defaultDisplayDefinition = new DisplayDefinition(AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, 575, 0, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, 0, 575);
    private final Paint defaultPaint;
    private final Paint fillRegionPaint;
    private final SubtitleService subtitleService;

    private static int getColor(int i, int i2, int i3, int i4) {
        return (i << 24) | (i2 << 16) | (i3 << 8) | i4;
    }

    public int getCueReplacementBehavior() {
        return 2;
    }

    public DvbParser(List<byte[]> list) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(list.get(0));
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        Paint paint = new Paint();
        this.defaultPaint = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        paint.setPathEffect((PathEffect) null);
        Paint paint2 = new Paint();
        this.fillRegionPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        paint2.setPathEffect((PathEffect) null);
        this.subtitleService = new SubtitleService(readUnsignedShort, readUnsignedShort2);
    }

    public void reset() {
        this.subtitleService.reset();
    }

    public void parse(byte[] bArr, int i, int i2, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr, i2 + i);
        parsableBitArray.setPosition(i);
        consumer.accept(parse(parsableBitArray));
    }

    private CuesWithTiming parse(ParsableBitArray parsableBitArray) {
        DisplayDefinition displayDefinition;
        int i;
        int i2;
        SparseArray<RegionObject> sparseArray;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        while (parsableBitArray.bitsLeft() >= 48 && parsableBitArray2.readBits(8) == 15) {
            parseSubtitlingSegment(parsableBitArray2, this.subtitleService);
        }
        PageComposition pageComposition = this.subtitleService.pageComposition;
        if (pageComposition == null) {
            return new CuesWithTiming(ImmutableList.of(), C.TIME_UNSET, C.TIME_UNSET);
        }
        if (this.subtitleService.displayDefinition != null) {
            displayDefinition = this.subtitleService.displayDefinition;
        } else {
            displayDefinition = this.defaultDisplayDefinition;
        }
        if (!(this.bitmap != null && displayDefinition.width + 1 == this.bitmap.getWidth() && displayDefinition.height + 1 == this.bitmap.getHeight())) {
            Bitmap createBitmap = Bitmap.createBitmap(displayDefinition.width + 1, displayDefinition.height + 1, Bitmap.Config.ARGB_8888);
            this.bitmap = createBitmap;
            this.canvas.setBitmap(createBitmap);
        }
        ArrayList arrayList = new ArrayList();
        SparseArray<PageRegion> sparseArray2 = pageComposition.regions;
        for (int i3 = 0; i3 < sparseArray2.size(); i3++) {
            this.canvas.save();
            PageRegion valueAt = sparseArray2.valueAt(i3);
            RegionComposition regionComposition = this.subtitleService.regions.get(sparseArray2.keyAt(i3));
            int i4 = valueAt.horizontalAddress + displayDefinition.horizontalPositionMinimum;
            int i5 = valueAt.verticalAddress + displayDefinition.verticalPositionMinimum;
            this.canvas.clipRect(i4, i5, Math.min(regionComposition.width + i4, displayDefinition.horizontalPositionMaximum), Math.min(regionComposition.height + i5, displayDefinition.verticalPositionMaximum));
            ClutDefinition clutDefinition = this.subtitleService.cluts.get(regionComposition.clutId);
            if (clutDefinition == null && (clutDefinition = this.subtitleService.ancillaryCluts.get(regionComposition.clutId)) == null) {
                clutDefinition = this.defaultClutDefinition;
            }
            SparseArray<RegionObject> sparseArray3 = regionComposition.regionObjects;
            int i6 = 0;
            while (i6 < sparseArray3.size()) {
                int keyAt = sparseArray3.keyAt(i6);
                RegionObject valueAt2 = sparseArray3.valueAt(i6);
                ObjectData objectData = this.subtitleService.objects.get(keyAt);
                ObjectData objectData2 = objectData == null ? this.subtitleService.ancillaryObjects.get(keyAt) : objectData;
                if (objectData2 != null) {
                    Paint paint = objectData2.nonModifyingColorFlag ? null : this.defaultPaint;
                    i2 = i6;
                    sparseArray = sparseArray3;
                    paintPixelDataSubBlocks(objectData2, clutDefinition, regionComposition.depth, valueAt2.horizontalPosition + i4, i5 + valueAt2.verticalPosition, paint, this.canvas);
                } else {
                    i2 = i6;
                    sparseArray = sparseArray3;
                }
                i6 = i2 + 1;
                sparseArray3 = sparseArray;
            }
            if (regionComposition.fillFlag) {
                if (regionComposition.depth == 3) {
                    i = clutDefinition.clutEntries8Bit[regionComposition.pixelCode8Bit];
                } else if (regionComposition.depth == 2) {
                    i = clutDefinition.clutEntries4Bit[regionComposition.pixelCode4Bit];
                } else {
                    i = clutDefinition.clutEntries2Bit[regionComposition.pixelCode2Bit];
                }
                this.fillRegionPaint.setColor(i);
                this.canvas.drawRect((float) i4, (float) i5, (float) (regionComposition.width + i4), (float) (regionComposition.height + i5), this.fillRegionPaint);
            }
            arrayList.add(new Cue.Builder().setBitmap(Bitmap.createBitmap(this.bitmap, i4, i5, regionComposition.width, regionComposition.height)).setPosition(((float) i4) / ((float) displayDefinition.width)).setPositionAnchor(0).setLine(((float) i5) / ((float) displayDefinition.height), 0).setLineAnchor(0).setSize(((float) regionComposition.width) / ((float) displayDefinition.width)).setBitmapHeight(((float) regionComposition.height) / ((float) displayDefinition.height)).build());
            this.canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            this.canvas.restore();
        }
        return new CuesWithTiming(arrayList, C.TIME_UNSET, C.TIME_UNSET);
    }

    private static void parseSubtitlingSegment(ParsableBitArray parsableBitArray, SubtitleService subtitleService2) {
        RegionComposition regionComposition;
        int readBits = parsableBitArray.readBits(8);
        int readBits2 = parsableBitArray.readBits(16);
        int readBits3 = parsableBitArray.readBits(16);
        int bytePosition = parsableBitArray.getBytePosition() + readBits3;
        if (readBits3 * 8 > parsableBitArray.bitsLeft()) {
            Log.w(TAG, "Data field length exceeds limit");
            parsableBitArray.skipBits(parsableBitArray.bitsLeft());
            return;
        }
        switch (readBits) {
            case 16:
                if (readBits2 == subtitleService2.subtitlePageId) {
                    PageComposition pageComposition = subtitleService2.pageComposition;
                    PageComposition parsePageComposition = parsePageComposition(parsableBitArray, readBits3);
                    if (parsePageComposition.state == 0) {
                        if (!(pageComposition == null || pageComposition.version == parsePageComposition.version)) {
                            subtitleService2.pageComposition = parsePageComposition;
                            break;
                        }
                    } else {
                        subtitleService2.pageComposition = parsePageComposition;
                        subtitleService2.regions.clear();
                        subtitleService2.cluts.clear();
                        subtitleService2.objects.clear();
                        break;
                    }
                }
                break;
            case 17:
                PageComposition pageComposition2 = subtitleService2.pageComposition;
                if (readBits2 == subtitleService2.subtitlePageId && pageComposition2 != null) {
                    RegionComposition parseRegionComposition = parseRegionComposition(parsableBitArray, readBits3);
                    if (pageComposition2.state == 0 && (regionComposition = subtitleService2.regions.get(parseRegionComposition.id)) != null) {
                        parseRegionComposition.mergeFrom(regionComposition);
                    }
                    subtitleService2.regions.put(parseRegionComposition.id, parseRegionComposition);
                    break;
                }
            case 18:
                if (readBits2 != subtitleService2.subtitlePageId) {
                    if (readBits2 == subtitleService2.ancillaryPageId) {
                        ClutDefinition parseClutDefinition = parseClutDefinition(parsableBitArray, readBits3);
                        subtitleService2.ancillaryCluts.put(parseClutDefinition.id, parseClutDefinition);
                        break;
                    }
                } else {
                    ClutDefinition parseClutDefinition2 = parseClutDefinition(parsableBitArray, readBits3);
                    subtitleService2.cluts.put(parseClutDefinition2.id, parseClutDefinition2);
                    break;
                }
                break;
            case 19:
                if (readBits2 != subtitleService2.subtitlePageId) {
                    if (readBits2 == subtitleService2.ancillaryPageId) {
                        ObjectData parseObjectData = parseObjectData(parsableBitArray);
                        subtitleService2.ancillaryObjects.put(parseObjectData.id, parseObjectData);
                        break;
                    }
                } else {
                    ObjectData parseObjectData2 = parseObjectData(parsableBitArray);
                    subtitleService2.objects.put(parseObjectData2.id, parseObjectData2);
                    break;
                }
                break;
            case 20:
                if (readBits2 == subtitleService2.subtitlePageId) {
                    subtitleService2.displayDefinition = parseDisplayDefinition(parsableBitArray);
                    break;
                }
                break;
        }
        parsableBitArray.skipBytes(bytePosition - parsableBitArray.getBytePosition());
    }

    private static DisplayDefinition parseDisplayDefinition(ParsableBitArray parsableBitArray) {
        int i;
        int i2;
        int i3;
        int i4;
        parsableBitArray.skipBits(4);
        boolean readBit = parsableBitArray.readBit();
        parsableBitArray.skipBits(3);
        int readBits = parsableBitArray.readBits(16);
        int readBits2 = parsableBitArray.readBits(16);
        if (readBit) {
            int readBits3 = parsableBitArray.readBits(16);
            int readBits4 = parsableBitArray.readBits(16);
            int readBits5 = parsableBitArray.readBits(16);
            i = parsableBitArray.readBits(16);
            i3 = readBits4;
            i2 = readBits5;
            i4 = readBits3;
        } else {
            i4 = 0;
            i2 = 0;
            i3 = readBits;
            i = readBits2;
        }
        return new DisplayDefinition(readBits, readBits2, i4, i3, i2, i);
    }

    private static PageComposition parsePageComposition(ParsableBitArray parsableBitArray, int i) {
        int readBits = parsableBitArray.readBits(8);
        int readBits2 = parsableBitArray.readBits(4);
        int readBits3 = parsableBitArray.readBits(2);
        parsableBitArray.skipBits(2);
        int i2 = i - 2;
        SparseArray sparseArray = new SparseArray();
        while (i2 > 0) {
            int readBits4 = parsableBitArray.readBits(8);
            parsableBitArray.skipBits(8);
            i2 -= 6;
            sparseArray.put(readBits4, new PageRegion(parsableBitArray.readBits(16), parsableBitArray.readBits(16)));
        }
        return new PageComposition(readBits, readBits2, readBits3, sparseArray);
    }

    private static RegionComposition parseRegionComposition(ParsableBitArray parsableBitArray, int i) {
        int i2;
        int i3;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int readBits = parsableBitArray2.readBits(8);
        parsableBitArray2.skipBits(4);
        boolean readBit = parsableBitArray.readBit();
        parsableBitArray2.skipBits(3);
        int i4 = 16;
        int readBits2 = parsableBitArray2.readBits(16);
        int readBits3 = parsableBitArray2.readBits(16);
        int readBits4 = parsableBitArray2.readBits(3);
        int readBits5 = parsableBitArray2.readBits(3);
        int i5 = 2;
        parsableBitArray2.skipBits(2);
        int readBits6 = parsableBitArray2.readBits(8);
        int readBits7 = parsableBitArray2.readBits(8);
        int readBits8 = parsableBitArray2.readBits(4);
        int readBits9 = parsableBitArray2.readBits(2);
        parsableBitArray2.skipBits(2);
        int i6 = i - 10;
        SparseArray sparseArray = new SparseArray();
        while (i6 > 0) {
            int readBits10 = parsableBitArray2.readBits(i4);
            int readBits11 = parsableBitArray2.readBits(i5);
            int readBits12 = parsableBitArray2.readBits(i5);
            int readBits13 = parsableBitArray2.readBits(12);
            int i7 = readBits9;
            parsableBitArray2.skipBits(4);
            int readBits14 = parsableBitArray2.readBits(12);
            i6 -= 6;
            if (readBits11 == 1 || readBits11 == 2) {
                i6 -= 2;
                i3 = parsableBitArray2.readBits(8);
                i2 = parsableBitArray2.readBits(8);
            } else {
                i3 = 0;
                i2 = 0;
            }
            sparseArray.put(readBits10, new RegionObject(readBits11, readBits12, readBits13, readBits14, i3, i2));
            readBits9 = i7;
            i5 = 2;
            i4 = 16;
        }
        return new RegionComposition(readBits, readBit, readBits2, readBits3, readBits4, readBits5, readBits6, readBits7, readBits8, readBits9, sparseArray);
    }

    private static ClutDefinition parseClutDefinition(ParsableBitArray parsableBitArray, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int i7 = 8;
        int readBits = parsableBitArray2.readBits(8);
        parsableBitArray2.skipBits(8);
        int i8 = 2;
        int i9 = i - 2;
        int[] generateDefault2BitClutEntries = generateDefault2BitClutEntries();
        int[] generateDefault4BitClutEntries = generateDefault4BitClutEntries();
        int[] generateDefault8BitClutEntries = generateDefault8BitClutEntries();
        while (i9 > 0) {
            int readBits2 = parsableBitArray2.readBits(i7);
            int readBits3 = parsableBitArray2.readBits(i7);
            int i10 = i9 - 2;
            int[] iArr = (readBits3 & 128) != 0 ? generateDefault2BitClutEntries : (readBits3 & 64) != 0 ? generateDefault4BitClutEntries : generateDefault8BitClutEntries;
            if ((readBits3 & 1) != 0) {
                i5 = parsableBitArray2.readBits(i7);
                i4 = parsableBitArray2.readBits(i7);
                i3 = parsableBitArray2.readBits(i7);
                i2 = parsableBitArray2.readBits(i7);
                i6 = i10 - 4;
            } else {
                i3 = parsableBitArray2.readBits(4) << 4;
                i6 = i10 - 2;
                int readBits4 = parsableBitArray2.readBits(4) << 4;
                i2 = parsableBitArray2.readBits(i8) << 6;
                i5 = parsableBitArray2.readBits(6) << i8;
                i4 = readBits4;
            }
            if (i5 == 0) {
                i2 = 255;
                i4 = 0;
                i3 = 0;
            }
            double d = (double) i5;
            double d2 = (double) (i4 - 128);
            double d3 = (double) (i3 - 128);
            iArr[readBits2] = getColor((byte) (255 - (i2 & 255)), Util.constrainValue((int) (d + (1.402d * d2)), 0, 255), Util.constrainValue((int) ((d - (0.34414d * d3)) - (d2 * 0.71414d)), 0, 255), Util.constrainValue((int) (d + (d3 * 1.772d)), 0, 255));
            i9 = i6;
            readBits = readBits;
            i7 = 8;
            i8 = 2;
        }
        return new ClutDefinition(readBits, generateDefault2BitClutEntries, generateDefault4BitClutEntries, generateDefault8BitClutEntries);
    }

    private static ObjectData parseObjectData(ParsableBitArray parsableBitArray) {
        int readBits = parsableBitArray.readBits(16);
        parsableBitArray.skipBits(4);
        int readBits2 = parsableBitArray.readBits(2);
        boolean readBit = parsableBitArray.readBit();
        parsableBitArray.skipBits(1);
        byte[] bArr = Util.EMPTY_BYTE_ARRAY;
        byte[] bArr2 = Util.EMPTY_BYTE_ARRAY;
        if (readBits2 == 1) {
            parsableBitArray.skipBits(parsableBitArray.readBits(8) * 16);
        } else if (readBits2 == 0) {
            int readBits3 = parsableBitArray.readBits(16);
            int readBits4 = parsableBitArray.readBits(16);
            if (readBits3 > 0) {
                bArr = new byte[readBits3];
                parsableBitArray.readBytes(bArr, 0, readBits3);
            }
            if (readBits4 > 0) {
                bArr2 = new byte[readBits4];
                parsableBitArray.readBytes(bArr2, 0, readBits4);
            } else {
                bArr2 = bArr;
            }
        }
        return new ObjectData(readBits, readBit, bArr, bArr2);
    }

    private static int[] generateDefault2BitClutEntries() {
        return new int[]{0, -1, ViewCompat.MEASURED_STATE_MASK, -8421505};
    }

    private static int[] generateDefault4BitClutEntries() {
        int[] iArr = new int[16];
        iArr[0] = 0;
        for (int i = 1; i < 16; i++) {
            if (i < 8) {
                iArr[i] = getColor(255, (i & 1) != 0 ? 255 : 0, (i & 2) != 0 ? 255 : 0, (i & 4) != 0 ? 255 : 0);
            } else {
                int i2 = 127;
                int i3 = (i & 1) != 0 ? 127 : 0;
                int i4 = (i & 2) != 0 ? 127 : 0;
                if ((i & 4) == 0) {
                    i2 = 0;
                }
                iArr[i] = getColor(255, i3, i4, i2);
            }
        }
        return iArr;
    }

    private static int[] generateDefault8BitClutEntries() {
        int[] iArr = new int[256];
        iArr[0] = 0;
        for (int i = 0; i < 256; i++) {
            int i2 = 255;
            if (i < 8) {
                int i3 = (i & 1) != 0 ? 255 : 0;
                int i4 = (i & 2) != 0 ? 255 : 0;
                if ((i & 4) == 0) {
                    i2 = 0;
                }
                iArr[i] = getColor(63, i3, i4, i2);
            } else {
                int i5 = i & TsExtractor.TS_STREAM_TYPE_DTS_HD;
                int i6 = 170;
                int i7 = 85;
                if (i5 == 0) {
                    int i8 = ((i & 1) != 0 ? 85 : 0) + ((i & 16) != 0 ? 170 : 0);
                    int i9 = ((i & 2) != 0 ? 85 : 0) + ((i & 32) != 0 ? 170 : 0);
                    if ((i & 4) == 0) {
                        i7 = 0;
                    }
                    if ((i & 64) == 0) {
                        i6 = 0;
                    }
                    iArr[i] = getColor(255, i8, i9, i7 + i6);
                } else if (i5 != 8) {
                    int i10 = 43;
                    if (i5 == 128) {
                        int i11 = ((i & 1) != 0 ? 43 : 0) + 127 + ((i & 16) != 0 ? 85 : 0);
                        int i12 = ((i & 2) != 0 ? 43 : 0) + 127 + ((i & 32) != 0 ? 85 : 0);
                        if ((i & 4) == 0) {
                            i10 = 0;
                        }
                        int i13 = i10 + 127;
                        if ((i & 64) == 0) {
                            i7 = 0;
                        }
                        iArr[i] = getColor(255, i11, i12, i13 + i7);
                    } else if (i5 == 136) {
                        int i14 = ((i & 1) != 0 ? 43 : 0) + ((i & 16) != 0 ? 85 : 0);
                        int i15 = ((i & 2) != 0 ? 43 : 0) + ((i & 32) != 0 ? 85 : 0);
                        if ((i & 4) == 0) {
                            i10 = 0;
                        }
                        if ((i & 64) == 0) {
                            i7 = 0;
                        }
                        iArr[i] = getColor(255, i14, i15, i10 + i7);
                    }
                } else {
                    int i16 = ((i & 1) != 0 ? 85 : 0) + ((i & 16) != 0 ? 170 : 0);
                    int i17 = ((i & 2) != 0 ? 85 : 0) + ((i & 32) != 0 ? 170 : 0);
                    if ((i & 4) == 0) {
                        i7 = 0;
                    }
                    if ((i & 64) == 0) {
                        i6 = 0;
                    }
                    iArr[i] = getColor(127, i16, i17, i7 + i6);
                }
            }
        }
        return iArr;
    }

    private static void paintPixelDataSubBlocks(ObjectData objectData, ClutDefinition clutDefinition, int i, int i2, int i3, Paint paint, Canvas canvas2) {
        int[] iArr;
        if (i == 3) {
            iArr = clutDefinition.clutEntries8Bit;
        } else if (i == 2) {
            iArr = clutDefinition.clutEntries4Bit;
        } else {
            iArr = clutDefinition.clutEntries2Bit;
        }
        int[] iArr2 = iArr;
        int i4 = i;
        int i5 = i2;
        Paint paint2 = paint;
        Canvas canvas3 = canvas2;
        paintPixelDataSubBlock(objectData.topFieldData, iArr2, i4, i5, i3, paint2, canvas3);
        paintPixelDataSubBlock(objectData.bottomFieldData, iArr2, i4, i5, i3 + 1, paint2, canvas3);
    }

    private static void paintPixelDataSubBlock(byte[] bArr, int[] iArr, int i, int i2, int i3, Paint paint, Canvas canvas2) {
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArr4;
        int i4 = i;
        byte[] bArr5 = bArr;
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        int i5 = i2;
        int i6 = i3;
        byte[] bArr6 = null;
        byte[] bArr7 = null;
        byte[] bArr8 = null;
        while (parsableBitArray.bitsLeft() != 0) {
            int readBits = parsableBitArray.readBits(8);
            if (readBits != 240) {
                switch (readBits) {
                    case 16:
                        if (i4 != 3) {
                            if (i4 != 2) {
                                bArr2 = null;
                                i5 = paint2BitPixelCodeString(parsableBitArray, iArr, bArr2, i5, i6, paint, canvas2);
                                parsableBitArray.byteAlign();
                                break;
                            } else {
                                bArr3 = bArr8 == null ? defaultMap2To4 : bArr8;
                            }
                        } else {
                            bArr3 = bArr6 == null ? defaultMap2To8 : bArr6;
                        }
                        bArr2 = bArr3;
                        i5 = paint2BitPixelCodeString(parsableBitArray, iArr, bArr2, i5, i6, paint, canvas2);
                        parsableBitArray.byteAlign();
                    case 17:
                        if (i4 == 3) {
                            bArr4 = bArr7 == null ? defaultMap4To8 : bArr7;
                        } else {
                            bArr4 = null;
                        }
                        i5 = paint4BitPixelCodeString(parsableBitArray, iArr, bArr4, i5, i6, paint, canvas2);
                        parsableBitArray.byteAlign();
                        break;
                    case 18:
                        i5 = paint8BitPixelCodeString(parsableBitArray, iArr, (byte[]) null, i5, i6, paint, canvas2);
                        break;
                    default:
                        switch (readBits) {
                            case 32:
                                bArr8 = buildClutMapTable(4, 4, parsableBitArray);
                                break;
                            case 33:
                                bArr6 = buildClutMapTable(4, 8, parsableBitArray);
                                break;
                            case 34:
                                bArr7 = buildClutMapTable(16, 8, parsableBitArray);
                                break;
                        }
                }
            } else {
                i6 += 2;
                i5 = i2;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v20, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int paint2BitPixelCodeString(androidx.media3.common.util.ParsableBitArray r13, int[] r14, byte[] r15, int r16, int r17, android.graphics.Paint r18, android.graphics.Canvas r19) {
        /*
            r0 = r13
            r1 = r17
            r8 = r18
            r9 = 0
            r10 = r16
            r2 = r9
        L_0x0009:
            r3 = 2
            int r4 = r13.readBits(r3)
            r5 = 1
            if (r4 == 0) goto L_0x0014
            r11 = r2
            r12 = r5
            goto L_0x0060
        L_0x0014:
            boolean r4 = r13.readBit()
            r6 = 3
            if (r4 == 0) goto L_0x0028
            int r4 = r13.readBits(r6)
            int r4 = r4 + r6
            int r3 = r13.readBits(r3)
        L_0x0024:
            r11 = r2
            r12 = r4
            r4 = r3
            goto L_0x0060
        L_0x0028:
            boolean r4 = r13.readBit()
            if (r4 == 0) goto L_0x0032
            r11 = r2
            r12 = r5
        L_0x0030:
            r4 = r9
            goto L_0x0060
        L_0x0032:
            int r4 = r13.readBits(r3)
            if (r4 == 0) goto L_0x005e
            if (r4 == r5) goto L_0x005b
            if (r4 == r3) goto L_0x004f
            if (r4 == r6) goto L_0x0042
            r11 = r2
        L_0x003f:
            r4 = r9
            r12 = r4
            goto L_0x0060
        L_0x0042:
            r4 = 8
            int r4 = r13.readBits(r4)
            int r4 = r4 + 29
            int r3 = r13.readBits(r3)
            goto L_0x0024
        L_0x004f:
            r4 = 4
            int r4 = r13.readBits(r4)
            int r4 = r4 + 12
            int r3 = r13.readBits(r3)
            goto L_0x0024
        L_0x005b:
            r11 = r2
            r12 = r3
            goto L_0x0030
        L_0x005e:
            r11 = r5
            goto L_0x003f
        L_0x0060:
            if (r12 == 0) goto L_0x007e
            if (r8 == 0) goto L_0x007e
            if (r15 == 0) goto L_0x0068
            byte r4 = r15[r4]
        L_0x0068:
            r2 = r14[r4]
            r8.setColor(r2)
            float r3 = (float) r10
            float r4 = (float) r1
            int r2 = r10 + r12
            float r6 = (float) r2
            int r2 = r1 + 1
            float r7 = (float) r2
            r2 = r19
            r5 = r6
            r6 = r7
            r7 = r18
            r2.drawRect(r3, r4, r5, r6, r7)
        L_0x007e:
            int r10 = r10 + r12
            if (r11 == 0) goto L_0x0082
            return r10
        L_0x0082:
            r2 = r11
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.dvb.DvbParser.paint2BitPixelCodeString(androidx.media3.common.util.ParsableBitArray, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v19, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int paint4BitPixelCodeString(androidx.media3.common.util.ParsableBitArray r13, int[] r14, byte[] r15, int r16, int r17, android.graphics.Paint r18, android.graphics.Canvas r19) {
        /*
            r0 = r13
            r1 = r17
            r8 = r18
            r9 = 0
            r10 = r16
            r2 = r9
        L_0x0009:
            r3 = 4
            int r4 = r13.readBits(r3)
            r5 = 1
            if (r4 == 0) goto L_0x0015
            r11 = r2
            r12 = r5
            goto L_0x006c
        L_0x0015:
            boolean r4 = r13.readBit()
            r6 = 3
            if (r4 != 0) goto L_0x002a
            int r3 = r13.readBits(r6)
            if (r3 == 0) goto L_0x0028
            int r3 = r3 + 2
            r11 = r2
            r12 = r3
        L_0x0026:
            r4 = r9
            goto L_0x006c
        L_0x0028:
            r11 = r5
            goto L_0x004b
        L_0x002a:
            boolean r4 = r13.readBit()
            r7 = 2
            if (r4 != 0) goto L_0x003e
            int r4 = r13.readBits(r7)
            int r4 = r4 + r3
            int r3 = r13.readBits(r3)
        L_0x003a:
            r11 = r2
            r12 = r4
            r4 = r3
            goto L_0x006c
        L_0x003e:
            int r4 = r13.readBits(r7)
            if (r4 == 0) goto L_0x0069
            if (r4 == r5) goto L_0x0066
            if (r4 == r7) goto L_0x005b
            if (r4 == r6) goto L_0x004e
            r11 = r2
        L_0x004b:
            r4 = r9
            r12 = r4
            goto L_0x006c
        L_0x004e:
            r4 = 8
            int r4 = r13.readBits(r4)
            int r4 = r4 + 25
            int r3 = r13.readBits(r3)
            goto L_0x003a
        L_0x005b:
            int r4 = r13.readBits(r3)
            int r4 = r4 + 9
            int r3 = r13.readBits(r3)
            goto L_0x003a
        L_0x0066:
            r11 = r2
            r12 = r7
            goto L_0x0026
        L_0x0069:
            r11 = r2
            r12 = r5
            goto L_0x0026
        L_0x006c:
            if (r12 == 0) goto L_0x008a
            if (r8 == 0) goto L_0x008a
            if (r15 == 0) goto L_0x0074
            byte r4 = r15[r4]
        L_0x0074:
            r2 = r14[r4]
            r8.setColor(r2)
            float r3 = (float) r10
            float r4 = (float) r1
            int r2 = r10 + r12
            float r6 = (float) r2
            int r2 = r1 + 1
            float r7 = (float) r2
            r2 = r19
            r5 = r6
            r6 = r7
            r7 = r18
            r2.drawRect(r3, r4, r5, r6, r7)
        L_0x008a:
            int r10 = r10 + r12
            if (r11 == 0) goto L_0x008e
            return r10
        L_0x008e:
            r2 = r11
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.dvb.DvbParser.paint4BitPixelCodeString(androidx.media3.common.util.ParsableBitArray, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int paint8BitPixelCodeString(androidx.media3.common.util.ParsableBitArray r13, int[] r14, byte[] r15, int r16, int r17, android.graphics.Paint r18, android.graphics.Canvas r19) {
        /*
            r0 = r13
            r1 = r17
            r8 = r18
            r9 = 0
            r10 = r16
            r2 = r9
        L_0x0009:
            r3 = 8
            int r4 = r13.readBits(r3)
            r5 = 1
            if (r4 == 0) goto L_0x0015
            r11 = r2
            r12 = r5
            goto L_0x0035
        L_0x0015:
            boolean r4 = r13.readBit()
            r6 = 7
            if (r4 != 0) goto L_0x002a
            int r3 = r13.readBits(r6)
            if (r3 == 0) goto L_0x0026
            r11 = r2
            r12 = r3
            r4 = r9
            goto L_0x0035
        L_0x0026:
            r11 = r5
            r4 = r9
            r12 = r4
            goto L_0x0035
        L_0x002a:
            int r4 = r13.readBits(r6)
            int r3 = r13.readBits(r3)
            r11 = r2
            r12 = r4
            r4 = r3
        L_0x0035:
            if (r12 == 0) goto L_0x0053
            if (r8 == 0) goto L_0x0053
            if (r15 == 0) goto L_0x003d
            byte r4 = r15[r4]
        L_0x003d:
            r2 = r14[r4]
            r8.setColor(r2)
            float r3 = (float) r10
            float r4 = (float) r1
            int r2 = r10 + r12
            float r6 = (float) r2
            int r2 = r1 + 1
            float r7 = (float) r2
            r2 = r19
            r5 = r6
            r6 = r7
            r7 = r18
            r2.drawRect(r3, r4, r5, r6, r7)
        L_0x0053:
            int r10 = r10 + r12
            if (r11 == 0) goto L_0x0057
            return r10
        L_0x0057:
            r2 = r11
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.dvb.DvbParser.paint8BitPixelCodeString(androidx.media3.common.util.ParsableBitArray, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    private static byte[] buildClutMapTable(int i, int i2, ParsableBitArray parsableBitArray) {
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) parsableBitArray.readBits(i2);
        }
        return bArr;
    }

    private static final class SubtitleService {
        public final SparseArray<ClutDefinition> ancillaryCluts = new SparseArray<>();
        public final SparseArray<ObjectData> ancillaryObjects = new SparseArray<>();
        public final int ancillaryPageId;
        public final SparseArray<ClutDefinition> cluts = new SparseArray<>();
        public DisplayDefinition displayDefinition;
        public final SparseArray<ObjectData> objects = new SparseArray<>();
        public PageComposition pageComposition;
        public final SparseArray<RegionComposition> regions = new SparseArray<>();
        public final int subtitlePageId;

        public SubtitleService(int i, int i2) {
            this.subtitlePageId = i;
            this.ancillaryPageId = i2;
        }

        public void reset() {
            this.regions.clear();
            this.cluts.clear();
            this.objects.clear();
            this.ancillaryCluts.clear();
            this.ancillaryObjects.clear();
            this.displayDefinition = null;
            this.pageComposition = null;
        }
    }

    private static final class DisplayDefinition {
        public final int height;
        public final int horizontalPositionMaximum;
        public final int horizontalPositionMinimum;
        public final int verticalPositionMaximum;
        public final int verticalPositionMinimum;
        public final int width;

        public DisplayDefinition(int i, int i2, int i3, int i4, int i5, int i6) {
            this.width = i;
            this.height = i2;
            this.horizontalPositionMinimum = i3;
            this.horizontalPositionMaximum = i4;
            this.verticalPositionMinimum = i5;
            this.verticalPositionMaximum = i6;
        }
    }

    private static final class PageComposition {
        public final SparseArray<PageRegion> regions;
        public final int state;
        public final int timeOutSecs;
        public final int version;

        public PageComposition(int i, int i2, int i3, SparseArray<PageRegion> sparseArray) {
            this.timeOutSecs = i;
            this.version = i2;
            this.state = i3;
            this.regions = sparseArray;
        }
    }

    private static final class PageRegion {
        public final int horizontalAddress;
        public final int verticalAddress;

        public PageRegion(int i, int i2) {
            this.horizontalAddress = i;
            this.verticalAddress = i2;
        }
    }

    private static final class RegionComposition {
        public final int clutId;
        public final int depth;
        public final boolean fillFlag;
        public final int height;
        public final int id;
        public final int levelOfCompatibility;
        public final int pixelCode2Bit;
        public final int pixelCode4Bit;
        public final int pixelCode8Bit;
        public final SparseArray<RegionObject> regionObjects;
        public final int width;

        public RegionComposition(int i, boolean z, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, SparseArray<RegionObject> sparseArray) {
            this.id = i;
            this.fillFlag = z;
            this.width = i2;
            this.height = i3;
            this.levelOfCompatibility = i4;
            this.depth = i5;
            this.clutId = i6;
            this.pixelCode8Bit = i7;
            this.pixelCode4Bit = i8;
            this.pixelCode2Bit = i9;
            this.regionObjects = sparseArray;
        }

        public void mergeFrom(RegionComposition regionComposition) {
            SparseArray<RegionObject> sparseArray = regionComposition.regionObjects;
            for (int i = 0; i < sparseArray.size(); i++) {
                this.regionObjects.put(sparseArray.keyAt(i), sparseArray.valueAt(i));
            }
        }
    }

    private static final class RegionObject {
        public final int backgroundPixelCode;
        public final int foregroundPixelCode;
        public final int horizontalPosition;
        public final int provider;
        public final int type;
        public final int verticalPosition;

        public RegionObject(int i, int i2, int i3, int i4, int i5, int i6) {
            this.type = i;
            this.provider = i2;
            this.horizontalPosition = i3;
            this.verticalPosition = i4;
            this.foregroundPixelCode = i5;
            this.backgroundPixelCode = i6;
        }
    }

    private static final class ClutDefinition {
        public final int[] clutEntries2Bit;
        public final int[] clutEntries4Bit;
        public final int[] clutEntries8Bit;
        public final int id;

        public ClutDefinition(int i, int[] iArr, int[] iArr2, int[] iArr3) {
            this.id = i;
            this.clutEntries2Bit = iArr;
            this.clutEntries4Bit = iArr2;
            this.clutEntries8Bit = iArr3;
        }
    }

    private static final class ObjectData {
        public final byte[] bottomFieldData;
        public final int id;
        public final boolean nonModifyingColorFlag;
        public final byte[] topFieldData;

        public ObjectData(int i, boolean z, byte[] bArr, byte[] bArr2) {
            this.id = i;
            this.nonModifyingColorFlag = z;
            this.topFieldData = bArr;
            this.bottomFieldData = bArr2;
        }
    }
}
