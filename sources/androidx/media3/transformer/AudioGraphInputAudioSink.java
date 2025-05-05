package androidx.media3.transformer;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.audio.AudioSink;
import java.nio.ByteBuffer;
import java.util.Objects;

final class AudioGraphInputAudioSink implements AudioSink {
    private final Controller controller;
    private EditedMediaItemInfo currentEditedMediaItemInfo;
    private Format currentInputFormat;
    private boolean inputStreamEnded;
    private long offsetToCompositionTimeUs;
    private AudioGraphInput outputGraphInput;
    private boolean signalledEndOfStream;

    public interface Controller {
        AudioGraphInput getAudioGraphInput(EditedMediaItem editedMediaItem, Format format) throws ExportException;

        long getCurrentPositionUs();

        boolean isEnded();

        void onPause() {
        }

        void onPlay() {
        }

        void onReset() {
        }
    }

    public void disableTunneling() {
    }

    public void enableTunnelingV21() {
    }

    public AudioAttributes getAudioAttributes() {
        return null;
    }

    public boolean getSkipSilenceEnabled() {
        return false;
    }

    public void handleDiscontinuity() {
    }

    public boolean hasPendingData() {
        return false;
    }

    public void setAudioAttributes(AudioAttributes audioAttributes) {
    }

    public void setAudioSessionId(int i) {
    }

    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo) {
    }

    public void setListener(AudioSink.Listener listener) {
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
    }

    public void setSkipSilenceEnabled(boolean z) {
    }

    public void setVolume(float f) {
    }

    public AudioGraphInputAudioSink(Controller controller2) {
        this.controller = controller2;
    }

    public void onMediaItemChanged(EditedMediaItem editedMediaItem, long j, boolean z) {
        this.currentEditedMediaItemInfo = new EditedMediaItemInfo(editedMediaItem, z);
        this.offsetToCompositionTimeUs = j;
    }

    public void configure(Format format, int i, int[] iArr) throws AudioSink.ConfigurationException {
        Assertions.checkArgument(supportsFormat(format));
        EditedMediaItem editedMediaItem = ((EditedMediaItemInfo) Assertions.checkStateNotNull(this.currentEditedMediaItemInfo)).editedMediaItem;
        Assertions.checkArgument(iArr == null);
        this.currentInputFormat = format;
        AudioGraphInput audioGraphInput = this.outputGraphInput;
        if (audioGraphInput != null) {
            audioGraphInput.onMediaItemChanged(editedMediaItem, C.TIME_UNSET, format, false);
        }
    }

    public boolean isEnded() {
        if (this.currentInputFormat == null) {
            return this.inputStreamEnded;
        }
        return this.inputStreamEnded && (!((EditedMediaItemInfo) Assertions.checkStateNotNull(this.currentEditedMediaItemInfo)).isLastInSequence || this.controller.isEnded());
    }

    public boolean handleBuffer(ByteBuffer byteBuffer, long j, int i) throws AudioSink.InitializationException {
        Assertions.checkState(!this.inputStreamEnded);
        EditedMediaItem editedMediaItem = ((EditedMediaItemInfo) Assertions.checkStateNotNull(this.currentEditedMediaItemInfo)).editedMediaItem;
        if (this.outputGraphInput == null) {
            try {
                AudioGraphInput audioGraphInput = this.controller.getAudioGraphInput(editedMediaItem, (Format) Assertions.checkStateNotNull(this.currentInputFormat));
                if (audioGraphInput == null) {
                    return false;
                }
                this.outputGraphInput = audioGraphInput;
                audioGraphInput.onMediaItemChanged(editedMediaItem, C.TIME_UNSET, this.currentInputFormat, false);
            } catch (ExportException e) {
                throw new AudioSink.InitializationException("Error creating AudioGraphInput", 0, this.currentInputFormat, false, e);
            }
        }
        return handleBufferInternal(byteBuffer, j, 0);
    }

    public void playToEndOfStream() {
        this.inputStreamEnded = true;
        if (this.currentInputFormat != null && !this.signalledEndOfStream && ((EditedMediaItemInfo) Assertions.checkStateNotNull(this.currentEditedMediaItemInfo)).isLastInSequence) {
            this.signalledEndOfStream = handleBufferInternal(AudioProcessor.EMPTY_BUFFER, Long.MIN_VALUE, 4);
        }
    }

    public int getFormatSupport(Format format) {
        return (!Objects.equals(format.sampleMimeType, MimeTypes.AUDIO_RAW) || format.pcmEncoding != 2) ? 0 : 2;
    }

    public boolean supportsFormat(Format format) {
        return getFormatSupport(format) == 2;
    }

    public long getCurrentPositionUs(boolean z) {
        long currentPositionUs = this.controller.getCurrentPositionUs();
        return currentPositionUs != Long.MIN_VALUE ? currentPositionUs - this.offsetToCompositionTimeUs : currentPositionUs;
    }

    public void play() {
        this.controller.onPlay();
    }

    public void pause() {
        this.controller.onPause();
    }

    public void flush() {
        this.inputStreamEnded = false;
        this.signalledEndOfStream = false;
    }

    public void reset() {
        flush();
        this.currentInputFormat = null;
        this.currentEditedMediaItemInfo = null;
        this.controller.onReset();
    }

    public PlaybackParameters getPlaybackParameters() {
        return PlaybackParameters.DEFAULT;
    }

    private boolean handleBufferInternal(ByteBuffer byteBuffer, long j, int i) {
        Assertions.checkStateNotNull(this.currentInputFormat);
        Assertions.checkState(!this.signalledEndOfStream);
        AudioGraphInput audioGraphInput = (AudioGraphInput) Assertions.checkNotNull(this.outputGraphInput);
        DecoderInputBuffer inputBuffer = audioGraphInput.getInputBuffer();
        if (inputBuffer == null) {
            return false;
        }
        inputBuffer.ensureSpaceForWrite(byteBuffer.remaining());
        ((ByteBuffer) Assertions.checkNotNull(inputBuffer.data)).put(byteBuffer).flip();
        long j2 = Long.MIN_VALUE;
        if (j != Long.MIN_VALUE) {
            j2 = this.offsetToCompositionTimeUs + j;
        }
        inputBuffer.timeUs = j2;
        inputBuffer.setFlags(i);
        return audioGraphInput.queueInputBuffer();
    }

    private static final class EditedMediaItemInfo {
        public final EditedMediaItem editedMediaItem;
        public final boolean isLastInSequence;

        public EditedMediaItemInfo(EditedMediaItem editedMediaItem2, boolean z) {
            this.editedMediaItem = editedMediaItem2;
            this.isLastInSequence = z;
        }
    }
}
