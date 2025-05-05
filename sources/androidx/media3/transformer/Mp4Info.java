package androidx.media3.transformer;

import android.content.Context;
import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

final class Mp4Info {
    public final Format audioFormat;
    public final long durationUs;
    public final long firstSyncSampleTimestampUsAfterTimeUs;
    public final boolean isFirstVideoSampleAfterTimeUsSyncSample;
    public final long lastSyncSampleTimestampUs;
    public final Format videoFormat;

    private Mp4Info(long j, long j2, long j3, boolean z, Format format, Format format2) {
        this.durationUs = j;
        this.lastSyncSampleTimestampUs = j2;
        this.firstSyncSampleTimestampUsAfterTimeUs = j3;
        this.isFirstVideoSampleAfterTimeUsSyncSample = z;
        this.videoFormat = format;
        this.audioFormat = format2;
    }

    public static Mp4Info create(Context context, String str) throws IOException {
        return create(context, str, C.TIME_UNSET);
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x013f A[Catch:{ all -> 0x016e }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x015c A[Catch:{ all -> 0x016e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.transformer.Mp4Info create(android.content.Context r29, java.lang.String r30, long r31) throws java.io.IOException {
        /*
            r0 = r30
            r1 = r31
            java.lang.String r3 = "The MP4 file is invalid"
            androidx.media3.extractor.mp4.Mp4Extractor r4 = new androidx.media3.extractor.mp4.Mp4Extractor
            androidx.media3.extractor.text.SubtitleParser$Factory r5 = androidx.media3.extractor.text.SubtitleParser.Factory.UNSUPPORTED
            r6 = 16
            r4.<init>(r5, r6)
            androidx.media3.transformer.Mp4Info$ExtractorOutputImpl r5 = new androidx.media3.transformer.Mp4Info$ExtractorOutputImpl
            r5.<init>()
            androidx.media3.datasource.DefaultDataSource r12 = new androidx.media3.datasource.DefaultDataSource
            r13 = 0
            r6 = r29
            r12.<init>((android.content.Context) r6, (boolean) r13)
            androidx.media3.datasource.DataSpec$Builder r6 = new androidx.media3.datasource.DataSpec$Builder
            r6.<init>()
            androidx.media3.datasource.DataSpec$Builder r6 = r6.setUri((java.lang.String) r0)
            androidx.media3.datasource.DataSpec r6 = r6.build()
            long r10 = r12.open(r6)     // Catch:{ all -> 0x016e }
            r6 = 0
            int r6 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            r14 = 1
            if (r6 == 0) goto L_0x0036
            r6 = r14
            goto L_0x0037
        L_0x0036:
            r6 = r13
        L_0x0037:
            androidx.media3.common.util.Assertions.checkState(r6)     // Catch:{ all -> 0x016e }
            androidx.media3.extractor.DefaultExtractorInput r15 = new androidx.media3.extractor.DefaultExtractorInput     // Catch:{ all -> 0x016e }
            r8 = 0
            r6 = r15
            r7 = r12
            r6.<init>(r7, r8, r10)     // Catch:{ all -> 0x016e }
            boolean r6 = r4.sniff(r15)     // Catch:{ all -> 0x016e }
            androidx.media3.common.util.Assertions.checkState(r6, r3)     // Catch:{ all -> 0x016e }
            r4.init(r5)     // Catch:{ all -> 0x016e }
            androidx.media3.extractor.PositionHolder r10 = new androidx.media3.extractor.PositionHolder     // Catch:{ all -> 0x016e }
            r10.<init>()     // Catch:{ all -> 0x016e }
        L_0x0052:
            boolean r6 = r5.seekMapInitialized     // Catch:{ all -> 0x016e }
            r7 = -1
            if (r6 != 0) goto L_0x00a3
            int r6 = r4.read(r15, r10)     // Catch:{ all -> 0x016e }
            if (r6 != r14) goto L_0x0091
            r12.close()     // Catch:{ all -> 0x016e }
            androidx.media3.datasource.DataSpec$Builder r6 = new androidx.media3.datasource.DataSpec$Builder     // Catch:{ all -> 0x016e }
            r6.<init>()     // Catch:{ all -> 0x016e }
            androidx.media3.datasource.DataSpec$Builder r6 = r6.setUri((java.lang.String) r0)     // Catch:{ all -> 0x016e }
            long r7 = r10.position     // Catch:{ all -> 0x016e }
            androidx.media3.datasource.DataSpec$Builder r6 = r6.setPosition(r7)     // Catch:{ all -> 0x016e }
            androidx.media3.datasource.DataSpec r6 = r6.build()     // Catch:{ all -> 0x016e }
            long r6 = r12.open(r6)     // Catch:{ all -> 0x016e }
            r8 = -1
            int r8 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r8 == 0) goto L_0x0080
            long r8 = r10.position     // Catch:{ all -> 0x016e }
            long r6 = r6 + r8
        L_0x0080:
            r15 = r6
            androidx.media3.extractor.DefaultExtractorInput r17 = new androidx.media3.extractor.DefaultExtractorInput     // Catch:{ all -> 0x016e }
            long r8 = r10.position     // Catch:{ all -> 0x016e }
            r6 = r17
            r7 = r12
            r18 = r10
            r10 = r15
            r6.<init>(r7, r8, r10)     // Catch:{ all -> 0x016e }
            r15 = r17
            goto L_0x00a0
        L_0x0091:
            r18 = r10
            if (r6 != r7) goto L_0x00a0
            boolean r6 = r5.seekMapInitialized     // Catch:{ all -> 0x016e }
            if (r6 == 0) goto L_0x009a
            goto L_0x00a0
        L_0x009a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x016e }
            r0.<init>(r3)     // Catch:{ all -> 0x016e }
            throw r0     // Catch:{ all -> 0x016e }
        L_0x00a0:
            r10 = r18
            goto L_0x0052
        L_0x00a3:
            long r8 = r4.getDurationUs()     // Catch:{ all -> 0x016e }
            int r0 = r5.videoTrackId     // Catch:{ all -> 0x016e }
            r3 = 0
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r0 == r7) goto L_0x0131
            java.util.Map<java.lang.Integer, androidx.media3.transformer.Mp4Info$ExtractorOutputImpl$TrackOutputImpl> r0 = r5.trackTypeToTrackOutput     // Catch:{ all -> 0x016e }
            r6 = 2
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x016e }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x016e }
            androidx.media3.transformer.Mp4Info$ExtractorOutputImpl$TrackOutputImpl r0 = (androidx.media3.transformer.Mp4Info.ExtractorOutputImpl.TrackOutputImpl) r0     // Catch:{ all -> 0x016e }
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)     // Catch:{ all -> 0x016e }
            androidx.media3.transformer.Mp4Info$ExtractorOutputImpl$TrackOutputImpl r0 = (androidx.media3.transformer.Mp4Info.ExtractorOutputImpl.TrackOutputImpl) r0     // Catch:{ all -> 0x016e }
            androidx.media3.common.Format r0 = r0.format     // Catch:{ all -> 0x016e }
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)     // Catch:{ all -> 0x016e }
            androidx.media3.common.Format r0 = (androidx.media3.common.Format) r0     // Catch:{ all -> 0x016e }
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x00d2
            r6 = r14
            goto L_0x00d3
        L_0x00d2:
            r6 = r13
        L_0x00d3:
            androidx.media3.common.util.Assertions.checkState(r6)     // Catch:{ all -> 0x016e }
            int r6 = r5.videoTrackId     // Catch:{ all -> 0x016e }
            androidx.media3.extractor.SeekMap$SeekPoints r6 = r4.getSeekPoints(r8, r6)     // Catch:{ all -> 0x016e }
            androidx.media3.extractor.SeekPoint r6 = r6.first     // Catch:{ all -> 0x016e }
            r15 = r8
            long r7 = r6.timeUs     // Catch:{ all -> 0x016e }
            int r6 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x012a
            int r6 = r5.videoTrackId     // Catch:{ all -> 0x016e }
            androidx.media3.extractor.SeekMap$SeekPoints r6 = r4.getSeekPoints(r1, r6)     // Catch:{ all -> 0x016e }
            androidx.media3.extractor.SeekPoint r9 = r6.first     // Catch:{ all -> 0x016e }
            long r9 = r9.timeUs     // Catch:{ all -> 0x016e }
            int r9 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r9 != 0) goto L_0x00f8
            androidx.media3.extractor.SeekPoint r6 = r6.first     // Catch:{ all -> 0x016e }
            long r9 = r6.timeUs     // Catch:{ all -> 0x016e }
            goto L_0x0107
        L_0x00f8:
            androidx.media3.extractor.SeekPoint r9 = r6.second     // Catch:{ all -> 0x016e }
            long r9 = r9.timeUs     // Catch:{ all -> 0x016e }
            int r9 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r9 > 0) goto L_0x0105
            androidx.media3.extractor.SeekPoint r6 = r6.second     // Catch:{ all -> 0x016e }
            long r9 = r6.timeUs     // Catch:{ all -> 0x016e }
            goto L_0x0107
        L_0x0105:
            r9 = -9223372036854775808
        L_0x0107:
            int r6 = r5.videoTrackId     // Catch:{ all -> 0x016e }
            long[] r6 = r4.getSampleTimestampsUs(r6)     // Catch:{ all -> 0x016e }
            int r1 = androidx.media3.common.util.Util.binarySearchCeil((long[]) r6, (long) r1, (boolean) r14, (boolean) r13)     // Catch:{ all -> 0x016e }
            int r2 = r6.length     // Catch:{ all -> 0x016e }
            if (r1 >= r2) goto L_0x0123
            r1 = r6[r1]     // Catch:{ all -> 0x016e }
            int r1 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r1 != 0) goto L_0x0123
            r27 = r0
            r22 = r7
            r24 = r9
            r26 = r14
            goto L_0x013a
        L_0x0123:
            r27 = r0
            r22 = r7
            r24 = r9
            goto L_0x0138
        L_0x012a:
            r27 = r0
            r22 = r7
            r24 = r10
            goto L_0x0138
        L_0x0131:
            r15 = r8
            r27 = r3
            r22 = r10
            r24 = r22
        L_0x0138:
            r26 = r13
        L_0x013a:
            int r0 = r5.audioTrackId     // Catch:{ all -> 0x016e }
            r1 = -1
            if (r0 == r1) goto L_0x015c
            java.util.Map<java.lang.Integer, androidx.media3.transformer.Mp4Info$ExtractorOutputImpl$TrackOutputImpl> r0 = r5.trackTypeToTrackOutput     // Catch:{ all -> 0x016e }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)     // Catch:{ all -> 0x016e }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x016e }
            androidx.media3.transformer.Mp4Info$ExtractorOutputImpl$TrackOutputImpl r0 = (androidx.media3.transformer.Mp4Info.ExtractorOutputImpl.TrackOutputImpl) r0     // Catch:{ all -> 0x016e }
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)     // Catch:{ all -> 0x016e }
            androidx.media3.transformer.Mp4Info$ExtractorOutputImpl$TrackOutputImpl r0 = (androidx.media3.transformer.Mp4Info.ExtractorOutputImpl.TrackOutputImpl) r0     // Catch:{ all -> 0x016e }
            androidx.media3.common.Format r0 = r0.format     // Catch:{ all -> 0x016e }
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)     // Catch:{ all -> 0x016e }
            androidx.media3.common.Format r0 = (androidx.media3.common.Format) r0     // Catch:{ all -> 0x016e }
            r28 = r0
            goto L_0x015e
        L_0x015c:
            r28 = r3
        L_0x015e:
            androidx.media3.transformer.Mp4Info r0 = new androidx.media3.transformer.Mp4Info     // Catch:{ all -> 0x016e }
            r19 = r0
            r20 = r15
            r19.<init>(r20, r22, r24, r26, r27, r28)     // Catch:{ all -> 0x016e }
            androidx.media3.datasource.DataSourceUtil.closeQuietly(r12)
            r4.release()
            return r0
        L_0x016e:
            r0 = move-exception
            androidx.media3.datasource.DataSourceUtil.closeQuietly(r12)
            r4.release()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.Mp4Info.create(android.content.Context, java.lang.String, long):androidx.media3.transformer.Mp4Info");
    }

    private static final class ExtractorOutputImpl implements ExtractorOutput {
        public int audioTrackId = -1;
        public boolean seekMapInitialized;
        final Map<Integer, TrackOutputImpl> trackTypeToTrackOutput = new HashMap();
        public int videoTrackId = -1;

        public void endTracks() {
        }

        public TrackOutput track(int i, int i2) {
            if (i2 == 2) {
                this.videoTrackId = i;
            } else if (i2 == 1) {
                this.audioTrackId = i;
            }
            TrackOutputImpl trackOutputImpl = this.trackTypeToTrackOutput.get(Integer.valueOf(i2));
            if (trackOutputImpl != null) {
                return trackOutputImpl;
            }
            TrackOutputImpl trackOutputImpl2 = new TrackOutputImpl();
            this.trackTypeToTrackOutput.put(Integer.valueOf(i2), trackOutputImpl2);
            return trackOutputImpl2;
        }

        public void seekMap(SeekMap seekMap) {
            this.seekMapInitialized = true;
        }

        private static final class TrackOutputImpl implements TrackOutput {
            private static final int FIXED_BYTE_ARRAY_SIZE = 16000;
            private final byte[] byteArray = new byte[16000];
            public Format format;

            public void sampleMetadata(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
            }

            public void format(Format format2) {
                this.format = format2;
            }

            public int sampleData(DataReader dataReader, int i, boolean z, int i2) throws IOException {
                int i3 = i;
                while (i3 > 0) {
                    boolean z2 = false;
                    int read = dataReader.read(this.byteArray, 0, Math.min(i3, this.byteArray.length));
                    if (read != -1) {
                        z2 = true;
                    }
                    Assertions.checkState(z2);
                    i3 -= read;
                }
                return i;
            }

            public void sampleData(ParsableByteArray parsableByteArray, int i, int i2) {
                while (i > 0) {
                    int min = Math.min(i, this.byteArray.length);
                    parsableByteArray.readBytes(this.byteArray, 0, min);
                    i -= min;
                }
            }
        }
    }
}
