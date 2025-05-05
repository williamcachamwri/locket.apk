package androidx.media3.exoplayer.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.BitmapUtil;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.SimpleDecoder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.image.ImageDecoder;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class BitmapFactoryImageDecoder extends SimpleDecoder<DecoderInputBuffer, ImageOutputBuffer, ImageDecoderException> implements ImageDecoder {
    private final BitmapDecoder bitmapDecoder;

    public interface BitmapDecoder {
        Bitmap decode(byte[] bArr, int i) throws ImageDecoderException;
    }

    public String getName() {
        return "BitmapFactoryImageDecoder";
    }

    public /* bridge */ /* synthetic */ ImageOutputBuffer dequeueOutputBuffer() throws ImageDecoderException {
        return (ImageOutputBuffer) super.dequeueOutputBuffer();
    }

    public static final class Factory implements ImageDecoder.Factory {
        private final BitmapDecoder bitmapDecoder;

        public Factory() {
            this.bitmapDecoder = new BitmapFactoryImageDecoder$Factory$$ExternalSyntheticLambda0();
        }

        public Factory(BitmapDecoder bitmapDecoder2) {
            this.bitmapDecoder = bitmapDecoder2;
        }

        public int supportsFormat(Format format) {
            if (format.sampleMimeType == null || !MimeTypes.isImage(format.sampleMimeType)) {
                return RendererCapabilities.create(0);
            }
            if (Util.isBitmapFactorySupportedMimeType(format.sampleMimeType)) {
                return RendererCapabilities.create(4);
            }
            return RendererCapabilities.create(1);
        }

        public BitmapFactoryImageDecoder createImageDecoder() {
            return new BitmapFactoryImageDecoder(this.bitmapDecoder);
        }
    }

    private BitmapFactoryImageDecoder(BitmapDecoder bitmapDecoder2) {
        super(new DecoderInputBuffer[1], new ImageOutputBuffer[1]);
        this.bitmapDecoder = bitmapDecoder2;
    }

    /* access modifiers changed from: protected */
    public DecoderInputBuffer createInputBuffer() {
        return new DecoderInputBuffer(1);
    }

    /* access modifiers changed from: protected */
    public ImageOutputBuffer createOutputBuffer() {
        return new ImageOutputBuffer() {
            public void release() {
                BitmapFactoryImageDecoder.this.releaseOutputBuffer(this);
            }
        };
    }

    /* access modifiers changed from: protected */
    public ImageDecoderException createUnexpectedDecodeException(Throwable th) {
        return new ImageDecoderException("Unexpected decode error", th);
    }

    /* access modifiers changed from: protected */
    public ImageDecoderException decode(DecoderInputBuffer decoderInputBuffer, ImageOutputBuffer imageOutputBuffer, boolean z) {
        try {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.data);
            Assertions.checkState(byteBuffer.hasArray());
            Assertions.checkArgument(byteBuffer.arrayOffset() == 0);
            imageOutputBuffer.bitmap = this.bitmapDecoder.decode(byteBuffer.array(), byteBuffer.remaining());
            imageOutputBuffer.timeUs = decoderInputBuffer.timeUs;
            return null;
        } catch (ImageDecoderException e) {
            return e;
        }
    }

    /* access modifiers changed from: private */
    public static Bitmap decode(byte[] bArr, int i) throws ImageDecoderException {
        try {
            return BitmapUtil.decode(bArr, i, (BitmapFactory.Options) null, -1);
        } catch (ParserException e) {
            throw new ImageDecoderException("Could not decode image data with BitmapFactory. (data.length = " + bArr.length + ", input length = " + i + ")", e);
        } catch (IOException e2) {
            throw new ImageDecoderException((Throwable) e2);
        }
    }
}
