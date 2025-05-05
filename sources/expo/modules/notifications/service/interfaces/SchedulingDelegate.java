package expo.modules.notifications.service.interfaces;

import expo.modules.notifications.notifications.model.NotificationRequest;
import java.util.Collection;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\u0016\u0010\n\u001a\u00020\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H&J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0004H&J\b\u0010\u000e\u001a\u00020\tH&J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\u0010"}, d2 = {"Lexpo/modules/notifications/service/interfaces/SchedulingDelegate;", "", "getAllScheduledNotifications", "", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "getScheduledNotification", "identifier", "", "removeAllScheduledNotifications", "", "removeScheduledNotifications", "identifiers", "scheduleNotification", "request", "setupScheduledNotifications", "triggerNotification", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SchedulingDelegate.kt */
public interface SchedulingDelegate {
    Collection<NotificationRequest> getAllScheduledNotifications();

    NotificationRequest getScheduledNotification(String str);

    void removeAllScheduledNotifications();

    void removeScheduledNotifications(Collection<String> collection);

    void scheduleNotification(NotificationRequest notificationRequest);

    void setupScheduledNotifications();

    void triggerNotification(String str);
}
