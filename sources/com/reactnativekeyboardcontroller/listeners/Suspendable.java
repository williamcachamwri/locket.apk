package com.reactnativekeyboardcontroller.listeners;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u0016R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/reactnativekeyboardcontroller/listeners/Suspendable;", "", "isSuspended", "", "()Z", "setSuspended", "(Z)V", "suspend", "", "suspended", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardAnimationCallback.kt */
public interface Suspendable {
    boolean isSuspended();

    void setSuspended(boolean z);

    void suspend(boolean z);

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: KeyboardAnimationCallback.kt */
    public static final class DefaultImpls {
        public static void suspend(Suspendable suspendable, boolean z) {
            suspendable.setSuspended(z);
        }
    }
}
