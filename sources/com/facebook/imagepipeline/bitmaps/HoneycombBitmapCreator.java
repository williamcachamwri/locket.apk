package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.FlexByteArrayPool;
import com.facebook.imagepipeline.memory.PoolFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/facebook/imagepipeline/bitmaps/HoneycombBitmapCreator;", "Lcom/facebook/common/webp/BitmapCreator;", "poolFactory", "Lcom/facebook/imagepipeline/memory/PoolFactory;", "(Lcom/facebook/imagepipeline/memory/PoolFactory;)V", "flexByteArrayPool", "Lcom/facebook/imagepipeline/memory/FlexByteArrayPool;", "jpegGenerator", "Lcom/facebook/imagepipeline/bitmaps/EmptyJpegGenerator;", "createNakedBitmap", "Landroid/graphics/Bitmap;", "width", "", "height", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HoneycombBitmapCreator.kt */
public final class HoneycombBitmapCreator implements BitmapCreator {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final FlexByteArrayPool flexByteArrayPool;
    private final EmptyJpegGenerator jpegGenerator;

    public HoneycombBitmapCreator(PoolFactory poolFactory) {
        Intrinsics.checkNotNullParameter(poolFactory, "poolFactory");
        this.jpegGenerator = new EmptyJpegGenerator(poolFactory.getPooledByteBufferFactory());
        FlexByteArrayPool flexByteArrayPool2 = poolFactory.getFlexByteArrayPool();
        Intrinsics.checkNotNullExpressionValue(flexByteArrayPool2, "poolFactory.flexByteArrayPool");
        this.flexByteArrayPool = flexByteArrayPool2;
    }

    public Bitmap createNakedBitmap(int i, int i2, Bitmap.Config config) {
        EncodedImage encodedImage;
        Intrinsics.checkNotNullParameter(config, "bitmapConfig");
        CloseableReference<PooledByteBuffer> generate = this.jpegGenerator.generate((short) i, (short) i2);
        Intrinsics.checkNotNullExpressionValue(generate, "jpegGenerator.generate(w…hort(), height.toShort())");
        CloseableReference<byte[]> closeableReference = null;
        try {
            encodedImage = new EncodedImage(generate);
            try {
                encodedImage.setImageFormat(DefaultImageFormats.JPEG);
                BitmapFactory.Options access$getBitmapFactoryOptions = Companion.getBitmapFactoryOptions(encodedImage.getSampleSize(), config);
                int size = generate.get().size();
                PooledByteBuffer pooledByteBuffer = generate.get();
                Intrinsics.checkNotNullExpressionValue(pooledByteBuffer, "jpgRef.get()");
                closeableReference = this.flexByteArrayPool.get(size + 2);
                byte[] bArr = closeableReference.get();
                Intrinsics.checkNotNullExpressionValue(bArr, "encodedBytesArrayRef.get()");
                byte[] bArr2 = bArr;
                pooledByteBuffer.read(0, bArr2, 0, size);
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, size, access$getBitmapFactoryOptions);
                if (decodeByteArray != null) {
                    decodeByteArray.setHasAlpha(true);
                    decodeByteArray.eraseColor(0);
                    CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
                    EncodedImage.closeSafely(encodedImage);
                    CloseableReference.closeSafely((CloseableReference<?>) generate);
                    return decodeByteArray;
                }
                throw new IllegalStateException("Required value was null.".toString());
            } catch (Throwable th) {
                th = th;
                CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely((CloseableReference<?>) generate);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            encodedImage = null;
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
            EncodedImage.closeSafely(encodedImage);
            CloseableReference.closeSafely((CloseableReference<?>) generate);
            throw th;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\t"}, d2 = {"Lcom/facebook/imagepipeline/bitmaps/HoneycombBitmapCreator$Companion;", "", "()V", "getBitmapFactoryOptions", "Landroid/graphics/BitmapFactory$Options;", "sampleSize", "", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HoneycombBitmapCreator.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final BitmapFactory.Options getBitmapFactoryOptions(int i, Bitmap.Config config) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDither = true;
            options.inPreferredConfig = config;
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inSampleSize = i;
            options.inMutable = true;
            return options;
        }
    }
}
