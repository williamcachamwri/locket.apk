package com.reactnativekeyboardcontroller.interactive;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/reactnativekeyboardcontroller/interactive/InteractiveKeyboardProvider;", "", "()V", "isInteractive", "", "()Z", "setInteractive", "(Z)V", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: InteractiveKeyboardProvider.kt */
public final class InteractiveKeyboardProvider {
    public static final InteractiveKeyboardProvider INSTANCE = new InteractiveKeyboardProvider();
    private static boolean isInteractive;

    private InteractiveKeyboardProvider() {
    }

    public final boolean isInteractive() {
        return isInteractive;
    }

    public final void setInteractive(boolean z) {
        isInteractive = z;
    }
}
