package expo.modules.notifications.notifications.channels.managers;

import android.app.NotificationChannelGroup;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import expo.modules.core.arguments.ReadableArguments;
import java.util.List;

public class AndroidXNotificationsChannelGroupManager implements NotificationsChannelGroupManager {
    private final NotificationManagerCompat mNotificationManager;

    public AndroidXNotificationsChannelGroupManager(Context context) {
        this.mNotificationManager = NotificationManagerCompat.from(context);
    }

    public NotificationChannelGroup getNotificationChannelGroup(String str) {
        return this.mNotificationManager.getNotificationChannelGroup(str);
    }

    public List<NotificationChannelGroup> getNotificationChannelGroups() {
        return this.mNotificationManager.getNotificationChannelGroups();
    }

    public NotificationChannelGroup createNotificationChannelGroup(String str, CharSequence charSequence, ReadableArguments readableArguments) {
        NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(str, charSequence);
        configureGroupWithOptions(notificationChannelGroup, readableArguments);
        this.mNotificationManager.createNotificationChannelGroup(notificationChannelGroup);
        return notificationChannelGroup;
    }

    public void deleteNotificationChannelGroup(String str) {
        this.mNotificationManager.deleteNotificationChannelGroup(str);
    }

    /* access modifiers changed from: protected */
    public void configureGroupWithOptions(Object obj, ReadableArguments readableArguments) {
        if (obj instanceof NotificationChannelGroup) {
            NotificationChannelGroup notificationChannelGroup = (NotificationChannelGroup) obj;
            if (Build.VERSION.SDK_INT >= 28 && readableArguments.containsKey("description")) {
                notificationChannelGroup.setDescription(readableArguments.getString("description"));
            }
        }
    }
}
