package com.reactnativekeyboardcontroller.extensions;

import android.content.res.Resources;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0005\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\u0007"}, d2 = {"dp", "", "", "getDp", "(F)D", "px", "getPx", "react-native-keyboard-controller_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Float.kt */
public final class FloatKt {
    public static final double getDp(float f) {
        return (double) (f / Resources.getSystem().getDisplayMetrics().density);
    }

    public static final double getPx(float f) {
        return (double) (f * Resources.getSystem().getDisplayMetrics().density);
    }
}
