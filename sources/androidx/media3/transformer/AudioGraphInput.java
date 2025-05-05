package androidx.media3.transformer;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.audio.AudioProcessingPipeline;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.ChannelMixingAudioProcessor;
import androidx.media3.common.audio.ChannelMixingMatrix;
import androidx.media3.common.audio.SonicAudioProcessor;
import androidx.media3.common.audio.SpeedChangingAudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

final class AudioGraphInput implements GraphInput {
    private static final long MAX_AUDIO_DRIFT_ALLOWED_US = 2000;
    private static final int MAX_INPUT_BUFFER_COUNT = 10;
    private AudioProcessingPipeline audioProcessingPipeline;
    private final Queue<DecoderInputBuffer> availableInputBuffers = new ConcurrentLinkedQueue();
    private DecoderInputBuffer currentInputBufferBeingOutput;
    private long currentItemExpectedInputDurationUs;
    private long currentItemInputBytesRead;
    private boolean currentItemSilenceAppended;
    private boolean inputBlocked;
    private boolean isCurrentItemLast;
    private final AudioProcessor.AudioFormat outputAudioFormat;
    private final Queue<DecoderInputBuffer> pendingInputBuffers;
    private final Queue<MediaItemChange> pendingMediaItemChanges;
    private boolean processedFirstMediaItemChange;
    private boolean queueEndOfStreamAfterSilence;
    private boolean receivedEndOfStreamFromInput;
    private SilentAudioGenerator silentAudioGenerator;
    private final AtomicLong startTimeUs;

