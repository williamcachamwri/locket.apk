package expo.modules.devmenu.modules;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import expo.interfaces.devmenu.DevMenuPreferencesInterface;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&H\u0016R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068V@VX\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068V@VX\u000e¢\u0006\f\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR$\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068V@VX\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR#\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u00128BX\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R$\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068V@VX\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\b\"\u0004\b\u001a\u0010\nR$\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068V@VX\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\b\"\u0004\b\u001d\u0010\n¨\u0006'"}, d2 = {"Lexpo/modules/devmenu/modules/DevMenuPreferencesHandle;", "Lexpo/interfaces/devmenu/DevMenuPreferencesInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "isOnboardingFinished", "()Z", "setOnboardingFinished", "(Z)V", "keyCommandsEnabled", "getKeyCommandsEnabled", "setKeyCommandsEnabled", "motionGestureEnabled", "getMotionGestureEnabled", "setMotionGestureEnabled", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "sharedPreferences$delegate", "Lkotlin/Lazy;", "showsAtLaunch", "getShowsAtLaunch", "setShowsAtLaunch", "touchGestureEnabled", "getTouchGestureEnabled", "setTouchGestureEnabled", "saveBoolean", "", "key", "", "serialize", "Lcom/facebook/react/bridge/WritableMap;", "setPreferences", "settings", "Lcom/facebook/react/bridge/ReadableMap;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuPreferences.kt */
public final class DevMenuPreferencesHandle implements DevMenuPreferencesInterface {
    private final Lazy sharedPreferences$delegate;

    public DevMenuPreferencesHandle(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.sharedPreferences$delegate = LazyKt.lazy(new DevMenuPreferencesHandle$sharedPreferences$2(context));
    }

    private final SharedPreferences getSharedPreferences() {
        return (SharedPreferences) this.sharedPreferences$delegate.getValue();
    }

    public boolean getMotionGestureEnabled() {
        return getSharedPreferences().getBoolean("motionGestureEnabled", true);
    }

    public void setMotionGestureEnabled(boolean z) {
        saveBoolean("motionGestureEnabled", z);
    }

    public boolean getTouchGestureEnabled() {
        return getSharedPreferences().getBoolean("touchGestureEnabled", true);
    }

    public void setTouchGestureEnabled(boolean z) {
        saveBoolean("touchGestureEnabled", z);
    }

    public boolean getKeyCommandsEnabled() {
        return getSharedPreferences().getBoolean("keyCommandsEnabled", true);
    }

    public void setKeyCommandsEnabled(boolean z) {
        saveBoolean("keyCommandsEnabled", z);
    }

    public boolean getShowsAtLaunch() {
        return getSharedPreferences().getBoolean("showsAtLaunch", false);
    }

    public void setShowsAtLaunch(boolean z) {
        saveBoolean("showsAtLaunch", z);
    }

    public boolean isOnboardingFinished() {
        return getSharedPreferences().getBoolean("isOnboardingFinished", false);
    }

    public void setOnboardingFinished(boolean z) {
        saveBoolean("isOnboardingFinished", z);
    }

    public WritableMap serialize() {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("motionGestureEnabled", getMotionGestureEnabled());
        createMap.putBoolean("touchGestureEnabled", getTouchGestureEnabled());
        createMap.putBoolean("keyCommandsEnabled", getKeyCommandsEnabled());
        createMap.putBoolean("showsAtLaunch", getShowsAtLaunch());
        createMap.putBoolean("isOnboardingFinished", isOnboardingFinished());
        Intrinsics.checkNotNullExpressionValue(createMap, "apply(...)");
        return createMap;
    }

    private final void saveBoolean(String str, boolean z) {
        getSharedPreferences().edit().putBoolean(str, z).apply();
    }

    public void setPreferences(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "settings");
        if (readableMap.hasKey("motionGestureEnabled")) {
            setMotionGestureEnabled(readableMap.getBoolean("motionGestureEnabled"));
        }
        if (readableMap.hasKey("keyCommandsEnabled")) {
            setKeyCommandsEnabled(readableMap.getBoolean("keyCommandsEnabled"));
        }
        if (readableMap.hasKey("showsAtLaunch")) {
            setShowsAtLaunch(readableMap.getBoolean("showsAtLaunch"));
        }
        if (readableMap.hasKey("touchGestureEnabled")) {
            setTouchGestureEnabled(readableMap.getBoolean("touchGestureEnabled"));
        }
    }
}
