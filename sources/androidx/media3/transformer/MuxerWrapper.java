package androidx.media3.transformer;

import android.media.MediaCodec;
import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.muxer.Muxer;
import com.canhub.cropper.CropImageOptions;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import java.io.File;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

final class MuxerWrapper {
    private static final long MAX_TRACK_WRITE_AHEAD_US = Util.msToUs(500);
    public static final int MUXER_MODE_APPEND = 2;
    public static final int MUXER_MODE_DEFAULT = 0;
    public static final int MUXER_MODE_MUX_PARTIAL = 1;
    public static final int MUXER_RELEASE_REASON_CANCELLED = 1;
    public static final int MUXER_RELEASE_REASON_COMPLETED = 0;
    public static final int MUXER_RELEASE_REASON_ERROR = 2;
    private static final String MUXER_TIMEOUT_ERROR_FORMAT_STRING = "Abort: no output sample written in the last %d milliseconds. DebugTrace: %s";
    private static final String TIMER_THREAD_NAME = "Muxer:Timer";
    private final ScheduledExecutorService abortScheduledExecutorService;
    private ScheduledFuture<?> abortScheduledFuture;
    private volatile int additionalRotationDegrees;
    private final Format appendVideoFormat;
    private final MediaCodec.BufferInfo bufferInfo;
    private final boolean dropSamplesBeforeFirstVideoSample;
    private long firstVideoPresentationTimeUs;
    private boolean isAborted;
    private boolean isEnded;
    private boolean isReady;
    private final Listener listener;
    private final long maxDelayBetweenSamplesMs;
    private long maxEndedTrackTimeUs;
    private long minEndedTrackTimeUs;
    private long minTrackTimeUs;
    private boolean muxedPartialAudio;
    private boolean muxedPartialVideo;
    private Muxer muxer;
    private final Muxer.Factory muxerFactory;
    private int muxerMode;
    private final String outputPath;
    private int previousTrackType;
    private volatile int trackCount;
    private final SparseArray<TrackInfo> trackTypeToInfo;

    public interface Listener {
        void onEnded(long j, long j2);

        void onError(ExportException exportException);

        void onTrackEnded(int i, Format format, int i2, int i3);
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MuxerMode {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MuxerReleaseReason {
    }

    public static final class AppendTrackFormatException extends Exception {
        public AppendTrackFormatException(String str) {
            super(str);
        }
    }

    public MuxerWrapper(String str, Muxer.Factory factory, Listener listener2, int i, boolean z, Format format, long j) {
        this.outputPath = str;
        this.muxerFactory = factory;
        this.listener = listener2;
        boolean z2 = false;
        Assertions.checkArgument(i == 0 || i == 1);
        this.muxerMode = i;
        this.dropSamplesBeforeFirstVideoSample = z;
        if ((i == 0 && format == null) || (i == 1 && format != null)) {
            z2 = true;
        }
        Assertions.checkArgument(z2, "appendVideoFormat must be present if and only if muxerMode is MUXER_MODE_MUX_PARTIAL.");
        this.appendVideoFormat = format;
        this.maxDelayBetweenSamplesMs = j;
        this.trackTypeToInfo = new SparseArray<>();
        this.previousTrackType = -2;
        this.firstVideoPresentationTimeUs = C.TIME_UNSET;
        this.minEndedTrackTimeUs = Long.MAX_VALUE;
        this.abortScheduledExecutorService = Util.newSingleThreadScheduledExecutor(TIMER_THREAD_NAME);
        this.bufferInfo = new MediaCodec.BufferInfo();
    }

    public static List<byte[]> getMostCompatibleInitializationData(Format format, Format format2) {
        if (format.initializationDataEquals(format2)) {
            return format.initializationData;
        }
        if (!Objects.equals(format2.sampleMimeType, MimeTypes.VIDEO_H264) || !Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H264) || format2.initializationData.size() != 2 || format.initializationData.size() != 2 || !Arrays.equals(format2.initializationData.get(1), format.initializationData.get(1))) {
            return null;
        }
        byte[] bArr = format2.initializationData.get(0);
        byte[] bArr2 = format.initializationData.get(0);
        int length = NalUnitUtil.NAL_START_CODE.length + 3;
        if (length >= bArr.length || bArr.length != bArr2.length) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (i != length && bArr[i] != bArr2[i]) {
                return null;
            }
        }
        for (int i2 = 0; i2 < NalUnitUtil.NAL_START_CODE.length; i2++) {
            if (bArr[i2] != NalUnitUtil.NAL_START_CODE[i2]) {
                return null;
            }
        }
        if ((bArr[NalUnitUtil.NAL_START_CODE.length] & Ascii.US) != 7 || bArr[NalUnitUtil.NAL_START_CODE.length + 1] == 0) {
            return null;
        }
        if (bArr2[length] >= bArr[length]) {
            return format.initializationData;
        }
        return format2.initializationData;
    }

