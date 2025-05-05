package androidx.media3.transformer;

import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.transformer.ExportResult;
import com.google.common.collect.ImmutableList;
import java.util.Objects;

@Deprecated
public final class TransformationResult {
    public final String audioEncoderName;
    public final int averageAudioBitrate;
    public final int averageVideoBitrate;
    public final int channelCount;
    public final ColorInfo colorInfo;
    public final long durationMs;
    public final long fileSizeBytes;
    public final int height;
    public final ImmutableList<ProcessedInput> processedInputs;
    public final int sampleRate;
    public final TransformationException transformationException;
    public final String videoEncoderName;
    public final int videoFrameCount;
    public final int width;

    @Deprecated
    public static final class Builder {
        private String audioEncoderName;
        private int averageAudioBitrate;
        private int averageVideoBitrate;
        private int channelCount;
        ColorInfo colorInfo;
        private long durationMs;
        private long fileSizeBytes;
        private int height;
        private ImmutableList<ProcessedInput> processedInputs;
        private int sampleRate;
        private TransformationException transformationException;
        private String videoEncoderName;
        private int videoFrameCount;
        private int width;

        public Builder() {
            this.processedInputs = ImmutableList.of();
            this.durationMs = C.TIME_UNSET;
            this.fileSizeBytes = -1;
            this.averageAudioBitrate = C.RATE_UNSET_INT;
            this.channelCount = -1;
            this.sampleRate = C.RATE_UNSET_INT;
            this.averageVideoBitrate = C.RATE_UNSET_INT;
            this.height = -1;
            this.width = -1;
        }

        Builder(ExportResult exportResult) {
            ImmutableList.Builder builder = new ImmutableList.Builder();
            for (int i = 0; i < exportResult.processedInputs.size(); i++) {
                ExportResult.ProcessedInput processedInput = (ExportResult.ProcessedInput) exportResult.processedInputs.get(i);
                builder.add((Object) new ProcessedInput(processedInput.mediaItem, processedInput.audioDecoderName, processedInput.videoDecoderName));
            }
            this.processedInputs = builder.build();
            this.durationMs = exportResult.durationMs;
            this.fileSizeBytes = exportResult.fileSizeBytes;
            this.averageAudioBitrate = exportResult.averageAudioBitrate;
            this.channelCount = exportResult.channelCount;
            this.sampleRate = exportResult.sampleRate;
            this.audioEncoderName = exportResult.audioEncoderName;
            this.averageVideoBitrate = exportResult.averageVideoBitrate;
            this.colorInfo = exportResult.colorInfo;
            this.height = exportResult.height;
            this.width = exportResult.width;
            this.videoFrameCount = exportResult.videoFrameCount;
            this.videoEncoderName = exportResult.videoEncoderName;
            if (exportResult.exportException != null) {
                this.transformationException = new TransformationException(exportResult.exportException);
            }
        }

        public Builder setProcessedInputs(ImmutableList<ProcessedInput> immutableList) {
            this.processedInputs = immutableList;
            return this;
        }

        public Builder setDurationMs(long j) {
            Assertions.checkArgument(j >= 0 || j == C.TIME_UNSET);
            this.durationMs = j;
            return this;
        }

        public Builder setFileSizeBytes(long j) {
            Assertions.checkArgument(j > 0 || j == -1);
            this.fileSizeBytes = j;
            return this;
        }

        public Builder setAverageAudioBitrate(int i) {
            Assertions.checkArgument(i > 0 || i == -2147483647);
            this.averageAudioBitrate = i;
            return this;
        }

        public Builder setChannelCount(int i) {
            Assertions.checkArgument(i > 0 || i == -1);
            this.channelCount = i;
            return this;
        }

        public Builder setSampleRate(int i) {
            Assertions.checkArgument(i > 0 || i == -2147483647);
            this.sampleRate = i;
            return this;
        }

        public Builder setAudioEncoderName(String str) {
            this.audioEncoderName = str;
            return this;
        }

        public Builder setAverageVideoBitrate(int i) {
            Assertions.checkArgument(i > 0 || i == -2147483647);
            this.averageVideoBitrate = i;
            return this;
        }

        public Builder setColorInfo(ColorInfo colorInfo2) {
            this.colorInfo = colorInfo2;
            return this;
        }

