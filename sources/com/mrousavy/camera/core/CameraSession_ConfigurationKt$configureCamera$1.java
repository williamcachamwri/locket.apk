package com.mrousavy.camera.core;

import androidx.camera.lifecycle.ProcessCameraProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.mrousavy.camera.core.CameraSession_ConfigurationKt", f = "CameraSession+Configuration.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1}, l = {259, 271}, m = "configureCamera", n = {"$this$configureCamera", "provider", "configuration", "useCases", "isStreamingHDR", "needsImageAnalysis", "enableHdrExtension", "$this$configureCamera", "provider", "configuration", "useCases"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3"})
/* compiled from: CameraSession+Configuration.kt */
final class CameraSession_ConfigurationKt$configureCamera$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    CameraSession_ConfigurationKt$configureCamera$1(Continuation<? super CameraSession_ConfigurationKt$configureCamera$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CameraSession_ConfigurationKt.configureCamera((CameraSession) null, (ProcessCameraProvider) null, (CameraConfiguration) null, this);
    }
}