    public void changeToAppendMode() {
        boolean z = true;
        if (this.muxerMode != 1) {
            z = false;
        }
        Assertions.checkState(z);
        this.muxerMode = 2;
    }

    public void setAdditionalRotationDegrees(int i) {
        Assertions.checkState(this.trackTypeToInfo.size() == 0 || this.additionalRotationDegrees == i, "The additional rotation cannot be changed after adding track formats.");
        this.additionalRotationDegrees = i;
    }

    public void setTrackCount(int i) {
        if (this.muxerMode != 2) {
            Assertions.checkState(this.trackTypeToInfo.size() == 0, "The track count cannot be changed after adding track formats.");
            this.trackCount = i;
        }
    }

    public boolean supportsSampleMimeType(String str) {
        return getSupportedSampleMimeTypes(MimeTypes.getTrackType(str)).contains(str);
    }

    public ImmutableList<String> getSupportedSampleMimeTypes(int i) {
        return this.muxerFactory.getSupportedSampleMimeTypes(i);
    }

    public void addTrackFormat(Format format) throws AppendTrackFormatException, Muxer.MuxerException {
        String str = format.sampleMimeType;
        int trackType = MimeTypes.getTrackType(str);
        Assertions.checkArgument(trackType == 1 || trackType == 2, "Unsupported track format: " + str);
        if (trackType == 2) {
            format = format.buildUpon().setRotationDegrees((format.rotationDegrees + this.additionalRotationDegrees) % CropImageOptions.DEGREES_360).build();
            if (this.muxerMode == 1) {
                List<byte[]> mostCompatibleInitializationData = getMostCompatibleInitializationData(format, (Format) Assertions.checkNotNull(this.appendVideoFormat));
                if (mostCompatibleInitializationData != null) {
                    format = format.buildUpon().setInitializationData(mostCompatibleInitializationData).build();
                } else {
                    throw new AppendTrackFormatException("Switching to MUXER_MODE_APPEND will fail.");
                }
            }
        }
        if (this.muxerMode == 2) {
            if (trackType == 2) {
                Assertions.checkState(Util.contains(this.trackTypeToInfo, 2));
                Format format2 = this.trackTypeToInfo.get(2).format;
                if (!Util.areEqual(format2.sampleMimeType, format.sampleMimeType)) {
                    throw new AppendTrackFormatException("Video format mismatch - sampleMimeType: " + format2.sampleMimeType + " != " + format.sampleMimeType);
                } else if (format2.width != format.width) {
                    throw new AppendTrackFormatException("Video format mismatch - width: " + format2.width + " != " + format.width);
                } else if (format2.height != format.height) {
                    throw new AppendTrackFormatException("Video format mismatch - height: " + format2.height + " != " + format.height);
                } else if (format2.rotationDegrees != format.rotationDegrees) {
                    throw new AppendTrackFormatException("Video format mismatch - rotationDegrees: " + format2.rotationDegrees + " != " + format.rotationDegrees);
                } else if (!format.initializationDataEquals((Format) Assertions.checkNotNull(this.appendVideoFormat))) {
                    throw new AppendTrackFormatException("The initialization data of the newly added track format doesn't match appendVideoFormat.");
                }
            } else if (trackType == 1) {
                Assertions.checkState(Util.contains(this.trackTypeToInfo, 1));
                Format format3 = this.trackTypeToInfo.get(1).format;
                if (!Util.areEqual(format3.sampleMimeType, format.sampleMimeType)) {
                    throw new AppendTrackFormatException("Audio format mismatch - sampleMimeType: " + format3.sampleMimeType + " != " + format.sampleMimeType);
                } else if (format3.channelCount != format.channelCount) {
                    throw new AppendTrackFormatException("Audio format mismatch - channelCount: " + format3.channelCount + " != " + format.channelCount);
                } else if (format3.sampleRate != format.sampleRate) {
                    throw new AppendTrackFormatException("Audio format mismatch - sampleRate: " + format3.sampleRate + " != " + format.sampleRate);
                } else if (!format3.initializationDataEquals(format)) {
                    throw new AppendTrackFormatException("Audio format mismatch - initializationData.");
                }
            }
            resetAbortTimer();
            return;
        }
        int i = this.trackCount;
        Assertions.checkState(i > 0, "The track count should be set before the formats are added.");
        Assertions.checkState(this.trackTypeToInfo.size() < i, "All track formats have already been added.");
        Assertions.checkState(!Util.contains(this.trackTypeToInfo, trackType), "There is already a track of type " + trackType);
        ensureMuxerInitialized();
        this.trackTypeToInfo.put(trackType, new TrackInfo(format, this.muxer.addTrack(format)));
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_MUXER, DebugTraceUtil.EVENT_INPUT_FORMAT, C.TIME_UNSET, "%s:%s", Util.getTrackTypeString(trackType), format);
        if (format.metadata != null) {
            for (int i2 = 0; i2 < format.metadata.length(); i2++) {
                this.muxer.addMetadataEntry(format.metadata.get(i2));
            }
        }
        if (this.trackTypeToInfo.size() == i) {
            this.isReady = true;
            resetAbortTimer();
        }
    }

    public Format getTrackFormat(int i) {
        Assertions.checkArgument(Util.contains(this.trackTypeToInfo, i));
        return this.trackTypeToInfo.get(i).format;
    }

    public boolean writeSample(int i, ByteBuffer byteBuffer, boolean z, long j) throws Muxer.MuxerException {
        int i2 = i;
        long j2 = j;
        Assertions.checkArgument(Util.contains(this.trackTypeToInfo, i));
        TrackInfo trackInfo = this.trackTypeToInfo.get(i);
        boolean canWriteSample = canWriteSample(i, j2);
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_MUXER, DebugTraceUtil.EVENT_CAN_WRITE_SAMPLE, j, "%s:%s", Util.getTrackTypeString(i), Boolean.valueOf(canWriteSample));
        if (i2 == 2) {
            if (this.firstVideoPresentationTimeUs == C.TIME_UNSET) {
                this.firstVideoPresentationTimeUs = j2;
            }
        } else if (i2 == 1 && this.dropSamplesBeforeFirstVideoSample && Util.contains(this.trackTypeToInfo, 2)) {
            long j3 = this.firstVideoPresentationTimeUs;
            if (j3 != C.TIME_UNSET && j2 < j3) {
                resetAbortTimer();
                return true;
            }
        }
        if (!canWriteSample) {
            return false;
        }
        if (trackInfo.sampleCount == 0) {
            trackInfo.startTimeUs = j2;
        }
        trackInfo.sampleCount++;
        trackInfo.bytesWritten += (long) byteBuffer.remaining();
        trackInfo.timeUs = Math.max(trackInfo.timeUs, j2);
        resetAbortTimer();
        Assertions.checkStateNotNull(this.muxer);
        this.bufferInfo.set(byteBuffer.position(), byteBuffer.remaining(), j, TransformerUtil.getMediaCodecFlags(z ? 1 : 0));
        ByteBuffer byteBuffer2 = byteBuffer;
        this.muxer.writeSampleData(trackInfo.trackToken, byteBuffer, this.bufferInfo);
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_MUXER, DebugTraceUtil.EVENT_ACCEPTED_INPUT, j, "%s", Util.getTrackTypeString(i));
        this.previousTrackType = i2;
        return true;
    }

    public void endTrack(int i) {
        if (this.isReady && Util.contains(this.trackTypeToInfo, i)) {
            TrackInfo trackInfo = this.trackTypeToInfo.get(i);
            this.minEndedTrackTimeUs = Math.max(0, Math.min(this.minEndedTrackTimeUs, trackInfo.startTimeUs));
            this.maxEndedTrackTimeUs = Math.max(this.maxEndedTrackTimeUs, trackInfo.timeUs);
            this.listener.onTrackEnded(i, trackInfo.format, trackInfo.getAverageBitrate(), trackInfo.sampleCount);
            DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_MUXER, DebugTraceUtil.EVENT_INPUT_ENDED, trackInfo.timeUs, "%s", Util.getTrackTypeString(i));
            if (this.muxerMode != 1) {
                this.trackTypeToInfo.delete(i);
                if (this.trackTypeToInfo.size() == 0) {
                    this.isEnded = true;
                    DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_MUXER, DebugTraceUtil.EVENT_OUTPUT_ENDED, this.maxEndedTrackTimeUs);
                }
            } else if (i == 2) {
                this.muxedPartialVideo = true;
            } else if (i == 1) {
                this.muxedPartialAudio = true;
            }
            long usToMs = Util.usToMs(this.maxEndedTrackTimeUs - this.minEndedTrackTimeUs);
            if (this.muxerMode == 1 && this.muxedPartialVideo && (this.muxedPartialAudio || this.trackCount == 1)) {
                this.listener.onEnded(usToMs, getCurrentOutputSizeBytes());
                ScheduledFuture<?> scheduledFuture = this.abortScheduledFuture;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                }
            } else if (this.isEnded) {
                this.listener.onEnded(usToMs, getCurrentOutputSizeBytes());
                this.abortScheduledExecutorService.shutdownNow();
            }
        }
    }

    public boolean isEnded() {
        if (!this.isEnded) {
            return this.muxerMode == 1 && this.muxedPartialVideo && (this.muxedPartialAudio || this.trackCount == 1);
        }
        return true;
    }

    public void finishWritingAndMaybeRelease(int i) throws Muxer.MuxerException {
        if (i != 0 || this.muxerMode != 1) {
            this.isReady = false;
            this.abortScheduledExecutorService.shutdownNow();
            Muxer muxer2 = this.muxer;
            if (muxer2 != null) {
                try {
                    muxer2.close();
                } catch (Muxer.MuxerException e) {
                    if (i != 1 || !((String) Assertions.checkNotNull(e.getMessage())).equals(FrameworkMuxer.MUXER_STOPPING_FAILED_ERROR_MESSAGE)) {
                        throw e;
                    }
                }
            }
        }
    }

    private boolean canWriteSample(int i, long j) {
        if ((this.dropSamplesBeforeFirstVideoSample && i != 2 && Util.contains(this.trackTypeToInfo, 2) && this.firstVideoPresentationTimeUs == C.TIME_UNSET) || !this.isReady) {
            return false;
        }
        if (this.trackTypeToInfo.size() == 1) {
            return true;
        }
        long j2 = MAX_TRACK_WRITE_AHEAD_US;
        if (j - this.trackTypeToInfo.get(i).timeUs > j2 && MimeTypes.getTrackType(((TrackInfo) Assertions.checkNotNull(getTrackInfoWithMinTimeUs(this.trackTypeToInfo))).format.sampleMimeType) == i) {
            return true;
        }
        if (i != this.previousTrackType) {
            this.minTrackTimeUs = ((TrackInfo) Assertions.checkNotNull(getTrackInfoWithMinTimeUs(this.trackTypeToInfo))).timeUs;
        }
        if (j - this.minTrackTimeUs <= j2) {
            return true;
        }
        return false;
    }

    private void resetAbortTimer() {
        Assertions.checkStateNotNull(this.muxer);
        if (this.maxDelayBetweenSamplesMs != C.TIME_UNSET) {
            ScheduledFuture<?> scheduledFuture = this.abortScheduledFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.abortScheduledFuture = this.abortScheduledExecutorService.schedule(new MuxerWrapper$$ExternalSyntheticLambda0(this), this.maxDelayBetweenSamplesMs, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$resetAbortTimer$0$androidx-media3-transformer-MuxerWrapper  reason: not valid java name */
    public /* synthetic */ void m1128lambda$resetAbortTimer$0$androidxmedia3transformerMuxerWrapper() {
        if (!this.isAborted) {
            this.isAborted = true;
            this.listener.onError(ExportException.createForMuxer(new IllegalStateException(Util.formatInvariant(MUXER_TIMEOUT_ERROR_FORMAT_STRING, Long.valueOf(this.maxDelayBetweenSamplesMs), DebugTraceUtil.generateTraceSummary())), ExportException.ERROR_CODE_MUXING_TIMEOUT));
        }
    }

    @EnsuresNonNull({"muxer"})
    private void ensureMuxerInitialized() throws Muxer.MuxerException {
        if (this.muxer == null) {
            this.muxer = this.muxerFactory.create(this.outputPath);
        }
    }

    private long getCurrentOutputSizeBytes() {
        long length = new File(this.outputPath).length();
        if (length > 0) {
            return length;
        }
        return -1;
    }

    private static TrackInfo getTrackInfoWithMinTimeUs(SparseArray<TrackInfo> sparseArray) {
        if (sparseArray.size() == 0) {
            return null;
        }
        TrackInfo valueAt = sparseArray.valueAt(0);
        for (int i = 1; i < sparseArray.size(); i++) {
            TrackInfo valueAt2 = sparseArray.valueAt(i);
            if (valueAt2.timeUs < valueAt.timeUs) {
                valueAt = valueAt2;
            }
        }
        return valueAt;
    }

    private static final class TrackInfo {
        public long bytesWritten;
        public final Format format;
        public int sampleCount;
        public long startTimeUs;
        public long timeUs;
        public final Muxer.TrackToken trackToken;

        public TrackInfo(Format format2, Muxer.TrackToken trackToken2) {
            this.format = format2;
            this.trackToken = trackToken2;
        }

        public int getAverageBitrate() {
            long j = this.timeUs;
            if (j <= 0) {
                return C.RATE_UNSET_INT;
            }
            long j2 = this.bytesWritten;
            if (j2 <= 0) {
                return C.RATE_UNSET_INT;
            }
            long j3 = this.startTimeUs;
            return j == j3 ? C.RATE_UNSET_INT : (int) Util.scaleLargeTimestamp(j2, 8000000, j - j3);
        }
    }
}
