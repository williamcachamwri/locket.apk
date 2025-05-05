package com.facebook.react.fabric.mounting;

import android.view.View;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceMountingManager$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SurfaceMountingManager f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ SurfaceMountingManager$$ExternalSyntheticLambda1(SurfaceMountingManager surfaceMountingManager, View view) {
        this.f$0 = surfaceMountingManager;
        this.f$1 = view;
    }

    public final void run() {
        this.f$0.lambda$addRootView$0(this.f$1);
    }
}
