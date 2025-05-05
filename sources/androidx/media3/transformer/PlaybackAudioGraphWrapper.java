package androidx.media3.transformer;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.transformer.AudioGraphInputAudioSink;
import androidx.media3.transformer.AudioMixer;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.util.Objects;

final class PlaybackAudioGraphWrapper {
    private static final int PRIMARY_SEQUENCE_INDEX = 0;
    /* access modifiers changed from: private */
    public final AudioGraph audioGraph;
    private int audioGraphInputsCreated;
    /* access modifiers changed from: private */
    public final AudioSink finalAudioSink;
    /* access modifiers changed from: private */
    public boolean hasRegisteredPrimaryFormat;
    /* access modifiers changed from: private */
    public int inputAudioSinksCreated;
    /* access modifiers changed from: private */
    public int inputAudioSinksPlaying;
    private AudioProcessor.AudioFormat outputAudioFormat = AudioProcessor.AudioFormat.NOT_SET;
    private long outputFramesWritten;
    private long seekPositionUs;

    static /* synthetic */ int access$008(PlaybackAudioGraphWrapper playbackAudioGraphWrapper) {
        int i = playbackAudioGraphWrapper.inputAudioSinksCreated;
        playbackAudioGraphWrapper.inputAudioSinksCreated = i + 1;
        return i;
    }

    static /* synthetic */ int access$308(PlaybackAudioGraphWrapper playbackAudioGraphWrapper) {
        int i = playbackAudioGraphWrapper.audioGraphInputsCreated;
        playbackAudioGraphWrapper.audioGraphInputsCreated = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(PlaybackAudioGraphWrapper playbackAudioGraphWrapper) {
        int i = playbackAudioGraphWrapper.inputAudioSinksPlaying;
        playbackAudioGraphWrapper.inputAudioSinksPlaying = i + 1;
        return i;
    }

    static /* synthetic */ int access$510(PlaybackAudioGraphWrapper playbackAudioGraphWrapper) {
        int i = playbackAudioGraphWrapper.inputAudioSinksPlaying;
        playbackAudioGraphWrapper.inputAudioSinksPlaying = i - 1;
        return i;
    }

    public PlaybackAudioGraphWrapper(AudioMixer.Factory factory, ImmutableList<AudioProcessor> immutableList, AudioSink audioSink) {
        this.audioGraph = new AudioGraph(factory, immutableList);
        this.finalAudioSink = audioSink;
    }

    public void release() {
        this.audioGraph.reset();
        this.finalAudioSink.reset();
        this.finalAudioSink.release();
        this.audioGraphInputsCreated = 0;
        this.inputAudioSinksCreated = 0;
        this.inputAudioSinksPlaying = 0;
    }

    public AudioGraphInputAudioSink createInput(int i) {
        return new AudioGraphInputAudioSink(new SinkController(i));
    }

    public boolean processData() throws ExportException, AudioSink.WriteException, AudioSink.InitializationException, AudioSink.ConfigurationException {
        int i = this.inputAudioSinksCreated;
        if (i == 0 || i != this.audioGraphInputsCreated) {
            return false;
        }
        if (Objects.equals(this.outputAudioFormat, AudioProcessor.AudioFormat.NOT_SET)) {
            AudioProcessor.AudioFormat outputAudioFormat2 = this.audioGraph.getOutputAudioFormat();
            if (Objects.equals(outputAudioFormat2, AudioProcessor.AudioFormat.NOT_SET)) {
                return false;
            }
            this.finalAudioSink.configure(Util.getPcmFormat(outputAudioFormat2), 0, (int[]) null);
            this.outputAudioFormat = outputAudioFormat2;
        }
        if (!this.audioGraph.isEnded()) {
            ByteBuffer output = this.audioGraph.getOutput();
            if (!output.hasRemaining()) {
                return false;
            }
            int remaining = output.remaining();
            boolean handleBuffer = this.finalAudioSink.handleBuffer(output, getBufferPresentationTimeUs(), 1);
            this.outputFramesWritten += (long) ((remaining - output.remaining()) / this.outputAudioFormat.bytesPerFrame);
            return handleBuffer;
        } else if (this.finalAudioSink.isEnded()) {
            return false;
        } else {
            this.finalAudioSink.playToEndOfStream();
            return false;
        }
    }

    private long getBufferPresentationTimeUs() {
        return this.seekPositionUs + Util.sampleCountToDurationUs(this.outputFramesWritten, this.outputAudioFormat.sampleRate);
    }

    public void startSeek(long j) {
        if (j == C.TIME_UNSET) {
            j = 0;
        }
        this.finalAudioSink.pause();
        this.audioGraph.blockInput();
        this.audioGraph.setPendingStartTimeUs(j);
        this.audioGraph.flush();
        this.finalAudioSink.flush();
        this.outputFramesWritten = 0;
        this.seekPositionUs = j;
    }

    public void endSeek() {
        this.audioGraph.unblockInput();
    }

    private final class SinkController implements AudioGraphInputAudioSink.Controller {
        private final boolean isSequencePrimary;
        private boolean playing;

        public SinkController(int i) {
            this.isSequencePrimary = i == 0;
            PlaybackAudioGraphWrapper.access$008(PlaybackAudioGraphWrapper.this);
        }

        public AudioGraphInput getAudioGraphInput(EditedMediaItem editedMediaItem, Format format) throws ExportException {
            if (!this.isSequencePrimary && !PlaybackAudioGraphWrapper.this.hasRegisteredPrimaryFormat) {
                return null;
            }
            AudioGraphInput registerInput = PlaybackAudioGraphWrapper.this.audioGraph.registerInput(editedMediaItem, format);
            PlaybackAudioGraphWrapper.access$308(PlaybackAudioGraphWrapper.this);
            if (this.isSequencePrimary) {
                boolean unused = PlaybackAudioGraphWrapper.this.hasRegisteredPrimaryFormat = true;
            }
            return registerInput;
        }

        public long getCurrentPositionUs() {
            return PlaybackAudioGraphWrapper.this.finalAudioSink.getCurrentPositionUs(false);
        }

        public boolean isEnded() {
            return PlaybackAudioGraphWrapper.this.finalAudioSink.isEnded();
        }

        public void onPlay() {
            if (!this.playing) {
                this.playing = true;
                PlaybackAudioGraphWrapper.access$508(PlaybackAudioGraphWrapper.this);
                if (PlaybackAudioGraphWrapper.this.inputAudioSinksCreated == PlaybackAudioGraphWrapper.this.inputAudioSinksPlaying) {
                    PlaybackAudioGraphWrapper.this.finalAudioSink.play();
                }
            }
        }

        public void onPause() {
            if (this.playing) {
                this.playing = false;
                if (PlaybackAudioGraphWrapper.this.inputAudioSinksCreated == PlaybackAudioGraphWrapper.this.inputAudioSinksPlaying) {
                    PlaybackAudioGraphWrapper.this.finalAudioSink.pause();
                }
                PlaybackAudioGraphWrapper.access$510(PlaybackAudioGraphWrapper.this);
            }
        }

        public void onReset() {
            onPause();
        }
    }
}
