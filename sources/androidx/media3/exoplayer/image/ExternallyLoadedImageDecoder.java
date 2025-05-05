package androidx.media3.exoplayer.image;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.image.ImageDecoder;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public final class ExternallyLoadedImageDecoder implements ImageDecoder {
    private final BitmapResolver bitmapResolver;
    private final DecoderInputBuffer inputBuffer;
    private final ImageOutputBuffer outputBuffer;
    private ListenableFuture<Bitmap> pendingDecode;
    private long pendingDecodeTimeUs;
    private boolean pendingEndOfStream;

    public interface BitmapResolver {
        ListenableFuture<Bitmap> resolve(ExternalImageRequest externalImageRequest);
    }

    public String getName() {
        return "externallyLoadedImageDecoder";
    }

    public void setOutputStartTimeUs(long j) {
    }

    public static final class ExternalImageRequest {
        public final Uri uri;

        public ExternalImageRequest(Uri uri2) {
            this.uri = uri2;
        }
    }

    public static final class Factory implements ImageDecoder.Factory {
        private final BitmapResolver bitmapResolver;

        public Factory(BitmapResolver bitmapResolver2) {
            this.bitmapResolver = bitmapResolver2;
        }

        public int supportsFormat(Format format) {
            int i;
            if (Objects.equals(format.sampleMimeType, MimeTypes.APPLICATION_EXTERNALLY_LOADED_IMAGE)) {
                i = 4;
            } else {
                i = MimeTypes.isImage(format.sampleMimeType) ? 1 : 0;
            }
            return RendererCapabilities.create(i);
        }

        public ExternallyLoadedImageDecoder createImageDecoder() {
            return new ExternallyLoadedImageDecoder(this.bitmapResolver);
        }
    }

    private ExternallyLoadedImageDecoder(BitmapResolver bitmapResolver2) {
        this.bitmapResolver = bitmapResolver2;
        this.inputBuffer = new DecoderInputBuffer(1);
        this.outputBuffer = new ImageOutputBuffer() {
            public void release() {
                clear();
            }
        };
    }

    public DecoderInputBuffer dequeueInputBuffer() {
        if (this.pendingDecode == null) {
            return this.inputBuffer;
        }
        return null;
    }

    public void queueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (decoderInputBuffer.isEndOfStream()) {
            this.pendingEndOfStream = true;
            decoderInputBuffer.clear();
            return;
        }
        ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.data);
        Assertions.checkState(byteBuffer.hasArray());
        this.pendingDecode = this.bitmapResolver.resolve(new ExternalImageRequest(Uri.parse(new String(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.remaining(), StandardCharsets.UTF_8))));
        this.pendingDecodeTimeUs = decoderInputBuffer.timeUs;
        decoderInputBuffer.clear();
    }

    public ImageOutputBuffer dequeueOutputBuffer() throws ImageDecoderException {
        if (this.pendingEndOfStream) {
            this.outputBuffer.addFlag(4);
            this.pendingEndOfStream = false;
            return this.outputBuffer;
        }
        ListenableFuture<Bitmap> listenableFuture = this.pendingDecode;
        if (listenableFuture == null || !listenableFuture.isDone()) {
            return null;
        }
        try {
            this.outputBuffer.bitmap = (Bitmap) Futures.getDone(this.pendingDecode);
            this.outputBuffer.timeUs = this.pendingDecodeTimeUs;
            ImageOutputBuffer imageOutputBuffer = this.outputBuffer;
            this.pendingDecode = null;
            return imageOutputBuffer;
        } catch (ExecutionException e) {
            throw new ImageDecoderException(e.getCause());
        } catch (CancellationException e2) {
            throw new ImageDecoderException((Throwable) e2);
        } catch (Throwable th) {
            this.pendingDecode = null;
            throw th;
        }
    }

    public void flush() {
        resetState();
    }

    public void release() {
        resetState();
    }

    private void resetState() {
        ListenableFuture<Bitmap> listenableFuture = this.pendingDecode;
        if (listenableFuture != null) {
            listenableFuture.cancel(false);
            this.pendingDecode = null;
        }
        this.pendingEndOfStream = false;
        this.outputBuffer.release();
    }
}
