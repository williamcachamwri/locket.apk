package androidx.camera.lifecycle;

import androidx.arch.core.util.Function;
import androidx.camera.lifecycle.ProcessCameraProvider;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProcessCameraProvider$Companion$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ ProcessCameraProvider$Companion$$ExternalSyntheticLambda0(Function1 function1) {
        this.f$0 = function1;
    }

    public final Object apply(Object obj) {
        return ProcessCameraProvider.Companion.getInstance$lambda$0(this.f$0, obj);
    }
}
