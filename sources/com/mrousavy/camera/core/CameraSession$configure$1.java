package com.mrousavy.camera.core;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.mrousavy.camera.core.CameraSession", f = "CameraSession.kt", i = {0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {108, 232, 149}, m = "configure", n = {"this", "lambda", "this", "lambda", "provider", "$this$withLock_u24default$iv", "this", "$this$withLock_u24default$iv", "config", "diff"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
/* compiled from: CameraSession.kt */
final class CameraSession$configure$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CameraSession this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraSession$configure$1(CameraSession cameraSession, Continuation<? super CameraSession$configure$1> continuation) {
        super(continuation);
        this.this$0 = cameraSession;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.configure((Function1<? super CameraConfiguration, Unit>) null, this);
    }
}
