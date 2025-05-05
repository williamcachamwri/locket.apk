package androidx.camera.extensions;

import android.content.Context;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigProvider;
import androidx.camera.core.impl.Identifier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExtensionsInfo$$ExternalSyntheticLambda1 implements CameraConfigProvider {
    public final /* synthetic */ ExtensionsInfo f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Identifier f$2;

    public /* synthetic */ ExtensionsInfo$$ExternalSyntheticLambda1(ExtensionsInfo extensionsInfo, int i, Identifier identifier) {
        this.f$0 = extensionsInfo;
        this.f$1 = i;
        this.f$2 = identifier;
    }

    public final CameraConfig getConfig(CameraInfo cameraInfo, Context context) {
        return this.f$0.m227lambda$injectExtensionCameraConfig$1$androidxcameraextensionsExtensionsInfo(this.f$1, this.f$2, cameraInfo, context);
    }
}
