package androidx.camera.camera2.internal;

import androidx.camera.core.ImageProxy;
import androidx.camera.core.internal.utils.RingBuffer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ZslControlImpl$$ExternalSyntheticLambda2 implements RingBuffer.OnRemoveCallback {
    public final void onRemove(Object obj) {
        ((ImageProxy) obj).close();
    }
}
