package com.mrousavy.camera.react;

import android.util.Log;
import android.view.View;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.uimanager.UIManagerHelper;
import com.mrousavy.camera.core.ViewNotFoundError;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, d2 = {"<anonymous>", "", "T", "run", "com/mrousavy/camera/core/utils/RunOnUiThreadKt$runOnUiThreadAndWait$2$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: runOnUiThread.kt */
public final class CameraViewModule$findCameraView$$inlined$runOnUiThreadAndWait$1 implements Runnable {
    final /* synthetic */ CancellableContinuation $continuation;
    final /* synthetic */ int $viewId$inlined;
    final /* synthetic */ CameraViewModule this$0;

    public CameraViewModule$findCameraView$$inlined$runOnUiThreadAndWait$1(CancellableContinuation cancellableContinuation, int i, CameraViewModule cameraViewModule) {
        this.$continuation = cancellableContinuation;
        this.$viewId$inlined = i;
        this.this$0 = cameraViewModule;
    }

    public final void run() {
        if (!this.$continuation.isCancelled()) {
            Log.d("CameraView", "Finding view " + this.$viewId$inlined + "...");
            ReactApplicationContext access$getReactApplicationContext = this.this$0.getReactApplicationContext();
            if (access$getReactApplicationContext != null) {
                Intrinsics.checkNotNull(access$getReactApplicationContext);
                UIManager uIManager = UIManagerHelper.getUIManager(access$getReactApplicationContext, 1);
                if (uIManager != null) {
                    Intrinsics.checkNotNull(uIManager);
                    View resolveView = uIManager.resolveView(this.$viewId$inlined);
                    CameraView cameraView = resolveView instanceof CameraView ? (CameraView) resolveView : null;
                    if (cameraView != null) {
                        Log.d("CameraView", "Found view " + this.$viewId$inlined + "!");
                        Result.Companion companion = Result.Companion;
                        this.$continuation.resumeWith(Result.m2444constructorimpl(cameraView));
                        return;
                    }
                    throw new ViewNotFoundError(this.$viewId$inlined);
                }
                throw new Error("UIManager not found!");
            }
            throw new Error("React Context was null!");
        }
        throw new CancellationException();
    }
}
