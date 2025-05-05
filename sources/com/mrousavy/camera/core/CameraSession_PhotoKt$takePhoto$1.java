package com.mrousavy.camera.core;

import com.mrousavy.camera.core.types.TakePhotoOptions;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.mrousavy.camera.core.CameraSession_PhotoKt", f = "CameraSession+Photo.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {48}, m = "takePhoto", n = {"photoOutput", "$this$takePicture$iv", "file$iv", "metadataProvider$iv", "callback$iv", "executor$iv", "enableShutterSound", "isMirrored"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "Z$0"})
/* compiled from: CameraSession+Photo.kt */
final class CameraSession_PhotoKt$takePhoto$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    CameraSession_PhotoKt$takePhoto$1(Continuation<? super CameraSession_PhotoKt$takePhoto$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CameraSession_PhotoKt.takePhoto((CameraSession) null, (TakePhotoOptions) null, this);
    }
}
