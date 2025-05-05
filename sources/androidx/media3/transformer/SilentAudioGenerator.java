package androidx.media3.transformer;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicLong;

final class SilentAudioGenerator {
    private static final int DEFAULT_BUFFER_SIZE_FRAMES = 1024;
    public final AudioProcessor.AudioFormat audioFormat;
    private final ByteBuffer internalBuffer;
    private final AtomicLong remainingBytesToOutput = new AtomicLong();

    public SilentAudioGenerator(AudioProcessor.AudioFormat audioFormat2) {
        this.audioFormat = audioFormat2;
        ByteBuffer order = ByteBuffer.allocateDirect(audioFormat2.bytesPerFrame * 1024).order(ByteOrder.nativeOrder());
        this.internalBuffer = order;
        order.flip();
    }

    public void addSilence(long j) {
        this.remainingBytesToOutput.addAndGet(((long) this.audioFormat.bytesPerFrame) * Util.durationUsToSampleCount(j, this.audioFormat.sampleRate));
    }

    public ByteBuffer getBuffer() {
        long j = this.remainingBytesToOutput.get();
        if (!this.internalBuffer.hasRemaining()) {
            this.internalBuffer.clear();
            if (j < ((long) this.internalBuffer.capacity())) {
                this.internalBuffer.limit((int) j);
            }
            this.remainingBytesToOutput.addAndGet((long) (-this.internalBuffer.remaining()));
        }
        return this.internalBuffer;
    }

    public boolean hasRemaining() {
        return this.internalBuffer.hasRemaining() || this.remainingBytesToOutput.get() > 0;
    }

    public void flush() {
        this.remainingBytesToOutput.set(0);
        this.internalBuffer.position(0);
        this.internalBuffer.limit(0);
    }
}
