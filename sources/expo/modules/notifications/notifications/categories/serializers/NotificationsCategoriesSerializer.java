package expo.modules.notifications.notifications.categories.serializers;

import android.os.Bundle;
import expo.modules.notifications.notifications.model.NotificationCategory;

public interface NotificationsCategoriesSerializer {
    Bundle toBundle(NotificationCategory notificationCategory);
}
