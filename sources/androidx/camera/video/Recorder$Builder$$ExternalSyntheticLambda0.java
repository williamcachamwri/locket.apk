package androidx.camera.video;

import androidx.camera.video.VideoSpec;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$Builder$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ QualitySelector f$0;

    public /* synthetic */ Recorder$Builder$$ExternalSyntheticLambda0(QualitySelector qualitySelector) {
        this.f$0 = qualitySelector;
    }

    public final void accept(Object obj) {
        ((VideoSpec.Builder) obj).setQualitySelector(this.f$0);
    }
}
