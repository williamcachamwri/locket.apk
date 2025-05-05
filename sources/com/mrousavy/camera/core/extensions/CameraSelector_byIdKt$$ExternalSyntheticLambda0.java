package com.mrousavy.camera.core.extensions;

import androidx.camera.core.CameraFilter;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CameraSelector_byIdKt$$ExternalSyntheticLambda0 implements CameraFilter {
    public final /* synthetic */ String f$0;

    public /* synthetic */ CameraSelector_byIdKt$$ExternalSyntheticLambda0(String str) {
        this.f$0 = str;
    }

    public final List filter(List list) {
        return CameraSelector_byIdKt.byId$lambda$1(this.f$0, list);
    }
}
