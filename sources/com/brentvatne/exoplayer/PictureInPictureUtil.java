package com.brentvatne.exoplayer;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Process;
import android.util.Rational;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.core.app.AppOpsManagerCompat;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.R;
import com.brentvatne.common.toolbox.DebugLog;
import com.brentvatne.receiver.PictureInPictureReceiver;
import com.facebook.react.uimanager.ThemedReactContext;
import expo.modules.notifications.service.NotificationsService;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\"\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J*\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0012H\u0007J\"\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0003J\b\u0010 \u001a\u00020\u0012H\u0003J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nH\u0003J\u0010\u0010\"\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nH\u0002J\u001a\u0010#\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010%H\u0007J0\u0010&\u001a\u0012\u0012\u0004\u0012\u00020(0'j\b\u0012\u0004\u0012\u00020(`)2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0010\u0010*\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nH\u0002J\b\u0010+\u001a\u00020\u0012H\u0002J\u0018\u0010,\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010-\u001a\u00020%H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/brentvatne/exoplayer/PictureInPictureUtil;", "", "()V", "FLAG_SUPPORTS_PICTURE_IN_PICTURE", "", "TAG", "", "addLifecycleEventListener", "Ljava/lang/Runnable;", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "view", "Lcom/brentvatne/exoplayer/ReactExoplayerView;", "applyAutoEnterEnabled", "", "pipParamsBuilder", "Landroid/app/PictureInPictureParams$Builder;", "autoEnterEnabled", "", "applyPlayingStatus", "receiver", "Lcom/brentvatne/receiver/PictureInPictureReceiver;", "isPaused", "applySourceRectHint", "playerView", "Lcom/brentvatne/exoplayer/ExoPlayerView;", "calcPictureInPictureAspectRatio", "Landroid/util/Rational;", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "calcRectHint", "Landroid/graphics/Rect;", "checkIsApiSupport", "checkIsSystemSupportPIP", "checkIsUserAllowPIP", "enterPictureInPictureMode", "pictureInPictureParams", "Landroid/app/PictureInPictureParams;", "getPictureInPictureActions", "Ljava/util/ArrayList;", "Landroid/app/RemoteAction;", "Lkotlin/collections/ArrayList;", "isSupportPictureInPicture", "isSupportPictureInPictureAction", "updatePictureInPictureActions", "pipParams", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PictureInPictureUtil.kt */
public final class PictureInPictureUtil {
    private static final int FLAG_SUPPORTS_PICTURE_IN_PICTURE = 4194304;
    public static final PictureInPictureUtil INSTANCE = new PictureInPictureUtil();
    private static final String TAG = "PictureInPictureUtil";

    private final boolean checkIsApiSupport() {
        return true;
    }

    private final boolean isSupportPictureInPictureAction() {
        return true;
    }

    private PictureInPictureUtil() {
    }

