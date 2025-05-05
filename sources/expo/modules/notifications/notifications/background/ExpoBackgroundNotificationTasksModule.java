package expo.modules.notifications.notifications.background;

import androidx.tracing.Trace;
import expo.modules.interfaces.taskManager.TaskManagerInterface;
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

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lexpo/modules/notifications/notifications/background/ExpoBackgroundNotificationTasksModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "taskManager", "Lexpo/modules/interfaces/taskManager/TaskManagerInterface;", "getTaskManager", "()Lexpo/modules/interfaces/taskManager/TaskManagerInterface;", "taskManager$delegate", "Lkotlin/Lazy;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoBackgroundNotificationTasksModule.kt */
public final class ExpoBackgroundNotificationTasksModule extends Module {
    private final Lazy taskManager$delegate = LazyKt.lazy(new ExpoBackgroundNotificationTasksModule$taskManager$2(this));

    /* access modifiers changed from: private */
    public final TaskManagerInterface getTaskManager() {
        return (TaskManagerInterface) this.taskManager$delegate.getValue();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoBackgroundNotificationTasksModule");
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("registerTaskAsync", new AnyType[0], new ExpoBackgroundNotificationTasksModule$definition$lambda$2$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("registerTaskAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ExpoBackgroundNotificationTasksModule$definition$lambda$2$$inlined$AsyncFunction$2.INSTANCE))}, new ExpoBackgroundNotificationTasksModule$definition$lambda$2$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("registerTaskAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("unregisterTaskAsync", new AnyType[0], new ExpoBackgroundNotificationTasksModule$definition$lambda$2$$inlined$AsyncFunction$4(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("unregisterTaskAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ExpoBackgroundNotificationTasksModule$definition$lambda$2$$inlined$AsyncFunction$5.INSTANCE))}, new ExpoBackgroundNotificationTasksModule$definition$lambda$2$$inlined$AsyncFunction$6(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("unregisterTaskAsync", asyncFunction2);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}
