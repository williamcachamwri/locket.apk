package com.reactnativekeyboardcontroller.views.overlay;

import android.view.View;
import android.view.WindowManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.view.ReactViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\u0006\u0010\u0013\u001a\u00020\fJ\b\u0010\u0014\u001a\u00020\fH\u0014J0\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0010H\u0014J\u0012\u0010\u001c\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u001d\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0006\u0010\u001e\u001a\u00020\fR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/reactnativekeyboardcontroller/views/overlay/OverKeyboardHostView;", "Lcom/facebook/react/views/view/ReactViewGroup;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "(Lcom/facebook/react/uimanager/ThemedReactContext;)V", "dispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "hostView", "Lcom/reactnativekeyboardcontroller/views/overlay/OverKeyboardRootViewGroup;", "windowManager", "Landroid/view/WindowManager;", "addView", "", "child", "Landroid/view/View;", "index", "", "getChildAt", "getChildCount", "hide", "onDetachedFromWindow", "onLayout", "changed", "", "l", "t", "r", "b", "removeView", "removeViewAt", "show", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OverKeyboardViewGroup.kt */
public final class OverKeyboardHostView extends ReactViewGroup {
    private final EventDispatcher dispatcher;
    private OverKeyboardRootViewGroup hostView;
    private final ThemedReactContext reactContext;
    private WindowManager windowManager;

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OverKeyboardHostView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        this.reactContext = themedReactContext;
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, getId());
        this.dispatcher = eventDispatcherForReactTag;
        Object systemService = themedReactContext.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        this.windowManager = (WindowManager) systemService;
        OverKeyboardRootViewGroup overKeyboardRootViewGroup = new OverKeyboardRootViewGroup(themedReactContext);
        this.hostView = overKeyboardRootViewGroup;
        overKeyboardRootViewGroup.setEventDispatcher$react_native_keyboard_controller_release(eventDispatcherForReactTag);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        hide();
    }

    public void addView(View view, int i) {
        UiThreadUtil.assertOnUiThread();
        this.hostView.addView(view, i);
    }

    public int getChildCount() {
        return this.hostView.getChildCount();
    }

    public View getChildAt(int i) {
        return this.hostView.getChildAt(i);
    }

    public void removeView(View view) {
        UiThreadUtil.assertOnUiThread();
        if (view != null) {
            this.hostView.removeView(view);
        }
    }

    public void removeViewAt(int i) {
        UiThreadUtil.assertOnUiThread();
        this.hostView.removeView(getChildAt(i));
    }

    public final void show() {
        this.windowManager.addView(this.hostView, new WindowManager.LayoutParams(-1, -1, 1000, 520, -3));
    }

    public final void hide() {
        if (this.hostView.isAttached$react_native_keyboard_controller_release()) {
            this.windowManager.removeView(this.hostView);
        }
    }
}
