package androidx.media3.common.audio;

import android.util.SparseArray;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import java.nio.ByteBuffer;

public final class ChannelMixingAudioProcessor extends BaseAudioProcessor {
    private final SparseArray<ChannelMixingMatrix> matrixByInputChannelCount = new SparseArray<>();

    public void putChannelMixingMatrix(ChannelMixingMatrix channelMixingMatrix) {
        this.matrixByInputChannelCount.put(channelMixingMatrix.getInputChannelCount(), channelMixingMatrix);
    }

    /* access modifiers changed from: protected */
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (audioFormat.encoding == 2) {
            ChannelMixingMatrix channelMixingMatrix = this.matrixByInputChannelCount.get(audioFormat.channelCount);
            if (channelMixingMatrix == null) {
                throw new AudioProcessor.UnhandledAudioFormatException("No mixing matrix for input channel count", audioFormat);
            } else if (channelMixingMatrix.isIdentity()) {
                return AudioProcessor.AudioFormat.NOT_SET;
            } else {
                return new AudioProcessor.AudioFormat(audioFormat.sampleRate, channelMixingMatrix.getOutputChannelCount(), 2);
            }
        } else {
            throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
        }
    }

    public void queueInput(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining() / this.inputAudioFormat.bytesPerFrame;
        ByteBuffer replaceOutputBuffer = replaceOutputBuffer(this.outputAudioFormat.bytesPerFrame * remaining);
        ByteBuffer byteBuffer2 = byteBuffer;
        ByteBuffer byteBuffer3 = replaceOutputBuffer;
        AudioMixingUtil.mix(byteBuffer2, this.inputAudioFormat, byteBuffer3, this.outputAudioFormat, (ChannelMixingMatrix) Assertions.checkStateNotNull(this.matrixByInputChannelCount.get(this.inputAudioFormat.channelCount)), remaining, false, true);
        replaceOutputBuffer.flip();
    }
}
