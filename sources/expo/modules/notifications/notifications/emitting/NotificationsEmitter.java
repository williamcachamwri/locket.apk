package expo.modules.notifications.notifications.emitting;

import android.os.Bundle;
import androidx.tracing.Trace;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.notifications.notifications.NotificationSerializer;
import expo.modules.notifications.notifications.interfaces.NotificationListener;
import expo.modules.notifications.notifications.interfaces.NotificationManager;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationResponse;
import expo.modules.notifications.service.NotificationsService;
import io.sentry.protocol.Response;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lexpo/modules/notifications/notifications/emitting/NotificationsEmitter;", "Lexpo/modules/kotlin/modules/Module;", "Lexpo/modules/notifications/notifications/interfaces/NotificationListener;", "()V", "lastNotificationResponseBundle", "Landroid/os/Bundle;", "notificationManager", "Lexpo/modules/notifications/notifications/interfaces/NotificationManager;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "onNotificationReceived", "", "notification", "Lexpo/modules/notifications/notifications/model/Notification;", "onNotificationResponseIntentReceived", "extras", "onNotificationResponseReceived", "", "response", "Lexpo/modules/notifications/notifications/model/NotificationResponse;", "onNotificationsDropped", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationsEmitter.kt */
public class NotificationsEmitter extends Module implements NotificationListener {
    /* access modifiers changed from: private */
    public Bundle lastNotificationResponseBundle;
    /* access modifiers changed from: private */
    public NotificationManager notificationManager;

    public ModuleDefinitionData definition() {
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoNotificationsEmitter");
            moduleDefinitionBuilder.Events("onDidReceiveNotification", "onNotificationsDeleted", "onDidReceiveNotificationResponse");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new NotificationsEmitter$definition$lambda$3$$inlined$OnCreate$1(this)));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new NotificationsEmitter$definition$lambda$3$$inlined$OnDestroy$1(this)));
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("getLastNotificationResponseAsync", new AnyType[0], new NotificationsEmitter$definition$lambda$3$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getLastNotificationResponseAsync", asyncFunctionComponent);
            AsyncFunction asyncFunction = asyncFunctionComponent;
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    public void onNotificationReceived(Notification notification) {
        Intrinsics.checkNotNullParameter(notification, NotificationsService.NOTIFICATION_KEY);
        sendEvent("onDidReceiveNotification", NotificationSerializer.toBundle(notification));
    }

    public boolean onNotificationResponseReceived(NotificationResponse notificationResponse) {
        Intrinsics.checkNotNullParameter(notificationResponse, Response.TYPE);
        Bundle bundle = NotificationSerializer.toBundle(notificationResponse);
        this.lastNotificationResponseBundle = bundle;
        sendEvent("onDidReceiveNotificationResponse", bundle);
        return true;
    }

    public void onNotificationResponseIntentReceived(Bundle bundle) {
        Bundle responseBundleFromExtras = NotificationSerializer.toResponseBundleFromExtras(bundle);
        this.lastNotificationResponseBundle = responseBundleFromExtras;
        sendEvent("onDidReceiveNotificationResponse", responseBundleFromExtras);
    }

    public void onNotificationsDropped() {
        sendEvent("onNotificationsDeleted", Bundle.EMPTY);
    }
}
