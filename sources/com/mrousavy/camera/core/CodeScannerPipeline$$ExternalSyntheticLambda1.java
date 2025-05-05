package com.mrousavy.camera.core;

import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CodeScannerPipeline$$ExternalSyntheticLambda1 implements OnFailureListener {
    public final /* synthetic */ CodeScannerPipeline f$0;

    public /* synthetic */ CodeScannerPipeline$$ExternalSyntheticLambda1(CodeScannerPipeline codeScannerPipeline) {
        this.f$0 = codeScannerPipeline;
    }

    public final void onFailure(Exception exc) {
        CodeScannerPipeline.analyze$lambda$2(this.f$0, exc);
    }
}
