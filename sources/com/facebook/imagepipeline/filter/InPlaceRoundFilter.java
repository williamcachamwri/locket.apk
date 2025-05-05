package com.facebook.imagepipeline.filter;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/facebook/imagepipeline/filter/InPlaceRoundFilter;", "", "()V", "roundBitmapInPlace", "", "bitmap", "Landroid/graphics/Bitmap;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: InPlaceRoundFilter.kt */
public final class InPlaceRoundFilter {
    public static final InPlaceRoundFilter INSTANCE = new InPlaceRoundFilter();

    private InPlaceRoundFilter() {
    }

    @JvmStatic
    public static final void roundBitmapInPlace(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = Math.min(width, height) / 2;
        int i = width / 2;
        int i2 = height / 2;
        if (min != 0) {
            Preconditions.checkArgument(Boolean.valueOf(min >= 1));
            Preconditions.checkArgument(Boolean.valueOf(width > 0 && ((float) width) <= 2048.0f));
            Preconditions.checkArgument(Boolean.valueOf(height > 0 && ((float) height) <= 2048.0f));
            Preconditions.checkArgument(Boolean.valueOf(i > 0 && i < width));
            Preconditions.checkArgument(Boolean.valueOf(i2 > 0 && i2 < height));
            int[] iArr = new int[(width * height)];
            int[] iArr2 = iArr;
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            int i3 = min - 1;
            Preconditions.checkArgument(Boolean.valueOf(i - i3 >= 0 && i2 - i3 >= 0 && i + i3 < width && i2 + i3 < height));
            int i4 = (-min) * 2;
            int[] iArr3 = new int[width];
            int i5 = i4 + 1;
            int i6 = 0;
            int i7 = 1;
            int i8 = 1;
            while (i3 >= i6) {
                int i9 = i + i3;
                int i10 = i - i3;
                int i11 = i + i6;
                int i12 = min;
                int i13 = i - i6;
                int i14 = i2 + i3;
                int i15 = i2 - i3;
                int i16 = i;
                int i17 = i2 + i6;
                int i18 = i2 - i6;
                Preconditions.checkArgument(Boolean.valueOf(i3 >= 0 && i11 < width && i13 >= 0 && i17 < height && i18 >= 0));
                int i19 = i17 * width;
                int i20 = height;
                int i21 = width * i18;
                int i22 = i2;
                int i23 = width * i14;
                int i24 = i4;
                int i25 = width * i15;
                int i26 = i7;
                System.arraycopy(iArr3, 0, iArr2, i19, i10);
                System.arraycopy(iArr3, 0, iArr2, i21, i10);
                System.arraycopy(iArr3, 0, iArr2, i23, i13);
                System.arraycopy(iArr3, 0, iArr2, i25, i13);
                int i27 = width - i9;
                System.arraycopy(iArr3, 0, iArr2, i19 + i9, i27);
                System.arraycopy(iArr3, 0, iArr2, i21 + i9, i27);
                int i28 = width - i11;
                System.arraycopy(iArr3, 0, iArr2, i23 + i11, i28);
                System.arraycopy(iArr3, 0, iArr2, i25 + i11, i28);
                if (i5 <= 0) {
                    i6++;
                    i8 += 2;
                    i5 += i8;
                }
                if (i5 > 0) {
                    i3--;
                    i7 = i26 + 2;
                    i5 += i7 + i24;
                    Bitmap bitmap2 = bitmap;
                    min = i12;
                    i4 = i24;
                } else {
                    Bitmap bitmap3 = bitmap;
                    min = i12;
                    i4 = i24;
                    i7 = i26;
                }
                i = i16;
                i2 = i22;
                height = i20;
            }
            int i29 = height;
            int i30 = min;
            int i31 = i2;
            for (int i32 = i31 - i30; -1 < i32; i32--) {
                System.arraycopy(iArr3, 0, iArr2, i32 * width, width);
            }
            int i33 = i29;
            for (int i34 = i31 + i30; i34 < i33; i34++) {
                System.arraycopy(iArr3, 0, iArr2, i34 * width, width);
            }
            bitmap.setPixels(iArr2, 0, width, 0, 0, width, i33);
        }
    }
}
