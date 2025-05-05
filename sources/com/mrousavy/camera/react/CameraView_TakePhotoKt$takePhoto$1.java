package com.mrousavy.camera.react;

import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.mrousavy.camera.react.CameraView_TakePhotoKt", f = "CameraView+TakePhoto.kt", i = {}, l = {19}, m = "takePhoto", n = {}, s = {})
/* compiled from: CameraView+TakePhoto.kt */
final class CameraView_TakePhotoKt$takePhoto$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    CameraView_TakePhotoKt$takePhoto$1(Continuation<? super CameraView_TakePhotoKt$takePhoto$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CameraView_TakePhotoKt.takePhoto((CameraView) null, (ReadableMap) null, this);
    }
}
