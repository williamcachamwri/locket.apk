package com.jimmydaddy.imagemarker;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import com.jimmydaddy.imagemarker.base.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/jimmydaddy/imagemarker/ImageProcess;", "", "()V", "Companion", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageProcess.kt */
public final class ImageProcess {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/jimmydaddy/imagemarker/ImageProcess$Companion;", "", "()V", "rotate", "Landroid/graphics/Bitmap;", "bitmap", "rotation", "", "scaleBitmap", "scale", "", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ImageProcess.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Bitmap rotate(Bitmap bitmap, Number number) {
            Intrinsics.checkNotNullParameter(bitmap, "bitmap");
            Intrinsics.checkNotNullParameter(number, "rotation");
            Matrix matrix = new Matrix();
            matrix.postRotate(number.floatValue());
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            return createBitmap;
        }

        public final Bitmap scaleBitmap(Bitmap bitmap, float f) {
            Bitmap bitmap2;
            Intrinsics.checkNotNullParameter(bitmap, "bitmap");
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            if (!(f == 1.0f) && f >= 0.0f) {
                matrix.postScale(f, f);
            }
            Integer num = null;
            try {
                bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            } catch (OutOfMemoryError e) {
                System.out.print(e.getMessage());
                Bitmap bitmap3 = null;
                while (bitmap3 == null) {
                    System.gc();
                    System.runFinalization();
                    bitmap3 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                }
                bitmap2 = bitmap3;
            }
            Integer valueOf = bitmap2 != null ? Integer.valueOf(bitmap2.getWidth()) : null;
            if (bitmap2 != null) {
                num = Integer.valueOf(bitmap2.getHeight());
            }
            Log.d(Constants.IMAGE_MARKER_TAG, "original width: " + width + " original height: " + height + " scaled width: " + valueOf + " scaled height: " + num);
            return bitmap2;
        }
    }
}
