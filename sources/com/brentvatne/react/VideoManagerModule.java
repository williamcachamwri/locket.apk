package com.brentvatne.react;

import android.view.View;
import com.brentvatne.exoplayer.ReactExoplayerView;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.UIManagerHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\r\u001a\u00020\u000eH\u0016J&\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0014\u0010\u0010\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020\u00060\u0011H\u0002J \u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0007J\u0018\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u001f\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0019H\u0007¢\u0006\u0002\u0010\u001cJ\u001a\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0007J\u0018\u0010 \u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010!\u001a\u00020\u0015H\u0007¨\u0006#"}, d2 = {"Lcom/brentvatne/react/VideoManagerModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "enterPictureInPictureCmd", "", "reactTag", "", "exitPictureInPictureCmd", "getCurrentPosition", "promise", "Lcom/facebook/react/bridge/Promise;", "getName", "", "performOnPlayerView", "callback", "Lkotlin/Function1;", "Lcom/brentvatne/exoplayer/ReactExoplayerView;", "seekCmd", "time", "", "tolerance", "setFullScreenCmd", "fullScreen", "", "setPlayerPauseStateCmd", "paused", "(ILjava/lang/Boolean;)V", "setSourceCmd", "source", "Lcom/facebook/react/bridge/ReadableMap;", "setVolumeCmd", "volume", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoManagerModule.kt */
public final class VideoManagerModule extends ReactContextBaseJavaModule {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String REACT_CLASS = "VideoManager";

    public String getName() {
        return REACT_CLASS;
    }

    public VideoManagerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private final void performOnPlayerView(int i, Function1<? super ReactExoplayerView, Unit> function1) {
        UiThreadUtil.runOnUiThread(new VideoManagerModule$$ExternalSyntheticLambda0(this, i, function1));
    }

    /* access modifiers changed from: private */
    public static final void performOnPlayerView$lambda$0(VideoManagerModule videoManagerModule, int i, Function1 function1) {
        Intrinsics.checkNotNullParameter(videoManagerModule, "this$0");
        Intrinsics.checkNotNullParameter(function1, "$callback");
        try {
            UIManager uIManager = UIManagerHelper.getUIManager(videoManagerModule.getReactApplicationContext(), 1);
            View resolveView = uIManager != null ? uIManager.resolveView(i) : null;
            if (resolveView instanceof ReactExoplayerView) {
                function1.invoke(resolveView);
            } else {
                function1.invoke(null);
            }
        } catch (Exception unused) {
            function1.invoke(null);
        }
    }

    @ReactMethod
    public final void setPlayerPauseStateCmd(int i, Boolean bool) {
        performOnPlayerView(i, new VideoManagerModule$setPlayerPauseStateCmd$1(bool));
    }

    @ReactMethod
    public final void seekCmd(int i, float f, float f2) {
        performOnPlayerView(i, new VideoManagerModule$seekCmd$1(f));
    }

    @ReactMethod
    public final void setVolumeCmd(int i, float f) {
        performOnPlayerView(i, new VideoManagerModule$setVolumeCmd$1(f));
    }

    @ReactMethod
    public final void setFullScreenCmd(int i, boolean z) {
        performOnPlayerView(i, new VideoManagerModule$setFullScreenCmd$1(z));
    }

    @ReactMethod
    public final void enterPictureInPictureCmd(int i) {
        performOnPlayerView(i, VideoManagerModule$enterPictureInPictureCmd$1.INSTANCE);
    }

    @ReactMethod
    public final void exitPictureInPictureCmd(int i) {
        performOnPlayerView(i, VideoManagerModule$exitPictureInPictureCmd$1.INSTANCE);
    }

    @ReactMethod
    public final void setSourceCmd(int i, ReadableMap readableMap) {
        performOnPlayerView(i, new VideoManagerModule$setSourceCmd$1(readableMap, this));
    }

    @ReactMethod
    public final void getCurrentPosition(int i, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        performOnPlayerView(i, new VideoManagerModule$getCurrentPosition$1(promise));
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/brentvatne/react/VideoManagerModule$Companion;", "", "()V", "REACT_CLASS", "", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoManagerModule.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
