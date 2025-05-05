package androidx.camera.mlkit.vision;

import android.graphics.Matrix;
import androidx.camera.core.ImageProxy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.interfaces.Detector;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MlKitAnalyzer$$ExternalSyntheticLambda1 implements OnCompleteListener {
    public final /* synthetic */ MlKitAnalyzer f$0;
    public final /* synthetic */ Map f$1;
    public final /* synthetic */ Detector f$2;
    public final /* synthetic */ Map f$3;
    public final /* synthetic */ ImageProxy f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ Matrix f$6;

    public /* synthetic */ MlKitAnalyzer$$ExternalSyntheticLambda1(MlKitAnalyzer mlKitAnalyzer, Map map, Detector detector, Map map2, ImageProxy imageProxy, int i, Matrix matrix) {
        this.f$0 = mlKitAnalyzer;
        this.f$1 = map;
        this.f$2 = detector;
        this.f$3 = map2;
        this.f$4 = imageProxy;
        this.f$5 = i;
        this.f$6 = matrix;
    }

    public final void onComplete(Task task) {
        this.f$0.m239lambda$detectRecursively$1$androidxcameramlkitvisionMlKitAnalyzer(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, task);
    }
}
