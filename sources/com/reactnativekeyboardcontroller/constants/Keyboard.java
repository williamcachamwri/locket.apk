package com.reactnativekeyboardcontroller.constants;

import android.os.Build;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/reactnativekeyboardcontroller/constants/Keyboard;", "", "()V", "IS_ANIMATION_EMULATED", "", "getIS_ANIMATION_EMULATED", "()Z", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Keyboard.kt */
public final class Keyboard {
    public static final Keyboard INSTANCE = new Keyboard();
    private static final boolean IS_ANIMATION_EMULATED = (Build.VERSION.SDK_INT < 30);

    private Keyboard() {
    }

    public final boolean getIS_ANIMATION_EMULATED() {
        return IS_ANIMATION_EMULATED;
    }
}
