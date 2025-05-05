package com.brentvatne.exoplayer;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.media3.ui.LegacyPlayerControlView;
import androidx.media3.ui.R;
import com.brentvatne.common.api.ControlsConfig;
import com.brentvatne.common.toolbox.DebugLog;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u00017B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u0012H\u0002J\u0006\u0010\"\u001a\u00020#J\b\u0010$\u001a\u00020#H\u0016J\b\u0010%\u001a\u00020#H\u0014J\b\u0010&\u001a\u00020#H\u0014J\b\u0010'\u001a\u00020#H\u0002J=\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00162\b\u0010,\u001a\u0004\u0018\u00010\u00122\b\u0010-\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u0016H\u0002¢\u0006\u0002\u0010/J\u0018\u00100\u001a\u00020#2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010!\u001a\u00020\u0012H\u0002J\b\u00101\u001a\u00020#H\u0002J3\u00101\u001a\u00020#2\u0006\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u00122\b\u00105\u001a\u0004\u0018\u00010\u00122\b\u0010.\u001a\u0004\u0018\u00010\u0016H\u0002¢\u0006\u0002\u00106R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0004\n\u0002\u0010\u0013R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0004\n\u0002\u0010\u0013R\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/brentvatne/exoplayer/FullScreenPlayerView;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "exoPlayerView", "Lcom/brentvatne/exoplayer/ExoPlayerView;", "reactExoplayerView", "Lcom/brentvatne/exoplayer/ReactExoplayerView;", "playerControlView", "Landroidx/media3/ui/LegacyPlayerControlView;", "onBackPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "controlsConfig", "Lcom/brentvatne/common/api/ControlsConfig;", "(Landroid/content/Context;Lcom/brentvatne/exoplayer/ExoPlayerView;Lcom/brentvatne/exoplayer/ReactExoplayerView;Landroidx/media3/ui/LegacyPlayerControlView;Landroidx/activity/OnBackPressedCallback;Lcom/brentvatne/common/api/ControlsConfig;)V", "containerView", "Landroid/widget/FrameLayout;", "initialNavigationBarIsVisible", "", "Ljava/lang/Boolean;", "initialNotificationBarIsVisible", "initialSystemBarsBehavior", "", "Ljava/lang/Integer;", "mKeepScreenOnHandler", "Landroid/os/Handler;", "mKeepScreenOnUpdater", "Lcom/brentvatne/exoplayer/FullScreenPlayerView$KeepScreenOnUpdater;", "parent", "Landroid/view/ViewGroup;", "generateDefaultLayoutParams", "Landroid/widget/FrameLayout$LayoutParams;", "getFullscreenIconResource", "isFullscreen", "hideWithoutPlayer", "", "onAttachedToWindow", "onStart", "onStop", "restoreSystemUI", "updateBarVisibility", "inset", "Landroidx/core/view/WindowInsetsControllerCompat;", "type", "shouldHide", "initialVisibility", "systemBarsBehavior", "(Landroidx/core/view/WindowInsetsControllerCompat;ILjava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;)V", "updateFullscreenButton", "updateNavigationBarVisibility", "window", "Landroid/view/Window;", "hideNavigationBarOnFullScreenMode", "hideNotificationBarOnFullScreenMode", "(Landroid/view/Window;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;)V", "KeepScreenOnUpdater", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FullScreenPlayerView.kt */
public final class FullScreenPlayerView extends Dialog {
    private final FrameLayout containerView;
    private final ControlsConfig controlsConfig;
    /* access modifiers changed from: private */
    public final ExoPlayerView exoPlayerView;
    private Boolean initialNavigationBarIsVisible;
    private Boolean initialNotificationBarIsVisible;
    private Integer initialSystemBarsBehavior;
    /* access modifiers changed from: private */
    public final Handler mKeepScreenOnHandler = new Handler(Looper.getMainLooper());
    private final KeepScreenOnUpdater mKeepScreenOnUpdater = new KeepScreenOnUpdater(this);
    private final OnBackPressedCallback onBackPressedCallback;
    private ViewGroup parent;
    private final LegacyPlayerControlView playerControlView;
    private final ReactExoplayerView reactExoplayerView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FullScreenPlayerView(Context context, ExoPlayerView exoPlayerView2, ReactExoplayerView reactExoplayerView2, LegacyPlayerControlView legacyPlayerControlView, OnBackPressedCallback onBackPressedCallback2, ControlsConfig controlsConfig2) {
        super(context, 16973833);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(exoPlayerView2, "exoPlayerView");
        Intrinsics.checkNotNullParameter(reactExoplayerView2, "reactExoplayerView");
        Intrinsics.checkNotNullParameter(onBackPressedCallback2, "onBackPressedCallback");
        Intrinsics.checkNotNullParameter(controlsConfig2, "controlsConfig");
        this.exoPlayerView = exoPlayerView2;
        this.reactExoplayerView = reactExoplayerView2;
        this.playerControlView = legacyPlayerControlView;
        this.onBackPressedCallback = onBackPressedCallback2;
        this.controlsConfig = controlsConfig2;
        FrameLayout frameLayout = new FrameLayout(context);
        this.containerView = frameLayout;
        setContentView(frameLayout, generateDefaultLayoutParams());
        Window window = getWindow();
        if (window != null) {
            this.initialSystemBarsBehavior = Integer.valueOf(new WindowInsetsControllerCompat(window, window.getDecorView()).getSystemBarsBehavior());
            WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(window.getDecorView());
            boolean z = true;
            this.initialNavigationBarIsVisible = Boolean.valueOf(rootWindowInsets != null && rootWindowInsets.isVisible(WindowInsetsCompat.Type.navigationBars()));
            WindowInsetsCompat rootWindowInsets2 = ViewCompat.getRootWindowInsets(window.getDecorView());
            this.initialNotificationBarIsVisible = Boolean.valueOf((rootWindowInsets2 == null || !rootWindowInsets2.isVisible(WindowInsetsCompat.Type.statusBars())) ? false : z);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00030\u00030\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/brentvatne/exoplayer/FullScreenPlayerView$KeepScreenOnUpdater;", "Ljava/lang/Runnable;", "fullScreenPlayerView", "Lcom/brentvatne/exoplayer/FullScreenPlayerView;", "(Lcom/brentvatne/exoplayer/FullScreenPlayerView;)V", "mFullscreenPlayer", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "run", "", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FullScreenPlayerView.kt */
    private static final class KeepScreenOnUpdater implements Runnable {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private static final long UPDATE_KEEP_SCREEN_ON_FLAG_MS = 200;
        private final WeakReference<FullScreenPlayerView> mFullscreenPlayer;

        public KeepScreenOnUpdater(FullScreenPlayerView fullScreenPlayerView) {
            Intrinsics.checkNotNullParameter(fullScreenPlayerView, "fullScreenPlayerView");
            this.mFullscreenPlayer = new WeakReference<>(fullScreenPlayerView);
        }

        public void run() {
            try {
                FullScreenPlayerView fullScreenPlayerView = (FullScreenPlayerView) this.mFullscreenPlayer.get();
                if (fullScreenPlayerView != null) {
                    Window window = fullScreenPlayerView.getWindow();
                    if (window != null) {
                        if (fullScreenPlayerView.exoPlayerView.isPlaying()) {
                            window.addFlags(128);
                        } else {
                            window.clearFlags(128);
                        }
                    }
                    fullScreenPlayerView.mKeepScreenOnHandler.postDelayed(this, UPDATE_KEEP_SCREEN_ON_FLAG_MS);
                }
            } catch (Exception e) {
                DebugLog.e("ExoPlayer Exception", "Failed to flag FLAG_KEEP_SCREEN_ON on fullscreen.");
                DebugLog.e("ExoPlayer Exception", e.toString());
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/brentvatne/exoplayer/FullScreenPlayerView$KeepScreenOnUpdater$Companion;", "", "()V", "UPDATE_KEEP_SCREEN_ON_FLAG_MS", "", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: FullScreenPlayerView.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        ViewGroup viewGroup = (ViewGroup) this.exoPlayerView.getParent();
        this.parent = viewGroup;
        if (viewGroup != null) {
            viewGroup.removeView(this.exoPlayerView);
        }
        this.containerView.addView(this.exoPlayerView, generateDefaultLayoutParams());
        LegacyPlayerControlView legacyPlayerControlView = this.playerControlView;
        if (legacyPlayerControlView != null) {
            updateFullscreenButton(legacyPlayerControlView, true);
            ViewGroup viewGroup2 = this.parent;
            if (viewGroup2 != null) {
                viewGroup2.removeView(legacyPlayerControlView);
            }
            this.containerView.addView(legacyPlayerControlView, generateDefaultLayoutParams());
        }
        updateNavigationBarVisibility();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.mKeepScreenOnHandler.removeCallbacks(this.mKeepScreenOnUpdater);
        this.containerView.removeView(this.exoPlayerView);
        ViewGroup viewGroup = this.parent;
        if (viewGroup != null) {
            viewGroup.addView(this.exoPlayerView, generateDefaultLayoutParams());
        }
        LegacyPlayerControlView legacyPlayerControlView = this.playerControlView;
        if (legacyPlayerControlView != null) {
            updateFullscreenButton(legacyPlayerControlView, false);
            View view = legacyPlayerControlView;
            this.containerView.removeView(view);
            ViewGroup viewGroup2 = this.parent;
            if (viewGroup2 != null) {
                viewGroup2.addView(view, generateDefaultLayoutParams());
            }
        }
        ViewGroup viewGroup3 = this.parent;
        if (viewGroup3 != null) {
            viewGroup3.requestLayout();
        }
        this.parent = null;
        this.onBackPressedCallback.handleOnBackPressed();
        restoreSystemUI();
    }

    private final void restoreSystemUI() {
        Window window = getWindow();
        if (window != null) {
            updateNavigationBarVisibility(window, this.initialNavigationBarIsVisible, this.initialNotificationBarIsVisible, this.initialSystemBarsBehavior);
        }
    }

    public final void hideWithoutPlayer() {
        int childCount = this.containerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (this.containerView.getChildAt(i) != this.exoPlayerView) {
                this.containerView.getChildAt(i).setVisibility(8);
            }
        }
    }

    private final int getFullscreenIconResource(boolean z) {
        if (z) {
            return R.drawable.exo_icon_fullscreen_exit;
        }
        return R.drawable.exo_icon_fullscreen_enter;
    }

    private final void updateFullscreenButton(LegacyPlayerControlView legacyPlayerControlView, boolean z) {
        String str;
        ImageButton imageButton = (ImageButton) legacyPlayerControlView.findViewById(com.brentvatne.react.R.id.exo_fullscreen);
        if (imageButton != null) {
            int fullscreenIconResource = getFullscreenIconResource(z);
            if (z) {
                str = getContext().getString(R.string.exo_controls_fullscreen_exit_description);
            } else {
                str = getContext().getString(R.string.exo_controls_fullscreen_enter_description);
            }
            Intrinsics.checkNotNull(str);
            imageButton.setImageResource(fullscreenIconResource);
            imageButton.setContentDescription(str);
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.reactExoplayerView.getPreventsDisplaySleepDuringVideoPlayback()) {
            this.mKeepScreenOnHandler.post(this.mKeepScreenOnUpdater);
        }
    }

    private final FrameLayout.LayoutParams generateDefaultLayoutParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, 0, 0, 0);
        return layoutParams;
    }

    static /* synthetic */ void updateBarVisibility$default(FullScreenPlayerView fullScreenPlayerView, WindowInsetsControllerCompat windowInsetsControllerCompat, int i, Boolean bool, Boolean bool2, Integer num, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            num = null;
        }
        fullScreenPlayerView.updateBarVisibility(windowInsetsControllerCompat, i, bool, bool2, num);
    }

