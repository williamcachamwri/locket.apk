package com.facebook.webpsupport;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.TypedValue;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.nativecode.StaticWebpNativeLoader;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

public class WebpBitmapFactoryImpl implements WebpBitmapFactory {
    private static final int HEADER_SIZE = 20;
    private static final int IN_TEMP_BUFFER_SIZE = 8192;
    private static BitmapCreator mBitmapCreator;
    private static WebpBitmapFactory.WebpErrorLogger mWebpErrorLogger;

    @Nullable
    private static native Bitmap nativeDecodeByteArray(byte[] bArr, int i, int i2, @Nullable BitmapFactory.Options options, float f, byte[] bArr2);

    @Nullable
    private static native Bitmap nativeDecodeStream(InputStream inputStream, @Nullable BitmapFactory.Options options, float f, byte[] bArr);

    private static native long nativeSeek(FileDescriptor fileDescriptor, long j, boolean z);

    public void setBitmapCreator(BitmapCreator bitmapCreator) {
        mBitmapCreator = bitmapCreator;
    }

    private static InputStream wrapToMarkSupportedStream(InputStream inputStream) {
        return !inputStream.markSupported() ? new BufferedInputStream(inputStream, 20) : inputStream;
    }

    @Nullable
    private static byte[] getWebpHeader(InputStream inputStream, @Nullable BitmapFactory.Options options) {
        inputStream.mark(20);
        byte[] bArr = (options == null || options.inTempStorage == null || options.inTempStorage.length < 20) ? new byte[20] : options.inTempStorage;
        try {
            inputStream.read(bArr, 0, 20);
            inputStream.reset();
            return bArr;
        } catch (IOException unused) {
            return null;
        }
    }

    private static void setDensityFromOptions(@Nullable Bitmap bitmap, @Nullable BitmapFactory.Options options) {
        if (bitmap != null && options != null) {
            int i = options.inDensity;
            if (i != 0) {
                bitmap.setDensity(i);
                int i2 = options.inTargetDensity;
                if (i2 != 0 && i != i2 && i != options.inScreenDensity && options.inScaled) {
                    bitmap.setDensity(i2);
                }
            } else if (options.inBitmap != null) {
                bitmap.setDensity(160);
            }
        }
    }

    public void setWebpErrorLogger(WebpBitmapFactory.WebpErrorLogger webpErrorLogger) {
        mWebpErrorLogger = webpErrorLogger;
    }

