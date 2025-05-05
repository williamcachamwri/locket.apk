package expo.modules.notifications.notifications.handling;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.tracing.Trace;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.notifications.ExceptionsKt;
import expo.modules.notifications.NotificationWasAlreadyHandledException;
import expo.modules.notifications.notifications.interfaces.NotificationListener;
import expo.modules.notifications.notifications.interfaces.NotificationManager;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehavior;
import expo.modules.notifications.service.NotificationsService;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0016J \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u000e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lexpo/modules/notifications/notifications/handling/NotificationsHandler;", "Lexpo/modules/kotlin/modules/Module;", "Lexpo/modules/notifications/notifications/interfaces/NotificationListener;", "()V", "handler", "Landroid/os/Handler;", "moduleRegistry", "Lexpo/modules/core/ModuleRegistry;", "notificationManager", "Lexpo/modules/notifications/notifications/interfaces/NotificationManager;", "notificationsHandlerThread", "Landroid/os/HandlerThread;", "tasksMap", "", "", "Lexpo/modules/notifications/notifications/handling/SingleNotificationHandlerTask;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "handleNotificationAsync", "", "identifier", "behavior", "Lexpo/modules/notifications/notifications/handling/NotificationBehaviourRecord;", "promise", "Lexpo/modules/kotlin/Promise;", "onNotificationReceived", "notification", "Lexpo/modules/notifications/notifications/model/Notification;", "onTaskFinished", "task", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationsHandler.kt */
public class NotificationsHandler extends Module implements NotificationListener {
    /* access modifiers changed from: private */
    public Handler handler;
    /* access modifiers changed from: private */
    public ModuleRegistry moduleRegistry;
    /* access modifiers changed from: private */
    public NotificationManager notificationManager;
    /* access modifiers changed from: private */
    public HandlerThread notificationsHandlerThread;
    /* access modifiers changed from: private */
    public final Map<String, SingleNotificationHandlerTask> tasksMap = new LinkedHashMap();

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoNotificationsHandlerModule");
            moduleDefinitionBuilder.Events("onHandleNotification", "onHandleNotificationTimeout");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new NotificationsHandler$definition$lambda$3$$inlined$OnCreate$1(this, moduleDefinitionBuilder)));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new NotificationsHandler$definition$lambda$3$$inlined$OnDestroy$1(this)));
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("handleNotificationAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationsHandler$definition$lambda$3$$inlined$AsyncFunction$1.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(NotificationBehaviourRecord.class), false, NotificationsHandler$definition$lambda$3$$inlined$AsyncFunction$2.INSTANCE))}, new NotificationsHandler$definition$lambda$3$$inlined$AsyncFunction$3(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("handleNotificationAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationsHandler$definition$lambda$3$$inlined$AsyncFunction$4.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(NotificationBehaviourRecord.class), false, NotificationsHandler$definition$lambda$3$$inlined$AsyncFunction$5.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NotificationsHandler$definition$lambda$3$$inlined$AsyncFunction$6.INSTANCE))}, new NotificationsHandler$definition$lambda$3$$inlined$AsyncFunction$7(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("handleNotificationAsync", asyncFunction);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final void handleNotificationAsync(String str, NotificationBehaviourRecord notificationBehaviourRecord, Promise promise) {
        SingleNotificationHandlerTask singleNotificationHandlerTask = this.tasksMap.get(str);
        if (singleNotificationHandlerTask != null) {
            singleNotificationHandlerTask.handleResponse(new NotificationBehavior(notificationBehaviourRecord.getShouldShowAlert(), notificationBehaviourRecord.getShouldPlaySound(), notificationBehaviourRecord.getShouldSetBadge(), notificationBehaviourRecord.getPriority()), ExceptionsKt.toLegacyPromise(promise));
            return;
        }
        throw new NotificationWasAlreadyHandledException(str);
    }

    public void onNotificationReceived(Notification notification) {
        Intrinsics.checkNotNullParameter(notification, NotificationsService.NOTIFICATION_KEY);
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            EventEmitter eventEmitter = getAppContext().eventEmitter(this);
            Handler handler2 = this.handler;
            if (handler2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("handler");
                handler2 = null;
            }
            SingleNotificationHandlerTask singleNotificationHandlerTask = new SingleNotificationHandlerTask(reactContext, eventEmitter, handler2, notification, this);
            Map<String, SingleNotificationHandlerTask> map = this.tasksMap;
            String identifier = singleNotificationHandlerTask.getIdentifier();
            Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
            map.put(identifier, singleNotificationHandlerTask);
            singleNotificationHandlerTask.start();
        }
    }

    public final void onTaskFinished(SingleNotificationHandlerTask singleNotificationHandlerTask) {
        Intrinsics.checkNotNullParameter(singleNotificationHandlerTask, "task");
        this.tasksMap.remove(singleNotificationHandlerTask.getIdentifier());
    }
}
