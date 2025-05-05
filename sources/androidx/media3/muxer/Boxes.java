package androidx.media3.muxer;

import android.media.MediaCodec;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Util;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4LocationData;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.muxer.FragmentedMp4Writer;
import com.google.android.material.internal.ViewUtils;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import io.sentry.protocol.Mechanism;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

final class Boxes {
    public static final int BOX_HEADER_SIZE = 8;
    private static final int BYTES_PER_INTEGER = 4;
    public static final int LARGE_SIZE_BOX_HEADER_SIZE = 16;
    private static final int MAX_FIXED_LEAF_BOX_SIZE = 200;
    public static final int MFHD_BOX_CONTENT_SIZE = 8;
    private static final long MVHD_TIMEBASE = 10000;
    public static final int TFHD_BOX_CONTENT_SIZE = 16;
    private static final int TRUN_BOX_NON_SYNC_SAMPLE_FLAGS = 16842752;
    private static final int TRUN_BOX_SYNC_SAMPLE_FLAGS = 33554432;
    public static final ImmutableList<Byte> XMP_UUID = ImmutableList.of((byte) -66, (byte) 122, (byte) -49, (byte) -53, (byte) -105, (byte) -87, (byte) 66, (byte) -24, (byte) -100, (byte) 113, (byte) -103, (byte) -108, (byte) -111, (byte) -29, (byte) -81, (byte) -84);

    public static int getTrunBoxContentSize(int i, boolean z) {
        return ((z ? 4 : 3) * i * 4) + 12;
    }

    private Boxes() {
    }

    public static ByteBuffer moov(List<Track> list, MetadataCollector metadataCollector, boolean z, int i) {
        ByteBuffer byteBuffer;
        long j;
        int i2;
        ArrayList arrayList;
        ArrayList arrayList2;
        int i3;
        int i4;
        ByteBuffer byteBuffer2;
        ByteBuffer byteBuffer3;
        ByteBuffer byteBuffer4;
        int i5;
        String str;
        String str2;
        int i6;
        String str3;
        ByteBuffer byteBuffer5;
        String str4;
        ByteBuffer byteBuffer6;
        MetadataCollector metadataCollector2 = metadataCollector;
        int i7 = (int) metadataCollector2.timestampData.creationTimestampSeconds;
        int i8 = (int) metadataCollector2.timestampData.modificationTimestampSeconds;
        long findMinimumPresentationTimestampUsAcrossTracks = findMinimumPresentationTimestampUsAcrossTracks(list);
        if (!z && findMinimumPresentationTimestampUsAcrossTracks == C.TIME_UNSET) {
            return ByteBuffer.allocate(0);
        }
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        int i9 = 0;
        long j2 = 0;
        int i10 = 1;
        while (i9 < list.size()) {
            Track track = list.get(i9);
            if (z || !track.writtenSamples.isEmpty()) {
                Format format = track.format;
                String bcp47LanguageTagToIso3 = bcp47LanguageTagToIso3(format.language);
                long j3 = j2;
                ArrayList arrayList5 = arrayList3;
                List<Integer> convertPresentationTimestampsToDurationsVu = convertPresentationTimestampsToDurationsVu(track.writtenSamples, track.videoUnitTimebase(), i, track.endOfStreamTimestampUs);
                long j4 = 0;
                int i11 = 0;
                while (i11 < convertPresentationTimestampsToDurationsVu.size()) {
                    j4 += (long) convertPresentationTimestampsToDurationsVu.get(i11).intValue();
                    i11++;
                    arrayList4 = arrayList4;
                    findMinimumPresentationTimestampUsAcrossTracks = findMinimumPresentationTimestampUsAcrossTracks;
                }
                j = findMinimumPresentationTimestampUsAcrossTracks;
                ArrayList arrayList6 = arrayList4;
                long j5 = track.writtenSamples.isEmpty() ? 0 : track.writtenSamples.get(0).presentationTimeUs;
                ArrayList arrayList7 = arrayList6;
                long usFromVu = usFromVu(j4, (long) track.videoUnitTimebase());
                int trackType = MimeTypes.getTrackType(format.sampleMimeType);
                ByteBuffer stts = stts(convertPresentationTimestampsToDurationsVu);
                if (MimeTypes.isVideo(format.sampleMimeType)) {
                    i4 = i9;
                    byteBuffer2 = ctts(track.writtenSamples, convertPresentationTimestampsToDurationsVu, track.videoUnitTimebase());
                } else {
                    i4 = i9;
                    byteBuffer2 = ByteBuffer.allocate(0);
                }
                ByteBuffer stsz = stsz(track.writtenSamples);
                ByteBuffer stsc = stsc(track.writtenChunkSampleCounts);
                long j6 = j4;
                List<Long> list2 = track.writtenChunkOffsets;
                ByteBuffer stco = z ? stco(list2) : co64(list2);
                long j7 = j5;
                if (trackType == -1 || trackType == 5) {
                    ByteBuffer nmhd = nmhd();
                    i5 = 1;
                    i6 = 3;
                    ByteBuffer[] byteBufferArr = {stsd(textMetaDataSampleEntry(format)), stts, stsz, stsc, stco};
                    str2 = "MetaHandle";
                    byteBuffer4 = stbl(byteBufferArr);
                    byteBuffer3 = nmhd;
                    str = Mechanism.JsonKeys.META;
                } else {
                    if (trackType == 1) {
                        byteBuffer5 = smhd();
                        byteBuffer6 = stbl(stsd(audioSampleEntry(format)), stts, stsz, stsc, stco);
                        str4 = "soun";
                        str3 = "SoundHandle";
                    } else if (trackType == 2) {
                        byteBuffer5 = vmhd();
                        byteBuffer6 = stbl(stsd(videoSampleEntry(format)), stts, byteBuffer2, stsz, stsc, stco, stss(track.writtenSamples));
                        str4 = "vide";
                        str3 = "VideoHandle";
                    } else {
                        throw new IllegalArgumentException("Unsupported track type");
                    }
                    byteBuffer4 = byteBuffer6;
                    str = str4;
                    byteBuffer3 = byteBuffer5;
                    str2 = str3;
                    i6 = 3;
                    i5 = 1;
                }
                Track track2 = track;
                long j8 = j3;
                int i12 = i10;
                ByteBuffer[] byteBufferArr2 = new ByteBuffer[i6];
                i2 = i4;
                byteBufferArr2[0] = tkhd(i10, usFromVu, i7, i8, metadataCollector2.orientationData.orientation, format);
                int i13 = i5;
                byteBufferArr2[i13] = edts(j7, j, usFromVu, 10000, (long) track2.videoUnitTimebase());
                ByteBuffer[] byteBufferArr3 = new ByteBuffer[3];
                byteBufferArr3[0] = mdhd(j6, track2.videoUnitTimebase(), i7, i8, bcp47LanguageTagToIso3);
                byteBufferArr3[i13] = hdlr(str, str2);
                ByteBuffer[] byteBufferArr4 = new ByteBuffer[3];
                byteBufferArr4[0] = byteBuffer3;
                ByteBuffer[] byteBufferArr5 = new ByteBuffer[i13];
                byteBufferArr5[0] = localUrl();
                byteBufferArr4[i13] = dinf(dref(byteBufferArr5));
                byteBufferArr4[2] = byteBuffer4;
                byteBufferArr3[2] = minf(byteBufferArr4);
                byteBufferArr2[2] = mdia(byteBufferArr3);
                arrayList2 = arrayList5;
                arrayList2.add(trak(byteBufferArr2));
                j2 = Math.max(j8, usFromVu);
                arrayList = arrayList7;
                arrayList.add(trex(i12));
                i3 = i12 + 1;
            } else {
                i3 = i10;
                i2 = i9;
                arrayList2 = arrayList3;
                arrayList = arrayList4;
                j = findMinimumPresentationTimestampUsAcrossTracks;
            }
            i9 = i2 + 1;
            metadataCollector2 = metadataCollector;
            arrayList3 = arrayList2;
            arrayList4 = arrayList;
            findMinimumPresentationTimestampUsAcrossTracks = j;
            i10 = i3;
        }
        int i14 = i10;
        ArrayList arrayList8 = arrayList3;
        ArrayList arrayList9 = arrayList4;
        ByteBuffer mvhd = mvhd(i14, i7, i8, j2);
        MetadataCollector metadataCollector3 = metadataCollector;
        ByteBuffer udta = udta(metadataCollector3.locationData);
        if (metadataCollector3.metadataEntries.isEmpty()) {
            byteBuffer = ByteBuffer.allocate(0);
        } else {
            byteBuffer = meta(hdlr("mdta", ""), keys(Lists.newArrayList(metadataCollector3.metadataEntries)), ilst(Lists.newArrayList(metadataCollector3.metadataEntries)));
        }
        ArrayList arrayList10 = new ArrayList();
        arrayList10.add(mvhd);
        arrayList10.add(udta);
        arrayList10.add(byteBuffer);
        arrayList10.addAll(arrayList8);
        if (z) {
            arrayList10.add(mvex(arrayList9));
        }
        ByteBuffer wrapBoxesIntoBox = BoxUtils.wrapBoxesIntoBox("moov", arrayList10);
        if (metadataCollector3.xmpData == null) {
            return wrapBoxesIntoBox;
        }
        return BoxUtils.concatenateBuffers(wrapBoxesIntoBox, uuid(XMP_UUID, ByteBuffer.wrap(metadataCollector3.xmpData.data)));
    }

