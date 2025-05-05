package androidx.media3.transformer;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.transformer.DefaultAudioMixer;
import java.nio.ByteBuffer;

public interface AudioMixer {

    public interface Factory {
        AudioMixer create();
    }

    int addSource(AudioProcessor.AudioFormat audioFormat, long j) throws AudioProcessor.UnhandledAudioFormatException;

    void configure(AudioProcessor.AudioFormat audioFormat, int i, long j) throws AudioProcessor.UnhandledAudioFormatException;

    ByteBuffer getOutput();

    boolean hasSource(int i);

    boolean isEnded();

    void queueInput(int i, ByteBuffer byteBuffer);

    void removeSource(int i);

    void reset();

    void setEndTimeUs(long j);

    void setSourceVolume(int i, float f);

    boolean supportsSourceAudioFormat(AudioProcessor.AudioFormat audioFormat);

    @Deprecated
    static AudioMixer create() {
        return new DefaultAudioMixer.Factory(true, true).create();
    }
}
