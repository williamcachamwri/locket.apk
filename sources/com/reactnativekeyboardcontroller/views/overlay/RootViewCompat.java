package com.reactnativekeyboardcontroller.views.overlay;

import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.uimanager.RootView;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017¨\u0006\u0006"}, d2 = {"Lcom/reactnativekeyboardcontroller/views/overlay/RootViewCompat;", "Lcom/facebook/react/uimanager/RootView;", "onChildStartedNativeGesture", "", "ev", "Landroid/view/MotionEvent;", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RootViewCompat.kt */
public interface RootViewCompat extends RootView {
    @Deprecated(message = "This method shouldn't be used anymore.", replaceWith = @ReplaceWith(expression = "onChildStartedNativeGesture(View childView, MotionEvent ev)", imports = {}))
    void onChildStartedNativeGesture(MotionEvent motionEvent);

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RootViewCompat.kt */
    public static final class DefaultImpls {
        @Deprecated(message = "This method shouldn't be used anymore.", replaceWith = @ReplaceWith(expression = "onChildStartedNativeGesture(View childView, MotionEvent ev)", imports = {}))
        public static void onChildStartedNativeGesture(RootViewCompat rootViewCompat, MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, "ev");
            rootViewCompat.onChildStartedNativeGesture((View) null, motionEvent);
        }
    }
}
