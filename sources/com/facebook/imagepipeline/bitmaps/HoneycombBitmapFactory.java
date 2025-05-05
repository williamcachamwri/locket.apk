package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ&\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0017J&\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/facebook/imagepipeline/bitmaps/HoneycombBitmapFactory;", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "jpegGenerator", "Lcom/facebook/imagepipeline/bitmaps/EmptyJpegGenerator;", "purgeableDecoder", "Lcom/facebook/imagepipeline/platform/PlatformDecoder;", "closeableReferenceFactory", "Lcom/facebook/imagepipeline/core/CloseableReferenceFactory;", "(Lcom/facebook/imagepipeline/bitmaps/EmptyJpegGenerator;Lcom/facebook/imagepipeline/platform/PlatformDecoder;Lcom/facebook/imagepipeline/core/CloseableReferenceFactory;)V", "immutableBitmapFallback", "", "createBitmapInternal", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "width", "", "height", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "createFallbackBitmap", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HoneycombBitmapFactory.kt */
public final class HoneycombBitmapFactory extends PlatformBitmapFactory {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "HoneycombBitmapFactory";
    private final CloseableReferenceFactory closeableReferenceFactory;
    private boolean immutableBitmapFallback;
    private final EmptyJpegGenerator jpegGenerator;
    private final PlatformDecoder purgeableDecoder;

    public HoneycombBitmapFactory(EmptyJpegGenerator emptyJpegGenerator, PlatformDecoder platformDecoder, CloseableReferenceFactory closeableReferenceFactory2) {
        Intrinsics.checkNotNullParameter(emptyJpegGenerator, "jpegGenerator");
        Intrinsics.checkNotNullParameter(platformDecoder, "purgeableDecoder");
        Intrinsics.checkNotNullParameter(closeableReferenceFactory2, "closeableReferenceFactory");
        this.jpegGenerator = emptyJpegGenerator;
        this.purgeableDecoder = platformDecoder;
        this.closeableReferenceFactory = closeableReferenceFactory2;
    }

    public CloseableReference<Bitmap> createBitmapInternal(int i, int i2, Bitmap.Config config) {
        EncodedImage encodedImage;
        Intrinsics.checkNotNullParameter(config, "bitmapConfig");
        if (this.immutableBitmapFallback) {
            return createFallbackBitmap(i, i2, config);
        }
        CloseableReference<PooledByteBuffer> generate = this.jpegGenerator.generate((short) i, (short) i2);
        Intrinsics.checkNotNullExpressionValue(generate, "jpegGenerator.generate(w…hort(), height.toShort())");
        try {
            encodedImage = new EncodedImage(generate);
            encodedImage.setImageFormat(DefaultImageFormats.JPEG);
            CloseableReference<Bitmap> decodeJPEGFromEncodedImage = this.purgeableDecoder.decodeJPEGFromEncodedImage(encodedImage, config, (Rect) null, generate.get().size());
            if (decodeJPEGFromEncodedImage == null) {
                throw new IllegalStateException("Required value was null.".toString());
            } else if (!decodeJPEGFromEncodedImage.get().isMutable()) {
                CloseableReference.closeSafely((CloseableReference<?>) decodeJPEGFromEncodedImage);
                this.immutableBitmapFallback = true;
                FLog.wtf(TAG, "Immutable bitmap returned by decoder");
                CloseableReference<Bitmap> createFallbackBitmap = createFallbackBitmap(i, i2, config);
                EncodedImage.closeSafely(encodedImage);
                generate.close();
                return createFallbackBitmap;
            } else {
                decodeJPEGFromEncodedImage.get().setHasAlpha(true);
                decodeJPEGFromEncodedImage.get().eraseColor(0);
                EncodedImage.closeSafely(encodedImage);
                generate.close();
                return decodeJPEGFromEncodedImage;
            }
        } catch (Throwable th) {
            generate.close();
            throw th;
        }
    }

    private final CloseableReference<Bitmap> createFallbackBitmap(int i, int i2, Bitmap.Config config) {
        CloseableReference<Bitmap> create = this.closeableReferenceFactory.create(Bitmap.createBitmap(i, i2, config), SimpleBitmapReleaser.getInstance());
        Intrinsics.checkNotNullExpressionValue(create, "closeableReferenceFactor…apReleaser.getInstance())");
        return create;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/facebook/imagepipeline/bitmaps/HoneycombBitmapFactory$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HoneycombBitmapFactory.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
