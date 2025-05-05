package coil;

import coil.EventListener;
import coil.ImageLoader;
import coil.request.ImageRequest;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageLoader$Builder$$ExternalSyntheticLambda0 implements EventListener.Factory {
    public final /* synthetic */ EventListener f$0;

    public /* synthetic */ ImageLoader$Builder$$ExternalSyntheticLambda0(EventListener eventListener) {
        this.f$0 = eventListener;
    }

    public final EventListener create(ImageRequest imageRequest) {
        return ImageLoader.Builder.eventListener$lambda$15(this.f$0, imageRequest);
    }
}
