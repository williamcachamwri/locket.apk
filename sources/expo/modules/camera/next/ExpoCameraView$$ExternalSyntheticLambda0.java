package expo.modules.camera.next;

import androidx.camera.video.VideoRecordEvent;
import androidx.core.util.Consumer;
import expo.modules.kotlin.Promise;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExpoCameraView$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ ExpoCameraView$$ExternalSyntheticLambda0(Promise promise) {
        this.f$0 = promise;
    }

    public final void accept(Object obj) {
        ExpoCameraView.record$lambda$3$lambda$1(this.f$0, (VideoRecordEvent) obj);
    }
}
