package com.reactnativekeyboardcontroller.listeners;

import com.reactnativekeyboardcontroller.events.FocusedInputLayoutChangedEventData;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"noFocusedInputEvent", "Lcom/reactnativekeyboardcontroller/events/FocusedInputLayoutChangedEventData;", "getNoFocusedInputEvent", "()Lcom/reactnativekeyboardcontroller/events/FocusedInputLayoutChangedEventData;", "react-native-keyboard-controller_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: FocusedInputObserver.kt */
public final class FocusedInputObserverKt {
    private static final FocusedInputLayoutChangedEventData noFocusedInputEvent = new FocusedInputLayoutChangedEventData(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -1, -1);

    public static final FocusedInputLayoutChangedEventData getNoFocusedInputEvent() {
        return noFocusedInputEvent;
    }
}
