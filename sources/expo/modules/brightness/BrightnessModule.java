package expo.modules.brightness;

import android.app.Activity;
import android.provider.Settings;
import androidx.tracing.Trace;
import expo.modules.core.errors.InvalidArgumentException;
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
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lexpo/modules/brightness/BrightnessModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "brightnessModeJSToNative", "", "jsValue", "brightnessModeNativeToJS", "nativeValue", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getSystemBrightness", "", "expo-brightness_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrightnessModule.kt */
public final class BrightnessModule extends Module {
    /* access modifiers changed from: private */
    public final int brightnessModeNativeToJS(int i) {
        if (i != 0) {
            return i != 1 ? 0 : 1;
        }
        return 2;
    }

    /* access modifiers changed from: private */
    public final Activity getCurrentActivity() {
        Activity currentActivity = getAppContext().getCurrentActivity();
        if (currentActivity != null) {
            return currentActivity;
        }
        throw new Exceptions.MissingActivity();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        AsyncFunction asyncFunction3;
        AsyncFunction asyncFunction4;
        AsyncFunction asyncFunction5;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoBrightness");
            moduleDefinitionBuilder.Events(BrightnessModuleKt.brightnessChangeEvent);
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("requestPermissionsAsync", new AnyType[0], new BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("requestPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$2.INSTANCE))}, new BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("requestPermissionsAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("getPermissionsAsync", new AnyType[0], new BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$4(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("getPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$5.INSTANCE))}, new BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$6(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("getPermissionsAsync", asyncFunction2);
            ObjectDefinitionBuilder objectDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Float.class == Promise.class) {
                asyncFunction3 = new AsyncFunctionWithPromiseComponent("setBrightnessAsync", new AnyType[0], new BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$7(this));
            } else {
                asyncFunction3 = new AsyncFunctionComponent("setBrightnessAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Float.class), false, BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$8.INSTANCE))}, new BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$9(this));
            }
            objectDefinitionBuilder3.getAsyncFunctions().put("setBrightnessAsync", asyncFunction3);
            asyncFunction3.runOnQueue(Queues.MAIN);
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("getBrightnessAsync", new AnyType[0], new BrightnessModule$definition$lambda$10$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getBrightnessAsync", asyncFunctionComponent);
            asyncFunctionComponent.runOnQueue(Queues.MAIN);
            AsyncFunctionComponent asyncFunctionComponent2 = new AsyncFunctionComponent("getSystemBrightnessAsync", new AnyType[0], new BrightnessModule$definition$lambda$10$$inlined$AsyncFunctionWithoutArgs$2(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getSystemBrightnessAsync", asyncFunctionComponent2);
            AsyncFunction asyncFunction6 = asyncFunctionComponent2;
            ObjectDefinitionBuilder objectDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Float.class == Promise.class) {
                asyncFunction4 = new AsyncFunctionWithPromiseComponent("setSystemBrightnessAsync", new AnyType[0], new BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$10(this));
            } else {
                asyncFunction4 = new AsyncFunctionComponent("setSystemBrightnessAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Float.class), false, BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$11.INSTANCE))}, new BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$12(this));
            }
            objectDefinitionBuilder4.getAsyncFunctions().put("setSystemBrightnessAsync", asyncFunction4);
            AsyncFunctionComponent asyncFunctionComponent3 = new AsyncFunctionComponent("restoreSystemBrightnessAsync", new AnyType[0], new BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$13(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("restoreSystemBrightnessAsync", asyncFunctionComponent3);
            asyncFunctionComponent3.runOnQueue(Queues.MAIN);
            AsyncFunctionComponent asyncFunctionComponent4 = new AsyncFunctionComponent("isUsingSystemBrightnessAsync", new AnyType[0], new BrightnessModule$definition$lambda$10$$inlined$AsyncFunctionWithoutArgs$3(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("isUsingSystemBrightnessAsync", asyncFunctionComponent4);
            asyncFunctionComponent4.runOnQueue(Queues.MAIN);
            AsyncFunctionComponent asyncFunctionComponent5 = new AsyncFunctionComponent("getSystemBrightnessModeAsync", new AnyType[0], new BrightnessModule$definition$lambda$10$$inlined$AsyncFunctionWithoutArgs$4(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getSystemBrightnessModeAsync", asyncFunctionComponent5);
            AsyncFunction asyncFunction7 = asyncFunctionComponent5;
            ObjectDefinitionBuilder objectDefinitionBuilder5 = moduleDefinitionBuilder;
            if (Integer.class == Promise.class) {
                asyncFunction5 = new AsyncFunctionWithPromiseComponent("setSystemBrightnessModeAsync", new AnyType[0], new BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$14(this));
            } else {
                asyncFunction5 = new AsyncFunctionComponent("setSystemBrightnessModeAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$15.INSTANCE))}, new BrightnessModule$definition$lambda$10$$inlined$AsyncFunction$16(this));
            }
            objectDefinitionBuilder5.getAsyncFunctions().put("setSystemBrightnessModeAsync", asyncFunction5);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final float getSystemBrightness() {
        float f;
        float f2;
        if (Settings.System.getInt(getCurrentActivity().getContentResolver(), "screen_brightness_mode") == 1) {
            f2 = Settings.System.getFloat(getCurrentActivity().getContentResolver(), "screen_auto_brightness_adj") + 1.0f;
            f = (float) 2;
        } else {
            String string = Settings.System.getString(getCurrentActivity().getContentResolver(), "screen_brightness");
            Intrinsics.checkNotNull(string);
            f2 = (float) Integer.parseInt(string);
            f = 255.0f;
        }
        return f2 / f;
    }

    /* access modifiers changed from: private */
    public final int brightnessModeJSToNative(int i) throws InvalidArgumentException {
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 0;
        }
        throw new InvalidArgumentException("Unsupported brightness mode " + i);
    }
}
