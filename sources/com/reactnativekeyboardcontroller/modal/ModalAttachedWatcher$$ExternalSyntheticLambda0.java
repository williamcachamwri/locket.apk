package com.reactnativekeyboardcontroller.modal;

import android.content.DialogInterface;
import com.facebook.react.views.view.ReactViewGroup;
import com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallback;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ModalAttachedWatcher$$ExternalSyntheticLambda0 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ KeyboardAnimationCallback f$0;
    public final /* synthetic */ ReactViewGroup f$1;
    public final /* synthetic */ ModalAttachedWatcher f$2;

    public /* synthetic */ ModalAttachedWatcher$$ExternalSyntheticLambda0(KeyboardAnimationCallback keyboardAnimationCallback, ReactViewGroup reactViewGroup, ModalAttachedWatcher modalAttachedWatcher) {
        this.f$0 = keyboardAnimationCallback;
        this.f$1 = reactViewGroup;
        this.f$2 = modalAttachedWatcher;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        ModalAttachedWatcher.onEventDispatch$lambda$1(this.f$0, this.f$1, this.f$2, dialogInterface);
    }
}
