package androidx.camera.camera2.internal;

import androidx.camera.camera2.internal.compat.CameraDeviceCompat;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda2 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ SynchronizedCaptureSessionBaseImpl f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ CameraDeviceCompat f$2;
    public final /* synthetic */ SessionConfigurationCompat f$3;

    public /* synthetic */ SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda2(SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl, List list, CameraDeviceCompat cameraDeviceCompat, SessionConfigurationCompat sessionConfigurationCompat) {
        this.f$0 = synchronizedCaptureSessionBaseImpl;
        this.f$1 = list;
        this.f$2 = cameraDeviceCompat;
        this.f$3 = sessionConfigurationCompat;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m89lambda$openCaptureSession$0$androidxcameracamera2internalSynchronizedCaptureSessionBaseImpl(this.f$1, this.f$2, this.f$3, completer);
    }
}
