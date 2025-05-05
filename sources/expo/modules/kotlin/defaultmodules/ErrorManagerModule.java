package expo.modules.kotlin.defaultmodules;

import android.os.Bundle;
import androidx.tracing.Trace;
import expo.modules.kotlin.events.EventEmitter;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/kotlin/defaultmodules/ErrorManagerModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "reportExceptionToLogBox", "", "codedException", "Lexpo/modules/kotlin/exception/CodedException;", "reportWarningToLogBox", "warning", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ErrorManagerModule.kt */
public final class ErrorManagerModule extends Module {
    public ModuleDefinitionData definition() {
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoModulesCoreErrorManager");
            moduleDefinitionBuilder.Events("ExpoModulesCoreErrorManager.onNewException", "ExpoModulesCoreErrorManager.onNewWarning");
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    public final void reportExceptionToLogBox(CodedException codedException) {
        Intrinsics.checkNotNullParameter(codedException, "codedException");
        EventEmitter eventEmitter = getAppContext().eventEmitter(this);
        if (eventEmitter != null) {
            Bundle bundle = new Bundle();
            String message = codedException.getMessage();
            if (message == null) {
                message = codedException.toString();
            }
            bundle.putString("message", message);
            Unit unit = Unit.INSTANCE;
            eventEmitter.emit("ExpoModulesCoreErrorManager.onNewException", bundle);
        }
    }

    public final void reportWarningToLogBox(String str) {
        Intrinsics.checkNotNullParameter(str, "warning");
        EventEmitter eventEmitter = getAppContext().eventEmitter(this);
        if (eventEmitter != null) {
            Bundle bundle = new Bundle();
            bundle.putString("message", str);
            Unit unit = Unit.INSTANCE;
            eventEmitter.emit("ExpoModulesCoreErrorManager.onNewWarning", bundle);
        }
    }
}
