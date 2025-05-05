package com.mrousavy.camera.react;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.mrousavy.camera.core.CameraError;
import com.mrousavy.camera.react.utils.CallbackPromiseKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "error", "Lcom/mrousavy/camera/core/CameraError;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraView+RecordVideo.kt */
final class CameraView_RecordVideoKt$startRecording$onError$1 extends Lambda implements Function1<CameraError, Unit> {
    final /* synthetic */ Callback $onRecordCallback;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraView_RecordVideoKt$startRecording$onError$1(Callback callback) {
        super(1);
        this.$onRecordCallback = callback;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((CameraError) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(CameraError cameraError) {
        Intrinsics.checkNotNullParameter(cameraError, "error");
        this.$onRecordCallback.invoke(null, CallbackPromiseKt.makeErrorMap$default(cameraError.getCode(), cameraError.getMessage(), (Throwable) null, (WritableMap) null, 12, (Object) null));
    }
}
