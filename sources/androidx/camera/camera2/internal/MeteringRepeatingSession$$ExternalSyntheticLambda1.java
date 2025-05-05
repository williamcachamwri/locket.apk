package androidx.camera.camera2.internal;

import android.util.Size;
import java.util.Comparator;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MeteringRepeatingSession$$ExternalSyntheticLambda1 implements Comparator {
    public final int compare(Object obj, Object obj2) {
        return Long.signum((((long) ((Size) obj).getWidth()) * ((long) ((Size) obj).getHeight())) - (((long) ((Size) obj2).getWidth()) * ((long) ((Size) obj2).getHeight())));
    }
}