    @JvmStatic
    public static final Runnable addLifecycleEventListener(ThemedReactContext themedReactContext, ReactExoplayerView reactExoplayerView) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        Intrinsics.checkNotNullParameter(reactExoplayerView, "view");
        ComponentActivity findActivity = PictureInPictureUtilKt.findActivity(themedReactContext);
        Function1 pictureInPictureUtil$addLifecycleEventListener$onPictureInPictureModeChanged$1 = new PictureInPictureUtil$addLifecycleEventListener$onPictureInPictureModeChanged$1(reactExoplayerView, findActivity);
        Function0 pictureInPictureUtil$addLifecycleEventListener$onUserLeaveHintCallback$1 = new PictureInPictureUtil$addLifecycleEventListener$onUserLeaveHintCallback$1(reactExoplayerView);
        findActivity.addOnPictureInPictureModeChangedListener(new PictureInPictureUtilKt$sam$androidx_core_util_Consumer$0(pictureInPictureUtil$addLifecycleEventListener$onPictureInPictureModeChanged$1));
        if (Build.VERSION.SDK_INT < 31) {
            findActivity.addOnUserLeaveHintListener(new PictureInPictureUtil$$ExternalSyntheticLambda0(pictureInPictureUtil$addLifecycleEventListener$onUserLeaveHintCallback$1));
        }
        return new PictureInPictureUtil$addLifecycleEventListener$1(themedReactContext, pictureInPictureUtil$addLifecycleEventListener$onPictureInPictureModeChanged$1, pictureInPictureUtil$addLifecycleEventListener$onUserLeaveHintCallback$1);
    }

    /* access modifiers changed from: private */
    public static final void addLifecycleEventListener$lambda$0(Function0 function0) {
        Intrinsics.checkNotNullParameter(function0, "$tmp0");
        function0.invoke();
    }

    @JvmStatic
    public static final void enterPictureInPictureMode(ThemedReactContext themedReactContext, PictureInPictureParams pictureInPictureParams) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        PictureInPictureUtil pictureInPictureUtil = INSTANCE;
        if (pictureInPictureUtil.isSupportPictureInPicture(themedReactContext)) {
            if (!pictureInPictureUtil.isSupportPictureInPictureAction() || pictureInPictureParams == null) {
                try {
                    PictureInPictureUtilKt.findActivity(themedReactContext).enterPictureInPictureMode();
                } catch (IllegalStateException e) {
                    DebugLog.e(TAG, e.toString());
                }
            } else {
                try {
                    PictureInPictureUtilKt.findActivity(themedReactContext).enterPictureInPictureMode(pictureInPictureParams);
                } catch (IllegalStateException e2) {
                    DebugLog.e(TAG, e2.toString());
                }
            }
        }
    }

    @JvmStatic
    public static final void applyPlayingStatus(ThemedReactContext themedReactContext, PictureInPictureParams.Builder builder, PictureInPictureReceiver pictureInPictureReceiver, boolean z) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        Intrinsics.checkNotNullParameter(pictureInPictureReceiver, NotificationsService.RECEIVER_KEY);
        if (builder != null) {
            PictureInPictureUtil pictureInPictureUtil = INSTANCE;
            builder.setActions(getPictureInPictureActions(themedReactContext, z, pictureInPictureReceiver));
            PictureInPictureParams build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            pictureInPictureUtil.updatePictureInPictureActions(themedReactContext, build);
        }
    }

    @JvmStatic
    public static final void applyAutoEnterEnabled(ThemedReactContext themedReactContext, PictureInPictureParams.Builder builder, boolean z) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        if (builder != null && Build.VERSION.SDK_INT >= 31) {
            builder.setAutoEnterEnabled(z);
            PictureInPictureUtil pictureInPictureUtil = INSTANCE;
            PictureInPictureParams build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            pictureInPictureUtil.updatePictureInPictureActions(themedReactContext, build);
        }
    }

    @JvmStatic
    public static final void applySourceRectHint(ThemedReactContext themedReactContext, PictureInPictureParams.Builder builder, ExoPlayerView exoPlayerView) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        Intrinsics.checkNotNullParameter(exoPlayerView, "playerView");
        if (builder != null) {
            PictureInPictureUtil pictureInPictureUtil = INSTANCE;
            builder.setSourceRectHint(calcRectHint(exoPlayerView));
            PictureInPictureParams build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            pictureInPictureUtil.updatePictureInPictureActions(themedReactContext, build);
        }
    }

    private final void updatePictureInPictureActions(ThemedReactContext themedReactContext, PictureInPictureParams pictureInPictureParams) {
        if (isSupportPictureInPictureAction() && isSupportPictureInPicture(themedReactContext)) {
            try {
                PictureInPictureUtilKt.findActivity(themedReactContext).setPictureInPictureParams(pictureInPictureParams);
            } catch (IllegalStateException e) {
                DebugLog.e(TAG, e.toString());
            }
        }
    }

    @JvmStatic
    public static final ArrayList<RemoteAction> getPictureInPictureActions(ThemedReactContext themedReactContext, boolean z, PictureInPictureReceiver pictureInPictureReceiver) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        Intrinsics.checkNotNullParameter(pictureInPictureReceiver, NotificationsService.RECEIVER_KEY);
        PendingIntent pipActionIntent = pictureInPictureReceiver.getPipActionIntent(z);
        Icon createWithResource = Icon.createWithResource(themedReactContext, z ? R.drawable.exo_icon_play : R.drawable.exo_icon_pause);
        Intrinsics.checkNotNullExpressionValue(createWithResource, "createWithResource(...)");
        CharSequence charSequence = z ? "play" : "pause";
        return CollectionsKt.arrayListOf(new RemoteAction(createWithResource, charSequence, charSequence, pipActionIntent));
    }

    @JvmStatic
    private static final Rect calcRectHint(ExoPlayerView exoPlayerView) {
        Rect rect = new Rect();
        View surfaceView = exoPlayerView.getSurfaceView();
        if (surfaceView != null) {
            surfaceView.getGlobalVisibleRect(rect);
        }
        int[] iArr = new int[2];
        View surfaceView2 = exoPlayerView.getSurfaceView();
        if (surfaceView2 != null) {
            surfaceView2.getLocationOnScreen(iArr);
        }
        rect.top = iArr[1];
        rect.bottom = rect.top + (rect.bottom - rect.top);
        return rect;
    }

    @JvmStatic
    public static final Rational calcPictureInPictureAspectRatio(ExoPlayer exoPlayer) {
        Intrinsics.checkNotNullParameter(exoPlayer, "player");
        Rational rational = new Rational(exoPlayer.getVideoSize().width, exoPlayer.getVideoSize().height);
        Rational rational2 = new Rational(239, 100);
        Rational rational3 = new Rational(100, 239);
        if (rational.floatValue() > rational2.floatValue()) {
            return rational2;
        }
        return rational.floatValue() < rational3.floatValue() ? rational3 : rational;
    }

    private final boolean isSupportPictureInPicture(ThemedReactContext themedReactContext) {
        return checkIsApiSupport() && checkIsSystemSupportPIP(themedReactContext) && checkIsUserAllowPIP(themedReactContext);
    }

    private final boolean checkIsSystemSupportPIP(ThemedReactContext themedReactContext) {
        ComponentActivity findActivity = PictureInPictureUtilKt.findActivity(themedReactContext);
        if (findActivity == null) {
            return false;
        }
        ActivityInfo activityInfo = findActivity.getPackageManager().getActivityInfo(findActivity.getComponentName(), 128);
        Intrinsics.checkNotNullExpressionValue(activityInfo, "getActivityInfo(...)");
        boolean z = (activityInfo.flags & 4194304) != 0;
        boolean hasSystemFeature = findActivity.getPackageManager().hasSystemFeature("android.software.picture_in_picture");
        if (!z || !hasSystemFeature) {
            return false;
        }
        return true;
    }

    private final boolean checkIsUserAllowPIP(ThemedReactContext themedReactContext) {
        Activity currentActivity = themedReactContext.getCurrentActivity();
        if (currentActivity != null && AppOpsManagerCompat.noteOpNoThrow(currentActivity, "android:picture_in_picture", Process.myUid(), currentActivity.getPackageName()) == 0) {
            return true;
        }
        return false;
    }
}
