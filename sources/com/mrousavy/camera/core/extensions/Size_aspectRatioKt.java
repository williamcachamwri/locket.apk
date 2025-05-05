package com.mrousavy.camera.core.extensions;

import android.util.Size;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"aspectRatio", "", "Landroid/util/Size;", "getAspectRatio", "(Landroid/util/Size;)F", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Size+aspectRatio.kt */
public final class Size_aspectRatioKt {
    public static final float getAspectRatio(Size size) {
        Intrinsics.checkNotNullParameter(size, "<this>");
        return ((float) size.getWidth()) / ((float) size.getHeight());
    }
}
