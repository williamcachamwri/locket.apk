package expo.modules.notifications.notifications.emitting;

import expo.modules.notifications.notifications.interfaces.NotificationManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "expo/modules/kotlin/modules/ModuleDefinitionBuilder$OnCreate$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleDefinitionBuilder.kt */
public final class NotificationsEmitter$definition$lambda$3$$inlined$OnCreate$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ NotificationsEmitter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationsEmitter$definition$lambda$3$$inlined$OnCreate$1(NotificationsEmitter notificationsEmitter) {
        super(0);
        this.this$0 = notificationsEmitter;
    }

    public final void invoke() {
        NotificationsEmitter notificationsEmitter = this.this$0;
        Object singletonModule = notificationsEmitter.getAppContext().getLegacyModuleRegistry().getSingletonModule("NotificationManager", NotificationManager.class);
        if (singletonModule != null) {
            notificationsEmitter.notificationManager = (NotificationManager) singletonModule;
            NotificationManager access$getNotificationManager$p = this.this$0.notificationManager;
            if (access$getNotificationManager$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("notificationManager");
                access$getNotificationManager$p = null;
            }
            access$getNotificationManager$p.addListener(this.this$0);
            return;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }
}
