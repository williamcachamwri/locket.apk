package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;
import android.os.SystemClock;
import com.google.android.gms.internal.mlkit_vision_common.zzms;
import com.google.android.gms.internal.mlkit_vision_common.zzmu;
import com.google.android.gms.internal.mlkit_vision_common.zzmw;
import com.google.android.odml.image.BitmapExtractor;
import com.google.android.odml.image.ByteBufferExtractor;
import com.google.android.odml.image.ImageProperties;
import com.google.android.odml.image.MediaImageExtractor;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;
import java.util.List;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public class CommonConvertUtils {
    public static InputImage convertMlImagetoInputImage(MlImage mlImage) {
        InputImage inputImage;
        Integer num;
        int i;
        ImageProperties imageProperties = mlImage.getContainedImageProperties().get(0);
        int storageType = imageProperties.getStorageType();
        if (storageType != 1) {
            inputImage = null;
            if (storageType == 2) {
                ByteBuffer extract = ByteBufferExtractor.extract(mlImage);
                int imageFormat = imageProperties.getImageFormat();
                if (imageFormat == 4) {
                    num = 17;
                } else if (imageFormat != 5) {
                    num = null;
                } else {
                    num = Integer.valueOf(InputImage.IMAGE_FORMAT_YV12);
                }
                if (num != null) {
                    zza(num.intValue(), 3, SystemClock.elapsedRealtime(), mlImage.getHeight(), mlImage.getWidth(), extract.limit(), mlImage.getRotation());
                    inputImage = InputImage.fromByteBuffer(extract, mlImage.getWidth(), mlImage.getHeight(), mlImage.getRotation(), num.intValue());
                }
            } else if (storageType == 3) {
                Image extract2 = MediaImageExtractor.extract(mlImage);
                if (extract2.getFormat() == 256) {
                    i = extract2.getPlanes()[0].getBuffer().limit();
                } else {
                    i = (extract2.getPlanes()[0].getBuffer().limit() * 3) / 2;
                }
                zza(extract2.getFormat(), 5, SystemClock.elapsedRealtime(), mlImage.getHeight(), mlImage.getWidth(), i, mlImage.getRotation());
                inputImage = InputImage.fromMediaImage(extract2, mlImage.getRotation());
            }
        } else {
            Bitmap extract3 = BitmapExtractor.extract(mlImage);
            zza(-1, 1, SystemClock.elapsedRealtime(), mlImage.getHeight(), mlImage.getWidth(), extract3.getAllocationByteCount(), mlImage.getRotation());
            inputImage = InputImage.fromBitmap(extract3, mlImage.getRotation());
        }
        if (inputImage != null) {
            zzmw.zza();
        }
        return inputImage;
    }

    public static int convertToAndroidImageFormat(int i) {
        int i2 = 17;
        if (i != 17) {
            i2 = 35;
            if (i != 35) {
                i2 = InputImage.IMAGE_FORMAT_YV12;
                if (i != 842094169) {
                    return 0;
                }
            }
        }
        return i2;
    }

    public static int convertToMVRotation(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 90) {
            return 1;
        }
        if (i == 180) {
            return 2;
        }
        if (i == 270) {
            return 3;
        }
        throw new IllegalArgumentException("Invalid rotation: " + i);
    }

    public static void transformPointArray(Point[] pointArr, Matrix matrix) {
        int length = pointArr.length;
        float[] fArr = new float[(length + length)];
        for (int i = 0; i < pointArr.length; i++) {
            int i2 = i + i;
            fArr[i2] = (float) pointArr[i].x;
            fArr[i2 + 1] = (float) pointArr[i].y;
        }
        matrix.mapPoints(fArr);
        for (int i3 = 0; i3 < pointArr.length; i3++) {
            int i4 = i3 + i3;
            pointArr[i3].set((int) fArr[i4], (int) fArr[i4 + 1]);
        }
    }

    public static void transformPointF(PointF pointF, Matrix matrix) {
        float[] fArr = {pointF.x, pointF.y};
        matrix.mapPoints(fArr);
        pointF.set(fArr[0], fArr[1]);
    }

    public static void transformPointList(List<PointF> list, Matrix matrix) {
        int size = list.size();
        float[] fArr = new float[(size + size)];
        for (int i = 0; i < list.size(); i++) {
            int i2 = i + i;
            fArr[i2] = list.get(i).x;
            fArr[i2 + 1] = list.get(i).y;
        }
        matrix.mapPoints(fArr);
        for (int i3 = 0; i3 < list.size(); i3++) {
            int i4 = i3 + i3;
            list.get(i3).set(fArr[i4], fArr[i4 + 1]);
        }
    }

    public static void transformRect(Rect rect, Matrix matrix) {
        RectF rectF = new RectF(rect);
        matrix.mapRect(rectF);
        rect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
    }

    private static void zza(int i, int i2, long j, int i3, int i4, int i5, int i6) {
        zzmu.zzb(zzms.zzb("vision-common"), i, i2, j, i3, i4, i5, i6);
    }
}
