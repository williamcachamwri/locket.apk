package com.reactnativekeyboardcontroller.extensions;

import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\u0004"}, d2 = {"isKeyboardAnimation", "", "Landroidx/core/view/WindowInsetsAnimationCompat;", "(Landroidx/core/view/WindowInsetsAnimationCompat;)Z", "react-native-keyboard-controller_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: WindowInsetsAnimationCompat.kt */
public final class WindowInsetsAnimationCompatKt {
    public static final boolean isKeyboardAnimation(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        Intrinsics.checkNotNullParameter(windowInsetsAnimationCompat, "<this>");
        return (windowInsetsAnimationCompat.getTypeMask() & WindowInsetsCompat.Type.ime()) != 0;
    }
}
