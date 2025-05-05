package expo.modules.devmenu.modules;

import androidx.tracing.Trace;
import com.facebook.react.bridge.ReadableMap;
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
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lexpo/modules/devmenu/modules/DevMenuPreferences;", "Lexpo/modules/kotlin/modules/Module;", "()V", "preferencesHandel", "Lexpo/modules/devmenu/modules/DevMenuPreferencesHandle;", "getPreferencesHandel", "()Lexpo/modules/devmenu/modules/DevMenuPreferencesHandle;", "preferencesHandel$delegate", "Lkotlin/Lazy;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuPreferences.kt */
public final class DevMenuPreferences extends Module {
    private final Lazy preferencesHandel$delegate = LazyKt.lazy(new DevMenuPreferences$preferencesHandel$2(this));

    /* access modifiers changed from: private */
    public final DevMenuPreferencesHandle getPreferencesHandel() {
        return (DevMenuPreferencesHandle) this.preferencesHandel$delegate.getValue();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("DevMenuPreferences");
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("getPreferencesAsync", new AnyType[0], new DevMenuPreferences$definition$lambda$2$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getPreferencesAsync", asyncFunctionComponent);
            AsyncFunction asyncFunction2 = asyncFunctionComponent;
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (ReadableMap.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("setPreferencesAsync", new AnyType[0], new DevMenuPreferences$definition$lambda$2$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("setPreferencesAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableMap.class), false, DevMenuPreferences$definition$lambda$2$$inlined$AsyncFunction$2.INSTANCE))}, new DevMenuPreferences$definition$lambda$2$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("setPreferencesAsync", asyncFunction);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}
