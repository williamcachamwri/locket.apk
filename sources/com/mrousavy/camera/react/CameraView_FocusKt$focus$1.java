package com.mrousavy.camera.react;

import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.mrousavy.camera.react.CameraView_FocusKt", f = "CameraView+Focus.kt", i = {0, 0, 0, 0}, l = {27, 18}, m = "focus", n = {"$this$focus", "previewView", "x", "y"}, s = {"L$0", "L$1", "D$0", "D$1"})
/* compiled from: CameraView+Focus.kt */
final class CameraView_FocusKt$focus$1 extends ContinuationImpl {
    double D$0;
    double D$1;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    CameraView_FocusKt$focus$1(Continuation<? super CameraView_FocusKt$focus$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CameraView_FocusKt.focus((CameraView) null, (ReadableMap) null, this);
    }
}
