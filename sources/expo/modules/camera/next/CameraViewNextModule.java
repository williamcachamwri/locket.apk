package expo.modules.camera.next;

import androidx.tracing.Trace;
import expo.modules.camera.next.records.BarcodeSettings;
import expo.modules.camera.next.records.CameraMode;
import expo.modules.camera.next.records.CameraType;
import expo.modules.camera.next.records.FlashMode;
import expo.modules.camera.next.records.VideoQuality;
import expo.modules.interfaces.permissions.Permissions;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
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
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lexpo/modules/camera/next/CameraViewNextModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "cacheDirectory", "Ljava/io/File;", "getCacheDirectory", "()Ljava/io/File;", "moduleScope", "Lkotlinx/coroutines/CoroutineScope;", "permissionsManager", "Lexpo/modules/interfaces/permissions/Permissions;", "getPermissionsManager", "()Lexpo/modules/interfaces/permissions/Permissions;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "Companion", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraViewNextModule.kt */
public final class CameraViewNextModule extends Module {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String TAG = "CameraViewNextModule";
    /* access modifiers changed from: private */
    public final CoroutineScope moduleScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getMain());

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        AsyncFunction asyncFunction3;
        AsyncFunction asyncFunction4;
        AsyncFunction asyncFunction5;
        AsyncFunction asyncFunction6;
        AsyncFunction asyncFunction7;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoCameraNext");
            moduleDefinitionBuilder.Events("onModernBarcodeScanned");
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("requestCameraPermissionsAsync", new AnyType[0], new CameraViewNextModule$definition$lambda$10$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("requestCameraPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewNextModule$definition$lambda$10$$inlined$AsyncFunction$2.INSTANCE))}, new CameraViewNextModule$definition$lambda$10$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("requestCameraPermissionsAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("requestMicrophonePermissionsAsync", new AnyType[0], new CameraViewNextModule$definition$lambda$10$$inlined$AsyncFunction$4(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("requestMicrophonePermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewNextModule$definition$lambda$10$$inlined$AsyncFunction$5.INSTANCE))}, new CameraViewNextModule$definition$lambda$10$$inlined$AsyncFunction$6(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("requestMicrophonePermissionsAsync", asyncFunction2);
            ObjectDefinitionBuilder objectDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction3 = new AsyncFunctionWithPromiseComponent("getCameraPermissionsAsync", new AnyType[0], new CameraViewNextModule$definition$lambda$10$$inlined$AsyncFunction$7(this));
            } else {
                asyncFunction3 = new AsyncFunctionComponent("getCameraPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewNextModule$definition$lambda$10$$inlined$AsyncFunction$8.INSTANCE))}, new CameraViewNextModule$definition$lambda$10$$inlined$AsyncFunction$9(this));
            }
            objectDefinitionBuilder3.getAsyncFunctions().put("getCameraPermissionsAsync", asyncFunction3);
            ObjectDefinitionBuilder objectDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction4 = new AsyncFunctionWithPromiseComponent("getMicrophonePermissionsAsync", new AnyType[0], new CameraViewNextModule$definition$lambda$10$$inlined$AsyncFunction$10(this));
            } else {
                asyncFunction4 = new AsyncFunctionComponent("getMicrophonePermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewNextModule$definition$lambda$10$$inlined$AsyncFunction$11.INSTANCE))}, new CameraViewNextModule$definition$lambda$10$$inlined$AsyncFunction$12(this));
            }
            objectDefinitionBuilder4.getAsyncFunctions().put("getMicrophonePermissionsAsync", asyncFunction4);
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new CameraViewNextModule$definition$lambda$10$$inlined$OnDestroy$1(this)));
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ExpoCameraView.class);
            if (moduleDefinitionBuilder.getViewManagerDefinition() == null) {
                ViewDefinitionBuilder viewDefinitionBuilder = new ViewDefinitionBuilder(orCreateKotlinClass, new LazyKType(Reflection.getOrCreateKotlinClass(ExpoCameraView.class), false, CameraViewNextModule$definition$lambda$10$$inlined$View$1.INSTANCE, 2, (DefaultConstructorMarker) null));
                viewDefinitionBuilder.EventsWithArray(CameraViewNextModuleKt.getCameraEvents());
                viewDefinitionBuilder.getProps().put("facing", new ConcreteViewProp("facing", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(CameraType.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$Prop$1.INSTANCE)), CameraViewNextModule$definition$1$6$1.INSTANCE));
                viewDefinitionBuilder.getProps().put("flashMode", new ConcreteViewProp("flashMode", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FlashMode.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$Prop$2.INSTANCE)), CameraViewNextModule$definition$1$6$2.INSTANCE));
                viewDefinitionBuilder.getProps().put("enableTorch", new ConcreteViewProp("enableTorch", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$Prop$3.INSTANCE)), CameraViewNextModule$definition$1$6$3.INSTANCE));
                viewDefinitionBuilder.getProps().put("zoom", new ConcreteViewProp("zoom", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Float.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$Prop$4.INSTANCE)), CameraViewNextModule$definition$1$6$4.INSTANCE));
                viewDefinitionBuilder.getProps().put("mode", new ConcreteViewProp("mode", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(CameraMode.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$Prop$5.INSTANCE)), CameraViewNextModule$definition$1$6$5.INSTANCE));
                viewDefinitionBuilder.getProps().put("mute", new ConcreteViewProp("mute", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), true, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$Prop$6.INSTANCE)), CameraViewNextModule$definition$1$6$6.INSTANCE));
                viewDefinitionBuilder.getProps().put("videoQuality", new ConcreteViewProp("videoQuality", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(VideoQuality.class), true, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$Prop$7.INSTANCE)), CameraViewNextModule$definition$1$6$7.INSTANCE));
                viewDefinitionBuilder.getProps().put("barcodeScannerSettings", new ConcreteViewProp("barcodeScannerSettings", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(BarcodeSettings.class), true, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$Prop$8.INSTANCE)), CameraViewNextModule$definition$1$6$8.INSTANCE));
                viewDefinitionBuilder.getProps().put("barcodeScannerEnabled", new ConcreteViewProp("barcodeScannerEnabled", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), true, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$Prop$9.INSTANCE)), CameraViewNextModule$definition$1$6$9.INSTANCE));
                if (Promise.class == Promise.class) {
                    asyncFunction5 = new AsyncFunctionWithPromiseComponent("takePicture", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ExpoCameraView.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$1.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(PictureOptions.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$2.INSTANCE))}, new CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$3(this));
                } else {
                    asyncFunction5 = new AsyncFunctionComponent("takePicture", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ExpoCameraView.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$4.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(PictureOptions.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$5.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$6.INSTANCE))}, new CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$7(this));
                }
                viewDefinitionBuilder.getAsyncFunctions().put("takePicture", asyncFunction5);
                asyncFunction5.runOnQueue(Queues.MAIN);
                if (Promise.class == Promise.class) {
                    asyncFunction6 = new AsyncFunctionWithPromiseComponent("record", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ExpoCameraView.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$8.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(RecordingOptions.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$9.INSTANCE))}, new CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$10(this));
                } else {
                    asyncFunction6 = new AsyncFunctionComponent("record", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ExpoCameraView.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$11.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(RecordingOptions.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$12.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$13.INSTANCE))}, new CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$14(this));
                }
                viewDefinitionBuilder.getAsyncFunctions().put("record", asyncFunction6);
                asyncFunction6.runOnQueue(Queues.MAIN);
                if (ExpoCameraView.class == Promise.class) {
                    asyncFunction7 = new AsyncFunctionWithPromiseComponent("stopRecording", new AnyType[0], new CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$15());
                } else {
                    asyncFunction7 = new AsyncFunctionComponent("stopRecording", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ExpoCameraView.class), false, CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$16.INSTANCE))}, new CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$17());
                }
                viewDefinitionBuilder.getAsyncFunctions().put("stopRecording", asyncFunction7);
                asyncFunction7.runOnQueue(Queues.MAIN);
                viewDefinitionBuilder.setOnViewDestroys(new CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$OnViewDestroys$1());
                moduleDefinitionBuilder.setViewManagerDefinition(viewDefinitionBuilder.build());
                return moduleDefinitionBuilder.buildModule();
            }
            throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
        } finally {
            Trace.endSection();
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

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/camera/next/CameraViewNextModule$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG$expo_camera_release", "()Ljava/lang/String;", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraViewNextModule.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG$expo_camera_release() {
            return CameraViewNextModule.TAG;
        }
    }
}