    @Nullable
    public Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor, @Nullable Rect rect, @Nullable BitmapFactory.Options options) {
        return hookDecodeFileDescriptor(fileDescriptor, rect, options);
    }

    @Nullable
    public Bitmap decodeStream(InputStream inputStream, @Nullable Rect rect, @Nullable BitmapFactory.Options options) {
        return hookDecodeStream(inputStream, rect, options);
    }

    @Nullable
    public Bitmap decodeFile(String str, @Nullable BitmapFactory.Options options) {
        return hookDecodeFile(str, options);
    }

    @Nullable
    public Bitmap decodeByteArray(byte[] bArr, int i, int i2, @Nullable BitmapFactory.Options options) {
        return hookDecodeByteArray(bArr, i, i2, options);
    }

    @Nullable
    public static Bitmap hookDecodeByteArray(byte[] bArr, int i, int i2, @Nullable BitmapFactory.Options options) {
        Bitmap bitmap;
        StaticWebpNativeLoader.ensure();
        if (!WebpSupportStatus.sIsWebpSupportRequired || !WebpSupportStatus.isWebpHeader(bArr, i, i2)) {
            bitmap = originalDecodeByteArray(bArr, i, i2, options);
            if (bitmap == null) {
                sendWebpErrorLog("webp_direct_decode_array_failed_on_no_webp");
            }
        } else {
            bitmap = nativeDecodeByteArray(bArr, i, i2, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
            if (bitmap == null) {
                sendWebpErrorLog("webp_direct_decode_array");
            }
            setWebpBitmapOptions(bitmap, options);
        }
        return bitmap;
    }

    @Nullable
    private static Bitmap originalDecodeByteArray(byte[] bArr, int i, int i2, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeByteArray(bArr, i, i2, options);
    }

    @Nullable
    public static Bitmap hookDecodeByteArray(byte[] bArr, int i, int i2) {
        return hookDecodeByteArray(bArr, i, i2, (BitmapFactory.Options) null);
    }

    @Nullable
    private static Bitmap originalDecodeByteArray(byte[] bArr, int i, int i2) {
        return BitmapFactory.decodeByteArray(bArr, i, i2);
    }

    @Nullable
    public static Bitmap hookDecodeStream(InputStream inputStream, @Nullable Rect rect, @Nullable BitmapFactory.Options options) {
        Bitmap bitmap;
        StaticWebpNativeLoader.ensure();
        InputStream wrapToMarkSupportedStream = wrapToMarkSupportedStream(inputStream);
        byte[] webpHeader = getWebpHeader(wrapToMarkSupportedStream, options);
        if (!WebpSupportStatus.sIsWebpSupportRequired || webpHeader == null || !WebpSupportStatus.isWebpHeader(webpHeader, 0, 20)) {
            bitmap = originalDecodeStream(wrapToMarkSupportedStream, rect, options);
            if (bitmap == null) {
                sendWebpErrorLog("webp_direct_decode_stream_failed_on_no_webp");
            }
        } else {
            bitmap = nativeDecodeStream(wrapToMarkSupportedStream, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
            if (bitmap == null) {
                sendWebpErrorLog("webp_direct_decode_stream");
            }
            setWebpBitmapOptions(bitmap, options);
            setPaddingDefaultValues(rect);
        }
        return bitmap;
    }

    @Nullable
    private static Bitmap originalDecodeStream(InputStream inputStream, @Nullable Rect rect, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeStream(inputStream, rect, options);
    }

    @Nullable
    public static Bitmap hookDecodeStream(InputStream inputStream) {
        return hookDecodeStream(inputStream, (Rect) null, (BitmapFactory.Options) null);
    }

    @Nullable
    private static Bitmap originalDecodeStream(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    @Nullable
    public static Bitmap hookDecodeFile(String str, @Nullable BitmapFactory.Options options) {
        FileInputStream create;
        Bitmap bitmap = null;
        try {
            create = SentryFileInputStream.Factory.create(new FileInputStream(str), str);
            bitmap = hookDecodeStream(create, (Rect) null, options);
            create.close();
        } catch (Exception unused) {
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return bitmap;
        throw th;
    }

    @Nullable
    public static Bitmap hookDecodeFile(String str) {
        return hookDecodeFile(str, (BitmapFactory.Options) null);
    }

    @Nullable
    public static Bitmap hookDecodeResourceStream(@Nullable Resources resources, @Nullable TypedValue typedValue, InputStream inputStream, @Nullable Rect rect, @Nullable BitmapFactory.Options options) {
        if (options == null) {
            options = new BitmapFactory.Options();
        }
        if (options.inDensity == 0 && typedValue != null) {
            int i = typedValue.density;
            if (i == 0) {
                options.inDensity = 160;
            } else if (i != 65535) {
                options.inDensity = i;
            }
        }
        if (options.inTargetDensity == 0 && resources != null) {
            options.inTargetDensity = resources.getDisplayMetrics().densityDpi;
        }
        return hookDecodeStream(inputStream, rect, options);
    }

    @Nullable
    private static Bitmap originalDecodeResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, Rect rect, BitmapFactory.Options options) {
        return BitmapFactory.decodeResourceStream(resources, typedValue, inputStream, rect, options);
    }

    @Nullable
    public static Bitmap hookDecodeResource(Resources resources, int i, @Nullable BitmapFactory.Options options) {
        InputStream openRawResource;
        TypedValue typedValue = new TypedValue();
        Bitmap bitmap = null;
        try {
            openRawResource = resources.openRawResource(i, typedValue);
            bitmap = hookDecodeResourceStream(resources, typedValue, openRawResource, (Rect) null, options);
            if (openRawResource != null) {
                openRawResource.close();
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        if (bitmap != null || options == null || options.inBitmap == null) {
            return bitmap;
        }
        throw new IllegalArgumentException("Problem decoding into existing bitmap");
        throw th;
    }

    @Nullable
    private static Bitmap originalDecodeResource(Resources resources, int i, BitmapFactory.Options options) {
        return BitmapFactory.decodeResource(resources, i, options);
    }

    @Nullable
    public static Bitmap hookDecodeResource(Resources resources, int i) {
        return hookDecodeResource(resources, i, (BitmapFactory.Options) null);
    }

    @Nullable
    private static Bitmap originalDecodeResource(Resources resources, int i) {
        return BitmapFactory.decodeResource(resources, i);
    }

    private static boolean setOutDimensions(@Nullable BitmapFactory.Options options, int i, int i2) {
        if (options == null || !options.inJustDecodeBounds) {
            return false;
        }
        options.outWidth = i;
        options.outHeight = i2;
        return true;
    }

    private static void setPaddingDefaultValues(@Nullable Rect rect) {
        if (rect != null) {
            rect.top = -1;
            rect.left = -1;
            rect.bottom = -1;
            rect.right = -1;
        }
    }

    private static void setBitmapSize(@Nullable BitmapFactory.Options options, int i, int i2) {
        if (options != null) {
            options.outWidth = i;
            options.outHeight = i2;
        }
    }

    @Nullable
    private static Bitmap originalDecodeFile(String str, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeFile(str, options);
    }

    @Nullable
    private static Bitmap originalDecodeFile(String str) {
        return BitmapFactory.decodeFile(str);
    }

    @Nullable
    public static Bitmap hookDecodeFileDescriptor(FileDescriptor fileDescriptor, @Nullable Rect rect, @Nullable BitmapFactory.Options options) {
        Bitmap bitmap;
        StaticWebpNativeLoader.ensure();
        long nativeSeek = nativeSeek(fileDescriptor, 0, false);
        if (nativeSeek != -1) {
            InputStream wrapToMarkSupportedStream = wrapToMarkSupportedStream(SentryFileInputStream.Factory.create(new FileInputStream(fileDescriptor), fileDescriptor));
            try {
                byte[] webpHeader = getWebpHeader(wrapToMarkSupportedStream, options);
                if (!WebpSupportStatus.sIsWebpSupportRequired || webpHeader == null || !WebpSupportStatus.isWebpHeader(webpHeader, 0, 20)) {
                    nativeSeek(fileDescriptor, nativeSeek, true);
                    bitmap = originalDecodeFileDescriptor(fileDescriptor, rect, options);
                    if (bitmap == null) {
                        sendWebpErrorLog("webp_direct_decode_fd_failed_on_no_webp");
                    }
                } else {
                    bitmap = nativeDecodeStream(wrapToMarkSupportedStream, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
                    if (bitmap == null) {
                        sendWebpErrorLog("webp_direct_decode_fd");
                    }
                    setPaddingDefaultValues(rect);
                    setWebpBitmapOptions(bitmap, options);
                }
                try {
                    wrapToMarkSupportedStream.close();
                    return bitmap;
                } catch (Throwable unused) {
                    return bitmap;
                }
            } catch (Throwable unused2) {
            }
        } else {
            Bitmap hookDecodeStream = hookDecodeStream(SentryFileInputStream.Factory.create(new FileInputStream(fileDescriptor), fileDescriptor), rect, options);
            setPaddingDefaultValues(rect);
            return hookDecodeStream;
        }
        throw th;
    }

    @Nullable
    private static Bitmap originalDecodeFileDescriptor(FileDescriptor fileDescriptor, @Nullable Rect rect, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
    }

    @Nullable
    public static Bitmap hookDecodeFileDescriptor(FileDescriptor fileDescriptor) {
        return hookDecodeFileDescriptor(fileDescriptor, (Rect) null, (BitmapFactory.Options) null);
    }

    @Nullable
    private static Bitmap originalDecodeFileDescriptor(FileDescriptor fileDescriptor) {
        return BitmapFactory.decodeFileDescriptor(fileDescriptor);
    }

    private static void setWebpBitmapOptions(@Nullable Bitmap bitmap, @Nullable BitmapFactory.Options options) {
        setDensityFromOptions(bitmap, options);
        if (options != null) {
            options.outMimeType = "image/webp";
        }
    }

    private static boolean shouldPremultiply(@Nullable BitmapFactory.Options options) {
        if (options != null) {
            return options.inPremultiplied;
        }
        return true;
    }

    @Nullable
    private static Bitmap createBitmap(int i, int i2, @Nullable BitmapFactory.Options options) {
        if (options == null || options.inBitmap == null || !options.inBitmap.isMutable()) {
            return mBitmapCreator.createNakedBitmap(i, i2, Bitmap.Config.ARGB_8888);
        }
        return options.inBitmap;
    }

    private static byte[] getInTempStorageFromOptions(@Nullable BitmapFactory.Options options) {
        return (options == null || options.inTempStorage == null) ? new byte[8192] : options.inTempStorage;
    }

    private static float getScaleFromOptions(@Nullable BitmapFactory.Options options) {
        float f = 1.0f;
        if (options == null) {
            return 1.0f;
        }
        int i = options.inSampleSize;
        if (i > 1) {
            f = 1.0f / ((float) i);
        }
        if (!options.inScaled) {
            return f;
        }
        int i2 = options.inDensity;
        int i3 = options.inTargetDensity;
        return (i2 == 0 || i3 == 0 || i2 == options.inScreenDensity) ? f : ((float) i3) / ((float) i2);
    }

    private static void sendWebpErrorLog(String str) {
        WebpBitmapFactory.WebpErrorLogger webpErrorLogger = mWebpErrorLogger;
        if (webpErrorLogger != null) {
            webpErrorLogger.onWebpErrorLog(str, "decoding_failure");
        }
    }
}
