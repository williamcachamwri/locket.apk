package com.reactnativekeyboardcontroller.extensions;

import android.view.ViewTreeObserver;
import android.widget.EditText;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0001\u0010\u0004\u001a\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\u0002\u0010\u0011J\u0006\u0010\u0017\u001a\u00020\u0010J\u0006\u0010\u0018\u001a\u00020\u0010R\u0001\u0010\u0004\u001a\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reactnativekeyboardcontroller/extensions/KeyboardControllerSelectionWatcher;", "", "editText", "Landroid/widget/EditText;", "action", "Lkotlin/Function6;", "", "Lkotlin/ParameterName;", "name", "start", "end", "", "startX", "startY", "endX", "endY", "", "(Landroid/widget/EditText;Lkotlin/jvm/functions/Function6;)V", "lastEditTextHeight", "lastSelectionEnd", "lastSelectionStart", "preDrawListener", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "destroy", "setup", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EditText.kt */
public final class KeyboardControllerSelectionWatcher {
    /* access modifiers changed from: private */
    public final Function6<Integer, Integer, Double, Double, Double, Double, Unit> action;
    /* access modifiers changed from: private */
    public final EditText editText;
    /* access modifiers changed from: private */
    public int lastEditTextHeight = -1;
    /* access modifiers changed from: private */
    public int lastSelectionEnd = -1;
    /* access modifiers changed from: private */
    public int lastSelectionStart = -1;
    private final ViewTreeObserver.OnPreDrawListener preDrawListener = new KeyboardControllerSelectionWatcher$preDrawListener$1(this);

    public KeyboardControllerSelectionWatcher(EditText editText2, Function6<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, Unit> function6) {
        Intrinsics.checkNotNullParameter(editText2, "editText");
        Intrinsics.checkNotNullParameter(function6, "action");
        this.editText = editText2;
        this.action = function6;
    }

    public final void setup() {
        this.editText.getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
    }

    public final void destroy() {
        this.editText.getViewTreeObserver().removeOnPreDrawListener(this.preDrawListener);
    }
}
