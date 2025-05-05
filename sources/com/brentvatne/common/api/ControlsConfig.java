package com.brentvatne.common.api;

import com.brentvatne.common.toolbox.ReactBridgeUtils;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b&\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 62\u00020\u0001:\u00016B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001a\u0010$\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\bR\u001a\u0010'\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001c\u0010*\u001a\u0004\u0018\u00010+X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00100\u001a\u000201X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105¨\u00067"}, d2 = {"Lcom/brentvatne/common/api/ControlsConfig;", "", "()V", "hideDuration", "", "getHideDuration", "()Z", "setHideDuration", "(Z)V", "hideForward", "getHideForward", "setHideForward", "hideFullscreen", "getHideFullscreen", "setHideFullscreen", "hideNavigationBarOnFullScreenMode", "getHideNavigationBarOnFullScreenMode", "setHideNavigationBarOnFullScreenMode", "hideNext", "getHideNext", "setHideNext", "hideNotificationBarOnFullScreenMode", "getHideNotificationBarOnFullScreenMode", "setHideNotificationBarOnFullScreenMode", "hidePlayPause", "getHidePlayPause", "setHidePlayPause", "hidePosition", "getHidePosition", "setHidePosition", "hidePrevious", "getHidePrevious", "setHidePrevious", "hideRewind", "getHideRewind", "setHideRewind", "hideSeekBar", "getHideSeekBar", "setHideSeekBar", "hideSettingButton", "getHideSettingButton", "setHideSettingButton", "liveLabel", "", "getLiveLabel", "()Ljava/lang/String;", "setLiveLabel", "(Ljava/lang/String;)V", "seekIncrementMS", "", "getSeekIncrementMS", "()I", "setSeekIncrementMS", "(I)V", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ControlsConfig.kt */
public final class ControlsConfig {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean hideDuration;
    private boolean hideForward;
    private boolean hideFullscreen;
    private boolean hideNavigationBarOnFullScreenMode = true;
    private boolean hideNext;
    private boolean hideNotificationBarOnFullScreenMode = true;
    private boolean hidePlayPause;
    private boolean hidePosition;
    private boolean hidePrevious;
    private boolean hideRewind;
    private boolean hideSeekBar;
    private boolean hideSettingButton = true;
    private String liveLabel;
    private int seekIncrementMS = 10000;

    @JvmStatic
    public static final ControlsConfig parse(ReadableMap readableMap) {
        return Companion.parse(readableMap);
    }

    public final boolean getHideSeekBar() {
        return this.hideSeekBar;
    }

    public final void setHideSeekBar(boolean z) {
        this.hideSeekBar = z;
    }

    public final boolean getHideDuration() {
        return this.hideDuration;
    }

    public final void setHideDuration(boolean z) {
        this.hideDuration = z;
    }

    public final boolean getHidePosition() {
        return this.hidePosition;
    }

    public final void setHidePosition(boolean z) {
        this.hidePosition = z;
    }

    public final boolean getHidePlayPause() {
        return this.hidePlayPause;
    }

    public final void setHidePlayPause(boolean z) {
        this.hidePlayPause = z;
    }

    public final boolean getHideForward() {
        return this.hideForward;
    }

    public final void setHideForward(boolean z) {
        this.hideForward = z;
    }

    public final boolean getHideRewind() {
        return this.hideRewind;
    }

    public final void setHideRewind(boolean z) {
        this.hideRewind = z;
    }

    public final boolean getHideNext() {
        return this.hideNext;
    }

    public final void setHideNext(boolean z) {
        this.hideNext = z;
    }

    public final boolean getHidePrevious() {
        return this.hidePrevious;
    }

    public final void setHidePrevious(boolean z) {
        this.hidePrevious = z;
    }

    public final boolean getHideFullscreen() {
        return this.hideFullscreen;
    }

    public final void setHideFullscreen(boolean z) {
        this.hideFullscreen = z;
    }

    public final boolean getHideNavigationBarOnFullScreenMode() {
        return this.hideNavigationBarOnFullScreenMode;
    }

    public final void setHideNavigationBarOnFullScreenMode(boolean z) {
        this.hideNavigationBarOnFullScreenMode = z;
    }

    public final boolean getHideNotificationBarOnFullScreenMode() {
        return this.hideNotificationBarOnFullScreenMode;
    }

    public final void setHideNotificationBarOnFullScreenMode(boolean z) {
        this.hideNotificationBarOnFullScreenMode = z;
    }

    public final String getLiveLabel() {
        return this.liveLabel;
    }

    public final void setLiveLabel(String str) {
        this.liveLabel = str;
    }

    public final boolean getHideSettingButton() {
        return this.hideSettingButton;
    }

    public final void setHideSettingButton(boolean z) {
        this.hideSettingButton = z;
    }

    public final int getSeekIncrementMS() {
        return this.seekIncrementMS;
    }

    public final void setSeekIncrementMS(int i) {
        this.seekIncrementMS = i;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/brentvatne/common/api/ControlsConfig$Companion;", "", "()V", "parse", "Lcom/brentvatne/common/api/ControlsConfig;", "controlsConfig", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ControlsConfig.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ControlsConfig parse(ReadableMap readableMap) {
            ControlsConfig controlsConfig = new ControlsConfig();
            if (readableMap != null) {
                controlsConfig.setHideSeekBar(ReactBridgeUtils.safeGetBool(readableMap, "hideSeekBar", false));
                controlsConfig.setHideDuration(ReactBridgeUtils.safeGetBool(readableMap, "hideDuration", false));
                controlsConfig.setHidePosition(ReactBridgeUtils.safeGetBool(readableMap, "hidePosition", false));
                controlsConfig.setHidePlayPause(ReactBridgeUtils.safeGetBool(readableMap, "hidePlayPause", false));
                controlsConfig.setHideForward(ReactBridgeUtils.safeGetBool(readableMap, "hideForward", false));
                controlsConfig.setHideRewind(ReactBridgeUtils.safeGetBool(readableMap, "hideRewind", false));
                controlsConfig.setHideNext(ReactBridgeUtils.safeGetBool(readableMap, "hideNext", false));
                controlsConfig.setHidePrevious(ReactBridgeUtils.safeGetBool(readableMap, "hidePrevious", false));
                controlsConfig.setHideFullscreen(ReactBridgeUtils.safeGetBool(readableMap, "hideFullscreen", false));
                controlsConfig.setSeekIncrementMS(ReactBridgeUtils.safeGetInt(readableMap, "seekIncrementMS", 10000));
                controlsConfig.setHideNavigationBarOnFullScreenMode(ReactBridgeUtils.safeGetBool(readableMap, "hideNavigationBarOnFullScreenMode", true));
                controlsConfig.setHideNotificationBarOnFullScreenMode(ReactBridgeUtils.safeGetBool(readableMap, "hideNotificationBarOnFullScreenMode", true));
                controlsConfig.setLiveLabel(ReactBridgeUtils.safeGetString(readableMap, "liveLabel", (String) null));
                controlsConfig.setHideSettingButton(ReactBridgeUtils.safeGetBool(readableMap, "hideSettingButton", true));
            }
            return controlsConfig;
        }
    }
}
