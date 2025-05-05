package expo.modules.camera.next.analyzers;

import androidx.camera.core.ImageProxy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BarcodeAnalyzer$$ExternalSyntheticLambda2 implements OnCompleteListener {
    public final /* synthetic */ ImageProxy f$0;

    public /* synthetic */ BarcodeAnalyzer$$ExternalSyntheticLambda2(ImageProxy imageProxy) {
        this.f$0 = imageProxy;
    }

    public final void onComplete(Task task) {
        BarcodeAnalyzer.analyze$lambda$4(this.f$0, task);
    }
}
