package com.mrousavy.camera.core;

import com.google.android.gms.tasks.OnSuccessListener;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CodeScannerPipeline$$ExternalSyntheticLambda0 implements OnSuccessListener {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ CodeScannerPipeline$$ExternalSyntheticLambda0(Function1 function1) {
        this.f$0 = function1;
    }

    public final void onSuccess(Object obj) {
        CodeScannerPipeline.analyze$lambda$1(this.f$0, obj);
    }
}
