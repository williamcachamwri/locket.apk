package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import java.nio.ByteBuffer;

class SynchronizedSonicAudioProcessor implements AudioProcessor {
    private final Object lock;
    private final SonicAudioProcessor sonicAudioProcessor = new SonicAudioProcessor();

    public SynchronizedSonicAudioProcessor(Object obj) {
        this.lock = obj;
    }

    public final void setSpeed(float f) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.setSpeed(f);
        }
    }

    public final void setPitch(float f) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.setPitch(f);
        }
    }

    public final void setOutputSampleRateHz(int i) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.setOutputSampleRateHz(i);
        }
    }

    public final long getMediaDuration(long j) {
        long mediaDuration;
        synchronized (this.lock) {
            mediaDuration = this.sonicAudioProcessor.getMediaDuration(j);
        }
        return mediaDuration;
    }

    public final long getPlayoutDuration(long j) {
        long playoutDuration;
        synchronized (this.lock) {
            playoutDuration = this.sonicAudioProcessor.getPlayoutDuration(j);
        }
        return playoutDuration;
    }

    public final long getProcessedInputBytes() {
        long processedInputBytes;
        synchronized (this.lock) {
            processedInputBytes = this.sonicAudioProcessor.getProcessedInputBytes();
        }
        return processedInputBytes;
    }

    public long getDurationAfterProcessorApplied(long j) {
        return getPlayoutDuration(j);
    }

    public final AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        AudioProcessor.AudioFormat configure;
        synchronized (this.lock) {
            configure = this.sonicAudioProcessor.configure(audioFormat);
        }
        return configure;
    }

    public final boolean isActive() {
        boolean isActive;
        synchronized (this.lock) {
            isActive = this.sonicAudioProcessor.isActive();
        }
        return isActive;
    }

    public final void queueInput(ByteBuffer byteBuffer) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.queueInput(byteBuffer);
        }
    }

    public final void queueEndOfStream() {
        synchronized (this.lock) {
            this.sonicAudioProcessor.queueEndOfStream();
        }
    }

    public final ByteBuffer getOutput() {
        ByteBuffer output;
        synchronized (this.lock) {
            output = this.sonicAudioProcessor.getOutput();
        }
        return output;
    }

    public final boolean isEnded() {
        boolean isEnded;
        synchronized (this.lock) {
            isEnded = this.sonicAudioProcessor.isEnded();
        }
        return isEnded;
    }

    public final void flush() {
        synchronized (this.lock) {
            this.sonicAudioProcessor.flush();
        }
    }

    public final void reset() {
        synchronized (this.lock) {
            this.sonicAudioProcessor.reset();
        }
    }
}
