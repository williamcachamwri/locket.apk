package com.mrousavy.camera.react;

import android.util.Log;
import androidx.camera.extensions.ExtensionsManager;
import androidx.camera.lifecycle.ProcessCameraProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.mrousavy.camera.core.extensions.ListenableFuture_awaitKt;
import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.mrousavy.camera.react.CameraDevicesManager$initialize$1", f = "CameraDevicesManager.kt", i = {}, l = {68, 70}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CameraDevicesManager.kt */
final class CameraDevicesManager$initialize$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ CameraDevicesManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraDevicesManager$initialize$1(CameraDevicesManager cameraDevicesManager, Continuation<? super CameraDevicesManager$initialize$1> continuation) {
        super(2, continuation);
        this.this$0 = cameraDevicesManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CameraDevicesManager$initialize$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraDevicesManager$initialize$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CameraDevicesManager cameraDevicesManager;
        CameraDevicesManager cameraDevicesManager2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Log.i("CameraDevices", "Initializing ProcessCameraProvider...");
            cameraDevicesManager2 = this.this$0;
            this.L$0 = cameraDevicesManager2;
            this.label = 1;
            obj = ListenableFuture_awaitKt.await(ProcessCameraProvider.Companion.getInstance(this.this$0.reactContext), this.this$0.executor, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            cameraDevicesManager2 = (CameraDevicesManager) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            cameraDevicesManager = (CameraDevicesManager) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                cameraDevicesManager.extensionsManager = (ExtensionsManager) obj;
                Log.i("CameraDevices", "Successfully initialized!");
            } catch (Throwable th) {
                SentryLogcatAdapter.e("CameraDevices", "Failed to initialize ProcessCameraProvider/ExtensionsManager! Error: " + th.getMessage(), th);
            }
            this.this$0.sendAvailableDevicesChangedEvent();
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        cameraDevicesManager2.cameraProvider = (ProcessCameraProvider) obj;
        Log.i("CameraDevices", "Initializing ExtensionsManager...");
        CameraDevicesManager cameraDevicesManager3 = this.this$0;
        ProcessCameraProvider access$getCameraProvider$p = this.this$0.cameraProvider;
        Intrinsics.checkNotNull(access$getCameraProvider$p);
        ListenableFuture<ExtensionsManager> instanceAsync = ExtensionsManager.getInstanceAsync(cameraDevicesManager3.reactContext, access$getCameraProvider$p);
        Intrinsics.checkNotNullExpressionValue(instanceAsync, "getInstanceAsync(...)");
        this.L$0 = cameraDevicesManager3;
        this.label = 2;
        Object await = ListenableFuture_awaitKt.await(instanceAsync, this.this$0.executor, this);
        if (await == coroutine_suspended) {
            return coroutine_suspended;
        }
        cameraDevicesManager = cameraDevicesManager3;
        obj = await;
        cameraDevicesManager.extensionsManager = (ExtensionsManager) obj;
        Log.i("CameraDevices", "Successfully initialized!");
        this.this$0.sendAvailableDevicesChangedEvent();
        return Unit.INSTANCE;
    }
}
