package androidx.camera.lifecycle;

import androidx.camera.core.impl.utils.futures.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LifecycleCameraProviderImpl$$ExternalSyntheticLambda1 implements AsyncFunction {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ LifecycleCameraProviderImpl$$ExternalSyntheticLambda1(Function1 function1) {
        this.f$0 = function1;
    }

    public final ListenableFuture apply(Object obj) {
        return LifecycleCameraProviderImpl.initAsync$lambda$2$lambda$1(this.f$0, obj);
    }
}
