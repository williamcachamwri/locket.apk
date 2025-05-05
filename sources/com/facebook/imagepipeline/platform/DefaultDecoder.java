package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import androidx.core.util.Pools;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.streams.TailAppendingInputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imagepipeline.memory.DummyBitmapPool;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public abstract class DefaultDecoder implements PlatformDecoder {
    private static final byte[] EOI_TAIL = {-1, -39};
    private static final Class<?> TAG = DefaultDecoder.class;
    private boolean mAvoidPoolGet;
    private boolean mAvoidPoolRelease;
    private final BitmapPool mBitmapPool;
    final Pools.Pool<ByteBuffer> mDecodeBuffers;
    @Nullable
    private final PreverificationHelper mPreverificationHelper = new PreverificationHelper();

    public abstract int getBitmapSize(int i, int i2, BitmapFactory.Options options);

    public DefaultDecoder(BitmapPool bitmapPool, Pools.Pool<ByteBuffer> pool, PlatformDecoderOptions platformDecoderOptions) {
        this.mBitmapPool = bitmapPool;
        if (bitmapPool instanceof DummyBitmapPool) {
            this.mAvoidPoolGet = platformDecoderOptions.getAvoidPoolGet();
            this.mAvoidPoolRelease = platformDecoderOptions.getAvoidPoolRelease();
        }
        this.mDecodeBuffers = pool;
    }

    @Nullable
    public CloseableReference<Bitmap> decodeFromEncodedImage(EncodedImage encodedImage, Bitmap.Config config, @Nullable Rect rect) {
        return decodeFromEncodedImageWithColorSpace(encodedImage, config, rect, (ColorSpace) null);
    }

    @Nullable
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImage(EncodedImage encodedImage, Bitmap.Config config, @Nullable Rect rect, int i) {
        return decodeJPEGFromEncodedImageWithColorSpace(encodedImage, config, rect, i, (ColorSpace) null);
    }

    @Nullable
    public CloseableReference<Bitmap> decodeFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config config, @Nullable Rect rect, @Nullable ColorSpace colorSpace) {
        BitmapFactory.Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, config, this.mAvoidPoolGet);
        boolean z = decodeOptionsForStream.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            return decodeFromStream((InputStream) Preconditions.checkNotNull(encodedImage.getInputStream()), decodeOptionsForStream, rect, colorSpace);
        } catch (RuntimeException e) {
            if (z) {
                return decodeFromEncodedImageWithColorSpace(encodedImage, Bitmap.Config.ARGB_8888, rect, colorSpace);
            }
            throw e;
        }
    }

    @Nullable
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config config, @Nullable Rect rect, int i, @Nullable ColorSpace colorSpace) {
        boolean isCompleteAt = encodedImage.isCompleteAt(i);
        BitmapFactory.Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, config, this.mAvoidPoolGet);
        TailAppendingInputStream inputStream = encodedImage.getInputStream();
        Preconditions.checkNotNull(inputStream);
        if (encodedImage.getSize() > i) {
            inputStream = new LimitedInputStream(inputStream, i);
        }
        if (!isCompleteAt) {
            inputStream = new TailAppendingInputStream(inputStream, EOI_TAIL);
        }
        boolean z = decodeOptionsForStream.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            CloseableReference<Bitmap> decodeFromStream = decodeFromStream(inputStream, decodeOptionsForStream, rect, colorSpace);
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return decodeFromStream;
        } catch (RuntimeException e2) {
            if (z) {
                CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace = decodeJPEGFromEncodedImageWithColorSpace(encodedImage, Bitmap.Config.ARGB_8888, rect, i, colorSpace);
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return decodeJPEGFromEncodedImageWithColorSpace;
            }
            throw e2;
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public CloseableReference<Bitmap> decodeStaticImageFromStream(InputStream inputStream, BitmapFactory.Options options, @Nullable Rect rect) {
        return decodeFromStream(inputStream, options, rect, (ColorSpace) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        com.facebook.common.logging.FLog.e(TAG, "Could not decode region %s, decoding full bitmap instead.", r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a5, code lost:
        if (r0 != null) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r0.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ab, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00ac, code lost:
        r5 = r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x009a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x0112 */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008b A[SYNTHETIC, Splitter:B:38:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0093 A[SYNTHETIC, Splitter:B:42:0x0093] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00af A[Catch:{ IllegalArgumentException -> 0x00f2, RuntimeException -> 0x00e9, all -> 0x00e7, IOException -> 0x0112 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00b6 A[Catch:{ IllegalArgumentException -> 0x00f2, RuntimeException -> 0x00e9, all -> 0x00e7, IOException -> 0x0112 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00c1 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00e0  */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.common.references.CloseableReference<android.graphics.Bitmap> decodeFromStream(java.io.InputStream r7, android.graphics.BitmapFactory.Options r8, @javax.annotation.Nullable android.graphics.Rect r9, @javax.annotation.Nullable android.graphics.ColorSpace r10) {
        /*
            r6 = this;
            com.facebook.common.internal.Preconditions.checkNotNull(r7)
            int r0 = r8.outWidth
            int r1 = r8.outHeight
            if (r9 == 0) goto L_0x0017
            int r0 = r9.width()
            int r1 = r8.inSampleSize
            int r0 = r0 / r1
            int r1 = r9.height()
            int r2 = r8.inSampleSize
            int r1 = r1 / r2
        L_0x0017:
            com.facebook.imagepipeline.platform.PreverificationHelper r2 = r6.mPreverificationHelper
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0027
            android.graphics.Bitmap$Config r5 = r8.inPreferredConfig
            boolean r2 = r2.shouldUseHardwareBitmapConfig(r5)
            if (r2 == 0) goto L_0x0027
            r2 = r3
            goto L_0x0028
        L_0x0027:
            r2 = r4
        L_0x0028:
            r5 = 0
            if (r9 != 0) goto L_0x0030
            if (r2 == 0) goto L_0x0030
            r8.inMutable = r4
            goto L_0x0053
        L_0x0030:
            if (r9 == 0) goto L_0x0038
            if (r2 == 0) goto L_0x0038
            android.graphics.Bitmap$Config r2 = android.graphics.Bitmap.Config.ARGB_8888
            r8.inPreferredConfig = r2
        L_0x0038:
            boolean r2 = r6.mAvoidPoolGet
            if (r2 != 0) goto L_0x0053
            int r2 = r6.getBitmapSize(r0, r1, r8)
            com.facebook.imagepipeline.memory.BitmapPool r4 = r6.mBitmapPool
            java.lang.Object r2 = r4.get(r2)
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            if (r2 == 0) goto L_0x004b
            goto L_0x0054
        L_0x004b:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r8 = "BitmapPool.get returned null"
            r7.<init>(r8)
            throw r7
        L_0x0053:
            r2 = r5
        L_0x0054:
            r8.inBitmap = r2
            if (r10 != 0) goto L_0x005e
            android.graphics.ColorSpace$Named r10 = android.graphics.ColorSpace.Named.SRGB
            android.graphics.ColorSpace r10 = android.graphics.ColorSpace.get(r10)
        L_0x005e:
            r8.inPreferredColorSpace = r10
            androidx.core.util.Pools$Pool<java.nio.ByteBuffer> r10 = r6.mDecodeBuffers
            java.lang.Object r10 = r10.acquire()
            java.nio.ByteBuffer r10 = (java.nio.ByteBuffer) r10
            if (r10 != 0) goto L_0x0072
            int r10 = com.facebook.common.memory.DecodeBufferHelper.getRecommendedDecodeBufferSize()
            java.nio.ByteBuffer r10 = java.nio.ByteBuffer.allocate(r10)
        L_0x0072:
            byte[] r4 = r10.array()     // Catch:{ IllegalArgumentException -> 0x00f2, RuntimeException -> 0x00e9 }
            r8.inTempStorage = r4     // Catch:{ IllegalArgumentException -> 0x00f2, RuntimeException -> 0x00e9 }
            if (r9 == 0) goto L_0x00b3
            if (r2 == 0) goto L_0x00b3
            android.graphics.Bitmap$Config r4 = r8.inPreferredConfig     // Catch:{ IllegalArgumentException -> 0x00f2, RuntimeException -> 0x00e9 }
            if (r4 == 0) goto L_0x00b3
            android.graphics.Bitmap$Config r4 = r8.inPreferredConfig     // Catch:{ IOException -> 0x0099, all -> 0x0097 }
            r2.reconfigure(r0, r1, r4)     // Catch:{ IOException -> 0x0099, all -> 0x0097 }
            android.graphics.BitmapRegionDecoder r0 = android.graphics.BitmapRegionDecoder.newInstance(r7, r3)     // Catch:{ IOException -> 0x0099, all -> 0x0097 }
            if (r0 == 0) goto L_0x0090
            android.graphics.Bitmap r9 = r0.decodeRegion(r9, r8)     // Catch:{ IOException -> 0x009a }
            goto L_0x0091
        L_0x0090:
            r9 = r5
        L_0x0091:
            if (r0 == 0) goto L_0x00b4
            r0.recycle()     // Catch:{ IllegalArgumentException -> 0x00f2, RuntimeException -> 0x00e9 }
            goto L_0x00b4
        L_0x0097:
            r8 = move-exception
            goto L_0x00ad
        L_0x0099:
            r0 = r5
        L_0x009a:
            java.lang.Class<?> r1 = TAG     // Catch:{ all -> 0x00ab }
            java.lang.String r3 = "Could not decode region %s, decoding full bitmap instead."
            java.lang.Object[] r9 = new java.lang.Object[]{r9}     // Catch:{ all -> 0x00ab }
            com.facebook.common.logging.FLog.e((java.lang.Class<?>) r1, (java.lang.String) r3, (java.lang.Object[]) r9)     // Catch:{ all -> 0x00ab }
            if (r0 == 0) goto L_0x00b3
            r0.recycle()     // Catch:{ IllegalArgumentException -> 0x00f2, RuntimeException -> 0x00e9 }
            goto L_0x00b3
        L_0x00ab:
            r8 = move-exception
            r5 = r0
        L_0x00ad:
            if (r5 == 0) goto L_0x00b2
            r5.recycle()     // Catch:{ IllegalArgumentException -> 0x00f2, RuntimeException -> 0x00e9 }
        L_0x00b2:
            throw r8     // Catch:{ IllegalArgumentException -> 0x00f2, RuntimeException -> 0x00e9 }
        L_0x00b3:
            r9 = r5
        L_0x00b4:
            if (r9 != 0) goto L_0x00ba
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeStream(r7, r5, r8)     // Catch:{ IllegalArgumentException -> 0x00f2, RuntimeException -> 0x00e9 }
        L_0x00ba:
            androidx.core.util.Pools$Pool<java.nio.ByteBuffer> r7 = r6.mDecodeBuffers
            r7.release(r10)
            if (r2 == 0) goto L_0x00d3
            if (r2 == r9) goto L_0x00d3
            com.facebook.imagepipeline.memory.BitmapPool r7 = r6.mBitmapPool
            r7.release(r2)
            if (r9 == 0) goto L_0x00cd
            r9.recycle()
        L_0x00cd:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r7.<init>()
            throw r7
        L_0x00d3:
            boolean r7 = r6.mAvoidPoolRelease
            if (r7 == 0) goto L_0x00e0
            com.facebook.imagepipeline.platform.DefaultDecoder$NoOpResourceReleaser r7 = com.facebook.imagepipeline.platform.DefaultDecoder.NoOpResourceReleaser.INSTANCE
            com.facebook.common.references.CloseableReference r7 = com.facebook.common.references.CloseableReference.of(r9, r7)
            return r7
        L_0x00e0:
            com.facebook.imagepipeline.memory.BitmapPool r7 = r6.mBitmapPool
            com.facebook.common.references.CloseableReference r7 = com.facebook.common.references.CloseableReference.of(r9, r7)
            return r7
        L_0x00e7:
            r7 = move-exception
            goto L_0x0113
        L_0x00e9:
            r7 = move-exception
            if (r2 == 0) goto L_0x00f1
            com.facebook.imagepipeline.memory.BitmapPool r8 = r6.mBitmapPool     // Catch:{ all -> 0x00e7 }
            r8.release(r2)     // Catch:{ all -> 0x00e7 }
        L_0x00f1:
            throw r7     // Catch:{ all -> 0x00e7 }
        L_0x00f2:
            r8 = move-exception
            if (r2 == 0) goto L_0x00fa
            com.facebook.imagepipeline.memory.BitmapPool r9 = r6.mBitmapPool     // Catch:{ all -> 0x00e7 }
            r9.release(r2)     // Catch:{ all -> 0x00e7 }
        L_0x00fa:
            r7.reset()     // Catch:{ IOException -> 0x0112 }
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeStream(r7)     // Catch:{ IOException -> 0x0112 }
            if (r7 == 0) goto L_0x0111
            com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser r9 = com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser.getInstance()     // Catch:{ IOException -> 0x0112 }
            com.facebook.common.references.CloseableReference r7 = com.facebook.common.references.CloseableReference.of(r7, r9)     // Catch:{ IOException -> 0x0112 }
            androidx.core.util.Pools$Pool<java.nio.ByteBuffer> r8 = r6.mDecodeBuffers
            r8.release(r10)
            return r7
        L_0x0111:
            throw r8     // Catch:{ IOException -> 0x0112 }
        L_0x0112:
            throw r8     // Catch:{ all -> 0x00e7 }
        L_0x0113:
            androidx.core.util.Pools$Pool<java.nio.ByteBuffer> r8 = r6.mDecodeBuffers
            r8.release(r10)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.platform.DefaultDecoder.decodeFromStream(java.io.InputStream, android.graphics.BitmapFactory$Options, android.graphics.Rect, android.graphics.ColorSpace):com.facebook.common.references.CloseableReference");
    }

    private static BitmapFactory.Options getDecodeOptionsForStream(EncodedImage encodedImage, Bitmap.Config config, boolean z) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = encodedImage.getSampleSize();
        options.inJustDecodeBounds = true;
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inMutable = true;
        if (!z) {
            BitmapFactory.decodeStream(encodedImage.getInputStream(), (Rect) null, options);
            if (options.outWidth == -1 || options.outHeight == -1) {
                throw new IllegalArgumentException();
            }
        }
        options.inJustDecodeBounds = false;
        return options;
    }

    private static final class NoOpResourceReleaser implements ResourceReleaser<Bitmap> {
        /* access modifiers changed from: private */
        public static final NoOpResourceReleaser INSTANCE = new NoOpResourceReleaser();

        public void release(Bitmap bitmap) {
        }

        private NoOpResourceReleaser() {
        }
    }
}
