package expo.modules.camera.next;

import androidx.lifecycle.Observer;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExpoCameraView$$ExternalSyntheticLambda1 implements Observer {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ ExpoCameraView$$ExternalSyntheticLambda1(Function1 function1) {
        this.f$0 = function1;
    }

    public final void onChanged(Object obj) {
        ExpoCameraView.observeCameraState$lambda$13(this.f$0, obj);
    }
}
