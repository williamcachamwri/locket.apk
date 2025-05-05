package expo.modules.splashscreen;

import androidx.tracing.Trace;
import expo.modules.kotlin.Promise;
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
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lexpo/modules/splashscreen/SplashScreenModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SplashScreenModule.kt */
public final class SplashScreenModule extends Module {
    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoSplashScreen");
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("preventAutoHideAsync", new AnyType[0], new SplashScreenModule$definition$lambda$6$$inlined$AsyncFunction$1(this, moduleDefinitionBuilder));
            } else {
                asyncFunction = new AsyncFunctionComponent("preventAutoHideAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, SplashScreenModule$definition$lambda$6$$inlined$AsyncFunction$2.INSTANCE))}, new SplashScreenModule$definition$lambda$6$$inlined$AsyncFunction$3(this, moduleDefinitionBuilder));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("preventAutoHideAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("hideAsync", new AnyType[0], new SplashScreenModule$definition$lambda$6$$inlined$AsyncFunction$4(this, moduleDefinitionBuilder));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("hideAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, SplashScreenModule$definition$lambda$6$$inlined$AsyncFunction$5.INSTANCE))}, new SplashScreenModule$definition$lambda$6$$inlined$AsyncFunction$6(this, moduleDefinitionBuilder));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("hideAsync", asyncFunction2);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}
