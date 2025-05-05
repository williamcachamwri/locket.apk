package com.mrousavy.camera.react;

import android.hardware.camera2.CameraManager;
import android.os.Handler;
import androidx.camera.core.CameraInfo;
import androidx.camera.extensions.ExtensionsManager;
import androidx.camera.lifecycle.ProcessCameraProvider;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.mrousavy.camera.core.CameraDeviceDetails;
import com.mrousavy.camera.core.CameraQueues;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000]\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0006\u0018\u0000 \"2\u00020\u0001:\u0001\"B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0016\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0015H\u0016J\b\u0010\u001c\u001a\u00020\u0013H\u0016J\b\u0010\u001d\u001a\u00020\u0013H\u0016J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020 H\u0007J\u0006\u0010!\u001a\u00020\u0013R\u0010\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/mrousavy/camera/react/CameraDevicesManager;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "callback", "com/mrousavy/camera/react/CameraDevicesManager$callback$1", "Lcom/mrousavy/camera/react/CameraDevicesManager$callback$1;", "cameraManager", "Landroid/hardware/camera2/CameraManager;", "cameraProvider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "executor", "Ljava/util/concurrent/ExecutorService;", "extensionsManager", "Landroidx/camera/extensions/ExtensionsManager;", "addListener", "", "eventName", "", "getConstants", "", "", "getDevicesJson", "Lcom/facebook/react/bridge/ReadableArray;", "getName", "initialize", "invalidate", "removeListeners", "count", "", "sendAvailableDevicesChangedEvent", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraDevicesManager.kt */
public final class CameraDevicesManager extends ReactContextBaseJavaModule {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "CameraDevices";
    private final CameraDevicesManager$callback$1 callback = new CameraDevicesManager$callback$1(this);
    /* access modifiers changed from: private */
    public final CameraManager cameraManager;
    /* access modifiers changed from: private */
    public ProcessCameraProvider cameraProvider;
    private final CoroutineScope coroutineScope;
    /* access modifiers changed from: private */
    public final ExecutorService executor;
    /* access modifiers changed from: private */
    public ExtensionsManager extensionsManager;
    /* access modifiers changed from: private */
    public final ReactApplicationContext reactContext;

    @ReactMethod
    public final void addListener(String str) {
        Intrinsics.checkNotNullParameter(str, "eventName");
    }

    public String getName() {
        return TAG;
    }

    @ReactMethod
    public final void removeListeners(int i) {
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/mrousavy/camera/react/CameraDevicesManager$Companion;", "", "()V", "TAG", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraDevicesManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CameraDevicesManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        this.reactContext = reactApplicationContext;
        ExecutorService cameraExecutor = CameraQueues.Companion.getCameraExecutor();
        this.executor = cameraExecutor;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(cameraExecutor));
        Object systemService = reactApplicationContext.getSystemService("camera");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.camera2.CameraManager");
        this.cameraManager = (CameraManager) systemService;
    }

    public void initialize() {
        super.initialize();
        this.cameraManager.registerAvailabilityCallback(this.callback, (Handler) null);
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CameraDevicesManager$initialize$1(this, (Continuation<? super CameraDevicesManager$initialize$1>) null), 3, (Object) null);
    }

    public void invalidate() {
        this.cameraManager.unregisterAvailabilityCallback(this.callback);
        super.invalidate();
    }

    private final ReadableArray getDevicesJson() {
        WritableArray createArray = Arguments.createArray();
        ProcessCameraProvider processCameraProvider = this.cameraProvider;
        if (processCameraProvider == null) {
            Intrinsics.checkNotNull(createArray);
            return createArray;
        }
        ExtensionsManager extensionsManager2 = this.extensionsManager;
        if (extensionsManager2 == null) {
            Intrinsics.checkNotNull(createArray);
            return createArray;
        }
        for (CameraInfo cameraDeviceDetails : processCameraProvider.getAvailableCameraInfos()) {
            createArray.pushMap(new CameraDeviceDetails(cameraDeviceDetails, extensionsManager2).toMap());
        }
        Intrinsics.checkNotNull(createArray);
        return createArray;
    }

    public final void sendAvailableDevicesChangedEvent() {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("CameraDevicesChanged", getDevicesJson());
    }

    public Map<String, Object> getConstants() {
        ReadableArray devicesJson = getDevicesJson();
        HashMap<String, Object> hashMap = null;
        ReadableMap map = devicesJson.size() > 0 ? devicesJson.getMap(0) : null;
        Pair[] pairArr = new Pair[2];
        pairArr[0] = TuplesKt.to("availableCameraDevices", devicesJson);
        if (map != null) {
            hashMap = map.toHashMap();
        }
        pairArr[1] = TuplesKt.to("userPreferredCameraDevice", hashMap);
        return MapsKt.mutableMapOf(pairArr);
    }
}
