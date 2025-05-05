package expo.modules.keepawake;

import androidx.tracing.Trace;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import expo.modules.core.interfaces.services.KeepAwakeManager;
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

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lexpo/modules/keepawake/KeepAwakeModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "keepAwakeManager", "Lexpo/modules/core/interfaces/services/KeepAwakeManager;", "getKeepAwakeManager", "()Lexpo/modules/core/interfaces/services/KeepAwakeManager;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-keep-awake_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeepAwakeModule.kt */
public final class KeepAwakeModule extends Module {
    /* access modifiers changed from: private */
    public final KeepAwakeManager getKeepAwakeManager() {
        Object obj;
        try {
            obj = getAppContext().getLegacyModuleRegistry().getModule(KeepAwakeManager.class);
        } catch (Exception unused) {
            obj = null;
        }
        KeepAwakeManager keepAwakeManager = (KeepAwakeManager) obj;
        if (keepAwakeManager != null) {
            return keepAwakeManager;
        }
        throw new MissingModuleException("KeepAwakeManager");
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoKeepAwake");
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent(RemoteConfigComponent.ACTIVATE_FILE_NAME, new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, KeepAwakeModule$definition$lambda$3$$inlined$AsyncFunction$1.INSTANCE))}, new KeepAwakeModule$definition$lambda$3$$inlined$AsyncFunction$2(this));
            } else {
                asyncFunction = new AsyncFunctionComponent(RemoteConfigComponent.ACTIVATE_FILE_NAME, new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, KeepAwakeModule$definition$lambda$3$$inlined$AsyncFunction$3.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, KeepAwakeModule$definition$lambda$3$$inlined$AsyncFunction$4.INSTANCE))}, new KeepAwakeModule$definition$lambda$3$$inlined$AsyncFunction$5(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put(RemoteConfigComponent.ACTIVATE_FILE_NAME, asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("deactivate", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, KeepAwakeModule$definition$lambda$3$$inlined$AsyncFunction$6.INSTANCE))}, new KeepAwakeModule$definition$lambda$3$$inlined$AsyncFunction$7(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("deactivate", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, KeepAwakeModule$definition$lambda$3$$inlined$AsyncFunction$8.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, KeepAwakeModule$definition$lambda$3$$inlined$AsyncFunction$9.INSTANCE))}, new KeepAwakeModule$definition$lambda$3$$inlined$AsyncFunction$10(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("deactivate", asyncFunction2);
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("isActivated", new AnyType[0], new KeepAwakeModule$definition$lambda$3$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("isActivated", asyncFunctionComponent);
            AsyncFunction asyncFunction3 = asyncFunctionComponent;
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}
