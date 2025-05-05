package androidx.media3.extractor.mp4;

import android.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.Mp4LocationData;
import androidx.media3.container.Mp4TimestampData;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.Ac3Util;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.AvcConfig;
import androidx.media3.extractor.DolbyVisionConfig;
import androidx.media3.extractor.ExtractorUtil;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.HevcConfig;
import androidx.media3.extractor.OpusUtil;
import androidx.media3.extractor.VorbisUtil;
import androidx.media3.extractor.mp4.FixedSampleSizeRechunker;
import androidx.media3.extractor.ts.PsExtractor;
import com.facebook.imagepipeline.common.RotationOptions;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class BoxParser {
    private static final int MAX_GAPLESS_TRIM_SIZE_SAMPLES = 4;
    private static final String TAG = "BoxParsers";
    private static final int TYPE_clcp = 1668047728;
    private static final int TYPE_mdta = 1835299937;
    private static final int TYPE_meta = 1835365473;
    private static final int TYPE_nclc = 1852009571;
    private static final int TYPE_nclx = 1852009592;
    private static final int TYPE_sbtl = 1935832172;
    private static final int TYPE_soun = 1936684398;
    private static final int TYPE_subt = 1937072756;
    private static final int TYPE_text = 1952807028;
    private static final int TYPE_vide = 1986618469;
    private static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    private interface SampleSizeBox {
        int getFixedSampleSize();

        int getSampleCount();

        int readNextSampleSize();
    }

    private static int getTrackTypeForHdlr(int i) {
        if (i == TYPE_soun) {
            return 1;
        }
        if (i == TYPE_vide) {
            return 2;
        }
        if (i == TYPE_text || i == TYPE_sbtl || i == TYPE_subt || i == TYPE_clcp) {
            return 3;
        }
        return i == 1835365473 ? 5 : -1;
    }

    public static int parseFullBoxFlags(int i) {
        return i & ViewCompat.MEASURED_SIZE_MASK;
    }

    public static int parseFullBoxVersion(int i) {
        return (i >> 24) & 255;
    }

    public static List<TrackSampleTable> parseTraks(Mp4Box.ContainerBox containerBox, GaplessInfoHolder gaplessInfoHolder, long j, DrmInitData drmInitData, boolean z, boolean z2, Function<Track, Track> function) throws ParserException {
        Mp4Box.ContainerBox containerBox2 = containerBox;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < containerBox2.containerChildren.size(); i++) {
            Mp4Box.ContainerBox containerBox3 = containerBox2.containerChildren.get(i);
            if (containerBox3.type != 1953653099) {
                GaplessInfoHolder gaplessInfoHolder2 = gaplessInfoHolder;
                Function<Track, Track> function2 = function;
            } else {
                Track apply = function.apply(parseTrak(containerBox3, (Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(Mp4Box.TYPE_mvhd)), j, drmInitData, z, z2));
                if (apply == null) {
                    GaplessInfoHolder gaplessInfoHolder3 = gaplessInfoHolder;
                } else {
                    GaplessInfoHolder gaplessInfoHolder4 = gaplessInfoHolder;
                    arrayList.add(parseStbl(apply, (Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox3.getContainerBoxOfType(Mp4Box.TYPE_mdia))).getContainerBoxOfType(Mp4Box.TYPE_minf))).getContainerBoxOfType(Mp4Box.TYPE_stbl)), gaplessInfoHolder));
                }
            }
        }
        return arrayList;
    }

    public static Metadata parseUdta(Mp4Box.LeafBox leafBox) {
        ParsableByteArray parsableByteArray = leafBox.data;
        parsableByteArray.setPosition(8);
        Metadata metadata = new Metadata(new Metadata.Entry[0]);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1835365473) {
                parsableByteArray.setPosition(position);
                metadata = metadata.copyWithAppendedEntriesFrom(parseUdtaMeta(parsableByteArray, position + readInt));
            } else if (readInt2 == 1936553057) {
                parsableByteArray.setPosition(position);
                metadata = metadata.copyWithAppendedEntriesFrom(SmtaAtomUtil.parseSmta(parsableByteArray, position + readInt));
            } else if (readInt2 == -1451722374) {
                metadata = metadata.copyWithAppendedEntriesFrom(parseXyz(parsableByteArray));
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return metadata;
    }

    public static Mp4TimestampData parseMvhd(ParsableByteArray parsableByteArray) {
        long j;
        long j2;
        parsableByteArray.setPosition(8);
        if (parseFullBoxVersion(parsableByteArray.readInt()) == 0) {
            j2 = parsableByteArray.readUnsignedInt();
            j = parsableByteArray.readUnsignedInt();
        } else {
            j2 = parsableByteArray.readLong();
            j = parsableByteArray.readLong();
        }
        return new Mp4TimestampData(j2, j, parsableByteArray.readUnsignedInt());
    }

    public static Metadata parseMdtaFromMeta(Mp4Box.ContainerBox containerBox) {
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_hdlr);
        Mp4Box.LeafBox leafBoxOfType2 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_keys);
        Mp4Box.LeafBox leafBoxOfType3 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_ilst);
        if (leafBoxOfType == null || leafBoxOfType2 == null || leafBoxOfType3 == null || parseHdlr(leafBoxOfType.data) != TYPE_mdta) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafBoxOfType2.data;
        parsableByteArray.setPosition(12);
        int readInt = parsableByteArray.readInt();
        String[] strArr = new String[readInt];
        for (int i = 0; i < readInt; i++) {
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            strArr[i] = parsableByteArray.readString(readInt2 - 8);
        }
        ParsableByteArray parsableByteArray2 = leafBoxOfType3.data;
        parsableByteArray2.setPosition(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray2.bytesLeft() > 8) {
            int position = parsableByteArray2.getPosition();
            int readInt3 = parsableByteArray2.readInt();
            int readInt4 = parsableByteArray2.readInt() - 1;
            if (readInt4 < 0 || readInt4 >= readInt) {
                Log.w(TAG, "Skipped metadata with unknown key index: " + readInt4);
            } else {
                MdtaMetadataEntry parseMdtaMetadataEntryFromIlst = MetadataUtil.parseMdtaMetadataEntryFromIlst(parsableByteArray2, position + readInt3, strArr[readInt4]);
                if (parseMdtaMetadataEntryFromIlst != null) {
                    arrayList.add(parseMdtaMetadataEntryFromIlst);
                }
            }
            parsableByteArray2.setPosition(position + readInt3);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    public static void maybeSkipRemainingMetaBoxHeaderBytes(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() != 1751411826) {
            position += 4;
        }
        parsableByteArray.setPosition(position);
    }

    public static Track parseTrak(Mp4Box.ContainerBox containerBox, Mp4Box.LeafBox leafBox, long j, DrmInitData drmInitData, boolean z, boolean z2) throws ParserException {
        long j2;
        Mp4Box.LeafBox leafBox2;
        long[] jArr;
        long[] jArr2;
        Mp4Box.ContainerBox containerBoxOfType;
        Pair<long[], long[]> parseEdts;
        Mp4Box.ContainerBox containerBox2 = containerBox;
        Mp4Box.ContainerBox containerBox3 = (Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox2.getContainerBoxOfType(Mp4Box.TYPE_mdia));
        int trackTypeForHdlr = getTrackTypeForHdlr(parseHdlr(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox3.getLeafBoxOfType(Mp4Box.TYPE_hdlr))).data));
        if (trackTypeForHdlr == -1) {
            return null;
        }
        TkhdData parseTkhd = parseTkhd(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox2.getLeafBoxOfType(Mp4Box.TYPE_tkhd))).data);
        long j3 = C.TIME_UNSET;
        if (j == C.TIME_UNSET) {
            leafBox2 = leafBox;
            j2 = parseTkhd.duration;
        } else {
            leafBox2 = leafBox;
            j2 = j;
        }
        long j4 = parseMvhd(leafBox2.data).timescale;
        if (j2 != C.TIME_UNSET) {
            j3 = Util.scaleLargeTimestamp(j2, 1000000, j4);
        }
        long j5 = j3;
        MdhdData parseMdhd = parseMdhd(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox3.getLeafBoxOfType(Mp4Box.TYPE_mdhd))).data);
        Mp4Box.LeafBox leafBoxOfType = ((Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox3.getContainerBoxOfType(Mp4Box.TYPE_minf))).getContainerBoxOfType(Mp4Box.TYPE_stbl))).getLeafBoxOfType(Mp4Box.TYPE_stsd);
        if (leafBoxOfType != null) {
            StsdData parseStsd = parseStsd(leafBoxOfType.data, parseTkhd.id, parseTkhd.rotationDegrees, parseMdhd.language, drmInitData, z2);
            if (z || (containerBoxOfType = containerBox2.getContainerBoxOfType(Mp4Box.TYPE_edts)) == null || (parseEdts = parseEdts(containerBoxOfType)) == null) {
                jArr2 = null;
                jArr = null;
            } else {
                jArr = (long[]) parseEdts.second;
                jArr2 = (long[]) parseEdts.first;
            }
            if (parseStsd.format == null) {
                return null;
            }
            StsdData stsdData = parseStsd;
            return new Track(parseTkhd.id, trackTypeForHdlr, parseMdhd.timescale, j4, j5, parseMdhd.mediaDurationUs, parseStsd.format, parseStsd.requiredSampleTransformation, stsdData.trackEncryptionBoxes, stsdData.nalUnitLengthFieldLength, jArr2, jArr);
        }
        throw ParserException.createForMalformedContainer("Malformed sample table (stbl) missing sample description (stsd)", (Throwable) null);
    }

    public static TrackSampleTable parseStbl(Track track, Mp4Box.ContainerBox containerBox, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        SampleSizeBox sampleSizeBox;
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr;
        long[] jArr;
        int[] iArr2;
        long[] jArr2;
        long j;
        int[] iArr3;
        int i5;
        int i6;
        int[] iArr4;
        long[] jArr3;
        int[] iArr5;
        int i7;
        int i8;
        int i9;
        boolean z2;
        int i10;
        int i11;
        Track track2 = track;
        Mp4Box.ContainerBox containerBox2 = containerBox;
        GaplessInfoHolder gaplessInfoHolder2 = gaplessInfoHolder;
        Mp4Box.LeafBox leafBoxOfType = containerBox2.getLeafBoxOfType(Mp4Box.TYPE_stsz);
        if (leafBoxOfType != null) {
            sampleSizeBox = new StszSampleSizeBox(leafBoxOfType, track2.format);
        } else {
            Mp4Box.LeafBox leafBoxOfType2 = containerBox2.getLeafBoxOfType(Mp4Box.TYPE_stz2);
            if (leafBoxOfType2 != null) {
                sampleSizeBox = new Stz2SampleSizeBox(leafBoxOfType2);
            } else {
                throw ParserException.createForMalformedContainer("Track has no sample table size information", (Throwable) null);
            }
        }
        int sampleCount = sampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            return new TrackSampleTable(track, new long[0], new int[0], 0, new long[0], new int[0], 0);
        }
        if (track2.type == 2 && track2.mediaDurationUs > 0) {
            track2 = track2.copyWithFormat(track2.format.buildUpon().setFrameRate(((float) sampleCount) / (((float) track2.mediaDurationUs) / 1000000.0f)).build());
        }
        Track track3 = track2;
        Mp4Box.LeafBox leafBoxOfType3 = containerBox2.getLeafBoxOfType(Mp4Box.TYPE_stco);
        if (leafBoxOfType3 == null) {
            leafBoxOfType3 = (Mp4Box.LeafBox) Assertions.checkNotNull(containerBox2.getLeafBoxOfType(Mp4Box.TYPE_co64));
            z = true;
        } else {
            z = false;
        }
        ParsableByteArray parsableByteArray = leafBoxOfType3.data;
        ParsableByteArray parsableByteArray2 = ((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox2.getLeafBoxOfType(Mp4Box.TYPE_stsc))).data;
        ParsableByteArray parsableByteArray3 = ((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox2.getLeafBoxOfType(Mp4Box.TYPE_stts))).data;
        Mp4Box.LeafBox leafBoxOfType4 = containerBox2.getLeafBoxOfType(Mp4Box.TYPE_stss);
        ParsableByteArray parsableByteArray4 = leafBoxOfType4 != null ? leafBoxOfType4.data : null;
        Mp4Box.LeafBox leafBoxOfType5 = containerBox2.getLeafBoxOfType(Mp4Box.TYPE_ctts);
        ParsableByteArray parsableByteArray5 = leafBoxOfType5 != null ? leafBoxOfType5.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(parsableByteArray2, parsableByteArray, z);
        parsableByteArray3.setPosition(12);
        int readUnsignedIntToInt = parsableByteArray3.readUnsignedIntToInt() - 1;
        int readUnsignedIntToInt2 = parsableByteArray3.readUnsignedIntToInt();
        int readUnsignedIntToInt3 = parsableByteArray3.readUnsignedIntToInt();
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(12);
            i = parsableByteArray5.readUnsignedIntToInt();
        } else {
            i = 0;
        }
        if (parsableByteArray4 != null) {
            parsableByteArray4.setPosition(12);
            i3 = parsableByteArray4.readUnsignedIntToInt();
            if (i3 > 0) {
                i2 = parsableByteArray4.readUnsignedIntToInt() - 1;
            } else {
                i2 = -1;
                parsableByteArray4 = null;
            }
        } else {
            i2 = -1;
            i3 = 0;
        }
        int fixedSampleSize = sampleSizeBox.getFixedSampleSize();
        String str = track3.format.sampleMimeType;
        if (fixedSampleSize != -1 && (MimeTypes.AUDIO_RAW.equals(str) || MimeTypes.AUDIO_MLAW.equals(str) || MimeTypes.AUDIO_ALAW.equals(str)) && readUnsignedIntToInt == 0 && i == 0 && i3 == 0) {
            long[] jArr4 = new long[chunkIterator.length];
            int[] iArr6 = new int[chunkIterator.length];
            while (chunkIterator.moveNext()) {
                jArr4[chunkIterator.index] = chunkIterator.offset;
                iArr6[chunkIterator.index] = chunkIterator.numSamples;
            }
            FixedSampleSizeRechunker.Results rechunk = FixedSampleSizeRechunker.rechunk(fixedSampleSize, jArr4, iArr6, (long) readUnsignedIntToInt3);
            jArr = rechunk.offsets;
            iArr = rechunk.sizes;
            i4 = rechunk.maximumSize;
            jArr2 = rechunk.timestamps;
            iArr2 = rechunk.flags;
            j = rechunk.duration;
        } else {
            long[] jArr5 = new long[sampleCount];
            int[] iArr7 = new int[sampleCount];
            long[] jArr6 = new long[sampleCount];
            int i12 = i3;
            int[] iArr8 = new int[sampleCount];
            int i13 = readUnsignedIntToInt;
            Track track4 = track3;
            int i14 = readUnsignedIntToInt2;
            ParsableByteArray parsableByteArray6 = parsableByteArray3;
            int i15 = i2;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            long j2 = 0;
            long j3 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = readUnsignedIntToInt3;
            int i22 = i12;
            int i23 = i;
            int i24 = i21;
            while (true) {
                if (i16 >= sampleCount) {
                    int[] iArr9 = iArr8;
                    int i25 = sampleCount;
                    i8 = i14;
                    i9 = i18;
                    break;
                }
                long j4 = j3;
                int i26 = i18;
                boolean z3 = true;
                while (i26 == 0) {
                    z3 = chunkIterator.moveNext();
                    if (!z3) {
                        break;
                    }
                    int i27 = i24;
                    long j5 = chunkIterator.offset;
                    i26 = chunkIterator.numSamples;
                    j4 = j5;
                    i24 = i27;
                    i14 = i14;
                    sampleCount = sampleCount;
                }
                int i28 = sampleCount;
                int i29 = i24;
                i8 = i14;
                if (!z3) {
                    Log.w(TAG, "Unexpected end of chunk data");
                    jArr5 = Arrays.copyOf(jArr5, i16);
                    iArr7 = Arrays.copyOf(iArr7, i16);
                    jArr6 = Arrays.copyOf(jArr6, i16);
                    iArr8 = Arrays.copyOf(iArr8, i16);
                    sampleCount = i16;
                    i9 = i26;
                    break;
                }
                int i30 = i23;
                if (parsableByteArray5 != null) {
                    while (i20 == 0 && i30 > 0) {
                        i20 = parsableByteArray5.readUnsignedIntToInt();
                        i19 = parsableByteArray5.readInt();
                        i30--;
                    }
                    i20--;
                }
                int i31 = i19;
                jArr5[i16] = j4;
                int readNextSampleSize = sampleSizeBox.readNextSampleSize();
                iArr7[i16] = readNextSampleSize;
                ChunkIterator chunkIterator2 = chunkIterator;
                if (readNextSampleSize > i17) {
                    i17 = readNextSampleSize;
                }
                SampleSizeBox sampleSizeBox2 = sampleSizeBox;
                jArr6[i16] = j2 + ((long) i31);
                iArr8[i16] = parsableByteArray4 == null ? 1 : 0;
                if (i16 == i15) {
                    iArr8[i16] = 1;
                    i22--;
                    if (i22 > 0) {
                        i15 = ((ParsableByteArray) Assertions.checkNotNull(parsableByteArray4)).readUnsignedIntToInt() - 1;
                    }
                }
                int[] iArr10 = iArr8;
                int i32 = i15;
                int i33 = i29;
                j2 += (long) i33;
                int i34 = i8 - 1;
                if (i34 != 0 || i13 <= 0) {
                    i11 = i33;
                } else {
                    i34 = parsableByteArray6.readUnsignedIntToInt();
                    i11 = parsableByteArray6.readInt();
                    i13--;
                }
                int i35 = i34;
                int i36 = i26 - 1;
                i16++;
                j3 = j4 + ((long) iArr7[i16]);
                i19 = i31;
                iArr8 = iArr10;
                sampleSizeBox = sampleSizeBox2;
                i24 = i11;
                sampleCount = i28;
                i15 = i32;
                i23 = i30;
                i14 = i35;
                i18 = i36;
                chunkIterator = chunkIterator2;
            }
            long j6 = j2 + ((long) i19);
            if (parsableByteArray5 != null) {
                int i37 = i23;
                while (true) {
                    if (i37 <= 0) {
                        break;
                    } else if (parsableByteArray5.readUnsignedIntToInt() != 0) {
                        z2 = false;
                        break;
                    } else {
                        parsableByteArray5.readInt();
                        i37--;
                    }
                }
            }
            z2 = true;
            if (i22 == 0 && i8 == 0 && i9 == 0 && i13 == 0) {
                i10 = i20;
                if (i10 == 0 && z2) {
                    track3 = track4;
                    iArr2 = iArr8;
                    j = j6;
                    jArr = jArr5;
                    jArr2 = jArr6;
                    i4 = i17;
                    iArr = iArr7;
                }
            } else {
                i10 = i20;
            }
            track3 = track4;
            Log.w(TAG, "Inconsistent stbl box for track " + track3.id + ": remainingSynchronizationSamples " + i22 + ", remainingSamplesAtTimestampDelta " + i8 + ", remainingSamplesInChunk " + i9 + ", remainingTimestampDeltaChanges " + i13 + ", remainingSamplesAtTimestampOffset " + i10 + (!z2 ? ", ctts invalid" : ""));
            iArr2 = iArr8;
            j = j6;
            jArr = jArr5;
            jArr2 = jArr6;
            i4 = i17;
            iArr = iArr7;
        }
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(j, 1000000, track3.timescale);
        if (track3.editListDurations == null) {
            Util.scaleLargeTimestampsInPlace(jArr2, 1000000, track3.timescale);
            return new TrackSampleTable(track3, jArr, iArr, i4, jArr2, iArr2, scaleLargeTimestamp);
        }
        if (track3.editListDurations.length == 1 && track3.type == 1 && jArr2.length >= 2) {
            long j7 = ((long[]) Assertions.checkNotNull(track3.editListMediaTimes))[0];
            long scaleLargeTimestamp2 = j7 + Util.scaleLargeTimestamp(track3.editListDurations[0], track3.timescale, track3.movieTimescale);
            iArr3 = iArr;
            i6 = i4;
            int[] iArr11 = iArr2;
            if (canApplyEditWithGaplessInfo(jArr2, j, j7, scaleLargeTimestamp2)) {
                long scaleLargeTimestamp3 = Util.scaleLargeTimestamp(j7 - jArr2[0], (long) track3.format.sampleRate, track3.timescale);
                i5 = sampleCount;
                long j8 = scaleLargeTimestamp3;
                long scaleLargeTimestamp4 = Util.scaleLargeTimestamp(j - scaleLargeTimestamp2, (long) track3.format.sampleRate, track3.timescale);
                if (!(j8 == 0 && scaleLargeTimestamp4 == 0) && j8 <= 2147483647L && scaleLargeTimestamp4 <= 2147483647L) {
                    GaplessInfoHolder gaplessInfoHolder3 = gaplessInfoHolder;
                    gaplessInfoHolder3.encoderDelay = (int) j8;
                    gaplessInfoHolder3.encoderPadding = (int) scaleLargeTimestamp4;
                    Util.scaleLargeTimestampsInPlace(jArr2, 1000000, track3.timescale);
                    return new TrackSampleTable(track3, jArr, iArr3, i6, jArr2, iArr11, Util.scaleLargeTimestamp(track3.editListDurations[0], 1000000, track3.movieTimescale));
                }
            } else {
                i5 = sampleCount;
            }
            iArr4 = iArr11;
        } else {
            i5 = sampleCount;
            iArr4 = iArr2;
            iArr3 = iArr;
            i6 = i4;
        }
        if (track3.editListDurations.length == 1 && track3.editListDurations[0] == 0) {
            long j9 = ((long[]) Assertions.checkNotNull(track3.editListMediaTimes))[0];
            for (int i38 = 0; i38 < jArr2.length; i38++) {
                jArr2[i38] = Util.scaleLargeTimestamp(jArr2[i38] - j9, 1000000, track3.timescale);
            }
            return new TrackSampleTable(track3, jArr, iArr3, i6, jArr2, iArr4, Util.scaleLargeTimestamp(j - j9, 1000000, track3.timescale));
        }
        boolean z4 = track3.type == 1;
        int[] iArr12 = new int[track3.editListDurations.length];
        int[] iArr13 = new int[track3.editListDurations.length];
        long[] jArr7 = (long[]) Assertions.checkNotNull(track3.editListMediaTimes);
        int i39 = 0;
        boolean z5 = false;
        int i40 = 0;
        int i41 = 0;
        while (i39 < track3.editListDurations.length) {
            long j10 = jArr7[i39];
            if (j10 != -1) {
                int i42 = i40;
                int i43 = i41;
                long scaleLargeTimestamp5 = Util.scaleLargeTimestamp(track3.editListDurations[i39], track3.timescale, track3.movieTimescale);
                iArr12[i39] = Util.binarySearchFloor(jArr2, j10, true, true);
                while (true) {
                    int i44 = iArr12[i39];
                    if (i44 < 0 || (iArr4[i44] & 1) != 0) {
                        long j11 = j10 + scaleLargeTimestamp5;
                        iArr13[i39] = Util.binarySearchCeil(jArr2, j11, z4, false);
                    } else {
                        iArr12[i39] = i44 - 1;
                    }
                }
                long j112 = j10 + scaleLargeTimestamp5;
                iArr13[i39] = Util.binarySearchCeil(jArr2, j112, z4, false);
                if (track3.type == 2) {
                    while (true) {
                        int i45 = iArr13[i39];
                        if (i45 >= jArr2.length - 1 || jArr2[i45 + 1] > j112) {
                            break;
                        }
                        iArr13[i39] = i45 + 1;
                    }
                }
                i41 = iArr13[i39];
                int i46 = iArr12[i39];
                i7 = i42 + (i41 - i46);
                z5 |= i43 != i46;
            } else {
                int i47 = i41;
                i7 = i40;
            }
            i39++;
            i40 = i7;
        }
        int i48 = i40;
        int i49 = 0;
        boolean z6 = z5 | (i48 != i5);
        long[] jArr8 = z6 ? new long[i48] : jArr;
        int[] iArr14 = z6 ? new int[i48] : iArr3;
        int i50 = z6 ? 0 : i6;
        int[] iArr15 = z6 ? new int[i48] : iArr4;
        long[] jArr9 = new long[i48];
        boolean z7 = false;
        int i51 = 0;
        int i52 = i50;
        long j12 = 0;
        while (i49 < track3.editListDurations.length) {
            long j13 = track3.editListMediaTimes[i49];
            int i53 = iArr12[i49];
            int[] iArr16 = iArr12;
            int i54 = iArr13[i49];
            int[] iArr17 = iArr13;
            if (z6) {
                int i55 = i54 - i53;
                System.arraycopy(jArr, i53, jArr8, i51, i55);
                jArr3 = jArr;
                iArr5 = iArr3;
                System.arraycopy(iArr5, i53, iArr14, i51, i55);
                System.arraycopy(iArr4, i53, iArr15, i51, i55);
            } else {
                jArr3 = jArr;
                iArr5 = iArr3;
            }
            boolean z8 = z7;
            int i56 = i52;
            while (i53 < i54) {
                int[] iArr18 = iArr4;
                int i57 = i49;
                long scaleLargeTimestamp6 = Util.scaleLargeTimestamp(j12, 1000000, track3.movieTimescale);
                long j14 = j12;
                long scaleLargeTimestamp7 = Util.scaleLargeTimestamp(jArr2[i53] - j13, 1000000, track3.timescale);
                boolean z9 = scaleLargeTimestamp7 < 0 ? true : z8;
                jArr9[i51] = scaleLargeTimestamp6 + scaleLargeTimestamp7;
                if (z6 && iArr14[i51] > i56) {
                    i56 = iArr5[i53];
                }
                i51++;
                i53++;
                z8 = z9;
                j12 = j14;
                iArr4 = iArr18;
                i49 = i57;
            }
            int i58 = i49;
            j12 += track3.editListDurations[i58];
            i49 = i58 + 1;
            z7 = z8;
            i52 = i56;
            iArr3 = iArr5;
            iArr12 = iArr16;
            iArr13 = iArr17;
            jArr = jArr3;
            iArr4 = iArr4;
        }
        long scaleLargeTimestamp8 = Util.scaleLargeTimestamp(j12, 1000000, track3.movieTimescale);
        if (z7) {
            track3 = track3.copyWithFormat(track3.format.buildUpon().setHasPrerollSamples(true).build());
        }
        return new TrackSampleTable(track3, jArr8, iArr14, i52, jArr9, iArr15, scaleLargeTimestamp8);
    }

    private static Metadata parseUdtaMeta(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        maybeSkipRemainingMetaBoxHeaderBytes(parsableByteArray);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1768715124) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + readInt);
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Metadata.Entry parseIlstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (parseIlstElement != null) {
                arrayList.add(parseIlstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    private static Metadata parseXyz(ParsableByteArray parsableByteArray) {
        short readShort = parsableByteArray.readShort();
        parsableByteArray.skipBytes(2);
        String readString = parsableByteArray.readString(readShort);
        int max = Math.max(readString.lastIndexOf(43), readString.lastIndexOf(45));
        try {
            return new Metadata(new Mp4LocationData(Float.parseFloat(readString.substring(0, max)), Float.parseFloat(readString.substring(max, readString.length() - 1))));
        } catch (IndexOutOfBoundsException | NumberFormatException unused) {
            return null;
        }
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        boolean z;
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullBoxVersion == 0 ? 8 : 16);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        if (parseFullBoxVersion == 0) {
            i = 4;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= i) {
                z = true;
                break;
            } else if (parsableByteArray.getData()[position + i3] != -1) {
                z = false;
                break;
            } else {
                i3++;
            }
        }
        long j = C.TIME_UNSET;
        if (z) {
            parsableByteArray.skipBytes(i);
        } else {
            long readUnsignedInt = parseFullBoxVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (readUnsignedInt != 0) {
                j = readUnsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        int readInt2 = parsableByteArray.readInt();
        int readInt3 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int readInt4 = parsableByteArray.readInt();
        int readInt5 = parsableByteArray.readInt();
        if (readInt2 == 0 && readInt3 == 65536 && readInt4 == -65536 && readInt5 == 0) {
            i2 = 90;
        } else if (readInt2 == 0 && readInt3 == -65536 && readInt4 == 65536 && readInt5 == 0) {
            i2 = 270;
        } else if (readInt2 == -65536 && readInt3 == 0 && readInt4 == 0 && readInt5 == -65536) {
            i2 = RotationOptions.ROTATE_180;
        }
        return new TkhdData(readInt, j, i2);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    private static MdhdData parseMdhd(ParsableByteArray parsableByteArray) {
        long j;
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullBoxVersion == 0 ? 8 : 16);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        int position = parsableByteArray.getPosition();
        if (parseFullBoxVersion == 0) {
            i = 4;
        }
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                z = true;
                break;
            } else if (parsableByteArray.getData()[position + i2] != -1) {
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            parsableByteArray.skipBytes(i);
        } else {
            long readUnsignedInt2 = parseFullBoxVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (readUnsignedInt2 != 0) {
                j = Util.scaleLargeTimestamp(readUnsignedInt2, 1000000, readUnsignedInt);
                int readUnsignedShort = parsableByteArray.readUnsignedShort();
                return new MdhdData(readUnsignedInt, j, "" + ((char) (((readUnsignedShort >> 10) & 31) + 96)) + ((char) (((readUnsignedShort >> 5) & 31) + 96)) + ((char) ((readUnsignedShort & 31) + 96)));
            }
        }
        j = -9223372036854775807L;
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        return new MdhdData(readUnsignedInt, j, "" + ((char) (((readUnsignedShort2 >> 10) & 31) + 96)) + ((char) (((readUnsignedShort2 >> 5) & 31) + 96)) + ((char) ((readUnsignedShort2 & 31) + 96)));
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, int i2, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        int i3;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i4 = i;
        parsableByteArray2.setPosition(12);
        int readInt = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(readInt);
        for (int i5 = 0; i5 < readInt; i5++) {
            int position = parsableByteArray.getPosition();
            int readInt2 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(readInt2 > 0, "childAtomSize must be positive");
            int readInt3 = parsableByteArray.readInt();
            if (readInt3 == 1635148593 || readInt3 == 1635148595 || readInt3 == 1701733238 || readInt3 == 1831958048 || readInt3 == 1836070006 || readInt3 == 1752589105 || readInt3 == 1751479857 || readInt3 == 1932670515 || readInt3 == 1211250227 || readInt3 == 1748121139 || readInt3 == 1987063864 || readInt3 == 1987063865 || readInt3 == 1635135537 || readInt3 == 1685479798 || readInt3 == 1685479729 || readInt3 == 1685481573 || readInt3 == 1685481521) {
                i3 = position;
                parseVideoSampleEntry(parsableByteArray, readInt3, i3, readInt2, i, i2, drmInitData, stsdData, i5);
            } else if (readInt3 == 1836069985 || readInt3 == 1701733217 || readInt3 == 1633889587 || readInt3 == 1700998451 || readInt3 == 1633889588 || readInt3 == 1835823201 || readInt3 == 1685353315 || readInt3 == 1685353317 || readInt3 == 1685353320 || readInt3 == 1685353324 || readInt3 == 1685353336 || readInt3 == 1935764850 || readInt3 == 1935767394 || readInt3 == 1819304813 || readInt3 == 1936684916 || readInt3 == 1953984371 || readInt3 == 778924082 || readInt3 == 778924083 || readInt3 == 1835557169 || readInt3 == 1835560241 || readInt3 == 1634492771 || readInt3 == 1634492791 || readInt3 == 1970037111 || readInt3 == 1332770163 || readInt3 == 1716281667 || readInt3 == 1767992678) {
                i3 = position;
                parseAudioSampleEntry(parsableByteArray, readInt3, position, readInt2, i, str, z, drmInitData, stsdData, i5);
            } else {
                if (readInt3 == 1414810956 || readInt3 == 1954034535 || readInt3 == 2004251764 || readInt3 == 1937010800 || readInt3 == 1664495672) {
                    parseTextSampleEntry(parsableByteArray, readInt3, position, readInt2, i, str, stsdData);
                } else if (readInt3 == 1835365492) {
                    parseMetaDataSampleEntry(parsableByteArray2, readInt3, position, i4, stsdData);
                } else if (readInt3 == 1667329389) {
                    stsdData.format = new Format.Builder().setId(i4).setSampleMimeType(MimeTypes.APPLICATION_CAMERA_MOTION).build();
                }
                i3 = position;
            }
            parsableByteArray2.setPosition(i3 + readInt2);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, StsdData stsdData) {
        parsableByteArray.setPosition(i2 + 8 + 8);
        String str2 = MimeTypes.APPLICATION_TTML;
        ImmutableList immutableList = null;
        long j = Long.MAX_VALUE;
        if (i != 1414810956) {
            if (i == 1954034535) {
                int i5 = (i3 - 8) - 8;
                byte[] bArr = new byte[i5];
                parsableByteArray.readBytes(bArr, 0, i5);
                immutableList = ImmutableList.of(bArr);
                str2 = MimeTypes.APPLICATION_TX3G;
            } else if (i == 2004251764) {
                str2 = MimeTypes.APPLICATION_MP4VTT;
            } else if (i == 1937010800) {
                j = 0;
            } else if (i == 1664495672) {
                stsdData.requiredSampleTransformation = 1;
                str2 = MimeTypes.APPLICATION_MP4CEA608;
            } else {
                throw new IllegalStateException();
            }
        }
        stsdData.format = new Format.Builder().setId(i4).setSampleMimeType(str2).setLanguage(str).setSubsampleOffsetUs(j).setInitializationData(immutableList).build();
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, int i5, DrmInitData drmInitData, StsdData stsdData, int i6) throws ParserException {
        String str;
        int i7;
        int i8;
        DrmInitData drmInitData2;
        int i9;
        int i10;
        int i11;
        int i12;
        List list;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i22 = i2;
        int i23 = i3;
        DrmInitData drmInitData3 = drmInitData;
        StsdData stsdData2 = stsdData;
        int i24 = 8;
        parsableByteArray2.setPosition(i22 + 8 + 8);
        parsableByteArray2.skipBytes(16);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray2.skipBytes(50);
        int position = parsableByteArray.getPosition();
        int i25 = i;
        if (i25 == 1701733238) {
            Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray2, i22, i23);
            if (parseSampleEntryEncryptionData != null) {
                i25 = ((Integer) parseSampleEntryEncryptionData.first).intValue();
                if (drmInitData3 == null) {
                    drmInitData3 = null;
                } else {
                    drmInitData3 = drmInitData3.copyWithSchemeType(((TrackEncryptionBox) parseSampleEntryEncryptionData.second).schemeType);
                }
                stsdData2.trackEncryptionBoxes[i6] = (TrackEncryptionBox) parseSampleEntryEncryptionData.second;
            }
            parsableByteArray2.setPosition(position);
        }
        String str2 = MimeTypes.VIDEO_H263;
        float f = 1.0f;
        List list2 = null;
        String str3 = null;
        byte[] bArr = null;
        int i26 = -1;
        int i27 = -1;
        int i28 = -1;
        int i29 = -1;
        int i30 = -1;
        ByteBuffer byteBuffer = null;
        EsdsData esdsData = null;
        NalUnitUtil.H265VpsData h265VpsData = null;
        boolean z = false;
        String str4 = i25 == 1831958048 ? MimeTypes.VIDEO_MPEG : i25 == 1211250227 ? str2 : null;
        int i31 = position;
        int i32 = 8;
        while (i31 - i22 < i23) {
            parsableByteArray2.setPosition(i31);
            int position2 = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (readInt == 0) {
                str = str2;
                if (parsableByteArray.getPosition() - i22 == i23) {
                    break;
                }
            } else {
                str = str2;
            }
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1635148611) {
                ExtractorUtil.checkContainerInput(str4 == null, (String) null);
                parsableByteArray2.setPosition(position2 + 8);
                AvcConfig parse = AvcConfig.parse(parsableByteArray);
                list2 = parse.initializationData;
                stsdData2.nalUnitLengthFieldLength = parse.nalUnitLengthFieldLength;
                if (!z) {
                    f = parse.pixelWidthHeightRatio;
                }
                String str5 = parse.codecs;
                int i33 = parse.maxNumReorderFrames;
                int i34 = parse.colorSpace;
                int i35 = parse.colorRange;
                int i36 = parse.colorTransfer;
                int i37 = parse.bitdepthLuma;
                int i38 = parse.bitdepthChroma;
                drmInitData2 = drmInitData3;
                i7 = readUnsignedShort2;
                i27 = i33;
                i9 = i25;
                i28 = i34;
                i8 = i35;
                i30 = i36;
                str4 = MimeTypes.VIDEO_H264;
                i32 = i38;
                str3 = str5;
                i24 = i37;
            } else if (readInt2 == 1752589123) {
                ExtractorUtil.checkContainerInput(str4 == null, (String) null);
                parsableByteArray2.setPosition(position2 + 8);
                HevcConfig parse2 = HevcConfig.parse(parsableByteArray);
                list2 = parse2.initializationData;
                stsdData2.nalUnitLengthFieldLength = parse2.nalUnitLengthFieldLength;
                if (!z) {
                    f = parse2.pixelWidthHeightRatio;
                }
                int i39 = parse2.maxNumReorderPics;
                String str6 = parse2.codecs;
                if (parse2.stereoMode != -1) {
                    i26 = parse2.stereoMode;
                }
                int i40 = parse2.colorSpace;
                int i41 = parse2.colorRange;
                int i42 = parse2.colorTransfer;
                int i43 = i39;
                int i44 = parse2.bitdepthLuma;
                int i45 = parse2.bitdepthChroma;
                h265VpsData = parse2.vpsData;
                drmInitData2 = drmInitData3;
                i7 = readUnsignedShort2;
                i28 = i40;
                i9 = i25;
                i8 = i41;
                i30 = i42;
                i32 = i45;
                str4 = MimeTypes.VIDEO_H265;
                int i46 = i43;
                str3 = str6;
                i24 = i44;
                i27 = i46;
            } else {
                drmInitData2 = drmInitData3;
                if (readInt2 == 1818785347) {
                    ExtractorUtil.checkContainerInput(MimeTypes.VIDEO_H265.equals(str4), "lhvC must follow hvcC atom");
                    NalUnitUtil.H265VpsData h265VpsData2 = h265VpsData;
                    ExtractorUtil.checkContainerInput(h265VpsData2 != null && h265VpsData2.layerInfos.size() >= 2, "must have at least two layers");
                    parsableByteArray2.setPosition(position2 + 8);
                    HevcConfig parseLayered = HevcConfig.parseLayered(parsableByteArray2, (NalUnitUtil.H265VpsData) Assertions.checkNotNull(h265VpsData2));
                    ExtractorUtil.checkContainerInput(stsdData2.nalUnitLengthFieldLength == parseLayered.nalUnitLengthFieldLength, "nalUnitLengthFieldLength must be same for both hvcC and lhvC atoms");
                    if (parseLayered.colorSpace != -1) {
                        i18 = i28;
                        ExtractorUtil.checkContainerInput(i18 == parseLayered.colorSpace, "colorSpace must be the same for both views");
                    } else {
                        i18 = i28;
                    }
                    if (parseLayered.colorRange != -1) {
                        i19 = i29;
                        ExtractorUtil.checkContainerInput(i19 == parseLayered.colorRange, "colorRange must be the same for both views");
                    } else {
                        i19 = i29;
                    }
                    if (parseLayered.colorTransfer != -1) {
                        i21 = i30;
                        i20 = i18;
                        ExtractorUtil.checkContainerInput(i21 == parseLayered.colorTransfer, "colorTransfer must be the same for both views");
                    } else {
                        i20 = i18;
                        i21 = i30;
                    }
                    ExtractorUtil.checkContainerInput(i24 == parseLayered.bitdepthLuma, "bitdepthLuma must be the same for both views");
                    ExtractorUtil.checkContainerInput(i32 == parseLayered.bitdepthChroma, "bitdepthChroma must be the same for both views");
                    if (list2 != null) {
                        list2 = ImmutableList.builder().addAll((Iterable) list2).addAll((Iterable) parseLayered.initializationData).build();
                    } else {
                        ExtractorUtil.checkContainerInput(false, "initializationData must be already set from hvcC atom");
                    }
                    h265VpsData = h265VpsData2;
                    i7 = readUnsignedShort2;
                    i9 = i25;
                    i30 = i21;
                    i8 = i19;
                    i28 = i20;
                    str3 = parseLayered.codecs;
                    str4 = MimeTypes.VIDEO_MV_HEVC;
                } else {
                    int i47 = i28;
                    i8 = i29;
                    int i48 = i30;
                    NalUnitUtil.H265VpsData h265VpsData3 = h265VpsData;
                    if (readInt2 == 1986361461) {
                        VexuData parseVideoExtendedUsageBox = parseVideoExtendedUsageBox(parsableByteArray2, position2, readInt);
                        if (!(parseVideoExtendedUsageBox == null || parseVideoExtendedUsageBox.eyesData == null)) {
                            if (h265VpsData3 == null || h265VpsData3.layerInfos.size() < 2) {
                                i17 = i26;
                                if (i17 == -1) {
                                    i26 = parseVideoExtendedUsageBox.eyesData.striData.eyeViewsReversed ? 5 : 4;
                                    h265VpsData = h265VpsData3;
                                    i7 = readUnsignedShort2;
                                    i9 = i25;
                                    i30 = i48;
                                    i28 = i47;
                                }
                                i26 = i17;
                                h265VpsData = h265VpsData3;
                                i7 = readUnsignedShort2;
                                i9 = i25;
                                i30 = i48;
                                i28 = i47;
                            } else {
                                ExtractorUtil.checkContainerInput(parseVideoExtendedUsageBox.hasBothEyeViews(), "both eye views must be marked as available");
                                ExtractorUtil.checkContainerInput(!parseVideoExtendedUsageBox.eyesData.striData.eyeViewsReversed, "for MV-HEVC, eye_views_reversed must be set to false");
                            }
                        }
                        i17 = i26;
                        i26 = i17;
                        h265VpsData = h265VpsData3;
                        i7 = readUnsignedShort2;
                        i9 = i25;
                        i30 = i48;
                        i28 = i47;
                    } else {
                        int i49 = i26;
                        NalUnitUtil.H265VpsData h265VpsData4 = h265VpsData3;
                        if (readInt2 == 1685480259 || readInt2 == 1685485123) {
                            i10 = i49;
                            i13 = i24;
                            i7 = readUnsignedShort2;
                            i14 = i32;
                            i9 = i25;
                            list = list2;
                            i15 = i47;
                            DolbyVisionConfig parse3 = DolbyVisionConfig.parse(parsableByteArray);
                            if (parse3 != null) {
                                String str7 = parse3.codecs;
                                str4 = MimeTypes.VIDEO_DOLBY_VISION;
                                str3 = str7;
                            }
                        } else if (readInt2 == 1987076931) {
                            ExtractorUtil.checkContainerInput(str4 == null, (String) null);
                            String str8 = i25 == 1987063864 ? MimeTypes.VIDEO_VP8 : MimeTypes.VIDEO_VP9;
                            parsableByteArray2.setPosition(position2 + 12);
                            byte readUnsignedByte = (byte) parsableByteArray.readUnsignedByte();
                            byte readUnsignedByte2 = (byte) parsableByteArray.readUnsignedByte();
                            int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                            int i50 = readUnsignedByte3 >> 4;
                            byte b = (byte) ((readUnsignedByte3 >> 1) & 7);
                            if (str8.equals(MimeTypes.VIDEO_VP9)) {
                                list2 = CodecSpecificDataUtil.buildVp9CodecPrivateInitializationData(readUnsignedByte, readUnsignedByte2, (byte) i50, b);
                            }
                            boolean z2 = (readUnsignedByte3 & 1) != 0;
                            int readUnsignedByte4 = parsableByteArray.readUnsignedByte();
                            int readUnsignedByte5 = parsableByteArray.readUnsignedByte();
                            int isoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(readUnsignedByte4);
                            int i51 = z2 ? 1 : 2;
                            i30 = ColorInfo.isoTransferCharacteristicsToColorTransfer(readUnsignedByte5);
                            str4 = str8;
                            i28 = isoColorPrimariesToColorSpace;
                            i7 = readUnsignedShort2;
                            i24 = i50;
                            i32 = i24;
                            h265VpsData = h265VpsData4;
                            i8 = i51;
                            i26 = i49;
                            i9 = i25;
                        } else {
                            if (readInt2 == 1635135811) {
                                int i52 = readInt - 8;
                                byte[] bArr2 = new byte[i52];
                                parsableByteArray2.readBytes(bArr2, 0, i52);
                                list2 = ImmutableList.of(bArr2);
                                parsableByteArray2.setPosition(position2 + 8);
                                ColorInfo parseAv1c = parseAv1c(parsableByteArray);
                                int i53 = parseAv1c.lumaBitdepth;
                                i32 = parseAv1c.chromaBitdepth;
                                int i54 = parseAv1c.colorSpace;
                                int i55 = parseAv1c.colorRange;
                                int i56 = parseAv1c.colorTransfer;
                                str4 = MimeTypes.VIDEO_AV1;
                                i30 = i56;
                                i24 = i53;
                                i7 = readUnsignedShort2;
                                i9 = i25;
                                i28 = i54;
                                i8 = i55;
                                h265VpsData = h265VpsData4;
                            } else if (readInt2 == 1668050025) {
                                if (byteBuffer == null) {
                                    byteBuffer = allocateHdrStaticInfo();
                                }
                                ByteBuffer byteBuffer2 = byteBuffer;
                                byteBuffer2.position(21);
                                byteBuffer2.putShort(parsableByteArray.readShort());
                                byteBuffer2.putShort(parsableByteArray.readShort());
                                byteBuffer = byteBuffer2;
                                i7 = readUnsignedShort2;
                                i9 = i25;
                                i30 = i48;
                                h265VpsData = h265VpsData4;
                                i28 = i47;
                            } else {
                                if (readInt2 == 1835295606) {
                                    if (byteBuffer == null) {
                                        byteBuffer = allocateHdrStaticInfo();
                                    }
                                    ByteBuffer byteBuffer3 = byteBuffer;
                                    short readShort = parsableByteArray.readShort();
                                    short readShort2 = parsableByteArray.readShort();
                                    i9 = i25;
                                    short readShort3 = parsableByteArray.readShort();
                                    short readShort4 = parsableByteArray.readShort();
                                    int i57 = i32;
                                    short readShort5 = parsableByteArray.readShort();
                                    int i58 = i24;
                                    short readShort6 = parsableByteArray.readShort();
                                    List list3 = list2;
                                    short readShort7 = parsableByteArray.readShort();
                                    i10 = i49;
                                    short readShort8 = parsableByteArray.readShort();
                                    long readUnsignedInt = parsableByteArray.readUnsignedInt();
                                    long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
                                    i7 = readUnsignedShort2;
                                    byteBuffer3.position(1);
                                    byteBuffer3.putShort(readShort5);
                                    byteBuffer3.putShort(readShort6);
                                    byteBuffer3.putShort(readShort);
                                    byteBuffer3.putShort(readShort2);
                                    byteBuffer3.putShort(readShort3);
                                    byteBuffer3.putShort(readShort4);
                                    byteBuffer3.putShort(readShort7);
                                    byteBuffer3.putShort(readShort8);
                                    byteBuffer3.putShort((short) ((int) (readUnsignedInt / 10000)));
                                    byteBuffer3.putShort((short) ((int) (readUnsignedInt2 / 10000)));
                                    byteBuffer = byteBuffer3;
                                    i32 = i57;
                                    i24 = i58;
                                    i11 = i47;
                                    list2 = list3;
                                } else {
                                    i10 = i49;
                                    i13 = i24;
                                    i7 = readUnsignedShort2;
                                    i14 = i32;
                                    i9 = i25;
                                    list = list2;
                                    if (readInt2 == 1681012275) {
                                        ExtractorUtil.checkContainerInput(str4 == null, (String) null);
                                        str4 = str;
                                    } else if (readInt2 == 1702061171) {
                                        ExtractorUtil.checkContainerInput(str4 == null, (String) null);
                                        esdsData = parseEsdsFromParent(parsableByteArray2, position2);
                                        String access$900 = esdsData.mimeType;
                                        byte[] access$1000 = esdsData.initializationData;
                                        list2 = access$1000 != null ? ImmutableList.of(access$1000) : list;
                                        str4 = access$900;
                                        i32 = i14;
                                        i24 = i13;
                                        i11 = i47;
                                    } else if (readInt2 == 1885434736) {
                                        f = parsePaspFromParent(parsableByteArray2, position2);
                                        i32 = i14;
                                        i24 = i13;
                                        i11 = i47;
                                        list2 = list;
                                        z = true;
                                        i12 = i48;
                                        h265VpsData = h265VpsData4;
                                        i26 = i10;
                                    } else if (readInt2 == 1937126244) {
                                        bArr = parseProjFromParent(parsableByteArray2, position2, readInt);
                                    } else if (readInt2 == 1936995172) {
                                        int readUnsignedByte6 = parsableByteArray.readUnsignedByte();
                                        parsableByteArray2.skipBytes(3);
                                        if (readUnsignedByte6 == 0) {
                                            int readUnsignedByte7 = parsableByteArray.readUnsignedByte();
                                            if (readUnsignedByte7 != 0) {
                                                i16 = 1;
                                                if (readUnsignedByte7 != 1) {
                                                    if (readUnsignedByte7 == 2) {
                                                        i16 = 2;
                                                    } else if (readUnsignedByte7 == 3) {
                                                        i16 = 3;
                                                    }
                                                }
                                            } else {
                                                i16 = 0;
                                            }
                                            i32 = i14;
                                            i24 = i13;
                                            i28 = i47;
                                            list2 = list;
                                            i30 = i48;
                                            h265VpsData = h265VpsData4;
                                            i26 = i16;
                                        }
                                        i16 = i10;
                                        i32 = i14;
                                        i24 = i13;
                                        i28 = i47;
                                        list2 = list;
                                        i30 = i48;
                                        h265VpsData = h265VpsData4;
                                        i26 = i16;
                                    } else {
                                        int i59 = 1;
                                        if (readInt2 == 1668246642) {
                                            i15 = i47;
                                            if (i15 == -1 && i48 == -1) {
                                                int readInt3 = parsableByteArray.readInt();
                                                if (readInt3 == TYPE_nclx || readInt3 == TYPE_nclc) {
                                                    int readUnsignedShort3 = parsableByteArray.readUnsignedShort();
                                                    int readUnsignedShort4 = parsableByteArray.readUnsignedShort();
                                                    parsableByteArray2.skipBytes(2);
                                                    boolean z3 = readInt == 19 && (parsableByteArray.readUnsignedByte() & 128) != 0;
                                                    i11 = ColorInfo.isoColorPrimariesToColorSpace(readUnsignedShort3);
                                                    if (!z3) {
                                                        i59 = 2;
                                                    }
                                                    i8 = i59;
                                                    i32 = i14;
                                                    i24 = i13;
                                                    list2 = list;
                                                    i12 = ColorInfo.isoTransferCharacteristicsToColorTransfer(readUnsignedShort4);
                                                    h265VpsData = h265VpsData4;
                                                    i26 = i10;
                                                } else {
                                                    Log.w(TAG, "Unsupported color type: " + Mp4Box.getBoxTypeString(readInt3));
                                                }
                                            }
                                        } else {
                                            i15 = i47;
                                        }
                                    }
                                    i32 = i14;
                                    i24 = i13;
                                    i11 = i47;
                                    list2 = list;
                                }
                                i12 = i48;
                                h265VpsData = h265VpsData4;
                                i26 = i10;
                            }
                            i26 = i49;
                        }
                        i11 = i15;
                        i32 = i14;
                        i24 = i13;
                        list2 = list;
                        i12 = i48;
                        h265VpsData = h265VpsData4;
                        i26 = i10;
                    }
                }
            }
            i31 += readInt;
            i22 = i2;
            i23 = i3;
            stsdData2 = stsdData;
            str2 = str;
            i25 = i9;
            drmInitData3 = drmInitData2;
            i29 = i8;
            readUnsignedShort2 = i7;
        }
        DrmInitData drmInitData4 = drmInitData3;
        int i60 = i24;
        int i61 = readUnsignedShort2;
        List list4 = list2;
        int i62 = i26;
        int i63 = i28;
        int i64 = i29;
        int i65 = i30;
        int i66 = i32;
        if (str4 != null) {
            Format.Builder colorInfo = new Format.Builder().setId(i4).setSampleMimeType(str4).setCodecs(str3).setWidth(readUnsignedShort).setHeight(i61).setPixelWidthHeightRatio(f).setRotationDegrees(i5).setProjectionData(bArr).setStereoMode(i62).setInitializationData(list4).setMaxNumReorderSamples(i27).setDrmInitData(drmInitData4).setColorInfo(new ColorInfo.Builder().setColorSpace(i63).setColorRange(i64).setColorTransfer(i65).setHdrStaticInfo(byteBuffer != null ? byteBuffer.array() : null).setLumaBitdepth(i60).setChromaBitdepth(i66).build());
            if (esdsData != null) {
                colorInfo.setAverageBitrate(Ints.saturatedCast(esdsData.bitrate)).setPeakBitrate(Ints.saturatedCast(esdsData.peakBitrate));
            }
            stsdData.format = colorInfo.build();
        }
    }

    private static ColorInfo parseAv1c(ParsableByteArray parsableByteArray) {
        int i;
        int i2;
        ColorInfo.Builder builder = new ColorInfo.Builder();
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.getData());
        parsableBitArray.setPosition(parsableByteArray.getPosition() * 8);
        int i3 = 1;
        parsableBitArray.skipBytes(1);
        int readBits = parsableBitArray.readBits(3);
        parsableBitArray.skipBits(6);
        boolean readBit = parsableBitArray.readBit();
        boolean readBit2 = parsableBitArray.readBit();
        int i4 = 10;
        if (readBits == 2 && readBit) {
            builder.setLumaBitdepth(readBit2 ? 12 : 10);
            if (readBit2) {
                i4 = 12;
            }
            builder.setChromaBitdepth(i4);
        } else if (readBits <= 2) {
            builder.setLumaBitdepth(readBit ? 10 : 8);
            if (!readBit) {
                i4 = 8;
            }
            builder.setChromaBitdepth(i4);
        }
        parsableBitArray.skipBits(13);
        parsableBitArray.skipBit();
        int readBits2 = parsableBitArray.readBits(4);
        if (readBits2 != 1) {
            Log.i(TAG, "Unsupported obu_type: " + readBits2);
            return builder.build();
        } else if (parsableBitArray.readBit()) {
            Log.i(TAG, "Unsupported obu_extension_flag");
            return builder.build();
        } else {
            boolean readBit3 = parsableBitArray.readBit();
            parsableBitArray.skipBit();
            if (!readBit3 || parsableBitArray.readBits(8) <= 127) {
                int readBits3 = parsableBitArray.readBits(3);
                parsableBitArray.skipBit();
                if (parsableBitArray.readBit()) {
                    Log.i(TAG, "Unsupported reduced_still_picture_header");
                    return builder.build();
                } else if (parsableBitArray.readBit()) {
                    Log.i(TAG, "Unsupported timing_info_present_flag");
                    return builder.build();
                } else if (parsableBitArray.readBit()) {
                    Log.i(TAG, "Unsupported initial_display_delay_present_flag");
                    return builder.build();
                } else {
                    int readBits4 = parsableBitArray.readBits(5);
                    boolean z = false;
                    for (int i5 = 0; i5 <= readBits4; i5++) {
                        parsableBitArray.skipBits(12);
                        if (parsableBitArray.readBits(5) > 7) {
                            parsableBitArray.skipBit();
                        }
                    }
                    int readBits5 = parsableBitArray.readBits(4);
                    int readBits6 = parsableBitArray.readBits(4);
                    parsableBitArray.skipBits(readBits5 + 1);
                    parsableBitArray.skipBits(readBits6 + 1);
                    if (parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(7);
                    }
                    parsableBitArray.skipBits(7);
                    boolean readBit4 = parsableBitArray.readBit();
                    if (readBit4) {
                        parsableBitArray.skipBits(2);
                    }
                    if (parsableBitArray.readBit()) {
                        i = 2;
                    } else {
                        i = parsableBitArray.readBits(1);
                    }
                    if (i > 0 && !parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(1);
                    }
                    if (readBit4) {
                        parsableBitArray.skipBits(3);
                    }
                    parsableBitArray.skipBits(3);
                    boolean readBit5 = parsableBitArray.readBit();
                    if (readBits3 == 2 && readBit5) {
                        parsableBitArray.skipBit();
                    }
                    if (readBits3 != 1 && parsableBitArray.readBit()) {
                        z = true;
                    }
                    if (parsableBitArray.readBit()) {
                        int readBits7 = parsableBitArray.readBits(8);
                        int readBits8 = parsableBitArray.readBits(8);
                        int readBits9 = parsableBitArray.readBits(8);
                        if (!z && readBits7 == 1 && readBits8 == 13 && readBits9 == 0) {
                            i2 = 1;
                        } else {
                            i2 = parsableBitArray.readBits(1);
                        }
                        ColorInfo.Builder colorSpace = builder.setColorSpace(ColorInfo.isoColorPrimariesToColorSpace(readBits7));
                        if (i2 != 1) {
                            i3 = 2;
                        }
                        colorSpace.setColorRange(i3).setColorTransfer(ColorInfo.isoTransferCharacteristicsToColorTransfer(readBits8));
                    }
                    return builder.build();
                }
            } else {
                Log.i(TAG, "Excessive obu_size");
                return builder.build();
            }
        }
    }

    private static ByteBuffer allocateHdrStaticInfo() {
        return ByteBuffer.allocate(25).order(ByteOrder.LITTLE_ENDIAN);
    }

    private static void parseMetaDataSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, StsdData stsdData) {
        parsableByteArray.setPosition(i2 + 8 + 8);
        if (i == 1835365492) {
            parsableByteArray.readNullTerminatedString();
            String readNullTerminatedString = parsableByteArray.readNullTerminatedString();
            if (readNullTerminatedString != null) {
                stsdData.format = new Format.Builder().setId(i3).setSampleMimeType(readNullTerminatedString).build();
            }
        }
    }

    private static Pair<long[], long[]> parseEdts(Mp4Box.ContainerBox containerBox) {
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_elst);
        if (leafBoxOfType == null) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafBoxOfType.data;
        parsableByteArray.setPosition(8);
        int parseFullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[readUnsignedIntToInt];
        long[] jArr2 = new long[readUnsignedIntToInt];
        int i = 0;
        while (i < readUnsignedIntToInt) {
            jArr[i] = parseFullBoxVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = parseFullBoxVersion == 1 ? parsableByteArray.readLong() : (long) parsableByteArray.readInt();
            if (parsableByteArray.readShort() == 1) {
                parsableByteArray.skipBytes(2);
                i++;
            } else {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
        }
        return Pair.create(jArr, jArr2);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return ((float) parsableByteArray.readUnsignedIntToInt()) / ((float) parsableByteArray.readUnsignedIntToInt());
    }

    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, boolean z, DrmInitData drmInitData, StsdData stsdData, int i5) throws ParserException {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        String str2;
        String str3;
        ImmutableList of;
        String str4;
        ImmutableList immutableList;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i11 = i;
        int i12 = i2;
        int i13 = i3;
        int i14 = i4;
        String str5 = str;
        DrmInitData drmInitData2 = drmInitData;
        StsdData stsdData2 = stsdData;
        parsableByteArray2.setPosition(i12 + 8 + 8);
        if (z) {
            i6 = parsableByteArray.readUnsignedShort();
            parsableByteArray2.skipBytes(6);
        } else {
            parsableByteArray2.skipBytes(8);
            i6 = 0;
        }
        if (i6 == 0 || i6 == 1) {
            i8 = parsableByteArray.readUnsignedShort();
            parsableByteArray2.skipBytes(6);
            i10 = parsableByteArray.readUnsignedFixedPoint1616();
            parsableByteArray2.setPosition(parsableByteArray.getPosition() - 4);
            i9 = parsableByteArray.readInt();
            if (i6 == 1) {
                parsableByteArray2.skipBytes(16);
            }
            i7 = -1;
        } else if (i6 == 2) {
            parsableByteArray2.skipBytes(16);
            i10 = (int) Math.round(parsableByteArray.readDouble());
            i8 = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray2.skipBytes(4);
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            int readUnsignedIntToInt2 = parsableByteArray.readUnsignedIntToInt();
            boolean z2 = (readUnsignedIntToInt2 & 1) != 0;
            boolean z3 = (readUnsignedIntToInt2 & 2) != 0;
            if (!z2) {
                if (readUnsignedIntToInt == 8) {
                    i7 = 3;
                } else if (readUnsignedIntToInt == 16) {
                    i7 = z3 ? 268435456 : 2;
                } else if (readUnsignedIntToInt == 24) {
                    i7 = z3 ? C.ENCODING_PCM_24BIT_BIG_ENDIAN : 21;
                } else if (readUnsignedIntToInt == 32) {
                    i7 = z3 ? C.ENCODING_PCM_32BIT_BIG_ENDIAN : 22;
                }
                parsableByteArray2.skipBytes(8);
                i9 = 0;
            } else if (readUnsignedIntToInt == 32) {
                i7 = 4;
                parsableByteArray2.skipBytes(8);
                i9 = 0;
            }
            i7 = -1;
            parsableByteArray2.skipBytes(8);
            i9 = 0;
        } else {
            return;
        }
        if (i11 == 1767992678) {
            i10 = -1;
            i8 = -1;
        }
        int position = parsableByteArray.getPosition();
        if (i11 == 1701733217) {
            Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray2, i12, i13);
            if (parseSampleEntryEncryptionData != null) {
                i11 = ((Integer) parseSampleEntryEncryptionData.first).intValue();
                if (drmInitData2 == null) {
                    drmInitData2 = null;
                } else {
                    drmInitData2 = drmInitData2.copyWithSchemeType(((TrackEncryptionBox) parseSampleEntryEncryptionData.second).schemeType);
                }
                stsdData2.trackEncryptionBoxes[i5] = (TrackEncryptionBox) parseSampleEntryEncryptionData.second;
            }
            parsableByteArray2.setPosition(position);
        }
        String str6 = MimeTypes.AUDIO_MPEGH_MHM1;
        if (i11 == 1633889587) {
            str2 = MimeTypes.AUDIO_AC3;
        } else if (i11 == 1700998451) {
            str2 = MimeTypes.AUDIO_E_AC3;
        } else if (i11 == 1633889588) {
            str2 = MimeTypes.AUDIO_AC4;
        } else if (i11 == 1685353315) {
            str2 = MimeTypes.AUDIO_DTS;
        } else if (i11 == 1685353320 || i11 == 1685353324) {
            str2 = MimeTypes.AUDIO_DTS_HD;
        } else if (i11 == 1685353317) {
            str2 = MimeTypes.AUDIO_DTS_EXPRESS;
        } else if (i11 == 1685353336) {
            str2 = MimeTypes.AUDIO_DTS_X;
        } else if (i11 == 1935764850) {
            str2 = MimeTypes.AUDIO_AMR_NB;
        } else if (i11 == 1935767394) {
            str2 = MimeTypes.AUDIO_AMR_WB;
        } else {
            if (i11 != 1936684916) {
                if (i11 == 1953984371) {
                    str2 = MimeTypes.AUDIO_RAW;
                    i7 = 268435456;
                } else if (i11 != 1819304813) {
                    str2 = (i11 == 778924082 || i11 == 778924083) ? MimeTypes.AUDIO_MPEG : i11 == 1835557169 ? MimeTypes.AUDIO_MPEGH_MHA1 : i11 == 1835560241 ? str6 : i11 == 1634492771 ? MimeTypes.AUDIO_ALAC : i11 == 1634492791 ? MimeTypes.AUDIO_ALAW : i11 == 1970037111 ? MimeTypes.AUDIO_MLAW : i11 == 1332770163 ? MimeTypes.AUDIO_OPUS : i11 == 1716281667 ? MimeTypes.AUDIO_FLAC : i11 == 1835823201 ? MimeTypes.AUDIO_TRUEHD : i11 == 1767992678 ? MimeTypes.AUDIO_IAMF : null;
                } else if (i7 != -1) {
                    str2 = MimeTypes.AUDIO_RAW;
                }
            }
            str2 = MimeTypes.AUDIO_RAW;
            i7 = 2;
        }
        int i15 = i7;
        ImmutableList immutableList2 = null;
        String str7 = null;
        EsdsData esdsData = null;
        while (position - i12 < i13) {
            parsableByteArray2.setPosition(position);
            int readInt = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1835557187) {
                parsableByteArray2.setPosition(position + 8);
                parsableByteArray2.skipBytes(1);
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                parsableByteArray2.skipBytes(1);
                if (Objects.equals(str2, str6)) {
                    str4 = String.format("mhm1.%02X", new Object[]{Integer.valueOf(readUnsignedByte)});
                } else {
                    str4 = String.format("mha1.%02X", new Object[]{Integer.valueOf(readUnsignedByte)});
                }
                String str8 = str4;
                int readUnsignedShort = parsableByteArray.readUnsignedShort();
                byte[] bArr = new byte[readUnsignedShort];
                String str9 = str8;
                parsableByteArray2.readBytes(bArr, 0, readUnsignedShort);
                if (immutableList2 == null) {
                    immutableList = ImmutableList.of(bArr);
                } else {
                    immutableList = ImmutableList.of(bArr, (byte[]) immutableList2.get(0));
                }
                immutableList2 = immutableList;
                str7 = str9;
            } else {
                if (readInt2 == 1835557200) {
                    parsableByteArray2.setPosition(position + 8);
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (readUnsignedByte2 > 0) {
                        byte[] bArr2 = new byte[readUnsignedByte2];
                        str3 = str6;
                        parsableByteArray2.readBytes(bArr2, 0, readUnsignedByte2);
                        if (immutableList2 == null) {
                            immutableList2 = ImmutableList.of(bArr2);
                        } else {
                            immutableList2 = ImmutableList.of((byte[]) immutableList2.get(0), bArr2);
                        }
                    }
                } else {
                    str3 = str6;
                    if (readInt2 == 1702061171 || (z && readInt2 == 2002876005)) {
                        int findBoxPosition = readInt2 == 1702061171 ? position : findBoxPosition(parsableByteArray2, Mp4Box.TYPE_esds, position, readInt);
                        if (findBoxPosition != -1) {
                            esdsData = parseEsdsFromParent(parsableByteArray2, findBoxPosition);
                            str2 = esdsData.mimeType;
                            byte[] access$1000 = esdsData.initializationData;
                            if (access$1000 != null) {
                                if (MimeTypes.AUDIO_VORBIS.equals(str2)) {
                                    immutableList2 = VorbisUtil.parseVorbisCsdFromEsdsInitializationData(access$1000);
                                } else {
                                    if (MimeTypes.AUDIO_AAC.equals(str2)) {
                                        AacUtil.Config parseAudioSpecificConfig = AacUtil.parseAudioSpecificConfig(access$1000);
                                        int i16 = parseAudioSpecificConfig.sampleRateHz;
                                        int i17 = parseAudioSpecificConfig.channelCount;
                                        str7 = parseAudioSpecificConfig.codecs;
                                        i10 = i16;
                                        i8 = i17;
                                    }
                                    immutableList2 = ImmutableList.of(access$1000);
                                }
                            }
                        }
                        position += readInt;
                        i12 = i2;
                        i13 = i3;
                        str6 = str3;
                    } else {
                        if (readInt2 == 1684103987) {
                            parsableByteArray2.setPosition(position + 8);
                            stsdData2.format = Ac3Util.parseAc3AnnexFFormat(parsableByteArray2, Integer.toString(i4), str5, drmInitData2);
                        } else if (readInt2 == 1684366131) {
                            parsableByteArray2.setPosition(position + 8);
                            stsdData2.format = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray2, Integer.toString(i4), str5, drmInitData2);
                        } else if (readInt2 == 1684103988) {
                            parsableByteArray2.setPosition(position + 8);
                            stsdData2.format = Ac4Util.parseAc4AnnexEFormat(parsableByteArray2, Integer.toString(i4), str5, drmInitData2);
                        } else if (readInt2 == 1684892784) {
                            if (i9 > 0) {
                                i10 = i9;
                                i8 = 2;
                                position += readInt;
                                i12 = i2;
                                i13 = i3;
                                str6 = str3;
                            } else {
                                throw ParserException.createForMalformedContainer("Invalid sample rate for Dolby TrueHD MLP stream: " + i9, (Throwable) null);
                            }
                        } else if (readInt2 == 1684305011 || readInt2 == 1969517683) {
                            stsdData2.format = new Format.Builder().setId(i14).setSampleMimeType(str2).setChannelCount(i8).setSampleRate(i10).setDrmInitData(drmInitData2).setLanguage(str5).build();
                            position += readInt;
                            i12 = i2;
                            i13 = i3;
                            str6 = str3;
                        } else if (readInt2 == 1682927731) {
                            int i18 = readInt - 8;
                            byte[] bArr3 = opusMagic;
                            byte[] copyOf = Arrays.copyOf(bArr3, bArr3.length + i18);
                            parsableByteArray2.setPosition(position + 8);
                            parsableByteArray2.readBytes(copyOf, bArr3.length, i18);
                            immutableList2 = OpusUtil.buildInitializationData(copyOf);
                        } else {
                            if (readInt2 == 1684425825) {
                                int i19 = readInt - 12;
                                byte[] bArr4 = new byte[(i19 + 4)];
                                bArr4[0] = 102;
                                bArr4[1] = 76;
                                bArr4[2] = 97;
                                bArr4[3] = 67;
                                parsableByteArray2.setPosition(position + 12);
                                parsableByteArray2.readBytes(bArr4, 4, i19);
                                of = ImmutableList.of(bArr4);
                            } else if (readInt2 == 1634492771) {
                                int i20 = readInt - 12;
                                byte[] bArr5 = new byte[i20];
                                parsableByteArray2.setPosition(position + 12);
                                parsableByteArray2.readBytes(bArr5, 0, i20);
                                Pair<Integer, Integer> parseAlacAudioSpecificConfig = CodecSpecificDataUtil.parseAlacAudioSpecificConfig(bArr5);
                                int intValue = ((Integer) parseAlacAudioSpecificConfig.first).intValue();
                                int intValue2 = ((Integer) parseAlacAudioSpecificConfig.second).intValue();
                                immutableList2 = ImmutableList.of(bArr5);
                                i10 = intValue;
                                i8 = intValue2;
                                position += readInt;
                                i12 = i2;
                                i13 = i3;
                                str6 = str3;
                            } else if (readInt2 == 1767990114) {
                                parsableByteArray2.setPosition(position + 8 + 1);
                                int readUnsignedLeb128ToInt = parsableByteArray.readUnsignedLeb128ToInt();
                                byte[] bArr6 = new byte[readUnsignedLeb128ToInt];
                                parsableByteArray2.readBytes(bArr6, 0, readUnsignedLeb128ToInt);
                                of = ImmutableList.of(bArr6);
                            } else {
                                position += readInt;
                                i12 = i2;
                                i13 = i3;
                                str6 = str3;
                            }
                            immutableList2 = of;
                            position += readInt;
                            i12 = i2;
                            i13 = i3;
                            str6 = str3;
                        }
                        position += readInt;
                        i12 = i2;
                        i13 = i3;
                        str6 = str3;
                    }
                }
                position += readInt;
                i12 = i2;
                i13 = i3;
                str6 = str3;
            }
            str3 = str6;
            position += readInt;
            i12 = i2;
            i13 = i3;
            str6 = str3;
        }
        if (stsdData2.format == null && str2 != null) {
            Format.Builder language = new Format.Builder().setId(i14).setSampleMimeType(str2).setCodecs(str7).setChannelCount(i8).setSampleRate(i10).setPcmEncoding(i15).setInitializationData(immutableList2).setDrmInitData(drmInitData2).setLanguage(str5);
            if (esdsData != null) {
                language.setAverageBitrate(Ints.saturatedCast(esdsData.bitrate)).setPeakBitrate(Ints.saturatedCast(esdsData.peakBitrate));
            }
            stsdData2.format = language.build();
        }
    }

    private static int findBoxPosition(ParsableByteArray parsableByteArray, int i, int i2, int i3) throws ParserException {
        int position = parsableByteArray.getPosition();
        ExtractorUtil.checkContainerInput(position >= i2, (String) null);
        while (position - i2 < i3) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == i) {
                return position;
            }
            position += readInt;
        }
        return -1;
    }

    private static EsdsData parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedByte());
        }
        if ((readUnsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if (MimeTypes.AUDIO_MPEG.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS_HD.equals(mimeTypeFromMp4ObjectType)) {
            return new EsdsData(mimeTypeFromMp4ObjectType, (byte[]) null, -1, -1);
        }
        parsableByteArray.skipBytes(4);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
        parsableByteArray.skipBytes(1);
        int parseExpandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[parseExpandableClassSize];
        parsableByteArray.readBytes(bArr, 0, parseExpandableClassSize);
        return new EsdsData(mimeTypeFromMp4ObjectType, bArr, readUnsignedInt2 > 0 ? readUnsignedInt2 : -1, readUnsignedInt > 0 ? readUnsignedInt : -1);
    }

    static VexuData parseVideoExtendedUsageBox(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        parsableByteArray.setPosition(i + 8);
        int position = parsableByteArray.getPosition();
        EyesData eyesData = null;
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1702454643) {
                eyesData = parseStereoViewBox(parsableByteArray, position, readInt);
            }
            position += readInt;
        }
        if (eyesData == null) {
            return null;
        }
        return new VexuData(eyesData);
    }

    private static EyesData parseStereoViewBox(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        parsableByteArray.setPosition(i + 8);
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            boolean z = false;
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1937011305) {
                parsableByteArray.skipBytes(4);
                int readUnsignedByte = parsableByteArray.readUnsignedByte() & 15;
                boolean z2 = (readUnsignedByte & 1) == 1;
                boolean z3 = (readUnsignedByte & 2) == 2;
                boolean z4 = (readUnsignedByte & 8) == 8;
                if ((readUnsignedByte & 4) == 4) {
                    z = true;
                }
                return new EyesData(new StriData(z2, z3, z4, z));
            }
            position += readInt;
        }
        return null;
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1936289382 && (parseCommonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, readInt)) != null) {
                return parseCommonEncryptionSinfFromParent;
            }
            position += readInt;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        int i3 = i + 8;
        boolean z = false;
        int i4 = -1;
        int i5 = 0;
        String str = null;
        Integer num = null;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1718775137) {
                num = Integer.valueOf(parsableByteArray.readInt());
            } else if (readInt2 == 1935894637) {
                parsableByteArray.skipBytes(4);
                str = parsableByteArray.readString(4);
            } else if (readInt2 == 1935894633) {
                i4 = i3;
                i5 = readInt;
            }
            i3 += readInt;
        }
        if (!C.CENC_TYPE_cenc.equals(str) && !C.CENC_TYPE_cbc1.equals(str) && !C.CENC_TYPE_cens.equals(str) && !C.CENC_TYPE_cbcs.equals(str)) {
            return null;
        }
        ExtractorUtil.checkContainerInput(num != null, "frma atom is mandatory");
        ExtractorUtil.checkContainerInput(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox parseSchiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, str);
        if (parseSchiFromParent != null) {
            z = true;
        }
        ExtractorUtil.checkContainerInput(z, "tenc atom is mandatory");
        return Pair.create(num, (TrackEncryptionBox) Util.castNonNull(parseSchiFromParent));
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        int i5 = i + 8;
        while (true) {
            byte[] bArr = null;
            if (i5 - i >= i2) {
                return null;
            }
            parsableByteArray.setPosition(i5);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1952804451) {
                int parseFullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (parseFullBoxVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    i3 = readUnsignedByte & 15;
                    i4 = (readUnsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, 16);
                if (z && readUnsignedByte2 == 0) {
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[readUnsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, readUnsignedByte3);
                }
                return new TrackEncryptionBox(z, str, readUnsignedByte2, bArr2, i4, i3, bArr);
            }
            i5 += readInt;
        }
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1886547818) {
                return Arrays.copyOfRange(parsableByteArray.getData(), i3, readInt + i3);
            }
            i3 += readInt;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i = readUnsignedByte & 127;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (readUnsignedByte & 127);
        }
        return i;
    }

    private static boolean canApplyEditWithGaplessInfo(long[] jArr, long j, long j2, long j3) {
        int length = jArr.length - 1;
        int constrainValue = Util.constrainValue(4, 0, length);
        int constrainValue2 = Util.constrainValue(jArr.length - 4, 0, length);
        if (jArr[0] > j2 || j2 >= jArr[constrainValue] || jArr[constrainValue2] >= j3 || j3 > j) {
            return false;
        }
        return true;
    }

    private BoxParser() {
    }

    private static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) throws ParserException {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            ExtractorUtil.checkContainerInput(parsableByteArray.readInt() != 1 ? false : true, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            long j;
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            if (this.chunkOffsetsAreLongs) {
                j = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                j = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = j;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    private static final class TkhdData {
        /* access modifiers changed from: private */
        public final long duration;
        /* access modifiers changed from: private */
        public final int id;
        /* access modifiers changed from: private */
        public final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    private static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    private static final class EsdsData {
        /* access modifiers changed from: private */
        public final long bitrate;
        /* access modifiers changed from: private */
        public final byte[] initializationData;
        /* access modifiers changed from: private */
        public final String mimeType;
        /* access modifiers changed from: private */
        public final long peakBitrate;

        public EsdsData(String str, byte[] bArr, long j, long j2) {
            this.mimeType = str;
            this.initializationData = bArr;
            this.bitrate = j;
            this.peakBitrate = j2;
        }
    }

    private static final class StriData {
        /* access modifiers changed from: private */
        public final boolean eyeViewsReversed;
        private final boolean hasAdditionalViews;
        /* access modifiers changed from: private */
        public final boolean hasLeftEyeView;
        /* access modifiers changed from: private */
        public final boolean hasRightEyeView;

        public StriData(boolean z, boolean z2, boolean z3, boolean z4) {
            this.hasLeftEyeView = z;
            this.hasRightEyeView = z2;
            this.eyeViewsReversed = z3;
            this.hasAdditionalViews = z4;
        }
    }

    private static final class EyesData {
        /* access modifiers changed from: private */
        public final StriData striData;

        public EyesData(StriData striData2) {
            this.striData = striData2;
        }
    }

    private static final class MdhdData {
        /* access modifiers changed from: private */
        public final String language;
        /* access modifiers changed from: private */
        public final long mediaDurationUs;
        /* access modifiers changed from: private */
        public final long timescale;

        public MdhdData(long j, long j2, String str) {
            this.timescale = j;
            this.mediaDurationUs = j2;
            this.language = str;
        }
    }

    static final class VexuData {
        /* access modifiers changed from: private */
        public final EyesData eyesData;

        public VexuData(EyesData eyesData2) {
            this.eyesData = eyesData2;
        }

        public boolean hasBothEyeViews() {
            EyesData eyesData2 = this.eyesData;
            return eyesData2 != null && eyesData2.striData.hasLeftEyeView && this.eyesData.striData.hasRightEyeView;
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Mp4Box.LeafBox leafBox, Format format) {
            ParsableByteArray parsableByteArray = leafBox.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if (MimeTypes.AUDIO_RAW.equals(format.sampleMimeType)) {
                int pcmFrameSize = Util.getPcmFrameSize(format.pcmEncoding, format.channelCount);
                if (readUnsignedIntToInt == 0 || readUnsignedIntToInt % pcmFrameSize != 0) {
                    Log.w(BoxParser.TAG, "Audio sample size mismatch. stsd sample size: " + pcmFrameSize + ", stsz sample size: " + readUnsignedIntToInt);
                    readUnsignedIntToInt = pcmFrameSize;
                }
            }
            this.fixedSampleSize = readUnsignedIntToInt == 0 ? -1 : readUnsignedIntToInt;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int getFixedSampleSize() {
            return this.fixedSampleSize;
        }

        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == -1 ? this.data.readUnsignedIntToInt() : i;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        public int getFixedSampleSize() {
            return -1;
        }

        public Stz2SampleSizeBox(Mp4Box.LeafBox leafBox) {
            ParsableByteArray parsableByteArray = leafBox.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fieldSize = parsableByteArray.readUnsignedIntToInt() & 255;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            int i = this.fieldSize;
            if (i == 8) {
                return this.data.readUnsignedByte();
            }
            if (i == 16) {
                return this.data.readUnsignedShort();
            }
            int i2 = this.sampleIndex;
            this.sampleIndex = i2 + 1;
            if (i2 % 2 != 0) {
                return this.currentByte & 15;
            }
            int readUnsignedByte = this.data.readUnsignedByte();
            this.currentByte = readUnsignedByte;
            return (readUnsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        }
    }
}