    public static ByteBuffer tkhd(int i, long j, int i2, int i3, int i4, Format format) {
        ByteBuffer allocate = ByteBuffer.allocate(200);
        allocate.putInt(7);
        allocate.putInt(i2);
        allocate.putInt(i3);
        allocate.putInt(i);
        int i5 = 0;
        allocate.putInt(0);
        allocate.putInt((int) vuFromUs(j, 10000));
        allocate.putInt(0);
        allocate.putInt(0);
        allocate.putInt(0);
        allocate.putShort(MimeTypes.isAudio(format.sampleMimeType) ? (short) 256 : 0);
        allocate.putShort(0);
        allocate.put(rotationMatrixFromOrientation(i4));
        int i6 = format.width != -1 ? format.width : 0;
        if (format.height != -1) {
            i5 = format.height;
        }
        allocate.putInt(i6 << 16);
        allocate.putInt(i5 << 16);
        allocate.flip();
        return BoxUtils.wrapIntoBox("tkhd", allocate);
    }

    public static ByteBuffer mvhd(int i, int i2, int i3, long j) {
        ByteBuffer allocate = ByteBuffer.allocate(200);
        allocate.putInt(0);
        allocate.putInt(i2);
        allocate.putInt(i3);
        allocate.putInt(10000);
        allocate.putInt((int) vuFromUs(j, 10000));
        allocate.putInt(65536);
        allocate.putShort(256);
        allocate.putShort(0);
        allocate.putInt(0);
        allocate.putInt(0);
        int[] iArr = {65536, 0, 0, 0, 65536, 0, 0, 0, 1073741824};
        for (int i4 = 0; i4 < 9; i4++) {
            allocate.putInt(iArr[i4]);
        }
        for (int i5 = 0; i5 < 6; i5++) {
            allocate.putInt(0);
        }
        allocate.putInt(i);
        allocate.flip();
        return BoxUtils.wrapIntoBox("mvhd", allocate);
    }

    public static ByteBuffer mdhd(long j, int i, int i2, int i3, String str) {
        ByteBuffer allocate = ByteBuffer.allocate(200);
        allocate.putInt(0);
        allocate.putInt(i2);
        allocate.putInt(i3);
        allocate.putInt(i);
        allocate.putInt((int) j);
        allocate.putShort(languageCodeFromString(str));
        allocate.putShort(0);
        allocate.flip();
        return BoxUtils.wrapIntoBox("mdhd", allocate);
    }

    public static ByteBuffer vmhd() {
        ByteBuffer allocate = ByteBuffer.allocate(200);
        allocate.putInt(0);
        allocate.putShort(0);
        allocate.putShort(0);
        allocate.putShort(0);
        allocate.putShort(0);
        allocate.flip();
        return BoxUtils.wrapIntoBox("vmhd", allocate);
    }

    public static ByteBuffer smhd() {
        ByteBuffer allocate = ByteBuffer.allocate(200);
        allocate.putInt(0);
        allocate.putShort(0);
        allocate.putShort(0);
        allocate.flip();
        return BoxUtils.wrapIntoBox("smhd", allocate);
    }

    public static ByteBuffer nmhd() {
        ByteBuffer allocate = ByteBuffer.allocate(200);
        allocate.putInt(0);
        allocate.flip();
        return BoxUtils.wrapIntoBox("nmhd", allocate);
    }

    public static ByteBuffer textMetaDataSampleEntry(Format format) {
        ByteBuffer allocate = ByteBuffer.allocate(200);
        byte[] utf8Bytes = Util.getUtf8Bytes((String) Assertions.checkNotNull(format.sampleMimeType));
        allocate.put(utf8Bytes);
        allocate.put((byte) 0);
        allocate.put(utf8Bytes);
        allocate.put((byte) 0);
        allocate.flip();
        return BoxUtils.wrapIntoBox("mett", allocate);
    }

    public static ByteBuffer minf(ByteBuffer... byteBufferArr) {
        return BoxUtils.wrapBoxesIntoBox("minf", Arrays.asList(byteBufferArr));
    }

