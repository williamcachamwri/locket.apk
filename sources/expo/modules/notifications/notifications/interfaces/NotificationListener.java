package expo.modules.notifications.notifications.interfaces;

import android.os.Bundle;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationResponse;

public interface NotificationListener {
    void onNotificationReceived(Notification notification) {
    }

    void onNotificationResponseIntentReceived(Bundle bundle) {
    }

    boolean onNotificationResponseReceived(NotificationResponse notificationResponse) {
        return false;
    }

    void onNotificationsDropped() {
    }
}
