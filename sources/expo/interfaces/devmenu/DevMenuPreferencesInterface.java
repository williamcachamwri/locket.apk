package expo.interfaces.devmenu;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0013\u001a\u00020\u0014H&J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\b\u0010\u0004\"\u0004\b\t\u0010\u0006R\u0018\u0010\n\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\u0004\"\u0004\b\f\u0010\u0006R\u0018\u0010\r\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u0004\"\u0004\b\u000f\u0010\u0006R\u0018\u0010\u0010\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0004\"\u0004\b\u0012\u0010\u0006¨\u0006\u0019"}, d2 = {"Lexpo/interfaces/devmenu/DevMenuPreferencesInterface;", "", "isOnboardingFinished", "", "()Z", "setOnboardingFinished", "(Z)V", "keyCommandsEnabled", "getKeyCommandsEnabled", "setKeyCommandsEnabled", "motionGestureEnabled", "getMotionGestureEnabled", "setMotionGestureEnabled", "showsAtLaunch", "getShowsAtLaunch", "setShowsAtLaunch", "touchGestureEnabled", "getTouchGestureEnabled", "setTouchGestureEnabled", "serialize", "Lcom/facebook/react/bridge/WritableMap;", "setPreferences", "", "settings", "Lcom/facebook/react/bridge/ReadableMap;", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuPreferencesInterface.kt */
public interface DevMenuPreferencesInterface {
    boolean getKeyCommandsEnabled();

    boolean getMotionGestureEnabled();

    boolean getShowsAtLaunch();

    boolean getTouchGestureEnabled();

    boolean isOnboardingFinished();

    WritableMap serialize();

    void setKeyCommandsEnabled(boolean z);

    void setMotionGestureEnabled(boolean z);

    void setOnboardingFinished(boolean z);

    void setPreferences(ReadableMap readableMap);

    void setShowsAtLaunch(boolean z);

    void setTouchGestureEnabled(boolean z);
}
