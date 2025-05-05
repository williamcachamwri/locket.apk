package com.mrousavy.camera.core;

import androidx.camera.core.ImageProxy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CodeScannerPipeline$$ExternalSyntheticLambda2 implements OnCompleteListener {
    public final /* synthetic */ ImageProxy f$0;

    public /* synthetic */ CodeScannerPipeline$$ExternalSyntheticLambda2(ImageProxy imageProxy) {
        this.f$0 = imageProxy;
    }

    public final void onComplete(Task task) {
        CodeScannerPipeline.analyze$lambda$3(this.f$0, task);
    }
}
