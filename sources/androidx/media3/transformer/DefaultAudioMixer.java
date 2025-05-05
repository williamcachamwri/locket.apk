package androidx.media3.transformer;

import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.audio.AudioMixingUtil;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.ChannelMixingMatrix;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.transformer.AudioMixer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class DefaultAudioMixer implements AudioMixer {
    private static final int DEFAULT_BUFFER_SIZE_MS = 500;
    private int bufferSizeFrames;
    /* access modifiers changed from: private */
    public final boolean clipFloatOutput;
    private long endPosition;
    private long inputLimit;
    private long maxPositionOfRemovedSources;
    private long mixerStartTimeUs;
    private MixingBuffer[] mixingBuffers;
    private int nextSourceId;
    private AudioProcessor.AudioFormat outputAudioFormat;
    private long outputPosition;
    private final boolean outputSilenceWithNoSources;
    private final SparseArray<SourceInfo> sources;

    public static final class Factory implements AudioMixer.Factory {
        private final boolean clipFloatOutput;
        private final boolean outputSilenceWithNoSources;

        public Factory() {
            this(false, true);
        }

        public Factory(boolean z, boolean z2) {
            this.outputSilenceWithNoSources = z;
            this.clipFloatOutput = z2;
        }

        public DefaultAudioMixer create() {
            return new DefaultAudioMixer(this.outputSilenceWithNoSources, this.clipFloatOutput);
        }
    }

    private DefaultAudioMixer(boolean z, boolean z2) {
        this.outputSilenceWithNoSources = z;
        this.clipFloatOutput = z2;
        this.sources = new SparseArray<>();
        this.outputAudioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.bufferSizeFrames = -1;
        this.mixingBuffers = new MixingBuffer[0];
        this.mixerStartTimeUs = C.TIME_UNSET;
        this.inputLimit = -1;
        this.endPosition = Long.MAX_VALUE;
        if (z) {
            this.maxPositionOfRemovedSources = Long.MAX_VALUE;
        }
    }

    public void configure(AudioProcessor.AudioFormat audioFormat, int i, long j) throws AudioProcessor.UnhandledAudioFormatException {
        Assertions.checkState(this.outputAudioFormat.equals(AudioProcessor.AudioFormat.NOT_SET), "Audio mixer already configured.");
        if (i == -1) {
            i = 500;
        }
        Assertions.checkArgument(i > 0);
        if (AudioMixingUtil.canMix(audioFormat)) {
            this.outputAudioFormat = audioFormat;
            this.bufferSizeFrames = (i * audioFormat.sampleRate) / 1000;
            this.mixerStartTimeUs = j;
            DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_AUDIO_MIXER, DebugTraceUtil.EVENT_OUTPUT_FORMAT, j, "%s", audioFormat);
            this.mixingBuffers = new MixingBuffer[]{allocateMixingBuffer(0), allocateMixingBuffer((long) this.bufferSizeFrames)};
            updateInputFrameLimit();
            return;
        }
        throw new AudioProcessor.UnhandledAudioFormatException("Can not mix to this AudioFormat.", audioFormat);
    }

    public void setEndTimeUs(long j) {
        checkStateIsConfigured();
        Assertions.checkArgument(j >= this.mixerStartTimeUs, "End time must be at least the configured start time.");
        this.endPosition = Util.durationUsToSampleCount(j - this.mixerStartTimeUs, this.outputAudioFormat.sampleRate);
        updateInputFrameLimit();
    }

    public boolean supportsSourceAudioFormat(AudioProcessor.AudioFormat audioFormat) {
        checkStateIsConfigured();
        return AudioMixingUtil.canMix(audioFormat, this.outputAudioFormat);
    }

    public int addSource(AudioProcessor.AudioFormat audioFormat, long j) throws AudioProcessor.UnhandledAudioFormatException {
        AudioProcessor.AudioFormat audioFormat2 = audioFormat;
        checkStateIsConfigured();
        if (supportsSourceAudioFormat(audioFormat)) {
            long durationUsToSampleCount = Util.durationUsToSampleCount(j - this.mixerStartTimeUs, audioFormat2.sampleRate);
            int i = this.nextSourceId;
            this.nextSourceId = i + 1;
            this.sources.append(i, new SourceInfo(audioFormat, ChannelMixingMatrix.create(audioFormat2.channelCount, this.outputAudioFormat.channelCount), durationUsToSampleCount));
            DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_AUDIO_MIXER, DebugTraceUtil.EVENT_REGISTER_NEW_INPUT_STREAM, j, "source(%s):%s", Integer.valueOf(i), audioFormat);
            return i;
        }
        throw new AudioProcessor.UnhandledAudioFormatException("Can not add source. MixerFormat=" + this.outputAudioFormat, audioFormat);
    }

    public boolean hasSource(int i) {
        checkStateIsConfigured();
        return Util.contains(this.sources, i);
    }

    public void setSourceVolume(int i, float f) {
        checkStateIsConfigured();
        Assertions.checkArgument(f >= 0.0f, "Volume must be non-negative.");
        getSourceById(i).setVolume(f);
    }

    public void removeSource(int i) {
        checkStateIsConfigured();
        this.maxPositionOfRemovedSources = Math.max(this.maxPositionOfRemovedSources, getSourceById(i).position);
        this.sources.delete(i);
    }

    public void queueInput(int i, ByteBuffer byteBuffer) {
        checkStateIsConfigured();
        if (byteBuffer.hasRemaining()) {
            SourceInfo sourceById = getSourceById(i);
            if (sourceById.position < this.inputLimit) {
                long min = Math.min(sourceById.getPositionAfterBuffer(byteBuffer), this.inputLimit);
                if (sourceById.getChannelMixingMatrix().isZero()) {
                    sourceById.discardTo(byteBuffer, min);
                    return;
                }
                long j = sourceById.position;
                long j2 = this.outputPosition;
                if (j < j2) {
                    sourceById.discardTo(byteBuffer, Math.min(min, j2));
                    if (sourceById.position == min) {
                        return;
                    }
                }
                for (MixingBuffer mixingBuffer : this.mixingBuffers) {
                    if (sourceById.position < mixingBuffer.limit) {
                        mixingBuffer.buffer.position(mixingBuffer.buffer.position() + (((int) (sourceById.position - mixingBuffer.position)) * this.outputAudioFormat.bytesPerFrame));
                        sourceById.mixTo(byteBuffer, Math.min(min, mixingBuffer.limit), mixingBuffer.buffer, this.outputAudioFormat);
                        mixingBuffer.buffer.reset();
                        if (sourceById.position == min) {
                            return;
                        }
                    }
                }
            }
        }
    }

    public ByteBuffer getOutput() {
        checkStateIsConfigured();
        if (isEnded()) {
            return AudioProcessor.EMPTY_BUFFER;
        }
        long j = this.endPosition;
        if (this.sources.size() == 0) {
            j = Math.min(j, this.maxPositionOfRemovedSources);
        }
        for (int i = 0; i < this.sources.size(); i++) {
            j = Math.min(j, this.sources.valueAt(i).position);
        }
        if (j <= this.outputPosition) {
            return AudioProcessor.EMPTY_BUFFER;
        }
        MixingBuffer mixingBuffer = this.mixingBuffers[0];
        long min = Math.min(j, mixingBuffer.limit);
        ByteBuffer duplicate = mixingBuffer.buffer.duplicate();
        duplicate.position(((int) (this.outputPosition - mixingBuffer.position)) * this.outputAudioFormat.bytesPerFrame).limit(((int) (min - mixingBuffer.position)) * this.outputAudioFormat.bytesPerFrame);
        ByteBuffer order = duplicate.slice().order(ByteOrder.nativeOrder());
        if (min == mixingBuffer.limit) {
            MixingBuffer[] mixingBufferArr = this.mixingBuffers;
            MixingBuffer mixingBuffer2 = mixingBufferArr[1];
            mixingBufferArr[0] = mixingBuffer2;
            mixingBufferArr[1] = allocateMixingBuffer(mixingBuffer2.limit);
        }
        this.outputPosition = min;
        updateInputFrameLimit();
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_AUDIO_MIXER, DebugTraceUtil.EVENT_PRODUCED_OUTPUT, C.TIME_UNSET, "bytesOutput=%s", Integer.valueOf(order.remaining()));
        return order;
    }

    public boolean isEnded() {
        checkStateIsConfigured();
        long j = this.outputPosition;
        return j >= this.endPosition || (j >= this.maxPositionOfRemovedSources && this.sources.size() == 0);
    }

    public void reset() {
        this.sources.clear();
        this.nextSourceId = 0;
        this.outputAudioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.bufferSizeFrames = -1;
        this.mixingBuffers = new MixingBuffer[0];
        this.mixerStartTimeUs = C.TIME_UNSET;
        this.inputLimit = -1;
        long j = 0;
        this.outputPosition = 0;
        this.endPosition = Long.MAX_VALUE;
        if (this.outputSilenceWithNoSources) {
            j = Long.MAX_VALUE;
        }
        this.maxPositionOfRemovedSources = j;
    }

    private void checkStateIsConfigured() {
        Assertions.checkState(!this.outputAudioFormat.equals(AudioProcessor.AudioFormat.NOT_SET), "Audio mixer is not configured.");
    }

    private MixingBuffer allocateMixingBuffer(long j) {
        ByteBuffer order = ByteBuffer.allocateDirect(this.bufferSizeFrames * this.outputAudioFormat.bytesPerFrame).order(ByteOrder.nativeOrder());
        order.mark();
        return new MixingBuffer(order, j, j + ((long) this.bufferSizeFrames));
    }

    private void updateInputFrameLimit() {
        this.inputLimit = Math.min(this.endPosition, this.outputPosition + ((long) this.bufferSizeFrames));
    }

    private SourceInfo getSourceById(int i) {
        Assertions.checkState(Util.contains(this.sources, i), "Source not found.");
        return this.sources.get(i);
    }

    private static class MixingBuffer {
        public final ByteBuffer buffer;
        public final long limit;
        public final long position;

        public MixingBuffer(ByteBuffer byteBuffer, long j, long j2) {
            this.buffer = byteBuffer;
            this.position = j;
            this.limit = j2;
        }
    }

    private final class SourceInfo {
        private final AudioProcessor.AudioFormat audioFormat;
        private final ChannelMixingMatrix baseChannelMixingMatrix;
        private ChannelMixingMatrix channelMixingMatrix;
        public long position;

        public SourceInfo(AudioProcessor.AudioFormat audioFormat2, ChannelMixingMatrix channelMixingMatrix2, long j) {
            this.audioFormat = audioFormat2;
            this.baseChannelMixingMatrix = channelMixingMatrix2;
            this.position = j;
            this.channelMixingMatrix = channelMixingMatrix2;
        }

        public ChannelMixingMatrix getChannelMixingMatrix() {
            return this.channelMixingMatrix;
        }

        public void setVolume(float f) {
            this.channelMixingMatrix = this.baseChannelMixingMatrix.scaleBy(f);
        }

        public long getPositionAfterBuffer(ByteBuffer byteBuffer) {
            return this.position + ((long) (byteBuffer.remaining() / this.audioFormat.bytesPerFrame));
        }

        public void discardTo(ByteBuffer byteBuffer, long j) {
            Assertions.checkArgument(j >= this.position);
            byteBuffer.position(byteBuffer.position() + (((int) (j - this.position)) * this.audioFormat.bytesPerFrame));
            this.position = j;
        }

        public void mixTo(ByteBuffer byteBuffer, long j, ByteBuffer byteBuffer2, AudioProcessor.AudioFormat audioFormat2) {
            Assertions.checkArgument(j >= this.position);
            AudioMixingUtil.mix(byteBuffer, this.audioFormat, byteBuffer2, audioFormat2, this.channelMixingMatrix, (int) (j - this.position), true, DefaultAudioMixer.this.clipFloatOutput);
            this.position = j;
        }
    }
}
