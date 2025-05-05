package androidx.camera.core;

import android.content.Context;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CameraX$$ExternalSyntheticLambda4 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ CameraX f$0;
    public final /* synthetic */ Context f$1;

    public /* synthetic */ CameraX$$ExternalSyntheticLambda4(CameraX cameraX, Context context) {
        this.f$0 = cameraX;
        this.f$1 = context;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m137lambda$initInternal$0$androidxcameracoreCameraX(this.f$1, completer);
    }
}