    private final void updateBarVisibility(WindowInsetsControllerCompat windowInsetsControllerCompat, int i, Boolean bool, Boolean bool2, Integer num) {
        if (bool != null) {
            if (!(!Intrinsics.areEqual((Object) Boolean.valueOf(bool.booleanValue()), (Object) bool2))) {
                bool = null;
            }
            if (bool == null) {
                return;
            }
            if (bool.booleanValue()) {
                windowInsetsControllerCompat.hide(i);
                if (num != null) {
                    windowInsetsControllerCompat.setSystemBarsBehavior(num.intValue());
                    return;
                }
                return;
            }
            windowInsetsControllerCompat.show(i);
        }
    }

    private final void updateNavigationBarVisibility(Window window, Boolean bool, Boolean bool2, Integer num) {
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(window, window.getDecorView());
        updateBarVisibility(windowInsetsControllerCompat, WindowInsetsCompat.Type.navigationBars(), bool, this.initialNavigationBarIsVisible, num);
        updateBarVisibility$default(this, windowInsetsControllerCompat, WindowInsetsCompat.Type.statusBars(), bool2, this.initialNotificationBarIsVisible, (Integer) null, 16, (Object) null);
    }

    private final void updateNavigationBarVisibility() {
        Window window = getWindow();
        if (window != null) {
            updateNavigationBarVisibility(window, Boolean.valueOf(this.controlsConfig.getHideNavigationBarOnFullScreenMode()), Boolean.valueOf(this.controlsConfig.getHideNotificationBarOnFullScreenMode()), 2);
        }
        if (this.controlsConfig.getHideNotificationBarOnFullScreenMode()) {
            LegacyPlayerControlView legacyPlayerControlView = this.playerControlView;
            LinearLayout linearLayout = legacyPlayerControlView != null ? (LinearLayout) legacyPlayerControlView.findViewById(com.brentvatne.react.R.id.exo_live_container) : null;
            if (linearLayout != null) {
                ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                layoutParams2.topMargin = 40;
                linearLayout.setLayoutParams(layoutParams2);
            }
        }
    }
}
