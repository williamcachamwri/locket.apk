package com.brentvatne.exoplayer;

import androidx.core.app.PictureInPictureModeChangedInfo;
import com.facebook.react.uimanager.ThemedReactContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/brentvatne/exoplayer/PictureInPictureUtil$addLifecycleEventListener$1", "Ljava/lang/Runnable;", "run", "", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PictureInPictureUtil.kt */
public final class PictureInPictureUtil$addLifecycleEventListener$1 implements Runnable {
    final /* synthetic */ ThemedReactContext $context;
    final /* synthetic */ Function1<PictureInPictureModeChangedInfo, Unit> $onPictureInPictureModeChanged;
    final /* synthetic */ Function0<Unit> $onUserLeaveHintCallback;

    PictureInPictureUtil$addLifecycleEventListener$1(ThemedReactContext themedReactContext, Function1<? super PictureInPictureModeChangedInfo, Unit> function1, Function0<Unit> function0) {
        this.$context = themedReactContext;
        this.$onPictureInPictureModeChanged = function1;
        this.$onUserLeaveHintCallback = function0;
    }

    public void run() {
        PictureInPictureUtilKt.findActivity(this.$context).removeOnPictureInPictureModeChangedListener(new PictureInPictureUtilKt$sam$androidx_core_util_Consumer$0(this.$onPictureInPictureModeChanged));
        PictureInPictureUtilKt.findActivity(this.$context).removeOnUserLeaveHintListener(new PictureInPictureUtil$addLifecycleEventListener$1$$ExternalSyntheticLambda0(this.$onUserLeaveHintCallback));
    }

    /* access modifiers changed from: private */
    public static final void run$lambda$0(Function0 function0) {
        Intrinsics.checkNotNullParameter(function0, "$tmp0");
        function0.invoke();
    }
}
