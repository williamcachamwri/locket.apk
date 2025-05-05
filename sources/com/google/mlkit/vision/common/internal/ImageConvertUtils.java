package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public class ImageConvertUtils {
    private static final ImageConvertUtils zza = new ImageConvertUtils();

    private ImageConvertUtils() {
    }

    public static ByteBuffer bufferWithBackingArray(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return byteBuffer;
        }
        byteBuffer.rewind();
        byte[] bArr = new byte[byteBuffer.limit()];
        byteBuffer.get(bArr);
        return ByteBuffer.wrap(bArr);
    }

    public static ImageConvertUtils getInstance() {
        return zza;
    }

    public static Bitmap yv12ToBitmap(ByteBuffer byteBuffer, int i, int i2, int i3) throws MlKitException {
        byte[] zzb = zzb(yv12ToNv21Buffer(byteBuffer, true).array(), i, i2);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(zzb, 0, zzb.length);
        return zza(decodeByteArray, i3, decodeByteArray.getWidth(), decodeByteArray.getHeight());
    }

    public static ByteBuffer yv12ToNv21Buffer(ByteBuffer byteBuffer, boolean z) {
        ByteBuffer byteBuffer2;
        int i;
        byteBuffer.rewind();
        int limit = byteBuffer.limit();
        int i2 = limit / 6;
        if (z) {
            byteBuffer2 = ByteBuffer.allocate(limit);
        } else {
            byteBuffer2 = ByteBuffer.allocateDirect(limit);
        }
        int i3 = 0;
        while (true) {
            i = i2 * 4;
            if (i3 >= i) {
                break;
            }
            byteBuffer2.put(i3, byteBuffer.get(i3));
            i3++;
        }
        for (int i4 = 0; i4 < i2 + i2; i4++) {
            byteBuffer2.put(i + i4, byteBuffer.get(((i4 % 2) * i2) + i + (i4 / 2)));
        }
        return byteBuffer2;
    }

    public static Bitmap zza(Bitmap bitmap, int i, int i2, int i3) {
        if (i == 0) {
            return Bitmap.createBitmap(bitmap, 0, 0, i2, i3);
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, i2, i3, matrix, true);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:16|17) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        throw r8;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0040 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] zzb(byte[] r7, int r8, int r9) throws com.google.mlkit.common.MlKitException {
        /*
            android.graphics.YuvImage r6 = new android.graphics.YuvImage
            r2 = 17
            r5 = 0
            r0 = r6
            r1 = r7
            r3 = r8
            r4 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0041 }
            r7.<init>()     // Catch:{ IOException -> 0x0041 }
            r0 = 0
            android.graphics.Rect r1 = new android.graphics.Rect     // Catch:{ all -> 0x0024 }
            r1.<init>(r0, r0, r8, r9)     // Catch:{ all -> 0x0024 }
            r8 = 100
            r6.compressToJpeg(r1, r8, r7)     // Catch:{ all -> 0x0024 }
            byte[] r8 = r7.toByteArray()     // Catch:{ all -> 0x0024 }
            r7.close()     // Catch:{ IOException -> 0x0041 }
            return r8
        L_0x0024:
            r8 = move-exception
            r7.close()     // Catch:{ all -> 0x0029 }
            goto L_0x0040
        L_0x0029:
            r7 = move-exception
            java.lang.Class<java.lang.Throwable> r9 = java.lang.Throwable.class
            java.lang.String r1 = "addSuppressed"
            r2 = 1
            java.lang.Class[] r2 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x0040 }
            java.lang.Class<java.lang.Throwable> r3 = java.lang.Throwable.class
            r2[r0] = r3     // Catch:{ Exception -> 0x0040 }
            java.lang.reflect.Method r9 = r9.getDeclaredMethod(r1, r2)     // Catch:{ Exception -> 0x0040 }
            java.lang.Object[] r7 = new java.lang.Object[]{r7}     // Catch:{ Exception -> 0x0040 }
            r9.invoke(r8, r7)     // Catch:{ Exception -> 0x0040 }
        L_0x0040:
            throw r8     // Catch:{ IOException -> 0x0041 }
        L_0x0041:
            r7 = move-exception
            java.lang.String r8 = "ImageConvertUtils"
            java.lang.String r9 = "Error closing ByteArrayOutputStream"
            io.sentry.android.core.SentryLogcatAdapter.w((java.lang.String) r8, (java.lang.String) r9)
            com.google.mlkit.common.MlKitException r8 = new com.google.mlkit.common.MlKitException
            java.lang.String r9 = "Image conversion error from NV21 format"
            r0 = 13
            r8.<init>(r9, r0, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.vision.common.internal.ImageConvertUtils.zzb(byte[], int, int):byte[]");
    }

    private static final void zzc(Image.Plane plane, int i, int i2, byte[] bArr, int i3, int i4) {
        ByteBuffer buffer = plane.getBuffer();
        buffer.rewind();
        int limit = ((buffer.limit() + plane.getRowStride()) - 1) / plane.getRowStride();
        if (limit != 0) {
            int i5 = i / (i2 / limit);
            int i6 = 0;
            for (int i7 = 0; i7 < limit; i7++) {
                int i8 = i6;
                for (int i9 = 0; i9 < i5; i9++) {
                    bArr[i3] = buffer.get(i8);
                    i3 += i4;
                    i8 += plane.getPixelStride();
                }
                i6 += plane.getRowStride();
            }
        }
    }

    public byte[] byteBufferToByteArray(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            return byteBuffer.array();
        }
        byteBuffer.rewind();
        int limit = byteBuffer.limit();
        byte[] bArr = new byte[limit];
        byteBuffer.get(bArr, 0, limit);
        return bArr;
    }

    public ByteBuffer cloneByteBuffer(ByteBuffer byteBuffer) {
        Preconditions.checkNotNull(byteBuffer);
        int capacity = byteBuffer.capacity();
        int position = byteBuffer.position();
        ByteBuffer allocateDirect = byteBuffer.isDirect() ? ByteBuffer.allocateDirect(capacity) : ByteBuffer.allocate(capacity);
        allocateDirect.limit(byteBuffer.limit());
        allocateDirect.put((ByteBuffer) byteBuffer.rewind());
        allocateDirect.position(position);
        byteBuffer.position(position);
        return allocateDirect;
    }

    public Bitmap convertJpegToUpRightBitmap(Image image, int i) {
        Preconditions.checkArgument(image.getFormat() == 256, "Only JPEG is supported now");
        Image.Plane[] planes = image.getPlanes();
        if (planes == null || planes.length != 1) {
            throw new IllegalArgumentException("Unexpected image format, JPEG should have exactly 1 image plane");
        }
        ByteBuffer buffer = planes[0].getBuffer();
        buffer.rewind();
        int remaining = buffer.remaining();
        byte[] bArr = new byte[remaining];
        buffer.get(bArr);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, remaining);
        return zza(decodeByteArray, i, decodeByteArray.getWidth(), decodeByteArray.getHeight());
    }

    public ByteBuffer convertToNv21Buffer(InputImage inputImage, boolean z) throws MlKitException {
        ByteBuffer byteBuffer;
        boolean z2 = z;
        int format = inputImage.getFormat();
        if (format == -1) {
            Bitmap bitmap = (Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal());
            if (bitmap.getConfig() == Bitmap.Config.HARDWARE) {
                bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, bitmap.isMutable());
            }
            Bitmap bitmap2 = bitmap;
            int width = bitmap2.getWidth();
            int height = bitmap2.getHeight();
            int i = width * height;
            int[] iArr = new int[i];
            bitmap2.getPixels(iArr, 0, width, 0, 0, width, height);
            int ceil = (int) Math.ceil(((double) height) / 2.0d);
            int ceil2 = ((ceil + ceil) * ((int) Math.ceil(((double) width) / 2.0d))) + i;
            if (z2) {
                byteBuffer = ByteBuffer.allocate(ceil2);
            } else {
                byteBuffer = ByteBuffer.allocateDirect(ceil2);
            }
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < height; i4++) {
                int i5 = 0;
                while (i5 < width) {
                    int i6 = iArr[i3];
                    int i7 = i6 >> 16;
                    int i8 = i6 >> 8;
                    int i9 = i6 & 255;
                    int i10 = i2 + 1;
                    int i11 = i7 & 255;
                    int i12 = i8 & 255;
                    byteBuffer.put(i2, (byte) Math.min(255, (((((i11 * 66) + (i12 * TsExtractor.TS_STREAM_TYPE_AC3)) + (i9 * 25)) + 128) >> 8) + 16));
                    if (i4 % 2 == 0 && i3 % 2 == 0) {
                        int i13 = i11 * 112;
                        int i14 = i + 1;
                        byteBuffer.put(i, (byte) Math.min(255, ((((i13 - (i12 * 94)) - (i9 * 18)) + 128) >> 8) + 128));
                        i = i14 + 1;
                        byteBuffer.put(i14, (byte) Math.min(255, (((((i11 * -38) - (i12 * 74)) + (i9 * 112)) + 128) >> 8) + 128));
                    }
                    i3++;
                    i5++;
                    i2 = i10;
                }
            }
            return byteBuffer;
        } else if (format == 17) {
            if (z2) {
                return bufferWithBackingArray((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer()));
            }
            return (ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer());
        } else if (format == 35) {
            return yuv420ThreePlanesToNV21((Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes()), inputImage.getWidth(), inputImage.getHeight());
        } else if (format == 842094169) {
            return yv12ToNv21Buffer((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer()), z2);
        } else {
            throw new MlKitException("Unsupported image format", 13);
        }
    }

    public Bitmap convertToUpRightBitmap(InputImage inputImage) throws MlKitException {
        int format = inputImage.getFormat();
        if (format == -1) {
            return zza((Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal()), inputImage.getRotationDegrees(), inputImage.getWidth(), inputImage.getHeight());
        }
        if (format == 17) {
            return nv21ToBitmap((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer()), inputImage.getWidth(), inputImage.getHeight(), inputImage.getRotationDegrees());
        }
        if (format == 35) {
            return nv21ToBitmap(yuv420ThreePlanesToNV21((Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes()), inputImage.getWidth(), inputImage.getHeight()), inputImage.getWidth(), inputImage.getHeight(), inputImage.getRotationDegrees());
        }
        if (format == 842094169) {
            return yv12ToBitmap((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer()), inputImage.getWidth(), inputImage.getHeight(), inputImage.getRotationDegrees());
        }
        throw new MlKitException("Unsupported image format", 13);
    }

    public Bitmap getUpRightBitmap(InputImage inputImage) throws MlKitException {
        Bitmap bitmapInternal = inputImage.getBitmapInternal();
        if (bitmapInternal != null) {
            return zza(bitmapInternal, inputImage.getRotationDegrees(), inputImage.getWidth(), inputImage.getHeight());
        }
        return convertToUpRightBitmap(inputImage);
    }

    public Bitmap nv21ToBitmap(ByteBuffer byteBuffer, int i, int i2, int i3) throws MlKitException {
        byte[] zzb = zzb(byteBufferToByteArray(byteBuffer), i, i2);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(zzb, 0, zzb.length);
        return zza(decodeByteArray, i3, decodeByteArray.getWidth(), decodeByteArray.getHeight());
    }

    public ByteBuffer yuv420ThreePlanesToNV21(Image.Plane[] planeArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = i3 / 4;
        byte[] bArr = new byte[(i4 + i4 + i3)];
        ByteBuffer buffer = planeArr[1].getBuffer();
        ByteBuffer buffer2 = planeArr[2].getBuffer();
        int position = buffer2.position();
        int limit = buffer.limit();
        buffer2.position(position + 1);
        buffer.limit(limit - 1);
        int i5 = (i3 + i3) / 4;
        boolean z = buffer2.remaining() == i5 + -2 && buffer2.compareTo(buffer) == 0;
        buffer2.position(position);
        buffer.limit(limit);
        if (z) {
            planeArr[0].getBuffer().get(bArr, 0, i3);
            ByteBuffer buffer3 = planeArr[1].getBuffer();
            planeArr[2].getBuffer().get(bArr, i3, 1);
            buffer3.get(bArr, i3 + 1, i5 - 1);
        } else {
            int i6 = i;
            int i7 = i2;
            byte[] bArr2 = bArr;
            zzc(planeArr[0], i6, i7, bArr2, 0, 1);
            zzc(planeArr[1], i6, i7, bArr2, i3 + 1, 2);
            zzc(planeArr[2], i, i2, bArr, i3, 2);
        }
        return ByteBuffer.wrap(bArr);
    }
}
