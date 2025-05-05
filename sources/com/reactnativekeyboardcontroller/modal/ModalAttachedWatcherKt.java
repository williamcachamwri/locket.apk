package com.reactnativekeyboardcontroller.modal;

import com.reactnativekeyboardcontroller.constants.Keyboard;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"TAG", "", "areEventsComingFromOwnWindow", "", "react-native-keyboard-controller_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModalAttachedWatcher.kt */
public final class ModalAttachedWatcherKt {
    /* access modifiers changed from: private */
    public static final String TAG = Reflection.getOrCreateKotlinClass(ModalAttachedWatcher.class).getQualifiedName();
    /* access modifiers changed from: private */
    public static final boolean areEventsComingFromOwnWindow = (!Keyboard.INSTANCE.getIS_ANIMATION_EMULATED());
}
