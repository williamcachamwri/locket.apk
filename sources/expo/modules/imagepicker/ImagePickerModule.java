package expo.modules.imagepicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.tracing.Trace;
import expo.modules.core.errors.ModuleNotFoundException;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.imagepicker.contracts.CameraContractOptions;
import expo.modules.imagepicker.contracts.CropImageContractOptions;
import expo.modules.imagepicker.contracts.ImageLibraryContractOptions;
import expo.modules.imagepicker.contracts.ImagePickerContractResult;
import expo.modules.interfaces.permissions.Permissions;
import expo.modules.interfaces.permissions.PermissionsResponseListener;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.activityresult.AppContextActivityResultLauncher;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionBuilder;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent;
import expo.modules.kotlin.functions.SuspendFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import java.io.File;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u000e\u0010\u001d\u001a\u00020\u001eH@¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"H\u0002J\u001b\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010&\u001a\u00020'H\u0002¢\u0006\u0002\u0010(J\u0018\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\n2\u0006\u0010!\u001a\u00020\"H\u0002J4\u0010+\u001a\u00020,2\u001c\u0010-\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0/\u0012\u0006\u0012\u0004\u0018\u00010,0.2\u0006\u0010!\u001a\u00020\"H@¢\u0006\u0002\u00100J,\u00101\u001a\u0002022\u001c\u0010-\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0/\u0012\u0006\u0012\u0004\u0018\u00010,0.H@¢\u0006\u0002\u00103R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX.¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\n0\bX.¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\n0\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lexpo/modules/imagepicker/ImagePickerModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "cacheDirectory", "Ljava/io/File;", "getCacheDirectory", "()Ljava/io/File;", "cameraLauncher", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultLauncher;", "Lexpo/modules/imagepicker/contracts/CameraContractOptions;", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "cropImageLauncher", "Lexpo/modules/imagepicker/contracts/CropImageContractOptions;", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "imageLibraryLauncher", "Lexpo/modules/imagepicker/contracts/ImageLibraryContractOptions;", "mediaHandler", "Lexpo/modules/imagepicker/MediaHandler;", "pendingMediaPickingResult", "Lexpo/modules/imagepicker/PendingMediaPickingResult;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "ensureCameraPermissionsAreGranted", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ensureTargetActivityIsAvailable", "options", "Lexpo/modules/imagepicker/ImagePickerOptions;", "getMediaLibraryPermissions", "", "", "writeOnly", "", "(Z)[Ljava/lang/String;", "handleResultUponActivityDestruction", "result", "launchContract", "", "pickerLauncher", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;Lexpo/modules/imagepicker/ImagePickerOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launchPicker", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult$Success;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImagePickerModule.kt */
public final class ImagePickerModule extends Module {
    /* access modifiers changed from: private */
    public AppContextActivityResultLauncher<CameraContractOptions, ImagePickerContractResult> cameraLauncher;
    /* access modifiers changed from: private */
    public AppContextActivityResultLauncher<CropImageContractOptions, ImagePickerContractResult> cropImageLauncher;
    /* access modifiers changed from: private */
    public AppContextActivityResultLauncher<ImageLibraryContractOptions, ImagePickerContractResult> imageLibraryLauncher;
    /* access modifiers changed from: private */
    public final MediaHandler mediaHandler = new MediaHandler(this);
    /* access modifiers changed from: private */
    public PendingMediaPickingResult pendingMediaPickingResult;

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        AsyncFunction asyncFunction3;
        AsyncFunction asyncFunction4;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name(ImagePickerConstants.TAG);
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("requestMediaLibraryPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$1.INSTANCE))}, new ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$2(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("requestMediaLibraryPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$3.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$4.INSTANCE))}, new ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$5(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("requestMediaLibraryPermissionsAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("getMediaLibraryPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$6.INSTANCE))}, new ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$7(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("getMediaLibraryPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$8.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$9.INSTANCE))}, new ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$10(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("getMediaLibraryPermissionsAsync", asyncFunction2);
            ObjectDefinitionBuilder objectDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction3 = new AsyncFunctionWithPromiseComponent("requestCameraPermissionsAsync", new AnyType[0], new ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$11(this));
            } else {
                asyncFunction3 = new AsyncFunctionComponent("requestCameraPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$12.INSTANCE))}, new ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$13(this));
            }
            objectDefinitionBuilder3.getAsyncFunctions().put("requestCameraPermissionsAsync", asyncFunction3);
            ObjectDefinitionBuilder objectDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction4 = new AsyncFunctionWithPromiseComponent("getCameraPermissionsAsync", new AnyType[0], new ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$14(this));
            } else {
                asyncFunction4 = new AsyncFunctionComponent("getCameraPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$15.INSTANCE))}, new ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$16(this));
            }
            objectDefinitionBuilder4.getAsyncFunctions().put("getCameraPermissionsAsync", asyncFunction4);
            AsyncFunctionBuilder AsyncFunction = moduleDefinitionBuilder.AsyncFunction("launchCameraAsync");
            AsyncFunction.setAsyncFunctionComponent(new SuspendFunctionComponent(AsyncFunction.getName(), new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ImagePickerOptions.class), false, ImagePickerModule$definition$lambda$7$$inlined$Coroutine$1.INSTANCE))}, new ImagePickerModule$definition$lambda$7$$inlined$Coroutine$2((Continuation) null, this)));
            AsyncFunctionBuilder AsyncFunction2 = moduleDefinitionBuilder.AsyncFunction("launchImageLibraryAsync");
            AsyncFunction2.setAsyncFunctionComponent(new SuspendFunctionComponent(AsyncFunction2.getName(), new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ImagePickerOptions.class), false, ImagePickerModule$definition$lambda$7$$inlined$Coroutine$3.INSTANCE))}, new ImagePickerModule$definition$lambda$7$$inlined$Coroutine$4((Continuation) null, this)));
            AsyncFunctionBuilder AsyncFunction3 = moduleDefinitionBuilder.AsyncFunction("getPendingResultAsync");
            SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(AsyncFunction3.getName(), new AnyType[0], new ImagePickerModule$definition$lambda$7$$inlined$Coroutine$5((Continuation) null, this));
            AsyncFunction3.setAsyncFunctionComponent(suspendFunctionComponent);
            BaseAsyncFunctionComponent baseAsyncFunctionComponent = suspendFunctionComponent;
            moduleDefinitionBuilder.RegisterActivityContracts(new ImagePickerModule$definition$1$8(this, (Continuation<? super ImagePickerModule$definition$1$8>) null));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new IllegalArgumentException("React Application Context is null".toString());
    }

    private final Activity getCurrentActivity() {
        ActivityProvider activityProvider = getAppContext().getActivityProvider();
        Activity currentActivity = activityProvider != null ? activityProvider.getCurrentActivity() : null;
        if (currentActivity != null) {
            return currentActivity;
        }
        throw new MissingCurrentActivityException();
    }

    /* access modifiers changed from: private */
    public final File getCacheDirectory() {
        return getAppContext().getCacheDirectory();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00cb A[Catch:{ OperationCanceledException -> 0x00ed }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00cc A[Catch:{ OperationCanceledException -> 0x00ed }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e9 A[Catch:{ OperationCanceledException -> 0x00ed }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object launchContract(kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super expo.modules.imagepicker.contracts.ImagePickerContractResult>, ? extends java.lang.Object> r9, expo.modules.imagepicker.ImagePickerOptions r10, kotlin.coroutines.Continuation<java.lang.Object> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof expo.modules.imagepicker.ImagePickerModule$launchContract$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            expo.modules.imagepicker.ImagePickerModule$launchContract$1 r0 = (expo.modules.imagepicker.ImagePickerModule$launchContract$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            expo.modules.imagepicker.ImagePickerModule$launchContract$1 r0 = new expo.modules.imagepicker.ImagePickerModule$launchContract$1
            r0.<init>(r8, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L_0x0063
            if (r2 == r5) goto L_0x004f
            if (r2 == r4) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ OperationCanceledException -> 0x00ed }
            goto L_0x00ea
        L_0x0032:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x003a:
            java.lang.Object r9 = r0.L$3
            kotlin.jvm.internal.Ref$ObjectRef r9 = (kotlin.jvm.internal.Ref.ObjectRef) r9
            java.lang.Object r10 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r2 = r0.L$1
            expo.modules.imagepicker.ImagePickerOptions r2 = (expo.modules.imagepicker.ImagePickerOptions) r2
            java.lang.Object r7 = r0.L$0
            expo.modules.imagepicker.ImagePickerModule r7 = (expo.modules.imagepicker.ImagePickerModule) r7
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ OperationCanceledException -> 0x00ed }
            goto L_0x00cd
        L_0x004f:
            java.lang.Object r9 = r0.L$3
            kotlin.jvm.internal.Ref$ObjectRef r9 = (kotlin.jvm.internal.Ref.ObjectRef) r9
            java.lang.Object r10 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r2 = r0.L$1
            expo.modules.imagepicker.ImagePickerOptions r2 = (expo.modules.imagepicker.ImagePickerOptions) r2
            java.lang.Object r7 = r0.L$0
            expo.modules.imagepicker.ImagePickerModule r7 = (expo.modules.imagepicker.ImagePickerModule) r7
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ OperationCanceledException -> 0x00ed }
            goto L_0x0081
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlin.jvm.internal.Ref$ObjectRef r11 = new kotlin.jvm.internal.Ref$ObjectRef     // Catch:{ OperationCanceledException -> 0x00ed }
            r11.<init>()     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.L$0 = r8     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.L$1 = r10     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.L$2 = r11     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.L$3 = r11     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.label = r5     // Catch:{ OperationCanceledException -> 0x00ed }
            java.lang.Object r9 = r8.launchPicker(r9, r0)     // Catch:{ OperationCanceledException -> 0x00ed }
            if (r9 != r1) goto L_0x007c
            return r1
        L_0x007c:
            r7 = r8
            r2 = r10
            r10 = r11
            r11 = r9
            r9 = r10
        L_0x0081:
            r9.element = r11     // Catch:{ OperationCanceledException -> 0x00ed }
            boolean r9 = r2.getAllowsMultipleSelection()     // Catch:{ OperationCanceledException -> 0x00ed }
            if (r9 != 0) goto L_0x00cf
            boolean r9 = r2.getAllowsEditing()     // Catch:{ OperationCanceledException -> 0x00ed }
            if (r9 == 0) goto L_0x00cf
            T r9 = r10.element     // Catch:{ OperationCanceledException -> 0x00ed }
            expo.modules.imagepicker.contracts.ImagePickerContractResult$Success r9 = (expo.modules.imagepicker.contracts.ImagePickerContractResult.Success) r9     // Catch:{ OperationCanceledException -> 0x00ed }
            java.util.List r9 = r9.getData()     // Catch:{ OperationCanceledException -> 0x00ed }
            int r9 = r9.size()     // Catch:{ OperationCanceledException -> 0x00ed }
            if (r9 != r5) goto L_0x00cf
            T r9 = r10.element     // Catch:{ OperationCanceledException -> 0x00ed }
            expo.modules.imagepicker.contracts.ImagePickerContractResult$Success r9 = (expo.modules.imagepicker.contracts.ImagePickerContractResult.Success) r9     // Catch:{ OperationCanceledException -> 0x00ed }
            java.util.List r9 = r9.getData()     // Catch:{ OperationCanceledException -> 0x00ed }
            r11 = 0
            java.lang.Object r9 = r9.get(r11)     // Catch:{ OperationCanceledException -> 0x00ed }
            kotlin.Pair r9 = (kotlin.Pair) r9     // Catch:{ OperationCanceledException -> 0x00ed }
            java.lang.Object r9 = r9.getFirst()     // Catch:{ OperationCanceledException -> 0x00ed }
            expo.modules.imagepicker.MediaType r11 = expo.modules.imagepicker.MediaType.IMAGE     // Catch:{ OperationCanceledException -> 0x00ed }
            if (r9 != r11) goto L_0x00cf
            expo.modules.imagepicker.ImagePickerModule$launchContract$2 r9 = new expo.modules.imagepicker.ImagePickerModule$launchContract$2     // Catch:{ OperationCanceledException -> 0x00ed }
            r9.<init>(r7, r10, r2, r6)     // Catch:{ OperationCanceledException -> 0x00ed }
            kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.L$0 = r7     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.L$1 = r2     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.L$2 = r10     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.L$3 = r10     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.label = r4     // Catch:{ OperationCanceledException -> 0x00ed }
            java.lang.Object r11 = r7.launchPicker(r9, r0)     // Catch:{ OperationCanceledException -> 0x00ed }
            if (r11 != r1) goto L_0x00cc
            return r1
        L_0x00cc:
            r9 = r10
        L_0x00cd:
            r9.element = r11     // Catch:{ OperationCanceledException -> 0x00ed }
        L_0x00cf:
            expo.modules.imagepicker.MediaHandler r9 = r7.mediaHandler     // Catch:{ OperationCanceledException -> 0x00ed }
            T r10 = r10.element     // Catch:{ OperationCanceledException -> 0x00ed }
            expo.modules.imagepicker.contracts.ImagePickerContractResult$Success r10 = (expo.modules.imagepicker.contracts.ImagePickerContractResult.Success) r10     // Catch:{ OperationCanceledException -> 0x00ed }
            java.util.List r10 = r10.getData()     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.L$0 = r6     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.L$1 = r6     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.L$2 = r6     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.L$3 = r6     // Catch:{ OperationCanceledException -> 0x00ed }
            r0.label = r3     // Catch:{ OperationCanceledException -> 0x00ed }
            java.lang.Object r11 = r9.readExtras$expo_image_picker_release(r10, r2, r0)     // Catch:{ OperationCanceledException -> 0x00ed }
            if (r11 != r1) goto L_0x00ea
            return r1
        L_0x00ea:
            expo.modules.imagepicker.ImagePickerResponse r11 = (expo.modules.imagepicker.ImagePickerResponse) r11     // Catch:{ OperationCanceledException -> 0x00ed }
            return r11
        L_0x00ed:
            expo.modules.imagepicker.ImagePickerResponse r9 = new expo.modules.imagepicker.ImagePickerResponse
            r9.<init>(r5, r6, r4, r6)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.ImagePickerModule.launchContract(kotlin.jvm.functions.Function1, expo.modules.imagepicker.ImagePickerOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final void handleResultUponActivityDestruction(ImagePickerContractResult imagePickerContractResult, ImagePickerOptions imagePickerOptions) {
        if (imagePickerContractResult instanceof ImagePickerContractResult.Success) {
            this.pendingMediaPickingResult = new PendingMediaPickingResult(((ImagePickerContractResult.Success) imagePickerContractResult).getData(), imagePickerOptions);
        }
    }

    /* access modifiers changed from: private */
    public final Object launchPicker(Function1<? super Continuation<? super ImagePickerContractResult>, ? extends Object> function1, Continuation<? super ImagePickerContractResult.Success> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ImagePickerModule$launchPicker$2(function1, (Continuation<? super ImagePickerModule$launchPicker$2>) null), continuation);
    }

    /* access modifiers changed from: private */
    public final String[] getMediaLibraryPermissions(boolean z) {
        if (Build.VERSION.SDK_INT >= 33) {
            return new String[0];
        }
        String[] strArr = new String[2];
        strArr[0] = "android.permission.WRITE_EXTERNAL_STORAGE";
        strArr[1] = z ^ true ? "android.permission.READ_EXTERNAL_STORAGE" : null;
        return (String[]) CollectionsKt.listOfNotNull((T[]) strArr).toArray(new String[0]);
    }

    /* access modifiers changed from: private */
    public final void ensureTargetActivityIsAvailable(ImagePickerOptions imagePickerOptions) {
        Intent intent = new Intent(imagePickerOptions.getMediaTypes().toCameraIntentAction());
        if (intent.resolveActivity(getCurrentActivity().getApplication().getPackageManager()) == null) {
            throw new MissingActivityToHandleIntent(intent.getType());
        }
    }

    /* access modifiers changed from: private */
    public final Object ensureCameraPermissionsAreGranted(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        Permissions permissions = getAppContext().getPermissions();
        if (permissions != null) {
            PermissionsResponseListener imagePickerModule$ensureCameraPermissionsAreGranted$2$1 = new ImagePickerModule$ensureCameraPermissionsAreGranted$2$1(cancellableContinuation);
            String[] strArr = new String[2];
            strArr[0] = Build.VERSION.SDK_INT < 33 ? "android.permission.WRITE_EXTERNAL_STORAGE" : null;
            strArr[1] = "android.permission.CAMERA";
            String[] strArr2 = (String[]) CollectionsKt.listOfNotNull((T[]) strArr).toArray(new String[0]);
            permissions.askForPermissions(imagePickerModule$ensureCameraPermissionsAreGranted$2$1, (String[]) Arrays.copyOf(strArr2, strArr2.length));
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
        }
        throw new ModuleNotFoundException("Permissions");
    }
}
