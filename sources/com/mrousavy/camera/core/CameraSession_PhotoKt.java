package com.mrousavy.camera.core;

import android.media.AudioManager;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH@¢\u0006\u0002\u0010\t\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\n"}, d2 = {"isSilent", "", "Landroid/media/AudioManager;", "(Landroid/media/AudioManager;)Z", "takePhoto", "Lcom/mrousavy/camera/core/Photo;", "Lcom/mrousavy/camera/core/CameraSession;", "options", "Lcom/mrousavy/camera/core/types/TakePhotoOptions;", "(Lcom/mrousavy/camera/core/CameraSession;Lcom/mrousavy/camera/core/types/TakePhotoOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraSession+Photo.kt */
public final class CameraSession_PhotoKt {
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object takePhoto(com.mrousavy.camera.core.CameraSession r17, com.mrousavy.camera.core.types.TakePhotoOptions r18, kotlin.coroutines.Continuation<? super com.mrousavy.camera.core.Photo> r19) {
        /*
            r0 = r19
            boolean r1 = r0 instanceof com.mrousavy.camera.core.CameraSession_PhotoKt$takePhoto$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            com.mrousavy.camera.core.CameraSession_PhotoKt$takePhoto$1 r1 = (com.mrousavy.camera.core.CameraSession_PhotoKt$takePhoto$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            com.mrousavy.camera.core.CameraSession_PhotoKt$takePhoto$1 r1 = new com.mrousavy.camera.core.CameraSession_PhotoKt$takePhoto$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L_0x0052
            if (r3 != r4) goto L_0x004a
            boolean r2 = r1.Z$0
            int r3 = r1.I$0
            java.lang.Object r3 = r1.L$5
            java.util.concurrent.Executor r3 = (java.util.concurrent.Executor) r3
            java.lang.Object r3 = r1.L$4
            com.mrousavy.camera.core.CameraSession$Callback r3 = (com.mrousavy.camera.core.CameraSession.Callback) r3
            java.lang.Object r3 = r1.L$3
            com.mrousavy.camera.core.MetadataProvider r3 = (com.mrousavy.camera.core.MetadataProvider) r3
            java.lang.Object r3 = r1.L$2
            java.io.File r3 = (java.io.File) r3
            java.lang.Object r3 = r1.L$1
            androidx.camera.core.ImageCapture r3 = (androidx.camera.core.ImageCapture) r3
            java.lang.Object r1 = r1.L$0
            androidx.camera.core.ImageCapture r1 = (androidx.camera.core.ImageCapture) r1
            kotlin.ResultKt.throwOnFailure(r0)
            r7 = r2
            goto L_0x018a
        L_0x004a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0052:
            kotlin.ResultKt.throwOnFailure(r0)
            androidx.camera.core.Camera r0 = r17.getCamera$react_native_vision_camera_release()
            if (r0 == 0) goto L_0x01d5
            com.mrousavy.camera.core.CameraConfiguration r3 = r17.getConfiguration$react_native_vision_camera_release()
            if (r3 == 0) goto L_0x01cf
            com.mrousavy.camera.core.CameraConfiguration$Output r3 = r3.getPhoto()
            boolean r5 = r3 instanceof com.mrousavy.camera.core.CameraConfiguration.Output.Enabled
            r6 = 0
            if (r5 == 0) goto L_0x006d
            com.mrousavy.camera.core.CameraConfiguration$Output$Enabled r3 = (com.mrousavy.camera.core.CameraConfiguration.Output.Enabled) r3
            goto L_0x006e
        L_0x006d:
            r3 = r6
        L_0x006e:
            if (r3 == 0) goto L_0x01c9
            androidx.camera.core.ImageCapture r5 = r17.getPhotoOutput$react_native_vision_camera_release()
            if (r5 == 0) goto L_0x01c3
            com.mrousavy.camera.core.types.Flash r7 = r18.getFlash()
            com.mrousavy.camera.core.types.Flash r8 = com.mrousavy.camera.core.types.Flash.OFF
            if (r7 == r8) goto L_0x008f
            androidx.camera.core.CameraInfo r0 = r0.getCameraInfo()
            boolean r0 = r0.hasFlashUnit()
            if (r0 == 0) goto L_0x0089
            goto L_0x008f
        L_0x0089:
            com.mrousavy.camera.core.FlashUnavailableError r0 = new com.mrousavy.camera.core.FlashUnavailableError
            r0.<init>()
            throw r0
        L_0x008f:
            com.mrousavy.camera.core.types.Flash r0 = r18.getFlash()
            int r0 = r0.toFlashMode()
            r5.setFlashMode(r0)
            boolean r0 = r18.getEnableShutterSound()
            r7 = 0
            if (r0 == 0) goto L_0x00ad
            android.media.AudioManager r0 = r17.getAudioManager$react_native_vision_camera_release()
            boolean r0 = isSilent(r0)
            if (r0 != 0) goto L_0x00ad
            r9 = r4
            goto L_0x00ae
        L_0x00ad:
            r9 = r7
        L_0x00ae:
            java.lang.Object r0 = r3.getConfig()
            com.mrousavy.camera.core.CameraConfiguration$Photo r0 = (com.mrousavy.camera.core.CameraConfiguration.Photo) r0
            boolean r0 = r0.isMirrored()
            com.mrousavy.camera.core.utils.OutputFile r3 = r18.getFile()
            java.io.File r13 = r3.getFile()
            java.lang.String r3 = "<get-file>(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r3)
            com.mrousavy.camera.core.MetadataProvider r3 = r17.getMetadataProvider$react_native_vision_camera_release()
            com.mrousavy.camera.core.CameraSession$Callback r11 = r17.getCallback$react_native_vision_camera_release()
            com.mrousavy.camera.core.CameraQueues$Companion r8 = com.mrousavy.camera.core.CameraQueues.Companion
            java.util.concurrent.ExecutorService r8 = r8.getCameraExecutor()
            r15 = r8
            java.util.concurrent.Executor r15 = (java.util.concurrent.Executor) r15
            r1.L$0 = r5
            r1.L$1 = r5
            r1.L$2 = r13
            r1.L$3 = r3
            r1.L$4 = r11
            r1.L$5 = r15
            r1.I$0 = r9
            r1.Z$0 = r0
            r1.label = r4
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            kotlinx.coroutines.CancellableContinuationImpl r14 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r1)
            r14.<init>(r8, r4)
            r14.initCancellability()
            r12 = r14
            kotlinx.coroutines.CancellableContinuation r12 = (kotlinx.coroutines.CancellableContinuation) r12
            if (r9 == 0) goto L_0x0100
            android.media.MediaActionSound r6 = new android.media.MediaActionSound
            r6.<init>()
        L_0x0100:
            r10 = r6
            if (r10 == 0) goto L_0x0106
            r10.load(r7)
        L_0x0106:
            androidx.camera.core.ImageCapture$OutputFileOptions$Builder r4 = new androidx.camera.core.ImageCapture$OutputFileOptions$Builder
            r4.<init>((java.io.File) r13)
            androidx.camera.core.ImageCapture$Metadata r6 = new androidx.camera.core.ImageCapture$Metadata
            r6.<init>()
            android.location.Location r7 = r3.getLocation()
            r17 = r1
            r19 = r2
            if (r7 == 0) goto L_0x0152
            double r1 = r7.getLatitude()
            double r7 = r7.getLongitude()
            r18 = r14
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r5
            java.lang.String r5 = "Setting Photo Location to "
            r14.<init>(r5)
            java.lang.StringBuilder r1 = r14.append(r1)
            java.lang.String r2 = ", "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r7)
            java.lang.String r2 = "..."
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "ImageCapture"
            android.util.Log.i(r2, r1)
            android.location.Location r1 = r3.getLocation()
            r6.setLocation(r1)
            goto L_0x0156
        L_0x0152:
            r16 = r5
            r18 = r14
        L_0x0156:
            r6.setReversedHorizontal(r0)
            r4.setMetadata(r6)
            androidx.camera.core.ImageCapture$OutputFileOptions r1 = r4.build()
            java.lang.String r2 = "build(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.mrousavy.camera.core.extensions.ImageCapture_takePictureKt$takePicture$2$1 r2 = new com.mrousavy.camera.core.extensions.ImageCapture_takePictureKt$takePicture$2$1
            r8 = r2
            r3 = r18
            r14 = r1
            r8.<init>(r9, r10, r11, r12, r13, r14)
            androidx.camera.core.ImageCapture$OnImageSavedCallback r2 = (androidx.camera.core.ImageCapture.OnImageSavedCallback) r2
            r4 = r16
            r4.m146lambda$takePicture$2$androidxcameracoreImageCapture(r1, r15, r2)
            java.lang.Object r1 = r3.getResult()
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r1 != r2) goto L_0x0182
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r17)
        L_0x0182:
            r2 = r19
            if (r1 != r2) goto L_0x0187
            return r2
        L_0x0187:
            r7 = r0
            r0 = r1
            r1 = r4
        L_0x018a:
            com.mrousavy.camera.core.extensions.PhotoFileInfo r0 = (com.mrousavy.camera.core.extensions.PhotoFileInfo) r0
            com.mrousavy.camera.core.utils.FileUtils$Companion r2 = com.mrousavy.camera.core.utils.FileUtils.Companion
            java.net.URI r3 = r0.getUri()
            java.lang.String r3 = r3.getPath()
            java.lang.String r4 = "getPath(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            android.util.Size r2 = r2.getImageSize(r3)
            int r1 = r1.getTargetRotation()
            com.mrousavy.camera.core.types.Orientation$Companion r3 = com.mrousavy.camera.core.types.Orientation.Companion
            com.mrousavy.camera.core.types.Orientation r6 = r3.fromSurfaceRotation(r1)
            com.mrousavy.camera.core.Photo r1 = new com.mrousavy.camera.core.Photo
            java.net.URI r0 = r0.getUri()
            java.lang.String r3 = r0.getPath()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            int r4 = r2.getWidth()
            int r5 = r2.getHeight()
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7)
            return r1
        L_0x01c3:
            com.mrousavy.camera.core.PhotoNotEnabledError r0 = new com.mrousavy.camera.core.PhotoNotEnabledError
            r0.<init>()
            throw r0
        L_0x01c9:
            com.mrousavy.camera.core.PhotoNotEnabledError r0 = new com.mrousavy.camera.core.PhotoNotEnabledError
            r0.<init>()
            throw r0
        L_0x01cf:
            com.mrousavy.camera.core.CameraNotReadyError r0 = new com.mrousavy.camera.core.CameraNotReadyError
            r0.<init>()
            throw r0
        L_0x01d5:
            com.mrousavy.camera.core.CameraNotReadyError r0 = new com.mrousavy.camera.core.CameraNotReadyError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.CameraSession_PhotoKt.takePhoto(com.mrousavy.camera.core.CameraSession, com.mrousavy.camera.core.types.TakePhotoOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final boolean isSilent(AudioManager audioManager) {
        return audioManager.getRingerMode() != 2;
    }
}
