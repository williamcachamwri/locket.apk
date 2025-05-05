package expo.modules.notifications.notifications.handling;

import android.os.Handler;
import android.os.HandlerThread;
import expo.modules.core.ModuleRegistry;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.notifications.notifications.interfaces.NotificationManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "expo/modules/kotlin/modules/ModuleDefinitionBuilder$OnCreate$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleDefinitionBuilder.kt */
public final class NotificationsHandler$definition$lambda$3$$inlined$OnCreate$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ ModuleDefinitionBuilder $this_ModuleDefinition$inlined;
    final /* synthetic */ NotificationsHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationsHandler$definition$lambda$3$$inlined$OnCreate$1(NotificationsHandler notificationsHandler, ModuleDefinitionBuilder moduleDefinitionBuilder) {
        super(0);
        this.this$0 = notificationsHandler;
        this.$this_ModuleDefinition$inlined = moduleDefinitionBuilder;
    }

    public final void invoke() {
        NotificationsHandler notificationsHandler = this.this$0;
        notificationsHandler.moduleRegistry = notificationsHandler.getAppContext().getLegacyModuleRegistry();
        NotificationsHandler notificationsHandler2 = this.this$0;
        ModuleRegistry access$getModuleRegistry$p = notificationsHandler2.moduleRegistry;
        HandlerThread handlerThread = null;
        if (access$getModuleRegistry$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moduleRegistry");
            access$getModuleRegistry$p = null;
        }
        Object singletonModule = access$getModuleRegistry$p.getSingletonModule("NotificationManager", NotificationManager.class);
        if (singletonModule != null) {
            notificationsHandler2.notificationManager = (NotificationManager) singletonModule;
            NotificationManager access$getNotificationManager$p = this.this$0.notificationManager;
            if (access$getNotificationManager$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("notificationManager");
                access$getNotificationManager$p = null;
            }
            access$getNotificationManager$p.addListener(this.this$0);
            this.this$0.notificationsHandlerThread = new HandlerThread("NotificationsHandlerThread - " + this.$this_ModuleDefinition$inlined.getClass());
            HandlerThread access$getNotificationsHandlerThread$p = this.this$0.notificationsHandlerThread;
            if (access$getNotificationsHandlerThread$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("notificationsHandlerThread");
                access$getNotificationsHandlerThread$p = null;
            }
            access$getNotificationsHandlerThread$p.start();
            NotificationsHandler notificationsHandler3 = this.this$0;
            HandlerThread access$getNotificationsHandlerThread$p2 = this.this$0.notificationsHandlerThread;
            if (access$getNotificationsHandlerThread$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("notificationsHandlerThread");
            } else {
                handlerThread = access$getNotificationsHandlerThread$p2;
            }
            notificationsHandler3.handler = new Handler(handlerThread.getLooper());
            return;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }
}
