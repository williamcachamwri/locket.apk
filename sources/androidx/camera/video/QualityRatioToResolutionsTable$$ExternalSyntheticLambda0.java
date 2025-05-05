package androidx.camera.video;

import android.util.Size;
import java.util.Comparator;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class QualityRatioToResolutionsTable$$ExternalSyntheticLambda0 implements Comparator {
    public final /* synthetic */ int f$0;

    public /* synthetic */ QualityRatioToResolutionsTable$$ExternalSyntheticLambda0(int i) {
        this.f$0 = i;
    }

    public final int compare(Object obj, Object obj2) {
        return QualityRatioToResolutionsTable.lambda$sortQualityRatioRow$0(this.f$0, (Size) obj, (Size) obj2);
    }
}
