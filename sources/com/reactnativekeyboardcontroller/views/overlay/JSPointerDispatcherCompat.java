package com.reactnativekeyboardcontroller.views.overlay;

import android.view.MotionEvent;
import android.view.ViewGroup;
import com.facebook.react.uimanager.JSPointerDispatcher;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.lang.reflect.Method;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reactnativekeyboardcontroller/views/overlay/JSPointerDispatcherCompat;", "Lcom/facebook/react/uimanager/JSPointerDispatcher;", "viewGroup", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "handleMotionEventMethod", "Ljava/lang/reflect/Method;", "getHandleMotionEventMethod", "()Ljava/lang/reflect/Method;", "handleMotionEventMethod$delegate", "Lkotlin/Lazy;", "handleMotionEventCompat", "", "event", "Landroid/view/MotionEvent;", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "isCapture", "", "Companion", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: JSPointerDispatcherCompat.kt */
public final class JSPointerDispatcherCompat extends JSPointerDispatcher {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String HANDLE_MOTION_EVENT = "handleMotionEvent";
    private static final int RN_72_PARAMS_COUNT = 3;
    private final Lazy handleMotionEventMethod$delegate = LazyKt.lazy(JSPointerDispatcherCompat$handleMotionEventMethod$2.INSTANCE);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JSPointerDispatcherCompat(ViewGroup viewGroup) {
        super(viewGroup);
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
    }

    private final Method getHandleMotionEventMethod() {
        return (Method) this.handleMotionEventMethod$delegate.getValue();
    }

    public final void handleMotionEventCompat(MotionEvent motionEvent, EventDispatcher eventDispatcher, boolean z) {
        Method handleMotionEventMethod = getHandleMotionEventMethod();
        if (handleMotionEventMethod == null) {
            return;
        }
        if (handleMotionEventMethod.getParameterCount() == 3) {
            handleMotionEventMethod.invoke(this, new Object[]{motionEvent, eventDispatcher, Boolean.valueOf(z)});
        } else {
            handleMotionEventMethod.invoke(this, new Object[]{motionEvent, eventDispatcher});
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/reactnativekeyboardcontroller/views/overlay/JSPointerDispatcherCompat$Companion;", "", "()V", "HANDLE_MOTION_EVENT", "", "RN_72_PARAMS_COUNT", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: JSPointerDispatcherCompat.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
