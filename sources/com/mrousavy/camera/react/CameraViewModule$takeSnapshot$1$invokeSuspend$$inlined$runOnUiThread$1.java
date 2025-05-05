package com.mrousavy.camera.react;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.mrousavy.camera.core.types.TakeSnapshotOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "com/mrousavy/camera/core/utils/RunOnUiThreadKt$runOnUiThread$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: runOnUiThread.kt */
public final class CameraViewModule$takeSnapshot$1$invokeSuspend$$inlined$runOnUiThread$1 implements Runnable {
    final /* synthetic */ ReadableMap $jsOptions$inlined;
    final /* synthetic */ Promise $promise$inlined;
    final /* synthetic */ CameraView $view$inlined;
    final /* synthetic */ CameraViewModule this$0;

    public CameraViewModule$takeSnapshot$1$invokeSuspend$$inlined$runOnUiThread$1(CameraViewModule cameraViewModule, ReadableMap readableMap, CameraView cameraView, Promise promise) {
        this.this$0 = cameraViewModule;
        this.$jsOptions$inlined = readableMap;
        this.$view$inlined = cameraView;
        this.$promise$inlined = promise;
    }

    public final void run() {
        try {
            TakeSnapshotOptions.Companion companion = TakeSnapshotOptions.Companion;
            ReactApplicationContext access$getReactApplicationContext = this.this$0.getReactApplicationContext();
            Intrinsics.checkNotNullExpressionValue(access$getReactApplicationContext, "access$getReactApplicationContext(...)");
            this.$promise$inlined.resolve(CameraView_TakeSnapshotKt.takeSnapshot(this.$view$inlined, companion.fromJSValue(access$getReactApplicationContext, this.$jsOptions$inlined)));
        } catch (Throwable th) {
            this.$promise$inlined.reject(th);
        }
    }
}
