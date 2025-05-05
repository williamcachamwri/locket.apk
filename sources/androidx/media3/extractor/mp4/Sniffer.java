package androidx.media3.extractor.mp4;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.Mp4Box;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.SniffFailure;
import java.io.IOException;

public final class Sniffer {
    public static final int BRAND_HEIC = 1751476579;
    public static final int BRAND_QUICKTIME = 1903435808;
    private static final int[] COMPATIBLE_BRANDS = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, Mp4Box.TYPE_avc1, Mp4Box.TYPE_hvc1, Mp4Box.TYPE_hev1, Mp4Box.TYPE_av01, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, BRAND_QUICKTIME, 1297305174, 1684175153, 1769172332, 1885955686};
    private static final int SEARCH_LENGTH = 4096;

    public static SniffFailure sniffFragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, true, false);
    }

    public static SniffFailure sniffUnfragmented(ExtractorInput extractorInput, boolean z) throws IOException {
        return sniffInternal(extractorInput, false, z);
    }

    private static SniffFailure sniffInternal(ExtractorInput extractorInput, boolean z, boolean z2) throws IOException {
        boolean z3;
        int i;
        boolean z4;
        boolean z5;
        int[] iArr;
        ExtractorInput extractorInput2 = extractorInput;
        boolean z6 = z2;
        long length = extractorInput.getLength();
        long j = -1;
        int i2 = (length > -1 ? 1 : (length == -1 ? 0 : -1));
        long j2 = 4096;
        if (i2 != 0 && length <= 4096) {
            j2 = length;
        }
        int i3 = (int) j2;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        boolean z7 = false;
        int i4 = 0;
        boolean z8 = false;
        while (true) {
            if (i4 >= i3) {
                break;
            }
            parsableByteArray.reset(8);
            if (!extractorInput2.peekFully(parsableByteArray.getData(), z7 ? 1 : 0, 8, true)) {
                break;
            }
            long readUnsignedInt = parsableByteArray.readUnsignedInt();
            int readInt = parsableByteArray.readInt();
            if (readUnsignedInt == 1) {
                extractorInput2.peekFully(parsableByteArray.getData(), 8, 8);
                i = 16;
                parsableByteArray.setLimit(16);
                readUnsignedInt = parsableByteArray.readLong();
            } else {
                if (readUnsignedInt == 0) {
                    long length2 = extractorInput.getLength();
                    if (length2 != j) {
                        readUnsignedInt = (length2 - extractorInput.getPeekPosition()) + ((long) 8);
                    }
                }
                i = 8;
            }
            long j3 = readUnsignedInt;
            long j4 = (long) i;
            if (j3 < j4) {
                return new AtomSizeTooSmallSniffFailure(readInt, j3, i);
            }
            i4 += i;
            if (readInt == 1836019574) {
                i3 += (int) j3;
                if (i2 != 0 && ((long) i3) > length) {
                    i3 = (int) length;
                }
            } else if (readInt == 1836019558 || readInt == 1836475768) {
                z3 = true;
            } else {
                long j5 = length;
                if (readInt == 1835295092) {
                    z8 = true;
                }
                int i5 = i4;
                if ((((long) i4) + j3) - j4 >= ((long) i3)) {
                    z3 = false;
                    break;
                }
                int i6 = (int) (j3 - j4);
                i4 = i5 + i6;
                if (readInt != 1718909296) {
                    z4 = false;
                    if (i6 != 0) {
                        extractorInput2.advancePeekPosition(i6);
                    }
                } else if (i6 < 8) {
                    return new AtomSizeTooSmallSniffFailure(readInt, (long) i6, 8);
                } else {
                    parsableByteArray.reset(i6);
                    z4 = false;
                    extractorInput2.peekFully(parsableByteArray.getData(), 0, i6);
                    int readInt2 = parsableByteArray.readInt();
                    if (isCompatibleBrand(readInt2, z6)) {
                        z8 = true;
                    }
                    parsableByteArray.skipBytes(4);
                    int bytesLeft = parsableByteArray.bytesLeft() / 4;
                    if (!z8 && bytesLeft > 0) {
                        iArr = new int[bytesLeft];
                        int i7 = 0;
                        while (true) {
                            if (i7 >= bytesLeft) {
                                z5 = z8;
                                break;
                            }
                            int readInt3 = parsableByteArray.readInt();
                            iArr[i7] = readInt3;
                            if (isCompatibleBrand(readInt3, z6)) {
                                z5 = true;
                                break;
                            }
                            i7++;
                        }
                    } else {
                        z5 = z8;
                        iArr = null;
                    }
                    if (!z5) {
                        return new UnsupportedBrandsSniffFailure(readInt2, iArr);
                    }
                    z8 = z5;
                }
                z7 = z4;
                length = j5;
            }
            j = -1;
        }
        z3 = z7;
        if (!z8) {
            return NoDeclaredBrandSniffFailure.INSTANCE;
        }
        if (z == z3) {
            return null;
        }
        if (z3) {
            return IncorrectFragmentationSniffFailure.FILE_FRAGMENTED;
        }
        return IncorrectFragmentationSniffFailure.FILE_NOT_FRAGMENTED;
    }

    private static boolean isCompatibleBrand(int i, boolean z) {
        if ((i >>> 8) == 3368816) {
            return true;
        }
        if (i == 1751476579 && z) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private Sniffer() {
    }
}
