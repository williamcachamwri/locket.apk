package com.reactnativekeyboardcontroller.extensions;

import android.text.Editable;
import android.text.TextWatcher;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/reactnativekeyboardcontroller/extensions/EditTextKt$addOnTextChangedListener$listener$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EditText.kt */
public final class EditTextKt$addOnTextChangedListener$listener$1 implements TextWatcher {
    final /* synthetic */ Function1<String, Unit> $action;
    final /* synthetic */ Ref.ObjectRef<String> $lastText;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    EditTextKt$addOnTextChangedListener$listener$1(Ref.ObjectRef<String> objectRef, Function1<? super String, Unit> function1) {
        this.$lastText = objectRef;
        this.$action = function1;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        T valueOf = String.valueOf(charSequence);
        if (!Intrinsics.areEqual((Object) valueOf, (Object) this.$lastText.element)) {
            this.$lastText.element = valueOf;
            this.$action.invoke(valueOf);
        }
    }
}
