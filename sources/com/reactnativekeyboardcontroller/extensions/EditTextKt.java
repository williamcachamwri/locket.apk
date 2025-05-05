package com.reactnativekeyboardcontroller.extensions;

import android.text.TextWatcher;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import com.facebook.hermes.intl.Constants;
import com.facebook.react.views.scroll.ReactScrollView;
import com.facebook.react.views.textinput.ReactEditText;
import com.reactnativekeyboardcontroller.log.Logger;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0001\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n*\u00020\u00022\u0001\u0010\f\u001a\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u000b0\r\u001a\u001e\u0010\u0017\u001a\u00020\u0018*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b0\u0019\u001a\f\u0010\u001a\u001a\u00020\u000b*\u0004\u0018\u00010\u0002\"\u0017\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u001b"}, d2 = {"keyboardType", "", "Landroid/widget/EditText;", "getKeyboardType", "(Landroid/widget/EditText;)Ljava/lang/String;", "parentScrollViewTarget", "", "getParentScrollViewTarget", "(Landroid/widget/EditText;)I", "addOnSelectionChangedListener", "Lkotlin/Function0;", "", "action", "Lkotlin/Function6;", "Lkotlin/ParameterName;", "name", "start", "end", "", "startX", "startY", "endX", "endY", "addOnTextChangedListener", "Landroid/text/TextWatcher;", "Lkotlin/Function1;", "focus", "react-native-keyboard-controller_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: EditText.kt */
public final class EditTextKt {
    public static final TextWatcher addOnTextChangedListener(EditText editText, Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(editText, "<this>");
        Intrinsics.checkNotNullParameter(function1, "action");
        EditTextKt$addOnTextChangedListener$listener$1 editTextKt$addOnTextChangedListener$listener$1 = new EditTextKt$addOnTextChangedListener$listener$1(new Ref.ObjectRef(), function1);
        try {
            Field declaredField = ReactEditText.class.getDeclaredField("mListeners");
            Intrinsics.checkNotNullExpressionValue(declaredField, "getDeclaredField(...)");
            boolean z = true;
            declaredField.setAccessible(true);
            Object obj = declaredField.get(editText);
            ArrayList arrayList = obj instanceof ArrayList ? (ArrayList) obj : null;
            if (arrayList != null) {
                Iterable iterable = arrayList;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    Iterator it = iterable.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!(it.next() instanceof TextWatcher)) {
                                z = false;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                if (z) {
                    arrayList.add(0, editTextKt$addOnTextChangedListener$listener$1);
                    return editTextKt$addOnTextChangedListener$listener$1;
                }
            }
            Logger.w$default(Logger.INSTANCE, editText.getClass().getSimpleName(), "Can not attach listener because `fieldValue` does not belong to `ArrayList<TextWatcher>`", (Throwable) null, 4, (Object) null);
        } catch (ClassCastException e) {
            Logger.w$default(Logger.INSTANCE, editText.getClass().getSimpleName(), "Can not attach listener because casting failed: " + e.getMessage(), (Throwable) null, 4, (Object) null);
        } catch (NoSuchFieldException e2) {
            Logger.w$default(Logger.INSTANCE, editText.getClass().getSimpleName(), "Can not attach listener because field `mListeners` not found: " + e2.getMessage(), (Throwable) null, 4, (Object) null);
        } catch (IllegalArgumentException e3) {
            Logger.w$default(Logger.INSTANCE, editText.getClass().getSimpleName(), "Can not attach listener to be the first in the list: " + e3.getMessage() + ". Attaching to the end...", (Throwable) null, 4, (Object) null);
            editText.addTextChangedListener(editTextKt$addOnTextChangedListener$listener$1);
        }
        return editTextKt$addOnTextChangedListener$listener$1;
    }

    public static final int getParentScrollViewTarget(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<this>");
        View view = editText;
        while (view != null) {
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
            if (view instanceof ReactScrollView) {
                ReactScrollView reactScrollView = (ReactScrollView) view;
                if (reactScrollView.getScrollEnabled()) {
                    return reactScrollView.getId();
                }
            }
        }
        return -1;
    }

    public static final void focus(EditText editText) {
        if (editText instanceof ReactEditText) {
            ((ReactEditText) editText).requestFocusFromJS();
        } else if (editText != null) {
            editText.requestFocus();
        }
    }

    public static final String getKeyboardType(EditText editText) {
        if (editText == null) {
            return "default";
        }
        int inputType = editText.getInputType() & 15;
        int inputType2 = editText.getInputType() & 4080;
        if (inputType2 == 32) {
            return "email-address";
        }
        if (inputType2 == 16) {
            return "url";
        }
        if (inputType2 == 144) {
            return "visible-password";
        }
        if (inputType == 2) {
            if ((editText.getInputType() & 8192) == 0 || (editText.getInputType() & 4096) != 0) {
                return (editText.getInputType() & 4096) != 0 ? Constants.COLLATION_OPTION_NUMERIC : "number-pad";
            }
            return "decimal-pad";
        } else if (inputType == 3) {
            return "phone-pad";
        } else {
            return "default";
        }
    }

    public static final Function0<Unit> addOnSelectionChangedListener(EditText editText, Function6<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, Unit> function6) {
        Intrinsics.checkNotNullParameter(editText, "<this>");
        Intrinsics.checkNotNullParameter(function6, "action");
        KeyboardControllerSelectionWatcher keyboardControllerSelectionWatcher = new KeyboardControllerSelectionWatcher(editText, function6);
        keyboardControllerSelectionWatcher.setup();
        return new EditTextKt$addOnSelectionChangedListener$1(keyboardControllerSelectionWatcher);
    }
}