    public AudioGraphInput(AudioProcessor.AudioFormat audioFormat, EditedMediaItem editedMediaItem, Format format) throws AudioProcessor.UnhandledAudioFormatException {
        AudioProcessor.AudioFormat audioFormat2 = new AudioProcessor.AudioFormat(format);
        Assertions.checkArgument(AudioGraph.isInputAudioFormatValid(audioFormat2), audioFormat2);
        boolean z = false;
        ByteBuffer order = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());
        for (int i = 0; i < 10; i++) {
            DecoderInputBuffer decoderInputBuffer = new DecoderInputBuffer(2);
            decoderInputBuffer.data = order;
            this.availableInputBuffers.add(decoderInputBuffer);
        }
        this.pendingInputBuffers = new ConcurrentLinkedQueue();
        this.pendingMediaItemChanges = new ConcurrentLinkedQueue();
        this.silentAudioGenerator = new SilentAudioGenerator(audioFormat2);
        AudioProcessingPipeline configureProcessing = configureProcessing(editedMediaItem, format, audioFormat2, audioFormat);
        this.audioProcessingPipeline = configureProcessing;
        configureProcessing.flush();
        AudioProcessor.AudioFormat outputAudioFormat2 = this.audioProcessingPipeline.getOutputAudioFormat();
        this.outputAudioFormat = outputAudioFormat2;
        Assertions.checkArgument(outputAudioFormat2.encoding == 2 ? true : z, outputAudioFormat2);
        this.startTimeUs = new AtomicLong(C.TIME_UNSET);
        this.currentItemExpectedInputDurationUs = C.TIME_UNSET;
    }

    public AudioProcessor.AudioFormat getOutputAudioFormat() {
        return this.outputAudioFormat;
    }

    public ByteBuffer getOutput() throws AudioProcessor.UnhandledAudioFormatException {
        ByteBuffer outputInternal = getOutputInternal();
        if (outputInternal.hasRemaining()) {
            return outputInternal;
        }
        if (!hasDataToOutput() && !this.pendingMediaItemChanges.isEmpty()) {
            configureForPendingMediaItemChange();
        }
        return AudioProcessor.EMPTY_BUFFER;
    }

    public void onMediaItemChanged(EditedMediaItem editedMediaItem, long j, Format format, boolean z) {
        if (format == null) {
            Assertions.checkState(j != C.TIME_UNSET, "Could not generate silent audio because duration is unknown.");
        } else {
            Assertions.checkState(MimeTypes.isAudio(format.sampleMimeType));
            AudioProcessor.AudioFormat audioFormat = new AudioProcessor.AudioFormat(format);
            Assertions.checkState(AudioGraph.isInputAudioFormatValid(audioFormat), audioFormat);
        }
        this.pendingMediaItemChanges.add(new MediaItemChange(editedMediaItem, j, format, z));
    }

    public DecoderInputBuffer getInputBuffer() {
        if (this.inputBlocked || !this.pendingMediaItemChanges.isEmpty()) {
            return null;
        }
        return this.availableInputBuffers.peek();
    }

    public boolean queueInputBuffer() {
        if (this.inputBlocked) {
            return false;
        }
        Assertions.checkState(this.pendingMediaItemChanges.isEmpty());
        DecoderInputBuffer remove = this.availableInputBuffers.remove();
        this.pendingInputBuffers.add(remove);
        this.startTimeUs.compareAndSet(C.TIME_UNSET, remove.timeUs);
        return true;
    }

    public long getStartTimeUs() {
        return this.startTimeUs.get();
    }

    public void blockInput() {
        this.inputBlocked = true;
    }

    public void unblockInput() {
        this.inputBlocked = false;
    }

    public void flush() {
        this.pendingMediaItemChanges.clear();
        boolean z = true;
        this.processedFirstMediaItemChange = true;
        if (!this.availableInputBuffers.isEmpty()) {
            clearAndAddToAvailableBuffers(this.availableInputBuffers.remove());
        }
        DecoderInputBuffer decoderInputBuffer = this.currentInputBufferBeingOutput;
        if (decoderInputBuffer != null) {
            clearAndAddToAvailableBuffers(decoderInputBuffer);
            this.currentInputBufferBeingOutput = null;
        }
        while (!this.pendingInputBuffers.isEmpty()) {
            clearAndAddToAvailableBuffers(this.pendingInputBuffers.remove());
        }
        if (this.availableInputBuffers.size() != 10) {
            z = false;
        }
        Assertions.checkState(z);
        this.silentAudioGenerator.flush();
        this.audioProcessingPipeline.flush();
        this.receivedEndOfStreamFromInput = false;
        this.queueEndOfStreamAfterSilence = false;
        this.startTimeUs.set(C.TIME_UNSET);
        this.currentItemExpectedInputDurationUs = C.TIME_UNSET;
        this.currentItemInputBytesRead = 0;
        this.currentItemSilenceAppended = false;
        this.isCurrentItemLast = false;
    }

    public void release() {
        this.audioProcessingPipeline.reset();
    }

    public boolean isEnded() {
        if (hasDataToOutput() || !this.pendingMediaItemChanges.isEmpty()) {
            return false;
        }
        if (this.currentItemExpectedInputDurationUs != C.TIME_UNSET) {
            if (!this.isCurrentItemLast) {
                return false;
            }
            if (this.receivedEndOfStreamFromInput || this.queueEndOfStreamAfterSilence) {
                return true;
            }
            return false;
        } else if (this.receivedEndOfStreamFromInput || this.queueEndOfStreamAfterSilence) {
            return true;
        } else {
            return false;
        }
    }

    private ByteBuffer getOutputInternal() {
        if (!this.processedFirstMediaItemChange) {
            return AudioProcessor.EMPTY_BUFFER;
        }
        if (!this.audioProcessingPipeline.isOperational()) {
            return feedOutputFromInput();
        }
        do {
        } while (feedProcessingPipelineFromInput());
        return this.audioProcessingPipeline.getOutput();
    }

    private boolean feedProcessingPipelineFromInput() {
        if (this.silentAudioGenerator.hasRemaining()) {
            ByteBuffer buffer = this.silentAudioGenerator.getBuffer();
            this.audioProcessingPipeline.queueInput(buffer);
            if (buffer.hasRemaining()) {
                return false;
            }
            if (this.silentAudioGenerator.hasRemaining()) {
                return true;
            }
            this.audioProcessingPipeline.queueEndOfStream();
            return false;
        }
        DecoderInputBuffer peek = this.pendingInputBuffers.peek();
        if (peek == null) {
            if (!this.pendingMediaItemChanges.isEmpty()) {
                if (shouldAppendSilence()) {
                    appendSilence();
                    return true;
                }
                this.audioProcessingPipeline.queueEndOfStream();
            }
            return false;
        } else if (!peek.isEndOfStream()) {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(peek.data);
            this.audioProcessingPipeline.queueInput(byteBuffer);
            this.currentItemInputBytesRead += ((long) byteBuffer.remaining()) - ((long) byteBuffer.remaining());
            if (byteBuffer.hasRemaining()) {
                return false;
            }
            clearAndAddToAvailableBuffers(this.pendingInputBuffers.remove());
            return true;
        } else if (shouldAppendSilence()) {
            appendSilence();
            clearAndAddToAvailableBuffers(this.pendingInputBuffers.remove());
            return true;
        } else {
            this.audioProcessingPipeline.queueEndOfStream();
            this.receivedEndOfStreamFromInput = true;
            clearAndAddToAvailableBuffers(this.pendingInputBuffers.remove());
            return false;
        }
    }

    private ByteBuffer feedOutputFromInput() {
        if (this.silentAudioGenerator.hasRemaining()) {
            return this.silentAudioGenerator.getBuffer();
        }
        DecoderInputBuffer decoderInputBuffer = this.currentInputBufferBeingOutput;
        if (decoderInputBuffer != null) {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkStateNotNull(decoderInputBuffer.data);
            if (byteBuffer.hasRemaining()) {
                return byteBuffer;
            }
            clearAndAddToAvailableBuffers((DecoderInputBuffer) Assertions.checkStateNotNull(this.currentInputBufferBeingOutput));
            this.currentInputBufferBeingOutput = null;
        }
        DecoderInputBuffer poll = this.pendingInputBuffers.poll();
        if (poll == null) {
            if (!this.pendingMediaItemChanges.isEmpty() && shouldAppendSilence()) {
                appendSilence();
            }
            return AudioProcessor.EMPTY_BUFFER;
        }
        ByteBuffer byteBuffer2 = poll.data;
        this.receivedEndOfStreamFromInput = poll.isEndOfStream();
        if (byteBuffer2 == null || !byteBuffer2.hasRemaining() || this.receivedEndOfStreamFromInput) {
            clearAndAddToAvailableBuffers(poll);
            if (this.receivedEndOfStreamFromInput && shouldAppendSilence()) {
                appendSilence();
            }
            return AudioProcessor.EMPTY_BUFFER;
        }
        this.currentInputBufferBeingOutput = poll;
        this.currentItemInputBytesRead += (long) byteBuffer2.remaining();
        return byteBuffer2;
    }

    private boolean hasDataToOutput() {
        if (!this.processedFirstMediaItemChange) {
            return false;
        }
        DecoderInputBuffer decoderInputBuffer = this.currentInputBufferBeingOutput;
        if ((decoderInputBuffer != null && decoderInputBuffer.data != null && this.currentInputBufferBeingOutput.data.hasRemaining()) || this.silentAudioGenerator.hasRemaining() || !this.pendingInputBuffers.isEmpty()) {
            return true;
        }
        if (!this.audioProcessingPipeline.isOperational() || this.audioProcessingPipeline.isEnded()) {
            return false;
        }
        return true;
    }

    private void clearAndAddToAvailableBuffers(DecoderInputBuffer decoderInputBuffer) {
        decoderInputBuffer.clear();
        decoderInputBuffer.timeUs = 0;
        this.availableInputBuffers.add(decoderInputBuffer);
    }

    private void configureForPendingMediaItemChange() throws AudioProcessor.UnhandledAudioFormatException {
        AudioProcessor.AudioFormat audioFormat;
        MediaItemChange mediaItemChange = (MediaItemChange) Assertions.checkStateNotNull(this.pendingMediaItemChanges.poll());
        this.currentItemInputBytesRead = 0;
        this.isCurrentItemLast = mediaItemChange.isLast;
        this.currentItemSilenceAppended = false;
        if (mediaItemChange.format != null) {
            this.currentItemExpectedInputDurationUs = mediaItemChange.durationUs;
            audioFormat = new AudioProcessor.AudioFormat(mediaItemChange.format);
            this.silentAudioGenerator = new SilentAudioGenerator(audioFormat);
        } else {
            if (mediaItemChange.editedMediaItem.effects.audioProcessors.isEmpty()) {
                this.currentItemExpectedInputDurationUs = mediaItemChange.editedMediaItem.getDurationAfterEffectsApplied(mediaItemChange.durationUs);
            } else {
                this.currentItemExpectedInputDurationUs = mediaItemChange.durationUs;
            }
            AudioProcessor.AudioFormat audioFormat2 = this.silentAudioGenerator.audioFormat;
            this.startTimeUs.compareAndSet(C.TIME_UNSET, 0);
            appendSilence();
            audioFormat = audioFormat2;
        }
        if (this.processedFirstMediaItemChange) {
            this.audioProcessingPipeline = configureProcessing(mediaItemChange.editedMediaItem, mediaItemChange.format, audioFormat, this.outputAudioFormat);
        }
        this.audioProcessingPipeline.flush();
        this.receivedEndOfStreamFromInput = false;
        this.processedFirstMediaItemChange = true;
    }

    private boolean shouldAppendSilence() {
        if (!this.currentItemSilenceAppended) {
            long j = this.currentItemExpectedInputDurationUs;
            return j != C.TIME_UNSET && j - currentItemActualInputDurationUs() > 2000;
        }
    }

    private void appendSilence() {
        this.silentAudioGenerator.addSilence(this.currentItemExpectedInputDurationUs - currentItemActualInputDurationUs());
        this.currentItemSilenceAppended = true;
        if (this.isCurrentItemLast) {
            this.queueEndOfStreamAfterSilence = true;
        }
    }

    private long currentItemActualInputDurationUs() {
        return Util.sampleCountToDurationUs(this.currentItemInputBytesRead / ((long) this.silentAudioGenerator.audioFormat.bytesPerFrame), this.silentAudioGenerator.audioFormat.sampleRate);
    }

    private static AudioProcessingPipeline configureProcessing(EditedMediaItem editedMediaItem, Format format, AudioProcessor.AudioFormat audioFormat, AudioProcessor.AudioFormat audioFormat2) throws AudioProcessor.UnhandledAudioFormatException {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        if (!(!editedMediaItem.flattenForSlowMotion || format == null || format.metadata == null)) {
            builder.add((Object) new SpeedChangingAudioProcessor(new SegmentSpeedProvider(format.metadata)));
        }
        builder.addAll((Iterable) editedMediaItem.effects.audioProcessors);
        if (audioFormat2.sampleRate != -1) {
            SonicAudioProcessor sonicAudioProcessor = new SonicAudioProcessor();
            sonicAudioProcessor.setOutputSampleRateHz(audioFormat2.sampleRate);
            builder.add((Object) sonicAudioProcessor);
        }
        if (audioFormat2.channelCount == 1 || audioFormat2.channelCount == 2) {
            ChannelMixingAudioProcessor channelMixingAudioProcessor = new ChannelMixingAudioProcessor();
            channelMixingAudioProcessor.putChannelMixingMatrix(ChannelMixingMatrix.create(1, audioFormat2.channelCount));
            channelMixingAudioProcessor.putChannelMixingMatrix(ChannelMixingMatrix.create(2, audioFormat2.channelCount));
            builder.add((Object) channelMixingAudioProcessor);
        }
        AudioProcessingPipeline audioProcessingPipeline2 = new AudioProcessingPipeline(builder.build());
        AudioProcessor.AudioFormat configure = audioProcessingPipeline2.configure(audioFormat);
        if ((audioFormat2.sampleRate == -1 || audioFormat2.sampleRate == configure.sampleRate) && ((audioFormat2.channelCount == -1 || audioFormat2.channelCount == configure.channelCount) && (audioFormat2.encoding == -1 || audioFormat2.encoding == configure.encoding))) {
            return audioProcessingPipeline2;
        }
        throw new AudioProcessor.UnhandledAudioFormatException("Audio can not be modified to match downstream format", audioFormat);
    }

    private static final class MediaItemChange {
        public final long durationUs;
        public final EditedMediaItem editedMediaItem;
        public final Format format;
        public final boolean isLast;

        public MediaItemChange(EditedMediaItem editedMediaItem2, long j, Format format2, boolean z) {
            this.editedMediaItem = editedMediaItem2;
            this.durationUs = j;
            this.format = format2;
            this.isLast = z;
        }
    }
}
