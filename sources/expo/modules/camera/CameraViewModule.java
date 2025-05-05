package expo.modules.camera;

import androidx.exifinterface.media.ExifInterface;
import androidx.tracing.Trace;
import expo.modules.interfaces.permissions.Permissions;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.Queues;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.views.ConcreteViewProp;
import expo.modules.kotlin.views.ViewDefinitionBuilder;
import java.io.File;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.coroutines.DebugKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lexpo/modules/camera/CameraViewModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "cacheDirectory", "Ljava/io/File;", "getCacheDirectory", "()Ljava/io/File;", "permissionsManager", "Lexpo/modules/interfaces/permissions/Permissions;", "getPermissionsManager", "()Lexpo/modules/interfaces/permissions/Permissions;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "findView", "Lexpo/modules/camera/ExpoCameraView;", "viewTag", "", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraViewModule.kt */
public final class CameraViewModule extends Module {
    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        AsyncFunction asyncFunction3;
        AsyncFunction asyncFunction4;
        AsyncFunction asyncFunction5;
        AsyncFunction asyncFunction6;
        AsyncFunction asyncFunction7;
        AsyncFunction asyncFunction8;
        AsyncFunction asyncFunction9;
        AsyncFunction asyncFunction10;
        AsyncFunction asyncFunction11;
        AsyncFunction asyncFunction12;
        AsyncFunction asyncFunction13;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoCamera");
            moduleDefinitionBuilder.Constants((Pair<String, ? extends Object>[]) new Pair[]{TuplesKt.to("Type", MapsKt.mapOf(TuplesKt.to("front", 1), TuplesKt.to("back", 0))), TuplesKt.to("FlashMode", MapsKt.mapOf(TuplesKt.to(DebugKt.DEBUG_PROPERTY_VALUE_OFF, 0), TuplesKt.to("on", 1), TuplesKt.to("auto", 3), TuplesKt.to("torch", 2))), TuplesKt.to("AutoFocus", MapsKt.mapOf(TuplesKt.to("on", true), TuplesKt.to(DebugKt.DEBUG_PROPERTY_VALUE_OFF, false))), TuplesKt.to(ExifInterface.TAG_WHITE_BALANCE, MapsKt.mapOf(TuplesKt.to("auto", 0), TuplesKt.to("cloudy", 1), TuplesKt.to("sunny", 2), TuplesKt.to("shadow", 3), TuplesKt.to("fluorescent", 4), TuplesKt.to("incandescent", 5))), TuplesKt.to("VideoQuality", MapsKt.mapOf(TuplesKt.to("2160p", 0), TuplesKt.to("1080p", 1), TuplesKt.to("720p", 2), TuplesKt.to("480p", 3), TuplesKt.to("4:3", 4)))});
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Integer.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("pausePreview", new AnyType[0], new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("pausePreview", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$2.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("pausePreview", asyncFunction);
            asyncFunction.runOnQueue(Queues.MAIN);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Integer.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("resumePreview", new AnyType[0], new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$4(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("resumePreview", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$5.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$6(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("resumePreview", asyncFunction2);
            asyncFunction2.runOnQueue(Queues.MAIN);
            ObjectDefinitionBuilder objectDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction3 = new AsyncFunctionWithPromiseComponent("takePicture", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(PictureOptions.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$7.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$8.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$9(this));
            } else {
                asyncFunction3 = new AsyncFunctionComponent("takePicture", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(PictureOptions.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$10.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$11.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$12.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$13(this));
            }
            objectDefinitionBuilder3.getAsyncFunctions().put("takePicture", asyncFunction3);
            asyncFunction3.runOnQueue(Queues.MAIN);
            ObjectDefinitionBuilder objectDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction4 = new AsyncFunctionWithPromiseComponent("record", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(RecordingOptions.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$14.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$15.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$16(this));
            } else {
                asyncFunction4 = new AsyncFunctionComponent("record", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(RecordingOptions.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$17.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$18.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$19.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$20(this));
            }
            objectDefinitionBuilder4.getAsyncFunctions().put("record", asyncFunction4);
            asyncFunction4.runOnQueue(Queues.MAIN);
            ObjectDefinitionBuilder objectDefinitionBuilder5 = moduleDefinitionBuilder;
            if (Integer.class == Promise.class) {
                asyncFunction5 = new AsyncFunctionWithPromiseComponent("stopRecording", new AnyType[0], new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$21(this));
            } else {
                asyncFunction5 = new AsyncFunctionComponent("stopRecording", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$22.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$23(this));
            }
            objectDefinitionBuilder5.getAsyncFunctions().put("stopRecording", asyncFunction5);
            asyncFunction5.runOnQueue(Queues.MAIN);
            ObjectDefinitionBuilder objectDefinitionBuilder6 = moduleDefinitionBuilder;
            if (Integer.class == Promise.class) {
                asyncFunction6 = new AsyncFunctionWithPromiseComponent("getSupportedRatios", new AnyType[0], new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$24(this));
            } else {
                asyncFunction6 = new AsyncFunctionComponent("getSupportedRatios", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$25.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$26(this));
            }
            objectDefinitionBuilder6.getAsyncFunctions().put("getSupportedRatios", asyncFunction6);
            asyncFunction6.runOnQueue(Queues.MAIN);
            ObjectDefinitionBuilder objectDefinitionBuilder7 = moduleDefinitionBuilder;
            if (Integer.class == Promise.class) {
                asyncFunction7 = new AsyncFunctionWithPromiseComponent("getAvailablePictureSizes", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$27.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$28(this));
            } else {
                asyncFunction7 = new AsyncFunctionComponent("getAvailablePictureSizes", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$29.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$30.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$31(this));
            }
            objectDefinitionBuilder7.getAsyncFunctions().put("getAvailablePictureSizes", asyncFunction7);
            asyncFunction7.runOnQueue(Queues.MAIN);
            ObjectDefinitionBuilder objectDefinitionBuilder8 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction8 = new AsyncFunctionWithPromiseComponent("requestPermissionsAsync", new AnyType[0], new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$32(this));
            } else {
                asyncFunction8 = new AsyncFunctionComponent("requestPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$33.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$34(this));
            }
            objectDefinitionBuilder8.getAsyncFunctions().put("requestPermissionsAsync", asyncFunction8);
            ObjectDefinitionBuilder objectDefinitionBuilder9 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction9 = new AsyncFunctionWithPromiseComponent("requestCameraPermissionsAsync", new AnyType[0], new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$35(this));
            } else {
                asyncFunction9 = new AsyncFunctionComponent("requestCameraPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$36.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$37(this));
            }
            objectDefinitionBuilder9.getAsyncFunctions().put("requestCameraPermissionsAsync", asyncFunction9);
            ObjectDefinitionBuilder objectDefinitionBuilder10 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction10 = new AsyncFunctionWithPromiseComponent("requestMicrophonePermissionsAsync", new AnyType[0], new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$38(this));
            } else {
                asyncFunction10 = new AsyncFunctionComponent("requestMicrophonePermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$39.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$40(this));
            }
            objectDefinitionBuilder10.getAsyncFunctions().put("requestMicrophonePermissionsAsync", asyncFunction10);
            ObjectDefinitionBuilder objectDefinitionBuilder11 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction11 = new AsyncFunctionWithPromiseComponent("getPermissionsAsync", new AnyType[0], new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$41(this));
            } else {
                asyncFunction11 = new AsyncFunctionComponent("getPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$42.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$43(this));
            }
            objectDefinitionBuilder11.getAsyncFunctions().put("getPermissionsAsync", asyncFunction11);
            ObjectDefinitionBuilder objectDefinitionBuilder12 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction12 = new AsyncFunctionWithPromiseComponent("getCameraPermissionsAsync", new AnyType[0], new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$44(this));
            } else {
                asyncFunction12 = new AsyncFunctionComponent("getCameraPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$45.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$46(this));
            }
            objectDefinitionBuilder12.getAsyncFunctions().put("getCameraPermissionsAsync", asyncFunction12);
            ObjectDefinitionBuilder objectDefinitionBuilder13 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction13 = new AsyncFunctionWithPromiseComponent("getMicrophonePermissionsAsync", new AnyType[0], new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$47(this));
            } else {
                asyncFunction13 = new AsyncFunctionComponent("getMicrophonePermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$48.INSTANCE))}, new CameraViewModule$definition$lambda$16$$inlined$AsyncFunction$49(this));
            }
            objectDefinitionBuilder13.getAsyncFunctions().put("getMicrophonePermissionsAsync", asyncFunction13);
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ExpoCameraView.class);
            if (moduleDefinitionBuilder.getViewManagerDefinition() == null) {
                ViewDefinitionBuilder viewDefinitionBuilder = new ViewDefinitionBuilder(orCreateKotlinClass, new LazyKType(Reflection.getOrCreateKotlinClass(ExpoCameraView.class), false, CameraViewModule$definition$lambda$16$$inlined$View$1.INSTANCE, 2, (DefaultConstructorMarker) null));
                viewDefinitionBuilder.Events("onCameraReady", "onMountError", "onBarCodeScanned", "onFacesDetected", "onFaceDetectionError", "onPictureSaved");
                viewDefinitionBuilder.setOnViewDestroys(new CameraViewModule$definition$lambda$16$lambda$15$$inlined$OnViewDestroysGeneric$1(new CameraViewModule$definition$1$14$1(this)));
                viewDefinitionBuilder.getProps().put("type", new ConcreteViewProp("type", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, CameraViewModule$definition$lambda$16$lambda$15$$inlined$Prop$1.INSTANCE)), CameraViewModule$definition$1$14$2.INSTANCE));
                viewDefinitionBuilder.getProps().put("ratio", new ConcreteViewProp("ratio", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, CameraViewModule$definition$lambda$16$lambda$15$$inlined$Prop$2.INSTANCE)), CameraViewModule$definition$1$14$3.INSTANCE));
                viewDefinitionBuilder.getProps().put("flashMode", new ConcreteViewProp("flashMode", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, CameraViewModule$definition$lambda$16$lambda$15$$inlined$Prop$3.INSTANCE)), CameraViewModule$definition$1$14$4.INSTANCE));
                viewDefinitionBuilder.getProps().put("autoFocus", new ConcreteViewProp("autoFocus", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, CameraViewModule$definition$lambda$16$lambda$15$$inlined$Prop$4.INSTANCE)), CameraViewModule$definition$1$14$5.INSTANCE));
                viewDefinitionBuilder.getProps().put("focusDepth", new ConcreteViewProp("focusDepth", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Float.class), false, CameraViewModule$definition$lambda$16$lambda$15$$inlined$Prop$5.INSTANCE)), CameraViewModule$definition$1$14$6.INSTANCE));
                viewDefinitionBuilder.getProps().put("zoom", new ConcreteViewProp("zoom", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Float.class), false, CameraViewModule$definition$lambda$16$lambda$15$$inlined$Prop$6.INSTANCE)), CameraViewModule$definition$1$14$7.INSTANCE));
                viewDefinitionBuilder.getProps().put("whiteBalance", new ConcreteViewProp("whiteBalance", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, CameraViewModule$definition$lambda$16$lambda$15$$inlined$Prop$7.INSTANCE)), CameraViewModule$definition$1$14$8.INSTANCE));
                viewDefinitionBuilder.getProps().put("pictureSize", new ConcreteViewProp("pictureSize", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, CameraViewModule$definition$lambda$16$lambda$15$$inlined$Prop$8.INSTANCE)), CameraViewModule$definition$1$14$9.INSTANCE));
                viewDefinitionBuilder.getProps().put("barCodeScannerSettings", new ConcreteViewProp("barCodeScannerSettings", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), true, CameraViewModule$definition$lambda$16$lambda$15$$inlined$Prop$9.INSTANCE)), CameraViewModule$definition$1$14$10.INSTANCE));
                viewDefinitionBuilder.getProps().put("useCamera2Api", new ConcreteViewProp("useCamera2Api", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, CameraViewModule$definition$lambda$16$lambda$15$$inlined$Prop$10.INSTANCE)), CameraViewModule$definition$1$14$11.INSTANCE));
                viewDefinitionBuilder.getProps().put("barCodeScannerEnabled", new ConcreteViewProp("barCodeScannerEnabled", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), true, CameraViewModule$definition$lambda$16$lambda$15$$inlined$Prop$11.INSTANCE)), CameraViewModule$definition$1$14$12.INSTANCE));
                viewDefinitionBuilder.getProps().put("faceDetectorEnabled", new ConcreteViewProp("faceDetectorEnabled", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), true, CameraViewModule$definition$lambda$16$lambda$15$$inlined$Prop$12.INSTANCE)), CameraViewModule$definition$1$14$13.INSTANCE));
                viewDefinitionBuilder.getProps().put("faceDetectorSettings", new ConcreteViewProp("faceDetectorSettings", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), true, CameraViewModule$definition$lambda$16$lambda$15$$inlined$Prop$13.INSTANCE)), CameraViewModule$definition$1$14$14.INSTANCE));
                moduleDefinitionBuilder.setViewManagerDefinition(viewDefinitionBuilder.build());
                ModuleDefinitionData buildModule = moduleDefinitionBuilder.buildModule();
                Trace.endSection();
                return buildModule;
            }
            throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
        } catch (Throwable th) {
            Throwable th2 = th;
            Trace.endSection();
            throw th2;
        }
    }

    /* access modifiers changed from: private */
    public final File getCacheDirectory() {
        return getAppContext().getCacheDirectory();
    }

    /* access modifiers changed from: private */
    public final Permissions getPermissionsManager() {
        Permissions permissions = getAppContext().getPermissions();
        if (permissions != null) {
            return permissions;
        }
        throw new Exceptions.PermissionsModuleNotFound();
    }

    /* access modifiers changed from: private */
    public final ExpoCameraView findView(int i) {
        ExpoCameraView expoCameraView = (ExpoCameraView) getAppContext().findView(i);
        if (expoCameraView != null) {
            return expoCameraView;
        }
        throw new Exceptions.ViewNotFound(Reflection.getOrCreateKotlinClass(ExpoCameraView.class), i);
    }
}
