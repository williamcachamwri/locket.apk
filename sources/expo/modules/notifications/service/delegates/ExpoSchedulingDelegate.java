package expo.modules.notifications.service.delegates;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.core.app.AlarmManagerCompat;
import androidx.core.app.NotificationCompat;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.notifications.service.interfaces.SchedulingDelegate;
import io.sentry.SentryBaseEvent;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Collection;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0016\u0010\u0017\u001a\u00020\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u0010H\u0016J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0011H\u0016J\u0018\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u0016H\u0016J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\""}, d2 = {"Lexpo/modules/notifications/service/delegates/ExpoSchedulingDelegate;", "Lexpo/modules/notifications/service/interfaces/SchedulingDelegate;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "alarmManager", "Landroid/app/AlarmManager;", "getAlarmManager", "()Landroid/app/AlarmManager;", "getContext", "()Landroid/content/Context;", "store", "Lexpo/modules/notifications/service/delegates/SharedPreferencesNotificationsStore;", "getStore", "()Lexpo/modules/notifications/service/delegates/SharedPreferencesNotificationsStore;", "getAllScheduledNotifications", "", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "getScheduledNotification", "identifier", "", "removeAllScheduledNotifications", "", "removeScheduledNotifications", "identifiers", "scheduleNotification", "request", "setupAlarm", "triggerAtMillis", "", "operation", "Landroid/app/PendingIntent;", "setupScheduledNotifications", "triggerNotification", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoSchedulingDelegate.kt */
public final class ExpoSchedulingDelegate implements SchedulingDelegate {
    private final AlarmManager alarmManager;
    private final Context context;
    private final SharedPreferencesNotificationsStore store;

    public ExpoSchedulingDelegate(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.store = new SharedPreferencesNotificationsStore(context2);
        Object systemService = context2.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        this.alarmManager = (AlarmManager) systemService;
    }

    /* access modifiers changed from: protected */
    public final Context getContext() {
        return this.context;
    }

    /* access modifiers changed from: protected */
    public final SharedPreferencesNotificationsStore getStore() {
        return this.store;
    }

    /* access modifiers changed from: protected */
    public final AlarmManager getAlarmManager() {
        return this.alarmManager;
    }

    public void setupScheduledNotifications() {
        for (NotificationRequest notificationRequest : this.store.getAllNotificationRequests()) {
            try {
                scheduleNotification(notificationRequest);
            } catch (Exception e) {
                String identifier = notificationRequest.getIdentifier();
                SentryLogcatAdapter.w("expo-notifications", "Notification " + identifier + " could not have been scheduled: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public Collection<NotificationRequest> getAllScheduledNotifications() {
        return this.store.getAllNotificationRequests();
    }

    public NotificationRequest getScheduledNotification(String str) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        try {
            return this.store.getNotificationRequest(str);
        } catch (IOException | ClassNotFoundException | NullPointerException unused) {
            return null;
        }
    }

    public void scheduleNotification(NotificationRequest notificationRequest) {
        Intrinsics.checkNotNullParameter(notificationRequest, SentryBaseEvent.JsonKeys.REQUEST);
        if (notificationRequest.getTrigger() == null) {
            NotificationsService.Companion.receive$default(NotificationsService.Companion, this.context, new Notification(notificationRequest), (ResultReceiver) null, 4, (Object) null);
        } else if (notificationRequest.getTrigger() instanceof SchedulableNotificationTrigger) {
            NotificationTrigger trigger = notificationRequest.getTrigger();
            Intrinsics.checkNotNull(trigger, "null cannot be cast to non-null type expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger");
            Date nextTriggerDate = ((SchedulableNotificationTrigger) trigger).nextTriggerDate();
            if (nextTriggerDate == null) {
                Log.d("expo-notifications", "Notification request \"" + notificationRequest.getIdentifier() + "\" will not trigger in the future, removing.");
                NotificationsService.Companion companion = NotificationsService.Companion;
                Context context2 = this.context;
                String identifier = notificationRequest.getIdentifier();
                Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
                NotificationsService.Companion.removeScheduledNotification$default(companion, context2, identifier, (ResultReceiver) null, 4, (Object) null);
                return;
            }
            this.store.saveNotificationRequest(notificationRequest);
            long time = nextTriggerDate.getTime();
            NotificationsService.Companion companion2 = NotificationsService.Companion;
            Context context3 = this.context;
            String identifier2 = notificationRequest.getIdentifier();
            Intrinsics.checkNotNullExpressionValue(identifier2, "getIdentifier(...)");
            setupAlarm(time, companion2.createNotificationTrigger(context3, identifier2));
        } else {
            String identifier3 = notificationRequest.getIdentifier();
            throw new IllegalArgumentException("Notification request \"" + identifier3 + "\" does not have a schedulable trigger (it's " + notificationRequest.getTrigger() + "). Refusing to schedule.");
        }
    }

    public void triggerNotification(String str) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        try {
            NotificationRequest notificationRequest = this.store.getNotificationRequest(str);
            Intrinsics.checkNotNull(notificationRequest);
            NotificationsService.Companion.receive$default(NotificationsService.Companion, this.context, new Notification(notificationRequest), (ResultReceiver) null, 4, (Object) null);
            NotificationsService.Companion.schedule$default(NotificationsService.Companion, this.context, notificationRequest, (ResultReceiver) null, 4, (Object) null);
        } catch (ClassNotFoundException e) {
            SentryLogcatAdapter.e("expo-notifications", "An exception occurred while triggering notification " + str + ", removing. " + e.getMessage());
            e.printStackTrace();
            NotificationsService.Companion.removeScheduledNotification$default(NotificationsService.Companion, this.context, str, (ResultReceiver) null, 4, (Object) null);
        } catch (InvalidClassException e2) {
            SentryLogcatAdapter.e("expo-notifications", "An exception occurred while triggering notification " + str + ", removing. " + e2.getMessage());
            e2.printStackTrace();
            NotificationsService.Companion.removeScheduledNotification$default(NotificationsService.Companion, this.context, str, (ResultReceiver) null, 4, (Object) null);
        } catch (NullPointerException e3) {
            SentryLogcatAdapter.e("expo-notifications", "An exception occurred while triggering notification " + str + ", removing. " + e3.getMessage());
            e3.printStackTrace();
            NotificationsService.Companion.removeScheduledNotification$default(NotificationsService.Companion, this.context, str, (ResultReceiver) null, 4, (Object) null);
        }
    }

    public void removeScheduledNotifications(Collection<String> collection) {
        Intrinsics.checkNotNullParameter(collection, NotificationsService.IDENTIFIERS_KEY);
        for (String str : collection) {
            this.alarmManager.cancel(NotificationsService.Companion.createNotificationTrigger(this.context, str));
            this.store.removeNotificationRequest(str);
        }
    }

    public void removeAllScheduledNotifications() {
        for (String createNotificationTrigger : this.store.removeAllNotificationRequests()) {
            this.alarmManager.cancel(NotificationsService.Companion.createNotificationTrigger(this.context, createNotificationTrigger));
        }
    }

    private final void setupAlarm(long j, PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT < 31 || this.alarmManager.canScheduleExactAlarms()) {
            AlarmManagerCompat.setExactAndAllowWhileIdle(this.alarmManager, 0, j, pendingIntent);
        } else {
            AlarmManagerCompat.setAndAllowWhileIdle(this.alarmManager, 0, j, pendingIntent);
        }
    }
}
