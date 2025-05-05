package com.mrousavy.camera.react;

import com.facebook.react.bridge.Promise;
import com.facebook.react.modules.core.PermissionListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CameraViewModule$$ExternalSyntheticLambda0 implements PermissionListener {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ Promise f$1;

    public /* synthetic */ CameraViewModule$$ExternalSyntheticLambda0(int i, Promise promise) {
        this.f$0 = i;
        this.f$1 = promise;
    }

    public final boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        return CameraViewModule.requestPermission$lambda$1(this.f$0, this.f$1, i, strArr, iArr);
    }
}
