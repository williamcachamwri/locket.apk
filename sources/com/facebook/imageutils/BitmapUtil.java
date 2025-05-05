package com.facebook.imageutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Pair;
import androidx.core.util.Pools;
import com.facebook.common.memory.DecodeBufferHelper;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0016\u001a\u0004\u0018\u00010\tH\u0002J\u001e\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J \u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0007J \u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007J\u0012\u0010\u001f\u001a\u00020 2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0007J\u0012\u0010!\u001a\u00020\u00042\b\u0010\"\u001a\u0004\u0018\u00010#H\u0007J\"\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\b\u0010\"\u001a\u0004\u0018\u00010#H\u0007J\u0012\u0010'\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010)H\u0007J\b\u0010*\u001a\u00020\tH\u0002J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\tH\u0002J\u0010\u0010.\u001a\u00020,2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R#\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b8BX\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/facebook/imageutils/BitmapUtil;", "", "()V", "ALPHA_8_BYTES_PER_PIXEL", "", "ARGB_4444_BYTES_PER_PIXEL", "ARGB_8888_BYTES_PER_PIXEL", "DECODE_BUFFERS", "Landroidx/core/util/Pools$SynchronizedPool;", "Ljava/nio/ByteBuffer;", "getDECODE_BUFFERS", "()Landroidx/core/util/Pools$SynchronizedPool;", "DECODE_BUFFERS$delegate", "Lkotlin/Lazy;", "MAX_BITMAP_SIZE", "", "POOL_SIZE", "RGBA_1010102_BYTES_PER_PIXEL", "RGBA_F16_BYTES_PER_PIXEL", "RGB_565_BYTES_PER_PIXEL", "useDecodeBufferHelper", "", "acquireByteBuffer", "decodeDimensions", "Landroid/util/Pair;", "uri", "Landroid/net/Uri;", "inputStream", "Ljava/io/InputStream;", "bytes", "", "decodeDimensionsAndColorSpace", "Lcom/facebook/imageutils/ImageMetaData;", "getPixelSizeForBitmapConfig", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "getSizeInByteForBitmap", "width", "height", "getSizeInBytes", "bitmap", "Landroid/graphics/Bitmap;", "obtainByteBuffer", "releaseByteBuffer", "", "byteBuffer", "setUseDecodeBufferHelper", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BitmapUtil.kt */
public final class BitmapUtil {
    public static final int ALPHA_8_BYTES_PER_PIXEL = 1;
    public static final int ARGB_4444_BYTES_PER_PIXEL = 2;
    public static final int ARGB_8888_BYTES_PER_PIXEL = 4;
    private static final Lazy DECODE_BUFFERS$delegate = LazyKt.lazy(BitmapUtil$DECODE_BUFFERS$2.INSTANCE);
    public static final BitmapUtil INSTANCE = new BitmapUtil();
    public static final float MAX_BITMAP_SIZE = 2048.0f;
    private static final int POOL_SIZE = 12;
    public static final int RGBA_1010102_BYTES_PER_PIXEL = 4;
    public static final int RGBA_F16_BYTES_PER_PIXEL = 8;
    public static final int RGB_565_BYTES_PER_PIXEL = 2;
    private static boolean useDecodeBufferHelper;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BitmapUtil.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|17) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                android.graphics.Bitmap$Config[] r0 = android.graphics.Bitmap.Config.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ALPHA_8     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_4444     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.RGB_565     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.RGBA_F16     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.RGBA_1010102     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.HARDWARE     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imageutils.BitmapUtil.WhenMappings.<clinit>():void");
        }
    }

    private BitmapUtil() {
    }

    private final Pools.SynchronizedPool<ByteBuffer> getDECODE_BUFFERS() {
        return (Pools.SynchronizedPool) DECODE_BUFFERS$delegate.getValue();
    }

    @JvmStatic
    public static final int getSizeInBytes(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        try {
            return bitmap.getAllocationByteCount();
        } catch (NullPointerException unused) {
            return bitmap.getByteCount();
        }
    }

    @JvmStatic
    public static final Pair<Integer, Integer> decodeDimensions(byte[] bArr) {
        return decodeDimensions((InputStream) new ByteArrayInputStream(bArr));
    }

    @JvmStatic
    public static final Pair<Integer, Integer> decodeDimensions(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(uri.getPath(), options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }
        return new Pair<>(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
    }

    @JvmStatic
    public static final Pair<Integer, Integer> decodeDimensions(InputStream inputStream) {
        if (inputStream != null) {
            BitmapUtil bitmapUtil = INSTANCE;
            ByteBuffer obtainByteBuffer = bitmapUtil.obtainByteBuffer();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try {
                options.inTempStorage = obtainByteBuffer.array();
                Pair<Integer, Integer> pair = null;
                BitmapFactory.decodeStream(inputStream, (Rect) null, options);
                if (options.outWidth != -1) {
                    if (options.outHeight != -1) {
                        pair = new Pair<>(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
                    }
                }
                bitmapUtil.releaseByteBuffer(obtainByteBuffer);
                return pair;
            } catch (Throwable th) {
                INSTANCE.releaseByteBuffer(obtainByteBuffer);
                throw th;
            }
        } else {
            throw new IllegalStateException("Required value was null.".toString());
        }
    }

    @JvmStatic
    public static final ImageMetaData decodeDimensionsAndColorSpace(InputStream inputStream) {
        if (inputStream != null) {
            BitmapUtil bitmapUtil = INSTANCE;
            ByteBuffer obtainByteBuffer = bitmapUtil.obtainByteBuffer();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try {
                options.inTempStorage = obtainByteBuffer.array();
                BitmapFactory.decodeStream(inputStream, (Rect) null, options);
                ImageMetaData imageMetaData = new ImageMetaData(options.outWidth, options.outHeight, options.outColorSpace);
                bitmapUtil.releaseByteBuffer(obtainByteBuffer);
                return imageMetaData;
            } catch (Throwable th) {
                INSTANCE.releaseByteBuffer(obtainByteBuffer);
                throw th;
            }
        } else {
            throw new IllegalStateException("Required value was null.".toString());
        }
    }

    @JvmStatic
    public static final int getPixelSizeForBitmapConfig(Bitmap.Config config) {
        switch (config == null ? -1 : WhenMappings.$EnumSwitchMapping$0[config.ordinal()]) {
            case 1:
            case 6:
            case 7:
                return 4;
            case 2:
                return 1;
            case 3:
            case 4:
                return 2;
            case 5:
                return 8;
            default:
                throw new UnsupportedOperationException("The provided Bitmap.Config is not supported");
        }
    }

    @JvmStatic
    public static final int getSizeInByteForBitmap(int i, int i2, Bitmap.Config config) {
        boolean z = true;
        if (i > 0) {
            if (i2 > 0) {
                int pixelSizeForBitmapConfig = getPixelSizeForBitmapConfig(config);
                int i3 = i * i2 * pixelSizeForBitmapConfig;
                if (i3 <= 0) {
                    z = false;
                }
                if (z) {
                    return i3;
                }
                throw new IllegalStateException(("size must be > 0: size: " + i3 + ", width: " + i + ", height: " + i2 + ", pixelSize: " + pixelSizeForBitmapConfig).toString());
            }
            throw new IllegalArgumentException(("height must be > 0, height is: " + i2).toString());
        }
        throw new IllegalArgumentException(("width must be > 0, width is: " + i).toString());
    }

    private final ByteBuffer acquireByteBuffer() {
        if (useDecodeBufferHelper) {
            return DecodeBufferHelper.INSTANCE.acquire();
        }
        return getDECODE_BUFFERS().acquire();
    }

    private final void releaseByteBuffer(ByteBuffer byteBuffer) {
        if (!useDecodeBufferHelper) {
            getDECODE_BUFFERS().release(byteBuffer);
        }
    }

    @JvmStatic
    public static final void setUseDecodeBufferHelper(boolean z) {
        useDecodeBufferHelper = z;
    }

    private final ByteBuffer obtainByteBuffer() {
        ByteBuffer acquireByteBuffer = acquireByteBuffer();
        if (acquireByteBuffer != null) {
            return acquireByteBuffer;
        }
        ByteBuffer allocate = ByteBuffer.allocate(DecodeBufferHelper.getRecommendedDecodeBufferSize());
        Intrinsics.checkNotNullExpressionValue(allocate, "allocate(DecodeBufferHel…mendedDecodeBufferSize())");
        return allocate;
    }
}
