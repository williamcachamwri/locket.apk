package expo.modules.notifications.notifications.interfaces;

import android.app.Notification;

public interface NotificationPresentationEffect {
    boolean onNotificationPresentationFailed(String str, int i, Notification notification);

    boolean onNotificationPresented(String str, int i, Notification notification);
}
