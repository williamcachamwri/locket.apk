package com.facebook.imagepipeline.filter;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0007J \u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J8\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0013H\u0002J@\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/facebook/imagepipeline/filter/IterativeBoxBlurFilter;", "", "()V", "TAG", "", "bound", "", "x", "l", "h", "boxBlurBitmapInPlace", "", "bitmap", "Landroid/graphics/Bitmap;", "iterations", "radius", "fastBoxBlur", "internalHorizontalBlur", "pixels", "", "outRow", "w", "row", "diameter", "div", "internalVerticalBlur", "outCol", "col", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: IterativeBoxBlurFilter.kt */
public final class IterativeBoxBlurFilter {
    public static final IterativeBoxBlurFilter INSTANCE = new IterativeBoxBlurFilter();
    private static final String TAG = "IterativeBoxBlurFilter";

    private final int bound(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    private IterativeBoxBlurFilter() {
    }

    @JvmStatic
    public static final void boxBlurBitmapInPlace(Bitmap bitmap, int i, int i2) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Preconditions.checkArgument(Boolean.valueOf(bitmap.isMutable()));
        boolean z = true;
        Preconditions.checkArgument(Boolean.valueOf(((float) bitmap.getHeight()) <= 2048.0f));
        Preconditions.checkArgument(Boolean.valueOf(((float) bitmap.getWidth()) <= 2048.0f));
        Preconditions.checkArgument(Boolean.valueOf(i2 > 0 && i2 <= 25));
        if (i <= 0) {
            z = false;
        }
        Preconditions.checkArgument(Boolean.valueOf(z));
        try {
            INSTANCE.fastBoxBlur(bitmap, i, i2);
        } catch (OutOfMemoryError e) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format((Locale) null, "OOM: %d iterations on %dx%d with %d radius", Arrays.copyOf(new Object[]{Integer.valueOf(i), Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), Integer.valueOf(i2)}, 4));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            FLog.e(TAG, format);
            throw e;
        }
    }

    private final void fastBoxBlur(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int i3 = i2 + 1;
        int i4 = i3 + i2;
        int[] iArr2 = new int[(i4 * 256)];
        int i5 = 1;
        while (true) {
            if (i5 >= 256) {
                break;
            }
            for (int i6 = 0; i6 < i4; i6++) {
                iArr2[i3] = i5;
                i3++;
            }
            i5++;
        }
        int[] iArr3 = new int[Math.max(width, height)];
        int i7 = i;
        int i8 = 0;
        while (i8 < i7) {
            int i9 = 0;
            while (i9 < height) {
                int i10 = i9;
                internalHorizontalBlur(iArr, iArr3, width, i9, i4, iArr2);
                System.arraycopy(iArr3, 0, iArr, i10 * width, width);
                i9 = i10 + 1;
            }
            int i11 = 0;
            while (i11 < width) {
                int i12 = i11;
                int i13 = i8;
                internalVerticalBlur(iArr, iArr3, width, height, i11, i4, iArr2);
                int i14 = i12;
                for (int i15 = 0; i15 < height; i15++) {
                    iArr[i14] = iArr3[i15];
                    i14 += width;
                }
                i11 = i12 + 1;
                i8 = i13;
            }
            i8++;
        }
        bitmap.setPixels(iArr, 0, width, 0, 0, width, height);
    }

    private final void internalHorizontalBlur(int[] iArr, int[] iArr2, int i, int i2, int i3, int[] iArr3) {
        int i4 = i * i2;
        int i5 = ((i2 + 1) * i) - 1;
        int i6 = i3 >> 1;
        int i7 = i + i6;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        for (int i12 = -i6; i12 < i7; i12++) {
            int i13 = iArr[bound(i4 + i12, i4, i5)];
            i8 += (i13 >> 16) & 255;
            i9 += (i13 >> 8) & 255;
            i10 += i13 & 255;
            i11 += i13 >>> 24;
            if (i12 >= i6) {
                iArr2[i12 - i6] = (iArr3[i11] << 24) | (iArr3[i8] << 16) | (iArr3[i9] << 8) | iArr3[i10];
                int i14 = iArr[bound((i12 - (i3 - 1)) + i4, i4, i5)];
                i8 -= (i14 >> 16) & 255;
                i9 -= (i14 >> 8) & 255;
                i10 -= i14 & 255;
                i11 -= i14 >>> 24;
            }
        }
    }

    private final void internalVerticalBlur(int[] iArr, int[] iArr2, int i, int i2, int i3, int i4, int[] iArr3) {
        int i5 = i3;
        int i6 = ((i2 - 1) * i) + i5;
        int i7 = (i4 >> 1) * i;
        int i8 = (i4 - 1) * i;
        int i9 = i5 - i7;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i9 <= i6 + i7) {
            int i15 = iArr[bound(i9, i5, i6)];
            i10 += (i15 >> 16) & 255;
            i11 += (i15 >> 8) & 255;
            i12 += i15 & 255;
            i13 += i15 >>> 24;
            if (i9 - i7 >= i5) {
                iArr2[i14] = (iArr3[i13] << 24) | (iArr3[i10] << 16) | (iArr3[i11] << 8) | iArr3[i12];
                i14++;
                int i16 = iArr[bound(i9 - i8, i5, i6)];
                i10 -= (i16 >> 16) & 255;
                i11 -= (i16 >> 8) & 255;
                i12 -= i16 & 255;
                i13 -= i16 >>> 24;
            }
            i9 += i;
        }
    }
}
