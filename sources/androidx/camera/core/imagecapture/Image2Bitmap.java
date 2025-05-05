package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;

public class Image2Bitmap implements Operation<Packet<ImageProxy>, Bitmap> {
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b6 A[Catch:{ all -> 0x00ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b9 A[Catch:{ all -> 0x00ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap apply(androidx.camera.core.processing.Packet<androidx.camera.core.ImageProxy> r11) throws androidx.camera.core.ImageCaptureException {
        /*
            r10 = this;
            java.lang.String r0 = "Can't convert "
            java.lang.String r1 = "Invalid postview image format : "
            r2 = 35
            r3 = 0
            r4 = 0
            int r5 = r11.getFormat()     // Catch:{ UnsupportedOperationException -> 0x00af }
            if (r5 != r2) goto L_0x0073
            java.lang.Object r1 = r11.getData()     // Catch:{ UnsupportedOperationException -> 0x00af }
            androidx.camera.core.ImageProxy r1 = (androidx.camera.core.ImageProxy) r1     // Catch:{ UnsupportedOperationException -> 0x00af }
            int r5 = r11.getRotationDegrees()     // Catch:{ UnsupportedOperationException -> 0x00af }
            int r5 = r5 % 180
            r6 = 1
            if (r5 == 0) goto L_0x001f
            r5 = r6
            goto L_0x0020
        L_0x001f:
            r5 = r3
        L_0x0020:
            if (r5 == 0) goto L_0x0027
            int r7 = r1.getHeight()     // Catch:{ UnsupportedOperationException -> 0x00af }
            goto L_0x002b
        L_0x0027:
            int r7 = r1.getWidth()     // Catch:{ UnsupportedOperationException -> 0x00af }
        L_0x002b:
            if (r5 == 0) goto L_0x0032
            int r5 = r1.getWidth()     // Catch:{ UnsupportedOperationException -> 0x00af }
            goto L_0x0036
        L_0x0032:
            int r5 = r1.getHeight()     // Catch:{ UnsupportedOperationException -> 0x00af }
        L_0x0036:
            androidx.camera.core.SafeCloseImageReaderProxy r8 = new androidx.camera.core.SafeCloseImageReaderProxy     // Catch:{ UnsupportedOperationException -> 0x00af }
            r9 = 2
            androidx.camera.core.impl.ImageReaderProxy r5 = androidx.camera.core.ImageReaderProxys.createIsolatedReader(r7, r5, r6, r9)     // Catch:{ UnsupportedOperationException -> 0x00af }
            r8.<init>(r5)     // Catch:{ UnsupportedOperationException -> 0x00af }
            int r5 = r1.getWidth()     // Catch:{ UnsupportedOperationException -> 0x0070, all -> 0x006d }
            int r6 = r1.getHeight()     // Catch:{ UnsupportedOperationException -> 0x0070, all -> 0x006d }
            int r5 = r5 * r6
            int r5 = r5 * 4
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocateDirect(r5)     // Catch:{ UnsupportedOperationException -> 0x0070, all -> 0x006d }
            int r6 = r11.getRotationDegrees()     // Catch:{ UnsupportedOperationException -> 0x0070, all -> 0x006d }
            androidx.camera.core.ImageProxy r5 = androidx.camera.core.ImageProcessingUtil.convertYUVToRGB(r1, r8, r5, r6, r3)     // Catch:{ UnsupportedOperationException -> 0x0070, all -> 0x006d }
            r1.close()     // Catch:{ UnsupportedOperationException -> 0x0070, all -> 0x006d }
            if (r5 == 0) goto L_0x0065
            android.graphics.Bitmap r1 = androidx.camera.core.internal.utils.ImageUtil.createBitmapFromImageProxy(r5)     // Catch:{ UnsupportedOperationException -> 0x0070, all -> 0x006d }
            r5.close()     // Catch:{ UnsupportedOperationException -> 0x0070, all -> 0x006d }
            r4 = r8
            goto L_0x0090
        L_0x0065:
            androidx.camera.core.ImageCaptureException r1 = new androidx.camera.core.ImageCaptureException     // Catch:{ UnsupportedOperationException -> 0x0070, all -> 0x006d }
            java.lang.String r5 = "Can't covert YUV to RGB"
            r1.<init>(r3, r5, r4)     // Catch:{ UnsupportedOperationException -> 0x0070, all -> 0x006d }
            throw r1     // Catch:{ UnsupportedOperationException -> 0x0070, all -> 0x006d }
        L_0x006d:
            r11 = move-exception
            r4 = r8
            goto L_0x00d4
        L_0x0070:
            r1 = move-exception
            r4 = r8
            goto L_0x00b0
        L_0x0073:
            int r5 = r11.getFormat()     // Catch:{ UnsupportedOperationException -> 0x00af }
            r6 = 256(0x100, float:3.59E-43)
            if (r5 != r6) goto L_0x0096
            java.lang.Object r1 = r11.getData()     // Catch:{ UnsupportedOperationException -> 0x00af }
            androidx.camera.core.ImageProxy r1 = (androidx.camera.core.ImageProxy) r1     // Catch:{ UnsupportedOperationException -> 0x00af }
            android.graphics.Bitmap r5 = androidx.camera.core.internal.utils.ImageUtil.createBitmapFromImageProxy(r1)     // Catch:{ UnsupportedOperationException -> 0x00af }
            r1.close()     // Catch:{ UnsupportedOperationException -> 0x00af }
            int r1 = r11.getRotationDegrees()     // Catch:{ UnsupportedOperationException -> 0x00af }
            android.graphics.Bitmap r1 = androidx.camera.core.internal.utils.ImageUtil.rotateBitmap(r5, r1)     // Catch:{ UnsupportedOperationException -> 0x00af }
        L_0x0090:
            if (r4 == 0) goto L_0x0095
            r4.close()
        L_0x0095:
            return r1
        L_0x0096:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch:{ UnsupportedOperationException -> 0x00af }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ UnsupportedOperationException -> 0x00af }
            r6.<init>(r1)     // Catch:{ UnsupportedOperationException -> 0x00af }
            int r1 = r11.getFormat()     // Catch:{ UnsupportedOperationException -> 0x00af }
            java.lang.StringBuilder r1 = r6.append(r1)     // Catch:{ UnsupportedOperationException -> 0x00af }
            java.lang.String r1 = r1.toString()     // Catch:{ UnsupportedOperationException -> 0x00af }
            r5.<init>(r1)     // Catch:{ UnsupportedOperationException -> 0x00af }
            throw r5     // Catch:{ UnsupportedOperationException -> 0x00af }
        L_0x00ad:
            r11 = move-exception
            goto L_0x00d4
        L_0x00af:
            r1 = move-exception
        L_0x00b0:
            int r11 = r11.getFormat()     // Catch:{ all -> 0x00ad }
            if (r11 != r2) goto L_0x00b9
            java.lang.String r11 = "YUV"
            goto L_0x00bb
        L_0x00b9:
            java.lang.String r11 = "JPEG"
        L_0x00bb:
            androidx.camera.core.ImageCaptureException r2 = new androidx.camera.core.ImageCaptureException     // Catch:{ all -> 0x00ad }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ad }
            r5.<init>(r0)     // Catch:{ all -> 0x00ad }
            java.lang.StringBuilder r11 = r5.append(r11)     // Catch:{ all -> 0x00ad }
            java.lang.String r0 = " to bitmap"
            java.lang.StringBuilder r11 = r11.append(r0)     // Catch:{ all -> 0x00ad }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00ad }
            r2.<init>(r3, r11, r1)     // Catch:{ all -> 0x00ad }
            throw r2     // Catch:{ all -> 0x00ad }
        L_0x00d4:
            if (r4 == 0) goto L_0x00d9
            r4.close()
        L_0x00d9:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.imagecapture.Image2Bitmap.apply(androidx.camera.core.processing.Packet):android.graphics.Bitmap");
    }
}
