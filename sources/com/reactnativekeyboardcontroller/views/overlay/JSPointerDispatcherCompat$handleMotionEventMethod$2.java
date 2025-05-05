package com.reactnativekeyboardcontroller.views.overlay;

import android.view.MotionEvent;
import com.facebook.react.uimanager.JSPointerDispatcher;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/lang/reflect/Method;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: JSPointerDispatcherCompat.kt */
final class JSPointerDispatcherCompat$handleMotionEventMethod$2 extends Lambda implements Function0<Method> {
    public static final JSPointerDispatcherCompat$handleMotionEventMethod$2 INSTANCE = new JSPointerDispatcherCompat$handleMotionEventMethod$2();

    JSPointerDispatcherCompat$handleMotionEventMethod$2() {
        super(0);
    }

    public final Method invoke() {
        try {
            return JSPointerDispatcher.class.getMethod("handleMotionEvent", new Class[]{MotionEvent.class, EventDispatcher.class, Boolean.TYPE});
        } catch (NoSuchMethodException unused) {
            try {
                return JSPointerDispatcher.class.getMethod("handleMotionEvent", new Class[]{MotionEvent.class, EventDispatcher.class});
            } catch (NoSuchMethodException unused2) {
                return null;
            }
        }
    }
}
