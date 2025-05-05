package com.mrousavy.camera.frameprocessors;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 #2\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0003J&\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0017H\u0007J!\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\rH J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0018\u0010 \u001a\u00020\u001f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\"H\u0007R\u0011\u0010\u0005\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/mrousavy/camera/frameprocessors/VisionCameraProxy;", "", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "context", "getContext", "()Lcom/facebook/react/bridge/ReactApplicationContext;", "mContext", "Ljava/lang/ref/WeakReference;", "mHybridData", "Lcom/facebook/jni/HybridData;", "mScheduler", "Lcom/mrousavy/camera/frameprocessors/VisionCameraScheduler;", "findCameraViewById", "Lcom/mrousavy/camera/react/CameraView;", "viewId", "", "initFrameProcessorPlugin", "Lcom/mrousavy/camera/frameprocessors/FrameProcessorPlugin;", "name", "", "options", "", "initHybrid", "jsContext", "", "jsCallInvokerHolder", "Lcom/facebook/react/turbomodule/core/CallInvokerHolderImpl;", "scheduler", "removeFrameProcessor", "", "setFrameProcessor", "frameProcessor", "Lcom/mrousavy/camera/frameprocessors/FrameProcessor;", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VisionCameraProxy.kt */
public final class VisionCameraProxy {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VisionCameraProxy";
    private WeakReference<ReactApplicationContext> mContext;
    private HybridData mHybridData;
    private VisionCameraScheduler mScheduler;
    private final ReactApplicationContext reactContext;

    private final native HybridData initHybrid(long j, CallInvokerHolderImpl callInvokerHolderImpl, VisionCameraScheduler visionCameraScheduler);

    public VisionCameraProxy(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        this.reactContext = reactApplicationContext;
        CallInvokerHolder jSCallInvokerHolder = getContext().getCatalystInstance().getJSCallInvokerHolder();
        Intrinsics.checkNotNull(jSCallInvokerHolder, "null cannot be cast to non-null type com.facebook.react.turbomodule.core.CallInvokerHolderImpl");
        CallInvokerHolderImpl callInvokerHolderImpl = (CallInvokerHolderImpl) jSCallInvokerHolder;
        JavaScriptContextHolder javaScriptContextHolder = getContext().getJavaScriptContextHolder();
        if (javaScriptContextHolder != null) {
            long j = javaScriptContextHolder.get();
            this.mScheduler = new VisionCameraScheduler();
            this.mContext = new WeakReference<>(getContext());
            this.mHybridData = initHybrid(j, callInvokerHolderImpl, this.mScheduler);
            return;
        }
        throw new Error("JSI Runtime is null! VisionCamera does not yet support bridgeless mode..");
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/mrousavy/camera/frameprocessors/VisionCameraProxy$Companion;", "", "()V", "TAG", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VisionCameraProxy.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final ReactApplicationContext getContext() {
        return this.reactContext;
    }

    /* JADX WARNING: type inference failed for: r2v4, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.mrousavy.camera.react.CameraView findCameraViewById(int r6) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Finding view "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r6)
            java.lang.String r1 = "..."
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "VisionCameraProxy"
            android.util.Log.d(r1, r0)
            java.lang.ref.WeakReference<com.facebook.react.bridge.ReactApplicationContext> r0 = r5.mContext
            java.lang.Object r0 = r0.get()
            com.facebook.react.bridge.ReactApplicationContext r0 = (com.facebook.react.bridge.ReactApplicationContext) r0
            r2 = 0
            if (r0 == 0) goto L_0x0033
            com.facebook.react.bridge.ReactContext r0 = (com.facebook.react.bridge.ReactContext) r0
            com.facebook.react.bridge.UIManager r0 = com.facebook.react.uimanager.UIManagerHelper.getUIManager(r0, r6)
            if (r0 == 0) goto L_0x0031
            android.view.View r2 = r0.resolveView(r6)
        L_0x0031:
            com.mrousavy.camera.react.CameraView r2 = (com.mrousavy.camera.react.CameraView) r2
        L_0x0033:
            java.lang.String r0 = "!"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            if (r2 == 0) goto L_0x003f
            java.lang.String r4 = "Found view "
            r3.<init>(r4)
            goto L_0x0044
        L_0x003f:
            java.lang.String r4 = "Couldn't find view "
            r3.<init>(r4)
        L_0x0044:
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
            if (r2 == 0) goto L_0x0056
            return r2
        L_0x0056:
            com.mrousavy.camera.core.ViewNotFoundError r0 = new com.mrousavy.camera.core.ViewNotFoundError
            r0.<init>(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.frameprocessors.VisionCameraProxy.findCameraViewById(int):com.mrousavy.camera.react.CameraView");
    }

    public final void setFrameProcessor(int i, FrameProcessor frameProcessor) {
        Intrinsics.checkNotNullParameter(frameProcessor, "frameProcessor");
        UiThreadUtil.runOnUiThread(new VisionCameraProxy$$ExternalSyntheticLambda0(this, i, frameProcessor));
    }

    /* access modifiers changed from: private */
    public static final void setFrameProcessor$lambda$0(VisionCameraProxy visionCameraProxy, int i, FrameProcessor frameProcessor) {
        Intrinsics.checkNotNullParameter(visionCameraProxy, "this$0");
        Intrinsics.checkNotNullParameter(frameProcessor, "$frameProcessor");
        visionCameraProxy.findCameraViewById(i).setFrameProcessor$react_native_vision_camera_release(frameProcessor);
    }

    public final void removeFrameProcessor(int i) {
        UiThreadUtil.runOnUiThread(new VisionCameraProxy$$ExternalSyntheticLambda1(this, i));
    }

    /* access modifiers changed from: private */
    public static final void removeFrameProcessor$lambda$1(VisionCameraProxy visionCameraProxy, int i) {
        Intrinsics.checkNotNullParameter(visionCameraProxy, "this$0");
        visionCameraProxy.findCameraViewById(i).setFrameProcessor$react_native_vision_camera_release((FrameProcessor) null);
    }

    public final FrameProcessorPlugin initFrameProcessorPlugin(String str, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, "options");
        return FrameProcessorPluginRegistry.getPlugin(str, this, map);
    }
}
