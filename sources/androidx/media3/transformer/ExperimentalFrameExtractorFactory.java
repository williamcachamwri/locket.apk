package androidx.media3.transformer;

import android.content.Context;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaCodec;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.transformer.Codec;
import androidx.media3.transformer.NoWriteMuxer;
import androidx.media3.transformer.Transformer;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

final class ExperimentalFrameExtractorFactory {

    public interface Listener {
        void onImageAvailable(Image image);
    }

    private ExperimentalFrameExtractorFactory() {
    }

    public static Transformer buildFrameExtractorTransformer(Context context, Listener listener) {
        return new Transformer.Builder(context).experimentalSetTrimOptimizationEnabled(false).setEncoderFactory(new ImageReaderEncoder.Factory(listener)).setMaxDelayBetweenMuxerSamplesMs(C.TIME_UNSET).setMuxerFactory(new NoWriteMuxer.Factory(ImmutableList.of(MimeTypes.AUDIO_AAC), ImmutableList.of(MimeTypes.VIDEO_H264))).setAudioMimeType(MimeTypes.AUDIO_AAC).setVideoMimeType(MimeTypes.VIDEO_H264).experimentalSetMaxFramesInEncoder(1).build();
    }

    private static final class ImageReaderEncoder implements Codec {
        private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocateDirect(0);
        private static final String TAG = "ImageReaderEncoder";
        private final Format configurationFormat;
        private boolean hasOutputBuffer;
        private final ImageReader imageReader;
        private boolean inputStreamEnded;
        private final MediaCodec.BufferInfo outputBufferInfo;
        private final Queue<Long> processedImageTimestampsNs = new ConcurrentLinkedQueue();

        public String getName() {
            return TAG;
        }

        public void release() {
        }

        public static final class Factory implements Codec.EncoderFactory {
            private final Listener listener;

            public Factory(Listener listener2) {
                this.listener = listener2;
            }

            public Codec createForAudioEncoding(Format format) {
                throw new UnsupportedOperationException();
            }

            public Codec createForVideoEncoding(Format format) {
                return new ImageReaderEncoder(format, this.listener);
            }
        }

        public ImageReaderEncoder(Format format, Listener listener) {
            this.configurationFormat = format;
            ImageReader newInstance = ImageReader.newInstance(format.width, format.height, 1, 1);
            this.imageReader = newInstance;
            newInstance.setOnImageAvailableListener(new ExperimentalFrameExtractorFactory$ImageReaderEncoder$$ExternalSyntheticLambda0(this, listener), Util.createHandlerForCurrentOrMainLooper());
            this.outputBufferInfo = new MediaCodec.BufferInfo();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$androidx-media3-transformer-ExperimentalFrameExtractorFactory$ImageReaderEncoder  reason: not valid java name */
        public /* synthetic */ void m1122lambda$new$0$androidxmedia3transformerExperimentalFrameExtractorFactory$ImageReaderEncoder(Listener listener, ImageReader imageReader2) {
            Image acquireNextImage = imageReader2.acquireNextImage();
            try {
                this.processedImageTimestampsNs.add(Long.valueOf(acquireNextImage.getTimestamp()));
                listener.onImageAvailable(acquireNextImage);
                if (acquireNextImage != null) {
                    acquireNextImage.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        public Format getConfigurationFormat() {
            return this.configurationFormat;
        }

        public Surface getInputSurface() {
            return this.imageReader.getSurface();
        }

        @EnsuresNonNullIf(expression = {"#1.data"}, result = true)
        public boolean maybeDequeueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
            throw new UnsupportedOperationException();
        }

        public void queueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
            throw new UnsupportedOperationException();
        }

        public void signalEndOfInputStream() {
            this.inputStreamEnded = true;
        }

        public Format getOutputFormat() {
            return this.configurationFormat;
        }

        public ByteBuffer getOutputBuffer() {
            if (maybeGenerateOutputBuffer()) {
                return EMPTY_BUFFER;
            }
            return null;
        }

        public MediaCodec.BufferInfo getOutputBufferInfo() {
            if (maybeGenerateOutputBuffer()) {
                return this.outputBufferInfo;
            }
            return null;
        }

        public boolean isEnded() {
            return this.inputStreamEnded && this.processedImageTimestampsNs.isEmpty();
        }

        public void releaseOutputBuffer(boolean z) {
            releaseOutputBuffer();
        }

        public void releaseOutputBuffer(long j) {
            releaseOutputBuffer();
        }

        private void releaseOutputBuffer() {
            this.hasOutputBuffer = false;
        }

        private boolean maybeGenerateOutputBuffer() {
            if (this.hasOutputBuffer) {
                return true;
            }
            Long poll = this.processedImageTimestampsNs.poll();
            if (poll == null) {
                return false;
            }
            this.hasOutputBuffer = true;
            this.outputBufferInfo.presentationTimeUs = poll.longValue() / 1000;
            return true;
        }
    }
}
