package androidx.media3.transformer;

import android.media.MediaCodec;
import androidx.media3.common.Format;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.transformer.AudioMixer;
import androidx.media3.transformer.Codec;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import org.checkerframework.dataflow.qual.Pure;

final class AudioSampleExporter extends SampleExporter {
    private final AudioGraph audioGraph;
    private final Codec encoder;
    private final AudioProcessor.AudioFormat encoderInputAudioFormat;
    private final DecoderInputBuffer encoderInputBuffer;
    private final DecoderInputBuffer encoderOutputBuffer;
    private long encoderTotalInputBytes;
    private final AudioGraphInput firstInput;
    private final Format firstInputFormat;
    private boolean returnedFirstInput;

    public AudioSampleExporter(Format format, Format format2, TransformationRequest transformationRequest, EditedMediaItem editedMediaItem, ImmutableList<AudioProcessor> immutableList, AudioMixer.Factory factory, Codec.EncoderFactory encoderFactory, MuxerWrapper muxerWrapper, FallbackListener fallbackListener) throws ExportException {
        super(format, muxerWrapper);
        String str;
        AudioGraph audioGraph2 = new AudioGraph(factory, immutableList);
        this.audioGraph = audioGraph2;
        this.firstInputFormat = format2;
        this.firstInput = audioGraph2.registerInput(editedMediaItem, format2);
        AudioProcessor.AudioFormat outputAudioFormat = audioGraph2.getOutputAudioFormat();
        this.encoderInputAudioFormat = outputAudioFormat;
        Assertions.checkState(!outputAudioFormat.equals(AudioProcessor.AudioFormat.NOT_SET));
        Format.Builder builder = new Format.Builder();
        if (transformationRequest.audioMimeType != null) {
            str = transformationRequest.audioMimeType;
        } else {
            str = (String) Assertions.checkNotNull(format.sampleMimeType);
        }
        Format build = builder.setSampleMimeType(str).setSampleRate(outputAudioFormat.sampleRate).setChannelCount(outputAudioFormat.channelCount).setPcmEncoding(outputAudioFormat.encoding).setCodecs(format2.codecs).build();
        Codec createForAudioEncoding = encoderFactory.createForAudioEncoding(build.buildUpon().setSampleMimeType(findSupportedMimeTypeForEncoderAndMuxer(build, muxerWrapper.getSupportedSampleMimeTypes(1))).build());
        this.encoder = createForAudioEncoding;
        this.encoderInputBuffer = new DecoderInputBuffer(0);
        this.encoderOutputBuffer = new DecoderInputBuffer(0);
        fallbackListener.onTransformationRequestFinalized(createFallbackTransformationRequest(transformationRequest, build, createForAudioEncoding.getConfigurationFormat()));
    }

    public AudioGraphInput getInput(EditedMediaItem editedMediaItem, Format format, int i) throws ExportException {
        if (this.returnedFirstInput) {
            return this.audioGraph.registerInput(editedMediaItem, format);
        }
        this.returnedFirstInput = true;
        Assertions.checkState(format.equals(this.firstInputFormat));
        return this.firstInput;
    }

    public void release() {
        this.audioGraph.reset();
        this.encoder.release();
    }

    /* access modifiers changed from: protected */
    public boolean processDataUpToMuxer() throws ExportException {
        ByteBuffer output = this.audioGraph.getOutput();
        if (!this.encoder.maybeDequeueInputBuffer(this.encoderInputBuffer)) {
            return false;
        }
        if (this.audioGraph.isEnded()) {
            DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_AUDIO_GRAPH, DebugTraceUtil.EVENT_OUTPUT_ENDED, Long.MIN_VALUE);
            queueEndOfStreamToEncoder();
            return false;
        } else if (!output.hasRemaining()) {
            return false;
        } else {
            feedEncoder(output);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public Format getMuxerInputFormat() throws ExportException {
        return this.encoder.getOutputFormat();
    }

    /* access modifiers changed from: protected */
    public DecoderInputBuffer getMuxerInputBuffer() throws ExportException {
        this.encoderOutputBuffer.data = this.encoder.getOutputBuffer();
        if (this.encoderOutputBuffer.data == null) {
            return null;
        }
        this.encoderOutputBuffer.timeUs = ((MediaCodec.BufferInfo) Assertions.checkNotNull(this.encoder.getOutputBufferInfo())).presentationTimeUs;
        this.encoderOutputBuffer.setFlags(1);
        return this.encoderOutputBuffer;
    }

    /* access modifiers changed from: protected */
    public void releaseMuxerInputBuffer() throws ExportException {
        this.encoder.releaseOutputBuffer(false);
    }

    /* access modifiers changed from: protected */
    public boolean isMuxerInputEnded() {
        return this.encoder.isEnded();
    }

    private void feedEncoder(ByteBuffer byteBuffer) throws ExportException {
        ByteBuffer byteBuffer2 = (ByteBuffer) Assertions.checkNotNull(this.encoderInputBuffer.data);
        int limit = byteBuffer.limit();
        byteBuffer.limit(Math.min(limit, byteBuffer.position() + byteBuffer2.capacity()));
        byteBuffer2.put(byteBuffer);
        this.encoderInputBuffer.timeUs = getOutputAudioDurationUs();
        this.encoderTotalInputBytes += (long) byteBuffer2.position();
        this.encoderInputBuffer.setFlags(0);
        this.encoderInputBuffer.flip();
        byteBuffer.limit(limit);
        this.encoder.queueInputBuffer(this.encoderInputBuffer);
    }

    private void queueEndOfStreamToEncoder() throws ExportException {
        Assertions.checkState(((ByteBuffer) Assertions.checkNotNull(this.encoderInputBuffer.data)).position() == 0);
        this.encoderInputBuffer.timeUs = getOutputAudioDurationUs();
        this.encoderInputBuffer.addFlag(4);
        this.encoderInputBuffer.flip();
        this.encoder.queueInputBuffer(this.encoderInputBuffer);
    }

    @Pure
    private static TransformationRequest createFallbackTransformationRequest(TransformationRequest transformationRequest, Format format, Format format2) {
        if (Util.areEqual(format.sampleMimeType, format2.sampleMimeType)) {
            return transformationRequest;
        }
        return transformationRequest.buildUpon().setAudioMimeType(format2.sampleMimeType).build();
    }

    private long getOutputAudioDurationUs() {
        return ((this.encoderTotalInputBytes / ((long) this.encoderInputAudioFormat.bytesPerFrame)) * 1000000) / ((long) this.encoderInputAudioFormat.sampleRate);
    }
}
