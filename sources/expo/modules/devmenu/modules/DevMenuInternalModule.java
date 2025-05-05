package expo.modules.devmenu.modules;

import android.content.Context;
import androidx.tracing.Trace;
import com.facebook.react.bridge.ReadableMap;
import expo.modules.core.utilities.EmulatorUtilities;
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
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lexpo/modules/devmenu/modules/DevMenuInternalModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuInternalModule.kt */
public final class DevMenuInternalModule extends Module {
    /* access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        AsyncFunction asyncFunction3;
        AsyncFunction asyncFunction4;
        AsyncFunction asyncFunction5;
        AsyncFunction asyncFunction6;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoDevMenuInternal");
            moduleDefinitionBuilder.Constants((Pair<String, ? extends Object>[]) new Pair[]{TuplesKt.to("doesDeviceSupportKeyCommands", Boolean.valueOf(EmulatorUtilities.INSTANCE.isRunningOnEmulator()))});
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("loadFontsAsync", new AnyType[0], new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("loadFontsAsync", asyncFunctionComponent);
            AsyncFunction asyncFunction7 = asyncFunctionComponent;
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (ReadableMap.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("dispatchCallableAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$1.INSTANCE))}, new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$2());
            } else {
                asyncFunction = new AsyncFunctionComponent("dispatchCallableAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$3.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableMap.class), true, DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$4.INSTANCE))}, new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$5());
            }
            objectDefinitionBuilder.getAsyncFunctions().put("dispatchCallableAsync", asyncFunction);
            AsyncFunctionComponent asyncFunctionComponent2 = new AsyncFunctionComponent("hideMenu", new AnyType[0], new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunctionWithoutArgs$2());
            moduleDefinitionBuilder.getAsyncFunctions().put("hideMenu", asyncFunctionComponent2);
            AsyncFunction asyncFunction8 = asyncFunctionComponent2;
            AsyncFunctionComponent asyncFunctionComponent3 = new AsyncFunctionComponent("closeMenu", new AnyType[0], new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunctionWithoutArgs$3());
            moduleDefinitionBuilder.getAsyncFunctions().put("closeMenu", asyncFunctionComponent3);
            AsyncFunction asyncFunction9 = asyncFunctionComponent3;
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Boolean.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("setOnboardingFinished", new AnyType[0], new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$6());
            } else {
                asyncFunction2 = new AsyncFunctionComponent("setOnboardingFinished", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), false, DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$7.INSTANCE))}, new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$8());
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("setOnboardingFinished", asyncFunction2);
            AsyncFunctionComponent asyncFunctionComponent4 = new AsyncFunctionComponent("openDevMenuFromReactNative", new AnyType[0], new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$9());
            moduleDefinitionBuilder.getAsyncFunctions().put("openDevMenuFromReactNative", asyncFunctionComponent4);
            AsyncFunction asyncFunction10 = asyncFunctionComponent4;
            ObjectDefinitionBuilder objectDefinitionBuilder3 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction3 = new AsyncFunctionWithPromiseComponent("onScreenChangeAsync", new AnyType[0], new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$10());
            } else {
                asyncFunction3 = new AsyncFunctionComponent("onScreenChangeAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$11.INSTANCE))}, new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$12());
            }
            objectDefinitionBuilder3.getAsyncFunctions().put("onScreenChangeAsync", asyncFunction3);
            ObjectDefinitionBuilder objectDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction4 = new AsyncFunctionWithPromiseComponent("fetchDataSourceAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$13.INSTANCE))}, new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$14());
            } else {
                asyncFunction4 = new AsyncFunctionComponent("fetchDataSourceAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$15.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$16.INSTANCE))}, new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$17());
            }
            objectDefinitionBuilder4.getAsyncFunctions().put("fetchDataSourceAsync", asyncFunction4);
            ObjectDefinitionBuilder objectDefinitionBuilder5 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction5 = new AsyncFunctionWithPromiseComponent("copyToClipboardAsync", new AnyType[0], new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$18(this));
            } else {
                asyncFunction5 = new AsyncFunctionComponent("copyToClipboardAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$19.INSTANCE))}, new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$20(this));
            }
            objectDefinitionBuilder5.getAsyncFunctions().put("copyToClipboardAsync", asyncFunction5);
            ObjectDefinitionBuilder objectDefinitionBuilder6 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction6 = new AsyncFunctionWithPromiseComponent("fireCallback", new AnyType[0], new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$21());
            } else {
                asyncFunction6 = new AsyncFunctionComponent("fireCallback", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$22.INSTANCE))}, new DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$23());
            }
            objectDefinitionBuilder6.getAsyncFunctions().put("fireCallback", asyncFunction6);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}
