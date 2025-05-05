package androidx.camera.core;

import android.content.Context;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CameraX$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ CameraX f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$5;

    public /* synthetic */ CameraX$$ExternalSyntheticLambda2(CameraX cameraX, Executor executor, long j, int i, Context context, CallbackToFutureAdapter.Completer completer) {
        this.f$0 = cameraX;
        this.f$1 = executor;
        this.f$2 = j;
        this.f$3 = i;
        this.f$4 = context;
        this.f$5 = completer;
    }

    public final void run() {
        this.f$0.m135lambda$initAndRetryRecursively$1$androidxcameracoreCameraX(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
