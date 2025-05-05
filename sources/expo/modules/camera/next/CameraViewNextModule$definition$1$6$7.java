package expo.modules.camera.next;

import expo.modules.camera.next.records.VideoQuality;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "view", "Lexpo/modules/camera/next/ExpoCameraView;", "quality", "Lexpo/modules/camera/next/records/VideoQuality;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraViewNextModule.kt */
final class CameraViewNextModule$definition$1$6$7 extends Lambda implements Function2<ExpoCameraView, VideoQuality, Unit> {
    public static final CameraViewNextModule$definition$1$6$7 INSTANCE = new CameraViewNextModule$definition$1$6$7();

    CameraViewNextModule$definition$1$6$7() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((ExpoCameraView) obj, (VideoQuality) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(ExpoCameraView expoCameraView, VideoQuality videoQuality) {
        Intrinsics.checkNotNullParameter(expoCameraView, "view");
        if (videoQuality != null) {
            expoCameraView.setVideoQuality(videoQuality);
        } else {
            expoCameraView.setVideoQuality(VideoQuality.VIDEO1080P);
        }
    }
}
