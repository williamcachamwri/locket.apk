package com.reactnativekeyboardcontroller.extensions;

import android.view.ViewGroup;
import android.view.ViewParent;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002¨\u0006\u0003"}, d2 = {"removeSelf", "", "Landroid/view/ViewGroup;", "react-native-keyboard-controller_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ViewGroup.kt */
public final class ViewGroupKt {
    public static final void removeSelf(ViewGroup viewGroup) {
        if (viewGroup != null) {
            ViewParent parent = viewGroup.getParent();
            ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup2 != null) {
                viewGroup2.removeView(viewGroup);
            }
        }
    }
}