    public static ByteBuffer dref(ByteBuffer... byteBufferArr) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putInt(0);
        allocate.putInt(byteBufferArr.length);
        allocate.flip();
        ArrayList arrayList = new ArrayList();
        arrayList.add(allocate);
        Collections.addAll(arrayList, byteBufferArr);
        return BoxUtils.wrapBoxesIntoBox("dref", arrayList);
    }

    public static ByteBuffer dinf(ByteBuffer byteBuffer) {
        return BoxUtils.wrapIntoBox("dinf", byteBuffer);
    }

    public static ByteBuffer localUrl() {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(1);
        allocate.flip();
        return BoxUtils.wrapIntoBox("url ", allocate);
    }

    public static ByteBuffer hdlr(String str, String str2) {
        ByteBuffer allocate = ByteBuffer.allocate(200);
        allocate.putInt(0);
        allocate.putInt(0);
        allocate.put(Util.getUtf8Bytes(str));
        allocate.putInt(0);
        allocate.putInt(0);
        allocate.putInt(0);
        allocate.put(Util.getUtf8Bytes(str2));
        allocate.put((byte) 0);
        allocate.flip();
        return BoxUtils.wrapIntoBox("hdlr", allocate);
    }

    public static ByteBuffer mdia(ByteBuffer... byteBufferArr) {
        return BoxUtils.wrapBoxesIntoBox("mdia", Arrays.asList(byteBufferArr));
    }

    public static ByteBuffer trak(ByteBuffer... byteBufferArr) {
        return BoxUtils.wrapBoxesIntoBox("trak", Arrays.asList(byteBufferArr));
    }

    public static ByteBuffer udta(Mp4LocationData mp4LocationData) {
        boolean z = false;
        if (mp4LocationData == null) {
            return ByteBuffer.allocate(0);
        }
        String formatInvariant = Util.formatInvariant("%+.4f%+.4f/", Float.valueOf(mp4LocationData.latitude), Float.valueOf(mp4LocationData.longitude));
        ByteBuffer allocate = ByteBuffer.allocate(formatInvariant.length() + 2 + 2);
        allocate.putShort((short) (allocate.capacity() - 4));
        allocate.putShort(5575);
        allocate.put(Util.getUtf8Bytes(formatInvariant));
        if (allocate.limit() == allocate.capacity()) {
            z = true;
        }
        Assertions.checkState(z);
        allocate.flip();
        return BoxUtils.wrapIntoBox("udta", BoxUtils.wrapIntoBox(new byte[]{-87, 120, 121, 122}, allocate));
    }

    public static ByteBuffer keys(List<MdtaMetadataEntry> list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            i += list.get(i2).key.length() + 8;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i + 8);
        allocate.putInt(0);
        allocate.putInt(list.size());
        for (int i3 = 0; i3 < list.size(); i3++) {
            allocate.put(BoxUtils.wrapIntoBox("mdta", ByteBuffer.wrap(Util.getUtf8Bytes(list.get(i3).key))));
        }
        allocate.flip();
        return BoxUtils.wrapIntoBox(UserMetadata.KEYDATA_FILENAME, allocate);
    }

    public static ByteBuffer ilst(List<MdtaMetadataEntry> list) {
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            i2 += list.get(i3).value.length + 16 + 8;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i2);
        while (i < list.size()) {
            int i4 = i + 1;
            MdtaMetadataEntry mdtaMetadataEntry = list.get(i);
            ByteBuffer allocate2 = ByteBuffer.allocate(mdtaMetadataEntry.value.length + 8);
            allocate2.putInt(mdtaMetadataEntry.typeIndicator);
            allocate2.putInt(mdtaMetadataEntry.localeIndicator);
            allocate2.put(mdtaMetadataEntry.value);
            allocate2.flip();
            ByteBuffer wrapIntoBox = BoxUtils.wrapIntoBox("data", allocate2);
            allocate.putInt(wrapIntoBox.remaining() + 8);
            allocate.putInt(i4);
            allocate.put(wrapIntoBox);
            i = i4;
        }
        allocate.flip();
        return BoxUtils.wrapIntoBox("ilst", allocate);
    }

    public static ByteBuffer meta(ByteBuffer... byteBufferArr) {
        return BoxUtils.wrapBoxesIntoBox(Mechanism.JsonKeys.META, Arrays.asList(byteBufferArr));
    }

    public static ByteBuffer uuid(List<Byte> list, ByteBuffer byteBuffer) {
        Assertions.checkArgument(byteBuffer.remaining() > 0);
        return BoxUtils.wrapBoxesIntoBox("uuid", ImmutableList.of(ByteBuffer.wrap(Bytes.toArray(list)), byteBuffer));
    }

    public static ByteBuffer audioSampleEntry(Format format) {
        String codecSpecificFourcc = codecSpecificFourcc(format);
        ByteBuffer codecSpecificBox = codecSpecificBox(format);
        ByteBuffer allocate = ByteBuffer.allocate(codecSpecificBox.remaining() + 200);
        allocate.putInt(0);
        allocate.putShort(0);
        allocate.putShort(1);
        allocate.putInt(0);
        allocate.putInt(0);
        allocate.putShort((short) format.channelCount);
        allocate.putShort(16);
        allocate.putShort(0);
        allocate.putShort(0);
        allocate.putInt(format.sampleRate << 16);
        allocate.put(codecSpecificBox);
        allocate.flip();
        return BoxUtils.wrapIntoBox(codecSpecificFourcc, allocate);
    }

    public static ByteBuffer codecSpecificBox(Format format) {
        String str = (String) Assertions.checkNotNull(format.sampleMimeType);
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1664118616:
                if (str.equals(MimeTypes.VIDEO_H263)) {
                    c = 0;
                    break;
                }
                break;
            case -1662735862:
                if (str.equals(MimeTypes.VIDEO_AV1)) {
                    c = 1;
                    break;
                }
                break;
            case -1662541442:
                if (str.equals(MimeTypes.VIDEO_H265)) {
                    c = 2;
                    break;
                }
                break;
            case -1606874997:
                if (str.equals(MimeTypes.AUDIO_AMR_WB)) {
                    c = 3;
                    break;
                }
                break;
            case -1003765268:
                if (str.equals(MimeTypes.AUDIO_VORBIS)) {
                    c = 4;
                    break;
                }
                break;
            case -53558318:
                if (str.equals(MimeTypes.AUDIO_AAC)) {
                    c = 5;
                    break;
                }
                break;
            case 1187890754:
                if (str.equals(MimeTypes.VIDEO_MP4V)) {
                    c = 6;
                    break;
                }
                break;
            case 1331836730:
                if (str.equals(MimeTypes.VIDEO_H264)) {
                    c = 7;
                    break;
                }
                break;
            case 1503095341:
                if (str.equals(MimeTypes.AUDIO_AMR_NB)) {
                    c = 8;
                    break;
                }
                break;
            case 1504891608:
                if (str.equals(MimeTypes.AUDIO_OPUS)) {
                    c = 9;
                    break;
                }
                break;
            case 1599127257:
                if (str.equals(MimeTypes.VIDEO_VP9)) {
                    c = 10;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return d263Box(format);
            case 1:
                return av1CBox(format);
            case 2:
                return hvcCBox(format);
            case 3:
                return damrBox(-31745);
            case 4:
            case 5:
                return esdsBox(format);
            case 6:
                return esdsBox(format);
            case 7:
                return avcCBox(format);
            case 8:
                return damrBox(-32257);
            case 9:
                return dOpsBox(format);
            case 10:
                return vpcCBox(format);
            default:
                throw new IllegalArgumentException("Unsupported format: " + str);
        }
    }

    public static ByteBuffer videoSampleEntry(Format format) {
        ByteBuffer codecSpecificBox = codecSpecificBox(format);
        String codecSpecificFourcc = codecSpecificFourcc(format);
        ByteBuffer allocate = ByteBuffer.allocate(codecSpecificBox.limit() + 200);
        allocate.putInt(0);
        allocate.putShort(0);
        allocate.putShort(1);
        allocate.putShort(0);
        allocate.putShort(0);
        allocate.putInt(0);
        allocate.putInt(0);
        allocate.putInt(0);
        allocate.putShort(format.width != -1 ? (short) format.width : 0);
        allocate.putShort(format.height != -1 ? (short) format.height : 0);
        allocate.putInt(4718592);
        allocate.putInt(4718592);
        allocate.putInt(0);
        allocate.putShort(1);
        allocate.putLong(0);
        allocate.putLong(0);
        allocate.putLong(0);
        allocate.putLong(0);
        allocate.putShort(24);
        allocate.putShort(-1);
        allocate.put(codecSpecificBox);
        if (format.colorInfo != null && codecSpecificFourcc.equals("vp09")) {
            allocate.put(smDmBox(format.colorInfo));
        }
        allocate.put(paspBox());
        if (format.colorInfo != null) {
            allocate.put(colrBox(format.colorInfo));
        }
        allocate.flip();
        return BoxUtils.wrapIntoBox(codecSpecificFourcc, allocate);
    }

    public static ByteBuffer edts(long j, long j2, long j3, long j4, long j5) {
        long j6 = j2 > 0 ? j - j2 : j;
        if (j6 != 0) {
            return BoxUtils.wrapIntoBox("edts", elst(j6, j3, j4, j5));
        }
        return ByteBuffer.allocate(0);
    }

    private static ByteBuffer elstEntry(long j, long j2, int i, int i2) {
        ByteBuffer allocate = ByteBuffer.allocate(20);
        allocate.putLong(j);
        allocate.putLong(j2);
        allocate.putShort((short) i);
        allocate.putShort((short) i2);
        allocate.flip();
        return allocate;
    }

    private static ByteBuffer elst(long j, long j2, long j3, long j4) {
        ByteBuffer allocate = ByteBuffer.allocate(50);
        allocate.putInt(16777216);
        if (j > 0) {
            allocate.putInt(2);
            allocate.put(elstEntry(vuFromUs(j, j3), -1, 1, 0));
            allocate.put(elstEntry(vuFromUs(j2, j3), 0, 1, 0));
        } else {
            allocate.putInt(1);
            allocate.put(elstEntry(vuFromUs(j2 - Math.abs(j), j3), vuFromUs(Math.abs(j), j4), 1, 0));
        }
        allocate.flip();
        return BoxUtils.wrapIntoBox("elst", allocate);
    }

    public static List<Integer> convertPresentationTimestampsToDurationsVu(List<MediaCodec.BufferInfo> list, int i, int i2, long j) {
        long j2;
        int i3 = i;
        long j3 = j;
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        if (list.isEmpty()) {
            return arrayList2;
        }
        boolean z = false;
        long j4 = 0;
        int i4 = 0;
        boolean z2 = false;
        while (i4 < list.size()) {
            long j5 = list.get(i4).presentationTimeUs;
            arrayList.add(Long.valueOf(j5));
            if (j5 < j4) {
                z2 = true;
            }
            i4++;
            j4 = j5;
        }
        if (z2) {
            Collections.sort(arrayList);
        }
        long longValue = ((Long) arrayList.get(0)).longValue();
        int i5 = 1;
        while (i5 < arrayList.size()) {
            long longValue2 = ((Long) arrayList.get(i5)).longValue();
            long j6 = longValue2;
            long vuFromUs = vuFromUs(longValue2 - longValue, (long) i3);
            Assertions.checkState(vuFromUs <= 2147483647L, "Only 32-bit sample duration is allowed");
            arrayList2.add(Integer.valueOf((int) vuFromUs));
            i5++;
            longValue = j6;
        }
        if (j3 != C.TIME_UNSET) {
            long j7 = (long) i3;
            j2 = vuFromUs(j3, j7) - vuFromUs(longValue, j7);
            if (j2 <= 2147483647L) {
                z = true;
            }
            Assertions.checkState(z, "Only 32-bit sample duration is allowed");
        } else {
            j2 = -1;
        }
        arrayList2.add(Integer.valueOf(getLastSampleDurationVu(arrayList2, i2, (int) j2)));
        return arrayList2;
    }

    public static ByteBuffer stts(List<Integer> list) {
        ByteBuffer allocate = ByteBuffer.allocate((list.size() * 8) + 200);
        allocate.putInt(0);
        int position = allocate.position();
        allocate.putInt(0);
        int i = -1;
        long j = -1;
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            int intValue = list.get(i3).intValue();
            long j2 = (long) intValue;
            if (j != j2) {
                int position2 = allocate.position();
                allocate.putInt(1);
                allocate.putInt(intValue);
                i2++;
                i = position2;
                j = j2;
            } else {
                allocate.putInt(i, allocate.getInt(i) + 1);
            }
        }
        allocate.putInt(position, i2);
        allocate.flip();
        return BoxUtils.wrapIntoBox("stts", allocate);
    }

    public static ByteBuffer ctts(List<MediaCodec.BufferInfo> list, List<Integer> list2, int i) {
        List<Integer> calculateSampleCompositionTimeOffsets = calculateSampleCompositionTimeOffsets(list, list2, i);
        if (calculateSampleCompositionTimeOffsets.isEmpty()) {
            return ByteBuffer.allocate(0);
        }
        ByteBuffer allocate = ByteBuffer.allocate((calculateSampleCompositionTimeOffsets.size() * 2 * 4) + 8);
        allocate.putInt(16777216);
        int position = allocate.position();
        allocate.putInt(0);
        int i2 = -1;
        int i3 = -1;
        int i4 = 0;
        for (int i5 = 0; i5 < calculateSampleCompositionTimeOffsets.size(); i5++) {
            int intValue = calculateSampleCompositionTimeOffsets.get(i5).intValue();
            if (i2 != intValue) {
                int position2 = allocate.position();
                allocate.putInt(1);
                allocate.putInt(intValue);
                i4++;
                i3 = position2;
                i2 = intValue;
            } else {
                allocate.putInt(i3, allocate.getInt(i3) + 1);
            }
        }
        allocate.putInt(position, i4);
        allocate.flip();
        return BoxUtils.wrapIntoBox("ctts", allocate);
    }

    public static List<Integer> calculateSampleCompositionTimeOffsets(List<MediaCodec.BufferInfo> list, List<Integer> list2, int i) {
        List<MediaCodec.BufferInfo> list3 = list;
        ArrayList arrayList = new ArrayList(list.size());
        if (list.isEmpty()) {
            return arrayList;
        }
        boolean z = false;
        long j = list3.get(0).presentationTimeUs;
        long j2 = 0;
        int i2 = 0;
        boolean z2 = false;
        long j3 = 0;
        while (i2 < list.size()) {
            long j4 = list3.get(i2).presentationTimeUs - j;
            long vuFromUs = vuFromUs(j4, (long) i) - j2;
            if (vuFromUs <= 2147483647L) {
                z = true;
            }
            Assertions.checkState(z, "Only 32-bit composition offset is allowed");
            long j5 = j;
            j2 += (long) list2.get(i2).intValue();
            arrayList.add(Integer.valueOf((int) vuFromUs));
            if (j4 < j3) {
                z2 = true;
            }
            i2++;
            list3 = list;
            j3 = j4;
            j = j5;
            z = false;
        }
        if (!z2) {
            arrayList.clear();
        }
        return arrayList;
    }

    public static ByteBuffer stsz(List<MediaCodec.BufferInfo> list) {
        ByteBuffer allocate = ByteBuffer.allocate((list.size() * 4) + 200);
        allocate.putInt(0);
        allocate.putInt(0);
        allocate.putInt(list.size());
        for (int i = 0; i < list.size(); i++) {
            allocate.putInt(list.get(i).size);
        }
        allocate.flip();
        return BoxUtils.wrapIntoBox("stsz", allocate);
    }

    public static ByteBuffer stsc(List<Integer> list) {
        ByteBuffer allocate = ByteBuffer.allocate((list.size() * 12) + 200);
        allocate.putInt(0);
        allocate.putInt(list.size());
        int i = 1;
        for (int i2 = 0; i2 < list.size(); i2++) {
            int intValue = list.get(i2).intValue();
            allocate.putInt(i);
            allocate.putInt(intValue);
            allocate.putInt(1);
            i++;
        }
        allocate.flip();
        return BoxUtils.wrapIntoBox("stsc", allocate);
    }

    public static ByteBuffer stco(List<Long> list) {
        ByteBuffer allocate = ByteBuffer.allocate((list.size() * 4) + 8);
        allocate.putInt(0);
        allocate.putInt(list.size());
        for (int i = 0; i < list.size(); i++) {
            long longValue = list.get(i).longValue();
            Assertions.checkState(longValue <= MuxerUtil.UNSIGNED_INT_MAX_VALUE, "Only 32-bit chunk offset is allowed");
            allocate.putInt((int) longValue);
        }
        allocate.flip();
        return BoxUtils.wrapIntoBox("stco", allocate);
    }

    public static ByteBuffer co64(List<Long> list) {
        ByteBuffer allocate = ByteBuffer.allocate((list.size() * 2 * 4) + 8);
        allocate.putInt(0);
        allocate.putInt(list.size());
        for (int i = 0; i < list.size(); i++) {
            allocate.putLong(list.get(i).longValue());
        }
        allocate.flip();
        return BoxUtils.wrapIntoBox("co64", allocate);
    }

    public static ByteBuffer stss(List<MediaCodec.BufferInfo> list) {
        ByteBuffer allocate = ByteBuffer.allocate((list.size() * 4) + 200);
        allocate.putInt(0);
        int position = allocate.position();
        allocate.putInt(list.size());
        int i = 0;
        int i2 = 1;
        for (int i3 = 0; i3 < list.size(); i3++) {
            if ((list.get(i3).flags & 1) > 0) {
                allocate.putInt(i2);
                i++;
            }
            i2++;
        }
        allocate.putInt(position, i);
        allocate.flip();
        return BoxUtils.wrapIntoBox("stss", allocate);
    }

    public static ByteBuffer stsd(ByteBuffer byteBuffer) {
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.limit() + 200);
        allocate.putInt(0);
        allocate.putInt(1);
        allocate.put(byteBuffer);
        allocate.flip();
        return BoxUtils.wrapIntoBox("stsd", allocate);
    }

    public static ByteBuffer stbl(ByteBuffer... byteBufferArr) {
        return BoxUtils.wrapBoxesIntoBox("stbl", Arrays.asList(byteBufferArr));
    }

    public static ByteBuffer ftyp() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ByteBuffer.wrap(Util.getUtf8Bytes("isom")));
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(131072);
        allocate.flip();
        arrayList.add(allocate);
        String[] strArr = {"isom", "iso2", "mp41"};
        for (int i = 0; i < 3; i++) {
            arrayList.add(ByteBuffer.wrap(Util.getUtf8Bytes(strArr[i])));
        }
        return BoxUtils.wrapBoxesIntoBox("ftyp", arrayList);
    }

    public static ByteBuffer moof(ByteBuffer byteBuffer, List<ByteBuffer> list) {
        return BoxUtils.wrapBoxesIntoBox("moof", new ImmutableList.Builder().add((Object) byteBuffer).addAll((Iterable) list).build());
    }

    public static ByteBuffer mfhd(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putInt(0);
        allocate.putInt(i);
        allocate.flip();
        return BoxUtils.wrapIntoBox("mfhd", allocate);
    }

    public static ByteBuffer traf(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        return BoxUtils.wrapBoxesIntoBox("traf", ImmutableList.of(byteBuffer, byteBuffer2));
    }

    public static ByteBuffer tfhd(int i, long j) {
        ByteBuffer allocate = ByteBuffer.allocate(16);
        allocate.putInt(1);
        allocate.putInt(i);
        allocate.putLong(j);
        allocate.flip();
        return BoxUtils.wrapIntoBox("tfhd", allocate);
    }

    public static ByteBuffer trun(List<FragmentedMp4Writer.SampleMetadata> list, int i, boolean z) {
        ByteBuffer allocate = ByteBuffer.allocate(getTrunBoxContentSize(list.size(), z));
        allocate.putInt(z ? 16781057 : 16779009);
        allocate.putInt(list.size());
        allocate.putInt(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            FragmentedMp4Writer.SampleMetadata sampleMetadata = list.get(i2);
            allocate.putInt(sampleMetadata.durationVu);
            allocate.putInt(sampleMetadata.size);
            allocate.putInt((sampleMetadata.flags & 1) != 0 ? TRUN_BOX_SYNC_SAMPLE_FLAGS : TRUN_BOX_NON_SYNC_SAMPLE_FLAGS);
            if (z) {
                allocate.putInt(sampleMetadata.compositionTimeOffsetVu);
            }
        }
        allocate.flip();
        return BoxUtils.wrapIntoBox("trun", allocate);
    }

    public static ByteBuffer mvex(List<ByteBuffer> list) {
        return BoxUtils.wrapBoxesIntoBox("mvex", list);
    }

    public static ByteBuffer trex(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(24);
        allocate.putInt(0);
        allocate.putInt(i);
        allocate.putInt(1);
        allocate.putInt(0);
        allocate.putInt(0);
        allocate.putInt(0);
        allocate.flip();
        return BoxUtils.wrapIntoBox("trex", allocate);
    }

    public static ByteBuffer getEdvdBoxHeader(long j) {
        ByteBuffer allocate = ByteBuffer.allocate(16);
        allocate.putInt(1);
        allocate.put(Util.getUtf8Bytes("edvd"));
        allocate.putLong(j + 16);
        allocate.flip();
        return allocate;
    }

    private static String bcp47LanguageTagToIso3(String str) {
        if (str == null) {
            return null;
        }
        Locale forLanguageTag = Locale.forLanguageTag(str);
        return forLanguageTag.getISO3Language().isEmpty() ? str : forLanguageTag.getISO3Language();
    }

    private static long usFromVu(long j, long j2) {
        return Util.scaleLargeValue(j, 1000000, j2, RoundingMode.HALF_UP);
    }

    private static int getLastSampleDurationVu(List<Integer> list, int i, int i2) {
        if (i == 0) {
            return 0;
        }
        if (i != 1) {
            throw new IllegalArgumentException("Unexpected value for the last frame duration behavior " + i);
        } else if (i2 != -1) {
            return i2;
        } else {
            if (list.size() < 2) {
                return 0;
            }
            return ((Integer) Iterables.getLast(list)).intValue();
        }
    }

    private static ByteBuffer d263Box(Format format) {
        ByteBuffer allocate = ByteBuffer.allocate(7);
        allocate.put("    ".getBytes(StandardCharsets.UTF_8));
        allocate.put((byte) 0);
        Pair<Integer, Integer> codecProfileAndLevel = CodecSpecificDataUtil.getCodecProfileAndLevel(format);
        if (codecProfileAndLevel == null) {
            codecProfileAndLevel = new Pair<>(1, 1);
        }
        allocate.put(((Integer) codecProfileAndLevel.second).byteValue());
        allocate.put(((Integer) codecProfileAndLevel.first).byteValue());
        allocate.flip();
        return BoxUtils.wrapIntoBox("d263", allocate);
    }

    private static ByteBuffer avcCBox(Format format) {
        Assertions.checkArgument(format.initializationData.size() >= 2, "csd-0 and/or csd-1 not found in the format for avcC box.");
        byte[] bArr = format.initializationData.get(0);
        Assertions.checkArgument(bArr.length > 0, "csd-0 is empty for avcC box.");
        byte[] bArr2 = format.initializationData.get(1);
        Assertions.checkArgument(bArr2.length > 0, "csd-1 is empty for avcC box.");
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        ByteBuffer wrap2 = ByteBuffer.wrap(bArr2);
        ByteBuffer allocate = ByteBuffer.allocate(wrap.limit() + wrap2.limit() + 200);
        allocate.put((byte) 1);
        ImmutableList<ByteBuffer> findNalUnits = AnnexBUtils.findNalUnits(wrap);
        Assertions.checkArgument(findNalUnits.size() == 1, "SPS data not found in csd0 for avcC box.");
        ByteBuffer byteBuffer = (ByteBuffer) findNalUnits.get(0);
        int remaining = byteBuffer.remaining();
        byte[] bArr3 = new byte[remaining];
        byteBuffer.get(bArr3);
        byteBuffer.rewind();
        NalUnitUtil.SpsData parseSpsNalUnit = NalUnitUtil.parseSpsNalUnit(bArr3, 0, remaining);
        allocate.put((byte) parseSpsNalUnit.profileIdc);
        allocate.put((byte) parseSpsNalUnit.constraintsFlagsAndReservedZero2Bits);
        allocate.put((byte) parseSpsNalUnit.levelIdc);
        allocate.put((byte) -1);
        allocate.put((byte) -31);
        allocate.putShort((short) byteBuffer.remaining());
        allocate.put(byteBuffer);
        byteBuffer.rewind();
        ImmutableList<ByteBuffer> findNalUnits2 = AnnexBUtils.findNalUnits(wrap2);
        Assertions.checkState(findNalUnits2.size() == 1, "PPS data not found in csd1.");
        allocate.put((byte) 1);
        ByteBuffer byteBuffer2 = (ByteBuffer) findNalUnits2.get(0);
        allocate.putShort((short) byteBuffer2.remaining());
        allocate.put(byteBuffer2);
        byteBuffer2.rewind();
        allocate.flip();
        return BoxUtils.wrapIntoBox("avcC", allocate);
    }

    private static ByteBuffer hvcCBox(Format format) {
        Assertions.checkArgument(!format.initializationData.isEmpty(), "csd-0 not found in the format for hvcC box.");
        byte[] bArr = format.initializationData.get(0);
        Assertions.checkArgument(bArr.length > 0, "csd-0 is empty for hvcC box.");
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        ByteBuffer allocate = ByteBuffer.allocate(wrap.limit() + 200);
        ImmutableList<ByteBuffer> findNalUnits = AnnexBUtils.findNalUnits(wrap);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < findNalUnits.size(); i++) {
            arrayList.add(AnnexBUtils.stripEmulationPrevention((ByteBuffer) findNalUnits.get(i)));
        }
        allocate.put((byte) 1);
        ByteBuffer byteBuffer = (ByteBuffer) arrayList.get(0);
        if (byteBuffer.get(byteBuffer.position()) == 64) {
            allocate.put(byteBuffer.get(6));
            allocate.putInt(byteBuffer.getInt(7));
            allocate.putInt(byteBuffer.getInt(11));
            allocate.putShort(byteBuffer.getShort(15));
            allocate.put(byteBuffer.get(17));
            allocate.putShort(-4096);
            allocate.put((byte) -4);
            ByteBuffer byteBuffer2 = (ByteBuffer) findNalUnits.get(1);
            int remaining = byteBuffer2.remaining();
            byte[] bArr2 = new byte[remaining];
            byteBuffer2.get(bArr2);
            byteBuffer2.rewind();
            NalUnitUtil.H265SpsData parseH265SpsNalUnit = NalUnitUtil.parseH265SpsNalUnit(bArr2, 0, remaining, (NalUnitUtil.H265VpsData) null);
            allocate.put((byte) (parseH265SpsNalUnit.chromaFormatIdc | 252));
            allocate.put((byte) (parseH265SpsNalUnit.bitDepthLumaMinus8 | 248));
            allocate.put((byte) (parseH265SpsNalUnit.bitDepthChromaMinus8 | 248));
            allocate.putShort(0);
            allocate.put((byte) 15);
            allocate.put((byte) findNalUnits.size());
            for (int i2 = 0; i2 < findNalUnits.size(); i2++) {
                ByteBuffer byteBuffer3 = (ByteBuffer) findNalUnits.get(i2);
                allocate.put((byte) ((byteBuffer3.get(0) >> 1) & 63));
                allocate.putShort(1);
                allocate.putShort((short) byteBuffer3.limit());
                allocate.put(byteBuffer3);
            }
            allocate.flip();
            return BoxUtils.wrapIntoBox("hvcC", allocate);
        }
        throw new IllegalArgumentException("First NALU in csd-0 is not the VPS.");
    }

    private static ByteBuffer av1CBox(Format format) {
        boolean z = true;
        Assertions.checkArgument(!format.initializationData.isEmpty(), "csd-0 is not found in the format for av1C box");
        byte[] bArr = format.initializationData.get(0);
        if (bArr.length <= 0) {
            z = false;
        }
        Assertions.checkArgument(z, "csd-0 is empty for av1C box.");
        return BoxUtils.wrapIntoBox("av1C", ByteBuffer.wrap(bArr));
    }

    private static ByteBuffer vpcCBox(Format format) {
        int i;
        int i2;
        int i3 = 1;
        Assertions.checkArgument(!format.initializationData.isEmpty(), "csd-0 is not found in the format for vpcC box");
        byte[] bArr = format.initializationData.get(0);
        Assertions.checkArgument(bArr.length > 3, "csd-0 for vp9 is invalid.");
        if (Ints.fromByteArray(bArr) == 16777216) {
            return BoxUtils.wrapIntoBox("vpcC", ByteBuffer.wrap(bArr));
        }
        ByteBuffer allocate = ByteBuffer.allocate(200);
        allocate.putInt(16777216);
        allocate.put(parseVp9CodecPrivateFromCsd(bArr, (format.colorInfo == null || format.colorInfo.colorRange == -1) ? 0 : format.colorInfo.colorRange));
        if (format.colorInfo != null) {
            i3 = ColorInfo.colorSpaceToIsoColorPrimaries(format.colorInfo.colorSpace);
            i2 = ColorInfo.colorTransferToIsoTransferCharacteristics(format.colorInfo.colorTransfer);
            i = ColorInfo.colorSpaceToIsoMatrixCoefficients(format.colorInfo.colorSpace);
        } else {
            i = 1;
            i2 = 1;
        }
        allocate.put((byte) i3);
        allocate.put((byte) i2);
        allocate.put((byte) i);
        allocate.putShort(0);
        allocate.flip();
        return BoxUtils.wrapIntoBox("vpcC", allocate);
    }

    private static ByteBuffer parseVp9CodecPrivateFromCsd(byte[] bArr, int i) {
        byte b = 10;
        byte b2 = 8;
        byte b3 = 0;
        byte b4 = 0;
        for (int i2 = 0; i2 < bArr.length; i2 += 3) {
            byte b5 = bArr[i2];
            int i3 = i2 + 2;
            if (b5 == 1) {
                b3 = bArr[i3];
            } else if (b5 == 2) {
                b = bArr[i3];
            } else if (b5 == 3) {
                b2 = bArr[i3];
            } else if (b5 == 4) {
                b4 = bArr[i3];
            }
        }
        ByteBuffer allocate = ByteBuffer.allocate(3);
        allocate.put(b3);
        allocate.put(b);
        allocate.put((byte) (i | (b2 << 4) | (b4 << 1)));
        allocate.flip();
        return allocate;
    }

    private static ByteBuffer smDmBox(ColorInfo colorInfo) {
        byte[] bArr = colorInfo.hdrStaticInfo;
        if (bArr == null) {
            return ByteBuffer.allocate(0);
        }
        ByteBuffer allocate = ByteBuffer.allocate(200);
        allocate.putInt(0);
        allocate.put(bArr);
        allocate.flip();
        return BoxUtils.wrapIntoBox("SmDm", allocate);
    }

    private static ByteBuffer paspBox() {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putInt(65536);
        allocate.putInt(65536);
        allocate.rewind();
        return BoxUtils.wrapIntoBox("pasp", allocate);
    }

    private static ByteBuffer colrBox(ColorInfo colorInfo) {
        ByteBuffer allocate = ByteBuffer.allocate(20);
        allocate.put((byte) 110);
        allocate.put((byte) 99);
        allocate.put((byte) 108);
        allocate.put((byte) 120);
        short colorSpaceToIsoColorPrimaries = (short) ColorInfo.colorSpaceToIsoColorPrimaries(colorInfo.colorSpace);
        short colorTransferToIsoTransferCharacteristics = (short) ColorInfo.colorTransferToIsoTransferCharacteristics(colorInfo.colorTransfer);
        short colorSpaceToIsoMatrixCoefficients = (short) ColorInfo.colorSpaceToIsoMatrixCoefficients(colorInfo.colorSpace);
        byte b = colorInfo.colorRange == 1 ? Byte.MIN_VALUE : 0;
        allocate.putShort(colorSpaceToIsoColorPrimaries);
        allocate.putShort(colorTransferToIsoTransferCharacteristics);
        allocate.putShort(colorSpaceToIsoMatrixCoefficients);
        allocate.put(b);
        allocate.flip();
        return BoxUtils.wrapIntoBox("colr", allocate);
    }

    private static String codecSpecificFourcc(Format format) {
        String str = (String) Assertions.checkNotNull(format.sampleMimeType);
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1664118616:
                if (str.equals(MimeTypes.VIDEO_H263)) {
                    c = 0;
                    break;
                }
                break;
            case -1662735862:
                if (str.equals(MimeTypes.VIDEO_AV1)) {
                    c = 1;
                    break;
                }
                break;
            case -1662541442:
                if (str.equals(MimeTypes.VIDEO_H265)) {
                    c = 2;
                    break;
                }
                break;
            case -1606874997:
                if (str.equals(MimeTypes.AUDIO_AMR_WB)) {
                    c = 3;
                    break;
                }
                break;
            case -1003765268:
                if (str.equals(MimeTypes.AUDIO_VORBIS)) {
                    c = 4;
                    break;
                }
                break;
            case -53558318:
                if (str.equals(MimeTypes.AUDIO_AAC)) {
                    c = 5;
                    break;
                }
                break;
            case 1187890754:
                if (str.equals(MimeTypes.VIDEO_MP4V)) {
                    c = 6;
                    break;
                }
                break;
            case 1331836730:
                if (str.equals(MimeTypes.VIDEO_H264)) {
                    c = 7;
                    break;
                }
                break;
            case 1503095341:
                if (str.equals(MimeTypes.AUDIO_AMR_NB)) {
                    c = 8;
                    break;
                }
                break;
            case 1504891608:
                if (str.equals(MimeTypes.AUDIO_OPUS)) {
                    c = 9;
                    break;
                }
                break;
            case 1599127257:
                if (str.equals(MimeTypes.VIDEO_VP9)) {
                    c = 10;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "s263";
            case 1:
                return "av01";
            case 2:
                return "hvc1";
            case 3:
                return "sawb";
            case 4:
            case 5:
                return "mp4a";
            case 6:
                return "mp4v-es";
            case 7:
                return "avc1";
            case 8:
                return "samr";
            case 9:
                return "Opus";
            case 10:
                return "vp09";
            default:
                throw new IllegalArgumentException("Unsupported format: " + str);
        }
    }

    private static ByteBuffer esdsBox(Format format) {
        Assertions.checkArgument(!format.initializationData.isEmpty(), "csd-0 not found in the format for esds box.");
        int i = 0;
        byte[] bArr = format.initializationData.get(0);
        Assertions.checkArgument(bArr.length > 0, "csd-0 is empty for esds box.");
        String str = (String) Assertions.checkNotNull(format.sampleMimeType);
        ByteBuffer vorbisInitializationData = str.equals(MimeTypes.AUDIO_VORBIS) ? getVorbisInitializationData(format) : ByteBuffer.wrap(bArr);
        int i2 = format.peakBitrate;
        int i3 = format.averageBitrate;
        boolean isVideo = MimeTypes.isVideo(str);
        int remaining = vorbisInitializationData.remaining();
        ByteBuffer sizeBuffer = getSizeBuffer(remaining);
        ByteBuffer sizeBuffer2 = getSizeBuffer(sizeBuffer.remaining() + remaining + 14);
        ByteBuffer sizeBuffer3 = getSizeBuffer(sizeBuffer.remaining() + remaining + sizeBuffer2.remaining() + 21);
        ByteBuffer allocate = ByteBuffer.allocate(remaining + 200);
        allocate.putInt(0);
        allocate.put((byte) 3);
        allocate.put(sizeBuffer3);
        allocate.putShort(0);
        allocate.put(isVideo ? Ascii.US : 0);
        allocate.put((byte) 4);
        allocate.put(sizeBuffer2);
        allocate.put(((Byte) Assertions.checkNotNull(MimeTypes.getMp4ObjectTypeFromMimeType(str))).byteValue());
        allocate.put((byte) ((isVideo ? 16 : 20) | 1));
        allocate.putShort((short) (((isVideo ? 96000 : ViewUtils.EDGE_TO_EDGE_FLAGS) >> 8) & 65535));
        allocate.put((byte) 0);
        if (i2 == -1) {
            i2 = 0;
        }
        allocate.putInt(i2);
        if (i3 != -1) {
            i = i3;
        }
        allocate.putInt(i);
        allocate.put((byte) 5);
        allocate.put(sizeBuffer);
        allocate.put(vorbisInitializationData);
        vorbisInitializationData.rewind();
        allocate.put((byte) 6);
        allocate.put((byte) 1);
        allocate.put((byte) 2);
        allocate.flip();
        return BoxUtils.wrapIntoBox("esds", allocate);
    }

    private static ByteBuffer getSizeBuffer(int i) {
        ArrayDeque arrayDeque = new ArrayDeque();
        int i2 = 0;
        while (true) {
            arrayDeque.push(Byte.valueOf((byte) (i2 | (i & 127))));
            i >>= 7;
            if (i <= 0) {
                break;
            }
            i2 = 128;
        }
        ByteBuffer allocate = ByteBuffer.allocate(arrayDeque.size());
        while (!arrayDeque.isEmpty()) {
            allocate.put(((Byte) arrayDeque.removeFirst()).byteValue());
        }
        allocate.flip();
        return allocate;
    }

    private static ByteBuffer getVorbisInitializationData(Format format) {
        boolean z = true;
        Assertions.checkArgument(format.initializationData.size() > 1, "csd-1 should contain setup header for Vorbis.");
        byte[] bArr = format.initializationData.get(0);
        int length = (bArr.length / 255) + 1;
        byte[] bArr2 = new byte[length];
        Arrays.fill(bArr2, (byte) -1);
        bArr2[length - 1] = (byte) (bArr.length % 255);
        byte[] bArr3 = format.initializationData.get(1);
        if (bArr3.length <= 0) {
            z = false;
        }
        Assertions.checkArgument(z, "csd-1 should be present and contain setup header for Vorbis.");
        ByteBuffer allocate = ByteBuffer.allocate(length + bArr.length + bArr3.length + 2);
        allocate.put((byte) 2);
        allocate.put(bArr2);
        allocate.put((byte) 0);
        allocate.put(bArr);
        allocate.put(bArr3);
        allocate.flip();
        return allocate;
    }

    private static ByteBuffer damrBox(short s) {
        ByteBuffer allocate = ByteBuffer.allocate(200);
        allocate.put("    ".getBytes(StandardCharsets.UTF_8));
        allocate.put((byte) 0);
        allocate.putShort(s);
        allocate.put((byte) 0);
        allocate.put((byte) 1);
        allocate.flip();
        return BoxUtils.wrapIntoBox("damr", allocate);
    }

    private static ByteBuffer dOpsBox(Format format) {
        boolean z = true;
        Assertions.checkArgument(!format.initializationData.isEmpty(), "csd-0 not found in the format for dOps box.");
        byte[] bArr = format.initializationData.get(0);
        if (bArr.length < 8) {
            z = false;
        }
        Assertions.checkArgument(z, "As csd0 contains 'OpusHead' in first 8 bytes, csd0 length should be greater than 8");
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length);
        allocate.put(bArr, 8, bArr.length - 8);
        allocate.flip();
        return BoxUtils.wrapIntoBox("dOps", allocate);
    }

    private static short languageCodeFromString(String str) {
        if (str == null) {
            return 0;
        }
        byte[] utf8Bytes = Util.getUtf8Bytes(str);
        if (utf8Bytes.length != 3) {
            return 0;
        }
        return (short) (((utf8Bytes[2] & Ascii.US) + ((utf8Bytes[1] & Ascii.US) << 5) + ((utf8Bytes[0] & Ascii.US) << 10)) & 32767);
    }

    private static byte[] rotationMatrixFromOrientation(int i) {
        if (i == 0) {
            return Util.toByteArray(65536, 0, 0, 0, 65536, 0, 0, 0, 1073741824);
        }
        if (i == 90) {
            return Util.toByteArray(0, 65536, 0, -65536, 0, 0, 0, 0, 1073741824);
        } else if (i == 180) {
            return Util.toByteArray(-65536, 0, 0, 0, -65536, 0, 0, 0, 1073741824);
        } else if (i == 270) {
            return Util.toByteArray(0, -65536, 0, 65536, 0, 0, 0, 0, 1073741824);
        } else {
            throw new IllegalArgumentException("invalid orientation " + i);
        }
    }

    private static long vuFromUs(long j, long j2) {
        return Util.scaleLargeValue(j, j2, 1000000, RoundingMode.HALF_UP);
    }

    private static long findMinimumPresentationTimestampUsAcrossTracks(List<Track> list) {
        long j = Long.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            Track track = list.get(i);
            if (!track.writtenSamples.isEmpty()) {
                j = Math.min(track.writtenSamples.get(0).presentationTimeUs, j);
            }
        }
        return j != Long.MAX_VALUE ? j : C.TIME_UNSET;
    }
}
