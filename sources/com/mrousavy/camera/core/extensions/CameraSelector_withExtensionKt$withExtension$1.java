package com.mrousavy.camera.core.extensions;

import android.content.Context;
import androidx.camera.core.CameraSelector;
import androidx.camera.lifecycle.ProcessCameraProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.mrousavy.camera.core.extensions.CameraSelector_withExtensionKt", f = "CameraSelector+withExtension.kt", i = {0, 0, 0, 0}, l = {22}, m = "withExtension", n = {"$this$withExtension", "extensionDebugName", "needsImageAnalysis", "extension"}, s = {"L$0", "L$1", "Z$0", "I$0"})
/* compiled from: CameraSelector+withExtension.kt */
final class CameraSelector_withExtensionKt$withExtension$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    CameraSelector_withExtensionKt$withExtension$1(Continuation<? super CameraSelector_withExtensionKt$withExtension$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CameraSelector_withExtensionKt.withExtension((CameraSelector) null, (Context) null, (ProcessCameraProvider) null, false, 0, (String) null, this);
    }
}
