package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.BaseAudioProcessor;
import androidx.media3.common.util.Assertions;
import java.nio.ByteBuffer;

final class ChannelMappingAudioProcessor extends BaseAudioProcessor {
    private int[] outputChannels;
    private int[] pendingOutputChannels;

    ChannelMappingAudioProcessor() {
    }

    public void setChannelMap(int[] iArr) {
        this.pendingOutputChannels = iArr;
    }

    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        int[] iArr = this.pendingOutputChannels;
        if (iArr == null) {
            return AudioProcessor.AudioFormat.NOT_SET;
        }
        if (audioFormat.encoding == 2) {
            boolean z = audioFormat.channelCount != iArr.length;
            int i = 0;
            while (i < iArr.length) {
                int i2 = iArr[i];
                if (i2 < audioFormat.channelCount) {
                    z |= i2 != i;
                    i++;
                } else {
                    throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
                }
            }
            if (z) {
                return new AudioProcessor.AudioFormat(audioFormat.sampleRate, iArr.length, 2);
            }
            return AudioProcessor.AudioFormat.NOT_SET;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    public void queueInput(ByteBuffer byteBuffer) {
        int[] iArr = (int[]) Assertions.checkNotNull(this.outputChannels);
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        ByteBuffer replaceOutputBuffer = replaceOutputBuffer(((limit - position) / this.inputAudioFormat.bytesPerFrame) * this.outputAudioFormat.bytesPerFrame);
        while (position < limit) {
            for (int i : iArr) {
                replaceOutputBuffer.putShort(byteBuffer.getShort((i * 2) + position));
            }
            position += this.inputAudioFormat.bytesPerFrame;
        }
        byteBuffer.position(limit);
        replaceOutputBuffer.flip();
    }

    /* access modifiers changed from: protected */
    public void onFlush() {
        this.outputChannels = this.pendingOutputChannels;
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        this.outputChannels = null;
        this.pendingOutputChannels = null;
    }
}