        public Builder setHeight(int i) {
            Assertions.checkArgument(i > 0 || i == -1);
            this.height = i;
            return this;
        }

        public Builder setWidth(int i) {
            Assertions.checkArgument(i > 0 || i == -1);
            this.width = i;
            return this;
        }

        public Builder setVideoFrameCount(int i) {
            Assertions.checkArgument(i >= 0);
            this.videoFrameCount = i;
            return this;
        }

        public Builder setVideoEncoderName(String str) {
            this.videoEncoderName = str;
            return this;
        }

        public Builder setTransformationException(TransformationException transformationException2) {
            this.transformationException = transformationException2;
            return this;
        }

        public TransformationResult build() {
            return new TransformationResult(this.processedInputs, this.durationMs, this.fileSizeBytes, this.averageAudioBitrate, this.channelCount, this.sampleRate, this.audioEncoderName, this.averageVideoBitrate, this.colorInfo, this.height, this.width, this.videoFrameCount, this.videoEncoderName, this.transformationException);
        }
    }

    @Deprecated
    public static final class ProcessedInput {
        public final String audioDecoderName;
        public final MediaItem mediaItem;
        public final String videoDecoderName;

        public ProcessedInput(MediaItem mediaItem2, String str, String str2) {
            this.mediaItem = mediaItem2;
            this.audioDecoderName = str;
            this.videoDecoderName = str2;
        }
    }

    private TransformationResult(ImmutableList<ProcessedInput> immutableList, long j, long j2, int i, int i2, int i3, String str, int i4, ColorInfo colorInfo2, int i5, int i6, int i7, String str2, TransformationException transformationException2) {
        this.processedInputs = immutableList;
        this.durationMs = j;
        this.fileSizeBytes = j2;
        this.averageAudioBitrate = i;
        this.channelCount = i2;
        this.sampleRate = i3;
        this.audioEncoderName = str;
        this.averageVideoBitrate = i4;
        this.colorInfo = colorInfo2;
        this.height = i5;
        this.width = i6;
        this.videoFrameCount = i7;
        this.videoEncoderName = str2;
        this.transformationException = transformationException2;
    }

    public Builder buildUpon() {
        return new Builder().setProcessedInputs(this.processedInputs).setDurationMs(this.durationMs).setFileSizeBytes(this.fileSizeBytes).setAverageAudioBitrate(this.averageAudioBitrate).setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setAudioEncoderName(this.audioEncoderName).setAverageVideoBitrate(this.averageVideoBitrate).setColorInfo(this.colorInfo).setHeight(this.height).setWidth(this.width).setVideoFrameCount(this.videoFrameCount).setVideoEncoderName(this.videoEncoderName).setTransformationException(this.transformationException);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransformationResult)) {
            return false;
        }
        TransformationResult transformationResult = (TransformationResult) obj;
        if (Objects.equals(this.processedInputs, transformationResult.processedInputs) && this.durationMs == transformationResult.durationMs && this.fileSizeBytes == transformationResult.fileSizeBytes && this.averageAudioBitrate == transformationResult.averageAudioBitrate && this.channelCount == transformationResult.channelCount && this.sampleRate == transformationResult.sampleRate && Objects.equals(this.audioEncoderName, transformationResult.audioEncoderName) && this.averageVideoBitrate == transformationResult.averageVideoBitrate && Objects.equals(this.colorInfo, transformationResult.colorInfo) && this.height == transformationResult.height && this.width == transformationResult.width && this.videoFrameCount == transformationResult.videoFrameCount && Objects.equals(this.videoEncoderName, transformationResult.videoEncoderName) && Objects.equals(this.transformationException, transformationResult.transformationException)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((Objects.hashCode(this.processedInputs) * 31) + ((int) this.durationMs)) * 31) + ((int) this.fileSizeBytes)) * 31) + this.averageAudioBitrate) * 31) + this.channelCount) * 31) + this.sampleRate) * 31) + Objects.hashCode(this.audioEncoderName)) * 31) + this.averageVideoBitrate) * 31) + Objects.hashCode(this.colorInfo)) * 31) + this.height) * 31) + this.width) * 31) + this.videoFrameCount) * 31) + Objects.hashCode(this.videoEncoderName)) * 31) + Objects.hashCode(this.transformationException);
    }
}
