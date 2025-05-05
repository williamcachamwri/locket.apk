package com.mrousavy.camera.core.extensions;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a:\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0001H@¢\u0006\u0002\u0010\r\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"TAG", "", "withExtension", "Landroidx/camera/core/CameraSelector;", "context", "Landroid/content/Context;", "provider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "needsImageAnalysis", "", "extension", "", "extensionDebugName", "(Landroidx/camera/core/CameraSelector;Landroid/content/Context;Landroidx/camera/lifecycle/ProcessCameraProvider;ZILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraSelector+withExtension.kt */
public final class CameraSelector_withExtensionKt {
    private static final String TAG = "CameraSelector";

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object withExtension(androidx.camera.core.CameraSelector r5, android.content.Context r6, androidx.camera.lifecycle.ProcessCameraProvider r7, boolean r8, int r9, java.lang.String r10, kotlin.coroutines.Continuation<? super androidx.camera.core.CameraSelector> r11) {
        /*
            boolean r0 = r11 instanceof com.mrousavy.camera.core.extensions.CameraSelector_withExtensionKt$withExtension$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            com.mrousavy.camera.core.extensions.CameraSelector_withExtensionKt$withExtension$1 r0 = (com.mrousavy.camera.core.extensions.CameraSelector_withExtensionKt$withExtension$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            com.mrousavy.camera.core.extensions.CameraSelector_withExtensionKt$withExtension$1 r0 = new com.mrousavy.camera.core.extensions.CameraSelector_withExtensionKt$withExtension$1
            r0.<init>(r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            java.lang.String r4 = "CameraSelector"
            if (r2 == 0) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            int r9 = r0.I$0
            boolean r8 = r0.Z$0
            java.lang.Object r5 = r0.L$1
            r10 = r5
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r5 = r0.L$0
            androidx.camera.core.CameraSelector r5 = (androidx.camera.core.CameraSelector) r5
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0089
        L_0x0039:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.StringBuilder r11 = r11.append(r10)
            java.lang.String r2 = " is enabled, looking up vendor "
            java.lang.StringBuilder r11 = r11.append(r2)
            java.lang.StringBuilder r11 = r11.append(r10)
            java.lang.String r2 = " extension..."
            java.lang.StringBuilder r11 = r11.append(r2)
            java.lang.String r11 = r11.toString()
            android.util.Log.i(r4, r11)
            java.util.concurrent.Executor r11 = androidx.core.content.ContextCompat.getMainExecutor(r6)
            java.lang.String r2 = "getMainExecutor(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r2)
            androidx.camera.core.CameraProvider r7 = (androidx.camera.core.CameraProvider) r7
            com.google.common.util.concurrent.ListenableFuture r6 = androidx.camera.extensions.ExtensionsManager.getInstanceAsync(r6, r7)
            java.lang.String r7 = "getInstanceAsync(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            r0.L$0 = r5
            r0.L$1 = r10
            r0.Z$0 = r8
            r0.I$0 = r9
            r0.label = r3
            java.lang.Object r11 = com.mrousavy.camera.core.extensions.ListenableFuture_awaitKt.await(r6, r11, r0)
            if (r11 != r1) goto L_0x0089
            return r1
        L_0x0089:
            androidx.camera.extensions.ExtensionsManager r11 = (androidx.camera.extensions.ExtensionsManager) r11
            boolean r6 = r11.isExtensionAvailable(r5, r9)
            if (r6 == 0) goto L_0x00d1
            java.lang.String r6 = "Device supports a "
            if (r8 == 0) goto L_0x00b2
            boolean r7 = r11.isImageAnalysisSupported(r5, r9)
            if (r7 != 0) goto L_0x00b2
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            java.lang.StringBuilder r6 = r7.append(r10)
            java.lang.String r7 = " vendor extension, but we cannot use it since we need ImageAnalysis and this extension does not work with ImageAnalysis use-cases."
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r4, r6)
            return r5
        L_0x00b2:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            java.lang.StringBuilder r6 = r7.append(r10)
            java.lang.String r7 = " vendor extension! Enabling..."
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r4, r6)
            androidx.camera.core.CameraSelector r5 = r11.getExtensionEnabledCameraSelector(r5, r9)
            java.lang.String r6 = "getExtensionEnabledCameraSelector(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
        L_0x00d1:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.extensions.CameraSelector_withExtensionKt.withExtension(androidx.camera.core.CameraSelector, android.content.Context, androidx.camera.lifecycle.ProcessCameraProvider, boolean, int, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
