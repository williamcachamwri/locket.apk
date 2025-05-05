package com.brentvatne.exoplayer;

import android.content.Context;
import com.brentvatne.common.api.BufferingStrategy;
import com.brentvatne.common.api.ControlsConfig;
import com.brentvatne.common.api.Source;
import com.brentvatne.common.api.SubtitleStyle;
import com.brentvatne.common.react.EventTypes;
import com.brentvatne.common.toolbox.DebugLog;
import com.brentvatne.common.toolbox.ReactBridgeUtils;
import com.brentvatne.react.ReactNativeVideoManager;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 S2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001SB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0014J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\tH\u0014J\u0014\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0002H\u0016J\u0018\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u000fH\u0007J\u0018\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u000fH\u0007J\u0018\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J\u001a\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007J\u001a\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\b\u0010\u001f\u001a\u0004\u0018\u00010\u001dH\u0007J\u0018\u0010 \u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u001aH\u0007J\u0018\u0010\"\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\u001aH\u0007J\u0018\u0010$\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u001aH\u0007J\u0018\u0010&\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010'\u001a\u00020\u001aH\u0007J\u0018\u0010(\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u001aH\u0007J\u0018\u0010*\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010+\u001a\u00020\u001aH\u0007J\u0018\u0010,\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010-\u001a\u00020.H\u0007J\u0018\u0010/\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u00100\u001a\u00020\u001aH\u0007J\u0018\u00101\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u00102\u001a\u00020\u001aH\u0007J\u0018\u00103\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u00104\u001a\u00020\u001aH\u0007J\u0018\u00105\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u00106\u001a\u00020\u001aH\u0007J\u0018\u00107\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u00108\u001a\u00020.H\u0007J\u0018\u00109\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010:\u001a\u00020.H\u0007J\u0018\u0010;\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010<\u001a\u00020\u001aH\u0007J\u0018\u0010=\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010>\u001a\u00020\u001aH\u0007J\u0018\u0010?\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010@\u001a\u00020\u000fH\u0007J\u001a\u0010A\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\b\u0010B\u001a\u0004\u0018\u00010\u001dH\u0007J\u001a\u0010C\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\b\u0010D\u001a\u0004\u0018\u00010\u001dH\u0007J\u001a\u0010E\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\b\u0010F\u001a\u0004\u0018\u00010\u001dH\u0007J\u0018\u0010G\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010H\u001a\u00020\u001aH\u0007J\u0018\u0010I\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010J\u001a\u00020KH\u0007J\u001a\u0010L\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\b\u0010M\u001a\u0004\u0018\u00010\u001dH\u0007J\u001a\u0010N\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\b\u0010M\u001a\u0004\u0018\u00010\u001dH\u0007J\u0018\u0010O\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010P\u001a\u00020KH\u0007J\u0018\u0010Q\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010R\u001a\u00020.H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Lcom/brentvatne/exoplayer/ReactExoplayerViewManager;", "Lcom/facebook/react/uimanager/ViewGroupManager;", "Lcom/brentvatne/exoplayer/ReactExoplayerView;", "config", "Lcom/brentvatne/exoplayer/ReactExoplayerConfig;", "(Lcom/brentvatne/exoplayer/ReactExoplayerConfig;)V", "addEventEmitters", "", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "view", "createViewInstance", "themedReactContext", "getExportedCustomDirectEventTypeConstants", "", "", "", "getName", "onDropViewInstance", "setAudioOutput", "videoView", "audioOutput", "setBufferingStrategy", "bufferingStrategy", "setControls", "controls", "", "setControlsStyles", "controlsStyles", "Lcom/facebook/react/bridge/ReadableMap;", "setDebug", "debugConfig", "setDisableDisconnectError", "disableDisconnectError", "setDisableFocus", "disableFocus", "setEnterPictureInPictureOnLeave", "enterPictureInPictureOnLeave", "setFocusable", "focusable", "setFullscreen", "fullscreen", "setHideShutterView", "hideShutterView", "setMaxBitRate", "maxBitRate", "", "setMuted", "muted", "setPaused", "paused", "setPlayInBackground", "playInBackground", "setPreventsDisplaySleepDuringVideoPlayback", "preventsSleep", "setProgressUpdateInterval", "progressUpdateInterval", "setRate", "rate", "setRepeat", "repeat", "setReportBandwidth", "reportBandwidth", "setResizeMode", "resizeMode", "setSelectedAudioTrack", "selectedAudioTrack", "setSelectedTextTrack", "selectedTextTrack", "setSelectedVideoTrack", "selectedVideoTrack", "setShowNotificationControls", "showNotificationControls", "setShutterColor", "color", "", "setSrc", "src", "setSubtitleStyle", "setViewType", "viewType", "setVolume", "volume", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ReactExoplayerViewManager.kt */
public final class ReactExoplayerViewManager extends ViewGroupManager<ReactExoplayerView> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PROP_AUDIO_OUTPUT = "audioOutput";
    private static final String PROP_BUFFERING_STRATEGY = "bufferingStrategy";
    private static final String PROP_CONTROLS = "controls";
    private static final String PROP_CONTROLS_STYLES = "controlsStyles";
    private static final String PROP_DEBUG = "debug";
    private static final String PROP_DISABLE_DISCONNECT_ERROR = "disableDisconnectError";
    private static final String PROP_DISABLE_FOCUS = "disableFocus";
    private static final String PROP_ENTER_PICTURE_IN_PICTURE_ON_LEAVE = "enterPictureInPictureOnLeave";
    private static final String PROP_FOCUSABLE = "focusable";
    private static final String PROP_FULLSCREEN = "fullscreen";
    private static final String PROP_HIDE_SHUTTER_VIEW = "hideShutterView";
    private static final String PROP_MAXIMUM_BIT_RATE = "maxBitRate";
    private static final String PROP_MUTED = "muted";
    private static final String PROP_PAUSED = "paused";
    private static final String PROP_PLAY_IN_BACKGROUND = "playInBackground";
    private static final String PROP_PREVENTS_DISPLAY_SLEEP_DURING_VIDEO_PLAYBACK = "preventsDisplaySleepDuringVideoPlayback";
    private static final String PROP_PROGRESS_UPDATE_INTERVAL = "progressUpdateInterval";
    private static final String PROP_RATE = "rate";
    private static final String PROP_REPEAT = "repeat";
    private static final String PROP_REPORT_BANDWIDTH = "reportBandwidth";
    private static final String PROP_RESIZE_MODE = "resizeMode";
    private static final String PROP_SELECTED_AUDIO_TRACK = "selectedAudioTrack";
    private static final String PROP_SELECTED_AUDIO_TRACK_TYPE = "type";
    private static final String PROP_SELECTED_AUDIO_TRACK_VALUE = "value";
    private static final String PROP_SELECTED_TEXT_TRACK = "selectedTextTrack";
    private static final String PROP_SELECTED_TEXT_TRACK_TYPE = "type";
    private static final String PROP_SELECTED_TEXT_TRACK_VALUE = "value";
    private static final String PROP_SELECTED_VIDEO_TRACK = "selectedVideoTrack";
    private static final String PROP_SELECTED_VIDEO_TRACK_TYPE = "type";
    private static final String PROP_SELECTED_VIDEO_TRACK_VALUE = "value";
    private static final String PROP_SHOW_NOTIFICATION_CONTROLS = "showNotificationControls";
    private static final String PROP_SHUTTER_COLOR = "shutterColor";
    private static final String PROP_SRC = "src";
    private static final String PROP_SUBTITLE_STYLE = "subtitleStyle";
    private static final String PROP_VIEW_TYPE = "viewType";
    private static final String PROP_VOLUME = "volume";
    private static final String REACT_CLASS = "RCTVideo";
    private static final String TAG = "ExoViewManager";
    private final ReactExoplayerConfig config;

    public String getName() {
        return REACT_CLASS;
    }

    public ReactExoplayerViewManager(ReactExoplayerConfig reactExoplayerConfig) {
        Intrinsics.checkNotNullParameter(reactExoplayerConfig, "config");
        this.config = reactExoplayerConfig;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b&\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/brentvatne/exoplayer/ReactExoplayerViewManager$Companion;", "", "()V", "PROP_AUDIO_OUTPUT", "", "PROP_BUFFERING_STRATEGY", "PROP_CONTROLS", "PROP_CONTROLS_STYLES", "PROP_DEBUG", "PROP_DISABLE_DISCONNECT_ERROR", "PROP_DISABLE_FOCUS", "PROP_ENTER_PICTURE_IN_PICTURE_ON_LEAVE", "PROP_FOCUSABLE", "PROP_FULLSCREEN", "PROP_HIDE_SHUTTER_VIEW", "PROP_MAXIMUM_BIT_RATE", "PROP_MUTED", "PROP_PAUSED", "PROP_PLAY_IN_BACKGROUND", "PROP_PREVENTS_DISPLAY_SLEEP_DURING_VIDEO_PLAYBACK", "PROP_PROGRESS_UPDATE_INTERVAL", "PROP_RATE", "PROP_REPEAT", "PROP_REPORT_BANDWIDTH", "PROP_RESIZE_MODE", "PROP_SELECTED_AUDIO_TRACK", "PROP_SELECTED_AUDIO_TRACK_TYPE", "PROP_SELECTED_AUDIO_TRACK_VALUE", "PROP_SELECTED_TEXT_TRACK", "PROP_SELECTED_TEXT_TRACK_TYPE", "PROP_SELECTED_TEXT_TRACK_VALUE", "PROP_SELECTED_VIDEO_TRACK", "PROP_SELECTED_VIDEO_TRACK_TYPE", "PROP_SELECTED_VIDEO_TRACK_VALUE", "PROP_SHOW_NOTIFICATION_CONTROLS", "PROP_SHUTTER_COLOR", "PROP_SRC", "PROP_SUBTITLE_STYLE", "PROP_VIEW_TYPE", "PROP_VOLUME", "REACT_CLASS", "TAG", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ReactExoplayerViewManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* access modifiers changed from: protected */
    public ReactExoplayerView createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "themedReactContext");
        ReactNativeVideoManager.Companion.getInstance().registerView(this);
        return new ReactExoplayerView(themedReactContext, this.config);
    }

    public void onDropViewInstance(ReactExoplayerView reactExoplayerView) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "view");
        reactExoplayerView.cleanUpResources();
        reactExoplayerView.exitPictureInPictureMode();
        ReactNativeVideoManager.Companion.getInstance().unregisterView(this);
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return EventTypes.Companion.toMap();
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactExoplayerView reactExoplayerView) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        Intrinsics.checkNotNullParameter(reactExoplayerView, "view");
        super.addEventEmitters(themedReactContext, reactExoplayerView);
        reactExoplayerView.eventEmitter.addEventEmitters(themedReactContext, reactExoplayerView);
    }

    @ReactProp(name = "src")
    public final void setSrc(ReactExoplayerView reactExoplayerView, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        Context applicationContext = reactExoplayerView.getContext().getApplicationContext();
        Source.Companion companion = Source.Companion;
        Intrinsics.checkNotNull(applicationContext);
        reactExoplayerView.setSrc(companion.parse(readableMap, applicationContext));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        com.brentvatne.common.toolbox.DebugLog.w(TAG, "Unsupported resize mode: " + r5 + " - falling back to fit");
        r4.setResizeModeModifier(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001a, code lost:
        if (r5.equals("contain") == false) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0031, code lost:
        if (r5.equals("none") != false) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0033, code lost:
        r4.setResizeModeModifier(0);
     */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "resizeMode")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResizeMode(com.brentvatne.exoplayer.ReactExoplayerView r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r0 = "videoView"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "resizeMode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            int r0 = r5.hashCode()
            r1 = 0
            switch(r0) {
                case -1881872635: goto L_0x0037;
                case 3387192: goto L_0x002b;
                case 94852023: goto L_0x001d;
                case 951526612: goto L_0x0014;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x0046
        L_0x0014:
            java.lang.String r0 = "contain"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0033
            goto L_0x0046
        L_0x001d:
            java.lang.String r0 = "cover"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0026
            goto L_0x0046
        L_0x0026:
            r5 = 4
            r4.setResizeModeModifier(r5)
            goto L_0x0063
        L_0x002b:
            java.lang.String r0 = "none"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0046
        L_0x0033:
            r4.setResizeModeModifier(r1)
            goto L_0x0063
        L_0x0037:
            java.lang.String r0 = "stretch"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0041
            goto L_0x0046
        L_0x0041:
            r5 = 3
            r4.setResizeModeModifier(r5)
            goto L_0x0063
        L_0x0046:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Unsupported resize mode: "
            r0.<init>(r2)
            java.lang.StringBuilder r5 = r0.append(r5)
            java.lang.String r0 = " - falling back to fit"
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            java.lang.String r0 = "ExoViewManager"
            com.brentvatne.common.toolbox.DebugLog.w(r0, r5)
            r4.setResizeModeModifier(r1)
        L_0x0063:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brentvatne.exoplayer.ReactExoplayerViewManager.setResizeMode(com.brentvatne.exoplayer.ReactExoplayerView, java.lang.String):void");
    }

    @ReactProp(defaultBoolean = false, name = "repeat")
    public final void setRepeat(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setRepeatModifier(z);
    }

    @ReactProp(defaultBoolean = false, name = "preventsDisplaySleepDuringVideoPlayback")
    public final void setPreventsDisplaySleepDuringVideoPlayback(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setPreventsDisplaySleepDuringVideoPlayback(z);
    }

    @ReactProp(name = "selectedVideoTrack")
    public final void setSelectedVideoTrack(ReactExoplayerView reactExoplayerView, ReadableMap readableMap) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        if (readableMap != null) {
            str2 = ReactBridgeUtils.safeGetString(readableMap, "type");
            str = ReactBridgeUtils.safeGetString(readableMap, "value");
        } else {
            str2 = null;
            str = null;
        }
        reactExoplayerView.setSelectedVideoTrack(str2, str);
    }

    @ReactProp(name = "selectedAudioTrack")
    public final void setSelectedAudioTrack(ReactExoplayerView reactExoplayerView, ReadableMap readableMap) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        if (readableMap != null) {
            str2 = ReactBridgeUtils.safeGetString(readableMap, "type");
            str = ReactBridgeUtils.safeGetString(readableMap, "value");
        } else {
            str2 = null;
            str = null;
        }
        reactExoplayerView.setSelectedAudioTrack(str2, str);
    }

    @ReactProp(name = "selectedTextTrack")
    public final void setSelectedTextTrack(ReactExoplayerView reactExoplayerView, ReadableMap readableMap) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        if (readableMap != null) {
            str2 = ReactBridgeUtils.safeGetString(readableMap, "type");
            str = ReactBridgeUtils.safeGetString(readableMap, "value");
        } else {
            str2 = null;
            str = null;
        }
        reactExoplayerView.setSelectedTextTrack(str2, str);
    }

    @ReactProp(defaultBoolean = false, name = "paused")
    public final void setPaused(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setPausedModifier(z);
    }

    @ReactProp(defaultBoolean = false, name = "muted")
    public final void setMuted(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setMutedModifier(z);
    }

    @ReactProp(defaultBoolean = false, name = "enterPictureInPictureOnLeave")
    public final void setEnterPictureInPictureOnLeave(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setEnterPictureInPictureOnLeave(z);
    }

    @ReactProp(name = "audioOutput")
    public final void setAudioOutput(ReactExoplayerView reactExoplayerView, String str) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        Intrinsics.checkNotNullParameter(str, PROP_AUDIO_OUTPUT);
        reactExoplayerView.setAudioOutput(AudioOutput.Companion.get(str));
    }

    @ReactProp(defaultFloat = 1.0f, name = "volume")
    public final void setVolume(ReactExoplayerView reactExoplayerView, float f) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setVolumeModifier(f);
    }

    @ReactProp(defaultFloat = 250.0f, name = "progressUpdateInterval")
    public final void setProgressUpdateInterval(ReactExoplayerView reactExoplayerView, float f) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setProgressUpdateInterval(f);
    }

    @ReactProp(defaultBoolean = false, name = "reportBandwidth")
    public final void setReportBandwidth(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setReportBandwidth(z);
    }

    @ReactProp(name = "rate")
    public final void setRate(ReactExoplayerView reactExoplayerView, float f) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setRateModifier(f);
    }

    @ReactProp(name = "maxBitRate")
    public final void setMaxBitRate(ReactExoplayerView reactExoplayerView, float f) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setMaxBitRateModifier((int) f);
    }

    @ReactProp(defaultBoolean = false, name = "playInBackground")
    public final void setPlayInBackground(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setPlayInBackground(z);
    }

    @ReactProp(defaultBoolean = false, name = "disableFocus")
    public final void setDisableFocus(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setDisableFocus(z);
    }

    @ReactProp(defaultBoolean = true, name = "focusable")
    public final void setFocusable(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setFocusable(z);
    }

    @ReactProp(name = "bufferingStrategy")
    public final void setBufferingStrategy(ReactExoplayerView reactExoplayerView, String str) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        Intrinsics.checkNotNullParameter(str, PROP_BUFFERING_STRATEGY);
        reactExoplayerView.setBufferingStrategy(BufferingStrategy.Companion.parse(str));
    }

    @ReactProp(defaultBoolean = false, name = "disableDisconnectError")
    public final void setDisableDisconnectError(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setDisableDisconnectError(z);
    }

    @ReactProp(defaultBoolean = false, name = "fullscreen")
    public final void setFullscreen(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setFullscreen(z);
    }

    @ReactProp(defaultInt = 1, name = "viewType")
    public final void setViewType(ReactExoplayerView reactExoplayerView, int i) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setViewType(i);
    }

    @ReactProp(defaultBoolean = false, name = "hideShutterView")
    public final void setHideShutterView(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setHideShutterView(z);
    }

    @ReactProp(defaultBoolean = false, name = "controls")
    public final void setControls(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setControls(z);
    }

    @ReactProp(name = "subtitleStyle")
    public final void setSubtitleStyle(ReactExoplayerView reactExoplayerView, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setSubtitleStyle(SubtitleStyle.Companion.parse(readableMap));
    }

    @ReactProp(defaultInt = -16777216, name = "shutterColor")
    public final void setShutterColor(ReactExoplayerView reactExoplayerView, int i) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setShutterColor(Integer.valueOf(i));
    }

    @ReactProp(name = "showNotificationControls")
    public final void setShowNotificationControls(ReactExoplayerView reactExoplayerView, boolean z) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setShowNotificationControls(z);
    }

    @ReactProp(defaultBoolean = false, name = "debug")
    public final void setDebug(ReactExoplayerView reactExoplayerView, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        boolean safeGetBool = ReactBridgeUtils.safeGetBool(readableMap, "enable", false);
        boolean safeGetBool2 = ReactBridgeUtils.safeGetBool(readableMap, "thread", false);
        if (safeGetBool) {
            DebugLog.setConfig(2, safeGetBool2);
        } else {
            DebugLog.setConfig(5, safeGetBool2);
        }
        reactExoplayerView.setDebug(safeGetBool);
    }

    @ReactProp(name = "controlsStyles")
    public final void setControlsStyles(ReactExoplayerView reactExoplayerView, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "videoView");
        reactExoplayerView.setControlsStyles(ControlsConfig.Companion.parse(readableMap));
    }
}
