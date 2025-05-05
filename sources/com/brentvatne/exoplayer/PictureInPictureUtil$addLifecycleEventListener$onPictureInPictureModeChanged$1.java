package com.brentvatne.exoplayer;

import androidx.activity.ComponentActivity;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "info", "Landroidx/core/app/PictureInPictureModeChangedInfo;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PictureInPictureUtil.kt */
final class PictureInPictureUtil$addLifecycleEventListener$onPictureInPictureModeChanged$1 extends Lambda implements Function1<PictureInPictureModeChangedInfo, Unit> {
    final /* synthetic */ ComponentActivity $activity;
    final /* synthetic */ ReactExoplayerView $view;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PictureInPictureUtil$addLifecycleEventListener$onPictureInPictureModeChanged$1(ReactExoplayerView reactExoplayerView, ComponentActivity componentActivity) {
        super(1);
        this.$view = reactExoplayerView;
        this.$activity = componentActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PictureInPictureModeChangedInfo) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PictureInPictureModeChangedInfo pictureInPictureModeChangedInfo) {
        Intrinsics.checkNotNullParameter(pictureInPictureModeChangedInfo, "info");
        this.$view.setIsInPictureInPicture(pictureInPictureModeChangedInfo.isInPictureInPictureMode());
        if (!pictureInPictureModeChangedInfo.isInPictureInPictureMode() && this.$activity.getLifecycle().getCurrentState() == Lifecycle.State.CREATED && !this.$view.playInBackground) {
            this.$view.setPausedModifier(true);
        }
    }
}
