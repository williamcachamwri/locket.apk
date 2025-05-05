package com.facebook.imagepipeline.transcoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Matrix;
import android.graphics.Rect;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\"\u0010\u0015\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002JO\u0010\u0016\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u00142\b\u0010\u001b\u001a\u0004\u0018\u00010\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016¢\u0006\u0002\u0010\u001eR\u0014\u0010\u0007\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/facebook/imagepipeline/transcoder/SimpleImageTranscoder;", "Lcom/facebook/imagepipeline/transcoder/ImageTranscoder;", "resizingEnabled", "", "maxBitmapSize", "", "(ZI)V", "identifier", "", "getIdentifier", "()Ljava/lang/String;", "canResize", "encodedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "rotationOptions", "Lcom/facebook/imagepipeline/common/RotationOptions;", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "canTranscode", "imageFormat", "Lcom/facebook/imageformat/ImageFormat;", "getSampleSize", "transcode", "Lcom/facebook/imagepipeline/transcoder/ImageTranscodeResult;", "outputStream", "Ljava/io/OutputStream;", "outputFormat", "quality", "colorSpace", "Landroid/graphics/ColorSpace;", "(Lcom/facebook/imagepipeline/image/EncodedImage;Ljava/io/OutputStream;Lcom/facebook/imagepipeline/common/RotationOptions;Lcom/facebook/imagepipeline/common/ResizeOptions;Lcom/facebook/imageformat/ImageFormat;Ljava/lang/Integer;Landroid/graphics/ColorSpace;)Lcom/facebook/imagepipeline/transcoder/ImageTranscodeResult;", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SimpleImageTranscoder.kt */
public final class SimpleImageTranscoder implements ImageTranscoder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "SimpleImageTranscoder";
    private final String identifier = TAG;
    private final int maxBitmapSize;
    private final boolean resizingEnabled;

    public SimpleImageTranscoder(boolean z, int i) {
        this.resizingEnabled = z;
        this.maxBitmapSize = i;
    }

    public ImageTranscodeResult transcode(EncodedImage encodedImage, OutputStream outputStream, RotationOptions rotationOptions, ResizeOptions resizeOptions, ImageFormat imageFormat, Integer num, ColorSpace colorSpace) {
        SimpleImageTranscoder simpleImageTranscoder;
        RotationOptions rotationOptions2;
        Bitmap bitmap;
        ImageTranscodeResult imageTranscodeResult;
        EncodedImage encodedImage2 = encodedImage;
        OutputStream outputStream2 = outputStream;
        ColorSpace colorSpace2 = colorSpace;
        Intrinsics.checkNotNullParameter(encodedImage2, "encodedImage");
        Intrinsics.checkNotNullParameter(outputStream2, "outputStream");
        Integer num2 = num == null ? 85 : num;
        if (rotationOptions == null) {
            rotationOptions2 = RotationOptions.Companion.autoRotate();
            simpleImageTranscoder = this;
        } else {
            simpleImageTranscoder = this;
            rotationOptions2 = rotationOptions;
        }
        int sampleSize = simpleImageTranscoder.getSampleSize(encodedImage2, rotationOptions2, resizeOptions);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        if (colorSpace2 != null) {
            options.inPreferredColorSpace = colorSpace2;
        }
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(encodedImage.getInputStream(), (Rect) null, options);
            if (decodeStream == null) {
                FLog.e(TAG, "Couldn't decode the EncodedImage InputStream ! ");
                return new ImageTranscodeResult(2);
            }
            Matrix transformationMatrix = JpegTranscoderUtils.getTransformationMatrix(encodedImage2, rotationOptions2);
            if (transformationMatrix != null) {
                try {
                    Bitmap createBitmap = Bitmap.createBitmap(decodeStream, 0, 0, decodeStream.getWidth(), decodeStream.getHeight(), transformationMatrix, false);
                    Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(\n          …x,\n                false)");
                    bitmap = createBitmap;
                } catch (OutOfMemoryError e) {
                    e = e;
                    bitmap = decodeStream;
                    try {
                        FLog.e(TAG, "Out-Of-Memory during transcode", (Throwable) e);
                        imageTranscodeResult = new ImageTranscodeResult(2);
                        bitmap.recycle();
                        decodeStream.recycle();
                        return imageTranscodeResult;
                    } catch (Throwable th) {
                        th = th;
                        bitmap.recycle();
                        decodeStream.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bitmap = decodeStream;
                    bitmap.recycle();
                    decodeStream.recycle();
                    throw th;
                }
            } else {
                bitmap = decodeStream;
            }
            try {
                bitmap.compress(Companion.getOutputFormat(imageFormat), num2.intValue(), outputStream2);
                int i = 1;
                if (sampleSize > 1) {
                    i = 0;
                }
                imageTranscodeResult = new ImageTranscodeResult(i);
            } catch (OutOfMemoryError e2) {
                e = e2;
                FLog.e(TAG, "Out-Of-Memory during transcode", (Throwable) e);
                imageTranscodeResult = new ImageTranscodeResult(2);
                bitmap.recycle();
                decodeStream.recycle();
                return imageTranscodeResult;
            }
            bitmap.recycle();
            decodeStream.recycle();
            return imageTranscodeResult;
        } catch (OutOfMemoryError e3) {
            FLog.e(TAG, "Out-Of-Memory during transcode", (Throwable) e3);
            return new ImageTranscodeResult(2);
        }
    }

    public boolean canResize(EncodedImage encodedImage, RotationOptions rotationOptions, ResizeOptions resizeOptions) {
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (rotationOptions == null) {
            rotationOptions = RotationOptions.Companion.autoRotate();
        }
        return this.resizingEnabled && DownsampleUtil.determineSampleSize(rotationOptions, resizeOptions, encodedImage, this.maxBitmapSize) > 1;
    }

    public boolean canTranscode(ImageFormat imageFormat) {
        Intrinsics.checkNotNullParameter(imageFormat, "imageFormat");
        return imageFormat == DefaultImageFormats.HEIF || imageFormat == DefaultImageFormats.JPEG;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    private final int getSampleSize(EncodedImage encodedImage, RotationOptions rotationOptions, ResizeOptions resizeOptions) {
        if (!this.resizingEnabled) {
            return 1;
        }
        return DownsampleUtil.determineSampleSize(rotationOptions, resizeOptions, encodedImage, this.maxBitmapSize);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/facebook/imagepipeline/transcoder/SimpleImageTranscoder$Companion;", "", "()V", "TAG", "", "getOutputFormat", "Landroid/graphics/Bitmap$CompressFormat;", "format", "Lcom/facebook/imageformat/ImageFormat;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SimpleImageTranscoder.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final Bitmap.CompressFormat getOutputFormat(ImageFormat imageFormat) {
            if (imageFormat == null) {
                return Bitmap.CompressFormat.JPEG;
            }
            if (imageFormat == DefaultImageFormats.JPEG) {
                return Bitmap.CompressFormat.JPEG;
            }
            if (imageFormat == DefaultImageFormats.PNG) {
                return Bitmap.CompressFormat.PNG;
            }
            if (DefaultImageFormats.isStaticWebpFormat(imageFormat)) {
                return Bitmap.CompressFormat.WEBP;
            }
            return Bitmap.CompressFormat.JPEG;
        }
    }
}
