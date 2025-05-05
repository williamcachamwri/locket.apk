package androidx.camera.video;

import android.util.Range;
import androidx.camera.video.VideoSpec;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$Builder$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ int f$0;

    public /* synthetic */ Recorder$Builder$$ExternalSyntheticLambda2(int i) {
        this.f$0 = i;
    }

    public final void accept(Object obj) {
        ((VideoSpec.Builder) obj).setBitrate(new Range(Integer.valueOf(this.f$0), Integer.valueOf(this.f$0)));
    }
}
