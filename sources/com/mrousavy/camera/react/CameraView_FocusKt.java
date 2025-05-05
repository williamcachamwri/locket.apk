package com.mrousavy.camera.react;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H@¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"focus", "", "Lcom/mrousavy/camera/react/CameraView;", "pointMap", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/mrousavy/camera/react/CameraView;Lcom/facebook/react/bridge/ReadableMap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraView+Focus.kt */
public final class CameraView_FocusKt {
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object focus(com.mrousavy.camera.react.CameraView r12, com.facebook.react.bridge.ReadableMap r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            boolean r0 = r14 instanceof com.mrousavy.camera.react.CameraView_FocusKt$focus$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            com.mrousavy.camera.react.CameraView_FocusKt$focus$1 r0 = (com.mrousavy.camera.react.CameraView_FocusKt$focus$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            com.mrousavy.camera.react.CameraView_FocusKt$focus$1 r0 = new com.mrousavy.camera.react.CameraView_FocusKt$focus$1
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 == r4) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00c9
        L_0x002e:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0036:
            double r12 = r0.D$1
            double r12 = r0.D$0
            java.lang.Object r12 = r0.L$1
            androidx.camera.view.PreviewView r12 = (androidx.camera.view.PreviewView) r12
            java.lang.Object r12 = r0.L$0
            com.mrousavy.camera.react.CameraView r12 = (com.mrousavy.camera.react.CameraView) r12
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00af
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.String r14 = "x"
            double r8 = r13.getDouble(r14)
            java.lang.String r14 = "y"
            double r10 = r13.getDouble(r14)
            androidx.camera.view.PreviewView r7 = r12.getPreviewView$react_native_vision_camera_release()
            if (r7 == 0) goto L_0x00cc
            boolean r13 = com.facebook.react.bridge.UiThreadUtil.isOnUiThread()
            if (r13 == 0) goto L_0x0078
            android.content.res.Resources r13 = android.content.res.Resources.getSystem()
            android.util.DisplayMetrics r13 = r13.getDisplayMetrics()
            float r13 = r13.density
            androidx.camera.core.MeteringPointFactory r14 = r7.getMeteringPointFactory()
            float r2 = (float) r8
            float r2 = r2 * r13
            float r4 = (float) r10
            float r4 = r4 * r13
            androidx.camera.core.MeteringPoint r13 = r14.createPoint(r2, r4)
            goto L_0x00b0
        L_0x0078:
            r0.L$0 = r12
            r0.L$1 = r7
            r0.D$0 = r8
            r0.D$1 = r10
            r0.label = r4
            r13 = r0
            kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
            kotlinx.coroutines.CancellableContinuationImpl r14 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r13)
            r14.<init>(r2, r4)
            r14.initCancellability()
            r6 = r14
            kotlinx.coroutines.CancellableContinuation r6 = (kotlinx.coroutines.CancellableContinuation) r6
            com.mrousavy.camera.react.CameraView_FocusKt$focus$$inlined$runOnUiThreadAndWait$1 r2 = new com.mrousavy.camera.react.CameraView_FocusKt$focus$$inlined$runOnUiThreadAndWait$1
            r5 = r2
            r5.<init>(r6, r7, r8, r10)
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            com.facebook.react.bridge.UiThreadUtil.runOnUiThread(r2)
            java.lang.Object r14 = r14.getResult()
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r14 != r2) goto L_0x00ac
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r13)
        L_0x00ac:
            if (r14 != r1) goto L_0x00af
            return r1
        L_0x00af:
            r13 = r14
        L_0x00b0:
            java.lang.String r14 = "runOnUiThreadAndWait(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r14)
            androidx.camera.core.MeteringPoint r13 = (androidx.camera.core.MeteringPoint) r13
            com.mrousavy.camera.core.CameraSession r12 = r12.getCameraSession$react_native_vision_camera_release()
            r14 = 0
            r0.L$0 = r14
            r0.L$1 = r14
            r0.label = r3
            java.lang.Object r12 = com.mrousavy.camera.core.CameraSession_FocusKt.focus(r12, r13, r0)
            if (r12 != r1) goto L_0x00c9
            return r1
        L_0x00c9:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x00cc:
            com.mrousavy.camera.core.FocusRequiresPreviewError r12 = new com.mrousavy.camera.core.FocusRequiresPreviewError
            r12.<init>()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.react.CameraView_FocusKt.focus(com.mrousavy.camera.react.CameraView, com.facebook.react.bridge.ReadableMap, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
