package expo.modules.notifications.notifications.background;

import expo.modules.interfaces.taskManager.TaskManagerInterface;
import expo.modules.notifications.ModuleNotFoundException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lexpo/modules/interfaces/taskManager/TaskManagerInterface;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoBackgroundNotificationTasksModule.kt */
final class ExpoBackgroundNotificationTasksModule$taskManager$2 extends Lambda implements Function0<TaskManagerInterface> {
    final /* synthetic */ ExpoBackgroundNotificationTasksModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExpoBackgroundNotificationTasksModule$taskManager$2(ExpoBackgroundNotificationTasksModule expoBackgroundNotificationTasksModule) {
        super(0);
        this.this$0 = expoBackgroundNotificationTasksModule;
    }

    public final TaskManagerInterface invoke() {
        Object obj;
        try {
            obj = this.this$0.getAppContext().getLegacyModuleRegistry().getModule(TaskManagerInterface.class);
        } catch (Exception unused) {
            obj = null;
        }
        TaskManagerInterface taskManagerInterface = (TaskManagerInterface) obj;
        if (taskManagerInterface != null) {
            return taskManagerInterface;
        }
        throw new ModuleNotFoundException(Reflection.getOrCreateKotlinClass(TaskManagerInterface.class));
    }
}
