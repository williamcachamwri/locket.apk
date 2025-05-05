package androidx.camera.video;

import android.graphics.Rect;
import android.util.Size;
import java.util.Comparator;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoCapture$$ExternalSyntheticLambda2 implements Comparator {
    public final /* synthetic */ Rect f$0;

    public /* synthetic */ VideoCapture$$ExternalSyntheticLambda2(Rect rect) {
        this.f$0 = rect;
    }

    public final int compare(Object obj, Object obj2) {
        return VideoCapture.lambda$adjustCropRectToValidSize$4(this.f$0, (Size) obj, (Size) obj2);
    }
}
