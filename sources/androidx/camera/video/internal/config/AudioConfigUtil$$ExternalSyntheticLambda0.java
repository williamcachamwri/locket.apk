package androidx.camera.video.internal.config;

import java.util.Comparator;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioConfigUtil$$ExternalSyntheticLambda0 implements Comparator {
    public final /* synthetic */ int f$0;

    public /* synthetic */ AudioConfigUtil$$ExternalSyntheticLambda0(int i) {
        this.f$0 = i;
    }

    public final int compare(Object obj, Object obj2) {
        return AudioConfigUtil.lambda$selectSampleRateOrNearestSupported$0(this.f$0, (Integer) obj, (Integer) obj2);
    }
}
