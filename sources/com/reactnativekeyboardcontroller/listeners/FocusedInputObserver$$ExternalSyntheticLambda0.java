package com.reactnativekeyboardcontroller.listeners;

import android.text.TextWatcher;
import android.widget.EditText;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FocusedInputObserver$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ EditText f$0;
    public final /* synthetic */ TextWatcher f$1;

    public /* synthetic */ FocusedInputObserver$$ExternalSyntheticLambda0(EditText editText, TextWatcher textWatcher) {
        this.f$0 = editText;
        this.f$1 = textWatcher;
    }

    public final void run() {
        FocusedInputObserver.focusListener$lambda$4$lambda$2$lambda$1(this.f$0, this.f$1);
    }
}
