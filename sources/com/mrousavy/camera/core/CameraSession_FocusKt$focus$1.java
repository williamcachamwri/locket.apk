package com.mrousavy.camera.core;

import androidx.camera.core.MeteringPoint;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.mrousavy.camera.core.CameraSession_FocusKt", f = "CameraSession+Focus.kt", i = {}, l = {22}, m = "focus", n = {}, s = {})
/* compiled from: CameraSession+Focus.kt */
final class CameraSession_FocusKt$focus$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    CameraSession_FocusKt$focus$1(Continuation<? super CameraSession_FocusKt$focus$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CameraSession_FocusKt.focus((CameraSession) null, (MeteringPoint) null, this);
    }
}
