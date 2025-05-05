package expo.modules.haptics;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import androidx.tracing.Trace;
import expo.modules.haptics.arguments.HapticsVibrationType;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lexpo/modules/haptics/HapticsModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "vibrator", "Landroid/os/Vibrator;", "getVibrator", "()Landroid/os/Vibrator;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "vibrate", "", "type", "Lexpo/modules/haptics/arguments/HapticsVibrationType;", "expo-haptics_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HapticsModule.kt */
public final class HapticsModule extends Module {
    private final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    private final Vibrator getVibrator() {
        if (Build.VERSION.SDK_INT >= 31) {
            Object systemService = getContext().getSystemService("vibrator_manager");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.VibratorManager");
            Vibrator defaultVibrator = ((VibratorManager) systemService).getDefaultVibrator();
            Intrinsics.checkNotNull(defaultVibrator);
            return defaultVibrator;
        }
        Object systemService2 = getContext().getSystemService("vibrator");
        Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.os.Vibrator");
        return (Vibrator) systemService2;
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoHaptics");
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("notificationAsync", new AnyType[0], new HapticsModule$definition$lambda$3$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("notificationAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, HapticsModule$definition$lambda$3$$inlined$AsyncFunction$2.INSTANCE))}, new HapticsModule$definition$lambda$3$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("notificationAsync", asyncFunction);
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("selectionAsync", new AnyType[0], new HapticsModule$definition$lambda$3$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("selectionAsync", asyncFunctionComponent);
            AsyncFunction asyncFunction3 = asyncFunctionComponent;
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("impactAsync", new AnyType[0], new HapticsModule$definition$lambda$3$$inlined$AsyncFunction$4(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("impactAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, HapticsModule$definition$lambda$3$$inlined$AsyncFunction$5.INSTANCE))}, new HapticsModule$definition$lambda$3$$inlined$AsyncFunction$6(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("impactAsync", asyncFunction2);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final void vibrate(HapticsVibrationType hapticsVibrationType) {
        getVibrator().vibrate(VibrationEffect.createWaveform(hapticsVibrationType.getTimings(), hapticsVibrationType.getAmplitudes(), -1));
    }
}
