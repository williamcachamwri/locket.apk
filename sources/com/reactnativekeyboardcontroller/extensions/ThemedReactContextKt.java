package com.reactnativekeyboardcontroller.extensions;

import android.app.UiModeManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.reactnativekeyboardcontroller.listeners.WindowDimensionListener;
import com.reactnativekeyboardcontroller.log.Logger;
import expo.modules.devlauncher.launcher.manifest.DevLauncherUserInterface;
import io.sentry.protocol.Message;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a \u0010\t\u001a\u00020\n*\u0004\u0018\u00010\u00022\u0006\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e\u001a\u001c\u0010\u000f\u001a\u00020\n*\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011\u001a\n\u0010\u0012\u001a\u00020\n*\u00020\u0002\"\u0017\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0013"}, d2 = {"appearance", "", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getAppearance", "(Lcom/facebook/react/uimanager/ThemedReactContext;)Ljava/lang/String;", "isSystemDarkMode", "", "context", "Landroid/content/Context;", "dispatchEvent", "", "viewId", "", "event", "Lcom/facebook/react/uimanager/events/Event;", "emitEvent", "params", "Lcom/facebook/react/bridge/WritableMap;", "setupWindowDimensionsListener", "react-native-keyboard-controller_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ThemedReactContext.kt */
public final class ThemedReactContextKt {
    public static final void setupWindowDimensionsListener(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "<this>");
        new WindowDimensionListener(themedReactContext);
    }

    public static final void dispatchEvent(ThemedReactContext themedReactContext, int i, Event<?> event) {
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        Intrinsics.checkNotNull(themedReactContext, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, i);
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(event);
        }
    }

    public static final void emitEvent(ThemedReactContext themedReactContext, String str, WritableMap writableMap) {
        ReactApplicationContext reactApplicationContext;
        DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter;
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_EVENT);
        Intrinsics.checkNotNullParameter(writableMap, Message.JsonKeys.PARAMS);
        if (!(themedReactContext == null || (reactApplicationContext = themedReactContext.getReactApplicationContext()) == null || (rCTDeviceEventEmitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)) == null)) {
            rCTDeviceEventEmitter.emit(str, writableMap);
        }
        Logger.i$default(Logger.INSTANCE, "ThemedReactContext", str, (Throwable) null, 4, (Object) null);
    }

    public static final String getAppearance(ThemedReactContext themedReactContext) {
        if (themedReactContext == null) {
            return "default";
        }
        return isSystemDarkMode(themedReactContext) ? DevLauncherUserInterface.DARK : DevLauncherUserInterface.LIGHT;
    }

    private static final boolean isSystemDarkMode(Context context) {
        if (Build.VERSION.SDK_INT < 29) {
            return false;
        }
        Object systemService = context.getSystemService("uimode");
        UiModeManager uiModeManager = systemService instanceof UiModeManager ? (UiModeManager) systemService : null;
        if (uiModeManager == null || uiModeManager.getNightMode() != 2) {
            return false;
        }
        return true;
    }
}
