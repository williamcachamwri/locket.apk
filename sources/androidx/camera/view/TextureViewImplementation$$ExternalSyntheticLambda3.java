package androidx.camera.view;

import androidx.camera.core.SurfaceRequest;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TextureViewImplementation$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$0;

    public /* synthetic */ TextureViewImplementation$$ExternalSyntheticLambda3(CallbackToFutureAdapter.Completer completer) {
        this.f$0 = completer;
    }

    public final void accept(Object obj) {
        boolean unused = this.f$0.set((SurfaceRequest.Result) obj);
    }
}
