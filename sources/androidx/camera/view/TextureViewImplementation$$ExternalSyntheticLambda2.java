package androidx.camera.view;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TextureViewImplementation$$ExternalSyntheticLambda2 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ TextureViewImplementation f$0;

    public /* synthetic */ TextureViewImplementation$$ExternalSyntheticLambda2(TextureViewImplementation textureViewImplementation) {
        this.f$0 = textureViewImplementation;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m326lambda$waitForNextFrame$3$androidxcameraviewTextureViewImplementation(completer);
    }
}
