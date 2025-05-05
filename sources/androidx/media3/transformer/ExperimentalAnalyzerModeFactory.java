package androidx.media3.transformer;

import android.content.Context;
import android.media.MediaCodec;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.video.PlaceholderSurface;
import androidx.media3.transformer.Codec;
import androidx.media3.transformer.NoWriteMuxer;
import androidx.media3.transformer.Transformer;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

public final class ExperimentalAnalyzerModeFactory {
    private ExperimentalAnalyzerModeFactory() {
    }

    public static Transformer buildAnalyzer(Context context) {
        return buildAnalyzer(context, new Transformer.Builder(context).build());
    }

    public static Transformer buildAnalyzer(Context context, Transformer transformer) {
        return transformer.buildUpon().experimentalSetTrimOptimizationEnabled(false).experimentalSetMaxFramesInEncoder(-1).setEncoderFactory(new DroppingEncoder.Factory(context)).setMaxDelayBetweenMuxerSamplesMs(C.TIME_UNSET).setMuxerFactory(new NoWriteMuxer.Factory(ImmutableList.of(MimeTypes.AUDIO_AAC), ImmutableList.of(MimeTypes.VIDEO_H264))).setAudioMimeType(MimeTypes.AUDIO_AAC).setVideoMimeType(MimeTypes.VIDEO_H264).build();
    }

    private static final class DroppingEncoder implements Codec {
        private static final int INTERNAL_BUFFER_SIZE = 8196;
        private static final String TAG = "DroppingEncoder";
        private final ByteBuffer buffer = ByteBuffer.allocateDirect(INTERNAL_BUFFER_SIZE).order(ByteOrder.nativeOrder());
        private final Format configurationFormat;
        private boolean inputStreamEnded;
        private final Surface placeholderSurface;

        public String getName() {
            return TAG;
        }

        public ByteBuffer getOutputBuffer() {
            return null;
        }

        public MediaCodec.BufferInfo getOutputBufferInfo() {
            return null;
        }

        public void releaseOutputBuffer(long j) {
        }

        public void releaseOutputBuffer(boolean z) {
        }

        public static final class Factory implements Codec.EncoderFactory {
            private final Context context;

            public Factory(Context context2) {
                this.context = context2;
            }

            public Codec createForAudioEncoding(Format format) {
                return new DroppingEncoder(this.context, format);
            }

            public Codec createForVideoEncoding(Format format) {
                return new DroppingEncoder(this.context, format);
            }
        }

        public DroppingEncoder(Context context, Format format) {
            this.configurationFormat = format;
            this.placeholderSurface = PlaceholderSurface.newInstance(context, false);
        }

        public Format getConfigurationFormat() {
            return this.configurationFormat;
        }

        public Surface getInputSurface() {
            return this.placeholderSurface;
        }

        @EnsuresNonNullIf(expression = {"#1.data"}, result = true)
        public boolean maybeDequeueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
            if (this.inputStreamEnded) {
                return false;
            }
            decoderInputBuffer.data = this.buffer;
            return true;
        }

        public void queueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
            Assertions.checkState(!this.inputStreamEnded, "Input buffer can not be queued after the input stream has ended.");
            if (decoderInputBuffer.isEndOfStream()) {
                this.inputStreamEnded = true;
            }
            decoderInputBuffer.clear();
            decoderInputBuffer.data = null;
        }

        public void signalEndOfInputStream() {
            this.inputStreamEnded = true;
        }

        public Format getOutputFormat() {
            return this.configurationFormat;
        }

        public boolean isEnded() {
            return this.inputStreamEnded;
        }

        public void release() {
            this.placeholderSurface.release();
        }
    }
}
