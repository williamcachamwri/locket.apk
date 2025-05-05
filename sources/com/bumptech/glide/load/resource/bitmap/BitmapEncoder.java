package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;

public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);
    private static final String TAG = "BitmapEncoder";
    private final ArrayPool arrayPool;

    public BitmapEncoder(ArrayPool arrayPool2) {
        this.arrayPool = arrayPool2;
    }

    @Deprecated
    public BitmapEncoder() {
        this.arrayPool = null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:14|(2:34|35)|36|37) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x00c5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean encode(com.bumptech.glide.load.engine.Resource<android.graphics.Bitmap> r9, java.io.File r10, com.bumptech.glide.load.Options r11) {
        /*
            r8 = this;
            java.lang.String r0 = "BitmapEncoder"
            java.lang.String r1 = "Compressed with type: "
            java.lang.Object r9 = r9.get()
            android.graphics.Bitmap r9 = (android.graphics.Bitmap) r9
            android.graphics.Bitmap$CompressFormat r2 = r8.getFormat(r9, r11)
            int r3 = r9.getWidth()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            int r4 = r9.getHeight()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.String r5 = "encode: [%dx%d] %s"
            com.bumptech.glide.util.pool.GlideTrace.beginSectionFormat(r5, r3, r4, r2)
            long r3 = com.bumptech.glide.util.LogTime.getLogTime()     // Catch:{ all -> 0x00c6 }
            com.bumptech.glide.load.Option<java.lang.Integer> r5 = COMPRESSION_QUALITY     // Catch:{ all -> 0x00c6 }
            java.lang.Object r5 = r11.get(r5)     // Catch:{ all -> 0x00c6 }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ all -> 0x00c6 }
            int r5 = r5.intValue()     // Catch:{ all -> 0x00c6 }
            r6 = 0
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0058 }
            r7.<init>(r10)     // Catch:{ IOException -> 0x0058 }
            java.io.FileOutputStream r6 = io.sentry.instrumentation.file.SentryFileOutputStream.Factory.create((java.io.FileOutputStream) r7, (java.io.File) r10)     // Catch:{ IOException -> 0x0058 }
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r10 = r8.arrayPool     // Catch:{ IOException -> 0x0058 }
            if (r10 == 0) goto L_0x0049
            com.bumptech.glide.load.data.BufferedOutputStream r10 = new com.bumptech.glide.load.data.BufferedOutputStream     // Catch:{ IOException -> 0x0058 }
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r7 = r8.arrayPool     // Catch:{ IOException -> 0x0058 }
            r10.<init>(r6, r7)     // Catch:{ IOException -> 0x0058 }
            r6 = r10
        L_0x0049:
            r9.compress(r2, r5, r6)     // Catch:{ IOException -> 0x0058 }
            r6.close()     // Catch:{ IOException -> 0x0058 }
            if (r6 == 0) goto L_0x0054
            r6.close()     // Catch:{ IOException -> 0x0054 }
        L_0x0054:
            r10 = 1
            goto L_0x006b
        L_0x0056:
            r9 = move-exception
            goto L_0x00c0
        L_0x0058:
            r10 = move-exception
            r5 = 3
            boolean r5 = android.util.Log.isLoggable(r0, r5)     // Catch:{ all -> 0x0056 }
            if (r5 == 0) goto L_0x0065
            java.lang.String r5 = "Failed to encode Bitmap"
            android.util.Log.d(r0, r5, r10)     // Catch:{ all -> 0x0056 }
        L_0x0065:
            if (r6 == 0) goto L_0x006a
            r6.close()     // Catch:{ IOException -> 0x006a }
        L_0x006a:
            r10 = 0
        L_0x006b:
            r5 = 2
            boolean r5 = android.util.Log.isLoggable(r0, r5)     // Catch:{ all -> 0x00c6 }
            if (r5 == 0) goto L_0x00bc
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c6 }
            r5.<init>(r1)     // Catch:{ all -> 0x00c6 }
            java.lang.StringBuilder r1 = r5.append(r2)     // Catch:{ all -> 0x00c6 }
            java.lang.String r2 = " of size "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x00c6 }
            int r2 = com.bumptech.glide.util.Util.getBitmapByteSize(r9)     // Catch:{ all -> 0x00c6 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x00c6 }
            java.lang.String r2 = " in "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x00c6 }
            double r2 = com.bumptech.glide.util.LogTime.getElapsedMillis(r3)     // Catch:{ all -> 0x00c6 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x00c6 }
            java.lang.String r2 = ", options format: "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x00c6 }
            com.bumptech.glide.load.Option<android.graphics.Bitmap$CompressFormat> r2 = COMPRESSION_FORMAT     // Catch:{ all -> 0x00c6 }
            java.lang.Object r11 = r11.get(r2)     // Catch:{ all -> 0x00c6 }
            java.lang.StringBuilder r11 = r1.append(r11)     // Catch:{ all -> 0x00c6 }
            java.lang.String r1 = ", hasAlpha: "
            java.lang.StringBuilder r11 = r11.append(r1)     // Catch:{ all -> 0x00c6 }
            boolean r9 = r9.hasAlpha()     // Catch:{ all -> 0x00c6 }
            java.lang.StringBuilder r9 = r11.append(r9)     // Catch:{ all -> 0x00c6 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00c6 }
            android.util.Log.v(r0, r9)     // Catch:{ all -> 0x00c6 }
        L_0x00bc:
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            return r10
        L_0x00c0:
            if (r6 == 0) goto L_0x00c5
            r6.close()     // Catch:{ IOException -> 0x00c5 }
        L_0x00c5:
            throw r9     // Catch:{ all -> 0x00c6 }
        L_0x00c6:
            r9 = move-exception
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.BitmapEncoder.encode(com.bumptech.glide.load.engine.Resource, java.io.File, com.bumptech.glide.load.Options):boolean");
    }

    private Bitmap.CompressFormat getFormat(Bitmap bitmap, Options options) {
        Bitmap.CompressFormat compressFormat = (Bitmap.CompressFormat) options.get(COMPRESSION_FORMAT);
        if (compressFormat != null) {
            return compressFormat;
        }
        if (bitmap.hasAlpha()) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    public EncodeStrategy getEncodeStrategy(Options options) {
        return EncodeStrategy.TRANSFORMED;
    }
}
