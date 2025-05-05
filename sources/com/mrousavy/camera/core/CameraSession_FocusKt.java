package com.mrousavy.camera.core;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H@¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"focus", "", "Lcom/mrousavy/camera/core/CameraSession;", "meteringPoint", "Landroidx/camera/core/MeteringPoint;", "(Lcom/mrousavy/camera/core/CameraSession;Landroidx/camera/core/MeteringPoint;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraSession+Focus.kt */
public final class CameraSession_FocusKt {
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00b6 A[Catch:{ OperationCanceledException -> 0x00c4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00bc A[Catch:{ OperationCanceledException -> 0x00c4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object focus(com.mrousavy.camera.core.CameraSession r18, androidx.camera.core.MeteringPoint r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r0 = r20
            java.lang.String r1 = "Focusing to "
            boolean r2 = r0 instanceof com.mrousavy.camera.core.CameraSession_FocusKt$focus$1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            com.mrousavy.camera.core.CameraSession_FocusKt$focus$1 r2 = (com.mrousavy.camera.core.CameraSession_FocusKt$focus$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L_0x001d
        L_0x0018:
            com.mrousavy.camera.core.CameraSession_FocusKt$focus$1 r2 = new com.mrousavy.camera.core.CameraSession_FocusKt$focus$1
            r2.<init>(r0)
        L_0x001d:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            java.lang.String r6 = "CameraSession"
            if (r4 == 0) goto L_0x0039
            if (r4 != r5) goto L_0x0031
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ OperationCanceledException -> 0x00c4 }
            goto L_0x00ae
        L_0x0031:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r0)
            androidx.camera.core.Camera r0 = r18.getCamera$react_native_vision_camera_release()
            if (r0 == 0) goto L_0x00d0
            androidx.camera.core.FocusMeteringAction$Builder r4 = new androidx.camera.core.FocusMeteringAction$Builder
            r7 = r19
            r4.<init>((androidx.camera.core.MeteringPoint) r7)
            androidx.camera.core.FocusMeteringAction r4 = r4.build()
            java.lang.String r7 = "build(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r7)
            androidx.camera.core.CameraInfo r7 = r0.getCameraInfo()
            boolean r7 = r7.isFocusMeteringSupported(r4)
            if (r7 == 0) goto L_0x00ca
            java.util.List r7 = r4.getMeteringPointsAf()     // Catch:{ OperationCanceledException -> 0x00c4 }
            java.lang.String r8 = "getMeteringPointsAf(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)     // Catch:{ OperationCanceledException -> 0x00c4 }
            r9 = r7
            java.lang.Iterable r9 = (java.lang.Iterable) r9     // Catch:{ OperationCanceledException -> 0x00c4 }
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            com.mrousavy.camera.core.CameraSession_FocusKt$focus$2 r7 = com.mrousavy.camera.core.CameraSession_FocusKt$focus$2.INSTANCE     // Catch:{ OperationCanceledException -> 0x00c4 }
            r15 = r7
            kotlin.jvm.functions.Function1 r15 = (kotlin.jvm.functions.Function1) r15     // Catch:{ OperationCanceledException -> 0x00c4 }
            r16 = 31
            r17 = 0
            java.lang.String r7 = kotlin.collections.CollectionsKt.joinToString$default(r9, r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ OperationCanceledException -> 0x00c4 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ OperationCanceledException -> 0x00c4 }
            r8.<init>(r1)     // Catch:{ OperationCanceledException -> 0x00c4 }
            java.lang.StringBuilder r1 = r8.append(r7)     // Catch:{ OperationCanceledException -> 0x00c4 }
            java.lang.String r7 = "..."
            java.lang.StringBuilder r1 = r1.append(r7)     // Catch:{ OperationCanceledException -> 0x00c4 }
            java.lang.String r1 = r1.toString()     // Catch:{ OperationCanceledException -> 0x00c4 }
            android.util.Log.i(r6, r1)     // Catch:{ OperationCanceledException -> 0x00c4 }
            androidx.camera.core.CameraControl r0 = r0.getCameraControl()     // Catch:{ OperationCanceledException -> 0x00c4 }
            com.google.common.util.concurrent.ListenableFuture r0 = r0.startFocusAndMetering(r4)     // Catch:{ OperationCanceledException -> 0x00c4 }
            java.lang.String r1 = "startFocusAndMetering(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch:{ OperationCanceledException -> 0x00c4 }
            com.mrousavy.camera.core.CameraQueues$Companion r1 = com.mrousavy.camera.core.CameraQueues.Companion     // Catch:{ OperationCanceledException -> 0x00c4 }
            java.util.concurrent.ExecutorService r1 = r1.getCameraExecutor()     // Catch:{ OperationCanceledException -> 0x00c4 }
            java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1     // Catch:{ OperationCanceledException -> 0x00c4 }
            r2.label = r5     // Catch:{ OperationCanceledException -> 0x00c4 }
            java.lang.Object r0 = com.mrousavy.camera.core.extensions.ListenableFuture_awaitKt.await(r0, r1, r2)     // Catch:{ OperationCanceledException -> 0x00c4 }
            if (r0 != r3) goto L_0x00ae
            return r3
        L_0x00ae:
            androidx.camera.core.FocusMeteringResult r0 = (androidx.camera.core.FocusMeteringResult) r0     // Catch:{ OperationCanceledException -> 0x00c4 }
            boolean r0 = r0.isFocusSuccessful()     // Catch:{ OperationCanceledException -> 0x00c4 }
            if (r0 == 0) goto L_0x00bc
            java.lang.String r0 = "Focused successfully!"
            android.util.Log.i(r6, r0)     // Catch:{ OperationCanceledException -> 0x00c4 }
            goto L_0x00c1
        L_0x00bc:
            java.lang.String r0 = "Focus failed."
            android.util.Log.i(r6, r0)     // Catch:{ OperationCanceledException -> 0x00c4 }
        L_0x00c1:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00c4:
            com.mrousavy.camera.core.FocusCanceledError r0 = new com.mrousavy.camera.core.FocusCanceledError
            r0.<init>()
            throw r0
        L_0x00ca:
            com.mrousavy.camera.core.FocusNotSupportedError r0 = new com.mrousavy.camera.core.FocusNotSupportedError
            r0.<init>()
            throw r0
        L_0x00d0:
            com.mrousavy.camera.core.CameraNotReadyError r0 = new com.mrousavy.camera.core.CameraNotReadyError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.CameraSession_FocusKt.focus(com.mrousavy.camera.core.CameraSession, androidx.camera.core.MeteringPoint, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
