package com.facebook.imageformat;

import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.imageformat.ImageFormat;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.Nullable;

public class ImageFormatChecker {
    private static ImageFormatChecker sInstance;
    @Nullable
    private List<ImageFormat.FormatChecker> mCustomImageFormatCheckers;
    private final DefaultImageFormatChecker mDefaultFormatChecker = new DefaultImageFormatChecker();
    private int mMaxHeaderLength;

    private ImageFormatChecker() {
        updateMaxHeaderLength();
    }

    public void setUseNewOrder(boolean z) {
        this.mDefaultFormatChecker.setUseNewOrder(z);
    }

    public void setCustomImageFormatCheckers(@Nullable List<ImageFormat.FormatChecker> list) {
        this.mCustomImageFormatCheckers = list;
        updateMaxHeaderLength();
    }

    public ImageFormat determineImageFormat(InputStream inputStream) throws IOException {
        Preconditions.checkNotNull(inputStream);
        int i = this.mMaxHeaderLength;
        byte[] bArr = new byte[i];
        int readHeaderFromStream = readHeaderFromStream(i, inputStream, bArr);
        ImageFormat determineFormat = this.mDefaultFormatChecker.determineFormat(bArr, readHeaderFromStream);
        if (determineFormat != null && determineFormat != ImageFormat.UNKNOWN) {
            return determineFormat;
        }
        List<ImageFormat.FormatChecker> list = this.mCustomImageFormatCheckers;
        if (list != null) {
            for (ImageFormat.FormatChecker determineFormat2 : list) {
                ImageFormat determineFormat3 = determineFormat2.determineFormat(bArr, readHeaderFromStream);
                if (determineFormat3 != null && determineFormat3 != ImageFormat.UNKNOWN) {
                    return determineFormat3;
                }
            }
        }
        return ImageFormat.UNKNOWN;
    }

    private void updateMaxHeaderLength() {
        this.mMaxHeaderLength = this.mDefaultFormatChecker.getHeaderSize();
        List<ImageFormat.FormatChecker> list = this.mCustomImageFormatCheckers;
        if (list != null) {
            for (ImageFormat.FormatChecker headerSize : list) {
                this.mMaxHeaderLength = Math.max(this.mMaxHeaderLength, headerSize.getHeaderSize());
            }
        }
    }

    private static int readHeaderFromStream(int i, InputStream inputStream, byte[] bArr) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(bArr);
        Preconditions.checkArgument(Boolean.valueOf(bArr.length >= i));
        if (!inputStream.markSupported()) {
            return ByteStreams.read(inputStream, bArr, 0, i);
        }
        try {
            inputStream.mark(i);
            return ByteStreams.read(inputStream, bArr, 0, i);
        } finally {
            inputStream.reset();
        }
    }

    public static synchronized ImageFormatChecker getInstance() {
        ImageFormatChecker imageFormatChecker;
        synchronized (ImageFormatChecker.class) {
            if (sInstance == null) {
                sInstance = new ImageFormatChecker();
            }
            imageFormatChecker = sInstance;
        }
        return imageFormatChecker;
    }

    public static ImageFormat getImageFormat(InputStream inputStream) throws IOException {
        return getInstance().determineImageFormat(inputStream);
    }

    public static ImageFormat getImageFormat_WrapIOException(InputStream inputStream) {
        try {
            return getImageFormat(inputStream);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:6|7|8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        com.facebook.common.internal.Closeables.closeQuietly((java.io.InputStream) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        return com.facebook.imageformat.ImageFormat.UNKNOWN;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0014 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.imageformat.ImageFormat getImageFormat(java.lang.String r2) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0014 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0014 }
            java.io.FileInputStream r0 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r1, (java.lang.String) r2)     // Catch:{ IOException -> 0x0014 }
            com.facebook.imageformat.ImageFormat r2 = getImageFormat((java.io.InputStream) r0)     // Catch:{ IOException -> 0x0014 }
            com.facebook.common.internal.Closeables.closeQuietly((java.io.InputStream) r0)
            return r2
        L_0x0012:
            r2 = move-exception
            goto L_0x001a
        L_0x0014:
            com.facebook.imageformat.ImageFormat r2 = com.facebook.imageformat.ImageFormat.UNKNOWN     // Catch:{ all -> 0x0012 }
            com.facebook.common.internal.Closeables.closeQuietly((java.io.InputStream) r0)
            return r2
        L_0x001a:
            com.facebook.common.internal.Closeables.closeQuietly((java.io.InputStream) r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imageformat.ImageFormatChecker.getImageFormat(java.lang.String):com.facebook.imageformat.ImageFormat");
    }
}
