package expo.modules.notifications.notifications.interfaces;

public interface NotificationManager {
    void addListener(NotificationListener notificationListener);

    void removeListener(NotificationListener notificationListener);
}
