package expo.modules.notifications.notifications.interfaces;

import java.io.Serializable;
import java.util.Date;

public interface SchedulableNotificationTrigger extends NotificationTrigger, Serializable {
    Date nextTriggerDate();
}
