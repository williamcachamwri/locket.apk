package com.reactnativekeyboardcontroller.extensions;

import android.graphics.Rect;
import android.view.View;
import com.reactnativekeyboardcontroller.log.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0006\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0007\u001a\n\u0010\n\u001a\u00020\u0007*\u00020\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000b"}, d2 = {"tmpIntArr", "", "screenLocation", "Landroid/view/View;", "getScreenLocation", "(Landroid/view/View;)[I", "copyBoundsInWindow", "", "rect", "Landroid/graphics/Rect;", "requestApplyInsetsWhenAttached", "react-native-keyboard-controller_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: View.kt */
public final class ViewKt {
    private static final int[] tmpIntArr = new int[2];

    public static final void requestApplyInsetsWhenAttached(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (view.isAttachedToWindow()) {
            view.requestApplyInsets();
        } else {
            view.addOnAttachStateChangeListener(new ViewKt$requestApplyInsetsWhenAttached$1());
        }
    }

    public static final void copyBoundsInWindow(View view, Rect rect) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(rect, "rect");
        if (view.isAttachedToWindow()) {
            rect.set(0, 0, view.getWidth(), view.getHeight());
            int[] iArr = tmpIntArr;
            view.getLocationInWindow(iArr);
            rect.offset(iArr[0], iArr[1]);
            return;
        }
        Logger.w$default(Logger.INSTANCE, "View.copyBoundsInWindow", "Can not copy bounds as view is not attached to window", (Throwable) null, 4, (Object) null);
    }

    public static final int[] getScreenLocation(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr;
    }
}
