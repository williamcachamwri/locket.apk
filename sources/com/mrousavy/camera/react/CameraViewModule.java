package com.mrousavy.camera.react;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.uimanager.UIManagerHelper;
import com.mrousavy.camera.core.CameraQueues;
import com.mrousavy.camera.core.ViewNotFoundError;
import com.mrousavy.camera.core.types.PermissionStatus;
import com.mrousavy.camera.frameprocessors.VisionCameraInstaller;
import com.mrousavy.camera.frameprocessors.VisionCameraProxy;
import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 .2\u00020\u0001:\u0001.B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u0014J \u0010\u0015\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\b\u0010\u0018\u001a\u00020\nH\u0007J\b\u0010\u0019\u001a\u00020\nH\u0007J\b\u0010\u001a\u001a\u00020\nH\u0007J\b\u0010\u001b\u001a\u00020\nH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u001e\u001a\u00020\bH\u0007J\b\u0010\u001f\u001a\u00020\fH\u0016J\u0018\u0010 \u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010!\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\"\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010#\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0018\u0010$\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010%\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J \u0010&\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020)H\u0007J\u0018\u0010*\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J \u0010+\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J \u0010-\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0010H\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/mrousavy/camera/react/CameraViewModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "backgroundCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "canRequestPermission", "", "permission", "", "cancelRecording", "", "viewTag", "", "promise", "Lcom/facebook/react/bridge/Promise;", "findCameraView", "Lcom/mrousavy/camera/react/CameraView;", "viewId", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "focus", "point", "Lcom/facebook/react/bridge/ReadableMap;", "getCameraPermissionStatus", "getLocationPermissionStatus", "getMicrophonePermissionStatus", "getName", "getPermission", "Lcom/mrousavy/camera/core/types/PermissionStatus;", "installFrameProcessorBindings", "invalidate", "pauseRecording", "requestCameraPermission", "requestLocationPermission", "requestMicrophonePermission", "requestPermission", "resumeRecording", "startRecording", "jsOptions", "onRecordCallback", "Lcom/facebook/react/bridge/Callback;", "stopRecording", "takePhoto", "options", "takeSnapshot", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@ReactModule(name = "CameraView")
/* compiled from: CameraViewModule.kt */
public final class CameraViewModule extends ReactContextBaseJavaModule {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "CameraView";
    /* access modifiers changed from: private */
    public static int sharedRequestCode = 10;
    private final CoroutineScope backgroundCoroutineScope = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(CameraQueues.Companion.getCameraExecutor()));

    public String getName() {
        return "CameraView";
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/mrousavy/camera/react/CameraViewModule$Companion;", "", "()V", "TAG", "", "sharedRequestCode", "", "getSharedRequestCode", "()I", "setSharedRequestCode", "(I)V", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraViewModule.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getSharedRequestCode() {
            return CameraViewModule.sharedRequestCode;
        }

        public final void setSharedRequestCode(int i) {
            CameraViewModule.sharedRequestCode = i;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CameraViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
    }

    static {
        try {
            System.loadLibrary("VisionCamera");
        } catch (UnsatisfiedLinkError e) {
            SentryLogcatAdapter.e(VisionCameraProxy.TAG, "Failed to load VisionCamera C++ library!", e);
            throw e;
        }
    }

    public void invalidate() {
        super.invalidate();
        if (CoroutineScopeKt.isActive(this.backgroundCoroutineScope)) {
            CoroutineScopeKt.cancel$default(this.backgroundCoroutineScope, "CameraViewModule has been destroyed.", (Throwable) null, 2, (Object) null);
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public final boolean installFrameProcessorBindings() {
        try {
            ReactApplicationContext reactApplicationContext = getReactApplicationContext();
            Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
            VisionCameraInstaller.install(new VisionCameraProxy(reactApplicationContext));
            return true;
        } catch (Error e) {
            SentryLogcatAdapter.e("CameraView", "Failed to install Frame Processor JSI Bindings!", e);
            return false;
        }
    }

    @ReactMethod
    public final void takePhoto(int i, ReadableMap readableMap, Promise promise) {
        Intrinsics.checkNotNullParameter(readableMap, "options");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.backgroundCoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CameraViewModule$takePhoto$1(this, i, promise, readableMap, (Continuation<? super CameraViewModule$takePhoto$1>) null), 3, (Object) null);
    }

    @ReactMethod
    public final void takeSnapshot(int i, ReadableMap readableMap, Promise promise) {
        Intrinsics.checkNotNullParameter(readableMap, "jsOptions");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.backgroundCoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CameraViewModule$takeSnapshot$1(this, i, readableMap, promise, (Continuation<? super CameraViewModule$takeSnapshot$1>) null), 3, (Object) null);
    }

    @ReactMethod
    public final void startRecording(int i, ReadableMap readableMap, Callback callback) {
        Intrinsics.checkNotNullParameter(readableMap, "jsOptions");
        Intrinsics.checkNotNullParameter(callback, "onRecordCallback");
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.backgroundCoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CameraViewModule$startRecording$1(this, i, readableMap, callback, (Continuation<? super CameraViewModule$startRecording$1>) null), 3, (Object) null);
    }

    @ReactMethod
    public final void pauseRecording(int i, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.backgroundCoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CameraViewModule$pauseRecording$1(promise, this, i, (Continuation<? super CameraViewModule$pauseRecording$1>) null), 3, (Object) null);
    }

    @ReactMethod
    public final void resumeRecording(int i, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.backgroundCoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CameraViewModule$resumeRecording$1(this, i, promise, (Continuation<? super CameraViewModule$resumeRecording$1>) null), 3, (Object) null);
    }

    @ReactMethod
    public final void stopRecording(int i, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.backgroundCoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CameraViewModule$stopRecording$1(this, i, promise, (Continuation<? super CameraViewModule$stopRecording$1>) null), 3, (Object) null);
    }

    @ReactMethod
    public final void cancelRecording(int i, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.backgroundCoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CameraViewModule$cancelRecording$1(this, i, promise, (Continuation<? super CameraViewModule$cancelRecording$1>) null), 3, (Object) null);
    }

    @ReactMethod
    public final void focus(int i, ReadableMap readableMap, Promise promise) {
        Intrinsics.checkNotNullParameter(readableMap, "point");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.backgroundCoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CameraViewModule$focus$1(this, i, promise, readableMap, (Continuation<? super CameraViewModule$focus$1>) null), 3, (Object) null);
    }

    private final boolean canRequestPermission(String str) {
        Activity currentActivity = getCurrentActivity();
        PermissionAwareActivity permissionAwareActivity = currentActivity instanceof PermissionAwareActivity ? (PermissionAwareActivity) currentActivity : null;
        if (permissionAwareActivity != null) {
            return permissionAwareActivity.shouldShowRequestPermissionRationale(str);
        }
        return false;
    }

    private final PermissionStatus getPermission(String str) {
        PermissionStatus fromPermissionStatus = PermissionStatus.Companion.fromPermissionStatus(ContextCompat.checkSelfPermission(getReactApplicationContext(), str));
        return (fromPermissionStatus != PermissionStatus.DENIED || !canRequestPermission(str)) ? fromPermissionStatus : PermissionStatus.NOT_DETERMINED;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public final String getCameraPermissionStatus() {
        return getPermission("android.permission.CAMERA").getUnionValue();
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public final String getMicrophonePermissionStatus() {
        return getPermission("android.permission.RECORD_AUDIO").getUnionValue();
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public final String getLocationPermissionStatus() {
        PermissionStatus permission = getPermission("android.permission.ACCESS_FINE_LOCATION");
        if (permission == PermissionStatus.GRANTED) {
            return permission.getUnionValue();
        }
        return getPermission("android.permission.ACCESS_COARSE_LOCATION").getUnionValue();
    }

    private final void requestPermission(String str, Promise promise) {
        Activity currentActivity = getReactApplicationContext().getCurrentActivity();
        if (currentActivity instanceof PermissionAwareActivity) {
            int i = sharedRequestCode;
            sharedRequestCode = i + 1;
            ((PermissionAwareActivity) currentActivity).requestPermissions(new String[]{str}, i, new CameraViewModule$$ExternalSyntheticLambda0(i, promise));
            return;
        }
        promise.reject("NO_ACTIVITY", "No PermissionAwareActivity was found! Make sure the app has launched before calling this function.");
    }

    /* access modifiers changed from: private */
    public static final boolean requestPermission$lambda$1(int i, Promise promise, int i2, String[] strArr, int[] iArr) {
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Intrinsics.checkNotNullParameter(strArr, "<anonymous parameter 1>");
        Intrinsics.checkNotNullParameter(iArr, "grantResults");
        if (i2 != i) {
            return false;
        }
        promise.resolve(PermissionStatus.Companion.fromPermissionStatus((iArr.length == 0) ^ true ? iArr[0] : -1).getUnionValue());
        return true;
    }

    @ReactMethod
    public final void requestCameraPermission(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        requestPermission("android.permission.CAMERA", promise);
    }

    @ReactMethod
    public final void requestMicrophonePermission(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        requestPermission("android.permission.RECORD_AUDIO", promise);
    }

    @ReactMethod
    public final void requestLocationPermission(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        requestPermission("android.permission.ACCESS_FINE_LOCATION", promise);
    }

    /* access modifiers changed from: private */
    public final Object findCameraView(int i, Continuation<? super CameraView> continuation) {
        if (UiThreadUtil.isOnUiThread()) {
            Log.d("CameraView", "Finding view " + i + "...");
            ReactApplicationContext access$getReactApplicationContext = getReactApplicationContext();
            if (access$getReactApplicationContext != null) {
                Intrinsics.checkNotNull(access$getReactApplicationContext);
                UIManager uIManager = UIManagerHelper.getUIManager(access$getReactApplicationContext, 1);
                if (uIManager != null) {
                    Intrinsics.checkNotNull(uIManager);
                    View resolveView = uIManager.resolveView(i);
                    CameraView cameraView = resolveView instanceof CameraView ? (CameraView) resolveView : null;
                    if (cameraView != null) {
                        Log.d("CameraView", "Found view " + i + "!");
                        return cameraView;
                    }
                    throw new ViewNotFoundError(i);
                }
                throw new Error("UIManager not found!");
            }
            throw new Error("React Context was null!");
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        UiThreadUtil.runOnUiThread(new CameraViewModule$findCameraView$$inlined$runOnUiThreadAndWait$1(cancellableContinuationImpl, i, this));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
