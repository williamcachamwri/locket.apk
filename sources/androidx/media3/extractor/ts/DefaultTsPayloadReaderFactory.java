package androidx.media3.extractor.ts;

import android.util.SparseArray;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.SignedBytes;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public final class DefaultTsPayloadReaderFactory implements TsPayloadReader.Factory {
    private static final int DESCRIPTOR_TAG_CAPTION_SERVICE = 134;
    public static final int FLAG_ALLOW_NON_IDR_KEYFRAMES = 1;
    public static final int FLAG_DETECT_ACCESS_UNITS = 8;
    public static final int FLAG_ENABLE_HDMV_DTS_AUDIO_STREAMS = 64;
    public static final int FLAG_IGNORE_AAC_STREAM = 2;
    public static final int FLAG_IGNORE_H264_STREAM = 4;
    public static final int FLAG_IGNORE_SPLICE_INFO_STREAM = 16;
    public static final int FLAG_OVERRIDE_CAPTION_DESCRIPTORS = 32;
    private final List<Format> closedCaptionFormats;
    private final int flags;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public DefaultTsPayloadReaderFactory() {
        this(0);
    }

    public DefaultTsPayloadReaderFactory(int i) {
        this(i, ImmutableList.of());
    }

    public DefaultTsPayloadReaderFactory(int i, List<Format> list) {
        this.flags = i;
        this.closedCaptionFormats = list;
    }

    public SparseArray<TsPayloadReader> createInitialPayloadReaders() {
        return new SparseArray<>();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0067, code lost:
        return new androidx.media3.extractor.ts.PesReader(new androidx.media3.extractor.ts.Ac3Reader(r6.language, r6.getRoleFlags()));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media3.extractor.ts.TsPayloadReader createPayloadReader(int r5, androidx.media3.extractor.ts.TsPayloadReader.EsInfo r6) {
        /*
            r4 = this;
            r0 = 2
            if (r5 == r0) goto L_0x0150
            r1 = 3
            if (r5 == r1) goto L_0x013f
            r1 = 4
            if (r5 == r1) goto L_0x013f
            r2 = 21
            if (r5 == r2) goto L_0x0134
            r2 = 27
            r3 = 0
            if (r5 == r2) goto L_0x0113
            r1 = 36
            if (r5 == r1) goto L_0x0104
            r1 = 45
            if (r5 == r1) goto L_0x00f9
            r1 = 89
            if (r5 == r1) goto L_0x00ec
            r1 = 172(0xac, float:2.41E-43)
            if (r5 == r1) goto L_0x00db
            r1 = 257(0x101, float:3.6E-43)
            if (r5 == r1) goto L_0x00ce
            r1 = 138(0x8a, float:1.93E-43)
            if (r5 == r1) goto L_0x00bb
            r1 = 139(0x8b, float:1.95E-43)
            if (r5 == r1) goto L_0x00a8
            switch(r5) {
                case 15: goto L_0x008f;
                case 16: goto L_0x0080;
                case 17: goto L_0x0068;
                default: goto L_0x0031;
            }
        L_0x0031:
            switch(r5) {
                case 128: goto L_0x0150;
                case 129: goto L_0x0057;
                case 130: goto L_0x004e;
                default: goto L_0x0034;
            }
        L_0x0034:
            switch(r5) {
                case 134: goto L_0x0038;
                case 135: goto L_0x0057;
                case 136: goto L_0x00bb;
                default: goto L_0x0037;
            }
        L_0x0037:
            return r3
        L_0x0038:
            r5 = 16
            boolean r5 = r4.isSet(r5)
            if (r5 == 0) goto L_0x0041
            goto L_0x004d
        L_0x0041:
            androidx.media3.extractor.ts.SectionReader r3 = new androidx.media3.extractor.ts.SectionReader
            androidx.media3.extractor.ts.PassthroughSectionPayloadReader r5 = new androidx.media3.extractor.ts.PassthroughSectionPayloadReader
            java.lang.String r6 = "application/x-scte35"
            r5.<init>(r6)
            r3.<init>(r5)
        L_0x004d:
            return r3
        L_0x004e:
            r5 = 64
            boolean r5 = r4.isSet(r5)
            if (r5 != 0) goto L_0x00bb
            return r3
        L_0x0057:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.Ac3Reader r0 = new androidx.media3.extractor.ts.Ac3Reader
            java.lang.String r1 = r6.language
            int r6 = r6.getRoleFlags()
            r0.<init>(r1, r6)
            r5.<init>(r0)
            return r5
        L_0x0068:
            boolean r5 = r4.isSet(r0)
            if (r5 == 0) goto L_0x006f
            goto L_0x007f
        L_0x006f:
            androidx.media3.extractor.ts.PesReader r3 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.LatmReader r5 = new androidx.media3.extractor.ts.LatmReader
            java.lang.String r0 = r6.language
            int r6 = r6.getRoleFlags()
            r5.<init>(r0, r6)
            r3.<init>(r5)
        L_0x007f:
            return r3
        L_0x0080:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.H263Reader r0 = new androidx.media3.extractor.ts.H263Reader
            androidx.media3.extractor.ts.UserDataReader r6 = r4.buildUserDataReader(r6)
            r0.<init>(r6)
            r5.<init>(r0)
            return r5
        L_0x008f:
            boolean r5 = r4.isSet(r0)
            if (r5 == 0) goto L_0x0096
            goto L_0x00a7
        L_0x0096:
            androidx.media3.extractor.ts.PesReader r3 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.AdtsReader r5 = new androidx.media3.extractor.ts.AdtsReader
            java.lang.String r0 = r6.language
            int r6 = r6.getRoleFlags()
            r1 = 0
            r5.<init>(r1, r0, r6)
            r3.<init>(r5)
        L_0x00a7:
            return r3
        L_0x00a8:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.DtsReader r0 = new androidx.media3.extractor.ts.DtsReader
            java.lang.String r1 = r6.language
            int r6 = r6.getRoleFlags()
            r2 = 5408(0x1520, float:7.578E-42)
            r0.<init>(r1, r6, r2)
            r5.<init>(r0)
            return r5
        L_0x00bb:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.DtsReader r0 = new androidx.media3.extractor.ts.DtsReader
            java.lang.String r1 = r6.language
            int r6 = r6.getRoleFlags()
            r2 = 4096(0x1000, float:5.74E-42)
            r0.<init>(r1, r6, r2)
            r5.<init>(r0)
            return r5
        L_0x00ce:
            androidx.media3.extractor.ts.SectionReader r5 = new androidx.media3.extractor.ts.SectionReader
            androidx.media3.extractor.ts.PassthroughSectionPayloadReader r6 = new androidx.media3.extractor.ts.PassthroughSectionPayloadReader
            java.lang.String r0 = "application/vnd.dvb.ait"
            r6.<init>(r0)
            r5.<init>(r6)
            return r5
        L_0x00db:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.Ac4Reader r0 = new androidx.media3.extractor.ts.Ac4Reader
            java.lang.String r1 = r6.language
            int r6 = r6.getRoleFlags()
            r0.<init>(r1, r6)
            r5.<init>(r0)
            return r5
        L_0x00ec:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.DvbSubtitleReader r0 = new androidx.media3.extractor.ts.DvbSubtitleReader
            java.util.List<androidx.media3.extractor.ts.TsPayloadReader$DvbSubtitleInfo> r6 = r6.dvbSubtitleInfos
            r0.<init>(r6)
            r5.<init>(r0)
            return r5
        L_0x00f9:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.MpeghReader r6 = new androidx.media3.extractor.ts.MpeghReader
            r6.<init>()
            r5.<init>(r6)
            return r5
        L_0x0104:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.H265Reader r0 = new androidx.media3.extractor.ts.H265Reader
            androidx.media3.extractor.ts.SeiReader r6 = r4.buildSeiReader(r6)
            r0.<init>(r6)
            r5.<init>(r0)
            return r5
        L_0x0113:
            boolean r5 = r4.isSet(r1)
            if (r5 == 0) goto L_0x011a
            goto L_0x0133
        L_0x011a:
            androidx.media3.extractor.ts.PesReader r3 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.H264Reader r5 = new androidx.media3.extractor.ts.H264Reader
            androidx.media3.extractor.ts.SeiReader r6 = r4.buildSeiReader(r6)
            r0 = 1
            boolean r0 = r4.isSet(r0)
            r1 = 8
            boolean r1 = r4.isSet(r1)
            r5.<init>(r6, r0, r1)
            r3.<init>(r5)
        L_0x0133:
            return r3
        L_0x0134:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.Id3Reader r6 = new androidx.media3.extractor.ts.Id3Reader
            r6.<init>()
            r5.<init>(r6)
            return r5
        L_0x013f:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.MpegAudioReader r0 = new androidx.media3.extractor.ts.MpegAudioReader
            java.lang.String r1 = r6.language
            int r6 = r6.getRoleFlags()
            r0.<init>(r1, r6)
            r5.<init>(r0)
            return r5
        L_0x0150:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.H262Reader r0 = new androidx.media3.extractor.ts.H262Reader
            androidx.media3.extractor.ts.UserDataReader r6 = r4.buildUserDataReader(r6)
            r0.<init>(r6)
            r5.<init>(r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.ts.DefaultTsPayloadReaderFactory.createPayloadReader(int, androidx.media3.extractor.ts.TsPayloadReader$EsInfo):androidx.media3.extractor.ts.TsPayloadReader");
    }

    private SeiReader buildSeiReader(TsPayloadReader.EsInfo esInfo) {
        return new SeiReader(getClosedCaptionFormats(esInfo));
    }

    private UserDataReader buildUserDataReader(TsPayloadReader.EsInfo esInfo) {
        return new UserDataReader(getClosedCaptionFormats(esInfo));
    }

    private List<Format> getClosedCaptionFormats(TsPayloadReader.EsInfo esInfo) {
        String str;
        int i;
        List<byte[]> list;
        if (isSet(32)) {
            return this.closedCaptionFormats;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(esInfo.descriptorBytes);
        List<Format> list2 = this.closedCaptionFormats;
        while (parsableByteArray.bytesLeft() > 0) {
            int readUnsignedByte = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte();
            if (readUnsignedByte == 134) {
                list2 = new ArrayList<>();
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
                for (int i2 = 0; i2 < readUnsignedByte2; i2++) {
                    String readString = parsableByteArray.readString(3);
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    boolean z = true;
                    boolean z2 = (readUnsignedByte3 & 128) != 0;
                    if (z2) {
                        i = readUnsignedByte3 & 63;
                        str = MimeTypes.APPLICATION_CEA708;
                    } else {
                        str = MimeTypes.APPLICATION_CEA608;
                        i = 1;
                    }
                    byte readUnsignedByte4 = (byte) parsableByteArray.readUnsignedByte();
                    parsableByteArray.skipBytes(1);
                    if (z2) {
                        if ((readUnsignedByte4 & SignedBytes.MAX_POWER_OF_TWO) == 0) {
                            z = false;
                        }
                        list = CodecSpecificDataUtil.buildCea708InitializationData(z);
                    } else {
                        list = null;
                    }
                    list2.add(new Format.Builder().setSampleMimeType(str).setLanguage(readString).setAccessibilityChannel(i).setInitializationData(list).build());
                }
            }
            parsableByteArray.setPosition(position);
        }
        return list2;
    }

    private boolean isSet(int i) {
        return (i & this.flags) != 0;
    }
}
