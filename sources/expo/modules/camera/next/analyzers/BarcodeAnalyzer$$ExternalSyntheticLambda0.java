package expo.modules.camera.next.analyzers;

import com.google.android.gms.tasks.OnSuccessListener;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BarcodeAnalyzer$$ExternalSyntheticLambda0 implements OnSuccessListener {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ BarcodeAnalyzer$$ExternalSyntheticLambda0(Function1 function1) {
        this.f$0 = function1;
    }

    public final void onSuccess(Object obj) {
        BarcodeAnalyzer.analyze$lambda$2(this.f$0, obj);
    }
}
