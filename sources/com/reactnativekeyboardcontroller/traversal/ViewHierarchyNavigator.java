package com.reactnativekeyboardcontroller.traversal;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import com.facebook.react.bridge.UiThreadUtil;
import com.reactnativekeyboardcontroller.extensions.EditTextKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00112\b\u0010\n\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006H\u0002J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0006¨\u0006\u0018"}, d2 = {"Lcom/reactnativekeyboardcontroller/traversal/ViewHierarchyNavigator;", "", "()V", "findEditTextInDirection", "Landroid/widget/EditText;", "currentFocus", "Landroid/view/View;", "direction", "", "findEditTextInHierarchy", "viewGroup", "Landroid/view/ViewGroup;", "findEditTextOrGoDeeper", "child", "findNextEditText", "findPreviousEditText", "getAllInputFields", "", "isValidTextInput", "", "view", "setFocusTo", "", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ViewHierarchyNavigator.kt */
public final class ViewHierarchyNavigator {
    public static final ViewHierarchyNavigator INSTANCE = new ViewHierarchyNavigator();

    private ViewHierarchyNavigator() {
    }

    public final void setFocusTo(String str, View view) {
        Intrinsics.checkNotNullParameter(str, "direction");
        Intrinsics.checkNotNullParameter(view, "view");
        UiThreadUtil.runOnUiThread(new ViewHierarchyNavigator$$ExternalSyntheticLambda0(Intrinsics.areEqual((Object) str, (Object) "next") ? findNextEditText(view) : findPreviousEditText(view)));
    }

    /* access modifiers changed from: private */
    public static final void setFocusTo$lambda$0(EditText editText) {
        EditTextKt.focus(editText);
    }

    public final List<EditText> getAllInputFields(View view) {
        List<EditText> arrayList = new ArrayList<>();
        getAllInputFields$findEditTexts(arrayList, view);
        return arrayList;
    }

    private static final void getAllInputFields$findEditTexts(List<EditText> list, View view) {
        if (INSTANCE.isValidTextInput(view)) {
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.EditText");
            list.add((EditText) view);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                getAllInputFields$findEditTexts(list, viewGroup.getChildAt(i));
            }
        }
    }

    private final EditText findNextEditText(View view) {
        return findEditTextInDirection(view, 1);
    }

    private final EditText findPreviousEditText(View view) {
        return findEditTextInDirection(view, -1);
    }

    private final EditText findEditTextInDirection(View view, int i) {
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null) {
            return null;
        }
        int indexOfChild = viewGroup.indexOfChild(view);
        int i2 = i > 0 ? indexOfChild + 1 : indexOfChild - 1;
        int childCount = i > 0 ? viewGroup.getChildCount() : -1;
        while (i2 != childCount) {
            View childAt = viewGroup.getChildAt(i2);
            Intrinsics.checkNotNull(childAt);
            EditText findEditTextOrGoDeeper = findEditTextOrGoDeeper(childAt, i);
            if (findEditTextOrGoDeeper != null) {
                return findEditTextOrGoDeeper;
            }
            i2 += i;
        }
        return findEditTextInDirection(viewGroup, i);
    }

    private final EditText findEditTextInHierarchy(ViewGroup viewGroup, int i) {
        int childCount = viewGroup.getChildCount();
        IntProgression until = i > 0 ? RangesKt.until(0, childCount) : RangesKt.downTo(childCount - 1, 0);
        int first = until.getFirst();
        int last = until.getLast();
        int step = until.getStep();
        if ((step <= 0 || first > last) && (step >= 0 || last > first)) {
            return null;
        }
        while (true) {
            View childAt = viewGroup.getChildAt(first);
            Intrinsics.checkNotNull(childAt);
            EditText findEditTextOrGoDeeper = findEditTextOrGoDeeper(childAt, i);
            if (findEditTextOrGoDeeper != null) {
                return findEditTextOrGoDeeper;
            }
            if (first == last) {
                return null;
            }
            first += step;
        }
    }

    private final EditText findEditTextOrGoDeeper(View view, int i) {
        if (isValidTextInput(view)) {
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.EditText");
            return (EditText) view;
        } else if (view instanceof ViewGroup) {
            return findEditTextInHierarchy((ViewGroup) view, i);
        } else {
            return null;
        }
    }

    private final boolean isValidTextInput(View view) {
        return (view instanceof EditText) && ((EditText) view).isEnabled();
    }
}
